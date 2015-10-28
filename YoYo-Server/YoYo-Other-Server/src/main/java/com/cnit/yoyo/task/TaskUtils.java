package com.cnit.yoyo.task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnit.yoyo.model.quartz.ScheduleJob;
import com.cnit.yoyo.util.ApplicationContextUtil;

/**
 * @Description:
 * @author wanghb
 * @date 2015年8月28日
 * @Copyright 2015 cnit
 * @version V1.0.0
 */
public class TaskUtils {
	private static final Log log = LogFactory.getLog(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJob scheduleJob) {
		Object provider = null;
		if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			provider = ApplicationContextUtil.getBeanByName(scheduleJob.getBeanClass());
		}
		if (provider == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		Method method = null;
		try {
			method = provider.getClass().getMethod(scheduleJob.getMethodName(), Object.class);
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]未启动成功，方法名设置错误，方法必须带一个参数！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(provider,Object.class);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
