package com.cnit.yoyo.shop.trade.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cnit.yoyo.constant.AfterSalesConstant;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.dto.OrderRefundsBillsQryDTO;
import com.cnit.yoyo.model.trade.dto.ReshipDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**  
* @Title: ReshipManagerController.java
* @Package com.cnit.yoyo.shop.trade.controller
* @Description: 卖家中心>>交易管理>>退款管理Controller
* @Author 王鹏
* @date 2015-4-16 下午2:32:03
* @version V1.0  
*/ 
@Controller
@RequestMapping("/orderRefundsManager")
public class OrderRefundsManagerController {

	@Autowired
    private RemoteService orderService;
	
	 /**
     * 
     * @Description: 进入退款管理列表
     * @param @return    设定文件 
     * @author 王鹏
     * @date 2015-3-18 下午7:34:51
     */
    @RequestMapping("/toRefundsList")
    public String toOrderRefundsList(HttpServletRequest request,OrderRefundsBillsQryDTO qryDTO){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        //TODO 从session中获取 店铺id
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
        	Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        	if(null!=storeId){
	        	String pageIndex = request.getParameter("page");
	        	String pageSize = request.getParameter("rows");
	        	dataMap.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
	        	dataMap.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
	        	dataMap.put("storeId",storeId);
	        	dataMap.put("safeguardRequire",AfterSalesConstant.AfterSalesRequire.NEED_REFUND.getKey());
	        	Integer [] status={
	        			   AfterSalesConstant.AfterSalesStatus.STATUS_1.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_2.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_3.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_4.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_5.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_6.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_7.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_8.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_9.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_10.getKey(),
	        	           AfterSalesConstant.AfterSalesStatus.STATUS_11.getKey()};
	        	dataMap.put("statsuslist", status);
	            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
	            request.setAttribute("returnProducts", resultObject.getContent());
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/tradeMng/refundsList";
    }
    
    
	 /**
     * 
     * @Description: 查看退款信息
     * @param @return    设定文件 
     * @author 王鹏
     * @date 2015-3-18 下午7:34:51
     */
    @RequestMapping("/viewRefunds")
    public String toRefundsView(HttpServletRequest request,@RequestParam(value="refundId",required=true)Long returnId){
        ResultObject resultObject = null;
        try {
        	Map<String, Object> params=new HashMap<String,Object>();
        	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-05");    		
        	params.put("returnId", returnId);
        	resultObject = orderService.doService(new RequestObject(headObject, params));
        	request.setAttribute("returnProduct", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/tradeMng/refundsView";
    }
}
