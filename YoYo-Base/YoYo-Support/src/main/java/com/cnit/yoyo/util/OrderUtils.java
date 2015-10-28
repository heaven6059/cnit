package com.cnit.yoyo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

public class OrderUtils {
	public static Properties properties = new Properties();
    public static Resource configLocations;
	private static final Log log = LogFactory.getLog(OrderUtils.class);
	static{
		try {
			InputStream inputStream = OrderUtils.class.getClassLoader().getResourceAsStream("orderProcess.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream)); 
			properties.load(bf);
		} catch (IOException e) {
			log.error("加载订单退货状态文件失败");
			e.printStackTrace();
		}
	}
	
	public static String getPropertyValue(String property)throws Exception{
		try {
			return properties.getProperty(property).trim();
		} catch (Exception e) {
			log.error("读取订单退货状态信息失败",e);
			throw new Exception("读取订单退货状态信息失败", e); 
		}
	}
}
