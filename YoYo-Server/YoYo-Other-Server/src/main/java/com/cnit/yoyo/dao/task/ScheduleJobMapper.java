package com.cnit.yoyo.dao.task;

import java.util.List;

import com.cnit.yoyo.model.quartz.ScheduleJob;

public interface ScheduleJobMapper {
	int deleteByPrimaryKey(Long jobId);

	int insert(ScheduleJob record);

	int insertSelective(ScheduleJob record);

	ScheduleJob selectByPrimaryKey(Long jobId);

	int updateByPrimaryKeySelective(ScheduleJob record);

	int updateByPrimaryKey(ScheduleJob record);

	List<ScheduleJob> getAll();
	
	ScheduleJob selectByBeanClass(ScheduleJob record);
}	