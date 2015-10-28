<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = "/"
			+ request.getContextPath().substring(1).split("\\/")[0];
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
<link type="text/css" href="${path}/resources/styles/common.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/login.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/register.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/password.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/changePwd.js"></script>
<script type="text/javascript">
	var _path = "${path}";
	var _valiStatus = "${valiStatus}";
</script>
</head>
<body>
	<div class="par_main">
		<p>找回密码</p>
		<!--第一步-->
		<div class="par_main_in show" id="par1">
			<div class="par_bg">
				<span></span>
				<ul>
					<li class="par_current">填写账户名</li>
					<li>验证身份</li>
					<li>设置新密码</li>
					<li>完成</li>
				</ul>
			</div>
			<div class="par_con">
				<dl>
					<dt class="par_con_t">用户名：</td>
					<dd class="user">
						<input id="id_loginName" type="text" name="user" placeholder="邮箱/用户名/已验证手机">
					</dd>
				</dl>
				<dl class="par_code clearfix">
					<dt class="par_con_t fl">验证码：</dt>
					<dd class="code fr">
						<div class="code_ipt fl">
							<input type="text" name="code" placeholder="验证码" id="id_imgValidation">
						</div>
						<div class="code_bg fl">
							<img id="js-mail_vcode_img" style="height: 40px; width: 65px;" onclick="javascript:refresh(this);"
								src="${portalPath}/verifyImage?width=85&amp;height=40" alt="code">
						</div>
						<span class="fr"><a href="#" onclick="javascript:refreshImage();">看不清楚，换一张</a></span>
					</dd>
				</dl>
				<a href="#" class="sub w304" id="id_step_one">提 交</a>
			</div>
		</div>
		<!--第二步-->
		<div class="par_main_in" id="par2">
			<div class="par_bg show_par1">
				<span></span>
				<ul>
					<li>填写账户名</li>
					<li class="par_current">验证身份</li>
					<li>设置新密码</li>
					<li>完成</li>
				</ul>
			</div>
			<div class="par_con">
				<dl>
					<span class="par_con_t fl">身份验证方式：</span>
					<div class="user">
						<select id="par_seleck"></select>
					</div>
				</dl>
				<dl class="par_code clearfix">
					<dt class="par_con_t fl">验证码：</dt>
					<dd class="codew fr">
						<div class="codew_ipt fl">
							<input type="text" name="code" placeholder="验证码" id="id_validate_code">
						</div>
						<div class="codew_btn fr">
							<button type="submit" id="id_validate_code_button" oranText="获取验证码">获取验证码</button>
						</div>
					</dd>
				</dl>
				<a href="#" class="sub w304" id="id_step_two">提 交</a>
			</div>
		</div>
		<!--第三步-->
		<div class="par_main_in" id="par3">
			<div class="par_bg show_par2">
				<span></span>
				<ul>
					<li>填写账户名</li>
					<li>验证身份</li>
					<li class="par_current">设置新密码</li>
					<li>完成</li>
				</ul>
			</div>
			<div class="par_con">
				<dl>
					<dt class="par_con_t fl">密码：</dt>
					<dd class="user">
						<input type="password" name="par" id="id_loginPassword">
					</dd>
				</dl>
				<dl>
					<dt class="par_con_t fr">验证密码：</dt>
					<dd class="user">
						<input type="password" name="par">
					</dd>
				</dl>
				<a href="#" class="sub w304" id="id_step_three">提 交</a>
			</div>
		</div>
		<!--第四步-->
		<div class="par_main_in" id="par4">
			<div class="par_bg show_par3">
				<span></span>
				<ul>
					<li>填写账户名</li>
					<li>验证身份</li>
					<li>设置新密码</li>
					<li class="par_current">完成</li>
				</ul>
			</div>
			<div class="par_con">
				<div class="par_finished">
					<span>密码修改成功！</span>
				</div>
				<a href="#" class="sub w304" id="id_login">立即登录</a>
				<a href="#" class="sub sub2 w304" id="id_take_look">随便逛逛</a>
			</div>
		</div>
	</div>
	<script>
		//第二步切换
		$(function() {
			$('#id_step_one').click(_changePwd_step_1_vali);
			$('#id_step_two').click(_changePwd_step_2_vali);
			$('#id_step_three').click(_changePwd_step_3_vali);
			$('#id_validate_code_button').click(getValidateCode);
			$('#id_login').click(function() {
				window.location.href = _path + '/register/login';
			});
			$('#id_take_look').click(function() {
				window.location.href = _path;
			});
		})
	</script>
</body>
</html>