package com.cnit.yoyo.dao.order;

import com.cnit.yoyo.model.order.PaymentRefundsDetail;

public interface PaymentRefundsDetailMapper {
    int insert(PaymentRefundsDetail record);

    int insertSelective(PaymentRefundsDetail record);
}