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
<title>店铺违规</title>
<link type="text/css"	href="${path}/resources/styles/shopMng/shopManager.css?v=${versionVal}"	rel="stylesheet" />
</head>
<body>
	<div class="shop_manager_right shop_manager2">
	 <div class="title " style="color:red;">${msg }</div>
	</div>
</body>
</html>