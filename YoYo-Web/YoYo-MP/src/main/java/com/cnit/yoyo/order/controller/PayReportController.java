
package com.cnit.yoyo.order.controller;

import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("/pay")
public class PayReportController {
	public static final Logger log = LoggerFactory.getLogger(PayReportController.class);

	@Autowired
	private RemoteService orderService;

	@RequestMapping("/paylog")
	public String home() {
		return "pages/biz/payReport/payList";
	}

	@RequestMapping("/getList")
	@ResponseBody
	public Object getList(HttpServletRequest request, PaymentLog paymentLog ) {
		ResultObject resultObject = null;
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = orderService.doService(new RequestObject(headObject,paymentLog));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject.getContent();
	}
}
