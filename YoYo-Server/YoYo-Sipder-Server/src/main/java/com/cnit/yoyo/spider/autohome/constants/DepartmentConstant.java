package com.cnit.yoyo.spider.autohome.constants;

import com.cnit.yoyo.spider.Config.Configuration;

/**
 * 系别常量
 * @Author:yangyi  
 * @Date:2015年6月16日  
 * @Version:1.0.0
 */
public abstract class DepartmentConstant {
	/**
	 * 中国
	 */
	public static final Integer PIPE_CHINA = Integer.valueOf(Configuration.getInstance().getConfigValue("country.china", "7"));
	/**
	 * 德国
	 */
	public static final Integer PIPE_GERMANY = Integer.valueOf(Configuration.getInstance().getConfigValue("country.germany", "1"));
	/**
	 * 日本
	 */
	public static final Integer PIPE_JAPAN = Integer.valueOf(Configuration.getInstance().getConfigValue("country.japan", "2"));
	/**
	 * 美国
	 */
	public static final Integer PIPE_USA = Integer.valueOf(Configuration.getInstance().getConfigValue("country.usa", "6"));
	/**
	 * 韩国
	 */
	public static final Integer PIPE_KOREA = Integer.valueOf(Configuration.getInstance().getConfigValue("country.korea", "10"));
	/**
	 * 法国
	 */
	public static final Integer PIPE_FRANCE = Integer.valueOf(Configuration.getInstance().getConfigValue("country.france", "17"));
	/**
	 * 英国
	 */
	public static final Integer PIPE_BRITISH = Integer.valueOf(Configuration.getInstance().getConfigValue("country.british", "18"));
	/**
	 * 意大利
	 */
	public static final Integer PIPE_ITALY = Integer.valueOf(Configuration.getInstance().getConfigValue("country.italy", "19"));
	/**
	 * 瑞典
	 */
	public static final Integer PIPE_SWEDEN = Integer.valueOf(Configuration.getInstance().getConfigValue("country.sweden", "20"));
	/**
	 * 捷克
	 */
	public static final Integer PIPE_CZECH = Integer.valueOf(Configuration.getInstance().getConfigValue("country.czech", "21"));


	public static final Integer SQL_COUNT_LIMIT = 1000;

}
