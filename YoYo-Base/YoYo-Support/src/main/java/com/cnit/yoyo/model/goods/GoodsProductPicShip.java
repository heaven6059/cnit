package com.cnit.yoyo.model.goods;

import java.io.Serializable;
/**
 * 
 * @ClassName: GoodsProductPicShip 
 * @Description:商品，货品，图片关系 
 * @author xiaox
 * @date 2015-4-14 下午2:43:12
 */
public class GoodsProductPicShip implements Serializable {
    private static final long serialVersionUID = -7379016104006890406L;
    private Long id;
    private Long goodsId;
    private Long productId;
    private Long pictureId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }
}