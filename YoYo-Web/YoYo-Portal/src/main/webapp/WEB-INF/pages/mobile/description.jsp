<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>红包使用规则</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<style type="text/css">
.celebrate{width:100%;height:350px;}
.celebrate span{font-size:14px;line-height:1.3em}
</style>
</head>
<body style="padding:0;margin:10px">
	<div style="width:100%;height:100%;">
		<div>
			<img alt="" src="${path}/resources/images/description/description.jpg" width="100%" height="240px">
		</div>
		<div class="celebrate">
			
				<p style="font-size:16px;padding-top:5px">红包使用规则：</p>
				<span>1.领红包时填写的手机号即为您登录淘屏网的账号;</span><br/>
				<span>2.初始密码为您领红包时填写的手机号码;</span><br/>
				<span>3.发放至淘屏网账户的红包登陆后即可使用;</span><br/>
				<span>4.红包额度为100元，不限使用次数;</span><br/>
				<span>5.红包使用期限至2015年9月29日;</span><br/>
				<span>6.本次活动最终解释权归淘屏网所有;</span><br/>
				<span>（淘屏网www.taoping.cn）</span><br/>
				<br/>
				<span>点击<a href="http://www.taoping.cn/ordermain/skipdownload/index.html">下载淘屏APP</a>，开启您的淘屏之旅！</span>
			
		</div>
	</div>
</body>
</html>