package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.util.List;

public class AccessoryCatalog implements Serializable {
	private Integer catalogId;

	private String catalogName;

	private Integer catId;

	private Integer orderNum;

	private List<AccessoryParam> accessoryParams;

	private static final long serialVersionUID = 1L;

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", catalogId=").append(catalogId);
		sb.append(", catalogName=").append(catalogName);
		sb.append(", catId=").append(catId);
		sb.append(", orderNum=").append(orderNum);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	public List<AccessoryParam> getAccessoryParams() {
		return accessoryParams;
	}

	public void setAccessoryParams(List<AccessoryParam> accessoryParams) {
		this.accessoryParams = accessoryParams;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		AccessoryCatalog other = (AccessoryCatalog) that;
		return (this.getCatalogId() == null ? other.getCatalogId() == null
				: this.getCatalogId().equals(other.getCatalogId()))
				&& (this.getCatalogName() == null ? other.getCatalogName() == null
						: this.getCatalogName().equals(other.getCatalogName()))
				&& (this.getCatId() == null ? other.getCatId() == null : this
						.getCatId().equals(other.getCatId()))
				&& (this.getOrderNum() == null ? other.getOrderNum() == null
						: this.getOrderNum().equals(other.getOrderNum()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
		result = prime
				* result
				+ ((getCatalogName() == null) ? 0 : getCatalogName().hashCode());
		result = prime * result
				+ ((getCatId() == null) ? 0 : getCatId().hashCode());
		result = prime * result
				+ ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
		return result;
	}
}