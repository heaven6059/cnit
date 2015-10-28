package com.cnit.yoyo.model.other.drive.dto;

import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;

public class ReservationDriveQryDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031079577186114134L;
	private Integer carId;
	private String userName;
	private Integer userSex;
	private String phone;
	private Integer isReplace;
	private Integer replaceBrandId;
	private Integer replaceDepId;
	private Integer replaceCarId;
	private Long storeId;
	private Long companyId;

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
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

	public Integer getIsReplace() {
		return isReplace;
	}

	public void setIsReplace(Integer isReplace) {
		this.isReplace = isReplace;
	}

	public Integer getReplaceBrandId() {
		return replaceBrandId;
	}

	public void setReplaceBrandId(Integer replaceBrandId) {
		this.replaceBrandId = replaceBrandId;
	}

	public Integer getReplaceDepId() {
		return replaceDepId;
	}

	public void setReplaceDepId(Integer replaceDepId) {
		this.replaceDepId = replaceDepId;
	}

	public Integer getReplaceCarId() {
		return replaceCarId;
	}

	public void setReplaceCarId(Integer replaceCarId) {
		this.replaceCarId = replaceCarId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
