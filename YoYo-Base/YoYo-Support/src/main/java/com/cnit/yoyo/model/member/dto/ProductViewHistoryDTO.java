package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: ProductViewHistory.java
 * @Package com.cnit.yoyo.model.member
 * @Description: 商品浏览历史表
 * @Author 王鹏
 * @date 2015-5-18 下午3:29:51
 * @version V1.0
 */
public class ProductViewHistoryDTO implements Serializable {

	private Integer id;
	private Long memberId;
	private Long productId;
	private Integer goodsId;
	private Long viewDate;
	private BigDecimal price;
	private String productName;
	private String productBn;
	private String picturePath;
	private Integer disabled;
	private Integer marketable;
	private String loginName;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getViewDate() {
		return viewDate;
	}

	public void setViewDate(Long viewDate) {
		this.viewDate = viewDate;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Integer getMarketable() {
		return marketable;
	}

	public void setMarketable(Integer marketable) {
		this.marketable = marketable;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	

}