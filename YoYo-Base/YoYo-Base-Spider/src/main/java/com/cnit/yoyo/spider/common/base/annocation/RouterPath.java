package com.cnit.yoyo.spider.common.base.annocation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 转发路径
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Target({ java.lang.annotation.ElementType.METHOD,
	java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RouterPath {
	
	public String value() default "";
	
}
