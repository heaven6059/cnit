package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;

/**
 * @description 相关商品的dto
 * @detail <功能详细描述>
 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
 * @version 1.0.0
 */
public class GoodsRelatedDTO implements Serializable {

    private static final long serialVersionUID = -5662419260128210026L;
    private String goodsName;
    private Integer goodsId;
    private String manual; // 关联关系，单向或双向

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

}