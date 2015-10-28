package com.cnit.yoyo.base.xml;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

/**
 * 获取所有需要登录的方法
 * @Author:yangyi  
 * @Date:2015年7月23日  
 * @Version:1.0.0
 */
public class AppLoginsHolder {
    private static final Log log = LogFactory.getLog(AppLoginsHolder.class);
    private static Applogins applogins;
    private static Resource configLocation;

    public Resource getConfigLocation() {
        return configLocation;
    }
    
    public static Applogins getApplogins() {
		return applogins;
	}

	public static void setApplogins(Applogins applogins) {
		AppLoginsHolder.applogins = applogins;
	}

	public Applogins getApploginsMethod() {
        return applogins;
    }

   /**
    * @Description:读取注入的配置文件并封装到Applogins
    * @param configLocation
    */
    public void setConfigLocation(Resource configLocation) {
    	AppLoginsHolder.configLocation = configLocation;
        if (AppLoginsHolder.configLocation != null) {
            try {
            	applogins=new Applogins().getResponse(configLocation.getFile().getPath());
//            	AppServiceMapHolder.properties.load(configLocation.getFile());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

   /**
    * @Description:读取指定方法是否需要登录
    * @param method
    * @return
    */
    public static boolean getNeedLoginMethod(String method) {
        if (applogins != null){
        	return applogins.getMethod().contains(method);
        }
        return false;
    }
}
