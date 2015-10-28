package com.cnit.yoyo.rmi.mpindex.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.mpindex.MpIndexMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;

/**   
 * @Description: 管理平台首页数据Service
 * @author  王鹏
 * @date 2015-8-12 下午1:01:51
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Service("mpIndexService")
public class MpIndexService {

	@Autowired
	private MpIndexMapper mpIndexMapper;
	
	/**
	 * @description <b>获取统计信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-12
	 * @param data
	 * @return
	 * Object
	*/
	public Object findStatisticsData(Object data){
		HeadObject headObject = new HeadObject();		
		Map<String, Object> param=(Map<String, Object>) data;
		try{
			param.put("AccountInfo", mpIndexMapper.selectAccountInfo(param));
			param.put("OrderTips",mpIndexMapper.selectOrderTips(param));
			param.put("ShopSellsInfo",mpIndexMapper.selectShopSellsInfo(param));
			param.put("GoodsStatistics",mpIndexMapper.selectGoodsStatistics(param));
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(headObject, param);
	}
}
