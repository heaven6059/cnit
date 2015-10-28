package com.cnit.yoyo.membermsg.controller;
/**   
 * @Description: 站内信
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
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/memberMsg")
public class MemberMsgController {
	
	 @Autowired
	 private RemoteService  memberService ;
	 
	 

	 /**
	  * 
	 *@Description:站内信列表页面
	 *@return
	 *@throws Exception
	  */
	 @RequestMapping("/memberMsgList")
	 public Object memberMsgList() throws Exception
	 { 
		
		 return "/pages/biz/membermsg/memberMsgList";
	 }
	 
	
	 /**
	  * 
	 *@Description:站内信列表数据
	 *@param request
	 *@return
	 *@throws Exception
	  */
	 @ResponseBody
	 @RequestMapping("/getMemberMsgList")
	 public Object getMemberMsgList(HttpServletRequest request ) throws Exception{
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030113-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 MemberListDo memberDo=CommonUtil.getMemberListDo(request);
		 try{
			 if(null!=memberDo){
				 String hasRead = request.getParameter("hasRead");
				 int pageNum = StringUtil.isEmpty(request.getParameter("pageNum")) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.valueOf(request.getParameter("pageNum"));
				 int pageSize = StringUtil.isEmpty(request.getParameter("pageSize")) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.valueOf(request.getParameter("pageSize")); 
				 Map<String,Object> paraData = new HashMap<String, Object>();
				 paraData.put("memberId", memberDo.getMemberId());
				 paraData.put("pageNum", pageNum);
				 paraData.put("pageSize", pageSize);
				 paraData.put("hasRead", hasRead);
				 RequestObject  requestObject  =  new RequestObject(headObject, paraData);
				 ResultObject resultObject =(ResultObject) memberService.doService(requestObject);
				 return resultObject.getContent();
			 }else{				 
				 return CommonUtil.notLoign(headObject);	 
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
			 return CommonUtil.processExpction(headObject);
		 }
	 }
	 
	 
	    /**
	      * @description <b>获取站内消息详情</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-6-9
	      * @param @param request
	      * @param @param msgId
	      * @param @return
	      * @return String
	    */
	   @RequestMapping("/getMemberMsgDetailById")
	   public String getMemberMsgDetailById(HttpServletRequest request,@RequestParam(value="msgId",required=true)Integer msgId){
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030113-02");
	    	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	        ResultObject resultObject = null;
	        try {
	        	if(null!=memberDo){
		        	Map<String, Object> dataMap=new HashMap<String, Object>();
		        	dataMap.put("msgIds", msgId);
		        	dataMap.put("memberId", memberDo.getMemberId());
		            resultObject = memberService.doService(new RequestObject(headObject, dataMap));
		            request.setAttribute("memberMsg", resultObject.getContent());
		            //修改当前消息为已读
		            headObject =  CommonHeadUtil.geneHeadObject(request, "030113-03");
		            memberService.doService(new RequestObject(headObject, dataMap));
		        }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "/pages/biz/membermsg/memberMsgDetail";
	   }
	   
	   /**
	  * @description <b>修改站内信阅读状态</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-9
	  * @param @param request
	  * @param @param ids
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/updateMsgReadStatus")
	public Object updateMsgReadStatus(HttpServletRequest request,@RequestParam(defaultValue="ids",required=true)Long [] ids){
			 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030113-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
			 MemberListDo memberDo=CommonUtil.getMemberListDo(request);
			 try{
				 if(null!=memberDo){
					 Map<String,Object> paraData = new HashMap<String, Object>();
					 paraData.put("memberId", memberDo.getMemberId());
					 paraData.put("msgIds", ids);
					 ResultObject resultObject =(ResultObject) memberService.doService(new RequestObject(headObject, paraData));
					 return resultObject;
				 }else{				 
					 return CommonUtil.notLoign(headObject);	 
				 }
			 }catch (Exception e) {
				 e.printStackTrace();
				 return CommonUtil.processExpction(headObject);
			 }
	   }

}

