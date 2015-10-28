-- 一级菜单
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values('商品','menu','',0),
('订单','menu','',0),
('会员','menu','',0),
('店铺','menu','',0),
('营销','menu','',0),
('报表','menu','',0),
('客服','menu','',0),
('站点','menu','',0);

-- 二级菜单
set  @spid=(select id from t_desktop_resource  where RESOURCE_NAME='商品');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('商品管理','menu','',@spid),
('商品配置','menu','',@spid),
('店铺品牌管理','menu','',@spid),
('保养配置','menu','',@spid);


-- 三级菜单

set  @pid31=(select id from t_desktop_resource  where RESOURCE_NAME='商品管理');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('商品列表','menu','',@pid31),
('汽车厂商','menu','/carFactory/index',@pid31),
('车型列表','menu','',@pid31),
('车型数据项','menu','/carData/index',@pid31),
('配件参数类型','menu','/accessory/accessoryCataIndex',@pid31),
('配件列表','menu','/accessory/accessoryCataIndex',@pid31);



set  @pid32=(select id from t_desktop_resource  where RESOURCE_NAME='商品配置');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('商品分类','menu','/cate/cateIndex',@pid32),
('商品规格','menu','/spec/specIndex',@pid32),
('商品品牌','menu','/spec/specIndex',@pid32),
('虚拟分类','menu','/cate/virtualCateIndex',@pid32);



set  @pid33=(select id from t_desktop_resource  where RESOURCE_NAME='店铺品牌管理');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('品牌审核','menu','/brand/apply_index',@pid33);


set  @pid34=(select id from t_desktop_resource  where RESOURCE_NAME='保养配置');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('保养周期','menu','/carMaintain/index',@pid34);


-- 会员模块
set  @pid2=(select id from t_desktop_resource  where RESOURCE_NAME='会员');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('会员管理','menu','',@pid2),
('咨询评论','menu','',@pid2);

set  @pid21=(select id from t_desktop_resource  where RESOURCE_NAME='会员管理');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('会员列表','menu','/member/members',@pid21),
('会员等级','menu','/memberLevel',@pid21);



set  @pid22=(select id from t_desktop_resource  where RESOURCE_NAME='咨询评论');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('咨询列表','menu','',@pid22),
('评论列表','menu','',@pid22),
('站内信','menu','',@pid22),
('消息设置','menu','',@pid22);






