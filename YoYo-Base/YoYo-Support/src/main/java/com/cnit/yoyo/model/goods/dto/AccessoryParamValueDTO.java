package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;

public class AccessoryParamValueDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2662655175757956490L;
	private String value;
	private Integer catalogId;
	private Integer paramId;
	private String catalogName;
	private String paramName;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public Integer getParamId() {
		return paramId;
	}

	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
