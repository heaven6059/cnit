package com.cnit.yoyo.rmi.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.ShopCarCouponMapper;
import com.cnit.yoyo.dao.shop.ShopFocusAdMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.shop.ShopCarCoupon;
import com.cnit.yoyo.model.shop.ShopFocusAd;
import com.cnit.yoyo.model.shop.dto.ShopCarCouponDTO;

/**  
* @Title: ShopCarCouponService.java
* @Package com.cnit.yoyo.rmi.shop.service
* @Description: 店铺首页新车优惠
* @Author 王鹏
* @date 2015-6-11 下午3:40:49
* @version V1.0  
*/ 
@Service("shopCarCouponService")
public class ShopCarCouponService {
	@Autowired
	private ShopCarCouponMapper shopCarCouponMapper;
	
	/**
	  * @description <b>收</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findShopCarCoupon(Object object){
		HeadObject head=new HeadObject();
		List<ShopCarCouponDTO> shopCarCouponDTOs=null;
		try{
			shopCarCouponDTOs = shopCarCouponMapper.selectShopCarCoupon((ShopCarCoupon) object);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,shopCarCouponDTOs);
	}
}
