package com.cnit.yoyo.focus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**  
* @Title: ProductWishListController.java
* @Package com.cnit.yoyo.wishlist.controller
* @Description: 活动收藏 Controller
* @Author 王鹏
* @date 2015-4-30 上午10:40:50
* @version V1.0  
*/ 
@Controller
@RequestMapping("/activityWishList")
public class ActivityWishListController {
	 @Autowired
	 private RemoteService  memberService ;

	 @RequestMapping("/toWishList")
	 public String toWishList(){
		 return "pages/biz/wishlist/activityWishList";
	 }
	 
   /**
     * @description <b>获取活动数据</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-21
     * @param @param request
     * @param @return
     * @return Object
   */
   @RequestMapping("/loadActivityWishList")
   public Object loadActivityWishList(HttpServletRequest request){
   	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030203-01");
       ResultObject resultObject = null;
       //TODO 从session中获取 店铺id
       JSONObject paramJSON=new JSONObject();
       paramJSON.put("page", request.getParameter("page"));
       try {
    		MemberListDo memberDo=CommonUtil.getMemberListDo(request);
    	   if(null!=memberDo){
    		   int memberId = Integer.parseInt(memberDo.getMemberId());
               paramJSON.put("memberId", memberId);
    		   resultObject = memberService.doService(new RequestObject(headObject, paramJSON.toJSONString()));
    		   return resultObject;
    	   }else{
    		   headObject.setRetCode(ErrorCode.FAILURE);
    		   headObject.setRetMsg("未登录");
    	   }
       } catch (Exception e) {
    	   headObject.setRetCode(ErrorCode.FAILURE);
           e.printStackTrace();
       }
       return headObject;
   }
   
   /**
    * @description <b>删除关注的活动信息</b>
    * @author 王鹏
    * @version 1.0.0
    * @data 2015-4-21
    * @param @param request
    * @param @return
    * @return Object
  */
  @RequestMapping("/deleteActivityWishList")
  public Object deleteActivityWishList(HttpServletRequest request,@RequestParam(value="id",required=true)Integer [] id){
  	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030203-02");
      ResultObject resultObject = null;
      try {
    	  Map<String, Object> paramMap=new HashMap<String, Object>();
    	  paramMap.put("ids", id);
    	  MemberListDo memberDo=CommonUtil.getMemberListDo(request);
    	  if(null!=memberDo){
	  	      int memberId = Integer.parseInt(memberDo.getMemberId());
	    	  paramMap.put("memberId", memberId);
	    	  resultObject = memberService.doService(new RequestObject(headObject,paramMap));
	    	  return resultObject.getHead();
    	  }else{
    		  headObject.setRetCode(ErrorCode.FAILURE);
    		  headObject.setRetMsg("未登录");
    	  }
      } catch (Exception e) {
          e.printStackTrace();
          headObject.setRetCode(ErrorCode.FAILURE);
      }
      return headObject;
  }
}
