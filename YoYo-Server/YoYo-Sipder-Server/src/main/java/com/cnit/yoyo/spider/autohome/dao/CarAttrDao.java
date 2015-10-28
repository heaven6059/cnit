package com.cnit.yoyo.spider.autohome.dao;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarAttr;


/**
 * 车属性dao
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface CarAttrDao {
	void add(CarAttr entity);

	void addOfBatch(List<CarAttr> eList);

	void upd(CarAttr entity);

	void updOfBatch(List<CarAttr> eList);

	/**
	 * @param ids
	 * @return
	 */
	Map<String, Integer> exists(Iterable<String> ids);
}
