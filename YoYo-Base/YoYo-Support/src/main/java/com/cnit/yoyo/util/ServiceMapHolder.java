/**
 * 文 件 名   :  ServiceMapHolder.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-5 下午1:43:33
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

/**
 * @description 服务码与服务提供者的映射关系封装
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class ServiceMapHolder {
    private static final Log log = LogFactory.getLog(ServiceMapHolder.class);
    private static Properties properties;
    private static Resource configLocation;

    public Resource getConfigLocation() {
        return configLocation;
    }

    /**
     * @description 读取注入的配置文件并封装到Properties
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-2
     * @param configLocation
     */
    public void setConfigLocation(Resource configLocation) {
        ServiceMapHolder.configLocation = configLocation;
        if (ServiceMapHolder.configLocation != null) {
            ServiceMapHolder.properties = new Properties();
            try {
                ServiceMapHolder.properties.load(configLocation.getInputStream());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * @description 读取指定服务码的服务提供者
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-5
     * @param serviceCode
     * @return
     */
    public static String getServiceProvider(String serviceCode) {
        if (properties != null)
            return (String) properties.get(serviceCode);
        return null;
    }
}
