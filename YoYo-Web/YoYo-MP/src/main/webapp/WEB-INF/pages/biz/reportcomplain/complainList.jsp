<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>举报管理</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/biz/reportcomplain/complain.js?v=${versionVal}"></script>
</head>
<body>
	<div id="panel-goods">
		<div id="toolbar-report-list">
			<a href="#" style="margin: 5px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_order')" title="当前状态中高级筛选">高级筛选</a>
		</div>
		<table id="complain_list_dataGrid" style="marge: 10px auto;">
		</table>
	</div>
	<div id="window-view-after-sales" class="easyui-dialog" title="Basic Dialog" data-options="iconCls:'icon-save',closed:true" style="width:840px;height:740px;padding:10px">
    </div>
	<!-- 高级查询 -->
	<div id="advance_search_order" class="easyui-dialog advance_search" title="高级筛选" style="background: #F5F5F5" data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="complain_search_form" class="easyui-form" method="post" data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
					<td align="right">投诉编号: </td>
					<td><input id="complainId" name="complainId" class="easyui-textbox search_class"  style="width: 220px"/></td>
				</tr>
				<tr>
					<td align="right">被投诉方: </td>
					<td><input id="storeName" name="storeName" class="easyui-textbox search_class"  style="width: 220px"/></td>
				</tr>
				<tr>
					<td align="right">投诉原因: </td>
					<td>
						<select style="width: 220px" name="reason" id="reason">
							<option value="" selected="selected">请选择</option>
							<option value="after">售后问题 </option>
							<option value="action">行为违规</option>
							<option value="quality">质量问题</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">投诉状态: </td>
					<td style="text-align: left;">
						<select style="width: 220px" name="status" id="status">
							<option value="" selected="selected">请选择</option>
							<c:forEach items="${complainStatus}" var="item">
							<option value="${item.key}">${item.value}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="complainAdvanceSearch()" style="width: 90px">立即筛选</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearComplainSearch()" style="width: 90px">清除条件</a>
	</div>
	
</body>
</html>