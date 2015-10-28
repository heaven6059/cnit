package com.cnit.yoyo.rmi.good.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.AccessoryCatalogMapper;
import com.cnit.yoyo.dao.car.AccessoryMapper;
import com.cnit.yoyo.dao.goods.GoodsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.AccessoryCatalog;
import com.cnit.yoyo.model.car.AccessoryParam;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;
import com.cnit.yoyo.model.goods.dto.AccessoryCatalogDTO;
import com.cnit.yoyo.model.goods.dto.AccessoryParamDTO;
import com.cnit.yoyo.model.goods.dto.AccessoryParamValueDTO;
import com.cnit.yoyo.model.goods.dto.GoodsAccessoryDTO;
import com.mysql.fabric.xmlrpc.base.Array;

/**  
* @Title: GoodsCompareService.java
* @Package com.cnit.yoyo.rmi.good.service
* @Description: 商品比较Service
* @Author 王鹏
* @date 2015-5-26 上午10:38:27
* @version V1.0  
*/ 
@Service("goodsCompareService")
public class GoodsCompareService {
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private AccessoryCatalogMapper accessoryCatalogMapper; 
	
	/**
	  * @description <b>查询商品配件信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-26
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object getGoodsAccessoryById(Object data) {
		HeadObject head=new HeadObject();
		Map<String, Object> resultMap=new TreeMap<String, Object>();
		try{
			List<GoodsAccessoryDTO> list = goodsMapper.selectGoodsAccessoryInfo((Integer []) data);
			List<AccessoryCatalogDTO> catalog=goodsMapper.selectGoodsAccessoryCatalog((Integer []) data);
			
			List<Map<String, Object>> compareList=new ArrayList<Map<String,Object>>();
			for(AccessoryCatalogDTO catalogDTO : catalog){
				Map<String, Object> catalogMap=new TreeMap<String, Object>();
				List<Map<String, Object>> catalogList=new ArrayList<Map<String,Object>>();
				catalogMap.put("name", catalogDTO.getCatalogName());
				catalogMap.put("catalog", catalogList);
				for (AccessoryParamDTO catalogParamDTO : catalogDTO.getParamDTOs()) {
					Map<String, Object> catalogParamMap=new TreeMap<String, Object>();
					List<Map<String, Object>> catalogParamList=new ArrayList<Map<String,Object>>();
					catalogParamMap.put("name", catalogParamDTO.getParamName());
					catalogParamMap.put("catalogChildren", catalogParamList);
					for (GoodsAccessoryDTO accessoryDTO : list) {
						for(AccessoryParamValueDTO valueDTO:accessoryDTO.getAccessoryParamValues()){
							if(valueDTO.getParamId().equals(catalogParamDTO.getParamId())){
								Map<String, Object> accessoryMap=new TreeMap<String, Object>();
								accessoryMap.put("goodsName", accessoryDTO.getGoodsName());
								accessoryMap.put("goodsImg", accessoryDTO.getGoodsImg());
								accessoryMap.put("goodsId", accessoryDTO.getGoodsId());
								accessoryMap.put("values", valueDTO.getValue());
								catalogParamList.add(accessoryMap);
							}
						}
					}
					catalogList.add(catalogParamMap);
				}
				compareList.add(catalogMap);
			}
			resultMap.put("goods", list);
			resultMap.put("compareResult", compareList);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), resultMap);
	}

	
	private static List<AccessoryCatalogDTO> getCataLogs(List<AccessoryCatalogDTO> list){
		List<AccessoryCatalogDTO> list2=new ArrayList<AccessoryCatalogDTO>();
		list2.addAll(list);
		for (int i = 0; i < list2.size() - 1; i++) {
			for (int j = list2.size() - 1; j > i; j--) {
				if (list2.get(j).getCatalogId().equals(list2.get(i).getCatalogId())) {
					list2.remove(j);
				}
			}
		}
		return list2;
	}
}
