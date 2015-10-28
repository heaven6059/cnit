package com.cnit.yoyo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;

/**
 * @ClassName: ShopOrderController  
 * @Description: 卖家订单管理 
 * @detail <详细说明>
 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
 * @date 2015-8-21 上午10:57:05  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/shopOrder")
public class ShopOrderController extends BaseController {
	
	@Autowired
    private RemoteService orderService;
	
	/**
	 * @Title:  loadOrderList  
	 * @Description:  根据提交过滤加载订单列表
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-21 上午10:57:25  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/loadOrderList")
	public Object loadOrderList(String data, HttpServletRequest request) { 
		log.info("---------ShopOrderController.loadOrderList-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
	    ResultObject resultObject = null;
	    try {
	    	JSONObject obj = JSON.parseObject(data);
	    	OrderQryDTO dto = new OrderQryDTO();
	    	String status = obj.getString("status");
	    	String payStatus = obj.getString("payStatus");
	    	dto.setStatus(status);
	    	dto.setPayStatus(payStatus);
	    	if(null == obj.getInteger("page") ||  obj.getInteger("page") < 1){
	    		dto.setPage(1);
	    	}
	    	String orderTime = obj.getString("orderTime");
	    	if(StringUtils.isNotBlank(orderTime)){
	    		dto.setDateCondition(convertDateCondition(orderTime));
	    	}
	    	MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
	    	dto.setCompanyId(Integer.valueOf(memberListDo.getCompanyId().toString()));
	    	dto.setStoreId(Integer.valueOf((memberListDo.getStoreId().toString())));
	    	headObject = CommonHeadUtil.geneHeadObject("orderManagerService.qryOrderList");
	    	
			resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, dto));
	    } catch (Exception e) {
	    	log.error(e.getMessage(),e);
	    	return processExpction(e.getMessage());
	    }
		return resultObject;
	}
	
	/**
	 * @Title:  viewOrder  
	 * @Description:  查看订单  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-21 下午3:27:03  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @param request
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/viewOrder")
	@ResponseBody
	public Object viewOrder(String data, HttpServletRequest request) { 
		log.info("---------ShopOrderController.viewOrder-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
	    ResultObject resultObject = null;
	    
	    JSONObject obj = JSON.parseObject(data);
    	Long orderId = obj.getLong("orderId");
	    if(null == orderId){
	    	return processExpction("orderId不能为空");
	    }
	    try {
	    	headObject = CommonHeadUtil.geneHeadObject("orderManagerService.qryOrderByOrderId");
			resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, orderId));
		} catch (Exception e) {
	    	log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * @Title:  canelOrder  
	 * @Description:  取消订单  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-21 下午3:27:13  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @param request
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@ResponseBody
    @RequestMapping("/canelOrder")
    public Object canelOrder(String data, HttpServletRequest request) { 
		log.info("---------ShopOrderController.canelOrder-----------");
		log.info("----------------------data:"+data+"-------------------------");
		HeadObject headObject = null;
		ResultObject resultObject = null;

		JSONObject obj = JSON.parseObject(data);
    	Long orderId = obj.getLong("orderId");
	    if(null == orderId){
	    	return processExpction("orderId不能为空");
	    }
        try {
	    	MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
        	if(null != memberListDo){
        		headObject = CommonHeadUtil.geneHeadObject("orderManagerService.updateOrderStatus");
            	Map<String, Object> dataMap=new HashMap<String, Object>();
            	dataMap.put("status", "dead");
            	dataMap.put("orderId", orderId);
            	dataMap.put("memberId", memberListDo.getMemberId());
            	dataMap.put("memberName", memberListDo.getLoginName());
            	resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, dataMap));
        	}else{
        		return processExpction("sessionId丢失");
        	}
        } catch (Exception e) {
        	log.error(e.getMessage(),e);
            e.printStackTrace();
            return processExpction(headObject);
        }
        return resultObject;
    }

	@ResponseBody
	@RequestMapping("/updateStatus")
	public Object updateStatus(String data, HttpServletRequest request){
		log.info("---------ShopOrderController.canelOrder-----------");
		log.info("----------------------data:"+data+"-------------------------");
		HeadObject headObject = null;
		ResultObject resultObject = null;
		JSONObject obj = JSON.parseObject(data);
		try{
	    	MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
    		headObject = CommonHeadUtil.geneHeadObject("orderManagerService.updateOrderStatus");
        	if(null != memberListDo){
        		Map<String, Object> dataMap=new HashMap<String, Object>();
            	dataMap.put("status", obj.getString("status"));
            	dataMap.put("orderId", obj.getLong("orderId"));
            	dataMap.put("payStatus", obj.getString("payStatus"));
            	dataMap.put("memberId", memberListDo.getMemberId());
            	dataMap.put("memberName", memberListDo.getLoginName());
            	resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, dataMap));
        	}else{
        		return processExpction("sessionId丢失");
        	}
		}catch (Exception e) {
			log.error(e.getMessage(),e);
            e.printStackTrace();
			return processExpction(headObject);
		}
        return resultObject;        		
	}
	
	/**
     * @description <b>根据界面选择的时间条件 获取不同的时间区间</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-21
     * @param @param orderTime
     * @param @return
     * @return Date
   */
	private Date convertDateCondition(String orderTime){
		if(StringUtil.isEmpty(orderTime))
			return null;
		if(orderTime.equals("3th")){
			return DateUtils.addMonth(new Date(), -3);
		}
		if(orderTime.equals("6th")){
			return DateUtils.addMonth(new Date(), -6);
		}
		if(orderTime.equals("12Y")){
			return DateUtils.getCurYearBegin();
		}
		if(orderTime.equals("13Y")){
	   		return DateUtils.addMonth(new Date(), -12);
	   	}
	   	return null;
	}
}
