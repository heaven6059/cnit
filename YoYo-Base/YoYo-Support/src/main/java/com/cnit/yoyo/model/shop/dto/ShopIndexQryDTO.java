package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;

public class ShopIndexQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 659508818355746531L;
	private Long Id;
	private Long companyId;
	private Integer num;
	private Integer identifier;
	private Integer enabled;

	public ShopIndexQryDTO() {

	}

	public ShopIndexQryDTO(Long companyId) {
		this.companyId = companyId;
	}

	public ShopIndexQryDTO(Long companyId, Integer enabled) {
		this.companyId = companyId;
		this.enabled = enabled;
	}

	public ShopIndexQryDTO(Long companyId, Integer num, Integer identifier) {
		this.companyId = companyId;
		this.num = num;
		this.identifier = identifier;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}
