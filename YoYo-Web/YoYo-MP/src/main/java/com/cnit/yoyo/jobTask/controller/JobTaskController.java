package com.cnit.yoyo.jobTask.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.model.quartz.ScheduleJob;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

@Controller
@RequestMapping("/task")
public class JobTaskController {
	public final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RemoteService otherService;

	@RequestMapping("/toTaskList")
	public Object toTaskList(HttpServletRequest request) {
		return "pages/biz/task/taskList";
	}
	
	@RequestMapping("/taskList")
	@ResponseBody
	public Object taskList(HttpServletRequest request) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "11150828-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try{
			return otherService.doService(new RequestObject(headObject,"TESt")).getContent();
		}catch (Exception e) {
			log.error("查询任务列表异常");
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
		}
	}

	@RequestMapping("/add")
	@ResponseBody
	public Object taskList(HttpServletRequest request, ScheduleJob scheduleJob) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "11150828-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try{
			return otherService.doService(new RequestObject(headObject, scheduleJob));
		}catch (Exception e) {
			log.error("保存任务失败");
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
		}
	}

	@RequestMapping("/changeJobStatus")
	@ResponseBody
	public Object changeJobStatus(HttpServletRequest request,ScheduleJob scheduleJob) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "11150828-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try{
			return otherService.doService(new RequestObject(headObject, scheduleJob));
		}catch (Exception e) {
			log.error("改变任务状态失败");
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
		}
	}

	@RequestMapping("/updateCron")
	@ResponseBody
	public Object updateCron(HttpServletRequest request,ScheduleJob scheduleJob) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "11150828-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try{
			return otherService.doService(new RequestObject(headObject, scheduleJob)).getHead();
		}catch (Exception e) {
			log.error("更改任务时间有误!	");
			headObject.setRetCode(ErrorCode.FAILURE);
			headObject.setRetMsg("更改任务时间有误!	");
			return headObject;
		}
	}
	@RequestMapping("/runAJobNow")
	@ResponseBody
	public Object runAJobNow(HttpServletRequest request, Long jobId) {
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobId(jobId);
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "11150828-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try{
			return otherService.doService(new RequestObject(headObject, scheduleJob));
		}catch (Exception e) {
			log.error("立即执行任务异常!	");
			headObject.setRetCode(ErrorCode.FAILURE);
			headObject.setRetMsg("立即执行任务异常!	");
			return headObject;
		}
	}
	@RequestMapping("/resumeJob")
	@ResponseBody
	public Object resumeJob(HttpServletRequest request,ScheduleJob scheduleJob) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "11150828-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try{
			scheduleJob.setCmd("start");
			return otherService.doService(new RequestObject(headObject, scheduleJob));
		}catch (Exception e) {
			log.error("恢复行任务异常!	");
			headObject.setRetCode(ErrorCode.FAILURE);
			headObject.setRetMsg("恢复执行任务异常!	");
			return headObject;
		}
	}
	@RequestMapping("/pauseJob")
	@ResponseBody
	public Object pauseJob(HttpServletRequest request,ScheduleJob scheduleJob) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "11150828-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try{
			scheduleJob.setCmd("stop");
			return otherService.doService(new RequestObject(headObject, scheduleJob));
		}catch (Exception e) {
			log.error("暂停执行任务异常!	");
			headObject.setRetCode(ErrorCode.FAILURE);
			headObject.setRetMsg("暂停执行任务异常!	");
			return headObject;
		}
	}
	@RequestMapping("/getTaskById")
	@ResponseBody
	public Object getTaskById(HttpServletRequest request, Long jobId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "11150828-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try{
			return otherService.doService(new RequestObject(headObject, jobId)).getContent();
		}catch (Exception e) {
			log.error("获取任务异常!	");
			headObject.setRetCode(ErrorCode.FAILURE);
			headObject.setRetMsg("获取任务异常!	");
			return headObject;
		}
	}
}
