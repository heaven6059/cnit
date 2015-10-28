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
<title>会员中心</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<link href="${path}/resources/styles/membercenter.css" rel="stylesheet" type="text/css" media="screen, projection" />
</head>
<body>
	<div class="sell_ri fr">
		<!--<div class="member_center_nav">当前位置：<a href="#">首页</a> > <a href="#" class="current">会员中心</a></div>-->
		<div class="sell_ri_top">
			<div class="sell_ri_top_ri">
				<div class="order_info">
					<div class="order_info_le fl">
						<p class="tit_p">
							<span>${memberDo.loginName}</span>，欢迎您！ <font class="price-normal_1 price-normal"></font>
						</p>
						<ul>
							<li>订单提醒： 
								<span class="red"> <a href="${memPath}/memberOrder/orderList?payStatus=0&status=create">待付款(${orderTips.unPay})</a></span>
								<span class="red"> <a href="${memPath}/memberOrder/orderList?status=confirm">待完成(${orderTips.confirm})</a></span>
								<span class="red"> <a href="${memPath}/memberOrder/orderList?status=finish">待评价(${orderTips.unComments})</a></span>
							</li>
							<li>
								我的关注： 
								<span><a href="${memPath}/productWishList/toWishList">关注的商品(${orderTips.wishCount})</a></span>
							</li>
						</ul>
					</div>
					<div class="memberindex_money fr">
						<ul>
							<li><b>我的积分：</b><a href="${memPath}/point/getPointListPage">${member.pointUseable}</a>分</li>
							<li><b>优惠券：</b><a href="${memPath}/memberCoupon/memberCoupon">${orderTips.coupons}张</a></li>
						</ul>
					</div>
					<div class="cl_zhi"></div>
				</div>
			</div>
			<div class="cl_zhi"></div>
		</div>

		<!--订单-待评价-待付款开始-->
		<div id="all_orders" class="Plate member-main">
			<h4>
				<a id="nopayed" href="javascript:void(0);" class="current">待付款</a>
				<a id="confirm" href="javascript:void(0);">待确认</a>
				<a id="comment" href="javascript:void(0);">待评价</a>
				<a id="refunds" href="javascript:void(0);">退款中</a>
			</h4>
			<div id="my_order" class="Plate_info">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" style="border-bottom: none" class="gridlist">
					<thead>
                        <tr class="firstTr">
                            <th style=" width: 450px;" colspan="2">订单信息</th>
                            <th style=" width: 135px;"></th>
                            <th style=" width: 140px;">订单金额</th>
                            <th style=" width: 125px;">订单状态</th>
                            <th style="width: 120px">操作</th>
                        </tr>                        
                    </thead>
					<tbody>
						<c:forEach var="order" items="${orders.rows}" varStatus="status">
		              	<tr class="tr-th">
		              	<td colspan="6">
		            		<span>订单号：<a href="../memberOrder/viewOrder?orderId=${order.orderId} "class="dd_color" >${order.orderId}</a></span>
		              		<span><fmt:formatDate value="${order.createtime}" pattern="yyyy-MM-dd hh:mm:ss"/></span>
		              		<span><a href="${portalPath}/shop/index?companyId=${order.store.companyId}" target="_blank">${order.store.storeName}</a></span>
		              	</td>
		              	</tr>
		              	<c:forEach var="orderItem" items="${order.orderItems}" varStatus="itemStatus">
            			<tr>
		            		<td>
		            			<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.product.goodsId}" class="font-blue" target="_blank">
		            				<img src="${imagePath}${orderItem.product.picturePath}" width="50px" height="50px" />
		            			</a>
		            		</td>
		            		<td class="product_name">
		            			<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.product.goodsId}" class="font-blue" target="_blank">${orderItem.product.name}</a>
		            		</td>
		            		<td class="left">		            		
		            			单价：<fmt:formatNumber value="${orderItem.price}" pattern="#.00"/><br>
		            			数量：${orderItem.nums}
		            		</td>
		            		<c:if test="${itemStatus.index eq 0}">
		            			<td rowspan="${fn:length(order.orderItems)}" class="price">￥<fmt:formatNumber value="${order.finalAmount}" pattern="#.00"/></td>		            			
		            			<td rowspan="${fn:length(order.orderItems)}" class="textcenter">
		            				<div style="width: 100px; display: block; margin: 0 auto;">待付款</div>
								</td>
								<td rowspan="${fn:length(order.orderItems)}">
								<a class="btn-4" href="../pay/payOrder?orderId=${order.orderId} " >付款</a><br>
								<a target="_blank" href="../memberOrder/viewOrder?orderId=${order.orderId}" >查看</a><br>
								<a href="javascript:void(0);" onclick ="cancelOrder(${order.orderId})">取消订单</a><br>
							</c:if>
		            	</tr>	          
		           		</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!--订单-待评价-待付款结束-->
		<!--关注的商品-->
		<div class="Plate clb member-main">
			<h4>
				<div class="p_tit">
					<span>您关注的商品</span>
				</div>
			</h4>
			<div style="height: 255px;margin: 10px 0;position: relative;width: 100%;">
        	 	<a class="clo_ds_le"></a>
                <a class="clo_ds_ri"></a>
                <div class="picscroll_buy pics">
	            	<ul style="position: absolute; width:${fn:length(focusProducts.rows)*228}px; height: 255px; top: 0px; left: 0px;">
	            		<c:forEach var="focusProduct" items="${focusProducts.rows}">
						<li>
                            <a href="${portalPath}/goodsManager/goodsIndex?goodsId=${focusProduct.goodsId}"><img src="${imagePath}${focusProduct.productImg}"></a>
                            <p><a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${focusProduct.goodsId}">${focusProduct.productName}</a></p>
                            <div class="price-wrap"><span class="font-red font14px fontbold">￥<fmt:formatNumber value="${focusProduct.productPrice}" pattern="#.00"/></span></div>
                         </li>	            			
	            		</c:forEach>
	                </ul>
                </div>
            </div>
		</div>
		<!--关注的商品-->
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/membercenter/membercenter.js?v=${versionVal}"></script>
</body>
</html>

