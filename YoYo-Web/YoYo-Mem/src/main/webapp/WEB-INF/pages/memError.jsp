<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单支付</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<style type="text/css">
.lijizhifu2 {
	background: rgba(0, 0, 0, 0) url("../resources/images/lijizhifu2.gif")no-repeat scroll 0 0;
	border: 0 none;
	cursor: pointer;
	display: block;
	height: 37px;
	margin: 0 auto;
	width: 140px;
}

.payment-list .pl-item:hover {
	border: 1px solid #ff5d5b;
}
</style>
<style type="text/css">
.paybox.paybox-selected {
	background-color: #fff;
	border: 2px solid #b0c2e1;
	border-radius: 2px;
	margin-top: -2px;
	padding: 0;
}

.paybox .p-wrap {
	padding-bottom: 10px;
	padding-top: 10px;
	position: relative;
}

.paybox .p-key {
	float: left;
	padding-left: 18px;
	padding-top: 5px;
}
.error-content{
	margin: 0 auto;
	text-align: center;
	padding: 10px 0px;
}
.error-content p{
	font-size: 14px;
	color: red;
}
</style>
</head>

<body>
	<div class="webwidth">
		<div class="error-content">
			<p>${errorMsg}</p>
		</div>
		<div class="error-content">
			<a href="${href}">返回前一页</a>
		</div>
	</div>
	<div class="clr"></div>
</body>
</html>