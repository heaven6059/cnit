package com.cnit.yoyo.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.OrderConstant.OrderStatus;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;


/**   
 * @Description: 会员中心
 * @author  王鹏
 * @date 2015年7月29日 上午10:40:14
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Controller
public class MemberCenterController {
	@Autowired
    private RemoteService orderService;
	
	@Autowired
	private RemoteService memberService;
	
	/**
	 * @description <b>用户中心首页</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月29日
	 * @param request
	 * @return
	 * String
	*/
	@RequestMapping("/memberCenter/index")
	public String memberCenterIndex(HttpServletRequest request){
		MemberListDo memberDo=CommonUtil.getMemberListDo(request);
		this.findOrderTips(request, Integer.parseInt(memberDo.getMemberId()));
		this.findOrderInfos(request);
		this.selectMember(memberDo.getAccountId(), request);
		this.loadProductWishList(request);
		request.setAttribute("memberDo", memberDo);
		return "/pages/membercenter/memberCenterIndex";
	}
	
	
	  private void selectMember(Integer accountId,HttpServletRequest request){
		  try{
			if (accountId != null && accountId != 0) 
			{
				HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-17",GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("accountId", accountId);
				ResultObject resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
				request.setAttribute("member", resultObject.getContent());
			}
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		}
	
	/**
	 * @description <b>订单统计提示信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月29日
	 * @param request
	 * @param memberId
	 * void
	*/
	private void findOrderTips(HttpServletRequest request,Integer memberId){
		HeadObject headObject = null;
		try{
			headObject = CommonHeadUtil.geneHeadObject(request, "020104-11");
			ResultObject resultObject = orderService.doService(new RequestObject(headObject,memberId));
		    request.setAttribute("orderTips", resultObject.getContent());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @description <b>查询用户中心订单信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-10
	 * @return
	 * Object
	*/
	@ResponseBody
	@RequestMapping("/memberCenter/findOrderInfos")
	private Object findOrderInfos(HttpServletRequest request){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-01");
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			String status=StringUtil.isEmpty(request.getParameter("status"))?OrderStatus.CREATE.getKey():request.getParameter("status");
			dataMap.put("memberId", memberListDo.getMemberId());
			dataMap.put("pageNum", 1);
			dataMap.put("pageSize", 10);
	        dataMap.put("status",status);
	        if(OrderStatus.CREATE.getKey().equals(status)){
	        	dataMap.put("payStatus",0);	
	        }
	        ResultObject resultObject = orderService.doService(new RequestObject(headObject, dataMap));
	        request.setAttribute("orders", resultObject.getContent());
	        return resultObject.getContent();
		}catch(Exception e){
			e.printStackTrace();
			return CommonUtil.processExpction(headObject);
		}
	}
	
	
	 /**
     * @description <b>获取关注的商品信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-21
     * @param @param request
     * @param @return
     * @return Object
   */
   public void loadProductWishList(HttpServletRequest request){
   	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030201-01");
       //TODO 从session中获取 店铺id
       JSONObject paramJSON=new JSONObject();
       paramJSON.put("page", 1);
       paramJSON.put("rows", 20);
       try {
    	   MemberListDo memberDo=CommonUtil.getMemberListDo(request);
		   int memberId = Integer.parseInt(memberDo.getMemberId());
    	   paramJSON.put("memberId", memberId);
    	   ResultObject resultObject = memberService.doService(new RequestObject(headObject, paramJSON.toJSONString()));
           request.setAttribute("focusProducts", resultObject.getContent());
       } catch (Exception e) {
    	   headObject.setRetCode(ErrorCode.FAILURE);
           e.printStackTrace();
       }
   }
}
