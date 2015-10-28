package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.car.CarYear;
/**
 * 汽车年款dto
* @author ssd
* @date 2015-4-8 下午4:01:17
 */
public class CarYearDTO extends CarYear implements Serializable {

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	
	private Integer factoryId;
	
    private String carDeptName;  //车系名称
    
    private String factoryName;

	public String getCarDeptName() {
		return carDeptName;
	}

	public void setCarDeptName(String carDeptName) {
		this.carDeptName = carDeptName;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}
    
   
}