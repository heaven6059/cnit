package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

/**
 * 
 *@description 保养项目商品 dto
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
public class CarMaintainGoodsDTO  implements Serializable {

    private static final long serialVersionUID = -1225073949363689614L;
	
    private Integer maintainItemId;       //保养项id
    private String maintainItemName;      //保养项名称
    private String maintainAccName;       //保养配件类别名称
    private Integer goodsCateId;          //商品分类id,用于查找对应商品
    
    //保存默认保养商品
    private Integer maintainId;  //保养配件周期表id
    private Integer productId;
    private Integer companyId;
    private Integer storeId;
    private String defaultName;  //默认货品名称
    private Integer defaultProductId;  //默认货品id
    
    
    
    public Integer getMaintainItemId() {
        return maintainItemId;
    }
    public void setMaintainItemId(Integer maintainItemId) {
        this.maintainItemId = maintainItemId;
    }
    public String getMaintainItemName() {
        return maintainItemName;
    }
    public void setMaintainItemName(String maintainItemName) {
        this.maintainItemName = maintainItemName;
    }
    public String getMaintainAccName() {
        return maintainAccName;
    }
    public void setMaintainAccName(String maintainAccName) {
        this.maintainAccName = maintainAccName;
    }
    public Integer getGoodsCateId() {
        return goodsCateId;
    }
    public void setGoodsCateId(Integer goodsCateId) {
        this.goodsCateId = goodsCateId;
    }
    public Integer getMaintainId() {
        return maintainId;
    }
    public void setMaintainId(Integer maintainId) {
        this.maintainId = maintainId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public String getDefaultName() {
        return defaultName;
    }
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
    public Integer getDefaultProductId() {
        return defaultProductId;
    }
    public void setDefaultProductId(Integer defaultProductId) {
        this.defaultProductId = defaultProductId;
    }
    
    
    
	
}