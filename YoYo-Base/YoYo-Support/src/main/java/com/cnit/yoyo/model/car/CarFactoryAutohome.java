package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class CarFactoryAutohome implements Serializable {
    private Integer factoryId;

    private String autohomeId;

    private static final long serialVersionUID = 1L;

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getAutohomeId() {
        return autohomeId;
    }

    public void setAutohomeId(String autohomeId) {
        this.autohomeId = autohomeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", factoryId=").append(factoryId);
        sb.append(", autohomeId=").append(autohomeId);
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
        CarFactoryAutohome other = (CarFactoryAutohome) that;
        return (this.getFactoryId() == null ? other.getFactoryId() == null : this.getFactoryId().equals(other.getFactoryId()))
            && (this.getAutohomeId() == null ? other.getAutohomeId() == null : this.getAutohomeId().equals(other.getAutohomeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFactoryId() == null) ? 0 : getFactoryId().hashCode());
        result = prime * result + ((getAutohomeId() == null) ? 0 : getAutohomeId().hashCode());
        return result;
    }
}