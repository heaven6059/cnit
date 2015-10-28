<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link type="text/css" href="${path}/resources/styles/shopMng/roleManager.css?r=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?r=${versionVal}" rel="stylesheet" />
<script type="text/javascript">var _path="${path}";</script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?r=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/shopMng/roleManager.js?r=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?r=${versionVal}"></script>
</head>
<body>
<div class="member-main member-main2">
	<div class="shop_manager_right">
		<div class="title">
			<span style="float: left">角色管理<span class="disc">|</span> <span
				class="disc add-icon"><a href="javascript:addRole();">添加新角色</a></span>
			</span>
		</div>
		<!-- 添加角色 start -->
		<div id="addRoles" class="addRoles" style="border:none;">
			<form id="addRoles_form">
				<table class="addRoles_table">
					<tbody>
						<tr>
							<td style="width: 80px;"><span style="color: red;">* </span>角色名称：</td>
							<td><input type="text" name="rolesName" id="rolesName"
								class="easyui-validatebox validatebox-text validatebox-invalid"
								data-options="required:true" missingmessage="请填写角色名称" 
								style="border: 1px solid #cccccc; padding: 6px 5px; width: 300px;
								line-height: 18px;vertical-align: middle;"><input type="hidden" name="rolesId" id="rolesId" /></td>
						</tr>
						<tr>
							<td align="right"><span style="color: red;">* </span>角色权限：</td>
							<td > <input type="checkbox" style="width:auto;" onclick="selectAllRes(this)" name="selectAll" />全选</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td id="resource_td">
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td id="resource_td">
								<input type="button" value="保存" onclick="saveRoles()" style="background-color: #1C86EE;color:white;padding: 10px 20px;margin: auto 50px;">
								<input type="button" value="取消" onclick="hideAddRoles()" style="background-color: #1C86EE;color:white;padding: 10px 20px;margin: auto 50px;">
							</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="storeId"> <input type="hidden" name="limitStore" id="limitStore" value="6">
				<!-- 已经开店的个数 -->
			</form>
			<a href="#" onclick="javascript:saveStore()" class="save_btn addshop-form-a"></a> <a href="javascript:$('#addShop').hide();" class="cancel_btn "></a>
		</div>
		<!-- 添加分店 end -->
		<div class="panel datagrid">
			<div class="datagrid-wrap panel-body panel-body-noheader" style="width: 100%">
				<table class="datagrid-btable" style="table-layout: auto;border: 1px solid #F0F0F0;width: 100%;">
						<tr>
							<th>角色名</th>
							<th>更新时间</th>
							<th>操作</th>
						</tr>
				</table>
			</div>
			<div class="page clearfix">				
				<div id="Pagination" class="yoyoPagination" ></div>
			</div>
		</div>
	</div>
</div>
</body>
</html>