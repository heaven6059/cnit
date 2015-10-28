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
<title>绑定其他账号</title>
<%@include file="/resources/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/sellYoYo.css" />
<link type="text/css" rel="stylesheet" href="${path}/resources/styles/pamauth/pamAuthList.css" >
<script type="text/javascript">
	var _path = '${path}';
</script>
</head>
<body>
	<div class="sell_main ml10 fl">
		<div class="sell_title">
			<div class="title_border">
				<span>绑定其他账号</span>
			</div>
		</div>
		<div class="bdshare-button-style0-32">
			<p>您可以选择绑定以下第三方账号：</p>
			<a href="javascript:void(0)" id="QQLink" class="bd_qq" title="绑定QQ"></a>
			<a href="javascript:void(0)" id="WXLink" class="bd_weixin" title="绑定微信"></a>
			<a href="javascript:void(0)" id="SNLink" class="bd_sina" title="绑定新浪微博"></a>
		</div>
		<div>
			<div class="title title2">我的绑定情况：</div>
			<div>
				<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id = "tableId">
					<tbody>
						<tr>
							<th style="width: 250px">第三方账号</th>
							<th style="width: 250px">账号类型</th>
							<th style="width: 250px">绑定时间</th>
							<th >操作</th>
						</tr>
					</tbody>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</div>
<script type="text/javascript" src="${path}/resources/scripts/biz/pamauth/pamAuthList.js"></script>	
</body>
</html>