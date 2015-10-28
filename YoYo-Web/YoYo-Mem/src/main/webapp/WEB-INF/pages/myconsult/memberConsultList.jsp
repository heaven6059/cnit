<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论管理</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">我的咨询</div>
			<ul class="switchable-triggerBox clearfix">
				<li class="active">我的咨询</li>
			</ul>
			<div class="clr"></div>
			<div　class="mc ui-switchable-panel comments-table ui-switchable-panel-selected"　id="comment-0" style="display: block;">
				<div class="com-table-header">
					<span class="item column1" style="width:580px;">咨询内容</span> 
					<span class="item column2">咨询商品</span>
				</div>
				<div class="com-table-main">
				<!-- 评论内容 -->
				</div>
				<div class="com-table-footer">
					<div class="page clearfix">
						<div id="Pagination" class="yoyoPagination"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/myconsult/memberConsult.js"></script>
</body>
</html>

