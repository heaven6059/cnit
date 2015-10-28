package com.cnit.yoyo.reship.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cnit.yoyo.constant.AfterSalesConstant;
import com.cnit.yoyo.model.goods.Product;
import com.cnit.yoyo.model.trade.AftersalesReturnProductWithBLOBs;

/**
 *
 */
public class ReshipDTO extends AftersalesReturnProductWithBLOBs implements Serializable {

	/***/
	private static final long serialVersionUID = -915255520313214920L;
	private Product product;
	private Integer reshipId;
	private Long orderItemId;
	private String goodsType;
	private String productBn;
	private String productName;
	private Integer number;
	private Long goodsId;
	private BigDecimal productPrice;
	private String logText;
	private Integer role;
	private Integer opId;
	private String opName;
	private String statusText;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getReshipId() {
		return reshipId;
	}

	public void setReshipId(Integer reshipId) {
		this.reshipId = reshipId;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getProductBn() {
		return productBn;
	}

	public void setProductBn(String productBn) {
		this.productBn = productBn;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getLogText() {
		return logText;
	}

	public void setLogText(String logText) {
		this.logText = logText;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getOpId() {
		return opId;
	}

	public void setOpId(Integer opId) {
		this.opId = opId;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStatusText(){
		if(null==super.getStatus())return "";
		return AfterSalesConstant.AfterSalesStatus.getAfterSalesStausText(super.getStatus());
	}

}