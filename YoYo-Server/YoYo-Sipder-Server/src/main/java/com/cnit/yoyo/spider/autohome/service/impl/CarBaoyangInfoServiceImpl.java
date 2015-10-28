package com.cnit.yoyo.spider.autohome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.spider.autohome.dao.CarBaoyangInfoDao;
import com.cnit.yoyo.spider.autohome.service.CarBaoyangInfoService;
import com.cnit.yoyo.spider.autohome.service.CarInfoService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangInfo;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;



/**
 * @author yangyi
 *
 */
@Service
public class CarBaoyangInfoServiceImpl implements CarBaoyangInfoService{

	@Resource
	private CarBaoyangInfoDao carBaoyangInfoDao;
	
	@Override
	public void addOrUpd(List<CarBaoyangInfo> eList) {
		Map<String, CarBaoyangInfo> carBaoyangInfoMap = new HashMap<String, CarBaoyangInfo>();
		for (CarBaoyangInfo temp : eList) {
			String id = temp.getId();
			carBaoyangInfoMap.put(id, temp);
		}

		List<CarBaoyangInfo> addList = new ArrayList<CarBaoyangInfo>();
		List<CarBaoyangInfo> updList = new ArrayList<CarBaoyangInfo>();
		Set<String> keySet = carBaoyangInfoMap.keySet();
		Map<String, Integer> result = carBaoyangInfoDao.exists(keySet);
		for (String key : keySet) {
			Integer e = result.get(key);
			CarBaoyangInfo carBaoyangInfo = carBaoyangInfoMap.get(key);
			if (null != e && e > 0) {
				updList.add(carBaoyangInfo);
			} else {
				addList.add(carBaoyangInfo);
			}
		}

		if (CollectionUtils.isNotEmpty(addList)) {
			carBaoyangInfoDao.addOfBatch(addList);
		}
		if (CollectionUtils.isNotEmpty(updList)) {
			carBaoyangInfoDao.updOfBatch(updList);
		}
	}

}