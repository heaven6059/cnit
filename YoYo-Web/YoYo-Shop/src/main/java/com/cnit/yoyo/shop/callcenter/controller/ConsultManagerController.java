package com.cnit.yoyo.shop.callcenter.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnit.yoyo.constant.CommentConstant;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberCommentWithBLOBs;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**  
* @Title: ConsultManagerController.java
* @Package com.cnit.yoyo.shop.callcenter.controller
* @Description: 卖家中心>客服中心>咨询管理Controller
* @Author 王鹏
* @date 2015-5-4 下午3:29:03
* @version V1.0  
*/
@Controller
@RequestMapping("/consultManager")
public class ConsultManagerController {
	
	@Autowired
    private RemoteService memberService;
	
	@RequestMapping("/toConsult")
	public String toConsult(){
		return "pages/callcenter/consultList";
	}
	
	
	/**
     * @description <b>加载当前店铺的咨询消息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-21
     * @param @param request
     * @param @return
     * @return Object
   */
   @RequestMapping("/loadConsultList")
   public Object loadOrderList(HttpServletRequest request,MemberCommentQryDTO qryDTO){
	    ResultObject resultObject = null;
   		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030101-04");
   		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
   		try {    	   
    	   if(null!=memberListDo){
    		   qryDTO.setStoreId(memberListDo.getStoreId().intValue());
    		   qryDTO.setCompanyId(memberListDo.getCompanyId());
    		   qryDTO.setRows(GlobalStatic.DEFAULT_PAGE_SIZE_10);
    		   qryDTO.setObjectType("ask");
    	   }
           resultObject = memberService.doService(new RequestObject(headObject, qryDTO));
   		} catch (Exception e) {
           e.printStackTrace();
   		}
   		return resultObject;
   }
   
   /**
  * @description <b>回复咨询</b>
  * @author 王鹏
  * @version 1.0.0
  * @data 2015-7-2
  * @param @param request
  * @param @param memberCommentWithBLOBs
  * @param @return
  * @return Object
*/
@RequestMapping("/doReplyComment")
   public Object doCommentReply(HttpServletRequest request,MemberCommentWithBLOBs memberCommentWithBLOBs){
	   HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030101-02");
		ResultObject resultObject=new ResultObject();
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if(null!=memberListDo){
				memberCommentWithBLOBs.setMemberId(Integer.parseInt(memberListDo.getMemberId()));
				memberCommentWithBLOBs.setAuthor(memberListDo.getLoginName());
				memberCommentWithBLOBs.setTime(new Date());
				memberCommentWithBLOBs.setStoreId(memberListDo.getStoreId().intValue());
				memberCommentWithBLOBs.setCompanyId(memberListDo.getCompanyId());
				memberCommentWithBLOBs.setCommentsType(CommentConstant.CommentsType.REPLY.getKey());				
				resultObject=memberService.doService(new RequestObject(headObject, memberCommentWithBLOBs));
				return resultObject;
			}else{
				return CommonUtil.notLoign(headObject);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return CommonUtil.processExpction(headObject);
		}
   }
   
   
}
