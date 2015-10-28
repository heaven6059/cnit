package com.cnit.yoyo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.api.vo.CarBrandVO;
import com.cnit.yoyo.api.vo.OrderCommentVO;
import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.base.validation.ValidationResult;
import com.cnit.yoyo.base.validation.ValidationUtils;
import com.cnit.yoyo.constant.CommentConstant;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.model.order.OrderComment;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/commentsManage")
public class CommentsManagerController extends BaseController {

	@Autowired
    private RemoteService orderService;
	
	
	/**
	 * @Title:  commentsListData  
	 * @Description:  评论列表  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-25 下午4:34:48  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @param request
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/commentsListData")
    public Object commentsListData(String data, HttpServletRequest request) {
		log.info("---------CommentsManagerController.commentsListData-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
	    ResultObject resultObject = null;
    	try {
    	    MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
    	    if(null != memberListDo){
    	    	JSONObject obj = JSON.parseObject(data);
		    	String pageIndex = obj.getString("page");
	        	String pageSize =obj.getString("rows");
	        	
	    		MemberCommentQryDTO qryDTO = new MemberCommentQryDTO();
	    		qryDTO.setPage(StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
	    		qryDTO.setRows(StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
	    		qryDTO.setStoreId(memberListDo.getStoreId().intValue());
	       		qryDTO.setCompanyId(memberListDo.getCompanyId());
	       		qryDTO.setObjectType("order");
		    	headObject = CommonHeadUtil.geneHeadObject("orderCommentService.findMemberComment");
	       		resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, qryDTO));
    	    }
	    	
	    } catch (Exception e) {
	    	log.error(e.getMessage(),e);
	    	return processExpction(e.getMessage());
	    }
		return resultObject;
	}
	
	/**
	 * @Title:  replyComment  
	 * @Description:  卖家回复评论  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-25 下午4:35:02  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @param request
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/replyComment")
    public Object replyComment(String data, HttpServletRequest request) {
		log.info("---------CommentsManagerController.replyComment-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
	    ResultObject resultObject = null;
	    
	    OrderCommentVO orderCommentVO = JSON.parseObject(data, OrderCommentVO.class);
		ValidationResult bindingResult = ValidationUtils.validateEntity(orderCommentVO);
		if (bindingResult.isHasErrors()) {
			return elementErrors(headObject, bindingResult);
		}
		
	    try{
		    MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
			if(null!=memberListDo){
				orderCommentVO.setCommentsType(CommentConstant.CommentsType.EXPLAIN.getKey());
				orderCommentVO.setMemberId(Long.parseLong(memberListDo.getMemberId()));
				OrderComment record = new OrderComment();
				BeanUtils.copyProperties(record,orderCommentVO);
				headObject = CommonHeadUtil.geneHeadObject("orderCommentService.saveOrderComment");
	       		resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, record));
			}else{
				return CommonUtil.notLoign(headObject);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("数据处理异常{}",e);
			return processExpction(headObject);
		}
	    return resultObject;
	}
	
	/**
	 * @Title:  commentDisplay  
	 * @Description: 评论显示  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-25 下午4:35:12  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @param request
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/commentDisplay")
	public Object commentDisplay(String data, HttpServletRequest request) {
		log.info("---------CommentsManagerController.commentDisplay-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
	    ResultObject resultObject = null;
		try{
			JSONObject obj = JSON.parseObject(data);
			if(null == obj.getString("commentId")){
				return processExpction("commentId不能为空！");
			}else if(null == obj.getString("display")){
				return processExpction("display不能为空！");
			}
		    String [] ids = {obj.getString("commentId")};
			headObject = CommonHeadUtil.geneHeadObject("orderCommentService.updateOrderCommentDisply");
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("commentIds", ids);
			map.put("display", obj.getString("display"));
			resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, map));
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			return CommonUtil.processExpction(headObject);
		}
		return resultObject;
	}
}

