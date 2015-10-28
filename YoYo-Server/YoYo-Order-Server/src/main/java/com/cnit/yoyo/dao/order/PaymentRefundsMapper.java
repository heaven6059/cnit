package com.cnit.yoyo.dao.order;

import com.cnit.yoyo.model.order.PaymentRefunds;

public interface PaymentRefundsMapper {
    int insert(PaymentRefunds record);

    int insertSelective(PaymentRefunds record);
}