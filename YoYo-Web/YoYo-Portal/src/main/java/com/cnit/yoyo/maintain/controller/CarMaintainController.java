package com.cnit.yoyo.maintain.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.membercar.model.MemberCar;
import com.cnit.yoyo.membercar.model.MemberCarQryDTO;
import com.cnit.yoyo.model.car.dto.CarMaintainQryDTO;
import com.cnit.yoyo.model.car.dto.MaintainProductDTO;
import com.cnit.yoyo.model.goods.GoodsTimePrice;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.DateUtils;


/**  
* @Title: CarMaintainController.java
* @Package com.cnit.yoyo.maintain.controller
* @Description: TODO(用一句话描述该文件做什么)
* @Author 王鹏
* @date 2015-6-15 下午5:51:38
* @version V1.0  
*/ 
@Controller
@RequestMapping("/carMaintain")
public class CarMaintainController {
	
	@Autowired
	private RemoteService  memberService ;
	
	@Autowired
	public RemoteService itemService;
	
	/**
	  * @description <b>进入自助保养</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-29
	  * @param @param request
	  * @param @param memberCar
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/step1")
	public String setp1(HttpServletRequest request,MemberCar memberCar){
		try{
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-06");
			MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	    	if(null!=memberDo){
	    		memberCar.setLastmodified(new Date());
	    		memberCar.setMemberId(Integer.parseInt(memberDo.getMemberId()));
	    		memberService.doService(new RequestObject(headObject, memberCar));
	    		MemberCarQryDTO carQryDTO=new MemberCarQryDTO();
	    		headObject = CommonHeadUtil.geneHeadObject(request, "030114-08");
	    		carQryDTO.setMemberId(Long.parseLong(memberDo.getMemberId()));
     	    	ResultObject result = memberService.doService(new RequestObject(headObject, carQryDTO));
     	    	request.setAttribute("defaultCar", result.getContent());
	    	}
		}catch (Exception e) {
			e.printStackTrace();
		}
    	return "pages/biz/maintain/setp1";
	}
	
	/**
	  * @description <b>查询保养项</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-29
	  * @param @param request
	  * @param @param qryDTO
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/step2")
	public String setp2(HttpServletRequest request,CarMaintainQryDTO qryDTO){
		try{
			Calendar calendar=Calendar.getInstance();
			calendar.set(Calendar.YEAR, qryDTO.getYear());
			calendar.set(Calendar.MONTH, qryDTO.getMonth()-1);
			qryDTO.setMaintainMonth(DateUtils.monthSpace(calendar.getTime()));
			HeadObject head= CommonHeadUtil.geneHeadObject(request, "010803-11");
			ResultObject resultObject = itemService.doService(new RequestObject(head,qryDTO));
			Map<String, Object> resultMap = (Map<String, Object>) resultObject.getContent();
			request.setAttribute("carMaintainAccessoryItems", resultMap.get("carMaintainAccessoryItems"));
			request.setAttribute("carMaintain", resultMap.get("carMaintain"));
			request.setAttribute("qryDTO", qryDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/biz/maintain/setp2";
	}
	
	/**
	  * @description <b>查询保养项默认商品</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-29
	  * @param @param request
	  * @param @param qryDTO
	  * @param @return
	  * @return String
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping("/step3")
	public String setp3(HttpServletRequest request,CarMaintainQryDTO qryDTO){
		try{
			HeadObject head= CommonHeadUtil.geneHeadObject(request, "010803-09");
			ResultObject resultObject = itemService.doService(new RequestObject(head,qryDTO));
			this.loadGoodsCat(request, qryDTO);
			List<MaintainProductDTO> maintainProductDTOs=(List<MaintainProductDTO>) resultObject.getContent();
			for (MaintainProductDTO maintainProductDTO : maintainProductDTOs) {
				head = CommonHeadUtil.geneHeadObject(request, "011601-01");
				Map<String, Object> param=new HashMap<String,Object>();
    			param.put("priceDate", DateUtils.getDate(qryDTO.getAppDate(), "yyyy-MM-dd"));
    			param.put("goodsId", maintainProductDTO.getGoodsId());
    			resultObject = itemService.doService(new RequestObject(head, param));
    			if(null!=resultObject.getContent()){
    				GoodsTimePrice goodsTimePrice=(GoodsTimePrice)resultObject.getContent();
    				maintainProductDTO.setPrice(goodsTimePrice.getPrice());
    			}
			}
			request.setAttribute("carMaintain", maintainProductDTOs);
			request.setAttribute("qryDTO", qryDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/biz/maintain/setp3";
	}
	
	private void loadGoodsCat(HttpServletRequest request,CarMaintainQryDTO qryDTO){
		try{
			HeadObject head= CommonHeadUtil.geneHeadObject(request, "010803-12");
			ResultObject resultObject = itemService.doService(new RequestObject(head,qryDTO));
			request.setAttribute("catalogs", resultObject.getContent());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	  * @description <b>查询可做当前保养的店铺</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-29
	  * @param @param request
	  * @param @param qryDTO
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/findMaintainCompany")
	public Object findMaintainCompany(HttpServletRequest request,CarMaintainQryDTO qryDTO){
		HeadObject head= CommonHeadUtil.geneHeadObject(request, "010803-08");
		try{
			ResultObject resultObject = itemService.doService(new RequestObject(head,qryDTO));
			return resultObject;
		}catch (Exception e) {
			e.printStackTrace();
			return CommonUtil.processExpction(head);
		}
	}
	
	/**
	  * @description <b>查询可选配件</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-29
	  * @param @param request
	  * @param @param qryDTO
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/findOptionalAccessories")
	public Object findOptionalAccessories(HttpServletRequest request,CarMaintainQryDTO qryDTO){
		HeadObject head= CommonHeadUtil.geneHeadObject(request, "010803-10");
		try{
			ResultObject resultObject = itemService.doService(new RequestObject(head,qryDTO));
			return resultObject;
		}catch (Exception e) {
			e.printStackTrace();
			return CommonUtil.processExpction(head);
		}
	}
	
}
