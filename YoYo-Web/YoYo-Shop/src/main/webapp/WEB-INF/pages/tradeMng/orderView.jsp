<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<link type="text/css" href="${path}/resources/styles/member.css?r=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/tradeMng/orderdetail.css?r=${versionVal}" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
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
										<c:if test="${order.status eq 'create'}">待付款</c:if>
										<c:if test="${order.status eq 'unconfirm'}">待确认</c:if>										
										<c:if test="${order.status eq 'confirm'}">已确认</c:if>										
										<c:if test="${order.status eq 'refunds'}">退款中</c:if>												
										<c:if test="${order.status eq 'finish'}">已完成</c:if>
										<c:if test="${order.status eq 'dead'}">已撤单</c:if>
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
												<td colspan="5">
												<c:choose>
												<c:when test="${order.status eq 'dead'}">订单已取消</c:when>
												<c:when test="${order.status eq 'refunds'}">退款中</c:when>
												<c:otherwise>
												<div class="section3" id="process">
								            		<div class="node fore ready">
														<ul>
															<li class="tx1">&nbsp;</li>
															<li class="tx2">提交订单</li>
															<li class="tx3" id="track_time_0"><fmt:formatDate value="${order.createtime}" pattern="yyyy-MM-dd"/><br><fmt:formatDate value="${order.createtime}" pattern="hh:mm:ss"/></li>
														</ul>
													</div>
						            				
													<div class="proce <c:choose><c:when test="${order.status eq 'unconfirm'||order.status eq 'confirm'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>"><ul><li class="tx1">&nbsp;</li></ul></div>
													<div class="node <c:choose><c:when test="${order.status eq 'unconfirm'||order.status eq 'confirm'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>">
														<ul>
															<li class="tx1">&nbsp;</li>
															<li class="tx2">等待确认</li>
															<li class="tx3" id="track_time_4"></li>
														</ul>
													</div>
													
													<div class="proce <c:choose><c:when test="${order.status eq 'confirm'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>"><ul><li class="tx1">&nbsp;</li></ul></div>
													<div class="node <c:choose><c:when test="${order.status eq 'confirm'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>">
														<ul>
															<li class="tx1">&nbsp;</li>
															<li class="tx2">已确认</li>
															<li class="tx3" id="track_time_1"></li>
														</ul>
													</div>
													
													<div class="proce <c:choose><c:when test="${order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>"><ul><li class="tx1">&nbsp;</li></ul></div>
													<div class="node <c:choose><c:when test="${order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>">
														<ul>
															<li class="tx1">&nbsp;</li>
															<li class="tx2">完成</li>
															<li class="tx3" id="track_time_6"></li>
														</ul>
													</div>
												</div>
												</c:otherwise>
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
											<td style="text-align: center;"><fmt:formatDate value="${orderLog.alttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td style="text-align: center;">${orderLog.logText}</td>
											<td style="text-align: center;">${orderLog.opName}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div style="display: none;" class="switchable-panel"></div>
					</div>
				</div>

				<div id="orderinfo">
					<div class="mt">
						<strong>订单信息</strong>
					</div>
					<div class="mc">
						<dl class="fore">
							<dt>客户信息</dt>
							<dd>
								<ul>
									<li>联&nbsp;系&nbsp;人：${order.member.name}</li>
									<li>手机号码：${order.member.mobile}</li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>支付方式</dt>
							<dd>
								<ul>
									<li>支付方式：${order.payment}</li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>订单备注</dt>
							<dd>
								<ul>
									<li>订单备注：${order.memo}</li>
								</ul>
							</dd>
						</dl>						
						<dl>
							<dt>商品清单</dt>
							<dd class="p-list">
								<table width="100%" cellspacing="0" cellpadding="0">
									<tbody>
										<tr>
											<th width="15%">商品图片</th>
											<th width="60%">商品名称</th>
											<th width="15%">商品单价</th>
											<th width="10%">商品数量</th>
										</tr>
										<c:forEach var="orderItem" items="${order.orderItems}">
										<tr>
											<td>
												<div class="img-list">
													<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.product.goodsId}" target="_blank" class="img-box"> 
														<img width="50" height="50" src="${imgUrl}${orderItem.product.picturePath}" />
													</a>
												</div>
											</td>
											<td>
												<div class="al fl">
													<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.product.goodsId}" target="_blank" class="flk13">${orderItem.product.name}</a>
												</div>
												<div class="clr"></div>
												<div class="fl" id="coupon_1234430319"></div>
											</td>
											<td><span class="ftx04">￥<fmt:formatNumber value="${orderItem.price}" pattern="#,#00.00#"/></span></td>
											<td>${orderItem.nums}</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</dd>
						</dl>
					</div>
					<!--金额-->
					<div class="total">
						<ul>
							<li><span>总商品金额：</span>￥<fmt:formatNumber value="${order.totalAmount}" pattern="#,#00.00#"/></li>
						</ul>
						<span class="clearfix"></span>
						<span style="color: #EDEDED;"></span>
						<div class="extra">
							应付总额：<span class="ftx04"><b>￥<fmt:formatNumber value="${order.finalAmount}" pattern="#,#00.00#"/></b></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

