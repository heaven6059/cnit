package com.cnit.yoyo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.GoodsCat;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.site.Ad;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 门户首页
 * 
 * @author wanghb
 * @date 2015-04-22
 * @version 1.0.0
 */
@Controller
public class IndexController {
	/**
	 * 登出页面路径
	 */
	private final static String NEED_REFERER = "/cart/index";
	private final static String NEED_REFERER1 = "/goodsManager/goodsIndex";

	public static final Logger log = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	public RemoteService searchClientService;
	@Autowired
	public RemoteService itemService;
	@Autowired
	public RemoteService otherService;
	
	@RequestMapping("/register/login")
	public String toSignin(HttpServletRequest request) {
		String returnURL = request.getHeader("Referer");
		if (StringUtil.isEmpty(returnURL)) {
			returnURL = "/index";
		}
		if (returnURL.contains(NEED_REFERER) || returnURL == "/index" || returnURL.contains(NEED_REFERER1)) {
			request.setAttribute("ReturnURL", returnURL);
		}
		return "pages/sign/signin";
	}

	// 2015.05.27 xiaox 修改 添加参数，跳转到指定类型注册：会员与商家注册
	@RequestMapping("/register/signup")
	public Object toSignup(HttpServletRequest request, Map<String, String> map) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/sign/signup");
		try {
			String type = request.getParameter("type");
			map.put("type", type);
			Map<String, Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("currrentPage", 1);
			dataMap1.put("pageSize", 3);
			dataMap1.put("orderFile", "buyCount");
			dataMap1.put("buyCount", "1");
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject= searchClientService.doService(new RequestObject(headObject, dataMap1));
			mv.addObject("resultHot", resultObject.getContent());
		} catch (Exception e) {
			log.error("关键字搜索异常!" + e);
			mv.addObject("message", "关键字搜索异常!");
		}
		return mv;
	}

	@RequestMapping("/index")
	public Object index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pages/index");
		try {
		    //广告设置
		    HeadObject headObject2 = CommonHeadUtil.geneHeadObject(request, "990701-02");
		    ResultObject resultObject2 = otherService.doService(new RequestObject(headObject2));
		    Map<String,Object> map2 = (Map<String, Object>) resultObject2.getContent();
		    mv.addObject("homeHead",map2.get("homeHead"));
		    JSONObject obj2 = JSONObject.fromObject(((Ad)map2.get("homeHead")).getAdConfig());
		    mv.addObject("homeHeadAdConfig", obj2);
		    map2.remove("homeHead");
		    mv.addObject("adMap",JSONObject.fromObject(map2));
			//
		    Map<String, Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("currrentPage", 1);
			dataMap1.put("pageSize", 4);
			dataMap1.put("orderFile", "buyCount");
			dataMap1.put("buyCount", "1");
			HeadObject headObject10 = CommonHeadUtil.geneHeadObject(request, "050101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject10 = searchClientService.doService(new RequestObject(headObject10, dataMap1));
			mv.addObject("resultHot", resultObject10.getContent());

			mv.addObject("cateId", this.searchByCateName(4, "新车", "result", request, mv));// 新车
			mv.addObject("cateId3", this.searchByCateName(5, "保养", "mainResult", request, mv));// 保养
			mv.addObject("cateId2", this.searchByCateName(6, "配件", "accessResult", request, mv));// 配件
			request.getSession().setAttribute("imagePath", Configuration.getInstance().getConfigValue("images.url"));
		} catch (Exception e) {
		    e.printStackTrace();
			log.error("关键字搜索异常!" + e);
			mv.addObject("message", "关键字搜索异常!");
		}
		return mv;
	}

	private Object searchByCateName(Integer pageSize, String cateName, String key, HttpServletRequest request, ModelAndView mv) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("currrentPage", 1);
		dataMap.put("pageSize", pageSize);
		dataMap.put("orderFile", "lastModify");

		JSONObject params = new JSONObject();
		params.put("catName", cateName);
		params.put("disabled", "0");
		HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "2000020103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject1 = itemService.doService(new RequestObject(headObject1, params));
		List<GoodsCat> list = com.alibaba.fastjson.JSONArray.parseArray(resultObject1.getContent().toString(), GoodsCat.class);
		Integer cateId = 0;
		if (null != list && list.size() > 0) {
			cateId = list.get(0).getCatId();
		}
		dataMap.put("cateId", cateId);
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = searchClientService.doService(new RequestObject(headObject, dataMap));
		mv.addObject(key, resultObject.getContent());
		return cateId;
	}

	/**
	 * @description <b>是否登录</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-6-18
	 * @param @param request
	 * @param @return
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping("/islogin")
	public Object isLogin(HttpServletRequest request) {
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		HeadObject head = new HeadObject();
		if (null != memberListDo) {
			head.setRetCode(ErrorCode.SUCCESS);
			return new ResultObject(head);
		} else {
			return CommonUtil.notLoign(head);
		}
	}
}
