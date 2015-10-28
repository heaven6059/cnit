package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CarFactory
 * @Description: 汽车厂商
 * @author xiaox
 * @date 2015-3-28 上午10:43:50
 */

public class CarFactory implements Serializable {

    private static final long serialVersionUID = -469939310189773676L;
    private Integer factoryId;
    private String factoryName;
    private String linkUrl;
    private Integer groupId;
    private Integer scopeId;
    private Integer brandId;
    private String iconFile;
    private String pinyin;
    private Date createtime;
    private String carBrandBrandtobrand;
    private String disabled;
    private String orderStmt;  //排序

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getScopeId() {
        return scopeId;
    }

    public void setScopeId(Integer scopeId) {
        this.scopeId = scopeId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getIconFile() {
        return iconFile;
    }

    public void setIconFile(String iconFile) {
        this.iconFile = iconFile;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCarBrandBrandtobrand() {
        return carBrandBrandtobrand;
    }

    public void setCarBrandBrandtobrand(String carBrandBrandtobrand) {
        this.carBrandBrandtobrand = carBrandBrandtobrand;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getOrderStmt() {
        return orderStmt;
    }

    public void setOrderStmt(String orderStmt) {
        this.orderStmt = orderStmt;
    }

}