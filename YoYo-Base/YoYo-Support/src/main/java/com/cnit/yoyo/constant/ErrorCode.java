/**
 * 文 件 名   :  ErrorCode.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-27 上午10:03:59
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.constant;

/**
 * @description 错误码
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class ErrorCode {
    /** 未知错误 */
    public static final String UNKOWN = "UNKOWN";
    /** 处理成功 */
    public static final String SUCCESS = "000000";
    /** 处理失败 */
    public static final String FAILURE = "000002";
    /** 处理中 */
    public static final String DOING = "000010";
    /** 方法不存在 */
    public static final String NO_METHOD = "000011";
    /** 方法不存在 */
    public static final String SITIVE_WORDS = "000012";
    /** 字段校验异常 */
    public static final String VALIDATE_ELEMENT_ERROR = "000100";
    
    /** 用户中心校验失败 */
    public static final String USERCENTER_VERIFY_FAILURE = "PUD001";
    
    /** 用户中心校验其他错误 */
    public static final String USERCENTER_VERIFY_OTHER_FAILURE = "PUD010";
    
    /** 用户中心注册失败 */
    public static final String USERCENTER_REG_FAILURE = "PUD002";
    
    /** 用户中心注册其他错误 */
    public static final String USERCENTER_REG_OTHER_FAILURE = "PUD020";
    
    /** 处理失败:超过最大上传字节 */
    public static final String OVER_LIMIT_SIZE = "000003";
    
    /** 已经存在 */
    public static final String IS_EXIST = "000004";
    
    
    /** 单店类型不能添加分店 */
    public static final String IS_NOT_COMPANY = "000005";
    
    /** 没有会员信息 */
    public static final String IS_NOT_MEMBER = "MEM001";
    
    /** 分店个数超出限制 */
    public static final String IS_STORE_NUMS_OVER = "000006";
    
    /** 存在级联数据 */
    public static final String IS_EXIST_CASCADE = "000007";
    
    /** 申请存在的品牌 */
    public static final String IS_EXIST_MORE_APPLY = "000012";
    
    /** 存在货品 */
    public static final String IS_EXIST_PRODUCT = "000008";
    
    /**数据超出规定数**/
    public static final String THE_NUMBER_OVER="000009";
    		
    /** 无返回结果 */
    public static final String NO_DATA = "000001";
    /** 页面输入数据错误 */
    public static final String PDE0001 = "PDE001";// 必要字段输入空值
    public static final String PDE0002 = "PDE002";// 用户名/密码错误
    /** 系统内部程序错误 */
    public static final String SPE001 = "SPE001";// 无有效配置信息
    public static final String SPE002 = "SPE002";// 调用了未经初始化的对象或者是不存在的对象（空指针异常）
    public static final String SPE003 = "SPE003";// IO异常
    public static final String SPE004 = "SPE004";// 指定的类不存在
    public static final String SPE005 = "SPE005";//数学运算异常
    public static final String SPE006 = "SPE006";//数组下标越界
    public static final String SPE007 = "SPE007";//方法的参数错误
    public static final String SPE008 = "SPE008";//类型强制转换错误
    public static final String SPE009 = "SPE009";//违背安全原则异常
    public static final String SPE010 = "SPE010";//方法末找到异常
    /** 系统环境错误 */
    public static final String SEE001 = "SEE001";
    public static final String SEE002 = "SEE002";//Java虚拟机发生了内部错误
    
    public static final String NOT_LOGIN ="NL001";//未登录
    /**一般业务错误**/
    public static final String BUSINESS_ERROR="BE00001";
    
    /**达到商品最大发布数量*/
    public static final String IS_MAX_GOODS ="000010";
    /**店铺违规，限制发布商品*/
    public static final String IS_LIMIT_GOODS ="000011";
    /**大于库存值*/
    public static final String MORE_THAN_STORE = "000013";
    
    /**店铺限制发布商品*/
    public static final String IS_LIMIT_GOODS_MSG ="店铺违规禁止发布或编辑商品!";
    
    /**店铺超出有效期*/
    public static final String IS_ENBALED_MSG ="店铺超出有效期，请尽快与平台联系!";
}
