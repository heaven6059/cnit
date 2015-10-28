package com.cnit.yoyo.dao.shop;

import java.util.List;

import com.cnit.yoyo.model.shop.ShopIndexPush;
import com.cnit.yoyo.model.shop.ShopIndexPushWithBLOBs;
import com.cnit.yoyo.model.shop.dto.ShopIndexQryDTO;

public interface ShopIndexPushMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopIndexPushWithBLOBs record);

    Long insertSelective(ShopIndexPushWithBLOBs record);

    ShopIndexPushWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopIndexPushWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ShopIndexPushWithBLOBs record);

    int updateByPrimaryKey(ShopIndexPush record);
    
    List<ShopIndexPushWithBLOBs> selectShopIndexByCompanyId(ShopIndexQryDTO param);
    
    ShopIndexPushWithBLOBs selectShopIndexById(ShopIndexQryDTO param);
}