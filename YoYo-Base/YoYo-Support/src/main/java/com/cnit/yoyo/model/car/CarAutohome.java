package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class CarAutohome implements Serializable {
    private Integer carId;

    private String autohomeId;

    private static final long serialVersionUID = 1L;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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
        sb.append(", carId=").append(carId);
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
        CarAutohome other = (CarAutohome) that;
        return (this.getCarId() == null ? other.getCarId() == null : this.getCarId().equals(other.getCarId()))
            && (this.getAutohomeId() == null ? other.getAutohomeId() == null : this.getAutohomeId().equals(other.getAutohomeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCarId() == null) ? 0 : getCarId().hashCode());
        result = prime * result + ((getAutohomeId() == null) ? 0 : getAutohomeId().hashCode());
        return result;
    }
}