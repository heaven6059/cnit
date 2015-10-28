/**
 * 文 件 名   :  GlobalStatic.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-23 上午10:17:31
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.constant;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public final class GlobalStatic {
	
	/**
	 * 判断用户中心环境（开发环境不去用户中心校验）
	 */
	public final static String MAIN_ENVIRONMENT="MAIN_ENVIRONMENT";
	/**
	 * 是否允许多地点重复登录
	 */
	public final static String LOGIN_ALLOWMULTIPLES="LOGIN.ALLOWMULTIPLES";
    /**
     * 集团类型
     */
    public final static String SHOP_TYPE_COMPANY = "1";

    /**
     * 单店类型
     */
    public final static String SHOP_TYPE_UNIT = "0";

    /**
     * 用户信息
     */
    public static final String CURRENT_USER = "user";

    /**
     * 状态码类型：本系统
     */
    public final static String RET_TYPE_THIS = "THIS";
    /**
     * 状态码类型：外部系统
     */
    public final static String RET_TYPE_OTHER = "OTHER";
    /**
     * 存放userId的session的key
     */
    public final static String USER_SESSION_ID = "userId";

    /**
     * 存入loginUser用户信息的Session中的Key
     */
    public final static String USER_SESSION_OBJECT = "loginUser";

    /**
     * 存入loginUserPrivilege用户信息的Session中的Key入
     */
    public final static String USER_SESSION_PRIVILEGE = "loginUserPrivilege";

    /**
     * 系统默认语言
     */
    static String DEFAULTLANGUAGE = "zh_CN";

    /**
     * cookie保存时间 默认一天 60*60*24
     */
    static int COOKIETIME = 86400;

    /**
     * 验证码
     */
    public static final String VALIDATE_CODE = "VALIDATE_CODE";

    /**
     * 短信验证码
     */
    public static final String SMS_CODE = "SMS_CODE";
    /**
     * 短信发送成功后的主建
     */
    public static final String TASK_ID = "taskID";
    

    /**
     * 邮箱验证码
     */
    public static final String EMAIL_CODE = "EMAIL_CODE";

    /**
     * 查询条件：当前页数的Map中的Key
     */
    public static final String QUERY_CONDITION_PAGE_INDEX = "pageIndex";

    /**
     * 查询条件：每页记录条数的Map中的Key
     */
    public static final String QUERY_CONDITION_PAGE_SIZE = "pageSize";

    /**
     * 查询条件：当前页的记录数的Map中的Key
     */
    public static final String QUERY_CONDITION_ROWS = "rows";

    /**
     * 第三方授权登录：QQ
     */
    public static final String THIRD_PART_QQ = "QQ";
    /**
     * 第三方授权登录：微信
     */
    public static final String THIRD_PART_WX = "WX";
    /**
     * 第三方授权登录：新浪
     */
    public static final String THIRD_PART_SN = "SN";
    /**
     * 帐户名类型：EMAIL
     */
    public static final String EMAIL = "1";
    /**
     * 帐户名类型：手机号码
     */
    public static final String MOBILE = "2";
    /**
     * 帐户名类型：字符串
     */
    public static final String USERNAME = "3";
    /**
     * 账户状态：无效
     */
    public static final String ACCOUNT_STATUS_OFF = "0";
    /**
     * 账户状态：有效
     */
    public static final String ACCOUNT_STATUS_ON = "1";

    /**
     * 帐号类型：前台买家账户
     */
    public static final String ACCOUNT_TYPE_BUYER = "100";

    /**
     * 帐号类型：前台卖家账户
     */
    public static final String ACCOUNT_TYPE_SELLER = "110";

    /**
     * 帐号类型：前台店员账户
     */
    public static final String ACCOUNT_TYPE_SELLER_HAND = "120";
    /**
     * 帐号类型：平台超级管理员
     */
    public static final String ACCOUNT_TYPE_SUPER = "200";
    /**
     * 帐号类型：平台管理员
     */
    public static final String ACCOUNT_TYPE_AMDIN = "210";
    /**
     * 帐号类型：平台管理员
     */
    public static final String ACCOUNT_TYPE_AMDIN_HAND = "220";
    /**
     * 会员状态：无效
     */
    public static final String MEMBER_STATUS_UNVALID = "0000";
    /**
     * 会员状态：有效
     */
    public static final String MEMBER_STATUS_VALID = "1000";
    /**
     * 会员状态：手机短息认证
     */
    public static final String MEMBER_STATUS_MOBILE = "1100";
    /**
     * 会员状态：手机邮箱认证
     */
    public static final String MEMBER_STATUS_EMAIL = "1110";
    /**
     * 会员状态：实名认证
     */
    public static final String MEMBER_STATUS_TRUE = "1001";
    /**
     * 会员等级类型：买家等级
     */
    public static final String MEMBER_LEVEL_TYPE_BUYER = "1";
    /**
     * 会员等级类型：卖家等级
     */
    public static final String MEMBER_LEVEL_TYPE_SELLER = "2";
    /**
     * 系统标志：前台web
     */
    public static final String SYSTEM_CODE_FRONT = "1000";
    /**
     * 系统标志：后台web
     */
    public static final String SYSTEM_CODE_BACK = "2000";
    /**
     * 系统标志：数据库服务
     */
    public static final String SYSTEM_CODE_DATA = "3000";
    /**
     * 系统标志：第三方服务
     */
    public static final String SYSTEM_CODE_THIRD = "4000";
    
    /**
     * 系统标志：ios手机服务
     */
    public static final String SYSTEM_CODE_IOS = "5000";
    
    /**
     * 系统标志：安卓手机服务
     */
    public static final String SYSTEM_CODE_ANDROID = "6000";

    /**
     * 默认列表显示数据数量
     */
    public static final int DEFAULT_PAGE_SIZE = 20;
    
    /**
     * 默认列表显示数据数量:10
     */
    public static final int DEFAULT_PAGE_SIZE_10 = 10;
    
    /**
     * 默认列表显示数据数量:5
     */
    public static final int DEFAULT_PAGE_SIZE_5 = 5;
    /**
     * 默认列表页码
     */
    public static final int DEFAULT_PAGE_INDEX = 1;

    /**
     * 通用开关标志：开
     */
    public static final String COMMON_SW_ON = "1";
    /**
     * 通用开关标志：关
     */
    public static final String COMMON_SW_OFF = "0";

    /**
     * 数据操作类型：新增
     */
    public static final String DATA_OP_INSERT = "INSERT";
    /**
     * 数据操作类型：修改
     */
    public static final String DATA_OP_MODIFY = "MODIFY";
    /**
     * 数据操作类型：删除
     */
    public static final String DATA_OP_DEL = "DEL";
    /**
     * 来源：PC网页端
     */
    public static final String CHANNEL_WEB = "10";
    /**
     * 来源：PC管理端
     */
    public static final String CHANNEL_MP = "11";
    /**
     * 来源：Android客户端
     */
    public static final String CHANNEL_AND = "20";
    /**
     * 来源：ios客户端
     */
    public static final String CHANNEL_IOS = "21";

    /**
     * 默认值，启用
     */
    public static final String DISABLED_DEFAULT_ON = "0";

    /**
     * 默认值，禁用或删除
     */
    public static final String DISABLED_DEFAULT_OFF = "1";

    /**
     * 新品牌使用
     */
    public static final String NEW_BRAND_USER = "0";

    /**
     * 旧品牌使用
     */
    public static final String OLD_BRAND_USER = "1";
    
    /**
     * 首页布局，北部
     */
    public static final String LAYOUT_NORTH = "north";
    
    /**
     * 首页布局，西部
     */
    public static final String LAYOUT_WEST = "west";
    
    /**
     * 'goods'：商品,'article'：文章
     */
    public static final String KEYWORDS_RESTYPE_GOODS = "goods";
    public static final String KEYWORDS_RESTYPE_ARTICLE = "article";

    /* 数据类型：字符 */
    public static final String DATA_TYPE_STR = "STR";
    /* 数据类型：整数 */
    public static final String DATA_TYPE_INT = "INT";
    /* 数据类型：浮点 */
    public static final String DATA_TYPE_DEC = "DEC";
    /* 数据类型：布尔 */
    public static final String DATA_TYPE_BOL = "BOL";

    public static final String SORT_FIELD = "sort";
    public static final String SORT_TYPE = "order";
    public static final String SORT_FIELD_DEFAULT = "orderNum";
    public static final String SORT_TYPE_DEFAULT = "desc";
    
    /**当前登录的用户信息**/
    public static final String USER_INFO = "CURRENT_USERINFO";
    
    /**
     * 判断启用JMagick还是GraphicsMagick 
     */
    public static final String JORGMAGICK="magick";
    /**
     * 是否启用图片服务器0为不启用1为启用
     */
    public static final String  IMAGESERVICE="imageService";
    
    /**
     * 是否启用图片服务器的路径
     */
    public static final String  IMAGESERVICEURL="imageServiceUrl";
    
    //图片路径
    public static final String  IMAGES_URL="images_url";
    
    /**
     * 图片所属模块
     */
    public static final String  IMAGES_PATH_PRODUCT="product";//产品图片
    public static final String  IMAGES_PATH_MEM="member";//会员图片
    public static final String  IMAGES_PATH_OTHER="other";//其它图片
    public static final String  IMAGES_PATH_MP="mp";//平台上传的图片
    public static final String  IMAGES_PATH_SHOP="shop";//卖家上传的图片
    
    
    /***TODO 最顶层分类的标志*/
    public static final String  CATALOG_CAR="999";   //整车
    public static final String  CATALOG_ACCESSORY="888"; //配件
    public static final String  CATALOG_MAINTAIN="777";  //保养
    public static final String  CATALOG_BOUTIQUE="666";  //精品
    public static final String  CATALOG_OTHER="555";     //其他
    
    /**角色名称*/
    public static final String  ROLE_SHOP_COMPANY="company";     //集团店铺管理员
    public static final String  ROLE_SHOP_STORE="store";     //单店店铺管理员
    
    /**店主标志*/
    public static final String  SHOP_TYPE="1";    
    
    /**店铺通过审核的标志*/
    public static final String  SHOP_CHECK_PASS="1";
    /**店铺商品是否需要审核*/
    public static final String  SHOP_CHECK_IN_GOODS="1";   //需要审核
    public static final String  SHOP_CHECK_OUT_GOODS="0";   //不需要审核
    
}
