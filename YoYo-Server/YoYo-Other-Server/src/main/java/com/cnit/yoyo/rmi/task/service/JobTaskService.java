package com.cnit.yoyo.rmi.task.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.task.ScheduleJobMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.quartz.ScheduleJob;
import com.cnit.yoyo.task.QuartzJobFactory;
import com.cnit.yoyo.task.QuartzJobFactoryDisallowConcurrentExecution;
import com.cnit.yoyo.util.ApplicationContextUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @Description: 计划任务管理
 * @author wanghb
 * @date 2015年8月28日
 */
@Service("jobTaskService")
public class JobTaskService {
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private ScheduleJobMapper scheduleJobMapper;
	
	/**
	 * 从数据库中取区别于getAllJob
	 * 
	 * @return
	 */
	public Object getAllTask(Object data) {
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		ResultPage<ScheduleJob> result = null;
		try {
			PageHelper.startPage(0, 20);
			result = new ResultPage<ScheduleJob>(scheduleJobMapper.getAll());
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("从数据库中取出任务异常！");
		}
		return new ResultObject(head, JSON.toJSON(result));
	}

	/**
	 * 添加到数据库中 区别于addJob
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public Object addTask(ScheduleJob scheduleJob) {
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		try {
			try {
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
			} catch (Exception e) {
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("时间表达式有误，无法解析！");
				return new ResultObject(head);
			}
			Object obj = null;
			if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
//				obj = com.cnit.yoyo.task.SpringUtils.getBean(scheduleJob.getSpringId());
			} else {
				obj = ApplicationContextUtil.getBeanByName(scheduleJob.getBeanClass());
//				Class clazz = Class.forName(scheduleJob.getBeanClass());
//				obj = clazz.newInstance();
			}
			if (obj == null) {
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("未找到目标类！");
				return new ResultObject(head);
			} else {
				Class clazz = obj.getClass();
				Method method = null;
				try {
					method = clazz.getMethod(scheduleJob.getMethodName(), Object.class);
				} catch (Exception e) {
					head.setRetCode(ErrorCode.FAILURE);
					head.setRetMsg("未找到目标方法！");
					return new ResultObject(head);
				}
				if (method == null) {
					head.setRetCode(ErrorCode.FAILURE);
					head.setRetMsg("未找到目标方法！");
					return new ResultObject(head);
				}
			}
			scheduleJob.setCreateTime(new Date());
			scheduleJobMapper.insertSelective(scheduleJob);
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("从数据库中取出任务异常！");
		}
		return new ResultObject(head);
	}

	/**
	 * 从数据库中查询job
	 */
	public Object getTaskById(Object jobId) {
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		ScheduleJob result = null;
		try {
			result = scheduleJobMapper.selectByPrimaryKey((Long) jobId);
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("从数据库中取出任务异常！");
		}
		return new ResultObject(head, JSON.toJSON(result));
	}

	/**
	 * 更改任务状态
	 * 
	 * @throws SchedulerException
	 */
	@SuppressWarnings("unused")
	public Object changeStatus(Object data) throws SchedulerException {
		ScheduleJob temp = (ScheduleJob) data;
		ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(temp.getJobId());
		job.setUpdateTime(new Date());
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		ScheduleJob result = null;
		try {
			if (job == null) {
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("从数据库中取出任务异常！");
				return new ResultObject(head);
			}
			if ("stop".equals(temp.getCmd())) {
				deleteJob(job);
				job.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
				scheduleJobMapper.updateByPrimaryKeySelective(job);
			} else if ("start".equals(temp.getCmd())) {
				job.setJobStatus(ScheduleJob.STATUS_RUNNING);
				addJob(job);
				scheduleJobMapper.updateByPrimaryKeySelective(job);
			}else if ("delte".equals(temp.getCmd())) {
				deleteJob(job);
				scheduleJobMapper.deleteByPrimaryKey(job.getJobId());
			}
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("从数据库中取出任务异常！");
		}
		return new ResultObject(head, JSON.toJSON(result));
	}

	/**
	 * 更改任务 cron表达式
	 * 
	 * @throws SchedulerException
	 */
	@SuppressWarnings("unused")
	public Object updateCron(Object data) throws SchedulerException {
		List<ScheduleJob> list=this.getRunningJob();
		for(ScheduleJob job:list){
			System.out.println("----------"+job.getBeanClass());
		}
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		ScheduleJob temp = (ScheduleJob) data;
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(temp.getCronExpression());
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("时间表达式有误，无法解析！");
			return new ResultObject(head);
		}
		if(null!=temp.getJobId() && temp.getJobId()>0){
			log.debug("修改");
			ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(temp.getJobId());
			try {
				if (job == null) {
					head.setRetCode(ErrorCode.FAILURE);
					head.setRetMsg("从数据库中取出任务异常！");
					return new ResultObject(head);
				}
				job.setCronExpression(temp.getCronExpression());
				if (ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
					updateJobCron(job);
				}
				job.setUpdateTime(new Date());
				scheduleJobMapper.updateByPrimaryKeySelective(job);
			} catch (Exception e) {
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("更改任务cron表达式更新异常！");
			}
		}else{//新增
			log.debug("新增");
			ScheduleJob dobuleJob=scheduleJobMapper.selectByBeanClass(temp);
			if(null!=dobuleJob){
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("不能添加重复的任务！");
				return new ResultObject(head);
			}else{
				temp.setCreateTime(new Date());
				scheduleJobMapper.insertSelective(temp);
				if(temp.getJobStatus().equals("1")){
					addJob(temp);
				}
			}
		}
		return new ResultObject(head);
	}

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addJob(ScheduleJob job) throws SchedulerException {
		if (job == null || ScheduleJob.STATUS_NOT_RUNNING.equals(job.getJobStatus())) {//如果设置为不运行则不初始化
			return;
		}
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		log.debug(scheduler + "........................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	@SuppressWarnings("unused")
	@PostConstruct
	public void init() throws Exception {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		// 这里获取任务信息数据
		List<ScheduleJob> jobList = scheduleJobMapper.getAll();
		for (ScheduleJob job : jobList) {
			addJob(job);
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<ScheduleJob> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<ScheduleJob> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScheduleJob job = new ScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
			scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);
	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public Object runAJobNow(Object scheduleJob) throws SchedulerException {
		ScheduleJob temp = (ScheduleJob) scheduleJob;
		ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(temp.getJobId());
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		try {
//			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
//			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//			// 不存在，创建一个
//			if (null == trigger) {
//				job.setJobStatus("1");
//				this.addJob(job);
//				JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
//				scheduler.pauseJob(jobKey);
//				scheduler.triggerJob(jobKey);
//			}else{
				JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
				scheduler.triggerJob(jobKey);
//			}
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("立即执行job异常！" + e);
		}
		return head;
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

}
