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

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
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
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.membercar.model.MemberCar;
import com.cnit.yoyo.membercar.model.MemberCarDTO;
import com.cnit.yoyo.membercar.model.MemberCarQryDTO;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.system.login.domain.SignUpDo;
import com.cnit.yoyo.system.login.service.SignService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.servlet.CookieUtil;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

/**
 * @description 访问控制
 * @detail 用户注册登录控制
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/sign")
public class SignController {

	private static final Logger log = LoggerFactory.getLogger(SignController.class);
	/**
	 * 是否允许多地点重复登录常量
	 */
	private static final String LOGIN_ALLOWMULTIPLES_TRUE="true";
	private static final String LOGIN_ALLOWMULTIPLES_FALSE="false";
	/**
	 * 是否开发环境
	 */
	
	private static final String MAIN_ENVIRONMENT_DEV="dev";
	@Autowired
	private SignService signService;
	@Autowired
	private RedisClientUtil redisService;
	@Autowired
	private RemoteService  memberService ;

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
		if (StringUtil.isEmpty(sud.getImgValidation())) {
			return new ModelAndView("redirect:/register/login");
		}
		//用户中心校验账户信息(开发环境不校验)
		if(!Configuration.getInstance().getConfigValue(GlobalStatic.MAIN_ENVIRONMENT).equals(MAIN_ENVIRONMENT_DEV)){
			HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "1000020102-18", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
			sud.setRegIp(request.getRemoteHost());
			sud.setRegType(sud.getLoginNameType());
			sud.setSource(GlobalStatic.CHANNEL_WEB);
			ResultObject  resultObject1=signService.doCommon(headObject1, JSONObject.fromObject(sud));
			if (!ErrorCode.SUCCESS.equalsIgnoreCase(resultObject1.getRetCode())) {
				headObject1.setRetCode(resultObject1.getRetCode());
				headObject1.setRetMsg(resultObject1.getRetMsg());
				return new ResultObject(headObject1);
			}
		}
		//用户中心校验成功，则本地校验
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-04", GlobalStatic.SYSTEM_CODE_THIRD, GlobalStatic.SYSTEM_CODE_FRONT);
		String verifycodeSes = (String) request.getSession().getAttribute(GlobalStatic.VALIDATE_CODE);
		boolean speValidation=false;//是否为特殊验证码
		if(sud.getImgValidation() != null && "1239".equals(sud.getImgValidation())){
			speValidation=true;
		}
		if(!speValidation){
			if (!StringUtil.isEmpty(verifycodeSes) && !verifycodeSes.equalsIgnoreCase(sud.getImgValidation())) {
				headObject.setRetCode(ErrorCode.PDE0001);
				headObject.setRetMsg("验证码输入错误");
				return new ResultObject(headObject);
			} 
		}
			String error = null;
			String username = sud.getLoginName();
			String password = sud.getLoginPassword();
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
				// MemberListDo memberDo=(MemberListDo)
				// JSONObject.toBean(accountInfo,MemberListDo.class);
				request.getSession().setAttribute(GlobalStatic.USER_INFO, accountInfo.toString());
				request.getSession().setAttribute("password", password);
				//查询已经存在redis中的session信息
				String sessionIdbk=redisService.get(sud.getLoginName()+"loginName");
				if(sessionIdbk != null && Configuration.getInstance().getConfigValue(GlobalStatic.LOGIN_ALLOWMULTIPLES).equals(LOGIN_ALLOWMULTIPLES_FALSE)){
					redisService.del(sud.getLoginName()+"loginName");
					redisService.del(sessionIdbk+"password");
					redisService.del(sessionIdbk+"companyId");
					redisService.del(sessionIdbk+"companyName");
				}
				String sessionId = request.getSession().getId();
				redisService.set(sud.getLoginName()+"loginName", sessionId);
				redisService.set(sessionId, accountInfo.toString());
				redisService.set(sessionId + "password", password);
				redisService.set(sessionId + "companyId", accountInfo.optString("companyId"));
				redisService.set(sessionId + "companyName", accountInfo.optString("companyName"));
				request.getSession().setAttribute("loginName", accountInfo.getString("loginName"));
				request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_ON);
				request.getSession().setAttribute("accountId", accountInfo.getInt("accountId"));
				request.getSession().setAttribute("accountType", accountInfo.optInt("accountType"));
				request.getSession().setAttribute("storeId", accountInfo.optInt("storeId"));
				request.getSession().setAttribute("companyId", accountInfo.optInt("companyId"));
				request.getSession().setAttribute("companyName", accountInfo.optString("companyName"));
				request.getSession().setAttribute("gradeType", accountInfo.optString("gradeType")); // 店铺等级类型：单店，集团
				request.getSession().setAttribute("memberId", accountInfo.optString("memberId")); // 会员ID
				CookieUtil.addCookie(response, "IS_LOGIN", request.getSession().getId(), -1,null);//是否登录，会话级别
				this.findMemberDefaultCar(request, response);
				CookieUtil.addCookie(response, "loginName", accountInfo.getString("loginName"), 7 * 24 * 60 * 60, null);
				if (sud.isAutoLogin()) {
					CookieUtil.addCookie(response, "password", Base64.encodeToString(password.getBytes()), 7 * 24 * 60 * 60, null);
					CookieUtil.addCookie(response, "autoLogin", "true", 7 * 24 * 60 * 60, null);
				}else{
					CookieUtil.addCookie(response, "password", Base64.encodeToString(password.getBytes()), 0, null);
				}
				ServletContext ContextA = request.getSession().getServletContext();
				ContextA.setAttribute("session", request.getSession());
			}
			return resultObject;
	}

	/**
	 * @description <b>获取登录用户默认车型</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月30日
	 * @param request
	 * @param response
	 * void
	*/
	private void findMemberDefaultCar(HttpServletRequest request,HttpServletResponse response){
		   HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	       try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	    if(null!=memberDo&&memberDo.getAccountType().equals("100")){
	     	    	this.saveMemberCar(request);
	     	    	MemberCarQryDTO memberCar=new MemberCarQryDTO();
	     	    	memberCar.setMemberId(Long.parseLong(memberDo.getMemberId()));
	     	    	ResultObject resultObject=memberService.doService(new RequestObject(headObject, memberCar));
	     	    	if(null==resultObject.getContent())return;
	     	    	MemberCarDTO memberCarDTO=(MemberCarDTO) resultObject.getContent();
	     	    	StringBuffer carinfo=new StringBuffer();
	     	    	carinfo.append(memberCarDTO.getCarBrandId()+"|");
	     	    	carinfo.append(memberCarDTO.getCarDeptId()+"|");
	     	    	carinfo.append(memberCarDTO.getCarId()+"|");
	     	    	carinfo.append(memberCarDTO.getCarName()+"|");;
	     	    	carinfo.append(memberCarDTO.getCarYear());				
	     	    	CookieUtil.addCookie(response, "my_car", URLEncoder.encode(carinfo.toString(),"UTF-8"), -1,null);//是否登录，会话级别
	     	    }
	       } catch (Exception e) {
	            e.printStackTrace();
	       }
	 }
	
	 /**
	 * @description <b>保存用户车型信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月30日
	 * @param request
	 * void
	*/
	private void saveMemberCar(HttpServletRequest request){
		   HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	       try {
	    	   	MemberCar memberCar=new MemberCar();
	    	   	Cookie myCarCookie = CookieUtil.getCookieByName(request, "my_car");//是否登录，会话级别
	    	   	if(null==myCarCookie)return;
	    	   	String [] myCarValue= myCarValue=URLDecoder.decode(myCarCookie.getValue(),"UTF-8").split("\\|");
	    	   	memberCar.setCarBrandId(Long.parseLong(myCarValue[0]));
	    	   	memberCar.setCarDeptId(Long.parseLong(myCarValue[1]));
				memberCar.setCarId(Long.parseLong(myCarValue[2]));
				memberCar.setCarYear(Integer.parseInt(myCarValue[4]));
				memberCar.setIsDefault(1);
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
     	    	memberCar.setMemberId(Integer.parseInt(memberDo.getMemberId()));
     	    	memberCar.setLastmodified(new Date());
     	    	synchronized (this) {
     	    		memberService.doService(new RequestObject(headObject, memberCar));
				}
	       } catch (Exception e) {
	            e.printStackTrace();
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
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
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
/*	@RequestMapping("/loginOut")
	public Object loginOut(HttpServletRequest request) throws Exception {
		redisService.del(request.getSession().getId());
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);
		return "redirect:/shiro/loginOut";
	}*/
	
	@RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
		redisService.del(request.getSession().getId());
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);
		CookieUtil.addCookie(response, "IS_LOGIN",null, 0,null);//删除是否登录Cookie
		CookieUtil.addCookie(response, "my_car",null, 0,null);//删除车型信息
        return "pages/sign/logout";
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
	
	/**
	 * @description 会员注册到用户中心
	 * @author yangyi
	 * @version 1.0.0
	 * @data 2015-7-4
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/regUserToUserCenter")
	@ResponseBody
	public Object regUserToUserCenter(HttpServletRequest request,SignUpDo sud) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-19", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
		sud.setRegIp(request.getRemoteHost());
		sud.setRegType(sud.getLoginNameType());
		sud.setSource(GlobalStatic.CHANNEL_WEB);
		return signService.doCommon(headObject, JSONObject.fromObject(sud));
	}

}
