<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = "/"
					+ request.getContextPath().substring(1).split("\\/")[0];
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" href="${path}/resources/styles/common.css" rel="stylesheet" />
</head>
<body>
	<div class="email-status">
		<c:out value="${requestScope.tips}" />
		<c:out value="${requestScope.email}" />
	</div>
</body>
</html>