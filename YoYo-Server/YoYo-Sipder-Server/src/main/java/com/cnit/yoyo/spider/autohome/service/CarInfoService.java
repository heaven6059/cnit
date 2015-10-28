package com.cnit.yoyo.spider.autohome.service;

import java.util.List;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;



/**
 * 车信息
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface CarInfoService {
	void addOrUpd(List<CarInfo> eList);
	/**
	 * 执行存储过程
	 */
	void batchUpdateCarLevel();
	
	CarInfo queryByKey(CarInfo parm);
}