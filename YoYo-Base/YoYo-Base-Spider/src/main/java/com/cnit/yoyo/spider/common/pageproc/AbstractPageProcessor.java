package com.cnit.yoyo.spider.common.pageproc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class AbstractPageProcessor implements PageProcessor {
	protected Logger	logger	= LoggerFactory.getLogger(this.getClass());

	protected Site		site	= Site.me().setCycleRetryTimes(6000000).setRetryTimes(2000).setSleepTime(2000).setTimeOut(60000);
	protected String	url;


	@Override
	public Site getSite() {
		return this.site;
	}

}
