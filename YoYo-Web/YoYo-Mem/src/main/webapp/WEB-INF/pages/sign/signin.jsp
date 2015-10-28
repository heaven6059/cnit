<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cnit.yoyo.util.Configuration" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
	application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>登录</title>
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/validity/jquery.validity.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?r=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/signin.js?r=${versionVal}"></script>
<link type="text/css" href="${path}/resources/styles/common.css?r=${versionVal}" rel="stylesheet" />
<script type="text/javascript">
	var _path = "${path}";
</script>
</head>
<body onkeydown="bindEnter(event)">
	<div class="hide">
		<input id="ReturnURL" type="text" value=<c:out value="${requestScope.ReturnURL}"/> />
	</div>
	<div class="wrap">
		<div class="sign-nt clearfix" style="height:90px;">
			<div style="float: left; margin-left: 100px;">
				<a>
					<img height="86px" width="200px" src="${path}/resources/images/sign/logo.jpg" alt="" />
				</a>
			</div>
			<div style="float: right; margin-right: 100px;">
				<a>
					<img height="60px" width="225px" src="${path}/resources/images/sign/u3.png" alt="" />
				</a>
			</div>
		</div>
		<div class="sign-ct clearfix">
			<div class="banner">
				<div>
					<img height="400px" width="500px" src="${path}/resources/images/sign/u4.png" alt="" />
				</div>
			</div>
			<div class="sign-box">
				<form id="login-form" class="form">
					<div class="form-title">
						<div class="title">
							<a>会员登录</a>
						</div>
						<div class="attack">
							<a>注册新用户</a>
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-label">
							<span>邮箱/用户名/已验证手机：</span>
						</div>
						<div class="form-item-editor">
							<div class="editor-border-group" style="width: 350px;">
								<span class="prefix"></span> <input class="text" type="text" name="loginName" id="id_loginName" /> <span
									class="subfix"></span>
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-label">
							<span>密码：</span>
						</div>
						<div class="form-item-editor">
							<div class="editor-border-group" style="width: 350px;">
								<span class="prefix"></span> <input class="text" type="password" name="loginPassword" id="id_loginPassword" />
								<span class="subfix"></span>
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-label">
							<span>验证码：</span>
						</div>
						<div class="form-item-editor">
							<div class="editor-border-group" style="width: 150px;">
								<span class="prefix"></span> <input class="text" type="text" name="imgValidation" id="id_imgValidation" /> <span
									class="subfix"></span>
							</div>
							<div class="editor-noborder-group">
								<img id="js-mail_vcode_img" style="height: 40px; width: 60px;" onclick="javascript:refresh(this);"
									src="${path}/verifyImage?width=60&height=30" alt="code" />
								<a>看不清，换一张</a>
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-editor">
							<div class="editor-noborder-group">
								<span class="prefix"></span> <input class="checkbox" type="checkbox" name="autoLogin" id="id_autoLogin" /> <span
									class="subfix">自动登录</span>
							</div>
							<div class="editor-noborder-group">
								<a href="${path}/password/changePwd">忘记密码</a>
							</div>
						</div>
					</div>
					<div class="form-item">
						<div class="form-item-button">
							<a  class="form-button" href="javascript:ajaxButtonClicked()">登录</a>
							
						</div>
					</div>
					
					<div class="hide">
						<input type="hidden" name="loginNameType" id="id_loginNameType" class="text" />
					</div>
				</form>
				<div>
					<div>使用第三方授权账号登录优优</div>
					<div class="clearfix">
						<ul class="oauth">
							<li><a onclick="javascript:getAuthorizeURL('QQ');">
									<img class="oauth-logo" src="${path}/resources/images/oauth/qq-113.jpg" />
								</a></li>
							<li><a onclick="javascript:getAuthorizeURL('WX');">
									<img class="oauth-logo" src="${path}/resources/images/oauth/wx-113.jpg" />
								</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
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