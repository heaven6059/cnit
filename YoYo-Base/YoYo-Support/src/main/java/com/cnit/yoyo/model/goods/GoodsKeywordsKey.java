package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class GoodsKeywordsKey implements Serializable {
    private static final long serialVersionUID = 6956629465718904959L;
    private Long goodsId;
    private String keyword;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}