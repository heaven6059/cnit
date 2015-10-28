package com.cnit.yoyo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRole;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @ClassName: ReturnProductManagerController  
 * @Description: 卖家退换货
 * @detail <详细说明>
 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
 * @date 2015-8-24 下午2:37:48  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/returnProductManager")
public class ReturnProductManagerController extends BaseController {

	@Autowired
    private RemoteService orderService;
	
    /**
     * @Title:  reshipList  
     * @Description:  退换货列表  
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-8-24 下午4:34:53  
     * @version 1.0.0 
     * @param @param data
     * @param @param request
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/returnProductList")
    public Object reshipList(String data, HttpServletRequest request) {
		log.info("---------ReturnProductManagerController.reshipList-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
	    ResultObject resultObject = null;
	    Map<String, Object> dataMap = new HashMap<String, Object>();
	    MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
	    if(null != memberListDo){
	    	try {
		    	JSONObject obj = JSON.parseObject(data);
	        	String pageIndex = obj.getString("pageIndex");
	        	String pageSize =obj.getString("pageSize");
		    	dataMap.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
	        	dataMap.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
	        	dataMap.put("storeId",Integer.valueOf(memberListDo.getStoreId()+""));
	        	dataMap.put("isSafeguard", 2);
		    	headObject = CommonHeadUtil.geneHeadObject("returnProductManagerService.qryReturnProductList");
				resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, dataMap));
		    } catch (Exception e) {
		    	log.error(e.getMessage(),e);
		    	return processExpction(e.getMessage());
		    }
	    }
		return resultObject;
    }
    
    
    /**
     * @Title:  returnProduct  
     * @Description: 退换货品信息
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-8-24 下午3:17:59  
     * @version 1.0.0 
     * @param @param data
     * @param @param request
     * @param @return
     * @return String  返回类型 
     * @throws
     */
    @RequestMapping("/returnProduct")
    public Object returnProduct(String data, HttpServletRequest request) {
    	log.info("---------ReturnProductManagerController.returnProduct-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
        ResultObject resultObject = null;
        JSONObject obj = JSON.parseObject(data);
        Integer isSafeguard = obj.getInteger("isSafeguard");
        Long returnId = obj.getLong("returnId");
        try {
        	if(isSafeguard == 2){
		    	headObject = CommonHeadUtil.geneHeadObject("returnProductManagerService.qryReturnProductById");
        		resultObject =  (ResultObject)orderService.doServiceByServer(new RequestObject(headObject, returnId));
        	}else{
        		Map<String, Object> params=new HashMap<String,Object>();
		    	headObject = CommonHeadUtil.geneHeadObject("returnProductManagerService.findRefundsProductById");
        		params.put("returnId", returnId);
				resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, params));
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error(e.getMessage(),e);
	    	return processExpction(e.getMessage());
        }
        return resultObject;
    }
    
    /**
     * @Title:  processReship  
     * @Description:  退换货处理  
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-8-24 下午3:52:26  
     * @version 1.0.0 
     * @param @param request
     * @param @param dto
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    @ResponseBody
    @RequestMapping("/processReship")
    public Object processReship(String data, HttpServletRequest request) {
    	log.info("---------ReturnProductManagerController.processReship-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
        ResultObject resultObject = null;
        JSONObject obj = JSON.parseObject(data);
        //"sellerReason=2132421412431&status=3&returnId=205&orderId=888101508248058"
        try {
        	headObject = CommonHeadUtil.geneHeadObject("returnProductManagerService.updateReturnProductStatus");
    	    MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
     	   	if(null != memberListDo){
     	   	   ReshipDTO dto = new ReshipDTO();
     	   	   if(null !=obj.getString("sellerReason") ){
     	   		   dto.setSellerReason(obj.getString("sellerReason"));
     	   	   }
     	   	   dto.setStatus(obj.getInteger("status"));
     	   	   dto.setReturnId(obj.getLong("returnId"));
     	   	   dto.setOrderId(obj.getLong("orderId"));
     	   	   
	           dto.setOpId(Integer.valueOf(memberListDo.getMemberId()));
	           dto.setOpName(memberListDo.getLoginName());
	           dto.setRole(AfterSalesRole.STATUS_SELLER.getKey());
	           dto.setLogText(AfterSalesStatus.getAfterSalesStausText(dto.getStatus()));
	           resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, dto));
     	   	}else{
     	   		return APICommonUtil.notLoign(headObject);
     	   	}
        } catch (Exception e) {
            e.printStackTrace();
        	log.error(e.getMessage(),e);
	    	return processExpction(e.getMessage());
        }     
        return resultObject;
    }
    
    /**
     * @Title:  shopAppealAfterSales  
     * @Description:  卖家举证 
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-8-24 下午3:57:39  
     * @version 1.0.0 
     * @param @param request
     * @param @param dto
     * @param @return
     * @return String  返回类型 
     * @throws
     */
    @RequestMapping("/shopAppealAfterSales")
    public Object shopAppealAfterSales(String data, HttpServletRequest request) {
    	log.info("---------ReturnProductManagerController.processReship-----------");
		log.info("----------------------data:"+data+"-------------------------");
	    HeadObject headObject = null;
        ResultObject resultObject = null;
        JSONObject obj = JSON.parseObject(data);
    	// interevenComment=不是我的问题~~~~~~~~~~~~~~, returnId=204, orderId=888101508246926,interevenImage=/shop/201508/1440406266285.png,/shop/201508/1440406272154.png
        try {
        	headObject = CommonHeadUtil.geneHeadObject("returnProductManagerService.updateReturnProductStatus");
    	    MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
     	   	if(null!=memberListDo){
     	   	   ReshipDTO dto = new ReshipDTO();
     	   	   dto.setReturnId(obj.getLong("returnId"));
     	   	   dto.setOrderId(obj.getLong("orderId"));
     	   	   dto.setInterevenImage("interevenImage");
     	   	   dto.setInterevenComment("interevenComment");
     	   	   
     	   	   dto.setStatus(AfterSalesStatus.STATUS_5.getKey());
     	   	   dto.setOpId(Integer.parseInt(memberListDo.getMemberId()));
 	           dto.setOpName(memberListDo.getLoginName());
 	           dto.setRole(AfterSalesRole.STATUS_SELLER.getKey());
 	           dto.setStatus(AfterSalesStatus.STATUS_5.getKey());
 	           dto.setLogText(AfterSalesStatus.STATUS_5.getValue());
 	           resultObject = (ResultObject)orderService.doServiceByServer(new RequestObject(headObject, dto));
     	   	}
        } catch (Exception e) {
        	e.printStackTrace();
          	log.error(e.getMessage(),e);
  	    	return processExpction(e.getMessage());
        }
    	return resultObject;
   }
}
