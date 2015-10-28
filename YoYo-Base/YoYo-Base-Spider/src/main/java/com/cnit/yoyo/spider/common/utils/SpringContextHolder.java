package com.cnit.yoyo.spider.common.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 以静态变量保存Spring ApplicationContext, 
 * 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component("SpringContextHolder")
public class SpringContextHolder implements ApplicationContextAware,
		DisposableBean {

	private static ApplicationContext	applicationContext	= null;

	private static final Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	/**
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		logger.info("注入ApplicationContext到SpringContextHolder:"
				+ applicationContext);

		if (SpringContextHolder.applicationContext != null) {
			logger.warn("SpringContextHolder中的ApplicationContext被覆盖,原有Context为:"
					+ SpringContextHolder.applicationContext);
		}

		SpringContextHolder.applicationContext = applicationContext; // NOSONAR
	}

	/**
	 * 实现DisposableBean接口,在Context关闭时清理静态变量.
	 */
	public void destroy() throws Exception {
		SpringContextHolder.cleanApplicationContext();

	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型. 如果有多个Bean符合Class, 取出第一个.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		checkApplicationContext();
		return applicationContext.getBean(requiredType);
	}
	
	public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) {
		checkApplicationContext();
		Map<String, T> beanMap = applicationContext.getBeansOfType(requiredType);
		return beanMap;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T getBeanAware(Class<?> requiredType) {
		checkApplicationContext();
		return (T) applicationContext.getBean(requiredType);
	}

	/**
	 * 清除applicationContext静态变量.
	 */
	public static void cleanApplicationContext() {
		logger.info("清除SpringContextHolder中的ApplicationContext:"
				+ applicationContext);
		applicationContext = null;
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException(
					"applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}
}
