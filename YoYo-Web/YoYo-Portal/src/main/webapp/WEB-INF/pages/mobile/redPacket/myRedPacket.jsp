<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的红包</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<style type="text/css">
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,img,legend,input,textarea,p,em,i,blockquote,th,td{margin:0;padding:0;border:0;outline:0;font-weight:normal;font-style:inherit;font-size:12px}
body{background:#f1f1f1;padding:0;margin:0}
.hb_c{height:160px;background:#fff;}
.hb{height:120px;background:#d93900 url(${path}/resources/images/personalCenter/hb.gif) no-repeat 15px center}
.time{height:40px;line-height:40px;color:#999;font-size:16px;padding:0 20px}
.hb dl{color:#fff;padding:25px 0 0 120px}
.hb dl dt{font-size:3em;margin-bottom:10px}
.hb dl dd{color:#f5f5f5;font-size:16px;padding-left:10px}
.celebrate{width:100%;height:350px;}
.celebrate p{line-height:1.3em;padding:30px 0 20px 10px;font-size:14px}
</style>
</head>
<body>
	<div style="width:100%;padding:10px;">
		<p style="font-size:16px">当前登录用户：${userMobile}</p>
	</div>
	<div class="hb_c">
		<c:choose>
			<c:when test="${isSuccess==true}">
				<div class="hb">
					<dl>		
						<dt>￥<fmt:formatNumber type="number" value="${money}" pattern="0.00" maxFractionDigits="2" /></dt>
						<dd>您的红包余额</dd>
					</dl>	
				</div>
				<div class="time">有效期至：2015-09-29</div>
			</c:when>
			<c:otherwise>
				<div class="hb">
					<dl>		
						<dt>￥0.00</dt>
						<dd></dd>
					</dl>	
				</div>
				<div class="time">查询失败,请稍后重试!</div>
			</c:otherwise>
		</c:choose>		
	</div>
	<div class="celebrate">
		<p>
			<span style="font-size:16px">红包使用规则:</span>
			<br/><br/>
			1.领红包时填写的手机号即为您登录淘屏网的账号;<br/>
			2.初始密码为您领红包时填写的手机号码;<br/>
			3.发放至淘屏网账户的红包登陆后即可使用;<br/>
			4.红包额度为100元，不限使用次数;<br/>
			5.红包使用期限至2015年9月29日;<br/>
			6.本次活动最终解释权归淘屏网所有;<br/>
			（淘屏网www.taoping.cn）<br/>
			<br/>
			点击<a href="http://www.taoping.cn/ordermain/skipdownload/index.html">下载淘屏APP</a>，开启您的淘屏之旅！
		</p>
	</div>
</body>
</html>