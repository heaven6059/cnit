package com.cnit.yoyo.spider.autohome.service;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.ColorInfo;


/**
 * 颜色信息
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface ColorInfoService {
	void addOrUpd(Iterable<ColorInfo> eList);
}