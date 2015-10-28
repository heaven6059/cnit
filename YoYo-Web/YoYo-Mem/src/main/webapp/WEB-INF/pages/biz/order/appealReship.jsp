<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.net.URLDecoder" %>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>返修退换货申诉</title>
<%@include file="/resources/include/head.jsp" %>

<link rel="stylesheet" href="${path}/resources/styles/reship/reship.css" type="text/css">
<link rel="stylesheet" href="${path}/resources/scripts/imageUpload/zyUpload.css" type="text/css">
<!-- 引用核心层插件 -->
<script type="text/javascript" >
var _path = "${path}"; 
</script>
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/order/addReship.js?v=${versionVal}"></script>
<style type="text/css">
.list-type li, .list-cred li, .list-qjtype li {
    float: left;
    margin-right: 14px;
}
.list-type-new li {
    position: relative;
    white-space: nowrap;
}
li {
    list-style: outside none none;
}

.list-type-new li a {
    border: 1px solid #ebebeb;
    display: block;
    height: 18px;
    line-height: 18px;
    padding: 6px 28px;
    text-align: center;
    color: #666;
}

.list-type-new li.selected a {
    border: 2px solid #e4393c;
    padding: 5px 27px;
}


.list-prou .selected b, .list-prou01 .selected b, .list-type-new .selected b {
    background: rgba(0, 0, 0, 0) url("http://misc.360buyimg.com/product/skin/2012/i/newicon20130617.png") repeat scroll -202px -224px;
    bottom: 0;
    height: 12px;
    overflow: hidden;
    position: absolute;
    right: 0;
    width: 12px;
}

a {
    color: #666;
    text-decoration: none;
}
.quantity-form a{
padding-right: 0px;
}
.decrement {
    background-position: 0 0;
    margin-right: 2px;
}

.decrement, .increment {
    border: 1px solid #c9c9c9;
    display: inline-block;
    height: 13px;
    line-height: 13px;
    margin-right: 2px;
    overflow: hidden;
    text-align: center;
    vertical-align: middle;
    width: 13px;
}
.text01 {
    margin-right: 3px;
    padding: 1px 5px;
    text-align: center;
    width: 20px;
}
.text {
	height:18px;
    border: 1px solid #ccc;
    line-height: 18px;
    padding: 5px;
}

</style>
</head>
<body>
	<div class="shop_manager_right ">
		<div class="title ">返修退换货申诉</div>
		<form  id="reshipForm" class="section">
			<input type="hidden" name="productId" value="${order.orderItems[0].productId}" /> 
			<input type=hidden name="storeId" value="${order.store.storeId}" />
			<input type=hidden name="itemsId" value="${order.orderItems[0].itemId}" />
			<input type=hidden name="productPrice" value="${order.orderItems[0].price}" />
			<input class="x-input inputstyle" type="hidden" name="storeName" value="${order.store.storeName}" />
			<input type="hidden" value="${returnId}" name="returnId" />
			<input class="x-input inputstyle" type="hidden" name="memberImagePath" id="memberImagePath"/>
			<input type="hidden" name="isSafeguard" id="isSafeguard" value="2"/>
			<div id="gEditor-Body">
				<div class="title_fb ">填写申诉申请基本信息</div>
				<div class="FormWrap" style="background: none">
					<div class="mod-main mod-comm">
					    <div class="mc">
					            <table class="tb-void" width="100%" cellspacing="0" cellpadding="0" border="0">
					                <thead>
					                <tr>
					                     <th width="70%" colspan="2">商品名称</th>
					                     <th width="10%">购买数量</th>
					                     <th width="20%">购买单价</th>
					                </tr>
					                </thead>
					                <tbody>
					                <c:forEach var="item" items="${order.orderItems}">
					                <tr>
					                    <td width="10%">
			                               <a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${item.product.goodsId}">
		                                     <img width="50" height="50" src="${imgUrl}/${item.product.picturePath}" title="${item.name}">
				                           </a>
					                    </td>
					                    <td>
				                           <a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${item.product.goodsId}">${item.name}</a>
					                    </td>					                    
					                    <td>${item.nums}</td>
					                    <td>${item.price}</td>
					                </tr>
					                </c:forEach>
					                </tbody>
					           </table>
					    </div>
					</div>
					<div class="store_title">
						<label><span style="color: red; ">*温馨提示：</span> 本次售后申诉服务将由 卖家<span style="color: red; "> ${order.store.storeName}</span> 为您提供</label>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="liststyle data width1" style="border: none">
						<tr>
							<td colspan="2">
							</td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>订单号：</th>
							<td>
								<label>${order.orderId}</label>
								<input class="x-input inputstyle" type="hidden" name="orderId" value="${order.orderId}" readonly = "readonly"/>
							</td>
						</tr>

						<tr>
							<th><em><font color="red">*</font></em>售后要求：</th>
							<td>
								<div class="fl">
									<ul class="list-type list-type-new">
										<c:forEach items="${afterSalesRequires}" var="afterSalesRequire">
										<li id="${afterSalesRequire.key}" name="applyType" class="">
											<a class="choose" href="javascript:void(0);">${afterSalesRequire.value}
												<b></b>
											</a>
										</li>
										</c:forEach>
										<input type="hidden" name="safeguardRequire" id="safeguardRequire"/>				
									</ul>									
								</div>
							</td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>服务类型：</th>
							<td>
								<div class="fl">
									<ul class="list-type list-type-new">
										<c:forEach items="${afterSalesType}" var="afterSalesType">
										<li id="${afterSalesType.key}" name="applyType" class="">
											<a href="javascript:void(0);">${afterSalesType.value}
												<b></b>
											</a>
										</li>
										</c:forEach>
										<input type="hidden" name="safeguardType" id="safeguardType" />						
									</ul>			
								</div>
							</td>
						</tr>
						<tr class="hide" id="amount_tr">
							<th><em><font color="red">*</font></em>退款金额：</th>
							<td>
								<span id="amount_span">${order.orderItems[0].price}</span>
								<input type="hidden" name="amount" id="amount"/>
							</td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>提交数量：</th>
							<td>
								<div class="fl">
									<div class="quantity-form">
										<a href="javascript:void(0);" class="decrement" name="applyNum_operate_minus">-</a>
										<input type="text" class="text text01" value="1" data-price="${order.orderItems[0].price}" data-nums="${order.orderItems[0].nums}" name="nums" id="number" readonly="readonly" maxlength="2"/>
										<a href="javascript:void(0);" class="increment" name="applyNum_operate_add">+</a>
									</div>
									<div class="clr"></div>
								</div>
							</td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>联系人姓名：</th>
							<td>
								<input autocomplete="off" class="x-input inputstyle" type="text" name="memberName" id="memberName"  maxlength="20" value="${order.member.name}"/>
							</td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>联系人手机：</th>
							<td>
								<input autocomplete="off" class="x-input inputstyle" type="text" name="memberPhone" id="memberPhone"  maxlength="11" value="${order.member.mobile}"/>
							</td>
						</tr>
						<tr>
							<th>联系人地址：</th>
							<td>
								<input autocomplete="off" class="x-input inputstyle" type="text" name="refundAddress"  />
							</td>
						</tr>

						<tr>
							<th><em><font color="red">*</font></em>问题描述：</th>
							<td>
								<textarea id="content_desc" type="textarea" class="x-input" name="content" style="resize: none;" cols="50" rows="5" maxth="255"></textarea>
								<div class="notice-inline">描述购买的商品、服务的质量问题</div>
							</td>
						</tr>
						<tr>
							<th>上传凭证相册：</th>
							<td>
								<div class="goods-image-div" id="goods_picture"></div>
								<div style="clear: both;"></div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		<div style="text-align: center; margin-top: 20px;">
			<input name="status" value="1" type="hidden"/>
			<input name="is_safeguard" value="2" type="hidden"/>  
			<button class="btn btn-primary" type="button" id="appeal_save_button">
				<span><span>保存</span></span>
			</button>
		</div>
		</form>
	</div> 

			

</body>
</html>