<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻详情</title>
<link href="${path}/resources/styles/news/detail.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="right-extra">
	<div id="detail" class="m">
	 <div class="mt"><h1>${news.title}</h1>
		<div class="summary">时间：<fmt:formatDate value="${news.pubtime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
	 </div>
	 <div class="mc">
	 	${news.newsContent.content}
	 </div>
	</div>
	<div id="review_top"></div>
	<div id="container"></div>
</div>
</body>
</html>