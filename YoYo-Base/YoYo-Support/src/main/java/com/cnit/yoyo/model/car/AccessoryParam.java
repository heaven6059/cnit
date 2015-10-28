package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class AccessoryParam implements Serializable {

    private Integer paramId;
    private String paramName;
    private String paramValues;
    private String dataType;
    private Integer orderNum;
    private String dataFormat;
    private Integer catalogId;
    private static final long serialVersionUID = 1L;

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValues() {
        return paramValues;
    }

    public void setParamValues(String paramValues) {
        this.paramValues = paramValues;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paramId=").append(paramId);
        sb.append(", paramName=").append(paramName);
        sb.append(", paramValues=").append(paramValues);
        sb.append(", dataType=").append(dataType);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", dataFormat=").append(dataFormat);
        sb.append(", catalogId=").append(catalogId);
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
        AccessoryParam other = (AccessoryParam) that;
        return (this.getParamId() == null ? other.getParamId() == null : this.getParamId().equals(other.getParamId()))
                && (this.getParamName() == null ? other.getParamName() == null : this.getParamName().equals(
                        other.getParamName()))
                && (this.getParamValues() == null ? other.getParamValues() == null : this.getParamValues().equals(
                        other.getParamValues()))
                && (this.getDataType() == null ? other.getDataType() == null : this.getDataType().equals(
                        other.getDataType()))
                && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(
                        other.getOrderNum()))
                && (this.getDataFormat() == null ? other.getDataFormat() == null : this.getDataFormat().equals(
                        other.getDataFormat()))
                && (this.getCatalogId() == null ? other.getCatalogId() == null : this.getCatalogId().equals(
                        other.getCatalogId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getParamId() == null) ? 0 : getParamId().hashCode());
        result = prime * result + ((getParamName() == null) ? 0 : getParamName().hashCode());
        result = prime * result + ((getParamValues() == null) ? 0 : getParamValues().hashCode());
        result = prime * result + ((getDataType() == null) ? 0 : getDataType().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getDataFormat() == null) ? 0 : getDataFormat().hashCode());
        result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
        return result;
    }
}