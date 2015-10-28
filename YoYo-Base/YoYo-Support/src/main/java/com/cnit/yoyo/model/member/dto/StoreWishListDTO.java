package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.member.StoreWishList;

public class StoreWishListDTO extends StoreWishList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9196407084686033753L;

	private String storeName;
	private String shopName;
	private Integer favCount;
	private String storeUrl;
	private String storeLogo;
	private String loginName;

	public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public String getStoreUrl() {
		return storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getFavCount() {
		return favCount;
	}

	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}