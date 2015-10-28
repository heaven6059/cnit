package com.cnit.yoyo.spider.autohome.pageproc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.pipeline.CarTypeAttrPipeline;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.dto.autohome.vo.FactoryConfig;
import com.cnit.yoyo.spider.common.spider.SpiderHolder;
import com.cnit.yoyo.spider.common.utils.DateUtil;
import com.cnit.yoyo.spider.common.utils.HttpUtils;
import com.cnit.yoyo.spider.common.utils.SpiderUtil;
import com.cnit.yoyo.spider.common.utils.SpringContextHolder;


/**
 * 通用结果页面
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component
public class PublicAutohomePageproc extends AutohomePageProcessor {
	/** 日志对象 */
	private static Logger LOG = LoggerFactory.getLogger(PublicAutohomePageproc.class);
	private List<Html>				result;
	@Override
	public void process(Page page) {
		LOG.info(">>>>>");
		Html html = page.getHtml();
		result=new ArrayList<Html>();
		result.add(html);
	}
	public List<Html> getResult() {
		return result;
	}
	public void setResult(List<Html> result) {
		this.result = result;
	}

}
