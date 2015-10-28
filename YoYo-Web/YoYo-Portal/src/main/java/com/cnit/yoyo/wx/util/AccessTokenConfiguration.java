package com.cnit.yoyo.wx.util;


import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;

/**
 * 微信公众号配置
 * @Author:yangyi  
 * @Date:2015年8月19日  
 * @Version:1.0.0
 */
public class AccessTokenConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(AccessTokenConfiguration.class);
	private static AccessTokenConfiguration _instance = null;
	private static ApiConfig apiConfig = null;

	private AccessTokenConfiguration(String appid,String secret) {
		try {
			apiConfig = new ApiConfig(appid, secret);
		} catch (Exception e) {
			logger.error("系统初始化token失败!", e);
		}
	}

	public static AccessTokenConfiguration getInstance(String appid,String secret) {
		if (_instance == null) {
			_instance = new AccessTokenConfiguration(appid,secret);
		}
		return _instance;
	}

	public static AccessTokenConfiguration getInstance() {
		return _instance;
	}

	/**
	 * @Description:AccessToken
	 * @return
	 */
	public String getAccessToken() {
		return apiConfig.getAccessToken();
	}

	/**
	 * @Description:获取appid
	 * @return
	 */
	public String getAppid() {
		return apiConfig.getAppid();
	}

	/**
	 * @Description:获取secret
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getSecret() {
		return apiConfig.getSecret();
	}

}
