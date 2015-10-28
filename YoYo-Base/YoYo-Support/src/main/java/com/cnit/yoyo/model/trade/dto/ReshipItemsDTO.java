package com.cnit.yoyo.model.trade.dto;

import java.io.Serializable;

public class ReshipItemsDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2630022958469778307L;
	private Long reshipItemId;
	private Integer orderItemId;// 订单详情Id
	private Integer number;// 退货数量
	private String productName;// 退货商品
	private String productBn;// 退货商品编号
	private Integer productId;// 退货商品Id

	public Long getReshipItemId() {
		return reshipItemId;
	}

	public void setReshipItemId(Long reshipItemId) {
		this.reshipItemId = reshipItemId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBn() {
		return productBn;
	}

	public void setProductBn(String productBn) {
		this.productBn = productBn;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
