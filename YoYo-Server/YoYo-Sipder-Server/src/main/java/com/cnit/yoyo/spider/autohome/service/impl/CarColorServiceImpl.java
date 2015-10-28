package com.cnit.yoyo.spider.autohome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.spider.autohome.dao.CarColorDao;
import com.cnit.yoyo.spider.autohome.service.CarColorService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarColor;

/**
 * @author yangyi
 *
 */
@Service
public class CarColorServiceImpl implements CarColorService {

	@Resource
	private CarColorDao	carColorDao;

	@Override
	public void addOrUpd(List<CarColor> eList) {
		Map<String, CarColor> carAttrMap = new HashMap<String, CarColor>();
		for (CarColor temp : eList) {
			Long carTypeId = temp.getCarTypeId();
			Long colorId = temp.getColorId();
			carAttrMap.put(String.valueOf(carTypeId+"-"+colorId), temp);
		}

		List<CarColor> addList = new ArrayList<CarColor>();
		List<CarColor> updList = new ArrayList<CarColor>();
		Set<String> keySet = carAttrMap.keySet();
		Map<String, Integer> result = carColorDao.exists(keySet);
		for (String key : keySet) {
			Integer e = result.get(key);
			CarColor carColor = carAttrMap.get(key);
			if (null != e && e > 0) {
				updList.add(carColor);
			} else {
				addList.add(carColor);
			}
		}

		if (CollectionUtils.isNotEmpty(addList)) {
			carColorDao.addOfBatch(addList);
		}
		if (CollectionUtils.isNotEmpty(updList)) {
			carColorDao.updOfBatch(updList);
		}
	}

}
