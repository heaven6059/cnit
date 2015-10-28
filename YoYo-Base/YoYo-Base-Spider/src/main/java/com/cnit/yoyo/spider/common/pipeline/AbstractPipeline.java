package com.cnit.yoyo.spider.common.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.pipeline.Pipeline;

public abstract class AbstractPipeline implements Pipeline {
	protected Logger	logger	= LoggerFactory.getLogger(this.getClass());
}
