package com.cnit.yoyo.spider.common.spider;  

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
  
public class FactoryScopeSpider extends Spider {

	public FactoryScopeSpider(PageProcessor pageProcessor) {
		super(pageProcessor);  
	}

	/**
     * create a spider with pageProcessor.
     *
     * @param pageProcessor
     * @return new spider
     * @see PageProcessor
     */
    public static Spider create(PageProcessor pageProcessor) {
        return new FactoryScopeSpider(pageProcessor);
    }

	@Override
	public Spider addUrl(String... urls) {
		List<String> tempUrls=new ArrayList<String>();
		for(String url :urls){
			tempUrls.add(url+System.currentTimeMillis());
		}
		urls = tempUrls.toArray(new String[] {});
		return super.addUrl(urls);
    }
}
