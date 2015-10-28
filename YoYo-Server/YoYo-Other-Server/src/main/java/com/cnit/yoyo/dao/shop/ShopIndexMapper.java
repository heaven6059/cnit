package com.cnit.yoyo.dao.shop;

import java.util.List;

import com.cnit.yoyo.model.goods.Goods;
import com.cnit.yoyo.model.shop.dto.ShopIndexQryDTO;

public interface ShopIndexMapper {
	List<Goods> selectShopIndexHotSell(ShopIndexQryDTO indexQryDTO);
	
	List<Goods> selectShopIndexCategoryGoods(ShopIndexQryDTO param);
	
	List<Goods> selectShopIndexHotCommentGoods(ShopIndexQryDTO param);
	
}
