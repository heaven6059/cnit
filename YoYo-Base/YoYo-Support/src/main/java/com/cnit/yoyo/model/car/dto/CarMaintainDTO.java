package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

/**
 * 
 * @Description: 保养周期 dto
 * @author xiaox
 * @date 2015-3-26 下午4:30:51
 */
public class CarMaintainDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer maintainId;
	private Integer carId;
	private String carDeptName;
	private Integer maintainTime;// 保养周期
	private String maintainKm; // 保养里程
	private Integer crawlerId;// 爬虫数据ID
	private Integer carTripRange; // 行驶里程
	private Integer purchaseTime; // 购买时长
	private Integer firstMaintainTime; // 首保时间
	private Integer firstMaintainKm; // 首保公里数
	private Integer carBrand;// 车型所属品牌
	private Integer carFactory;// 所属厂商
	private Integer carDept;// 所属车系
	private String officialMaintain;// 官方保养项
	private String optionalMaintain;// 自选保养项

	private Integer sort;

	private String remark;

	private String disabled;
	private Integer maintStatus;

	public Integer getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(Integer maintainId) {
		this.maintainId = maintainId;
	}

	public String getCarDeptName() {
		return carDeptName;
	}

	public void setCarDeptName(String carDeptName) {
		this.carDeptName = carDeptName;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getMaintainKm() {
		return maintainKm;
	}

	public void setMaintainKm(String maintainKm) {
		this.maintainKm = maintainKm;
	}

	public Integer getCarTripRange() {
		return carTripRange;
	}

	public void setCarTripRange(Integer carTripRange) {
		this.carTripRange = carTripRange;
	}

	public Integer getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Integer purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public Integer getFirstMaintainTime() {
		return firstMaintainTime;
	}

	public void setFirstMaintainTime(Integer firstMaintainTime) {
		this.firstMaintainTime = firstMaintainTime;
	}

	public Integer getFirstMaintainKm() {
		return firstMaintainKm;
	}

	public void setFirstMaintainKm(Integer firstMaintainKm) {
		this.firstMaintainKm = firstMaintainKm;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Integer getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getCarFactory() {
		return carFactory;
	}

	public void setCarFactory(Integer carFactory) {
		this.carFactory = carFactory;
	}

	public Integer getCarDept() {
		return carDept;
	}

	public void setCarDept(Integer carDept) {
		this.carDept = carDept;
	}

	public String getOfficialMaintain() {
		return officialMaintain;
	}

	public void setOfficialMaintain(String officialMaintain) {
		this.officialMaintain = officialMaintain;
	}

	public String getOptionalMaintain() {
		return optionalMaintain;
	}

	public void setOptionalMaintain(String optionalMaintain) {
		this.optionalMaintain = optionalMaintain;
	}

	public Integer getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(Integer maintainTime) {
		this.maintainTime = maintainTime;
	}

	public Integer getMaintStatus() {
		return maintStatus;
	}

	public void setMaintStatus(Integer maintStatus) {
		this.maintStatus = maintStatus;
	}

	public Integer getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(Integer crawlerId) {
		this.crawlerId = crawlerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}