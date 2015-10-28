/**
 * 文 件 名   :  SignController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-17 下午3:26:41
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.servlet.CookieUtil;

/**
 * @description 管理员登录
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/sign")
public class SignController {

	@Autowired
	private RemoteService memberService;

	/**
	 * @description 管理平台登录
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-17
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public Object doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-01", GlobalStatic.SYSTEM_CODE_THIRD, GlobalStatic.SYSTEM_CODE_BACK);

		String error = null;
		String username = request.getParameter("loginName");
		String password = request.getParameter("loginPassword");
		if (StringUtil.isEmpty(username)) {
			return new ModelAndView("redirect:/");
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "用户名/密码错误";
			headObject.setRetCode(ErrorCode.PDE0002);
			headObject.setRetMsg(error);
			return new ResultObject(headObject);
		} catch (IncorrectCredentialsException e) {
			error = "用户名/密码错误";
			headObject.setRetCode(ErrorCode.PDE0002);
			headObject.setRetMsg(error);
			return new ResultObject(headObject);
		} catch (LockedAccountException e) {
			error = "账号被锁定";
			headObject.setRetCode(ErrorCode.PDE0002);
			headObject.setRetMsg(error);
			return new ResultObject(headObject);
		} catch (ExcessiveAttemptsException e) {
			e.printStackTrace();
			error = "登录失败次数过多";
			headObject.setRetCode(ErrorCode.PDE0002);
			headObject.setRetMsg(error);
			return new ResultObject(headObject);
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			headObject.setRetMsg("其他错误");
			return new ResultObject(headObject);
		}

		// shiro验证逻辑------start-------
		/*
		 * String exceptionClassName =
		 * (String)request.getAttribute("shiroLoginFailure");
		 * if(UnknownAccountException
		 * .class.getName().equals(exceptionClassName)) { error = "用户名/密码错误";
		 * headObject.setRetCode(ErrorCode.PDE0001);
		 * headObject.setRetMsg(error); return new ResultObject(headObject); }
		 * else if(IncorrectCredentialsException.class.getName().equals(
		 * exceptionClassName)) { error = "用户名/密码错误";
		 * headObject.setRetCode(ErrorCode.PDE0001);
		 * headObject.setRetMsg(error); return new ResultObject(headObject); }
		 * else if(exceptionClassName != null) { error = "其他错误：" +
		 * exceptionClassName; headObject.setRetCode(ErrorCode.PDE0001);
		 * headObject.setRetMsg(error); return new ResultObject(headObject); }
		 */
		headObject.setRetCode(ErrorCode.SUCCESS);
		// shiro验证逻辑------end-------

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("loginName", request.getParameter("loginName"));
		// dataMap.put("loginPassword", request.getParameter("loginPassword"));
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, dataMap));
		if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
			// 账户验证成功 将账户有关信息写入会话
			JSONObject accountInfo = JSONObject.fromObject(resultObject.getContent());
			request.getSession().setAttribute("loginName", accountInfo.getString("loginName"));
			request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_ON);
			request.getSession().setAttribute("accountId", accountInfo.getInt("accountId"));
			request.getSession().setAttribute("accountType", accountInfo.getString("accountType"));
			if ("true".equalsIgnoreCase(request.getParameter("autoLogin"))) {
				CookieUtil.addCookie(response, "loginName", accountInfo.getString("loginName"), 7 * 24 * 60 * 60, null);
				CookieUtil.addCookie(response, "autoLogin", "1", 7 * 24 * 60 * 60, null);
			}
		}
		return resultObject;
	}

	/**
	 * @description 注销登录
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-2-11
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loginOut")
	public Object loginOut(HttpServletRequest request) throws Exception {
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		return "redirect:/index";
	}
	
/**
 * 
 *@description 检查账户是否存在，并且是否已经开店
 *@detail <方法详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 *@data 2015-6-8
 *@param request
 *@return
 *@throws Exception
 */
    @RequestMapping("/checkAccountExist")
    @ResponseBody
    public Object checkAccountExist(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-33", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        String loginNameType = request.getParameter("loginNameType");
        String loginName = request.getParameter("loginName");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        if ("1".equalsIgnoreCase(loginNameType)) {
            data.put("email", loginName);
        }
        if ("2".equalsIgnoreCase(loginNameType)) {
            data.put("mobile", loginName);
        }
        if ("3".equalsIgnoreCase(loginNameType)) {
            data.put("loginName", loginName);
        }
        return memberService.doService(new RequestObject(headObject, data));
    }
}
