<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<%-- <link type="text/css" href="${path}/resources/styles/shopcenter.css" rel="stylesheet" /> --%>
<!--面包屑导航-->
<div class="root_nav">
	<div class="root_navin">
    	<span><a href="javascript:;">首页</a></span>&nbsp;>&nbsp;
    	<span><a href="javascript:;">买家中心</a></span>
    </div>
</div>
<!--侧导航-->
	<div class="sidebar fl">
        <p>买家中心</p>
        <div class="buy_sid">
            <ul class="listUl">
                <li class="listLi">
                    <a href="javascript:;" class="myTitle">关注中心</a>
                    <ul>
                        <li><a href="${path}/productWishList/toWishList">关注的商品</a></li>
                        <li><a href="${path}/storeWishList/toWishList">关注的店铺</a></li>
                        <li><a href="${path}/activityWishList/toWishList">关注的活动</a></li>
                    </ul>
                </li>
                <li class="listLi">
                    <a href="javascript:;" class="myTitle">我的咨询</a>
                    <ul>
                        <li><a href="${path}/memberComment/toMemberComment">我的评论</a></li>
                        <li><a href="${path}/memberConsult/toMemberConsult">我的咨询</a></li>
                        <li><a href="${path}/memberViewHistory/toMemberViewHistory">浏览历史</a></li>
                        <li><a href="${path}/requirement/myRequirement">我的需求</a></li>
                    </ul>
                </li>
                <li class="listLi">
                    <a href="javascript:;" class="myTitle">我的购买</a>
                    <ul>
						<li><a href="${path}/mypainting/myDamageList">我的钣金</a></li>
                        <li><a href="${path}/shoppingHistory/toShoppingHistory">我最近购买的商品</a></li>
                    </ul>
                </li>
                <li class="listLi">
                    <a href="javascript:;" class="myTitle">交易管理</a>
                    <ul>
                        <li><a href="${path}/memberOrder/orderList">我的订单</a></li>
                        <li><a href="${path}/reship/refundsList">我的退款</a></li>
                        <li><a href="${path}/mypainting/orderList">钣金订单</a></li>
                        <li><a href="${path}/memberCoupon/memberCoupon?type=1">我的优惠券</a></li>
                        <li><a href="${path}/memberCoupon/memberCoupon?type=2">我的代金券</a></li>
                        <li><a href="${path}/advance/getAdvanceListPage">我的预存款</a></li>
                        <li><a href="${path}/advance/advanceRecharge">预存款充值</a></li>
						<li><a href="${path}/memberMsg/memberMsgList">站内信(0)</a></li>
						<li><a href="${path}/memberOrder/orderRecycleBin">回收站</a></li>
						<li><a href="${portalPath}/cart/index">我的购物车</a></li>
                        <li><a href="${path}/goodsVirtualItems/getGoodsVirtualItemsListPage">我购买的虚拟商品</a></li>
                    </ul>
                </li>
                <li class="listLi">
                    <a href="javascript:;" class="myTitle">账户中心</a>
                    <ul>
                        <li><a href="${path}/personInfo/getPersonInfo">个人信息</a></li>
                        <li><a href="${path}/point/getPointListPage">我的积分</a></li>
                        <li><a href="javascript:;">积分兑换</a></li>
                        <li><a href="${path}/membercar/memberCarList">我的车型</a></li>
                        <li><a href="${path}/accountsecurity/getAccountSecurityPage">账号安全管理</a></li>
                        <li><a href="${path}/pamAuth/getPamAuthListPage">绑定其他账号</a></li>
                    </ul>
                </li>
				<li class="listLi">
                    <a href="javascript:;" class="myTitle">售后管理</a>
                    <ul>
                        <li><a href="${path}/reship/applyReship">售后申请</a></li>
                        <li><a href="${path}/reship/getReshipListPage">售后申请记录</a></li>
                    </ul>
                </li>
                <li class="listLi">
                    <a href="javascript:;" class="myTitle">维权管理</a>
                    <ul>
                        <li><a href="${path}/complain/getComplainListPage">投诉管理</a></li>
                        <li><a href="${path}/report/reportList">举报管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
	<!--侧导航 end-->




