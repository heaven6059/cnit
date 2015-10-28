//验证用户名
jQuery.validator.addMethod("loginNameRequired", function(value, element) {
	var val = $("[name='loginName']").val();
	var f = patterns.userName.test(val);
	if (!f) {
		f = patterns.email.test(val);
	}
	if (!f) {
		f = patterns.mobile.test(val);
	}
	return f;
}, "1-16位字符，支持字母、数字及“_”“-”组合！");
// 验证密码
jQuery.validator.addMethod("loginPasswordRequired", function(value, element) {
	var val = $("[name='loginPassword']").val();
	if (isNumber($("#id_loginPassword").val())) {
		return false;
	}
	return patterns.password.test(val);
}, "8-16位字符，建议由字母，数字和符号两种以上组合");
// 验证确认密码
jQuery.validator.addMethod("loginPasswordConfirmRequired", function(value,
		element) {
	var val = $("[name='loginPasswordConfirm']").val();
	if (val != $("#id_loginPassword").val()) {
		return false;
	}
	return true;
}, "确认密码不匹配");

// 验证手机
jQuery.validator.addMethod("mobileRequired", function(value, element) {
	if ($('#id_mobile_item').css('display') == 'block') {
		if ($("[name='mobile']").val().length == 0) {
			return false;
		}
	}
	return true;
});
// 验证手机格式
jQuery.validator.addMethod("mobileFormatRequired", function(value, element) {
	if ($('#id_mobile_item').css('display') == 'block') {
		var val = $("[name='mobile']").val();
		if ($("[name='mobile']").val().length > 0) {
			return patterns.mobile.test(val);
		}
	}
	return true;
});

// 验证短信验证码
jQuery.validator.addMethod("smsValidationRequired", function(value, element) {
	if ($('#id_mobile_item').css('display') == 'block') {
		if ($("[name='smsValidation']").val().length == 0) {
			return false;
		}
	}
	return true;
});
// 验证邮箱
jQuery.validator.addMethod("emailRequired", function(value, element) {
	if ($('#id_mobile_item').css('display') != 'block') {
		if ($("[name='email']").val().length == 0) {
			return false;
		}
	}
	return true;
});
// 验证邮箱格式
jQuery.validator.addMethod("emailFormatRequired", function(value, element) {
	if ($('#id_mobile_item').css('display') != 'block') {
		var val = $("[name='email']").val();
		if ($("[name='email']").val().length > 0) {
			return patterns.email.test(val);
		}
	}
	return true;
});
// 验证邮箱验证码
jQuery.validator.addMethod("emailValidationRequired", function(value, element) {
	if ($('#id_mobile_item').css('display') != 'block') {
		if ($("[name='emailValidation']").val().length == 0) {
			return false;
		}
	}
	return true;
});

var signupformRules = {
	onfocusout : function(element) {
		layer.close($(element).attr('layerindex'));
	},
	onfocusin : function(element) {
		layer.close($(element).attr('layerindex'));
	},
	onkeydown : true,
	rules : {
		"loginName" : {
			required : true,
			loginNameRequired : true
		},
		"loginPassword" : {
			required : true,
			loginPasswordRequired : true
		},
		"loginPasswordConfirm" : {
			required : true,
			loginPasswordConfirmRequired : true
		},
		"mobile" : {
			mobileRequired : true,
			mobileFormatRequired : true
		},
		"smsValidation" : {
			smsValidationRequired : true
		},
		"email" : {
			emailRequired : true,
			emailFormatRequired : true
		},
		"emailValidation" : {
			emailValidationRequired : true
		},
		"agreement" : {
			required : true
		}
	},
	messages : {
		"loginName" : {
			required : "请输入用户名！"
		},
		"loginPassword" : {
			required : "请设置密码！"
		},
		"loginPasswordConfirm" : {
			required : "确认密码不能为空！"
		},
		"mobile" : {
			mobileRequired : "请输入手机号码！",
			mobileFormatRequired : "手机号码格式有误，请输入正确的手机号！"
		},
		"smsValidation" : {
			smsValidationRequired : "请输入短信验证码"
		},
		"email" : {
			emailRequired : "请输入邮箱地址！",
			emailFormatRequired : "邮箱地址不正确，请重新输入！"
		},
		"emailValidation" : {
			emailValidationRequired : "请输入邮箱验证码"
		},
		"agreement" : {
			required : "请接受服务协议"
		}
	},
	// 错误信息的显示位置
	errorClass : "valid",
	errorPlacement : function(error, element) {// 指定错误信息位置
		layer.close(element.attr('layerindex'));
		var eid = element.attr('name'); // 获取元素的name属性
		if (element.is(':radio') || element.is(':checkbox')) {// 如果是radio或checkbox
			var index = renderMsgReg(element.parent().parent(),
					'<label calss="valid">' + error.get(0).innerHTML
							+ '</label>');
			element.attr('layerindex', index);
			// error.insertAfter(element.parent().parent()); //将错误信息添加当前元素的父结点后面
		} else {
			var indexElement = element.parent();
			if ('smsValidation' == eid || 'emailValidation' == eid) {
				indexElement = element.parent().parent();
			}
			var index = renderMsgReg(indexElement, '<label calss="valid">'
					+ error.get(0).innerHTML + '</label>');
			element.attr('layerindex', index);
			// error.insertAfter(element);
		}
	},
	success : function(label) {
		layer.close($('#' + label.get(0).attributes[0].nodeValue).attr(
				'layerindex'));
	}
};
function renderMsgReg($obj, msg) {
	return layer.tips(msg, $obj, {
		tips : [ 2, '#3595CC' ],
		tipsMore : true,
		time : 0
	});
}
$(document)
		.ready(
				function() {
					$("#signup-form").validate(signupformRules).valid();
					$('#id_loginPassword')
							.tooltip(
									{
										position : 'right',
										content : '<span style="color:#757575;font-size : 10px;">8-16位字符，建议由字母，数字和符号两种以上组合</span>',
										onShow : function() {
											$(this).tooltip('tip').css({
												backgroundColor : '#fff',
												borderColor : '#c7c7c7'
											});
										}
									});
					$('#dublueloginPassword')
							.tooltip(
									{
										position : 'right',
										content : '<span style="color:#757575;font-size : 10px;">8-16位字符，支持大小写字母、数字和符号</span>',
										onShow : function() {
											$(this).tooltip('tip').css({
												backgroundColor : '#fff',
												borderColor : '#c7c7c7'
											});
										}
									});
					$('#id_loginName')
							.tooltip(
									{
										position : 'right',
										content : '<span style="color:#757575;font-size : 10px;">1-16位字符，支持字母、数字及“_”“-”组合</span>',
										onShow : function() {
											$(this).tooltip('tip').css({
												backgroundColor : '#fff',
												borderColor : '#c7c7c7'
											});
										}
									});

					$("#person").addClass('active');
					$('#id_accountType').val('100');
					$('.form-tab-button').on('click', function() {
						$('.form-tab-button').removeClass('active');
						$(this).addClass('active');
						if ($(this).attr('id') == 'enterprise') {
							$('#id_accountType').val('110');
						} else {
							$('#id_accountType').val('100');
						}
					});
					if ($("#type").val() == '110') { // 入驻商家注册
						$("#enterprise").click();
					}
					$.validity.setup({
						outputMode : 'layer',
						layerStyle : [ 'background-color:#F26C4F; color:#fff',
								'#F26C4F' ],
						layerGuide : 2,
						more : true,
						layerTime : 5,
						layerMaxWidth : 240
					});
					$('#id_smscode_button').on('click', function() {
						checkMobileIfUsed('id_mobile');
					});
					$('#id_emailcode_button').on('click', function() {
						checkEmailIfUsed('id_email');
					});
				});

function showAgreement() {
	// 页面层
	layer.open({
		type : 1,
		title : '用户注册协议',
		skin : 'layui-layer-rim', // 加上边框
		area : [ '924px', '497px' ], // 宽高
		btn : [ '同意并继续' ],
		content : $('#agreementText').html()
	});
}
function checkEmailIfUsed(obj) {
	$obj = $('#' + obj);
	if ($obj.val()) {
		var param = {};
		param.loginName = $obj.val();
		param.loginNameType = '1';
		checkAccountExist(obj, param, function(data) {
			if (data.content.rows != 0) {
				renderMsg($obj, '该邮箱已被使用！');
			} else {
				getEmailCode(obj, 'id_emailcode_button');
			}
		});
	} else {
		renderMsg($obj, '请输入邮箱地址！');
	}
}

function checkMobileIfUsed(obj) {
	$obj = $('#' + obj);
	if ($obj.val()) {
		var param = {};
		param.loginName = $obj.val();
		param.loginNameType = '2';
		checkAccountExist(obj, param, function(data) {
			if (data.content.rows != 0) {
				renderMsg($obj, '该手机号码已被使用！');
			} else {
				getSmsCode(obj, 'id_smscode_button');
			}
		});
	} else {
		renderMsg($obj, '请输入手机号码！');
	}
}

/*
 * 用途：检查输入字符串是否符合正整数格式 输入：s：字符串 返回：如果通过验证返回true,否则返回false
 */
function isNumber(s) {
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
};

function checkIfLoginNameUsed(obj) {
	$obj = $('#' + obj);
	if ($obj.val()) {
		var param = {};
		param.loginName = $obj.val();
		param.loginNameType = '3';
		checkAccountExist(obj, param, function(data) {
			if (data.content.rows != 0) {
				renderMsg($obj, '该用户名已被占用！');
			}
		});
	}
}

/**
 * 方法描述：验证手机与验证邮箱切换 作者：李明 创建时间：2015-3-17
 * 
 * @param _vailType
 */
function changeVailType(_vailType) {
	if (_vailType == 'id_mobile_item') {
		$('#id_mobile_item').removeClass('hide');
		$('#id_email_item').addClass('hide');
		$('#id_email_item').val('');
		$('#id_emailValidation').val('');
	} else if (_vailType == 'id_email_item') {
		$('#id_email_item').removeClass('hide');
		$('#id_mobile_item').addClass('hide');
		$('#id_mobile_item').val('');
		$('#id_smsValidation').val('');
	}
}


/**
 * 方法描述：表单校验 作者：李明 创建时间：2015-3-17
 * 
 * @param _vailType
 */
function validateMyAjaxInputs() {
	$.validity.start();
	var f = false;
	$('#id_loginName').require('请输入用户名！').match(function(val) {
		f = patterns.userName.test(val);
		if (!f) {
			f = patterns.email.test(val);
		}
		if (!f) {
			f = patterns.mobile.test(val);
		}
		return f;
	}, '1-16位字符，支持字母、数字及“_”“-”组合！');
	if (f) {
		$("#id_loginPassword").require('请设置密码！');
		$("#id_loginPassword").match(function(val) {
			if (isNumber($("#id_loginPassword").val())) {
				return false;
			}
			f = patterns.password.test(val);
			return f;
		}, '8-16位字符，建议由字母，数字和符号两种以上组合');
	}
	if (f) {
		$("#dublueloginPassword").require("确认密码不能为空");
		$("#dublueloginPassword").match(function(val) {
			if ($("#id_loginPassword").val() != val) {
				f = false;
			} else {
				f = true;
			}
			return f;
		}, "确认密码不匹配");
	}
	if (f) {
		if ($('#id_mobile_item').css('display') == 'block') {
			$('#id_mobile').require('请输入手机号码！');
			$('#id_mobile').match(function(val) {
				f = patterns.mobile.test(val);
				return f;
			}, '手机号码格式有误，请输入正确的手机号！');
			if (f) {
				if ($('#id_smsValidation').val() == '') {
					layer.tips('请输入短信验证码！', $('#id_smsValidation'), {
						tips : [ 2, '#3595CC' ],
						time : 4000
					});
					f = false;
					return f;
				}
				// $('#id_smsValidation').require('请输入短信验证码！');
			}
		}
	}
	if (f) {
		if ($('#id_email_item').css('display') == 'block') {
			$('#id_email').require('请输入邮箱地址！');
			$('#id_email').match(function(val) {
				f = patterns.email.test(val);
				return f;
			}, '邮箱地址不正确，请重新输入！');
			if (f) {
				if ($('#id_emailValidation').val() == '') {
					layer.tips('请输入邮箱验证码！', $('#id_emailValidation'), {
						tips : [ 2, '#3595CC' ],
						time : 4000
					});
					f = false;
					return f;
				}
			}
			// $('#id_emailValidation').require('请输入邮箱验证码！');
		}
	}
	if (f) {
		if ($("#agreementBtn").attr("checked") == "checked") {
			return true;
		} else {
			layer.tips('请接受服务协议', $('#agreementDiv'), {
				tips : [ 2, '#3595CC' ],
				time : 4000
			});
			return false;
		}
	}
}


/**
 * 方法描述：提交注册 作者：李明 创建时间：2015-3-17
 */
function ajaxButtonClicked() {
	if ($("#signup-form").valid()) {
		var submitTos = function() {
			formSubmit('signup-form', _path + '/sign/signUp', function(data) {
				window.location.href = _path + '/register/login';
			}, function(data) {
				if (data.retCode == 'PDE003') {
					renderMsg($('#id_loginName'), data.retMsg);
				} else {
					renderMsg($('#signup-form'), data.retMsg);
				}
			});
		};
		//		submitTos();
		if (!$('#id_mobile_item').attr('class')) {
			validateSmsCode('id_smsValidation','id_mobile', function(data) {
				submitTos();
			}, $.noop);
		} else {
			validateEmailCode('id_emailValidation', function(data) {
				submitTos();
			}, $.noop);
		}
	}
}

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
	$("#dublueloginPassword").focus(function(){
		var p=this.parentNode;
		var password=document.createElement("input");
		password.type="password";
		password.name="loginPasswordConfirm";
		password.id="dublueloginPassword";
		p.replaceChild(password,this);
		window.setTimeout(function(){password.focus()},50);
	});
});
