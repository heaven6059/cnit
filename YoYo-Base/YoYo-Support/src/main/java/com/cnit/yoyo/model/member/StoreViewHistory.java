package com.cnit.yoyo.model.member;

import java.io.Serializable;
import java.util.Date;

/**  
* @Title: StoreViewHistory.java
* @Package com.cnit.yoyo.model.member
* @Description: 店铺浏览历史
* @Author 王鹏
* @date 2015-5-18 下午3:28:38
* @version V1.0  
*/ 
public class StoreViewHistory implements Serializable {

	private Integer id;
	private Long companyId;
	private Long memberId;
	private Long storeId;
	private Date wishlistDate;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Date getWishlistDate() {
		return wishlistDate;
	}

	public void setWishlistDate(Date wishlistDate) {
		this.wishlistDate = wishlistDate;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", companyId=").append(companyId);
		sb.append(", memberId=").append(memberId);
		sb.append(", storeId=").append(storeId);
		sb.append(", wishlistDate=").append(wishlistDate);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		StoreViewHistory other = (StoreViewHistory) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getCompanyId() == null ? other.getCompanyId() == null
						: this.getCompanyId().equals(other.getCompanyId()))
				&& (this.getMemberId() == null ? other.getMemberId() == null
						: this.getMemberId().equals(other.getMemberId()))
				&& (this.getStoreId() == null ? other.getStoreId() == null
						: this.getStoreId().equals(other.getStoreId()))
				&& (this.getWishlistDate() == null ? other.getWishlistDate() == null
						: this.getWishlistDate()
								.equals(other.getWishlistDate()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
		result = prime * result
				+ ((getMemberId() == null) ? 0 : getMemberId().hashCode());
		result = prime * result
				+ ((getStoreId() == null) ? 0 : getStoreId().hashCode());
		result = prime
				* result
				+ ((getWishlistDate() == null) ? 0 : getWishlistDate()
						.hashCode());
		return result;
	}
}