package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: StoreViewHistory.java
 * @Package com.cnit.yoyo.model.member
 * @Description: 店铺浏览历史
 * @Author 王鹏
 * @date 2015-5-18 下午3:28:38
 * @version V1.0
 */
public class StoreViewHistoryDTO implements Serializable {

	private Integer id;
	private Long companyId;
	private Long memberId;
	private Long storeId;
	private String stroeName;
	private Date viewDate;
	private String shopName;
	private String logo;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getStroeName() {
		return stroeName;
	}

	public void setStroeName(String stroeName) {
		this.stroeName = stroeName;
	}

}