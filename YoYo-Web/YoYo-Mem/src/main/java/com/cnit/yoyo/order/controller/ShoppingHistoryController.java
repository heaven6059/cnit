package com.cnit.yoyo.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**  
* @Title: BuyHistoryController.java
* @Package com.cnit.yoyo.order.controller
* @Description: 购买历史记录
* @Author 王鹏
* @date 2015-6-16 下午6:56:24
* @version V1.0  
*/ 
@Controller
@RequestMapping("/shoppingHistory")
public class ShoppingHistoryController {
	
	@Autowired
    private RemoteService orderService;
	
	@RequestMapping("/toShoppingHistory")
	public String toShoppingHistory(HttpServletRequest request,OrderQryDTO dto){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-09");
    	try{
    		MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
    		dto.setMemberId(Long.parseLong(memberListDo.getMemberId()));
    		ResultObject resultObject = orderService.doService(new RequestObject(headObject, dto));
    		request.setAttribute("history", resultObject.getContent());
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        return "pages/biz/shoppinghistory/shoppinghistory";
	}
}
