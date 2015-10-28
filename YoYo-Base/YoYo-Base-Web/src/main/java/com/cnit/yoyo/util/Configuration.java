package com.cnit.yoyo.util;


import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description:
 * @author wanghb
 * @date 2015-06-03
 * @version V1.0
 * @Company: cnit.
 * @Copyright Copyright (c) 2015
 */
public class Configuration {
	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
	private static Configuration _instance = null;

	private static PropertiesConfiguration propConfig = null;

	/** 监听时间,半个小时加载一次 */
	// private static long refreshDelay = 5*1000;
	private static long refreshDelay = 1 * 30 * 60 * 1000;

	private Configuration(String configPath) {
		try {
			propConfig = new PropertiesConfiguration(configPath);
			FileChangedReloadingStrategy reloadStrategy = new FileChangedReloadingStrategy();
			reloadStrategy.setRefreshDelay(refreshDelay);
			propConfig.setReloadingStrategy(reloadStrategy);
		} catch (ConfigurationException e) {
			logger.error("系统配置文件不存在!", e);
		}
	}

	public static Configuration getInstance(String configPath) {
		if (_instance == null) {
			_instance = new Configuration(configPath);
		}
		return _instance;
	}

	public static Configuration getInstance() {
		return _instance;
	}

	/**
	 * 获取系统配置参数
	 * 
	 * @param key
	 * @return
	 */
	public String getConfigValue(String key) {
		return propConfig.getString(key);
	}

	/**
	 * 获取系统配置参数
	 * 
	 * @param key
	 * @param defaultValue
	 *            如果没有找到参数值,则返回此默认值
	 * @return
	 */
	public String getConfigValue(String key, String defaultValue) {
		return propConfig.getString(key, defaultValue);
	}

	/**
	 * 例： colors.pie = #FF0000, #00FF00, #0000FF List colorList =
	 * config.getList("colors.pie"); 获取系统配置参数
	 * 
	 * @param key
	 * @return 返回
	 */
	@SuppressWarnings("unchecked")
	public List<String> getList(String key) {
		return propConfig.getList(key);
	}

}
