package com.cnit.yoyo.shop.focus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.FocusStoreQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**   
 * @Description: 卖家关注店铺
 * @author  王鹏
 * @date 2015-8-13 上午10:18:35
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Controller
@RequestMapping("/focusStore")
public class FocusStoreController {
	
	@Autowired
	 private RemoteService  memberService ;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		FocusStoreQryDTO qryDTO=new FocusStoreQryDTO();
		qryDTO.setPage(1);
		qryDTO.setRows(200 );
		this.loadFocusStoreList(request, qryDTO);
		return "pages/focusCenter/shopStoreFocus";
	}
	
	@ResponseBody
	@RequestMapping("/loadFocusStoreList")
	public Object loadFocusStoreList(HttpServletRequest request,FocusStoreQryDTO qryDTO){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030202-04");
       try {
    	   MemberListDo memberDo=CommonUtil.getMemberListDo(request);
    	   if(null!=memberDo){
    		   qryDTO.setStoreId(memberDo.getStoreId());
    		   qryDTO.setCompanyId(memberDo.getCompanyId());
    		   ResultObject resultObject = memberService.doService(new RequestObject(headObject, qryDTO));
    		   request.setAttribute("FocusStore", resultObject.getContent());
    		   return resultObject;
    	   }else{
    		   return CommonUtil.notLoign(headObject);
    	   }
       } catch (Exception e) {
           e.printStackTrace();
           return CommonUtil.processExpction(headObject);
       }
	}
	
}
