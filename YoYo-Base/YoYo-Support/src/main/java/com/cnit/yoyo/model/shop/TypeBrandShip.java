package com.cnit.yoyo.model.shop;

import java.io.Serializable;
/**
 * 
 * @ClassName: TypeBrandShip 
 * @Description: 商品分类与品牌的关系对象
 * @author xiaox
 * @date 2015-3-25 下午7:15:29
 */
public class TypeBrandShip extends TypeBrandShipKey implements Serializable {

    private Integer catId;
    private String identifier;
    private Integer brandOrder;
    private static final long serialVersionUID = 1L;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getBrandOrder() {
        return brandOrder;
    }

    public void setBrandOrder(Integer brandOrder) {
        this.brandOrder = brandOrder;
    }

   
}