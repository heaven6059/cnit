/**
 * 文 件 名   :  IndexController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-2 上午10:55:49
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.servlet.CookieUtil;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
public class MpIndexController {
	
	@Autowired
    private RemoteService otherService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		String loginStatus = (String) request.getSession().getAttribute(
				"loginStatus");
		Cookie autoLogin = CookieUtil.getCookieByName(request, "autoLogin");
		if (autoLogin != null && StringUtil.isEmpty(autoLogin.getValue())
				&& "1".equalsIgnoreCase(autoLogin.getValue())) {

		}
		if (GlobalStatic.ACCOUNT_STATUS_ON.equalsIgnoreCase(loginStatus)) {
			return "pages/biz/index";
		} else {
			return "redirect:/";
		}
	}
	
/*	@RequestMapping("/login")
    public String toSignin(){
        return "pages/biz/login";
    }*/
	@RequestMapping("/mpIndex")
	public String mpIndex(HttpServletRequest request){
		try{
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990901-01");
			Map<String, Object> param=new HashMap<String, Object>();
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, param));
			param = (Map<String, Object>) resultObject.getContent();
			request.setAttribute("AccountInfo",param.get("AccountInfo"));
			request.setAttribute("OrderTips",param.get("OrderTips"));
			request.setAttribute("ShopSellsInfo",param.get("ShopSellsInfo"));
			request.setAttribute("GoodsStatistics",param.get("GoodsStatistics"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/biz/mpIndex";
	}
}
