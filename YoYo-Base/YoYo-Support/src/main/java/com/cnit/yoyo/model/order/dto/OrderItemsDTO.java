package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cnit.yoyo.dto.BaseQryDTO;

public class OrderItemsDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -907899341894815845L;
	private Integer itemId;// 详单ID
	private Long orderId;// 主单ID
	private String payStatus;// 支付状态
	private String shipStatus;// 发货状态
	private String status;// 订单状态
	private Date createtime;// 下单时间
	private String shipName;// 收货人
	private BigDecimal price;// 订单商品价格
	private String productId;// 商品ID
	private String name;// 商品名

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(String shipStatus) {
		this.shipStatus = shipStatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
