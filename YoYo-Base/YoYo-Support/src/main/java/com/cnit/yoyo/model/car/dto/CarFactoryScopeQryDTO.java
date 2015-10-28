package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.dto.BaseQryDTO;

public class CarFactoryScopeQryDTO extends BaseQryDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5041340158760496169L;
	private String carType;

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

}