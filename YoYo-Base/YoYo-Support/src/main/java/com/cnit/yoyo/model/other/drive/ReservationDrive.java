package com.cnit.yoyo.model.other.drive;

import java.io.Serializable;

public class ReservationDrive implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -331907532266575653L;
	private Integer id;
	private String userName;
	private Integer userSex;
	private String phone;
	private Long addTime;
	private String county;
	private String city;
	private String province;
	private String carName;
	private Integer isReplace;// 是否置换
	private String carMiles;// 行驶里程
	private Integer cardYear;// 年份
	private Integer cardMonth;// 月份
	private String replaceBrand;// 置换车型品牌
	private String replaceDept;// 置换车型车系
	private String replaceCar;// 置换车型

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIsReplace() {
		return isReplace;
	}

	public void setIsReplace(Integer isReplace) {
		this.isReplace = isReplace;
	}

	public String getCarMiles() {
		return carMiles;
	}

	public void setCarMiles(String carMiles) {
		this.carMiles = carMiles;
	}

	public Integer getCardYear() {
		return cardYear;
	}

	public void setCardYear(Integer cardYear) {
		this.cardYear = cardYear;
	}

	public Integer getCardMonth() {
		return cardMonth;
	}

	public void setCardMonth(Integer cardMonth) {
		this.cardMonth = cardMonth;
	}

	public String getReplaceBrand() {
		return replaceBrand;
	}

	public void setReplaceBrand(String replaceBrand) {
		this.replaceBrand = replaceBrand;
	}

	public String getReplaceDept() {
		return replaceDept;
	}

	public void setReplaceDept(String replaceDept) {
		this.replaceDept = replaceDept;
	}

	public String getReplaceCar() {
		return replaceCar;
	}

	public void setReplaceCar(String replaceCar) {
		this.replaceCar = replaceCar;
	}

}
