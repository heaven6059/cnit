package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cnit.yoyo.dto.BaseQryDTO;

public class EarnestDTO extends BaseQryDTO implements Serializable{

    private static final long serialVersionUID = -4893298248597198383L;
    // 添加时间
    private String addDate;
    // 过期时间
    private String expireDate;
    // 最新修改时间
    private String lastModifyDate;
    // 保证金金额
    private Double value;
    // 收费标准
    private Double issueMoney;
    
    private Long id;
    private Long companyId;
    private BigDecimal earnestValue;
    private Date addtime;
    private Date expiretime;
    private Date lastModify;
    private String reason;
    private String remark;
    private Integer source;
    private String operator;
    private String orders;
    private String goods;

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(String lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public EarnestDTO() {
        super();
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getIssueMoney() {
        return issueMoney;
    }

    public void setIssueMoney(Double issueMoney) {
        this.issueMoney = issueMoney;
    }

    public EarnestDTO(Long id, Long companyId, Double earnestValue, Date addtime, Date expiretime, Date lastModify, String reason, String remark,
            Integer source, String operator) {
        super();
        setId(id);
        setCompanyId(companyId);
        this.value = earnestValue;
        setAddtime(addtime);
        setExpiretime(expiretime);
        setLastModify(lastModify);
        setReason(reason);
        setRemark(remark);
        setSource(source);
        setOperator(operator);
    }

    @Override
    public String toString() {
        return "EarnestDTO [addDate=" + addDate + ", expireDate=" + expireDate + ", lastModifyDate=" + lastModifyDate + ", value=" + value
                + ", issueMoney=" + issueMoney + "]";
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

    public BigDecimal getEarnestValue() {
        return earnestValue;
    }

    public void setEarnestValue(BigDecimal earnestValue) {
        this.earnestValue = earnestValue;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

}
