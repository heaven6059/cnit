package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class AttributeValue implements Serializable {

    private Integer attrValueId;
    private String attrValue;
    private Integer attrId;
    private Integer catId;
    private String attrSearchValue;
    private Integer orderNum;
    private static final long serialVersionUID = 1L;
    public Integer getAttrValueId() {
        return attrValueId;
    }
    public void setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
    }
    public String getAttrValue() {
        return attrValue;
    }
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }
    public Integer getAttrId() {
        return attrId;
    }
    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
    public Integer getCatId() {
        return catId;
    }
    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    public String getAttrSearchValue() {
        return attrSearchValue;
    }
    public void setAttrSearchValue(String attrSearchValue) {
        this.attrSearchValue = attrSearchValue;
    }
    public Integer getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attrValueId=").append(attrValueId);
        sb.append(", attrValue=").append(attrValue);
        sb.append(", attrId=").append(attrId);
        sb.append(", catId=").append(catId);
        sb.append(", attrSearchValue=").append(attrSearchValue);
        sb.append(", orderNum=").append(orderNum);
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
        AttributeValue other = (AttributeValue) that;
        return (this.getAttrValueId() == null ? other.getAttrValueId() == null : this.getAttrValueId().equals(
                other.getAttrValueId()))
                && (this.getAttrValue() == null ? other.getAttrValue() == null : this.getAttrValue().equals(
                        other.getAttrValue()))
                && (this.getAttrId() == null ? other.getAttrId() == null : this.getAttrId().equals(other.getAttrId()))
                && (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
                && (this.getAttrSearchValue() == null ? other.getAttrSearchValue() == null : this.getAttrSearchValue()
                        .equals(other.getAttrSearchValue()))
                && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(
                        other.getOrderNum()));
    } 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAttrValueId() == null) ? 0 : getAttrValueId().hashCode());
        result = prime * result + ((getAttrValue() == null) ? 0 : getAttrValue().hashCode());
        result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getAttrSearchValue() == null) ? 0 : getAttrSearchValue().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        return result;
    }
}