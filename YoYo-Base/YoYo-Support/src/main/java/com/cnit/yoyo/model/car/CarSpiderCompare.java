package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.util.Date;

public class CarSpiderCompare implements Serializable {
    private Integer id;

	private String spiderValId;

	private String beforeTips;

	private String afterTips;

	private String beforeValue;

	private String afterValue;

	private String compareType;

	private String type;

	private Integer status;

	private String createId;

	private Date createTime;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpiderValId() {
		return spiderValId;
	}

	public void setSpiderValId(String spiderValId) {
		this.spiderValId = spiderValId;
	}

	public String getBeforeTips() {
		return beforeTips;
	}

	public void setBeforeTips(String beforeTips) {
		this.beforeTips = beforeTips;
	}

	public String getAfterTips() {
		return afterTips;
	}

	public void setAfterTips(String afterTips) {
		this.afterTips = afterTips;
	}

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

	public String getCompareType() {
		return compareType;
	}

	public void setCompareType(String compareType) {
		this.compareType = compareType;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", spiderValId=").append(spiderValId);
		sb.append(", beforeTips=").append(beforeTips);
		sb.append(", afterTips=").append(afterTips);
		sb.append(", beforeValue=").append(beforeValue);
		sb.append(", afterValue=").append(afterValue);
		sb.append(", compareType=").append(compareType);
		sb.append(", type=").append(type);
		sb.append(", status=").append(status);
		sb.append(", createId=").append(createId);
		sb.append(", createTime=").append(createTime);
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
		CarSpiderCompare other = (CarSpiderCompare) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getSpiderValId() == null ? other.getSpiderValId() == null
						: this.getSpiderValId().equals(other.getSpiderValId()))
				&& (this.getBeforeTips() == null ? other.getBeforeTips() == null
						: this.getBeforeTips().equals(other.getBeforeTips()))
				&& (this.getAfterTips() == null ? other.getAfterTips() == null
						: this.getAfterTips().equals(other.getAfterTips()))
				&& (this.getBeforeValue() == null ? other.getBeforeValue() == null
						: this.getBeforeValue().equals(other.getBeforeValue()))
				&& (this.getAfterValue() == null ? other.getAfterValue() == null
						: this.getAfterValue().equals(other.getAfterValue()))
				&& (this.getCompareType() == null ? other.getCompareType() == null
						: this.getCompareType().equals(other.getCompareType()))
				&& (this.getType() == null ? other.getType() == null : this
						.getType().equals(other.getType()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getCreateId() == null ? other.getCreateId() == null
						: this.getCreateId().equals(other.getCreateId()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime
				* result
				+ ((getSpiderValId() == null) ? 0 : getSpiderValId().hashCode());
		result = prime * result
				+ ((getBeforeTips() == null) ? 0 : getBeforeTips().hashCode());
		result = prime * result
				+ ((getAfterTips() == null) ? 0 : getAfterTips().hashCode());
		result = prime
				* result
				+ ((getBeforeValue() == null) ? 0 : getBeforeValue().hashCode());
		result = prime * result
				+ ((getAfterValue() == null) ? 0 : getAfterValue().hashCode());
		result = prime
				* result
				+ ((getCompareType() == null) ? 0 : getCompareType().hashCode());
		result = prime * result
				+ ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getCreateId() == null) ? 0 : getCreateId().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		return result;
	}

}