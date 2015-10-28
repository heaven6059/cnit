package com.cnit.yoyo.model.member.dto;

import java.math.BigDecimal;

import com.cnit.yoyo.model.member.ProductWishList;

public class ProductWishListDTO extends ProductWishList {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6817579453661694186L;
	private String productName;
	private String productImg;
	private Long goodsId;
	private String productUrl;
	private BigDecimal productPrice;
	private String productBn;
	private String marketable;
	private Integer disabled;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductBn() {
		return productBn;
	}

	public void setProductBn(String productBn) {
		this.productBn = productBn;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getMarketable() {
		return marketable;
	}

	public void setMarketable(String marketable) {
		this.marketable = marketable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

}
