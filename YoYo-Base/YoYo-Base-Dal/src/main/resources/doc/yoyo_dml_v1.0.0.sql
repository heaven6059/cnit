/*==============================================================*/
/* 数据库脚本 begin                                     */
/*==============================================================*/
-- 店铺列表菜单 @date:2015-6-8 @create by:xiaox
UPDATE `t_desktop_resource` SET `URL`='/shop/index' WHERE  `ID`=71;
UPDATE `t_desktop_resource` SET `URL`='/shopCheck/index' WHERE  `ID`=72;

-- 店员角色菜单 @date:2015-6-9 @create by:xiaox
UPDATE t_desktop_resource` SET `URL`='/roleManager/shopRole' WHERE  `ID`=78;
UPDATE `yoyo`.`t_desktop_resource` SET `URL`='/clerkManager/shopClerk' WHERE  `ID`=79;

-- 初始化国别（厂商系别）基础表数据 @date2015-6-25 @create by yangyi
delete from t_car_factory_scope;
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('中国',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('德国',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('日本',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('美国',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('韩国',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('法国',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('英国',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('意大利',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('瑞典',NOW(),'0',NOW());
INSERT into t_car_factory_scope(car_type,createtime,disabled,last_midifity) VALUES ('捷克',NOW(),'0',NOW());

-- 初始化车系级别基础表数据 @date:2015-6-9 @create by:yangyi
-- INSERT into t_car_brand_grade(GradeName,status) select tt.VAL as GradeName,0 from t_autohome_car_attr tt where tt.NAME='级别' group by tt.VAL;
delete from t_car_brand_grade;
INSERT into t_car_brand_grade(GradeName,status) values ('MPV',0);
INSERT into t_car_brand_grade(GradeName,status) values ('中型SUV',0);
INSERT into t_car_brand_grade(GradeName,status) values ('中型车',0);
INSERT into t_car_brand_grade(GradeName,status) values ('中大型SUV',0);
INSERT into t_car_brand_grade(GradeName,status) values ('中大型车',0);
INSERT into t_car_brand_grade(GradeName,status) values ('低端皮卡',0);
INSERT into t_car_brand_grade(GradeName,status) values ('大型SUV',0);
INSERT into t_car_brand_grade(GradeName,status) values ('大型车',0);
INSERT into t_car_brand_grade(GradeName,status) values ('小型SUV',0);
INSERT into t_car_brand_grade(GradeName,status) values ('小型车',0);
INSERT into t_car_brand_grade(GradeName,status) values ('微卡',0);
INSERT into t_car_brand_grade(GradeName,status) values ('微型车',0);
INSERT into t_car_brand_grade(GradeName,status) values ('微面',0);
INSERT into t_car_brand_grade(GradeName,status) values ('紧凑型SUV',0);
INSERT into t_car_brand_grade(GradeName,status) values ('紧凑型车',0);
INSERT into t_car_brand_grade(GradeName,status) values ('跑车',0);
INSERT into t_car_brand_grade(GradeName,status) values ('轻客',0);
INSERT into t_car_brand_grade(GradeName,status) values ('高端皮卡',0);

-- 更新车系级别、车系年份 存储过程 @date:2015-6-23 @create by:yangyi
CREATE PROCEDURE batchUpdateCarLevel()
BEGIN
  /*定义游标*/
  DECLARE v_dt bigint(20) DEFAULT 0;
  DECLARE v_num int DEFAULT 0;
  DECLARE temp_years varchar(5000); #车型id
  DECLARE id varchar(50); #车型id
  DECLARE pid varchar(100); #车系id 
  DECLARE temp_ids varchar(5000);
  DECLARE temp_vals varchar(5000);

  -- 更新车年份
  DECLARE cur_carlevel CURSOR FOR
  SELECT
    GROUP_CONCAT(DISTINCT SUBSTRING(SUBSTRING_INDEX(`NAME`, '款', 1), - 4)) AS years,
    yuy.pid
  FROM t_autohome_car_info yuy
  WHERE yuy.id LIKE 'CT%'
  GROUP BY yuy.pid;
  -- 更新级别
  DECLARE cur_cartype CURSOR FOR
  SELECT
    REPLACE(GROUP_CONCAT(tc.id SEPARATOR '\',\''), 'CT', '') AS id,
    tc.pid
  FROM t_autohome_car_info tc
  WHERE tc.pid LIKE 'CS%'
  GROUP BY tc.pid;

  /*游标循环到末尾时给定义的常量赋值*/
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET v_dt = - 1;
  /*开游标*/
  OPEN cur_carlevel;
  /*游标赋值*/
  FETCH cur_carlevel INTO temp_years, pid;
  SET v_num = 1;
  /* 循环体 */
  WHILE (v_dt != - 1) DO
    UPDATE t_autohome_car_info ttc
    SET ttc.`YEAR` = temp_years
    WHERE ttc.id = pid;
    /*每循环100次commit下*/
    SET v_num = v_num + 1;
    IF v_num > 10 THEN
      COMMIT;
      SET v_num = 1;
    END IF;
    /*读取下一行的数据*/
    FETCH cur_carlevel INTO temp_years, pid;
  /*循环结束*/
  END WHILE;
  /*关闭游标*/
  CLOSE cur_carlevel;

  /*开游标*/
  OPEN cur_cartype;
  /*游标赋值*/
  FETCH cur_cartype INTO temp_ids, pid;

  SET v_num = 1;
  SET v_dt= 1;
  /* 循环体 */
  WHILE (v_dt != - 1) DO
    SELECT
      GROUP_CONCAT(T.GradeID) INTO temp_vals
    FROM (SELECT
        gd.GradeID
      FROM t_autohome_car_attr at1,
           t_car_brand_grade gd
      WHERE `at1`.CAR_TYPE_ID IN ('\'' + temp_ids + '\'') AND `at1`.`NAME` = '级别' AND at1.VAL = gd.GradeName
      GROUP BY gd.GradeID) T;
    UPDATE t_autohome_car_info tci
    SET tci.`LEVEL` = temp_vals
    WHERE tci.id = pid;

    /*每循环100次commit下*/
    SET v_num = v_num + 1;
    IF v_num > 10 THEN
      COMMIT;
      SET v_num = 1;
    END IF;
    /*读取下一行的数据*/
    FETCH cur_cartype INTO temp_ids, pid;
  /*循环结束*/
  END WHILE;
  /*关闭游标*/
  CLOSE cur_cartype;

END

-- 新增钣金订单管理 @date:2015-6-26 @create by:caizhijie
INSERT INTO `yoyo`.`t_site_resource` (`RESOURCE_NAME`,`RESOURCE_TYPE`,`URL`,`PARENT_ID`) VALUES ('钣金订单管理','menu','/paintingManager/orderList','37');

-- 更新店铺浏览历史@date:2015-6-27 @create by:wangpeng
DROP PROCEDURE PRO_ADD_T_STORE_VIEW_HISTORY;
create procedure PRO_ADD_T_STORE_VIEW_HISTORY(IN MEMBER BIGINT,IN COMPANY BIGINT,IN VIEWDATE TIMESTAMP)
BEGIN
	DECLARE V_COUNT INT(10);	
	SELECT COUNT(0) INTO V_COUNT FROM t_store_view_history WHERE member_id=MEMBER AND company_id=COMPANY;		
	IF V_COUNT < 1 THEN
       	INSERT INTO t_store_view_history (member_id,company_id,view_date) VALUES (MEMBER,COMPANY,VIEWDATE);
		COMMIT;
	END IF;
END;

-- 初始化首页广告管理数据
DELETE FROM `t_site_ad`;
INSERT INTO `t_site_ad` (`ad_id`, `ad_type`, `ad_config`, `begin_time`, `end_time`, `enabled`, `order_no`, `sequence`, `name`, `description`) VALUES
	(1, 0, '{"intervalTime":"2000","content":[{"picUrl":"/resources/images/index/banner01.jpg","destUrl":"http://www.taobao.com","title":"淘宝网","desc":""},{"picUrl":"/resources/images/index/h_ad.jpg","destUrl":"http://www.oschina.net","title":"开源中国","desc":""},{"picUrl":"/resources/images/index/h_ad.jpg","destUrl":"http://www.360buy.com","title":"京东商城","desc":""},{"picUrl":"/resources/images/index/yo_08.jpg","destUrl":"http://www.123456.com","title":"找不到地址","desc":""}]}', '2015-07-01 00:00:00', '2015-07-03 00:00:00', 1, 1, NULL, 'homeCarousel', '首页轮播广告'),
	(2, 1, '{"intervalTime":"","content":[{"picUrl":"/resources/images/index/h_ad.jpg","destUrl":"","title":"喷油嘴清洗服务","desc":"广告说明"}]}', '2015-07-01 00:00:00', '2015-07-03 00:00:00', 1, 2, NULL, 'homeHead', '首页头部广告');
-- 广告管理角色权限
--恢复广告管理角色权限的脚本
set @r_id = (select id from t_desktop_resource where resource_name='广告管理');
delete from t_desktop_resource where resource_name='广告管理';
delete from t_resource_role where role_id=3 and resource_id=@r_id;

--set @r_id = (select id from t_desktop_resource where resource_name='编辑广告');
--delete from t_desktop_resource where resource_name='编辑广告';
--delete from t_resource_role where role_id=3 and resource_id=@r_id;
--广告管理角色权限
set @p_id = (select id from t_desktop_resource where resource_name='页面管理');
insert into t_desktop_resource(
resource_name,resource_type,url,parent_id
)values(
'广告管理','menu','/siteAd/index',@p_id
);

set @r_id = (select id from t_desktop_resource where resource_name='广告管理');
insert into t_resource_role(
role_id,resource_id
)values(
3,@r_id
);

/*set @p_id = (select id from t_desktop_resource where resource_name='页面管理');
insert into t_desktop_resource(
resource_name,resource_type,url,parent_id
)values(
'编辑广告','button','/siteAd/edit',@p_id
);

set @r_id = (select id from t_desktop_resource where resource_name='编辑广告');
insert into t_resource_role(
role_id,resource_id
)values(
3,@r_id
);*/
CREATE PROCEDURE pro_find_goods_cat_childLst (IN identifierId INT,IN rootId INT)
BEGIN
  CREATE TEMPORARY TABLE IF NOT EXISTS tmpLst
     (sno int primary key auto_increment,goods_cat int,depth int);
  DELETE FROM tmpLst;
  set max_sp_recursion_depth=12;
  CALL pro_goods_cat_childLst(rootId,0);
	SELECT CAT_ID AS id,CAT_NAME AS NAME,PARENT_CAT_ID AS pId FROM t_goods_cat WHERE DISABLED =0 AND identifier = identifierId  AND CAT_ID NOT IN (select tmpLst.goods_cat from tmpLst order by tmpLst.sno);
END;


CREATE PROCEDURE pro_goods_cat_childLst (IN rootId INT,IN nDepth INT)
BEGIN		
    DECLARE done INT DEFAULT 0;
    DECLARE b INT;
    DECLARE cur1 CURSOR FOR SELECT CAT_ID FROM t_goods_cat where PARENT_CAT_ID=rootId;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;    
    insert into tmpLst values (null,rootId,nDepth);    
    OPEN cur1;    
    FETCH cur1 INTO b;
    WHILE done=0 DO
       CALL pro_goods_cat_childLst(b,nDepth+1);
       FETCH cur1 INTO b;
    END WHILE;    
    CLOSE cur1;
END;

	
-- 增加整车品牌菜单 @date:2015.07.06 xiaox
INSERT INTO `yoyo`.`t_desktop_resource` (`ID`, `RESOURCE_NAME`, `RESOURCE_TYPE`, `TARGET`, `MENU_ORDER`, `PARENT_ID`,`URL`) VALUES (423, '整车品牌', 'menu', '1', 4, 11,'/brand/carIndex');
INSERT INTO `yoyo`.`t_resource_role` ( `ROLE_ID`, `RESOURCE_ID`) VALUES ( 3, 423);

--文章数据初始化
TRUNCATE TABLE t_content_article_nodes;
INSERT INTO `t_content_article_nodes` VALUES ('1', '0', '1', '购物指南', null, ',', null, null, null, '1', '1', '0', '1', '0', '2015-07-20 13:41:57', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('2', '0', '1', '售后服务', null, ',', null, null, null, '1', '1', '0', '2', '0', '2015-07-20 13:42:33', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('3', '0', '1', '支付方式', null, ',', null, null, null, '1', '1', '0', '3', '0', '2015-07-20 13:42:38', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('4', '0', '1', '特色服务', null, ',', null, null, null, '1', '1', '0', '4', '0', '2015-07-20 13:42:57', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('5', '1', '2', '购物流程', null, ',1,', null, null, null, '1', '1', '0', '1', '0', '2015-07-21 15:29:05', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('6', '1', '2', '会员介绍', null, ',1,', null, null, null, '1', '0', '0', '2', '0', '2015-07-20 13:55:03', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('7', '1', '2', '团购活动', null, ',1,', null, null, null, '1', '0', '0', '3', '0', '2015-07-20 13:55:08', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('8', '1', '2', '常见问题', null, ',1,', null, null, null, '1', '0', '0', '4', '0', '2015-07-20 13:55:13', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('9', '1', '2', '购物指引', null, ',1,', null, null, null, '1', '0', '0', '5', '0', '2015-07-20 13:55:20', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('10', '1', '2', '联系客服', null, ',1,', null, null, null, '1', '0', '0', '6', '0', '2015-07-20 13:54:48', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('11', '2', '2', '售后政策', null, ',2,', null, null, null, '1', '0', '0', '1', '0', '2015-07-21 10:19:13', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('12', '2', '2', '价格保护', null, ',2,', null, null, null, '1', '0', '0', '2', '0', '2015-07-20 13:56:59', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('13', '2', '2', '退款说明', null, ',2,', null, null, null, '1', '0', '0', '3', '0', '2015-07-20 13:57:06', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('14', '2', '2', '返修/退换货', null, ',2,', null, null, null, '1', '0', '0', '4', '0', '2015-07-20 13:57:15', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('15', '2', '2', '如何办理退换货', null, ',2,', null, null, null, '1', '0', '0', '5', '0', '2015-07-20 13:59:00', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('16', '2', '2', '取消订单', null, ',2,', null, null, null, '1', '0', '0', '6', '0', '2015-07-20 13:59:20', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('17', '3', '2', '货到付款', null, ',3,', null, null, null, '1', '0', '0', '1', '0', '2015-07-20 14:23:28', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('18', '3', '2', '在线支付', null, ',3,', null, null, null, '1', '0', '0', '2', '0', '2015-07-20 14:23:44', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('19', '3', '2', '分期支付', null, ',3,', null, null, null, '1', '0', '0', '3', '0', '2015-07-20 14:23:59', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('20', '3', '2', '到店支付', null, ',3,', null, null, null, '1', '0', '0', '4', '0', '2015-07-20 14:24:13', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('21', '3', '2', '手机支付', null, ',3,', null, null, null, '1', '0', '0', '5', '0', '2015-07-20 14:24:22', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('22', '3', '2', '银联转账', null, ',3,', null, null, null, '1', '0', '0', '6', '0', '2015-07-20 14:24:38', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('23', '4', '2', '一元起拍', null, ',4,', null, null, null, '1', '0', '0', '1', '0', '2015-07-20 14:24:50', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('24', '4', '2', '延保服务', null, ',4,', null, null, null, '1', '0', '0', '2', '0', '2015-07-20 14:24:58', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('25', '4', '2', 'YOYO豆', null, ',4,', null, null, null, '1', '0', '0', '3', '0', '2015-07-20 14:25:05', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('26', '4', '2', '节能补贴', null, ',4,', null, null, null, '1', '0', '0', '4', '0', '2015-07-20 14:25:15', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('27', '4', '2', 'YOYO保障', null, ',4,', null, null, null, '1', '0', '0', '5', '0', '2015-07-20 14:25:24', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('28', '5', '3', '账号注册', null, ',1,5,', null, null, null, '1', '0', '0', '1', '0', '2015-07-21 15:17:41', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('29', '5', '3', '下单流程', null, ',1,5,', null, null, null, '1', '0', '0', '2', '0', '2015-07-21 15:17:41', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('30', '5', '3', '挑选商品', null, ',1,5,', null, null, null, '1', '0', '0', '3', '0', '2015-07-21 15:17:41', null, '0', '', '0', '1');
INSERT INTO `t_content_article_nodes` VALUES ('31', '5', '3', '提交订单', null, ',1,5,', null, null, null, '1', '0', '0', '4', '0', '2015-07-21 15:17:41', null, '0', '', '0', '1');

TRUNCATE TABLE t_content_article_indexs;
INSERT INTO `t_content_article_indexs` VALUES ('1', '如何注册YOYO账号？', null, '1', '28', '', '2015-07-22 10:20:15', '2015-07-22 10:20:15', '1', '1', null, '0');
INSERT INTO `t_content_article_indexs` VALUES ('2', '如何注册企业会员？', null, '1', '28', '', '2015-07-22 10:20:52', '2015-07-22 10:20:52', '1', '1', null, '0');
INSERT INTO `t_content_article_indexs` VALUES ('3', 'YOYO购物流程', null, '1', '29', '', '2015-07-22 10:21:46', '2015-07-22 10:21:46', '1', '1', null, '0');
INSERT INTO `t_content_article_indexs` VALUES ('4', '如何购买下单？', null, '1', '29', '', '2015-07-22 10:22:24', '2015-07-22 10:22:24', '1', '1', null, '0');
INSERT INTO `t_content_article_indexs` VALUES ('5', '如何查找想要的商品？', null, '1', '30', '', '2015-07-22 10:24:05', '2015-07-22 10:24:05', '1', '1', null, '0');
INSERT INTO `t_content_article_indexs` VALUES ('6', '如何填写收货地址？', null, '1', '31', '', '2015-07-22 10:24:55', '2015-07-22 10:24:55', '1', '1', null, '0');
INSERT INTO `t_content_article_indexs` VALUES ('7', '如何查看购物车中的商品？', null, '1', '31', '', '2015-07-22 10:25:31', '2015-07-22 10:25:31', '1', '1', null, '0');
INSERT INTO `t_content_article_indexs` VALUES ('8', '为什么收货地址里面没有我的地区所在的街道？', null, '1', '31', '', '2015-07-22 10:26:00', '2015-07-22 10:26:00', '1', '1', null, '0');

TRUNCATE TABLE t_content_article_bodys;
INSERT INTO `t_content_article_bodys` VALUES ('1', '1', null, '<p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">若您还没有京东账号，请点击<a href=\"https://reg.jd.com/reg/person?ReturnUrl=http%3A%2F%2Fwww.jd.com\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\">注册</a>，详细操作步骤如下：&nbsp;</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">1. 打开京东首页，在右上方，点击“免费注册”按钮；</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><br/></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><img alt=\"\" src=\"http://misc.360buyimg.com/help/misc/img/r1.png\" width=\"750\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/><a class=\"r1\" href=\"http://help.jd.com/user/issue/33-14.html#r2\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\"></a></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">2. 进入到注册页面，请填写您的邮箱、手机等信息完成注册；</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><img alt=\"\" src=\"http://misc.360buyimg.com/help/misc/img/r2.png\" width=\"750\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/><a class=\"r2\" href=\"http://help.jd.com/user/issue/33-14.html#r1\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\"></a><a class=\"r3\" href=\"http://help.jd.com/user/issue/33-14.html#r3\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\"></a></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">3. 注册成功后，请完成账户安全验证，来提高您的账户安全等级。</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><img alt=\"\" src=\"http://misc.360buyimg.com/help/misc/img/r3.png\" width=\"750\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/></p>', null, null, null, '2015-07-22 10:20:15', '2015-07-22 10:20:15', null, '3068', null);
INSERT INTO `t_content_article_bodys` VALUES ('2', '2', null, '<p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 28px; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">注册企业会员，请点击<a href=\"https://reg.jd.com/reg/company\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\">注册</a>，具体操作步骤如下：</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 28px; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">1.请登陆<a href=\"http://www.jd.com/\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\">京东网站</a>，首页上方有“免费注册”，点击“免费注册”进入用户注册的页面；</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 28px; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">2.用户类型请选择“企业用户”，按照页面提示将信息填写完整后提交申请即可，成功提交后，我司会安排客户经理电话联系您沟通相关合作事宜，如您的条件符合我司的企业用户条件，客户经理审核通过后您就成功的成为我司的企业用户，如果审核失败，用户类型就是“个人用户”。（如下图）</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; color: rgb(102, 102, 102); background-color: rgb(255, 255, 255);\">&nbsp;<img title=\"\" alt=\"\" src=\"http://img30.360buyimg.com/pophelp/jfs/t661/91/1249948618/49492/5fb495aa/54c1c7afN4febd2fc.png\" width=\"700\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px; text-indent: 28px; font-family: 宋体, SimSun;\"/></p>', null, null, null, '2015-07-22 10:20:52', '2015-07-22 10:20:52', null, '1735', null);
INSERT INTO `t_content_article_bodys` VALUES ('3', '3', null, '<img alt=\"\" src=\"http://img30.360buyimg.com/pophelp/g10/M00/13/11/rBEQWVFlHpYIAAAAAACI4qKbEu4AAD0VAKcRBMAAIj6957.jpg\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px; font-family: Arial, Verdana, 宋体; line-height: 21px; text-indent: 24px; font-size: 16px; background-color: rgb(255, 255, 255);\"/>', null, null, null, '2015-07-22 10:21:46', '2015-07-22 10:21:46', null, '370', null);
INSERT INTO `t_content_article_bodys` VALUES ('4', '4', null, '<p style=\"margin: 0px 0px 20px 28px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">下单操作步骤：</span></p><p style=\"margin: 0px 0px 20px 28px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">1.<span style=\"margin: 0px; padding: 0px; font-family: \'Times New Roman\'; font-size: 9px;\">&nbsp;</span>浏览您要购买的商品，点击“加入购物车”，商品会自动添加到购物车里；</span></p><p style=\"margin: 0px 0px 20px 28px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">2.<span style=\"margin: 0px; padding: 0px; font-family: \'Times New Roman\'; font-size: 9px;\">&nbsp;</span>如果您需要更改商品数量，需在商品数量框中输入购买数量；（如下图）</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1600/22/79126860/210347/86ef9fc6/55559e93Neeb7d93a.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/></p><p style=\"margin: 0px 0px 20px 28px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">3.选好商品后点击“去结算”；（如下图）</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t499/111/1373199640/48946/d4550d3f/55559ef5N96f685d7.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/></p><p style=\"margin: 0px 0px 20px 28px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">4.详细填写收货人信息、支付方式、发票信息，核对送货清单等信息；（如下图）</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1585/224/74678807/78237/391603a/55559faeN41044e9e.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/></p><p class=\"MsoListParagraph\" style=\"margin: 5px 0px 5px 28px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">5.确认无误后点击“提交订单”，生成新订单并显示订单编号；</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体;\">&nbsp;&nbsp;<span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">&nbsp;6.查看订单详细信息：可进入“我的京东”→“<a href=\"http://order.jd.com/center/list.action\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\">订单中心</a>”查看。</span></span></p>', null, null, null, '2015-07-22 10:22:24', '2015-07-22 10:22:24', null, '3737', null);
INSERT INTO `t_content_article_bodys` VALUES ('5', '5', null, '<p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">通过商品品类查询商品：例如，我需要购买魅族手机MX4 移动4G 灰色版本</span></p><p dir=\"ltr\" style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">步骤一：将鼠标移到京东首页【全部商品分类】→【手机、数码、京东通讯】→【手机通讯】→【手机】点击鼠标左键；</span><img src=\"http://img30.360buyimg.com/pophelp/jfs/t841/16/886028927/81792/818d8b3c/5555a220Ndc5388cf.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px; text-indent: 2em;\"/></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px;\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">步骤二和步骤三：在手机筛选页面的【品牌】选项中，找到【魅族】LOGO并单击鼠标，然后再选中【网络】中的【移动4G】、在【机身颜色】中选择【灰色】即可；</span></span><img src=\"http://img30.360buyimg.com/pophelp/jfs/t964/284/884542134/80848/b9ee6fdb/5555a26eNeddfc380.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px; text-indent: 2em;\"/></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; text-indent: 2em;\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1357/3/81043292/32792/4503983c/5555a2e5Nbdde89f8.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/>&nbsp;</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体;\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">步骤四：根据筛选结果选择所需要的手机。</span></span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; line-height: 18px; font-family: Calibri, sans-serif;\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1267/326/907591768/200285/ad8584ca/5555a31dN9475f711.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/></span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px;\">&nbsp;</span></p>', null, null, null, '2015-07-22 10:24:05', '2015-07-22 10:24:05', null, '3132', null);
INSERT INTO `t_content_article_bodys` VALUES ('6', '6', null, '<p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; line-height: 18px; font-family: 宋体;\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">填写收货地址的操作步骤：</span></span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; line-height: 18px; font-family: 宋体, SimSun;\">步骤一：先登陆京东，点击京东首页右上角【<a href=\"http://home.jd.com/\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\">我的京东</a>】；(如下图)</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1480/349/75621466/50397/a3c200fc/5555a3efNface3bbd.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">步骤二：点击<span style=\"margin: 0px; padding: 0px;\">【账户设置】</span>-【<a href=\"http://easybuy.jd.com/address/getEasyBuyList.action\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\">收货地址</a>】进入地址填写页面，然后点击【新增收货地址】即可输入您的收货地址。<span style=\"margin: 0px; padding: 0px; line-height: 18px;\">(如下图)</span></span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\"><span style=\"margin: 0px; padding: 0px; line-height: 18px;\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1282/158/164967368/55551/83b564b5/5555a45cN8aaa4e1d.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/></span></span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\"><span style=\"margin: 0px; padding: 0px; line-height: 18px;\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1213/313/896969443/30364/f4b0a086/5555a4b3Nbd759c6d.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px; text-indent: 28px;\"/></span></span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\"><span style=\"margin: 0px; padding: 0px; line-height: 18px;\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1387/286/74502962/21268/6c6d043/5555a4d6N0ae067e5.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px;\"/></span></span></p>', null, null, null, '2015-07-22 10:24:55', '2015-07-22 10:24:55', null, '3548', null);
INSERT INTO `t_content_article_bodys` VALUES ('7', '7', null, '<p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">将商品加入购物车后可在<a href=\"http://www.jd.com/\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(77, 183, 235); text-decoration: none;\">京东首页</a>，右上角点击【我的购物车】即可进入购物车查询、删除商品。（如下图）</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\"><img src=\"http://img30.360buyimg.com/pophelp/jfs/t1060/317/876523882/22145/a9e32ed5/5555a5f4N9b34eda4.png\" width=\"670\" title=\"\" alt=\"\" style=\"margin: 10px 0px 10px -2em; padding: 0px; vertical-align: middle; border: 0px; text-align: center; max-width: 650px; text-indent: 28px;\"/></span></p><div><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\"><br/></span></div>', null, null, null, '2015-07-22 10:25:31', '2015-07-22 10:25:31', null, '1048', null);
INSERT INTO `t_content_article_bodys` VALUES ('8', '8', null, '<p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">为减少您的困扰，提升购物体验，我们将街道进行合并到“城区”里，若“街道”无法选择，请您直接选择“城区”下单。</span></p><p style=\"margin: 0px 0px 20px; padding: 0px; text-indent: 2em; line-height: 21px; font-family: Arial, Verdana, 宋体; font-size: 12px; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体, SimSun;\">温馨提示：如您无法选择对应地址提交订单，请您咨询身边的朋友或者家人等，请勿随意选择一个相近地址，以免配送区域选择有误，导致后续无法给您进行配送，感谢您的支持和理解。</span></p>', null, null, null, '2015-07-22 10:26:00', '2015-07-22 10:26:00', null, '634', null);

/*==============================================================*/
/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
/*  数据库脚本 end
/*==============================================================*/

