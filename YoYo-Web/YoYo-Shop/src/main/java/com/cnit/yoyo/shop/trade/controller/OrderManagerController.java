package com.cnit.yoyo.shop.trade.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;

/**  
* @Title: OrderManagerController.java
* @Package com.cnit.yoyo.shop.trade.controller
* @Description: 卖家中心>>交易管理>>订单管理Controller
* @Author 王鹏
* @date 2015-4-21 上午11:36:08
* @version V1.0  
*/ 
@Controller
@RequestMapping("/shopOrder")
public class OrderManagerController {

	@Autowired
    private RemoteService orderService;
	
    /**
      * @description <b>交易管理订单列表</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-21
      * @param @param request
      * @param @return
      * @return String
    */
    @RequestMapping("/orderList")
    public String orderList(HttpServletRequest request,OrderQryDTO dto){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        dto.setDateCondition(convertDateCondition(dto.getOrderTime()));
        try {
        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
            if(null!=memberListDo){
	            dto.setStoreId(memberListDo.getStoreId().intValue());
	            dto.setCompanyId(memberListDo.getCompanyId().intValue());
	            resultObject = orderService.doService(new RequestObject(headObject, dto));
	            request.setAttribute("orders", resultObject.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/tradeMng/orderList";
    }
    
    /**
     * @description <b>交易管理订单列表</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-21
     * @param @param request
     * @param @return
     * @return String
   */
   @RequestMapping("/toOrderList")
   public String toOrderList(HttpServletRequest request,OrderQryDTO qryDTO){
	   request.setAttribute("orderQry", qryDTO);
       return "pages/tradeMng/orderList";
   }
    
    /**
      * @description <b>根绝订单列表中选择的状态条件切换加载(Ajax)数据</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-21
      * @param @param request
      * @param @return
      * @return Object
    */
    @RequestMapping("/loadOrderList")
    public Object loadOrderList(HttpServletRequest request,OrderQryDTO dto){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = new ResultObject();
        try {
        	Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        	Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        	if(null!=storeId&&null!=companyId){
	        	dto.setStoreId(storeId);
	        	dto.setCompanyId(companyId);
	        	dto.setDateCondition(convertDateCondition(dto.getOrderTime()));
	            resultObject = orderService.doService(new RequestObject(headObject, dto));
	            return resultObject;
        	}else{
        		resultObject.setHead(headObject);
        		headObject.setRetCode(ErrorCode.FAILURE);
        		headObject.setRetMsg("未登录");
        	}
        } catch (Exception e) {
            e.printStackTrace();
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
    	if(StringUtil.isEmpty(orderTime))return null;
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

    /**
      * @description <b>查看订单</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-21
      * @param @param request
      * @param @param orderid
      * @param @return
      * @return String
    */
    @RequestMapping("/viewOrder")
    public String viewOrder(HttpServletRequest request,@RequestParam(value="orderid",required=true)Long orderid){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020103-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = orderService.doService(new RequestObject(headObject, orderid));
            request.setAttribute("order", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/tradeMng/orderView";
    }

    /**
      * @description <b>取消订单</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-21
      * @param @param request
      * @param @param orderid
      * @param @return
      * @return String
    */
    @ResponseBody
    @RequestMapping("/doCanelOrder")
    public Object doCanelOrder(HttpServletRequest request,@RequestParam(value="orderid",required=true)Long orderId,@RequestParam(value="addon",required=true)String addon){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020103-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
	        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
	        	if(null!=memberListDo){
	        	Map<String, Object> dataMap=new HashMap<String, Object>();
	        	dataMap.put("status", "dead");
	        	dataMap.put("orderId", orderId);
	        	dataMap.put("memberId",memberListDo.getMemberId());
	        	dataMap.put("memberName",memberListDo.getLoginName());
	        	dataMap.put("addon", addon);
	            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
	            return resultObject;
        	}else{
        		return CommonUtil.notLoign(headObject);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.processExpction(headObject);
        }
    }
    
	 /**
     * 
     * @Description: 查看订单
     * @param @return    设定文件 
     * @author ssd
     * @date 2015-3-18 下午7:34:51
     */
    @RequestMapping("/printOrder")
    public String printOrder(HttpServletRequest request,@RequestParam(value="orderid",required=true)Long orderid){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020103-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = orderService.doService(new RequestObject(headObject, orderid));
            request.setAttribute("order", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/tradeMng/printOrder";
    }
    
    /**
      * @description <b>修改订单状态</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-6-25
      * @param @param request
      * @param @param orderid
      * @param @param status
      * @param @return
      * @return Object
    */
    @ResponseBody
    @RequestMapping("/updateStatus")
    public Object updateStatus(HttpServletRequest request,@RequestParam(value="orderid",required=true)Long orderid,@RequestParam(value="status",required=true)String status,@RequestParam(value="payStatus",required=true)Integer payStatus,@RequestParam(value="addon",required=true)String addon){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020103-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        try {
        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
        	if(null!=memberListDo){
        		Map<String, Object> dataMap=new HashMap<String, Object>();
            	dataMap.put("status", status);
            	dataMap.put("orderId", orderid);
            	dataMap.put("payStatus", payStatus);
            	dataMap.put("memberId", memberListDo.getMemberId());
            	dataMap.put("memberName", memberListDo.getLoginName());
            	dataMap.put("saleName", request.getParameter("saleName"));
            	dataMap.put("salePhone", request.getParameter("salePhone"));
            	dataMap.put("addon", addon);
            	ResultObject resultObject = orderService.doService(new RequestObject(headObject, dataMap));
                return resultObject;        		
        	}else{
        		return CommonUtil.processExpction(headObject);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.processExpction(headObject);
        }
    }
}
