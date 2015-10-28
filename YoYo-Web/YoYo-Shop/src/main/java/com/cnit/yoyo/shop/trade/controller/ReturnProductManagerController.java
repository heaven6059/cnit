package com.cnit.yoyo.shop.trade.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRole;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**  
* @Title: ReshipManagerController.java
* @Package com.cnit.yoyo.shop.trade.controller
* @Description: 卖家中心>>交易管理>>退货管理Controller
* @Author 王鹏
* @date 2015-4-16 下午2:32:03
* @version V1.0  
*/ 
@Controller
@RequestMapping("/returnProductManager")
public class ReturnProductManagerController {

	@Autowired
    private RemoteService orderService;
	
	 /**
     * 
     * @Description: 进入退货管理列表
     * @param @return    设定文件 
     * @author 王鹏
     * @date 2015-3-18 下午7:34:51
     */
    @RequestMapping("/toReturnProductList")
    public String toReshipList(HttpServletRequest request){
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
	        	dataMap.put("isSafeguard", 2);
	            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
	            request.setAttribute("returnProducts", resultObject.getContent());
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/tradeMng/returnProductList";
    }
    
    
	 /**
     * 
     * @Description: 进入处理退货信息界面
     * @param @return    设定文件 
     * @author 王鹏
     * @date 2015-3-18 下午7:34:51
     */
    @RequestMapping("/toProcessReturnProduct")
    public String toProcessReship(HttpServletRequest request,@RequestParam(value="returnId",required=true)Long returnId,@RequestParam(value="isSafeguard",required=true)Long isSafeguard){
        ResultObject resultObject = null;
        try {
        	if(isSafeguard==2){
        		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        		resultObject = orderService.doService(new RequestObject(headObject, returnId));
        	}else{
        		Map<String, Object> params=new HashMap<String,Object>();
        		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        		params.put("returnId", returnId);
        		resultObject = orderService.doService(new RequestObject(headObject, params));
        	}
            request.setAttribute("returnProduct", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/tradeMng/returnProductProcess";
    }
    
	 /**
     * 
     * @Description: 处理退货信息
     * @param @return    设定文件 
     * @author 王鹏
     * @date 2015-3-18 下午7:34:51
     */
    @ResponseBody
    @RequestMapping("/processReturnProduct")
    public Object processReship(HttpServletRequest request,ReshipDTO dto){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        try {
        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
     	   	if(null!=memberDo){
	           dto.setOpId(Integer.valueOf(memberDo.getMemberId()));
	           dto.setOpName(memberDo.getLoginName());
	           dto.setRole(AfterSalesRole.STATUS_SELLER.getKey());
	           dto.setLogText(AfterSalesStatus.getAfterSalesStausText(dto.getStatus()));
	           return orderService.doService(new RequestObject(headObject, dto));
     	   	}else{
     	   		return CommonUtil.notLoign(headObject);
     	   	}
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.processExpction(headObject);
        }        
    }
    
    /**
      * @description <b>退货信息查看</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-23
      * @param @param request
      * @param @param returnId
      * @param @return
      * @return String
    */
    @RequestMapping("/toReturnProductView")
    public String toReturnProductView(HttpServletRequest request,@RequestParam(value="returnId",required=true)Long returnId,@RequestParam(value="isSafeguard",required=true)Long isSafeguard){
        ResultObject resultObject = null;
        try {
        	if(isSafeguard==2){
        		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        		resultObject = orderService.doService(new RequestObject(headObject, returnId));
        	}else{
        		Map<String, Object> params=new HashMap<String,Object>();
        		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        		params.put("returnId", returnId);
        		resultObject = orderService.doService(new RequestObject(headObject, params));
        	}
            request.setAttribute("returnProduct", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/tradeMng/returnProductView";
    }
    
    
	 /**
     * @description <b>卖家举证</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-6-2
     * @param @param request
     * @param @return
     * @return String
   */
   @RequestMapping("/shopAppealAfterSales")
   public String shopAppealAfterSales(HttpServletRequest request,ReshipDTO dto){
   	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
       try {
       	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
    	   	if(null!=memberDo){
    	   	   dto.setStatus(AfterSalesStatus.STATUS_5.getKey());
    	   	   dto.setOpId(Integer.parseInt(memberDo.getMemberId()));
	           dto.setOpName(memberDo.getLoginName());
	           dto.setRole(AfterSalesRole.STATUS_SELLER.getKey());
	           dto.setStatus(AfterSalesStatus.STATUS_5.getKey());
	           dto.setLogText(AfterSalesStatus.STATUS_5.getValue());
	           orderService.doService(new RequestObject(headObject, dto));
    	   	}
       } catch (Exception e) {
           e.printStackTrace();
       }
   	return "redirect:/returnProductManager/toReturnProductList";
  }
}
