package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.order.OrderRefunds;
import com.cnit.yoyo.model.order.OrderRefundsBills;

public class OrderRefundsBillsDTO extends OrderRefundsBills implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5107361683989331508L;
	private String applyName;

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}