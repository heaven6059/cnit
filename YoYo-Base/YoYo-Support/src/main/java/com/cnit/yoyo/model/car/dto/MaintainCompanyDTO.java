package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

public class MaintainCompanyDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8010656510712248537L;
	private Long companyId;
	private String companyName;
	private Long accId;
	private String companyTel;
	private String companyArea;
	private String companyAddr;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyArea() {
		return companyArea;
	}

	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

}
