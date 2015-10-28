package com.cnit.yoyo.spider.autohome.pipeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.service.CarAttrService;
import com.cnit.yoyo.spider.autohome.service.CarBaoyangAttrService;
import com.cnit.yoyo.spider.autohome.service.CarBaoyangInfoService;
import com.cnit.yoyo.spider.autohome.service.CarColorService;
import com.cnit.yoyo.spider.autohome.service.CarInfoService;
import com.cnit.yoyo.spider.autohome.service.ColorInfoService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangInfo;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarColor;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.ColorInfo;
import com.cnit.yoyo.spider.common.pipeline.AbstractPipeline;

/**
 * 车保养数据处理
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component
public class CarBaoyangPipeline extends AbstractPipeline {

	@Resource
	private CarBaoyangInfoService carBaoyangInfoService;
	@Resource
	private CarBaoyangAttrService carBaoyangAttrService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		CarBaoyangInfo baoyangInfo=resultItems.get(Constant.PIPE_CAR_BAOYANG_INFO);
		List<CarBaoyangAttr> baoyangAttrList=resultItems.get(Constant.PIPE_CAR_BAOYANG_ATTR);

		// 持久化
		if (baoyangInfo != null) {
			List<CarBaoyangInfo> baoyangInfoList =new ArrayList<CarBaoyangInfo>();
			baoyangInfoList.add(baoyangInfo);
			carBaoyangInfoService.addOrUpd(baoyangInfoList);
		}
		if (CollectionUtils.isNotEmpty(baoyangAttrList)) {
			carBaoyangAttrService.addOrUpd(baoyangAttrList);
		}
	}

}
