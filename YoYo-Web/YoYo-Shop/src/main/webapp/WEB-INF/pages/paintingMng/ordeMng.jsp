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
<title>订单管理</title>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?v=${versionVal}" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">钣金订单管理</div>
				<div id="content">
					<div class="Plate">
						<h4>
							订单状态：
							<a class="current" args="all" href="javascript:void(0);">全部</a>  
							<a args="nopayed" href="javascript:void(0);">待付款</a> 
							<a args="unconfirm" href="javascript:void(0);">待确认</a> 
							<a args="uninstall" href="javascript:void(0);">待安装</a>
							<a args="install" href="javascript:void(0);">已安装</a>
							<a args="finish" href="javascript:void(0);">已完成</a> 
							<a args="dead" href="javascript:void(0);">已取消</a>
						</h4>
					</div>
					<div class="clr"></div>
					<div class="lineh b4">
						订单号：<input maxlength="30" type="text" vtype="text" id="search_order_id" size="30" value="" name="" class="x-input " autocomplete="off" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
						<select class=" x-input-select inputstyle" required="1" name="order_time" id="order_time" type="select">
							<option value="0">全部时间</option>
							<option value="3">三个月内</option>
							<option value="6">半年内</option>
							<option value="12">今年内</option>
							<option value="13">1年以前</option>
						</select>
						<button id="btnsearch" class="btn search1" type="button">
							<span><span>搜索</span></span>
						</button>
						<input type="hidden" id="pay_status" value="" name="type" class="x-input " autocomplete="off">
						<input type="hidden" id="status" value="" name="goods_name" class="x-input " autocomplete="off">
					</div>
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="shop_order_list">
						<tbody>
							<tr>
								<th width="290" colspan="2">订单商品</th>
								<th width="98">订单金额</th>
								<th width="80">下单日期</th>
								<th width="80">买家</th>
								<th width="100">订单状态</th>
								<th width="80">操作</th>
							</tr>
						</tbody>
						<c:forEach var="order" items="${orders.rows}">
							<tbody>
								<tr class="tr-th">
									<td colspan="7">
									<span class="tcol1">
										订单号:<a href="">${order.orderId}</a>
									</span>
									</td>
								</tr>
								<c:forEach var="orderItem" items="${order.damageOfferList}" varStatus="status">
								<tr>						
									<td class="product">
										<img src="${path}/resources/images/tm.png" data-original="${orderItem.product.pictures[0].picturePath}" width="80px" height="80px"/>
									</td>
									<td class="product_name">
										<label class="col1">${orderItem.product.name}</label>
										<label class="col2">￥<fmt:formatNumber value="${orderItem.price}" pattern="#,#00.00#"/></label>
									</td>						
									<c:if test="${status.first}">
										<td rowspan="${fn:length(order.damageOfferList)}">￥<fmt:formatNumber value="${order.payed}" pattern="#,#00.00#"/></td>
										<td rowspan="${fn:length(order.damageOfferList)}"><fmt:formatDate value="${order.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td rowspan="${fn:length(order.damageOfferList)}">${order.member.name}</td>					
									<td rowspan="${fn:length(order.damageOfferList)}" class="textcenter">
										<div style="width: 100px; display: block; margin: 0 auto;">未支付</div>
									</td>
									<td rowspan="${fn:length(order.damageOfferList)}">
										<a class="font-blue operate-btn" href="../paintingManager/viewOrder?orderid=${order.id}">查看订单</a> 
										<a class="font-blue operate-btn" href="../paintingManager/doCanelOrder?orderid=${order.id}">取消订单</a>
										<a class="font-blue operate-btn" target="_blank" href="../paintingManager/printOrder?orderid=${order.id}">打印订单</a>
									</td>		
								</c:if>
							</tr>
							</c:forEach>
						</tbody>
					</c:forEach>				
				</table>
				<div class="page clearfix">
					<div id="Pagination" class="yoyoPagination"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/paintingMng/orderMng.js?v=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?v=${versionVal}"></script>
	<script type="text/javascript">
		total="${orders.total}",rows="${orders.pageSize}";
	</script>
</body>
</html>