package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.cnit.yoyo.util.JacksonDateTimeSerializer;

/**
 * 
 * @ClassName: Store
 * @Description: 分店
 * @author xiaox
 * @date 2015-3-10 上午9:29:12
 * @date modied by yuping 2015-5-12 下午3:00 memberId
 */
public class Store implements Serializable {
	/***/
	private static final long serialVersionUID = -4785710331005798151L;
	private Long storeId;
	private Long gradeId;
	private String shopName;// 分店店主名
	private Integer accountId;// 店主ID
	private Integer memberId;// 会员Id
	private Long companyId;
	private String companyName;
	private String storeIdcard;
	private String storeIdcardname;
	private String storeName;
	private String area;
	private String addr;
	private String tel;
	private String zip;
	private String email;
	private Long storeGrade;
	private String shopstatus;
	private String status;
	private String disabled;
	private Integer dOrder;
	private String image;
	private String imageId;
	private String imageCid;
	private String imageCodeid;
	private String imageTaxid;
	private Integer themeId;
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
	private String remark;
	private Company company;

	@JsonSerialize(using = JacksonDateTimeSerializer.class)
	private Date lastModify; // 修改时间
	private String areaIds;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStoreIdcard() {
		return storeIdcard;
	}

	public void setStoreIdcard(String storeIdcard) {
		this.storeIdcard = storeIdcard;
	}

	public String getStoreIdcardname() {
		return storeIdcardname;
	}

	public void setStoreIdcardname(String storeIdcardname) {
		this.storeIdcardname = storeIdcardname;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getStoreGrade() {
		return storeGrade;
	}

	public void setStoreGrade(Long storeGrade) {
		this.storeGrade = storeGrade;
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

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
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

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}