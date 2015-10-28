$(function() {
	$("#id_loginPassword").focus(function(){
		var p=this.parentNode;
		var password=document.createElement("input");
		password.type="password";
		password.name="loginPassword";
		password.id="id_loginPassword";
		p.replaceChild(password,this);
		window.setTimeout(function(){password.focus()},50);
	});
	autoFillPassword();
     $("#subBtn").bind("click",
     function() {
    	 ajaxButtonClicked();
     });
	$("#id_autoLogin").click(function(){
		var checked=$(this).is(':checked');
		if(checked){
			var loginPassword=$.cookie('password');
			if(loginPassword !== '' && loginPassword !=undefined){
				var password=$("#id_loginPassword");
				password.attr("type","password");
				$("#id_loginPassword").val($.base64.decode($.cookie('password')));
			}
			$(this).val(true).attr("checked",'true');
		}else{
			$("#id_loginPassword").val('');
			$(this).val(false);
		}
	});
	$.validity.setup({
		outputMode : 'layer',
		layerStyle : ['background-color:#F26C4F; color:#fff', '#F26C4F'],
		layerGuide : 2,
		layerTime : 5,
		layerMaxWidth : 240
	});
	$('input[name="loginName"]').focusout(function() {
		var val = $(this).val();
		getLoginNameType(val);
	});
});

function autoFillPassword(){
	var loginName=$.cookie('loginName');
	if(loginName){
		$("#id_loginName").val($.cookie('loginName'));
	}
	var loginPassword=$.cookie('password');
	if(loginPassword !== '' && loginPassword !=undefined){
		var password=$("#id_loginPassword");
		password.attr("type","password");
		$("#id_loginPassword").val($.base64.decode($.cookie('password')));
		$("#id_autoLogin").val(true).attr("checked",'true');
	}
}
function getLoginNameType(val) {
//	if (patterns.mobile.test(val)) {
//		$('#id_loginNameType').val('2');
//	} else if (patterns.email.test(val)) {
//		$('#id_loginNameType').val('1');
//	} else {
		$('#id_loginNameType').val('3');
//	}
}

function validateMyAjaxInputs() {
	$.validity.start();
	$('#id_imgValidation').require('请输入验证码！');
	$('#id_loginPassword').require('请输入密码！');
	$('#id_loginName').require('请输入用户名！');
	var result = $.validity.end();
	return result.valid;
}

$(document).keydown(function(e) {
	// enter
	if (e.keyCode == 13) {
		ajaxButtonClicked();
	}
});
function refreshImage() {
	$("#js-mail_vcode_img").click();
};
function ajaxButtonClicked() {
	getLoginNameType($('#id_loginName').val());
	if (validateMyAjaxInputs()) {
		validateImgCode('id_imgValidation', function(data) {
			$.ajax({
				type : "POST",
				url : _path + "/sign/doLogin",
				data : yoyo.form2Json('login-form'),
			}).done(function(data) {
				if (data.retCode == '000000') {
					//门户登录成功之后，根据类型判断，如果是买家的话，马上进行一次买家中心的登录，为了解决门户上面两次请求买家中心的问题，比如立即购买的
					if(data.content.accountType == 100) {
						$.ajax({
							type : "POST",
							url : yoyo.memUrl + "/sign2/doLogin2",
							data : yoyo.form2Json('login-form'),
						}).done(function(data) {
							
						});
					}
					
					var returnURL = $('#ReturnURL').val();
					if (returnURL && returnURL != '/index' && returnURL != '/') {
						window.location.href = $('#ReturnURL').val();
					} else {
						window.location.href = _path + '/index';
					}
				} else {
					renderMsg($('#id_loginName'), data.retMsg);
				}
			});
		}, $.noop);
	}
}