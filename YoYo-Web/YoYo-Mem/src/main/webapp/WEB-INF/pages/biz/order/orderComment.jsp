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
<title>评论订单</title>
<%@include file="/resources/include/head.jsp" %>

<link rel="stylesheet" href="${path}/resources/styles/order/orderdetail.css?v=${versionVal}" type="text/css">

<!-- 引用核心层插件 -->
<script type="text/javascript" >
var _path = "${path}"; 
</script>
<!-- 引用控制层插件 -->
<script src="${path}/resources/scripts/biz/order/orderComment.js?v=${versionVal}"></script>

</head>
<body>
<div class="member-main member-main2">
	<div id="evalu01" class="mod-main mod-comm extra-main">
	    <div class="mt">
	        <h3>商品评价</h3>
	        <div class="extra-l ftx03 ml10">
	            <span id="tip-num"></span>
	        </div>
	    </div>
		<div class="mc">
			<table class="tb-void tb-line">
				<colgroup>
					<col width="500">
					<col width="170">
					<col width="">
				</colgroup>
				<thead>
					<tr>
						<th>商品信息</th>
						<th>购买时间</th>
						<th>评价状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${order}" varStatus="status">
					<tr>
						<td colspan="3">
							<ul  class="pro-info">
								<li class="fore1">
									<div class="p-info clearfix">
										<div class="p-img fl">
											<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${item.goodsId}" target="_blank">
												<img width="50" height="50" class="lazy" src="${item.picturePath}">
											</a>
										</div>
										<div class="p-name fl">
											<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${item.goodsId}" target="_blank">${item.productName}</a>
										</div>
									</div>
								</li>
								<li class="fore2"><span class="ftx03"><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd hh:mm:ss"/></span></li>
								<c:if test="${item.replyCount==0}">
								<li class="fore3 forem"><a class="pj" href="javascript:;">发表评价<b class="icon-show"></b></a></li>
								</c:if>
								<c:if test="${item.replyCount>0}">
								<li class="fore3 forem">已评价</li>
								</c:if>
							</ul>
							<div class="clr"></div>
							<form>
								<input type="hidden" name="orderId" value="${item.orderId}"/>
								<input type="hidden" name="orderItemId" value="${item.orderItemId}"/>
								<input type="hidden" name="productId" value="${item.productId}"/>
								<input type="hidden" name="goodsId" value="${item.goodsId}"/>
							</form>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div id="cmment_template" style="display: none;">
	<div class="comment-box prompt01">
		<div class="box-t"></div>
		<div class="form">
			<div class="item item01 titleEl">
				<span class="label"><em>*</em>标题：</span>
				<div class="tit">
					<input type="text" value="" class="text area01" style="width: 340px; color:#303030" name="title" id="title" autocomplete="off">
					<div class="clr"></div>
					<div class="msg-text ftx-03">4-20字</div>
				</div>
				<span class="msg-error-01 ml10 hide">麻烦填写4-20个字呦</span>
				<div class="clr"></div>
			</div>
	
			<div class="item scoreEl">
				<span class="label"><em>*</em>评分：</span>
				<div class="fl">
					<span class="commstar"> 
						<a _val="1" class="star1" href="javascript:;"></a> 
						<a _val="2" class="star2" href="javascript:;"></a> 
						<a _val="3" class="star3" href="javascript:;"></a> 
						<a _val="4" class="star4" href="javascript:;"></a> 
						<a _val="5" class="star5" href="javascript:;"></a>
					</span>
					<input type="hidden" id="commentStar" name="commentStar" />
					<div class="clr"></div>
				</div>
				<span class="msg-error-01 ml10 hide">你的评分是偶们前进的动力</span>
				<div class="clr"></div>
			</div>
	
			<div class="item item01 xindeEl">
				<span class="label"><em>*</em>心得：</span>
				<div class="cont">
					<textarea class="area area01" name="comment" id="comment"  onblur="if(this.value==''){this.value='商品是否给力？快分享你的购买心得吧~';this.style.color='#999'}" onfocus="if(this.value=='商品是否给力？快分享你的购买心得吧~'){this.value='';this.style.color='#000'}" style="color:#999">商品是否给力？快分享你的购买心得吧~</textarea>
                        <!--<textarea class="area area01" name="comment" id="comment" onfocus="if(this.value=='商品是否给力？快分享你的购买心得吧~') {this.value='';}this.style.color='#999';" onblur="if(this.value=='') {this.value='商品是否给力？快分享你的购买心得吧~';this.style.color='#000';}" >商品是否给力？快分享你的购买心得吧~</textarea> -->
					<div class="clr"></div>
					<span class="msg-error-01 hide">麻烦填写10-500个字呦</span>
					<div class="msg-text ftx-03">10-500字</div>
				</div>
				<div class="clr"></div>
			</div>
	
			<div class="item item02">
				<span class="label">&nbsp;</span>
				<div class="fl">
					<a class="btn-5 mr20 submitform" href="#none"><s></s><span class="pingjiaEl">评价</span></a>
				</div>
				<div class="clr"></div>
			</div>
		</div>
	</div>
</div>
</body>
</html>