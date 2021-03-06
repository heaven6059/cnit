package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class SpecGoodsIndex extends SpecGoodsIndexKey implements Serializable {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_spec_goods_index.SPEC_ID
     * @mbggenerated  Wed Apr 15 17:41:47 CST 2015
     */
    private Integer specId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_spec_goods_index
     * @mbggenerated  Wed Apr 15 17:41:47 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_spec_goods_index.SPEC_ID
     * @return  the value of t_spec_goods_index.SPEC_ID
     * @mbggenerated  Wed Apr 15 17:41:47 CST 2015
     */
    public Integer getSpecId() {
        return specId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_spec_goods_index.SPEC_ID
     * @param specId  the value for t_spec_goods_index.SPEC_ID
     * @mbggenerated  Wed Apr 15 17:41:47 CST 2015
     */
    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_goods_index
     * @mbggenerated  Wed Apr 15 17:41:47 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", specId=").append(specId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_goods_index
     * @mbggenerated  Wed Apr 15 17:41:47 CST 2015
     */
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
        SpecGoodsIndex other = (SpecGoodsIndex) that;
        return (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
                && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(
                        other.getProductId()))
                && (this.getSpecValueId() == null ? other.getSpecValueId() == null : this.getSpecValueId().equals(
                        other.getSpecValueId()))
                && (this.getSpecId() == null ? other.getSpecId() == null : this.getSpecId().equals(other.getSpecId()));
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_goods_index
     * @mbggenerated  Wed Apr 15 17:41:47 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getSpecValueId() == null) ? 0 : getSpecValueId().hashCode());
        result = prime * result + ((getSpecId() == null) ? 0 : getSpecId().hashCode());
        return result;
    }
}