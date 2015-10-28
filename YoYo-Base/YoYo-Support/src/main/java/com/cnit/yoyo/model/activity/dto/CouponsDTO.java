package com.cnit.yoyo.model.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: CouponsDTO
 * @Description: 优惠券dto
 * @author xiaox
 * @date 2015-4-20 上午10:51:09
 */
public class CouponsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cpnsId; // 优惠券id
	private String cpnsName; // 优惠券名称
	private String issueType; // 发行类型：0 => ('平台发行'),1 => ('店铺发行')
	private String cpnsPrefix; // 生成优惠券前缀/号码(当全局时为号码)
	private Integer cpnsGenQuantity; // 获取的总数量
	private String cpnsKey; // 优惠券生成的key
	private String cpnsStatus; // 优惠券方案状态
	private Integer cpnsPoint; // 兑换优惠券积分
	private Integer ruleId; // 促销规则ID
	private String cpnsType; // 0 => ('一张无限使用'),1 => ('多张使用一次'),
	private Integer onlineQuantity; // 线上发放的总数
	private Integer onlineLimit; // 线上用户领取的限制
	private Integer onlineGenQuantity; // 线上领取的数量
	
	private Integer sortOrder; // 优先级
	private String stopRulesProcessing; // 排他性
	private String description; // 规则描述
	private Date fromTime; // 开始时间
	private Date toTime; // 结束时间
	private String memberLvIds; // 会员级别集合
	private String cTemplate; // 过滤条件模板
	private String sTemplate; // 优惠方案模板
	private String status; // 启用状态
	private String picIds; // 图片id
	private Long companyId;
	private Long storeId;
	private String bigPic; // 默认图片

	private String storeName; // 店铺名称
	private String catAndbrandIds; // 优惠券适用的分类与品牌
	private String conditions;
	private String actionSolution;
	private String fromTimeStr;
	private String toTimeStr;

	private Integer type;
	private BigDecimal cnnsPrice;
	private BigDecimal cnnsPar;

	
	public Integer getOnlineGenQuantity() {
		return onlineGenQuantity;
	}

	public void setOnlineGenQuantity(Integer onlineGenQuantity) {
		this.onlineGenQuantity = onlineGenQuantity;
	}

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

	public Integer getOnlineQuantity() {
		return onlineQuantity;
	}

	public void setOnlineQuantity(Integer onlineQuantity) {
		this.onlineQuantity = onlineQuantity;
	}

	public Integer getOnlineLimit() {
		return onlineLimit;
	}

	public void setOnlineLimit(Integer onlineLimit) {
		this.onlineLimit = onlineLimit;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getStopRulesProcessing() {
		return stopRulesProcessing;
	}

	public void setStopRulesProcessing(String stopRulesProcessing) {
		this.stopRulesProcessing = stopRulesProcessing;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPicIds() {
		return picIds;
	}

	public void setPicIds(String picIds) {
		this.picIds = picIds;
	}

	public String getCatAndbrandIds() {
		return catAndbrandIds;
	}

	public void setCatAndbrandIds(String catAndbrandIds) {
		this.catAndbrandIds = catAndbrandIds;
	}

	public String getBigPic() {
		return bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	public String getToTimeStr() {
		return toTimeStr;
	}

	public void setToTimeStr(String toTimeStr) {
		this.toTimeStr = toTimeStr;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getActionSolution() {
		return actionSolution;
	}

	public void setActionSolution(String actionSolution) {
		this.actionSolution = actionSolution;
	}

	public String getFromTimeStr() {
		return fromTimeStr;
	}

	public void setFromTimeStr(String fromTimeStr) {
		this.fromTimeStr = fromTimeStr;
	}

}