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
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?r=${versionVal}" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">评论管理</div>
			<ul class="switchable-triggerBox clearfix">
				<li class="active">来自买家的评论</li>
			</ul>
			<div class="clr"></div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist">
				<tbody>
					<tr>
						<th>评论信息</th>
						<th>商品信息</th>
						<th>评论人</th>
						<th>操作</th>
					</tr>
				</tbody>
			</table>
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination" ></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?r=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/tradeMng/shopCommentMng.js?r=${versionVal}"></script>
</body>
</html>

