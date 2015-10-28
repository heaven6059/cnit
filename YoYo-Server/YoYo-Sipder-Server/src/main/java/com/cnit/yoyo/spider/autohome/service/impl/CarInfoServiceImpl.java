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
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;



/**
 * @author yangyi
 *
 */
@Service
public class CarInfoServiceImpl implements CarInfoService{

	@Resource
	private CarInfoDao carInfoDao;
	
	@Override
	public void addOrUpd(List<CarInfo> eList) {
		Map<String, CarInfo> carInfoMap = new HashMap<String, CarInfo>();
		for (CarInfo temp : eList) {
			String id = temp.getId();
			carInfoMap.put(id, temp);
		}

		List<CarInfo> addList = new ArrayList<CarInfo>();
		List<CarInfo> updList = new ArrayList<CarInfo>();
		Set<String> keySet = carInfoMap.keySet();
		Map<String, Integer> result = carInfoDao.exists(keySet);
		for (String key : keySet) {
			Integer e = result.get(key);
			CarInfo carInfo = carInfoMap.get(key);
			if (null != e && e > 0) {
				updList.add(carInfo);
			} else {
				addList.add(carInfo);
			}
		}

		if (CollectionUtils.isNotEmpty(addList)) {
			carInfoDao.addOfBatch(addList);
		}
		if (CollectionUtils.isNotEmpty(updList)) {
			carInfoDao.updOfBatch(updList);
		}
	}

	@Override
	public CarInfo queryByKey(CarInfo entity) {
		return carInfoDao.queryByKey(entity);
	}

	@Override
	public void batchUpdateCarLevel() {
		carInfoDao.batchUpdateCarLevel();
	}
	
}