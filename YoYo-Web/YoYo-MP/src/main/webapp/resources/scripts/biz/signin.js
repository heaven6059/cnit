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
	$.validity.patterns.mobile = /^1[3|4|5|8][0-9]{9}$/;
	$.validity.patterns.userName = /^[a-zA-Z]{1}[a-zA-Z0-9_]?$/;
	$('input[name="loginName"]').focusout(function(){
		var partern = $.validity.patterns.mobile;
		var val = $(this).val();
		if (partern.test(val)){
			$('#id_loginNameType').val('2');
		} else{
			partern = $.validity.patterns.email;
			if (partern.test(val)){
				$('#id_loginNameType').val('1');
			} else{
				$('#id_loginNameType').val('3');
			}
		}
	});
});


function validateMyAjaxInputs(){
	$.validity.start();
	$('#id_imgValidation').require('请输入验证码！');
	$('#id_loginPassword').require('请设置密码！');
	$('#id_loginName').require('请输入用户名！').match(function(val){
		return $.validity.patterns['email'].test(val) || $.validity.patterns['userName'].test(val) || $.validity.patterns['mobile'].test(val);
	} , '输入用户名格式不符合要求！');
	var result = $.validity.end();
	return result.valid;
}

function ajaxButtonClicked(){
	if (validateMyAjaxInputs()){
		$.ajax({
			type : "POST" ,
			url : _path+"/doLogin" ,
			data : $('#login-form').serialize() ,
		}).done(function(data){
			window.location.href=_path+"/index" ;
		});
	}
}