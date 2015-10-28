package com.cnit.yoyo.model.shop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cnit.yoyo.model.shop.dto.ShopIndexPushDTO;

public class ShopIndexPush implements Serializable {
	private List<ShopIndexPushDTO> leftPushs;
	private List<ShopIndexPushDTO> rightPushs;
	private List<ShopIndexPushDTO> titlePushs;

	private Long id;

	private Long companyId;

	private Long storeId;

	private String title;

	private String titlePush;

	private String enabled;

	private Date lastModify;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlePush() {
		return titlePush;
	}

	public void setTitlePush(String titlePush) {
		this.titlePush = titlePush;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public List<ShopIndexPushDTO> getLeftPushs() {
		return leftPushs;
	}

	public void setLeftPushs(List<ShopIndexPushDTO> leftPushs) {
		this.leftPushs = leftPushs;
	}

	public List<ShopIndexPushDTO> getRightPushs() {
		return rightPushs;
	}

	public void setRightPushs(List<ShopIndexPushDTO> rightPushs) {
		this.rightPushs = rightPushs;
	}

	public List<ShopIndexPushDTO> getTitlePushs() {
		return titlePushs;
	}

	public void setTitlePushs(List<ShopIndexPushDTO> titlePushs) {
		this.titlePushs = titlePushs;
	}

}