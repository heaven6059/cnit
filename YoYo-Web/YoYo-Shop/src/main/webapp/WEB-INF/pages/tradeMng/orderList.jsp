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
<title>订单管理</title>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?t=${time}" rel="stylesheet" />
<style>
select{outline:none;}select:hover{outline:#fff solid 1px;} select:focus{outline:#fff solid 1px;}
</style>
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">我的订单</div>

			<div class="Plate">
				<h4>
					订单状态：
					<a <c:if test="${empty orderQry.status}">class="current"</c:if> args="all" href="javascript:void(0);">全部</a>  
					<a args="nopayed" <c:if test="${orderQry.status eq 'create'}">class="current"</c:if> href="javascript:void(0);">待付款</a> 
					<a args="unconfirm" <c:if test="${orderQry.status eq 'unconfirm'}">class="current"</c:if> href="javascript:void(0);">待确认</a> 
					<a args="confirm" <c:if test="${orderQry.status eq 'confirm'}">class="current"</c:if> href="javascript:void(0);">已确认</a>
					<a args="finish" <c:if test="${orderQry.status eq 'finish'}">class="current"</c:if> href="javascript:void(0);">已完成</a> 
					<a args="dead" <c:if test="${orderQry.status eq 'dead'}">class="current"</c:if> href="javascript:void(0);">已撤单</a>
				</h4>
			</div>
			<div class="clr"></div>
			<div class="lineh b4">
				订单号：<input maxlength="18" type="text" vtype="text" id="search_order_id" size="30" value="" name="" class="x-input " autocomplete="off" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"> 
				商品名称:<input maxlength="30" type="text" vtype="text" id="search_goods_name" size="30" value="" name="" class="x-input " autocomplete="off"> 
					<select class=" x-input-select inputstyle" required="1" name="order_time" id="order_time" type="select" style="outline:none">
						<option value="">全部时间</option>
						<option value="3th">三个月内</option>
						<option value="6th">半年内</option>
						<option value="13Y">1年以前</option>
					</select>
				<button id="btnsearch" class="btn search1" type="button">
					<span><span>搜索</span></span>
				</button>
				<input type="hidden" id="pay_status" value="${orderQry.payStatus}" name="type" class="x-input " autocomplete="off">
				<input type="hidden" id="status" value="${orderQry.status}" name="goods_name" class="x-input " autocomplete="off">
			</div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="shop_order_list">
				<tbody>
					<tr>
						<th width="290" colspan="3">订单商品</th>
						<th width="98">订单金额</th>
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
					<c:forEach var="orderItem" items="${order.orderItems}" varStatus="status">
					<tr>						
						<td class="product">
							<img src="${path}/resources/images/tm.png" data-original="${orderItem.product.pictures[0].picturePath}" width="80px" height="80px"/>
						</td>
						<td class="product_name">
							<label class="col1">${orderItem.product.name}</label>
							<label class="col2">￥<fmt:formatNumber value="${orderItem.price}" pattern="#,#00.00#"/></label>
							<label class="col3">&nbsp;&nbsp;数量:${orderItem.nums}</label>
						</td>						
						<c:if test="${status.first}">
							<td rowspan="${fn:length(order.orderItems)}">￥<fmt:formatNumber value="${order.finalAmount}" pattern="#,#00.00#"/></td>
							<td rowspan="${fn:length(order.orderItems)}"><fmt:formatDate value="${order.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td rowspan="${fn:length(order.orderItems)}">${order.member.name}</td>					
							<td rowspan="${fn:length(order.orderItems)}" class="textcenter">
							<div style="width: 100px; display: block; margin: 0 auto;">未支付</div>
							</td>
							<td rowspan="${fn:length(order.orderItems)}">
								<a class="font-blue operate-btn" href="../tradeManager/viewOrder?orderid=${order.orderId}">查看订单</a> 
								<a class="font-blue operate-btn" href="../tradeManager/doCanelOrder?orderid=${order.orderId}">取消订单</a>
								<a class="font-blue operate-btn" target="_blank" href="../tradeManager/printOrder?orderid=${order.orderId}">打印订单</a>
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
	<div id="salesConsultant" style="display: none;">
		<div style="margin: 0 auto;width: 370px;text-align:center;padding:30px 0px">
			<div>
				<h1 style="  font-size: 14px;font-style: initial;font-weight: 700;">销售顾问信息(选填)</h1>
			</div>
			<div style="padding: 5px 0px">
				<p style="width: 100px;padding: 4px 2px;text-align: right;" class="fl">姓名：</p>
				<p><input type="text" class="x-input fl" style="width: 160px" maxlength="10" id="sale-name"/></p>
			<div class="clearfix"></div>
			</div>
			<div style="padding: 5px 0px">
				<p style="width: 100px;padding: 4px 2px;text-align: right;" class="fl">联系方式：</p>
				<p><input type="text" class="x-input fl" style="width: 160px" maxlength="11" id="sale-phone"/></p>
				<div class="clearfix"></div>
			</div>
			<div style="padding: 10px 0px">
				<input type="button" class="btn confirm-order" value="确认" />
				<input type="button" class="btn cancel-order" value="取消" />
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/tradeMng/orderMng.js?r=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
	<script type="text/javascript">
		total="${orders.total}",rows="${orders.pageSize}";
	</script>
</body>
</html>


