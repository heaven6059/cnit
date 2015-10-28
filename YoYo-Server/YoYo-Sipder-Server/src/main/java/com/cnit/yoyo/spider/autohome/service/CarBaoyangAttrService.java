package com.cnit.yoyo.spider.autohome.service;

import java.util.List;

import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;



/**
 * 车保养详细信息
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface CarBaoyangAttrService {
	void addOrUpd(List<CarBaoyangAttr> eList);
}