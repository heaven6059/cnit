<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约试驾</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/callcenter/reservationDrive.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
</head>
<body>
	<div class="member-main">
		<div class="title title2">预约试驾</div>
		<div  style="margin-top: 10px; border:none;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;">
			<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" style="border: 1px solid #ddd;">
				<tr>
					<th>预约车型</th>
					<th>预约人姓名</th>
					<th>预约人性别</th>
					<th>预约人手机</th>
					<th>预约人地域</th>
					<th>预约时间</th>
				</tr>
			</table>
		</div>
		<div class="page clearfix">
			<div id="Pagination" class="yoyoPagination"></div>
		</div>
	</div>
	<div class="clearfix"></div>
</body>
</html>