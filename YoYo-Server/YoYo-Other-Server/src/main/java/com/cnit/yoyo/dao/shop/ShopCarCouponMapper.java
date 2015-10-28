package com.cnit.yoyo.dao.shop;

import java.util.List;

import com.cnit.yoyo.model.shop.ShopCarCoupon;
import com.cnit.yoyo.model.shop.dto.ShopCarCouponDTO;

public interface ShopCarCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopCarCoupon record);

    int insertSelective(ShopCarCoupon record);

    ShopCarCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopCarCoupon record);

    int updateByPrimaryKey(ShopCarCoupon record);
    
    List<ShopCarCouponDTO> selectShopCarCoupon(ShopCarCoupon carCoupon);
}