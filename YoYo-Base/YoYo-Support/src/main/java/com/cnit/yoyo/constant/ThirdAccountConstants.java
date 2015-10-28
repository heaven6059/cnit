package com.cnit.yoyo.constant;

/**
 *@Description:  第三方账户常量
 *@Author:yangyi
 *@Date:2015年6月6日  
 *@Version:1.0.0
 */
public interface ThirdAccountConstants {
	/**
	 * 成功注册到用户中心
	 */
	public static final String ACCOUNT_REGCENTER_0="0";
	/**
	 * 注册到用户中心失败
	 */
	public static final String ACCOUNT_REGCENTER_1="1";
	/**
	 * 停用
	 */
	public static final String ACCOUNT_STATUS_0="0";
	/**
	 * 正常
	 */
	public static final String ACCOUNT_STATUS_1="1";
	/**
	 * web前端
	 */
	public static final String ACCOUNT_SOURCE_10="10";
	/**
	 * web管理平台
	 */
	public static final String ACCOUNT_SOURCE_11="11";
	/**
	 * appAndroid
	 */
	public static final String ACCOUNT_SOURCE_20="20";
	/**
	 * appIOS
	 */
	public static final String ACCOUNT_SOURCE_21="21";

	/**
	 *@Description: 第三方帐号平台类型
	 *@Author:yangyi  
	 */
	interface ACCOUNT_TYPE {

		/**
		 * QQ
		 */
		String QQ = "QQ";
		/**
		 * 微信
		 */
		String WX = "WX";
		/**
		 * 新浪微博
		 */
		String SN = "SN";
	}
}
