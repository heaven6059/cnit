package com.cnit.yoyo.rmi.good.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.GoodsTimePriceMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.GoodsTimePrice;

/**   
 * @Description: 商品时间区间价格Service
 * @author  王鹏
 * @date 2015年8月19日 上午10:27:51
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Service("goodsTimePriceService")
public class GoodsTimePriceService {
	@Autowired
	private GoodsTimePriceMapper goodsTimePriceMapper;
	
	/**
	 * @description <b>查询商品时间价格区间</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年8月19日
	 * @return
	 * Object
	*/
	public Object findGoodsTimePrice(Object object){
		HeadObject headObject=new HeadObject();
		GoodsTimePrice goodsTimePrice = null;
		try{
			goodsTimePrice = goodsTimePriceMapper.selectGoodsTimePrice((Map<String, Object>) object);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(headObject, goodsTimePrice);
	}
}
