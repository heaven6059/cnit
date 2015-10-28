package com.cnit.yoyo.spider.autohome.service;

import java.util.List;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarAttr;


/**
 * 车属性
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface CarAttrService {
	void addOrUpd(List<CarAttr> eList);
}
