package com.cnit.yoyo.spider.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 系统日志处理类，aop拦截  
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Aspect
@Component("SystemLogAspect")
public class SystemLogAspect {
	private Logger logger = null;

	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");


	@Pointcut(value = "execution(* com.cnit.yoyo.spider..service.*.*(..))")
	public void pointCut() {
	}

	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		long beginTime = System.currentTimeMillis();// 1、开始时间
		startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
		Object target = joinPoint.getTarget();
		String methodName = joinPoint.getSignature().getName();
		logger = LoggerFactory.getLogger(target.getClass());
		logger.info("\r\n");
		logger.info(methodName + " Start,Params:"
				+ JSONObject.toJSONString(joinPoint.getArgs()));
	}

	@AfterReturning(value = "pointCut()", returning = "retval")
	public void afterReturning(JoinPoint joinPoint, Object retval) {
		long endTime = System.currentTimeMillis();// 2、结束时间
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;// 3、消耗的时间

		Object target = joinPoint.getTarget();
		String methodName = joinPoint.getSignature().getName();
		
		if (null == logger) {
			logger = LoggerFactory.getLogger(target.getClass());
		}
		logger.info(methodName+ " End  ,Retval:" + JSONObject.toJSONString(retval));

		if (consumeTime > 3000) {// 此处认为处理时间超过3000毫秒的请求为慢请求
			logger.info(String.format("%s consume %d millis", methodName, consumeTime));
		}
	}

	@AfterThrowing(value = "pointCut()", throwing = "ex")
	public void afterThrowing(Exception ex) {
		logger.error("\tafterThrowing ， Error Message:", ex);
	}

}
