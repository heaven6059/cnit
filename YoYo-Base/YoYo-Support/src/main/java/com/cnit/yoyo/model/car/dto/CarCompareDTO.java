package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.car.Car;

public class CarCompareDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6247501996139754169L;
	private Car car;
	private Boolean carDataCateogryIsRoot=false;
	private Integer carDataCategoryId;
	private String carDataCategoryName;
	private Integer carDataId;
	private String carDataName;
	private String carDataValue;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCarDataCategoryId() {
		return carDataCategoryId;
	}

	public void setCarDataCategoryId(Integer carDataCategoryId) {
		this.carDataCategoryId = carDataCategoryId;
	}

	public String getCarDataCategoryName() {
		return carDataCategoryName;
	}

	public void setCarDataCategoryName(String carDataCategoryName) {
		this.carDataCategoryName = carDataCategoryName;
	}

	public Integer getCarDataId() {
		return carDataId;
	}

	public void setCarDataId(Integer carDataId) {
		this.carDataId = carDataId;
	}

	public String getCarDataName() {
		return carDataName;
	}

	public void setCarDataName(String carDataName) {
		this.carDataName = carDataName;
	}

	public String getCarDataValue() {
		return carDataValue;
	}

	public void setCarDataValue(String carDataValue) {
		this.carDataValue = carDataValue;
	}

	public Boolean getCarDataCateogryIsRoot() {
		return carDataCateogryIsRoot;
	}

	public void setCarDataCateogryIsRoot(Boolean carDataCateogryIsRoot) {
		this.carDataCateogryIsRoot = carDataCateogryIsRoot;
	}

}
