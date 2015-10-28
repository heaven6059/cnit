package com.cnit.yoyo.dao.order;

import java.util.List;

import com.cnit.yoyo.model.order.PaymentLog;

public interface PaymentLogMapper {
	int insert(PaymentLog record);

	int insertSelective(PaymentLog record);

	List<PaymentLog> selectPaymentLog(PaymentLog paymentLog);

	List<PaymentLog> selectPaymentLogBuyer(PaymentLog paymentLog);

	List<PaymentLog> selectPaymentLogSell(PaymentLog paymentLog);
	
	List<PaymentLog> selectPaymentLogSellDetail(PaymentLog paymentLog);
	
	List<PaymentLog> selectRefunds(PaymentLog paymentLog);

	void update(PaymentLog record);
}