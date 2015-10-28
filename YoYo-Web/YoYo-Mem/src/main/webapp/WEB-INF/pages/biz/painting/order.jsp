<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    request.setAttribute("time", System.currentTimeMillis());
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单确认</title>
<link href="${path}/resources/styles/cart/backpanel.css" type="text/css" rel="stylesheet">
<link href="${path}/resources/styles/cart/checkout.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/sellYoYo.css" />
<link href="${path}/resources/styles/painting/order.css" type="text/css" rel="stylesheet">

<script src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js"></script>
<script type="text/javascript">var _path="${path}"</script>
<script src="${path}/resources/scripts/biz/painting/order.js?v=${time}"></script>

</head>
<body>
	<div id="main_container" class="sell_main ml10 fl">
		<div class="sell_title">
			<div class="title_border">
				<span>确认订单</span>
			</div>
		</div>
		<div class="w m2">
			<a name="consigneeFocus"></a>
			<div id="checkout">
				<div class="mt">
					<h2>填写并核对订单信息</h2>
				</div>
				<div id="wizard" class="checkout-steps">
					<div id="step-0" class="step step-complete">
						<div class="step-title">
							<div id="save-consignee-tip" class="step-right"></div>
							<strong id="consigneeTitleDiv">个人信息</strong>
						</div>
						<div class="step-content">
							<table class="card-table" >
		                         <tbody>
				                      	<tr>
											<th><em class="red">*</em>您的姓名：</th>
											<td>
											    <input class="inp-text" type="text" id="name" name="name" value="${member.name }">
											    <span class="errortip fn-hide error_name">请填写您的姓名</span>
											</td>
				                        </tr>
				                        <tr>
				                        	<th><em class="red">*</em>手机号码：</th>
				                            	<td>
				                                	<input class="inp-text"  type="text" id="phone" name="phone"  value="${member.mobile }">
				                                	<span class="errortip fn-hide error_phone">请填写正确的手机号</span>
				                                </td>
				                            </th>
				                        </tr>
		                           </tbody>
		                     </table>
						</div>
					</div>
					<div id="step-1" class="step step-complete">
					<form>
						<div class="step-title">
							<div id="save-consignee-tip" class="step-right"></div>
							<strong id="consigneeTitleDiv">门店信息</strong>
							<%--请选择安装门店 --%>
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
									<div class="shopping-list ABTest">
										<div class="goods-list">
											<h4 class="vendor_name_h" id="vendor_1">商家：</h4>
											<div class="goods-items">
												<ul class="ui-switchable-panel-main" style="top: 0px; position: relative;" id="consignee-list-1">
													<c:if test="${issueType == 1 && (storeList == null || fn:length(storeList) == 0)}">
	                    								<li class="ui-switchable-panel store-selected" style="display: list-item;height:auto;width:auto;margin-left:20px;margin-right: 10px;" id="consignee_index_1">
	                    									<p class="red" style="font-size: 14px;">该商家在<span style="font-weight:bolder">${area}</span>没有分店，请选择其他地区的分店或选择其他商家</p>
	                    								</li>
													</c:if>
                    								<c:forEach items="${storeList}" var="store" varStatus="status">
                    									<c:choose>
                    										<c:when test="${status.first}">
	                    										<li class="ui-switchable-panel store-selected" style="display: list-item;height:auto;width:auto;margin-left:20px;margin-right: 10px;" id="consignee_index_1">
																	<div class="consignee-item item-selected" consigneeid="" name="company_store" style="width:160px;height:90px;">
																		<span limit="" title="storeName">${store.storeName}</span>
																		<br>
																		<span class="addr-name" limit="" title="">${store.area } ${store.addr}</span>
																		<br>
																		<span limit="" title="storeName">${store.tel}</span>
																		<br>
																		<b></b>
																	</div>
																	<input type="hidden" value="${store.storeId}"/>
																	<input class="companyId" type="hidden" value="${store.companyId}"/>
																</li>
                    										</c:when>
                    										<c:otherwise>
                    											 <li class="ui-switchable-panel" style="display: list-item;height:auto;width:auto;margin-left:20px;margin-right: 10px;" id="consignee_index_1">
																	<div class="consignee-item" consigneeid="" name="company_store" style="width:160px;height:90px;">
																		<span limit="" title="">${store.storeName}</span>
																		<br>
																		<span class="addr-name" limit="" title="">${store.area } ${store.addr}</span>
																		<br>
																		<b></b>
																	</div>
																	<input type="hidden" value="${store.storeId}"/>
																	<input class="companyId" type="hidden" value="${store.companyId}"/>
																</li>
                    										</c:otherwise>
                    									</c:choose>
                    								</c:forEach>
												</ul>
											</div>
										</div>
										<div class="clr"></div>
									</div>
								</div>
								<div class="clearfix" style="margin: 10px 45px 10px 0;">
									<a href="javascript:;" id="selectStorsLink" style="color: #005ea7;float:right">手动选择其他地区分店</a>
								</div>
								<div id="selectStores" class="selectStores hide">
										<table class="selectTable" >
											<tbody>
												<tr>
				                                    <th style="padding-bottom: 10px;"><em class="red">*</em>地址：</th>
				                                    <td>
				                                        <div class="selmain">
				                                            <div class="select zindex-05">
				                                            	<select class="select-selected" id="province" name="province">
										                        	<option value="0">选择省份</option>
										                        </select>
				                                            </div>
				                                        </div>
				                                        <div class="selmain">
				                                            <div class="select zindex-05">
				                                            	<select class="select-selected" id="city" name="city">
									                            	<option value="0">选择城市</option>
									                            </select>
				                                            </div>
				                                        </div>
				                                        <div class="selmain city-last">
				                                            <div class="select zindex-05 select-disabled">
				                                            	<select class="select-selected" id="region" name="region">
									                            	<option value="0">选择地区</option>
									                            </select>
				                                            </div>
				                                        </div>
				                                    </td>
				                                    <button class="btn" type="button" id="submit" onclick="selectStores()">选择</button>
				                                </tr>
											</tbody>
										</table>
								</div>
							</div>
						</div>
					</div>
					<div id="step-2" class="step step-complete">
						<a name="payAndShipFocus"></a>
						<div class="step-title">
							<div id="save-payAndShip-tip" class="step-right"></div>
							<strong>支付及时间预定</strong> 
							<span class="step-action" id="payment-ship_edit_action">
							</span>
						</div>
						<div class="step-content">
							<div id="payment-ship" class="sbox-wrap">
								<div class="payment">
									<h3>支付方式</h3>
									<input type="hidden" id="instalmentPlan" value="false">
									<div style="padding-bottom: 10px"></div>
									<div class="mc form">
										<div class="item">
											<div class="label">
												<input type="radio" checked="checked" name="payment" payname="到店支付" payid="1" id="pay-method-1" class="hookbox" value="1"> 
												<label for="pay-method-1">到店支付</label>
											</div>
											<span class="clr"></span>
											<div class="sment-mark item-selected" style="" id="otherSupportSkus-1"></div>
										</div>
										<div class="item item-selected">
											<div class="label">
												<input type="radio"  name="payment" payname="在线支付" payid="4" id="pay-method-4" class="hookbox" value="0"> 
												<label for="pay-method-4">在线支付 </label>
											</div>
											<span class="clr"></span>
										</div>
									</div>
								</div>
								<div id="shipment">
									<div class="way" style="padding-top: 20px;">
										<h3>消费时间</h3>
										<div class="mc form">
											<div id="jd-shipment-way-category" class="way-category way-category-selected " style="padding-top: 5px;">
												<div id="jd-shipment-extend-info" class="express-form">
													<div class="list delivery-time" id="jd-delivery-time">
														<div class="field">
															<span id="promise-311">
																<div class="t-item">
																	<label for="delivery-t4">指定消费时间</label>
																	<input type="text" id="date-311" readonly="true">
																	<input type="hidden" name="sendPay311" id="sendPay311" value="{&quot;1&quot;:1,&quot;35&quot;:0,&quot;30&quot;:1}">
																	<input type="hidden" name="day" id="day" value="">
																	<input type="hidden" id="range" name="range" value="">
																</div>
															</span>
															<span id="promise-411"></span>
														</div>
													</div>

													<div class="list warm-prompt"></div>
												</div>
												<br>
											</div>
											<br>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="step-title" style="border-top: 1px solid #ddd;margin-top: 20px;"><strong>备注</strong></div>
						<div class="step-content" style="border-bottom: 1px solid #ddd;padding-bottom: 20px;">
							<div class="sbox-wrap">
<!-- 								留言：<input id="markText" name="markText" value="" style="width: 200px;margin-left: 10px;border: 1px solid #ccc;height: 20px;line-height: 20px;" /> -->
								留言：<textarea maxlength="300" placeholder="如有需要，请提交300字以内的留言" id="markText" name="markText"  value="" style="padding: 5px;margin-left: 10px;border: 1px solid #ccc;height: 45px;width: 394px;vertical-align: middle;" autocomplete="off" ></textarea>
							</div>
							
						</div>				
						<div id="step-4" class="step step-complete">
							<div class="step-title">
								<a href="javascript:;" id="retureUrl" class="return-edit">返回修改商家</a><strong>商品清单</strong>
							</div>
							<div class="step-content">
								<div id="part-order" class="sbox-wrap">
									<div class="shopping-list ABTest">
										<div class="goods-list">
											<div class="goods-items">
												<c:forEach items="${offerList}" var="offer" begin="0" end ="0">
													<input type="hidden" value="${offer.totalprice}"/>
													<input name="offerId" type="hidden" value="${offer.id}"/>
													<c:forEach items="${offer.offerDetailList}" var="detail"  varStatus="status">
														<div class="goods-item ">
															<div class="p-img">
																<a target="_blank" href="javaScript:void(0)">
																    <c:forTokens items="${detail.carDamageDetail.pic}" delims=";" var="picPath" begin="0" end="0">
																		<img src="${imgUrl}${picPath}" width="82px" height="82px" alt=""/>
																	</c:forTokens>
																</a>
															</div>
															<div class="goods-msg">
																<div class="p-name">
																	<a target="_blank" href="javaScript:void(0)">${detail.carDamageDetail.carPart.partName } </a>
																</div>
																<div class="p-price">
																	<strong>${detail.offerPrice}</strong>
																	<span class="ml20"></span> 
																</div>
															</div>
															<div>${detail.remark}</div>
															<div class="clr"></div>
														</div>
													</c:forEach>
												</c:forEach>
											</div>
										</div>
										<div class="clr"></div>
									</div>
								</div>
							</div>
							<div id="checkout-floatbar" class="checkout-buttons group">
								<div class="sticky-placeholder" style="display: block;">
									<div class="sticky-wrap">
										<div class="inner">
											<button type="button"  class="checkout-submit" id="order-submit"></button>
											<c:forEach items="${offerList}" var="offer" begin="0" end ="0">
												<span class="total">应付总额：<strong id="payPriceId">${offer.totalprice}</strong>元
											</c:forEach>
											</span>
										</div>
										<span id="submit_message" style="display: none" class="submit-error"></span>
										<div class="submit-check-info" id="submit_check_info_message" style="display: none"></div>
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