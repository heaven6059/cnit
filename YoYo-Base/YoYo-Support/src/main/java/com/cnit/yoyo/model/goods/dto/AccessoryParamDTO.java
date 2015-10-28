package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;

public class AccessoryParamDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -951845480423558377L;
	private Integer paramId;
	private String paramName;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getParamId() {
		return paramId;
	}

	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}
