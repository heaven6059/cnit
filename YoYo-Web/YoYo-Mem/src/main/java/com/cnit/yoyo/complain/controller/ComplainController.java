package com.cnit.yoyo.complain.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.complain.model.Complain;
import com.cnit.yoyo.complain.model.ComplainComments;
import com.cnit.yoyo.complain.model.dto.ComplainQryDTO;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**   
 * @Package com.cnit.yoyo.personInfo.controller 
 * @Description: 投诉管理
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-27 下午1:39:53 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Controller
@RequestMapping("/complain")
public class ComplainController {
	
	 @Autowired
	 private RemoteService  memberService ;
	 
	 /**
	  * 
	  * @Description: 获取投诉管理列表
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/getComplainListPage")
	 public Object getComplainListPage() throws Exception
	 { 
		
		 return "/pages/biz/complain/complainList";
	 }
	 
	 /**
	  * 
	  * @Description: 获取投诉管理列表
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/getComplainList")
	 @ResponseBody
	 public Object getComplainList(HttpServletRequest request,ComplainQryDTO complainQryDTO) throws Exception
	 { 
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030105-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 try{
			 MemberListDo memberDo=CommonUtil.getMemberListDo(request);
			 if(null!=memberDo){
				 complainQryDTO.setMemberId(Long.valueOf(memberDo.getMemberId()));
				 RequestObject  requestObject  =  new RequestObject(headObject, complainQryDTO);
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
	     * @Description: 投诉卖家
	     * @param request
	     * @param orderid
	     * @return
	     */
	    @RequestMapping("/saveComplain")
	    @ResponseBody
	    public Object saveComplain(HttpServletRequest request,Complain complain){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030105-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        try {
	        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
	        	if(null!=memberListDo){
	        		complain.setFromMemberId(Integer.parseInt(memberListDo.getMemberId()));
	        		complain.setFromUname(memberListDo.getLoginName());
	        		return memberService.doService(new RequestObject(headObject, complain));
	        	}else{
	        		return CommonUtil.notLoign(headObject);
	        	}
	        } catch (Exception e) {
	            e.printStackTrace();
	            return CommonUtil.processExpction(headObject);
	        }
	    }
	    
	    /**
	      * @description <b>撤销投诉</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-6-17
	      * @param @param request
	      * @param @param id
	      * @param @return
	      * @return Object
	    */
	    @ResponseBody
	    @RequestMapping("/cancelComplain")
	    public Object cancelComplain(HttpServletRequest request,@RequestParam(value="id",required=true)Integer id){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030105-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
	            resultObject = memberService.doService(new RequestObject(headObject, id));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultObject;
	    }
	    
	      
	    /**
	      * @description <b>保存投诉留言</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-6-17
	      * @param @param request
	      * @param @param complainComment
	      * @param @return
	      * @return Object
	    */
	    @RequestMapping("/saveComplainComment")
	    @ResponseBody
	    public Object saveComplainComments(HttpServletRequest request,ComplainComments complainComment){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030105-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        String  complainId = request.getParameter("complainId");
	        request.setAttribute("complainId", complainId);
	        MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
        	if(null!=memberListDo){
        		complainComment.setAuthorId((Integer.parseInt(memberListDo.getMemberId())));
        		complainComment.setAuthor((memberListDo.getLoginName()));
        	}
	        try {
	            resultObject = memberService.doService(new RequestObject(headObject, complainComment));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultObject;
	    }	    

}

