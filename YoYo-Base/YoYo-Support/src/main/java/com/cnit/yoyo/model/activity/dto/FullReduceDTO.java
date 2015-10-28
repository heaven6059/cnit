package com.cnit.yoyo.model.activity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: 满减活动dto 
 */
public class FullReduceDTO implements Serializable {
    private static final long serialVersionUID = 1L;   
    private Long actId;					//活动id
    private Integer ruleId;					//促销规则ID
    private Long companyId;
    private Long storeId;
    private String name;				//活动名称
    private String issueType;				//发行类型：0 => ('平台发行'),1 => ('店铺发行')
    private String usePlatform;				//使用平台 0:全平台；1:PC端；2：手机端
    private String actStatus;     //1=>__('待审核'), 2=>__('审核通过'),3=>__('审核不通过'),
    private String description;				//规则描述
    private Date   StartTime; 				//开始时间
    private Date   endTime; 					//结束时间
    private String fromTimeStr;
    private String toTimeStr;
    private String conditions;  //规则条件
    private int[] goodsId;  //商品ID
    private Integer joinTimes;  //参加次数
    private String memberLvIds; //会员级别
    private String activityType;
    
    
	public Long getActId() {
		return actId;
	}
	public void setActId(Long actId) {
		this.actId = actId;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getUsePlatform() {
		return usePlatform;
	}
	public void setUsePlatform(String usePlatform) {
		this.usePlatform = usePlatform;
	}
	public String getActStatus() {
		return actStatus;
	}
	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFromTimeStr() {
		return fromTimeStr;
	}
	public void setFromTimeStr(String fromTimeStr) {
		this.fromTimeStr = fromTimeStr;
	}
	public String getToTimeStr() {
		return toTimeStr;
	}
	public void setToTimeStr(String toTimeStr) {
		this.toTimeStr = toTimeStr;
	}
	public int[] getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int[] goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getJoinTimes() {
		return joinTimes;
	}
	public void setJoinTimes(Integer joinTimes) {
		this.joinTimes = joinTimes;
	}
	public String getMemberLvIds() {
		return memberLvIds;
	}
	public void setMemberLvIds(String memberLvIds) {
		this.memberLvIds = memberLvIds;
	}
	public Date getStartTime() {
		return StartTime;
	}
	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
   
}