package com.cnit.yoyo.coupon.model.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.dto.BaseQryDTO;

public class MemberCouponQryDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1176779800508381276L;
	private Long memberId;
	private Long memcCode;
	private Long orderId;
	private List<Long> memcCodes;
	private List<Long> storeIds;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public List<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(List<Long> storeIds) {
		this.storeIds = storeIds;
	}

	public List<Long> getMemcCodes() {
		return memcCodes;
	}

	public void setMemcCodes(List<Long> memcCodes) {
		this.memcCodes = memcCodes;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getMemcCode() {
		return memcCode;
	}

	public void setMemcCode(Long memcCode) {
		this.memcCode = memcCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
