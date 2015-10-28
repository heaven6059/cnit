package com.cnit.yoyo.shop.callcenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
import com.cnit.yoyo.report.model.ReportComment;
import com.cnit.yoyo.report.model.ReportQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**  
* @Title: ReportController.java
* @Package com.cnit.yoyo.shop.callcenter.controller
* @Description: 卖家举报管理
* @Author 王鹏
* @date 2015年7月7日 下午2:35:16
* @version V1.0  
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
	 public Object getReportListPage() throws Exception{ 
		 return "pages/callcenter/reportList";
	 }
	 
	
	 /**
	  * @description <b>获取举报列表数据</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015年7月7日
	  * @param @param request
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	*/
	 @ResponseBody
	 @RequestMapping("/reportListData")
	 public Object reportListData(HttpServletRequest request,ReportQryDTO reportQryDTO) throws Exception
	 { 
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030112-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 try{
			 MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			 if(null!=memberListDo){
				 RequestObject  requestObject  =  new RequestObject(headObject, reportQryDTO);
				 reportQryDTO.setStoreId(memberListDo.getStoreId());
				 reportQryDTO.setCompanyId(memberListDo.getCompanyId());
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
      * @description <b>保存举报留言</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015年7月7日
      * @param @param request
      * @param @param reportComment
      * @param @return
      * @return Object
    */
    @ResponseBody
    @RequestMapping("/saveReportComment")
    public Object saveReportComment(HttpServletRequest request,ReportComment reportComment){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030112-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        String  reportId = request.getParameter("reportId");
        request.setAttribute("reportId", reportId);
        try {
        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if(null!=memberListDo){
	        	reportComment.setAuthorId(Integer.parseInt(memberListDo.getMemberId()));
	        	reportComment.setAuthor(memberListDo.getLoginName());
	            return memberService.doService(new RequestObject(headObject, reportComment));
			}else{
				return CommonUtil.notLoign(headObject);
			}
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.processExpction(headObject);
        }
    }
	    
    /**
      * @description <b>查看举报详情</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015年7月7日
      * @param @param request
      * @param @param reportId
      * @param @return
      * @return String
    */
    @RequestMapping("/reportDetail")
    public String reportDetail(HttpServletRequest request,@RequestParam(value="reportId",required=true)Long reportId){
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
        return "pages/callcenter/reportDetail";
    }
	 
}

