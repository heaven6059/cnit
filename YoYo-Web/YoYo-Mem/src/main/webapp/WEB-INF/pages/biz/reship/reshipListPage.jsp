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
<title>售后申请记录</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/reship/reshipListPage.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
</head>
<body>
	<div class="member-main">
		<div>
			<div class="title title2">售后申请记录</div>

			<div>
				<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id = "tableId">
					<tbody>
						<tr>
							<th width="10%">申请编号</th>
							<th width="10%">订单号</th>
							<th width="35%">商品名称</th>
							<th width="10%">数量</th>
							<th width="10%">申请时间</th>
							<th width="15%">退货状态</th>
							<th width="10%">操作</th>
						</tr>
					</tbody>
					
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination"></div>
			</div>
		</div>
	</div>
</body>
</html>