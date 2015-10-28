package com.cnit.yoyo.wx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import cn.taoping.jsonrpc.core.dto.ReqParamsObj;
import cn.taoping.jsonrpc.core.dto.ResponseObj;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.weixin.tencent.WeixinUtil;
import com.cnit.yoyo.weixin.vo.CheckLoginVo;
import com.cnit.yoyo.weixin.vo.TWeixinAccountinfo;

public class BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 获取当前request对象
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}
	
	/**
	 * 获取当前response对象
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return ((ServletWebRequest) RequestContextHolder.currentRequestAttributes()).getResponse();
	}
	
	/**
	 * 获取当前session对象
	 * @return
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 针对用户未登录却直接点击了非登录页面（比如"我的红包"按钮）而做出的处理
	 * @return
	 */
	protected CheckLoginVo unKonwUserLogin() {
		CheckLoginVo checkLoginVo = new CheckLoginVo();
		boolean loginState = this.getUserLoginState();
		if(loginState==true){//处于登录状态
			checkLoginVo.setLoginState(true);
			checkLoginVo.setRequestUrl("");
		}else{//处于未登录状态
			HttpServletRequest request = this.getRequest();
			String servletPath = request.getServletPath();
			System.out.println("unKonwUserLogin servletPath======="+servletPath);
//			String requestURI = request.getRequestURI();//mainControler/queryMyWallet		
//			System.out.println("unKonwUserLogin requestURI======="+requestURI);
			String queryString = request.getQueryString();//a=1&b=2
			if(StringUtils.isNotBlank(queryString)){
				String[] qsArray = queryString.split("&");//a=1 b=2
				for(int j = 0; j<qsArray.length; j++){
					if(qsArray[j].indexOf("code=") != -1){//表示有code参数
						qsArray[j] = "";
					}
				}
				String newStr = "";
				for(int j = 0; j<qsArray.length; j++){//"" a=1 
					if(j == 0){
						newStr = newStr + qsArray[j]; //""
					}else if(j!=0){
						if(newStr.equals("")){
							newStr = newStr + qsArray[j];
						}else{
							newStr = newStr + "&" + qsArray[j];
						}
					}
				}
				queryString = newStr;
			}
			System.out.println("-----unKonwUserLogin queryString-----"+queryString);
			String lastUrl = servletPath+(StringUtils.isEmpty(queryString)?"":("?"+queryString));
			String requestUrl = "/getRedPacket?requestUrl="+lastUrl;
			checkLoginVo.setLoginState(false);
			checkLoginVo.setRequestUrl(requestUrl);
		}
		return checkLoginVo;
	}
	
	/**
	 * 获取微信全局唯一票据
	 * @param fappname
	 * @return
	 */
	public String getAccessToken(String fappname){
//		List<TWeixinAccountinfo> list = this.accountinfoService.findWeixinAccountByName(fappname);
//		if(list.size()>0){
//			return list.get(0).getFaccesstoken();
//		}
		return null;
	}
	
	/**
	 * 获取用户登录状态（true登录  false未登录）
	 * @return
	 */
	public boolean getUserLoginState(){
		HttpServletRequest request = this.getRequest();
		String tokenId = (String) request.getSession().getAttribute(WeixinUtil.SESSION_TP_SID);
		//session中无登录信息
		if(StringUtils.isEmpty(tokenId)){
			//根据openid查询数据库中是否有用户登录信息
			String openid = (String) request.getSession().getAttribute(WeixinUtil.SESSION_TP_OPEN_ID);
//			TWeixinUserLoginInfo userLoginInfo = this.userLoginInfoService.findByOpenid(openid);
//			if(userLoginInfo!=null && userLoginInfo.getFlaststate().equals("1")){//表明上一次用户处于登录状态
//				//后台帮用户执行登录操作
//				ReqParamsObj regReqObj = new ReqParamsObj();
//				Map<String, Object> regMap = new HashMap<String, Object>();
//				regMap.put("userMobile", Long.parseLong(userLoginInfo.getFphonenumber()));
//				regMap.put("verificationCode", "");
//				regMap.put("taskID", "0");//短信ID(调用发送短信接口返回值)可为空 传0表示不验证短信验证码
//				regMap.put("openID", userLoginInfo.getFopenid());//用户关注公众号key
//				regMap.put("openKey", WeixinUtil.TAOPING_KEY);//公众号key,即公众号的微信号
//				regReqObj.setParamMap(regMap);
//				//@return [参数说明] result={userID:1,companyID:0,userAlias:"userName",sid:99}
//				ResponseObj regResponseObj = this.wechatService.setWechatLogin(regReqObj);
//				LOGGER.debug("getUserLoginInfo setWechatLogin 远程返回结果=" + JSON.toJSONString(regResponseObj));
//				if (null!=regResponseObj && regResponseObj.isSuccess()) {//登录成功
//					System.out.println("resultTostring::::::"+regResponseObj.getResult().toString());
//					JSONObject regResultObj = JSONObject.fromObject(regResponseObj.getResult());
//					String userID = regResultObj.get("userID").toString();
//					String sid = regResultObj.get("sid").toString();
//					//存入session
//					request.getSession().setAttribute(WeixinUtil.SESSION_TP_USERID, userID);
//					request.getSession().setAttribute(WeixinUtil.SESSION_TP_SID, sid);
//					request.getSession().setAttribute(WeixinUtil.SESSION_TP_USERMOBILE, userLoginInfo.getFphonenumber());
//					return true;
//				}else{
//					System.out.println("getUserLoginInfo setWechatLogin 登录失败");
//					return false;
//				}
//			}else{
//				//用户上一次处于未登录状态，或者用户尚未成为我们的微信用户
//				return false;
//			}
		}
		return true;
	}
}
