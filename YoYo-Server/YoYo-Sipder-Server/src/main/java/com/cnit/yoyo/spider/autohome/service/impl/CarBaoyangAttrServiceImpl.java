package com.cnit.yoyo.spider.autohome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.spider.autohome.dao.CarBaoyangAttrDao;
import com.cnit.yoyo.spider.autohome.dao.CarColorDao;
import com.cnit.yoyo.spider.autohome.service.CarBaoyangAttrService;
import com.cnit.yoyo.spider.autohome.service.CarColorService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarColor;

/**
 * @author yangyi
 *
 */
@Service
public class CarBaoyangAttrServiceImpl implements CarBaoyangAttrService {

	@Resource
	private CarBaoyangAttrDao	carBaoyangAttrDao;

	@Override
	public void addOrUpd(List<CarBaoyangAttr> eList) {
		Map<String, CarBaoyangAttr> carBaoyangAttrMap = new HashMap<String, CarBaoyangAttr>();
		for (CarBaoyangAttr temp : eList) {
			String id = temp.getId();
			carBaoyangAttrMap.put(id, temp);
		}

		List<CarBaoyangAttr> addList = new ArrayList<CarBaoyangAttr>();
		List<CarBaoyangAttr> updList = new ArrayList<CarBaoyangAttr>();
		Set<String> keySet = carBaoyangAttrMap.keySet();
		Map<String, Integer> result = carBaoyangAttrDao.exists(keySet);
		for (String key : keySet) {
			Integer e = result.get(key);
			CarBaoyangAttr carBaoyangAttr = carBaoyangAttrMap.get(key);
			if (null != e && e > 0) {
				updList.add(carBaoyangAttr);
			} else {
				addList.add(carBaoyangAttr);
			}
		}

		if (CollectionUtils.isNotEmpty(addList)) {
			carBaoyangAttrDao.addOfBatch(addList);
		}
		if (CollectionUtils.isNotEmpty(updList)) {
			carBaoyangAttrDao.updOfBatch(updList);
		}
	}


}
