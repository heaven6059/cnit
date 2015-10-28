package com.cnit.yoyo.spider.autohome.pageproc;

import us.codecraft.webmagic.Site;

import com.cnit.yoyo.spider.common.pageproc.AbstractPageProcessor;


/**
 * 页面解析
 * 
 * @author yangyi
 *
 */
public abstract class AutohomePageProcessor extends AbstractPageProcessor {

	@Override
	public Site getSite() {
		super.getSite().addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
				.addHeader("Accept-Encoding", "gzip, deflate")
				.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.addHeader("Connection", "keep-alive").addHeader("Host", "car.autohome.com.cn")
				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0")
				.addHeader("Referer", "http://www.autohome.com.cn/");
		return this.site;
	}

}
