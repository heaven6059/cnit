package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class DriveOrConsult implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.car_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private Integer carId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.province_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private Integer provinceId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.city_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private Integer cityId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.county_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private Integer countyId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.user_name
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private String userName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.user_sex
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private String userSex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.phone
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private String phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.type
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private String type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.dealer_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private String dealerId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.is_replace
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private Integer isReplace;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.add_time
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private Long addTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_drive_or_consult.state
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private Integer state;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_drive_or_consult
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.id
	 * @return  the value of t_drive_or_consult.id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.id
	 * @param id  the value for t_drive_or_consult.id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.car_id
	 * @return  the value of t_drive_or_consult.car_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public Integer getCarId() {
		return carId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.car_id
	 * @param carId  the value for t_drive_or_consult.car_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.province_id
	 * @return  the value of t_drive_or_consult.province_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.province_id
	 * @param provinceId  the value for t_drive_or_consult.province_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.city_id
	 * @return  the value of t_drive_or_consult.city_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.city_id
	 * @param cityId  the value for t_drive_or_consult.city_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.county_id
	 * @return  the value of t_drive_or_consult.county_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public Integer getCountyId() {
		return countyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.county_id
	 * @param countyId  the value for t_drive_or_consult.county_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.user_name
	 * @return  the value of t_drive_or_consult.user_name
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.user_name
	 * @param userName  the value for t_drive_or_consult.user_name
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.user_sex
	 * @return  the value of t_drive_or_consult.user_sex
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public String getUserSex() {
		return userSex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.user_sex
	 * @param userSex  the value for t_drive_or_consult.user_sex
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.phone
	 * @return  the value of t_drive_or_consult.phone
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.phone
	 * @param phone  the value for t_drive_or_consult.phone
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.type
	 * @return  the value of t_drive_or_consult.type
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.type
	 * @param type  the value for t_drive_or_consult.type
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.dealer_id
	 * @return  the value of t_drive_or_consult.dealer_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public String getDealerId() {
		return dealerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.dealer_id
	 * @param dealerId  the value for t_drive_or_consult.dealer_id
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.is_replace
	 * @return  the value of t_drive_or_consult.is_replace
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public Integer getIsReplace() {
		return isReplace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.is_replace
	 * @param isReplace  the value for t_drive_or_consult.is_replace
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setIsReplace(Integer isReplace) {
		this.isReplace = isReplace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.add_time
	 * @return  the value of t_drive_or_consult.add_time
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public Long getAddTime() {
		return addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.add_time
	 * @param addTime  the value for t_drive_or_consult.add_time
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_drive_or_consult.state
	 * @return  the value of t_drive_or_consult.state
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_drive_or_consult.state
	 * @param state  the value for t_drive_or_consult.state
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_drive_or_consult
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", carId=").append(carId);
		sb.append(", provinceId=").append(provinceId);
		sb.append(", cityId=").append(cityId);
		sb.append(", countyId=").append(countyId);
		sb.append(", userName=").append(userName);
		sb.append(", userSex=").append(userSex);
		sb.append(", phone=").append(phone);
		sb.append(", type=").append(type);
		sb.append(", dealerId=").append(dealerId);
		sb.append(", isReplace=").append(isReplace);
		sb.append(", addTime=").append(addTime);
		sb.append(", state=").append(state);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_drive_or_consult
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		DriveOrConsult other = (DriveOrConsult) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getCarId() == null ? other.getCarId() == null : this
						.getCarId().equals(other.getCarId()))
				&& (this.getProvinceId() == null ? other.getProvinceId() == null
						: this.getProvinceId().equals(other.getProvinceId()))
				&& (this.getCityId() == null ? other.getCityId() == null : this
						.getCityId().equals(other.getCityId()))
				&& (this.getCountyId() == null ? other.getCountyId() == null
						: this.getCountyId().equals(other.getCountyId()))
				&& (this.getUserName() == null ? other.getUserName() == null
						: this.getUserName().equals(other.getUserName()))
				&& (this.getUserSex() == null ? other.getUserSex() == null
						: this.getUserSex().equals(other.getUserSex()))
				&& (this.getPhone() == null ? other.getPhone() == null : this
						.getPhone().equals(other.getPhone()))
				&& (this.getType() == null ? other.getType() == null : this
						.getType().equals(other.getType()))
				&& (this.getDealerId() == null ? other.getDealerId() == null
						: this.getDealerId().equals(other.getDealerId()))
				&& (this.getIsReplace() == null ? other.getIsReplace() == null
						: this.getIsReplace().equals(other.getIsReplace()))
				&& (this.getAddTime() == null ? other.getAddTime() == null
						: this.getAddTime().equals(other.getAddTime()))
				&& (this.getState() == null ? other.getState() == null : this
						.getState().equals(other.getState()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_drive_or_consult
	 * @mbggenerated  Tue May 12 16:37:40 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getCarId() == null) ? 0 : getCarId().hashCode());
		result = prime * result
				+ ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());
		result = prime * result
				+ ((getCityId() == null) ? 0 : getCityId().hashCode());
		result = prime * result
				+ ((getCountyId() == null) ? 0 : getCountyId().hashCode());
		result = prime * result
				+ ((getUserName() == null) ? 0 : getUserName().hashCode());
		result = prime * result
				+ ((getUserSex() == null) ? 0 : getUserSex().hashCode());
		result = prime * result
				+ ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = prime * result
				+ ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result
				+ ((getDealerId() == null) ? 0 : getDealerId().hashCode());
		result = prime * result
				+ ((getIsReplace() == null) ? 0 : getIsReplace().hashCode());
		result = prime * result
				+ ((getAddTime() == null) ? 0 : getAddTime().hashCode());
		result = prime * result
				+ ((getState() == null) ? 0 : getState().hashCode());
		return result;
	}
}