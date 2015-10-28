package com.cnit.yoyo.spider.common.dto.autohome.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CarBaoyangInfo implements Serializable {
	private static final long serialVersionUID = 4309505573644543376L;
	
	private String id;//表主建,车型id+车系id+变速箱名称
	private String carTypeId;// 车型id
	private String carSeriesId;//车系id
	private String gearBox;//变速箱名称
	private String gearBoxId;//变速箱id
	private String firstUpKeep;//首保（xx公里）
	private String firstUpKeepMon;//首保（xx个月）
	private String secondUpKeep;//二保（xx公里/xx个月）
	private String secondUpKeepMon;//二保（xx个月）
	private String interval;//间隔（xx公里/xx个月）
	private String intervalMon;//间隔（xx个月）
	private String upKeepStatus;//保养状态 0：更换 1：检查
	private String remark;//备注
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	/**
	 * 保养详情List
	 */
	private List<CarBaoyangAttr> carBaoyangAttrs;
	
	public CarBaoyangInfo() {
		super();  
	}
	public CarBaoyangInfo(String id, String carTypeId, String carSeriesId,
			String gearBox, String gearBoxId, String firstUpKeep,
			String secondUpKeep, String interval, String upKeepStatus,
			String remark, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.carTypeId = carTypeId;
		this.carSeriesId = carSeriesId;
		this.gearBox = gearBox;
		this.gearBoxId = gearBoxId;
		this.firstUpKeep = firstUpKeep;
		this.secondUpKeep = secondUpKeep;
		this.interval = interval;
		this.upKeepStatus = upKeepStatus;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}
	public String getCarSeriesId() {
		return carSeriesId;
	}
	public void setCarSeriesId(String carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	public String getGearBox() {
		return gearBox;
	}
	public void setGearBox(String gearBox) {
		this.gearBox = gearBox;
	}
	public String getGearBoxId() {
		return gearBoxId;
	}
	public void setGearBoxId(String gearBoxId) {
		this.gearBoxId = gearBoxId;
	}
	public String getFirstUpKeep() {
		return firstUpKeep;
	}
	public void setFirstUpKeep(String firstUpKeep) {
		this.firstUpKeep = firstUpKeep;
	}
	public String getSecondUpKeep() {
		return secondUpKeep;
	}
	public void setSecondUpKeep(String secondUpKeep) {
		this.secondUpKeep = secondUpKeep;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getUpKeepStatus() {
		return upKeepStatus;
	}
	public void setUpKeepStatus(String upKeepStatus) {
		this.upKeepStatus = upKeepStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<CarBaoyangAttr> getCarBaoyangAttrs() {
		return carBaoyangAttrs;
	}
	public void setCarBaoyangAttrs(List<CarBaoyangAttr> carBaoyangAttrs) {
		this.carBaoyangAttrs = carBaoyangAttrs;
	}
	public String getFirstUpKeepMon() {
		return firstUpKeepMon;
	}
	public void setFirstUpKeepMon(String firstUpKeepMon) {
		this.firstUpKeepMon = firstUpKeepMon;
	}
	public String getSecondUpKeepMon() {
		return secondUpKeepMon;
	}
	public void setSecondUpKeepMon(String secondUpKeepMon) {
		this.secondUpKeepMon = secondUpKeepMon;
	}
	public String getIntervalMon() {
		return intervalMon;
	}
	public void setIntervalMon(String intervalMon) {
		this.intervalMon = intervalMon;
	}
	
}
