package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @Description: 车型详情列表
 * @author ssd
 */
public class CarTypeConfigDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int carId;//车型ID
	
	private int carDeptId;//车系ID
	
    private String carName;//车型名称
    
    private String keyword;//光键值
    
    private int viewCount;//数量

    private String status; //状态
    
    private BigDecimal price; //价钱
    
    private String iconFile;//图片
    
    private Date lastUpdate; 
    
    private Integer filterId;
    
    private Long storePrice;
    
    private String isdel;
    
    private String configs;


	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}


	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getCarDeptId() {
		return carDeptId;
	}

	public void setCarDeptId(int carDeptId) {
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

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getConfigs() {
		return configs;
	}

	public void setConfigs(String configs) {
		this.configs = configs;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getFilterId() {
		return filterId;
	}

	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}

	public Long getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(Long storePrice) {
		this.storePrice = storePrice;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}


    
    
   
}