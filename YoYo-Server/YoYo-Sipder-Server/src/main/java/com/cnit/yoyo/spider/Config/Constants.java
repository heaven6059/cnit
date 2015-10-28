package com.cnit.yoyo.spider.Config;

/**
 * @author wanghb
 * @version V1.0
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class Constants {
	/**
	 * 发动机对象
	 */
	public static final String ENGINE_DEFAULT = "AutoHome_Baoyang_Engine";
	/**
	 * 常量
	 */
	private static final String PARAM_LEFT = "[";
	/**
	 * 常量
	 */
	private static final String PARAM_RIGHT = "]";
	/**
	 * 常量
	 */
	private static final String PARAM_COLON = "'";

	/**
	 * 动态获取发动机属性值
	 * 
	 * @Description:
	 * @param val
	 * @return
	 */
	public static String getStringName(String val) {
		StringBuffer bf = new StringBuffer();
		bf.append(ENGINE_DEFAULT).append(PARAM_LEFT).append(PARAM_COLON)
				.append(val).append(PARAM_RIGHT).append(PARAM_COLON);
		return bf.toString();
	}

}
