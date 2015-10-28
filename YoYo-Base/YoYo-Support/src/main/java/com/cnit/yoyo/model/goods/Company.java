package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 店铺
 * @detail <功能详细描述>
 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
 * @version 1.0.0
 */
public class Company implements Serializable {
    private static final long serialVersionUID = -8159066512658499840L;
    private Long companyId;
    private Long gradeId;
    private String shopName;
    private Integer accountId;
    private String storeName;
    private Long storeGrade;
    private Long regionId;
    private Double earnest;
    private String companyName;
    private String companyNo;
    private String companyTaxno;
    private String companyCodename;
    private String companyIdname;
    private String companyIdcard;
    private String companyCname;
    private String companyCidcard;
    private String companyCharge;
    private String companyCtel;
    private String companyArea;
    private String companyAddr;
    private String companyCarea;
    private String companyCareaIds;
    private String companyAreaIds;
    private String companyCaddr;
    private String companyEarnest;
    private String shopstatus;
    private String status;
    private String approved;
    private String certification;
    private String recommend;
    private String disabled;
    private Integer dOrder;
    private String image;
    private String imageId;
    private String imageCid;
    private String imageCodeid;
    private String imageTaxid;
    private Integer favCount;
    private Integer buyMCount;
    private Long storeSpace;
    private Long storeUsedspace;
    private Integer experience;
    private BigDecimal alertNum;
    private String limitGoods;
    private String limitGoodsdown;
    private String limitNews;
    private Integer limitNewsValue;
    private String limitStore;
    private String limitStoredown;
    private String limitSales;
    private String limitEarnest;
    private String storeCert;
    private String bankName;
    private String bankCardid;
    private Date lastModify;  //修改时间
    private Date lastTime;  //店铺的有效期
    private Date companyTime;
    private Date companyTimestart;
    private Date companyTimeend;
    private Date approveTime; //审核时间
    private Date approvedTime; //审核通过时间
    private Date applyTime;    //申请时间
    private String isCheck;  //是否需要审核商品
    private String companyType; //店铺类型：0：快修店 1：4s店
    private String companyPhone; //企业手机号码
    
    
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Long getGradeId() {
        return gradeId;
    }
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public Long getStoreGrade() {
        return storeGrade;
    }
    public void setStoreGrade(Long storeGrade) {
        this.storeGrade = storeGrade;
    }
    public Long getRegionId() {
        return regionId;
    }
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
    public Date getLastTime() {
        return lastTime;
    }
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
    public Double getEarnest() {
        return earnest;
    }
    public void setEarnest(Double earnest) {
        this.earnest = earnest;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyNo() {
        return companyNo;
    }
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }
    public String getCompanyTaxno() {
        return companyTaxno;
    }
    public void setCompanyTaxno(String companyTaxno) {
        this.companyTaxno = companyTaxno;
    }
    public String getCompanyCodename() {
        return companyCodename;
    }
    public void setCompanyCodename(String companyCodename) {
        this.companyCodename = companyCodename;
    }
    public String getCompanyIdname() {
        return companyIdname;
    }
    public void setCompanyIdname(String companyIdname) {
        this.companyIdname = companyIdname;
    }
    public String getCompanyIdcard() {
        return companyIdcard;
    }
    public void setCompanyIdcard(String companyIdcard) {
        this.companyIdcard = companyIdcard;
    }
    public String getCompanyCname() {
        return companyCname;
    }
    public void setCompanyCname(String companyCname) {
        this.companyCname = companyCname;
    }
    public String getCompanyCidcard() {
        return companyCidcard;
    }
    public void setCompanyCidcard(String companyCidcard) {
        this.companyCidcard = companyCidcard;
    }
    public String getCompanyCharge() {
        return companyCharge;
    }
    public void setCompanyCharge(String companyCharge) {
        this.companyCharge = companyCharge;
    }
    public String getCompanyCtel() {
        return companyCtel;
    }
    public void setCompanyCtel(String companyCtel) {
        this.companyCtel = companyCtel;
    }
    public String getCompanyArea() {
        return companyArea;
    }
    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }
    public String getCompanyAddr() {
        return companyAddr;
    }
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }
    public String getCompanyCarea() {
        return companyCarea;
    }
    public void setCompanyCarea(String companyCarea) {
        this.companyCarea = companyCarea;
    }
    public String getCompanyCaddr() {
        return companyCaddr;
    }
    public void setCompanyCaddr(String companyCaddr) {
        this.companyCaddr = companyCaddr;
    }
    public String getCompanyEarnest() {
        return companyEarnest;
    }
    public void setCompanyEarnest(String companyEarnest) {
        this.companyEarnest = companyEarnest;
    }
    public Date getCompanyTime() {
        return companyTime;
    }
    public void setCompanyTime(Date companyTime) {
        this.companyTime = companyTime;
    }
    public Date getCompanyTimestart() {
        return companyTimestart;
    }
    public void setCompanyTimestart(Date companyTimestart) {
        this.companyTimestart = companyTimestart;
    }
    public Date getCompanyTimeend() {
        return companyTimeend;
    }
    public void setCompanyTimeend(Date companyTimeend) {
        this.companyTimeend = companyTimeend;
    }
    public String getShopstatus() {
        return shopstatus;
    }
    public void setShopstatus(String shopstatus) {
        this.shopstatus = shopstatus;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getApproved() {
        return approved;
    }
    public void setApproved(String approved) {
        this.approved = approved;
    }
    public String getCertification() {
        return certification;
    }
    public void setCertification(String certification) {
        this.certification = certification;
    }
    public String getRecommend() {
        return recommend;
    }
    public void setRecommend(String recommend) {
        this.recommend = recommend;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getImageCid() {
        return imageCid;
    }
    public void setImageCid(String imageCid) {
        this.imageCid = imageCid;
    }
    public String getImageCodeid() {
        return imageCodeid;
    }
    public void setImageCodeid(String imageCodeid) {
        this.imageCodeid = imageCodeid;
    }
    public String getImageTaxid() {
        return imageTaxid;
    }
    public void setImageTaxid(String imageTaxid) {
        this.imageTaxid = imageTaxid;
    }
    public Date getApproveTime() {
        return approveTime;
    }
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }
    public Date getApprovedTime() {
        return approvedTime;
    }
    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }
    public Date getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public Integer getFavCount() {
        return favCount;
    }
    public void setFavCount(Integer favCount) {
        this.favCount = favCount;
    }
    public Integer getBuyMCount() {
        return buyMCount;
    }
    public void setBuyMCount(Integer buyMCount) {
        this.buyMCount = buyMCount;
    }
    public Long getStoreSpace() {
        return storeSpace;
    }
    public void setStoreSpace(Long storeSpace) {
        this.storeSpace = storeSpace;
    }
    public Long getStoreUsedspace() {
        return storeUsedspace;
    }
    public void setStoreUsedspace(Long storeUsedspace) {
        this.storeUsedspace = storeUsedspace;
    }
    public Integer getExperience() {
        return experience;
    }
    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    public BigDecimal getAlertNum() {
        return alertNum;
    }
    public void setAlertNum(BigDecimal alertNum) {
        this.alertNum = alertNum;
    }
    public String getLimitGoods() {
        return limitGoods;
    }
    public void setLimitGoods(String limitGoods) {
        this.limitGoods = limitGoods;
    }
    public String getLimitGoodsdown() {
        return limitGoodsdown;
    }
    public void setLimitGoodsdown(String limitGoodsdown) {
        this.limitGoodsdown = limitGoodsdown;
    }
    public String getLimitNews() {
        return limitNews;
    }
    public void setLimitNews(String limitNews) {
        this.limitNews = limitNews;
    }
    public Integer getLimitNewsValue() {
        return limitNewsValue;
    }
    public void setLimitNewsValue(Integer limitNewsValue) {
        this.limitNewsValue = limitNewsValue;
    }
    public String getLimitStore() {
        return limitStore;
    }
    public void setLimitStore(String limitStore) {
        this.limitStore = limitStore;
    }
    public String getLimitStoredown() {
        return limitStoredown;
    }
    public void setLimitStoredown(String limitStoredown) {
        this.limitStoredown = limitStoredown;
    }
    public String getLimitSales() {
        return limitSales;
    }
    public void setLimitSales(String limitSales) {
        this.limitSales = limitSales;
    }
    public String getLimitEarnest() {
        return limitEarnest;
    }
    public void setLimitEarnest(String limitEarnest) {
        this.limitEarnest = limitEarnest;
    }
    public String getStoreCert() {
        return storeCert;
    }
    public void setStoreCert(String storeCert) {
        this.storeCert = storeCert;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankCardid() {
        return bankCardid;
    }
    public void setBankCardid(String bankCardid) {
        this.bankCardid = bankCardid;
    }
    public String getIsCheck() {
        return isCheck;
    }
    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
    public String getCompanyAreaIds() {
        return companyAreaIds;
    }
    public void setCompanyAreaIds(String companyAreaIds) {
        this.companyAreaIds = companyAreaIds;
    }
    public String getCompanyCareaIds() {
        return companyCareaIds;
    }
    public void setCompanyCareaIds(String companyCareaIds) {
        this.companyCareaIds = companyCareaIds;
    }
    public String getCompanyType() {
        return companyType;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    public String getCompanyPhone() {
        return companyPhone;
    }
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

   
}