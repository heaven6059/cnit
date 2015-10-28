package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.util.Date;

public class CarSpiderAuditLog implements Serializable {
    private Integer id;

	private String spiderCompareId;

	private String type;

	private Integer status;

	private String createId;

	private Date createTime;

	private String cause;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpiderCompareId() {
		return spiderCompareId;
	}

	public void setSpiderCompareId(String spiderCompareId) {
		this.spiderCompareId = spiderCompareId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", spiderCompareId=").append(spiderCompareId);
		sb.append(", type=").append(type);
		sb.append(", status=").append(status);
		sb.append(", createId=").append(createId);
		sb.append(", createTime=").append(createTime);
		sb.append(", cause=").append(cause);
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
		CarSpiderAuditLog other = (CarSpiderAuditLog) that;
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
						.getCause().equals(other.getCause()));
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
		return result;
	}

}