<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>支付成功</title>
	    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	    <meta http-equiv="imagetoolbar" content="no"/>
	    <meta name="apple-mobile-web-app-capable" content="yes"/>
	    
	    <link href="${path}/resources/styles/cart/base.css?v=${versionVal}" type="text/css" rel="stylesheet">
	    <link href="${path}/resources/styles/cart/addToCart.css?v=${versionVal}" type="text/css" rel="stylesheet">
	    <script src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
		<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
		<script type="text/javascript">var _path="${path}"</script>
		<style type="text/css">
			.add_cg h2 span{
			    color: red;
			    font: 15px 微软雅黑;
			}
		</style>
	</head>
	<body>
		<div id="main_container" class="cart root61" style="width: 1200px !important; margin:auto; height: auto;display: table;">
			<div class="add_con">
				<div class="add_left fl">
			    	<div class="add_top clearfix">
			        	<div class="add_cg fl">
			            	<h2>订单:<span id="orderId">${order.id}</span>&nbsp;&nbsp;支付成功!</h2>
			            	<br/>
			            	<h2>付款金额:<span>￥<fmt:formatNumber value="${order.payed}" pattern="#,#00.00#"/></span></h2>
			            </div>
			            <div class="fr">
			            	<a class="add_btn" id="viewLink" href="${path}/mypainting/viewOrder?orderId=${order.id}">查看订单详情</a>
			                <span>您还可以<a class="dd_color" id="listLink" href="${path}/mypainting/orderList">订单列表</a></span>
			            </div>
			        </div>
				</div>
			</div>
		</div>
	</body>
</html>