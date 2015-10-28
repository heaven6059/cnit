package com.cnit.yoyo.model.shop;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *@description 经营范围类别
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
public class CompanyRegionCat implements Serializable {
    private Integer regionId;

    private String regionName;

    private Integer parentRegionId;

    private Date lastMofify;

    private String disabled;

    private Date createTime;
    private int childCount; //子类数目

    private static final long serialVersionUID = 1L;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getParentRegionId() {
        return parentRegionId;
    }

    public void setParentRegionId(Integer parentRegionId) {
        this.parentRegionId = parentRegionId;
    }

    public Date getLastMofify() {
        return lastMofify;
    }

    public void setLastMofify(Date lastMofify) {
        this.lastMofify = lastMofify;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }


}