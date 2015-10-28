package com.cnit.yoyo.report.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.report.model.Report;
import com.cnit.yoyo.report.model.ReportComment;
import com.cnit.yoyo.report.model.ReportQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**   
 * @Description: 举报管理
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-27 下午1:39:53 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Controller
@RequestMapping("/report")
public class ReportController {
	
	 @Autowired
	 private RemoteService  memberService ;
	 
	 
	 /**
	  * 
	  * @Description: 获取举报管理列表页面
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/reportList")
	 public Object getReportListPage() throws Exception
	 { 
		
		 return "/pages/biz/report/reportList";
	 }
	 
	 /**
	  * 
	  * @Description: 获取举报管理列表
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/getReportList")
	 @ResponseBody
	 public Object getReportList(HttpServletRequest request,ReportQryDTO reportQryDTO) throws Exception
	 { 
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030112-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 try{
			 MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			 if(null!=memberListDo){
				 RequestObject  requestObject  =  new RequestObject(headObject, reportQryDTO);
				 reportQryDTO.setMemberId(Long.parseLong(memberListDo.getMemberId()));
				 ResultObject resultObject =(ResultObject) memberService.doService(requestObject);
				 return resultObject.getContent();
			 }else{
				 return CommonUtil.notLoign(headObject);
			 }
		 }catch(Exception e){
			 e.printStackTrace();
			 return CommonUtil.processExpction(headObject);
		 }
	 }
	 

	 
	    /**
	     * 
	     * @Description: 举报商品
	     * @param request
	     * @param orderid
	     * @return
	     */
	 @ResponseBody
	 @RequestMapping("/saveReport")
	 public Object saveReport(HttpServletRequest request,Report report){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030112-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
        	report.setFromMemberId(Integer.parseInt(memberListDo.getMemberId()));
        	report.setFromUname(memberListDo.getLoginName());
            resultObject = memberService.doService(new RequestObject(headObject, report));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
	 }	 
    
	    
	    /**
	     * 
	     * @Description: 举报商品
	     * @param request
	     * @param orderid
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/updateReportStatus")
	    public Object updateReportStatus(HttpServletRequest request,Report report){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030112-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
	            resultObject = memberService.doService(new RequestObject(headObject, report));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultObject;
	    }	
	      
	    /**
	     * 
	     * @Description: 举报留言
	     * @param request
	     * @param orderid
	     * @return
	     */
	    @RequestMapping("/saveReportComment")
	    @ResponseBody
	    public Object saveReportComment(HttpServletRequest request,ReportComment reportComment){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030112-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        String  reportId = request.getParameter("reportId");
	        request.setAttribute("reportId", reportId);
	        try {
	            resultObject = memberService.doService(new RequestObject(headObject, reportComment));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultObject;
	    }
	    
	    /**
	     * 
	     * @Description: 投诉卖家
	     * @param request
	     * @param orderid
	     * @return
	     */
	    @RequestMapping("/getReportDetailById")
	    public String getReportDetailById(HttpServletRequest request,@RequestParam(value="reportId",required=true)Long reportId){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030112-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
	        	Map<String, Object> dataMap=new HashMap<String, Object>();
	        	dataMap.put("reportId", reportId);
	            resultObject = memberService.doService(new RequestObject(headObject, dataMap));
	            request.setAttribute("reportDTO", resultObject.getContent());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "/pages/biz/report/reportDetail";
	    }
	 
}

