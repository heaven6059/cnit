package com.cnit.yoyo.shop.callcenter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.other.drive.dto.ReservationDriveQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**   
 * @Description: 询问最低价
 * @author  王鹏
 * @date 2015年7月30日 下午12:57:14
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Controller
@RequestMapping("/lowPriceConsult")
public class LowPriceConsultController {

	@Autowired
	private RemoteService itemService;
	
	@RequestMapping("/lowPriceConsultList")
	public String lowPriceConsultList(){
		return "pages/callcenter/lowPriceConsultList";
	}
	
	@ResponseBody
	@RequestMapping("/loadLowPriceConsult")
	public Object loadLowPriceConsult(HttpServletRequest request,ReservationDriveQryDTO qryDTO){
	   	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010901-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        try{
        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
        	if(null!=memberListDo){
        		qryDTO.setStoreId(memberListDo.getStoreId());
        		ResultObject resultObject = itemService.doService(new RequestObject(headObject,qryDTO));
        		return resultObject.getContent();
        	}else{
        		return CommonUtil.notLoign(headObject);
        	}
        }catch (Exception e) {
        	e.printStackTrace();
        	return CommonUtil.processExpction(headObject);
        }
	}
}
