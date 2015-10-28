<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
	<title>登录</title> <script type="text/javascript" src="${path}/resources/scripts/easytabs/jquery.easytabs.min.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/validity/jquery.validity.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/signin.js?v=${versionVal}"></script>
	<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body onkeydown="bindEnter(event)">
	<div class="login-box align-center border-radius-4px bgcolor-285583 transpar90 ">
		<div class="login-box-title font-w-b-16 bgcolor-2d6ea5">
			<p>会员登录</p>
		</div>
		<form id="login-form" class="common-form">
			<div class="cell font-w-b-16">
				<input type="text" placeholder="邮箱/手机号码/用户名" name="loginName" id="id_loginName" class="text" />
			</div>
			<div class="cell font-w-b-16">
				<input type="password" placeholder="密码" name="loginPassword" id="id_loginPassword" class="text" />
			</div>
			<div class="cell vcode font-w-b-16">
				<input type="text" placeholder="验证码" name="imgValidation" id="id_imgValidation" class="text" maxlength="4" /> <img
					id="js-mail_vcode_img" onclick="javascript:refresh(this);" src="${path}/verifyImage?width=60&height=30" alt="code" />
			</div>
			<div class="cell">
				<a class="button" id="js-mobile_btn" href="javascript:ajaxButtonClicked()">登录</a>
				<a href="${path}/changePwd">忘记密码</a>
			</div>
			<div class="cell">
				<img src="${path}/resources/images/oauth/connect_qq_3.png" onclick="javascript:getAuthorizeURL('QQ');"> <img
					src="${path}/resources/images/oauth/connect_wx_3.png" onclick="javascript:getAuthorizeURL('WX');">
			</div>
			<div class="hide">
				<input type="hidden" name="loginNameType" id="id_loginNameType" class="text" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function bindEnter(obj) {
			if (obj.keyCode == 13) {
				login();
			}
		}
	</script>
</body>
</html>