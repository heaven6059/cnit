package com.cnit.yoyo.model.goods;

import java.io.Serializable;

/**
 * @ClassName: GoodsRelated
 * @Description: 相关商品
 * @author xiaox
 * @date 2015-4-14 下午2:42:50
 */
public class GoodsRelated extends GoodsRelatedKey implements Serializable {
    private static final long serialVersionUID = 3393802824576159542L;
    private String manual;
    private Integer rate;

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

}