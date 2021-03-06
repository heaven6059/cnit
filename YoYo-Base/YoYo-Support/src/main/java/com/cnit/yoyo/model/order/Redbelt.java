package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Redbelt implements  Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.redbelt_id
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Long redbeltId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.target
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Integer target;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.nums
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Integer nums;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.total_amount
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private BigDecimal totalAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.rule
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Integer rule;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.categorys
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private String categorys;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.start_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.end_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.status
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.remark
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.create_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.update_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.create_user
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Integer createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_redbelt.update_user
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private Integer updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_redbelt
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    private static final long serialVersionUID = 1L;

    private String memberLvIds;
    
    public String getMemberLvIds() {
		return memberLvIds;
	}

	public void setMemberLvIds(String memberLvIds) {
		this.memberLvIds = memberLvIds;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.redbelt_id
     *
     * @return the value of t_redbelt.redbelt_id
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Long getRedbeltId() {
        return redbeltId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.redbelt_id
     *
     * @param redbeltId the value for t_redbelt.redbelt_id
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setRedbeltId(Long redbeltId) {
        this.redbeltId = redbeltId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.target
     *
     * @return the value of t_redbelt.target
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Integer getTarget() {
        return target;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.target
     *
     * @param target the value for t_redbelt.target
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setTarget(Integer target) {
        this.target = target;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.nums
     *
     * @return the value of t_redbelt.nums
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Integer getNums() {
        return nums;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.nums
     *
     * @param nums the value for t_redbelt.nums
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setNums(Integer nums) {
        this.nums = nums;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.total_amount
     *
     * @return the value of t_redbelt.total_amount
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.total_amount
     *
     * @param totalAmount the value for t_redbelt.total_amount
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.rule
     *
     * @return the value of t_redbelt.rule
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Integer getRule() {
        return rule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.rule
     *
     * @param rule the value for t_redbelt.rule
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setRule(Integer rule) {
        this.rule = rule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.categorys
     *
     * @return the value of t_redbelt.categorys
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public String getCategorys() {
        return categorys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.categorys
     *
     * @param categorys the value for t_redbelt.categorys
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setCategorys(String categorys) {
        this.categorys = categorys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.start_time
     *
     * @return the value of t_redbelt.start_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.start_time
     *
     * @param startTime the value for t_redbelt.start_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.end_time
     *
     * @return the value of t_redbelt.end_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.end_time
     *
     * @param endTime the value for t_redbelt.end_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.status
     *
     * @return the value of t_redbelt.status
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.status
     *
     * @param status the value for t_redbelt.status
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.remark
     *
     * @return the value of t_redbelt.remark
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.remark
     *
     * @param remark the value for t_redbelt.remark
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.create_time
     *
     * @return the value of t_redbelt.create_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.create_time
     *
     * @param createTime the value for t_redbelt.create_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.update_time
     *
     * @return the value of t_redbelt.update_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.update_time
     *
     * @param updateTime the value for t_redbelt.update_time
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.create_user
     *
     * @return the value of t_redbelt.create_user
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.create_user
     *
     * @param createUser the value for t_redbelt.create_user
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_redbelt.update_user
     *
     * @return the value of t_redbelt.update_user
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_redbelt.update_user
     *
     * @param updateUser the value for t_redbelt.update_user
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redbelt
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", redbeltId=").append(redbeltId);
        sb.append(", target=").append(target);
        sb.append(", nums=").append(nums);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", rule=").append(rule);
        sb.append(", categorys=").append(categorys);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redbelt
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Redbelt other = (Redbelt) that;
        return (this.getRedbeltId() == null ? other.getRedbeltId() == null : this.getRedbeltId().equals(other.getRedbeltId()))
            && (this.getTarget() == null ? other.getTarget() == null : this.getTarget().equals(other.getTarget()))
            && (this.getNums() == null ? other.getNums() == null : this.getNums().equals(other.getNums()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getRule() == null ? other.getRule() == null : this.getRule().equals(other.getRule()))
            && (this.getCategorys() == null ? other.getCategorys() == null : this.getCategorys().equals(other.getCategorys()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redbelt
     *
     * @mbggenerated Mon Sep 28 16:08:19 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRedbeltId() == null) ? 0 : getRedbeltId().hashCode());
        result = prime * result + ((getTarget() == null) ? 0 : getTarget().hashCode());
        result = prime * result + ((getNums() == null) ? 0 : getNums().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getRule() == null) ? 0 : getRule().hashCode());
        result = prime * result + ((getCategorys() == null) ? 0 : getCategorys().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        return result;
    }
}