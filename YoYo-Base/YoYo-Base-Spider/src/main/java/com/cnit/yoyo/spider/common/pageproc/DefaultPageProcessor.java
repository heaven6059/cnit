package com.cnit.yoyo.spider.common.pageproc;

import us.codecraft.webmagic.Page;

public class DefaultPageProcessor extends AbstractPageProcessor {

	@Override
	public void process(Page page) {
		logger.info("未进行解析页面:" + page.getUrl().get());
	}

}
