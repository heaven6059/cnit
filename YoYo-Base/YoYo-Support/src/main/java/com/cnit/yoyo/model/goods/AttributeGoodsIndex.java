package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class AttributeGoodsIndex extends AttributeGoodsIndexKey implements Serializable {

    private Integer attrValueId;
    
    private String attrValue; //属性值
    private static final long serialVersionUID = 1L;

    public String getAttrValue() {
        return attrValue;
    }

    public Integer getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }
    
    
}