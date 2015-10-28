package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class GoodsCatWithBLOBs extends GoodsCat implements Serializable {
    private String finder;

    private String tabs;

    private String addon;

    private static final long serialVersionUID = 1L;

    public String getFinder() {
        return finder;
    }

    public void setFinder(String finder) {
        this.finder = finder;
    }

    public String getTabs() {
        return tabs;
    }

    public void setTabs(String tabs) {
        this.tabs = tabs;
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", finder=").append(finder);
        sb.append(", tabs=").append(tabs);
        sb.append(", addon=").append(addon);
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
        GoodsCatWithBLOBs other = (GoodsCatWithBLOBs) that;
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
            && (this.getIdentifier() == null ? other.getIdentifier() == null : this.getIdentifier().equals(other.getIdentifier()))
            && (this.getFinder() == null ? other.getFinder() == null : this.getFinder().equals(other.getFinder()))
            && (this.getTabs() == null ? other.getTabs() == null : this.getTabs().equals(other.getTabs()))
            && (this.getAddon() == null ? other.getAddon() == null : this.getAddon().equals(other.getAddon()));
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
        result = prime * result + ((getFinder() == null) ? 0 : getFinder().hashCode());
        result = prime * result + ((getTabs() == null) ? 0 : getTabs().hashCode());
        result = prime * result + ((getAddon() == null) ? 0 : getAddon().hashCode());
        return result;
    }
}