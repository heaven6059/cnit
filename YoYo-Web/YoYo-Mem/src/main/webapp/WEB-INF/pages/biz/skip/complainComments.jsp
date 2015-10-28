<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
   String complainId =(String)request.getAttribute("complainId");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投诉管理</title>
<%@include file="/resources/include/head.jsp"%>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/b2c.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/framework.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/pay/basic.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/complain/complain.css" />
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
</head>
<body>
	<div class="webwidth">
		<div class="feed-back">
			<div class="error clearfix">
				<div class="span-1 pic"></div>
				<div class="span-11 ">
					<p class="jumpurl">
						系统将在 <span id="time">5</span> 秒钟后自动跳转至新网址，如果未能跳转，
						<a href="${path}/memberOrder/complainDetail/?complainId="+${complainId} title="点击访问">请点击</a>。
					</p>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
    delayURL();
	function delayURL() {
		var delay = document.getElementById("time").innerHTML;
		var t = setTimeout("delayURL()", 1000);
		if (delay > 0) {
			delay--;
			document.getElementById("time").innerHTML = delay;
		} else {
			clearTimeout(t);
			window.location.href = "${path}/memberOrder/complainDetail/?complainId="+${complainId};
		}
	}
</script>
</body>
</html>