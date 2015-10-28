package com.cnit.yoyo.spider.autohome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.spider.autohome.dao.CarInfoDao;
import com.cnit.yoyo.spider.autohome.service.CarInfoService;
import com.cnit.yoyo.spider.autohome.service.CarSerieLevelService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;



/**
 * @author yangyi
 *
 */
@Service
public class CarSerieLevelServiceImpl implements CarSerieLevelService{

	@Resource
	private CarInfoDao carInfoDao;
	

	@Override
	public void upCarSerieLevel() {
		//查到所属车系
		List<CarInfo> serieInfos=carInfoDao.queryCarSerieInfos();
		//根据车系查询下面的所有车型
		//根据根据车型查到所有的车级别存放到set中
	}
	
}