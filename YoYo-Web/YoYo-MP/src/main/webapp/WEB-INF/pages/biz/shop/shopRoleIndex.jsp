<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>店铺角色列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/shop/shopCheckIndex.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/shop/shopRoleIndex.js"></script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRoles()"> 删除 </a>
		
		<a id="member-ads-button" href="javascript:openAdvaceQuery('advance_search_shoprole')" class="easyui-linkbutton" iconCls="icon-search" style="margin-left:20px;">高级筛选</a>
	</div>
	<table id="shopRoleDataGrid" style="marge: 10px auto;">
	</table>
	
	 
		
		<!-- 高级刷选 -->
	<div id="advance_search_shoprole" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
        
		<form id="shoprole_search_form" class="easyui-form" method="post"
		data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
					<td align="right"><span>角色名称: </span></td>
					<td><input id="rolesName" name="rolesName"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>店铺名称: </span></td>
					<td><input id="shopName" name="shopName"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>分店名称: </span></td>
					<td><input id="storeName" name="storeName"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>角色建立时间: </span></td>
					<td><input  name="regtimeStart" class="easyui-datebox" style="width: 95px;"/>~
					<input  name="regtimeEnd"	class="easyui-datebox" style="width: 95px;"/></td>
				</tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('shopRoleDataGrid','shoprole_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="search_clear('shoprole_search_form','shopRoleDataGrid')" style="width: 90px">清除条件</a>
    </div>
	
</body>
</html>
