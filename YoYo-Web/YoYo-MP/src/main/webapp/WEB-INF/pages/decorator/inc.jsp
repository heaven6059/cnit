<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="com.cnit.yoyo.util.Configuration" %> 
<%
   String path = request.getContextPath();
   request.setAttribute("path", path);
   application.setAttribute("imgUrl", Configuration.getInstance().getConfigValue("images.url"));
   application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?t=${versionVal}"></script>
<link type="text/css" href="${path}/resources/scripts/easyui/themes/default/easyui.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/scripts/easyui/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/common.css?t=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/good/brandIndex.css?t=${versionVal}" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.advanceSeacher.js"></script>
<link type="text/css" href="${path}/resources/scripts/cleditor/jquery.cleditor.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/cleditor/jquery.cleditor.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?t=${versionVal}"></script>
<script type="text/javascript">
	var _path = '${path}';
</script>
</head>
<body>