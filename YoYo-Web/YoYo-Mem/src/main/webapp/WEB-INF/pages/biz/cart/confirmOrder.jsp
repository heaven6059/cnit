<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>确认订单</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta http-equiv="imagetoolbar" content="no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<link href="${path}/resources/styles/cart/base.css?v=${versionVal}"" type="text/css" rel="stylesheet">
<link href="${path}/resources/styles/cart/checkout.css?v=${versionVal}"" type="text/css" rel="stylesheet">
<link href="${path}/resources/styles/cart/backpanel.css?v=${versionVal}"" type="text/css" rel="stylesheet">
<script type="text/javascript">var _path="${path}"</script>
<script src="${path}/resources/scripts/biz/cart/confirmOrder.js?v=${versionVal}"></script>
<style type="text/css">
.checkout-buttons .checkout-submit {
	background-color: #e00;
	position: relative;
	line-height: 36px;
	overflow: hidden;
	color: #fff;
	font-weight: bold;
	font-size: 16px;
}

.checkout-buttons .checkout-submit b {
	position: absolute;
	left: 0;
	top: 0;
	width: 135px;
	height: 36px;
	background:
		url(http://misc.360buyimg.com/purchase/trade/skin/i/btn-submit.jpg)
		no-repeat;
	cursor: pointer;
	overflow: hidden;
}

.checkout-buttons .checkout-submit:hover {
	background-color: #EF494D;
}

.checkout-buttons  .checkout-submit:hover b {
	background-position: 0 -36px;
}

.checkout-buttons .checkout-submit-disabled {
	background-color: #ccc;
	position: relative;
	line-height: 36px;
	font-weight: bold;
	font-size: 16px;
	cursor: not-allowed;
}

.checkout-buttons .checkout-submit-disabled b {
	position: absolute;
	left: 0;
	top: 0;
	width: 135px;
	height: 36px;
	background:
		url(http://misc.360buyimg.com/purchase/trade/skin/i/btn-disabled.png)
		no-repeat;
	cursor: not-allowed;
}
.header {
	 padding: 0px; 
}
</style>
</head>
<body>

<div id="main_container" class="cart" style="width: 1200px !important; margin:auto; width: auto; height: auto;">
	<div class="w m2">
		<a name="consigneeFocus"></a>
		<div id="checkout">
			<div class="mt">
				<h2>填写并核对订单信息</h2>
			</div>
			<div id="wizard" class="checkout-steps">				
				<div id="step-2" class="step step-complete"><a name="payAndShipFocus"></a>
					<div class="step-title">
						<div id="save-payAndShip-tip" class="step-right">
						</div>
						<strong>支付及时间预定</strong>
						<span class="step-action" id="payment-ship_edit_action"></span>
					</div>
					<div class="step-content">
						<div id="payment-ship" class="sbox-wrap">
							<div class="payment" >
								<h3>支付方式</h3>
								<input type="hidden" id="instalmentPlan" value="false">
								<div style="padding-bottom: 10px"></div>
								<div class="mc form">
									<div class="item item-selected fl">
										<div class="label">
											<input type="radio" checked="checked" name="payment" onclick="showSkuDialog(this)" payname="在线支付" payid="4" id="pay-method-4" class="hookbox" value="4">
											<label for="pay-method-4">在线支付</label>
										</div>
										<span class="clr"></span>
									</div>
								
									<div class="item">
										<div class="label">
											<input type="radio" name="payment" onclick="showSkuDialog(this)" payname="到店支付" payid="1" id="pay-method-1" class="hookbox" value="1"> 
											<label for="pay-method-1">到店支付</label>
										</div>
										<span class="clr"></span>
										<div class="sment-mark item-selected" style="" id="otherSupportSkus-1"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="order-coupon">
						<div id="orderCouponItem" class="item">
							<div class="toggle-title">
								<a href="#none" class="toggler"><b></b>使用优惠券</a>
							</div>
							<div id="orderCouponId" class="toggle-wrap" style="display: none;">
								<div id="coupons" data-widget="tabs" class="cbox">
									<div class="mt">
										<ul class="tab">
											<li class="coupon-tab-item curr" id="canUseLi">可用优惠券<em>(0)</em></li>
										</ul>
									</div>
									<div style="display: block;" data-widget="tab-content" class="coupon-tab-con" id="canUseCouponDiv">
										<div class="inner">
											<c:if test="${fn:length(memberCoupons)==0}">
											<div class="tip coupon-note ftx-03">
												此订单暂无可用的优惠券。 
											</div>
											</c:if>
											<c:if test="${fn:length(memberCoupons)>0}">
											<div class="virtual-from form">
												<div class="virtual-table">
													<c:forEach var="coupon" items="${memberCoupons}">
													<div class="virtual-table-body">
														<div class="virtual-action">
															<input type="checkbox" value="${coupon.memcCode}" data-coupon="${coupon.coupons.cpnsId},${coupon.coupons.storeId},${coupon.salesRuleGoods.cTemplate},${coupon.salesRuleGoods.conditions},${coupon.salesRuleGoods.sTemplate},${coupon.salesRuleGoods.actionSolution}" class="jdcheckbox" name="couponckbox" style="margin-top: 7px;">
														</div>
														<div class="virtual-sum">
															<span class="coupon-scope">
																<c:if test="${coupon.salesRuleGoods.cTemplate eq 'promotion_conditions_order_subtotalallgoods'}">当订单商品总价满${coupon.salesRuleGoods.conditions}&nbsp;</c:if>
																<c:if test="${coupon.salesRuleGoods.cTemplate eq 'promotion_conditions_order_itemsquanityallgoods'}">当订单商品数量满${coupon.salesRuleGoods.conditions}&nbsp;</c:if>
																<c:if test="${coupon.salesRuleGoods.cTemplate eq 'promotion_conditions_order_allorderallgoods'}">对所有订单给予优惠&nbsp;</c:if>
																
																<c:if test="${coupon.salesRuleGoods.sTemplate=='promotion_solutions_addscore' }">赠送${coupon.salesRuleGoods.actionSolution}积分</c:if>
															 	<c:if test="${coupon.salesRuleGoods.sTemplate=='promotion_solutions_topercent' }">价格乘以${coupon.salesRuleGoods.actionSolution}%折扣出售</c:if>
															 	<c:if test="${coupon.salesRuleGoods.sTemplate=='promotion_solutions_bypercent' }">价格减去${coupon.salesRuleGoods.actionSolution}%折扣出售</c:if>
															 	<c:if test="${coupon.salesRuleGoods.sTemplate=='promotion_solutions_byfixed' }">价格优惠${coupon.salesRuleGoods.actionSolution}出售</c:if>
															 	<c:if test="${coupon.salesRuleGoods.sTemplate=='promotion_solutions_getcoupon' }">送优惠券</c:if>
															</span>
														</div>

														<div title="${coupon.storeName}" class="virtual-type">
															<div sytle="width:300px;height:28px;overflow:hidden;line-height:28px;">${coupon.storeName}</div>
														</div>
														<div class="virtual-vtime">有效期：<fmt:formatDate value="${coupon.salesRuleGoods.fromTime}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${coupon.salesRuleGoods.toTime}" pattern="yyyy-MM-dd"/></div>
														<div clas="clr"></div>
													</div>
													</c:forEach>
												</div>
											</div>
											<div class="total">
												共使用了 <strong class="ftx-01" id="couponNum"> 0</strong>
												张优惠券&#12288;可以优惠 <strong class="ftx-01" id="couponPrice">0.00</strong>
												元
											</div>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- 支付 -->
                        <div id="member-point-div" class="item">
                              <div class="toggle-title">
                                  <a href="#none" class="toggler"><b></b>使用积分</a>
                              </div>
                              <div class="toggle-wrap hide">
                                  <div class="cbox">
                                      <div class="inner">
                                          <div class="form">
                                         	  <span class="txtBox"><input maxlength="9" type="text" autocomplete="off" id="J_PointInput" class="tc-text cost">点</span>
                                         	  <span class="discharge">- <span class="tc-rmb">¥</span><strong id="J_Discharge">0.00</strong></span>
                                         	  <p class="discharge-error hide"></p>
                                         	  <p class="totalPoint"><span>（可用<span class="usablePoints"><c:out value="${member.pointUseable}" default="0"/></span>点）</span></p>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                        </div>
                        
						<!-- 余额支付结束 -->
                    </div>
				
				<div id="step-4" class="step step-complete">
				<div class="step-title" style="border-top: 1px solid #ddd;margin-top: 20px;"><strong>订单商品信息</strong></div>
					<input type="hidden" name="proIdList" value="${proIdList}" />
					<input type="hidden" name="quantityList" value="${quantityList}" />
					<div class="step-content">
						<div id="part-order" class="sbox-wrap">
								<c:forEach var="cartList" items="${cartList}">
									<div class="shopping-list ABTest">
										<div class="goods-list">
											<h4 class="vendor_name_h">
												商家：<a style="width: auto;" href="${portalPath}/shop/index?companyId=${cartList.store.companyId}">${cartList.store.storeName}</a>
											</h4>
											<div class="goods-items">
												<c:forEach var="product" items="${cartList.cartProducts}">
												<div class="goods-item ">
													<input type="hidden" value="${product.productId}" name="goods_item_id" />
													<input type="hidden" value="${product.goodsId}" name="goods_id" />
													<input type="hidden" value="<c:out value="${product.quantity}" default="1" />" name="goods_quantity" />
													<c:if test="${!empty ag}"><input type="hidden" value="${appointment}" name="goods_appointment" /></c:if>
													<c:if test="${empty ag}"><input type="hidden" value="${product.appointment}" name="goods_appointment" /></c:if>
													<input type="hidden" value="${cartList.store.companyId}" name="goods_companyId" />
													<input type="hidden" value="${cartList.store.storeId}" name="goods_storeId" />
													<input type="hidden" value="${product.price}" name="goods_price"/>
													<div class="p-img">
														<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${product.goodsId}" target="_blank">
														<img width="80px" height="80px" src="${imagePath}${product.picturePath}"></a>
													</div>
													<div class="goods-msg">
														<div class="p-name">
															<a style="width: 100%; display: block; overflow: hidden; height: 18px;" target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${product.goodsId}">${product.name}</a>
															<p style="width: 100%; overflow: hidden; height: 18px;">${product.specInfo}</p>
															<c:if test="${!empty ag&&!empty appointment}"><p style="width: 100%; overflow: hidden; height: 18px;">预约时间段：${appointment}</p></c:if>
															<c:if test="${empty ag&&!empty product.appointment}"><p style="width: 100%; overflow: hidden; height: 18px;">预约时间段：${product.appointment}</p></c:if>
															<c:if test="${!empty scoreBuy}"><span style="background: #ffa100 none repeat scroll 0 0;border-radius: 2px;color: #fff;padding: 2px 5px;font-size: 12px;">积分换购</span></c:if>
														</div>
														<div class="p-price">
															<c:choose>
																<c:when test="${empty ag}">
																	<strong>￥<fmt:formatNumber value="${product.price*product.quantity}" pattern="#0.00"/></strong><span class="ml20">x<c:out value="${product.quantity}" default="1"/></span>	
																</c:when>
																<c:otherwise>												
																	<!-- 优惠套装 -->																	
																	<strong>￥<fmt:formatNumber value="${product.price}" pattern="#0.00"/></strong><span class="ml20">x<c:out value="${product.quantity}" default="1"/></span>	
																</c:otherwise>
															</c:choose>															
														</div>
													</div>
													<div class="clr"></div>
												</div>
												</c:forEach>
											</div>
										</div>
										<div class="clr"></div>
									</div>
								</c:forEach>
						</div>
						<div class="step-title" style="border-top: 1px solid #ddd;margin-top: 20px;"><strong>备注</strong></div>
						<div class="step-content" style="border-bottom: 1px solid #ddd;padding-bottom: 20px;">
							<div class="sbox-wrap">
								留言：
								<textarea title="选填：对本次交易的说明（建议填写已经和卖家达成一致的说明）" name="remark" value="" style="margin-left: 10px;border: 1px solid #ccc;height: 45px;width: 394px;vertical-align: middle;" autocomplete="off" ></textarea>
							</div>
						</div>
						<div class="order-summary" style="padding:20px 44px;">
							<input type="hidden" id="accessoryId" value="${ag.id}" />
							<div class="statistic fr">
								<div class="list">
									<span>总商品金额：</span> 
									<em class="price" id="warePriceId" v="0">￥<fmt:formatNumber value="${sumPrice}" pattern="#0.00"/></em>
								</div>

								<div class="list" id="showCouponPrice">
									<span>商品优惠：</span><em class="price" id="couponPriceId">￥<fmt:formatNumber value="${discountPrice}" pattern="#0.00" /></em>
								</div>

								<div class="list">
									<span>应付总额：</span> <em class="price" id="sumPayPriceId">￥<fmt:formatNumber value="${sumPrice-discountPrice}" pattern="#0.00"/></em>
								</div>
								<c:if test="${!empty scoreBuy}">
								<div class="list">
									<input type="hidden" id="orderType" value="${orderType}">
									<span>消费积分：</span><em class="price" id="payPoint">${scoreBuy.score}</em>
								</div>
								</c:if>
							</div>
							<div class="clr"></div>
						</div>
						<div id="checkout-floatbar" class="checkout-buttons group">
							<div class="sticky-placeholder" style="display: block;">
								<div class="sticky-wrap">
									<div class="inner">
		                                <button type="submit" class="checkout-submit" id="order-submit" >提交订单<b></b></button>
											<span class="total">应付总额：<strong id="payPriceId">￥<fmt:formatNumber value="${sumPrice-discountPrice}" pattern="#0.00"/></strong>元 											    
			    							</span>
									</div>									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>

</html>