package com.cnit.yoyo.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.CommentConstant.CommentsType;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.OrderComment;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

@Controller
@RequestMapping("/orderComment")
public class OrderCommentController {
    
	@Autowired
    private RemoteService orderService;
	
	
	 /**
     * 
     * @Description: 订单评论界面
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/orderComment")
    public String viewOrder(HttpServletRequest request,OrderQryDTO qryDTO){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = orderService.doService(new RequestObject(headObject, qryDTO));
            request.setAttribute("order", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/biz/order/orderComment";
    }
	
    
    @ResponseBody
    @RequestMapping("/addOrderComment")
    public Object addOrderComment(HttpServletRequest request,OrderComment orderComment){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-01");
    	try{
    		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
    		if(null!=memberListDo){
    			orderComment.setCreateTime(new Date());
    			orderComment.setCommentsType(CommentsType.COMMENT.getKey().toString());
    			orderComment.setMemberId(Long.parseLong(memberListDo.getMemberId()));
	    		ResultObject resultObject = orderService.doService(new RequestObject(headObject, orderComment));
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
