package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.util.List;

public class CompanyAndStoreBean implements Serializable {
	private static final long serialVersionUID = -1589465828524931203L;
	private Integer companyId;
	private String issueType;
	private String shopName;
	private Integer accountId;
	private String storeName;
	private Integer storeGrade;
	private String companyName;
	private List<StoreBean> stores;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getStoreGrade() {
		return storeGrade;
	}

	public void setStoreGrade(Integer storeGrade) {
		this.storeGrade = storeGrade;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<StoreBean> getStores() {
		return stores;
	}

	public void setStores(List<StoreBean> stores) {
		this.stores = stores;
	}

}
