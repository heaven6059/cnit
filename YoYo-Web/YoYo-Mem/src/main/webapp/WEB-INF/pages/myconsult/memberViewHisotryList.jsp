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
<title>浏览历史</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">浏览历史</div>
				<ul class="switchable-triggerBox clearfix">
					<li data-args="product" class="active">浏览的商品</li>
					<li data-args="store" class="">浏览的店铺</li>
				</ul>
			<div class="clr"></div>
			<div class="history_content_items">
				<div class="history_content">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="product_history_list">
						<tbody>
							<tr>
								<th width="5%"></th>
								<th width="40%" colspan="2">商品名称</th>
								<th width="15%">商品单价</th>
								<th width="15%">浏览时间</th>
								<th width="10%">操作</th>
							</tr>
						</tbody>
					</table>
					
				</div>
				<div class="history_content hide">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="sotre_history_list">
						<tbody>
							<tr>
								<th width="20"></th>
								<th width="80">店铺Logo</th>
								<th width="300">店铺名称</th>
								<th width="150">浏览时间</th>
								<th width="80">操作</th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="page clearfix">
				<div class="member-del">
					<input type="checkbox" id="del-all-box">&nbsp;&nbsp;全选&nbsp;&nbsp;
					<span class="trigger-btn" id="del-all-btn">删除</span>
				</div>
				<div id="Pagination" class="yoyoPagination"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/myconsult/memberViewHistory.js"></script>
</body>
</html>

