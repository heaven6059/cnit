package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class StoreBean implements Serializable {
	private static final long serialVersionUID = -231321770540541362L;
	private Integer storeId;
	private Integer companyId;
	private Integer accountId;
	private String storeName;
	private String disabled;
	private String limitGoods;  //分店是否限制发布商品：0不限制，1：限制

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

    public String getLimitGoods() {
        return limitGoods;
    }

    public void setLimitGoods(String limitGoods) {
        this.limitGoods = limitGoods;
    }
	
}
