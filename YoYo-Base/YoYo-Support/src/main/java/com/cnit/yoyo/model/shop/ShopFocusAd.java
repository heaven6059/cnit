package com.cnit.yoyo.model.shop;

import java.io.Serializable;
import java.util.Date;

public class ShopFocusAd implements Serializable {
	private Long id;

	private String focusTitle;

	private String focusType;

	private Integer focusOrder;

	private String focusImg;

	private String focusUrl;

	private String focusEnabled;

	private Long companyId;

	private Long storeId;

	private Date lastModify;

	private static final long serialVersionUID = 1L;

	public ShopFocusAd() {

	}

	public ShopFocusAd(Long companyId) {
		this.companyId = companyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFocusTitle() {
		return focusTitle;
	}

	public void setFocusTitle(String focusTitle) {
		this.focusTitle = focusTitle;
	}

	public String getFocusType() {
		return focusType;
	}

	public void setFocusType(String focusType) {
		this.focusType = focusType;
	}

	public Integer getFocusOrder() {
		return focusOrder;
	}

	public void setFocusOrder(Integer focusOrder) {
		this.focusOrder = focusOrder;
	}

	public String getFocusImg() {
		return focusImg;
	}

	public void setFocusImg(String focusImg) {
		this.focusImg = focusImg;
	}

	public String getFocusEnabled() {
		return focusEnabled;
	}

	public void setFocusEnabled(String focusEnabled) {
		this.focusEnabled = focusEnabled;
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

	public Date getLastModify() {
		return lastModify;
	}

	public String getFocusUrl() {
		return focusUrl;
	}

	public void setFocusUrl(String focusUrl) {
		this.focusUrl = focusUrl;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
}