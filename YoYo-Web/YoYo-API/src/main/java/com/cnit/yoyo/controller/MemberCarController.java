package com.cnit.yoyo.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.api.vo.MemberCarDefVO;
import com.cnit.yoyo.api.vo.MemberCarDelVO;
import com.cnit.yoyo.api.vo.MemberCarGetdefVO;
import com.cnit.yoyo.api.vo.MemberCarQryVO;
import com.cnit.yoyo.api.vo.MemberCarSaveVO;
import com.cnit.yoyo.api.vo.MemberCarUpdVO;
import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.base.validation.ValidationResult;
import com.cnit.yoyo.base.validation.ValidationUtils;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.JsonDateValueProcessor;
import com.cnit.yoyo.util.JsonDecimalValueProcessor;

@Controller("memberCarController")
@RequestMapping("/membercar")
public class MemberCarController extends BaseController {

	@Autowired
	private RemoteService itemService;
	@Autowired
	private RemoteService memberService;

	/**
	 * @Description:我的车型列表页面
	 * @param qryDTO
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/memberCarList", method = RequestMethod.GET)
	public Object memberCarList(String data, HttpServletRequest request)
			throws Exception {
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject resultObjectJson =null;
		try {
			MemberCarQryVO qryDTO = (MemberCarQryVO) JSONObject.toBean(JSONObject.fromObject(data), MemberCarQryVO.class);
			headObject = CommonHeadUtil.geneHeadObject("memberCarService.memberCarList");
			ValidationResult bindingResult = ValidationUtils.validateEntity(qryDTO);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(
					headObject, qryDTO));
			JsonConfig jsonConfig = new JsonConfig();  
	        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
	        jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonDecimalValueProcessor());
	        resultObjectJson = JSONObject.fromObject(resultObject,jsonConfig);
		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObjectJson;
	}

	/**
	 * @Description:设为默认车型
	 * @param memberCar
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/setDefaulCar", method = RequestMethod.POST)
	@ResponseBody
	public Object setDefaulCar(String data, HttpServletRequest request) {
		ResultObject resultObject = null;
		HeadObject headObject = null;
		String sessionid=request.getAttribute("sessionid") == null ? "" :request.getAttribute("sessionid").toString();
		try {
			MemberCarDefVO memberCar = (MemberCarDefVO) JSONObject.toBean(JSONObject.fromObject(data), MemberCarDefVO.class);
			memberCar.setSessionid(sessionid);
			headObject = CommonHeadUtil.geneHeadObject("memberCarService.setDefaultCarForApp");
			ValidationResult bindingResult = ValidationUtils.validateEntity(memberCar);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			MemberListDo memberDo = APICommonUtil.getMemberListDo(sessionid);
			if (null != memberDo) {
				memberCar.setLastmodified(new Date());
				memberCar.setMemberId(Integer.parseInt(memberDo.getMemberId()));
				resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(
						headObject, memberCar));
			} else {
				headObject.setRetCode(ErrorCode.NOT_LOGIN);
				headObject.setRetMsg("sessionId不正确或者未登录");
				return new ResultObject(headObject);
			}
		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}

	/**
	 * 
	 * @Description: 保存我的车型
	 * @param request
	 * @param orderid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveMemberCar", method = RequestMethod.POST)
	public Object saveMemberCar(String data, HttpServletRequest request) {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		String sessionid=request.getAttribute("sessionid") == null ? "" :request.getAttribute("sessionid").toString();
		try {
			JSONObject json = JSONObject.fromObject(data);
			JSONArray jArray = json.getJSONArray("dataList");  //数据是批量的
			for(Iterator it = jArray.iterator();it.hasNext();){
				JSONObject subData = (JSONObject) it.next();
				MemberCarSaveVO memberCar = (MemberCarSaveVO) JSONObject.toBean(subData, MemberCarSaveVO.class);
				memberCar.setSessionid(sessionid);
				headObject = CommonHeadUtil.geneHeadObject("memberCarService.saveMemberCar");
				ValidationResult bindingResult = ValidationUtils.validateEntity(memberCar);
				if (bindingResult.isHasErrors()) {
					return elementErrors(headObject, bindingResult);
				}
				MemberListDo memberDo = APICommonUtil.getMemberListDo(sessionid);
				if (null != memberDo) {
					memberCar.setMemberId(Integer.parseInt(memberDo.getMemberId()));
					memberCar.setLastmodified(new Date());
					resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(
							headObject, memberCar));
					if (resultObject.getRetCode() == ErrorCode.THE_NUMBER_OVER) {
						resultObject.getHead().setRetMsg("车型数据超出规定数");
					}
				} else {
					headObject.setRetCode(ErrorCode.NOT_LOGIN);
					headObject.setRetMsg("sessionId不正确或者未登录");
					return new ResultObject(headObject);
				}
			}
		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}

	/**
	 * 
	 * @Description: 更新我的车型
	 * @param request
	 * @param orderid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMemberCar", method = RequestMethod.POST)
	public Object updateMemberCar(String data, HttpServletRequest request) {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		String sessionid=request.getAttribute("sessionid") == null ? "" :request.getAttribute("sessionid").toString();
		try {
			MemberCarUpdVO memberCarDTO = (MemberCarUpdVO) JSONObject.toBean(JSONObject.fromObject(data), MemberCarUpdVO.class);
			memberCarDTO.setSessionid(sessionid);
			headObject = CommonHeadUtil.geneHeadObject("memberCarService.updateMemberCar");
			ValidationResult bindingResult = ValidationUtils.validateEntity(memberCarDTO);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			MemberListDo memberDo = APICommonUtil.getMemberListDo(sessionid);
			if (null != memberDo) {
				memberCarDTO.setMemberId(Integer.parseInt(memberDo
						.getMemberId()));
				memberCarDTO.setLastmodified(new Date());
				resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(
						headObject, memberCarDTO));
			} else {
				headObject.setRetCode(ErrorCode.NOT_LOGIN);
				headObject.setRetMsg("sessionId不正确或者未登录");
				return new ResultObject(headObject);
			}

		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}

	/**
	 * @Description:删除用户车型
	 * @param request
	 * @param qryDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMemberCar", method = RequestMethod.POST)
	public Object deleteMemberCar(String data, HttpServletRequest request) {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		String sessionid=request.getAttribute("sessionid") == null ? "" :request.getAttribute("sessionid").toString();
		try {
			MemberCarDelVO qryDTO = (MemberCarDelVO) JSONObject.toBean(JSONObject.fromObject(data), MemberCarDelVO.class);
			qryDTO.setSessionid(sessionid);
			headObject = CommonHeadUtil.geneHeadObject( "memberCarService.deleteMemberCar");
			ValidationResult bindingResult = ValidationUtils
					.validateEntity(data);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			MemberListDo memberDo = APICommonUtil.getMemberListDo(sessionid);
			if (null != memberDo) {
				qryDTO.setMemberId(Long.parseLong(memberDo.getMemberId()));
				resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(
						headObject, qryDTO));
			} else {
				headObject.setRetCode(ErrorCode.NOT_LOGIN);
				headObject.setRetMsg("sessionId不正确或者未登录");
				return new ResultObject(headObject);
			}
		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}

	/**
	 * 
	 * @Description: 获取默认车型
	 * @param request
	 * @param orderid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findMemberDefaultCar", method = RequestMethod.GET)
	public Object findMemberDefaultCar(String data, HttpServletRequest request) {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		String sessionid=request.getAttribute("sessionid") == null ? "" :request.getAttribute("sessionid").toString();
		try {
			MemberCarGetdefVO memberCar = (MemberCarGetdefVO) JSONObject.toBean(JSONObject.fromObject(data),MemberCarGetdefVO.class);
			memberCar.setSessionid(sessionid);
			headObject = CommonHeadUtil.geneHeadObject("memberCarService.findMemberDefaultCar");
			ValidationResult bindingResult = ValidationUtils
					.validateEntity(data);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			MemberListDo memberDo = APICommonUtil.getMemberListDo(sessionid);
			if (null != memberDo) {
				memberCar.setMemberId(Long.parseLong(memberDo.getMemberId()));
				resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(
						headObject, memberCar));
			} else {
				headObject.setRetCode(ErrorCode.NOT_LOGIN);
				headObject.setRetMsg("sessionId不正确或者未登录");
				return new ResultObject(headObject);
			}
		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}

}
