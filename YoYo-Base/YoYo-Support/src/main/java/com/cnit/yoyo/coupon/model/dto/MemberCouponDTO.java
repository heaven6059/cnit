package com.cnit.yoyo.coupon.model.dto;

import java.io.Serializable;

import com.cnit.yoyo.coupon.model.MemberCoupon;
import com.cnit.yoyo.model.activity.Coupons;
import com.cnit.yoyo.model.activity.SalesRuleGoodsWithBLOBs;

public class MemberCouponDTO extends MemberCoupon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1004239064934043530L;
	private String storeName;
	private Coupons coupons;
	private SalesRuleGoodsWithBLOBs salesRuleGoods;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Coupons getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public SalesRuleGoodsWithBLOBs getSalesRuleGoods() {
		return salesRuleGoods;
	}

	public void setSalesRuleGoods(SalesRuleGoodsWithBLOBs salesRuleGoods) {
		this.salesRuleGoods = salesRuleGoods;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
