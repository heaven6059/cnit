package com.cnit.yoyo.reship.model;

import java.io.Serializable;

import com.cnit.yoyo.model.goods.Product;

public class ReshipItems implements Serializable {

	/***/
	private static final long serialVersionUID = 4231588695524585828L;
	private Product product;
	private Integer reshipId;
	private Integer orderItemId;
	private String goodsType;
	private String productBn;
	private String productName;
	private Float number;
	private Integer productId;
	private Long returnId;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(", orderItemId=").append(orderItemId);
		sb.append(", goodsType=").append(goodsType);
		sb.append(", returnId=").append(returnId);
		sb.append(", productBn=").append(productBn);
		sb.append(", productName=").append(productName);
		sb.append(", number=").append(number);
		sb.append(", productId=").append(productId);
		return sb.toString();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	


	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Long getReturnId() {
		return returnId;
	}

	public void setReturnId(Long returnId) {
		this.returnId = returnId;
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

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getReshipId() {
		return reshipId;
	}

	public void setReshipId(Integer reshipId) {
		this.reshipId = reshipId;
	}


}