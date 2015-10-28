<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站内信</title>

<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/membermsg/memberMsgList.js?v=${versionVal}"></script>
</head>
<body>
	<div class="member-main ">
		<div >
			<div class="title">站内信</div>

			<div class="Plate">
				<h4>
					<a href="javascript:void(0);" class="current" data-args="" ">全部</a>  
					<a href="javascript:void(0);" data-args="true" >已读</a> 
					<a href="javascript:void(0);" data-args="false" >未读</a> 
				</h4>
			</div>
			<div class="clr"></div>
			<table class="gridlist border-all gridlist_member" width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="tableId">
				<thead>
					<tr>
						<th width="5%"></th>
						<th width="60%">标题</th>
						<th width="20%">时间时间</th>
						<th width="15%">操作</th>
					</tr>
				</thead>
			</table>
			<div class="page clearfix">
				<div class="member-del">
					<input type="checkbox" id="del-all-box">&nbsp;&nbsp;全选&nbsp;&nbsp;
					<a class="trigger-btn long-toolbtn">批量设为已读</a>
				</div>
				<div id="Pagination" class="yoyoPagination" ></div>
			</div>
		</div>
	</div>
</body>
</html>

