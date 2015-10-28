package com.cnit.yoyo.index.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**   
 * @Description: 卖家中心
 * @author  王鹏
 * @date 2015-8-11 上午9:46:40
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Controller
public class ShopCenterController {
	
	@Autowired
    private RemoteService orderService;
	
	@Autowired
	private RemoteService itemService;
	
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
	@RequestMapping("/shopCenter/index")
	public String shopCenterIndex(HttpServletRequest request){
		MemberListDo memberDo=CommonUtil.getMemberListDo(request);
		this.findStoreInfo(request, memberDo);
		this.findOrderTips(request, memberDo);
		this.findOrderSellesInfo(request, memberDo);
		this.findTopSales(request, memberDo);
		request.setAttribute("memberDo", memberDo);
		return "pages/shopcenter/shopCenterIndex";
	}
	
	/**
	 * @description <b>查询店铺信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-11
	 * @param request
	 * @param memberListDo
	 * void
	*/
	public void findStoreInfo(HttpServletRequest request,MemberListDo memberListDo){
		try{
			HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"031001-07");
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("storeId", memberListDo.getStoreId());
			ResultObject resultObject = memberService.doService(new RequestObject(headObject,param));
		    request.setAttribute("store", resultObject.getContent());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @description <b>获取提示信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-11
	 * @param request
	 * @param memberListDo
	 * void
	*/
	public void findOrderTips(HttpServletRequest request,MemberListDo memberListDo){
		try{
			HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"020103-05");
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("storeId", memberListDo.getStoreId());
			ResultObject resultObject = orderService.doService(new RequestObject(headObject,param));
		    request.setAttribute("tips", resultObject.getContent());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @description <b>获取订单销量统计信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-11
	 * @param request
	 * @param memberListDo
	 * void
	*/
	public void findOrderSellesInfo(HttpServletRequest request,MemberListDo memberListDo){
		try{
			HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"020103-06");
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("storeId", memberListDo.getStoreId());
			ResultObject resultObject = orderService.doService(new RequestObject(headObject,param));
		    request.setAttribute("orderSelles", resultObject.getContent());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * @description <b>查询店铺销量排行</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-8-11
     * @param request
     * @param companyId
     * void
    */
    private void findTopSales(HttpServletRequest request,MemberListDo memberListDo){
    	try{
			HeadObject headObject = null;
			headObject = CommonHeadUtil.geneHeadObject(request, "010201-18");
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("companyId", memberListDo.getCompanyId());
			ResultObject resultObject = itemService.doService(new RequestObject(headObject,param));
		    request.setAttribute("topSales", resultObject.getContent());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	
	
}
