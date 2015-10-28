package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.car.Car;
import com.cnit.yoyo.model.car.CarDept;

/**
 * 汽车车系dto
 * 
 * @author ssd
 * @date 2015-4-8 下午4:01:17
 */
public class CarDeptDTO extends CarDept implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private List<Car> cars;

	private String factoryName; // 厂商名称
	private Integer brandId;
	private String brandName;
	private String gradeName; // 级别名称

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}