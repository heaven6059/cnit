<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<link href="${path}/resources/styles/cart/base.css" type="text/css" rel="stylesheet">
<link href="${path}/resources/styles/cart/checkout.css" type="text/css" rel="stylesheet">
<link href="${path}/resources/styles/cart/backpanel.css" type="text/css" rel="stylesheet">
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
				<div id="step-1" class="step step-complete">
					<div class="step-title">
						<div id="save-consignee-tip" class="step-right">
						</div>
						<strong id="consigneeTitleDiv">门店信息</strong><%--请选择安装门店 --%>
						<!-- <span class="step-action" id="consignee_edit_action"><a href="#none" onclick="edit_Consignee()">[修改]</a></span> -->
					</div>

						<div class="step-cont">
	<div id="consignee-addr" class="consignee-addr">
		<div class="addr-ctrl hide" style="display: none;">
			<div id="addr-up" class="addr-up disabled"></div>
			<div id="addr-down" class="addr-down disabled"></div>
		</div>
		<div id="consignee-ret"></div>
		<div class="consignee-cont consignee-off ui-switchable-panel-body" style="position: relative; height: auto;" id="consignee1">
		</div>
	</div>
	
	
</div>

					</div>
				<div id="step-2" class="step step-complete"><a name="payAndShipFocus"></a>
					<div class="step-title">
						<div id="save-payAndShip-tip" class="step-right">
						</div>
						<strong>支付及时间预定</strong>
						<span class="step-action" id="payment-ship_edit_action"><!-- <a href="#none" onclick="edit_Payment(false)">[修改]</a> --></span> 					</div>
					<div class="step-content">
						<div id="payment-ship" class="sbox-wrap">

								<div class="payment" ><!-- style="display:none;" -->
									<h3>支付方式</h3>
									<input type="hidden" id="instalmentPlan" value="false">
									<div style="padding-bottom: 10px"></div>
									<div class="mc form">
										<div class="item item-selected fl">
											<div class="label">
												<input type="radio" checked="checked" name="payment"
													onclick="showSkuDialog(this)" payname="在线支付" payid="4"
													id="pay-method-4" class="hookbox" value="4"> <label
													for="pay-method-4">在线支付 <font
													class="whiteBarSpanClass" color="#FF6600"
													style="display: none;">[支持打白条]</font>
												</label>
											</div>
											<span class="clr"></span>
										</div>
										
										<div class="item">
											<div class="label">
												<input type="radio" name="payment"
													onclick="showSkuDialog(this)" payname="到店支付" payid="1"
													id="pay-method-1" class="hookbox" value="1"> <label
													for="pay-method-1">到店支付 <span style="display: none"
													id="supportPaySkus-1"> (1 样商品) </span>
												</label>
											</div>
											<span class="clr"></span>
											<div class="sment-mark item-selected" style="" id="otherSupportSkus-1"></div>
										</div>
									</div>
								</div>

								<div id="shipment" ><!-- style="display:none;" -->
									<div class="way" style="padding-top: 20px;">
										<h3>消费时间</h3>
										<div class="mc form">
											<div id="jd-shipment-way-category"
												class="way-category way-category-selected "
												style="padding-top: 5px;">

												<div id="jd-shipment-extend-info" class="express-form">
													<div class="list delivery-time" id="jd-delivery-time">
														<span class="label">消费时间：</span>
														<div class="field">
															<div class="t-item commonshipment" style="display: none">
																<input id="delivery-t3" name="delivery-t" checked=""
																	class="hookbox" value="3" type="radio"> <label
																	for="delivery-t3">工作日、双休日与假日均可送货</label><span
																	id="promise-t3">&nbsp;&nbsp;<font color="red">4月17日（周五）</font></span>
															</div>
															<!-- 311 -->
															<span id="promise-311"><div class="t-item">
																	<!-- <input type="radio" value="4" class="hookbox"
																		name="delivery-t" id="delivery-t4"> --><label
																		for="delivery-t4">指定消费时间</label><input type="text"
																		id="date-311" readonly="true"><input
																		type="hidden" name="sendPay311" id="sendPay311"
																		value="{&quot;1&quot;:1,&quot;35&quot;:0,&quot;30&quot;:1}"><input
																		type="hidden" name="day" id="day" value="2015-4-17"><input
																		type="hidden" id="range" name="range" value="">
																</div></span> <span id="promise-411"></span>
														</div>
													</div>


													<div class="list payment-type hide" id="jd-before-notify">
														<span class="label">是否送货前确认：</span>
														<div class="field">
															<ul class="group">
																<li><input type="radio" value="1" class="hookbox"
																	checked="" name="jd-inform"> <label for="">是</label>
																</li>
																<li><input type="radio" value="0" class="hookbox"
																	name="jd-inform"> <label for="">否</label></li>
															</ul>
														</div>
													</div>
													<div class="list warm-prompt">
												</div>
											</div>

											<br>
										</div>
										<br>
									</div>
								</div>

							</div>
					</div>
				</div>
				
				<div id="step-4" class="step step-complete">
					<input type="hidden" name="proIdList" value="${proIdList}" />
					<input type="hidden" name="quantityList" value="${quantityList}" />
					<div class="step-content">
						<div id="part-order" class="sbox-wrap">
						
						</div><!--@end div#part-order-->


								<div class="order-summary" style="display:none;padding:20px 44px;">
									<input type="hidden" id="accessoryId" value="" />
									<div class="statistic fr">
										<div class="list">
											<span><!-- <em class="ftx-01">0</em> 件商品， -->总商品金额：</span> <em
												class="price" id="warePriceId" v="0">￥0.00</em>
										</div>

										<div class="list" id="showCouponPrice">
											<span>商品优惠：</span><em class="price" id="couponPriceId">-￥0.00</em>
										</div>

										<div class="list">
											<span>应付总额：</span> <em class="price" id="sumPayPriceId">￥0.00</em>
										</div>
									</div>
									<div class="clr"></div>
								</div>



								<div id="checkout-floatbar" class="checkout-buttons group"><div class="sticky-placeholder" style="display: block;"><div class="sticky-wrap">
							<div class="inner">
								
                                <!--input type="submit"  class="checkout-submit" value="" id="order-submit" onclick="javascript:submit_Order();"/-->
                                <button type="submit" class="checkout-submit" id="order-submit" >
                                        提交订单
                                        <b></b>
                                </button>
																	<span class="total">应付总额：<strong id="payPriceId">￥0.00</strong>元 
									    <label class="noShowMoney hide" id="giftBuyHidePriceDiv">
									    	<input type="checkbox" id="giftBuyHidePrice" checked="">隐藏礼品价格
									    </label>
	    							</span>
																<div class="checkout-submit-tip" id="changeAreaAndPrice" style="display: none;">由于地址更换，价格可能发生变化，请核对后再提交订单</div>
								<div style="display:none" id="factoryShipCodShowDivBottom" class="dispatching">
									部分商品货到付款方式：先由京东配送“提货单”并收款，然后厂商发货。
								</div>
							</div>
							<span id="submit_message" style="display:none" class="submit-error"></span>
							<div class="submit-check-info" id="submit_check_info_message" style="display:none"></div>
						</div></div></div>
					</div>
				</div>
			</div>
		</div>
	
	</div>
</div>
</body>

</html>