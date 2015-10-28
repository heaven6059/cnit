package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @ClassName: CarDataDecimal 
 * @Description: 车型数据：Decimal类型
 * @author xiaox
 * @date 2015-3-31 上午10:44:32
 */
public class CarDataDecimal  implements Serializable {
	 
	private Integer carId;

    private Integer dataId;
    
    private BigDecimal value;

    private static final long serialVersionUID = 1L;

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

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}