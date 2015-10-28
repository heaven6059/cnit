package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class AccessoryCarIndex implements Serializable {
    private Integer relateId;

    private Integer accId;

    private Integer carId;

    private String carName;

    private Integer factoryId;

    private String factoryName;

    private Integer deptId;

    private String deptName;

    private Integer yearId;

    private String yearValue;

    private static final long serialVersionUID = 1L;

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public String getYearValue() {
        return yearValue;
    }

    public void setYearValue(String yearValue) {
        this.yearValue = yearValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", relateId=").append(relateId);
        sb.append(", accId=").append(accId);
        sb.append(", carId=").append(carId);
        sb.append(", carName=").append(carName);
        sb.append(", factoryId=").append(factoryId);
        sb.append(", factoryName=").append(factoryName);
        sb.append(", deptId=").append(deptId);
        sb.append(", deptName=").append(deptName);
        sb.append(", yearId=").append(yearId);
        sb.append(", yearValue=").append(yearValue);
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
        AccessoryCarIndex other = (AccessoryCarIndex) that;
        return (this.getRelateId() == null ? other.getRelateId() == null : this.getRelateId().equals(other.getRelateId()))
            && (this.getAccId() == null ? other.getAccId() == null : this.getAccId().equals(other.getAccId()))
            && (this.getCarId() == null ? other.getCarId() == null : this.getCarId().equals(other.getCarId()))
            && (this.getCarName() == null ? other.getCarName() == null : this.getCarName().equals(other.getCarName()))
            && (this.getFactoryId() == null ? other.getFactoryId() == null : this.getFactoryId().equals(other.getFactoryId()))
            && (this.getFactoryName() == null ? other.getFactoryName() == null : this.getFactoryName().equals(other.getFactoryName()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getYearId() == null ? other.getYearId() == null : this.getYearId().equals(other.getYearId()))
            && (this.getYearValue() == null ? other.getYearValue() == null : this.getYearValue().equals(other.getYearValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRelateId() == null) ? 0 : getRelateId().hashCode());
        result = prime * result + ((getAccId() == null) ? 0 : getAccId().hashCode());
        result = prime * result + ((getCarId() == null) ? 0 : getCarId().hashCode());
        result = prime * result + ((getCarName() == null) ? 0 : getCarName().hashCode());
        result = prime * result + ((getFactoryId() == null) ? 0 : getFactoryId().hashCode());
        result = prime * result + ((getFactoryName() == null) ? 0 : getFactoryName().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getYearId() == null) ? 0 : getYearId().hashCode());
        result = prime * result + ((getYearValue() == null) ? 0 : getYearValue().hashCode());
        return result;
    }
}