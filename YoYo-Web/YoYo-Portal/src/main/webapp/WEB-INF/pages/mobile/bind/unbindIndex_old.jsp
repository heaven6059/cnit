<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>解除绑定</title>
<jsp:include page="../base/inc/inc.jsp"></jsp:include>
</head>
<body>
	<div data-role="page" style="width:100%">
		<div style="margin:0;padding:0;width:100%">
			<div style="width:100%;text-align:center;">	
				<img alt="" src="${path}/resources/images/personalCenter/header.png" width="100%" height="80px">
			</div>
			<div style="margin:15px;">
				<table style="width:100%;text-align:center">
					<tr>
						<td colspan="2"><input type="text" name="text-basic" id="mobilePhone" placeholder="手机号"></td>
					</tr>
					<tr>
						<td><input type="text" name="text-basic" id="verifyCode" placeholder="验证码"></td>
						<td style="padding-left:5px;width:20%;"><input id="smBtn" type="button" data-mini="true" value="获取验证码" onclick="getVerifyCode()"></td></tr>
					<tr><td colspan="2"><button class="ui-shadow ui-btn ui-corner-all" style="background-color:#C00008;color:#FFFFFF" onclick="weixinUnbind()" id="unbindBtn">解除绑定</button></td></tr>					
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../include/notice.jsp"></jsp:include>
	
<script type="text/javascript">
	var $smBtn = $("#smBtn");
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
			url: "${path}/weixinUnbind",
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
	
	function weixinUnbind(){
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
		var jsonparam = {};
		jsonparam.ftelephone = ftelephone;
		jsonparam.verifyCode = verifyCode;
		$.ajax({
			type: "POST",
			url: "${path}/weixinUnbind",
			data: jsonparam,
			dataType: "json",
			async:false,
			success: function(data){
				if(data.success){
					window.location.href = "${path}/unbindSuccess";
				}else{
					alert(data.msg);
					return false;
				}
			}
		});
	}
</script>
</body>
</html>