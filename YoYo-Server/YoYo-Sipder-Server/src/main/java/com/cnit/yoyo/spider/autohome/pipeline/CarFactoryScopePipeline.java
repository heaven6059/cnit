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
 * 车系列表数据更新处理
 * @Author:yangyi  
 * @Date:2015年6月16日  
 * @Version:1.0.0
 */
@Component
public class CarFactoryScopePipeline extends AbstractPipeline {

	@Resource
	private CarInfoService	carInfoService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		//厂商级别
		List<CarInfo> factoryScopeList = resultItems.get(Constant.PIPE_CAR_FACTORY_SCOPE);
		// 持久化
		if(CollectionUtils.isNotEmpty(factoryScopeList)){
			carInfoService.addOrUpd(factoryScopeList);
		}
		
	}

}
