package com.cnit.yoyo.spider.common.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

/**
 * 爬虫抓取监听
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public abstract class AbstractSpiderListener implements SpiderListener {
	protected Logger	logger	= LoggerFactory.getLogger(this.getClass());
	
	protected final AtomicInteger	successCount	= new AtomicInteger(0);

	protected final AtomicInteger	errorCount		= new AtomicInteger(0);

	protected List<String>			errorUrls		= Collections.synchronizedList(new ArrayList<String>());

	@Override
	public void onSuccess(Request request) {
		successCount.incrementAndGet();
	}

	@Override
	public void onError(Request request) {
		String url = request.getUrl();
		errorUrls.add(url);
		errorCount.incrementAndGet();
		logger.error("抓取失败: " + url + ",当前成功计数:"+successCount+",当前失败计数:"+errorCount);
		
	}

	public AtomicInteger getSuccessCount() {
		return successCount;
	}

	public AtomicInteger getErrorCount() {
		return errorCount;
	}

	public List<String> getErrorUrls() {
		return errorUrls;
	}
}
