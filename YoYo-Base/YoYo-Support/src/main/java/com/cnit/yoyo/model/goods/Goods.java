package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 商品
 * @detail <功能详细描述>
 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
 * @version 1.0.0
 */
public class Goods implements Serializable {
    private static final long serialVersionUID = 344035893875962242L;
    private Integer goodsId;
    private Integer catId;
    private Integer brandId;
    private Integer promptId;
    private Integer companyId;
    private Integer storeId;
    private String fbn;
    private String dbn;
    private String name;
    private String marketable;
    private BigDecimal score;
    private BigDecimal cost;
    private BigDecimal price;
    private BigDecimal mktPrice;
    private Integer store;
    private BigDecimal storeFreeze;
    private Integer notifyNum;
    private String downTime;
    private String upTime;
    private String lastModify;
    private Integer minBuy;
    private String unit;
    private BigDecimal weight;
    private String brief;
    private String goodsKind;
    private String goodsType;
    private String udfImg;
    private String imageDefaultId;
    private String thumbnailPic;
    private String smallPic;
    private String midPic;
    private String bigPic;
    private String storePlace;
    private String goodsCond;
    private String scoreSetting;
    private String nostoreSell;
    private String disabled;
    private Integer buyCount;
    private Integer buyWCount;
    private Integer buyMCount;
    private Integer viewWCount;
    private Integer viewMCount;
    private Integer rankCount;
    private Integer commentsCount;
    private Integer viewCount;
    private Integer favCount;
    private String tags;
    private String marketableAllow;
    private String keywords;
    private String marketableContent;
    private BigDecimal avgPoint;
    private String freightBear;
    private BigDecimal packageScale;
    private String packageUnit;
    private String packageUse;
    private Integer sOrder;
    private Integer dOrder;
    private Integer accId;
    private Integer carId;
    private Integer p1;  //商品库存预警值
    private Integer p20;
    private Integer p19;
    private Integer p18;
    private Integer p17;
    private Integer p16;
    private Integer p15;
    private Integer p14;
    private Integer p13;
    private Integer p12;
    private Integer p11;
    private Integer p10;
    private Integer p9;
    private Integer p8;
    private Integer p7;
    private Integer p6;
    private Integer p5;
    private Integer p4;
    private Integer p3;
    private Integer p2;
    private String p21;
    private String p50;
    // 商品详情
    private String intro;
    private String p49;
    private String p48;
    private String p47;
    private String p46;
    private String p45;
    private String p44;
    private String p43;
    private String p42;
    private String p41;
    private String p40;
    private String p39;
    private String p38;
    private String p37;
    private String p36;
    private String p35;
    private String p34;
    private String p33;
    private String p32;
    private String p31;
    private String p30;
    private String p29;
    private String p28;
    private String p27;
    private String p26;
    private String p25;
    private String p24;
    private String p23;
    private String p22;
    //显示
    private int checkCat;   //是否需要审核:0不需要，大于0的都需要
    private String identifier;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getPromptId() {
        return promptId;
    }

    public void setPromptId(Integer promptId) {
        this.promptId = promptId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getFbn() {
        return fbn;
    }

    public void setFbn(String fbn) {
        this.fbn = fbn;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketable() {
        return marketable;
    }

    public void setMarketable(String marketable) {
        this.marketable = marketable;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMktPrice() {
        return mktPrice;
    }

    public void setMktPrice(BigDecimal mktPrice) {
        this.mktPrice = mktPrice;
    }


    public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public BigDecimal getStoreFreeze() {
        return storeFreeze;
    }

    public void setStoreFreeze(BigDecimal storeFreeze) {
        this.storeFreeze = storeFreeze;
    }

    public Integer getNotifyNum() {
        return notifyNum;
    }

    public void setNotifyNum(Integer notifyNum) {
        this.notifyNum = notifyNum;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    public Integer getMinBuy() {
        return minBuy;
    }

    public void setMinBuy(Integer minBuy) {
        this.minBuy = minBuy;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getGoodsKind() {
        return goodsKind;
    }

    public void setGoodsKind(String goodsKind) {
        this.goodsKind = goodsKind;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getUdfImg() {
        return udfImg;
    }

    public void setUdfImg(String udfImg) {
        this.udfImg = udfImg;
    }

    public String getImageDefaultId() {
        return imageDefaultId;
    }

    public void setImageDefaultId(String imageDefaultId) {
        this.imageDefaultId = imageDefaultId;
    }

    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public String getMidPic() {
        return midPic;
    }

    public void setMidPic(String midPic) {
        this.midPic = midPic;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getStorePlace() {
        return storePlace;
    }

    public void setStorePlace(String storePlace) {
        this.storePlace = storePlace;
    }

    public String getGoodsCond() {
        return goodsCond;
    }

    public void setGoodsCond(String goodsCond) {
        this.goodsCond = goodsCond;
    }

    public String getScoreSetting() {
        return scoreSetting;
    }

    public void setScoreSetting(String scoreSetting) {
        this.scoreSetting = scoreSetting;
    }

    public String getNostoreSell() {
        return nostoreSell;
    }

    public void setNostoreSell(String nostoreSell) {
        this.nostoreSell = nostoreSell;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getBuyWCount() {
        return buyWCount;
    }

    public void setBuyWCount(Integer buyWCount) {
        this.buyWCount = buyWCount;
    }

    public Integer getBuyMCount() {
        return buyMCount;
    }

    public void setBuyMCount(Integer buyMCount) {
        this.buyMCount = buyMCount;
    }

    public Integer getViewWCount() {
        return viewWCount;
    }

    public void setViewWCount(Integer viewWCount) {
        this.viewWCount = viewWCount;
    }

    public Integer getViewMCount() {
        return viewMCount;
    }

    public void setViewMCount(Integer viewMCount) {
        this.viewMCount = viewMCount;
    }

    public Integer getRankCount() {
        return rankCount;
    }

    public void setRankCount(Integer rankCount) {
        this.rankCount = rankCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getFavCount() {
        return favCount;
    }

    public void setFavCount(Integer favCount) {
        this.favCount = favCount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMarketableAllow() {
        return marketableAllow;
    }

    public void setMarketableAllow(String marketableAllow) {
        this.marketableAllow = marketableAllow;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMarketableContent() {
        return marketableContent;
    }

    public void setMarketableContent(String marketableContent) {
        this.marketableContent = marketableContent;
    }

    public BigDecimal getAvgPoint() {
        return avgPoint;
    }

    public void setAvgPoint(BigDecimal avgPoint) {
        this.avgPoint = avgPoint;
    }

    public String getFreightBear() {
        return freightBear;
    }

    public void setFreightBear(String freightBear) {
        this.freightBear = freightBear;
    }

    public BigDecimal getPackageScale() {
        return packageScale;
    }

    public void setPackageScale(BigDecimal packageScale) {
        this.packageScale = packageScale;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public String getPackageUse() {
        return packageUse;
    }

    public void setPackageUse(String packageUse) {
        this.packageUse = packageUse;
    }

    public Integer getsOrder() {
        return sOrder;
    }

    public void setsOrder(Integer sOrder) {
        this.sOrder = sOrder;
    }

    public Integer getdOrder() {
        return dOrder;
    }

    public void setdOrder(Integer dOrder) {
        this.dOrder = dOrder;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getP1() {
        return p1;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }

    public Integer getP20() {
        return p20;
    }

    public void setP20(Integer p20) {
        this.p20 = p20;
    }

    public Integer getP19() {
        return p19;
    }

    public void setP19(Integer p19) {
        this.p19 = p19;
    }

    public Integer getP18() {
        return p18;
    }

    public void setP18(Integer p18) {
        this.p18 = p18;
    }

    public Integer getP17() {
        return p17;
    }

    public void setP17(Integer p17) {
        this.p17 = p17;
    }

    public Integer getP16() {
        return p16;
    }

    public void setP16(Integer p16) {
        this.p16 = p16;
    }

    public Integer getP15() {
        return p15;
    }

    public void setP15(Integer p15) {
        this.p15 = p15;
    }

    public Integer getP14() {
        return p14;
    }

    public void setP14(Integer p14) {
        this.p14 = p14;
    }

    public Integer getP13() {
        return p13;
    }

    public void setP13(Integer p13) {
        this.p13 = p13;
    }

    public Integer getP12() {
        return p12;
    }

    public void setP12(Integer p12) {
        this.p12 = p12;
    }

    public Integer getP11() {
        return p11;
    }

    public void setP11(Integer p11) {
        this.p11 = p11;
    }

    public Integer getP10() {
        return p10;
    }

    public void setP10(Integer p10) {
        this.p10 = p10;
    }

    public Integer getP9() {
        return p9;
    }

    public void setP9(Integer p9) {
        this.p9 = p9;
    }

    public Integer getP8() {
        return p8;
    }

    public void setP8(Integer p8) {
        this.p8 = p8;
    }

    public Integer getP7() {
        return p7;
    }

    public void setP7(Integer p7) {
        this.p7 = p7;
    }

    public Integer getP6() {
        return p6;
    }

    public void setP6(Integer p6) {
        this.p6 = p6;
    }

    public Integer getP5() {
        return p5;
    }

    public void setP5(Integer p5) {
        this.p5 = p5;
    }

    public Integer getP4() {
        return p4;
    }

    public void setP4(Integer p4) {
        this.p4 = p4;
    }

    public Integer getP3() {
        return p3;
    }

    public void setP3(Integer p3) {
        this.p3 = p3;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    public String getP21() {
        return p21;
    }

    public void setP21(String p21) {
        this.p21 = p21;
    }

    public String getP50() {
        return p50;
    }

    public void setP50(String p50) {
        this.p50 = p50;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getP49() {
        return p49;
    }

    public void setP49(String p49) {
        this.p49 = p49;
    }

    public String getP48() {
        return p48;
    }

    public void setP48(String p48) {
        this.p48 = p48;
    }

    public String getP47() {
        return p47;
    }

    public void setP47(String p47) {
        this.p47 = p47;
    }

    public String getP46() {
        return p46;
    }

    public void setP46(String p46) {
        this.p46 = p46;
    }

    public String getP45() {
        return p45;
    }

    public void setP45(String p45) {
        this.p45 = p45;
    }

    public String getP44() {
        return p44;
    }

    public void setP44(String p44) {
        this.p44 = p44;
    }

    public String getP43() {
        return p43;
    }

    public void setP43(String p43) {
        this.p43 = p43;
    }

    public String getP42() {
        return p42;
    }

    public void setP42(String p42) {
        this.p42 = p42;
    }

    public String getP41() {
        return p41;
    }

    public void setP41(String p41) {
        this.p41 = p41;
    }

    public String getP40() {
        return p40;
    }

    public void setP40(String p40) {
        this.p40 = p40;
    }

    public String getP39() {
        return p39;
    }

    public void setP39(String p39) {
        this.p39 = p39;
    }

    public String getP38() {
        return p38;
    }

    public void setP38(String p38) {
        this.p38 = p38;
    }

    public String getP37() {
        return p37;
    }

    public void setP37(String p37) {
        this.p37 = p37;
    }

    public String getP36() {
        return p36;
    }

    public void setP36(String p36) {
        this.p36 = p36;
    }

    public String getP35() {
        return p35;
    }

    public void setP35(String p35) {
        this.p35 = p35;
    }

    public String getP34() {
        return p34;
    }

    public void setP34(String p34) {
        this.p34 = p34;
    }

    public String getP33() {
        return p33;
    }

    public void setP33(String p33) {
        this.p33 = p33;
    }

    public String getP32() {
        return p32;
    }

    public void setP32(String p32) {
        this.p32 = p32;
    }

    public String getP31() {
        return p31;
    }

    public void setP31(String p31) {
        this.p31 = p31;
    }

    public String getP30() {
        return p30;
    }

    public void setP30(String p30) {
        this.p30 = p30;
    }

    public String getP29() {
        return p29;
    }

    public void setP29(String p29) {
        this.p29 = p29;
    }

    public String getP28() {
        return p28;
    }

    public void setP28(String p28) {
        this.p28 = p28;
    }

    public String getP27() {
        return p27;
    }

    public void setP27(String p27) {
        this.p27 = p27;
    }

    public String getP26() {
        return p26;
    }

    public void setP26(String p26) {
        this.p26 = p26;
    }

    public String getP25() {
        return p25;
    }

    public void setP25(String p25) {
        this.p25 = p25;
    }

    public String getP24() {
        return p24;
    }

    public void setP24(String p24) {
        this.p24 = p24;
    }

    public String getP23() {
        return p23;
    }

    public void setP23(String p23) {
        this.p23 = p23;
    }

    public String getP22() {
        return p22;
    }

    public void setP22(String p22) {
        this.p22 = p22;
    }

    public int getCheckCat() {
        return checkCat;
    }

    public void setCheckCat(int checkCat) {
        this.checkCat = checkCat;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}