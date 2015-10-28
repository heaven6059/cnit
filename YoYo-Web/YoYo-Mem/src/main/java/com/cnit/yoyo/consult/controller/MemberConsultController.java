package com.cnit.yoyo.consult.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

@Controller
@RequestMapping("/memberConsult")
public class MemberConsultController {
	@Autowired
	 private RemoteService  memberService ;
	
	@RequestMapping("/toMemberConsult")
	public String toMemberConsult(){
		return "pages/myconsult/memberConsultList";
	}
	
	/**
	  * @description <b>加载用户的信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-15
	  * @param @param request
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/loadMemberConsult")
	public Object loadMemberConsult(HttpServletRequest request,MemberCommentQryDTO dto)throws Exception{
		HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030101-13");
		MemberListDo memberDo=CommonUtil.getMemberListDo(request);
   	   	if(null!=memberDo){
   	   		dto.setMemberId(Integer.parseInt(memberDo.getMemberId()));
   	   		ResultObject resultObject=memberService.doService(new RequestObject(headObject, dto));
   	   		return resultObject;
   	   	}else{
   	   		headObject.setRetCode(ErrorCode.FAILURE);
    	   headObject.setRetMsg("未登录");
   	   	}
   	   	return headObject;
	}
}
