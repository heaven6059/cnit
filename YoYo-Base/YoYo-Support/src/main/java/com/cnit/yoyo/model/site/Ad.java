package com.cnit.yoyo.model.site;

import java.io.Serializable;
import java.util.Date;

public class Ad implements Serializable {
    private Integer adId;

    private Integer adType;

    private Date beginTime;

    private Date endTime;

    private Boolean enabled;

    private Integer sequence;

    private String name;

    private String description;

    private String adConfig;
    
    private String picSize;
    
    //广告的配置项
    private String config;
    
    private Date createTime;
    
    private Integer adNum;
    

	public String getPicSize() {
		return picSize;
	}

	public void setPicSize(String picSize) {
		this.picSize = picSize;
	}

	public Integer getAdNum() {
		return adNum;
	}

	public void setAdNum(Integer adNum) {
		this.adNum = adNum;
	}

	private static final long serialVersionUID = 1L;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getAdType() {
        return adType;
    }

    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdConfig() {
        return adConfig;
    }

    public void setAdConfig(String adConfig) {
        this.adConfig = adConfig;
    }
}