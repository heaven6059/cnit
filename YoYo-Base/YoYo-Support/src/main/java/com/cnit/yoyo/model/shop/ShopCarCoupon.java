package com.cnit.yoyo.model.shop;

import java.io.Serializable;
import java.util.Date;

public class ShopCarCoupon implements Serializable {
    private Long id;

    private Long companyId;

    private Long storeId;

    private String selasTitle;

    private Long productId;

    private Integer hotIcon;

    private String enabled;

    private Date lastModify;

    private static final long serialVersionUID = 1L;

    public ShopCarCoupon(){
    	
    }
    
    public ShopCarCoupon(Long companyId){
    	this.companyId=companyId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getSelasTitle() {
        return selasTitle;
    }

    public void setSelasTitle(String selasTitle) {
        this.selasTitle = selasTitle;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getHotIcon() {
        return hotIcon;
    }

    public void setHotIcon(Integer hotIcon) {
        this.hotIcon = hotIcon;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }
}