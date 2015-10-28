package com.cnit.yoyo.spider.common.dto.autohome.pojo;

import java.io.Serializable;

public class CarColor implements Serializable {
	private static final long serialVersionUID = -6363676694069536476L;
	
	
	private Long carTypeId; // 车型id
	private Long colorId; // 颜色id
	private Boolean isInner; // true为内饰颜色 false为外观颜色

	public Long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}

	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}

	public Boolean getIsInner() {
		return isInner;
	}

	public void setIsInner(Boolean isInner) {
		this.isInner = isInner;
	}

}
