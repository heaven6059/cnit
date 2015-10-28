package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.math.BigDecimal;

public class RedbeltVo implements Serializable {
	private static final long serialVersionUID = -6024400213428930813L;
	private Long redbeltId;
	private String target;
	private String memberLvIds;
	private Integer nums;
	private BigDecimal totalAmount;
	private Integer rule;
	private String categorys;
	private String ranges;
	private String startTime;
	private String endTime;
	private Integer status;
	private String remark;

	public Long getRedbeltId() {
		return redbeltId;
	}

	public void setRedbeltId(Long redbeltId) {
		this.redbeltId = redbeltId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMemberLvIds() {
		return memberLvIds;
	}

	public void setMemberLvIds(String memberLvIds) {
		this.memberLvIds = memberLvIds;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getRule() {
		return rule;
	}

	public void setRule(Integer rule) {
		this.rule = rule;
	}

	public String getCategorys() {
		return categorys;
	}

	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	public String getRanges() {
		return ranges;
	}

	public void setRanges(String ranges) {
		this.ranges = ranges;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}