package com.cnit.yoyo.paycenter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRole;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.PaymentLog;
import com.cnit.yoyo.model.order.PaymentRefunds;
import com.cnit.yoyo.paycenter.util.AlipayNotify;
import com.cnit.yoyo.paycenter.util.AlipaySubmit;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.DateUtils;

/**
 * 在线支付支付异步回调
 * 
 * @author wanghb
 * @version V1.0 @createDateTime：2015-07-23
 * @Company: CNIT.
 * @Copyright: Copyright (c) 2015
 */
@Controller
public class PayController {
	private final static Log logger = LogFactory.getLog(PayController.class);

	@Autowired
	private RemoteService orderService;
	@Autowired
	private RemoteService otherService;

	/**
	 * 支付宝即时支付异步返回确认
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/aliPayNotify")
	public void aliPayNotify(HttpServletRequest request, HttpServletResponse response, PaymentLog paymentLog) throws IOException {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		PrintWriter out = response.getWriter();
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			// 商户订单号
			String out_trade_no = paymentLog.getOut_trade_no();
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			boolean verify_result = AlipayNotify.verify(params);
			if (verify_result) {
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					if (out_trade_no.startsWith("333")) {
						HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "990201-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
						JSONObject obj = new JSONObject();
						obj.put("orderId", out_trade_no);
						obj.put("type", 5);
						resultObject = otherService.doService(new RequestObject(headObject1, obj));
					} else {
						Map<String, Object> dataMap = new HashMap<String, Object>();
						dataMap.put("payStatus", "1");
						dataMap.put("isDisplay", "0");
						dataMap.put("status", "unconfirm");
						dataMap.put("orderId", out_trade_no);
						dataMap.put("paymentLog", JSONObject.fromObject(paymentLog));
						resultObject = orderService.doService(new RequestObject(headObject, dataMap));
						request.setAttribute("ordermap", resultObject.getContent());
					}
					out.println("success"); // 请不要修改或删除
				}
			} else {// 验证失败
				out.println("fail");
				logger.error("即时支付验证失败" + verify_result);
			}
		} catch (Exception e) {
			out.println("fail");
			logger.error("即时支付异常" + e);
		}
		out.flush();
		out.close();
	}

	/**
	 * 支付宝退款异步返回确认
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/aliPayPefundNotify")
	public void aliPayPefundNotify(HttpServletRequest request, HttpServletResponse response, PaymentRefunds refunds) throws IOException {
		PrintWriter out = response.getWriter();
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			// 批次号
			String batch_no = request.getParameter("batch_no");
			// 批量退款数据中转账成功的笔数
			String success_num = request.getParameter("success_num");
			logger.info("批次号:" + batch_no + "批量退款数据中转账成功的笔数:" + success_num);
			// 批量退款数据中的详细信息
			String result_details = request.getParameter("result_details");
			logger.info("批量退款数据中的详细信息:" + result_details);
			if (AlipayNotify.verify(params)) {// 验证成功
				ResultObject resultObject = null;
				HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "020102-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				resultObject = orderService.doService(new RequestObject(headObject1, refunds));
				if (resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)) {
					out.println("success");
				} else {
					out.println("fail");
				}
			} else {
				out.println("fail");
				logger.error("退款异步验证失败");
			}
		} catch (Exception e) {
			logger.error("退款异步验证失败" + e);
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * 支付宝即时支付
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/directPayByUser")
	public void directPayByUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		// 商户订单号
		String out_trade_no = request.getParameter("WIDout_trade_no");
		// 订单名称
		String subject = request.getParameter("WIDsubject");
		// 付款金额
		String total_fee = request.getParameter("WIDtotal_fee");
		// 订单描述
		String body = request.getParameter("WIDbody");
		// 商品展示地址
		String show_url = request.getParameter("WIDshow_url");

		// 防钓鱼时间戳
		String anti_phishing_key = "";
		// 若要使用请调用类文件submit中的query_timestamp函数
		// 客户端的IP地址
		String exter_invoke_ip = "";
		// 非局域网的外网IP地址，如：221.0.0.1
		// 默认支付方式
		String paymethod = "bankPay";
		// 默认网银必填，银行简码请参考接口技术文档
		String defaultbank = request.getParameter("WIDdefaultbank");
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", Configuration.getInstance().getConfigValue("ali_MerId"));
		sParaTemp.put("seller_email", Configuration.getInstance().getConfigValue("ali_email"));
		sParaTemp.put("_input_charset", Configuration.getInstance().getConfigValue("input_charset"));
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("notify_url", Configuration.getInstance().getConfigValue("ali_notifyUrl"));
		sParaTemp.put("return_url", Configuration.getInstance().getConfigValue("ali_returnUrl"));
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		if (null != defaultbank && defaultbank != "") {
			sParaTemp.put("paymethod", paymethod);
			sParaTemp.put("defaultbank", defaultbank);
		}
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		out.println(sHtmlText);
		// out.flush();
		// out.close();
	}

	@RequestMapping("/updatePayLog/{str}")
	public void updatePayLog(HttpServletRequest request, @PathVariable String str) {
		try {
			ResultObject resultObject = null;
			HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "020104-14", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = orderService.doService(new RequestObject(headObject1, str));
			if (resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)) {
				logger.info("更新支付日志成功" + str);
			} else {
				logger.error("更新支付日志失败" + str);
			}
		} catch (Exception e) {
			logger.error("更新支付日志失败" + str);
		}
	}

	/**
	 * 支付宝退款
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/refundPay")
	public void refundPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		// 退款当天日期
		// String refund_date =request.getParameter("WIDrefund_date");
		String refund_date = DateUtils.dateToString(new Date(), DateUtils.NORMALSS_DATE_PATTERN);

		// 批次号
		// String batch_no =request.getParameter("WIDbatch_no");
		String batch_no = DateUtils.dateToString(new Date(), DateUtils.NORMAL_DATETIME_PATTERN) + new Random().nextInt(10000);

		// 退款笔数
		String batch_num = "1";
		// 必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）

		// 退款详细数据
		String payId = request.getParameter("payId");
		String detail_data = "2015080300001000500059558927^0.01^协商好退款";

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
		sParaTemp.put("partner", Configuration.getInstance().getConfigValue("ali_MerId"));
		sParaTemp.put("_input_charset", Configuration.getInstance().getConfigValue("input_charset"));
		sParaTemp.put("notify_url", Configuration.getInstance().getConfigValue("ali__refund_notifyUrl"));
		sParaTemp.put("seller_email", Configuration.getInstance().getConfigValue("ali_email"));
		sParaTemp.put("refund_date", refund_date);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);

		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		out.println(sHtmlText);
		out.flush();
		out.close();
	}
}
