<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>钣金订单详情</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/order/orderdetail.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/sellYoYo.css" />
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/painting/paintingUtil.js"></script>
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
								<li>订单号：<span class="price-normal">${order.id}</span></li>
								<li>订单金额：<span class="point">￥<fmt:formatNumber value="${order.payed}" pattern="#,#00.00#"/></span></li>
								<li>订单状态：
									<span class="siteparttitle-blue">
										<c:if test="${order.status eq 'create' && order.paymentId eq '0'}">待付款</c:if>
										<c:if test="${order.status eq 'create' && order.paymentId eq '1'}">待确认</c:if>										
										<c:if test="${order.status eq 'unconfirm'}">待确认</c:if>										
										<c:if test="${order.status eq 'uninstall'}">未安装</c:if>
										<c:if test="${order.status eq 'install'}">已安装</c:if>
										<c:if test="${order.status eq 'finish'}">已完成</c:if>												
										<c:if test="${order.status eq 'dead'}">已取消</c:if>
									</span>
								</li>
							</ul>
						</td>
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
															<div class="section4" id="process">
											            		<div class="node fore ready">
																	<ul>
																		<li class="tx1">&nbsp;</li>
																		<li class="tx2">提交订单</li>
																		<li class="tx3" id="track_time_0"><fmt:formatDate value="${order.createtime}" pattern="yyyy-MM-dd"/><br><fmt:formatDate value="${order.createtime}" pattern="hh:mm:ss"/></li>
																	</ul>
																</div>
																<div class="proce <c:choose><c:when test="${(order.status eq 'create' && order.paymentId eq '1') || order.status eq 'unconfirm'||order.status eq 'uninstall'||order.status eq 'install'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>">
																	<ul><li class="tx1">&nbsp;</li></ul>
																</div>
																<div class="node <c:choose><c:when test="${(order.status eq 'create' && order.paymentId eq '1') || order.status eq 'unconfirm'||order.status eq 'uninstall'||order.status eq 'install'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>">
																	<ul>
																		<li class="tx1">&nbsp;</li>
																		<li class="tx2">等待确认</li>
																		<li class="tx3" id="track_time_4"></li>
																	</ul>
																</div>
																
																<div class="proce <c:choose><c:when test="${order.status eq 'uninstall'||order.status eq 'install'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>"><ul><li class="tx1">&nbsp;</li></ul></div>
																<div class="node <c:choose><c:when test="${order.status eq 'uninstall'||order.status eq 'install'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>">
																	<ul>
																		<li class="tx1">&nbsp;</li>
																		<li class="tx2">待安装</li>
																		<li class="tx3" id="track_time_1"></li>
																	</ul>
																</div>
																
																<div class="proce <c:choose><c:when test="${order.status eq 'install'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>"><ul><li class="tx1">&nbsp;</li></ul></div>
																<div class="node <c:choose><c:when test="${order.status eq 'install'||order.status eq 'finish'}">ready</c:when><c:otherwise>wait</c:otherwise></c:choose>">
																	<ul>
																		<li class="tx1">&nbsp;</li>
																		<li class="tx2">已安装</li>
																		<li class="tx3" id="track_time_5"></li>
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
											<th width="20%"><strong>处理时间</strong></th>
											<th width="50%"><strong>处理信息</strong></th>
											<th width="30%"><strong>操作人</strong></th>
										</tr>
									</tbody>
									<tbody>
									<c:forEach var="log" items="${order.logs}">
										<tr>
											<td style="text-align: center;"><fmt:formatDate value="${log.alttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td>${log.logText}</td>
											<td style="text-align: center;">${log.opName}</td>
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
							<dt>收货人信息</dt>
							<dd>
								<ul>
									<li>收&nbsp;货&nbsp;人：${order.name}</li>
									<li>手机号码：${order.phone}</li>
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
							<dt>店铺信息</dt>
							<dd>
								<ul>
									<li>店铺名称：${order.store.storeName}</li>
									<li>联系电话：${order.store.tel}</li>
									<li>店铺地址：${order.store.area}&nbsp${order.store.addr}</li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>预约消费时间</dt>
							<dd>
								<ul>
									<li>预约消费时间：&nbsp;&nbsp;<fmt:formatDate value="${order.consumptionTime}" pattern="yyyy-MM-dd"/></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>订单备注</dt>
							<dd>
								<ul>
									<li>订单备注：${order.markText}</li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>清单</dt>
							<dd class="p-list">
								<table width="100%" cellspacing="0" cellpadding="3" class="gridlist">
									<colgroup>
										<col class="span-auto">
										<col class="span-3">
										<col class="span-4">
									</colgroup>
									<tbody>
										<tr>
											<th>汽车部位</th>
											<th>金额小计</th>
										</tr>
									</tbody>
									<tbody>
									<c:forEach var="offerDetail" items="${order.damageOfferList}">
										<tr>
											<td>
												<div class="clearfix horizontal-m">
													<c:forTokens items="${offerDetail.carDamageDetail.pic}" delims=";" var="picPath">
										                    <div class="sell_img fl">
										                        <a href="javascript:void(0)"><img src="${imgUrl}${picPath}" width="80px" height="80px"/></a>
										                    </div>
													</c:forTokens>
													<div class="goods-main">
														<div class="goodinfo" style="width: 50%">
															<h3>${offerDetail.carDamageDetail.carPart.partName}</h3>
														</div>
													</div>
												</div>
											</td>
											<td class="textcenter vm font-orange">￥<fmt:formatNumber value="${offerDetail.offerPrice}" pattern="#,#00.00#"/></td>
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
							<li><span>总商品金额：</span>￥<fmt:formatNumber value="${order.payed}" pattern="#,#00.00#"/></li>
						</ul>
						<span class="clearfix"></span>
						<span style="color: #EDEDED;"></span>
						<div class="extra">
							应付总额：<span class="ftx04"><b>￥<fmt:formatNumber value="${order.payed}" pattern="#,#00.00#"/></b></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>