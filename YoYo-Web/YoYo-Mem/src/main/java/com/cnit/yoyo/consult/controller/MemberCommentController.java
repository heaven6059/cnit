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
@RequestMapping("/memberComment")
public class MemberCommentController {
	
	@Autowired
	 private RemoteService  memberService ;
	
	@Autowired
    private RemoteService orderService;
	
	@RequestMapping("/toMemberComment")
	public String toMemberComment(){
		return "pages/myconsult/memberCommentsList";
	}
	
	/**
	  * @description <b>加载用户的评论</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-15
	  * @param @param request
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/loadMemberComment")
	public Object loadMemberComment(HttpServletRequest request,MemberCommentQryDTO dto)throws Exception{
		HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "020105-03");
		MemberListDo memberDo=CommonUtil.getMemberListDo(request);
   	   	if(null!=memberDo){
   	   		dto.setMemberId(Integer.parseInt(memberDo.getMemberId()));
   	   		ResultObject resultObject=orderService.doService(new RequestObject(headObject, dto));
   	   		return resultObject;
   	   	}else{
   	   		headObject.setRetCode(ErrorCode.FAILURE);
   	   		headObject.setRetMsg("未登录");
   	   	}
   	   	return headObject;
	}
	
	
	/**
	  * @description <b>加载评论的回复</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-15
	  * @param @param request
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/loadMemberCommentReply")
	public Object loadMemberCommentReply(HttpServletRequest request,MemberCommentQryDTO dto)throws Exception{
		HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030101-11");
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
