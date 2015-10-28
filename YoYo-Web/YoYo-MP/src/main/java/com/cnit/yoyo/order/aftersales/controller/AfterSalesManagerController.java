package com.cnit.yoyo.order.aftersales.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesReason;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRequire;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRole;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.trade.dto.AfterSalesQryDTO;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;


@Controller
@RequestMapping("/aftersales")
public class AfterSalesManagerController {
	@Autowired
    private RemoteService orderService;
    /**
      * @description <b>退款平台介入列表</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015年7月6日
      * @param @return
      * @return String
    */
    @RequestMapping("/adminIntereven")
	public String adminIntereven(HttpServletRequest request){
    	request.setAttribute("afterSalesType", AfterSalesReason.getAfterSalesReasons());
    	request.setAttribute("afterSalesRequires", AfterSalesRequire.getAfterSalesRequires());
		return "pages/biz/aftersales/afterSalesList";
	}
    
    /**
      * @description <b>加载需平台介入的售后数据</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015年7月6日
      * @param @return
      * @return Object
    */
    @ResponseBody
    @RequestMapping("/loadaftersales")
    public Object loadAfterSales(HttpServletRequest request,AfterSalesQryDTO qryDTO){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-04");
    	ResultObject resultObject = null;
    	try{
    		resultObject = orderService.doService(new RequestObject(headObject, qryDTO));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return resultObject;
    }
    
    /**
      * @description <b>查看售后服务纠纷</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015年7月6日
      * @param @return
      * @return String
    */
    @RequestMapping("/viewAfterSales")
    public String viewAfterSales(HttpServletRequest request,AfterSalesQryDTO qryDTO){
    	ResultObject resultObject = null;
    	try{
    		if(qryDTO.getIsSafeguard()==2){
        		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        		resultObject = orderService.doService(new RequestObject(headObject, qryDTO.getReturnId()));
        	}else{
        		Map<String, Object> params=new HashMap<String,Object>();
        		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        		params.put("returnId", qryDTO.getReturnId());
        		resultObject = orderService.doService(new RequestObject(headObject, params));
        	}
    		request.setAttribute("afterSales", resultObject.getContent());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return "pages/biz/aftersales/afterSalesEdit";
    }

    
    /**
      * @description <b>强制处理退款</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015年7月6日
      * @param @param request
      * @param @param dto
      * @param @return
      * @return String
    */
    @RequestMapping("/processAfterSales")
    public String processReship(HttpServletRequest request,ReshipDTO dto){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        try {
           dto.setOpId(0);
           dto.setOpName((String)request.getSession().getAttribute("loginName"));
           dto.setRole(AfterSalesRole.STATUS_ADMIN.getKey());
           dto.setLogText(AfterSalesStatus.getAfterSalesStausText(dto.getStatus()));
           orderService.doService(new RequestObject(headObject, dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/aftersales/adminIntereven";
    }
    
    /**
     * @description <b></b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年7月24日
     * @return
     * String
    */
    @RequestMapping("/refundsList")
    public String toRefundsList(){
    	return "pages/biz/aftersales/refundsList";
    }
    
    /**
     * @description <b>加载需要退款的数据</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年7月24日
     * @param request
     * @return
     * Object
    */
    @ResponseBody
    @RequestMapping("/loadRefundsList")
    public Object loadRefundsList(HttpServletRequest request,AfterSalesQryDTO qryDTO){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-06");
    	ResultObject resultObject=null;
        try {
        	resultObject = orderService.doService(new RequestObject(headObject, qryDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }
    
    /**
     * @description <b>加载需要退款的数据</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年7月24日
     * @param request
     * @return
     * Object
    */
    @RequestMapping("/viewRefunds")
    public String viewRefunds(HttpServletRequest request,AfterSalesQryDTO qryDTO){
    	ResultObject resultObject=null;
    	HeadObject headObject = null;
        try {
    		Map<String, Object> params=new HashMap<String, Object>();
    		params.put("returnId", qryDTO.getReturnId());
    		headObject = CommonHeadUtil.geneHeadObject(request, "020102-05");
    		resultObject = orderService.doService(new RequestObject(headObject, params));
    		request.setAttribute("refunds", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/biz/aftersales/refundsEdit";
    }
    
    /**
     * @description <b>处理退款</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年7月24日
     * @param request
     * @param productDTO
     * @return
     * String
    */
    @RequestMapping("/processRefunds")
    public String processRefunds(HttpServletRequest request,ReshipDTO dto){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-07");
    	try{
	    	dto.setOpId(0);
	        dto.setOpName((String)request.getSession().getAttribute("loginName"));
	        dto.setRole(AfterSalesRole.STATUS_ADMIN.getKey());
	        dto.setLogText(AfterSalesStatus.getAfterSalesStausText(dto.getStatus()));
	        orderService.doService(new RequestObject(headObject, dto));
    	}catch(Exception e){
            e.printStackTrace();
    	}
    	return "redirect:/aftersales/refundsList";
    }
}
