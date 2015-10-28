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
			<div class="title">店铺商品历史</div>
				<ul class="switchable-triggerBox clearfix">
					<li data-args="product" class="active">浏览的商品</li>
				</ul>
			<div class="clr"></div>
			<div class="history_content_items">
				<div class="history_content">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="product_history_list">
						<tbody>
							<tr>
								<th width="500px" colspan="2">商品名称</th>
								<th width="120px">商品单价</th>
								<th width="150px">浏览时间</th>
								<th width="200px">浏览人</th>
							</tr>
							<c:forEach items="${ProductsView.rows}" var="content">
							<tr>
								<td>
									<img width="50" height="50" src="${imgUrl}${content.picturePath}" />
								</td>
								<td style="text-align:left;">
									<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${content.goodsId}">${content.productName}</a>
								</td>
								<td>￥${content.price}</td>
								<td>${content.viewDate}</td>								
								<td>
									<div style="width: 200px;word-break:break-all; overflow: auto;">${content.loginName}</div>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="page clearfix">				
						<div id="Pagination" class="yoyoPagination" ></div>
					</div>
				</div>
			</div>
		</div> 
	</div>
	<script type="text/javascript">var total=${ProductsView.total};</script>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/focusCenter/viewProductList.js"></script>
</body>
</html>

