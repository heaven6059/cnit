/**
 * 
 */
package com.cnit.yoyo.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Description: 系统参数配置工具类
 * @author  wanghb
 * @version V1.0 
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class Configuration {
	private static final Log logger = LogFactory.getLog(Configuration.class);
	
	private static Configuration _instance = null;
	
	private static PropertiesConfiguration propConfig = null;
	
	/**监听时间,半个小时加载一次*/
    private static long refreshDelay = 5*1000;
//    private static long refreshDelay = 1*30*60*1000;
    
    private Configuration(String configPath){
        logger.info("init...");
        try {
            propConfig = new PropertiesConfiguration(configPath);
            FileChangedReloadingStrategy reloadStrategy = new FileChangedReloadingStrategy();
            reloadStrategy.setRefreshDelay(refreshDelay);
            propConfig.setReloadingStrategy(reloadStrategy);
        } catch (ConfigurationException e) {
            logger.error("系统配置文件不存在!",e);
        }
    }
	
	public static Configuration getInstance(String configPath){
		if(_instance == null){
			_instance = new Configuration(configPath);
		}
		return _instance;
	}
	
	public static Configuration getInstance(){
		return _instance;
	}
	
	/**
	 * 获取系统配置参数 
	 * @param key
	 * @return
	 */
	public String getConfigValue(String key){
		return propConfig.getString(key);
	}
	/**
	 * 获取系统配置参数
	 * @param key
	 * @param defaultValue 如果没有找到参数值,则返回此默认值
	 * @return
	 */
	public String getConfigValue(String key,String defaultValue){
		return propConfig.getString(key,defaultValue);
	}
}
