package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class CarDataListValueKey implements Serializable {
    private Integer carId;

    private Integer dataId;

    private static final long serialVersionUID = 1L;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", carId=").append(carId);
        sb.append(", dataId=").append(dataId);
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
        CarDataListValueKey other = (CarDataListValueKey) that;
        return (this.getCarId() == null ? other.getCarId() == null : this.getCarId().equals(other.getCarId()))
            && (this.getDataId() == null ? other.getDataId() == null : this.getDataId().equals(other.getDataId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCarId() == null) ? 0 : getCarId().hashCode());
        result = prime * result + ((getDataId() == null) ? 0 : getDataId().hashCode());
        return result;
    }
}