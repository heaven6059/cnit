package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @Description: 车型列表
 * @author ssd
 */
public class CarTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String carId;// 车型ID
	private Integer carDeptId;// 车系ID
	private String carName;// 车型名称
	private String brandname;// 品牌名称
	private String factoryName; // 厂商名称
	private String gradeName;// 级别
	private Integer gradeId;// 级别ID
	private String status; // 状态
	private String keyword;// 光键值
	private BigDecimal price; // 价钱
	private String carDeptName; // 车系名称
	private Integer brandId;// 品牌Id
	private String brandlogo; // 品牌logo
	private String carYearValue;// 年款
	private String iconFile;// 图片
	private String cargearbox; //
	private Integer isconfig; // 判断是否添加配置项，ssd add

	// 2015.04.28 xiaox
	private Integer factoryId; // 厂商id
	private Integer deptId; // 车系id
	private Integer carYearId; // 年款id

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getCarDeptName() {
		return carDeptName;
	}

	public void setCarDeptName(String carDeptName) {
		this.carDeptName = carDeptName;
	}

	public String getBrandlogo() {
		return brandlogo;
	}

	public void setBrandlogo(String brandlogo) {
		this.brandlogo = brandlogo;
	}

	public String getCarYearValue() {
		return carYearValue;
	}

	public void setCarYearValue(String carYearValue) {
		this.carYearValue = carYearValue;
	}


	public String getCargearbox() {
		return cargearbox;
	}

	public void setCargearbox(String cargearbox) {
		this.cargearbox = cargearbox;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Integer getCarDeptId() {
		return carDeptId;
	}

	public void setCarDeptId(Integer carDeptId) {
		this.carDeptId = carDeptId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getIconFile() {
		return iconFile;
	}

	public void setIconFile(String iconFile) {
		this.iconFile = iconFile;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getCarYearId() {
		return carYearId;
	}

	public void setCarYearId(Integer carYearId) {
		this.carYearId = carYearId;
	}

	public Integer getIsconfig() {
		return isconfig;
	}

	public void setIsconfig(Integer isconfig) {
		this.isconfig = isconfig;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}