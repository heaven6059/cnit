package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class BrandAutohome implements Serializable {
    private Integer brandId;

    private String autohomeId;

    private static final long serialVersionUID = 1L;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getAutohomeId() {
        return autohomeId;
    }

    public void setAutohomeId(String autohomeId) {
        this.autohomeId = autohomeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", brandId=").append(brandId);
        sb.append(", autohomeId=").append(autohomeId);
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
        BrandAutohome other = (BrandAutohome) that;
        return (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getAutohomeId() == null ? other.getAutohomeId() == null : this.getAutohomeId().equals(other.getAutohomeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getAutohomeId() == null) ? 0 : getAutohomeId().hashCode());
        return result;
    }
}