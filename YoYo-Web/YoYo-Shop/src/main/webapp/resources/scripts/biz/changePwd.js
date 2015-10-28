;
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
	$('#id_loginName').focusout(function(){
		patternLoginName($(this).val() , '');
	});
	$('#id_smsValidation').focusout(function(){
		validateSmsCode($('#id_smsValidation'),function(){
			$('#id_changepwd').css('display','');
		},function(){
			$('#id_changepwd').css('display','none');
		});
	});
});

function patternLoginName(val , subfix){
	if (patterns.mobile.test(val)){
		$('#id_mcode' + subfix).removeClass('hide');
		$('#id_loginNameType' + subfix).val('2');
	} else{
		$('#id_mcode' + subfix).addClass('hide');
		if ($.validity.patterns.email.test(val)){
			$('#id_send_email' + subfix).removeClass('hide');
			$('#id_loginNameType' + subfix).val('1');
		} else{
			$('#id_loginNameType' + subfix).val('3');
		}
	}
}

/**
 * 方法描述：通过邮箱修改密码时发送验证邮件
 * 作者：李明
 * 创建时间：2015-2-12
 */
function sendChangePwdEmail(obj){
	var $obj = $('#' + obj);
	var params = {
		'email' : $obj.val()
	};
	$.ajax({
		type : 'POST' ,
		url : _path + '/sign/sendChangePwdEmail' ,
		data : params
	}).done(function(data){
		if (data.head.retCode != '000000'){
			renderMsg($obj , data.head.retMsg);
		} else{
			renderMsg($obj , '邮件已发出，请注意查收！');
		}
	});
}

function next(loginNameType){
	// email
	if (loginNameType == '1'){
		sendChangePwdEmail();
	}
	// mobile
	if (loginNameType == '2'){
		var params = {
			'moblie' : $('#js_mobile_ipt').val() ,
			'smsCodeInput' : $('#js-mobile_vcode_ipt').val() ,
			'loginNameType' : loginNameType
		};
	}
}