package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

public class MaintainBrandDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3608138616667319188L;
	private Long brandId;
	private String brandName;

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}