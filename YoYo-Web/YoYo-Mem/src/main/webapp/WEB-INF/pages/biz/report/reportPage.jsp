<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
   int productId =  Integer.parseInt(request.getParameter("productId"));
   request.setAttribute("productId", productId);
   int goodsId =  Integer.parseInt(request.getParameter("goodsId"));
   request.setAttribute("goodsId", goodsId);
   String name =  request.getParameter("name");
   request.setAttribute("name", name);
   String picturePath =  request.getParameter("picturePath");
   request.setAttribute("picturePath", picturePath);
   String storeName =  request.getParameter("storeName");
   request.setAttribute("storeName", storeName);
   Long storeId =Long.parseLong(request.getParameter("storeId"));
   request.setAttribute("storeId", storeId);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>举报管理</title>
<%@include file="/resources/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/complain.css" />

<link rel="stylesheet" href="${path}/resources/scripts/imageUpload/zyUpload.css"	type="text/css">
<!-- 引用核心层插件 -->
<script type="text/javascript" >
var _path = "${path}"; 
</script>
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script src="${path}/resources/scripts/biz/common.js"></script>
<script src="${path}/resources/scripts/biz/order/complainPage.js"></script>
</head>
<body>
	<div class="member-main">
		<div class="title title2">举报管理</div>
		<div class="progress-bar at-step-0">
			<ol class="four-steps three-step">
				<li class="step-item step-0"><strong>1</strong>
					<h2 style="width: 255px;">申请举报</h2></li>
				<li class="step-item step-1"><strong>2</strong>
					<h2 style="width: 255px;">平台介入处理</h2></li>
				<li class="step-item step-2 step-3"><strong>3</strong>
					<h2 style="width: 267px;">举报完成</h2></li>
			</ol>
		</div>
		<div class="FormWrap"
			style="background-color: #FFFFFF; border: 0 none; padding: 0;">
			<div class="col-main">
				<div class="main-wrap">
					<form class="boxbase J_BoxTab form-edit J_FormEdit" id = "formId">
						<input type="hidden" name="source" value="buyer"> 
						<input type="hidden" name="fromMemberId" value="${order.member.memberId}"> 
					    <input type="hidden" name="fromUname" value="${order.member.name}"> 
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
									<b>*</b><label>举报原因：</label>
								</div>
								<div class="info J_ReasonBox">
									<label style="margin-right: 10px;">
									      <input type="radio" name="reason" value="false" checked="checked" class="J_ReasonId" >虚假信息
									</label> 
									<label style="margin-right: 10px;">
									     <input type="radio"  name="reason" value="unconformity" class="J_ReasonId" >图片不符
									</label>
								</div>
							</div>
							<div class="row-item first-row-item">
								<div class="ctitle">
									<label>手机：</label>
								</div>
								<div class="info J_ReasonBox">
									<input style="height: 20px;"  name="mobile" class="J_Phone">
								</div>
							</div>
							<div class="row-item first-row-item clear-fix">
								<div class="ctitle">
									<b>*</b><label>留言：</label>
								</div>
								<div class="info">
									<textarea class="introduction J_FormVerify_Textarea J_FormVerify" name="comment" placeholder="留言不能为空！"></textarea>
									<div style="padding-left: 236px;" class="help-msg">
										<strong style="padding-left: 115px;" class="text-count J_TextCount">300</strong>/300字
									</div>
								</div>
							</div>
						</div>
								<div >
									<div >
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
									</div>
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
							<span>商品信息</span>
						</h4>
					</div>
					<div class="box-bd">
						<div class="row-item first-row-item">
							<div class="ctitle">
								<a alt="img" href="${portalPath}/goodsManager/goodsIndex?goodsId=${reportDTO.goodsId}" target="_blank">
								 <img src="http://10.255.8.17:8082/imageUrl${reportDTO.picturePath}" class="goods-img"  
									alt="${reportDTO.name}">
								</a> <label class=""></label>
							</div>
							<div class="info">
								<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${reportDTO.goodsId}" class="text-link">${reportDTO.name}</a> <br>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>卖家：</label>
							</div>
							<div class="info">
								<a target="_blank" href="#">${reportDTO.storeName}</a> <span>
								</span>
							</div>
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