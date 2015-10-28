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
<script src="${path}/resources/scripts/common/yoyo.js"></script>


<script type="text/javascript">
$(document).ready(function(){

		 var _path="${path}";
		
		var loginUrl=yoyo.portalUrl+'/register/login';
		//买家注销登录
		var memurl = _path+'/mem/loginOut';
		var params = {};
		/* commonAjax(memurl, params, function(data) {
		}, function(data) {
		}); */
		$.ajax({
			url:memurl,
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
		var portalurl = yoyo.portalUrl+'/shiro/loginOut';
		var params = {};
		$.ajax({
			url:portalurl,
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