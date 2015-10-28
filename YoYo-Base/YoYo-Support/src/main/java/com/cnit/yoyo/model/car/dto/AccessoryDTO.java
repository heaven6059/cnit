package com.cnit.yoyo.model.car.dto;

import com.cnit.yoyo.model.car.Accessory;

public class AccessoryDTO extends Accessory {

	private static final long serialVersionUID = 1937988456084861550L;
	
	private String catalogName;//类型名称
	
	private String carName;//使用车型名称

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	
	
}
