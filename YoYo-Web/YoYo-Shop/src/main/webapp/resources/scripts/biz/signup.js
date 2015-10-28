$(function(){
	$("#person").css('background-color' , 'green');
	$('#id_accountType').val('100');
	$('.form-tab-button').on('click' , function(){
		$('.form-tab-button').css('background-color' , 'red').removeClass('active');
		$(this).css('background-color' , 'green').addClass('active');
		if ($(this).attr('id') == 'enterprise'){
			$('#id_accountType').val('110');
		} else{
			$('#id_accountType').val('100');
		}
	});
	$.validity.setup({
		outputMode : 'layer' ,
		layerStyle : ['background-color:#F26C4F; color:#fff' , '#F26C4F'] ,
		layerGuide : 2 ,
		layerTime : 5 ,
		layerMaxWidth : 240
	});
});

function checkEmailIfUsed(obj){
	$obj = $('#' + obj);
	if ($obj.val()){
		var param = {};
		param.loginName = $obj.val();
		param.loginNameType = '1';
		checkAccountExist(obj , param , function(data){
			if (data.content.rows != 0){
				renderMsg($obj , '该邮箱已被使用！');
			} else{
				getEmailCode(obj);
			}
		});
	} else{
		renderMsg($obj , '请输入邮箱地址！');
	}
}
function checkMobileIfUsed(obj){
	$obj = $('#' + obj);
	if ($obj.val()){
		var param = {};
		param.loginName = $obj.val();
		param.loginNameType = '2';
		checkAccountExist(obj , param , function(data){
			if (data.content.rows != 0){
				renderMsg($obj , '该手机号码已被使用！');
			} else{
				getSmsCode(obj);
			}
		});
	}
}
function checkIfLoginNameUsed(obj){
	$obj = $('#' + obj);
	if ($obj.val()){
		var param = {};
		param.loginName = $obj.val();
		param.loginNameType = '3';
		checkAccountExist(obj , param , function(data){
			if (data.content.rows != 0){
				renderMsg($obj , '该用户名已被占用！');
			}
		});
	}
}

function eventBanding(){
	$('#id_loginName').on('focusout' , function(event){
		if (event.value){
			if (patterns.userName.test(event.value)){
				$(this).addClass('');
			} else{

			}
		}
	});
}

/**
 * 方法描述：验证手机与验证邮箱切换
 * 作者：李明
 * 创建时间：2015-3-17
 * @param _vailType
 */
function changeVailType(_vailType){
	if (_vailType == 'id_mobile_item'){
		$('#id_mobile_item').removeClass('hide');
		$('#id_email_item').addClass('hide');
		$('#id_email_item').val('');
		$('#id_emailValidation').val('');
	} else if (_vailType == 'id_email_item'){
		$('#id_email_item').removeClass('hide');
		$('#id_mobile_item').addClass('hide');
		$('#id_mobile_item').val('');
		$('#id_smsValidation').val('');
	}
}

/**
 * 方法描述：表单校验
 * 作者：李明
 * 创建时间：2015-3-17
 * @param _vailType
 */
function validateMyAjaxInputs(){
	$.validity.start();
	if ($('#id_mobile_item').css('display') == 'block'){
		$('#id_mobile_item').require('请输入手机号码！').match(function(val){
			return patterns.mobile.test(val);
		} , '手机号码格式有误，请输入正确的手机号！');
		$('#id_smsValidation').require('请输入短信验证码！');
	}
	if ($('#id_email_item').css('display') == 'block'){
		$('#id_email_item').require('请输入邮箱地址！').match(function(val){
			return patterns.email.test(val);
		} , '邮箱地址不正确，请重新输入！');
		$('#id_emailValidation').require('请输入邮箱验证码！');
	}
	$('.password').require('请设置密码！').equal('两次输入密码不一致！').match(function(val){
		return patterns.password.test(val);
	} , '输入的密码为大小字母及@!#$^&.~*字符组合,长度8~16位');
	$('#id_loginName').require('请输入用户名！').match(function(val){
		return patterns.userName.test(val);
	} , '8-16位字符，支持字母、数字及“_”“-”组合！');
	var result = $.validity.end();
	return result.valid;
}

/**
 * 方法描述：提交注册
 * 作者：李明
 * 创建时间：2015-3-17
 */
function ajaxButtonClicked(){
	if (validateMyAjaxInputs()){
		formSubmit('signup-form' , _path + '/sign/signUp' , function(data){
			window.location.href = _path + '/sign/login';
		} , function(data){
			if (data.retCode == 'PDE003'){
				renderMsg($('#id_loginName') , data.retMsg);
			} else{
				renderMsg($('#signup-form') , data.retMsg);
			}
		});
	}
}
