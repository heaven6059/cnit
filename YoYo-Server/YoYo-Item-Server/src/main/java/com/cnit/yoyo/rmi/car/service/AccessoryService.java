/**
\ * 文 件 名   :  AccessoryService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年3月30日 下午1:57:11
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.car.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.car.AccessoryCarIndexMapper;
import com.cnit.yoyo.dao.car.AccessoryCatalogMapper;
import com.cnit.yoyo.dao.car.AccessoryMapper;
import com.cnit.yoyo.dao.car.AccessoryParamBolMapper;
import com.cnit.yoyo.dao.car.AccessoryParamDecMapper;
import com.cnit.yoyo.dao.car.AccessoryParamIntMapper;
import com.cnit.yoyo.dao.car.AccessoryParamMapper;
import com.cnit.yoyo.dao.car.AccessoryParamStrMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.Accessory;
import com.cnit.yoyo.model.car.AccessoryCarIndex;
import com.cnit.yoyo.model.car.AccessoryCarIndexExample;
import com.cnit.yoyo.model.car.AccessoryCatalog;
import com.cnit.yoyo.model.car.AccessoryCatalogExample;
import com.cnit.yoyo.model.car.AccessoryExample;
import com.cnit.yoyo.model.car.AccessoryExample.Criteria;
import com.cnit.yoyo.model.car.AccessoryParam;
import com.cnit.yoyo.model.car.AccessoryParamBol;
import com.cnit.yoyo.model.car.AccessoryParamBolExample;
import com.cnit.yoyo.model.car.AccessoryParamDec;
import com.cnit.yoyo.model.car.AccessoryParamDecExample;
import com.cnit.yoyo.model.car.AccessoryParamExample;
import com.cnit.yoyo.model.car.AccessoryParamInt;
import com.cnit.yoyo.model.car.AccessoryParamIntExample;
import com.cnit.yoyo.model.car.AccessoryParamStr;
import com.cnit.yoyo.model.car.AccessoryParamStrExample;
import com.cnit.yoyo.model.car.AccessoryParamValue;
import com.cnit.yoyo.model.car.dto.AccessoryDTO;
import com.cnit.yoyo.model.goods.Goods;
import com.cnit.yoyo.model.goods.dto.GoodsQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description 汽车配件服务
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
@Service("accessoryService")
public class AccessoryService {
	public static final Logger log = LoggerFactory.getLogger(AccessoryService.class);

	@Autowired
	public AccessoryCatalogMapper accessoryCatalogMapper;
	@Autowired
	public AccessoryParamMapper accessoryParamMapper;
	@Autowired
	public AccessoryParamBolMapper accessoryParamBolMapper;
	@Autowired
	public AccessoryParamStrMapper accessoryParamStrMapper;
	@Autowired
	public AccessoryParamIntMapper accessoryParamIntMapper;
	@Autowired
	public AccessoryParamDecMapper accessoryParamDecMapper;
	@Autowired
	public AccessoryMapper accessoryMapper;
	@Autowired
	public AccessoryCarIndexMapper accessoryCarIndexMapper;

	public void main(String[] args, Object data) throws Exception {
		data = getAccessoryCatalogByParam(data);
		data = getAccessoryCatalogDetailById(data);
		data = saveAccessoryCatalogDetail(data);
		data = deleteAccessoryCatalogById(data);
		data = deleteAccessoryParamById(data);
		data = getAccessoryByParam(data);
		data = getAccessoryDetailById(data);
		data = getAccessoryParamValue(data);
		data = saveAccessory(data);
		data = deleteAccessory(data);
	}

	/**
	 * @description 删除配件信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月2日
	 * @param data
	 * @return
	 */
	public Object deleteAccessory(Object data) {
		log.info("start[AccessoryService.deleteAccessory]");
		// 删除STR值
		AccessoryParamStrExample accessoryParamStrExample = new AccessoryParamStrExample();
		accessoryParamStrExample.createCriteria().andAccIdEqualTo((Integer) data);
		accessoryParamStrMapper.deleteByExample(accessoryParamStrExample);
		// 删除Bol值
		AccessoryParamBolExample accessoryParamBolExample = new AccessoryParamBolExample();
		accessoryParamBolExample.createCriteria().andAccIdEqualTo((Integer) data);
		accessoryParamBolMapper.deleteByExample(accessoryParamBolExample);
		// 删除Int值
		AccessoryParamIntExample accessoryParamIntExample = new AccessoryParamIntExample();
		accessoryParamIntExample.createCriteria().andAccIdEqualTo((Integer) data);
		accessoryParamIntMapper.deleteByExample(accessoryParamIntExample);
		// 删除Dec值
		AccessoryParamDecExample accessoryParamDecExample = new AccessoryParamDecExample();
		accessoryParamDecExample.createCriteria().andAccIdEqualTo((Integer) data);
		accessoryParamDecMapper.deleteByExample(accessoryParamDecExample);
		// 删除车型绑定
		AccessoryCarIndexExample accessoryCarIndexExample = new AccessoryCarIndexExample();
		accessoryCarIndexExample.createCriteria().andAccIdEqualTo((Integer) data);
		accessoryCarIndexMapper.deleteByExample(accessoryCarIndexExample);
		// 删除配件信息
		accessoryMapper.deleteByPrimaryKey((Integer) data);
		log.info("end[AccessoryService.deleteAccessory]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 获取配件参数值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月1日
	 * @param data
	 * @return
	 */
	public Object getAccessoryParamValue(Object data) throws Exception {
		log.info("start[AccessoryService.getAccessoryParamValue]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("catalogId", jsonObject.optInt("catalogId"));
		paramMap.put("accId", jsonObject.optInt("accId", jsonObject.optInt("accId")));
		List<AccessoryParamValue> accessoryParamValue = accessoryMapper.selectAccessoryParamValue(paramMap);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (value == null) {
					return true;
				} else {
					return false;
				}
			}
		});
		log.info("end[AccessoryService.getAccessoryParamValue]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
				JSONArray.fromObject(accessoryParamValue, jsonConfig));
	}

	/**
	 * @description 保存汽车配件信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月1日
	 * @param data
	 * @return
	 */
	public Object saveAccessory(Object data) throws Exception {
		log.info("start[AccessoryService.saveAccessory]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Accessory accessory = (Accessory) JSONObject.toBean((JSONObject) jsonObject.get("accessory"), Accessory.class);

		JSONArray cars = jsonObject.getJSONArray("cars");
		if (cars.size() > 0) {
			accessory.setIsRelatedCar("1");
		} else {
			accessory.setIsRelatedCar("0");
		}
		AccessoryExample example=new AccessoryExample();
		example.setDistinct(true);
		example.createCriteria().andAccNameEqualTo(accessory.getAccName());
		List<Accessory>  list=accessoryMapper.selectByExampleWithBLOBs(example);
		if (accessory.getAccId() != null) {
			if(null!=list && list.size()>0) {
				if(!accessory.getAccId().equals(list.get(0).getAccId())){
					return new HeadObject(ErrorCode.IS_EXIST);
				}
			}
			accessoryMapper.updateByPrimaryKey(accessory);
			// 删除STR值
			AccessoryParamStrExample accessoryParamStrExample = new AccessoryParamStrExample();
			accessoryParamStrExample.createCriteria().andAccIdEqualTo(accessory.getAccId());
			accessoryParamStrMapper.deleteByExample(accessoryParamStrExample);
			// 删除Bol值
			AccessoryParamBolExample accessoryParamBolExample = new AccessoryParamBolExample();
			accessoryParamBolExample.createCriteria().andAccIdEqualTo(accessory.getAccId());
			accessoryParamBolMapper.deleteByExample(accessoryParamBolExample);
			// 删除Int值
			AccessoryParamIntExample accessoryParamIntExample = new AccessoryParamIntExample();
			accessoryParamIntExample.createCriteria().andAccIdEqualTo(accessory.getAccId());
			accessoryParamIntMapper.deleteByExample(accessoryParamIntExample);
			// 删除Dec值
			AccessoryParamDecExample accessoryParamDecExample = new AccessoryParamDecExample();
			accessoryParamDecExample.createCriteria().andAccIdEqualTo(accessory.getAccId());
			accessoryParamDecMapper.deleteByExample(accessoryParamDecExample);
			// 删除绑定的车型
			AccessoryCarIndexExample accessoryCarIndexExample = new AccessoryCarIndexExample();
			accessoryCarIndexExample.createCriteria().andAccIdEqualTo(accessory.getAccId());
			accessoryCarIndexMapper.deleteByExample(accessoryCarIndexExample);
		} else {
			if(null!=list && list.size()>0) {
				if(accessory.getAccName().equals(list.get(0).getAccName())){
					return new HeadObject(ErrorCode.IS_EXIST);
				}
			}
			accessoryMapper.insertSelective(accessory);
		}

		if (cars.size() > 0) {
			List<AccessoryCarIndex> accessoryCarIndexs = new ArrayList<AccessoryCarIndex>();
			for (Iterator iterator = cars.iterator(); iterator.hasNext();) {
				AccessoryCarIndex accessoryCarIndex = (com.cnit.yoyo.model.car.AccessoryCarIndex) JSONObject.toBean(
						(JSONObject) iterator.next(), AccessoryCarIndex.class);
				accessoryCarIndex.setAccId(accessory.getAccId());
				accessoryCarIndexs.add(accessoryCarIndex);
			}
			accessoryCarIndexMapper.batchInsert(accessoryCarIndexs);
		}

		JSONArray params = jsonObject.getJSONArray("params");
		if (params.size() > 0) {
			List<AccessoryParamStr> strValues = new ArrayList<AccessoryParamStr>();
			List<AccessoryParamInt> intValues = new ArrayList<AccessoryParamInt>();
			List<AccessoryParamBol> bolValues = new ArrayList<AccessoryParamBol>();
			List<AccessoryParamDec> decValues = new ArrayList<AccessoryParamDec>();
			for (Iterator iterator = params.iterator(); iterator.hasNext();) {
				JSONObject param = (JSONObject) iterator.next();
				if (param.containsKey("value")) {
					if (GlobalStatic.DATA_TYPE_STR.equalsIgnoreCase(param.getString("dataType"))) {
						AccessoryParamStr strValue = new AccessoryParamStr();
						strValue.setAccId(accessory.getAccId());
						strValue.setParamId(param.getInt("paramId"));
						strValue.setValue(param.getString("value"));
						strValue.setCatalogId(accessory.getCatId());
						strValues.add(strValue);
					} else if (GlobalStatic.DATA_TYPE_BOL.equalsIgnoreCase(param.getString("dataType"))) {
						AccessoryParamBol bolValue = new AccessoryParamBol();
						bolValue.setAccId(accessory.getAccId());
						bolValue.setParamId(param.getInt("paramId"));
						bolValue.setValue(param.getBoolean("value"));
						bolValue.setCatalogId(accessory.getCatId());
						bolValues.add(bolValue);
					} else if (GlobalStatic.DATA_TYPE_INT.equalsIgnoreCase(param.getString("dataType"))) {
						AccessoryParamInt intValue = new AccessoryParamInt();
						intValue.setAccId(accessory.getAccId());
						intValue.setParamId(param.getInt("paramId"));
						intValue.setValue(param.getInt("value"));
						intValue.setCatalogId(accessory.getCatId());
						intValues.add(intValue);
					} else if (GlobalStatic.DATA_TYPE_DEC.equalsIgnoreCase(param.getString("dataType"))) {
						AccessoryParamDec decValue = new AccessoryParamDec();
						decValue.setAccId(accessory.getAccId());
						decValue.setParamId(param.getInt("paramId"));
						decValue.setValue(param.opt("value") == null ? null : new BigDecimal(param.getDouble("value")));
						decValue.setCatalogId(accessory.getCatId());
						decValues.add(decValue);
					}
				}
			}
			/* 批量插入 */
			if (strValues.size() > 0) {
				accessoryParamStrMapper.batchInsert(strValues);
			}
			if (intValues.size() > 0) {
				accessoryParamIntMapper.batchInsert(intValues);
			}
			if (decValues.size() > 0) {
				accessoryParamDecMapper.batchInsert(decValues);
			}
			if (bolValues.size() > 0) {
				accessoryParamBolMapper.batchInsert(bolValues);
			}
		}
		log.info("end[AccessoryService.saveAccessory]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 查询配件详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月1日
	 * @param data
	 * @return
	 */
	public Object getAccessoryDetailById(Object data) throws Exception {
		log.info("start[AccessoryService.getAccessoryDetailById]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Accessory accessory = accessoryMapper.selectAccInfoAndBindingCars((Integer) data);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("catalogId", accessory.getCatId());
		paramMap.put("accId", (Integer) data);
		List<AccessoryParamValue> accessoryParamValue = accessoryMapper.selectAccessoryParamValue(paramMap);
		jsonObject.clear();
		jsonObject.element("accessory", accessory);
		jsonObject.element("params", accessoryParamValue);
		log.info("end[AccessoryService.getAccessoryDetailById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
	}

	/**
	 * @description 分页查询配件信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月1日
	 * @param data
	 * @return
	 */
	public Object getAccessoryByParam(Object data) throws Exception {
		log.info("start[AccessoryService.getAccessoryByParam]");
		JSONObject content = JSONObject.fromObject(data);
		PageHelper.startPage(QueryParamObject.getPage(content), QueryParamObject.getRows(content));
		ResultPage<AccessoryCatalog> page = new ResultPage(accessoryMapper.selectByQueryParams(
				QueryParamObject.getMqls(content), QueryParamObject.getOrderByCause(content)));
		log.info("end[AccessoryService.getAccessoryByParam]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
	}

	/**
	 * @description 通过Id删除配件参数项目
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月30日
	 * @param data
	 * @return
	 */
	public Object deleteAccessoryParamById(Object data) throws Exception {
		log.info("start[AccessoryService.deleteAccessoryParamById]");
		JSONObject content = JSONObject.fromObject(data);
		String dataType = content.getString("dataType");
		Integer paramId = content.getInt("paramId");
		int count = 0;
		// STR-字符串 INT-整形 BOL-布尔型 DEC-浮点型
		if ("STR".equalsIgnoreCase(dataType)) {
			AccessoryParamStrExample accessoryParamStrExample = new AccessoryParamStrExample();
			accessoryParamStrExample.createCriteria().andParamIdEqualTo(paramId);
			count = accessoryParamStrMapper.countByExample(accessoryParamStrExample);
		} else if ("INT".equalsIgnoreCase(dataType)) {
			AccessoryParamIntExample accessoryParamIntExample = new AccessoryParamIntExample();
			accessoryParamIntExample.createCriteria().andParamIdEqualTo(paramId);
			count = accessoryParamIntMapper.countByExample(accessoryParamIntExample);
		} else if ("BOL".equalsIgnoreCase(dataType)) {
			AccessoryParamBolExample accessoryParamBolExample = new AccessoryParamBolExample();
			accessoryParamBolExample.createCriteria().andParamIdEqualTo(paramId);
			count = accessoryParamBolMapper.countByExample(accessoryParamBolExample);
		} else if ("DEC".equalsIgnoreCase(dataType)) {
			AccessoryParamDecExample accessoryParamDecExample = new AccessoryParamDecExample();
			accessoryParamDecExample.createCriteria().andParamIdEqualTo(paramId);
			count = accessoryParamDecMapper.countByExample(accessoryParamDecExample);
		}
		String retCode = null;
		if (count > 0) {
			retCode = ErrorCode.FAILURE;
		} else {
			accessoryParamMapper.deleteByPrimaryKey(paramId);
			retCode = ErrorCode.SUCCESS;
		}
		log.info("end[AccessoryService.deleteAccessoryParamById]");
		return new HeadObject(retCode);
	}

	/**
	 * @description 通过Id删除配件分类信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月30日
	 * @param data
	 * @return
	 */
	public Object deleteAccessoryCatalogById(Object data) throws Exception {
		log.info("start[AccessoryService.deleteAccessoryCatalogById]");

		// 判断该配件分类下是否有配件
		AccessoryExample accessoryExample = new AccessoryExample();
		accessoryExample.createCriteria().andCatIdEqualTo((Integer) data);
		if (accessoryMapper.countByExample(accessoryExample) < 1) {
			// 删除配件参数值
			AccessoryParamBolExample accessoryParamBolExample = new AccessoryParamBolExample();
			accessoryParamBolExample.createCriteria().andCatalogIdEqualTo((Integer) data);
			accessoryParamBolMapper.deleteByExample(accessoryParamBolExample);

			AccessoryParamStrExample accessoryParamStrExample = new AccessoryParamStrExample();
			accessoryParamStrExample.createCriteria().andCatalogIdEqualTo((Integer) data);
			accessoryParamStrMapper.deleteByExample(accessoryParamStrExample);

			AccessoryParamDecExample accessoryParamDecExample = new AccessoryParamDecExample();
			accessoryParamDecExample.createCriteria().andCatalogIdEqualTo((Integer) data);
			accessoryParamDecMapper.deleteByExample(accessoryParamDecExample);

			AccessoryParamIntExample accessoryParamIntExample = new AccessoryParamIntExample();
			accessoryParamIntExample.createCriteria().andCatalogIdEqualTo((Integer) data);
			accessoryParamIntMapper.deleteByExample(accessoryParamIntExample);
			/* 删除配件参数项 */
			AccessoryParamExample accessoryParamExample = new AccessoryParamExample();
			accessoryParamExample.createCriteria().andCatalogIdEqualTo((Integer) data);
			accessoryParamMapper.deleteByExample(accessoryParamExample);
			/* 删除配件参数类型 */
			accessoryCatalogMapper.deleteByPrimaryKey((Integer) data);
		}else{
			return new HeadObject("PED008");
		}
		log.info("end[AccessoryService.deleteAccessoryCatalogById]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 保存配件详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月30日
	 * @param data
	 * @return
	 */
	public Object saveAccessoryCatalogDetail(Object data) throws Exception {
		log.info("start[AccessoryService.saveAccessoryCatalogDetail]");
		JSONObject content = JSONObject.fromObject(data);
		AccessoryCatalog accessoryCatalog = (AccessoryCatalog) JSONObject.toBean(content.getJSONObject("catalog"),AccessoryCatalog.class);
		AccessoryCatalogExample searchExample=new AccessoryCatalogExample();
		searchExample.createCriteria().andCatalogNameEqualTo(accessoryCatalog.getCatalogName());
		List<AccessoryCatalog> tempList=accessoryCatalogMapper.selectByExample(searchExample);
		/* 更新 */
		if (accessoryCatalog.getCatalogId() != null) {
			if(null!=tempList && tempList.size()>0){
				if(!tempList.get(0).getCatalogId().equals(accessoryCatalog.getCatalogId())){
					return new HeadObject(ErrorCode.IS_EXIST);
				}
			}
			accessoryCatalogMapper.updateByPrimaryKeySelective(accessoryCatalog);
		}
		/* 新增 */
		else {
			if(null!=tempList && tempList.size()>0){
				if(tempList.get(0).getCatalogName().equals(accessoryCatalog.getCatalogName())){
					return new HeadObject(ErrorCode.IS_EXIST);
				}
			}
			accessoryCatalogMapper.insertSelective(accessoryCatalog);
		}
		/* 新添加的配件参数项 */
		JSONArray inserted = content.optJSONArray("inserted");
		if (inserted != null && inserted.size() > 0) {
			List<AccessoryParam> list = new ArrayList<AccessoryParam>();
			Iterator<JSONObject> iterator = inserted.iterator();
			while (iterator.hasNext()) {
				AccessoryParam param = (AccessoryParam) JSONObject.toBean((JSONObject) iterator.next(),AccessoryParam.class);
				param.setCatalogId(accessoryCatalog.getCatalogId());
				param.setDataType(getAccessoryTypeStr(param.getDataType()));
				list.add(param);
			}
			/* 此处批量插入 */
			accessoryParamMapper.batchInsert(list);
		}
		/* 删除的配件参数项 */
		JSONArray deleted = content.optJSONArray("deleted");
		if (deleted != null && deleted.size() > 0) {
			List<Integer> list = new ArrayList<Integer>();
			Iterator<JSONObject> iterator = deleted.iterator();
			while (iterator.hasNext()) {
				list.add(((JSONObject) iterator.next()).getInt("paramId"));
			}
			/* 此处批量删除 */
			accessoryParamMapper.batchDelete(list);
		}
		/* 修改的配件参数项 */
		JSONArray updated = content.optJSONArray("updated");
		if (updated != null && updated.size() > 0) {
			List<AccessoryParam> list = new ArrayList<AccessoryParam>();
			Iterator<JSONObject> iterator = updated.iterator();
			while (iterator.hasNext()) {
				AccessoryParam param = (AccessoryParam) JSONObject.toBean((JSONObject) iterator.next(),AccessoryParam.class);
				param.setDataType(getAccessoryTypeStr(param.getDataType()));
				accessoryParamMapper.updateByPrimaryKeySelective(param);
				list.add(param);
			}
			/* 此处批量更新 */
//			accessoryParamMapper.batchUpdate(list);
		}
		log.info("end[AccessoryService.saveAccessoryCatalogDetail]");
		return new HeadObject(ErrorCode.SUCCESS);
	}
	private String getAccessoryTypeStr(String dataTypeStr){
		if ("字符".equalsIgnoreCase(dataTypeStr)) {
			return "STR";
		} else if ("整数".equalsIgnoreCase(dataTypeStr)) {
			return "INT";
		} else if ("布尔".equalsIgnoreCase(dataTypeStr)) {
			return "BOL";
		} else if ("小数".equalsIgnoreCase(dataTypeStr)) {
			return "DEC";
		}
		return null;
	}
	/**
	 * @description 通过ID查询配件参数分类及其数据项列表信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月30日
	 * @param data
	 * @return
	 */
	public Object getAccessoryCatalogDetailById(Object data) throws Exception {
		log.info("start[AccessoryService.getAccessoryCatalogDetailByParam]");
		AccessoryCatalog accessoryCatalog = accessoryCatalogMapper.selectByPrimaryKey((Integer) data);
		AccessoryParamExample example = new AccessoryParamExample();
		example.createCriteria().andCatalogIdEqualTo((Integer) data);
		List<AccessoryParam> params = accessoryParamMapper.selectByExample(example);
		JSONObject resultJson = new JSONObject();
		resultJson.put("catalog", accessoryCatalog);
		resultJson.put("params", params);
		log.info("end[AccessoryService.getAccessoryCatawlogDetailByParam]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), resultJson);
	}

	/**
	 * @description 查询配件参数分类信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月30日
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object getAccessoryCatalogByParam(Object data) throws Exception {
		log.info("start[AccessoryService.getAccessoryCatalogByParam]");
		JSONObject content = JSONObject.fromObject(data);
		PageHelper.startPage(QueryParamObject.getPage(content), QueryParamObject.getRows(content));
		@SuppressWarnings("unchecked")
        ResultPage<AccessoryCatalog> page = new ResultPage(accessoryCatalogMapper.selectByQueryParams(
				QueryParamObject.getMqls(content), QueryParamObject.getOrderByCause(content)));
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Integer.class, new com.cnit.yoyo.util.JsonIntegerValueProcessor());
		log.info("end[AccessoryService.getAccessoryCatalogByParam]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page, jsonConfig));
	}

	/**
	 * 
	 * @description 获取所有的配件参数分类信息，不需要分页
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-6
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object getAccessoryCatalogList(Object data) throws Exception {
		log.info("start[AccessoryService.getAccessoryCatalogList]");
		AccessoryCatalogExample example = new AccessoryCatalogExample();
		example.createCriteria().andCatIdEqualTo((Integer)data);
		log.info("end[AccessoryService.getAccessoryCatalogList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), accessoryCatalogMapper.selectByExample(example));
	}

	/**
	 * 
	 * @description 获得配件列表
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-6
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object getAccessoryCatalogDataList(Object data) throws Exception {
		log.info("start[AccessoryService.getAccessoryCatalogByParam]");
		AccessoryExample example = new AccessoryExample();
		Map<String,Integer> map =( Map<String,Integer> )data;
		
		if(map.get("brandId")!=null && map.get("brandId")!=0){
			example.createCriteria().andBrandIdEqualTo(map.get("brandId")).andDisabledEqualTo("0").andCatIdEqualTo(map.get("catalogId"));
		}else {
			example.createCriteria().andDisabledEqualTo("0").andCatIdEqualTo(map.get("catalogId")); //2015.07.16 添加 disabled=1表示可用
		}
		
		
		log.info("end[AccessoryService.getAccessoryCatalogByParam]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), accessoryMapper.selectByExample(example));
	}

	/**
	 * 
	 * @description 根据配件id查找配件类型
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-14
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object findAccessoryTypeInfo(Object data) throws Exception {
		log.info("start[AccessoryService.findAccessoryTypeInfo]");
		AccessoryCatalog info = accessoryMapper.findAccessoryTypeInfo((Integer) data);
		log.info("end[AccessoryService.findAccessoryTypeInfo]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), info);
	}
	
	/**
	 * 
	 *@description 获得保养配件类别
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-16
	 *@param data
	 *@return
	 *@throws Exception
	 */
	   public Object getMaintainAccessoryCat(Object data) throws Exception {
	        log.info("start[AccessoryService.getMaintainAccessoryCat]");
	        ResultPage<AccessoryCatalog> page = null;
	        JSONObject content = JSONObject.fromObject(data);
            PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
            page = new ResultPage<AccessoryCatalog>(accessoryCatalogMapper.findMaintainCatList());
	        log.info("end[AccessoryService.getMaintainAccessoryCat]");
	        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
	    }
	   
	 
   /**
    * @Title:  selectAccessoryById  
    * @Description:  TODO(根据主键查询对象)  
    * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
    * @date 2015-6-25 上午11:10:47  
    * @version 1.0.0 
    * @param @param data
    * @param @return
    * @param @throws Exception
    * @return Object  返回类型 
    * @throws
    */
	public Object selectAccessoryById(Object data) throws Exception {
		log.info("start[AccessoryService.selectAccessoryById]");
		AccessoryDTO accessory  = accessoryMapper.selectAccessoryById((Integer)data);
		if(accessory!=null){
			List<String> carNameList =  accessoryCarIndexMapper.selectCarNameByAccId(accessory.getAccId());
			if(carNameList!=null && carNameList.size()>=1){
				accessory.setCarName(carNameList.toString().substring(1, carNameList.toString().length()-1));
			}
		}
		log.info("end[AccessoryService.selectAccessoryById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), accessory);
	}
	
	/**
	 * 
	 *@description 查找适用车型
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-7-14
	 *@param data
	 *@return
	 */
   public Object findCars(Object data) {
        JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        List<AccessoryCarIndex> cars = accessoryMapper.selecBindingCarsByAccId(content.getInt("accId"));
        ResultPage<AccessoryCarIndex> page = new ResultPage<AccessoryCarIndex>(cars);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
    }
}
