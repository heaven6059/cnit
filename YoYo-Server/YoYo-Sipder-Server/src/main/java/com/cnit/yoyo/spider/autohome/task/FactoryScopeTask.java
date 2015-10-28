package com.cnit.yoyo.spider.autohome.task;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.spider.autohome.pageproc.CarBaoyangPageproc;
import com.cnit.yoyo.spider.autohome.pageproc.CarBrandSeriePageproc;
import com.cnit.yoyo.spider.autohome.pageproc.CarFactoryScopePageproc;
import com.cnit.yoyo.spider.autohome.pipeline.CarBaoyangPipeline;
import com.cnit.yoyo.spider.autohome.pipeline.CarBrandSeriePipeline;
import com.cnit.yoyo.spider.autohome.pipeline.CarFactoryScopePipeline;
import com.cnit.yoyo.spider.autohome.service.CarInfoService;
import com.cnit.yoyo.spider.common.spider.SpiderHolder;
import com.cnit.yoyo.spider.common.utils.SpringContextHolder;

/**
 * 车系类型更新线程
 * @Author:yangyi  
 * @Date:2015年6月16日  
 * @Version:1.0.0
 */
@Component("factoryScopeTask")
public class FactoryScopeTask {
	/** 日志对象 */
	private static final Log LOG = LogFactory.getLog(FactoryScopeTask.class.getName());
	
	private static List<String> urls=new LinkedList<String>();
	static{
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=1&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//中国
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=2&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//德国
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=3&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//日本
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=4&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//美国
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=5&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//韩国
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=6&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//法国
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=7&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//英国
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=8&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//意大利
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=9&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//瑞典
		urls.add("http://car.autohome.com.cn/zhaoche/AsLoadMore.ashx?page=0&price=0&brandid=0&country=11&deliveryCapacity=0&level=0&struct=0&seat=0&_=");//捷克
	}
    
    public void execute() {
        if (LOG.isInfoEnabled()) {
            LOG.info("汽车之家车系类型更新线程开始执行>>>>>");
            CarInfoService carInfoService=SpringContextHolder.getBean(CarInfoService.class);
            LOG.info("车级别、车年款更新线程开始执行>>>>>");
            carInfoService.batchUpdateCarLevel();
            LOG.info("车级别、车年款更新线程结束执行<<<<<");
            CarFactoryScopePageproc carFactoryScopePageproc = SpringContextHolder.getBean(CarFactoryScopePageproc.class);
            CarFactoryScopePipeline carFactoryScopePipeline = SpringContextHolder.getBean(CarFactoryScopePipeline.class);
    		SpiderHolder.getFactoryScopeInstance(carFactoryScopePageproc, urls).addPipeline(carFactoryScopePipeline).runAsync();
        }
    }
    
    
}
