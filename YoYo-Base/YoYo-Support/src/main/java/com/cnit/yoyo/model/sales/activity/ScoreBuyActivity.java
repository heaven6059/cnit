package com.cnit.yoyo.model.sales.activity;

import java.io.Serializable;
import java.util.Date;

public class ScoreBuyActivity implements Serializable {
    private Integer actId;
    private String name;
    private Date endTime;
    private Date applyStartTime;
    private Date startTime;
    private Date applyEndTime;
    private String businessType;
    private Integer actOpen;
    private Integer actStatus;
    private Date lastModified;
    private Integer type;
    private String priceTag;
    private Long limitNum;
    private String description;
    private static final long serialVersionUID = 1L;

    public Integer getActId() {
        return actId;
    }

    
    public void setActId(Integer actId) {
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
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getPriceTag() {
        return priceTag;
    }
    public void setPriceTag(String priceTag) {
        this.priceTag = priceTag;
    }
    public Long getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Long limitNum) {
        this.limitNum = limitNum;
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
        sb.append(", businessType=").append(businessType);
        sb.append(", actOpen=").append(actOpen);
        sb.append(", actStatus=").append(actStatus);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", type=").append(type);
        sb.append(", priceTag=").append(priceTag);
        sb.append(", limitNum=").append(limitNum);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}