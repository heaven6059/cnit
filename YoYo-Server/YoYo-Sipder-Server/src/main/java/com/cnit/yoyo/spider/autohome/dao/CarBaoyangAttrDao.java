package com.cnit.yoyo.spider.autohome.dao;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangInfo;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;


/**
 * 车信息dao
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface CarBaoyangAttrDao {
	void add(CarBaoyangAttr entity);

	void addOfBatch(List<CarBaoyangAttr> eList);

	void upd(CarBaoyangAttr entity);

	void updOfBatch(List<CarBaoyangAttr> eList);

	/**
	 * @param ids
	 * @return
	 */
	Map<String, Integer> exists(Iterable<String> ids);
}
