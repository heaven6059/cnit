<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注销登录</title>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>

<script type="text/javascript">
	$(function(){
		var _path="${path}";
		
		var loginUrl=_path+'/register/login';
		//卖家注销登录
		var shopurl = yoyo.shopUrl+'/shop/loginOut';
		var params = {};
		commonAjax(shopurl, params, function(data) {
		}, function(data) {
		});
		
		//买家注销登录
		var memurl = yoyo.memUrl+'/mem/loginOut';
		var params = {};
		commonAjax(memurl, params, function(data) {
		}, function(data) {
		});
		
		//门户注销登录
		var url = _path+'/shiro/loginOut';
		var params = {};
		commonAjax(url, params, function(data) {
		}, function(data) {
		});
		
		window.location.href = loginUrl;
	});
</script>
</head>
<body>
</body>
</html>