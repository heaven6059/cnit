package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CarMaintainQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8174109254001086577L;

	private Long carId;

	private Long maintainKm;

	private Integer year;

	private Integer month;

	private Integer maintainMonth;// 保养月份周期月份

	private Long[] ids;

	private Long[] accessoryCategoryIds;

	private Long companyId;

	private Long brandId;

	private Integer categoryId;

	private Long maintainId;

	private Integer accCount;

	private String area;

	private String appDate;// 预约时间

	private List<CarMaintainItemDTO> maintainItemDTOs;

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getMaintainKm() {
		return maintainKm;
	}

	public void setMaintainKm(Long maintainKm) {
		this.maintainKm = maintainKm;
	}

	public List<CarMaintainItemDTO> getMaintainItemDTOs() {
		return maintainItemDTOs;
	}

	public void setMaintainItemDTOs(List<CarMaintainItemDTO> maintainItemDTOs) {
		this.maintainItemDTOs = maintainItemDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long[] getAccessoryCategoryIds() {
		return accessoryCategoryIds;
	}

	public void setAccessoryCategoryIds(Long[] accessoryCategoryIds) {
		this.accessoryCategoryIds = accessoryCategoryIds;
	}

	public Integer getMaintainMonth() {
		return maintainMonth;
	}

	public void setMaintainMonth(Integer maintainMonth) {
		this.maintainMonth = maintainMonth;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(Long maintainId) {
		this.maintainId = maintainId;
	}

	public Integer getAccCount() {
		return accCount;
	}

	public void setAccCount(Integer accCount) {
		this.accCount = accCount;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

}
