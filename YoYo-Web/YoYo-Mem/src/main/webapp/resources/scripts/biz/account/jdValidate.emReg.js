$.extend(validateFunction,{
	regValidate : function() {
		$("#regName").jdValidate(validatePrompt.oldPwd,validateFunction.oldPwd, true);
		$("#pwd").jdValidate(validatePrompt.pwd,validateFunction.pwd, true);
		$("#pwdRepeat").jdValidate(validatePrompt.pwdRepeat,validateFunction.pwdRepeat, true);
		return validateFunction.FORM_submit([ "#regName", "#pwd","#pwdRepeat" ]);
	}
});
var isSubmit = false;
$("#pwd").bind("keyup", function() {validateFunction.pwdstrength();}).jdValidate(validatePrompt.pwd, validateFunction.pwd);
$("#pwdRepeat").jdValidate(validatePrompt.pwdRepeat, validateFunction.pwdRepeat);
$("#regName").jdValidate(validatePrompt.oldPwd, validateFunction.oldPwd);


function checkReadMe() {
	if ($("#readme").attr("checked") == true) {
		$("#protocol_error").removeClass().addClass("error hide");
		return true;
	} else {
		$("#protocol_error").removeClass().addClass("error");
		return false;
	}
}

function agreeonProtocol() {
	if ($("#readme").attr("checked") == true) {
		$("#protocol_error").removeClass().addClass("error hide");
		return true;
	}
}

function protocolReg() {
	$("#closeBox").click();
	//reg();
}
//主注册流程
function reg() {
	passed = validateFunction.regValidate();	
	if(passed){
		submitForm();
	}
	
}
//popup注册
function popupReg() {
	var mobileCodeFlag = false;
	 var agreeProtocol = checkReadMe();
		var mobileCode = $("#mobileCode").val();
		if (mobileCode == "") {
			$("#mobileCode").attr({
				"class" : "text highlight2"
			});
			$('#mobileCode_error').addClass('error').html('请输入短信验证码');
		} else {
			mobileCodeFlag = true;
		}
	    var passed = validateRegName() && validateFunction.regValidate() && agreeProtocol && mobileCodeFlag && mobileFlags;;
	    if (passed) {
	        $("#popupRegButton").attr({ "disabled": "disabled" }).removeClass().addClass("btn-img btn-regist wait-btn");
	        $.ajax({
	            type: "POST",
	            url: "../register/regService?r=" + Math.random(),
	            contentType: "application/x-www-form-urlencoded; charset=utf-8",
	            data: $("#popupPersonRegForm").serialize(),
	            success: function (result) {
	                if (result) {
	                    var obj = eval(result);
	                    if (obj.info) {
	                    	showMessage(obj.info);
	                        verc();
	                        $("#popupRegButton").removeAttr("disabled").removeClass().addClass("btn-img btn-regist");
	                        return;
	                    }
	                    if (obj.noAuth) {
	                        verc();
	                        window.parent.location = obj.noAuth;
	                        return;
	                    }
	                    if (obj.success == true) {
	                        window.parent.jdModelCallCenter.init(true);
	                        return;
	                    }
	                }
	            }
	        });
	    } else {
	        $("#popupRegButton").removeAttr("disabled").removeClass().addClass("btn-img btn-regist");
	    }
	
}

function popupContinueReg() {
	$("#protocolContent").removeClass().addClass("regist-bor hide");
	$("#popupPersonRegForm").show();

	popupReg();
}

function showProtocol() {
	$("#popupPersonRegForm").hide();
	$("#protocolContent").removeClass().addClass("regist-bor");

}
function showMessage(alertMsg)
{
	$.jdThickBox({
		  type: "text",/*也可以是text,html,image,ajax,json*/
	         width: 360,
	         height: 100,
	         source: '<div class="thickbox-tip">'
	        	 		+'<div class="icon-box">'
	        	 		+'<span class="warn-icon m-icon"></span>'
	        	 		+ '<div class="item-fore ">'
	        	 		+'<h2 class="ftx-04 " id="alertMsg">'+alertMsg+'</h2>'
	        	 		+' </div>'
	        	 		+'</div>'
	        	 		+'</div>',
	         title: "温馨提示",
	         _close_val: "×",
	         _con: "opinioncon",
	         _titleOn: true
	});
}
