package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: Car
 * @Description: 车型
 * @author xiaox
 * @date 2015-3-26 下午4:11:30
 */
public class Car implements Serializable {
	private static final long serialVersionUID = -1591257049167306979L;
	private Integer carId;
	private Integer carDeptId;
	private String carName;
	private String keyword;
	private String iconFile;
	private Integer viewCount;
	private Date lastUpdate;
	private String status;
	private BigDecimal price;
	private Long storePrice;
	private Integer filterId;
	private String isdel;
	private Integer isconfig;// 是否添加配置项，为了页面上做判断。ssd添加

	private List<CarConfigWithBLOBs> carConfigs;
	private CarConfigWithBLOBs carConfigWithBLOBs;

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarDeptId() {
		return carDeptId;
	}

	public void setCarDeptId(Integer carDeptId) {
		this.carDeptId = carDeptId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
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

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(Long storePrice) {
		this.storePrice = storePrice;
	}

	public Integer getFilterId() {
		return filterId;
	}

	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public List<CarConfigWithBLOBs> getCarConfigs() {
		return carConfigs;
	}

	public void setCarConfigs(List<CarConfigWithBLOBs> carConfigs) {
		this.carConfigs = carConfigs;
	}

	public Integer getIsconfig() {
		return isconfig;
	}

	public void setIsconfig(Integer isconfig) {
		this.isconfig = isconfig;
	}

	public CarConfigWithBLOBs getCarConfigWithBLOBs() {
		return carConfigWithBLOBs;
	}

	public void setCarConfigWithBLOBs(CarConfigWithBLOBs carConfigWithBLOBs) {
		this.carConfigWithBLOBs = carConfigWithBLOBs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}