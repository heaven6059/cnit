package com.cnit.yoyo.consult.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.ProductViewHistoryQryDTO;
import com.cnit.yoyo.model.member.dto.StoreViewHistoryQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

@Controller
@RequestMapping("/memberViewHistory")
public class MemberViewHistoryController {
	
	@Autowired
	private RemoteService memberService;
	
	@RequestMapping("/toMemberViewHistory")
	public String toMemberViewHistory(){
		return "pages/myconsult/memberViewHisotryList";
	}
	
	
	/**
	  * @description <b>加载商品浏览历史记录信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param request
	  * @param @param dto
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/loadProductViewHistory")
	public Object loadProductViewHistory(HttpServletRequest request,ProductViewHistoryQryDTO dto)throws Exception{
		HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030301-01");
		MemberListDo memberDo=CommonUtil.getMemberListDo(request);
   	   	if(null!=memberDo){
   		   dto.setMemberId(Long.parseLong(memberDo.getMemberId()));
   		   ResultObject resultObject=memberService.doService(new RequestObject(headObject, dto));
   		   return resultObject;
   	   	}else{
   		   headObject.setRetCode(ErrorCode.FAILURE);
   		   headObject.setRetMsg("未登录");
   	   	}
		return headObject;
	}
	
	
	/**
	  * @description <b>加载店铺浏览历史记录信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param request
	  * @param @param dto
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/loadStoreViewHistory")
	public Object loadStoreViewHistory(HttpServletRequest request,StoreViewHistoryQryDTO dto)throws Exception{
		HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030302-01");
		dto.setRows(5);
		MemberListDo memberDo=CommonUtil.getMemberListDo(request);
   	   	if(null!=memberDo){
   	   		dto.setMemberId(Long.parseLong(memberDo.getMemberId()));
			ResultObject resultObject=memberService.doService(new RequestObject(headObject, dto));
			return resultObject;
   	   	}else{
    	   headObject.setRetCode(ErrorCode.FAILURE);
       	   headObject.setRetMsg("未登录");
       	}
		return headObject;
	}
	
	  
	/**
	  * @description <b>删除店铺浏览历史</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param request
	  * @param @param id
	  * @param @return
	  * @return Object
	*/
	@RequestMapping("/deleteStoreViewHistory")
	  public Object deleteStoreViewHistory(HttpServletRequest request,@RequestParam(value="id",required=true)Integer [] id){
	  	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030302-02");
	      ResultObject resultObject = null;
	      try {
	    	  resultObject = memberService.doService(new RequestObject(headObject, id));
	    	  return resultObject.getHead();
	      } catch (Exception e) {
	          e.printStackTrace();
	          headObject.setRetCode(ErrorCode.FAILURE);
	          return headObject;
	      }
	  }

	  
	  /**
	  * @description <b>删除商品浏览历史</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param request
	  * @param @param id
	  * @param @return
	  * @return Object
	*/
	@RequestMapping("/deleteProductViewHistory")
	  public Object deleteProductViewHistory(HttpServletRequest request,@RequestParam(value="id",required=true)Integer [] id){
	  	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030301-02");
	      ResultObject resultObject = null;
	      try {
	    	  MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
	    	  Map<String, Object> params=new HashMap<String, Object>();
	    	  params.put("memberId", memberListDo.getMemberId());
	    	  params.put("ids", id);
	    	  resultObject = memberService.doService(new RequestObject(headObject, params));
	    	  return resultObject.getHead();
	      } catch (Exception e) {
	          e.printStackTrace();
	          headObject.setRetCode(ErrorCode.FAILURE);
	          return headObject;
	      }
	  }
	
	  /**
	  * @description <b>删除商品浏览历史</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param request
	  * @param @param id
	  * @param @return
	  * @return Object
	*/
	@RequestMapping("/saveViewStore")
	public Object saveViewStore(HttpServletRequest request,@RequestParam(value="viewStore",required=true)String viewStore){
	  	  HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030302-03");
		  ResultObject resultObject = null;
		  try {
			  MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			  StoreViewHistoryQryDTO storeViewHistoryQryDTO=new StoreViewHistoryQryDTO();
			  storeViewHistoryQryDTO.setMemberId(Long.parseLong(memberListDo.getMemberId()));
			  storeViewHistoryQryDTO.setViewData(viewStore);
			  resultObject = memberService.doService(new RequestObject(headObject, storeViewHistoryQryDTO));
			  return resultObject.getHead();
		  } catch (Exception e) {
		      e.printStackTrace();
		      headObject.setRetCode(ErrorCode.FAILURE);
		      return headObject;
		  }
	}
}
