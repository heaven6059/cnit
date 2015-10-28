package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class ProductWithBLOBs extends Product implements Serializable {
    private static final long serialVersionUID = -5950897757365777927L;

    private String limitGoodsdown;//下架所有商品
    
    private String limitStore;//店铺屏蔽
    
    private String limitStoredown;//关闭店铺
    
    private String storeShopstatus;
    
    private String storeStatus;
    
    private String storeDisabled;
    
    private Integer companyId;//集团id

	public String getLimitGoodsdown() {
		return limitGoodsdown;
	}

	public void setLimitGoodsdown(String limitGoodsdown) {
		this.limitGoodsdown = limitGoodsdown;
	}

	public String getLimitStore() {
		return limitStore;
	}

	public void setLimitStore(String limitStore) {
		this.limitStore = limitStore;
	}

	public String getLimitStoredown() {
		return limitStoredown;
	}

	public void setLimitStoredown(String limitStoredown) {
		this.limitStoredown = limitStoredown;
	}

	public String getStoreShopstatus() {
		return storeShopstatus;
	}

	public void setStoreShopstatus(String storeShopstatus) {
		this.storeShopstatus = storeShopstatus;
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}

	public String getStoreDisabled() {
		return storeDisabled;
	}

	public void setStoreDisabled(String storeDisabled) {
		this.storeDisabled = storeDisabled;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
    
    
}