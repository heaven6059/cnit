package com.cnit.yoyo.accountsecurity.controller;
/**   
 * @Description: 积分
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

@Controller
@RequestMapping("/accountsecurity")
public class AccountSecurityController {
	
	 @Autowired
	 private RemoteService  memberService ;
	 
	 //@Autowired
	 //private RedisClientUtil  redisService ;
	 
	 
	 /**
	  * 
	  * @Description: 获取密码页面
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/getAccountSecurityPage")
	 public Object getAccountSecurityPage() { 
		
		 return "/pages/biz/accountsecurity/accountSecurityPage";
	 }
	 
	 /**
	  * 
	  * @Description: 修改密码
	  * @param request
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/updatePassword")
	 @ResponseBody
	 public Object updatePassword(HttpServletRequest request  ) throws Exception{ 
		 String oldPassword =  request.getParameter("oldPassword");
		 String newPassword = request.getParameter("newPassword");
		 String confirmPassword = request.getParameter("confirmPassword");
		 
		 String accountInfo = (String) CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO);//获取当前用户信息
	     JSONObject account = JSONObject.fromObject(accountInfo);
	     MemberListDo memberDo=(MemberListDo) JSONObject.toBean(account,MemberListDo.class);
	     String loginName = memberDo.getLoginName();
	     int accountId = memberDo.getAccountId();
	     
	     PamAccount  pamAccount =  new PamAccount();// 账号设值
	     pamAccount.setLoginName(loginName);
	     pamAccount.setLoginPassword(oldPassword);
	     pamAccount.setAccountId(accountId); 
	     
		 Map<String,Object> oldParaData = new HashMap<String, Object>();
		 oldParaData.put("pamAccount", pamAccount);
		 HeadObject oldPasshead =  CommonHeadUtil.geneHeadObject(request, "030108-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 RequestObject  oldPassRequest  =  new RequestObject(oldPasshead, oldParaData);
		 ResultObject oldPassResult =(ResultObject) memberService.doService(oldPassRequest);
		 
		 if(ErrorCode.SUCCESS.equals(oldPassResult.getRetCode())){// 输入旧密码与数据库密码一致
			 Map<String,Object> newParaData = new HashMap<String, Object>();
			 newParaData.put("newPassword",newPassword);
			 newParaData.put("oldPassword",oldPassword);
			 newParaData.put("confirmPassword",confirmPassword);
			 newParaData.put("loginName", loginName);
			 newParaData.put("accountId", accountId);
			 HeadObject     updatePasshead =  CommonHeadUtil.geneHeadObject(request, "030108-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
			 RequestObject  updatePassRequest  =  new RequestObject(updatePasshead, newParaData);
			 ResultObject   updatePassResult =(ResultObject) memberService.doService(updatePassRequest);
			 return  updatePassResult;
		 }else{
			 return  oldPassResult;
		 }
	 }

}

