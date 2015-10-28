<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOYO汽车商城-商家入驻</title>
<link type="text/css" rel="stylesheet"
	href="${path}/resources/styles/shopEnter/shopRegister.css">
<link rel="stylesheet" type="text/css"
	href="${path}/resources/styles/shopEnter/enter.css">
</head>
<body>
<!--main-->
<div class="enter_main">
	<div class="enter_in">
		<!--商家入驻流程-->
		<div class="enter_top">
			<b></b>
			<p>商家入驻流程</p>
			<b></b>
		</div>
		<div class="enter_top">
			<i></i> <span>400-0571-137</span>
		</div>
		<div class="enter_bottom clearfix">
			<ul class="clearfix">
				<li>
					<div class="enter_t">
						<span></span>
						<h3>1、注册企业账号</h3>
						<p>注册YOYO商城企业账号</p>
					</div>
					<c:if test="${sessionScope.loginStatus!=1}">
						<div class="enter_b">
							<a href="${portalPath}/register/signup?type=110"><p>注册企业账号</p>
								<i></i></a>
						</div>
					</c:if>
				</li>
				<li class="enter_li02">
					<div class="enter_t">
						<span></span>
						<h3>2、填写资料</h3>
						<p>填写申请信息，提交资质选择店铺类型，在线签署服务协议邮寄纸质资料</p>
					</div>
					<c:if test="${sessionScope.accountType==110 || sessionScope.accountType==120}">
						<div class="enter_b">
							<a href="${path}/shopEnter/shopRegisterData"><p>提交入驻申请</p>
								<i></i></a>
						</div>
					</c:if>
				</li>
				<li class="enter_li03">
					<div class="enter_t">
						<span></span>
						<h3>3、等待审核</h3>
						<p>YOYO 5个工作日给到审核</p>
					</div>
				</li>
				<li class="enter_li04">
					<div class="enter_t">
						<span></span>
						<h3>4、缴纳保证金开店</h3>
						<p>缴纳技术服务年费、诚信保证金装修店铺，发布商品</p>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>