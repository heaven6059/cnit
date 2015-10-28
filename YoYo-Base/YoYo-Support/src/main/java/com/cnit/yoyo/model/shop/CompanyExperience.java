package com.cnit.yoyo.model.shop;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 *@description 店铺经验值记录
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
public class CompanyExperience implements Serializable {
    private Integer id;
    private String type;
    private Integer experience;
    private Long orderId;
    private String remark;
    private Date modifyTime;
    private Integer operator; //操作者id
    private Integer companyId;
    private static final long serialVersionUID = 1L;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getExperience() {
        return experience;
    }
    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Integer getOperator() {
        return operator;
    }
    public void setOperator(Integer operator) {
        this.operator = operator;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
  
}