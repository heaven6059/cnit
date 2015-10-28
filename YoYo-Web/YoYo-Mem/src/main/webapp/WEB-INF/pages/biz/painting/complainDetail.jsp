<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投诉详情</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/complain/complain.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/popup/magnific-popup.css" />
<link rel="stylesheet" href="${path}/resources/styles/zyUpload.css"	type="text/css">
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/popup/jquery.magnific-popup.min.js"></script>
<script type="text/javascript" >
	var _path = "${path}"; 
</script>
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script src="${path}/resources/scripts/biz/painting/complainDetail.js"></script>
</head>
	<body>
		<div class="member-main">
			<div class="title title2">投诉管理</div>
			<div class="progress-bar 
				<c:if test="${complain.status eq 'intervene'}">at-step-1</c:if>
				<c:if test="${complain.status eq 'success' || complain.status eq 'error' || complain.status eq'cancel'}">at-step-2</c:if>">
				<ol class="four-steps three-step">
					<li class="step-item step-0"><strong>1</strong>
						<h2 style="width: 255px;">申请投诉</h2></li>
					<li class="step-item step-1"><strong>2</strong>
						<h2 style="width: 255px;">平台介入处理</h2></li>
					<li class="step-item step-2 step-3"><strong>3</strong>
						<h2 style="width: 267px;">投诉完成</h2></li>
				</ol>
			</div>
			<div class="FormWrap" style="background-color: #FFFFFF; border: 0 none; padding: 0;">
				<div class="col-main">
					<div class="main-wrap">
						<div class="progress-remind">
							<s class="icon warn-icon"></s>
							<c:if test="${complain.status eq 'cancel'}">
								<h4>已撤销投诉</h4>
							</c:if>
							<c:if test="${complain.status eq 'intervene'}">
								<h4>审核中，请耐心等待</h4>
							</c:if>
							<c:if test="${complain.status eq 'success'}">
								<h4>投诉成功</h4>
							</c:if>
							<c:if test="${complain.status eq 'error'}">
								<h4>投诉不成立</h4>
							</c:if>
						</div>
						<div class="boxbase msg-board J_MsgBoard">
							<div class="box-bd">
								<h4>
									<span>留言板 </span>
								</h4>
								<c:if test="${complain.status eq 'intervene'}">
									<div class="box-act J_BoxAct">
										<a href="javascript:;" id="J_ToggleMsgAction" class="more" onclick="showHideMessageBoard()">我要留言<b></b></a>
									</div>
								</c:if>
								<form id="formId" class="box-action  hidden">
									<input type="hidden" name="complainId" value="${complain.complainId}">
									<input type="hidden" name="source" value="buyer">
									<input type="hidden" name="authorId" value="${complain.fromMemberId}">
									<input type="hidden" name="author" value="${complain.fromUname}">
									<input type="hidden" name="disabled" value="false">
									<input type="hidden" id="imagePath" name="imagePath">
									<div class="J_MemoWrap memo-wrap">
										<textarea name="comment" placeholder="留言不能为空" class="msg-textarea J_MsgTextarea J_FormVerify"></textarea>
										<div class="text-count hide">
											<span class="msg-error">麻烦填写4-20个字</span>
										</div>
										<a class="submit-btn J_SubmitBtn" id="save_button">发表留言</a>
									</div>
									<div style="margin-top: 30px">
										<div class="ctitle">
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
								</form>
							</div>
							
							<div class="J_MsgDetail">
								<c:if test="${fn:length(complain.complainComments)>0}">
									<c:forEach items="${complain.complainComments}" var="complainComment">
										<div class="row-item first-row-item odd">
											<div class="ctitle avator">自己：</div>
											<div class="info">
												<div class="action">
													<span> ${complainComment.author}</span> &nbsp;&nbsp;
													(<span class="time"><fmt:formatDate value="${complainComment.lastModified}"pattern="yyyy-MM-dd HH:mm:ss" /></span>)
												</div>
												<div class="action-desc">
													<label></label>
													<div class="desc" style="text-indent: 2em;">${complainComment.comment}</div>
												</div>
												<c:if test="${!empty complainComment.imagePath}">
													<c:forEach var="image" items="${fn:split(complainComment.imagePath,',')}">
														<div class="pics J_Pics" style="float: left;">
															<a style="width: 193px; height: 125px;" title="" href="${image}">
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
							<c:if test="${complain.status eq 'intervene'}">
								<div class="box-act">
									<a href="javascript:cancelComplain(${complain.complainId});" class="">撤销</a>
								</div>
							</c:if>
						</div>
						<div class="box-bd J_RequestInfo">
							<div class="row-item ">
								<div class="ctitle">
									<label>投诉编号：</label>
								</div>
								<div class="info nowrap">${complain.complainId}</div>
							</div>
							<div class="row-item ">
								<div class="ctitle">
									<label>投诉类型：</label>
								</div>
								<c:if test="${complain.reason == 'action'}">
									<div class="info">行为违规</div>
								</c:if>
								<c:if test="${complain.reason == 'after'}">
									<div class="info">售后问题</div>
								</c:if>
								<c:if test="${complain.reason == 'quality'}">
									<div class="info">质量问题</div>
								</c:if>
							</div>
							<div class="row-item ">
								<div class="ctitle">
									<label>投诉状态：</label>
								</div>
								<c:if test="${complain.status == 'intervene'}">
									<div class="info">平台介入</div>
								</c:if>
								<c:if test="${complain.status == 'success'}">
									<div class="info">投诉成立</div>
								</c:if>
								<c:if test="${complain.status == 'error'}">
									<div class="info">投诉不成立</div>
								</c:if>
								<c:if test="${complain.status == 'cancel'}">
									<div class="info">投诉撤销</div>
								</c:if>
							</div>
							<div class="row-item ">
								<div class="ctitle">
									<label>被投诉方：</label>
								</div>
								<div class="info">${complain.storeName }</div>
							</div>
							<div class="row-item ">
								<div class="ctitle">
									<label>申请时间：</label>
								</div>
								<div class="info normalwrap">
									<fmt:formatDate value="${complain.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</div>
							</div>
						</div>
						<div class="box-bd">
							<div class="row-item last-row-item">
								<a href="#" class="more J_MoreDescTrigger"><s></s></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>