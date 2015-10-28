/**
 * 文 件 名   :  SpecService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-18 下午2:05:46
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.good.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.goods.CatSpecShipMapper;
import com.cnit.yoyo.dao.goods.SpecGoodsIndexMapper;
import com.cnit.yoyo.dao.goods.SpecMapper;
import com.cnit.yoyo.dao.goods.SpecValuesMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.spec.SpecDTO;
import com.cnit.yoyo.dto.spec.SpecQryDTO;
import com.cnit.yoyo.model.goods.CatSpecShipExample;
import com.cnit.yoyo.model.goods.CompanyAndStoreBean;
import com.cnit.yoyo.model.goods.Spec;
import com.cnit.yoyo.model.goods.SpecGoodsIndex;
import com.cnit.yoyo.model.goods.SpecGoodsIndexExample;
import com.cnit.yoyo.model.goods.SpecValues;
import com.cnit.yoyo.model.goods.SpecValuesExample;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.goods.StoreBean;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description 商品规格业务处理
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Service("specService")
public class SpecService {
	public static final Logger log = LoggerFactory.getLogger(SpecService.class);
	@Autowired
	private SpecMapper specMapper;
	@Autowired
	private SpecValuesMapper specValuesMapper;
	@Autowired
	private SpecGoodsIndexMapper specGoodsIndexMapper;
	@Autowired
	private CatSpecShipMapper catSpecShipMapper;
	@Autowired
	private RemoteService memberService;

	/**
	 * @description 分页查询商品规格
	 * @detail 名称类的字段用模糊查询 id以及下拉的字段用等于查询
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 */
	public Object getSpecByParam(Object data) throws Exception {
		log.info("start[SpecService.getSpecByParam]");
		JSONObject content = JSONObject.fromObject(data);
		PageHelper.startPage(QueryParamObject.getPage(content), QueryParamObject.getRows(content));
		ResultPage<Spec> page = new ResultPage<Spec>(specMapper.selectByQueryParams(QueryParamObject.getMqls(content), QueryParamObject.getOrderByCause(content)));
		log.info("end[SpecService.getSpecByParam]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
	}

	/**
	 * @description 分页查询商品规格值
	 * @detail 名称类的字段用模糊查询 id以及下拉的字段用等于查询
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 */
	public Object getSpecValuesByParam(Object data) throws Exception {
		log.info("start[SpecService.getSpecValuesByParam]");
		JSONObject content = JSONObject.fromObject(data);
		PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
		SpecValuesExample example = new SpecValuesExample();
		if (!StringUtil.isEmpty(content.getString("specValueId"))) {// 规格值Id
			example.createCriteria().andSpecValueIdEqualTo(Integer.parseInt(content.getString("specValueId")));
		}
		if (!StringUtil.isEmpty(content.getString("specId"))) {// 规格Id
			example.createCriteria().andSpecIdEqualTo(Integer.parseInt(content.getString("specId")));
		}
		if (!StringUtil.isEmpty(content.getString("specValueName"))) {// 规格值名称
			example.createCriteria().andSpecValueNameLike(content.getString("specValueName"));
		}
		if (!StringUtil.isEmpty(content.getString("specValueAlias"))) {// 规格值别名
			example.createCriteria().andSpecValueAliasLike(content.getString("specValueAlias"));
		}
		if (!StringUtil.isEmpty(content.getString("specValue"))) {// 规格值
			example.createCriteria().andSpecValueEqualTo(content.getString("specValue"));
		}
		if (!StringUtil.isEmpty(content.getString("disabled"))) {// 是否可用
			example.createCriteria().andDisabledEqualTo(content.getString("disabled"));
		}
		ResultPage<SpecValues> page = new ResultPage(specValuesMapper.selectByExample(example));
		log.info("end[SpecService.getSpecValuesByParam]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
	}

	/**
	 * @description 根据规格ID查询规格及其规格值详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object getSpecAndValuesById(Object data) throws Exception {
		log.info("start[SpecService.getSpecAndValuesById]");
		Integer specId = (Integer) data;
		// 查询规格
		Spec spec = specMapper.selectByPrimaryKey(specId);
		// 查询规格值
		SpecValuesExample example = new SpecValuesExample();
		example.createCriteria().andSpecIdEqualTo(specId);
		List<SpecValues> values = specValuesMapper.selectByExample(example);
		JSONObject resultJson = new JSONObject();
		resultJson.put("spec", spec);
		resultJson.put("values", values);
		log.info("end[SpecService.getSpecAndValuesById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), resultJson);
	}

	/**
	 * @description 新增规格及规格值
	 * @detail data:由spec与values值列表两个部分组成
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 */
	public Object saveSpecAndValues(Object data) throws Exception {
		log.info("start[SpecService.saveSpecAndValues]");
		JSONObject content = JSONObject.fromObject(data);
		Spec spec = (Spec) JSONObject.toBean(content.getJSONObject("spec"), Spec.class);
		PageHelper.startPage(0, 1);
		JSONObject content1 =new JSONObject();
		net.sf.json.JSONArray equals =new  net.sf.json.JSONArray();
		JSONObject equal = new JSONObject();
		equal.put("paramName", "specNameQ");
		equal.put("paramValue",spec.getSpecName());
		equals.add(equal);
		content1.put("equals", equals);
		ResultPage<Spec> page = new ResultPage<Spec>(specMapper.selectByQueryParams(QueryParamObject.getMqls(content1), QueryParamObject.getOrderByCause(content1)));
		if (spec.getSpecId() != null) {// 修改
			if(page.getTotal()>0 &&page.getRows()!=null && (!spec.getSpecId().equals(page.getRows().get(0).getSpecId()))){
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			specMapper.updateByPrimaryKeySelective(spec);
			JSONArray inserted = content.getJSONArray("inserted");// 新添加的规格值
			Iterator insertedIter = inserted.iterator();
			while (insertedIter.hasNext()) {
				JSONObject object = (JSONObject) insertedIter.next();
				SpecValues specValues = (SpecValues) JSONObject.toBean(object, SpecValues.class);
				specValues.setSpecId(spec.getSpecId());
				specValuesMapper.insertSelective(specValues);
			}
			JSONArray updated = content.getJSONArray("updated");// 修改的规格值
			Iterator updatedIter = updated.iterator();
			while (updatedIter.hasNext()) {
				JSONObject object = (JSONObject) updatedIter.next();
				SpecValues specValues = (SpecValues) JSONObject.toBean(object, SpecValues.class);
				SpecGoodsIndexExample example=new SpecGoodsIndexExample();
				example.createCriteria().andSpecValueIdEqualTo(specValues.getSpecValueId());
				List<SpecGoodsIndex> list=specGoodsIndexMapper.selectByExample(example);
				if(null!=list && list.size()>0){//规格值已经关联商品不能更新
					return new HeadObject(ErrorCode.THE_NUMBER_OVER);
				}
				specValuesMapper.updateByPrimaryKeySelective(specValues);
			}
			JSONArray deleted = content.getJSONArray("deleted");// 删除的规格值
			Iterator deletedIter = deleted.iterator();
			while (deletedIter.hasNext()) {
				JSONObject object = (JSONObject) deletedIter.next();
				SpecGoodsIndexExample example=new SpecGoodsIndexExample();
				example.createCriteria().andSpecValueIdEqualTo(object.getInt("specValueId"));
				List<SpecGoodsIndex> list=specGoodsIndexMapper.selectByExample(example);
				if(null!=list && list.size()>0){//规格值已经关联商品不能更新
					return new HeadObject(ErrorCode.THE_NUMBER_OVER);
				}
				specValuesMapper.deleteByPrimaryKey(object.getInt("specValueId"));
			}
		} else {// 新增
			if(page.getTotal()>0){
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			specMapper.insertSelective(spec);
			JSONArray values = content.getJSONArray("inserted");
			if (values.size() > 0) {
				Iterator iterator = values.iterator();
				while (iterator.hasNext()) {
					JSONObject object = (JSONObject) iterator.next();
					SpecValues specValues = (SpecValues) JSONObject.toBean(object, SpecValues.class);
					specValues.setSpecId(spec.getSpecId());
					specValuesMapper.insertSelective(specValues);
				}
			}
		}
		log.info("end[SpecService.saveSpecAndValues]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 修改规格
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 */
	private void modifySpec(Object data) {
		JSONObject content = JSONObject.fromObject(data);
		log.info("start[SpecService.modifySpec]");
		Spec spec = (Spec) JSONObject.toBean(content, Spec.class);
		specMapper.updateByPrimaryKeySelective(spec);
		log.info("end[SpecService.modifySpec]");
	}

	/**
	 * @description 修改规格值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 */
	private void modifySpecValues(Object data) throws Exception {
		log.info("start[SpecService.modifySpecValues]");
		JSONArray content = JSONArray.fromObject(data);
		Iterator iterator = content.iterator();
		while (iterator.hasNext()) {
			JSONObject object = (JSONObject) iterator.next();
			SpecValues specValues = (SpecValues) JSONObject.toBean(object, SpecValues.class);
			specValuesMapper.updateByPrimaryKeySelective(specValues);
		}
		log.info("end[SpecService.modifySpecValues]");
	}

	/**
	 * @description 修改规格及规格值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 */
	public Object modifySpecAndValues(Object data) throws Exception {
		log.info("start[SpecService.modifySpecAndValues]");
		JSONObject content = JSONObject.fromObject(data);
		modifySpec(content.get("spec"));
		modifySpecValues(content.get("values"));
		log.info("end[SpecService.modifySpecAndValues]");
		return new HeadObject(ErrorCode.SUCCESS);
	}

	/**
	 * @description 删除规格及规格值
	 * @detail
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 */
	public Object deleteSpec(Object data) throws Exception {
		log.info("start[SpecService.deleteSpec]");
		Integer specId = Integer.parseInt((String) data);
		CatSpecShipExample catSpecShipExample = new CatSpecShipExample();
		catSpecShipExample.createCriteria().andSpecIdEqualTo(specId);
		// 判断该规格下是否已绑定分类 绑定分类的规格不能被删除
		if (catSpecShipMapper.countByExample(catSpecShipExample) < 1) {
			/*
			 * SpecGoodsIndexExample specGoodsIndexExample = new
			 * SpecGoodsIndexExample();
			 * specGoodsIndexExample.createCriteria().andSpecIdEqualTo(specId);
			 * if (specGoodsIndexMapper.countByExample(specGoodsIndexExample) <
			 * 1) {
			 */
			SpecValuesExample specValuesExample = new SpecValuesExample();
			specValuesExample.createCriteria().andSpecIdEqualTo(specId);
			specValuesMapper.deleteByExample(specValuesExample);
			specMapper.deleteByPrimaryKey(specId);
			// }
			return new HeadObject(ErrorCode.SUCCESS);
		} else {
			log.info("end[SpecService.deleteSpec]");
			return new HeadObject("PDE006");
		}
	}

	/**
	 * 
	 * @Title: selectSpecAndValueById
	 * @Description: 根据商品规格值id列表查询商品规格数据
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @param @param data
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public Object selectSpecAndValueById(Object data) throws Exception {
		log.info("start[SpecService.selectSpecAndValueById]");
		List<Integer> valueIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(data), Integer.class);
		List<SpecQryDTO> specQryDTOList = specValuesMapper.selectSpecAndValueById(valueIdList);
		log.info("end[SpecService.selectSpecAndValueById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(specQryDTOList));
	}

	/**
	 * @description 根据分类ID查询规格及规格值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月14日
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object getSpecAndValuesByCatId(Object data) throws Exception {
		log.info("start[SpecService.getSpecAndValuesByCatId]");
		JSONObject content = JSONObject.fromObject(data);
		List<SpecDTO> catSpecShips = specMapper.selectSpecAndSepcVal(content.getInt("catId"),content.getInt("carId"));

		// 查询集团信息
		Long companyId = content.optLong("companyId");
		if (companyId != null) {
			HeadObject headObject = CommonHeadUtil.geneHeadObject("companyService.selectCompanyAndStoreById");
			CompanyAndStoreBean company = (CompanyAndStoreBean) memberService.doServiceByServer(new RequestObject(headObject, companyId));
			if (GlobalStatic.SHOP_TYPE_COMPANY.equalsIgnoreCase(company.getIssueType())) {
				List<StoreBean> stores = company.getStores();
				if (stores != null && stores.size() > 0) {
					SpecDTO spec = new SpecDTO();
					spec.setSpecId(content.getInt("companyId")); // 店铺地址id
					spec.setSpecName("分店");
					List<SpecValues> valuesList = new ArrayList<SpecValues>();
					for (StoreBean storeBean : stores) {
						SpecValues value = new SpecValues();
						value.setSpecId(storeBean.getCompanyId());
						value.setSpecValueId(storeBean.getStoreId()); // 分店id
						value.setSpecValue(storeBean.getStoreName());
						value.setSpecValueName(storeBean.getStoreName());
						value.setSpecValueAlias(storeBean.getStoreName());
						value.setLimitGoods(storeBean.getLimitGoods());
						valuesList.add(value);
					}
					spec.setValues(valuesList);
					catSpecShips.add(spec);
				}
			}
		}
		// 根据登陆者是否为集团店铺 ，店铺id查找分店地址，作为默认规格
		/*
		 * if(content.getString("gradeType").equals(GlobalStatic.SHOP_TYPE_COMPANY
		 * )){ //集团类型 HeadObject headObject =
		 * CommonHeadUtil.geneHeadObject("storeService.findShopByCId");
		 * 
		 * @SuppressWarnings("unchecked") List<Store> stores = (List<Store> )
		 * memberService.doServiceByServer(new
		 * RequestObject(headObject,content.getInt("companyId")));
		 * List<SpecValues> valuesList = new ArrayList<SpecValues>();
		 * if(stores!=null && stores.size()>0){ for(Store s :stores){ SpecValues
		 * value = new SpecValues();
		 * value.setSpecId(content.getInt("companyId"));
		 * value.setSpecValueId(s.getStoreId().intValue()); //分店id
		 * value.setSpecValueName(s.getStoreName()); valuesList.add(value); }
		 * spec.setValues(valuesList); catSpecShips.add(spec); } }
		 */

		log.info("end[SpecService.getSpecAndValuesByCatId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(catSpecShips));
	}

}
