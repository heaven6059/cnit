package com.cnit.yoyo.model.goods;

import java.io.Serializable;

/**
 * @description 相关商品key
 * @detail <功能详细描述>
 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
 * @version 1.0.0
 */
public class GoodsRelatedKey implements Serializable {
    private static final long serialVersionUID = -8619573592789899257L;

    private Long goods1;

    private Long goods2;

    public Long getGoods1() {
        return goods1;
    }

    public void setGoods1(Long goods1) {
        this.goods1 = goods1;
    }

    public Long getGoods2() {
        return goods2;
    }

    public void setGoods2(Long goods2) {
        this.goods2 = goods2;
    }

}