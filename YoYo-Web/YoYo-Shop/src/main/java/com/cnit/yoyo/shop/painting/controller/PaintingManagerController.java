/**
 * 文 件 名   :  PaintingManagerController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="zjcai@chinacnit.com">蔡志杰</a>
 * 创建时间  :  2015-5-12 下午2:05:01
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.shop.painting.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.cnit.yoyo.model.painting.dto.CarDamageQueryDTO;
import com.cnit.yoyo.model.painting.dto.PaintingOrderDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;


/**
 *@description 卖家中心-->钣金管理
 *@detail 卖家中心-->钣金管理
 *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/paintingManager")
public class PaintingManagerController {
    private static final Log logger = LogFactory.getLog(PaintingManagerController.class);
    
    @Autowired
    private RemoteService otherService;
    
    @RequestMapping("/detailList")
    public Object getOrderList(HttpServletRequest request, CarDamageQueryDTO dto)
    {
        logger.info("###########PaintingManagerController.getOrderList-->start");
        Integer companyId =  (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-15", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try{
        	//查询店铺经营范围是否有钣金喷漆
        	 resultObject = otherService.doService(new RequestObject(headObject,Long.parseLong(companyId.toString())));
        	 if((Integer)resultObject.getContent() > 0)
        	 {
	        	if(null == dto.getCarDamageId())
	        	{
	        		dto.setCompanyId(companyId);
	        	}
	            headObject = CommonHeadUtil.geneHeadObject(request, "990201-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	            resultObject = otherService.doService(new RequestObject(headObject,dto));
	            request.setAttribute("damageList", resultObject.getContent());
        	 }else{
 	            request.setAttribute("noPermission", true);
        	 }
        }
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########PaintingManagerController.getOrderList-->end");
        return "pages/paintingMng/detailList";
    }
    
    @RequestMapping("/ajaxList")
    @ResponseBody
    public Object getAjaxOrderList(HttpServletRequest request, CarDamageQueryDTO dto)
    {
    	if(null == dto.getCarDamageId())
    	{
    		Integer companyId =  (Integer) CommonUtil.getSession(request).getAttribute("companyId");
    		dto.setCompanyId(companyId);
    	}
    	
        logger.info("###########PaintingManagerController.getAjaxOrderList-->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try 
        {
            resultObject = otherService.doService(new RequestObject(headObject,dto));
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########PaintingManagerController.getAjaxOrderList-->end");
        return resultObject.getContent();
    }
    
    
    /**
     *@description 查看受损单详情
     *@detail edit+1代表报价页面，0代表查看页面
     *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
     *@version 1.0.0
     *@data 2015-5-14
     *@param request
     *@param id
     *@param edit
     *@return
     */
    @RequestMapping("/detail")
    public Object viewDetail(HttpServletRequest request,@RequestParam(value="id")Integer id,@RequestParam(value="edit",defaultValue="0")String edit)
    {
        logger.info("###########PaintingManagerController.viewDetail-->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        logger.info("id:"+id+"###########edit:"+edit);
        
		Integer companyId =  (Integer) CommonUtil.getSession(request).getAttribute("companyId");

        ResultObject resultObject = null;
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("edit", edit);
        obj.put("companyId", companyId);
        try 
        {
            resultObject = otherService.doService(new RequestObject(headObject, obj));
            request.setAttribute("detailList", resultObject.getContent());
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########PaintingManagerController.viewDetail-->end");
        return "pages/paintingMng/detail";
    }
    
    @RequestMapping("/offer")
    @ResponseBody
    public Object offerPrice(HttpServletRequest request)
    {
        logger.info("###########PaintingManagerController.offerPrice-->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-15", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("companyId", request.getParameter("companyId"));
        jsonObject.put("totalprice", request.getParameter("totalprice"));
        jsonObject.put("carDamageId", request.getParameter("carDamageId"));
        jsonObject.put("rows", request.getParameter("rows"));
        ResultObject resultObject = null;
        try {
        	resultObject = otherService.doService(new RequestObject(headObject,Long.parseLong(request.getParameter("companyId"))));
    		if((Integer)resultObject.getContent() < 0){
    			return new ResultObject(new HeadObject("NOPERMISSION", "<span style='font-size: 14px;'><b>对不起，您没有报价权限！</b></span>"));
    		}else{
    	        headObject = CommonHeadUtil.geneHeadObject(request, "990201-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
                resultObject = otherService.doService(new RequestObject(headObject,jsonObject));
    		}
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########PaintingManagerController.offerPrice-->end");
        return resultObject;
    }
    
    @RequestMapping("/orderList")
    public String toOrderlist(){
    	return "/pages/paintingMng/ordeMng";
    }

    
    /**
     * 钣金订单列表
     * @param request
     * @return
     */
    @RequestMapping("/getOrderList")
    @ResponseBody
    public Object orderList(HttpServletRequest request, PaintingOrderDTO dto)
    {
        logger.info("###########PaintingManagerController.orderList-->start");
        
    	try{
    		if(StringUtils.isNotBlank(dto.getId())){
        		Long.valueOf(dto.getId());
    		}
    	}catch (NumberFormatException e) {
			return new ResultObject(new HeadObject(ErrorCode.FAILURE, "请输入正确的订单号!"));
		}
    	
        int storeId = (int) CommonUtil.getSession(request).getAttribute("storeId");
    	dto.setStoreId(storeId);
    	
		Integer companyId =  (Integer) CommonUtil.getSession(request).getAttribute("companyId");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-15", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try 
		{
       	 	resultObject = otherService.doService(new RequestObject(headObject,Long.parseLong(companyId.toString())));
			if((Integer)resultObject.getContent() > 0){
				 headObject = CommonHeadUtil.geneHeadObject(request, "990201-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				 resultObject = otherService.doService(new RequestObject(headObject,dto));
			}else{
				return new ResultObject(new HeadObject(ErrorCode.FAILURE, "<span style='font-size: 14px;'>对不起，您的经营范围未有<b>钣金喷漆</b></span>"));
			}
		} 
		catch (Exception e) 
		{
		    logger.error(e.getMessage());
		    e.printStackTrace();
		}
        logger.info("###########PaintingManagerController.orderList-->end");
        return resultObject;
    }
    
    /**
     * 通过订单编号查看订单详情
     * @param request
     * @param ordrId
     * @return
     */
    @RequestMapping("viewOrder")
    public Object viewOrder(HttpServletRequest request, Long orderId)
    {
    	logger.info("###########PaintingManagerController.viewOrder-->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	try 
    	{
    		resultObject = otherService.doService(new RequestObject(headObject,orderId));
    		request.setAttribute("order", resultObject.getContent());
    	} 
    	catch (Exception e) 
    	{
    		logger.error(e.getMessage());
    		e.printStackTrace();
    	}
    	logger.info("###########PaintingController.viewOrder-->end");
    	return "/pages/paintingMng/orderDetail";
    }
    
    /**
     * 打印查看
     * @param request
     * @param orderId
     * @return
     */
    @RequestMapping("printOrder")
    public Object printOrder(HttpServletRequest request, Long orderId)
    {
    	logger.info("###########PaintingManagerController.viewOrder-->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        //TODO 从session中获取 店铺id
        try {
            resultObject = otherService.doService(new RequestObject(headObject, orderId));
            request.setAttribute("order", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.fromObject(resultObject);
		System.out.println(json);
    	logger.info("###########PaintingController.viewOrder-->end");
    	return "/pages/paintingMng/printOrder";
    }
    
    /**
     * 修改订单
     * @param request
     * @param orderId
     * @param type
     * @return
     */
    @RequestMapping("handleOrder")
    @ResponseBody
    public Object handleOrder(HttpServletRequest request,String orderId, Integer type)
    {
	 	logger.info("###########YOYOMem.PaintingController.handleOrder-->start");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		JSONObject obj = new JSONObject();
		obj.put("orderId", orderId);
		obj.put("type", type);
		
		if(null != request.getParameter("oldStatus")){
			obj.put("oldStatus", request.getParameter("oldStatus"));
		}
		
		try 
		{
			 resultObject = otherService.doService(new RequestObject(headObject,obj));
		} 
		catch (Exception e) 
		{
		    logger.error(e.toString());
		    e.printStackTrace();
		}
		logger.info("###########YOYOMem.PaintingController.handleOrder-->end");
		return resultObject;
    }
}
