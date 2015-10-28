<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>优惠券列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<link type="text/css" rel="stylesheet"	href="${path }/resources/styles/activity/couponsList.css?v=${versionVal}">
</head>
<body>

	<script type="text/javascript" src="${path}/resources/scripts/biz/activity/couponsList.js?v=${versionVal}"></script>
	<div id="toolbar_coupons">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true"	onclick="openSaveDialog()"> 添加</a> 
		<!-- <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteCarfactory()">删除 </a> -->
		
	</div>
	<table id="coupons_datagrid"></table>

</body>
</html>
