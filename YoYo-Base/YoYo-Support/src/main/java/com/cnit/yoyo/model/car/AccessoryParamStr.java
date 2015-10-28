package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class AccessoryParamStr extends AccessoryParamStrKey implements Serializable {
    private Integer catalogId;

    private String value;

    private static final long serialVersionUID = 1L;

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catalogId=").append(catalogId);
        sb.append(", value=").append(value);
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
        AccessoryParamStr other = (AccessoryParamStr) that;
        return (this.getAccId() == null ? other.getAccId() == null : this.getAccId().equals(other.getAccId()))
            && (this.getParamId() == null ? other.getParamId() == null : this.getParamId().equals(other.getParamId()))
            && (this.getCatalogId() == null ? other.getCatalogId() == null : this.getCatalogId().equals(other.getCatalogId()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccId() == null) ? 0 : getAccId().hashCode());
        result = prime * result + ((getParamId() == null) ? 0 : getParamId().hashCode());
        result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        return result;
    }
}