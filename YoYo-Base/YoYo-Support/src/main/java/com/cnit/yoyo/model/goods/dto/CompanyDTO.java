package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;
/**
 * 
 * @ClassName: CompanyDo 
 * @Description: 店铺数据传输对象
 * @author xiaox
 * @date 2015-3-10 上午9:17:47
 */
public class CompanyDTO extends BaseQryDTO implements Serializable {

    private Long companyId;
  
    private Long gradeId;

    private String shopName; 
   
    private Integer accountId;

    private String storeName;  //店铺名称
   
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
    
    private String companyArea_2;  //对于只有2级的省，如：台湾省

    private String companyAddr;

    private String companyCarea;
    
    private String companyCarea_2;
 
    private String companyCaddr;

    private String companyEarnest;
    private String shopstatus;

    
    private String companyRemark;  //营业执照经营范围

    private String companyUrl;
  
    private String image;

    private String imageId;

    private String imageCid;

    private String imageCodeid;

    private String imageTaxid;
    private String companyCareaIds;
    private String companyAreaIds;
  
   
    private String bankName;
 
    private String bankCardid;
    
    private String tel;
    
    private String remark;
    private String approvedremark;
    private String lastModify;  //修改时间
    private String lastTime;  //店铺的有效期
    private String companyTime;
    private String companyTimestart;
    private String companyTimeend;
    private String approveTime; //审核时间
    private String approvedTime; //审核通过时间
    private String applyTime;    //申请时间
    
    private int brandId;
    private String brandName;  //新申请品牌名称
    private String brandAlias;  //别名
    
    private String loginName;  //店主登录名
    private String email;
    private String issueMoney; //保证金
    private int experience;
    private String approved; //审核状态
    private String issueType; //店铺类型
    private String isCheck; //商品是否需要审核
    private String companyType; //店铺类型：0：快修店 1：4s店
    private String companyPhone; //企业手机号码
    
    //高级查询字段
    private String approvedTimeStart;   //审核通过开始时间
    private String approvedTimeEnd;
    private String applyTimeStart;      //申请开始时间
    private String applyTimeEnd;
    private String disabled; 
 
    
    ////分店属性
    
    private String storeIdcardname; //店主实名
    private String storeIdcard;
    private String catIds; //需要审核的分类id集合
    private String regionCatName; //店铺的经营范围名称集合
    private String orderStmt;  //排序
    private static final long serialVersionUID = 1L;


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


    public String getCompanyArea_2() {
        return companyArea_2;
    }


    public void setCompanyArea_2(String companyArea_2) {
        this.companyArea_2 = companyArea_2;
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


    public String getCompanyCarea_2() {
        return companyCarea_2;
    }


    public void setCompanyCarea_2(String companyCarea_2) {
        this.companyCarea_2 = companyCarea_2;
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


    public String getCompanyRemark() {
        return companyRemark;
    }


    public void setCompanyRemark(String companyRemark) {
        this.companyRemark = companyRemark;
    }


    public String getCompanyUrl() {
        return companyUrl;
    }


    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
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


    public String getTel() {
        return tel;
    }


    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getApprovedremark() {
        return approvedremark;
    }


    public void setApprovedremark(String approvedremark) {
        this.approvedremark = approvedremark;
    }


  

    public String getLastModify() {
        return lastModify;
    }


    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }


    public String getLastTime() {
        return lastTime;
    }


    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }


    public String getCompanyTime() {
        return companyTime;
    }


    public void setCompanyTime(String companyTime) {
        this.companyTime = companyTime;
    }


    public String getCompanyTimestart() {
        return companyTimestart;
    }


    public void setCompanyTimestart(String companyTimestart) {
        this.companyTimestart = companyTimestart;
    }


    public String getCompanyTimeend() {
        return companyTimeend;
    }


    public void setCompanyTimeend(String companyTimeend) {
        this.companyTimeend = companyTimeend;
    }


    public String getApproveTime() {
        return approveTime;
    }


    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }


    public String getApprovedTime() {
        return approvedTime;
    }


    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }


    public String getApplyTime() {
        return applyTime;
    }


    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }


    public int getBrandId() {
        return brandId;
    }


    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }


    public String getBrandName() {
        return brandName;
    }


    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


    public String getBrandAlias() {
        return brandAlias;
    }


    public void setBrandAlias(String brandAlias) {
        this.brandAlias = brandAlias;
    }


    public String getStoreIdcardname() {
        return storeIdcardname;
    }


    public void setStoreIdcardname(String storeIdcardname) {
        this.storeIdcardname = storeIdcardname;
    }


    public String getStoreIdcard() {
        return storeIdcard;
    }


    public void setStoreIdcard(String storeIdcard) {
        this.storeIdcard = storeIdcard;
    }




    public String getLoginName() {
        return loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getShopstatus() {
        return shopstatus;
    }


    public void setShopstatus(String shopstatus) {
        this.shopstatus = shopstatus;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getIssueMoney() {
        return issueMoney;
    }


    public void setIssueMoney(String issueMoney) {
        this.issueMoney = issueMoney;
    }


    public int getExperience() {
        return experience;
    }


    public void setExperience(int experience) {
        this.experience = experience;
    }


    public String getApproved() {
        return approved;
    }


    public void setApproved(String approved) {
        this.approved = approved;
    }


    public String getApprovedTimeStart() {
        return approvedTimeStart;
    }


    public void setApprovedTimeStart(String approvedTimeStart) {
        this.approvedTimeStart = approvedTimeStart;
    }


    public String getApprovedTimeEnd() {
        return approvedTimeEnd;
    }


    public void setApprovedTimeEnd(String approvedTimeEnd) {
        this.approvedTimeEnd = approvedTimeEnd;
    }


    public String getApplyTimeStart() {
        return applyTimeStart;
    }


    public void setApplyTimeStart(String applyTimeStart) {
        this.applyTimeStart = applyTimeStart;
    }


    public String getApplyTimeEnd() {
        return applyTimeEnd;
    }


    public void setApplyTimeEnd(String applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }


    public String getIssueType() {
        return issueType;
    }


    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }


    public String getIsCheck() {
        return isCheck;
    }


    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }


    public String getCatIds() {
        return catIds;
    }


    public void setCatIds(String catIds) {
        this.catIds = catIds;
    }


    public String getCompanyCareaIds() {
        return companyCareaIds;
    }


    public void setCompanyCareaIds(String companyCareaIds) {
        this.companyCareaIds = companyCareaIds;
    }


    public String getCompanyAreaIds() {
        return companyAreaIds;
    }


    public void setCompanyAreaIds(String companyAreaIds) {
        this.companyAreaIds = companyAreaIds;
    }


    public String getDisabled() {
        return disabled;
    }


    public void setDisabled(String disabled) {
        this.disabled = disabled;
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


    public String getRegionCatName() {
        return regionCatName;
    }


    public void setRegionCatName(String regionCatName) {
        this.regionCatName = regionCatName;
    }


    public String getOrderStmt() {
        return orderStmt;
    }


    public void setOrderStmt(String orderStmt) {
        this.orderStmt = orderStmt;
    }


  

  
}