
package com.cnit.yoyo.dto.spec;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.goods.SpecValues;

/**
 * 
 * @ClassName: SpecDTO 
 * @Description: 发布商品时，获取规格的值的数据传输对象
 * @author xiaox
 * @date 2015-4-8 下午5:04:41
 */
public class SpecDTO implements Serializable {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer specId;
    private String specName;
    private String specMemo;
    private String specAlias;
    private String specShowType;
    private String specSelectMode;
    private Integer orderNum;
    private String disabled;
    private String limitGoods;  //分店是否限制发布商品：0不限制，1：限制
    
    private List<SpecValues>  values;  //规格对应的规格值

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

   

    public List<SpecValues> getValues() {
        return values;
    }

    public void setValues(List<SpecValues> values) {
        this.values = values;
    }

    public String getLimitGoods() {
        return limitGoods;
    }

    public void setLimitGoods(String limitGoods) {
        this.limitGoods = limitGoods;
    }

   
    
    
}
