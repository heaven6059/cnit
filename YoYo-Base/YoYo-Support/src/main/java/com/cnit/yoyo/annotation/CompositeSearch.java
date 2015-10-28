package com.cnit.yoyo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CompositeSearch {

	/*
	 * 有数据库同一个字段，如时间字段在数据库 里是一个，但是在查询时需要大于小于的情况，因此添加fieldName
	 * 如果searchBean的属性与数据库一一对应则不需要设置fieldName 如果是多个属性对应一个数据库字段需要设置fieldName
	 */
	public String fieldName() default "";

}
