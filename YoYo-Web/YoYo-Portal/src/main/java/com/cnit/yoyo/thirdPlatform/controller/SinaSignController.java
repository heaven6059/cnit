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
package com.cnit.yoyo.thirdPlatform.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
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

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.system.login.domain.SignUpDo;
import com.cnit.yoyo.system.login.service.SignService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.servlet.CookieUtil;


/**
 * @description 新浪第三方访问控制
 * @detail 用户注册登录控制
 * @author yangyi
 * @version 1.0.0
 */
@Controller
@RequestMapping("/sinasign")
public class SinaSignController {

    private static final Logger log = LoggerFactory.getLogger(SinaSignController.class);
    @Autowired
    private SignService signService;
    @Autowired
    private RedisClientUtil redisService;
    
    /**
	 * 新浪微博登录的回调
	 * 
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/afterLogin")
    public ModelAndView afterLogin(String code ,HttpServletRequest request, HttpServletResponse response){
    		String accesstoken = null;
    		 code = request.getParameter("code");
    		 try {
    		if (code != null) {

    			Oauth oauth = new Oauth();
    			AccessToken accessToken;
					accessToken = oauth.getAccessTokenByCode(code);
    			accesstoken = accessToken.getAccessToken();
    			if (accessToken != null) {
    				Weibo weibo = new Weibo();
    				weibo.setToken(accessToken.getAccessToken());
    				System.out.println(accessToken.getUid());
    				System.out.println(accesstoken);
    				Users users = new Users();
    				users.client.setToken(accesstoken);
    				User weiboUser;
    				try {
    					weiboUser = users.showUserById(accessToken.getUid());
    					System.out.println(weiboUser.getId());
    					System.out.println(weiboUser.getGender());
    					System.out.println(weiboUser.getName());
    					System.out.println(accesstoken);
//    					session.setAttribute("weiboUser", weiboUser);
    				} catch (WeiboException e) {
    					e.printStackTrace();
    				}
//    				HttpServletResponse response = getResponse();

    				/**
    				 * 用于注入数据库 Client client = FbbUtil.getClient(); FbbUser fbbUser=
    				 * client.doOauth(1, weiboUser.getId());
    				 */
    			}
    		} else {
//    			errorMsg = "新浪oauth 认证请求非法！";
//    			return LOGIN;
    		}
    		} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            return new ModelAndView("redirect:/YoYoPortal/index");
    }
	
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
    @RequestMapping("/doLogin")
    @ResponseBody
    public Object memberLogin(HttpServletRequest request, HttpServletResponse response, SignUpDo sud) throws Exception {
    	if(StringUtil.isEmpty(sud.getImgValidation())) {
    		return new ModelAndView("redirect:/register/login");
    	}
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-04", GlobalStatic.SYSTEM_CODE_THIRD, GlobalStatic.SYSTEM_CODE_FRONT);
        String verifycodeSes = (String) request.getSession().getAttribute(GlobalStatic.VALIDATE_CODE);
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
            }catch (UnknownAccountException e) {  
            	error = "用户名/密码错误";
                headObject.setRetCode(ErrorCode.PDE0002);
                headObject.setRetMsg(error);
                return new ResultObject(headObject);
            }catch (IncorrectCredentialsException e) {  
            	error = "用户名/密码错误";
                headObject.setRetCode(ErrorCode.PDE0002);
                headObject.setRetMsg(error);
                return new ResultObject(headObject);
            }catch (LockedAccountException e) {  
            	error = "账号被锁定";
                headObject.setRetCode(ErrorCode.PDE0002);
                headObject.setRetMsg(error);
                return new ResultObject(headObject);
            }catch (Exception e) {  
            	e.printStackTrace();
                headObject.setRetCode(ErrorCode.FAILURE);
                headObject.setRetMsg("其他错误");
                return new ResultObject(headObject);
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
        
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("loginName", sud.getLoginName());
            dataMap.put("loginPassword", sud.getLoginPassword());
            dataMap.put("loginNameType", sud.getLoginNameType());
            dataMap.put("loginIp", request.getRemoteHost());
            ResultObject resultObject = signService.doCommon(headObject, dataMap);
            if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
                // 账户验证成功 将账户有关信息写入会话
                JSONObject accountInfo = JSONObject.fromObject(resultObject.getContent());
                //MemberListDo memberDo=(MemberListDo) JSONObject.toBean(accountInfo,MemberListDo.class);
                request.getSession().setAttribute(GlobalStatic.USER_INFO,accountInfo.toString());
                request.getSession().setAttribute("password",password);
                String sessionId = request.getSession().getId();
                redisService.set(sessionId, accountInfo.toString());
                request.getSession().setAttribute("loginName", accountInfo.getString("loginName"));
                request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_ON);
                request.getSession().setAttribute("accountId", accountInfo.getInt("accountId"));
                request.getSession().setAttribute("accountType", accountInfo.optInt("accountType"));
                request.getSession().setAttribute("storeId", accountInfo.optInt("storeId"));
                request.getSession().setAttribute("companyId", accountInfo.optInt("companyId"));
                request.getSession().setAttribute("companyName", accountInfo.optString("companyName"));
                request.getSession().setAttribute("gradeType", accountInfo.optString("gradeType"));  //店铺等级类型：单店，集团
                request.getSession().setAttribute("memberId", accountInfo.optString("memberId"));  //会员ID
                CookieUtil.addCookie(response, "IS_LOGIN", request.getSession().getId(), -1,null);//是否登录，会话级别
                if (sud.isAutoLogin()) {
                    CookieUtil.addCookie(response, "loginName", accountInfo.getString("loginName"), 7 * 24 * 60 * 60,null);
                    CookieUtil.addCookie(response, "autoLogin", "1", 7 * 24 * 60 * 60,null);
                }
                ServletContext ContextA =request.getSession().getServletContext();
                ContextA.setAttribute("session", request.getSession());
            }
            return resultObject;
        }
    }

    /**
     * @description 会员注册
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
    	redisService.del(request.getSession().getId());
        request.getSession().setAttribute("loginName", "");
        request.getSession().setAttribute("accountId",0);
        request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
        ServletContext ContextA =request.getSession().getServletContext();
        request.getSession().setAttribute(GlobalStatic.USER_INFO,"");
        ContextA.setAttribute("session",null);
        return "redirect:/index";
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
