package com.cnit.yoyo.spider.autohome.task;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.cnit.yoyo.spider.autohome.pageproc.CarBaoyangPageproc;
import com.cnit.yoyo.spider.autohome.pageproc.CarBrandSeriePageproc;
import com.cnit.yoyo.spider.autohome.pipeline.CarBaoyangPipeline;
import com.cnit.yoyo.spider.autohome.pipeline.CarBrandSeriePipeline;
import com.cnit.yoyo.spider.common.spider.SpiderHolder;
import com.cnit.yoyo.spider.common.utils.SpringContextHolder;

/**
 * 汽车之家爬虫调度类
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component("autohomeSpiderTask")
public class AutohomeSpiderTask {
	/** 日志对象 */

	private static final Log LOG = LogFactory.getLog(AutohomeSpiderTask.class.getName());
    private String url = "http://car.autohome.com.cn/zhaoche/pinpai/";
    //http://car.autohome.com.cn/baoyang/index.html
    public void execute() {
        if (LOG.isInfoEnabled()) {
            LOG.info("汽车之家爬虫任务线程开始执行>>>>>");
    		CarBrandSeriePageproc carBrandPageproc = SpringContextHolder.getBean(CarBrandSeriePageproc.class);
    		CarBrandSeriePipeline carBrandPipeline = SpringContextHolder.getBean(CarBrandSeriePipeline.class);
    		SpiderHolder.getInstance(carBrandPageproc, url).addPipeline(carBrandPipeline).runAsync();
        }
    }
    
    public void testBaoyang(){
    	LOG.info(111);
    	String url1 = "http://car.autohome.com.cn/baoyang/detail_0_364_12137_0_0_0_0.html";
    	String url2 = "http://car.autohome.com.cn/Baoyang/detail_999_364_12127_0_0_0.html";
    	List<String> urls=new LinkedList<String>();
    	urls.add(url1);
    	urls.add(url2);
    	// 继续抓取,新启一个保养数据的spider
		CarBaoyangPageproc carBaoyangPageproc = SpringContextHolder.getBean(CarBaoyangPageproc.class);
		CarBaoyangPipeline carBaoyangPipeline = SpringContextHolder.getBean(CarBaoyangPipeline.class);
		SpiderHolder.getInstance(carBaoyangPageproc, urls).addPipeline(carBaoyangPipeline).runAsync();
    }
    
}
