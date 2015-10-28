/**
 * 文 件 名   :  SignController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-22 下午2:57:27
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.system.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.system.login.domain.SignUpDo;
import com.cnit.yoyo.system.login.service.SignService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.servlet.CookieUtil;

/**
 * @description 访问控制
 * @detail 用户注册登录控制
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/sign1")
public class SignShopController {

    private static final Logger log = LoggerFactory.getLogger(SignShopController.class);
    @Autowired
    private SignService signService;
    @Autowired
	private RedisClientUtil redisService;
    /**
     * @description 会员登录
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-26
     * @param request
     * @param sud
     * @return
     * @throws Exception
     */
    @RequestMapping("/doLogin1")
    @ResponseBody
    public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-04", GlobalStatic.SYSTEM_CODE_THIRD,GlobalStatic.SYSTEM_CODE_FRONT);
        	String error = null;
        	//String accountInfo = (String) CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO);
        	String sessionId = request.getSession().getId();
        	log.info("sessionId>>>"+sessionId);
        	String accountInfo = redisService.get(sessionId);
        	log.info("accountInfo>>>"+accountInfo);
        	String path =  Configuration.getInstance().getConfigValue("portal.url");
        	
        	String str = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
        	if(StringUtil.isEmpty(accountInfo)) {
        		clearSession(request);
        		return new ModelAndView("redirect:"+str+path+"/register/login");
        	}
        	//String password = (String)CommonUtil.getSession(request).getAttribute("password");
        	String password = redisService.get(sessionId + "password");
        	JSONObject account = JSONObject.fromObject(accountInfo);
        	MemberListDo memberDo=(MemberListDo) JSONObject.toBean(account,MemberListDo.class);
        	String username = memberDo.getLoginName();  
            
            Subject subject = SecurityUtils.getSubject();  
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
            try {  
                subject.login(token);  
            } catch (UnknownAccountException e) {  
            	error = "用户名/密码错误";
                headObject.setRetCode(ErrorCode.PDE0002);
                headObject.setRetMsg(error);
                clearSession(request);
                return new ModelAndView("redirect:"+str+path+"/register/login");
            }catch (IncorrectCredentialsException e) {  
            	error = "用户名/密码错误";
                headObject.setRetCode(ErrorCode.PDE0002);
                headObject.setRetMsg(error);
                clearSession(request);
                return new ModelAndView("redirect:"+str+path+"/register/login");
            }catch (Exception e) {  
            	error = "其他错误";
                headObject.setRetCode(ErrorCode.FAILURE);
                headObject.setRetMsg(error);
                clearSession(request);
                return new ModelAndView("redirect:"+str+path+"/register/login");
            }
            
        	/*String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
            
            if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
                headObject.setRetCode(ErrorCode.PDE0001);
                headObject.setRetMsg(error);
            } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
                headObject.setRetCode(ErrorCode.PDE0001);
                headObject.setRetMsg(error);
            } else if(exceptionClassName != null) {
                error = "其他错误：" + exceptionClassName;
                headObject.setRetCode(ErrorCode.PDE0001);
                headObject.setRetMsg(error);
            }*/
            headObject.setRetCode(ErrorCode.SUCCESS);
            ResultObject resultObject = new ResultObject();
            resultObject.setHead(headObject);
            request.getSession().setAttribute("loginName", account.getString("loginName"));
            request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_ON);
            request.getSession().setAttribute("accountId", account.getInt("accountId"));
            request.getSession().setAttribute("accountType", account.getInt("accountType"));
            CookieUtil.addCookie(response, "IS_LOGIN", request.getSession().getId(), -1,null);//是否登录，会话级别
            CookieUtil.addCookie(response, "loginName", account.getString("loginName"), 7 * 24 * 60 * 60,null);
            CookieUtil.addCookie(response, "autoLogin", "1", 7 * 24 * 60 * 60,null);
            //return "redirect:/YoYoShop/shopManager/manager";
            return new ModelAndView("redirect:/shopCenter/index");
    }

    /**
     * @description 个人会员注册
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-26
     * @param request
     * @param sud
     * @return
     * @throws Exception
     */
    @RequestMapping("/signUp")
    @ResponseBody
    public Object personSignUp(HttpServletRequest request, @ModelAttribute("form") SignUpDo sud) throws Exception {
        HeadObject headObject = CommonHeadUtil
                .geneHeadObject(request, "1000020102-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
        sud.setRegIp(request.getRemoteHost());
        sud.setRegType(sud.getLoginNameType());
        sud.setSource(GlobalStatic.CHANNEL_WEB);
        return signService.doCommon(headObject, JSONObject.fromObject(sud));
    }
    
    private void clearSession(HttpServletRequest request) {
    	redisService.del(request.getSession().getId());
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);
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
    /*@RequestMapping("/loginOut1")
    public Object loginOut(HttpServletRequest request) throws Exception {
    	redisService.del(request.getSession().getId());
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);
		return "redirect:/shop/loginOut";
    	String str = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		return new ModelAndView("redirect:"+str+"/YoYoPortal/sign/loginOut");
    }*/
    
    @RequestMapping("/loginOut1")
    public String loginOut(HttpServletRequest request){
    	redisService.del(request.getSession().getId());
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);
    	
        return "pages/shopMng/logout";
    }

    /**
     * @description 检查账户是否存在
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-16
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/checkAccountExist")
    @ResponseBody
    public Object checkAccountExist(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
        return signService.doCommon(headObject, data);
    }
	   
}
