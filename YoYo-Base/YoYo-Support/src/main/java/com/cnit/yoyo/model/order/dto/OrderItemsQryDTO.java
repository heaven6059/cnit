package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemsQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -907899341894815845L;
	private Integer productId;
	private String name;// 商品名
	private Integer sendnum;
	private BigDecimal price;// 订单商品价格
	private BigDecimal amount;
	private Integer nums;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSendnum() {
		return sendnum;
	}
	public void setSendnum(Integer sendnum) {
		this.sendnum = sendnum;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	
}
