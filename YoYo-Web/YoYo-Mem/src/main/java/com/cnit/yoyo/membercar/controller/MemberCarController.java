package com.cnit.yoyo.membercar.controller;
/**   
 * @Description: 会员 我的车型
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.net.URLEncoder;
import java.util.Date;

import javax.management.relation.Relation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cnit.yoyo.membercar.model.MemberCar;
import com.cnit.yoyo.membercar.model.MemberCarDTO;
import com.cnit.yoyo.membercar.model.MemberCarQryDTO;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.servlet.CookieUtil;

@Controller
@RequestMapping("/membercar")
public class MemberCarController {
	
	 @Autowired
	 private RemoteService itemService;
	
	 @Autowired
	 private RemoteService  memberService ;
	 

	 /**
	  * 
	  * @Description: 我的车型列表页面
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/memberCarList")
	 public Object memberCarList(HttpServletRequest request,MemberCarQryDTO qryDTO) throws Exception { 
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
		try{
			MemberListDo memberDo=CommonUtil.getMemberListDo(request);
     	    if(null!=memberDo){
     	    	qryDTO.setMemberId(Long.parseLong(memberDo.getMemberId()));
     	    	resultObject = memberService.doService(new RequestObject(headObject, qryDTO));
        		request.setAttribute("memberCarDTOs", resultObject.getContent());
     	    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/biz/membercar/memberCarList";
	 }
	 
	 
	 /**
	  * 
	  * @Description: 我的车型 更改页面
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/editMemberCar")
	 public Object  editMemberCar(HttpServletRequest request,MemberCarQryDTO carQryDTO) throws Exception {
		 try {
			 String opType=request.getParameter("opType");
			 if(opType.equals("Edit")){
				HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-05");
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	    if(null!=memberDo){
	     	    	carQryDTO.setMemberId(Long.parseLong(memberDo.getMemberId()));
	     	    	ResultObject result = memberService.doService(new RequestObject(headObject, carQryDTO));
	     	    	request.setAttribute("memberCar", result.getContent());
	     	    }
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		return "/pages/biz/membercar/editMemberCar";
	 }
	 
	  /**
	     * 
	     * @Description: 更新我的车型
	     * @param request
	     * @param orderid
	     * @return
	 */
	 @ResponseBody
	 @RequestMapping("/saveMemberCar")
	 public Object saveMemberCar(HttpServletRequest request,MemberCar memberCar){
		   HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	       try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	    if(null!=memberDo){
	     	    	memberCar.setMemberId(Integer.parseInt(memberDo.getMemberId()));
	     	    	memberCar.setLastmodified(new Date());
	     	    	return memberService.doService(new RequestObject(headObject, memberCar));
	     	    }else{
	     	    	return new ResultObject(headObject.setRetCode(ErrorCode.NOT_LOGIN));
	     	    }
	       } catch (Exception e) {
	            e.printStackTrace();
	            return new ResultObject(headObject.setRetCode(ErrorCode.FAILURE));
	       }
	 }
	 
	 /**
	 * 
	 * @Description: 获取默认车型
	 * @param request
	 * @param orderid
	 * @return
	 */
	 @ResponseBody
	 @RequestMapping("/findMemberDefaultCar")
	 public Object findMemberDefaultCar(HttpServletRequest request,MemberCarQryDTO memberCar){
		   HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	       try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	    if(null!=memberDo){
	     	    	memberCar.setMemberId(Long.parseLong(memberDo.getMemberId()));
	     	    	return memberService.doService(new RequestObject(headObject, memberCar));
	     	    }else{
	     	    	return new ResultObject(headObject.setRetCode(ErrorCode.NOT_LOGIN));
	     	    }
	       } catch (Exception e) {
	            e.printStackTrace();
	            return new ResultObject(headObject.setRetCode(ErrorCode.FAILURE));
	       }
	 }
	 
	 /**
	 * 
	 * @Description: 是否添加过当前车型
	 * @param request
	 * @param orderid
	 * @return
	 */
	 @ResponseBody
	 @RequestMapping("/hasAddCurrentCar")
	 public Object hasAddCurrentCar(HttpServletRequest request,MemberCarQryDTO memberCar){
		  HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	      try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	    if(null!=memberDo){
	     	    	memberCar.setMemberId(Long.parseLong(memberDo.getMemberId()));
	     	    	return memberService.doService(new RequestObject(headObject, memberCar));
	     	    }else{
	     	    	return new ResultObject(headObject.setRetCode(ErrorCode.NOT_LOGIN));
	     	    }
	      } catch (Exception e) {
	            e.printStackTrace();
	            return new ResultObject(headObject.setRetCode(ErrorCode.FAILURE));
	      }
	 }
	 
    /**
     * 
     * @Description: 更新我的车型
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/updateMemberCar")
    public Object updateMemberCar(HttpServletRequest request,MemberCar memberCar){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        try {
        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
        	memberCar.setMemberId(Integer.parseInt(memberListDo.getMemberId()));
            memberService.doService(new RequestObject(headObject, memberCar));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/membercar/memberCarList";
    }
    
    /**
      * @description <b>设为默认车型</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-6-4
      * @param @param request
      * @param @param memberCar
      * @param @return
      * @return String
    */
    @RequestMapping("/setDefaulCar")
    public String setDefaulCar(HttpServletRequest request,HttpServletResponse response,MemberCar memberCar){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	try {
        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
        	if(null!=memberDo){
        		memberCar.setLastmodified(new Date());
        		memberCar.setMemberId(Integer.parseInt(memberDo.getMemberId()));
        		memberService.doService(new RequestObject(headObject, memberCar));
        		this.findMemberDefaultCar(request, response);
        	}else{
//        		return new ResultObject(headObject.setRetCode(ErrorCode.NOT_LOGIN));
        	}
    	} catch (Exception e) {
            e.printStackTrace();
    	}
    	return "redirect:/membercar/memberCarList";
    }
    
    
	/**
	 * @description <b>获取登录用户默认车型</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月30日
	 * @param request
	 * @param response
	 * void
	*/
	private void findMemberDefaultCar(HttpServletRequest request,HttpServletResponse response){
		   HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	       try {
	        	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
	     	    if(null!=memberDo&&memberDo.getAccountType().equals("100")){
	     	    	MemberCarQryDTO memberCar=new MemberCarQryDTO();
	     	    	memberCar.setMemberId(Long.parseLong(memberDo.getMemberId()));
	     	    	ResultObject resultObject=memberService.doService(new RequestObject(headObject, memberCar));
	     	    	if(null==resultObject.getContent()){
	     	    		CookieUtil.addCookie(response, "my_car",null, 0,null);//删除车型信息
	     	    		return;
	     	    	};
	     	    	MemberCarDTO memberCarDTO=(MemberCarDTO) resultObject.getContent();
	     	    	StringBuffer carinfo=new StringBuffer();
	     	    	carinfo.append(memberCarDTO.getCarBrandId()+"|");
	     	    	carinfo.append(memberCarDTO.getCarDeptId()+"|");
	     	    	carinfo.append(memberCarDTO.getCarId()+"|");
	     	    	carinfo.append(memberCarDTO.getCarName()+"|");;
	     	    	carinfo.append(memberCarDTO.getCarYear());				
	     	    	CookieUtil.addCookie(response, "my_car", URLEncoder.encode(carinfo.toString(),"UTF-8"), -1,null);//是否登录，会话级别
	     	    }
	       } catch (Exception e) {
	            e.printStackTrace();
	       }
	 }
    
    /**
     * @description <b>删除用户车型</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-6-4
     * @param @param request
     * @param @param memberCar
     * @param @return
     * @return String
   */
   @RequestMapping("/deleteMemberCar")
   public String deleteMemberCar(HttpServletRequest request,HttpServletResponse response,MemberCarQryDTO qryDTO){
   	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030114-04");
   	try {
       	MemberListDo memberDo=CommonUtil.getMemberListDo(request);
       	if(null!=memberDo){
       		qryDTO.setMemberId(Long.parseLong(memberDo.getMemberId()));
       		memberService.doService(new RequestObject(headObject, qryDTO));
       		this.findMemberDefaultCar(request, response);
       	}
   	} catch (Exception e) {
           e.printStackTrace();
   	}
   	return "redirect:/membercar/memberCarList";
   }
    
	 
    @ResponseBody
    @RequestMapping("/findSelect")
    public Object findSelect(HttpServletRequest request,@RequestParam(value="deptId",required=false) Integer deptId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020119-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject,deptId));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
}

