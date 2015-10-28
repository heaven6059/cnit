package com.cnit.yoyo.spider.autohome.pipeline;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.service.CarAttrService;
import com.cnit.yoyo.spider.autohome.service.CarColorService;
import com.cnit.yoyo.spider.autohome.service.CarInfoService;
import com.cnit.yoyo.spider.autohome.service.ColorInfoService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarColor;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.ColorInfo;
import com.cnit.yoyo.spider.common.pipeline.AbstractPipeline;

/**
 * 车型数据处理
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component
public class CarTypeAttrPipeline extends AbstractPipeline {

	@Resource
	private CarInfoService carInfoService;
	@Resource
	private CarAttrService carAttrService;
	@Resource
	private ColorInfoService colorInfoService;
	@Resource
	private CarColorService carColorService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<CarInfo> typeCarList = resultItems.get(Constant.PIPE_CAR_TYPE);
		List<CarAttr> carAttrList = resultItems.get(Constant.PIPE_CAR_ATTR);
		// 车身颜色
		Collection<ColorInfo> colorInfoList = resultItems.get(Constant.PIPE_COLOR_INFO);
		// 车型 与 颜色的关系
		List<CarColor> carColorList = resultItems.get(Constant.PIPE_CAR_COLOR);

		// 持久化
		if (CollectionUtils.isNotEmpty(typeCarList)) {
			carInfoService.addOrUpd(typeCarList);
		}
		if (CollectionUtils.isNotEmpty(carAttrList)) {
			carAttrService.addOrUpd(carAttrList);
		}
		if (CollectionUtils.isNotEmpty(colorInfoList)) {
			colorInfoService.addOrUpd(colorInfoList);
		}
		if (CollectionUtils.isNotEmpty(carColorList)) {
			carColorService.addOrUpd(carColorList);
		}
	}

}
