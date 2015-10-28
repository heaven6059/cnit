package com.cnit.yoyo.autohome.model;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.spider.AutohomeCarAttr;
import com.cnit.yoyo.model.spider.AutohomeCarInfo;

public class AutohomeCarInfoDTO extends AutohomeCarInfo implements Serializable {
	private String autohomeId;
	private Integer relationId;
	private String dataType;
	
	public String getAutohomeId() {
		return autohomeId;
	}
	public void setAutohomeId(String autohomeId) {
		this.autohomeId = autohomeId;
	}
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}