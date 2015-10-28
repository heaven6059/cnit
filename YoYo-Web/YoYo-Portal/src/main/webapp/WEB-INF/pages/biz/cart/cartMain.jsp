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
    <title>购物车</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="imagetoolbar" content="no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
	<link href="${path}/resources/styles/cart/cart2.css?v=${versionVal}" type="text/css" rel="stylesheet">
	<link href="${path}/resources/styles/cart/sortedDynamic.css?v=${versionVal}" type="text/css" rel="stylesheet">
	<script type="text/javascript">var _path="${path}"</script>
	<script src="${path}/resources/scripts/biz/cart/cartIndex.js?v=${versionVal}"></script>
	<style type="text/css">
	.shop {font-weight: bold;height: 30px;line-height: 30px;padding-left: 11px;}
	.cart-checkbox {float: left;margin-right: 5px;position: relative;z-index: 3;}
	.shop .shop-txt {float: left;width: 538px;}
	.jdcheckbox {float: none;margin: 8px 12px 0 1px;padding: 0;position: relative;vertical-align: middle;z-index: 5;}
	.shop .self-shop-name {color: #e4393c;height: 18px;line-height: 99em;overflow: hidden;}
	.shop .shop-name {display: inline-block;height: 30px;line-height: 30px;max-width: 144px;overflow: hidden;vertical-align: middle;}
	a {color: #666;text-decoration: none;}
	</style>
</head>
<body>
<div id="main_container" class="cart" style="width: 1200px !important; margin:auto; width: auto; height: auto;">

<div class="add_con">
	<div class="cartList">
	<div class="cart_filter">
    	<div class="switch fl">
        	<strong class="orange">全部商品<span></span></strong>
        </div>
    </div>
    <div class="w">
        <div class="cart_main">
            <div class="cart_thead">
                <div class="t_checkbox_all" style="padding-left: 10px">
                    <input type="checkbox" class="CheckedAll" id="CheckedAll1" />
                    <span>全选</span>
                </div>
                <div style=" margin-left: 80px;" class="t_goods">商品</div>
                <div class="t_price">单价(元)</div>
                <div class="t_quantity">数量</div>
                <div class="t_sum">小计(元)</div>
                <div class="t_action">操作</div>
            </div>
            <div class="car_list">
            	<c:forEach var="cartList" items="${cartList}">
				<div id="store_${cartList.store.storeId}" class="shop-goods">
					<div class="shop">
						<div class="cart-checkbox">
							<input type="checkbox" class="jdcheckbox" name="checkShop"/>
						</div>
						<span class="shop-txt">
							<a href="${portalPath}/shop/index?companyId=${cartList.store.companyId}" target="_blank" class="shop-name self-shop-name">${cartList.store.storeName}</a>
						</span>
					</div>
					<c:forEach var="product" items="${cartList.cartProducts}">
					<div id="product_${product.productId}" class="item_list">
						<div class="item_single">
							<div class="p_checkbox fl">
								<div style="width: 25px;" class="t_checkbox">
									<input type="checkbox" value="${product.productId}" name="proItem_140" data-name="${product.name}" data-app="${product.goodsId},${product.appointment},${product.appointmentIndex}" class="cr" />
								</div>
							</div>
							<div class="p_goods fl">
								<div class="goods_item">
									<div style="margin-right: auto;" class="p_img fl">
										<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${product.goodsId}">
										<img width="80" height="80" alt="${product.name}" src="${imagePath}${product.picturePath}">
										</a>
									</div>
									<div class="item_msg fr">
										<div style="height: 80px;" class="p_name">
											<a style="height: 40px; overflow: hidden; display: block;" target="_blank" title="${product.name}" href="${portalPath}/goodsManager/goodsIndex?goodsId=${product.goodsId}">${product.name}</a>
											<p style="height: 40px; overflow: hidden;" title="">${product.specInfo}</p>
										</div>
										<div class="p_extend"></div>
									</div>
								</div>
							</div>
							<div class="p_price fl">
								<span>${product.price}</span>
							</div>
							<div class="p_quantity fl">
								<div class="quantity_form">
									<a href="javascript:;" class="rem_btn fl">-</a>
									<input id="changeQuantity_${product.productId}" back="6" value="${product.quantity}" class="fl itxt">
									<a href="javascript:;" class="add_btn fr">+</a>
								</div>
								<div style="text-align: center;color: #aaa;">
								<c:choose>
									<c:when test="${product.disabled==1||product.marketable!=1}">失效</c:when>
									<c:when test="${product.store>0}">有货</c:when>
									<c:when test="${product.store<=0}">无货</c:when>
								</c:choose>
								</div>							
							</div>
							<div class="p_sum fl">
								<strong>${product.price}</strong>
							</div>
							<div class="p_ops fr">
								<a class="cart-remove" id="remove_${product.productId}" href="javascript:;">删除</a>
								<a id="wishList_${product.productId}" href="javascript:;">移到我的关注</a>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
				</c:forEach>
			</div>
        </div>
    	<div class="cart_floatbar">
        	<div class="select_all fl">
                <div class="t_checkbox_all">
                    <input type="checkbox" class="CheckedAll" id="CheckedAll2" />
                    <span>全选</span>
                </div>
            </div>
            <div class="operation fl" style="width: 330px;">
            	<a id="deleteAll" href="javascript:;">删除选中的商品</a>
            	<a href="javascript:;" onclick="addWishList()">移到我的关注</a>
            	<a href="javascript:;" onclick="removeDisabled()">清空失效商品</a>
            </div>
            <div class="toolbar_right fr" style="width: 799px;">
            	<div class="btn_area">
                	<a href="javascript:;">去结算</a>
                </div>
                <div class="price_sum">
                	<span class="txt">总价(不含运费):</span>
                    <span class="price sumPrice">
                    	<em class="red">￥0.00</em>
                    </span>
                    <br>
                </div>
                <div class="amount_sum">
                	已选择<em class="red">0</em>件商品
                </div>
            </div>
        </div>
    </div>
    
    </div>
    <div class="w" id="extendPart" style="display:none;">
    	<div class="tab_nav clearfix">
        	<ul>
            	<li id="lick" class="" style="display:none;">猜你喜欢</li>
                <li id="gz" class="" style="display:none;">我的关注</li>
                <li id="liu" class="tab_cur" style="display:none;">最近浏览</li>
            </ul>
        </div>
        <div class="tab_con">
        	<a class="arrow_l" href="javascript:;"></a>
        	<a class="arrow_r" href="javascript:;"></a>
            <div id="lick_select" class="p_list_cont">
                <div class="v_content_list" style="width: 3258px; height: 248px; position: absolute; left: 0; ">
                    
                </div>
            </div>
            <div id="gz_select" class="p_list_cont">
                <div class="v_content_list" style="width: 3258px; height: 248px; position: relative; left: 0; ">
                  
                </div>
            </div>
            <div id="liu_select" class="p_list_cont sort_show">
                <div class="v_content_list" style="width: 3258px; height: 248px; position: relative; left: 0; ">
                 
                </div>
            </div>
        </div>
    </div>
</div>

</div>
</body>
