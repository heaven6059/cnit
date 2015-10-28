package com.cnit.yoyo.model.activity.dto;

import com.cnit.yoyo.model.activity.CouponsApplyBrand;

public class CouponsApplyBrandDTO extends CouponsApplyBrand{
    private static final long serialVersionUID = -754442358527127336L;
    private String brandName; 
    private String catName;
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getCatName() {
        return catName;
    }
    public void setCatName(String catName) {
        this.catName = catName;
    }
    
    
}
