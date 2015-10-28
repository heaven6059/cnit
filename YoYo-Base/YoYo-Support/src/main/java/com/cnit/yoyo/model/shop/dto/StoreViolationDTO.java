package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.shop.StoreViolation;

public class StoreViolationDTO extends StoreViolation implements Serializable{

	private static final long serialVersionUID = -7134265422546939649L;
	private String catName;//违规类型名称
	private Integer parentCatId;//父级违规类型id(第一分类)
	private String parentCatName;//父级违规类型名称(第一分类)
	private String storeName;//分店名称
	private String companyName;//集团名称
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Integer getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(Integer parentCatId) {
		this.parentCatId = parentCatId;
	}
	public String getParentCatName() {
		return parentCatName;
	}
	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
