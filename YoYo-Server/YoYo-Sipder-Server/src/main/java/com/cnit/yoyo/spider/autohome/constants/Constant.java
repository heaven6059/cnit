package com.cnit.yoyo.spider.autohome.constants;

/**
 * 全局常量
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public abstract class Constant {
	public static final String CARFACTORYURL="http://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx";
	/**
	 * 车品牌
	 */
	public static final String PIPE_CAR_BRAND = "PIPE_CAR_BRAND";
	/**
	 * 车厂商
	 */
	public static final String PIPE_CAR_FACTORY = "PIPE_CAR_FACTORY";
	/**
	 * 车厂商系别
	 */
	public static final String PIPE_CAR_FACTORY_SCOPE = "PIPE_CAR_FACTORY_SCOPE";
	/**
	 * 车系
	 */
	public static final String PIPE_CAR_SERIE = "PIPE_CAR_SERIE";
	/**
	 * 车型
	 */
	public static final String PIPE_CAR_TYPE = "PIPE_CAR_TYPE";
	/**
	 * 车属性
	 */
	public static final String PIPE_CAR_ATTR = "PIPE_CAR_ATTR";
	/**
	 * 车颜色-车型关联表
	 */
	public static final String PIPE_CAR_COLOR = "PIPE_CAR_COLOR";
	/**
	 * 车颜色
	 */
	public static final String PIPE_COLOR_INFO = "PIPE_COLOR_INFO";
	/**
	 * 保养属性详情
	 */
	public static final String PIPE_CAR_BAOYANG_ATTR = "PIPE_CAR_BAOYANG_ATTR";
	/**
	 * 保养信息基础
	 */
	public static final String PIPE_CAR_BAOYANG_INFO = "PIPE_CAR_BAOYANG_INFO";

	/**
	 * 车品牌id前缀
	 */
	public static final String CARBRAND_ID_PREFIX = "CB";

	/**
	 * 车厂商id前缀
	 */
	public static final String CARFACTORY_ID_PERFIX = "CF";
	public static final String CARFACTORY_ID_PERFIX_V = "CFV";
	/**
	 * 车系id前缀
	 */
	public static final String CARSERIE_ID_PREFIX = "CS";
	/**
	 * 车型id前缀
	 */
	public static final String CARTYPE_ID_PREFIX = "CT";

	public static final Integer SQL_COUNT_LIMIT = 1000;

}
