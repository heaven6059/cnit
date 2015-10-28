<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的关注</title>
<link type="text/css" href="${path}/resources/styles/order/memberOrder.css" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">我的关注</div>
			<ul class="switchable-triggerBox clearfix">
				<li class="active">我关注的商品</li>
				
			</ul>
			<div class="clr"></div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" style="border:none">
				<tbody>
					<tr>
						<th width="30px">&nbsp;</th>
						<th width="50px">&nbsp;</th>
						<th width="350px">商品名称</th>
						<th width="100px">商品单价</th>
						<th width="100px">收藏时间</th>
						<th width="100px">操作</th>
					</tr>
				</tbody>
			</table>
			<div class="page clearfix">
				<div class="member-del">
					<input type="checkbox" id="del-all-box">&nbsp;&nbsp;全选&nbsp;&nbsp;
					<a class="trigger-btn" id="del-all-btn">删除</a>
				</div>
				<div id="Pagination" class="yoyoPagination" ></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/wishlist/wishlist.js?t=${time}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?t=${time}"></script>
</body>
</html>

