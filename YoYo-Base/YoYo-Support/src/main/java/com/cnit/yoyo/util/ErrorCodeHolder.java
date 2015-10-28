/**
 * 文 件 名   :  ErrorCodeHolder.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-5 下午4:16:12
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

import com.cnit.yoyo.constant.ErrorCode;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class ErrorCodeHolder {
    private static final Log log = LogFactory.getLog(ErrorCodeHolder.class);
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
        ErrorCodeHolder.configLocation = configLocation;
        if (ErrorCodeHolder.configLocation != null) {
            ErrorCodeHolder.properties = new Properties();
            try {
                ErrorCodeHolder.properties.load(configLocation.getInputStream());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
    
    /**
     *@description 根据异常名称获得错误码
     *@detail <方法详细描述>
     *@author <a href="liming@cnit.com">李明</a>
     *@version 1.0.0
     *@data 2015-2-5
     *@param ex
     *@return
     */
    public static String getErrorCode(String errorType){
        if(ErrorCodeHolder.properties != null){
            return properties.getProperty(errorType);
        }else{
            return ErrorCode.UNKOWN;
        }
    }
}
