package com.cnit.yoyo.model.goods;

import java.io.Serializable;

import javax.persistence.Column;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * @description 品牌
 * @detail <功能详细描述>
 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
 * @version 1.0.0
 */
public class Brand extends BaseQryDTO implements Serializable {
	private static final long serialVersionUID = 49768699602839993L;
	private Integer brandId;
	@Column(name = "brand_name")
	private String brandName;
	private String brandUrl;
	private String brandDesc;
	private String brandLogo;
	private String brandAptitude;
	private String brandKeywords;
	private String brandSetting;
	private String disabled;
	private Integer ordernum;
	private Integer favCount;
	private String title;
	private String metaKeywords;
	private int brandType; // 品牌类型
	private String orderStmt; // 排序 brand_id desc
	private String brandPinyin;// 品牌对应的中文拼音

	private String metaDesc;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandUrl() {
		return brandUrl;
	}

	public void setBrandUrl(String brandUrl) {
		this.brandUrl = brandUrl;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
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

	public String getBrandKeywords() {
		return brandKeywords;
	}

	public void setBrandKeywords(String brandKeywords) {
		this.brandKeywords = brandKeywords;
	}

	public String getBrandSetting() {
		return brandSetting;
	}

	public void setBrandSetting(String brandSetting) {
		this.brandSetting = brandSetting;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getFavCount() {
		return favCount;
	}

	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDesc() {
		return metaDesc;
	}

	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}

	public int getBrandType() {
		return brandType;
	}

	public void setBrandType(int brandType) {
		this.brandType = brandType;
	}

	public String getOrderStmt() {
		return orderStmt;
	}

	public void setOrderStmt(String orderStmt) {
		this.orderStmt = orderStmt;
	}

	public String getBrandPinyin() {
		return brandPinyin;
	}

	public void setBrandPinyin(String brandPinyin) {
		this.brandPinyin = brandPinyin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}