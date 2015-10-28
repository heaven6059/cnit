package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class AccessoryParamStrKey implements Serializable {
    private Integer accId;

    private Integer paramId;

    private static final long serialVersionUID = 1L;

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accId=").append(accId);
        sb.append(", paramId=").append(paramId);
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
        AccessoryParamStrKey other = (AccessoryParamStrKey) that;
        return (this.getAccId() == null ? other.getAccId() == null : this.getAccId().equals(other.getAccId()))
            && (this.getParamId() == null ? other.getParamId() == null : this.getParamId().equals(other.getParamId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccId() == null) ? 0 : getAccId().hashCode());
        result = prime * result + ((getParamId() == null) ? 0 : getParamId().hashCode());
        return result;
    }
}