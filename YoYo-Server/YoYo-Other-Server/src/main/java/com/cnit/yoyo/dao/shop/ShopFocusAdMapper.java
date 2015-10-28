package com.cnit.yoyo.dao.shop;

import java.util.List;

import com.cnit.yoyo.model.shop.ShopFocusAd;

public interface ShopFocusAdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopFocusAd record);

    int insertSelective(ShopFocusAd record);

    ShopFocusAd selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopFocusAd record);

    int updateByPrimaryKey(ShopFocusAd record);
    
    List<ShopFocusAd> selectShopFocusAd(ShopFocusAd shopFocusAd);
}