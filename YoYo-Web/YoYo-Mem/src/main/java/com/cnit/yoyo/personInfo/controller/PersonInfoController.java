package com.cnit.yoyo.personInfo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.MemberWithBLOBs;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**   
 * @Package com.cnit.yoyo.personInfo.controller 
 * @Description: 修改手机号和Email个人信息
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-27 下午1:39:53 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Controller
@RequestMapping("/personInfo")
public class PersonInfoController {
	
	 @Autowired
	 private RemoteService  memberService ;
	 
	 /**
	  * 
	  * @Description: 获取会员手机号 和 邮箱信息 
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/getPersonInfo")
	 public Object getPersonInfo(HttpServletRequest request  ) throws Exception
	 { 
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030102-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		 if(null!=memberListDo){
			 ResultObject resultObject =(ResultObject)memberService.doService(new RequestObject(headObject,Integer.valueOf(memberListDo.getMemberId())));
			 request.setAttribute("member", resultObject.getContent());
		 }
		 return  "/pages/biz/personInfo/personInfoMain";
	 }
	 
	 
	 /**
	  * 
	  * @Description: 更改个人信息 
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/updatePersonInfo")
	 @ResponseBody
	 public Object updatePersonInfo(HttpServletRequest request,MemberWithBLOBs memberDTO) throws Exception{
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030102-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 RequestObject  requestObject  =  new RequestObject(headObject, memberDTO);
		 ResultObject resultObject =(ResultObject) memberService.doService(requestObject);
		 return resultObject;
	 } 

}

