package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.util.List;

public class MaintainAccessoryItemsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4328984245278765243L;
	private Long id;
	private Long catId;
	private String catName;
	private Long catalogId;
	private String catalogName;

	private List<MaintainAccessoryItemsDTO> maintainAccessorys;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

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

	public List<MaintainAccessoryItemsDTO> getMaintainAccessorys() {
		return maintainAccessorys;
	}

	public void setMaintainAccessorys(
			List<MaintainAccessoryItemsDTO> maintainAccessorys) {
		this.maintainAccessorys = maintainAccessorys;
	}

}
