package com.cnit.yoyo.rmi.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.ShopFocusAdMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.shop.ShopFocusAd;

/**  
* @Title: ShopFocusAdService.java
* @Package com.cnit.yoyo.rmi.shop.service
* @Description: 店铺焦点图广告
* @Author 王鹏
* @date 2015-6-11 上午10:32:16
* @version V1.0  
*/ 
@Service("shopFocusAdService")
public class ShopFocusAdService {
	@Autowired
	private ShopFocusAdMapper shoFocusAdMapper;
	
	/**
	  * @description <b>查询店铺焦点图</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findShopFocusAd(Object object){
		HeadObject head=new HeadObject();
		List<ShopFocusAd> listFocusAds=null;
		try{
			listFocusAds = shoFocusAdMapper.selectShopFocusAd((ShopFocusAd) object);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,listFocusAds);
	}
	
	/**
	  * @description <b>修改或保存焦点图</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-26
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object saveOrUpdateFocusAd(Object object){
		HeadObject head=new HeadObject();
		List<ShopFocusAd> shopFocusAds=(List<ShopFocusAd>) object;
		try{
			for (ShopFocusAd shopFocusAd : shopFocusAds) {
				if(null!=shopFocusAd.getId()){
					shoFocusAdMapper.updateByPrimaryKeySelective(shopFocusAd);
				}else{
					shoFocusAdMapper.insertSelective(shopFocusAd);
				}
			}
			head.setRetCode(ErrorCode.SUCCESS);			
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	/**
	  * @description <b>删除焦点图</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-26
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object deleteFocusAd(Object object){
		HeadObject head=new HeadObject();
		try{
			shoFocusAdMapper.deleteByPrimaryKey((Long) object);
			head.setRetCode(ErrorCode.SUCCESS);			
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	/**
	  * @description <b>修改焦点图启用状态</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-26
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object updateFocusAdEnable(Object object){
		HeadObject head=new HeadObject();
		try{
			shoFocusAdMapper.updateByPrimaryKeySelective((ShopFocusAd) object);
			head.setRetCode(ErrorCode.SUCCESS);			
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
}
