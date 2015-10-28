package com.cnit.yoyo.rmi.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.order.PaymentLogMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.PaymentLog;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Service("paymentLogService")
public class PaymentLogService {

	@Autowired
	private PaymentLogMapper paymentLogMapper;

	public Object selectPaymentLog(Object object) {
		ResultPage<PaymentLog> page = null;
		JSONObject resultJson = null;
		try {
			PaymentLog paymentLog = (PaymentLog) object;
			List<PaymentLog> list = new ArrayList<PaymentLog>();
			Double total = 0.00, subtotal = 0.00;
			String totalFile = "";
			if (StringUtils.isNotBlank(paymentLog.getType())) {
				if (paymentLog.getType().equals("1")) {
					totalFile = "name";
					list = paymentLogMapper.selectPaymentLogBuyer(paymentLog);
				} else if (paymentLog.getType().equals("2")) {
					totalFile = "shopName";
					list = paymentLogMapper.selectPaymentLogSell(paymentLog);
				} else if (paymentLog.getType().equals("3")) {
					totalFile = "orderId";
					list = paymentLogMapper.selectRefunds(paymentLog);
				} else if (paymentLog.getType().equals("4")) {
					totalFile = "shopName";
					list = paymentLogMapper.selectPaymentLogSellDetail(paymentLog);
				}

			} else {
				totalFile = "orderId";
				list = paymentLogMapper.selectPaymentLog(paymentLog);
			}
			if (null != list && list.size() > 0) {
				for (PaymentLog log : list) {
					total = this.add(total, log.getTotalFee());
				}
			}
			List<PaymentLog> list1 = new ArrayList<PaymentLog>();

			PageHelper.startPage(QueryParamObject.getPage(JSONObject.fromObject(object)), QueryParamObject.getRows(JSONObject.fromObject(object)));
			if (StringUtils.isNotBlank(paymentLog.getType())) {
				if (paymentLog.getType().equals("1")) {
					list1 = paymentLogMapper.selectPaymentLogBuyer(paymentLog);
				} else if (paymentLog.getType().equals("2")) {
					list1 = paymentLogMapper.selectPaymentLogSell(paymentLog);
				} else if (paymentLog.getType().equals("3")) {
					list1 = paymentLogMapper.selectRefunds(paymentLog);
				} else if (paymentLog.getType().equals("4")) {
					list1 = paymentLogMapper.selectPaymentLogSellDetail(paymentLog);
				}
			} else {
				list1 = paymentLogMapper.selectPaymentLog(paymentLog);
			}

			if (null != list1 && list1.size() > 0) {
				for (PaymentLog log : list1) {
					subtotal = this.add(subtotal, log.getTotalFee());
				}
			}
			page = new ResultPage<PaymentLog>(list1);
			JSONArray arry = new JSONArray();
			JSONObject json = new JSONObject();
			json.put("totalFee", subtotal);
			json.put(totalFile, "小计");
			arry.add(json);
			JSONObject json1 = new JSONObject();
			json1.put("totalFee", total);
			json1.put(totalFile, "合计");
			arry.add(json1);
			resultJson = JSONObject.fromObject(page);
			resultJson.accumulate("footer", arry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
		JSONObject json = JSONObject.fromObject(resultJson, jsonConfig);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
	}

	private Double add(Double value1, Double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.add(b2).doubleValue();
	}
}
