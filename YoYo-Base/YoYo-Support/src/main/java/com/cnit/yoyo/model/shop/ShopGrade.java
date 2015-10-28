package com.cnit.yoyo.model.shop;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ShopGrade
 * @Description: 店铺等级
 * @author xiaox
 * @date 2015-3-11 上午9:58:46
 */
public class ShopGrade implements Serializable {
    private static final long serialVersionUID = -8868138954978006180L;
    private Long gradeId;
    private Integer shopNums;  //开店个数
    private String goodsNum;   //允许发布商品数
    private String couponsNum; //允许发行优惠数
    private String themeNum;   //可选模板套数
    private String gradeMoney; //收费标准
    private String gradeName;  
    private String issueMoney; //保证金额
    private String issueType;  //公司类型
    private Integer experience;
    private String defaultLv;
    private String certification;
    private String disabled;
    private Date lastModify;
    private Integer dOrder;
    private String remark;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getShopNums() {
        return shopNums;
    }

    public void setShopNums(Integer shopNums) {
        this.shopNums = shopNums;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getCouponsNum() {
        return couponsNum;
    }

    public void setCouponsNum(String couponsNum) {
        this.couponsNum = couponsNum;
    }

    public String getThemeNum() {
        return themeNum;
    }

    public void setThemeNum(String themeNum) {
        this.themeNum = themeNum;
    }

    public String getGradeMoney() {
        return gradeMoney;
    }

    public void setGradeMoney(String gradeMoney) {
        this.gradeMoney = gradeMoney;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getIssueMoney() {
        return issueMoney;
    }

    public void setIssueMoney(String issueMoney) {
        this.issueMoney = issueMoney;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getDefaultLv() {
        return defaultLv;
    }

    public void setDefaultLv(String defaultLv) {
        this.defaultLv = defaultLv;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }



    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public Integer getdOrder() {
        return dOrder;
    }

    public void setdOrder(Integer dOrder) {
        this.dOrder = dOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}