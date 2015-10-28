<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>购物车</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="imagetoolbar" content="no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    
    <script src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
	<link href="${path}/resources/styles/cart/base.css?v=${versionVal}" type="text/css" rel="stylesheet">
	<link href="${path}/resources/styles/cart/cart.css?v=${versionVal}" type="text/css" rel="stylesheet">
	<link href="${path}/resources/styles/cart/cart2.css?v=${versionVal}" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
	<script type="text/javascript">var _path="${path}"</script>
	<script src="${path}/resources/scripts/biz/cart/cartIndex.js?v=${versionVal}"></script>
	
</head>
<body>
<div id="main_container" class="cart" style="width: 1200px !important; margin:auto; width: auto; height: auto;">
	<div class="w">
	<div class="cart-filter-bar ui-switchable-tab">
		<ul class="switch-cart">
			<li class="switch-cart-item curr ui-switchable-selected">
					<em>全部商品</em>
			</li>
		</ul>
		<div class="clr"></div>
	</div>		
	</div>

	<div class="cart-warp">
		
			<div class="w">	
				<div id="jd-cart">
	<div class="cart-main">
		<div class="cart-thead">
			<div class="column t-checkbox">
				<div class="cart-checkbox">
					<input checked="checked" id="toggle-checkboxes_up" name="toggle-checkboxes" class="jdcheckbox" clstag="clickcart|keycount|xincart|cart_allCheck" type="checkbox">
					<label class="checked" for="">勾选全部商品</label>
				</div>
				全选
			</div>
			<div class="column t-goods">商品</div>
			<div class="column t-props"></div>
			<div class="column t-price">单价(元)</div>
			<div class="column t-quantity">数量</div>
			<div class="column t-sum">小计(元)</div>
			<div class="column t-action">操作</div>
		</div>
		<input id="allSkuIds" value="1010727120,1286519385,1246904089,1017663587" type="hidden">
<input id="outSkus" value="" type="hidden">
<input id="isLogin" value="0" type="hidden">
<input id="hiddenLocationId" type="hidden">
<input id="hiddenLocation" type="hidden">
<input id="ids" value="1010727120,1286519385,1246904089,1017663587" type="hidden">
<!-- 修改数量之前的值 -->
<input id="changeBeforeValue" value="" type="hidden">
<input id="changeBeforeId" value="" type="hidden">
<input value="1" id="checkedCartState" type="hidden">
<input value="12586,87599,34736" id="venderIds" type="hidden">
<div id="cart-list">

			</div>
	</div>
</div>
<div id="cart-floatbar">
	<div style=" height: 52px;" class="ui-ceilinglamp-1"><!-- width: 990px; --><div style=" height: 50px;" class="cart-toolbar "><!-- fixed-bottom     width: 1903px; -->
		<div class="toolbar-wrap">
			<div style="display: none;" class="selected-item-list hide">
			</div>
			<div class="options-box">
				<div class="select-all">
					<div class="cart-checkbox">
						<input checked="checked" id="toggle-checkboxes_down" name="toggle-checkboxes" class="jdcheckbox" clstag="clickcart|keycount|xincart|cart_allCheckDown" type="checkbox">
						<label class="checked" for="">勾选全部商品</label>
					</div>
					全选
				</div>
				<div class="operation">
					<a href="javascript:;" clstag="clickcart|keycount|xincart|cart_somesku_del" class="remove-batch">删除选中的商品</a>
					<a href="#none" class="follow-batch" clstag="clickcart|keycount|xincart|cart_somesku_guanzhu">移到我的关注</a>	
				</div>
				<div class="clr"></div>
				<div class="toolbar-right">
					<div class="normal">
						<div class="comm-right">
							<div class="btn-area">
								<a data-bind="1" href="javascript:void(0);" clstag="clickcart|keycount|xincart|cart_gotoOrder" class="submit-btn ">
								去结算<b></b></a>
								<!-- <a href="javascript:void(0);" class="submit-btn submit-btn-disabled">
								去结算<b></b></a> -->
							</div>
							<div class="price-sum">
								<div>
									<span class="txt">总价（不含运费）：</span>
									<span class="price sumPrice"><em  id="totalPrice">￥0.00</em></span>
									<br>
									<%-- <span class="txt">已节省：</span>
									<span class="price totalRePrice">-￥0.00</span> --%>
								</div>
							</div>
							<div class="amount-sum">
								已选择<em id="goodsSum">0</em>件商品
							</div>
							<div class="clr"></div>
						</div>
					</div>
					<!-- <div style="display: none;" class="combine">
						<div class="comm-right">
							<div class="btn-area">
								<a href="javascript:void(0);" onclick="return false;" clstag="clickcart|keycount|xincart|cart_gotoOrderOut" class="jdInt-submit-btn">
								去全球购结算<b></b></a>
								<a href="javascript:void(0);" onclick="return false;" clstag="clickcart|keycount|xincart|cart_gotoOrder" class="common-submit-btn">
								去京东结算<b></b></a>
							</div>
							<div class="price-sum">
								<div>
									<span class="txt">总价（不含运费）：</span>
									<span class="price sumPrice"><em>￥127.50</em></span>
									<br>
									<span class="txt">已节省：</span>
									<span class="price totalRePrice">-￥0.00</span>
								</div>
							</div>
							<div class="amount-sum">
								已选择<em>4</em>件商品<b class="up"></b>
							</div>
							<div class="clr"></div>
						</div>
						<div class="clr"></div>
					</div> -->
				</div>
				
			</div>
		</div>
	</div></div>
</div>
<div class="cart-removed">
	<div class="r-tit">已删除商品，您可以重新购买或加关注：</div>
</div>			</div>
			
	</div>



</div>
<%-- <div class="preload"><img src="${path}/resources/images/cart/u0_normal.jpg" width="1" height="1"/><img src="${path}/resources/images/cart/u3_normal.png" width="1" height="1"/><img src="${path}/resources/images/cart/u7_normal.png" width="1" height="1"/><img src="${path}/resources/images/cart/u18_normal.png" width="1" height="1"/><img src="购物车_已登录__files/u24_normal.png" width="1" height="1"/><img src="${path}/resources/images/cart/u48_normal.png" width="1" height="1"/><img src="${path}/resources/images/cart/u50_normal.png" width="1" height="1"/><img src="${path}/resources/images/cart/u52_normal.png" width="1" height="1"/><img src="${path}/resources/images/cart/u70_normal.png" width="1" height="1"/><img src="${path}/resources/images/cart/u72_normal.png" width="1" height="1"/><img src="${path}/resources/images/cart/u50_normal.png" width="1" height="1"/><img src="已登录页首_files/u17_normal.png" width="1" height="1"/><img src="已登录页首_files/u22_normal.png" width="1" height="1"/><img src="已登录页首_files/u26_line.png" width="1" height="1"/><img src="已登录页首_files/u28_line.png" width="1" height="1"/><img src="已登录页首_files/u29_line.png" width="1" height="1"/><img src="已登录页首_files/u30_line.png" width="1" height="1"/><img src="已登录页首_files/u32_line.png" width="1" height="1"/><img src="已登录页首_files/u35_selected.png" width="1" height="1"/><img src="已登录页首_files/u35_normal.png" width="1" height="1"/><img src="页尾_files/u697_line.png" width="1" height="1"/><img src="页尾_files/u704_line.png" width="1" height="1"/><img src="页尾_files/u706_line.png" width="1" height="1"/><img src="页尾_files/u708_line.png" width="1" height="1"/><img src="页尾_files/u712_line.png" width="1" height="1"/><img src="页尾_files/u713_line.png" width="1" height="1"/><img src="页尾_files/u714_line.png" width="1" height="1"/><img src="页尾_files/u718_normal.png" width="1" height="1"/><img src="页尾_files/u720_normal.png" width="1" height="1"/><img src="页尾_files/u722_normal.png" width="1" height="1"/><img src="页尾_files/u724_normal.jpg" width="1" height="1"/></div> --%>
</body>
