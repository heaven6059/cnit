package com.cnit.yoyo.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.cnit.yoyo.model.quartz.ScheduleJob;

/**
 * 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作 :
 * 
 * @author wanghb
 * @date 2015年8月28日
 * @Copyright 2015 cnit
 * @version V1.0.0
 */
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}

}
