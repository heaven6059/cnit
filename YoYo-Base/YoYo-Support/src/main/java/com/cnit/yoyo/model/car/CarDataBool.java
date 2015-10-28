package com.cnit.yoyo.model.car;

import java.io.Serializable;

/**
 * 
 * @ClassName: CarDataBool 
 * @Description: 车型数据：bool类型 
 * @author xiaox
 * @date 2015-3-31 上午10:43:30
 */
public class CarDataBool implements Serializable {
	
	private Integer carId;

    private Integer dataId;
    
    private String value;

    private static final long serialVersionUID = 1L;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

   
}