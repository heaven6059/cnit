<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>支付</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="imagetoolbar" content="no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
	<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
    <script src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
	<link href="${path}/resources/styles/cart/base.css" type="text/css" rel="stylesheet">
<%-- 	<link href="${path}/resources/styles/cart/cart.css" type="text/css" rel="stylesheet">
	<link href="${path}/resources/styles/cart/cart2.css" type="text/css" rel="stylesheet"> --%>
	<%-- <link href="${path}/resources/styles/cart/addSuccess.css" type="text/css" rel="stylesheet"> --%>
	<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
	<script type="text/javascript">var _path="${path}"</script>
<%-- 	<script src="${path}/resources/scripts/biz/cart/cartIndex.js"></script> --%>
	
</head>
<body>

<div id="main_container" class="cart root61" style="width: 1200px !important; margin:auto; width: auto; height: auto;">
	<div style="line-height: 100px;text-align: center;font-size: 20px;color: gray;margin-top: 50px;">
	     去付款？开发中，敬请期待……
	</div>
</div>
</body>
