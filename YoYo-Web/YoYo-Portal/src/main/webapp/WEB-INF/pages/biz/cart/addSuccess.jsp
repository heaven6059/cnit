<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>商品已成功加入购物车</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
	var _path = "${path}"
</script>
<link href="${path}/resources/styles/cart/addToCart.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${path}/resources/scripts/biz/cart/toAddSuccess.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>

</head>
<body>
	<div id="main_container" class="cart root61" style="width: 1200px !important; margin: auto; height: auto; display: table;">
		<!--加入到购物车-->
		<div class="add_con">
			<div class="add_left fl">
				<input type="hidden" id="product_id" value="${productId}" />
				<div class="add_top clearfix">
					<div class="add_cg fl">
						<h3>商品已成功加入到购物车!</h3>
					</div>
					<div class="fr">
						<a class="add_btn" href="${path}/cart/index">去购物车结算</a> <span>您还可以<a class="dd_color" href="javascript:history.back();">继续购物</a></span>
					</div>
				</div>
				<div class="add_in" style="display: none; line-height: 22px;">
					<div class="shop_title">
						<p>购买该商品的用户还购买了</p>
					</div>
					<div class="shop_con clearfix">
						<ul>
						</ul>
					</div>
				</div>
				<div class="add_in" style="display: none;">
					<div class="shop_title">
						<p>您可能还需要以下商品</p>
					</div>
					<div class="shop_con clearfix">
						<ul>
						</ul>
					</div>
				</div>
			</div>
			<div class="add_right fr" style="display: none; height: auto;">
				<p>
					<i></i>我的购物车
				</p>
				<dl class="bb_d clearfix">
					<dt style="display: none;">刚加入购物车的商品</dt>
					
				</dl>
				<div class="total">
					共<strong class="red">0</strong>件商品<br /> 金额总计：<strong class="red">0.00</strong>
				</div>
				<div class="btns">
					<a class="add_btn" href="${path}/cart/index">去购物车结算</a>
				</div>
			</div>
		</div>
	</div>
</body>