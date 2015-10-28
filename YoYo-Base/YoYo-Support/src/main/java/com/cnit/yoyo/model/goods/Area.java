package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class Area implements Serializable {
    private static final long serialVersionUID = -8197758391075667966L;
    private Integer areaId;
    private String areaName;
    private Integer areaParentId;
    private Byte areaSort;
    private Integer areaDeep;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }

    public Byte getAreaSort() {
        return areaSort;
    }

    public void setAreaSort(Byte areaSort) {
        this.areaSort = areaSort;
    }

    public Integer getAreaDeep() {
        return areaDeep;
    }

    public void setAreaDeep(Integer areaDeep) {
        this.areaDeep = areaDeep;
    }

}