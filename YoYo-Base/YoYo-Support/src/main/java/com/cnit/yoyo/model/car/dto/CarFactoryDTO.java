package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.car.CarDept;
import com.cnit.yoyo.model.car.CarFactory;

/**
 * 
 * @ClassName: CarFactoryDTO
 * @Description: 汽车厂商dto
 * @author xiaox
 * @date 2015-3-28 上午11:35:03
 */
public class CarFactoryDTO extends CarFactory implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private List<CarDept> carDepts;

	private String carType; // 厂商区域

	private String brandName; // 品牌名称

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<CarDept> getCarDepts() {
		return carDepts;
	}

	public void setCarDepts(List<CarDept> carDepts) {
		this.carDepts = carDepts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}