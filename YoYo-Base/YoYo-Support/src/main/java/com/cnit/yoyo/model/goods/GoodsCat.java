package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsCat implements Serializable {
    private Integer catId;

    private String catName;

    private Integer parentCatId;

    private BigDecimal profitPoint;

    private Integer orderNum;

    private Integer hitCount;

    private Integer goodsCount;

    private Integer childCount;

    private String catLogo;

    private String disabled;

    private String hidden;

    private String lastModify;

    private String title;

    private String metaKeywords;

    private String metaDescription;

    private String identifier;

    private static final long serialVersionUID = 1L;
    
    //保养配件需要，用于展示是否已经有保养配件
    private String accessoryName; //保养配件名称
    private String accessoryIds;  //保养配件id集合
    
    private int companyId;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(Integer parentCatId) {
        this.parentCatId = parentCatId;
    }

    public BigDecimal getProfitPoint() {
        return profitPoint;
    }

    public void setProfitPoint(BigDecimal profitPoint) {
        this.profitPoint = profitPoint;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public String getCatLogo() {
        return catLogo;
    }

    public void setCatLogo(String catLogo) {
        this.catLogo = catLogo;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catId=").append(catId);
        sb.append(", catName=").append(catName);
        sb.append(", parentCatId=").append(parentCatId);
        sb.append(", profitPoint=").append(profitPoint);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", hitCount=").append(hitCount);
        sb.append(", goodsCount=").append(goodsCount);
        sb.append(", childCount=").append(childCount);
        sb.append(", catLogo=").append(catLogo);
        sb.append(", disabled=").append(disabled);
        sb.append(", hidden=").append(hidden);
        sb.append(", lastModify=").append(lastModify);
        sb.append(", title=").append(title);
        sb.append(", metaKeywords=").append(metaKeywords);
        sb.append(", metaDescription=").append(metaDescription);
        sb.append(", identifier=").append(identifier);
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
        GoodsCat other = (GoodsCat) that;
        return (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
            && (this.getCatName() == null ? other.getCatName() == null : this.getCatName().equals(other.getCatName()))
            && (this.getParentCatId() == null ? other.getParentCatId() == null : this.getParentCatId().equals(other.getParentCatId()))
            && (this.getProfitPoint() == null ? other.getProfitPoint() == null : this.getProfitPoint().equals(other.getProfitPoint()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
            && (this.getHitCount() == null ? other.getHitCount() == null : this.getHitCount().equals(other.getHitCount()))
            && (this.getGoodsCount() == null ? other.getGoodsCount() == null : this.getGoodsCount().equals(other.getGoodsCount()))
            && (this.getChildCount() == null ? other.getChildCount() == null : this.getChildCount().equals(other.getChildCount()))
            && (this.getCatLogo() == null ? other.getCatLogo() == null : this.getCatLogo().equals(other.getCatLogo()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getHidden() == null ? other.getHidden() == null : this.getHidden().equals(other.getHidden()))
            && (this.getLastModify() == null ? other.getLastModify() == null : this.getLastModify().equals(other.getLastModify()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getMetaKeywords() == null ? other.getMetaKeywords() == null : this.getMetaKeywords().equals(other.getMetaKeywords()))
            && (this.getMetaDescription() == null ? other.getMetaDescription() == null : this.getMetaDescription().equals(other.getMetaDescription()))
            && (this.getIdentifier() == null ? other.getIdentifier() == null : this.getIdentifier().equals(other.getIdentifier()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getCatName() == null) ? 0 : getCatName().hashCode());
        result = prime * result + ((getParentCatId() == null) ? 0 : getParentCatId().hashCode());
        result = prime * result + ((getProfitPoint() == null) ? 0 : getProfitPoint().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getHitCount() == null) ? 0 : getHitCount().hashCode());
        result = prime * result + ((getGoodsCount() == null) ? 0 : getGoodsCount().hashCode());
        result = prime * result + ((getChildCount() == null) ? 0 : getChildCount().hashCode());
        result = prime * result + ((getCatLogo() == null) ? 0 : getCatLogo().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getHidden() == null) ? 0 : getHidden().hashCode());
        result = prime * result + ((getLastModify() == null) ? 0 : getLastModify().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getMetaKeywords() == null) ? 0 : getMetaKeywords().hashCode());
        result = prime * result + ((getMetaDescription() == null) ? 0 : getMetaDescription().hashCode());
        result = prime * result + ((getIdentifier() == null) ? 0 : getIdentifier().hashCode());
        return result;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getAccessoryIds() {
        return accessoryIds;
    }

    public void setAccessoryIds(String accessoryIds) {
        this.accessoryIds = accessoryIds;
    }

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

   
	
}