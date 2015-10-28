<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<%@include file="/resources/include/head.jsp" %> 
  
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/memberMng/regist.css">
<script type="text/javascript" src="${path}/resources/scripts/biz/account/jdValidate.js"></script>
<script type="text/javascript">
$(function(){
	$("#name").bind("blur",function() {
		validateForm();
	});
	$("#nickName").bind("blur",function(){
		validateForm();
	});
	$("#email").bind("blur",function(){
		validateForm();
	});
	$("#mobile").bind("blur",function(){		
		validateForm();
	});
});

function validateForm(){
	if(validateRules.isNull($("#name").val())){
		$("#regName_error").show().addClass("error").text("请输入用户名");
		return false;
	}else{
		$("#regName_error").hide();
	}
	
	if(validateRules.isNull($("#nickName").val())){
		$("#nickName_error").show().addClass("error").text("请输入用昵称");
		return false;
	}else{
		$("#nickName_error").hide();
	}
	
	if(!validateRules.isEmail($("#email").val())){
		$("#email_error").show().addClass("error").text("请输入正确的邮箱地址");
		return false;
	}else{
		$("#email_error").hide();
	}
	
	if(validateRules.isNull($("#mobile").val())||!validateRules.isMobile($("#mobile").val())){
		$("#phone_error").show().addClass("error").text("请输入正确的手机号码");
		return false;
	}else{
		$("#phone_error").hide();
	}
	return true;
}

//主注册流程
function reg() {
	if(validateForm()){
		submitForm();
	}
	
}
</script>
</head>
<body>
	<div class="member-main">
		<div class="title title2">
			个人信息<span class="disc">| 请尽量完整填写您的个人信息，方便店家与您联系。</span>
		</div>
		
		<div id="regist" class="w">
	        <form  method="POST" id="personRegForm">
		            <div class="form">
		                <div id="select-regName" class="item">
		                    <span class="label"><b class="ftx04">*</b>名称：</span>
		                    <div class="fl item-ifo">
		                        <div class="o-intelligent-regName">
		                            <input type="text"  value="${member.name}" tabindex="1" class="text" maxlength="20" name="name" id="name">
		                            <ul class="hide" id="intelligent-regName"></ul>
		                            <label class="blank" id="regName_succeed"></label>
		                            <label class="hide" id="regName_error"></label>
		                        </div>
		                    </div>
		                </div>
		                <div id="select-regName" class="item">
		                    <span class="label"><b class="ftx04">*</b>昵称：</span>
		                    <div class="fl item-ifo">
		                        <div class="o-intelligent-regName">
		                            <input type="text" value="${member.nickName}" tabindex="1" class="text" maxlength="20" name="nickName" id="nickName">
		                            <ul class="hide" id="intelligent-nickName"></ul>
		                            <label class="blank" id="nickName_succeed"></label>
		                            <label class="hide" id="nickName_error"></label>
		                        </div>
		                    </div>
		                </div>
		                <div id="o-password">
		                    <div class="item">
		                        <span class="label"><b class="ftx04">*</b>邮箱：</span>
		                        <div class="fl item-ifo">
		                            <input name="email" value="${member.email}" id="email" type="text" autocomplete="off" style="ime-mode:disabled;" tabindex="2" class="text" maxlength="25">
		                            <label class="blank" id="email_succeed"></label>
		                            <label id="email_error"></label>
		                        </div>
		                    </div>
		                    <div class="item">
		                        <span class="label"><b class="ftx04">*</b>手机号码：</span>
		                        <div class="fl item-ifo">
		                            <input name="mobile" value="${member.mobile}" id="mobile" type="text" autocomplete="off" onblur="phoneBlur();" onkeyup="phoneKeyup();"  tabindex="3" class="text" maxlength="20">
		                            <label class="blank" id="phone_succeed"></label>
		                            <label id="phone_error" class=""></label>
		                        </div>
		                    </div>
						</div>
		                <div class="item">
		                    <span class="label">&nbsp;</span>
		                    <input type="hidden" name="memberId" value="${member.memberId}"/>
		                    <button class="btn submit-btn" type="button" onclick = "reg();"><span><span>确认修改</span></span></button>        
		                </div>
		            </div>
					<span class="clr"></span>
		        </form>
		</div>
	</div>
<script type="text/javascript">
	function submitForm(){
		var url=yoyo.memUrl+"/personInfo/updatePersonInfo";
		var data=$('#personRegForm').serializeArray();
		 yoyo.ajaxRequest(url,data,true,"json",function(data){
			   		yoyo.loading("","");
			   		var resultCode = data.retCode;
			   		if (resultCode == yoyo.successCode) {
			   			layer.alert("修改成功！",{title:false,closeBtn:false,icon:1,end:function (){
				   			window.location.href = yoyo.memUrl+"/personInfo/getPersonInfo"; 
			   			}});
			   		} else {
			   			layer.alert("个人信息修改失败！",{title:false,closeBtn:false,icon:0});
			   		}
			   	});
	} 	
</script>
</body>
</html>