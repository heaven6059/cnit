<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    request.setAttribute("time", System.currentTimeMillis());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>咨询管理</title>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?v=${versionVal}" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">咨询管理</div>
			<ul class="switchable-triggerBox clearfix">
				<li data="" class="active">全部咨询</li>
				<li data="1" class="">已回复咨询</li>
				<li data="0" class="">未回复咨询</li>
			</ul>
			<div class="clr"></div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist">
				<tbody>
					<tr>
<!-- 						<th width="5%"></th> -->
						<th width="65%">咨询信息</th>
						<th width="30%">商品信息</th>
						<th width="5%">操作</th>
					</tr>
				</tbody>
			</table>
			<div class="page clearfix">
				<!-- 
				<div class="member-del">
					<input type="checkbox" id="del-all-box">&nbsp;&nbsp;全选&nbsp;&nbsp;
					<button class="trigger-btn" id="del-all-btn">删除</button>
				</div>
				 -->
				<div id="Pagination" class="yoyoPagination" ></div>
			</div>
		</div>
	</div>
	<input type="hidden" id="filterReply" />
	<script type="text/javascript" src="${path}/resources/scripts/biz/callcenter/consultMng.js?v=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
</body>
</html>

