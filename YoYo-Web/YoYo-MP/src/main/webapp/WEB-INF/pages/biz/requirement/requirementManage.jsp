<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>快速发布需求管理</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/requirementManage/requirementManage.js"></script>
<!-- <link type="text/css" href="${path}/resources/styles/requirement/requirementManage.css" rel="stylesheet" />  -->
</head>
<body>
	<div id="panel-goods">
		<div id="toolbar-order-list">
			<a href="#" style="margin: 5px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_order')" title="当前状态中高级筛选">高级筛选</a>
		</div>
		<table id="requirementTable"></table>
	</div>
	<!-- 高级查询 -->
	<div id="advance_search_order" class="easyui-dialog advance_search" title="高级筛选" style="background: #F5F5F5" data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="search_form" class="easyui-form" method="post" data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
					<td align="right"><span>发布人: </span></td>
					<td><input id="name" name="name" class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>发布账号: </span></td>
					<td><input id="loginName" name="loginName" class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>发布日期: </span></td>
					<td style="text-align: left;">
						<input id="startDate" name="startDate" type="text" class="easyui-datebox" />
						至
						<input id="endDate" name="endDate" type="text" class="easyui-datebox" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="advanceSearch()" style="width: 90px">立即筛选</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="searchClear()" style="width: 90px">清除条件</a>
	</div>
</body>
</html>