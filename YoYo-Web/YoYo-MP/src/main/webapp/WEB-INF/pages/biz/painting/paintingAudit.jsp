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
<title>受损单审核</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/styles/painting/paintingAudit.css" rel="stylesheet" />

<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/painting/paintingAudit.js"></script>
</head>
<body>
	<div id="tabs" class="easyui-tabs">
		<div title="全部" selected="true"></div>
		<div title="审核中"></div>
		<div title="通过"></div>
		<div title="未通过"></div>
	</div>
	<table id="paintingTable"></table>
</body>
</html>