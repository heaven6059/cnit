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
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**  
* @Title: ProductWishListController.java
* @Package com.cnit.yoyo.wishlist.controller
* @Description: 店铺收藏 Controller
* @Author 王鹏
* @date 2015-4-30 上午10:40:50
* @version V1.0  
*/ 
@Controller
@RequestMapping("/storeWishList")
public class StoreWishListController {
	 @Autowired
	 private RemoteService  memberService ;

	 @RequestMapping("/toWishList")
	 public String toWishList(){
		 return "pages/biz/wishlist/storeWishList";
	 }
	 
   /**
     * @description <b>加载收藏店铺信息数据</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-21
     * @param @param request
     * @param @return
     * @return Object
   */
   @RequestMapping("/loadStoreWishList")
   public Object loadStoreWishList(HttpServletRequest request,OrderQryDTO dto){
   	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030202-01");
       //TODO 从session中获取 店铺id
       try {
    	   MemberListDo memberDo=CommonUtil.getMemberListDo(request);
    	   if(null!=memberDo){
    		   JSONObject paramJSON=new JSONObject();
    		   paramJSON.put("page", request.getParameter("page"));
    		   int memberId = Integer.parseInt(memberDo.getMemberId());
    		   paramJSON.put("memberId", memberId);
    		   ResultObject resultObject = memberService.doService(new RequestObject(headObject, paramJSON.toJSONString()));
    		   return resultObject;
    	   }else{
    		   headObject.setRetCode(ErrorCode.FAILURE);
    		   headObject.setRetMsg("未登录");
    	   }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return headObject;
   }
   
   /**
    * @description <b>删除收藏店铺数据</b>
    * @author 王鹏
    * @version 1.0.0
    * @data 2015-4-21
    * @param @param request
    * @param @return
    * @return Object
  */
  @RequestMapping("/deleteStoreWishList")
  public Object deleteStoreWishList(HttpServletRequest request,@RequestParam(value="companyId",required=true)Integer [] companyId,@RequestParam(value="id",required=true)Integer [] id){
  	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030202-02");
      ResultObject resultObject = null;
      try {
    	  Map<String, Object> map=new HashMap<String, Object>();
    	  map.put("id", id);
    	  map.put("companyId", companyId);
    	  resultObject = memberService.doService(new RequestObject(headObject, map));
    	  return resultObject.getHead();
      } catch (Exception e) {
          e.printStackTrace();
          headObject.setRetCode(ErrorCode.FAILURE);
          return headObject;
      }
  }
}
