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
package com.cnit.yoyo.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.groups.Default;

import net.sf.json.JSONObject;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.api.group.First;
import com.cnit.yoyo.api.group.Second;
import com.cnit.yoyo.api.vo.CarBrandVO;
import com.cnit.yoyo.api.vo.LoginUpVO;
import com.cnit.yoyo.api.vo.MemberCarDefVO;
import com.cnit.yoyo.api.vo.MemberCarGetdefVO;
import com.cnit.yoyo.api.vo.SignUpVO;
import com.cnit.yoyo.api.vo.ThirdSignUpVO;
import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.base.validation.ValidationResult;
import com.cnit.yoyo.base.validation.ValidationUtils;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.servlet.CookieUtil;
import com.cnit.yoyo.util.sms.spi.SmsVerifyKit;

/**
 * 用户注册登录控制
 * 
 * @Author:yangyi
 * @Date:2015年7月9日
 * @Version:1.0.0
 */
@Controller("signController")
@RequestMapping("/sign")
public class SignController extends BaseController {
	private final Integer SEEEION_DEF_TIME=1000*60*24*3;//默认保存3天
	/**
	 * 是否开发环境
	 */
	private static final String MAIN_ENVIRONMENT_DEV = "dev";
	@Autowired
	private RedisClientUtil redisService;
	@Autowired
	private RemoteService memberService;
	
	/**
	 * @Description:第三方账户登录
	 * @param request
	 * @param response
	 * @param sud
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/thirdAccountLogin", method = RequestMethod.POST)
	public Object thirdAccountLogin(String data, HttpServletRequest request)
			throws Exception {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		try {
			log.info("第三方账号登录开始---");
			ThirdSignUpVO thirdAccount = (ThirdSignUpVO) JSONObject.toBean(JSONObject.fromObject(data), ThirdSignUpVO.class);
			thirdAccount.setLoginIp(request.getRemoteAddr());
			headObject = CommonHeadUtil.geneHeadObject("thirdAccountService.findAndUpdateThirdAccountByParam");
			ValidationResult bindingResult = ValidationUtils.validateEntity(thirdAccount);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, thirdAccount));
			if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
				// 账户验证成功 将账户有关信息写入会话
				String sessionId = request.getSession().getId();
				JSONObject accountInfo = JSONObject.fromObject(resultObject.getContent()).accumulate("sessionId",sessionId);
				resultObject.setContent(accountInfo);
				redisService.set(thirdAccount.getLoginName() + "loginName", sessionId,SEEEION_DEF_TIME);
				redisService.set(sessionId, accountInfo.toString(),SEEEION_DEF_TIME);
				redisService.set(sessionId + "password", thirdAccount.getLoginPassword(),SEEEION_DEF_TIME);
				redisService.set(sessionId + "storeId",accountInfo.optString("storeId"),SEEEION_DEF_TIME);
				redisService.set(sessionId + "companyId",accountInfo.optString("companyId"),SEEEION_DEF_TIME);
				redisService.set(sessionId + "companyName",accountInfo.optString("companyName"),SEEEION_DEF_TIME);
			}
		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}
	

	/**
	 * @Description:会员登录
	 * @param request
	 * @param response
	 * @param sud
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public Object memberLogin(String data, HttpServletRequest request)
			throws Exception {
		HeadObject headObject1 = null;
		ResultObject resultObject = null;
		try {
			LoginUpVO sud = (LoginUpVO) JSONObject.toBean(
					JSONObject.fromObject(data), LoginUpVO.class);
			headObject1 = CommonHeadUtil.geneHeadObject("loginAuthoriedService.verifyAndSyncUserInfo");
			ValidationResult bindingResult = ValidationUtils
					.validateEntity(data);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject1, bindingResult);
			}

			log.info("手机会员登录开始---");
			// 用户中心校验账户信息(开发环境不校验)
			if (!Configuration.getInstance()
					.getConfigValue(GlobalStatic.MAIN_ENVIRONMENT)
					.equals(MAIN_ENVIRONMENT_DEV)) {
				sud.setRegIp(request.getRemoteHost());
				sud.setRegType(sud.getLoginNameType());
				sud.setSource(GlobalStatic.CHANNEL_WEB);
				Object retObject=memberService.doServiceByServer(new RequestObject(headObject1, JSONObject.fromObject(sud)));
				if(retObject instanceof HeadObject){
					HeadObject resultObject1=(HeadObject)retObject;
					if (!ErrorCode.SUCCESS.equalsIgnoreCase(resultObject1.getRetCode())) {
						headObject1.setRetCode(resultObject1.getRetCode());
						headObject1.setRetMsg(resultObject1.getRetMsg());
						return new ResultObject(headObject1);
					}
				}else{
					ResultObject resultObject1=(ResultObject)retObject;
					if (!ErrorCode.SUCCESS.equalsIgnoreCase(resultObject1.getRetCode())) {
						headObject1.setRetCode(resultObject1.getRetCode());
						headObject1.setRetMsg(resultObject1.getRetMsg());
						return new ResultObject(headObject1);
					}
				}
			}
			// 用户中心校验成功，则本地校验
			HeadObject headObject = CommonHeadUtil.geneHeadObject("loginAuthoriedService.doLogin");
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
			} catch (ExcessiveAttemptsException e) {
				error = "登录失败次数过多";
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
			dataMap.put("loginNameType", GlobalStatic.ACCOUNT_TYPE_BUYER);
			dataMap.put("loginIp", request.getRemoteHost());
			resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, dataMap));
			if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
				// 账户验证成功 将账户有关信息写入会话
				String sessionId = request.getSession().getId();
				JSONObject accountInfo = JSONObject.fromObject(resultObject.getContent()).accumulate("sessionId",sessionId);
				resultObject.setContent(accountInfo);
				redisService.set(sud.getLoginName() + "loginName", sessionId,SEEEION_DEF_TIME);
				redisService.set(sessionId, accountInfo.toString(),SEEEION_DEF_TIME);
				redisService.set(sessionId + "password", password,SEEEION_DEF_TIME);
				redisService.set(sessionId + "storeId",accountInfo.optString("storeId"),SEEEION_DEF_TIME);
				redisService.set(sessionId + "companyId",accountInfo.optString("companyId"),SEEEION_DEF_TIME);
				redisService.set(sessionId + "companyName",accountInfo.optString("companyName"),SEEEION_DEF_TIME);
			}
		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject1);
		}
		return resultObject;
	}

	/**
	 * @Description:会员注册
	 * @param sud
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public Object personSignUp(String data, HttpServletRequest request)
			throws Exception {
		HeadObject headObject = null;
		try {
			SignUpVO sud = (SignUpVO) JSONObject.toBean(
					JSONObject.fromObject(data), SignUpVO.class);
			headObject = CommonHeadUtil.geneHeadObject("loginAuthoriedService.saveMember");
			ValidationResult bindingResult = ValidationUtils.validateEntity(data);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}

			HeadObject headObject1 = validateThirdSmsCode(request, sud);
			if (!ErrorCode.SUCCESS.equalsIgnoreCase(headObject1.getRetCode())) {
				return new ResultObject(headObject1);
			}
			sud.setRegIp(request.getRemoteHost());
			sud.setRegType("2");// 手机注册
			sud.setSource(GlobalStatic.CHANNEL_AND);
			sud.setAccountType(GlobalStatic.ACCOUNT_TYPE_BUYER);
			sud.setMobile(sud.getLoginName());
			sud.setEmail("");
			return memberService.doServiceByServer(new RequestObject(headObject,JSONObject.fromObject(sud)));
		} catch (Exception e) {
			log.error("数据处理异常{}", e);
			e.printStackTrace();
			return processExpction(headObject);
		}
	}

	/**
	 *  验证短信验证码(短信平台验证)
	 * @param request
	 * @param smsId
	 * @param smsCode
	 * @return
	 * @throws Exception
	 */
	public HeadObject validateThirdSmsCode(HttpServletRequest request, SignUpVO sud) throws Exception {
		HeadObject headObject = new HeadObject();
		String retStr=new SmsVerifyKit(sud.getAppkey(), sud.getLoginName(), sud.getZone(), sud.getSmsCode()).go();
		JSONObject retJson=JSONObject.fromObject(retStr);
//		switch (Integer.valueOf(retJson.get("status").toString())) { 
//		case "200":
//			headObject.setRetCode(ErrorCode.SUCCESS);
//			headObject.setRetMsg("短信服务器验证码验证通过！");
//			break;
//		case "512":
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器拒绝访问，或者拒绝操作");
//			break;
//		case "513":
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器Appkey不存在或被禁用。");
//			break;
//		case "514":
//			headObject.setRetCode(ErrorCode.SUCCESS);
//			headObject.setRetMsg("短信服务器权限不足");
//			break;
//		case "515":
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器内部错误");
//			break;
//		case "517":
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器缺少必要的请求参数");
//			break;
//		case "518":
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器请求中用户的手机号格式不正确（包括手机的区号）");
//			break;
//		case "519":
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器请求发送验证码次数超出限制");
//			break;
//		case "520":
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器无效验证码。");
//			break;
//		case "526":
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器余额不足");
//			break;
//		default:
//			headObject.setRetCode(ErrorCode.FAILURE);
//			headObject.setRetMsg("短信服务器系统错误");
//			break;
//		}
		return headObject;
	}
	
	
	/**
	 * @description 验证短信验证码
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-2-12
	 * @param request
	 * @param smsCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/validateSmsCode")
	@ResponseBody
	public HeadObject validateSmsCode(HttpServletRequest request, String smsId,
			String smsCode) throws Exception {
		HeadObject headObject = new HeadObject();
		String smsCodeSes = redisService.get(smsId);
		// String smsCodeSes = (String)
		// request.getSession().getAttribute(smsId);
		if (StringUtil.isEmpty(smsCodeSes)) {
			headObject.setRetCode(ErrorCode.PDE0001);
			headObject.setRetMsg("请输入手机号码后点击免费获取验证码！");
		} else if (smsCodeSes.equalsIgnoreCase(smsCode)) {
			headObject.setRetCode(ErrorCode.SUCCESS);
			headObject.setRetMsg("短信验证码验证通过！");
		} else {
			headObject.setRetCode(ErrorCode.PDE0001);
			headObject.setRetMsg("短信验证码输入不正确！");
		}
		return headObject;
	}

}
