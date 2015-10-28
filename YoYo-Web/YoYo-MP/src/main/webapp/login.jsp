<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cnit.yoyo.util.Configuration"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
	application.setAttribute("portalUrl", Configuration.getInstance().getConfigValue("portal.url"));
	response.addHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.addHeader("Cache-Control", "pre-check=0, post-check=0");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/validity/jquery.validity.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/layer/layer.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
<link type="text/css" href="${path}/resources/styles/common_20150525.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/base.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/login.css?v=${versionVal}" rel="stylesheet" />
<title>YOYO商城管理平台</title>
</head>
<body onkeydown="bindEnter(event)">
	<div class="h_top">
		<div class="h_top_in">
			<div class="h_top_l fl">
				<h1 class="fl">
					<a href="${portalUrl };">yoyo商城</a>
				</h1>
				<div class="login_txt fl">欢迎登录</div>
			</div>
			<div class="h_top_r fr"></div>
		</div>
	</div>
	<div class="main">
		<div class="merchantLogin">
			<form id="login-form" class="form">
				<div class="main_in">
					<div class="login">
						<div class="login_t">
							<h2 class="fl">用户登录</h2>
						</div>
						<div class="login_b">
							<div class="user">
								<span class="fl"></span> <input class="fr" type="text" value="邮箱/用户名/已验证手机" onblur="if(this.value==''){this.value='邮箱/用户名/已验证手机';this.style.color='#999'}" onfocus="if(this.value=='邮箱/用户名/已验证手机'){this.value='';this.style.color='#000'}" style="color: #999" name="loginName" id="id_loginName" /><span class="subfix"></span>
							</div>
							<div class="user pws">
								<span class="fl"></span> <input class="fr" type="text" name="loginPassword" id="id_loginPassword" value="密码" onblur="if(this.value==''){this.setAttribute('type','text');this.value='密码';this.style.color='#999'}" onfocus="if(this.value=='密码'){this.value='';this.style.color='#000'};this.setAttribute('type','password');" style="color: #999" /><span class="subfix"></span>
							</div>
							<!-- <div class="find">
                        <label><input class="fl" type="checkbox" value="1" name="autoLogin" id="id_autoLogin"/> 自动登录</label>
                    </div> -->
							<a href="javascript:login();" class="sub" id="subBtn">登 录</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--footer-->
	<div class="footer bt0">
		<div class="footer_b">
			<p>CopyRight © 2015 ,All Rights Reserved. 版权所有 天津迪族信息技术有限公司   津B2-20070108-4号</p>
		</div>
	</div>
</body>
<script type="text/javascript">
	var _path = "${path}";
	function login() {
		if ($('#id_loginName').val()) {
			if ($('#id_loginPassword').val()) {
				formSubmit('login-form', _path + '/sign/doLogin',
						function(data) {
							window.location.href = _path + '/index';
						}, function(data) {
							renderMsg('id_loginName', data.retMsg);
						});
			} else {
				renderMsg('id_loginPassword', '请输入密码！');
			}
		} else {
			renderMsg('id_loginName', '请输入用户名！');
		}
	}

	function bindEnter(obj) {
		if (obj.keyCode == 13) {
			login();
		}
	}
</script>
</html>