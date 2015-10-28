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
package com.cnit.yoyo.member.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.member.domain.SignUpDo;
import com.cnit.yoyo.model.member.MemberListDo;
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
@RequestMapping("/sign2")
public class SignMemController {

    private static final Logger log = LoggerFactory.getLogger(SignMemController.class);
    
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
    @RequestMapping("/doLogin2")
    @ResponseBody
    public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response, SignUpDo sud) throws Exception {
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-04", GlobalStatic.SYSTEM_CODE_THIRD,
                GlobalStatic.SYSTEM_CODE_FRONT);
        /*String verifycodeSes = (String) request.getSession().getAttribute(GlobalStatic.VALIDATE_CODE);
        if (!StringUtil.isEmpty(verifycodeSes) && !verifycodeSes.equalsIgnoreCase(sud.getImgValidation())) {
            headObject.setRetCode(ErrorCode.PDE0001);
            headObject.setRetMsg("验证码输入错误");
            return new ResultObject(headObject);
        } else {
        	String error = null;
        	String username = sud.getLoginName();  
            String password = sud.getLoginPassword();
            Subject subject = SecurityUtils.getSubject();  
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
            try {  
                subject.login(token);  
            } catch (UnknownAccountException e) {  
                error = "用户名/密码错误";
            }

        	
        	String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
            
            if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
                headObject.setRetCode(ErrorCode.PDE0001);
                headObject.setRetMsg(error);
                return new ResultObject(headObject);
            } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
                headObject.setRetCode(ErrorCode.PDE0001);
                headObject.setRetMsg(error);
                return new ResultObject(headObject);
            } else if(exceptionClassName != null) {
                error = "其他错误：" + exceptionClassName;
                headObject.setRetCode(ErrorCode.PDE0001);
                headObject.setRetMsg(error);
                return new ResultObject(headObject);
            }
            headObject.setRetCode(ErrorCode.SUCCESS);
        	
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("loginName", sud.getLoginName());
            dataMap.put("loginPassword", sud.getLoginPassword());
            dataMap.put("loginNameType", sud.getLoginNameType());
            dataMap.put("loginIp", request.getRemoteHost());
            ResultObject resultObject = signService.doCommon(headObject, dataMap);
            if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
                // 账户验证成功 将账户有关信息写入会话
                JSONObject accountInfo = JSONObject.fromObject(resultObject.getContent());
                
                redisService.set(request.getSession().getId(), accountInfo.toString());
                
                request.getSession().setAttribute("loginName", accountInfo.getString("loginName"));
                request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_ON);
                request.getSession().setAttribute("accountId", accountInfo.getInt("accountId"));
                if (sud.isAutoLogin()) {
                    CookieUtil.addCookie(response, "loginName", accountInfo.getString("loginName"), 7 * 24 * 60 * 60,null);
                    CookieUtil.addCookie(response, "autoLogin", "1", 7 * 24 * 60 * 60,null);
                }
            }
            return resultObject;
        }*/
    	
    	String error = null;
    	String path =  Configuration.getInstance().getConfigValue("portal.url");
		String sessionId = request.getSession().getId();
    	//String accountInfo = (String) CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO);
    	String accountInfo = redisService.get(sessionId);
    	String str = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
    	if(StringUtil.isEmpty(accountInfo)) {
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
        }  catch (UnknownAccountException e) {  
        	error = "用户名/密码错误";
            headObject.setRetCode(ErrorCode.PDE0002);
            headObject.setRetMsg(error);
            return new ModelAndView("redirect:"+str+path+"/register/login");
        }catch (IncorrectCredentialsException e) {  
        	error = "用户名/密码错误";
            headObject.setRetCode(ErrorCode.PDE0002);
            headObject.setRetMsg(error);
            return new ModelAndView("redirect:"+str+path+"/register/login");
        }catch (Exception e) {  
        	error = "其他错误";
            headObject.setRetCode(ErrorCode.FAILURE);
            headObject.setRetMsg(error);
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
        CookieUtil.addCookie(response, "IS_LOGIN", request.getSession().getId(), -1,null);
        CookieUtil.addCookie(response, "loginName", account.getString("loginName"), 7 * 24 * 60 * 60,null);
        CookieUtil.addCookie(response, "autoLogin", "1", 7 * 24 * 60 * 60,null);
        return new ModelAndView("redirect:/memberCenter/index");
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
    /*@RequestMapping("/loginOut2")
    public Object loginOut(HttpServletRequest request) throws Exception {
    	redisService.del(request.getSession().getId());
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);
		return "redirect:/mem/loginOut2";
    	String str = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		return new ModelAndView("redirect:"+str+"/YoYoPortal/sign/loginOut");
    }*/
    
    @RequestMapping("/loginOut2")
    public String loginOut(HttpServletRequest request){
		redisService.del(request.getSession().getId());
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);
    	
        return "pages/index/logout";
    }
	   
}
