package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;
import com.cnit.yoyo.model.member.StoreWishList;

public class FocusStoreQryDTO extends BaseQryDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1996666599167192328L;
	private Long memberId;
	private Long storeId;
	private Long companyId;

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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}