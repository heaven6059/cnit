package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class CarSpiderAuditLogWithBLOBs extends CarSpiderAuditLog implements Serializable {
    private String beforeValue;

	private String afterValue;

	private static final long serialVersionUID = 1L;

	public String getBeforeValue() {
		return beforeValue;
	}

	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	public String getAfterValue() {
		return afterValue;
	}

	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", beforeValue=").append(beforeValue);
		sb.append(", afterValue=").append(afterValue);
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
		CarSpiderAuditLogWithBLOBs other = (CarSpiderAuditLogWithBLOBs) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getSpiderCompareId() == null ? other
						.getSpiderCompareId() == null : this
						.getSpiderCompareId()
						.equals(other.getSpiderCompareId()))
				&& (this.getType() == null ? other.getType() == null : this
						.getType().equals(other.getType()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getCreateId() == null ? other.getCreateId() == null
						: this.getCreateId().equals(other.getCreateId()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getCause() == null ? other.getCause() == null : this
						.getCause().equals(other.getCause()))
				&& (this.getBeforeValue() == null ? other.getBeforeValue() == null
						: this.getBeforeValue().equals(other.getBeforeValue()))
				&& (this.getAfterValue() == null ? other.getAfterValue() == null
						: this.getAfterValue().equals(other.getAfterValue()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime
				* result
				+ ((getSpiderCompareId() == null) ? 0 : getSpiderCompareId()
						.hashCode());
		result = prime * result
				+ ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getCreateId() == null) ? 0 : getCreateId().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result
				+ ((getCause() == null) ? 0 : getCause().hashCode());
		result = prime
				* result
				+ ((getBeforeValue() == null) ? 0 : getBeforeValue().hashCode());
		result = prime * result
				+ ((getAfterValue() == null) ? 0 : getAfterValue().hashCode());
		return result;
	}

}