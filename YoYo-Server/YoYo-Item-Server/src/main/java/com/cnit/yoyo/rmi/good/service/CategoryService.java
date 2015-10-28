/**
 * 文 件 名   :  CategoryService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年3月21日 下午2:09:52
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.good.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.AttributeMapper;
import com.cnit.yoyo.dao.goods.AttributeValueMapper;
import com.cnit.yoyo.dao.goods.CatSpecShipMapper;
import com.cnit.yoyo.dao.goods.GoodsCatMapper;
import com.cnit.yoyo.dao.goods.GoodsVirtualCatMapper;
import com.cnit.yoyo.dao.goods.SpecGoodsIndexMapper;
import com.cnit.yoyo.dao.goods.SpecMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.TreeNode;
import com.cnit.yoyo.dto.spec.SpecDTO;
import com.cnit.yoyo.model.goods.Attribute;
import com.cnit.yoyo.model.goods.AttributeExample;
import com.cnit.yoyo.model.goods.AttributeValue;
import com.cnit.yoyo.model.goods.AttributeValueExample;
import com.cnit.yoyo.model.goods.CatSpecShip;
import com.cnit.yoyo.model.goods.CatSpecShipExample;
import com.cnit.yoyo.model.goods.GoodsCat;
import com.cnit.yoyo.model.goods.GoodsCatExample;
import com.cnit.yoyo.model.goods.GoodsCatWithBLOBs;
import com.cnit.yoyo.model.goods.GoodsVirtualCat;
import com.cnit.yoyo.model.goods.GoodsVirtualCatExample;
import com.cnit.yoyo.model.goods.GoodsVirtualCatWithBLOBs;
import com.cnit.yoyo.model.goods.Spec;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.TreeUtils;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
@Service("categoryService")
public class CategoryService {
	public static final Logger log = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	GoodsCatMapper goodsCatMapper;
	@Autowired
	AttributeMapper attributeMapper;
	@Autowired
	AttributeValueMapper attributeValueMapper;
	@Autowired
	CatSpecShipMapper catSpecShipMapper;
	@Autowired
	private SpecGoodsIndexMapper specGoodsIndexMapper;

	@Autowired
	SpecMapper specMapper;
	@Autowired
	public GoodsVirtualCatMapper goodsVirtualCatMapper;

	/**
	 * @description 新增或修改虚拟分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月26日
	 * @param data
	 * @return
	 */
	public Object saveVirtualCategory(Object data) throws Exception {
		log.info("start[CategoryService.saveVirtualCategoryByCatId]");
		JSONObject content = JSONObject.fromObject(data);
		String filter = content.get("filter").toString();
		content.remove("filter");
		GoodsVirtualCatWithBLOBs goodsCat = (GoodsVirtualCatWithBLOBs) JSONObject.toBean(content, GoodsVirtualCatWithBLOBs.class);
		GoodsVirtualCatWithBLOBs parentCat = goodsVirtualCatMapper.selectByPrimaryKey(goodsCat.getParentCatId());// 父级（用户选择的）
		goodsCat.setFilter(filter);
		GoodsVirtualCatExample example = new GoodsVirtualCatExample();
		example.createCriteria().andCatNameEqualTo(goodsCat.getCatName());
		List<GoodsVirtualCat> temp = goodsVirtualCatMapper.selectByExample(example);

		if (goodsCat.getCatId() != null) {
			if (temp != null && temp.size() > 0 && (!temp.get(0).getCatId().equals(goodsCat.getCatId()))) {
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			GoodsVirtualCatWithBLOBs currentCat = goodsVirtualCatMapper.selectByPrimaryKey(goodsCat.getCatId());// 当前
			goodsVirtualCatMapper.updateByPrimaryKeyWithBLOBs(goodsCat);
			if (goodsCat.getParentCatId() != currentCat.getParentCatId()) {// 父级是否发生变化
				// 减去原来父级子类个数
				if (null != currentCat.getParentCatId() && currentCat.getParentCatId() != 0) {
					GoodsVirtualCatWithBLOBs parentCatDB = goodsVirtualCatMapper.selectByPrimaryKey(currentCat.getParentCatId());// 父级（更新前
					GoodsVirtualCatWithBLOBs updateParent = new GoodsVirtualCatWithBLOBs();
					updateParent.setCatId(parentCatDB.getCatId());
					updateParent.setChildCount(parentCatDB.getChildCount() - 1);
					goodsVirtualCatMapper.updateByPrimaryKeySelective(updateParent);
				}
				// 增加用户选择父类个数
				if (null != goodsCat.getParentCatId() && goodsCat.getParentCatId() != 0) {
					GoodsVirtualCatWithBLOBs parentCatDB = goodsVirtualCatMapper.selectByPrimaryKey(goodsCat.getParentCatId());// 父级（更新前
					GoodsVirtualCatWithBLOBs updateParent = new GoodsVirtualCatWithBLOBs();
					updateParent.setCatId(parentCatDB.getCatId());
					updateParent.setChildCount(parentCatDB.getChildCount() + 1);
					goodsVirtualCatMapper.updateByPrimaryKeySelective(updateParent);
				}
			}

		} else {
			if (temp != null && temp.size() > 0) {
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			if (goodsCat.getParentCatId() == null) {
				goodsCat.setParentCatId(0);
				goodsCat.setCatPath(goodsCat.getCatName());
			} else {
				goodsCat.setCatPath(parentCat.getCatPath() + ":" + goodsCat.getCatName());
				// 增加用户选择父类个数
				// GoodsVirtualCatWithBLOBs parentCatDB =
				// goodsVirtualCatMapper.selectByPrimaryKey(goodsCat.getParentCatId());//
				// 父级（更新前
				// GoodsVirtualCatWithBLOBs updateParent = new
				// GoodsVirtualCatWithBLOBs();
				// updateParent.setCatId(parentCatDB.getCatId());
				// updateParent.setChildCount(parentCatDB.getChildCount() + 1);
				// goodsVirtualCatMapper.updateByPrimaryKeySelective(updateParent);
				goodsVirtualCatMapper.increaseSubcatCount(parentCat.getCatId());
			}
			goodsVirtualCatMapper.insertSelective(goodsCat);
		}
		log.info("end[CategoryService.saveVirtualCategoryByCatId]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 删除虚拟分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月26日
	 * @param data
	 * @return
	 */
	public Object deleteVirtualCategoryByCatId(Object data) throws Exception {
		log.info("start[CategoryService.deleteVirtualCategoryByCatId]");
		GoodsVirtualCat goodsVirtualCat = goodsVirtualCatMapper.selectByPrimaryKey((Integer) data);
		if (null == goodsVirtualCat.getChildCount() || goodsVirtualCat.getChildCount() < 1) {
			goodsVirtualCatMapper.decreaseSubcatCount(goodsVirtualCat.getParentCatId());
			goodsVirtualCatMapper.deleteByPrimaryKey((Integer) data);
			log.info("end[CategoryService.deleteVirtualCategoryByCatId]");
			return new HeadObject(ErrorCode.SUCCESS);
		} else {
			log.info("end[CategoryService.deleteVirtualCategoryByCatId]");
			return new HeadObject("PDE005");
		}
	}

	/**
	 * @description 根据分类ID查询虚拟分类详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月26日
	 * @param data
	 * @return
	 */
	public Object getVirtualCategoryByCatId(Object data) throws Exception {
		log.info("start[CategoryService.getVirtualCategoryByCatId]");
		GoodsVirtualCatWithBLOBs goodsCat = goodsVirtualCatMapper.selectByPrimaryKey((Integer) data);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cate", goodsCat);
		log.info("end[CategoryService.getVirtualCategoryByCatId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject.toString());
	}

	/**
	 * @description 获取虚拟分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月26日
	 * @param data
	 * @return
	 */
	public Object getVirtualCategoryByExample(Object data) throws Exception {
		log.info("start[CategoryService.getVirtualCategoryByExample]");
		JSONObject content = JSONObject.fromObject(data);
		GoodsVirtualCatExample example = new GoodsVirtualCatExample();
		if (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content, "parentCatId"))) {// 上级分类Id
			example.createCriteria().andParentCatIdEqualTo((Integer) content.get("parentCatId"));
		}
		if (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content, "catName"))) {// 分类名称
			example.createCriteria().andCatNameLike(content.getString("catName"));
		}
		List<GoodsVirtualCat> page = goodsVirtualCatMapper.selectByExample(example);
		log.info("end[CategoryService.getVirtualCategoryByExample]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(page));
	}

	/**
	 * @description 获取所有虚拟分类
	 * @author wanghb
	 * @version 1.0.0
	 * @data 2015年6月9日
	 * @param data
	 * @return
	 */
	public Object getVirtualCategoryAll(Object data) throws Exception {
		log.info("start[CategoryService.getVirtualCategoryByExample]");
		GoodsVirtualCatExample example = new GoodsVirtualCatExample();
		example.createCriteria().andParentCatIdEqualTo(0).andHiddenEqualTo("0");
		List<GoodsVirtualCat> parentList = goodsVirtualCatMapper.selectByExample(example);// 所有一级分类
		for (GoodsVirtualCat car : parentList) {
			car.setChildCateList(getVirtualCategoryBy(car.getCatId()));
		}
		log.info("end[CategoryService.getVirtualCategoryByExample]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(parentList));
	}

	/**
	 * 查询虚拟父类下所有子类
	 * 
	 * @Description:
	 * @param parentCatId
	 * @return
	 */
	public List<GoodsVirtualCat> getVirtualCategoryBy(Integer parentCatId) {
		GoodsVirtualCatExample example = new GoodsVirtualCatExample();
		example.createCriteria().andParentCatIdEqualTo(parentCatId).andHiddenEqualTo("0");
		List<GoodsVirtualCat> list = goodsVirtualCatMapper.selectByExample(example);
		for (GoodsVirtualCat cat : list) {
			getVirtualCategory(cat);
		}
		return list;
	}

	private GoodsVirtualCat getVirtualCategory(GoodsVirtualCat cat) {
		GoodsVirtualCatExample example = new GoodsVirtualCatExample();
		example.createCriteria().andParentCatIdEqualTo(cat.getCatId()).andHiddenEqualTo("0");
		List<GoodsVirtualCat> list = goodsVirtualCatMapper.selectByExample(example);
		cat.setChildCateList(list);
		for (GoodsVirtualCat childrenCat : list) {
			getVirtualCategory(childrenCat);
		}
		return cat;
	}

	/**
	 * @description 修改商品分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月21日
	 * @param data
	 * @return
	 */
	public Object updateCategory(Object data) throws Exception {
		log.info("start[CategoryService.updateCategory]");
		JSONObject content = JSONObject.fromObject(data);
		// 更新分类信息
		GoodsCatWithBLOBs goodsCat = (GoodsCatWithBLOBs) JSONObject.toBean(content.getJSONObject("cat"), GoodsCatWithBLOBs.class);
		goodsCatMapper.updateByPrimaryKeySelective(goodsCat);
		// 更新扩展属性
		JSONArray attrJsonArray = JSONArray.fromObject(content.getJSONObject("attrs"));
		// 更新规格
		JSONArray specJsonArray = JSONArray.fromObject(content.getJSONObject("specs"));
		log.info("end[CategoryService.updateCategory]");
		return null;
	}

	/**
	 * @description 删除商品分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月21日
	 * @param data
	 * @return
	 */
	public Object deletCategory(Object data) throws Exception {
		log.info("start[CategoryService.deletCategory]");
		/* 查询该分类下是否存在子分类 存在则不予删除 */
		GoodsCat goodsCat = goodsCatMapper.selectByPrimaryKey((Integer) data);
		HeadObject headObject = new HeadObject();
		if (goodsCat.getChildCount() < 1) {
			/* undo:查询该分类下是否存在商品 存在则不予删除 */

			/* 删除该分类下的扩展属性 */
			AttributeExample attributeExample = new AttributeExample();
			attributeExample.createCriteria().andCatIdEqualTo((Integer) data);
			attributeMapper.deleteByExample(attributeExample);
			/* 删除该分类下的扩展属性值 */
			AttributeValueExample attributeValueExample = new AttributeValueExample();
			attributeValueExample.createCriteria().andCatIdEqualTo((Integer) data);
			attributeValueMapper.deleteByExample(attributeValueExample);
			/* 删除该分类与规格的关联关系 */
			CatSpecShipExample catSpecShipExample = new CatSpecShipExample();
			catSpecShipExample.createCriteria().andCatIdEqualTo((Integer) data);
			catSpecShipMapper.deleteByExample(catSpecShipExample);
			/* 删除该分类 */
			goodsCatMapper.deleteByPrimaryKey((Integer) data);
			/* 更新上级分类的值 */
			goodsCatMapper.decreaseSubcatCount(goodsCat.getParentCatId());
			log.info("end[CategoryService.deletCategory]");
			return headObject.setRetCode(ErrorCode.SUCCESS);
		} else {
			log.info("end[CategoryService.deletCategory]");
			return headObject.setRetCode("PDE005");
		}
	}

	/**
	 * @description 新增商品分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月21日
	 * @param data
	 * @return
	 */
	public Object saveCategory(Object data) throws Exception {
		log.info("start[CategoryService.saveCategory]");
		JSONObject content = JSONObject.fromObject(data);
		GoodsCatWithBLOBs goodsCat = (GoodsCatWithBLOBs) JSONObject.toBean(content.getJSONObject("cate"), GoodsCatWithBLOBs.class);
		GoodsCatExample temp = new GoodsCatExample();
		temp.createCriteria().andCatNameEqualTo(goodsCat.getCatName());
		List<GoodsCat> tempPage = goodsCatMapper.selectByExample(temp);
		if (goodsCat.getCatId() == null) {// 新增分类
			// 判断名称是否存在
			if (null != tempPage && tempPage.size() > 0) {
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			// 更新父亲节点的子节点数量
			if (goodsCat.getParentCatId() != null) {
				GoodsCatWithBLOBs parentCat = goodsCatMapper.selectByPrimaryKey(goodsCat.getParentCatId());
				goodsCatMapper.increaseSubcatCount(parentCat.getCatId());
				goodsCat.setIdentifier(parentCat.getIdentifier());
			}
			goodsCat.setDisabled("0");
			goodsCatMapper.insertSelective(goodsCat);
		} else {
			if (null != tempPage && tempPage.size() > 0 && (!goodsCat.getCatId().equals(tempPage.get(0).getCatId()))) {
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			goodsCatMapper.updateByPrimaryKeySelective(goodsCat);
			if (null != tempPage && tempPage.size() == 1 && null != tempPage.get(0).getParentCatId() && tempPage.get(0).getParentCatId() != 0 && tempPage.get(0).getParentCatId() != goodsCat.getParentCatId()) {
				// 减去原来父级子类个数
				GoodsCatWithBLOBs parentCatDB = goodsCatMapper.selectByPrimaryKey(tempPage.get(0).getParentCatId());// 父级（更新前）
				GoodsCatWithBLOBs updateParent = new GoodsCatWithBLOBs();
				updateParent.setCatId(parentCatDB.getCatId());
				updateParent.setChildCount(parentCatDB.getChildCount() - 1);
				goodsCatMapper.updateByPrimaryKeySelective(updateParent);
				// 增加用户选择父类个数
				GoodsCatWithBLOBs parentCatDB1 = goodsCatMapper.selectByPrimaryKey(goodsCat.getParentCatId());// 父级（更新后）
				GoodsCatWithBLOBs updateParent1 = new GoodsCatWithBLOBs();
				updateParent1.setCatId(parentCatDB1.getCatId());
				updateParent1.setChildCount(parentCatDB1.getChildCount() + 1);
				goodsCatMapper.updateByPrimaryKeySelective(updateParent1);
			}
		}
		// 新增分类的扩展属性
		JSONArray insertedAttrJsonArray = content.getJSONArray("inserted");
		if (insertedAttrJsonArray.size() > 0) {
			Iterator<JSONObject> interator = insertedAttrJsonArray.iterator();
			while (interator.hasNext()) {
				Attribute attribute = (Attribute) JSONObject.toBean((JSONObject) interator.next(), Attribute.class);
				attribute.setCatId(goodsCat.getCatId());
				attributeMapper.insertSelective(attribute);
				if (!StringUtil.isEmpty(attribute.getAttrValues())) {
					String[] attrValues = attribute.getAttrValues().split("\\|");
					List<AttributeValue> valueList = new ArrayList<AttributeValue>();
					for (int i = 0; i < attrValues.length; i++) {
						AttributeValue value = new AttributeValue();
						value.setAttrId(attribute.getAttrId());
						value.setAttrValue(attrValues[i]);
						value.setCatId(goodsCat.getCatId());
						value.setOrderNum(i);
						valueList.add(value);
					}
					attributeValueMapper.insertBatch(valueList);
				}
			}
		}
		AttributeValueExample attributeValueExample = new AttributeValueExample();
		// 修改分类的扩展属性
		JSONArray updatedAttrJsonArray = content.getJSONArray("updated");
		if (updatedAttrJsonArray.size() > 0) {
			Iterator<JSONObject> interator = updatedAttrJsonArray.iterator();
			while (interator.hasNext()) {
				// 更新属性
				Attribute attribute = (Attribute) JSONObject.toBean((JSONObject) interator.next(), Attribute.class);
				attributeMapper.updateByPrimaryKeySelective(attribute);
				// 更新属性值
				if (!StringUtil.isEmpty(attribute.getAttrValues())) {
					String[] attrValues = attribute.getAttrValues().split("\\|");
					List<AttributeValue> valueList = new ArrayList<AttributeValue>();
					for (int i = 0; i < attrValues.length; i++) {
						// 查询该属性值是否已经存在 不存在则添加
						attributeValueExample.clear();
						attributeValueExample.createCriteria().andAttrValueEqualTo(attrValues[i]).andAttrIdEqualTo(attribute.getAttrId());
						attributeValueMapper.deleteByExample(attributeValueExample);
						// if
						// (attributeValueMapper.countByExample(attributeValueExample)
						// < 1) {
						AttributeValue value = new AttributeValue();
						value.setAttrId(attribute.getAttrId());
						value.setAttrValue(attrValues[i]);
						value.setCatId(goodsCat.getCatId());
						value.setOrderNum(i);
						valueList.add(value);
						// }
					}
					attributeValueMapper.insertBatch(valueList);
					// 删除不在列表中的值
					attributeValueExample.clear();
					attributeValueExample.createCriteria().andAttrIdEqualTo(attribute.getAttrId()).andAttrValueNotIn(Arrays.asList(attrValues));
					attributeValueMapper.deleteByExample(attributeValueExample);
				}
			}
		}
		// 删除分类的扩展属性
		JSONArray deletedAttrJsonArray = content.getJSONArray("deleted");
		if (deletedAttrJsonArray.size() > 0) {
			Iterator<JSONObject> interator = deletedAttrJsonArray.iterator();
			while (interator.hasNext()) {
				Integer attrId = ((JSONObject) interator.next()).getInt("attrId");
				attributeMapper.deleteByPrimaryKey(attrId);
				attributeValueExample.clear();
				attributeValueExample.createCriteria().andAttrIdEqualTo(attrId);
				attributeValueMapper.deleteByExample(attributeValueExample);
			}
		}
		// 新增规格绑定分类
		JSONArray specJsonArray = content.getJSONArray("specs");
		List<Integer> specIds = new ArrayList<Integer>();
		if (specJsonArray.size() > 0) {
			Iterator<String> specInterator = specJsonArray.iterator();
			while (specInterator.hasNext()) {
				String specId = specInterator.next();
				specIds.add(Integer.parseInt(specId));
			}
			// 判断删除的规格是否已经关联了商品
			CatSpecShipExample selectCatSpec = new CatSpecShipExample();
			selectCatSpec.createCriteria().andCatIdEqualTo(goodsCat.getCatId());
			int count = catSpecShipMapper.selectCountByCateSId(goodsCat.getCatId(),specIds);
			if(count>0){
				return new HeadObject(ErrorCode.THE_NUMBER_OVER);
			}
		}

		// 先删除关系
		CatSpecShipExample catSpecShipExample = new CatSpecShipExample();
		catSpecShipExample.createCriteria().andCatIdEqualTo(goodsCat.getCatId());
		catSpecShipMapper.deleteByExample(catSpecShipExample);

		if (specJsonArray.size() > 0) {
			// 再建立新关系
			List<CatSpecShip> catSpecShipList = new ArrayList<CatSpecShip>();
			Iterator<String> specInterator = specJsonArray.iterator();
			while (specInterator.hasNext()) {
				String specId = specInterator.next();
				CatSpecShip catSpecShip = new CatSpecShip();
				catSpecShip.setCatId(goodsCat.getCatId());
				catSpecShip.setSpecId(Integer.parseInt(specId));
				catSpecShipList.add(catSpecShip);
			}
			catSpecShipMapper.insertBatch(catSpecShipList);
		}
		log.info("end[CategoryService.saveCategory]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 根据分类ID查询分类详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月21日
	 * @param data
	 * @return
	 */
	public Object getCategoryByCatId(Object data) throws Exception {
		log.info("start[CategoryService.getCategoryByCatId]");
		JSONObject jsonObject = new JSONObject();
		JSONObject content = JSONObject.fromObject(data);
		GoodsCatWithBLOBs goodsCat = goodsCatMapper.selectByPrimaryKey(content.getInt("catId"));
		jsonObject.put("cate", goodsCat);
		// 获取指定分类id的所有上级分类id，因为属性是继承的
		List<Integer> parentIds = goodsCatMapper.getParentIds(content.getInt("catId"));

		// 扩展属性
		AttributeExample attributeExample = new AttributeExample();
		// attributeExample.createCriteria().andCatIdEqualTo((Integer) data);
		attributeExample.createCriteria().andCatIdIn(parentIds);
		List<Attribute> attributes = attributeMapper.selectByExampleWithBLOBs(attributeExample);
		jsonObject.put("attrs", attributes);
		// 规格 2015.04.08 xiaox修改
		// CatSpecShipExample catSpecShipExample = new CatSpecShipExample();
		// catSpecShipExample.createCriteria().andCatIdEqualTo((Integer) data);
		// List<CatSpecShip> catSpecShips =
		// catSpecShipMapper.selectByExample(catSpecShipExample);
		//List<SpecDTO> catSpecShips = specMapper.selectSpecAndSepcVal(content.getInt("catId"),content.getInt("carId"));
		List<Spec> catSpecShips = specMapper.selectSpec(content.getInt("catId"));
		jsonObject.put("specs", catSpecShips);
		log.info("end[CategoryService.getCategoryByCatId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject.toString());
	}
	/**
	 * 根据分类查询扩展信息
	 * 
	 * @Description:
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object getAttributeByCatId(Object data) throws Exception {
		List<Attribute> attributes = attributeMapper.selectByCateIds(data.toString());
		for (Attribute attribute : attributes) {
			if (StringUtils.isNotBlank(attribute.getAttrValues())) {
				attribute.setAttrValueList(Arrays.asList(attribute.getAttrValues().split("\\|")));
				AttributeValueExample example = new AttributeValueExample();
				example.createCriteria().andAttrIdEqualTo(attribute.getAttrId()).andCatIdEqualTo(attribute.getCatId());
				List<AttributeValue> attrValueLists = attributeValueMapper.selectByExample(example);
				attribute.setAttrValueLists(attrValueLists);
			}
		}
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), attributes);
	}

	/**
	 * @description 通过参数查询商品分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月21日
	 * @param data
	 * @return
	 */
	public Object getCategoryByExample(Object data) throws Exception {
		log.info("start[CategoryService.getCategoryByExample]");
		JSONObject content = JSONObject.fromObject(data);
		/*
		 * GoodsCatExample example = new GoodsCatExample(); if
		 * (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content,
		 * "parentCatId"))) {// 上级分类Id
		 * example.createCriteria().andParentCatIdEqualTo((Integer)
		 * content.get("parentCatId")); } if
		 * (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content,
		 * "catName"))) {// 分类名称
		 * example.createCriteria().andCatNameLike(content.
		 * getString("catName")); } if
		 * (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content,
		 * "identifier"))) {// 分类标志
		 * example.createCriteria().andIdentifierEqualTo
		 * (content.getString("identifier"));
		 * }//example.createCriteria().andIdentifierNotEqualTo(value)
		 * example.createCriteria().andDisabledEqualTo("0");
		 */
		GoodsCat cat = new GoodsCat();
		if (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content, "parentCatId"))) {// 上级分类Id
			cat.setParentCatId((Integer) content.get("parentCatId"));
		}
		if (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content, "catName"))) {// 分类名称
			cat.setCatName(content.getString("catName"));
		}
		if (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content, "identifier"))) {// 分类标志
			cat.setIdentifier(content.getString("identifier"));
		}
		if (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content, "companyId"))) {// 分类标志
			cat.setCompanyId(content.optInt("companyId"));
		}
		List<GoodsCat> page = goodsCatMapper.selectCategory(cat);
		log.info("end[CategoryService.getCategoryByExample]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(page));
	}

	/**
	 * 
	 * @Title: getCatSpecByCatId
	 * @Description: 根据商品分类id查询分类规格
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @param @param data
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public Object getCatSpecByCatId(Object data) throws Exception {
		log.info("start[CategoryService.getCatSpecByCatId]");
		JSONObject content = JSONObject.fromObject(data);
		Integer catId = content.getInt("catId");
		CatSpecShipExample catSpecShipExample = new CatSpecShipExample();
		catSpecShipExample.createCriteria().andCatIdEqualTo(catId);
		List<CatSpecShip> catSpecShips = catSpecShipMapper.selectByExample(catSpecShipExample);
		log.info("end[CategoryService.getCatSpecByCatId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(catSpecShips));
	}

	/**
	 * 
	 * @description 查找指定id的基本信息
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-4-28
	 * @param data
	 * @return
	 */
	public Object findCateById(Object data) throws Exception {
		log.info("start[CategoryService.findCateById]");
		GoodsCatWithBLOBs cate = goodsCatMapper.selectByPrimaryKey((Integer) data);
		log.info("end[CategoryService.findCateById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), cate);
	}

	/**
	 * @description <b>根据分类ID集合查找指定分类集合的信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param data
	 * @param @return
	 * @return Object
	 */
	public Object findCategoryByIds(Object data) throws Exception {
		log.info("start[CategoryService.findCategoryByIds]");
		@SuppressWarnings("unchecked")
		List<GoodsCatWithBLOBs> cate = goodsCatMapper.selectCategoryByIds((Long[]) data);
		log.info("end[CategoryService.findCategoryByIds]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), cate);
	}

	/**
	 * @description <b>查询保养分类信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param data
	 * @param @return
	 * @return Object
	 */
	public Object findMaintainCategory(Object data) throws Exception {
		List<GoodsCatWithBLOBs> cate = goodsCatMapper.selectMaintainCategory((String) data);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), cate);
	}

	/**
	 * @description <b>查询保养分类信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param data
	 * @param @return
	 * @return Object
	 */
	public Object findOfficialMaintainCategory(Object data) throws Exception {
		List<GoodsCatWithBLOBs> cate = goodsCatMapper.selectOfficialMaintainCategory((String) data);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), cate);
	}

	/**
	 * @description <b>查询自选保养分类信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param data
	 * @param @return
	 * @return Object
	 */
	public Object findOptionalMaintainCategory(Object data) throws Exception {
		List<GoodsCatWithBLOBs> cate = goodsCatMapper.selectOptionalMaintainCategory();
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), cate);
	}

	/**
	 * @description <b>查找自选保养分类树</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param data
	 * @param @return
	 * @return Object
	 */
	public Object findOptionalMaintainCategoryTree(Object data) throws Exception {
		List<TreeNode> treeNode = goodsCatMapper.selectOptionalMaintainCategoryTree((String) data);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(TreeUtils.toTreeNode(treeNode)));
	}

	/**
	 * @description <b>获取所有主分类</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-6
	 * @param @return
	 * @return Object
	 */
	public Object getAllMainCategory(Object data) throws Exception {
		List<GoodsCatWithBLOBs> cate = goodsCatMapper.selectAllMainCategory();
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), cate);
	}

	/**
	 * @description <b>获得分类树形数据</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-9
	 * @param @return
	 * @return Object
	 */
	public Object getAllCategoryTree(Object data) throws Exception {
		List<TreeNode> treeNode = goodsCatMapper.selectAllCategoryTree();
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(TreeUtils.toTreeNode(treeNode)));
	}

	/**
	 * 
	 * @description 根据类型identifier获取第一级的分类
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-19
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object findCategoryTree(Object data) throws Exception {
		List<GoodsCat> treeNode = goodsCatMapper.findCategoryTree((String) data);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(treeNode));
	}

	/**
	 * 
	 * @description 通过父级id查子级
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-20
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object selectByPid(Object data) throws Exception {
		log.info("start[CategoryService.selectByPid]");
		JSONObject content = JSONObject.fromObject(data);
		List<GoodsCat> page = goodsCatMapper.selectByPid((Integer) content.get("parentCatId"));
		log.info("end[CategoryService.selectByPid]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(page));
	}

	/**
	 * 
	 * @description 通过父级id查子级,没有被隐藏的
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-20
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object selectNoHiddenByPid(Object data) throws Exception {
		log.info("start[CategoryService.selectByPid]");
		JSONObject content = JSONObject.fromObject(data);
		List<GoodsCat> page = goodsCatMapper.selectNoHiddenByPid((Integer) content.get("parentCatId"));
		log.info("end[CategoryService.selectByPid]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(page));
	}

	/**
	 * 
	 * @description 通过店铺id查找店铺商品分类
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-7-7
	 * @param data
	 * @return
	 */
	public Object findGoodsCatByCompanyId(Object data) {
		log.info("start[CategoryService.findGoodsCatByCompanyId]");
		List<GoodsCat> page = goodsCatMapper.findGoodsCatByCompanyId((Integer) data);
		log.info("end[CategoryService.findGoodsCatByCompanyId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}

	/**
	 * 获取有效可见的虚拟分类
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object getUsefulVirtualCategory(Object data) throws Exception {
		log.info("start[CategoryService.getUsefulVirtualCategory]");
		List<GoodsVirtualCatWithBLOBs> goodsCat = (List<GoodsVirtualCatWithBLOBs>) goodsVirtualCatMapper.selectAllUseful();
		log.info("end[CategoryService.getUsefulVirtualCategory]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), goodsCat);
	}

}
