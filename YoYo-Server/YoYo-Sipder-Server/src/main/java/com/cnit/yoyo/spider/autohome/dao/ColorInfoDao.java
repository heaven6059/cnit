package com.cnit.yoyo.spider.autohome.dao;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.ColorInfo;


/**
 * @author tcloud
 *
 */
public interface ColorInfoDao {
	void add(ColorInfo entity);

	void addOfBatch(List<ColorInfo> eList);

	void upd(ColorInfo entity);

	void updOfBatch(List<ColorInfo> eList);

	/**
	 * @param ids
	 * @return
	 */
	Map<Long, Integer> exists(Iterable<Long> ids);
}
