package com.cnit.yoyo.goodsvirtualitems.dao;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.goodsvirtualitems.model.GoodsVirtualItems;


public interface GoodsVirtualItemsMapper {

	/** 
	 * @Description: 
	 * @param paraData
	 * @return			
	*/
	public List<GoodsVirtualItems> getGoodsVirtualItemsList(Map<String, Object> paraData);
}