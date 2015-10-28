/**
 * 文 件 名   :  GoodsDetailInfoDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月14日 下午2:15:12
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
public class GoodsDetailInfoDTO implements Serializable {
    private static final long serialVersionUID = -6094971082819270082L;

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

    private BigDecimal store;

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

    private String tags;

    private String marketableAllow;

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

    private String intro; // 商品详情

    private String specDesc; // 货品规格序列化

    private String attrDesc; // 商品属性序列化

    private String keywords;

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

    public BigDecimal getStore() {
        return store;
    }

    public void setStore(BigDecimal store) {
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
