package com.cnit.yoyo.rmi.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.ShopIndexMapper;
import com.cnit.yoyo.dao.shop.ShopIndexPushMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Goods;
import com.cnit.yoyo.model.shop.ShopIndexPushWithBLOBs;
import com.cnit.yoyo.model.shop.dto.ShopIndexPushDTO;
import com.cnit.yoyo.model.shop.dto.ShopIndexQryDTO;

/**  
* @Title: ShopIndexService.java
* @Package com.cnit.yoyo.rmi.shop.service
* @Description: 店铺首页Service
* @Author 王鹏
* @date 2015-6-11 下午5:46:29
* @version V1.0  
*/ 
@Service("shopIndexService")
public class ShopIndexService {
	@Autowired
	private ShopIndexMapper shopIndexMapper;
	
	@Autowired
	private ShopIndexPushMapper shopIndexPushMapper;
	
	public static final Logger log = LoggerFactory.getLogger(ShopIndexService.class);
	
	/**
	  * @description <b>获取商家推送列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-12
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findShopIndexPushList(Object object){
		HeadObject head=new HeadObject();
		List<ShopIndexPushWithBLOBs> list= null;
		try{
			list=shopIndexPushMapper.selectShopIndexByCompanyId((ShopIndexQryDTO) object);
			for (ShopIndexPushWithBLOBs shopIndexPush : list) {
				buildShopIndexJSON(shopIndexPush);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,list);
	}
	
	/**
	  * @description <b>获取商家推送列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-12
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findShopIndexPushById(Object object){
		HeadObject head=new HeadObject();
		ShopIndexPushWithBLOBs shopIndexPush= null;
		try{
			shopIndexPush=shopIndexPushMapper.selectShopIndexById((ShopIndexQryDTO) object);
			buildShopIndexJSON(shopIndexPush);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,shopIndexPush);
	}
	
	public Object updaetShopIndexPush(Object record){
		HeadObject head=new HeadObject();
		try{
			shopIndexPushMapper.updateByPrimaryKeySelective((ShopIndexPushWithBLOBs) record);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	public Object deleteShopIndexPush(Object object){
		HeadObject head=new HeadObject();
		try{
			shopIndexPushMapper.deleteByPrimaryKey((Long) object);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	
	public Object saveOrUpdateShopIndexPush(Object object){
		HeadObject head=new HeadObject();
		ShopIndexPushWithBLOBs record=(ShopIndexPushWithBLOBs)object;
		try{
			record.setLastModify(new Date());
			if(null!=record.getId()){
				shopIndexPushMapper.updateByPrimaryKeySelective(record);
			}else{
				shopIndexPushMapper.insertSelective(record);				
			}
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,record);
	}
	
	/**
	  * @description <b>店铺热销查询</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findShopHotSell(Object object){
		HeadObject head=new HeadObject();
		List<Goods> shopCarCouponDTOs=null;
		try{
			shopCarCouponDTOs = shopIndexMapper.selectShopIndexHotSell((ShopIndexQryDTO)object);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,shopCarCouponDTOs);
	}
	
	/**
	  * @description <b>获得店铺热门评论商品</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findShopIndexHotCommentGoods(Object object){
		HeadObject head=new HeadObject();
		List<Goods> shopCarCouponDTOs=null;
		try{
			shopCarCouponDTOs = shopIndexMapper.selectShopIndexHotCommentGoods((ShopIndexQryDTO)object);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,shopCarCouponDTOs);
	}
	
	/**
	  * @description <b>按分类获取热销商品</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findShopIndexCategoryGoods(Object object){
		HeadObject head=new HeadObject();
		List<Goods> shopCarCouponDTOs=null;
		try{
			shopCarCouponDTOs = shopIndexMapper.selectShopIndexCategoryGoods((ShopIndexQryDTO)object);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,shopCarCouponDTOs);
	}
	
	/**
	  * @description <b>查询店铺推送</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findShopIndexPush(Object object){
		HeadObject head=new HeadObject();
		List<ShopIndexPushWithBLOBs> shopIndexPushWithBLOBs=null;
		try{
			shopIndexPushWithBLOBs = shopIndexPushMapper.selectShopIndexByCompanyId((ShopIndexQryDTO) object);
			if(shopIndexPushWithBLOBs.size()>0){
				for (ShopIndexPushWithBLOBs shopIndexPush : shopIndexPushWithBLOBs){
					buildShopIndexJSON(shopIndexPush);
				}
			}
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,shopIndexPushWithBLOBs);
	}
	
	private void buildShopIndexJSON(ShopIndexPushWithBLOBs shopIndexPush){
		try{
			if(StringUtils.isNotBlank(shopIndexPush.getTitlePush())){
				List<ShopIndexPushDTO> titlePushList=new ArrayList<ShopIndexPushDTO>();
				for (Object title : JSONArray.parseArray(shopIndexPush.getTitlePush())) {
					titlePushList.add(JSONObject.toJavaObject((JSONObject)title, ShopIndexPushDTO.class));
				}
				shopIndexPush.setTitlePushs(titlePushList);
			}
		}catch (Exception e) {
			log.error("店铺首页标题数据转换失败", e);
		}
		
		try{
			if(StringUtils.isNotBlank(shopIndexPush.getLeftPush())){
				List<ShopIndexPushDTO> leftPushList=new ArrayList<ShopIndexPushDTO>();
				for (Object title : JSONArray.parseArray(shopIndexPush.getLeftPush())) {
					leftPushList.add(JSONObject.toJavaObject((JSONObject)title, ShopIndexPushDTO.class));
				}
				shopIndexPush.setLeftPushs(leftPushList);
			}
		}catch (Exception e) {
			log.error("店铺首页左侧数据转换失败", e);
		}
		
		try{
			if(StringUtils.isNotBlank(shopIndexPush.getRightPush())){
				List<ShopIndexPushDTO> rightPushList=new ArrayList<ShopIndexPushDTO>();
				for (Object title : JSONArray.parseArray(shopIndexPush.getRightPush())) {
					rightPushList.add(JSONObject.toJavaObject((JSONObject)title, ShopIndexPushDTO.class));
				}
				shopIndexPush.setRightPushs(rightPushList);
			}
		}catch (Exception e) {
			log.error("店铺首页右侧数据转换失败", e);
		}
	}
}
