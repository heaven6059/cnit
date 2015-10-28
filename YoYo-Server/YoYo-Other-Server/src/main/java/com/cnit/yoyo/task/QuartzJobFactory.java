package com.cnit.yoyo.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cnit.yoyo.model.quartz.ScheduleJob;

/**
 * 计划任务执行处 无状态 :
 * 
 * @author wanghb
 * @date 2015年8月28日
 * @Copyright 2015 cnit
 * @version V1.0.0
 */
public class QuartzJobFactory implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}
