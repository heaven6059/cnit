<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.cnit.yoyo.util.Configuration" %>  
<%
    String path = "/"+ request.getContextPath().substring(1).split("\\/")[0];
	request.setAttribute("path", path);
	application.setAttribute("imgUrl", Configuration.getInstance().getConfigValue("images.url"));
	application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
	application.setAttribute("portalPath", Configuration.getInstance().getConfigValue("portal.url"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><sitemesh:write property='title' /></title>
<link rel="stylesheet" type="text/css" href="${portalPath}/resources/styles/base.css?r=${versionVal}">
<link rel="stylesheet" type="text/css" href="${portalPath}/resources/styles/common.css?r=${versionVal}">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/sellYoYo.css?r=${versionVal}">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/buyYoYo.css?r=${versionVal}">


<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/placeholder/jquery.enplaceholder.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?r=${versionVal}"></script>

<link type="text/css" href="${path}/resources/scripts/easyui/themes/bootstrap/easyui.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/scripts/easyui/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/scripts/bxslider/jquery.bxslider.css" rel="stylesheet" />
<script type="text/javascript">
	var _path = "${path}";
</script>
<sitemesh:write property='head' />
</head>
<body>
	<div class="header">
		<jsp:include page="../index/head.jsp" />
		<jsp:include page="../index/menu.jsp" />
	</div>
	<div class="add_con clearfix">
		<jsp:include page="./left.jsp" />
		<sitemesh:write property='body' />
	</div>
	<div class="footer">
		<jsp:include page="./footer.jsp" />
	</div> 
</body>
</html>