package com.cnit.yoyo.model.quartz;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

/**   
 * 动态管理任务调度 
 * @author wanghb
 * @date 2015年8月28日
 * @Copyright 2015 cnit
 * @version V1.0.0 		
*/
public class ScheduleJob implements java.io.Serializable{  
	  
	private static final long serialVersionUID = 1534327224005394956L;
	
	public static final String STATUS_RUNNING = "1";  
    public static final String STATUS_NOT_RUNNING = "0";  
    public static final String CONCURRENT_IS = "1";  
    public static final String CONCURRENT_NOT = "0"; 
    
    private Long jobId; 
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime; 
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;  
    /** 
     * 任务名称 
     */  
    private String jobName;  
    /** 
     * 任务分组 
     */  
    private String jobGroup;  
    /** 
     * 任务状态 是否启动任务 
     */  
    private String jobStatus;  
    /** 
     * cron表达式 
     */  
    private String cronExpression;  
    /** 
     * 描述 
     */  
    private String description;  
    /** 
     * 任务执行时调用哪个类的方法 包名+类名 
     */  
    private String beanClass;  
    /** 
     * 任务是否有状态 
     */  
    private String isConcurrent;  
    /** 
     * spring bean 
     */  
    private String springId;  
    /** 
     * 任务调用的方法名 
     */  
    private String methodName;
    private String cmd;//执行命令stop、start
    
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public String getIsConcurrent() {
		return isConcurrent;
	}
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	public String getSpringId() {
		return springId;
	}
	public void setSpringId(String springId) {
		this.springId = springId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}  
  
} 

