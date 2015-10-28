<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%
	response.setHeader("Access-Control-Allow-Origin", "*"); 
	String path = request.getContextPath();
	request.setAttribute("path", path);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	request.setAttribute("basePath", basePath);
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>
	<c:choose>
		<c:when test="${firstTimeFlag==true}">领取优惠券成功</c:when>
		<c:otherwise>已领取优惠券</c:otherwise>
	</c:choose>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<style type="text/css">
body{background:#d93900;padding:0;margin:0}
a{text-decoration:none}
.celebrate{text-align:center;height:150px;background:#d93900 url(${path}/resources/images/personalCenter/hb.gif) no-repeat center 40px}
.celebrate p{color:#fff;line-height:2.0em;padding:150px 0 20px 0}
.view_hb{color:#e3384a;font-size:2.0em;font-weight:bold;text-align:center;width:200px;height:60px;line-height:60px;margin:50px auto;display:block;background:#ffe971;-moz-border-radius:5px;-webkit-border-radius:5px;border-radius:5px}
</style>
</head>
<body>
	<div class="celebrate">
		<p>
			<span style="font-size:19px">
			<c:choose><c:when test="${firstTimeFlag==true}">恭喜！您已成功领取</c:when><c:otherwise>您已领过</c:otherwise></c:choose>优优车优惠券！</span><br/><br/>请前往"个人中心 > 我的优惠券"<br/>查看您的优惠券！
		</p>
		<a href="javascript:void(0)" class="view_hb" onclick="showMyWallet()">查看优惠券</a>
	</div>

<script type="text/javascript">
	function showMyWallet(){
		window.location.href = "${path}/mainController/queryMyRedPacket";
	}
</script>
</body>
</html>