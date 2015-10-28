/**
 * 文 件 名   :  IndexController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-23 上午10:48:26
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnit.yoyo.util.StringUtil;

/**
 *@description <一句话功能简述>
 *@detail <功能详细描述>
 *@author <a href="liming@cnit.com">李明</a>
 *@version 1.0.0
 */
@Controller
public class IndexController {
    @RequestMapping("/register/login")
    public String toSignin(HttpServletRequest request){
    	String returnURL = request.getParameter("ReturnURL");
    	if(StringUtil.isEmpty(returnURL)){
    		returnURL="/index";
    	}
    	request.setAttribute("ReturnURL", returnURL);
        return "pages/sign/signin";
    }
    @RequestMapping("/register/signup")
    public String toSignup(){
        return "pages/sign/signup";
    }
    @RequestMapping("/index")
    public String index(HttpServletRequest request){
    	request.getSession().setAttribute("accountId", 152);
        return "pages/index";
    }
    @RequestMapping("/productDetail")
    public String productDetail(){
        return "pages/productDetail";
    }
    @RequestMapping("/error")
    public String error(){
        return "pages/biz/error/error";
    }
    
    @RequestMapping("/test")
    public String test(){
        return "pages/manager/memberManager";
    }
   
}
