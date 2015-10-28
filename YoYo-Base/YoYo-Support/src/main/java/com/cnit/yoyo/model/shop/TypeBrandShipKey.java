package com.cnit.yoyo.model.shop;

import java.io.Serializable;

public class TypeBrandShipKey implements Serializable {

    private Integer id;
    private Integer brandId;
    private static final long serialVersionUID = 1L;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBrandId() {
        return brandId;
    }
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
    
}