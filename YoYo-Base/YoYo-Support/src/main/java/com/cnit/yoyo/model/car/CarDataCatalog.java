package com.cnit.yoyo.model.car;

import java.io.Serializable;

/**
 * @ClassName: CarDataCatalog
 * @Description: 车型数据项类别
 * @author xiaox
 * @date 2015-3-31 下午2:22:46
 */
public class CarDataCatalog implements Serializable {
    private static final long serialVersionUID = -5854747167491145432L;
    private Integer catalogId;
    private Integer orderId;
    private String catalogName;
    private String disabled;
    private String orderStmt;  //排序 

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
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