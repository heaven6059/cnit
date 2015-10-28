<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<style type="text/css">
.member-main {
    background: #fff none repeat scroll 0 0;
    float: left;
    padding-left: 200px;
    width: 1200px;
}
.footer_t_l {
    border-right: 1px solid #dadada;
    height: 160px;
    padding: 15px 0 0 40px;
    position: relative;
    width: 164px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<link type="text/css" href="${path}/resources/styles/order/memberOrder.css" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2" style="float: left;padding-left: 200px;width: 1200px;">
		<div style="height: auto">
			<div class="title">订单信息</div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" style="margin: 10px 0 20px 0" class="order-info">
				<tbody>
					<tr>
						<!-- order status -->
						<td width="30%" valign="top">
							<ul>
								<li>订单号：<span class="price-normal">${order.orderId}</span></li>
								<li>订单金额：<span class="point">￥<fmt:formatNumber value="${order.finalAmount}" pattern="#,#00.00#"/></span></li>
								<li>订单状态：
									<span class="siteparttitle-blue">
										<c:if test="${order.payStatus eq 0|| order.payStatus eq 500}">
											未付款<c:if test="${order.status eq 'dead'}">[已撤单]</c:if>
										</c:if>										
										<c:if test="${order.payStatus eq '1' && order.status eq 'uninstall'}">已支付[未安装]</c:if>
										<c:if test="${order.payStatus eq '1' && order.status eq 'install'}">已支付[已安装]</c:if>
										<c:if test="${order.payStatus eq '1' && order.status eq 'finish'}">已支付[已完成]</c:if>												
										<c:if test="${order.payStatus eq '1' && order.status eq 'dead'}">已支付[已撤单]</c:if>
									</span>
								</li>
							</ul>
						</td>
						<!-- order action -->
					</tr>
				</tbody>
			</table>
			<!-- common order info -->
			<div style="border: none" class="order-track">
				<div class="switch" id="order_des">
					<ul class="switchable-triggerBox clearfix">
						<li class="active">订单追踪</li>
					</ul>
					<div class="switchable-content">
						<div class="switchable-panel">
							<div class="box">
								<div class="flow">
									<table width="100%" cellspacing="0" cellpadding="0" border="0">
										<tbody>
											<tr>
												<td class="box-td <c:if test="${order.payStatus eq 0 || order.payStatus eq 500}">point</c:if>">提交订单</td>
												<td class="box-td <c:if test="${order.payStatus eq 1 && order.status eq 'create'}">point</c:if>">等待确认</td>												
												<td class="box-td <c:if test="${order.payStatus eq 1 && order.status eq 'uninstall'}">point</c:if>">捡配货物</td>
												<td class="box-td <c:if test="${order.payStatus eq 1 && order.status eq 'install'}">point</c:if>">维修中</td>
												<td class="box-td <c:if test="${order.payStatus eq 1 && order.status eq 'finish'}">point</c:if>">维修完成</td>
											</tr>
											<tr>
												<td class="flow-bg" colspan="5">
												<c:choose>
													<c:when test="${order.payStatus eq 'create'|| order.payStatus eq 0}"><div class="flow-bg bg1"></div></c:when>
													<c:when test="${order.payStatus eq 1 && order.status eq 'create'}"><div class="flow-bg bg2"></div></c:when>
													<c:when test="${order.payStatus eq 1 && order.status eq 'uninstall'}"><div class="flow-bg bg3"></div></c:when>
													<c:when test="${order.payStatus eq 1 && order.status eq 'install'}"><div class="flow-bg bg4"></div></c:when>
													<c:when test="${order.payStatus eq 1 && order.status eq 'finish'}"><div class="flow-bg bg4"></div></c:when>													
												</c:choose>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								
								<table width="100%" cellspacing="0" cellpadding="0">
									<tbody id="tbody_track">
										<tr>
											<th width="15%"><strong>处理时间</strong></th>
											<th width="50%"><strong>处理信息</strong></th>
											<th width="35%"><strong>操作人</strong></th>
										</tr>
									</tbody>
									<tbody>
									<c:forEach var="orderLog" items="${order.orderLogs}">
										<tr>
											<td><fmt:formatDate value="${orderLog.alttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td>${orderLog.logText}</td>
											<td>${orderLog.opName}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div style="display: none;" class="switchable-panel"></div>
					</div>
				</div>

				<div class="cart-wrap ">
					<div class="FormWrap gift-bag order-trace">
						<!-- <h4>商品</h4> -->
						<table width="100%" cellspacing="0" cellpadding="3"
							class="gridlist">
							<colgroup>
								<col class="span-auto">
								<col class="span-3">
								<col class="span-4">
							</colgroup>
							<tbody>
								<tr>
									<th>商品</th>
									<th>数量</th>
									<th>金额小计</th>
								</tr>
							</tbody>
							<tbody>
							<c:forEach var="orderItem" items="${order.orderItems}">
								<tr>
									<td>
										<div class="clearfix horizontal-m">
											<div style="width: 80px; height: 80px; margin: 0 5px" class="product-list-img goodpic">
												<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.product.goodsId}" target="_blank" style="border: 0px none;">
													<img  src="${orderItem.product.picturePath}" width="80px" height="80px"/>
												</a>
											</div>
											<div class="goods-main">
												<div class="goodinfo" style="width: 50%">
													<h3><a href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.product.goodsId}" class="font-blue" target="_blank">${orderItem.product.name}</a></h3>
												</div>
											</div>
										</div>
									</td>
									<td class="textcenter vm">${orderItem.nums}</td>									
									<td class="textcenter vm font-orange">￥<fmt:formatNumber value="${orderItem.price}" pattern="#,#00.00#"/></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>

						<h4>收货信息</h4>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="takegoods">
							<tbody>
								<tr>
									<th>联系人：</th>
									<td>${order.member.name}</td>
								</tr>
								<tr>
									<th>联系电话：</th>
									<td>${order.member.mobile}</td>
								</tr>
							</tbody>
						</table>						
						<h4>支付方式</h4>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="takegoods">
							<tbody>
								<tr>
									<td style="padding-left: 40px">${order.payment}</td>
								</tr>
							</tbody>
						</table>
						<h4>订单备注</h4>
						<table width="100%" cellspacing="0" cellpadding="0" border="0"
							class="takegoods">
							<tbody>
								<tr>
									<td style="padding-left: 40px">${order.markText}</td>
								</tr>
							</tbody>
						</table>
						
						<h4>店铺地址</h4>
						<table width="100%" cellspacing="0" cellpadding="0" border="0"
							class="takegoods">
							<tbody>
								<tr>
									<td style="padding-left: 40px">${order.store.addr}</td>
								</tr>
							</tbody>
						</table>

						<h4>结算信息</h4>
						<table width="100%" cellspacing="0" cellpadding="0" border="0"
							style="border: none" class="liststyle data">
							<colgroup>
								<col width="88%">
								<col width="12%">
							</colgroup>
							<tbody>
								<tr>
									<th>商品总金额:</th>
									<td class="font14px font-orange">￥<fmt:formatNumber value="${order.totalAmount}" pattern="#,#00.00#"/></td>
								</tr>
								<tr>
									<th>订单总金额:</th>
									<td class="font16px font-orange fontbold">￥<fmt:formatNumber value="${order.finalAmount}" pattern="#,#00.00#"/></td>
								</tr>
							</tbody>
						</table>
						<!-- order info end -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function (){
			$(".product-list-img a img").each(function (x,item){
				$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
			});
		});
	</script>
</body>
</html>

