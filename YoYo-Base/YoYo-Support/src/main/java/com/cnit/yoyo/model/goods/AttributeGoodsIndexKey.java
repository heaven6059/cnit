package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class AttributeGoodsIndexKey implements Serializable {
    private Integer goodsId;
    private Integer catId;
    private Integer attrId;
    private static final long serialVersionUID = 1L;
    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getCatId() {
        return catId;
    }
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getAttrId() {
        return attrId;
    }
    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsId=").append(goodsId);
        sb.append(", catId=").append(catId);
        sb.append(", attrId=").append(attrId);
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
        AttributeGoodsIndexKey other = (AttributeGoodsIndexKey) that;
        return (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
                && (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
                && (this.getAttrId() == null ? other.getAttrId() == null : this.getAttrId().equals(
                        other.getAttrId()));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
        return result;
    }
}