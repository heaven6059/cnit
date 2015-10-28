package com.cnit.yoyo.pamauth.controller;

/**   
 * @Description: 绑定账号
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.thirdaccount.ThirdAccount;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@Controller
@RequestMapping("/pamAuth")
public class PamAuthController {

	public static final Logger logger = LoggerFactory.getLogger(PamAuthController.class);

	@Autowired
	private RemoteService memberService;

	// @Autowired
	// private RedisClientUtil redisService;//redis缓存服务

	/**
	 * 
	 * @Description: 获取绑定账号页面
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPamAuthListPage")
	public Object getPamAuthListPage() throws Exception {
		return "/pages/biz/pamauth/pamAuthList";
	}

	/**
	 * @Description: 查询绑定账号
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPamAuthList")
	@ResponseBody
	public Object getPamAuthList(HttpServletRequest request){
        logger.info("###########YOYOMem-->PamAuthController.getPamAuthList----->start");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-29", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		HttpSession session = CommonUtil.getSession(request);
		int account = (int) session.getAttribute("accountId");
		ResultObject resultObject = null;
        try {
            resultObject = memberService.doService(new RequestObject(headObject, account));
        } 
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMem-->PamAuthController.getPamAuthList----->end");
		return resultObject;
	}
	
	/**
	 * 取消绑定
	 * @param request
	 * @return
	 */
	@RequestMapping("/removeBinding")
	@ResponseBody
	public Object removeBinding(HttpServletRequest request){
        logger.info("###########YOYOMem-->PamAuthController.removeBinding----->start");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-30", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		//第三方帐号表主键
		Integer accountId = Integer.valueOf(request.getParameter("id"));
		ResultObject resultObject = null;
        try {
            resultObject = memberService.doService(new RequestObject(headObject, accountId));
        } 
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMem-->PamAuthController.removeBinding----->end");
		return resultObject;
	}
	
	 /**
     * 手动在账号中心绑定第三方账户（qq）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/bindingQQ")
	public void bindExistAccount(HttpServletRequest request,HttpServletResponse response) {
		logger.info("###########YOYOPortal-->ContentSignController.bindExistAccount----->start");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-31", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		//yoyo表主键
		Integer pamAccountId = (Integer)CommonUtil.getSession(request).getAttribute("accountId");
		try {
			if(!checkIfExist(request, pamAccountId)){
				AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
				String accessToken = "";
				String openID = "";
				String qqName = "";
				long tokenExpireIn = 0L;
				if (accessTokenObj.getAccessToken().equals("")) {
					// 我们的网站被CSRF攻击了或者用户取消了授权,做一些数据统计工作
					logger.info("没有获取到响应参数");
				} else {
					accessToken = accessTokenObj.getAccessToken();
					tokenExpireIn = accessTokenObj.getExpireIn();
					request.getSession().setAttribute("demo_access_token",accessToken);
					request.getSession().setAttribute("demo_token_expirein",String.valueOf(tokenExpireIn));
					// 利用获取到的accessToken 去获取当前用的openid -------- start
					OpenID openIDObj = new OpenID(accessToken);
					openID = openIDObj.getUserOpenID();
					logger.info("openID:" + openID );
					request.getSession().setAttribute("openID", openID);
					// 利用获取到的accessToken 去获取当前用户的openid --------- end
					//获取昵称--->start
					UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
					UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
					if (userInfoBean.getRet() == 0) {
						qqName = userInfoBean.getNickname();
					} else {
						logger.info("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
					}
					//获取昵称--->end
					ThirdAccount tAccount = new ThirdAccount();
					tAccount.setOpenId(openID);
					tAccount.setAccountName(qqName);
					tAccount.setAccountType("QQ");
					tAccount.setPamAccountId(pamAccountId);
			        Date now = new Date();
			        tAccount.setCreateTime(now);
			        tAccount.setUpdateTime(now);
		            memberService.doService(new RequestObject(headObject, tAccount));
					response.sendRedirect("getPamAuthListPage");
				}
			}
		} catch (QQConnectException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		logger.info("###########YOYOPortal-->ContentSignController.bindExistAccount----->end");
	}
    
    private Boolean checkIfExist(HttpServletRequest request, Integer pamAccountId) throws Exception{
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-32", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		ThirdAccount tAccount = new ThirdAccount();
		tAccount.setPamAccountId(pamAccountId);
		tAccount.setAccountType("QQ");
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, tAccount));
		if(resultObject.getRetCode() == ErrorCode.IS_EXIST){
			return true;
		}else{
			return false;
		}
    }
}
