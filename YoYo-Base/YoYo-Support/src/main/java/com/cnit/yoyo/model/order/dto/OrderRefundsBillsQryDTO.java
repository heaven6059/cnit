package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;

public class OrderRefundsBillsQryDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5107361683989331508L;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}