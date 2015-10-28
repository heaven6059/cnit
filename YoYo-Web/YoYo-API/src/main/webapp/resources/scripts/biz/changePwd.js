function doChangePwd() {
	var param = {};
	param.accountId = $('#id_accountId');
	param.loginPassword = $('#id_loginPassword');
	commonAjax(_path + '/sign/doChangePwd', param, function(data) {
		window.location.href = _path + '/sign/login';
	}, function(data) {
		renderMsg($('#id_loginPassword'), data.retMsg);
	});
}

function _changePwd_step_1_vali() {
	// 验证输入的帐号是否符合平台规范
	var $loginName = $('#id_loginName');
	var $imgValidation = $('#id_imgValidation');
	if ($loginName.val()) {
		var loginNameType = testLoginNameType($loginName.val());
		if (loginNameType != '0') {
			// 验证验证码
			validateImgCode('id_imgValidation', function(data) {
				var param = {};
				param.loginName = $loginName.val();
				param.loginNameType = loginNameType;
				// 验证账户对应的账户是否存在且有效并获取用户数据
				commonAjax(_path + '/sign/checkAccountExist', param, function(data) {
					var user = data.content.rows[0];
					var $par_seleck = $('#par_seleck');
					if (user) {
						if (user.mobile) {
							$par_seleck.append($('<option type="mobile" value=' + user.mobile + '>手机</option>'));
						}
						if (user.email) {
							$par_seleck.append($('<option type="email" value=' + user.email + '>邮箱</option>'));
						}
						$(".par_main_in").removeClass("show");
						$("#par2").addClass("show");
					} else {
						// 用户不存在
						renderMsg($loginName, '用户不存在，请重新输入用户名！');
					}
				}, function(data) {

				});
			}, function(data) {
				// 验证码错误
				renderMsg($imgValidation, data.retMsg);
			});
		} else {
			// 账户名不满足平台要求
			renderMsg($loginName, '请输入邮箱/用户名/已验证手机！');
		}
	} else {
		// 账户名不能为空
		renderMsg($loginName, '请输入用户名！');
	}
}

function _changePwd_step_2_vali() {
	var $validateType = $('#par_seleck option:selected');
	if ($validateType.attr('type') == 'mobile') {
		validateSmsCode('id_validate_code', function(data) {
			$(".par_main_in").removeClass("show");
			$("#par3").addClass("show");
		}, function(data) {
			renderMsg($('#id_validate_code'), '验证码错误！');
		});
	} else if ($validateType.attr('type') == 'email') {
		// 发送验证邮件
		validateEmailCode('id_validate_code', function(data) {
			$(".par_main_in").removeClass("show");
			$("#par3").show();
		}, function(data) {
			renderMsg($('#id_validate_code'), '验证码错误！');
		});
	}
}

function _changePwd_step_3_vali() {
	var $passwords = $("input:password");
	var pass = [];
	$.each($("input:password"), function(index, item) {
		var value = $(item).val();
		if (value) {
			if (!patterns.password.test(value)) {
				renderMsg($(item), '输入的密码为大小字母及@!#$^&.~*字符组合,长度8~16位');
				return;
			}
			pass.push(value);
		} else {
			renderMsg($(item), '请输入密码');
			return;
		}
	});
	if (pass[0] != pass[1]) {
		renderMsg($passwords, '两次输入的密码不一致');
		return;
	} else {
		var param = {};
		param.loginName = $('#id_loginName').val();
		param.loginNameType = testLoginNameType(param.loginName);
		param.loginPassword = $('#id_loginPassword').val();
		commonAjax(_path + '/password/doChangePwd', param, function(data) {
			$(".par_main_in").removeClass("show");
			$("#par3").hide();
			$("#par4").addClass("show");
		}, function(data) {
			renderMsg($('#id_loginPassword'), data.retMsg);
		});
	}
}

function getValidateCode() {
	var $validateType = $('#par_seleck option:selected');
	if ($validateType && $validateType.val()) {
		var validateType = $validateType.attr('type');
		if (validateType == 'mobile') {
			getSmsCode('par_seleck','id_validate_code_button');
		} else if (validateType == 'email') {
			getEmailCode('par_seleck','id_validate_code_button');
		}
	} else {
		renderMsg($validateType, '请选择身份校验方式！');
	}
}