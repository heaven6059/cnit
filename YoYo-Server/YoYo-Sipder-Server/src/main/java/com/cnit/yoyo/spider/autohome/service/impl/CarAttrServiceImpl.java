package com.cnit.yoyo.spider.autohome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.spider.autohome.dao.CarAttrDao;
import com.cnit.yoyo.spider.autohome.service.CarAttrService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarAttr;

/**
 * @author yangyi
 *
 */
@Service
public class CarAttrServiceImpl implements CarAttrService {

	@Resource
	private CarAttrDao	carAttrDao;

	@Override
	public void addOrUpd(List<CarAttr> eList) {
		Map<String, CarAttr> carAttrMap = new HashMap<String, CarAttr>();
		for (CarAttr carAttr : eList) {
			String id = carAttr.getId();
			if(StringUtils.isEmpty(id)){
				continue;
			}
			carAttrMap.put(id, carAttr);
		}

		List<CarAttr> addList = new ArrayList<CarAttr>();
		List<CarAttr> updList = new ArrayList<CarAttr>();
		Set<String> keySet = carAttrMap.keySet();
		Map<String, Integer> result = carAttrDao.exists(keySet);
		for (String key : keySet) {
			Integer e = result.get(key);
			CarAttr carAttr = carAttrMap.get(key);
			if (null != e && e > 0) {
				updList.add(carAttr);
			} else {
				addList.add(carAttr);
			}
		}

		if (CollectionUtils.isNotEmpty(addList)) {
			carAttrDao.addOfBatch(addList);
		}
		if (CollectionUtils.isNotEmpty(updList)) {
			carAttrDao.updOfBatch(updList);
		}
	}

}
