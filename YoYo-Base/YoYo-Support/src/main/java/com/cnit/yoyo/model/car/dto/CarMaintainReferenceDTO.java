package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * @Title: CarMaintainReferenceDTO.java
 * @Package com.cnit.yoyo.model.car.dto
 * @Description: 保养周期对照表
 * @Author 王鹏
 * @date 2015-5-13 下午2:27:53
 * @version V1.0
 */
public class CarMaintainReferenceDTO extends BaseQryDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6523625389248741773L;

	private Integer maintainId;
	private Integer carId;
	private String carName;
	private Integer maintainTime;// 保养时间
	private String maintainKm;
	private Integer carBrand;// 车型所属品牌
	private Integer carFactory;// 所属厂商
	private Integer carDept;// 所属车系
	private String officialMaintain;// 官方保养项
	private String optionalMaintain;// 自选保养项
	private Integer maintStatus;// 包装状态
	private String crawlerCarName;// 爬虫数据车型
	private String cycleCarName;// 本地数据车型
	private Integer crawlerCarId;// 爬虫数据对应车型Id
	private Integer crawlerId;// 爬虫数据
	private String crawlerMaintainKm;// 爬虫数据保养公里
	private Integer crawlerMaintainTime;// 爬虫数据保养时间
	private String crawlerOfficialMaintain;// 爬虫数据保养项
	private Boolean invert;
	private Integer companyId;//店铺id
	private Integer storeId; //分店id
	//卖家中心---》设置保养商品中使用
	private Integer num;  //保养周期在保养默认商品表中的数量，即：num>0，表示该保养周期已经设置了默认商品

	public Integer getCrawlerCarId() {
		return crawlerCarId;
	}

	public void setCrawlerCarId(Integer crawlerCarId) {
		this.crawlerCarId = crawlerCarId;
	}

	public Integer getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(Integer maintainId) {
		this.maintainId = maintainId;
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

	public Integer getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(Integer crawlerId) {
		this.crawlerId = crawlerId;
	}

	public String getCrawlerMaintainKm() {
		return crawlerMaintainKm;
	}

	public void setCrawlerMaintainKm(String crawlerMaintainKm) {
		this.crawlerMaintainKm = crawlerMaintainKm;
	}

	public Integer getCrawlerMaintainTime() {
		return crawlerMaintainTime;
	}

	public void setCrawlerMaintainTime(Integer crawlerMaintainTime) {
		this.crawlerMaintainTime = crawlerMaintainTime;
	}

	public String getCrawlerOfficialMaintain() {
		return crawlerOfficialMaintain;
	}

	public void setCrawlerOfficialMaintain(String crawlerOfficialMaintain) {
		this.crawlerOfficialMaintain = crawlerOfficialMaintain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCrawlerCarName() {
		return crawlerCarName;
	}

	public void setCrawlerCarName(String crawlerCarName) {
		this.crawlerCarName = crawlerCarName;
	}

	public String getCycleCarName() {
		return cycleCarName;
	}

	public void setCycleCarName(String cycleCarName) {
		this.cycleCarName = cycleCarName;
	}

	public Boolean getInvert() {
		return invert;
	}

	public void setInvert(Boolean invert) {
		this.invert = invert;
	}

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}