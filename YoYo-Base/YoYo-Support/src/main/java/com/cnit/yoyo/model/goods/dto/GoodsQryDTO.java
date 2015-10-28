/**
 * 文 件 名   :  GoodsQryDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午4:05:25
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
public class GoodsQryDTO extends BaseQryDTO implements Serializable {
    private static final long serialVersionUID = 3972668715243253431L;
    private String marketableQ;  //是否上架0：下架 1：上架
    private String disabledQ;    //是否删除 
    private Long companyId;
    private Long storeId;        //分店id
    private Integer storeNum;    //库存数量
    /**卖家中心》出售中的商品*/
    private BigDecimal minPrice; //最小价格
    private BigDecimal maxPrice; //最大价格
    private Integer minBuyCount; //最小销量
    private Integer maxBuyCount; //最大销量
    private String name;         //商品名称
    private String fbn;          //编号
    private Integer catId;       //分类id
    private Long pictureId;     //图片id
    private String picUrl;     //图片地址
    
    private int isChecking; //查询是否在审核中与审核不同的
    private int checkCat;   //是否需要审核:0不需要，大于0的都需要
    private String identifier;
    private String [] childArr; //子节点集合
    
    
    
    public String getMarketableQ() {
        return marketableQ;
    }

    public void setMarketableQ(String marketableQ) {
        this.marketableQ = marketableQ;
    }

    public String getDisabledQ() {
        return disabledQ;
    }

    public void setDisabledQ(String disabledQ) {
        this.disabledQ = disabledQ;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinBuyCount() {
        return minBuyCount;
    }

    public void setMinBuyCount(Integer minBuyCount) {
        this.minBuyCount = minBuyCount;
    }

    public Integer getMaxBuyCount() {
        return maxBuyCount;
    }

    public void setMaxBuyCount(Integer maxBuyCount) {
        this.maxBuyCount = maxBuyCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFbn() {
        return fbn;
    }

    public void setFbn(String fbn) {
        this.fbn = fbn;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(Integer storeNum) {
        this.storeNum = storeNum;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getIsChecking() {
        return isChecking;
    }

    public void setIsChecking(int isChecking) {
        this.isChecking = isChecking;
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

    public String[] getChildArr() {
        return childArr;
    }

    public void setChildArr(String[] childArr) {
        this.childArr = childArr;
    }

   

  

}
