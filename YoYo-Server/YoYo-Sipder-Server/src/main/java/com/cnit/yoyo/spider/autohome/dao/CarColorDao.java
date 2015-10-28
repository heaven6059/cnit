package com.cnit.yoyo.spider.autohome.dao;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarColor;


/**
 * 车颜色dao
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface CarColorDao {
	void add(CarColor entity);

	void addOfBatch(List<CarColor> eList);

	void upd(CarColor entity);

	void updOfBatch(List<CarColor> eList);

	/**
	 * @param ids
	 * @return
	 */
	Map<String, Integer> exists(Iterable<String> ids);
}
