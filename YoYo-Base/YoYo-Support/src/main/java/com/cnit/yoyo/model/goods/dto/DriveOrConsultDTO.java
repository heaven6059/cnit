package com.cnit.yoyo.model.goods.dto;

import com.cnit.yoyo.model.goods.DriveOrConsult;

public class DriveOrConsultDTO extends DriveOrConsult {

	private String addDate;
	private String cityName;
	private String applyType;
	
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	
}
