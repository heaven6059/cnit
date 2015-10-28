package com.cnit.yoyo.shop.businessinfo.controller;

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

/**  
* @Title: AccountSecurity.java
* @Package com.cnit.yoyo.shop.businessinfo.controller
* @Description: 卖家账户安全信息
* @Author 王鹏
* @date 2015-6-5 下午4:58:10
* @version V1.0  
*/
@Controller
@RequestMapping("/accountSecurity")
public class AccountSecurityController {
	
	 @Autowired
	 private RemoteService memberService;
	
	@RequestMapping("/toAccountSecurity")
	public String toAccountSecurity(){
		return "pages/accountsecurity/accountSecurityPage";
	}
	
	/**
	  * @description <b>修改账户密码</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-5
	  * @param @param request
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/updateAccountPwd")
	public Object updateAccountPwd(HttpServletRequest request){
		 HeadObject head =  CommonHeadUtil.geneHeadObject(request, "030108-03");		 
		 try{
			 String oldPassword =  request.getParameter("oldPassword");
			 String password = request.getParameter("password");
			 Map<String, Object> paramMap=new HashMap<String, Object>();
			 MemberListDo memberDo = CommonUtil.getMemberListDo(request);
		     if(null!=memberDo){
		    	 paramMap.put("loginName", memberDo.getLoginName());
		    	 paramMap.put("oldPassword", oldPassword);
		    	 paramMap.put("password", password);
			     return memberService.doService(new RequestObject(head, paramMap));
		     }else{
		    	 return CommonUtil.notLoign(head);
		     }
		 }catch (Exception e) {
			 e.printStackTrace();
			 return CommonUtil.processExpction(head);
		 }
	}
}
