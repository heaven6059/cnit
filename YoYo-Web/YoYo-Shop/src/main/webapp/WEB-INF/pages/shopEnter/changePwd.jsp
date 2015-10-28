<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path}/resources/scripts/smartWizard/jquery.smartWizard-2.0.js"></script>
<link type="text/css" href="${path}/resources/scripts/smartWizard/smart_wizard.css?r=${versionVal}" rel="stylesheet" />
</head>
<body>
	<div id="wizard" class="swMain">
		<ul>
			<li><a href="#step-1"> <label class="stepNumber">1</label> <span class="stepDesc">第一步<br /> <small>填写账户名</small></span></a></li>
			<li><a href="#step-2"> <label class="stepNumber">2</label> <span class="stepDesc">第二步<br /> <small>验证身份</small></span></a></li>
			<li><a href="#step-3"> <label class="stepNumber">3</label> <span class="stepDesc">第三步<br /> <small>设置新密码</small></span></a></li>
			<li><a href="#step-4"> <label class="stepNumber">4</label> <span class="stepDesc"> 第四步<br /> <small>完成</small></span></a></li>
		</ul>
		<div id="step-1" class="common-form">
			<div class="cell">
				<input type="text" placeholder="手机号码/邮箱" name="loginName" id="id_loginName" class="text" value="<c:out value='${loginName}'/>" /> <input
					type="hidden" name="loginNameType" id="id_loginNameType" />
			</div>
			<div class="cell vcode">
				<input type="text" placeholder="验证码" name="imgValidation" id="id_imgValidation" class="text" maxlength="4" /> <img id="js-mail_vcode_img"
					onclick="javascript:refresh(this);" src="${path}/verifyImage?width=60&height=30" alt="code" />
			</div>
		</div>
		<div id="step-2" class="common-form">
			<div id="id_mcode" class="cell mcode hide">
				<input type="text" placeholder="短信验证码" name="smsValidation" id="id_smsValidation" class="text" /> <a
					href="javascript:getSmsCode('id_loginName');" class="button">免费获取验证码 </a>
			</div>
			<div id="id_send_email" class="cell hide">
				<a id="js-mobile_btn" class="button" href="javascript:sendChangePwdEmail('id_loginName')">发送邮件</a>
			</div>
		</div>
		<div id="step-3" class="common-form">
			<div class="cell">
				<input type="password" placeholder="设置新密码" name="loginPassword" id="id_loginPassword" class="text" />
			</div>
			<div class="cell">
				<input type="password" placeholder="验证新密码" name="loginPasswordConfirm" id="id_loginPasswordConfirm" class="text" />
			</div>
		</div>
		<div id="step-4" class="common-form"></div>
		<script type="text/javascript">
			var _params = {};
			$(document).ready(function(){
				$('#wizard').smartWizard({
					transitionEffect : 'none' ,
					includeFinishButton : false ,
					labelNext : '下一步' ,
					labelPrevious : '上一步' ,
					onLeaveStep : function(obj){
						var curIdx = obj.attr('rel');
						if (curIdx == '1'){
							validateImgCode($("#id_imgValidation") , function(){
								_params.imgValidationStatus = true;
							} , function(){
								_params.imgValidationStatus = false;
							});
							return _params.imgValidationStatus;
						}
						if (curIdx == '2'){

						}
					}
				});
			});
		</script>
	</div>
</body>
</html>