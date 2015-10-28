package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccessoryParamValue implements Serializable {
    private static final long serialVersionUID = -3464930110419391924L;

    private Integer paramId;
    private Integer accId;
    private String paramName;
    private String paramValues;
    private String dataType;
    private String dataFormat;
    private Integer bolValue;
    private String strValue;
    private Integer intValue;
    private BigDecimal decValue;

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
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

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public Integer getBolValue() {
        return bolValue;
    }

    public void setBolValue(Integer bolValue) {
        this.bolValue = bolValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public BigDecimal getDecValue() {
        return decValue;
    }

    public void setDecValue(BigDecimal decValue) {
        this.decValue = decValue;
    }
}