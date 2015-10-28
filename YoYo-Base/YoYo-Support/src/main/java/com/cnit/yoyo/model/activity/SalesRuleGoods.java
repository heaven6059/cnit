package com.cnit.yoyo.model.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SalesRuleGoods
 * @Description: 商品促销规则
 * @author xiaox
 * @date 2015-4-20 上午10:50:13
 */
public class SalesRuleGoods implements Serializable {

    private static final long serialVersionUID = 1960823683367973002L;
    private Integer ruleId;
    private String name;
    private Long companyId;
    private Long storeId;
    private Date createTime;
    private Date fromTime;
    private Date toTime;
    private String memberLvIds;
    private String status;
    private String stopRulesProcessing;
    private Integer sortOrder;
    private Boolean freeShipping;
    private String cTemplate;
    private String sTemplate;
    private Date applyTime;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public String getMemberLvIds() {
        return memberLvIds;
    }

    public void setMemberLvIds(String memberLvIds) {
        this.memberLvIds = memberLvIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStopRulesProcessing() {
        return stopRulesProcessing;
    }

    public void setStopRulesProcessing(String stopRulesProcessing) {
        this.stopRulesProcessing = stopRulesProcessing;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getcTemplate() {
        return cTemplate;
    }

    public void setcTemplate(String cTemplate) {
        this.cTemplate = cTemplate;
    }

    public String getsTemplate() {
        return sTemplate;
    }

    public void setsTemplate(String sTemplate) {
        this.sTemplate = sTemplate;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

}