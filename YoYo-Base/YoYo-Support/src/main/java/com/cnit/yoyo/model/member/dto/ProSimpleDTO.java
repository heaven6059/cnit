package com.cnit.yoyo.model.member.dto;

import java.math.BigDecimal;

public class ProSimpleDTO {

	private String productName;
	private String picturePath;
	private Integer goodsId;
	private BigDecimal price;
	private Double priceDouble;
	private Integer commentCount;
	private Long productId;
	
	public ProSimpleDTO() {
		super();
	}
	
	public ProSimpleDTO(String productName, String picturePath,
			Integer goodsId, BigDecimal price, Double priceDouble,
			Integer commentCount, Long productId) {
		super();
		this.productName = productName;
		this.picturePath = picturePath;
		this.goodsId = goodsId;
		this.price = price;
		this.priceDouble = priceDouble;
		this.commentCount = commentCount;
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Double getPriceDouble() {
		return priceDouble;
	}
	public void setPriceDouble(Double priceDouble) {
		this.priceDouble = priceDouble;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
