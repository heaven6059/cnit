$(function(){
	$.validity.setup({
		outputMode : 'layer' ,
		// the third part layer tip frameworks style,when there no third part framework use default
		layerStyle : ['background-color:#F26C4F; color:#fff' , '#F26C4F'] ,
		// the position of the third part layer tip frameworks shows 0：top 1:right 2:buttom 3:left
		layerGuide : 2 ,
		// the duration of the third part layer tip frameworks shows
		layerTime : 5 ,
		// the max width of the third part layer tip frameworks display
		layerMaxWidth : 240
	});
	$('input[name="loginName"]').focusout(function(){
		var val = $(this).val();
		if (patterns.mobile.test(val)){
			$('#id_loginNameType').val('2');
		}
		if (patterns.email.test(val)){
			$('#id_loginNameType').val('1');
		}
		if (patterns.userName.test(val)){
			$('#id_loginNameType').val('3');
		}
	});
});

function validateMyAjaxInputs(){
	$.validity.start();
	$('#id_imgValidation').require('请输入验证码！');
	$('#id_loginPassword').require('请输入密码！');
	$('#id_loginName').require('请输入用户名！');
	var result = $.validity.end();
	return result.valid;
}

function ajaxButtonClicked(){
	if (validateMyAjaxInputs()){
		$.ajax({
			type : "POST" ,
			url : _path + "/sign/doLogin" ,
			data : $('#login-form').serialize() ,
		}).done(function(data){
			if (data.retCode == '000000'){
				window.location.href = _path + "/index";
			} else{
				renderMsg($('#id_loginName') , data.retMsg);
			}
		});
	}
}