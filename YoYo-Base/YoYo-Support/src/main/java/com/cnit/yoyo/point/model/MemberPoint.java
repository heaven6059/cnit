package com.cnit.yoyo.point.model;

import java.io.Serializable;
import java.util.Date;

public class MemberPoint implements Serializable {
    private Integer id;//积分日志ID

    private Integer memberId;//会员ID

    private Integer inPoint;//收入积分

    private Integer outPoint;//支出积分

    private Integer remainPoint;//剩余积分

    private Date createtime;//创建时间

    private Date expiretime;//过期时间

    private String isOverdue;//无过期

    private String remark;//备注

    private Long relatedId;//积分关联对象ID

    private Boolean type;//操作类型

    private String operator;//操作员ID

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getInPoint() {
        return inPoint;
    }

    public void setInPoint(Integer inPoint) {
        this.inPoint = inPoint;
    }

    public Integer getOutPoint() {
        return outPoint;
    }

    public void setOutPoint(Integer outPoint) {
        this.outPoint = outPoint;
    }

    public Integer getRemainPoint() {
        return remainPoint;
    }

    public void setRemainPoint(Integer remainPoint) {
        this.remainPoint = remainPoint;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", inPoint=").append(inPoint);
        sb.append(", outPoint=").append(outPoint);
        sb.append(", remainPoint=").append(remainPoint);
        sb.append(", createtime=").append(createtime);
        sb.append(", expiretime=").append(expiretime);
        sb.append(", isOverdue=").append(isOverdue);
        sb.append(", remark=").append(remark);
        sb.append(", relatedId=").append(relatedId);
        sb.append(", type=").append(type);
        sb.append(", operator=").append(operator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}