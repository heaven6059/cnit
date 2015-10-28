-- 一级菜单
insert into t_site_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values('交易管理','menu','',0),
('商品管理','menu','',0),
('店铺管理','menu','',0),
('结算中心','menu','',0),
('客服中心','menu','',0),
('营销中心','menu','',0);

-- 二级菜单
set  @spid=(select id from t_site_resource  where RESOURCE_NAME='交易管理');
insert into t_site_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('订单管理','menu','',@spid),
('退换货管理','menu','',@spid),
('退款管理','menu','',@spid),
('评论管理','menu','',@spid);


set  @pid21=(select id from t_site_resource  where RESOURCE_NAME='商品管理');
insert into t_site_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('发布商品','menu','/goodsPublish/index',@pid21),
('设置保养商品','menu','',@pid21),
('出售中的商品','menu','',@pid21),
('仓库中的商品','menu','',@pid21),
('预警中的商品','menu','',@pid21),
('商品分类管理','menu','',@pid21),
('商品品牌管理','menu','/brand/businessManager',@pid21);



set  @pid22=(select id from t_site_resource  where RESOURCE_NAME='店铺管理');
insert into t_site_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('店铺首页','menu','',@pid22),
('店铺管理','menu','/shopManager/manager',@pid22),
('基本设置','menu','/shopManager/shopBaseSetup',@pid22),
('角色管理','menu','',@pid22),
('店员管理','menu','/clerkManager/findClerk',@pid22),
('模版设置','menu','',@pid22),
('保证金','menu','',@pid22);


set  @pid23=(select id from t_site_resource  where RESOURCE_NAME='结算中心');
insert into t_site_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('结算报表','menu','',@pid23);


set  @pid24=(select id from t_site_resource  where RESOURCE_NAME='客服中心');
insert into t_site_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('投诉管理','menu','',@pid24),
('举报管理','menu','',@pid24),
('咨询管理','menu','',@pid24),
('站内信(0)','menu','',@pid24),
('在线客服管理','menu','',@pid24);



set  @pid25=(select id from t_site_resource  where RESOURCE_NAME='营销中心');
insert into t_site_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('优惠券','menu','',@pid25),
('活动报名','menu','',@pid25),
('我参加的活动','menu','',@pid25);

