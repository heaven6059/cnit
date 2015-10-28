

/**
 * 在“商品”下添加菜单
 * 注意：以下4个字段必须输入，并且RESOURCE_NAME不能相同
 * RESOURCE_TYPE暂时只有menu与button 2种，页面都是menu
 */


-- 二级菜单
set  @spid=(select id from t_desktop_resource  where RESOURCE_NAME='商品');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('商品管理','menu','',@spid);

-- 三级菜单
set  @spid2=(select id from t_desktop_resource  where RESOURCE_NAME='商品管理');
insert into t_desktop_resource  (RESOURCE_NAME,RESOURCE_TYPE,URL,PARENT_ID)
values
('汽车厂商','menu','/carFactory/index',@spid2);