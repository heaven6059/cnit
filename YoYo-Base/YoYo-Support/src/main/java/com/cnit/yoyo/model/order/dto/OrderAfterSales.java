package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderAfterSales implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5752877724911984138L;
	private Long orderId;
	private Date createTime;

	private List<OrderItemsAfterSales> afterSales;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<OrderItemsAfterSales> getAfterSales() {
		return afterSales;
	}

	public void setAfterSales(List<OrderItemsAfterSales> afterSales) {
		this.afterSales = afterSales;
	}

}
