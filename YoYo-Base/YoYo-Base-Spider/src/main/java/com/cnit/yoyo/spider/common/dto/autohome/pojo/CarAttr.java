package com.cnit.yoyo.spider.common.dto.autohome.pojo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class CarAttr implements Serializable {
	private static final long serialVersionUID = 1745700196012776088L;

	private String id;// 表主键,车型id+参数项名称+参数名称

	private String name;// 参数名称
	private String val;// 参数值
	private Long carTypeId;// 车型id
	private Integer parentOrderIndex;// 位置号
	private Integer childOrderIndex;// 位置号
	private String type;//参数值类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		if(StringUtils.isEmpty(val)){
			val = "";
		}
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public Long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}

	public Integer getParentOrderIndex() {
		return parentOrderIndex;
	}

	public void setParentOrderIndex(Integer parentOrderIndex) {
		this.parentOrderIndex = parentOrderIndex;
	}

	public Integer getChildOrderIndex() {
		return childOrderIndex;
	}

	public void setChildOrderIndex(Integer childOrderIndex) {
		this.childOrderIndex = childOrderIndex;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
