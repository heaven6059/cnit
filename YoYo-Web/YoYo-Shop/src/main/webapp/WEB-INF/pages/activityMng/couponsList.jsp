<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>优惠卷管理</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
</head>
<body>
<div class="member-main">
	<div class="title title2">店铺<c:if test="${t==1}">优惠券</c:if><c:if test="${t==2}">代金券</c:if></div>
	<div >
	<input type="hidden" id="type" value="${t }"/>
	 <div  style="margin-top: 10px; border:none;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;">
		<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id ="tableList" style="border: 1px solid #ddd;text-align:center;">
			<tr>
				<th>号码</th>
				<th>名称</th>
				<th>类型</th>
				<th>获取的总数量</th>
				<th>发行类型</th>
				<th>启用状态</th>
				<th>线上发放数</th>
				<th>线上领取数</th>
				<th>每人限量</th>
				<th style="width:150px;">操作</th>
			</tr>
		</table>
	</div>
	<div class="page clearfix">
		<div id="Pagination" class="yoyoPagination"></div>
	</div>
	</div>
</div>
<script type="text/javascript" src="${path}/resources/scripts/biz/activityMng/couponsList.js?r=${versionVal}"></script>
</body>
</html>

