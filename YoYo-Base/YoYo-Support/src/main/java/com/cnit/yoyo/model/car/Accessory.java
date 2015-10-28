package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.math.BigDecimal;

public class Accessory implements Serializable {
    private Integer accId;

    private Integer catId;

    private String accName;

    private String accSpec;

    private String accUnit;

    private String accForshort;

    private String accCode;

    private String accOem;

    private Integer brandId;

    private String brandName;

    private String accMainPlants;

    private BigDecimal accScPrice;

    private BigDecimal accPrice;

    private String accPack;

    private String accLogistics;

    private String disabled;

    private String isRelatedCar;

    private Integer orderNum;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccSpec() {
        return accSpec;
    }

    public void setAccSpec(String accSpec) {
        this.accSpec = accSpec;
    }

    public String getAccUnit() {
        return accUnit;
    }

    public void setAccUnit(String accUnit) {
        this.accUnit = accUnit;
    }

    public String getAccForshort() {
        return accForshort;
    }

    public void setAccForshort(String accForshort) {
        this.accForshort = accForshort;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getAccOem() {
        return accOem;
    }

    public void setAccOem(String accOem) {
        this.accOem = accOem;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAccMainPlants() {
        return accMainPlants;
    }

    public void setAccMainPlants(String accMainPlants) {
        this.accMainPlants = accMainPlants;
    }

    public BigDecimal getAccScPrice() {
        return accScPrice;
    }

    public void setAccScPrice(BigDecimal accScPrice) {
        this.accScPrice = accScPrice;
    }

    public BigDecimal getAccPrice() {
        return accPrice;
    }

    public void setAccPrice(BigDecimal accPrice) {
        this.accPrice = accPrice;
    }

    public String getAccPack() {
        return accPack;
    }

    public void setAccPack(String accPack) {
        this.accPack = accPack;
    }

    public String getAccLogistics() {
        return accLogistics;
    }

    public void setAccLogistics(String accLogistics) {
        this.accLogistics = accLogistics;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getIsRelatedCar() {
        return isRelatedCar;
    }

    public void setIsRelatedCar(String isRelatedCar) {
        this.isRelatedCar = isRelatedCar;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accId=").append(accId);
        sb.append(", catId=").append(catId);
        sb.append(", accName=").append(accName);
        sb.append(", accSpec=").append(accSpec);
        sb.append(", accUnit=").append(accUnit);
        sb.append(", accForshort=").append(accForshort);
        sb.append(", accCode=").append(accCode);
        sb.append(", accOem=").append(accOem);
        sb.append(", brandId=").append(brandId);
        sb.append(", brandName=").append(brandName);
        sb.append(", accMainPlants=").append(accMainPlants);
        sb.append(", accScPrice=").append(accScPrice);
        sb.append(", accPrice=").append(accPrice);
        sb.append(", accPack=").append(accPack);
        sb.append(", accLogistics=").append(accLogistics);
        sb.append(", disabled=").append(disabled);
        sb.append(", isRelatedCar=").append(isRelatedCar);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", remark=").append(remark);
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
        Accessory other = (Accessory) that;
        return (this.getAccId() == null ? other.getAccId() == null : this.getAccId().equals(other.getAccId()))
            && (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
            && (this.getAccName() == null ? other.getAccName() == null : this.getAccName().equals(other.getAccName()))
            && (this.getAccSpec() == null ? other.getAccSpec() == null : this.getAccSpec().equals(other.getAccSpec()))
            && (this.getAccUnit() == null ? other.getAccUnit() == null : this.getAccUnit().equals(other.getAccUnit()))
            && (this.getAccForshort() == null ? other.getAccForshort() == null : this.getAccForshort().equals(other.getAccForshort()))
            && (this.getAccCode() == null ? other.getAccCode() == null : this.getAccCode().equals(other.getAccCode()))
            && (this.getAccOem() == null ? other.getAccOem() == null : this.getAccOem().equals(other.getAccOem()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getAccMainPlants() == null ? other.getAccMainPlants() == null : this.getAccMainPlants().equals(other.getAccMainPlants()))
            && (this.getAccScPrice() == null ? other.getAccScPrice() == null : this.getAccScPrice().equals(other.getAccScPrice()))
            && (this.getAccPrice() == null ? other.getAccPrice() == null : this.getAccPrice().equals(other.getAccPrice()))
            && (this.getAccPack() == null ? other.getAccPack() == null : this.getAccPack().equals(other.getAccPack()))
            && (this.getAccLogistics() == null ? other.getAccLogistics() == null : this.getAccLogistics().equals(other.getAccLogistics()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getIsRelatedCar() == null ? other.getIsRelatedCar() == null : this.getIsRelatedCar().equals(other.getIsRelatedCar()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccId() == null) ? 0 : getAccId().hashCode());
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getAccName() == null) ? 0 : getAccName().hashCode());
        result = prime * result + ((getAccSpec() == null) ? 0 : getAccSpec().hashCode());
        result = prime * result + ((getAccUnit() == null) ? 0 : getAccUnit().hashCode());
        result = prime * result + ((getAccForshort() == null) ? 0 : getAccForshort().hashCode());
        result = prime * result + ((getAccCode() == null) ? 0 : getAccCode().hashCode());
        result = prime * result + ((getAccOem() == null) ? 0 : getAccOem().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getAccMainPlants() == null) ? 0 : getAccMainPlants().hashCode());
        result = prime * result + ((getAccScPrice() == null) ? 0 : getAccScPrice().hashCode());
        result = prime * result + ((getAccPrice() == null) ? 0 : getAccPrice().hashCode());
        result = prime * result + ((getAccPack() == null) ? 0 : getAccPack().hashCode());
        result = prime * result + ((getAccLogistics() == null) ? 0 : getAccLogistics().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getIsRelatedCar() == null) ? 0 : getIsRelatedCar().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}