
package com.cnit.yoyo.member.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.CommentConstant;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberCommentWithBLOBs;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.model.order.OrderComment;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 会员管理
 * @detail 会员评论，咨询，短消息的controller
 * @author xiaox
 * @version 1.0.0
 */
@Controller
@RequestMapping("/memberComment")
public class MemberCommentController {

    @Autowired
    private RemoteService memberService;
    
    @Autowired
    private RemoteService orderService;
   

   @RequestMapping("/toMemberAskList")
   public String toMemberAskList(HttpServletRequest request) {
	   request.setAttribute("objectType",request.getParameter("objectType"));
	   return "pages/biz/comment/consultList";
   }
   
   @RequestMapping("/toMemberComment")
   public String toMemberComment(HttpServletRequest request) {
	   request.setAttribute("objectType",request.getParameter("objectType"));
	   return "pages/biz/comment/commentList";
   }
    
    
    /**
      * @description <b>会员商品咨询数据列表</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-21
      * @param @param objectType
      * @param @param request
      * @param @return
      * @param @throws Exception
      * @return Object
    */
   @ResponseBody
    @RequestMapping("/getMemberAskList")
    public Object getMemberAskList(HttpServletRequest request,MemberCommentQryDTO qryDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030101-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        qryDTO.setObjectType("ask");
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, qryDTO));
        return resultObject.getContent();
    }
    
    /**
     * @description <b>会员商品咨询数据列表</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-21
     * @param @param objectType
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Object
   */
   @ResponseBody
   @RequestMapping("/getMemberComment")
   public Object getMemberComment(HttpServletRequest request,MemberCommentQryDTO qryDTO) throws Exception {
       HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
       return orderService.doService(new RequestObject(headObject, qryDTO));
   }
    
    /**
      * @description <b>获取用户咨询详情</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-22
      * @param @param request
      * @param @param commentId
      * @param @return
      * @param @throws Exception
      * @return Object
    */
    @RequestMapping("/getMemberAskDetail")
    public Object getMemberAskDetail(HttpServletRequest request,@RequestParam(value="commentId",required=true)Long commentId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030101-01");
        ModelAndView modelView = new ModelAndView();
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, commentId));
	    if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
            modelView.addObject("content", resultObject.getContent());
        }
        modelView.setViewName("pages/biz/comment/consultEdit");
        return modelView;
    }

    /**
     * @description <b>获取用户咨询详情</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-22
     * @param @param request
     * @param @param commentId
     * @param @return
     * @param @throws Exception
     * @return Object
   */
   @RequestMapping("/getMemberCommentDetail")
   public Object getMemberCommentDetail(HttpServletRequest request,MemberCommentQryDTO memberCommentQryDTO) throws Exception {
       HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-04");
       ModelAndView modelView = new ModelAndView();
       ResultObject resultObject = orderService.doService(new RequestObject(headObject, memberCommentQryDTO));
	    if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
           modelView.addObject("content", resultObject.getContent());
       }
       modelView.setViewName("pages/biz/comment/commentEdit");
       return modelView;
   }
    
	/**
	  * @description <b>保存回复咨询信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-20
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/doReplyAsk")
	public Object doReplyAsk(HttpServletRequest request,MemberCommentWithBLOBs memberComment){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030101-02");
		ResultObject resultObject=new ResultObject();
		try{
			Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
			String loginName=(String)CommonUtil.getSession(request).getAttribute("loginName");
			Integer accountId=(Integer)CommonUtil.getSession(request).getAttribute("accountId");
			if(null!=storeId){
				memberComment.setStoreId(storeId);
				memberComment.setTime(new Date());
				memberComment.setAuthorId(accountId);
				memberComment.setAuthor(loginName);
				resultObject=memberService.doService(new RequestObject(headObject, memberComment));
				return resultObject;
			}else{
				resultObject.setHead(headObject);
				headObject.setRetCode(ErrorCode.FAILURE);
				headObject.setRetMsg("未登录");
			}
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return resultObject;
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
			return CommonUtil.processExpction(headObject);
		}
	}
	
	/**
	  * @description <b>删解释信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-17
	  * @param @param request
	  * @param @param orderComment
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/doDelCommentReply")
	public Object doDelComment(HttpServletRequest request,OrderComment orderComment){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-06");
		ResultObject resultObject=new ResultObject();
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if(null!=memberListDo){
				resultObject=orderService.doService(new RequestObject(headObject, orderComment.getCommentId()));
				return resultObject;
			}else{
				resultObject.setHead(headObject);
				headObject.setRetCode(ErrorCode.FAILURE);
				headObject.setRetMsg("未登录");
			}
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return resultObject;
	}
	
	/**
	  * @description <b>删除咨询信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-20
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/doDelAsk")
	public Object doDelAsk(HttpServletRequest request,@RequestParam(value="commentId",required=true)String [] commentId){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030101-06");
		try{
			ResultObject resultObject=memberService.doService(new RequestObject(headObject, commentId));
			return resultObject.getHead();
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
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
	@RequestMapping("/openOrCloseComment")
	public Object openOrCloseComment(HttpServletRequest request,@RequestParam(value="commentId",required=true)String [] commentIds,@RequestParam(value="display",required=true)String display){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-07");
		try{
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("commentIds", commentIds);
			map.put("display", display);
			return orderService.doService(new RequestObject(headObject, map));
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
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
	@RequestMapping("/openOrCloseAsk")
	public Object openOrCloseAsk(HttpServletRequest request,@RequestParam(value="commentId",required=true)String [] commentIds,@RequestParam(value="display",required=true)String display){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030101-05");
		try{
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("commentIds", commentIds);
			map.put("display", display);
			ResultObject resultObject=memberService.doService(new RequestObject(headObject, map));
			return resultObject.getHead();
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
		}
	}
	
}
