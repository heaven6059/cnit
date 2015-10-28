/**
 * 文 件 名   :  specQryDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-18 下午4:03:32
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.dto.spec;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * @description 规格查询数据传输对象
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class SpecQryDTO extends BaseQryDTO {
    private Integer specId;
    private String specName;
    private String specMemo;
    private String specAlias;
    private String specShowType;
    private String specSelectMode;
    private Integer orderNum;
    private String disabled;
    private Integer specValueId;
    private String specValueName;
    private String specValueAlias;
    private String specValue;

    public Integer getSpecId() {
        return specId;
    }

    public String getSpecName() {
        return specName;
    }

    public String getSpecMemo() {
        return specMemo;
    }

    public String getSpecAlias() {
        return specAlias;
    }

    public String getSpecShowType() {
        return specShowType;
    }

    public String getSpecSelectMode() {
        return specSelectMode;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public void setSpecMemo(String specMemo) {
        this.specMemo = specMemo;
    }

    public void setSpecAlias(String specAlias) {
        this.specAlias = specAlias;
    }

    public void setSpecShowType(String specShowType) {
        this.specShowType = specShowType;
    }

    public void setSpecSelectMode(String specSelectMode) {
        this.specSelectMode = specSelectMode;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public Integer getSpecValueId() {
        return specValueId;
    }

    public String getSpecValueName() {
        return specValueName;
    }

    public String getSpecValueAlias() {
        return specValueAlias;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValueId(Integer specValueId) {
        this.specValueId = specValueId;
    }

    public void setSpecValueName(String specValueName) {
        this.specValueName = specValueName;
    }

    public void setSpecValueAlias(String specValueAlias) {
        this.specValueAlias = specValueAlias;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }
}
