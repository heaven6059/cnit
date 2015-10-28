package com.cnit.yoyo.wx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.taoping.jsonrpc.core.dto.ReqParamsObj;
import cn.taoping.jsonrpc.core.dto.ResponseObj;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.thirdaccount.ThirdAccount;
import com.cnit.yoyo.system.login.service.SignService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.weixin.tencent.WeixinUtil;
import com.cnit.yoyo.weixin.vo.ResultJson;
import com.cnit.yoyo.wx.util.AccessTokenConfiguration;

@Controller
@RequestMapping("/weixin")
public class WeiXinIndexController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinIndexController.class);
	private final Integer SEEEION_DEF_TIME=1000*60*24*3;//默认保存3天
	@Autowired
	private SignService signService;
	@Autowired
	private RedisClientUtil redisService;
	/**
	 * 优优车微信登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String loginIndex(ModelMap model){
		HttpServletRequest request = this.getRequest();
		Integer tokenId = (Integer) request.getSession().getAttribute(WeixinUtil.SESSION_TP_SID);
		//已登录
		if(tokenId != null){
			return "pages/mobile/login/loggedIn";
		}
		String requestUrl =  request.getParameter("requestUrl"); 
		System.out.println("login requestUrl----" + requestUrl);
		if(StringUtils.isEmpty(requestUrl)){
			requestUrl = "/weixin/redPacketSuccess";
		}
//		if(requestUrl.startsWith("/TaoPingWap/")){//后期可能工程名会改，需动态获取匹配
//			requestUrl = requestUrl.replace("/TaoPingWap", "");
//		}
		model.addAttribute("requestUrl", requestUrl);
		return "pages/mobile/login/login";
	}
	
	/**
	 * 领取红包成功页面(第一次成功领取红包才会进该入口)
	 * @return
	 */
	@RequestMapping("/redPacketSuccess")
	public String walletSuccess(ModelMap model){
		model.addAttribute("firstTimeFlag", true);//第一次成功获取红包
		return "pages/mobile/redPacket/getSuccess";
	}
	
	/**
	 * 获取验证码（掌淘科技）
	 * @param ftelephone
	 * @return
	 */
	@RequestMapping("/getMsgVerifyCode")
	@ResponseBody
	public Object getMsgVerifyCode(@RequestParam(value = "ftelephone", required = true)String ftelephone){
		ResultJson resultJson = new ResultJson(true);
		resultJson.setSuccess(false);
//		try {
//			
//			JSONObject jsonObject = SmsVerifyKit.smsGo(ftelephone);
//			System.out.println("getMsgVerifyCode-----"+jsonObject);
//			if(jsonObject==null || !"200".equals(jsonObject.getString("status"))){//返回状态码为200表示正常收到短信
//				resultJson.setSuccess(false);
//			}
//		} catch (Exception e) {
//			resultJson.setSuccess(false);
//			e.printStackTrace();
//		}
		return resultJson;
	}
	
	/**
	 * 领取红包入口页面
	 * @return
	 */
	@RequestMapping("/getRedPacket")
	public String redPacketIndex(ModelMap model){
		HttpServletRequest request = this.getRequest();
		//已登录则说明已成功领取过红包，则跳转至领取成功页面
		if(this.getUserLoginState()==true){
			model.addAttribute("firstTimeFlag", false);
			return "redPacket/getSuccess";
		}
		//因不提供解绑接口，所以除非系统异常，一般到这一步就表明用户是第一次注册或者用户未注册过直接点击别的按钮页面
		String requestUrl =  request.getParameter("requestUrl"); 
		if(StringUtils.isEmpty(requestUrl)){
			requestUrl = "/mainController/redPacketSuccess";
		}
//		if(requestUrl.startsWith("/TaoPingWap/")){//后期可能工程名会改，需动态获取匹配
//			requestUrl = requestUrl.replace("/TaoPingWap", "");
//		}
		model.addAttribute("requestUrl", requestUrl);
		return "redPacket/redPacketIndex";
	}
	
	/**
	 * 微信注册+登录+绑定+领取红包
	 * @param request
	 * @return
	 */
	@RequestMapping("/weixinLogin")
	@ResponseBody
	public ResultJson weixinLogin(){
		HttpServletRequest request = this.getRequest();
		ResultJson resultJson = new ResultJson(true);
		String ftelephone = request.getParameter("ftelephone");
		String verifyCode = request.getParameter("verifyCode");
		String requestUrl = request.getParameter("requestUrl");
		String openid = (String) request.getSession().getAttribute(WeixinUtil.SESSION_TP_OPEN_ID);
		try {
			//校验验证码是否正确
//			if(WeixinUtil.FREENUMBERS.indexOf(ftelephone) == -1){
//				JSONObject jsonObject = SmsVerifyKit.smsGoCode(ftelephone, verifyCode);
//				if(jsonObject==null || !"200".equals(jsonObject.getString("status"))){//返回状态码为200表示正常
//					resultJson.setSuccess(false);
//					resultJson.setMsg("验证码不正确");
//					return resultJson;
//				}
//			}
			//注册+登录+绑定
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-28", GlobalStatic.SYSTEM_CODE_THIRD, GlobalStatic.SYSTEM_CODE_FRONT);
            ThirdAccount thirdAccount=new ThirdAccount();
            thirdAccount.setOpenId(openid);
            thirdAccount.setLoginName(ftelephone);
            thirdAccount.setSource("23");
//            thirdAccount.setAccountName(userInfoBean.getNickname());
            thirdAccount.setLoginPassword("888888");
            thirdAccount.setAccountType(GlobalStatic.THIRD_PART_WX);
            thirdAccount.setYyAccountType(GlobalStatic.ACCOUNT_TYPE_BUYER);
            ResultObject thirdAccountResultObject = signService.doCommon(headObject, thirdAccount);
            ////注册+登录+绑定成功，执行登录操作
            if (ErrorCode.SUCCESS.equalsIgnoreCase(thirdAccountResultObject.getRetCode())) {
            	resultJson.setSuccess(true);
            	resultJson.setMsg("注册成功");
            }else{
            	resultJson.setSuccess(false);
            	resultJson.setMsg("绑定失败,请稍后重试");
            	return resultJson;
            }
            MemberListDo accountInfo=(MemberListDo) JSONObject.toBean(JSONObject.fromObject(thirdAccountResultObject.getContent()), MemberListDo.class);
//			JSONObject regResultObj = JSONObject.fromObject(regResponseObj.getResult());
//			String userID = regResultObj.get("userID").toString();
//			String sid = regResultObj.get("sid").toString();
			//微信发送注册成功并领取优惠券消息
			//红包领取成功才将登录信息存入session
			request.getSession().setAttribute(WeixinUtil.SESSION_TP_ACCOUNTID, accountInfo.getAccountId());
			request.getSession().setAttribute(WeixinUtil.SESSION_TP_SID, accountInfo.getAccountId());
			request.getSession().setAttribute(WeixinUtil.SESSION_TP_USERMOBILE, ftelephone);
			//保存用户登录信息至缓存
			String accountCache=redisService.get(openid);
			/**
			 * TODO
			 * 查询是否有领过优惠券 
			 */
			if(accountCache == null){
				//不论模板消息是否发送成功，都继续往后执行，毕竟这是次要的
				WeixinUtil.sendTemplateMsgForReg(AccessTokenConfiguration.getInstance().getAccessToken(), ftelephone, openid);
			}
//			TWeixinUserLoginInfo entity = this.userLoginInfoService.findByOpenid(openid);
			if(accountCache!=null && accountCache.length() > 0){
				redisService.set(openid, JSONObject.fromObject(accountInfo).toString(),SEEEION_DEF_TIME);
			}else{//说明用户是第一次注册并领取优惠券
				redisService.set(openid, JSONObject.fromObject(accountInfo).toString(),SEEEION_DEF_TIME);
			}
		} catch (Exception e) {
			resultJson.setSuccess(false);
			resultJson.setMsg("登录失败,请稍候重试");
			e.printStackTrace();
		}
		resultJson.setObject(requestUrl);
		return resultJson;
	}
	
//	/**
//	 * 解除绑定页面
//	 * @return
//	 */
//	@RequestMapping("/unbindIndex")
//	public String unbindIndex(){
//		CheckLoginVo ckLoginVo = this.unKonwUserLogin();
//		if(!ckLoginVo.getLoginState()){
//			return "redirect:"+ckLoginVo.getRequestUrl();
//		}
//		return "bind/unbindIndex";
//	}
//	
//	/**
//	 * 执行解绑操作
//	 * @return
//	 */
//	@RequestMapping("/weixinUnbind")
//	@ResponseBody
//	public ResultJson weixinUnbind(){
//		HttpServletRequest request = this.getRequest();
//		ResultJson resultJson = new ResultJson(true);
//		try {
//			HttpSession session = request.getSession();
//			String userId = (String) session.getAttribute(WeixinUtil.SESSION_TP_USERID);
//			String tokenId = (String) session.getAttribute(WeixinUtil.SESSION_TP_SID);	
//			String openid = (String) request.getSession().getAttribute(WeixinUtil.SESSION_TP_OPEN_ID);
//			String userMobile = (String) request.getSession().getAttribute(WeixinUtil.SESSION_TP_USERMOBILE);
//			ReqParamsObj reqObj = new ReqParamsObj();
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("userID", Long.parseLong(userId));
//			paramMap.put("tokenID", Long.parseLong(tokenId));
//			paramMap.put("openID", openid);
//			paramMap.put("openKey", WeixinUtil.TAOPING_KEY);
//			paramMap.put("userMobile", Long.parseLong(userMobile));
//			paramMap.put("verificationCode", "");
//			paramMap.put("taskID", "0");
//			reqObj.setParamMap(paramMap);
//			ResponseObj responseObj = this.wechatService.setWechatUnbind(reqObj);
//			if(responseObj==null || !responseObj.isSuccess()){
//				resultJson.setSuccess(false);
//				resultJson.setMsg("解除绑定失败，请稍后重试！");
//				return resultJson;
//			}
//			//清空session中的userID+SID(即tokenId)+openid+userMobile
//			session.setAttribute(WeixinUtil.SESSION_TP_USERID, null);
//			session.setAttribute(WeixinUtil.SESSION_TP_SID, null);
//			session.setAttribute(WeixinUtil.SESSION_TP_OPEN_ID, null);
//			session.setAttribute(WeixinUtil.SESSION_TP_USERMOBILE, null);
//			//更新数据库用户登录状态为未登录
//			TWeixinUserLoginInfo entity = new TWeixinUserLoginInfo();
//			entity.setFopenid(openid);
//			entity.setFlaststate("0");
//			entity.setFupdatetime(TimeHelper.getCurrentTimeString());
//			this.userLoginInfoService.updateState(entity);
//		} catch (Exception e) {
//			resultJson.setSuccess(false);
//			resultJson.setMsg("解除绑定失败，请稍后重试！");
//			e.printStackTrace();
//		}
//		return resultJson;
//	}
	
	/**
	 * 进入红包使用规则详情页
	 * @return
	 */
	@RequestMapping("/description")
	public String description(){
		return "description";
	}
	
	/**
	 * 已成功登录页面
	 * @return
	 */
	/*@RequestMapping("/loggedIn")
	public String loggedInIndex(){
		return "login/loggedIn";
	}*/
	
	//暂时弃用
	/*@RequestMapping("/unbindSuccess")
	public String unbindSuccess(){
		this.getSession().setAttribute(WeixinUtil.SESSION_TP_OPEN_ID, null);
		return "bind/unbindSuccess";
	}*/
}
