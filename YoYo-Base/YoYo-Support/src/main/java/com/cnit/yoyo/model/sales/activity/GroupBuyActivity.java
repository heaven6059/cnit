package com.cnit.yoyo.model.sales.activity;

import java.io.Serializable;
import java.util.Date;

public class GroupBuyActivity implements Serializable {
    private Long actId;

    private String name;

    private Date endTime;

    private Date applyStartTime;

    private Date startTime;

    private Date applyEndTime;

    private String storeType;

    private String activityTag;

    private String priceTag;

    private Integer nums;

    private String businessType;

    private Integer actOpen;

    private Integer actStatus;

    private Date lastModified;

    private String description;

    private static final long serialVersionUID = 1L;

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(Date applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getActivityTag() {
        return activityTag;
    }

    public void setActivityTag(String activityTag) {
        this.activityTag = activityTag;
    }

    public String getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(String priceTag) {
        this.priceTag = priceTag;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getActOpen() {
        return actOpen;
    }

    public void setActOpen(Integer actOpen) {
        this.actOpen = actOpen;
    }

    public Integer getActStatus() {
        return actStatus;
    }

    public void setActStatus(Integer actStatus) {
        this.actStatus = actStatus;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", actId=").append(actId);
        sb.append(", name=").append(name);
        sb.append(", endTime=").append(endTime);
        sb.append(", applyStartTime=").append(applyStartTime);
        sb.append(", startTime=").append(startTime);
        sb.append(", applyEndTime=").append(applyEndTime);
        sb.append(", storeType=").append(storeType);
        sb.append(", activityTag=").append(activityTag);
        sb.append(", priceTag=").append(priceTag);
        sb.append(", nums=").append(nums);
        sb.append(", businessType=").append(businessType);
        sb.append(", actOpen=").append(actOpen);
        sb.append(", actStatus=").append(actStatus);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", description=").append(description);
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
        GroupBuyActivity other = (GroupBuyActivity) that;
        return (this.getActId() == null ? other.getActId() == null : this.getActId().equals(other.getActId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getApplyStartTime() == null ? other.getApplyStartTime() == null : this.getApplyStartTime().equals(other.getApplyStartTime()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getApplyEndTime() == null ? other.getApplyEndTime() == null : this.getApplyEndTime().equals(other.getApplyEndTime()))
            && (this.getStoreType() == null ? other.getStoreType() == null : this.getStoreType().equals(other.getStoreType()))
            && (this.getActivityTag() == null ? other.getActivityTag() == null : this.getActivityTag().equals(other.getActivityTag()))
            && (this.getPriceTag() == null ? other.getPriceTag() == null : this.getPriceTag().equals(other.getPriceTag()))
            && (this.getNums() == null ? other.getNums() == null : this.getNums().equals(other.getNums()))
            && (this.getBusinessType() == null ? other.getBusinessType() == null : this.getBusinessType().equals(other.getBusinessType()))
            && (this.getActOpen() == null ? other.getActOpen() == null : this.getActOpen().equals(other.getActOpen()))
            && (this.getActStatus() == null ? other.getActStatus() == null : this.getActStatus().equals(other.getActStatus()))
            && (this.getLastModified() == null ? other.getLastModified() == null : this.getLastModified().equals(other.getLastModified()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActId() == null) ? 0 : getActId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getApplyStartTime() == null) ? 0 : getApplyStartTime().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getApplyEndTime() == null) ? 0 : getApplyEndTime().hashCode());
        result = prime * result + ((getStoreType() == null) ? 0 : getStoreType().hashCode());
        result = prime * result + ((getActivityTag() == null) ? 0 : getActivityTag().hashCode());
        result = prime * result + ((getPriceTag() == null) ? 0 : getPriceTag().hashCode());
        result = prime * result + ((getNums() == null) ? 0 : getNums().hashCode());
        result = prime * result + ((getBusinessType() == null) ? 0 : getBusinessType().hashCode());
        result = prime * result + ((getActOpen() == null) ? 0 : getActOpen().hashCode());
        result = prime * result + ((getActStatus() == null) ? 0 : getActStatus().hashCode());
        result = prime * result + ((getLastModified() == null) ? 0 : getLastModified().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}