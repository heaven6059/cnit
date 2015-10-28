package com.cnit.yoyo.dao.mpindex;

import java.util.Map;

import com.cnit.yoyo.model.goods.dto.GoodsStatisticsDTO;
import com.cnit.yoyo.model.member.dto.PamAccountInfoDTO;
import com.cnit.yoyo.model.trade.dto.OrderSellsInfoDTO;
import com.cnit.yoyo.model.trade.dto.OrderTipsDTO;

public interface MpIndexMapper {
	OrderSellsInfoDTO selectShopSellsInfo(Map<String, Object> param);
	
	PamAccountInfoDTO selectAccountInfo(Map<String, Object> param);
	
	OrderTipsDTO selectOrderTips(Map<String, Object> param);
	
	GoodsStatisticsDTO selectGoodsStatistics(Map<String, Object> param);
}
