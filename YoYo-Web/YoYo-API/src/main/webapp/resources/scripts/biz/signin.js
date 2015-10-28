$(function() {
     $("#subBtn").bind("click",
     function() {
    	 ajaxButtonClicked();
     });
	$("#id_autoLogin").click(function(){
		var checked=$(this).is(':checked');
		if(checked){
			var loginName=$.cookie('loginName');
			if(loginName){
				$("#id_loginName").val($.cookie('loginName'));
				$("#id_loginPassword").val($.base64.decode($.cookie('password')));
			}
			$(this).val(true);
		}else{
			$("#id_loginName").val('');
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