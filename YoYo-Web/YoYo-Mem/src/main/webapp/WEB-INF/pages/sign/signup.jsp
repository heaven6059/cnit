<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cnit.yoyo.util.Configuration" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员注册</title>
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/validity/jquery.validity.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?r=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/signup.js?r=${versionVal}"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/base.css?r=${versionVal}"></link>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/common.css?r=${versionVal}"></link>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/login.css?r=${versionVal}"></link>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/register.css?r=${versionVal}"></link>
<script src="../base/js/index.js?r=${versionVal}"></script>

<script type="text/javascript">
	_path = "${path}";
	function bindEnter(obj) {
		if (obj.keyCode == 13) {
			login();
		}
	}
</script>
</head>
<body onkeydown="bindEnter(event)">
	<!--header-->
	<div class="h_top">
		<div class="h_top_in">
			<div class="h_top_l fl">
				<h1 class="fl">
					<a href="javascript:;">yoyo商城</a>
				</h1>
				<div class="login_txt fr">欢迎登录dddddddd</div>
			</div>
		</div>
	</div>

	<!--con-->
	<div class="reg_con">
		<div class="ref_nav">
			<ul>
				<li><a class="form-tab-button" id="person" href="#">会员注册</a></li>
				<li><a class="form-tab-button" id="enterprise" href="#">入驻商家注册</a></li>
			</ul>
		</div>
		<div class="reg_con_in">
			<div class="reg_con_l fl">
				<div class="login_b">
					<div class="user">
						<input type="text" name="loginName" placeholder="邮箱/用户名/已验证手机" id="id_loginName">
					</div>
					<div class="user pws">
						<input type="password" name="user" placeholder="密码" name="loginPassword" id="id_loginPassword">
					</div>
					<div class="user pws">
						<input type="password" name="user" placeholder="确认密码" name="loginPasswordConfirm" id="id_loginPassword">
					</div>
					<div id="id_mobile_item">
						<div class="code">
							<div class="code_ipt fl">
								<input type="text" name="code" placeholder="验证手机" name="mobile" id="id_mobile">
							</div>
							<span class="fr">或<a href="#" onclick="changeVailType('id_email_item')">验证邮箱</a></span>
						</div>
						<div class="codew">
							<div class="codew_ipt fl">
								<input type="text" name="code" placeholder="验证码" name="smsValidation" id="id_smsValidation">
							</div>
							<div class="codew_btn fr">
								<button type="submit">
									<a href="javascript:checkMobileIfUsed('id_mobile')">获取短信验证码 </a>
								</button>
							</div>
						</div>
					</div>
					<div id="id_email_item" class="hide">
						<div class="code">
							<div class="code_ipt fl">
								<input type="text" name="code" placeholder="验证邮箱" name="email" id="id_email">
							</div>
							<span class="fr">或<a href="#" onclick="changeVailType('id_mobile_item')">验证手机</a></span>
						</div>
						<div class="codew">
							<div class="codew_ipt fl">
								<input type="text" name="code" placeholder="邮箱验证码" name="emailValidation" id="id_emailValidation">
							</div>
							<div class="codew_btn fr">
								<button type="submit">
									<a href="javascript:checkEmailIfUsed('id_email')">获取邮箱验证码 </a>
								</button>
							</div>
						</div>
					</div>
					<div class="find">
						<input style="width: 13px; height: 13px;" class="fl" type="checkbox" value="我已阅读并同意">自动登录 <a class="fr"
							href="#">《服务协议》</a>
					</div>

					<div class="sub">
						<button type="button" value="注册">
							<a href="javascript:ajaxButtonClicked()">注 册</a>
						</button>
					</div>
				</div>
			</div>
			<div class="reg_con_r fr">
				<div class="reg_con_dl">
					<span>已有YOYO账号？</span>
					<button type="button" value="立即登录">
						<a href="${memPath}/register/login">立即登录</a>
					</button>
				</div>
				<div class="login_f">
					<p class="fl">使用第三方授权帐号登录优优：</p>
					<ul class="clearfix fr">
						<li><a href="#">QQ</a></li>
						<li class="login_li02"><a href="#">微信</a></li>
						<li class="login_li03"><a class="bor0" href="#">微博</a></li>
					</ul>
				</div>
				<div class="login_show">
					<p>热卖推荐：</p>
					<ul>
						<li>
							<a href="#">
									<img src="${path}/resources/images/login_show.jpg" alt="" />
									<h4>长安 PSA DS 5</h4>
									<h3 class="red">21.99<span>万元起</span></h3>
									<h5>2800最高回馈28000</h5>
							</a>
						</li>
						<li style="width: 142px;">
								<a href="#">
									<img src="${path}/resources/images/login_show.jpg" alt="" />
									<h4>长安 PSA DS 5</h4>
									<h3 class="red">21.99<span>万元起</span></h3>
									<h5>2800最高回馈28000</h5>
								</a>
							</li>
						<li style="border-right: 0;">
							<a href="#"> 
								<img src="${path}/resources/images/login_show.jpg" alt="" />
								<h4>长安 PSA DS 5</h4>
								<h3 class="red">21.99<span>万元起</span></h3>
								<h5>2800最高回馈28000</h5>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!--footer-->
	<div class="footer bor0">
		<div class="footer_b">
			<ul class="clearfix">
				<li><a href="javascript:;">关于我们</a></li>
				<li><a href="javascript:;">联系我们</a></li>
				<li><a href="javascript:;">商家入驻</a></li>
				<li><a href="javascript:;">营销中心</a></li>
				<li><a href="javascript:;">手机YOYO</a></li>
				<li><a href="javascript:;">友情链接</a></li>
				<li><a href="javascript:;">销售联盟</a></li>
				<li><a href="javascript:;">会员俱乐部</a></li>
				<li><a href="javascript:;">企业频道</a></li>
			</ul>
			<p>CopyRight © 2015 ,All Rights Reserved. 版权所有 天津迪族信息技术有限公司   津B2-20070108-4号</p>
		</div>
	</div>

</body>
</html>
