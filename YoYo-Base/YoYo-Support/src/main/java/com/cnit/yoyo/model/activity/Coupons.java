package com.cnit.yoyo.model.activity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName: Coupons
 * @Description: 优惠券
 * @author xiaox
 * @date 2015-4-20 上午10:49:52
 */
public class Coupons implements Serializable {

	private static final long serialVersionUID = -6576355475547907198L;
	private Integer cpnsId;
	private String cpnsName;
	private String issueType; // 发行类型
	private Long companyId;
	private Long storeId;
	private Integer pmtId;
	private String cpnsPrefix;
	private Integer cpnsGenQuantity; // 总数量
	private String cpnsKey;
	private String cpnsStatus; // 是否启用
	private Integer cpnsPoint;
	private Integer ruleId;
	private String cpnsType; // 优惠券类型
	private Integer onlineGenQuantity; // 线上领取的数量
	private Integer onlineLimit;
	private Integer onlineQuantity;
	private Date createTime;
	private String disabled;
	private String storeName;// 店铺名称
	private String bigPic;// 默认图片

	private String fromTime;// 开始时间
	private String toTime;// 结束时间

	private Integer type;
	private BigDecimal cnnsPrice;
	private BigDecimal cnnsPar;

	public BigDecimal getCnnsPrice() {
		return cnnsPrice;
	}

	public void setCnnsPrice(BigDecimal cnnsPrice) {
		this.cnnsPrice = cnnsPrice;
	}

	public BigDecimal getCnnsPar() {
		return cnnsPar;
	}

	public void setCnnsPar(BigDecimal cnnsPar) {
		this.cnnsPar = cnnsPar;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCpnsId() {
		return cpnsId;
	}

	public void setCpnsId(Integer cpnsId) {
		this.cpnsId = cpnsId;
	}

	public String getCpnsName() {
		return cpnsName;
	}

	public void setCpnsName(String cpnsName) {
		this.cpnsName = cpnsName;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
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

	public Integer getPmtId() {
		return pmtId;
	}

	public void setPmtId(Integer pmtId) {
		this.pmtId = pmtId;
	}

	public String getCpnsPrefix() {
		return cpnsPrefix;
	}

	public void setCpnsPrefix(String cpnsPrefix) {
		this.cpnsPrefix = cpnsPrefix;
	}

	public Integer getCpnsGenQuantity() {
		return cpnsGenQuantity;
	}

	public void setCpnsGenQuantity(Integer cpnsGenQuantity) {
		this.cpnsGenQuantity = cpnsGenQuantity;
	}

	public String getCpnsKey() {
		return cpnsKey;
	}

	public void setCpnsKey(String cpnsKey) {
		this.cpnsKey = cpnsKey;
	}

	public String getCpnsStatus() {
		return cpnsStatus;
	}

	public void setCpnsStatus(String cpnsStatus) {
		this.cpnsStatus = cpnsStatus;
	}

	public Integer getCpnsPoint() {
		return cpnsPoint;
	}

	public void setCpnsPoint(Integer cpnsPoint) {
		this.cpnsPoint = cpnsPoint;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getCpnsType() {
		return cpnsType;
	}

	public void setCpnsType(String cpnsType) {
		this.cpnsType = cpnsType;
	}

	public Integer getOnlineGenQuantity() {
		return onlineGenQuantity;
	}

	public void setOnlineGenQuantity(Integer onlineGenQuantity) {
		this.onlineGenQuantity = onlineGenQuantity;
	}

	public Integer getOnlineLimit() {
		return onlineLimit;
	}

	public void setOnlineLimit(Integer onlineLimit) {
		this.onlineLimit = onlineLimit;
	}

	public Integer getOnlineQuantity() {
		return onlineQuantity;
	}

	public void setOnlineQuantity(Integer onlineQuantity) {
		this.onlineQuantity = onlineQuantity;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBigPic() {
		return bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

}