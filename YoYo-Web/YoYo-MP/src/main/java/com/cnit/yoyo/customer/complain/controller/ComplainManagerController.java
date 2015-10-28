package com.cnit.yoyo.customer.complain.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.complain.model.Complain;
import com.cnit.yoyo.complain.model.ComplainComments;
import com.cnit.yoyo.complain.model.dto.ComplainQryDTO;
import com.cnit.yoyo.constant.ComplainConstant;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;


/**  
* @Title: ComplainController.java
* @Package com.cnit.yoyo.shop.callcenter.controller
* @Description: 投诉管理
* @Author 王鹏
* @date 2015年7月7日 上午9:27:34
* @version V1.0  
*/ 
@Controller
@RequestMapping("/complain")
public class ComplainManagerController {
	
	 @Autowired
	 private RemoteService  memberService ;
	
	 /**
	  * @description <b>进入卖家投诉列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015年7月7日
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	*/
	@RequestMapping("/complainList")
	 public Object complainList(HttpServletRequest request) throws Exception{
		request.setAttribute("complainStatus",ComplainConstant.ComplainStatus.getComplainStatus());
		 return "pages/biz/reportcomplain/complainList";
	 }
	 
	
	 /**
	  * @description <b>获取投诉列表数据</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015年7月7日
	  * @param @param request
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	 */
	 @ResponseBody
	 @RequestMapping("/loadComplainData")
	 public Object complainList(HttpServletRequest request,ComplainQryDTO complainQryDTO){
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030105-01") ;
		 try{
			 return memberService.doService(new RequestObject(headObject, complainQryDTO));
		 }catch(Exception e){
			 e.printStackTrace();
			 return CommonUtil.processExpction(headObject);
		 }
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
    
    
    /**
     * 
     * @Description: 投诉详情页
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/complainDetail")
    public String complainDetail(HttpServletRequest request,@RequestParam(value="complainId",required=true)Long complainId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030105-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
            resultObject = memberService.doService(new RequestObject(headObject, complainId));
            request.setAttribute("complain", resultObject.getContent());
            request.setAttribute("memberListDo", memberListDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/biz/reportcomplain/complainDetail";
    }
    
    /**
      * @description <b>修改投诉</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015年7月8日
      * @param @param request
      * @param @param complain
      * @param @return
      * @return Object
    */
    @ResponseBody
    @RequestMapping("/updateComplain")
    public Object updateComplain(HttpServletRequest request,Complain complain){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030105-07");
        try {
            return memberService.doService(new RequestObject(headObject, complain));
        } catch (Exception e) {
            e.printStackTrace();
            return CommonUtil.processExpction(headObject);
        }
    }

}

