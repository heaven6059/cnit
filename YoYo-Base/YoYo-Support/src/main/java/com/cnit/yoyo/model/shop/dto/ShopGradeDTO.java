package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * @ClassName: ShopGradeDTO
 * @Description: 店铺等级DTO
 * @author xiaox
 * @date 2015-5-25 上午9:58:46
 */
public class ShopGradeDTO extends BaseQryDTO implements Serializable {
    
    private static final long serialVersionUID = 5541742110981806723L;
    private Long gradeId;
    private Integer shopNums;
    private String goodsNum;
    private String couponsNum;
    private String themeNum;
    private String gradeMoney;
    private String gradeName;
    private String issueMoney;
    private String issueType;
    private Integer experience;
    private String defaultLv;
    private String certification;
    private String disabled;
    private Date lastModify;
    private Integer dOrder;
    private String remark;
    //查询条件
    private Integer maxShopNums;
    private Integer maxEexperience;
    private Integer maxIssueMoney;
    private Integer maxGoodsNum;
    private Integer maxCouponsNum;
    private Integer maxThemeNum;
    private Integer maxGradeMoney;
    

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

    public Integer getMaxEexperience() {
        return maxEexperience;
    }

    public void setMaxEexperience(Integer maxEexperience) {
        this.maxEexperience = maxEexperience;
    }

    public Integer getMaxIssueMoney() {
        return maxIssueMoney;
    }

    public void setMaxIssueMoney(Integer maxIssueMoney) {
        this.maxIssueMoney = maxIssueMoney;
    }

    public Integer getMaxGoodsNum() {
        return maxGoodsNum;
    }

    public void setMaxGoodsNum(Integer maxGoodsNum) {
        this.maxGoodsNum = maxGoodsNum;
    }

    public Integer getMaxCouponsNum() {
        return maxCouponsNum;
    }

    public void setMaxCouponsNum(Integer maxCouponsNum) {
        this.maxCouponsNum = maxCouponsNum;
    }

    public Integer getMaxThemeNum() {
        return maxThemeNum;
    }

    public void setMaxThemeNum(Integer maxThemeNum) {
        this.maxThemeNum = maxThemeNum;
    }

    public Integer getMaxGradeMoney() {
        return maxGradeMoney;
    }

    public void setMaxGradeMoney(Integer maxGradeMoney) {
        this.maxGradeMoney = maxGradeMoney;
    }

    public Integer getMaxShopNums() {
        return maxShopNums;
    }

    public void setMaxShopNums(Integer maxShopNums) {
        this.maxShopNums = maxShopNums;
    }
    
}