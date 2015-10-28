package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.util.List;

public class AccessoryCatalogDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -951845480423558377L;
	private Integer catalogId;
	private String catalogName;
	private List<AccessoryParamDTO> paramDTOs;

	public List<AccessoryParamDTO> getParamDTOs() {
		return paramDTOs;
	}

	public void setParamDTOs(List<AccessoryParamDTO> paramDTOs) {
		this.paramDTOs = paramDTOs;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
