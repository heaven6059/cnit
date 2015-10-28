package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MaintainProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4957271333764095365L;
	private Long productId;
	private Long goodsId;
	private Integer store;
	private Long companyId;
	private Long storeId;
	private Integer marketable;
	private Integer disabled;
	private String catalogName;
	private String catName;
	private String productName;
	private BigDecimal price;
	private String picturePath;
	private Long categoryId;
	private Object commentInfo;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getMarketable() {
		return marketable;
	}

	public void setMarketable(Integer marketable) {
		this.marketable = marketable;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Object getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(Object commentInfo) {
		this.commentInfo = commentInfo;
	}

}
