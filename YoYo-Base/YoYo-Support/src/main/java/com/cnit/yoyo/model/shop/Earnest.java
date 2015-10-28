package com.cnit.yoyo.model.shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Earnest implements Serializable {

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
	private static final long serialVersionUID = 1L;
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

}