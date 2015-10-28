/*==============================================================*/
/* 数据库脚本 begin                                     */
/*==============================================================*/

-- 添加店铺商品需要审核表 @date:2015-6-10 @create by:xiaox 
CREATE TABLE `t_company_check_category` (
`id`  int(11) NULL AUTO_INCREMENT ,
`company_id`  int(11) NULL ,
`cat_id`  int(11) NULL COMMENT '分类id' ,
PRIMARY KEY (`id`)
)
;
-- 添加是否需要审核商品字段 @date:2015-6-10 @create by:xiaox 
ALTER TABLE `t_company`
ADD COLUMN `isCheck` VARCHAR(1) NOT NULL DEFAULT '0' COMMENT '店铺商品是否需要审核：0：不需要 1：需要';

-- 爬虫基础数据表 @date:2015-6-01 @create by:wangp 
ALTER TABLE `t_aftersales_return_product`
DROP COLUMN `item_id`,
DROP COLUMN `nums`,
DROP COLUMN `product_id`,
ADD COLUMN `items_id`  int(20) NULL COMMENT '订单详情Id' AFTER `store_name`,
ADD COLUMN `nums`  int(3) NULL COMMENT '退货数量' AFTER `items_id`,
ADD COLUMN `product_id`  bigint(30) NULL COMMENT '商品Id' AFTER `nums`;

-- t_shop_focus_ad @date:2015-6-11 @create by:wangp 
drop table if exists t_shop_focus_ad;
create table t_shop_focus_ad
(
   id                   bigint not null auto_increment,
   focus_title          varchar(50) comment '焦点图标题',
   focus_type           enum("left","right","top","down","fadeout") default 'fadeout' comment '焦点图显示方式{left:左滚动,right:右滚动,top:上滚动,down:下滚动,fadeout:淡出}',
   focus_order          int comment '焦点图排序',
   focus_img            varchar(1000) not null comment '焦点图',
   focus_url            varchar(300),
   focus_enabled        enum("1","0") default '1' comment 'focus_enabled{1:是,0:否}',
   company_id           bigint not null comment '店铺ID',
   store_id             bigint comment '分店ID',
   last_modify          datetime,
   primary key (id)
);
alter table t_shop_focus_ad comment '店铺焦点图广告';

-- t_shop_car_coupon @date:2015-6-11 @create by:wangp 
drop table if exists t_shop_car_coupon;
create table t_shop_car_coupon
(
   id                   bigint not null auto_increment,
   company_id           bigint not null,
   store_id             bigint,
   selas_title          varchar(200) comment '推销标题',
   product_id           bigint not null comment '推销商品Id',
   hot_icon             int comment '人气角标',
   enabled              enum('1','0') comment '启用标志',
   last_modify          datetime comment '修改时间',
   primary key (id)
);
alter table t_shop_car_coupon comment '店铺首页新车优惠';

-- t_order_comment @date:2015-6-11 @create by:wangp 
drop table if exists t_order_comment;
create table t_order_comment
(
   comment_id           bigint not null auto_increment,
   for_comment_id       bigint default 0 comment '父级ID',
   order_id             bigint comment '订单ID',
   order_item_id        bigint comment '订单详情ID',
   product_id           bigint,
   title                varchar(100) comment '评论标题',
   comment              varchar(1000) comment '评论内容',
   comment_star         int comment '商品评分',
   member_id            varchar(1000) comment '会员ID',
   comments_type        enum('0','1','2','3') comment '0=>解释,评论类型:1 => 评论,2 => 回复,3 => 追加',
   display              enum('0','1'),
   create_time          date,
   primary key (comment_id)
);
alter table t_order_comment comment '订单评论表';

-- t_aftersales_return_product @date:2015-6-18 @create by:wangp 
DROP TABLE IF EXISTS `t_aftersales_return_product`;
CREATE TABLE `t_aftersales_return_product` (
  `return_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `return_bn` varchar(32) DEFAULT NULL COMMENT '退货记录流水号标识',
  `title` varchar(200) DEFAULT NULL,
  `old_return_id` bigint(20) DEFAULT NULL,
  `content` longtext COMMENT '退货内容',
  `status` int(11) DEFAULT '1' COMMENT '1 =>(退款协议等待卖家确认),2 =>(审核中),3 =>(同意退款),4 =>(完成),5 =>(拒绝),6 =>(已收货),7 =>(已质检),8 =>(补差价),9 =>(已拒绝退款),10 =>(已取消),11 =>(卖家不同意协议，等待买家修改),12 =>(买家已退货，等待卖家确认收货),13 =>(已修改),14 =>(卖家收到退货，拒绝退款),15 =>(卖家同意退款，等待卖家打款至平台),16 =>(卖家已退款，等待系统结算),',
  `image_file3` varchar(255) DEFAULT NULL,
  `image_file` varchar(255) DEFAULT NULL,
  `image_file2` varchar(255) DEFAULT NULL,
  `intereven_image` varchar(255) DEFAULT NULL,
  `intereven_comment` longtext,
  `product_data` longtext COMMENT '退货货品记录',
  `comment` longtext COMMENT '管理员备注',
  `add_time` datetime DEFAULT NULL COMMENT '售后处理时间',
  `seller_amount` decimal(20,0) DEFAULT NULL COMMENT '商家承担金额',
  `amount` decimal(20,0) DEFAULT NULL COMMENT '退款金额',
  `shipping_amount` decimal(20,0) DEFAULT NULL COMMENT '运费退款金额',
  `close_time` datetime DEFAULT NULL COMMENT '关闭时间',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `refund_type` int(11) DEFAULT '1' COMMENT ' 1 =>(取消订单),2 =>(需要退货),3 =>(已收到，无需退货),4 =>(未收到货),',
  `is_intervene` int(11) DEFAULT '1' COMMENT ' 1 =>(平台未介入),2 =>(等待卖家举证),3 =>(平台已介入),4 =>(平台已处理),',
  `intervene_reason` int(11) DEFAULT '1' COMMENT ' 1 =>(空包裹，少货),2 =>(快递问题),3 =>(卖家发错货),4 =>(虚假发货),5 =>(多拍，搓牌，不想要),6 =>(其他),',
  `intervene_phone` varchar(30) DEFAULT NULL COMMENT '平台介入手机',
  `intervene_mail` varchar(30) DEFAULT NULL COMMENT '平台介入邮箱',
  `ship_cost` decimal(20,0) DEFAULT NULL COMMENT '退邮金额',
  `amount_seller` decimal(20,0) DEFAULT NULL COMMENT '商品剩余金额',
  `seller_reason` longtext COMMENT '卖家拒绝原因',
  `seller_comment` longtext COMMENT '卖家留言',
  `is_safeguard` int(11) DEFAULT '1' COMMENT '1 =>(售前),2 =>(售后),',
  `safeguard_type` int(11) DEFAULT '1' COMMENT '1 =>(商品问题),2 =>(七天无理由退换货),3 =>(发票无效),4 =>(退回多付的运费),5 =>(未收到货),',
  `safeguard_require` int(11) DEFAULT '1' COMMENT ' 1 =>(不退货部分退款),2 =>(需要退货退款),3 =>(要求换货),4 =>(要求维修),5 =>(已经退货，要求退款),6 =>(要求退款),',
  `refund_address` varchar(255) DEFAULT NULL COMMENT '退货地址',
  `return_score` decimal(20,0) DEFAULT NULL COMMENT '退货积分',
  `image_upload` varchar(255) DEFAULT NULL COMMENT '付款证明',
  `is_return_money` int(11) DEFAULT '1' COMMENT ' 1 =>(未打款),2 =>(已打款),',
  `return_money_id` varchar(50) DEFAULT NULL COMMENT '流水单号',
  `disabled` int(11) DEFAULT '0' COMMENT 'true,false',
  `create_time` datetime DEFAULT NULL,
  `member_image_path` varchar(500) DEFAULT NULL COMMENT '买家上传照片路径',
  `member_name` varchar(40) DEFAULT NULL COMMENT '买家联系人姓名',
  `member_phone` varchar(50) DEFAULT NULL COMMENT '买家联系人手机',
  `store_name` varchar(300) DEFAULT NULL COMMENT '卖家名称',
  `items_id` int(20) DEFAULT NULL COMMENT '订单详情Id',
  `nums` int(3) DEFAULT NULL COMMENT '退货数量',
  `product_id` bigint(30) DEFAULT NULL COMMENT '商品Id',
  PRIMARY KEY (`return_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='售后申请表';

-- 添加注解 @date:2015-5-27 @create by:xiaox 
ALTER TABLE `t_company`
	CHANGE COLUMN `grade_id` `grade_id` BIGINT(20) NULL DEFAULT NULL COMMENT '店铺等级ID' AFTER `company_id`,
	CHANGE COLUMN `shop_name` `shop_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '公司账号名' AFTER `grade_id`,
	CHANGE COLUMN `account_id` `account_id` MEDIUMINT(8) NULL DEFAULT NULL COMMENT '公司账号ID' AFTER `shop_name`,
	CHANGE COLUMN `store_name` `store_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '店铺名称' AFTER `account_id`,
	CHANGE COLUMN `region_id` `region_id` BIGINT(20) NULL DEFAULT NULL COMMENT '经营范围' AFTER `store_grade`,
	CHANGE COLUMN `last_time` `last_time` VARCHAR(20) NULL DEFAULT NULL COMMENT '有效期' AFTER `region_id`,
	CHANGE COLUMN `earnest` `earnest` DOUBLE(20,2) NULL DEFAULT NULL COMMENT '保证金' AFTER `last_time`,
	CHANGE COLUMN `company_name` `company_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '企业名称' AFTER `earnest`,
	CHANGE COLUMN `company_no` `company_no` VARCHAR(50) NULL DEFAULT NULL COMMENT '营业执照号' AFTER `company_name`,
	CHANGE COLUMN `company_taxno` `company_taxno` VARCHAR(50) NULL DEFAULT NULL COMMENT '税务登记证号' AFTER `company_no`,
	CHANGE COLUMN `company_codename` `company_codename` VARCHAR(50) NULL DEFAULT NULL COMMENT '企业组织机构代码' AFTER `company_taxno`,
	CHANGE COLUMN `company_idname` `company_idname` VARCHAR(50) NULL DEFAULT NULL COMMENT '法定代表人名' AFTER `company_codename`,
	CHANGE COLUMN `company_cname` `company_cname` VARCHAR(50) NULL DEFAULT NULL COMMENT '公司负责人姓名' AFTER `company_idcard`,
	CHANGE COLUMN `company_charge` `company_charge` VARCHAR(50) NULL DEFAULT NULL COMMENT '负责人职位' AFTER `company_cidcard`,
	CHANGE COLUMN `company_ctel` `company_ctel` VARCHAR(50) NULL DEFAULT NULL COMMENT '企业联系电话' AFTER `company_charge`,
	CHANGE COLUMN `company_area` `company_area` VARCHAR(100) NULL DEFAULT NULL COMMENT '公司注册地区' AFTER `company_ctel`,
	CHANGE COLUMN `company_addr` `company_addr` VARCHAR(255) NULL DEFAULT NULL COMMENT '公司注册地址' AFTER `company_area`,
	CHANGE COLUMN `company_carea` `company_carea` VARCHAR(100) NULL DEFAUL;/* 大SQL查询 (5.5 KiB)，分割于 2,000 个字符 */
	
-- 修改时间类型 @date:2015-5-27 @create by:xiaox
ALTER TABLE `t_business_storemanger`
MODIFY COLUMN `last_modify`  datetime NULL DEFAULT NULL;
ALTER TABLE `t_company`
MODIFY COLUMN `last_time`  datetime NULL DEFAULT NULL COMMENT '有效期' ,
MODIFY COLUMN `company_time`  datetime NULL DEFAULT NULL COMMENT '公司成立时间' ,
MODIFY COLUMN `company_timestart`  datetime NULL DEFAULT NULL COMMENT '营业执照有效期' ,
MODIFY COLUMN `company_timeend`  datetime NULL DEFAULT NULL COMMENT '营业执照有效期结束' ,
MODIFY COLUMN `last_modify`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
MODIFY COLUMN `approve_time`  datetime NULL DEFAULT NULL COMMENT '审核时间' ,
MODIFY COLUMN `approved_time`  datetime NULL DEFAULT NULL COMMENT '审核通过时间',
MODIFY COLUMN `apply_time`  datetime NULL DEFAULT NULL COMMENT '申请时间';

-- 经验值记录表 @date:2015-6-1 @create by:xiaox
CREATE TABLE `t_company_experience` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`type`  varchar(1) NULL DEFAULT '0' COMMENT '经验值获得类型：0：管理员改变经验值，1：店铺评分获得经验值' ,
`experience`  int(11) NULL DEFAULT 0 COMMENT '经验值' ,
`order_id`  bigint NULL DEFAULT NULL COMMENT '订单id' ,
`remark`  varchar(500) NULL ,
`modify_time`  datetime NULL COMMENT '修改日期' ,
`operator`  int(11) NULL COMMENT '操作者id' ,
`company_id`  int(20) NULL COMMENT '店铺id',
PRIMARY KEY (`id`)
)
;
ALTER TABLE `t_company`
MODIFY COLUMN `experience`  int(10) NULL DEFAULT 0;

ALTER TABLE `t_business_earnest_log`
MODIFY COLUMN `expiretime`  int(10) NULL ;

-- 商品表使用扩展字段P_21 @date:2015-6-4 @create by:xiaox
ALTER TABLE `t_goods`
CHANGE COLUMN `P_21` `P_21` VARCHAR(1) NULL DEFAULT '0' COMMENT '是否开启规格；0：开启，1：未开启' ;
	
-- 添加区分店员与店主 @date:2015-6-8 @create by:xiaox
ALTER TABLE `t_business_storemember`
ADD COLUMN `type` VARCHAR(1) NOT NULL DEFAULT '0' COMMENT '类型：0：店员 1：店主';

-- 店铺角色 添加删除字段 @date:2015-6-9 @create by:xiaox
ALTER TABLE `t_business_storeroles`
	ADD COLUMN `disabled` VARCHAR(1) NOT NULL DEFAULT '0' COMMENT '0:可用 1：删除' ;
	
ALTER TABLE `t_site_roles`
CHANGE COLUMN `ROLE_NAME` `ROLE_NAME` VARCHAR(100) NULL DEFAULT NULL COMMENT '角色英文名' ,
CHANGE COLUMN `DESCRIPTION` `DESCRIPTION` VARCHAR(200) NULL DEFAULT NULL COMMENT '角色中文名称' ;

-- 加了个状态注解 @date:2015-6-11 @create by:xiaox
ALTER TABLE `t_goods`
CHANGE COLUMN `MARKETABLE` `MARKETABLE` VARCHAR(2) NULL DEFAULT '0' COMMENT '是否销售,0:未销售（下架，审核通过）,1：已销售（上架），-1：暂存本地，2：待审核 3：审核不通过';
	
ALTER TABLE `t_products`
CHANGE COLUMN `marketable` `marketable` VARCHAR(2) NOT NULL DEFAULT '0' COMMENT '是否销售,0:未销售（下架，审核通过）,1：已销售（上架），-1：暂存本地，2：待审核 3：审核不通过';
ALTER TABLE `t_goods`
CHANGE COLUMN `P_22` `P_22` VARCHAR(1000) NULL DEFAULT NULL COMMENT '审核不通过的原因' ;
	
ALTER TABLE `t_goods`
CHANGE COLUMN `P_23` `P_23` VARCHAR(255) NULL DEFAULT NULL COMMENT '审核通过或不通过时间';

-- 添加店铺地区id集合 @date:2015-6-16 @create by:xiaox
ALTER TABLE `t_company`
ADD COLUMN `company_area_ids` VARCHAR(50) NULL COMMENT '公司注册地区的id集合' ,
ADD COLUMN `company_carea_ids` VARCHAR(50) NULL COMMENT '公司联系地区的id集合' ;
	
ALTER TABLE `t_business_storemanger`
ADD COLUMN `area_ids` VARCHAR(50) NULL COMMENT '地区id集合' ;
	
ALTER TABLE `t_company`
CHANGE COLUMN `approved` `approved` VARCHAR(2) NULL DEFAULT '0' COMMENT ' \'-1\':提交了基本信息，还没提交资质文件图片信息 \'0\'=>\'待审核\',\'1\'=>\'审核通过\',\'2\'=>\'审核未通过\'' ;

-- 汽车之家基本信息表，存放车型车系车品牌数据 @date:2015-6-16 @create by:yangyi
drop table if exists t_autohome_car_info;
CREATE TABLE `t_autohome_car_info` (
  `ID` varchar(50) NOT NULL COMMENT '汽车之家ID, CB为车品牌，CF为厂商,CS为车系,CT为车型',
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `PID` varchar(50) DEFAULT NULL COMMENT '父级ID',
  `IMG_PATH` varchar(200) DEFAULT NULL COMMENT '汽车之家图片地址',
  `AUTOHOME_URL` varchar(200) DEFAULT NULL COMMENT '汽车之家URL',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 汽车之家颜色信息表，存放颜色数据 @date:2015-6-16 @create by:yangyi
drop table if exists t_autohome_color_info;
CREATE TABLE `t_autohome_color_info` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `VAL` varchar(50) DEFAULT NULL,
  `PIC_NUM` int(11) DEFAULT NULL,
  `CLUB_PIC_NUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 汽车之家颜色、车型关联关系表，存放颜色和车型的关联数据 @date:2015-6-16 @create by:yangyi
drop table if exists t_autohome_car_color;
CREATE TABLE `t_autohome_car_color` (
  `CAR_TYPE_ID` bigint(20) NOT NULL,
  `COLOR_ID` bigint(20) NOT NULL,
  `IS_INNER` int(11) DEFAULT NULL,
  PRIMARY KEY (`CAR_TYPE_ID`,`COLOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 汽车之家属性数据表，存放属性数据 @date:2015-6-16 @create by:yangyi
drop table if exists t_autohome_car_attr;
CREATE TABLE `t_autohome_car_attr` (
  `ID` varchar(50) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `VAL` varchar(100) DEFAULT NULL,
  `CAR_TYPE_ID` varchar(20) DEFAULT NULL,
  `PARENT_ORDER_INDEX` int(11) DEFAULT NULL,
  `CHILD_ORDER_INDEX` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 汽车之家保养数据基础表 @date:2015-6-16 @create by:yangyi
drop table if exists T_AUTOHOME_BAOYANG_INFO;
CREATE TABLE `t_autohome_baoyang_info` (
  `ID` varchar(50) NOT NULL COMMENT '汽车之家保养数据表ID',
  `CAR_TYPE_ID` varchar(50) NOT NULL COMMENT '车型id',
  `CAR_SERIES_ID` varchar(50) DEFAULT NULL COMMENT '车系id',
  `GEARBOX` varchar(50) DEFAULT NULL COMMENT '变速箱名称',
  `GEARBOX_ID` varchar(20) DEFAULT NULL COMMENT '变速箱id',
  `FIRST_UPKEEP` varchar(50) DEFAULT NULL COMMENT '首保（xx公里/xx个月）',
  `SECOND_UPKEEP` varchar(50) DEFAULT NULL COMMENT '二保（xx公里/xx个月）',
  `UPKEEP_INTERVAL` varchar(50) DEFAULT NULL COMMENT '间隔（xx公里/xx个月）',
  `UPKEEP_STATUS` varchar(10) DEFAULT NULL COMMENT '保养状态 0：更换 1：检查',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车之家保养数据基础表';

-- 汽车之家保养数据详情表 @date:2015-6-16 @create by:yangyi
drop table if exists T_AUTOHOME_BAOYANG_ATTR;
CREATE TABLE `t_autohome_baoyang_attr` (
  `ID` varchar(50) NOT NULL COMMENT '汽车之家保养数据表ID',
  `BAOYANG_ID` varchar(50) NOT NULL COMMENT '汽车之家保养数据表主建ID',
  `ITEM` varchar(100) NOT NULL COMMENT '保养项目名称,如：发动机机油',
  `GEARBOX` varchar(50) DEFAULT NULL COMMENT '变速箱名称',
  `MILEAGE` varchar(20) DEFAULT NULL COMMENT '里程数',
  `UPKEEP_COST` varchar(50) DEFAULT NULL COMMENT '价格',
  `UPKEEP_MONTH` varchar(20) DEFAULT NULL COMMENT '月份',
  `UPKEEP_STATUS` varchar(10) DEFAULT '0' COMMENT '保养状态 0：更换 1：检查',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车之家保养数据详情表';

-- 保养数据变速箱类型基础表 @date:2015-6-16 @create by:yangyi
drop table if exists T_AUTOHOME_ITEM_TYPE;
create table T_AUTOHOME_ITEM_TYPE
(
   ITEM_ID              int not null COMMENT '变速箱类型表id',
   ITEM                 varchar(50) not null COMMENT '变速箱类型名称',
   primary key (ITEM_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保养数据变速箱类型基础表';

-- 保养项目类型基础表 @date:2015-6-16 @create by:yangyi
drop table if exists T_AUTOHOME_GEARBOX_TYPE;
create table T_AUTOHOME_GEARBOX_TYPE
(
   GEARBOX_ID           varchar(10) not null COMMENT '保养类型主建id',
   GEARBOX              varchar(50) COMMENT '保养类型值',
   primary key (GEARBOX_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保养项目类型基础表';

-- 第三方账户信息表，qq、微信或者微博账号信息 @date:2015-6-16 @create by:yangyi
CREATE TABLE `t_third_account` (
  `ACCOUNT_ID` int(8) NOT NULL AUTO_INCREMENT COMMENT '第三方帐号表主建',
  `OPEN_ID` varchar(50) NOT NULL COMMENT '第三方帐号唯一标示,登录帐号',
  `ACCOUNT_NAME` varchar(16) NOT NULL COMMENT '第三方帐号名称',
  `ACCOUNT_TYPE` varchar(8) NOT NULL COMMENT '第三方帐号平台类型 QQ:qq平台 WX:微信平台 SN:新浪微博平台',
  `PAM_ACCOUNT_ID` int(8) NOT NULL COMMENT '优优帐号ID',
  `ACCOUNT_STATUS` varchar(3) NOT NULL DEFAULT '1' COMMENT '帐号状态 0：停用 1：启用',
  `REG_IP` varchar(16) DEFAULT NULL COMMENT '注册ip地址',
  `REG_TIME` varchar(16) DEFAULT NULL COMMENT '注册时间',
  `SOURCE` varchar(10) DEFAULT '10' COMMENT '10：web前端  11：web管理平台  20：appAndroid 21:appIOS',
  `LOGIN_COUNT` int(11) DEFAULT '0' COMMENT '登录次数',
  `LOGIN_IP` varchar(16) DEFAULT NULL COMMENT '登录ip地址',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ACCOUNT_ID`),
  KEY `OPEN_ID` (`OPEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方账户信息表';
alter table t_members add `REGUSERCENTER` varchar(8) DEFAULT '1' comment '是否成功通知用户中心 0：成功通知 1：未通知';
alter table t_members add `USERCENTERID` INT DEFAULT null comment '用户中心ID';

-- portal首页广告管理表  @date:2015-7-3 @create by:jpzhou
CREATE TABLE `t_site_ad` (
	`ad_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`ad_type` INT(2) NULL DEFAULT NULL COMMENT '广告类型（0轮播广告，1图片广告)',
	`ad_config` TEXT NULL COMMENT '广告配置,使用JSON格式',
	`begin_time` DATETIME NULL DEFAULT NULL COMMENT '开始时间',
	`end_time` DATETIME NULL DEFAULT NULL COMMENT '结束时间',
	`enabled` TINYINT(1) NULL DEFAULT '1' COMMENT '是否启用',
	`order_no` INT(11) NULL DEFAULT NULL COMMENT '排序',
	`sequence` INT(11) NULL DEFAULT NULL COMMENT '序号',
	`name` VARCHAR(50) NULL DEFAULT NULL COMMENT '名称',
	`description` VARCHAR(50) NULL DEFAULT NULL COMMENT '描述',
	PRIMARY KEY (`ad_id`),
	UNIQUE INDEX `sequence` (`sequence`),
	UNIQUE INDEX `name` (`name`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3;


-- 品牌表  @date:2015-7-6 @create by:xiaox
ALTER TABLE `t_brand`
	ADD COLUMN `brand_type` INT(1) NOT NULL DEFAULT '1' COMMENT '1:整车的品牌，0：除整个品牌外的品牌' ;

-- 新增平台钣金订单菜单及钣金订单增加字段、删除外键 @date:2015-07-08 @create by:caizhijie
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO `yoyo`.`t_desktop_resource` (`ID`, `RESOURCE_NAME`, `RESOURCE_TYPE`, `APP_ID`, `WORKGROUND`, `MENU_GROUP`, `MENU_TITLE`, `URL`, `DISABLED`, `DISPLAY`, `PERMISSION`, `ADDON`, `TARGET`, `MENU_ORDER`, `IDENTITY`, `PARENT_ID`, `PARENT_IDS`, `ICON`, `WEIGHT`) VALUES ('420', '钣金订单列表', 'menu', NULL, NULL, NULL, NULL, '/painting/orderList', '0', '0', NULL, NULL, '1', NULL, NULL, '37', '', NULL, NULL);

Truncate table t_painting_orders;
ALTER TABLE `t_painting_orders` DROP FOREIGN KEY t_painting_orders_ibfk_1
ALTER TABLE `t_painting_orders` MODIFY COLUMN `account_id`  int(8) NOT NULL AFTER `member_id`;

-- 删除广告管理表的order_no字段，description字段长度改为100
ALTER TABLE `t_site_ad`
CHANGE COLUMN `description` `description` VARCHAR(100) NULL DEFAULT NULL COMMENT '描述',
DROP COLUMN `order_no`;

-- 优惠券和图片之间的关联表 @create by ssd
CREATE TABLE `t_coupons_pic_ship` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`cpns_id`  mediumint(8) NULL DEFAULT NULL ,
`picture_id`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=796
ROW_FORMAT=COMPACT
;

--新增来源字段@create by caizhijie
ALTER TABLE `t_painting_orders`
ADD COLUMN `source`  varchar(10) NOT NULL DEFAULT 'web' COMMENT '\'web\',\'android\',\'ios\'' AFTER `company_id`;
ALTER TABLE `t_car_damage`
ADD COLUMN `source`  varchar(10) NOT NULL DEFAULT 'web' COMMENT '\'web\',\'android\',\'ios\'' AFTER `account_id`;

--优惠券添加一个存储优惠券默认图片的字段@create by ssd
alter table t_coupons add big_pic VARCHAR(128);

--除整车之外的商品的价格时间区间表@create by ssd
CREATE TABLE `t_goods_time_price` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`goods_id`  bigint(20) NULL DEFAULT NULL ,
`price`  decimal(9,5) NULL DEFAULT NULL ,
`price_start`  datetime NULL DEFAULT NULL ,
`price_end`  datetime NULL DEFAULT NULL ,
`temp`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3
ROW_FORMAT=COMPACT
;

--除整车之外的商品的预约时间表@create by ssd
CREATE TABLE `t_goods_appointment` (
`appoint_id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`goods_id`  bigint(20) NULL DEFAULT NULL ,
`time_num1`  int(11) NULL DEFAULT NULL COMMENT '9:00-10:00的数量' ,
`time_num2`  int(11) NULL DEFAULT NULL COMMENT '10:00-11:00的数量' ,
`time_num3`  int(11) NULL DEFAULT NULL COMMENT '11:00-12:00的数量' ,
`time_num4`  int(11) NULL DEFAULT NULL COMMENT '12:00-13:00的数量' ,
`time_num5`  int(11) NULL DEFAULT NULL COMMENT '13:00-14:00的数量' ,
`time_num6`  int(11) NULL DEFAULT NULL COMMENT '14:00-15:00的数量' ,
`time_num7`  int(11) NULL DEFAULT NULL COMMENT '15:00-16:00的数量' ,
`time_num8`  int(11) NULL DEFAULT NULL COMMENT '16:00-17:00的数量' ,
`type`  int(11) NOT NULL COMMENT '1：工作日时间段；2：周末时间段；3：节假日时间段' ,
`appoint_start`  date NULL DEFAULT NULL ,
`appoint_end`  date NULL DEFAULT NULL ,
`temp`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`appoint_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=12
ROW_FORMAT=COMPACT
;

--营销活动规则表@create by ssd
CREATE TABLE `t_activity_rules` (
`rule_id`  mediumint(8) NOT NULL AUTO_INCREMENT ,
`rule_name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则名称' ,
`act_rules`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`description`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则描述' ,
`create_time`  datetime NULL DEFAULT NULL ,
`member_lv_ids`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员级别集合,逗号分隔' ,
`status`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开启状态0：未开启  1：开启' ,
`conditions`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则条件' ,
`stop_rules_processing`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否排斥 0:否，1：是' ,
`sort_order`  int(10) NULL DEFAULT NULL COMMENT '优先级' ,
`end_time`  datetime NULL DEFAULT NULL ,
`start_time`  datetime NULL DEFAULT NULL ,
`act_status`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则类型' ,
PRIMARY KEY (`rule_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=24
ROW_FORMAT=COMPACT
;

--营销活动与商品的关系表@create by ssd
CREATE TABLE `t_activity_fullreduce_goods_ship` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`act_id`  bigint(20) NOT NULL ,
`goods_id`  int(11) NOT NULL COMMENT '规则ID' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=205
ROW_FORMAT=COMPACT
;

--满减满折活动表@create by ssd
CREATE TABLE `t_activity_full_reduce` (
`act_id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`rule_id`  mediumint(8) NOT NULL COMMENT '规则ID' ,
`company_id`  bigint(20) NULL DEFAULT NULL COMMENT '集团ID' ,
`store_id`  bigint(20) NULL DEFAULT NULL COMMENT '分店ID' ,
`name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称' ,
`description`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动描述' ,
`end_time`  datetime NULL DEFAULT NULL COMMENT '活动结束时间' ,
`apply_start_time`  datetime NULL DEFAULT NULL COMMENT '申请开始时间' ,
`start_time`  datetime NULL DEFAULT NULL COMMENT '活动开始时间' ,
`apply_end_time`  datetime NULL DEFAULT NULL COMMENT '申请结束时间' ,
`store_type`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺类型' ,
`activity_tag`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动标签' ,
`price_tag`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格标签' ,
`business_type`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户经营范围' ,
`act_open`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '活动开启状态（1：是；0：否）' ,
`act_status`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '1=>__(\'待审核\'), 2=>__(\'审核通过\'),3=>__(\'审核不通过\'),' ,
`last_modified`  date NULL DEFAULT NULL ,
`issue_type`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 => (\'平台发行\'),1 => (\'店铺发行\')' ,
`disabled`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除：0：否 1：是' ,
`use_platform`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:全平台；1:PC端；2：手机端' ,
`member_lv_ids`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`activity_type`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动类型，1：满减活动，2：满折活动' ,
`join_times`  int(3) NULL DEFAULT NULL ,
PRIMARY KEY (`act_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=17
ROW_FORMAT=COMPACT
;




/*==============================================================*/
/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
/*  数据库脚本 end
/*==============================================================*/


