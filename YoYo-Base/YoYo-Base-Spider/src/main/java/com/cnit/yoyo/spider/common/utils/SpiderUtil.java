package com.cnit.yoyo.spider.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬虫工具类
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public abstract class SpiderUtil {

	/**
	 * 从url获取一串id http://www.autohome.com.cn/8888/options.html 中截取出ID8888
	 * 
	 * @param url
	 * @return
	 */
	public static String fetechNumStr(String url) {
		Matcher matcher = Pattern.compile("(\\d+-?\\d*)").matcher(url);
		String id = null;
		if (matcher.find()) {
			id = matcher.group(1);
		}
		return id;
	}
	public static Long fetchNumLong(String url) {
		Matcher matcher = Pattern.compile("(\\d+)").matcher(url);
		String id = null;
		if (matcher.find()) {
			id = matcher.group(1);
		}
		return Long.valueOf(id);
	}
	
	
	public static void main(String[] args) {
		//http://car.autohome.com.cn/price/brand-8.html
		//http://www.autohome.com.cn/2997/
		//http://car.autohome.com.cn/price/brand-8-61.html
		String str = SpiderUtil.fetechNumStr("http://car.autohome.com.cn/price/brand-8-61.html");
		System.out.println(str);
	}
}
