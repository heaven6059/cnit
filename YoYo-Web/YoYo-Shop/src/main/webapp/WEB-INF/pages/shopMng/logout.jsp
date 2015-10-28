<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注销登录</title>
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?r=${versionVal}"></script>

<script type="text/javascript">
	$(function(){
		var loginUrl=yoyo.portalUrl+'/register/login';
		
		//卖家注销登录
		var _path="${path}";
		var shopurl = _path+'/shop/loginOut';
		var params = {};
		/* commonAjax(shopurl, params, function(data) {
		}, function(data) {
		}); */
		$.ajax({
			url:shopurl,
			data:params,
			type:"post",
			dataType:"json",
			async: false,
			success:function(returnData){
			},	
		   	error:function(){
		   	}
	 });
		
		//门户注销登录
		var url = yoyo.portalUrl+'/shiro/loginOut';
		var params = {};
		/* commonAjax(url, params, function(data) {
		}, function(data) {
		}); */
		
		$.ajax({
			url:url,
			data:params,
			type:"post",
			dataType:"json",
			async: false,
			success:function(returnData){
			},	
		   	error:function(){
		   	}
	 });
		
		window.location.href = loginUrl;
	});
</script>
</head>
<body>
</body>
</html>