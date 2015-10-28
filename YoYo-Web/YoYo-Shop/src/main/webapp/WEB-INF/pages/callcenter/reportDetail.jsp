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
<title>举报管理</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/callcenter/complain.css?v=${versionVal}" />
<link rel="stylesheet" href="${path}/resources/styles/zyUpload.css"	type="text/css">
<!-- 引用核心层插件 -->
<script type="text/javascript" >
var _path = "${path}"; 
</script>
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script src="${path}/resources/scripts/biz/callcenter/reportDetail.js"></script>
</head>
<body>
	<div class="member-main">
		<div class="title title2">举报管理</div>
		<div class="progress-bar <c:if test="${reportDTO.status == 'voucher'||reportDTO.status == 'intervene'}">at-step-1</c:if><c:if test="${reportDTO.status == 'success'||reportDTO.status == 'error'||reportDTO.status == 'cancel'||reportDTO.status == 'finish'}">at-step-2</c:if>">
			<ol class="four-steps three-step">
				<li class="step-item step-0"><strong>1</strong>
					<h2 style="width: 255px;">申请举报</h2>
				</li>
				<li class="step-item step-1"><strong>2</strong>
					<h2 style="width: 255px;">平台介入处理</h2>
				</li>
				<li class="step-item step-2 step-3"><strong>3</strong>
					<h2 style="width: 267px;">举报完成</h2>
				</li>
			</ol>
		</div>
		<div class="FormWrap" style="background-color: #FFFFFF; border: 0 none; padding: 0;">
			<div class="col-main">
				<div class="main-wrap">
					<div class="progress-remind">留言：${reportDTO.memo}</div>
					<div class="progress-remind">
						<s class="icon warn-icon"></s>
						<h4>
							状态：<c:if test="${reportDTO.status == 'intervene'}">平台介入</c:if>
							<c:if test="${reportDTO.status == 'voucher'}">取证中</c:if>
							<c:if test="${reportDTO.status == 'success'}">举报成立</c:if>							
							<c:if test="${reportDTO.status == 'error'}">举报不成立</c:if>
							<c:if test="${reportDTO.status == 'cancel'}">举报撤销</c:if>
							<c:if test="${reportDTO.status == 'finish'}">完成</c:if>
						</h4>
					</div>
					<div class="boxbase msg-board J_MsgBoard">

						<div class="box-bd">
							<h4>
								<span>留言板 </span>
							</h4>
							<div class="box-act J_BoxAct">
								<a href="javascript:;" id="J_ToggleMsgAction" class="more" onclick = "showHideMessageBoard()">我要留言<b></b></a>
							</div>
							<form id="formId" class="box-action  hidden" >
									<input type="hidden" id="reportId" name="reportId" value="${reportDTO.reportId}">
									<input type="hidden" name="source" value="seller"> 
								    <input type="hidden" name="disabled" value="false">
								    <input type="hidden" id = "imagePath" name="imagePath" > 
								<div class="J_MemoWrap memo-wrap">
									<textarea name="comment" placeholder="留言不能为空"
										class="msg-textarea J_MsgTextarea J_FormVerify" ></textarea>
									<div class="text-count hide">
										<span class="msg-error">麻烦填写10-300个字</span>
									</div>
								</div>
								<div style="margin-top: 30px">
									<div class="ctitle">
										<label>上传凭证：</label>
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
								<div class="J_MemoWrap" style="height: 25px;">
									<a class="submit-btn J_SubmitBtn" id = "save_button">发表留言</a>
								</div>
							</form>
						</div>
						<c:if test="${fn:length(reportDTO.reportComments)>0}">
						<div class="J_MsgDetail">
						  <c:forEach items="${reportDTO.reportComments}" var="reportComment">
							<div class="row-item first-row-item odd">
							    <c:if test="${reportComment.source=='buyer'}"><div class="ctitle avator">买家：</div></c:if>
								<c:if test="${reportComment.source=='seller'}"><div class="ctitle avator">自己：</div></c:if>
								<div class="info">
									<div class="action">
										<span> ${reportComment.author}</span> &nbsp;&nbsp;(<span class="time"><fmt:formatDate value="${reportComment.lastModified}" pattern="yyyy-MM-dd HH:mm:ss" /></span>)
									</div>
									<div class="action-desc" style="padding: 5px 0px;">
										<div class="desc">${reportComment.comment}</div>
									</div>
									<c:if test="${!empty reportComment.imagePath}">
									<div class="pics J_Pics">
									<c:forEach var="image" items="${fn:split(reportComment.imagePath,',')}">
										<div class="fl">
											<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
										</div>
									</c:forEach>
									</div>
									</c:if>
									<s class="arrow"></s>
								</div>
							</div>
						 </c:forEach>
						</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-sub">
				<div class="request-info boxbase J_RequestInfoWrap ">
					<s class="box-tp"><b></b></s>
					<div class="box-hd">
						<i class="hd-icon"></i>
						<h4>
							<span>举报内容</span>
						</h4>						
					</div>
					<div class="box-bd J_RequestInfo">
						<div class="row-item ">
							<div class="ctitle">
								<label>举报编号：</label>
							</div>
							<div class="info nowrap">${reportDTO.reportId}</div>
						</div>
						<div class="row-item ">
							<div class="ctitle">
								<label>举报原因：</label>
							</div>
							<c:if test="${reportDTO.reason == 'false'}"> 
								<div class="info">虚假信息</div>
							</c:if>
							<c:if test="${reportDTO.reason == 'unconformity'}">
								<div class="info">图片不符</div>
							</c:if>
						</div>
						<div class="row-item ">
							<div class="ctitle">
								<label>举报状态：</label>
							</div>
							<c:if test="${reportDTO.status == 'intervene'}">
								<div class="info">平台介入</div>
							</c:if>
							<c:if test="${reportDTO.status == 'voucher'}">
								<div class="info">取证中</div>
							</c:if>
							<c:if test="${reportDTO.status == 'success'}">
								<div class="info">举报成立</div>
							</c:if>							
							<c:if test="${reportDTO.status == 'error'}">
								<div class="info">举报不成立</div>
							</c:if>
							<c:if test="${reportDTO.status == 'cancel'}">
								<div class="info">举报撤销</div>
							</c:if>							
							<c:if test="${reportDTO.status == 'finish'}">
								<div class="info">完成</div>
							</c:if>							
						</div>
						<div class="row-item ">
							<div class="ctitle">
								<label>被投诉方：</label>
							</div>
							<div class="info report_text" title="${reportDTO.storeName }">${reportDTO.storeName }</div>
						</div>
						<div class="row-item ">
							<div class="ctitle">
								<label>申请时间：</label>
							</div>
							<div class="info normalwrap"><fmt:formatDate value="${reportDTO.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
						</div>
					</div>
				</div>

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
								<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${reportDTO.goodsId}"> 
									<img src="${imgUrl}/${reportDTO.picturePath}" class="goods-img" alt="${reportDTO.name}" width="255" height="255"> 
								</a>
								<label class=""></label>
							</div>
							<div class="info">
								<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${reportDTO.goodsId}" class="text-link">${reportDTO.name}</a> <br>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>卖家：</label>
							</div>
							<div class="info report_text">
								<a target="_blank" href="" title="${reportDTO.storeName}">${reportDTO.storeName}</a> <span>
									<!---->
								</span>
							</div>
						</div>
						<div class="row-item">
							<div class="ctitle">
								<label>单价：</label>
							</div>
							<div class="info">
								<span><span class="price-thin">￥<fmt:formatNumber value="${reportDTO.price}" pattern="#,#00.00#"/></span>元</span>
							</div>
						</div>	
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