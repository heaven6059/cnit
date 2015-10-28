package com.cnit.yoyo.rmi.car.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarConfigMapper;
import com.cnit.yoyo.dao.car.CarDataCatalogMapper;
import com.cnit.yoyo.dao.car.CarMapper;
import com.cnit.yoyo.dao.goods.GoodsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.Car;
import com.cnit.yoyo.model.car.CarConfigWithBLOBs;
import com.cnit.yoyo.model.car.CarData;
import com.cnit.yoyo.model.car.dto.CarCompareDTO;
import com.cnit.yoyo.model.car.dto.CarConfiginfoDTO;
import com.cnit.yoyo.model.car.dto.CarDataCatalogDTO;
import com.cnit.yoyo.model.car.dto.CarDeptDTO;
import com.cnit.yoyo.model.goods.dto.GoodsCarDTO;
import com.cnit.yoyo.util.StringUtil;

/**  
* @Title: CarParameterService.java
* @Package com.cnit.yoyo.rmi.car.service
* @Description: 车型参数对比
* @Author 王鹏
* @date 2015-5-19 下午4:18:02
* @version V1.0  
*/ 
@Service("carParameterService")
public class CarParameterService {
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CarDataCatalogMapper carDataCatalogMapper;
	
	@Autowired
	private CarConfigMapper carConfigMapper;
	
	/**
	  * @description <b>获取车型参数</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-19
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findCarParameter(Object data){
		HeadObject head = new HeadObject();
		Map<String, Object> resultMap=new TreeMap<String, Object>(); 
		try {
			List<GoodsCarDTO> cars = goodsMapper.selectGoodsCar((Long[]) data);
			List<CarDataCatalogDTO> carDataCatalogDTOs = carDataCatalogMapper.selectCarDataCatalog();
			for (CarDataCatalogDTO dto : carDataCatalogDTOs) {//车型数据项分类
				for(CarData carData:dto.getCarDatas()){//车型数据项
					List<CarCompareDTO> compares=new ArrayList<CarCompareDTO>();
					carData.setCompares(compares);
					for (GoodsCarDTO car : cars) {
						JSONArray array = JSONArray.parseArray(car.getConfigs());//
						for (Object object : array) {
							JSONObject carConfig=(JSONObject) object;
							if(carData.getDataId().equals(carConfig.getInteger("dataId"))){
								CarCompareDTO compare=new CarCompareDTO();
								compare.setCarDataCategoryId(dto.getCatalogId());
								compare.setCarDataCategoryName(dto.getCatalogName());
								compare.setCarDataId(carData.getDataId());
								compare.setCarDataName(carData.getDisplayName());
								compare.setCarDataValue(carConfig.getString("configValue"));
								compares.add(compare);
								break;
							}
						}
					}
				}
			}
			resultMap.put("cars", cars);
			resultMap.put("carDataCatalogDTOs", carDataCatalogDTOs);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		
		return new ResultObject(head,resultMap);
	}
	
	public Object findCarByDept(Object deptId){
		HeadObject head = new HeadObject();
		List<CarDataCatalogDTO> carDataCatalogDTOs = carDataCatalogMapper.selectCarDataCatalog();
		CarDeptDTO carDept = carMapper.findCarDetailByDeptId((Integer) deptId);
		
		Map<String, Object> result=new TreeMap<String, Object>();
		result.put("seriesid", carDept.getCarDeptId());
		
		Map<String, Object> carNameMap=new TreeMap<String, Object>();
		List<Map<String, Object>> carNameList=new ArrayList<Map<String,Object>>();
		carNameMap.put("name", "车型名称");
		carNameMap.put("valueitems", carNameList);
		
		Map<String, Object> priceMap=new TreeMap<String, Object>();
		List<Map<String, Object>> priceList=new ArrayList<Map<String,Object>>();
		priceMap.put("name", "销售价");
		priceMap.put("valueitems", priceList);
		
		Map<String, Object> storePriceMap=new TreeMap<String, Object>();
		List<Map<String, Object>> storeList=new ArrayList<Map<String,Object>>();
		storePriceMap.put("name", "市场价");
		storePriceMap.put("valueitems", storeList);
		for (Car car : carDept.getCars()) {
			Map<String, Object> name=new TreeMap<String, Object>();
			name.put("specid", car.getCarId());
			name.put("value", car.getCarName());
			name.put("brandId", carDept.getBrandId());
			name.put("brandName", carDept.getBrandName());
			name.put("seriesId", carDept.getCarDeptId());
			name.put("seriesName", carDept.getCarDeptName());
			carNameList.add(name);
			
			Map<String, Object> price=new TreeMap<String, Object>();
			price.put("specid", car.getCarId());
			price.put("value", car.getPrice());
			priceList.add(price);
			
			
			Map<String, Object> storePrice=new TreeMap<String, Object>();
			storePrice.put("specid", car.getCarId());
			storePrice.put("value", car.getStorePrice());
			storeList.add(storePrice);
		}
		List<Map<String, Object>> paramitemsTypeList=new ArrayList<Map<String,Object>>();
		result.put("paramtypeitems", paramitemsTypeList);
		for (CarDataCatalogDTO catalog : carDataCatalogDTOs) {
			
			Map<String, Object> paramitemsMap=new TreeMap<String, Object>();
			List<Map<String, Object>> itemsTypeList=new ArrayList<Map<String,Object>>();
			if ("基本参数".equals(catalog.getCatalogName())) {
				itemsTypeList.add(carNameMap);
				itemsTypeList.add(priceMap);
				itemsTypeList.add(storePriceMap);
			}
			//装载车型数据项分类
			paramitemsMap.put("name", catalog.getCatalogName());
			paramitemsMap.put("paramitems", itemsTypeList);
			paramitemsTypeList.add(paramitemsMap);

			for(CarData carData:catalog.getCarDatas()){
				Map<String, Object> valueitems=new TreeMap<String, Object>();
				List<Map<String, Object>> valueItems=new ArrayList<Map<String,Object>>();
				try{
				//装载车型数据项
					valueitems.put("name", carData.getDisplayName());//车型数
					valueitems.put("valueitems", valueItems);
					itemsTypeList.add(valueitems);
				}catch(Exception e){
					e.printStackTrace();
				}
				for (Car car : carDept.getCars()) {
					if(null!=car.getCarConfigWithBLOBs()&&StringUtils.isNotEmpty(car.getCarConfigWithBLOBs().getConfigs())){
						JSONArray configArray=JSONArray.parseArray(car.getCarConfigWithBLOBs().getConfigs());
						for (Object carConfig : configArray) {
							try{
								JSONObject configObject=(JSONObject) carConfig;
								if(carData.getDataId().equals(configObject.getInteger("dataId"))){
									//装载车型数据项值
									Map<String, Object> map=new TreeMap<String, Object>();
									map.put("specid", car.getCarId());
									map.put("value", configObject.getString("configValue"));
									valueItems.add(map);
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head,result);
	}
	
	
	/**
	 * @Title:  selectConfigByCarId  
	 * @Description:  TODO(根据车型id查询车型配置)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> ,modify by ssd on 2015-6-2
	 * @date 2015-5-22 下午4:25:23  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectConfigByCarId(Object data){
		HeadObject head = new HeadObject();
		CarConfigWithBLOBs carConfig = null; 
		List<CarConfiginfoDTO> carConfigDtoList = null;
		try {
			carConfig = carConfigMapper.selectByCarId((Integer)data);
			if(null != carConfig) {
				carConfigDtoList = new ArrayList<CarConfiginfoDTO>();
				CarConfiginfoDTO dto = null;
				JSONObject jsonObject = null;
	       		String config = carConfig.getConfigs();
	           	 if(StringUtil.isEmpty(config)) {
	           		 head.setRetCode(ErrorCode.NO_DATA);
	           	 }else {
	           		 JSONArray ja = JSONArray.parseArray(config);
	           		 Object[] objs = ja.toArray();
	           		 for(int i=0;i<objs.length;i++) {
	           			dto = new CarConfiginfoDTO();
						jsonObject = (JSONObject) objs[i];
						dto.setDataId((String) jsonObject.get("dataId"));
						dto.setDisplayName((String) jsonObject.get("displayName"));
						dto.setDataType((String) jsonObject.get("dataType"));
						dto.setConfigValue((String) jsonObject.get("configValue"));
						carConfigDtoList.add(dto);
	           		 }
	           	 }
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		
		return new ResultObject(head,carConfigDtoList);
	}
	
	public Object findCarParameter2(Object data){
		HeadObject head = new HeadObject();
		JSONObject returnJson = new JSONObject();
		try {
			List<Car> cars = carMapper.findCarDetailByIds((Integer[]) data);
			List<CarDataCatalogDTO> carDataCatalogDTOs = carDataCatalogMapper.selectCarDataCatalog();
			for (CarDataCatalogDTO dto : carDataCatalogDTOs) {//车型数据项分类
				for(CarData carData:dto.getCarDatas()){//车型数据项
					List<CarCompareDTO> compares=new ArrayList<CarCompareDTO>();
					carData.setCompares(compares);
					for (Car car : cars) {
						JSONArray array = JSONArray.parseArray(car.getCarConfigWithBLOBs().getConfigs());//
						for (Object object : array) {
							JSONObject carConfig=(JSONObject) object;
							if(carData.getDataId().equals(carConfig.getInteger("dataId"))){
								CarCompareDTO compare=new CarCompareDTO();
								compare.setCar(car);
								compare.setCarDataCategoryId(dto.getCatalogId());
								compare.setCarDataCategoryName(dto.getCatalogName());
								compare.setCarDataId(carData.getDataId());
								compare.setCarDataName(carData.getDisplayName());
								compare.setCarDataValue(carConfig.getString("configValue"));
								compares.add(compare);
								break;
							}
						}
					}
				}
			}
//			resultMap.put("cars", cars);
//			resultMap.put("carDataCatalogDTOs", carDataCatalogDTOs);
			returnJson.put("cars", cars);
			returnJson.put("carDataCatalogDTOs", carDataCatalogDTOs);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		
		return new ResultObject(head,returnJson);
	}
}
