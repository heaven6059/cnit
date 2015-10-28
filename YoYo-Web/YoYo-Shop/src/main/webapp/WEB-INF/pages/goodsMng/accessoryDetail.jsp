<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>适用车型</title>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript"  src="${path}/resources/scripts/biz/goodsMng/accessoryDetail.js?r=${versionVal}"></script>
</head>
<body>

	<div class="member-main2">
		<div class="orderlist-warp">
			<div class="title">适用车型</div>
			<div class="clr"></div>
			
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist">
				<tbody>
					<tr>
						<th width="30%" style="border-left: 1px solid #f2f2f2;">厂商</th>
						<th width="25%">车系</th>
						<th width="15%">年款</th>
						<th width="30%">车型</th>
					</tr>
					<input type="hidden" id="accId" value="${accId }">
				</tbody>
				<c:forEach var="car" items="${cars.rows}">
				<tbody>
					
					<tr>						
						<td class="product_name">
							${car.factoryName }
						</td>	
						<td class="product_name">
							${car.deptName }
						</td>
						<td class="product_name">
							${car.yearValue }
						</td>
						<td class="product_name">
							${car.carName }
						</td>
					</tr>
				</tbody>
				</c:forEach>				
			</table>
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?t=${time}"></script>
	<script type="text/javascript">
		total="${cars.total}";rows="${cars.pageSize}";
	</script>
</body>
</html>

