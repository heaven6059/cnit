package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

public class MaintainAccessoryCatalogDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3969912941262966453L;
	private Long catalogId;
	private String catalogName;

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
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
