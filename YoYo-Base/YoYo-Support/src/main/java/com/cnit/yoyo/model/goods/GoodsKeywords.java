package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: GoodsKeywords
 * @Description: 商品关键字
 * @author xiaox
 * @date 2015-4-14 下午2:43:41
 */
public class GoodsKeywords extends GoodsKeywordsKey implements Serializable {
    private static final long serialVersionUID = -5586452494523922597L;
    private String refer;
    private String resType;
    private Date lastModify;

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

}