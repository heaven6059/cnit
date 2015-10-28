package com.cnit.yoyo.spider.autohome.dao;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;


/**
 * 车信息dao
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface CarInfoDao {
	void add(CarInfo entity);

	void addOfBatch(List<CarInfo> eList);

	void upd(CarInfo entity);

	void updOfBatch(List<CarInfo> eList);
	
	CarInfo queryByKey(CarInfo entity);
	
	void batchUpdateCarLevel();
	/**
	 * 查询车系集合
	 * @Description:
	 * @return
	 */
	List<CarInfo> queryCarSerieInfos();
	/**
	 * @param ids
	 * @return
	 */
	Map<String, Integer> exists(Iterable<String> ids);
}
