package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cnit.yoyo.model.shop.ShopCarCoupon;

public class ShopCarCouponDTO extends ShopCarCoupon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8326159120296818244L;
	private String productName;
	private BigDecimal price;
	private Long goodsId;
	private String picturePath;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

}