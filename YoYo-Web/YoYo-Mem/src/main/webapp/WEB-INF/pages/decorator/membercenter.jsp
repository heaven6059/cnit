<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.cnit.yoyo.util.Configuration" %>
<%@page import="com.cnit.yoyo.util.CommonUtil" %>  
<%
    String path = "/"+ request.getContextPath().substring(1).split("\\/")[0];
	request.setAttribute("path", path);
	application.setAttribute("memPath", Configuration.getInstance().getConfigValue("mem.url"));
	application.setAttribute("shopPath", Configuration.getInstance().getConfigValue("shop.url"));
	application.setAttribute("imgUrl", Configuration.getInstance().getConfigValue("images.url"));
	application.setAttribute("portalPath", Configuration.getInstance().getConfigValue("portal.url"));
	application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title><sitemesh:write property='title' /></title>
<script type="text/javascript" src="${portalPath}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${portalPath}/resources/scripts/cookie/jquery.cookie.js"></script>
<script type="text/javascript" src="${portalPath}/resources/scripts/placeholder/jquery.enplaceholder.js"></script>
<script type="text/javascript" src="${portalPath}/resources/scripts/layer1.9.3/layer.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/scripts/easyui/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/scripts/bxslider/jquery.bxslider.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${portalPath}/resources/styles/base.css?v=${versionVal}" />
<link rel="stylesheet" type="text/css" href="${portalPath}/resources/styles/common.css?v=${versionVal}" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods.css?v=${versionVal}" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/buyYoYo.css?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>

<sitemesh:write property='head' />
</head>
<body>
	<%-- <div class="wrap">
		<jsp:include page="./statusBar.jsp" />
		<jsp:include page="../index/head.jsp" />
		<jsp:include page="./left.jsp" />
		<div class="clearfix">
			<sitemesh:write property='body' />
		</div>
		<jsp:include page="./footer.jsp" />
	</div> --%>
	
	<div class="header">
		<jsp:include page="../index/head.jsp" />
		<jsp:include page="../index/menu.jsp" />
	</div>
	<!-- <div style="width:1200px;height:1200px;padding-left:355px;padding-top:10px;"> -->
	<div class="add_con clearfix">
		<jsp:include page="./left.jsp" />
		<%-- <div class="buy_main fl">
			<jsp:include page="../index/personcenter.jsp" />
			<div class="buy_con">
				<sitemesh:write property='body' />
			</div>
		</div> --%>
		<sitemesh:write property='body' />
		<%-- <jsp:include page="../index/helpcenter.jsp" /> --%>
	</div>
	<div class="footer">
		<jsp:include page="./footer.jsp" />
	</div>
	
</body>
</html>