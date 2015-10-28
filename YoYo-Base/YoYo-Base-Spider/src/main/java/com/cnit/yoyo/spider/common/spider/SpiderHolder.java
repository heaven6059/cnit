package com.cnit.yoyo.spider.common.spider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

import com.cnit.yoyo.spider.common.downloader.SpiderHttpClientDownloader;
import com.cnit.yoyo.spider.common.listener.DefaultSpiderListener;
import com.cnit.yoyo.spider.common.pageproc.AbstractPageProcessor;
import com.cnit.yoyo.spider.common.pageproc.DefaultPageProcessor;

/**
 * 爬虫处理类
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public class SpiderHolder {

	private static AbstractPageProcessor defaultPageProcessor = new DefaultPageProcessor();
	private static List<SpiderListener> defaultSpiderListeners = new ArrayList<SpiderListener>() {
		private static final long serialVersionUID = 1L;
		{
			add(new DefaultSpiderListener());
		}

	};
	private static SpiderHttpClientDownloader downloader = new SpiderHttpClientDownloader();

	private static Integer thread_count = 10;

	public static Spider getInstance(String... urls) {
		Spider spider = Spider.create(defaultPageProcessor).addUrl(urls).setSpiderListeners(defaultSpiderListeners)
				.setDownloader(downloader).thread(thread_count);
		return spider;
	}

	public static Spider getInstance(AbstractPageProcessor pageProcessor, String... urls) {
		Spider spider = Spider.create(pageProcessor).addUrl(urls).setSpiderListeners(defaultSpiderListeners)
				.setDownloader(downloader).thread(thread_count);
		return spider;
	}

	public static Spider getInstance(AbstractPageProcessor pageProcessor, Collection<String> urlList) {
		String[] urls = urlList.toArray(new String[] {});

		Spider spider = Spider.create(pageProcessor).addUrl(urls).setSpiderListeners(defaultSpiderListeners)
				.setDownloader(downloader).thread(thread_count);
		return spider;
	}
	
	public static Spider getFactoryScopeInstance(AbstractPageProcessor pageProcessor, Collection<String> urlList) {
		String[] urls = urlList.toArray(new String[] {});
		Spider spider = FactoryScopeSpider.create(pageProcessor).addUrl(urls).setSpiderListeners(defaultSpiderListeners)
				.setDownloader(downloader).thread(thread_count);
		return spider;
	}
	
	

}
