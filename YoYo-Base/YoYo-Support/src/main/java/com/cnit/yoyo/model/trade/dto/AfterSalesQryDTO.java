package com.cnit.yoyo.model.trade.dto;

import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;

public class AfterSalesQryDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7886404872842599774L;

	private Long returnId;
	private Long orderId;
	private String memberName;
	private Long afterSalesRequire;
	private Long afterSalesType;
	private Integer isSafeguard;

	
	public Long getReturnId() {
		return returnId;
	}

	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}

	public Integer getIsSafeguard() {
		return isSafeguard;
	}

	public void setIsSafeguard(Integer isSafeguard) {
		this.isSafeguard = isSafeguard;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getAfterSalesRequire() {
		return afterSalesRequire;
	}

	public void setAfterSalesRequire(Long afterSalesRequire) {
		this.afterSalesRequire = afterSalesRequire;
	}

	public Long getAfterSalesType() {
		return afterSalesType;
	}

	public void setAfterSalesType(Long afterSalesType) {
		this.afterSalesType = afterSalesType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
