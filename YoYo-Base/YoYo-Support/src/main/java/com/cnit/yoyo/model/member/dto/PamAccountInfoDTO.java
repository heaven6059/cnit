package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;

public class PamAccountInfoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5638470126183681736L;
	private Integer totalAccount;
	private Integer todayAccount;
	private Integer monthAccount;

	public Integer getTotalAccount() {
		return totalAccount;
	}

	public void setTotalAccount(Integer totalAccount) {
		this.totalAccount = totalAccount;
	}

	public Integer getTodayAccount() {
		return todayAccount;
	}

	public void setTodayAccount(Integer todayAccount) {
		this.todayAccount = todayAccount;
	}

	public Integer getMonthAccount() {
		return monthAccount;
	}

	public void setMonthAccount(Integer monthAccount) {
		this.monthAccount = monthAccount;
	}

}
