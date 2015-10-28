package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class GoodsWithBLOBs extends Goods implements Serializable {
    private static final long serialVersionUID = 8130815572920363218L;
//    private String intro; // 商品详情
    private String specDesc; // 货品规格序列化
    private String attrDesc; // 商品属性序列化
    private String countStat; // 统计数据序列化
    private String brandName;

//    public String getIntro() {
//        return intro;
//    }
//
//    public void setIntro(String intro) {
//        this.intro = intro;
//    }

    public String getSpecDesc() {
        return specDesc;
    }

    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }

    public String getAttrDesc() {
        return attrDesc;
    }

    public void setAttrDesc(String attrDesc) {
        this.attrDesc = attrDesc;
    }

    public String getCountStat() {
        return countStat;
    }

    public void setCountStat(String countStat) {
        this.countStat = countStat;
    }

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}