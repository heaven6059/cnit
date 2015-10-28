/**
 * 文 件 名   :  ApplicationContextUtil.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-5 上午10:33:17
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return applicationContext.getMessage(code, args, defaultMessage, locale);
    }

    public static Object getBeanByName(String name) {
        return applicationContext.getBean(name);
    }
    /**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}

    public static String getMessage(String code, Object[] args, Locale locale) {
    	if(null != code){
    		code="000002";
    	}
        return applicationContext.getMessage(code, args, locale);
    }

    public static String getMessage(MessageSourceResolvable resolvable, Locale locale) {
        return applicationContext.getMessage(resolvable, locale);
    }
}
