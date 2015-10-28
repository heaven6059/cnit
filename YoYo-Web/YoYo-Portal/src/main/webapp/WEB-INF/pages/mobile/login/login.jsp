<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.cnit.yoyo.util.Configuration" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
	StringBuffer url = request.getRequestURL();  
	String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
	application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
%>
<html>
<head>
<title>优优车登录</title>
<jsp:include page="../base/inc/inc.jsp"></jsp:include>
<style type="text/css">
.ui-page-theme-a .ui-btn, html .ui-bar-a .ui-btn, html .ui-body-a .ui-btn, html body .ui-group-theme-a .ui-btn, html head + body .ui-btn.ui-btn-a, .ui-page-theme-a .ui-btn:visited, html .ui-bar-a .ui-btn:visited, html .ui-body-a .ui-btn:visited, html body .ui-group-theme-a .ui-btn:visited, html head + body .ui-btn.ui-btn-a:visited {
    text-shadow:none;
}
body,input, select, textarea, button, .ui-btn {
    font-size: 1.1em;
}
.ui-shadow, .ui-shadow-inset {
    box-shadow: none;
}
.ui-page-theme-a .ui-btn:hover, html .ui-bar-a .ui-btn:hover, html .ui-body-a .ui-btn:hover, html body .ui-group-theme-a .ui-btn:hover, html head + body .ui-btn.ui-btn-a:hover {
    text-shadow:none;
}
</style>
</head>
<body>
<div data-role="page" style="width:100%">
	<div style="">
		<img alt="" src="${path}/resources/images/personalCenter/header.jpg" width="100%">
		<!-- <div style="padding:10px;color:#fff;background-color:#00AEBD;font-size:18px;">淘屏网登录</div> -->
	</div>
	<div style="margin:15px;">
		<table style="width:100%;text-align:center">
			<tr>
				<td colspan="2"><input type="text" name="text-basic" id="mobilePhone" placeholder="手机号" style="height:48px"></td>
			</tr>
			<tr>
				<td><input type="text" name="text-basic" id="verifyCode" placeholder="验证码" style="height:48px;fonit-size:16px"></td>
				<!-- <td style="padding-left:5px;width:20%;"><a href="javascript:void(0)"  class="ui-shadow ui-btn ui-corner-all" style="font-size:12px;text-shadow:none;font-weight:normal;">获取验证码</a></td>	 -->		
				<td style="padding-left:5px;width:20%;"><input id="smBtn" type="button" data-mini="true" value="获取验证码" onclick="getVerifyCode()"></td></tr>
			<tr><td colspan="2"><button class="ui-shadow ui-btn ui-corner-all" style="background-color:#d93900;color:#fff" onclick="weixinLogin()" id="loginBtn">登  录</button></td></tr>					
		</table>
	</div>
</div>
<jsp:include page="../include/notice.jsp"></jsp:include>
	
<script type="text/javascript">
	var $smBtn = $("#smBtn");
	var $loginBtn = $("#loginBtn");
	var wait = 60;

	function getVerifyCode(){
		var ftelephone = $.trim($("#mobilePhone").val());
		if(ftelephone==""){
			alert("手机号码不能为空");
			return false;
		}
		timewait();
		var jsonparam = {};
		jsonparam.ftelephone = ftelephone;
		$.ajax({
			type: "POST",
			url: "${path}/weixin/getMsgVerifyCode",
			data: jsonparam,
			dataType: "json",
			async:false,
			success: function(data){
				if(data.success){
					notice_show("验证码已发送");	
				}else{
					notice_show("验证码发送失败");
					return false;
				}
			}
		});
	}
	
	function timewait(){
		if(wait==0){
			$smBtn.removeAttr("disabled");
            $smBtn.val("获取验证码").button("refresh");
            wait = 60;
		}else{
			$smBtn.attr("disabled", true);
            $smBtn.val(wait + "秒后重新获取").button("refresh");
            wait--;
            setTimeout(function(){
            	timewait();
            },1000);
		}
	}
	
	function weixinLogin(){
		var ftelephone = $.trim($("#mobilePhone").val());
		var verifyCode = $.trim($("#verifyCode").val());
		if(ftelephone==""){
			alert("请输入手机号码");
			return false;
		}
		if(verifyCode==""){
			alert("请输入短信验证码");
			return false;
		}
		//$("#loginBtn").button('disable');
		$loginBtn.attr("disabled", true);
		var jsonparam = {};
		jsonparam.ftelephone = ftelephone;
		jsonparam.verifyCode = verifyCode;
		jsonparam.requestUrl = "${requestUrl}";
		console.log("login.jsp requestUrl------:"+jsonparam.requestUrl);
		$.ajax({
			type: "POST",
			url: "${path}/weixin/weixinLogin",
			data: jsonparam,
			dataType: "json",
			async:false,
			success: function(data){
				if(data.success){					
					window.location.href = "${path}"+data.object;//Object中存的是requestUrl
				}else{
					alert(data.msg);
					$loginBtn.removeAttr("disabled");
					return false;
				}
			}
		});
	}
</script>
</body>
</html>