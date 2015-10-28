package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;

public class ShopBrandBLOBsWithLabel implements Serializable{

	private static final long serialVersionUID = -434611365736362337L;
    private Integer companyBrandId;
	private Long companyId;
	private Integer brandId;
	private String label;
	private String brandName;
	private Integer storeCat;
	private String brandUrl;
	private String brandLogo;
	private String brandAptitude;
	private String disabled;
	private String status;
	private String type;
	private String brandKeywords;
	private String brandDesc;
	private String failReason;

	private String[] labelNames;
	private String[] labelFontColors;
	private String[] labelBgColors;
	
	
	private String companyName;
	private String catalogName; //分类名称
	

	public Integer getCompanyBrandId() {
		return companyBrandId;
	}

	public void setCompanyBrandId(Integer companyBrandId) {
		this.companyBrandId = companyBrandId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getStoreCat() {
		return storeCat;
	}

	public void setStoreCat(Integer storeCat) {
		this.storeCat = storeCat;
	}

	public String getBrandUrl() {
		return brandUrl;
	}

	public void setBrandUrl(String brandUrl) {
		this.brandUrl = brandUrl;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public String getBrandAptitude() {
		return brandAptitude;
	}

	public void setBrandAptitude(String brandAptitude) {
		this.brandAptitude = brandAptitude;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrandKeywords() {
		return brandKeywords;
	}

	public void setBrandKeywords(String brandKeywords) {
		this.brandKeywords = brandKeywords;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String[] getLabelNames() {
		return labelNames;
	}

	public void setLabelNames(String[] labelNames) {
		this.labelNames = labelNames;
	}

	public String[] getLabelFontColors() {
		return labelFontColors;
	}

	public void setLabelFontColors(String[] labelFontColors) {
		this.labelFontColors = labelFontColors;
	}

	public String[] getLabelBgColors() {
		return labelBgColors;
	}

	public void setLabelBgColors(String[] labelBgColors) {
		this.labelBgColors = labelBgColors;
	}

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

}