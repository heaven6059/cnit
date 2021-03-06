package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.util.List;

public class GoodsVirtualCat implements Serializable {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.CAT_ID
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private Integer catId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.CAT_NAME
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private String catName;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.PARENT_CAT_ID
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private Integer parentCatId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.ORDER_NUM
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private Integer orderNum;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.HIT_COUNT
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private Integer hitCount;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.CHILD_COUNT
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private Integer childCount;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.DISABLED
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private String disabled;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.HIDDEN
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private String hidden;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.TITLE
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private String title;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.META_KEYWORDS
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private String metaKeywords;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_virtual_cat.META_DESCRIPTION
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private String metaDescription;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_goods_virtual_cat
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    private static final long serialVersionUID = 1L;

    private List<GoodsVirtualCat> childCateList;
    
    private String icon;
    
    
    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.CAT_ID
     * @return  the value of t_goods_virtual_cat.CAT_ID
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.CAT_ID
     * @param catId  the value for t_goods_virtual_cat.CAT_ID
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.CAT_NAME
     * @return  the value of t_goods_virtual_cat.CAT_NAME
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public String getCatName() {
        return catName;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.CAT_NAME
     * @param catName  the value for t_goods_virtual_cat.CAT_NAME
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.PARENT_CAT_ID
     * @return  the value of t_goods_virtual_cat.PARENT_CAT_ID
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public Integer getParentCatId() {
        return parentCatId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.PARENT_CAT_ID
     * @param parentCatId  the value for t_goods_virtual_cat.PARENT_CAT_ID
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setParentCatId(Integer parentCatId) {
        this.parentCatId = parentCatId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.ORDER_NUM
     * @return  the value of t_goods_virtual_cat.ORDER_NUM
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.ORDER_NUM
     * @param orderNum  the value for t_goods_virtual_cat.ORDER_NUM
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.HIT_COUNT
     * @return  the value of t_goods_virtual_cat.HIT_COUNT
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public Integer getHitCount() {
        return hitCount;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.HIT_COUNT
     * @param hitCount  the value for t_goods_virtual_cat.HIT_COUNT
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.CHILD_COUNT
     * @return  the value of t_goods_virtual_cat.CHILD_COUNT
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public Integer getChildCount() {
        return childCount;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.CHILD_COUNT
     * @param childCount  the value for t_goods_virtual_cat.CHILD_COUNT
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.DISABLED
     * @return  the value of t_goods_virtual_cat.DISABLED
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public String getDisabled() {
        return disabled;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.DISABLED
     * @param disabled  the value for t_goods_virtual_cat.DISABLED
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.HIDDEN
     * @return  the value of t_goods_virtual_cat.HIDDEN
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public String getHidden() {
        return hidden;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.HIDDEN
     * @param hidden  the value for t_goods_virtual_cat.HIDDEN
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.TITLE
     * @return  the value of t_goods_virtual_cat.TITLE
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.TITLE
     * @param title  the value for t_goods_virtual_cat.TITLE
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.META_KEYWORDS
     * @return  the value of t_goods_virtual_cat.META_KEYWORDS
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public String getMetaKeywords() {
        return metaKeywords;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.META_KEYWORDS
     * @param metaKeywords  the value for t_goods_virtual_cat.META_KEYWORDS
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_virtual_cat.META_DESCRIPTION
     * @return  the value of t_goods_virtual_cat.META_DESCRIPTION
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public String getMetaDescription() {
        return metaDescription;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_virtual_cat.META_DESCRIPTION
     * @param metaDescription  the value for t_goods_virtual_cat.META_DESCRIPTION
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_virtual_cat
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catId=").append(catId);
        sb.append(", catName=").append(catName);
        sb.append(", parentCatId=").append(parentCatId);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", hitCount=").append(hitCount);
        sb.append(", childCount=").append(childCount);
        sb.append(", disabled=").append(disabled);
        sb.append(", hidden=").append(hidden);
        sb.append(", title=").append(title);
        sb.append(", metaKeywords=").append(metaKeywords);
        sb.append(", metaDescription=").append(metaDescription);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_virtual_cat
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
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
        GoodsVirtualCat other = (GoodsVirtualCat) that;
        return (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
                && (this.getCatName() == null ? other.getCatName() == null : this.getCatName().equals(
                        other.getCatName()))
                && (this.getParentCatId() == null ? other.getParentCatId() == null : this.getParentCatId().equals(
                        other.getParentCatId()))
                && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(
                        other.getOrderNum()))
                && (this.getHitCount() == null ? other.getHitCount() == null : this.getHitCount().equals(
                        other.getHitCount()))
                && (this.getChildCount() == null ? other.getChildCount() == null : this.getChildCount().equals(
                        other.getChildCount()))
                && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(
                        other.getDisabled()))
                && (this.getHidden() == null ? other.getHidden() == null : this.getHidden().equals(other.getHidden()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getMetaKeywords() == null ? other.getMetaKeywords() == null : this.getMetaKeywords().equals(
                        other.getMetaKeywords()))
                && (this.getMetaDescription() == null ? other.getMetaDescription() == null : this.getMetaDescription()
                        .equals(other.getMetaDescription()));
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_virtual_cat
     * @mbggenerated  Thu Mar 26 19:31:15 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getCatName() == null) ? 0 : getCatName().hashCode());
        result = prime * result + ((getParentCatId() == null) ? 0 : getParentCatId().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getHitCount() == null) ? 0 : getHitCount().hashCode());
        result = prime * result + ((getChildCount() == null) ? 0 : getChildCount().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getHidden() == null) ? 0 : getHidden().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getMetaKeywords() == null) ? 0 : getMetaKeywords().hashCode());
        result = prime * result + ((getMetaDescription() == null) ? 0 : getMetaDescription().hashCode());
        return result;
    }

	public List<GoodsVirtualCat> getChildCateList() {
		return childCateList;
	}

	public void setChildCateList(List<GoodsVirtualCat> childCateList) {
		this.childCateList = childCateList;
	}
    
}