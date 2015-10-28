<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的积分</title>
<%@include file="/resources/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/complain.css?v=${versionVal}" />
<link rel="stylesheet" href="${path}/resources/scripts/imageUpload/zyUpload.css"	type="text/css">
<link type="text/css" href="${path}/resources/styles/formValidate/formitem.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form-1.0.1.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form.valid-1.0.1.js"></script>
<!-- 引用核心层插件 -->
<script type="text/javascript" >
var _path = "${path}"; 
</script>
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
<script src="${path}/resources/scripts/biz/order/complainPage.js?v=${versionVal}"></script>
</head>
<body>
	<div class="member-main">
		<div class="title title2">投诉管理</div>
		<div class="progress-bar at-step-0">
			<ol class="four-steps three-step">
				<li class="step-item step-0"><strong>1</strong>
					<h2 style="width: 255px;">申请投诉</h2></li>
				<li class="step-item step-1"><strong>2</strong>
					<h2 style="width: 255px;">平台介入处理</h2></li>
				<li class="step-item step-2 step-3"><strong>3</strong>
					<h2 style="width: 267px;">投诉完成</h2></li>
			</ol>
		</div>
		<div class="FormWrap"
			style="background-color: #FFFFFF; border: 0 none; padding: 0;">
			<div class="col-main">
				<div class="main-wrap">
					<form class="boxbase J_BoxTab form-edit J_FormEdit" id = "formId">
						<input type="hidden" name="source" value="buyer"> 
						<input type="hidden" name="toMemberId" value="${order.store.memberId}"> 
					    <input type="hidden" name="toUname" value="${order.store.shopName}"> 
					    <input type="hidden" name="orderId" value="${order.orderId}"> 
						<input type="hidden" name="storeId" value="${order.storeId}">
					    <input type="hidden" name="storeName" value="${order.store.storeName}">
					    <input type="hidden" name="status" value="intervene">
					    <input type="hidden" name="disabled" value="false">
					    <input type="hidden" id = "imagePath" name="imagePath" >
						<div class="box-bd">
							<div class="row-item first-row-item">
								<div class="ctitle">
									<b>*</b><label>投诉类型：</label>
								</div>
								<div class="info J_ReasonBox">
									<label style="margin-right: 10px;">
									      <input type="radio" name="reason" value="after" checked="checked" class="J_ReasonId" >售后问题
									</label> 
									<label style="margin-right: 10px;">
									     <input type="radio"  name="reason" value="action" class="J_ReasonId" >行为违规
									</label>
									<label style="margin-right: 10px;">
									     <input type="radio"  name="reason" value="quality" class="J_ReasonId" >质量问题
									</label>									
								</div>
							</div>
							<div class="row-item first-row-item">
								<div class="ctitle">
									<b>*</b><label>手机：</label>
								</div>
								<div class="info J_ReasonBox">
									<input style="height: 20px;border:1px solid #AEAEAE;" id="mobile" maxlength="11"  name="mobile" class="J_Phone" required="true" >
								</div>
							</div>
							<div class="row-item first-row-item clear-fix">
								<div class="ctitle">
									<b>*</b><label>留言：</label>
								</div>
								<div class="info">
									<textarea class="introduction J_FormVerify_Textarea J_FormVerify" id = "comment" name="comment" placeholder="留言不能为空！" required="true"></textarea>
									<div style="padding-left: 150px;" class="help-msg">
										<strong style="padding-left: 115px;" class="text-count J_TextCount">300</strong>/300字
									</div>
								</div>
							</div>
						</div>
						<div style="margin-top: 30px">
							<div class="ctitle">
								<!-- <b>*</b> --><label>上传凭证：</label>
							</div>
							<div class="info" >
								<div class="goods-image-div" id="goods_picture"></div>
								<div style="clear: both;"></div>
							</div>
							<div >
								<div class="info">
									<div class="J_ImgHelpMsg help-msg">每张图片不超过2M，支持GIF，JPG，JPEG，PNG，BMP格式</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="box-bd">
							<div class="row-item first-row-item clear-fix">
								<div class="info">
									<button id="save_button" type="button" class="btn">提交</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			
					
			<div class="col-sub">
				<div id="J_BabyDetail" class="boxbase baby-detail J_BabyDetail">
					<div class="box-hd">
						<i class="hd-icon"></i>
						<h4>
							<span>宝贝信息</span>
						</h4>
					</div>
			   <c:forEach var="orderItem" items="${order.orderItems}">
					<div class="box-bd">
						<div class="row-item first-row-item">
							<div class="ctitle">
								<a alt="img" href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.product.goodsId}" target="_blank">
								 <img height="252" width="252" src="${orderItem.product.picturePath}" class="goods-img" alt="${orderItem.product.name}">
								</a> <label class=""></label>
							</div>
							<div class="info">
								<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.product.goodsId}" class="text-link">${orderItem.product.name}</a> <br>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>卖家：</label>
							</div>
							<div class="info">
								<a target="_blank" href="#">${order.store.storeName}</a> <span>
								</span>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>单价：</label>
							</div>
							<div class="info">
								<span><span class="price-thin">￥<fmt:formatNumber value="${orderItem.price}" pattern="#,#00.00#"/></span>元</span>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>数量：</label>
							</div>
							<div class="info">${orderItem.nums}</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>小计：</label>
							</div>
							<div class="info">
								<span><span class="price-bold">￥<fmt:formatNumber value="${orderItem.amount}" pattern="#,#00.00#"/></span>元</span>
							</div>
						</div>
						<div class="row-item last-row-item">
							<a target="_blank" href="../memberOrder/viewOrder?orderId=${order.orderId}" id="J_MoreBabyTrigger" class="more ">查看订单信息<s class="spt-icon"></s></a>
						</div>
					</div>
 		           </c:forEach>
					
					<div id="J_MoreBabyBox" class="box-bd  hidden" >
						<div class="row-item first-row-item">
							<div class="ctitle">
								<label>订单编号：</label>
							</div>
							<div class="info">
								<a target="_blank"
									href="../memberOrder/viewOrder?orderId=${order.orderId}"
									class="text-link">${order.orderId}</a>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>运费：</label>
							</div>
							<div class="info">
								<span><span class="price-bold">￥<fmt:formatNumber value="${order.costFreight}" pattern="#,#00.00#"/></span>元</span>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>总优惠：</label>
							</div>
							<div class="info">￥<fmt:formatNumber value="${order.discount + order.pmtGoods + order.pmtOrder}" pattern="#,#00.00#"/>元</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>总计：</label>
							</div>
							<div class="info">
								<span><span class="price-bold">￥<fmt:formatNumber value="${order.payed}" pattern="#,#00.00#"/></span>元</span> 
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>成交时间：</label>
							</div> 
							<div class="info"><fmt:formatDate value="${order.confirmTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" >
function  showOrHideOrder(){
	$("#J_MoreBabyBox").toggleClass("box-bd  hidden"); 
}
</script>		
</body>
</html>