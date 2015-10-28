/*==============================================================*/
/* 数据库脚本 begin                                     */
/*==============================================================*/

-- 添加app项目token表 @date:2015-8-2 @create by:yangyi 
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token` (
  `ftokenid` varchar(100) NOT NULL COMMENT '令牌方法id（uuid生成）',
  `fcreatetime` datetime DEFAULT NULL COMMENT '令牌创建时间',
  `fstate` varchar(5) DEFAULT NULL COMMENT '令牌方法执行情况 0 正在执行 1执行完成',
  `fresult` varchar(5000) DEFAULT NULL COMMENT '返回结果',
  `fendtime` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`ftokenid`),
  KEY `Index 2` (`fcreatetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检测方法令牌表';
-- 添加app项目客户端日志表  @date:2015-8-2 @create by:yangyi 
DROP TABLE IF EXISTS `t_applog`;
CREATE TABLE `t_applog` (
  `FAPPLOGID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT 'ID序号',
  `FUSERID` bigint(30) DEFAULT NULL COMMENT '用户主建id',
  `FUSERNAME` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `FPHONEMODEL` varchar(30) DEFAULT NULL COMMENT '手机型号,屏幕大小或者手机型号',
  `FPHONEOSTYPE` varchar(5) NOT NULL COMMENT '手机操作系统类型，5000android,6000ios',
  `FPHONEOS` varchar(50) DEFAULT NULL COMMENT '手机操作系统',
  `FINTERFACECODE` varchar(20) NOT NULL COMMENT '操作界面编码',
  `FINTERFACENAME` varchar(50) NOT NULL COMMENT '操作界面名称',
  `FUSETIME` datetime NOT NULL COMMENT '当前时间',
  PRIMARY KEY (`FAPPLOGID`),
  KEY `Index 2` (`FAPPLOGID`)
) ENGINE=MyISAM AUTO_INCREMENT=1548986 DEFAULT CHARSET=utf8 COMMENT='手机界面使用记录表（用来统计使用频率和次数）';

-- 添加app项目客户端统计表 @date:2015-8-02 @create by:yangyi 
DROP TABLE IF EXISTS `t_clientlog`;
CREATE TABLE `t_clientlog` (
  `fid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `fmethod` varchar(100) DEFAULT NULL COMMENT '方法名',
  `fnetmode` varchar(50) DEFAULT NULL COMMENT '网络模式(0:NO 1:Wwan 2:Wi-Fi)',
  `fisrelink` varchar(50) DEFAULT NULL COMMENT '重连标记(0:未重连 1:有重连)',
  `ftime` varchar(200) DEFAULT NULL COMMENT '数据请求时间(时间差--秒)',
  `fbegintime` varchar(100) DEFAULT NULL COMMENT '请求开始时间yyyyMMddHHmmss',
  `fresult` varchar(50) DEFAULT NULL COMMENT '成功失败编号 100 成功 其他编号 失败',
  `fresultinfo` text COMMENT '成功失败原因',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=284892 DEFAULT CHARSET=utf8 COMMENT='手机端调用每个方法所花销的时间';

-- 添加爬虫对比表 @date:2015-8-12 @create by:yangyi 
CREATE TABLE `t_car_spider_compare` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主建ID',
  `spider_val_id` varchar(100) DEFAULT NULL COMMENT '爬虫数据项id（如车型id）',
  `before_tips` text COMMENT '爬虫数据不同项对比前的值（json）',
  `after_tips` text COMMENT '爬虫数据不同项对比值（json）',
  `before_value` text COMMENT '对比前数据(json)',
  `after_value` text COMMENT '对比后数据(json)',
  `compare_type` varchar(10) DEFAULT NULL COMMENT '对比类型，add-新增，update-数据不一致',
  `type` varchar(100) DEFAULT NULL COMMENT '数据项类型CT,车型，CB车品牌，CS车系，CBY，保养，CP配件',
  `status` int(11) DEFAULT NULL COMMENT '状态。0生效，1失效',
  `create_id` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12945 DEFAULT CHARSET=utf8 COMMENT='爬虫数据对比表';

-- 添加爬虫对比日志表 @date:2015-8-12 @create by:yangyi 
CREATE TABLE `t_car_spider_aduit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '审核日志主建ID',
  `spider_compare_id` varchar(100) DEFAULT NULL COMMENT '爬虫对比表主建id',
  `before_value` text COMMENT '对比前数据(json)',
  `after_value` text COMMENT '对比后数据(json)',
  `type` varchar(100) DEFAULT NULL COMMENT '数据项类型CT,车型，CB车品牌，CS车系，CBY，保养，CP配件',
  `status` int(11) DEFAULT NULL COMMENT '审核状态。0审核通过，1审核拒绝',
  `create_id` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `cause` varchar(200) DEFAULT NULL COMMENT '审核不通过原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8 COMMENT='爬虫数据审核日志表';

-- 添加爬虫对比项基础表 @date:2015-8-12 @create by:yangyi 
CREATE TABLE `t_car_compare_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主建ID',
  `compare_type` varchar(100) DEFAULT NULL COMMENT '爬虫数据对比项',
  `compare_name` varchar(100) DEFAULT NULL COMMENT '爬虫数据对比项名称',
  `status` int(11) DEFAULT NULL COMMENT '状态。0生效，1失效',
  `create_id` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8 COMMENT='爬虫数据对比项基础表';

-- 修改活动表名 @date:2015-9-16 @create by:wanghb 
rename table t_scorebuy_activity to t_activity;
rename table t_scorebuy_apply to t_activity_apply;


--发布配件添加时间区间@create by ssd
alter table t_goods_time_price add product_id int(11);

/*==============================================================*/
/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
/*  数据库脚本 end
/*==============================================================*/


