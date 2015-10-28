package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class CarDeptAutohome implements Serializable {
    private Integer carDeptId;

    private String autohomeId;

    private static final long serialVersionUID = 1L;

    public Integer getCarDeptId() {
        return carDeptId;
    }

    public void setCarDeptId(Integer carDeptId) {
        this.carDeptId = carDeptId;
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
        sb.append(", carDeptId=").append(carDeptId);
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
        CarDeptAutohome other = (CarDeptAutohome) that;
        return (this.getCarDeptId() == null ? other.getCarDeptId() == null : this.getCarDeptId().equals(other.getCarDeptId()))
            && (this.getAutohomeId() == null ? other.getAutohomeId() == null : this.getAutohomeId().equals(other.getAutohomeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCarDeptId() == null) ? 0 : getCarDeptId().hashCode());
        result = prime * result + ((getAutohomeId() == null) ? 0 : getAutohomeId().hashCode());
        return result;
    }
}