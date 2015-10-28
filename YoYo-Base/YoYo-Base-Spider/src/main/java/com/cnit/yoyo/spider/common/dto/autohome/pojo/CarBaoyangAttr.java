package com.cnit.yoyo.spider.common.dto.autohome.pojo;

import java.io.Serializable;
import java.util.Date;

public class CarBaoyangAttr implements Serializable {
	
	private static final long serialVersionUID = -6940135559356074035L;
	private String id;//表主建,保养表主建id+保养项目名称+里程数
	private String baoyangId;// 保养表主建id
	private String item;// 保养项目名称,如：发动机机油
	private String gearBox;// 变速箱名称
	private String mileage;// 里程数
	private String upKeepCost;// 保养价格
	private String upKeepMonth;//月份数
	private String upKeepStatus;//保养状态 0：更换 1：检查
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	
	
	public CarBaoyangAttr() {
		super();  
	}

	public CarBaoyangAttr(String id, String baoyangId, String item,
			String gearBox, String mileage, String upKeepCost,
			String upKeepMonth, String upKeepStatus, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.baoyangId = baoyangId;
		this.item = item;
		this.gearBox = gearBox;
		this.mileage = mileage;
		this.upKeepCost = upKeepCost;
		this.upKeepMonth = upKeepMonth;
		this.upKeepStatus = upKeepStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getBaoyangId() {
		return baoyangId;
	}


	public void setBaoyangId(String baoyangId) {
		this.baoyangId = baoyangId;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public String getGearBox() {
		return gearBox;
	}


	public void setGearBox(String gearBox) {
		this.gearBox = gearBox;
	}


	public String getMileage() {
		return mileage;
	}


	public void setMileage(String mileage) {
		this.mileage = mileage;
	}


	public String getUpKeepCost() {
		return upKeepCost;
	}


	public void setUpKeepCost(String upKeepCost) {
		this.upKeepCost = upKeepCost;
	}


	public String getUpKeepMonth() {
		return upKeepMonth;
	}


	public void setUpKeepMonth(String upKeepMonth) {
		this.upKeepMonth = upKeepMonth;
	}


	public String getUpKeepStatus() {
		return upKeepStatus;
	}


	public void setUpKeepStatus(String upKeepStatus) {
		this.upKeepStatus = upKeepStatus;
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

}
