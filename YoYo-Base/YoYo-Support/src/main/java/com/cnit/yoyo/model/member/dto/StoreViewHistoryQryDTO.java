package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * @Title: StoreViewHistory.java
 * @Package com.cnit.yoyo.model.member
 * @Description: 店铺浏览历史
 * @Author 王鹏
 * @date 2015-5-18 下午3:28:38
 * @version V1.0
 */
public class StoreViewHistoryQryDTO extends BaseQryDTO implements Serializable {

	private Integer id;
	private Long companyId;
	private Long memberId;
	private Long storeId;
	private Date wishlistDate;
	private Long[] companyIds;
	private String viewData;
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

	public Date getWishlistDate() {
		return wishlistDate;
	}

	public void setWishlistDate(Date wishlistDate) {
		this.wishlistDate = wishlistDate;
	}

	public Long[] getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(Long[] companyIds) {
		this.companyIds = companyIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getViewData() {
		return viewData;
	}

	public void setViewData(String viewData) {
		this.viewData = viewData;
	}

}