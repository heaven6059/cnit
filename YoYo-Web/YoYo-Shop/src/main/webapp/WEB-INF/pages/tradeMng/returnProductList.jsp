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
<title>退换货管理</title>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?r=${versionVal}" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">退换货管理</div>
			<ul class="switchable-triggerBox clearfix">
				<li class="active">我收到的退货</li>
			</ul>
			<div class="clr"></div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist">
				<tbody>
					<tr>
						<th>订单号</th>
						<th>申请人</th>
						<th>申请时间</th>
						<th>处理状态</th>
						<th>操作</th>
					</tr>
				</tbody>
				<c:forEach var="returnProduct" items="${returnProducts.rows}">
					<tr>
						<td><a class="font-blue" href="../shopOrder/viewOrder?orderid=${returnProduct.orderId}" target="_blank">${returnProduct.orderId}</a></td>
					    <td class="textcenter">${returnProduct.order.member.name}</td>
						<td class="textcenter"><fmt:formatDate value="${returnProduct.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td class="textcenter">${returnProduct.refundText} </td>
					    <td class="textcenter">
					    	<c:if test="${returnProduct.status eq 1}">
							<a class="font-blue" href="../returnProductManager/toProcessReturnProduct?returnId=${returnProduct.returnId}&isSafeguard=${returnProduct.isSafeguard}">处理申请</a>
							</c:if>
							<a class="font-blue" href="../returnProductManager/toReturnProductView?returnId=${returnProduct.returnId}&isSafeguard=${returnProduct.isSafeguard}">查看</a>
						</td>
					</tr>
				</c:forEach>				
			</table>
			<form action="../returnProductManager/toReturnProductList" id="return_product_form" method="post">
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination"></div>
				<input type="hidden" id="total" name="total" value="${returnProducts.total}"/>
				<input type="hidden" id="rows" name="rows" value="${returnProducts.pageSize}"/>
				<input type="hidden" id="pageIndex" value="${returnProducts.pageIndex}"/>
			</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?r=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/tradeMng/shopReturnProductMng.js?r=${versionVal}"></script>
</body>
</html>

