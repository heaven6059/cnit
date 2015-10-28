package com.cnit.yoyo.shop.trade.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.CommentConstant;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.model.order.OrderComment;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**  
* @Title: CommentsManagerController.java
* @Package com.cnit.yoyo.shop.trade.controller
* @Description: 卖家中心>>交易管理>>评论管理Controller
* @Author 王鹏
* @date 2015-4-20 下午1:39:56
* @version V1.0  
*/ 
@Controller
@RequestMapping("/commentsManager")
public class CommentsManagerController {
	
	public static final Logger log = LoggerFactory.getLogger(CommentsManagerController.class);

	
	@Autowired
    private RemoteService memberService;
	
	@Autowired
    private RemoteService orderService;
	
	
	
	/**
	  * @description <b>查询属于当前商家的评论信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-20
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/toCommentsList")
	public String toCommentsList(HttpServletRequest request,MemberCommentQryDTO qryDTO){
		return "pages/tradeMng/commentsList";
	}
	
	/**
	  * @description <b>查询属于当前商家的评论信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-20
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@ResponseBody
	@RequestMapping("/getCommentsListData")
	public Object getCommentsListData(HttpServletRequest request,MemberCommentQryDTO qryDTO){
       HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-03");
     //TODO 从session中获取 店铺id
       try {
	    MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
       	if(null!=memberListDo){
       		qryDTO.setStoreId(memberListDo.getStoreId().intValue());
       		qryDTO.setCompanyId(memberListDo.getCompanyId());
       		qryDTO.setObjectType("order");
       		ResultObject resultObject = orderService.doService(new RequestObject(headObject, qryDTO));
       		return resultObject;
       	}else{
       		return CommonUtil.notLoign(headObject);
       	}
       } catch (Exception e) {
           e.printStackTrace();
      		log.error(e.toString());
           return CommonUtil.processExpction(headObject);
       }
	}
	
	/**
	  * @description <b>保存评论回复</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-17
	  * @param @param request
	  * @param @param orderComment
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/doCommentReply")
	public Object doReplyComment(HttpServletRequest request,OrderComment orderComment){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-01");
		ResultObject resultObject=new ResultObject();
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if(null!=memberListDo){
				orderComment.setCommentsType(CommentConstant.CommentsType.EXPLAIN.getKey());
				orderComment.setMemberId(Long.parseLong(memberListDo.getMemberId()));
				resultObject=orderService.doService(new RequestObject(headObject, orderComment));
				return resultObject;
			}else{
				return CommonUtil.notLoign(headObject);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			return CommonUtil.processExpction(headObject);
		}
	}
	
	
	/**
	  * @description <b>显示或关闭咨询信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-20
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@ResponseBody
	@RequestMapping("/setCommentDisplay")
	public Object openOrCloseComment(HttpServletRequest request,@RequestParam(value="commentId",required=true)String [] commentIds,@RequestParam(value="display",required=true)String display){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-07");
		try{
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("commentIds", commentIds);
			map.put("display", display);
			return orderService.doService(new RequestObject(headObject, map));
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
		}
	}
}
