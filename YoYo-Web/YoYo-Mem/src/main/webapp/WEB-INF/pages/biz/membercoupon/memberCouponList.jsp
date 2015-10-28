<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>

<c:set var="str" value="优惠券"/>
<c:if test="${type==2 }">
	<c:set var="str" value="代金券"/>
</c:if>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的${str}</title>
<link type="text/css" href="${path}/resources/styles/order/memberOrder.css" rel="stylesheet" />
</head>
<body>
<input type="hidden" id="type" value="${type }"/>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">我的${str}</div>
			<ul class="switchable-triggerBox clearfix">
				<li class="active">我的${str}</li>
			</ul>
			<div class="clr"></div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" style="border:none">
				<tbody>
					<tr>
						<th width="500px">${str}名称</th>
						<th width="300px">有效期</th>
						<th width="150px">操作</th>
					</tr>
				</tbody>
			</table>
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination" ></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/membercoupon/memberCouponList.js?v=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?v=${versionVal}"></script>
</body>
</html>