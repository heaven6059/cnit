package com.cnit.yoyo.dao.order;

import java.util.List;

import com.cnit.yoyo.model.order.OrderRefundsBills;
import com.cnit.yoyo.model.order.dto.OrderRefundsBillsDTO;
import com.cnit.yoyo.model.order.dto.OrderRefundsBillsQryDTO;

public interface OrderRefundsBillsMapper {
    int deleteByPrimaryKey(Integer refundId);

    int insert(OrderRefundsBills record);

    int insertSelective(OrderRefundsBills record);

    OrderRefundsBills selectByPrimaryKey(Integer refundId);

    int updateByPrimaryKeySelective(OrderRefundsBills record);

    int updateByPrimaryKey(OrderRefundsBills record);
    
    List<OrderRefundsBillsDTO> selectRefundsBills(OrderRefundsBillsQryDTO qryDTO);
}