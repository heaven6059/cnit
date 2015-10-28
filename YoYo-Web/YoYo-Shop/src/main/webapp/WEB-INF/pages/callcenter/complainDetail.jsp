<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投诉管理</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/callcenter/complain.css?v=${versionVal}" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/popup/magnific-popup.css" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/popup/jquery.magnific-popup.min.js"></script>
<link rel="stylesheet" href="${path}/resources/styles/zyUpload.css"	type="text/css">
<!-- 引用核心层插件 -->
<script type="text/javascript" >
var _path = "${path}"; 
var orderId = "${order.orderId}";
</script>
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script src="${path}/resources/scripts/biz/callcenter/complainDetail.js?v=${versionVal}"></script>
</head>
<body>
	<div class="member-main">
		<div class="title title2">投诉管理</div>
		<div class="progress-bar <c:if test="${order.status eq 'intervene'}">at-step-1</c:if><c:if test="${order.status eq 'success' || order.status eq 'error' || order.status eq'cancel'}">at-step-2</c:if>">
			<ol class="four-steps three-step">
				<li class="step-item step-0">
					<strong>1</strong>
					<h2 style="width: 255px;">申请投诉</h2>
				</li>
				<li class="step-item step-1">
					<strong>2</strong>
					<h2 style="width: 255px;">平台介入处理</h2>
				</li>
				<li class="step-item step-2 step-3">
					<strong>3</strong>
					<h2 style="width: 267px;">投诉完成</h2>
				</li>
			</ol>
		</div>
		<div class="FormWrap"
			style="background-color: #FFFFFF; border: 0 none; padding: 0;">
			<div class="col-main">
				<div class="main-wrap">
					<div class="progress-remind">
						<s class="icon warn-icon"></s>
						<c:if test="${order.status eq 'cancel'}">
							<h4>已撤销投诉</h4>
						</c:if>
						<c:if test="${order.status eq 'intervene'}">
							<h4>审核中，请耐心等待</h4>
						</c:if>
						<c:if test="${order.status eq 'success'}">
							<h4>投诉成功</h4>
						</c:if>
						<c:if test="${order.status eq 'error'}">
							<h4>投诉不成立</h4>
						</c:if>
					</div>
					<div class="boxbase msg-board J_MsgBoard">
						<div class="box-bd">
							<h4>
								<span>留言板 </span>
							</h4>
							<c:if test="${order.status eq 'intervene'}">
							<div class="box-act J_BoxAct">
								<a href="javascript:;" id="J_ToggleMsgAction" class="more" onclick = "showHideMessageBoard()">我要留言<b></b></a>
							</div>
							</c:if>
							<form id="formId" class="box-action  hidden" >
									<input type="hidden" name="complainId" value="${order.complainId}">
									<input type="hidden" name="source" value="seller"> 
									<input type="hidden" name="authorId" value="${memberListDo.memberId}"> 
								    <input type="hidden" name="author" value="${memberListDo.loginName}"> 
								    <input type="hidden" name="disabled" value="false">
								    <input type="hidden" id = "imagePath" name="imagePath" > 
								<div class="J_MemoWrap memo-wrap">
									<textarea name="comment" placeholder="留言不能为空" class="msg-textarea J_MsgTextarea J_FormVerify" ></textarea>
									<div class="text-count hide">
										<span class="msg-error">麻烦填写10-300个字</span>
									</div>
								</div>
								<div style="margin-top: 30px">
									<div class="ctitle">
										<!-- <b>*</b> -->
										<label>上传凭证：</label>
									</div>
									<div class="info">
										<div class="goods-image-div" id="goods_picture"></div>
									</div>
									<div class="info">
										<div class="J_ImgHelpMsg help-msg">每张图片不超过2M，支持GIF，JPG，JPEG，PNG，BMP格式</div>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="J_MemoWrap" style="height: 25px;">
									<a class="submit-btn J_SubmitBtn" id = "save_button">发表留言</a>
								</div>
							</form>
						</div>
						
						<div class="J_MsgDetail">
						<c:if test="${fn:length(order.complainComments)>0}">
						  <c:forEach items="${order.complainComments}" var="complainComment">
							<div class="row-item first-row-item odd">							
								<c:if test="${complainComment.source=='buyer'}"><div class="ctitle avator">买家：</div></c:if>
								<c:if test="${complainComment.source=='seller'}"><div class="ctitle avator">自己：</div></c:if>
								<div class="info">
									<div class="action">
										<span> ${complainComment.author}</span> &nbsp;&nbsp;(<span class="time"><fmt:formatDate value="${complainComment.lastModified}" pattern="yyyy-MM-dd HH:mm:ss" /></span>)
									</div>
									<div class="action-desc" >
										<label></label>
										<div class="desc" style="text-indent: 2em;">${complainComment.comment}</div>
									</div>
									<c:if test="${!empty complainComment.imagePath}">
									<c:forEach var="image" items="${fn:split(complainComment.imagePath,',')}">
									<div class="pics J_Pics" style="float: left;">
										<a style="width:193px;height:125px;" title="" href="${image}">
											<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${image}">											
										</a>
									</div>
									</c:forEach>
									</c:if>
									<s class="arrow"></s>
								</div>
							</div>
						 </c:forEach>
						 </c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sub">
				<div class="request-info boxbase J_RequestInfoWrap ">
					<s class="box-tp"><b></b></s>
					<div class="box-hd">
						<i class="hd-icon"></i>
						<h4>
							<span>投诉内容</span>
						</h4>						
					</div>
					<div class="box-bd J_RequestInfo">
						<div class="row-item ">
							<div class="ctitle">
								<label>投诉编号：</label>
							</div>
							<div class="info nowrap">${order.complainId}</div>
						</div>
						<div class="row-item ">
							<div class="ctitle">
								<label>投诉类型：</label>
							</div>
							<c:if test="${order.reason == 'action'}">
								<div class="info">行为违规</div>
							</c:if>
							<c:if test="${order.reason == 'after'}">
								<div class="info">售后问题</div>
							</c:if>
							<c:if test="${order.reason == 'quality'}">
								<div class="info">质量问题</div>
							</c:if>
						</div>
						<div class="row-item ">
							<div class="ctitle">
								<label>投诉状态：</label>
							</div>
							<c:if test="${order.status == 'intervene'}">
								<div class="info">平台介入</div>
							</c:if>
							<c:if test="${order.status == 'success'}">
								<div class="info">投诉成立</div>
							</c:if>							
							<c:if test="${order.status == 'error'}">
								<div class="info">投诉不成立</div>
							</c:if>
							<c:if test="${order.status == 'cancel'}">
								<div class="info">投诉撤销</div>
							</c:if>							
						</div>
						<div class="row-item ">
							<div class="ctitle">
								<label>被投诉方：</label>
							</div>
							<div class="info report_text" title="${order.storeName }">${order.storeName }</div>
						</div>
						<div class="row-item ">
							<div class="ctitle">
								<label>申请时间：</label>
							</div>
							<div class="info normalwrap"><fmt:formatDate value="${order.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
						</div>
					</div>

				</div>
				<div id="J_BabyDetail" class="boxbase baby-detail J_BabyDetail">
					<div class="box-hd">
						<i class="hd-icon"></i>
						<h4>
							<span>宝贝信息</span>
						</h4>
					</div>
					<c:forEach var="orderItem" items="${order.items}">
					<div class="box-bd">
						<div class="row-item first-row-item">
							<div class="ctitle">
								<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.goodsId}"> 
									<img width="252px" height="252px" src="${orderItem.picturePath}" class="goods-img" alt="${orderItem.name}">
								</a> 
								<label class=""></label>
							</div>
							<div class="info">
								<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.goodsId}" class="text-link">${orderItem.name}</a> <br>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>卖家：</label>
							</div>
							<div class="info report_text">
								<a target="_blank" title="${order.companyName}" href="${portalPath}/shop/index?companyId=${order.companyId}">${order.companyName}</a>
								<span>
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
							<a  href="${path}/shopOrder/viewOrder?orderid=${orderItem.orderId}" target="_blank" id="J_MoreBabyTrigger" class="more">查看订单信息<s class="spt-icon"></s></a>
						</div>
					</div>
					</c:forEach >
				</div>
					<div id="J_MoreBabyBox" class="box-bd  hidden">
						<div class="row-item first-row-item">
							<div class="ctitle">
								<label>订单编号：</label>
							</div>
							<div class="info">
								<a target="_blank"
									href=""
									class="text-link">${order.orderId}</a>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>总优惠：</label>
							</div>
							<div class="info">￥0.00元</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>总计：</label>
							</div>
							<div class="info">
								<span><span class="price-bold">￥<fmt:formatNumber value="${order.finalAmount}" pattern="#,#00.00#"/></span>元</span>
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
<script type="text/javascript">
function  showOrHideOrder(){
	$("#J_MoreBabyBox").toggleClass("box-bd  hidden"); 
}

function  showHideMessageBoard(){
	$("#formId").toggleClass("hidden"); 
}
</script>
</body>
</html>