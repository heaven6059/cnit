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
<title>我购买的虚拟商品</title>
<%@include file="/resources/include/head.jsp" %>
</head>
<body>
	<div class="member-main">
		<div>
			<div class="title title2">我购买的虚拟商品</div>
			<div>
				<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id = "tableId">
					<tbody>
						<tr>
							<th>订单号</th>
							<th>商品名称</th>
							<th>卡号</th>
							<th>密码</th>
							<th>发放时间</th>
						</tr>
					</tbody>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${path}/resources/scripts/biz/goodsvirtualitems/goodsVirtualItemsList.js?v=${versionVal}"></script>	
</body>
</html>