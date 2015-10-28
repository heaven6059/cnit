package com.cnit.yoyo.model.member.dto;

import com.cnit.yoyo.model.member.Roles;

public class RolesDTO extends Roles {

	private static final long serialVersionUID = 8742773376199686844L;

	private String lastModifyDate;

	private String storeName; // 分店名称
	private String shopName; // 店铺名称
	private String regtimeEnd;
	private String regtimeStart;
	private String orderStmt; // 排序
	private Integer memberCount;

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
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

	public String getRegtimeEnd() {
		return regtimeEnd;
	}

	public void setRegtimeEnd(String regtimeEnd) {
		this.regtimeEnd = regtimeEnd;
	}

	public String getRegtimeStart() {
		return regtimeStart;
	}

	public void setRegtimeStart(String regtimeStart) {
		this.regtimeStart = regtimeStart;
	}

	public String getOrderStmt() {
		return orderStmt;
	}

	public void setOrderStmt(String orderStmt) {
		this.orderStmt = orderStmt;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
