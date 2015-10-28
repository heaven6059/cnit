<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<link type="text/css" href="${path}/resources/styles/shopcenter.css" rel="stylesheet" />

<div class="shop-sidebar shop-sidebar2">
	<div class="shop-menu">
		<img src="${path}/resources/images/member/memberCenter.png" />
		<ul class="body">
			<li class="shop-menu-list">
				<div class="list-title-bg noborder">
					<h2 class="list-title-icon m-7">关注中心</h2>
				</div>
				<ul>
					<li><a href="#">关注的商品</a></li>
					<li><a href="#">关注的店铺</a></li>
					<li><a href="#">关注的活动</a></li>
				</ul>
			</li>
			
			<li class="shop-menu-list">
				<div class="list-title-bg">
					<h2 class="list-title-icon m-9">我的咨询</h2>
				</div>
				<ul>
					<li><a href="${path}/goodsPublish/index">我的评论</a></li>
					<li><a href="#">我的咨询</a></li>
					<li><a href="#">浏览历史</a></li>
				</ul>
			</li>
			<li class="shop-menu-list">
				<div class="list-title-bg">
					<h2 class="list-title-icon m-10">我的购买</h2>
				</div>
				<ul>
					<li><a target="_self" href="#">我最近购买的商品</a></li>
				</ul>
			</li>
			<li class="shop-menu-list">
				<div class="list-title-bg">
					<h2 class="list-title-icon m-12">交易管理</h2>
				</div>
				<ul>
					<li><a href="${path}/memberOrder/orderList">我的订单</a><li><a href="#">退款退货管理</a></li></li>
					<li><a href="#">我的优惠券</a></li>
					<li><a href="#">我购买的虚拟商品</a></li>
					<li><a href="#">我的预存款</a></li>
					<li><a href="#">预存款充值</a></li>
					
					<li><a href="#">站内信（0）</a></li>
					<li><a href="#">我的购物车</a></li>
				</ul>
			</li>
			<li class="shop-menu-list">
				<div class="list-title-bg">
					<h2 class="list-title-icon m-10">账户中心</h2>
				</div>
				<ul>
					<li><a href="${path}/personInfo/getPersonInfo">个人信息</a></li>
					<li><a href="#">我的积分</a></li>
					<li><a href="#">积分兑换</a></li>
					<li><a href="#">我的车型</a></li>
					<li><a href="#">账号安全管理</a></li>
					<li><a href="#">绑定其他账号</a></li>
				</ul>
			</li>
			<li class="shop-menu-list">
				<div class="list-title-bg">
					<h2 class="list-title-icon m-10">维权管理</h2>
				</div>
				<ul>
					<li><a href="#">投诉管理</a></li>
					<li><a href="#">举报</a></li>
				</ul>
			</li>
			
		</ul>
	</div>
</div>