package com.cnit.yoyo.model.goods;

import java.io.Serializable;

/**
 * @description 分类与规格的关系
 * @detail <功能详细描述>
 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
 * @version 1.0.0
 */
public class CatSpecShip implements Serializable {

    private static final long serialVersionUID = -1643662740919108795L;
    private Integer shipId;
    private Integer specId;
    private Integer catId;

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

}