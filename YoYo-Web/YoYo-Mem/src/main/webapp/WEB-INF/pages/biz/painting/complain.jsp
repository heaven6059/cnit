<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>投诉卖家</title>
<%@include file="/resources/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/complain.css" />
<link rel="stylesheet" href="${path}/resources/scripts/imageUpload/zyUpload.css"	type="text/css">
<link type="text/css" href="${path}/resources/styles/formValidate/formitem.css" rel="stylesheet" />
<style type="text/css">
	.form_item_input {
	  border: 1px solid #AEAEAE;
  	}
</style>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form-1.0.1.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form.valid-1.0.1.js"></script>
<script type="text/javascript" >var _path = "${path}"; </script>
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script src="${path}/resources/scripts/biz/common.js"></script>
<script src="${path}/resources/scripts/biz/painting/complain.js"></script>
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
						<input type="hidden" name="toMemberId" value="${order.memberId}"> 
					    <input type="hidden" name="toUname" value="${order.store.shopName}"> 
					    <input type="hidden" name="orderId" value="${order.id}"> 
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
									<input style="height: 20px;" id="mobile" maxlength="11"  name="mobile" class="J_Phone" required="true">
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
		</div>
	</div>
</body>
</html>