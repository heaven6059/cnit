package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.util.List;

public class Attribute implements Serializable {

    private Integer attrId;
    private String attrName;
    private String attrAlias;
    private String attrValueType;
    private String attrIsmultiple;
    private String attrInputType;
    private String attrShow;
    private Integer orderNum;
    private Integer catId;
    private String attrValues;
    private List<String> attrValueList;
    private List<AttributeValue> attrValueLists;
    
    private static final long serialVersionUID = 1L;

    
    public Integer getAttrId() {
        return attrId;
    }
    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
    public List<String> getAttrValueList() {
		return attrValueList;
	}
	public void setAttrValueList(List<String> attrValueList) {
		this.attrValueList = attrValueList;
	}
	public String getAttrName() {
        return attrName;
    }
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
    public String getAttrAlias() {
        return attrAlias;
    }
    public void setAttrAlias(String attrAlias) {
        this.attrAlias = attrAlias;
    }
    public String getAttrValueType() {
        return attrValueType;
    }
    public void setAttrValueType(String attrValueType) {
        this.attrValueType = attrValueType;
    }
    public String getAttrIsmultiple() {
        return attrIsmultiple;
    }
    public void setAttrIsmultiple(String attrIsmultiple) {
        this.attrIsmultiple = attrIsmultiple;
    }
    public String getAttrInputType() {
        return attrInputType;
    }
    public void setAttrInputType(String attrInputType) {
        this.attrInputType = attrInputType;
    }
    public String getAttrShow() {
        return attrShow;
    }
    public void setAttrShow(String attrShow) {
        this.attrShow = attrShow;
    }
    public Integer getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    public Integer getCatId() {
        return catId;
    }
    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    public String getAttrValues() {
        return attrValues;
    }
    public void setAttrValues(String attrValues) {
        this.attrValues = attrValues;
    }
    public List<AttributeValue> getAttrValueLists() {
		return attrValueLists;
	}
	public void setAttrValueLists(List<AttributeValue> attrValueLists) {
		this.attrValueLists = attrValueLists;
	}
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attrId=").append(attrId);
        sb.append(", attrName=").append(attrName);
        sb.append(", attrAlias=").append(attrAlias);
        sb.append(", attrValueType=").append(attrValueType);
        sb.append(", attrIsmultiple=").append(attrIsmultiple);
        sb.append(", attrInputType=").append(attrInputType);
        sb.append(", attrShow=").append(attrShow);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", catId=").append(catId);
        sb.append(", attrValues=").append(attrValues);
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
        Attribute other = (Attribute) that;
        return (this.getAttrId() == null ? other.getAttrId() == null : this.getAttrId().equals(other.getAttrId()))
                && (this.getAttrName() == null ? other.getAttrName() == null : this.getAttrName().equals(
                        other.getAttrName()))
                && (this.getAttrAlias() == null ? other.getAttrAlias() == null : this.getAttrAlias().equals(
                        other.getAttrAlias()))
                && (this.getAttrValueType() == null ? other.getAttrValueType() == null : this.getAttrValueType()
                        .equals(other.getAttrValueType()))
                && (this.getAttrIsmultiple() == null ? other.getAttrIsmultiple() == null : this.getAttrIsmultiple()
                        .equals(other.getAttrIsmultiple()))
                && (this.getAttrInputType() == null ? other.getAttrInputType() == null : this.getAttrInputType()
                        .equals(other.getAttrInputType()))
                && (this.getAttrShow() == null ? other.getAttrShow() == null : this.getAttrShow().equals(
                        other.getAttrShow()))
                && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(
                        other.getOrderNum()))
                && (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
                && (this.getAttrValues() == null ? other.getAttrValues() == null : this.getAttrValues().equals(
                        other.getAttrValues()));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
        result = prime * result + ((getAttrName() == null) ? 0 : getAttrName().hashCode());
        result = prime * result + ((getAttrAlias() == null) ? 0 : getAttrAlias().hashCode());
        result = prime * result + ((getAttrValueType() == null) ? 0 : getAttrValueType().hashCode());
        result = prime * result + ((getAttrIsmultiple() == null) ? 0 : getAttrIsmultiple().hashCode());
        result = prime * result + ((getAttrInputType() == null) ? 0 : getAttrInputType().hashCode());
        result = prime * result + ((getAttrShow() == null) ? 0 : getAttrShow().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getAttrValues() == null) ? 0 : getAttrValues().hashCode());
        return result;
    }
}