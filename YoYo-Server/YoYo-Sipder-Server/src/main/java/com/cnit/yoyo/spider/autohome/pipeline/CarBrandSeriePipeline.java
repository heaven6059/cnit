package com.cnit.yoyo.spider.autohome.pipeline;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.service.CarInfoService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.pipeline.AbstractPipeline;

/**
 * 车品牌数据处理
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component
public class CarBrandSeriePipeline extends AbstractPipeline {

	@Resource
	private CarInfoService	carInfoService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<CarInfo> brandCarList = resultItems.get(Constant.PIPE_CAR_BRAND);
		List<CarInfo> factoryCarList = resultItems.get(Constant.PIPE_CAR_FACTORY);
		List<CarInfo> serieCarList = resultItems.get(Constant.PIPE_CAR_SERIE);
		// 持久化
		brandCarList.addAll(factoryCarList);
		brandCarList.addAll(serieCarList);
		
		if(CollectionUtils.isNotEmpty(brandCarList)){
			carInfoService.addOrUpd(brandCarList);
		}
	}

}
