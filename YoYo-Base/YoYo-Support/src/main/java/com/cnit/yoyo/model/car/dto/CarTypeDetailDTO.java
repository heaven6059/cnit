package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @Description: 车型详情列表
 * @author ssd
 */
public class CarTypeDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String carId;//车型ID
	
	private String carDeptId;//车系ID
	
    private String carName;//车型名称
    
    private String keyword;//光键值
    
    private String viewCount;//数量

    private String status; //状态
    
    private BigDecimal price; //价钱
    
    private String iconFile;//图片
    
    private String displayName;//显示名称
    
    private String displayValue;//显示值
    
    //private String configs;


	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}


	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarDeptId() {
		return carDeptId;
	}

	public void setCarDeptId(String carDeptId) {
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


	public String getIconFile() {
		return iconFile;
	}

	public void setIconFile(String iconFile) {
		this.iconFile = iconFile;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

/*	public String getConfigs() {
		return configs;
	}

	public void setConfigs(String configs) {
		this.configs = configs;
	}*/

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


    
    
   
}