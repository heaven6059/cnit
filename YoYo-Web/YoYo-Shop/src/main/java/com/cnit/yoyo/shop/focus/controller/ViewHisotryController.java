package com.cnit.yoyo.shop.focus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.ProductViewHistoryQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

@Controller
@RequestMapping("/viewHisotry")
public class ViewHisotryController {
	
	@Autowired
	 private RemoteService  memberService ;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		ProductViewHistoryQryDTO qryDTO=new ProductViewHistoryQryDTO();
		qryDTO.setPage(1);
		qryDTO.setRows(20);
		this.loadViewProductList(request, qryDTO);
		return "pages/focusCenter/viewHisotryList";
	}
	
	@ResponseBody
	@RequestMapping("/loadViewProductList")
	public Object loadViewProductList(HttpServletRequest request,ProductViewHistoryQryDTO qryDTO){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030301-03");
		try {
			MemberListDo memberDo=CommonUtil.getMemberListDo(request);
			if(null!=memberDo){
	   		   qryDTO.setStoreId(memberDo.getStoreId());
	   		   qryDTO.setCompanyId(memberDo.getCompanyId());
	   		   ResultObject resultObject = memberService.doService(new RequestObject(headObject, qryDTO));
	   		   request.setAttribute("ProductsView", resultObject.getContent());
	   		   return resultObject.getContent();
	   	   	}else{
	   		   return CommonUtil.notLoign(headObject);
	   	   	}
		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtil.processExpction(headObject);
		}
	}
}
