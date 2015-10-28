package com.cnit.yoyo.spider.autohome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.spider.autohome.dao.ColorInfoDao;
import com.cnit.yoyo.spider.autohome.service.ColorInfoService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.ColorInfo;


/**
 * @author yangyi
 *
 */
@Service
public class ColorInfoServiceImpl implements ColorInfoService {

	@Resource
	private ColorInfoDao colorInfoDao;

	@Override
	public void addOrUpd(Iterable<ColorInfo> eList) {
		Map<Long, ColorInfo> carInfoMap = new HashMap<Long, ColorInfo>();
		for (ColorInfo temp : eList) {
			Long id = temp.getId();
			carInfoMap.put(id, temp);
		}

		List<ColorInfo> addList = new ArrayList<ColorInfo>();
		List<ColorInfo> updList = new ArrayList<ColorInfo>();
		Set<Long> keySet = carInfoMap.keySet();
		Map<Long, Integer> result = colorInfoDao.exists(keySet);
		for (Long key : keySet) {
			Integer e = result.get(key);
			ColorInfo colorInfo = carInfoMap.get(key);
			if (null != e && e > 0) {
				updList.add(colorInfo);
			} else {
				addList.add(colorInfo);
			}
		}

		if (CollectionUtils.isNotEmpty(addList)) {
			colorInfoDao.addOfBatch(addList);
		}
		if (CollectionUtils.isNotEmpty(updList)) {
			colorInfoDao.updOfBatch(updList);
		}
	}

}