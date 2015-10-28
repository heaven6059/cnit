package com.cnit.yoyo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.constant.AfterSalesConstant;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.dto.OrderRefundsBillsQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @ClassName: OrderRefundsManagerController  
 * @Description: 卖家退款  
 * @detail <详细说明>
 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
 * @date 2015-8-24 下午5:50:44  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/orderRefundsManager")
public class OrderRefundsManagerController extends BaseController {

	@Autowired
    private RemoteService orderService;
	
	/**
	 * @Title:  refundsList  
	 * @Description:  退款列表  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-24 下午5:54:08  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @param request
	 * @param @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/refundsList")
    public Object refundsList(String data, HttpServletRequest request) {
		log.info("---------OrderRefundsManagerController.refundsList-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    ResultObject resultObject = null;
        Map<String, Object> dataMap = new HashMap<String, Object>();

        HeadObject headObject = CommonHeadUtil.geneHeadObject("returnProductManagerService.qryReturnProductList");
    	MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
    	
    	if(null != memberListDo){
    		Integer storeId = Integer.valueOf(memberListDo.getStoreId()+"");
    		if(null!=storeId){
	        	String pageIndex = request.getParameter("pageIndex");
	        	String pageSize = request.getParameter("pageSize");
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
	            resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, dataMap));
        	}
    	}else{
    		return APICommonUtil.notLoign(headObject);
    	}
        return resultObject;
    }	
	
	/**
	 * @Title:  refundsInfo  
	 * @Description:  退款详细信息  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-24 下午5:53:32  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @param request
	 * @param @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/refundsInfo")
    public Object refundsInfo(String data, HttpServletRequest request) {
		log.info("---------OrderRefundsManagerController.refundsList-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
	    ResultObject resultObject = null;
	    JSONObject obj = JSON.parseObject(data);
        try {
        	headObject = CommonHeadUtil.geneHeadObject("returnProductManagerService.findRefundsProductById");
        	Map<String, Object> params=new HashMap<String,Object>();
        	params.put("returnId", obj.getLong(data));
        	resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, params));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
	    	return processExpction(e.getMessage());
        }
        return resultObject;
    }
}
