var accountId, accountType;
var address;
$(function(){
	initProvince();
	initReuqireType();
    $("#province").change(function () {
    	initCity(this.value);
    });
    $("#city").change(function () {
    	initRegion(this.value);
    });
    $('#btnSubmit').click(function(){
    	if(accountId ==0){
			window.location.href = yoyo.portalUrl+'/register/login';
		}
    	if(checkValid()){
    		address = '';
    		if($('#province').val() != 0){
    			address += $('#province').find("option:selected").text();
    		}
    		if($('#city').val() != 0){
    			address += $('#city').find("option:selected").text();
    		}
    		if($('#region').val() != 0){
    			address += $('#region').find("option:selected").text();
    		}
    		if($('#street').val()){
    			address += $('#street').val();
    		}
    		submit();
    	}
    });
});

/**
 * 初始化需求类型
 */
function initReuqireType(){
	$.ajax({
		url:yoyo.portalUrl+'/requirement/queryType',
		success:function(data){
			if(data != null){
				$.each(data,function(index,type){
					$('#requirementId').append('<option value="'+type.id+'">'+type.requireName+'</option>');
				});
			}
		}
	});
}

/**
 * 查询省份
 */
function initProvince(){
	$.ajax({
		url:yoyo.portalUrl+'/requirement/queryprovince',
		success:function(data){
			if(data != null){
				$.each(data,function(index,province){
					$('#province').append('<option value="'+province.areaId+'">'+province.areaName+'</option>');
				});
			}
		}
	});
}

/**
 * 查询城市
 * @param cityId
 */
function initCity(provinceId){
	if(provinceId && provinceId != '0'){
		$.ajax({
			url:yoyo.portalUrl+'/goodsManager/getCityInfo',
			data:{'provinceId':provinceId},
			success:function(data){
				if(data!=null&&data.content!=null){
					if(data.content.result==false){
						if(data.content.isProvinceExist==false){
							alert("该省份不存在！");
						}
					}else{
						$('#city').empty();
						$('#city').append('<option value="0">选择城市</option>');
						for(var i=0;i<data.content.length;i++){
							$('#city').append('<option value="'+data.content[i].areaId+'">'+data.content[i].areaName+'</option>');
						}
					}
				}
			}
		});
	}
}

/**
 * 查询地区
 * @param cityId
 */
function initRegion(cityId){
	if(cityId && cityId!='0'){
		$.ajax({
			url:yoyo.portalUrl+'/goodsManager/getCountyInfo',
			data:{'cityId':cityId},
			success:function(data){
				if(data!=null && data.content!=null){
					if(data.content.result==false){
						if(data.content.isCityExist==false){
							alert("该城市不存在！");
						}
					}else{
						$('#region').empty();
						$('#region').append('<option value="0">选择地区</option>');
						for(var i=0;i<data.content.length;i++){
							$('#region').append('<option value="'+data.content[i].areaId+'">'+data.content[i].areaName+'</option>');
						}
					}
				}
			}
		});
	}
}

var patterns = {
		mobile : /^1[3|4|5|8][0-9]{9}$/,
		email : /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
	};

/**
 * 校验
 */
function checkValid(){
	var isValid = true;
	if($('#requirementType').val() == 0){
		$('.error_type').show();
		isValid = false;
	}else{
		$('.error_type').hide();
	}
	if(!($('#name').val() && $('#name').val().trim())){
		$('.error_name').show();
		isValid = false;
	}else{
		$('.error_name').hide();
	}
	if(!patterns.mobile.test($('#phone').val())){
		$('.error_phone').show();
		isValid = false;
	}else{
		$('.error_phone').hide();
	}
	if($('#email').val() && !patterns.email.test($('#email').val())){
		$('.error_email').show();
		isValid = false;
	}else{
		$('.error_email').hide();
	}
//	if($('#province').val() == 0 || $('#city').val() == 0){
//		$('.error_site').show();
//		isValid = false;
//	}else{
//		$('.error_site').hide();
//	}
	if(!$('#content').val()){
		$('.error_content').show();
		isValid = false;
	}else{
		$('.error_content').hide();
	}
	return isValid;
}

/**
 * 提交表单
 */
function submit(){
	$.ajax({
		url:yoyo.portalUrl+'/requirement/submit',
		data:$('#requireForm').serialize()+'&accountId='+accountId+'&accountType='+accountType+'&address='+address,
		type:'POST',
		success:function(data){
			if(data.retCode == yoyo.successCode){
				if(/10\d{1}/.test(accountType)){
					layer.msg('提交成功！稍后可在买家中心查看');
				}else {
					layer.msg('提交成功！稍后可在卖家中心查看');
				}
				setTimeout('window.location.href= yoyo.portalUrl',1500);
			}
		}
	});
}