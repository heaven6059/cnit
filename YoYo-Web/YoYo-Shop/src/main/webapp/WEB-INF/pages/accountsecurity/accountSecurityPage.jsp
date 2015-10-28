<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账号安全管理</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/businessinfo/regist.css?v=${versionVal}">
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?v=${versionVal}" rel="stylesheet" />
<style type="text/css">
</style>
</head>
<body>
<div class="member-main">
  <div>
  	<div class="title title2">修改密码</div>
  	<div id="regist" class="w">
	        <form  method="POST" id="personRegForm">
		            <div class="form">
		                <div id="select-regName" class="item">
		                    <span class="label"><b class="ftx04">*</b>原密码：</span>
		                    <div class="fl item-ifo">
		                        <div class="o-intelligent-regName">
		                            <input type="password" tabindex="1" class="text" maxlength="20" name="oldPassword" id="regName">
		                            <i class="i-pass"></i>
		                            <ul class="hide" id="intelligent-regName"></ul>
		                            <label class="blank" id="regName_succeed"></label>
		                            <label class="hide" id="regName_error"></label>
		                        </div>
		                    </div>
		                </div>
		                <div id="capslock"><i></i><s></s>键盘大写锁定已打开，请注意大小写</div>
		                <div id="o-password">
		                    <div class="item">
		                        <span class="label"><b class="ftx04">*</b>新密码：</span>
		                        <div class="fl item-ifo">
		                            <input name="password" id="pwd" type="password" autocomplete="off" style="ime-mode:disabled;" tabindex="2" class="text" maxlength="20">
		                            <i class="i-pass"></i>
		                            <label class="blank" id="pwd_succeed"></label>
		                            <label id="pwd_error"></label>
		                            <span class="clr"></span>
		                            <label id="pwdstrength" class="hide"><span class="fl">安全程度：</span><b></b></label>
		                        </div>
		                    </div>
		                    <script type="text/javascript">
		                        $('#pwd')[0].onkeypress = function (event) {
		                            var e = event || window.event,$tip =$('#capslock'),kc = e.keyCode || e.which, // 按键的keyCode		                            
		                                    isShift = e.shiftKey || (kc == 16 ) || false; // shift键是否按住
		                            if (((kc >= 65 && kc <= 90) && !isShift) || ((kc >= 97 && kc <= 122) && isShift)) {
		                                $tip.show();
		                            }else {
		                                $tip.hide();
		                            }
		                        };
		                    </script>
		                    <div class="item">
		                        <span class="label"><b class="ftx04">*</b>请确认密码：</span>
		                        <div class="fl item-ifo">
		                            <input type="password" autocomplete="off" onpaste="return  false" tabindex="3" class="text" maxlength="20" name="confirmPassword" id="pwdRepeat">
		                            <i class="i-pass"></i>
		                            <label class="blank" id="pwdRepeat_succeed"></label>
		                            <label id="pwdRepeat_error" class=""></label>
		                        </div>
		                    </div>
						</div>
		                <div class="item">
		                    <span class="label">&nbsp;</span>
		                    <button class="btn submit-btn" rel="_request" type="button" onclick = "reg();"><span><span>确认修改</span></span></button>        
		                </div>
		            </div>
					<span class="clr"></span>
		        </form>
		</div>
  </div>
</div>

<script type="text/javascript" >
		function submitForm(){
			var url=yoyo.shopUrl+"/accountSecurity/updateAccountPwd";
			var data=$('#personRegForm').serializeArray();
			 yoyo.ajaxRequest(url,data,true,"json",function(data){
				if (data.retCode == "000000") {
					if(data.content.result){
						layer.msg(data.content.resultMsg,{icon:1,shade: [0.3, '#393D49'],time:1000,end:function (){
							window.location.href = yoyo.portalUrl+"/sign/loginOut";	
						}});
					}else{
						layer.msg(data.content.resultMsg,{icon:0,shade: [0.3, '#393D49'],time:1000});
					}
				}
			});
		 } 
</script>
<script type="text/javascript" src="${path}/resources/scripts/biz/account/jdValidate.js"></script>
<script type="text/javascript">
$(function (){
	$.extend(validateFunction,{
		regValidate : function() {
			$("#regName").jdValidate(validatePrompt.pwd,validateFunction.pwd, true);
			$("#pwd").jdValidate(validatePrompt.pwd,validateFunction.pwd, true);
			$("#pwdRepeat").jdValidate(validatePrompt.pwdRepeat,validateFunction.pwdRepeat, true);
			return validateFunction.FORM_submit([ "#regName", "#pwd","#pwdRepeat" ]);
		}
	});
	var isSubmit = false;
	$("#pwd").bind("keyup", function() {validateFunction.pwdstrength();}).jdValidate(validatePrompt.pwd, validateFunction.pwd);
	$("#pwdRepeat").jdValidate(validatePrompt.pwdRepeat, validateFunction.pwdRepeat);
	$("#regName").jdValidate(validatePrompt.oldPwd, validateFunction.oldPwd);

});
//主注册流程
function reg() {
	passed = validateFunction.regValidate();	
	if(passed){
		submitForm();
	}
	
}
</script>
</body>
</html>