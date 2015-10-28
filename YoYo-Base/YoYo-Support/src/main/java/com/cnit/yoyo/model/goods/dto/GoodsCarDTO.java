package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsCarDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8327533416137152164L;

	private Long goodsId;
	private Long carId;
	private String midPic;
	private BigDecimal price;
	private BigDecimal mktPrice;
	private String carName;
	private Long carDeptId;
	private String carDeptName;
	private Long factoryId;
	private String factoryName;
	private Long brandId;
	private String brandName;
	private String configs;
	private Long carYearId;
	private String carYear;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getMidPic() {
		return midPic;
	}

	public void setMidPic(String midPic) {
		this.midPic = midPic;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMktPrice() {
		return mktPrice;
	}

	public void setMktPrice(BigDecimal mktPrice) {
		this.mktPrice = mktPrice;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public Long getCarDeptId() {
		return carDeptId;
	}

	public void setCarDeptId(Long carDeptId) {
		this.carDeptId = carDeptId;
	}

	public String getCarDeptName() {
		return carDeptName;
	}

	public void setCarDeptName(String carDeptName) {
		this.carDeptName = carDeptName;
	}

	public Long getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Long factoryId) {
		this.factoryId = factoryId;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getConfigs() {
		return configs;
	}

	public void setConfigs(String configs) {
		this.configs = configs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCarYearId() {
		return carYearId;
	}

	public void setCarYearId(Long carYearId) {
		this.carYearId = carYearId;
	}

	public String getCarYear() {
		return carYear;
	}

	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}

}
