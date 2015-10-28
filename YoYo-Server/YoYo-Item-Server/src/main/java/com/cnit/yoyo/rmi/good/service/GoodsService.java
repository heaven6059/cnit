/**
 * 文 件 名   :  GoodsService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午5:56:20
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.good.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.goods.AccessoryGoodsMapper;
import com.cnit.yoyo.dao.goods.AttributeGoodsIndexMapper;
import com.cnit.yoyo.dao.goods.AttributeValueMapper;
import com.cnit.yoyo.dao.goods.GoodsTimePriceMapper;
import com.cnit.yoyo.dao.goods.GoodsAppointmentMapper;
import com.cnit.yoyo.dao.goods.GoodsKeywordsMapper;
import com.cnit.yoyo.dao.goods.GoodsMapper;
import com.cnit.yoyo.dao.goods.GoodsProductPicShipMapper;
import com.cnit.yoyo.dao.goods.GoodsRelatedMapper;
import com.cnit.yoyo.dao.goods.GoodsViewHistoryMapper;
import com.cnit.yoyo.dao.goods.ProductMapper;
import com.cnit.yoyo.dao.goods.SpecGoodsIndexMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.exception.BusinessException;
import com.cnit.yoyo.model.goods.AccessoryGoods;
import com.cnit.yoyo.model.goods.AccessoryGoodsExample;
import com.cnit.yoyo.model.goods.AttributeGoodsIndex;
import com.cnit.yoyo.model.goods.AttributeGoodsIndexExample;
import com.cnit.yoyo.model.goods.AttributeValue;
import com.cnit.yoyo.model.goods.AttributeValueExample;
import com.cnit.yoyo.model.goods.GoodsTimePrice;
import com.cnit.yoyo.model.goods.Goods;
import com.cnit.yoyo.model.goods.GoodsAppointment;
import com.cnit.yoyo.model.goods.GoodsCat;
import com.cnit.yoyo.model.goods.GoodsExample;
import com.cnit.yoyo.model.goods.GoodsForAppVo;
import com.cnit.yoyo.model.goods.GoodsKeywords;
import com.cnit.yoyo.model.goods.GoodsKeywordsExample;
import com.cnit.yoyo.model.goods.GoodsProductPicShip;
import com.cnit.yoyo.model.goods.GoodsProductPicShipExample;
import com.cnit.yoyo.model.goods.GoodsRelated;
import com.cnit.yoyo.model.goods.GoodsViewHistory;
import com.cnit.yoyo.model.goods.GoodsViewHistoryKey;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;
import com.cnit.yoyo.model.goods.ProductExample;
import com.cnit.yoyo.model.goods.ProductWithBLOBs;
import com.cnit.yoyo.model.goods.SpecGoodsIndex;
import com.cnit.yoyo.model.goods.SpecGoodsIndexExample;
import com.cnit.yoyo.model.goods.dto.AccessoryGoodsDTO;
import com.cnit.yoyo.model.goods.dto.GoodAttributeValueDTO;
import com.cnit.yoyo.model.goods.dto.GoodsCarDTO;
import com.cnit.yoyo.model.goods.dto.GoodsInfoListDTO;
import com.cnit.yoyo.model.goods.dto.GoodsProductDTO;
import com.cnit.yoyo.model.goods.dto.GoodsProductPicShipDTO;
import com.cnit.yoyo.model.goods.dto.GoodsProductSpecDTO;
import com.cnit.yoyo.model.goods.dto.GoodsProductUpdate;
import com.cnit.yoyo.model.goods.dto.GoodsQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.ApplicationContextUtil;
import com.cnit.yoyo.util.BNGenerator;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description 商品管理
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
@Service("goodsService")
public class GoodsService {
	public static final Logger log = LoggerFactory.getLogger(GoodsService.class);

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private AttributeGoodsIndexMapper attributeGoodsIndexMapper;

	@Autowired
	private GoodsProductPicShipMapper goodsProductPicShipMapper;

	@Autowired
	private AttributeGoodsIndexMapper attributeGoodesIndexMapper;
	
	@Autowired
	private AttributeValueMapper attributeValueMapper;

	@Autowired
	private GoodsKeywordsMapper goodsKeywordsMapper;

	@Autowired
	private GoodsRelatedMapper goodsRelatedMapper;

	@Autowired
	private AccessoryGoodsMapper accessoryGoodsMapper;

	@Autowired
	private SpecGoodsIndexMapper specGoodsIndexMapper;

	@Autowired
	private GoodsViewHistoryMapper goodsViewHistoryMapper;
	
	@Autowired
	private GoodsAppointmentMapper goodsAppointmentMapper;
	
	@Autowired
	private GoodsTimePriceMapper goodTimePriceMapper;
	
	@Autowired
	public RemoteService searchClientService;

	public void main(String[] args, Object data) {
		data = getGoodsByParam(data);
		data = batchExdiv(data);
		data = batchDelete(data);
		data = batchTagging(data);
		data = getGoodsDetailById(data);
		data = getAccessoryGoodsById(data);
	}

	/**
	 * @description 根据id查询配件商品信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月14日
	 * @param data
	 * @return
	 */
	public Object getAccessoryGoodsById(Object data) {
		log.info("start[GoodsService.getAccessoryGoodsById]");
		AccessoryGoodsExample example = new AccessoryGoodsExample();
		example.createCriteria().andGoodsIdEqualTo(Long.valueOf((Integer) data));
		List<AccessoryGoods> list = accessoryGoodsMapper.selectByExample(example);
		log.info("end[GoodsService.getAccessoryGoodsById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(list));
	}

	/**
	 * @description 更新商品信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月14日
	 * @param data
	 * @return
	 */
	public Object updateGoodInfo(Object data) throws Exception {
		log.info("start[GoodsService.updateGoodInfo]");
		GoodsProductUpdate goodsProductDTO = (GoodsProductUpdate) JSON.parseObject(data.toString(), GoodsProductUpdate.class);
		GoodsWithBLOBs goods = goodsProductDTO.getInfo();
		// 更新扩展属性
		List<AttributeGoodsIndex> exttributes = goodsProductDTO.getAttrJson();
		if (exttributes != null && exttributes.size() > 0) {
			// 先删除现有的扩展属性
			AttributeGoodsIndexExample example = new AttributeGoodsIndexExample();
			example.createCriteria().andGoodsIdEqualTo(goods.getGoodsId());
			attributeGoodesIndexMapper.deleteByExample(example);
			// 批量插入
			attributeGoodesIndexMapper.batchInsert(goodsProductDTO.getAttrJson());
		}
		// 更新关键字
		String keyWords = goodsProductDTO.getInfo().getKeywords();
		if (StringUtils.isNotBlank(keyWords)) {
			// 先删除现有关键字
			GoodsKeywordsExample example = new GoodsKeywordsExample();
			example.createCriteria().andGoodsIdEqualTo(Long.valueOf(goods.getGoodsId()));
			goodsKeywordsMapper.deleteByExample(example);
			List<GoodsKeywords> goodsKeywordsList = new ArrayList<GoodsKeywords>();
			GoodsKeywords temp = null;
			String[] keywords = keyWords.split("\\|");
			for (int i = 0; i < keywords.length; i++) {
				temp = new GoodsKeywords();
				temp.setGoodsId(Long.valueOf(goodsProductDTO.getInfo().getGoodsId()));
				temp.setKeyword(keywords[i]);
				temp.setResType(GlobalStatic.KEYWORDS_RESTYPE_GOODS);
				goodsKeywordsList.add(temp);
			}
			// 批量插入
			goodsKeywordsMapper.batchInsert(goodsKeywordsList);
		}
		// 更新配件
		if (null != goodsProductDTO.getAccessoryJson() && goodsProductDTO.getAccessoryJson().size() > 0) {
			accessoryGoodsMapper.deleteByGoodsId(goods.getGoodsId());
			accessoryGoodsMapper.batchInsert(goodsProductDTO.getAccessoryJson());// 批量插入
		}
		// 更新相关商品
		if (null != goodsProductDTO.getRelateGoodsJson() && goodsProductDTO.getRelateGoodsJson().size() > 0) {
			goodsRelatedMapper.deleteByGoodId1(goods.getGoodsId()); // 删除已经关联的商品
			goodsRelatedMapper.batchInsert(goodsProductDTO.getRelateGoodsJson());// 批量新增
		}
		// 更新规格货品
		List<GoodsProductSpecDTO> products = goodsProductDTO.getProducts();
		if (null != products && products.size() > 0) {
			List<ProductWithBLOBs> inserts = new ArrayList<ProductWithBLOBs>();
			List<ProductWithBLOBs> update = new ArrayList<ProductWithBLOBs>();
			for (int i = 0; i < products.size(); i++) {
				GoodsProductSpecDTO temp = products.get(i);
				ProductWithBLOBs product = new ProductWithBLOBs();
				product.setProductId(temp.getProductId());
				product.setBarcode(temp.getBn());
				product.setBn(temp.getBn());
				product.setCost(temp.getCost());
				product.setGoodsId(goods.getGoodsId());
				product.setGoodsType("normal");
				product.setMarketable(temp.getMarketable());
				product.setMktprice(temp.getMktprice());
				product.setName(goods.getName());
				product.setPrice(temp.getPrice());
				product.setStore(temp.getStore());
				product.setStorePlace(temp.getStorePlace());
				product.setTitle(goods.getName());
				product.setSpecDesc(temp.getSpecDesc());
				product.setSpecInfo(temp.getSpecInfo());
				if ("1".equalsIgnoreCase(temp.getMarketable())) {
					product.setUptime(DateUtils.getCurrentlyDate());
				}
				product.setLastModify(DateUtils.getCurrentlyDate());
				if (null == temp.getProductId()) {// 新增
					inserts.add(product);
				} else {
					update.add(product);
				}
			}
			// 批量插入
			if (inserts.size() > 0) {
				productMapper.batchInsert(inserts);
			}
			// 批量更新
			if (update.size() > 0) {
				productMapper.batchUpdate(update);
			}
		}
		// 更新商品
		goodsMapper.updateByPrimaryKeySelective(goodsProductDTO.getInfo());

		log.info("end[GoodsService.updateGoodInfo]");
		return null;
	}

	/**
	 * @description 获取商品详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月9日
	 * @param data
	 * @return
	 */
	public Object getGoodsDetailById(Object data) {
		log.info("start[GoodsService.getGoodsDetailById]");
		GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey((Integer) data);
		log.info("end[GoodsService.getGoodsDetailById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(goods));
	}

	/**
	 * @description 商品批量打标签
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月8日
	 * @param data
	 * @return
	 */
	public Object batchTagging(Object data) {
		log.info("start[GoodsService.batchTagging]");
		JSONObject content = JSONObject.fromObject(data);
		JSONArray goodsRows = content.getJSONArray("rows");
		List<Integer> goodsDetailIds = new ArrayList<Integer>();
		for (Object object : goodsRows) {
			JSONObject json = JSONObject.fromObject(object);
			goodsDetailIds.add(json.optInt("goodsId"));
		}
		if (goodsDetailIds.size() > 0) {
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);
		}
		log.info("end[GoodsService.batchTagging]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 商品批量删除
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月8日
	 * @param data
	 * @return
	 */
	public Object batchDelete(Object data) {
		log.info("start[GoodsService.batchDelete]");
		JSONObject content = JSONObject.fromObject(data);
		JSONArray goodsRows = content.getJSONArray("rows");
		List<Integer> goodsDetailIds = new ArrayList<Integer>();
		for (Object object : goodsRows) {
			JSONObject json = JSONObject.fromObject(object);
			goodsDetailIds.add(json.optInt("goodsId"));
		}
		if (goodsDetailIds.size() > 0) {
			/* 汽车商品批量删除 */
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setDisabled("1");
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);
		}
		log.info("end[GoodsService.batchDelete]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 商品批量降权
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月7日
	 * @param data
	 * @return
	 */
	public Object batchExdiv(Object data) {
		log.info("start[GoodsService.batchExdiv]");
		JSONObject content = JSONObject.fromObject(data);
		JSONArray goodsRows = content.getJSONArray("rows");
		List<Integer> goodsDetailIds = new ArrayList<Integer>();
		for (Object object : goodsRows) {
			JSONObject json = JSONObject.fromObject(object);
			goodsDetailIds.add(json.optInt("goodsId"));
		}
		if (goodsDetailIds.size() > 0) {
			/* 商品批量降权 */
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);
		}
		log.info("end[GoodsService.batchExdiv]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 商品批量下架
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月7日
	 * @param data
	 * @return
	 */
	public Object batchSaleDown(Object data) {
		log.info("start[GoodsService.batchSaleDown]");
		JSONObject content = JSONObject.fromObject(data);
		JSONArray goodsRows = content.getJSONArray("rows");
		List<Integer> goodsDetailIds = new ArrayList<Integer>();
		for (Object object : goodsRows) {
			JSONObject json = JSONObject.fromObject(object);
			goodsDetailIds.add(json.optInt("goodsId"));
		}
		if (goodsDetailIds.size() > 0) {
			/* 商品批量下架 */
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setMarketable("0");
			if (StringUtils.isBlank(content.getString("preType"))) { // 前端shop传过来的，不需要设置
				record.setMarketableAllow("1");
			} else {
				record.setMarketableAllow("0");
			}
			if (content.containsKey("cause")) {
				record.setP22(content.getString("cause"));
			}
			record.setMarketableContent(content.optString("downContent"));
			record.setDownTime(DateUtils.getCurrentDate(null));
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);

			/** 货品批量下架 @Add xiaox */
			/*
			 * ProductWithBLOBs product = new ProductWithBLOBs();
			 * product.setMarketable("0"); product.setUptime(new Date());
			 * product.setLastModify(new Date()); ProductExample proExample =
			 * new ProductExample();
			 * proExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			 * productMapper.updateByExampleSelective(product, proExample);
			 */

			HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.deleteIndexByGoodIds");
			searchClientService.doServiceByServer(new RequestObject(headObject, JSONArray.fromObject(goodsDetailIds)));
		}
		log.info("end[GoodsService.batchSaleDown]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 商品批量上架
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月7日
	 * @param data
	 * @return
	 */
	public Object batchSaleUp(Object data) {
		log.info("start[GoodsService.batchSaleUp]");
		JSONObject content = JSONObject.fromObject(data);
		JSONArray goodsRows = content.getJSONArray("rows");
		List<Integer> goodsDetailIds = new ArrayList<Integer>();
		for (Object object : goodsRows) {
			JSONObject json = JSONObject.fromObject(object);
			goodsDetailIds.add(json.optInt("goodsId"));
		}
		if (goodsDetailIds.size() > 0) {
			/* 商品批量上架 */
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setMarketable("1");
			record.setMarketableAllow("0");
			record.setMarketableContent("");
			record.setDownTime("");
			record.setUpTime(DateUtils.getCurrentDate(null));
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);

			/*	*//** 同时需要更改货品表上架 @Add xiaox 不需要上架货品，下架也同理 */
			/*
			 * ProductWithBLOBs product = new ProductWithBLOBs();
			 * product.setMarketable("1"); product.setUptime(new Date());
			 * product.setLastModify(new Date()); ProductExample proExample =
			 * new ProductExample();
			 * proExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			 * productMapper.updateByExampleSelective(product, proExample);
			 */

			// 增加到索引库
			List<GoodsInfoListDTO> goods = goodsMapper.selectGoodsByGoodsIds(StringUtils.join(goodsDetailIds, ","));
			HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.addGoodsOneIndex");
			searchClientService.doServiceByServer(new RequestObject(headObject, JSONArray.fromObject(goods)));
		}
		log.info("end[GoodsService.batchSaleUp]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 商品批量审核
	 * @detail <方法详细描述>
	 * @author wanghb
	 * @version 1.0.0
	 * @data 2015年6月16日
	 * @param data
	 * @return
	 */
	public Object batchCheck(Object data) {
		log.info("start[GoodsService.batchCheck]");
		JSONObject content = JSONObject.fromObject(data);
		JSONArray goodsRows = content.getJSONArray("rows");
		List<Integer> goodsDetailIds = new ArrayList<Integer>();
		for (Object object : goodsRows) {
			JSONObject json = JSONObject.fromObject(object);
			goodsDetailIds.add(json.optInt("goodsId"));
		}
		if (goodsDetailIds.size() > 0) {
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setMarketable(content.getString("status"));
			record.setP23(DateUtils.getCurrentDate(null));// 审核时间
			record.setP22(content.getString("cause"));// 审核不通过原因
			record.setMarketableContent("");
			record.setDownTime("");
			record.setUpTime("");
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);

		}
		log.info("end[GoodsService.batchCheck]");
		return new HeadObject(ErrorCode.SUCCESS);
	}
	
	/**
	 * @description 商品批量设置库存预警值
	 * @detail <方法详细描述>
	 * @author ssd
	 * @param data
	 * @return
	 */
	public Object batchAlert(Object data) {
		log.info("start[GoodsService.batchAlert]");
		JSONObject content = JSONObject.fromObject(data);
		JSONArray goodsRows = content.getJSONArray("rows");
		List<Integer> goodsDetailIds = new ArrayList<Integer>();
		for (Object object : goodsRows) {
			JSONObject json = JSONObject.fromObject(object);
			goodsDetailIds.add(json.optInt("goodsId"));
		}
		if (goodsDetailIds.size() > 0) {
			int storeValue = goodsMapper.selectMinStore(goodsDetailIds);
			int alertValue = content.optInt("alertValue");
			if(alertValue > storeValue) {
				return new HeadObject(ErrorCode.MORE_THAN_STORE);
			}
			
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setP1(alertValue);// 预警值
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(goodsDetailIds);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);

		}
		log.info("end[GoodsService.batchAlert]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * 
	 * @description 暂存本地的商品批量上架或审核
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-6-12
	 * @param data
	 * @return
	 */
	public Object batchUpOrCheck(Object data) {
		log.info("start[GoodsService.batchUpOrCheck]");
		JSONObject content = JSONObject.fromObject(data);
		JSONArray goodsRows = content.getJSONArray("rows");
		List<Integer> upGoodsId = new ArrayList<Integer>(); // 需要上架的商品id
		List<Integer> checkGoodsId = new ArrayList<Integer>(); // 需要审核的商品id
		JSONObject json = null;
		for (Object object : goodsRows) {
			json = JSONObject.fromObject(object);
			if (json.optInt("checkCat") == 0) {
				upGoodsId.add(json.optInt("goodsId"));
			} else {
				checkGoodsId.add(json.optInt("goodsId"));
			}
		}
		if (upGoodsId.size() > 0) {
			/* 商品批量上架 */
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setMarketable("1");
			record.setMarketableAllow("0");
			record.setMarketableContent("");
			record.setDownTime("");
			record.setUpTime(DateUtils.getCurrentDate(null));
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(upGoodsId);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);

			/** 同时需要更改货品表上架 @Add xiaox */
			ProductWithBLOBs product = new ProductWithBLOBs();
			product.setMarketable("1");
			product.setUptime(new Date());
			product.setLastModify(new Date());
			ProductExample proExample = new ProductExample();
			proExample.createCriteria().andGoodsIdIn(upGoodsId);
			productMapper.updateByExampleSelective(product, proExample);

			// 增加到索引库
			List<GoodsInfoListDTO> goods = goodsMapper.selectGoodsByGoodsIds(StringUtils.join(upGoodsId, ","));
			HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.addGoodsOneIndex");
			searchClientService.doServiceByServer(new RequestObject(headObject, JSONArray.fromObject(goods)));

		}

		if (checkGoodsId.size() > 0) {
			/* 商品批量审核 */
			GoodsWithBLOBs record = new GoodsWithBLOBs();
			record.setMarketable("2");
			record.setMarketableAllow("0");
			record.setMarketableContent("");
			record.setDownTime("");
			record.setUpTime(DateUtils.getCurrentDate(null));
			record.setLastModify(DateUtils.getCurrentDate(null));
			GoodsExample carGoodsExample = new GoodsExample();
			carGoodsExample.createCriteria().andGoodsIdIn(checkGoodsId);
			goodsMapper.updateByExampleSelective(record, carGoodsExample);
		}
		log.info("end[GoodsService.batchUpOrCheck]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 分页查询商品信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月3日
	 * @param data
	 * @return
	 */
	public Object getGoodsByParam(Object data) {
		log.info("start[GoodsService.getGoodsByParam]");
		JSONObject content = JSONObject.fromObject(data);
		PageHelper.startPage(QueryParamObject.getPage(content), QueryParamObject.getRows(content));
		ResultPage<GoodsInfoListDTO> page = new ResultPage<GoodsInfoListDTO>(goodsMapper.selectByQueryParams(QueryParamObject.getMqls(content), QueryParamObject.getOrderByCause(content)));
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
		JSONObject json = JSONObject.fromObject(page, jsonConfig);
		log.info("end[GoodsService.getGoodsByParam]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
	}

	/**
	 * @Description: 添加商品
	 * @param @param data
	 * @param @return
	 * @author xiaox
	 * @throws BusinessException
	 * @date 2015-4-11 上午11:23:08
	 */
	public Object saveGoods(Object data) throws BusinessException {
		HeadObject head = new HeadObject();
		int count = 0;
		try {
			JSONObject content = JSONObject.fromObject(data);
			GoodsProductDTO dto = (GoodsProductDTO) JSONObject.toBean(content, GoodsProductDTO.class);
			// 如果没有选规格，需要判断是否主店违规限制发布商品
			if (dto.getIsOpenSpec() == "1") { // 关闭规格
				if (goodsMapper.checkStoreLimitGoods(dto.getStoreId()) == 1) {
					head.setRetCode(ErrorCode.IS_LIMIT_GOODS);
					return new ResultObject(head);
				}
			}
			// 先要判断店铺商品数量是否超出范围
			/*
			 * int num=goodsMapper.checkGoodsNum(dto.getCompanyId());
			 * if(num==0){ //已结达到店铺最大发布商品数量
			 * head.setRetCode(ErrorCode.IS_MAX_GOODS); return new
			 * ResultObject(head); }
			 */

			if (StringUtils.isNotBlank(dto.getFbn())) { // 存在商品编号
				count = findGoodsByBn(dto.getFbn());
				if (count > 0) { // 存在相同商品编号
					head.setRetCode(ErrorCode.IS_EXIST);
					return new ResultObject(head);
				}
			}else {
				long temp = new Date().getTime();
				dto.setFbn(String.valueOf(temp));
			}

			// 1添加商品,商品无价格
			GoodsWithBLOBs goods = new GoodsWithBLOBs();
			BeanUtils.copyProperties(dto, goods);
			goods.setP21(dto.getIsOpenSpec());
			goods.setSpecDesc(dto.getGoodsSpecJson());
			goods.setLastModify(DateUtils.getCurrentDate(null));
			goods.setDbn(setGoodsBn(dto.getRootCategory()));
			goods.setBigPic(dto.getDefaultPic());
			goods.setSmallPic(dto.getDefaultPic());
			goods.setMidPic(dto.getDefaultPic());
			goods.setDisabled("0");

			/* 根据店铺发布商品的权限决定上下架待审核与待上架的状态 add by liming */
			String marketable = goods.getMarketable();
			// 卖家发布商品时选择立即上架 后台根据权限判断是否需要审核
			/*
			 * 2015.05.25 xiaox 暂时注释掉 if ("1".equalsIgnoreCase(marketable)) { //
			 * 判断权限 不需要审核 直接上架 marketable = 1 并存入上架时间
			 * 
			 * // 需要审核 marketable = 2 审核通过 marketable = 1
			 * goods.setUpTime(DateUtils.getCurrentDate(null)); } //
			 * 卖家发布商品时选择暂不上架 marketable = 3 进入待上架列表 else {
			 * goods.setMarketable("3"); }
			 */
			// 2015.05.27 xiaox 添加上架时间
			if (marketable.equals("1")) {
				goods.setUpTime(DateUtils.getCurrentDate(null));
			}

			goodsMapper.insertSelective(goods);
			
			// 2添加商品属性值
			AttributeGoodsIndex attr = null;
			List<AttributeGoodsIndex> attrList=new ArrayList<AttributeGoodsIndex>();
			if (StringUtils.isNotBlank(content.getString("attrJson"))) {
				JSONArray array = JSONArray.fromObject(content.getString("attrJson"));
				for (int i = 0; i < array.size(); i++) {
					attr = new AttributeGoodsIndex();
					attr.setAttrId(array.getJSONObject(i).getInt("id"));
					attr.setAttrValue(array.getJSONObject(i).getString("value"));
					attr.setGoodsId(goods.getGoodsId());
					attr.setCatId(dto.getCatId());
					attributeGoodsIndexMapper.insertSelective(attr);
					AttributeValueExample example=new AttributeValueExample();
					example.createCriteria().andAttrIdEqualTo(attr.getAttrId()).andAttrValueEqualTo(attr.getAttrValue());
					List<AttributeValue> list=attributeValueMapper.selectByExample(example);
					if(null!=list && list.size()>0){
						attr.setAttrValueId(list.get(0).getAttrValueId());
						attrList.add(attr);
					}
				}
			}
			
			int goodsId = goods.getGoodsId();
			long gId = (long)goodsId;
			//添加预约时间
			GoodsAppointment appoint = null;
			int appointType = dto.getAppointType();
			if (appointType > 0) {
				appoint = new GoodsAppointment();
				
				appoint.setGoodsId(gId);
				if(appointType == 3) {
					if(null != dto.getFromTime()) {
						appoint.setAppointStart(dto.getFromTime());
					}
					if(null != dto.getToTime()) {
						appoint.setAppointEnd(dto.getToTime());
					}
				}
				
				appoint.setType(appointType);
				appoint.setTimeNum1(dto.getTimenum1());
				appoint.setTimeNum2(dto.getTimenum2());
				appoint.setTimeNum3(dto.getTimenum3());
				appoint.setTimeNum4(dto.getTimenum4());
				appoint.setTimeNum5(dto.getTimenum5());
				appoint.setTimeNum6(dto.getTimenum6());
				appoint.setTimeNum7(dto.getTimenum7());
				appoint.setTimeNum8(dto.getTimenum8());
				
				goodsAppointmentMapper.insertSelective(appoint);
			}
			
			// 3.添加商品关键字
			GoodsKeywords keywords = null;
			if (StringUtils.isNotBlank(dto.getKeywords())) {
				String[] words = arrayUnique(dto.getKeywords().split("\\|"));
				for (int i = 0; i < words.length; i++) { // 多个关键词
					keywords = new GoodsKeywords();
					keywords.setGoodsId(Long.valueOf(goods.getGoodsId()));
					keywords.setKeyword(words[i]);
					keywords.setLastModify(new Date());
					keywords.setResType(GlobalStatic.KEYWORDS_RESTYPE_GOODS);
					goodsKeywordsMapper.insertSelective(keywords);
				}
			}
			
			Date priceStart = null;
			Date priceEnd = null;
			if(StringUtil.isNotEmpty(dto.getPriceStartTime())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				priceStart = sdf.parse(dto.getPriceStartTime()); 
			    priceEnd = sdf.parse(dto.getPriceEndTime());
			}
			
			// 4添加货品
			if (StringUtils.isNotBlank(content.getString("productJson"))) {
				GoodsProductPicShip ship = new GoodsProductPicShip();
				// SpecGoodsIndex specGoods = new SpecGoodsIndex(); //货品规格关系
				// String [] specValueItems = null; //规格项：1|颜色:1|红色
				// String [] items = null; //规格项详情：1|颜色 或 1|红色
				String[] picIds = null; // 货品图片ids

				ProductWithBLOBs product = new ProductWithBLOBs();
				JSONArray array = JSONArray.fromObject(content.getString("productJson"));
				if (null != array && array.size() > 0) {
					for (int i = 0; i < array.size(); i++) {
						GoodsProductSpecDTO temp = (GoodsProductSpecDTO) JSONObject.toBean(array.getJSONObject(i), GoodsProductSpecDTO.class);

						// 判断货号是否存在
						if (StringUtils.isNotBlank(temp.getBn())) {
							count = productMapper.findProductByBn(new String[] { temp.getBn() }, temp.getProductId());
							if (count > 0) { // 存在相同商品编号
								head.setRetCode(ErrorCode.IS_EXIST_PRODUCT);
								return new ResultObject(head);
							}
						}

						product = new ProductWithBLOBs();
						product.setProductId(temp.getProductId());
						product.setBarcode(temp.getBn());
						product.setBn(temp.getBn());
						product.setCost(temp.getCost());
						product.setGoodsId(goods.getGoodsId());
						product.setGoodsType("normal");
						product.setMarketable(temp.getMarketable());
						product.setMktprice(temp.getMktprice());
						product.setName(goods.getName());
						product.setPrice(temp.getPrice());
						product.setStore(temp.getStore());
						product.setStorePlace(temp.getStorePlace());
						product.setTitle(goods.getName());
						product.setSpecDesc(temp.getSpecDesc());
						product.setSpecInfo(temp.getSpecInfo());
						product.setPicturePath(temp.getPicturePath());
						if ("1".equalsIgnoreCase(temp.getMarketable())) {
							product.setUptime(DateUtils.getCurrentlyDate());
						}
						product.setLastModify(DateUtils.getCurrentlyDate());

						productMapper.insertSelective(product);

						// 5添加商品，货品，图片关系表
						ship.setGoodsId(Long.valueOf(goods.getGoodsId()));
						ship.setProductId(Long.valueOf(product.getProductId()));
						if (StringUtils.isNotBlank(temp.getPicIds())) {
							picIds = temp.getPicIds().split("\\|");
							for (int k = 0; k < picIds.length; k++) {
								ship.setPictureId(Long.valueOf(picIds[k]));
								goodsProductPicShipMapper.insertSelective(ship);
							}
						} else { // 没有选择图片，则设置默认第一张图片
							ship.setPictureId(dto.getDefaultPicId());
							goodsProductPicShipMapper.insertSelective(ship);

						}
						// 添加商品规格的关系
						/*
						 * 加入了分店sku后，此处不进行插入，没有使用到 2015.05.12
						 */
						SpecGoodsIndex specGoods = new SpecGoodsIndex();
						specGoods.setGoodsId(goods.getGoodsId());
						specGoods.setProductId(product.getProductId()); // 1|颜色:1|红色,2|款式:6|男装
						if (StringUtils.isNotBlank(temp.getSpecDesc())) {
							String[] specValueItems = (temp.getSpecDesc()).split(",");
							for (int j = 0; j < specValueItems.length; j++) {
								String[] items = specValueItems[j].split(":");
								specGoods.setSpecId(Integer.valueOf(items[0].split("\\|")[0]));
								specGoods.setSpecValueId(Integer.valueOf(items[1].split("\\|")[0]));
								specGoodsIndexMapper.insertSelective(specGoods);
							}
						}
						
						//添加价格区间
						GoodsTimePrice timePrice = null;
						if(null != priceStart) {
							timePrice = new GoodsTimePrice();
							timePrice.setGoodsId(gId);
							timePrice.setProductId(product.getProductId());
							timePrice.setPrice(dto.getTimePrice());
							timePrice.setPriceStart(priceStart);
							timePrice.setPriceEnd(priceEnd);
							goodTimePriceMapper.insertSelective(timePrice);
						}
						
					}

				}
			} else { // 不选择规格，单个货品
				ProductWithBLOBs product = new ProductWithBLOBs();
				GoodsProductPicShip ship = new GoodsProductPicShip();
				String[] picIds = dto.getPicIds(); // 货品图片ids
				String[] picIdsItems = null; // 每个货品的多个图片id
				product.setGoodsId(goods.getGoodsId());
				product.setBn(dto.getBn());
				product.setPrice(dto.getPrice());
				product.setCost(dto.getCost());
				product.setMktprice(dto.getMktPrice());
				product.setName(dto.getName());
				product.setStore(dto.getStore());
				product.setTitle(goods.getName());
				product.setLastModify(new Date());
				product.setUptime(new Date());
				product.setMarketable(dto.getMarketable());
				product.setPicturePath(dto.getDefaultPic());
				productMapper.insertSelective(product);
				// 5添加商品，货品，图片关系表:没有选择规格，则所有图片都关联到该货品上
				ship.setGoodsId(Long.valueOf(goods.getGoodsId()));
				ship.setProductId(Long.valueOf(product.getProductId()));
				if (picIds != null && picIds.length > 0) {
					picIdsItems = picIds[0].split("\\|");
					for (int k = 0; k < picIdsItems.length; k++) {
						ship.setPictureId(Long.valueOf(picIdsItems[k]));
						goodsProductPicShipMapper.insertSelective(ship);
					}
				}
				
				//添加价格区间
				GoodsTimePrice timePrice = null;
				if(null != priceStart) {
					timePrice = new GoodsTimePrice();
					timePrice.setGoodsId(gId);
					timePrice.setProductId(product.getProductId());
					timePrice.setPrice(dto.getTimePrice());
					timePrice.setPriceStart(priceStart);
					timePrice.setPriceEnd(priceEnd);
					goodTimePriceMapper.insertSelective(timePrice);
				}
			}
			// 6.添加相关商品
			if (StringUtils.isNotBlank(content.getString("relateGoodsJson"))) {
				JSONArray array = JSONArray.fromObject(content.getString("relateGoodsJson")); // 相关商品
				GoodsRelated goodsRelate = null;
				List<GoodsRelated> list = new ArrayList<GoodsRelated>();
				if (array != null && array.size() > 0) {
					JSONObject relateJson = null;
					for (int i = 0; i < array.size(); i++) { // 多个相关商品
						relateJson = array.getJSONObject(i);
						goodsRelate = new GoodsRelated();
						goodsRelate.setGoods1(Long.valueOf(goods.getGoodsId()));
						goodsRelate.setGoods2(relateJson.getLong("goods2"));
						goodsRelate.setManual(relateJson.getString("manual"));
						list.add(goodsRelate);
					}
					goodsRelatedMapper.batchInsert(list);// 批量新增
				}

			}

			// 7.添加配件
			if (StringUtils.isNotBlank(content.getString("accessoryJson"))) {
				JSONArray accArray = JSONArray.fromObject(content.getString("accessoryJson"));
				AccessoryGoods accGoods = null;
				for (int i = 0; i < accArray.size(); i++) {
					accGoods = (AccessoryGoods) JSONObject.toBean(accArray.getJSONObject(i), AccessoryGoods.class);
					accGoods.setGoodsId(Long.valueOf(goods.getGoodsId()));
					accessoryGoodsMapper.insertSelective(accGoods);
				}
			}
			if (marketable.equals("1")) {
				// 增加到索引库
				List<GoodsInfoListDTO> goodsInfoListDTO = goodsMapper.selectGoodsByGoodsIds(goods.getGoodsId().toString());
				HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.addGoodsOneIndex");
				searchClientService.doServiceByServer(new RequestObject(headObject, JSONArray.fromObject(goodsInfoListDTO)));
				if (null!=attrList && attrList.size()>0 ) {
					HeadObject headObjectAttr = CommonHeadUtil.geneHeadObject("searchService.addGoodsAttrIndex");
					searchClientService.doServiceByServer(new RequestObject(headObjectAttr, JSONArray.fromObject(attrList)));
				}
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
			throw new BusinessException(ErrorCode.SPE001);
		}
		return new ResultObject(head);
	}

	/**
	 * 
	 * @description 卖家中心编辑商品进行更新
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-4-30
	 * @param data
	 * @return
	 */
	public Object updateGoodInfoAtWeb(Object data) {
		log.info("start[GoodsService.updateGoodInfoAtWeb]");
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		GoodsProductDTO dto = (GoodsProductDTO) JSONObject.toBean(content, GoodsProductDTO.class);
		int count = 0;
		// 如果没有选规格，需要判断是否主店违规限制发布商品
		if (dto.getIsOpenSpec().equals("1")) { // 关闭规格
			if (goodsMapper.checkStoreLimitGoods(dto.getStoreId()) == 1) {
				head.setRetCode(ErrorCode.IS_LIMIT_GOODS);
				return new ResultObject(head);
			}
		}
		if (StringUtils.isNotBlank(dto.getFbn())) { // 存在商品编号
			count = findEditGoodsByBn(dto.getFbn(), dto.getGoodsId());
			if (count > 0) { // 存在相同商品编号
				head.setRetCode(ErrorCode.IS_EXIST);
				return new ResultObject(head);
			}
		}

		// 2更新商品属性值
		AttributeGoodsIndex attr = null;
		if (StringUtils.isNotBlank(content.getString("attrJson"))) {
			JSONArray array = JSONArray.fromObject(content.getString("attrJson"));
			// 先删除现有的扩展属性
			AttributeGoodsIndexExample example = new AttributeGoodsIndexExample();
			example.createCriteria().andGoodsIdEqualTo(dto.getGoodsId());
			attributeGoodesIndexMapper.deleteByExample(example);

			for (int i = 0; i < array.size(); i++) {
				attr = new AttributeGoodsIndex();
				attr.setAttrId(array.getJSONObject(i).getInt("id"));
				attr.setAttrValue(array.getJSONObject(i).getString("value"));
				attr.setGoodsId(dto.getGoodsId());
				attr.setCatId(dto.getCatId());
				attributeGoodsIndexMapper.insertSelective(attr);
			}
		}
		// 3.更新商品关键字
		GoodsKeywords keywords = null;
		// 先删除现有关键字
		GoodsKeywordsExample example = new GoodsKeywordsExample();
		example.createCriteria().andGoodsIdEqualTo(Long.valueOf(dto.getGoodsId()));
		goodsKeywordsMapper.deleteByExample(example);
		if (StringUtils.isNotBlank(dto.getKeywords())) {
			String[] words = arrayUnique(dto.getKeywords().split("\\|"));
			for (int i = 0; i < words.length; i++) { // 多个关键词
				keywords = new GoodsKeywords();
				keywords.setGoodsId(Long.valueOf(dto.getGoodsId()));
				keywords.setKeyword(words[i]);
				keywords.setLastModify(new Date());
				keywords.setResType(GlobalStatic.KEYWORDS_RESTYPE_GOODS);
				goodsKeywordsMapper.insertSelective(keywords);
			}
		}

		// 4.更新配件
		accessoryGoodsMapper.deleteByGoodsId(dto.getGoodsId());
		if (StringUtils.isNotBlank(content.getString("accessoryJson"))) {
			JSONArray accArray = JSONArray.fromObject(content.getString("accessoryJson"));
			AccessoryGoods accGoods = null;
			for (int i = 0; i < accArray.size(); i++) {
				accGoods = (AccessoryGoods) JSONObject.toBean(accArray.getJSONObject(i), AccessoryGoods.class);
				accGoods.setGoodsId(Long.valueOf(dto.getGoodsId()));
				accessoryGoodsMapper.insertSelective(accGoods);
			}
		}

		// 5.更新相关商品
		if (StringUtils.isNotBlank(content.getString("relateGoodsJson"))) {
			JSONArray array = JSONArray.fromObject(content.getString("relateGoodsJson")); // 相关商品
			goodsRelatedMapper.deleteByGoodId1(dto.getGoodsId()); // 删除已经关联的商品
			GoodsRelated goodsRelate = null;
			List<GoodsRelated> list = new ArrayList<GoodsRelated>();
			if (array != null && array.size() > 0) {
				JSONObject relateJson = null;
				for (int i = 0; i < array.size(); i++) { // 多个相关商品
					relateJson = array.getJSONObject(i);
					goodsRelate = new GoodsRelated();
					goodsRelate.setGoods1(Long.valueOf(dto.getGoodsId()));
					goodsRelate.setGoods2(relateJson.getLong("goods2"));
					goodsRelate.setManual(relateJson.getString("manual"));
					list.add(goodsRelate);
				}
				goodsRelatedMapper.batchInsert(list);// 批量新增
			}

		}

		// 6更新货品
		ProductWithBLOBs product = new ProductWithBLOBs();
		GoodsProductPicShip ship = new GoodsProductPicShip();
		product.setGoodsId(dto.getGoodsId());
		String[] picIds = dto.getPicIds(); // 货品图片ids
		String[] picIdsItems = null; // 每个货品的多个图片id
		if (StringUtils.isNotBlank(content.getString("productJson"))) {
			JSONArray array = JSONArray.fromObject(content.getString("productJson")); // 货品
			// 7更新商品，货品，图片关系表
			// 先删除所有
			GoodsProductPicShipExample picExample = new GoodsProductPicShipExample();
			picExample.createCriteria().andGoodsIdEqualTo(Long.valueOf(dto.getGoodsId()));
			goodsProductPicShipMapper.deleteByExample(picExample);

			// 更新规格商品表
			// 先删除所有
			SpecGoodsIndexExample specExample = new SpecGoodsIndexExample();
			specExample.createCriteria().andGoodsIdEqualTo(dto.getGoodsId());
			specGoodsIndexMapper.deleteByExample(specExample);

			if (null != array && array.size() > 0) {
				List<Integer> productIds = productMapper.findProductByGoodsId(dto.getGoodsId());
				for (int i = 0; i < array.size(); i++) {
					GoodsProductSpecDTO temp = (GoodsProductSpecDTO) JSONObject.toBean(array.getJSONObject(i), GoodsProductSpecDTO.class);
					product = new ProductWithBLOBs();
					product.setProductId(temp.getProductId());
					product.setBarcode(temp.getBn());
					product.setBn(temp.getBn());
					product.setCost(temp.getCost());
					product.setGoodsId(dto.getGoodsId());
					product.setGoodsType("normal");
					product.setMarketable(temp.getMarketable());
					product.setMktprice(temp.getMktprice());
					product.setName(dto.getName());
					product.setPrice(temp.getPrice());
					product.setStore(temp.getStore());
					product.setStorePlace(temp.getStorePlace());
					product.setTitle(dto.getName());
					product.setSpecDesc(temp.getSpecDesc());
					product.setSpecInfo(temp.getSpecInfo());
					product.setPicturePath(temp.getPicturePath());
					product.setDisabled("0");
					if ("1".equalsIgnoreCase(temp.getMarketable())) {
						product.setUptime(DateUtils.getCurrentlyDate());
					}
					product.setLastModify(DateUtils.getCurrentlyDate());
					if (null == temp.getProductId()) {// 新增

						// 判断货号是否存在
						if (StringUtils.isNotBlank(temp.getBn())) {
							count = productMapper.findProductByBn(new String[] { temp.getBn() }, temp.getProductId());
							if (count > 0) { // 存在相同商品编号
								head.setRetCode(ErrorCode.IS_EXIST_PRODUCT);
								return new ResultObject(head);
							}
						}

						productMapper.insertSelective(product);
						ship.setProductId(Long.valueOf(product.getProductId()));
					} else {
						productIds.remove(temp.getProductId());
						productMapper.updateByPrimaryKeyWithBLOBs(product);
						ship.setProductId(Long.valueOf(temp.getProductId()));
					}

					// 5插入商品，货品，图片关系表
					ship.setGoodsId(Long.valueOf(dto.getGoodsId()));
					if (StringUtils.isNotBlank(temp.getPicIds())) {
						picIds = temp.getPicIds().split("\\|");
						for (int k = 0; k < picIds.length; k++) {
							ship.setPictureId(Long.valueOf(picIds[k]));
							goodsProductPicShipMapper.insertSelective(ship);
						}
					} else { // 没有选择图片，则设置默认第一张图片
						ship.setPictureId(dto.getDefaultPicId());
						goodsProductPicShipMapper.insertSelective(ship);

					}
					// 添加商品规格的关系
					/* 加入了分店sku后，此处不进行插入，没有使用到 2015.05.12 */
					SpecGoodsIndex specGoods = new SpecGoodsIndex();
					specGoods.setGoodsId(dto.getGoodsId());
//					SpecGoodsIndexExample specGoodsIndexExample=new SpecGoodsIndexExample();
//					specGoodsIndexExample.createCriteria().andGoodsIdEqualTo(dto.getGoodsId());
//					specGoodsIndexMapper.deleteByExample(specGoodsIndexExample);
					specGoods.setProductId(product.getProductId()); // 1|颜色:1|红色,2|款式:6|男装
					if (StringUtils.isNotBlank(temp.getSpecDesc())) {
						String[] specValueItems = (temp.getSpecDesc()).split(",");
						for (int j = 0; j < specValueItems.length; j++) {
							String[] items = specValueItems[j].split(":");
							specGoods.setSpecId(Integer.valueOf(items[0].split("\\|")[0]));
							specGoods.setSpecValueId(Integer.valueOf(items[1].split("\\|")[0]));
							specGoodsIndexMapper.insertSelective(specGoods);
						}
					}

				}

				// 需要删除编辑时删除了的货品2015.06.27 xiaox
				if (productIds != null && productIds.size() > 0) { // 剩下的都是要删除的
					productMapper.batchDelete(productIds);
				}

			}
		} else { // 不选择规格，单个货品
			product.setBn(dto.getBn());
			product.setGoodsType("normal");
			product.setPrice(dto.getPrice());
			product.setCost(dto.getCost());
			product.setMktprice(dto.getMktPrice());
			product.setName(dto.getName());
			product.setStore(dto.getStore());
			product.setLastModify(new Date());
			product.setUptime(new Date());
			product.setMarketable(dto.getMarketable());
			product.setProductId(Integer.valueOf(dto.getProductId()));
			product.setPicturePath(dto.getDefaultPic());
			product.setDisabled("0");
			productMapper.updateByPrimaryKeyWithBLOBs(product);

			// 5添加商品，货品，图片关系表:没有选择规格，则所有图片都关联到该货品上
			ship.setGoodsId(Long.valueOf(dto.getGoodsId()));
			ship.setProductId(Long.valueOf(dto.getProductId()));
			// 先删除所有
			GoodsProductPicShipExample picExample = new GoodsProductPicShipExample();
			picExample.createCriteria().andGoodsIdEqualTo(Long.valueOf(dto.getGoodsId()));
			goodsProductPicShipMapper.deleteByExample(picExample);
			if (picIds != null && picIds.length > 0) {
				picIdsItems = picIds[0].split("\\|");
				for (int k = 0; k < picIdsItems.length; k++) {
					ship.setPictureId(Long.valueOf(picIdsItems[k]));
					goodsProductPicShipMapper.insertSelective(ship);
				}
			}
		}

		// 更新商品
		GoodsWithBLOBs goods = new GoodsWithBLOBs();
		BeanUtils.copyProperties(dto, goods);
		goods.setP21(dto.getIsOpenSpec());
		goods.setSpecDesc(dto.getGoodsSpecJson());
		goods.setLastModify(DateUtils.getCurrentDate(null));
		goods.setDbn(setGoodsBn(dto.getRootCategory()));
		goods.setBigPic(dto.getDefaultPic());
		goods.setSmallPic(dto.getDefaultPic());
		goods.setMidPic(dto.getDefaultPic());
		goodsMapper.updateByPrimaryKeySelective(goods);
		if (goods.getMarketable().equals("1")) {
			// 增加到索引库
			List<GoodsInfoListDTO> goodsInfoListDTO = goodsMapper.selectGoodsByGoodsIds(goods.getGoodsId().toString());
			HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.addGoodsOneIndex");
			searchClientService.doServiceByServer(new RequestObject(headObject, JSONArray.fromObject(goodsInfoListDTO)));
		}
		log.info("end[GoodsService.updateGoodInfoAtWeb]");
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head);
	}

	/**
	 * 
	 * @Title: selectByGoodsId
	 * @Description: 根据商品id查询商品对象
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @param @param data
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectByGoodsId(Object data) {
		log.info("start[GoodsService.selectByGoodsId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Integer goodsId = jsonObject.getInt("goodsId");
		GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);
		log.info("end[GoodsService.selectByGoodsId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(goods));
	}

	/**
	 * 
	 * @Description: 通过商品编号进行判断是否存在
	 * @param @param data
	 * @param @return
	 * @author xiaox
	 * @date 2015-4-14 下午5:30:45
	 */
	public int findGoodsByBn(String data) {
		return goodsMapper.findGoodsByBn(data);
	}

	/**
	 * 
	 * @Description: 编辑时通过商品编号进行判断是否存在
	 * @param @param data
	 * @param @return
	 * @author xiaox
	 * @date 2015-4-14 下午5:30:45
	 */
	public int findEditGoodsByBn(String data, Integer goodsId) {
		return goodsMapper.findEditGoodsByBn(data, goodsId);
	}

	/**
	 * @description <一句话方法简述>
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月13日
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object getGoodAttributeValue(Object data) throws Exception {
		log.info("start[GoodsService.getGoodAttributeValue]");
		JSONObject content = JSONObject.fromObject(data);
		GoodAttributeValueDTO record = new GoodAttributeValueDTO();
		record.setCatId(content.getInt("catId"));
		record.setGoodsId(content.getInt("goodsId"));
		List<GoodAttributeValueDTO> resultList = attributeGoodesIndexMapper.selectAttributeByParam(record);
		log.info("end[GoodsService.getGoodAttributeValue]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(resultList));
	}

	// 数组去掉重复数据
	private String[] arrayUnique(String[] a) {
		Set<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(a));
		return set.toArray(new String[0]);
	}

	// 数组去掉空值
	public static String[] arrayNotEmpty(String[] a) {
		List<String> tmp = new ArrayList<String>();
		if (a != null && a.length > 0) {
			for (String str : a) {
				if (str != null && str.trim().length() != 0) {
					tmp.add(str);
				}
			}
			return tmp.toArray(new String[0]);
		} else {
			return null;
		}
	}

	private String setGoodsBn(String rootId) {
		String bn = BNGenerator.getOtherBn(); // 其他
		if (rootId.equals(GlobalStatic.CATALOG_CAR)) { // 整车
			bn = BNGenerator.getCarBn();
		} else if (rootId.equals(GlobalStatic.CATALOG_MAINTAIN)) { // 保养
			bn = BNGenerator.getMaintainBn();
		} else if (rootId.equals(GlobalStatic.CATALOG_ACCESSORY)) { // 配件
			bn = BNGenerator.getAccessoryBn();
		} else if (rootId.equals(GlobalStatic.CATALOG_BOUTIQUE)) { // 精品
			bn = BNGenerator.getBoutiqueBn();
		}

		return bn;
	}

	private BigDecimal str2BigDecimal(String str) {
		BigDecimal b = new BigDecimal(0);
		if (StringUtils.isNotBlank(str)) {
			b = BigDecimal.valueOf(Double.valueOf(str));
		}
		return b;
	}

	/**
	 * 
	 * @description 卖家中心获取商品列表
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-4-27
	 * @param data
	 * @return
	 */
	public Object findGoods(Object data) {
		log.info("start[GoodsService.findGoods]");
		GoodsQryDTO qryDTO = (GoodsQryDTO) JSONObject.toBean(JSONObject.fromObject(data), GoodsQryDTO.class);
		PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows() <= 0 ? GlobalStatic.DEFAULT_PAGE_SIZE_10 : qryDTO.getRows());

		List<Goods> goods = goodsMapper.findGoods(qryDTO);
		ResultPage<Goods> page = new ResultPage<Goods>(goods);
		log.info("end[GoodsService.findGoods]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}
	
	/**
	 * 
	 * @description 卖家中心，获取需要参加活动的商品列表
	 */
	public Object findActivityGoods(Object data) {
		log.info("start[GoodsService.findActivityGoods]");
		GoodsQryDTO qryDTO = (GoodsQryDTO) JSONObject.toBean(JSONObject.fromObject(data), GoodsQryDTO.class);
		PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows() <= 0 ? GlobalStatic.DEFAULT_PAGE_SIZE_10 : qryDTO.getRows());

		List<Goods> goods = goodsMapper.findActivityGoods(qryDTO);
		ResultPage<Goods> page = new ResultPage<Goods>(goods);
		log.info("end[GoodsService.findActivityGoods]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}
	
	/**
	 * 
	 * @description 卖家中心，获取需要参加活动的商品列表
	 */
	public Object selectGoodsForActivity(Object data) {
		log.info("start[GoodsService.selectGoodsForActivity]");
		GoodsQryDTO qryDTO = (GoodsQryDTO) JSONObject.toBean(JSONObject.fromObject(data), GoodsQryDTO.class);
		PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows() <= 0 ? GlobalStatic.DEFAULT_PAGE_SIZE_10 : qryDTO.getRows());
		List<GoodsInfoListDTO> goods = goodsMapper.selectGoodsForActivity(qryDTO);
		ResultPage<GoodsInfoListDTO> page = new ResultPage<GoodsInfoListDTO>(goods);
		page.setRows(goods);
		log.info("end[GoodsService.selectGoodsForActivity]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}
	
	/**
	 * 
	 * @description 卖家中心，获取添加活动的商品列表
	 */
	public Object findAddActivityGoods(Object data) {
		log.info("start[GoodsService.findAddActivityGoods]");
		GoodsQryDTO qryDTO = (GoodsQryDTO) JSONObject.toBean(JSONObject.fromObject(data), GoodsQryDTO.class);
		//PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows() <= 0 ? GlobalStatic.DEFAULT_PAGE_SIZE_10 : qryDTO.getRows());

		List<Goods> goods = goodsMapper.findAddActivityGoods(qryDTO);
		ResultPage<Goods> page = new ResultPage<Goods>(goods);
		page.setRows(goods);
		log.info("end[GoodsService.findAddActivityGoods]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}
	
	/**
	 * 卖家中心，更新活动商品
	 * @param data
	 * @return
	 */
	public Object updateActivityGoods(Object data) {
		log.info("start[GoodsService.updateActivityGoods]");
		Integer[] ids = (Integer[]) data;
		goodsMapper.updateActivityGoods(ids);
		log.info("end[GoodsService.updateActivityGoods]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}
	
	/**
	 * 卖家中心，更新单个活动商品
	 * @param data
	 * @return
	 */
	public Object updateActivityGoodsById(Object data) {
		log.info("start[GoodsService.updateActivityGoodsById]");
		Integer goodsId = (Integer) data;
		goodsMapper.updateActivityGoodsById(goodsId);
		log.info("end[GoodsService.updateActivityGoodsById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}

	/**
	 * 
	 * @description 获得预警中的商品列表
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-4-29
	 * @param data
	 * @return
	 */
	public Object findWarnGoods(Object data) {
		log.info("start[GoodsService.findWarnGoods]");
		GoodsQryDTO qryDTO = (GoodsQryDTO) JSONObject.toBean(JSONObject.fromObject(data), GoodsQryDTO.class);
		PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows() <= 0 ? GlobalStatic.DEFAULT_PAGE_SIZE_10 : qryDTO.getRows());
		ResultPage<Goods> page = new ResultPage<Goods>(goodsMapper.findWarnGoods(qryDTO));
		log.info("end[GoodsService.findWarnGoods]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}

	/**
	 * @Title: selectByProductId
	 * @Description: TODO(根据productId查询商品对象)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-4 下午1:15:13
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectByProductId(Object data) {
		log.info("start[GoodsService.selectByProductId]");
		GoodsWithBLOBs goods = goodsMapper.selectByProductId((Integer) data);
		log.info("end[GoodsService.selectByProductId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), goods);
	}

	/**
	 * @Title: selectAttrByGoodsId
	 * @Description: TODO(根据商品id查询商品属性)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-5 下午2:58:49
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectAttrByGoodsId(Object data) {
		log.info("start[GoodsService.selectAttrByGoodsId]");
		List<GoodAttributeValueDTO> list = attributeGoodsIndexMapper.selectAttributeByGoodsId((Integer) data);
		log.info("end[GoodsService.selectAttrByGoodsId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
	}

	/**
	 * 查询所有商品扩展属性（构建索引用）
	 * 
	 * @author wanghb
	 * @Description:
	 * @param data
	 * @return
	 */
	public Object selectAllAttr(Object data) {
		log.info("start[GoodsService.selectAttrByGoodsId]");
		List<GoodAttributeValueDTO> list = attributeGoodsIndexMapper.selectAllAttribute((Integer) data);
		log.info("end[GoodsService.selectAttrByGoodsId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
	}

	/**
	 * @Title: selectAccessoryByGoodsId
	 * @Description: TODO(根据商品id查询套餐优惠信息)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-5 下午4:36:06
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Object selectAccessoryByGoodsId(Object data) {
			log.info("start[GoodsService.selectAccessoryByGoodsId]");
			JSONObject jsonObject = JSONObject.fromObject(data);
			Long goodsId = jsonObject.getLong("goodsId");
			jsonObject.clear();
			AccessoryGoodsExample example = new AccessoryGoodsExample();
			example.createCriteria().andGoodsIdEqualTo(goodsId);
			List<AccessoryGoods> page = accessoryGoodsMapper.selectByExample(example);
			for (AccessoryGoods accessoryGoods : page) {
				List<Integer> productIdList = null;
				List<String> keyWordList;
				List<Integer> catIdList = null;
				List<Integer> brandIdList = null;
				List<String> tagList = null;
				if ("normal".equals(accessoryGoods.getActype())) {
					// 选择几件商品作为配件
					if (!StringUtil.isEmpty(accessoryGoods.getAccessoryGoods())) {
						productIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject("[" + accessoryGoods.getAccessoryGoods() + "]"), Integer.class);
						List<ProductWithBLOBs> products = productMapper.selectByProductIds(productIdList);
						BigDecimal sumPrice=new BigDecimal(0);
						for (ProductWithBLOBs productWithBLOBs : products) {
							sumPrice=sumPrice.add(productWithBLOBs.getPrice());
						}
						accessoryGoods.setSumPrice(sumPrice.doubleValue());
						accessoryGoods.setProducts(products);
					}
				} else if ("group".equals(accessoryGoods.getActype())) {
					List<Integer> goodsIdList = null;
					// 选择一组商品搜索结果作为配件
					if (!StringUtil.isEmpty(accessoryGoods.getSearchKeywords())) {
						keyWordList = java.util.Arrays.asList(accessoryGoods.getSearchKeywords().split(","));
						if (null!=keyWordList && keyWordList.size() >= 1) {
							List<GoodsKeywords> goodsKeyList = goodsKeywordsMapper.selectByKeyWordList(keyWordList);
							goodsIdList = new ArrayList<Integer>();
							for (int i = 0; i < goodsKeyList.size(); i++) {
								goodsIdList.add(goodsKeyList.get(i).getGoodsId().intValue());
							}
						}
					}
					if (!StringUtil.isEmpty(accessoryGoods.getCatIds())) {
						catIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject("[" + accessoryGoods.getCatIds() + "]"), Integer.class);
					}
					if (!StringUtil.isEmpty(accessoryGoods.getBrandIds())){
						brandIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject("[" + accessoryGoods.getBrandIds() + "]"), Integer.class);
					}
					if (!StringUtil.isEmpty(accessoryGoods.getTags())) {
						tagList = java.util.Arrays.asList(accessoryGoods.getTags().split(","));
					}
					List<ProductWithBLOBs> products = productMapper.selectByParams(catIdList, brandIdList, goodsIdList, tagList, accessoryGoods.getPriceform(), accessoryGoods.getPriceto(), accessoryGoods.getGoodsId().intValue());
					BigDecimal sumPrice=new BigDecimal(0);
					for (ProductWithBLOBs productWithBLOBs : products) {
						sumPrice=sumPrice.add(productWithBLOBs.getPrice());
					}
					accessoryGoods.setSumPrice(sumPrice.doubleValue());
					accessoryGoods.setProducts(products);
				}
			}
			log.info("end[GoodsService.selectAccessoryByGoodsId]");
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}

	/**
	 * 
	 * @description 通过商品id获得图片
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-4
	 * @param data
	 * @return
	 */
	public Object findPicByGoodsId(Object data) {
		log.info("start[GoodsService.findPicByGoodsId]");
		List<GoodsProductPicShipDTO> list = null;
		list = goodsProductPicShipMapper.findPicByGoodsId((Integer) data);
		log.info("end[GoodsService.findPicByGoodsId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
	}

	/**
	 * @Title: selectRelatedByGoodsId
	 * @Description: TODO(根据goodsId查询相关商品)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-7 下午8:30:53
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectRelatedByGoodsId(Object data) {
		log.info("start[GoodsService.findRelateByGoodsId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Long goodsId = jsonObject.getLong("goodsId");
		List<GoodsRelated> relatedList = goodsRelatedMapper.selectRelatedByGoodsId(goodsId);
		List<GoodsWithBLOBs> page = null;
		if (relatedList != null && relatedList.size() >= 1) {
			List<Integer> goodsIdList = new ArrayList<Integer>();
			for (int i = 0; i < relatedList.size(); i++) {
				if (relatedList.get(i).getGoods1().equals(goodsId)) {
					goodsIdList.add(relatedList.get(i).getGoods2().intValue());
				} else if (relatedList.get(i).getGoods2().equals(goodsId) && relatedList.get(i).getManual().equals("both")) {
					goodsIdList.add(relatedList.get(i).getGoods1().intValue());
				}
			}
			if (goodsIdList != null && goodsIdList.size() >= 1) {// goodsIdList存在相同数值？？？？？				
				page = goodsMapper.selectByGoodsIds(goodsIdList);
			}
		}
		log.info("end[GoodsService.findRelateByGoodsId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}

	/**
	 * @Title: selectGoodsByAccessoryId
	 * @Description: TODO(根据优惠套餐id分页查询商品)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-9 上午11:41:44
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectGoodsByAccessoryId(Object data) {

		log.info("start[GoodsService.selectGoodsByAccessoryId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Long accessoryId = jsonObject.getLong("accessoryId");
		String isPager = jsonObject.getString("isPager");// 是否分页，1->是，0->否
		Object productIdObject = jsonObject.get("productId");
		Integer productId = null;
		if (productIdObject != null) {
			productId = (Integer) productIdObject;
		}
		Integer pageIndex = null;
		Integer pageSize = null;
		if ("1".equals(isPager)) {
			pageIndex = jsonObject.getInt("pageIndex");
			pageSize = jsonObject.getInt("pageSize");
		}
		jsonObject.clear();
		ResultPage<ProductWithBLOBs> productPage = null;
		// List<GoodsWithBLOBs> goodsList = null;
		List<ProductWithBLOBs> productList = null;
		AccessoryGoods accessoryGoods = accessoryGoodsMapper.selectByPrimaryKey(accessoryId);
		if (accessoryGoods != null) {
			List<Integer> productIdList = null;
			List<String> keyWordList;
			List<Integer> catIdList = null;
			List<Integer> brandIdList = null;
			List<String> tagList = null;
			if ("normal".equals(accessoryGoods.getActype())) {
				// 选择几件商品作为配件
				if (accessoryGoods.getAccessoryGoods() != null && !"".equals(accessoryGoods.getAccessoryGoods().trim())) {
					productIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject("[" + accessoryGoods.getAccessoryGoods() + "]"), Integer.class);
					if ("1".equals(isPager)) {
						PageHelper.startPage(pageIndex, pageSize);
						productPage = new ResultPage(productMapper.selectByProductIds(productIdList));
					} else {
						if (productIdList == null) {
							productIdList = new ArrayList<Integer>();
						}
						if (productId != null) {
							productIdList.add(productId);
						}
						productList = productMapper.selectByProductIds(productIdList);
					}
				}
			} else if ("group".equals(accessoryGoods.getActype())) {
				List<Integer> goodsIdList = null;
				// 选择一组商品搜索结果作为配件
				if (accessoryGoods.getSearchKeywords() != null && !"".equals(accessoryGoods.getSearchKeywords().trim())) {
					String keyWords = accessoryGoods.getSearchKeywords();
					keyWordList = java.util.Arrays.asList(keyWords.split(","));
					if (keyWordList != null && keyWordList.size() >= 1) {
						List<GoodsKeywords> goodsKeyList = goodsKeywordsMapper.selectByKeyWordList(keyWordList);
						if (goodsKeyList != null && goodsKeyList.size() >= 1) {
							goodsIdList = new ArrayList<Integer>();
							for (int i = 0; i < goodsKeyList.size(); i++) {
								goodsIdList.add(goodsKeyList.get(i).getGoodsId().intValue());
							}
						}
					}
				}
				if (accessoryGoods.getCatIds() != null && !"".equals(accessoryGoods.getCatIds().trim().trim())) {
					catIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject("[" + accessoryGoods.getCatIds() + "]"), Integer.class);
				}
				if (accessoryGoods.getBrandIds() != null && !"".equals(accessoryGoods.getBrandIds().trim())) {
					brandIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject("[" + accessoryGoods.getBrandIds() + "]"), Integer.class);
				}
				if (accessoryGoods.getTags() != null && !"".equals(accessoryGoods.getTags().trim())) {
					String tagsString = accessoryGoods.getSearchKeywords();
					tagList = java.util.Arrays.asList(tagsString.split(","));
				}

				if ("1".equals(isPager)) {
					PageHelper.startPage(pageIndex, pageSize);
					productPage = new ResultPage<ProductWithBLOBs>(productMapper.selectByParams(catIdList, brandIdList, goodsIdList, tagList, accessoryGoods.getPriceform(), accessoryGoods.getPriceto(), accessoryGoods.getGoodsId().intValue()));
				} else {
					productList = productMapper.selectByParams(catIdList, brandIdList, goodsIdList, tagList, accessoryGoods.getPriceform(), accessoryGoods.getPriceto(), accessoryGoods.getGoodsId().intValue());
					ProductWithBLOBs product = null;
					if (productId != null) {
						product = productMapper.selectByPrimaryKey(productId);
					}
					// GoodsWithBLOBs goods =
					// goodsMapper.selectByPrimaryKey(accessoryGoods.getGoodsId().intValue());
					if (productList == null) {
						productList = new ArrayList<ProductWithBLOBs>();
					}
					if (product != null && product.getProductId() != null) {
						productList.add(product);
					}
				}
			}
		}
		log.info("end[GoodsService.selectGoodsByAccessoryId]");
		if ("1".equals(isPager)) {
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(productPage));
		} else {
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), productList);
		}

	}

	/**
	 * @Title: saveGoodsViewHistory
	 * @Description: TODO(保存商品浏览历史记录对象)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-9 下午6:38:13
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object saveGoodsViewHistory(Object data) {
		log.info("start[GoodsService.saveGoodsViewHistory]");
		GoodsViewHistory history = (GoodsViewHistory) data;
		GoodsViewHistoryKey key = new GoodsViewHistoryKey();
		key.setMemberId(history.getMemberId());
		key.setGoodsId(history.getGoodsId());
		GoodsViewHistory historyFromDB = goodsViewHistoryMapper.selectByPrimaryKey(key);
		if (historyFromDB != null) {
			System.out.println(goodsViewHistoryMapper.updateByPrimaryKey(history));
		} else {
			System.out.println(goodsViewHistoryMapper.insert((GoodsViewHistory) data));
		}
		log.info("end[GoodsService.saveGoodsViewHistory]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}

	/**
	 * 
	 * @description 通过分类id与商品id获取所有的扩展属性
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-9
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object findAttributeByParam(Object data) throws Exception {
		log.info("start[GoodsService.getGoodAttributeValue]");
		JSONObject content = JSONObject.fromObject(data);
		GoodAttributeValueDTO record = new GoodAttributeValueDTO();
		record.setCatId(content.getInt("catId"));
		record.setGoodsId(content.getInt("goodsId"));
		List<GoodAttributeValueDTO> resultList = attributeGoodesIndexMapper.findAttributeByParam(record);
		log.info("end[GoodsService.getGoodAttributeValue]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(resultList));
	}

	/**
	 * @Title: increaseViewCount
	 * @Description: TODO(增加商品浏览次数)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-13 上午10:23:03
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object increaseViewCount(Object data) {
		log.info("start[GoodsService.increaseViewCount]");
		goodsMapper.increaseViewCount((Integer) data);
		log.info("end[GoodsService.increaseViewCount]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}

	/**
	 * @description <b>增加商品评论数</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-6-26
	 * @param @param data
	 * @param @return
	 * @return Object
	 */
	public Object addCommentsCount(Object data) {
		log.info("start[GoodsService.addCommentsCount]");
		HeadObject headObject = new HeadObject();
		try {
			goodsMapper.addCommentsCount((Long) data);
			headObject.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[GoodsService.addCommentsCount]");
		return new ResultObject(headObject);
	}

	/**
	 * @Title: selectAccessoryById
	 * @Description: TODO(根据id查询优惠套装对象)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-14 上午10:28:38
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectAccessoryById(Object data) {
		log.info("start[GoodsService.selectAccessoryById]");
		HeadObject headObject=new HeadObject();
		AccessoryGoods ag = null;
		try{
			ag = accessoryGoodsMapper.selectByPrimaryKey((Long) data);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[GoodsService.selectAccessoryById]");
		return new ResultObject(headObject, ag);
	}

	/**
	 * @Title: selectTopSalesByGoodsId
	 * @Description: TODO(根据店铺id查询本店热销商品)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-16 下午2:39:46
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectTopSalesByGoodsId(Object data) {
		log.info("start[GoodsService.selectTopSalesByGoodsId]");
		List<GoodsWithBLOBs> list = goodsMapper.selectTopSalesByGoodsId((Map<String, Object>) data);
		log.info("end[GoodsService.selectTopSalesByGoodsId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
	}

	/**
	 * 
	 * @description 通过品牌id查商品
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-19
	 * @param data
	 * @return
	 */
	public Object findGoodsByBrandId(Object data) {
		log.info("start[GoodsService.findGoodsByBrandId]");
		List<GoodsWithBLOBs> list = goodsMapper.findGoodsByBrandId((Integer) data);
		log.info("end[GoodsService.findGoodsByBrandId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
	}

	/**
	 * @Title: addFavCountByProductIdList
	 * @Description: TODO(根据货品id列表增加商品的被收藏次数)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-6-1 上午11:39:13
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object addFavCountByProductIdList(Object data) {
		log.info("start[GoodsService.addFavCountByProductIdList]");
		List<Integer> productIdList = (List<Integer>) data;
		List<GoodsWithBLOBs> goodsList = goodsMapper.selectByProductIdList(productIdList);
		if (goodsList != null && goodsList.size() >= 1) {
			List<Integer> goodsIdList = new ArrayList<Integer>();
			for (int i = 0; i < goodsList.size(); i++) {
				goodsIdList.add(goodsList.get(i).getGoodsId());
			}
			if (goodsIdList != null && goodsIdList.size() >= 1) {
				goodsMapper.addFavCountByGoodsIdList(goodsIdList);
			}
		}
		log.info("end[GoodsService.addFavCountByProductIdList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}

	/**
	 * @description <b>修改山商品购买次数</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-7-1
	 * @param @param data
	 * @param @return
	 * @return Object
	 */
	public Object addGoodsBuyCount(Object data) {
		log.info("start[GoodsService.updateGoodsBuyCountByIds]");
		try {
			JSONObject buyCountJSON = JSONObject.fromObject(data);
			goodsMapper.addGoodsBuyCount(buyCountJSON.getLong("goodsId"), buyCountJSON.getInt("buyCount"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("end[GoodsService.updateGoodsBuyCountByIds]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}

	/**
	 * 
	 * @description 本地暂存商品获取
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-6-11
	 * @param data
	 * @return
	 */
	public Object findLocationGoods(Object data) {
		log.info("start[GoodsService.findLocationGoods]");
		GoodsQryDTO qryDTO = (GoodsQryDTO) JSONObject.toBean(JSONObject.fromObject(data), GoodsQryDTO.class);
		PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows() <= 0 ? GlobalStatic.DEFAULT_PAGE_SIZE_10 : qryDTO.getRows());
		ResultPage<Goods> page = new ResultPage<Goods>(goodsMapper.findLocationGoods(qryDTO));
		log.info("end[GoodsService.findLocationGoods]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}

	/**
	 * 
	 * @description 查找后台下架商品
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-6-23
	 * @param data
	 * @return
	 */
	public Object findViolationGoods(Object data) {
		log.info("start[GoodsService.findViolationGoods]");
		GoodsQryDTO qryDTO = (GoodsQryDTO) JSONObject.toBean(JSONObject.fromObject(data), GoodsQryDTO.class);
		PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows() <= 0 ? GlobalStatic.DEFAULT_PAGE_SIZE_10 : qryDTO.getRows());
		ResultPage<Goods> page = new ResultPage<Goods>(goodsMapper.findViolationGoods(qryDTO));
		log.info("end[GoodsService.findViolationGoods]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}

	/**
	 * 
	 * @description 下架店铺的所有商品
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-6-26
	 * @param data
	 * @return
	 */
	public Object downGoodsByCompanyId(Object data) {
		log.info("start[GoodsService.downGoodsByCompanyId]");
		Integer[] ids = (Integer[]) data;
		goodsMapper.downGoodsByCompanyId(ids);
		log.info("end[GoodsService.downGoodsByCompanyId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}

	/**
	 * @Title: selectGoodsBrandById
	 * @Description: TODO(根据商品id查询对象)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-6-29 下午9:14:19
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectGoodsBrandById(Object data) {
		log.info("start[GoodsService.selectGoodsBrandById]");
		GoodsWithBLOBs goods = goodsMapper.selectGoodsBrandById((Integer) data);
		log.info("end[GoodsService.selectGoodsBrandById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), goods);
	}

	/**
	 * @Title: selectGoodsBrandById
	 * @Description: TODO(根据商品id查询对象)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-6-29 下午9:14:19
	 * @version 1.0.0
	 * @param @param data
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectGoodsBrandByProductId(Object data) {
		log.info("start[GoodsService.selectGoodsBrandById]");
		GoodsWithBLOBs goods = goodsMapper.selectGoodsBrandByProductId((Integer) data);
		log.info("end[GoodsService.selectGoodsBrandById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), goods);
	}

	/**
	 * 
	 * @description 获得所有指定节点的子节点
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-7-10
	 * @param data
	 * @return
	 */
	public Object findAllChildList(Object data) {
		String childIds = goodsMapper.getAllChildGoodsCat((Integer) data);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), childIds);
	}

	public Object findGoodsCarById(Object data){
		HeadObject headObject=new HeadObject();
		GoodsCarDTO goodsCarDTO= null;
		try{
			Long [] goods={(Long) data};
			List<GoodsCarDTO> cars = goodsMapper.selectGoodsCar(goods);
			if(cars.size()>0){
				goodsCarDTO = cars.get(0);	
			}
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject,goodsCarDTO);
	}
	
	/**
	 * @description <b>获取指定车型的车型商品</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月28日
	 * @param data
	 * @return Object
	 */
	public Object findGoodsByCarDept(Object data) {
		Map<String, Object> params = (Map<String, Object>) data;
		HeadObject headObject = new HeadObject();
		List<Goods> goods = null;
		try {
			goods = goodsMapper.selectGoodsByCarDept(params);
			headObject.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(headObject, goods);
	}

	public Object listGoods(Object data) throws Exception {
		log.info("start[GoodsService.listGoods]");
		Map<String, Object> params = (Map<String, Object>) data;
		String catId = (String) params.get("catId");
		// 根据虚拟分类获取商品分类
		Set<Integer> cateList = new HashSet<Integer>();// 分类集合
		if (null != catId) {// 虚拟分类
			Object provider = ApplicationContextUtil.getBeanByName("categoryService");
			// 根据方法名及参数类型获得方法对象
			Method method = provider.getClass().getMethod("getVirtualCategoryByCatId", Object.class);
			// 调用方法
			Object content = null;
			content = method.invoke(provider, Integer.valueOf(catId));
			ResultObject resultObject = (ResultObject) content;
			JSONObject jsonobject = JSONObject.fromObject(resultObject.getContent());
			JSONObject jsonobject1 = jsonobject.getJSONObject("cate");
			JSONObject jsonobject2 = jsonobject1.getJSONObject("filter");
			JSONArray jsonobject3 = jsonobject2.getJSONArray("ct");
			Object[] obj = jsonobject3.toArray();
			for (int i = 0; i < obj.length; i++) {
				JSONObject jsonobject4 = JSONObject.fromObject(obj[i]);
				cateList.add(jsonobject4.getInt("id"));
				// TODO：待优化
				JSONObject params2 = new JSONObject();
				params2.put("parentCatId", jsonobject4.getInt("id"));
				params2.put("disabled", "0");
				provider = ApplicationContextUtil.getBeanByName("categoryService");
				// 根据方法名及参数类型获得方法对象
				method = provider.getClass().getMethod("getCategoryByExample", Object.class);
				// 调用方法
				content = null;
				content = method.invoke(provider, params2);
				ResultObject resultObjectSub = (ResultObject) content;
				List<GoodsCat> listSub = com.alibaba.fastjson.JSONArray.parseArray(resultObjectSub.getContent().toString(), GoodsCat.class);
				if (null != listSub && listSub.size() > 0) {
					for (int j = 0; j < listSub.size(); j++) {
						cateList.add(listSub.get(j).getCatId());
					}
				}
			}
		}

		if (!cateList.isEmpty()) {
			List<Integer> list = new ArrayList<Integer>(cateList);
			params.put("catIds", list);
		}

		List<GoodsForAppVo> goods = goodsMapper.listGoods(params);
		log.info("end[GoodsService.listGoods]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), goods);
	}
}