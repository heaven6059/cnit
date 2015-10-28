package com.cnit.yoyo.thirdPlatform.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
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
import org.springframework.ui.Model;
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
import com.cnit.yoyo.model.thirdaccount.ThirdAccount;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.system.login.domain.SignUpDo;
import com.cnit.yoyo.system.login.service.SignService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.servlet.CookieUtil;
import com.qq.connect.QQConnect;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.QQConnectConfig;
import com.qq.connect.utils.RandomStatusGenerator;
import com.qq.connect.utils.http.HttpClient;
import com.qq.connect.utils.http.PostParameter;

/**
 * @description 第三方qq或者微信访问控制
 * @detail 用户注册登录控制
 * @author yangyi
 * @version 1.0.0
 */
@Controller
@RequestMapping("/connectsign")
public class ContentSignController {
	/**
	 * 是否允许多地点重复登录常量
	 */
	private static final String LOGIN_ALLOWMULTIPLES_TRUE="true";
	private static final String LOGIN_ALLOWMULTIPLES_FALSE="false";
	private static final Logger log = LoggerFactory
			.getLogger(ContentSignController.class);
	@Autowired
	private SignService signService;
	@Autowired
	private RemoteService  memberService ;
	@Autowired
	private RedisClientUtil redisService;

	@RequestMapping("/qqlogin")
    public void qqlogin(HttpServletRequest request, HttpServletResponse response,
            Model model) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
        	String path="";
            //设置第三方账户授权时选择的账户类型
            String accountType=request.getParameter("accountType");
        	String state = RandomStatusGenerator.getUniqueState();
            request.getSession().setAttribute("qq_connect_state", state);
            String scope = QQConnectConfig.getValue("scope");
            request.getSession().setAttribute(state+"accountType", accountType);
            String encodeUrl;
    		try
    		{
    			encodeUrl = URLEncoder.encode(QQConnectConfig.getValue("redirect_URI"),"UTF-8");
    		}
    		catch(UnsupportedEncodingException e)
    		{
    		    throw new QQConnectException("UnsupportedEncodingException for URLEncoder", e);
    		}
    		path=QQConnectConfig.getValue("authorizeURL").trim() + "?client_id="
                + QQConnectConfig.getValue("app_ID").trim() + "&redirect_uri="
                + encodeUrl.trim()
                + "&response_type=" + "code"
                + "&state="+state;
        	response.sendRedirect(path);  
            log.info("login by qq");
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
    }

	/**
	 * qq平台登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/afterQQLogin")
	public ModelAndView afterQQLogin(String code, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		response.setContentType("text/html; charset=utf-8");
		String state=request.getParameter("state");
		request.getSession().setAttribute("qq_connect_state", state);
		try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			String accessToken = null, openID = null;
			long tokenExpireIn = 0L;

			if (accessTokenObj.getAccessToken().equals("")) {
				log.error("QQ Login failed,caused by 没有获取到响应参数");
				// return "404";
			}
			accessToken = accessTokenObj.getAccessToken();
			tokenExpireIn = accessTokenObj.getExpireIn();
			log.info("Get accessToken from qq,accessToken:" + accessToken
					+ ",tokenExpireIn" + tokenExpireIn);

			// 利用获取到的accessToken 去获取当前用的openid
			OpenID openIDObj = new OpenID(accessToken);
			openID = openIDObj.getUserOpenID();
			log.info("利用获取到的accessToken:" + accessToken + ", 去获取到当前用户openid:"
					+ openID + ".");

			String icon = null, nickName = null;
			// 去获取用户在Qzone的昵称等信息
			UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
			UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
			String yyAccountType = (String) request.getSession().getAttribute(state+"accountType");
            HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-28", GlobalStatic.SYSTEM_CODE_THIRD, GlobalStatic.SYSTEM_CODE_FRONT);
            ThirdAccount thirdAccount=new ThirdAccount();
            thirdAccount.setOpenId(openID);
            thirdAccount.setLoginName(openID);
            thirdAccount.setAccountName(userInfoBean.getNickname());
            thirdAccount.setLoginPassword("888888");
            thirdAccount.setAccountType(GlobalStatic.THIRD_PART_QQ);
            thirdAccount.setYyAccountType(yyAccountType);
            ResultObject thirdAccountResultObject = signService.doCommon(headObject, thirdAccount);
            //操作成功，执行登录操作
            if (ErrorCode.SUCCESS.equalsIgnoreCase(thirdAccountResultObject.getRetCode())) {
    				// 账户创建并且关联成功 将账户有关信息写入会话，进入首页
                    JSONObject accountInfo = JSONObject.fromObject(thirdAccountResultObject.getContent());
                    accountInfo.remove("loginName");
                    accountInfo.element("loginName", thirdAccount.getLoginName());
                    accountInfo.element("loginImg", userInfoBean.getAvatar().getAvatarQQURL40());
                    accountInfo.element("nickName", userInfoBean.getNickname().trim());
                    request.getSession().setAttribute(GlobalStatic.USER_INFO,accountInfo.toString());
                    request.getSession().setAttribute("password",thirdAccount.getLoginPassword());
                  //查询已经存在redis中的session信息
    				String sessionIdbk=redisService.get(thirdAccount.getLoginName()+"loginName");
    				if(sessionIdbk != null && Configuration.getInstance().getConfigValue(GlobalStatic.LOGIN_ALLOWMULTIPLES).equals(LOGIN_ALLOWMULTIPLES_FALSE)){
    					redisService.del(thirdAccount.getLoginName()+"loginName");
    					redisService.del(sessionIdbk+"password");
    					redisService.del(sessionIdbk+"companyId");
    					redisService.del(sessionIdbk+"companyName");
    				}
    				String sessionId = request.getSession().getId();
    				redisService.set(thirdAccount.getLoginName()+"loginName", sessionId);
    				redisService.set(sessionId, accountInfo.toString());
    				redisService.set(sessionId + "password", thirdAccount.getLoginPassword());
    				redisService.set(sessionId + "companyId", accountInfo.optString("companyId"));
    				redisService.set(sessionId + "companyName", accountInfo.optString("companyName"));
    				request.getSession().setAttribute("loginName", accountInfo.getString("loginName"));
    				request.getSession().setAttribute("loginImg", userInfoBean.getAvatar().getAvatarURL30());
    				request.getSession().setAttribute("nickName", userInfoBean.getNickname());
                    request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_ON);
                    request.getSession().setAttribute("accountId", accountInfo.getInt("accountId"));
                    request.getSession().setAttribute("accountType", accountInfo.optInt("accountType"));
                    request.getSession().setAttribute("storeId", accountInfo.optInt("storeId"));
                    request.getSession().setAttribute("companyId", accountInfo.optInt("companyId"));
                    request.getSession().setAttribute("companyName", accountInfo.optString("companyName"));
                    request.getSession().setAttribute("gradeType", accountInfo.optString("gradeType"));  //店铺等级类型：单店，集团
                    request.getSession().setAttribute("memberId", accountInfo.optString("memberId"));  //会员ID
                    CookieUtil.addCookie(response, "IS_LOGIN", request.getSession().getId(), -1,null);//是否登录，会话级别
    				this.findMemberDefaultCar(request, response);
            }

		} catch (QQConnectException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = request.getScheme() + "://" + request.getServerName()+ ":" + request.getServerPort();
		return new ModelAndView("redirect:" + str + "/yoyoportal");
	}

	/**
	 * 微信登录验证
	 * 
	 * @return
	 */
	public ModelAndView afterWXLogin(String code, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			AccessToken qqtoken = new Oauth().getAccessTokenByRequest(request);
			if (qqtoken.getAccessToken().equals("")) {
				// errorMsg = "QQ登录校验失败!";
				// return "login";
			} else {
				// String accessToken = qqtoken.getAccessToken();
				// System.out.println(accessToken);
				// Long tokenExpireIn = qqtoken.getExpireIn();
				// OpenID oId = new OpenID(accessToken);
				// String openid = oId.getUserOpenID();
				// // session.setAttribute("demo_access_token", accessToken);
				// // session.setAttribute("demo_token_expirein",
				// // String.valueOf(tokenExpireIn);
				// // session.setAttribute("demo_openid", openid);
				// UserInfo userinfo = new UserInfo(accessToken, openid);
				// UserInfoBean ub = userinfo.getUserInfo();
				// System.out.println(ub.getNickname());
				// System.out.println(ub.getLevel());
				// com.qq.connect.api.weibo.UserInfo weiboUserInfo = new
				// com.qq.connect.api.weibo.UserInfo(
				// accessToken, openid);
				// System.out.println(weiboUserInfo.getUserInfo().getName());
				// System.out.println(weiboUserInfo.getUserInfo().getEmail());
				// System.out.println(weiboUserInfo.getUserInfo().getLevel());
				// session.setAttribute("zoneInfo", ub);
				// session.setAttribute("weiboInfo",
				// weiboUserInfo.getUserInfo());
				// return SUCCESS;
			}
		} catch (QQConnectException e) {
			e.printStackTrace();
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
	public Object memberLogin(HttpServletRequest request,
			HttpServletResponse response, SignUpDo sud) throws Exception {
		if (StringUtil.isEmpty(sud.getImgValidation())) {
			return new ModelAndView("redirect:/register/login");
		}
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,
				"1000020102-04", GlobalStatic.SYSTEM_CODE_THIRD,
				GlobalStatic.SYSTEM_CODE_FRONT);
		String verifycodeSes = (String) request.getSession().getAttribute(
				GlobalStatic.VALIDATE_CODE);
		if (!StringUtil.isEmpty(verifycodeSes)
				&& !verifycodeSes.equalsIgnoreCase(sud.getImgValidation())) {
			headObject.setRetCode(ErrorCode.PDE0001);
			headObject.setRetMsg("验证码输入错误");
			return new ResultObject(headObject);
		} else {
			String error = null;
			String username = sud.getLoginName();
			String password = sud.getLoginPassword();
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);
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
			} catch (Exception e) {
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
			ResultObject resultObject = signService.doCommon(headObject,dataMap);
			if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
				// 账户验证成功 将账户有关信息写入会话
				JSONObject accountInfo = JSONObject.fromObject(resultObject.getContent());
				request.getSession().setAttribute(GlobalStatic.USER_INFO,accountInfo.toString());
				request.getSession().setAttribute("password", password);
				String sessionId = request.getSession().getId();
				redisService.set(sessionId, accountInfo.toString());
				request.getSession().setAttribute("loginName",accountInfo.getString("loginName"));
				request.getSession().setAttribute("loginStatus",GlobalStatic.ACCOUNT_STATUS_ON);
				request.getSession().setAttribute("accountId",accountInfo.getInt("accountId"));
				request.getSession().setAttribute("accountType",accountInfo.optInt("accountType"));
				request.getSession().setAttribute("storeId",accountInfo.optInt("storeId"));
				request.getSession().setAttribute("companyId",accountInfo.optInt("companyId"));
				request.getSession().setAttribute("companyName",accountInfo.optString("companyName"));
				request.getSession().setAttribute("gradeType",accountInfo.optString("gradeType")); // 店铺等级类型：单店，集团
				request.getSession().setAttribute("memberId",accountInfo.optString("memberId")); // 会员ID
				CookieUtil.addCookie(response, "IS_LOGIN", request.getSession().getId(), -1,null);//是否登录，会话级别
				this.findMemberDefaultCar(request, response);
				if (sud.isAutoLogin()) {
					CookieUtil.addCookie(response, "loginName", accountInfo.getString("loginName"), 7 * 24 * 60 * 60, null);
					CookieUtil.addCookie(response, "password", Base64.encodeToString(password.getBytes()), 7 * 24 * 60 * 60, null);
					CookieUtil.addCookie(response, "autoLogin", "true", 7 * 24 * 60 * 60, null);
				}
				ServletContext ContextA = request.getSession().getServletContext();
				ContextA.setAttribute("session", request.getSession());
			}
			return resultObject;
		}
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
	public Object personSignUp(HttpServletRequest request,
			@ModelAttribute("form") SignUpDo sud) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,
				"1000020102-01", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_FRONT);
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
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus",
				GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);
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
	public Object checkAccountExist(HttpServletRequest request)
			throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,
				"1000020102-09", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_BACK);
		Map<String, Object> data = new HashMap<String, Object>();
		String pageIndex = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String loginNameType = request.getParameter("loginNameType");
		String loginName = request.getParameter("loginName");
		data.put("pageIndex",
				StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX
						: Integer.parseInt(pageIndex));
		data.put("pageSize",
				StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE
						: Integer.parseInt(pageSize));
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
