package com.cnit.yoyo.reship.controller;
/**   
 * @Description: 退货 返修
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRole;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.constant.ErrorCode;
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

@Controller
@RequestMapping("/reship")
public class ReshipController {
	
	 @Autowired
	 private RemoteService  orderService ;
	 

	 /**
	  * 
	  * @Description: 退换货主页面
	  * @return
	  */
	@RequestMapping("/getReshipListPage")
	public Object getReshipListPage() { 
	 return "/pages/biz/reship/reshipListPage";
	}
	
	/**
	 * 
	 * @Description: 申请返修列表页
	 * @return
	 */
    @RequestMapping("/refundsList")
    public String refundsList(HttpServletRequest request){
    	request.setAttribute("load", true);
       return "/pages/biz/reship/refundsList";
    }
	 
	/**
	 * 
	 * @Description: 申请返修列表页
	 * @return
	 */
    @RequestMapping("/applyReship")
    public String applyReship(HttpServletRequest request){
    	request.setAttribute("load", true);
       return "pages/biz/order/applyReship";
    }
	 
	    /**
	     * 
	     * @Description: 退换货记录表
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    @ResponseBody
	    @RequestMapping("/getReshipList")
	    public Object getReshipList(HttpServletRequest request) throws Exception{
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030111-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
			String accountInfo = (String) CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO);//获取当前用户信息
		    JSONObject account = JSONObject.fromObject(accountInfo);
		    try{
		    	MemberListDo memberDo=(MemberListDo) JSONObject.toBean(account,MemberListDo.class);
		    	if(null!=memberDo){
				    int memberId = Integer.parseInt(memberDo.getMemberId());
					String num =  request.getParameter("pageNum");
					String size = request.getParameter("pageSize");
					int pageNum = StringUtil.isEmpty(num) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.valueOf(num);
					int pageSize = StringUtil.isEmpty(size) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.valueOf(size); 
					
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("memberId", memberId);
					dataMap.put("pageNum", pageNum);
					dataMap.put("pageSize", pageSize);
			        dataMap.put("orderId",request.getParameter("orderId"));
			        dataMap.put("name",request.getParameter("name"));
			        dataMap.put("isSafeguard", 2);
			        return resultObject = orderService.doService(new RequestObject(headObject, dataMap));
		    	}else{
		    		headObject.setRetCode(ErrorCode.FAILURE);
		    		headObject.setRetMsg("未登录");
		    	}
		    }catch (Exception e) {
		    	
			}
	        return headObject;
	    }
	    

	    /**
	     * @description <b>退款记录</b>
	     * @author 王鹏
	     * @version 1.0.0
	     * @data 2015年7月27日
	     * @param request
	     * @return
	     * @throws Exception
	     * Object
	    */
	    @ResponseBody
	    @RequestMapping("/loadRefunds")
	    public Object loadRefunds(HttpServletRequest request) throws Exception{
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030111-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		    try{
		    	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
		    	if(null!=memberDo){
				    int memberId = Integer.parseInt(memberDo.getMemberId());
					String num =  request.getParameter("pageNum");
					String size = request.getParameter("pageSize");
					int pageNum = StringUtil.isEmpty(num) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.valueOf(num);
					int pageSize = StringUtil.isEmpty(size) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.valueOf(size); 
					
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("memberId", memberId);
					dataMap.put("pageNum", pageNum);
					dataMap.put("pageSize", pageSize);
			        dataMap.put("orderId",request.getParameter("orderId"));
			        dataMap.put("name",request.getParameter("name"));
			        dataMap.put("isSafeguard", 1);
			        ResultObject resultObject = orderService.doService(new RequestObject(headObject, dataMap));
			        return resultObject;
		    	}else{
		    		return CommonUtil.notLoign(headObject);
		    	}
		    }catch (Exception e) {
		    	e.printStackTrace();
		    	return CommonUtil.processExpction(headObject);
			}
	    }
	    
	    /**
	     * 
	     * @Description: 退货详细信息
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping("/viewAfterSales")
	    public Object viewAfterSales(HttpServletRequest request,@RequestParam(value="reshipId",required=true)Long reshipId) throws Exception{ 
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030111-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
	            resultObject = orderService.doService(new RequestObject(headObject, reshipId));
	            request.setAttribute("afterSales", resultObject.getContent());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	return "/pages/biz/reship/viewReship";
	    }
	    
	    /**
	     * 
	     * @Description: 退款详细信息
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping("/viewRefunds")
	    public Object viewRefunds(HttpServletRequest request,@RequestParam(value="reshipId",required=true)Long reshipId) throws Exception{ 
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-05");
	        ResultObject resultObject = null;
	        try {
	        	Map<String, Object> paramMap=new HashMap<String,Object>();
	        	paramMap.put("returnId", reshipId);
	            resultObject = orderService.doService(new RequestObject(headObject, paramMap));
	            request.setAttribute("afterSales", resultObject.getContent());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	return "/pages/biz/reship/viewRefunds";
	    }
	 
	 
	    /**
	     * 
	     * @Description: 保存退货申请信息
	     * @param request
	     * @param orderid
	     * @return
	     */
	    @RequestMapping("/saveReship")
	    @ResponseBody
	    public Object saveReship(HttpServletRequest request,ReshipDTO reshipDTO){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030111-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	        	if(null!=memberDo){
		        	reshipDTO.setOpId(Integer.valueOf(memberDo.getMemberId()));
		        	reshipDTO.setOpName(memberDo.getLoginName());
		            resultObject = orderService.doService(new RequestObject(headObject, reshipDTO));
	        	}else{
	        		headObject.setRetCode(ErrorCode.FAILURE);
	        		headObject.setRetMsg("未登录");
	        		resultObject=new ResultObject(headObject);
	        	}
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultObject;
	    }
	    
	    
	    /**
	     * @description <b>售后申述保存</b>
	     * @author 王鹏
	     * @version 1.0.0
	     * @data 2015年8月4日
	     * @param request
	     * @param reshipDTO
	     * @return
	     * Object
	    */
	    @ResponseBody
	    @RequestMapping("/saveAppleaReship")
	    public Object saveAppleaReship(HttpServletRequest request,ReshipDTO reshipDTO){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030111-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	        	if(null!=memberDo){
		        	reshipDTO.setOpId(Integer.valueOf(memberDo.getMemberId()));
		        	reshipDTO.setOpName(memberDo.getLoginName());
		            resultObject = orderService.doService(new RequestObject(headObject, reshipDTO));
	        	}else{
	        		headObject.setRetCode(ErrorCode.FAILURE);
	        		headObject.setRetMsg("未登录");
	        		resultObject=new ResultObject(headObject);
	        	}
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultObject;
	    }
	 
	    /**
	     * 
	     * @Description: 保存退货申请信息
	     * @param request
	     * @param orderid
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/updateAfterSalesStatus")
	    public Object updateAfterSalesStatus(HttpServletRequest request,ReshipDTO reshipDTO){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030111-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	        	if(null!=memberDo){
		        	reshipDTO.setOpId(Integer.valueOf(memberDo.getMemberId()));
		        	reshipDTO.setOpName(memberDo.getLoginName());
		            resultObject = orderService.doService(new RequestObject(headObject, reshipDTO));
	        	}else{
	        		headObject.setRetCode(ErrorCode.FAILURE);
	        		headObject.setRetMsg("未登录");
	        		resultObject=new ResultObject(headObject);
	        	}
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultObject;
	    }
	    

	    /**
	      * @description <b>自动处理退货</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-6-8
	      * @param @param request
	      * @param @param dto
	      * @param @return
	      * @return String
	    */
	    @RequestMapping("/autoProcessAfterSales")
	    public String autoProcessAfterSales(HttpServletRequest request,ReshipDTO dto){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	   	if(null!=memberDo){
		           dto.setOpName(AfterSalesRole.STATUS_SYSTEM.getValue());
		           dto.setRole(AfterSalesRole.STATUS_SYSTEM.getKey());
		           dto.setStatus(AfterSalesStatus.STATUS_8.getKey());
		           dto.setLogText(AfterSalesStatus.STATUS_8.getValue());
		           orderService.doService(new RequestObject(headObject, dto));
	     	   	}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "redirect:/reship/getReshipListPage";
	    }
	    
	    /**
	      * @description <b>卖家拒绝退货，进行申诉</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-6-2
	      * @param @param request
	      * @param @return
	      * @return String
	    */
	    @RequestMapping("/appealAfterSales")
	    public String appealAfterSales(HttpServletRequest request,ReshipDTO dto){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	   	if(null!=memberDo){
	     	   	   dto.setStatus(AfterSalesStatus.STATUS_4.getKey());//设置为平台介入状态
	     	   	   dto.setOpId(Integer.parseInt(memberDo.getMemberId()));
		           dto.setOpName(memberDo.getLoginName());
		           dto.setRole(AfterSalesRole.STATUS_MEMBER.getKey());
		           dto.setStatus(AfterSalesStatus.STATUS_4.getKey());
		           dto.setLogText(AfterSalesStatus.STATUS_4.getValue());
		           orderService.doService(new RequestObject(headObject, dto));
	     	   	}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	return "redirect:/reship/getReshipListPage";
	   }
	    
	    /**
	      * @description <b>卖家拒绝退货，进行申诉</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-6-2
	      * @param @param request
	      * @param @return
	      * @return String
	    */
	    @RequestMapping("/appealRefunds")
	    public String appealRefunds(HttpServletRequest request,ReshipDTO dto){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020102-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	   	if(null!=memberDo){
	     	   	   dto.setStatus(AfterSalesStatus.STATUS_4.getKey());//设置为平台介入状态
	     	   	   dto.setOpId(Integer.parseInt(memberDo.getMemberId()));
		           dto.setOpName(memberDo.getLoginName());
		           dto.setRole(AfterSalesRole.STATUS_MEMBER.getKey());
		           dto.setStatus(AfterSalesStatus.STATUS_4.getKey());
		           dto.setLogText(AfterSalesStatus.STATUS_4.getValue());
		           orderService.doService(new RequestObject(headObject, dto));
	     	   	}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	return "redirect:/reship/refundsList";
	   }
	    
	    /**
	     * @description <b>申请退款</b>
	     * @author 王鹏
	     * @version 1.0.0
	     * @data 2015年7月27日
	     * @param request
	     * @param dto
	     * @return
	     * Object
	    */
	    @ResponseBody
	    @RequestMapping("/applyRefunds")
	    public Object applyRefunds(HttpServletRequest request,ReshipDTO dto){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030111-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	        	if(null!=memberDo){
	        		dto.setOpId(Integer.valueOf(memberDo.getMemberId()));
	        		dto.setOpName(memberDo.getLoginName());
		            resultObject = orderService.doService(new RequestObject(headObject, dto));
	        	}else{
	        		return CommonUtil.notLoign(headObject);
	        	}
	        } catch (Exception e) {
	            e.printStackTrace();
	            return CommonUtil.processExpction(headObject);
	        }
	        return resultObject;
	    }
}

