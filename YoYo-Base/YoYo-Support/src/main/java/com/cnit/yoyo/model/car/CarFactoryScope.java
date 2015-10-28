package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.util.HtmlRegexpUtil;

public class CarFactoryScope implements Serializable {
    private Integer scopeId;

    private String carType;

    private Date createtime;

    private String disabled;

    private Date lastMidifity;

    private static final long serialVersionUID = 1L;

    public Integer getScopeId() {
        return scopeId;
    }

    public void setScopeId(Integer scopeId) {
        this.scopeId = scopeId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = HtmlRegexpUtil.replaceTag(carType);
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public Date getLastMidifity() {
        return lastMidifity;
    }

    public void setLastMidifity(Date lastMidifity) {
        this.lastMidifity = lastMidifity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scopeId=").append(scopeId);
        sb.append(", carType=").append(carType);
        sb.append(", createtime=").append(createtime);
        sb.append(", disabled=").append(disabled);
        sb.append(", lastMidifity=").append(lastMidifity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

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
        CarFactoryScope other = (CarFactoryScope) that;
        return (this.getScopeId() == null ? other.getScopeId() == null : this.getScopeId().equals(other.getScopeId()))
            && (this.getCarType() == null ? other.getCarType() == null : this.getCarType().equals(other.getCarType()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getLastMidifity() == null ? other.getLastMidifity() == null : this.getLastMidifity().equals(other.getLastMidifity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getScopeId() == null) ? 0 : getScopeId().hashCode());
        result = prime * result + ((getCarType() == null) ? 0 : getCarType().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getLastMidifity() == null) ? 0 : getLastMidifity().hashCode());
        return result;
    }
}