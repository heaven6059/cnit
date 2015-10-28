package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.car.Car;
/**
 * 
 * @Description: 车型详情列表
 * @author ssd
 */
public class CarTypeDeptDTO extends Car implements Serializable {
	private static final long serialVersionUID = 1L;
	
    
    private String carYearValue;


	public String getCarYearValue() {
		return carYearValue;
	}


	public void setCarYearValue(String carYearValue) {
		this.carYearValue = carYearValue;
	}

   
}