package com.cnit.yoyo.settle.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.PaymentLog;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;


@Controller
@RequestMapping("/settle")
public class SettleController {

	public static final Logger log = LoggerFactory.getLogger(SettleController.class);
	
	@Autowired
	private RemoteService orderService;
	
	@RequestMapping("/list")
    public String shopIndex(HttpServletRequest request,HttpServletResponse response){
		return "pages/settle/settleList";
    }
	
	@RequestMapping("/getTotal")
	@ResponseBody
	public Object getTotal(HttpServletRequest request, PaymentLog paymentLog ) {
		ResultObject resultObject = null;
		try {
			HttpSession session=CommonUtil.getSession(request);
			String storeId= session.getAttribute("storeId").toString();
			paymentLog.setStoreId(storeId);
			paymentLog.setType("2");
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = orderService.doService(new RequestObject(headObject,paymentLog));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject.getContent();
	}
	@RequestMapping("/getList")
	@ResponseBody
	public Object getList(HttpServletRequest request, PaymentLog paymentLog ) {
		ResultObject resultObject = null;
		try {
			HttpSession session=CommonUtil.getSession(request);
			String storeId= session.getAttribute("storeId").toString();
			paymentLog.setStoreId(storeId);
			paymentLog.setType("4");
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = orderService.doService(new RequestObject(headObject,paymentLog));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject.getContent();
	}
}
