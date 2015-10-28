var loginUrl = yoyo.portalUrl+'/register/login';
$(function () {
	/**最低价**/
	var carId=$("#carId").val(),
		carDeptId=$("#carDeptId").val(),
		brandId=$("#brandId").val(),
		carYearId=$("#carYearId").val();
	
	$("#low_Brand").change(function (){
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCarDeptHasGoods',
				params:{brandId:$(this).val()},
				callback:function(option){
					$("#low_Series option:gt(0)").remove();
					$("#low_year option:gt(0)").remove();
					$("#low_cartype option:gt(0)").remove();
					$("#low_Series").html(option);
					if(carDeptId){
						$("#low_Series").val(carDeptId).trigger("change");
					}
				}
			};
		getCarSeries(opts);
	});
	$("#low_Brand").val(brandId).trigger("change");
	
	$("#low_Series").change(function (){		
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCarYearHasGoods',
				params:{carDeptId:$(this).val()},
				callback:function(option){
					$("#low_year option:gt(0)").remove();
					$("#low_cartype option:gt(0)").remove();
					$("#low_year").html(option);
					if(carYearId){
						$("#low_year").val(carYearId).trigger("change");
					}
				}
			};
		getCarYear(opts);
	});
	
	$("#low_year").change(function (){
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCarHasGoods',
				params:{carDeptId:$("#low_Series").val(),carYearId:$(this).val()},
				callback:function(option){
					$("#low_cartype option:gt(0)").remove();
					$("#low_cartype").html(option);
					if(carId){
						$("#low_cartype").val(carId);
					}
				}
			};
		getCarType(opts);
	});
	
	/**预约试驾**/
	$("#drive_Brand").change(function (){
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCarDeptHasGoods',
				params:{brandId:$(this).val()},
				callback:function(option){
					$("#drive_Series option:gt(0)").remove();
					$("#drive_year option:gt(0)").remove();
					$("#drive_carType option:gt(0)").remove();
					$("#drive_Series").html(option);
					if(carDeptId){
						$("#drive_Series").val(carDeptId).trigger("change");
					}
				}
			};
		getCarSeries(opts);
	});
	$("#drive_Brand").val(brandId).trigger("change");
	
	$("#drive_Series").change(function (){
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCarYearHasGoods',
				params:{carDeptId:$(this).val()},
				callback:function(option){
					$("#drive_year option:gt(0)").remove();
					$("#drive_carType option:gt(0)").remove();
					$("#drive_year").html(option);	
					if(carYearId){
						$("#drive_year").val(carYearId).trigger("change");
					}
				}
			};
		getCarYear(opts);
	});
	
	$("#drive_year").change(function (){
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCarHasGoods',
				params:{carDeptId:$("#drive_Series").val(),carYearId:$(this).val()},
				callback:function(option){
					$("#drive_carType option:gt(0)").remove();
					$("#drive_carType").html(option);
					if(carId){
						$("#drive_carType").val(carId);
						carId=null;
						carDeptId=null;
						carYearId=null;
						brandId=null;
					}
				}
			};
		getCarType(opts);
	});
	
	/**置换车型**/
	$("#changecont_Brand").change(function (){
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCarDept',
				params:{brandId:$(this).val()},
				callback:function(option){
					$("#changecont_Series").html(option);		
				}
			};
		getCarSeries(opts);
	});
	
	$("#changecont_Series").change(function (){
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCarYear',
				params:{deptId:$(this).val()},
				callback:function(option){
					$("#changecont_year").html(option);		
				}
			};
		getCarYear(opts);
	});
	
	$("#changecont_year").change(function (){
		if($(this).val()==0)return;
		var opts={
				url:yoyo.portalUrl+'/comboBox/findCar',
				params:{deptId:$("#changecont_Series").val(),carYearId:$(this).val()},
				callback:function(option){
					$("#changecont_type").html(option);		
				}
			};
		getCarType(opts);
	});
	
	$(".xieyi_link").click(function (){
		layer.open({
			type: 1,
			title:false,
			move: false,
			area: ['790px', '600px'],
			closeBtn :2,
			zIndex:999,
			shadeClose: true, //点击遮罩关闭
			content:$("#xieyi1")			
		});
	});
	var isBuyer = $('#isBuyer').val();
	if(isBuyer!=null&&isBuyer!=''&& isBuyer == 'false'){
		window.location.href = loginUrl;
	}
	var type = $('#type').val();
	if(type == "2"){
		changeType($('#tab2'));
	}else{
		changeType($('#tab1'));
	}
	
	//切换询价/预约
	$(".tabcont-title ul li").click(function(e) {
		changeType(this);
		findApplyList($(this).attr("id").substring(3,4));
    });	
	
	//切换申请置换
	$("#trade").click(function(e) {
		if($(this).is(":checked")){
			$(".changecont ").removeClass("fn-hide");
		}else{
			$(".changecont ").addClass("fn-hide");
		}
    });
	
    //查询申请历史记录
    findApplyList(type);

    //置换--修改品牌
    jQuery("#sCar_Brand").change(function () {
    	var brandId  = $(this).val();
    	if(brandId!=null&&brandId!=''&&brandId!='0'){
//    		getCarDeptList($(this).val());
    		getUserCarList($(this).val());
    	}
    });
    //置换--修改车型
    jQuery("#sCar_Series").change(function () {
    	var carDeptId  = $(this).val();
    	if(carDeptId!=null&&carDeptId!=''&&carDeptId!='0'){
//    		getUserCarList($(this).val());
    		getUserCarDeptList($(this).val());
    	}
    });
    
    //修改城市
    jQuery("select[id^='AreaCity_']").change(function () {
    	var cityId  = $(this).val();
    	if(cityId!=null&&cityId!=''&&cityId!='0'){
    		getCountyInfo($(this).val(), $(this).attr("id"));
    	}
    });
    
    //姓名过滤特殊字符
    jQuery("input[id^='name_']").change(function () {
    	$(this).val($(this).val().replace(/[~'!<>@#$%^&*()-+_=:]/g, ""));
    	$(this).val($(this).val().replace(/[【】；‘、，。、; :\/\\.,.\[\]`-]/g, ""));
    	$(this).val($(this).val().replace(/[——?"|{}《》？……（）￥！：’"“”]/g, ""));
    });
    
    jQuery("input[id^='name_']").keyup(function () {
//    	$(this).val($(this).val().replace(/[~'!<>@#$%^&*()-+_=:|“”’‘；;?？，。、【】,. \/\\]/g, ""));
//    	$(this).val($(this).val().replace(/[\[\]`-·！￥……（）——：》《-]/g, ""));
    	$(this).val($(this).val().replace(/[~'!<>@#$%^&*()-+_=:]/g, ""));
    	$(this).val($(this).val().replace(/[【】；‘、，。、; :\/\\.,.\[\]`-]/g, ""));
    	$(this).val($(this).val().replace(/[——?"|{}《》？……（）￥！：’"“”]/g, ""));
    });
    
    
    //修改省份
    jQuery("select[id^='AreaProvince_']").change(function () {
    	var provinceId  = $(this).val();
    	if(provinceId!=null&&provinceId!=''&&provinceId!='0'){
    		getCityInfo($(this).val(), $(this).attr("id"));
    	}
    });
    
});

function subApply(form){
	var type=1;
	if($("#tab2").hasClass("current")){
		type=2;
	}
	if(document.getElementById("articleCB_"+type).checked == false){
		alert("请勾选个人信息保护声明");
		return;
	}
    var flag = submitValidate(type);
    var flagScar = true;    
    if(type==1){
    	if($("#trade").is(":checked")){
        	$("#isReplace").val(1);
        	flagScar = sCarValidate();
        }
    }
    if(type==1){
    	$("#low_type").val(type);
    }else{
    	$("#drive_type").val(type);
    }
    if (flag && flagScar) {  	
    	var params=$("#"+form).serialize();
    	var url=yoyo.portalUrl+"/goodsManager/createOrder";
		commonAjax(url, params, function(data) {
			var ele = null;
			if(type==1){
				ele = $(".btn-orange");
			}else if(type==2){
				ele = $(".btn-blue");
			}
			if(data.content.result==true){
				alert("申请已成功提交，我们将及时与您联系！");
				//window.location.reload(true);
			}else if(data.content.result==false){
				if(data.content.isObjectExist==true){
					alert("很抱歉！您已经对本车型提交过该意向，请勿重复提交！");
				}else{
					alert(data.content.msg);
					$(ele).next().hide();
					$(ele).show();
				}
			}else{
				alert(errorMsg);
			}
		}, function(data) {});
    }
}

//切换询价与试驾
function changeType(ele){
	var currentId = $(ele).attr("id");
    $(ele).addClass("current").siblings().removeClass("current");
	$(".card").removeClass("current");
	$("#" + currentId + "_select" ).addClass("current");
}

//查询申请历史记录
function findApplyList(type){
	var url = yoyo.portalUrl+"/goodsManager/getApplyList";
	var params = {};
	params.type = type;
	commonAjax(url, params, function(data) {
		if(data.content!=null&&data.content.rows!=null&&data.content.rows.length>=1){
			$('.notes-ul').empty();
			var str = '';
			for(var i=0;i<data.content.rows.length;i++){
				if(i%4==0){
					str += '<li class="bg-grey"><p>'+new Date(data.content.rows[i].addTime).format("yyyy-MM-dd hh:mm:ss")+'&nbsp;&nbsp;&nbsp;'+data.content.rows[i].cityName+'&nbsp;&nbsp;'+data.content.rows[i].userName+data.content.rows[i].applyType+'成功</p>';
				}else if(i%4==1){
					str += '<p>'+data.content.rows[i].addDate+'&nbsp;&nbsp;&nbsp;'+data.content.rows[i].cityName+'&nbsp;&nbsp;'+data.content.rows[i].userName+data.content.rows[i].applyType+'成功</p></li>';
				}else if(i%4==2){
					str += '<li><p>'+data.content.rows[i].addDate+'&nbsp;&nbsp;&nbsp;'+data.content.rows[i].cityName+'&nbsp;&nbsp;'+data.content.rows[i].userName+data.content.rows[i].applyType+'成功</p>';
				}else if(i%4==3){
					str += '<p>'+data.content.rows[i].addDate+'&nbsp;&nbsp;&nbsp;'+data.content.rows[i].cityName+'&nbsp;&nbsp;'+data.content.rows[i].userName+data.content.rows[i].applyType+'成功</p></li>';
				}
				if(i%4==3 || i==data.content.rows.length-1){
					$('.notes-ul').append(str);
					str = '';
				}
			}
		}
	}, function(data) {
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
}

function getCarSeries(opts){
	commonAjax(opts.url, opts.params, function(data) {
		var option=['<option value="0">选择车系</option>'];
		if(data!=null&&data.content!=null){
			$.each(data.content,function (x,item){
				option.push('<optgroup label="'+item.factoryName+'">');
				$.each(item.carDepts,function (x,item){
					option.push('<option value="'+item.carDeptId+'">'+item.carDeptName+'</option>');
				});
				option.push('</optgroup>');
			});
			opts.callback(option.join(""));
		}		
	}, function(data) {});
}

function getCarYear(opts){
	commonAjax(opts.url, opts.params, function(data) {
		var option=['<option value="0">选择年款</option>'];
		if(data!=null&&data.content!=null){
			$.each(data.content,function (x,year){
				option.push('<option value="'+year.id+'">'+year.carYearValue+'</option>');
			});
			opts.callback(option);
		}
	}, function(data) {});
}

function getCarType(opts){
	commonAjax(opts.url, opts.params, function(data) {
		var option=['<option value="0">选择车型</option>'];
		if(data!=null&&data.content!=null){
			$.each(data.content,function (x,item){				
				option.push('<option value="'+item.carId+'">'+item.carName+'</option>');
			});
			opts.callback(option);
		}
	}, function(data) {});
}

//根据用户旧车的品牌查找车型
function getUserCarList(brandId){
	if(brandId!=null&&brandId!=''&&brandId!='0'){
		var url = yoyo.portalUrl+'/goodsManager/getUserCarList';
		var params = {};
		params.brandId = brandId;
		commonAjax(url, params, function(data) {
			if(data!=null&&data.content!=null){
				if(data.content.result==false){
					if(data.content.isBrandExist==false){
						alert("该品牌不存在！");
					}
				}else{
					$('#sCar_Series').empty();
					$('#sCar_Series').append('<option value="0">选择车型</option>');
					for(var i=0;i<data.content.length;i++){
						$('#sCar_Series').append('<option value="'+data.content[i].carId+'">'+data.content[i].carName+'</option>');
					}
				}
			}
			
		}, function(data) {
			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}
}


//根据用户旧车的车型查找车系
function getUserCarDeptList(carId){
	if(carId!=null&&carId!=''&&carId!='0'){
		var url = yoyo.portalUrl+'/goodsManager/getUserCarDeptList';
		var params = {};
		params.carId = carId;
		commonAjax(url, params, function(data) {
			if(data!=null&&data.content!=null){
				if(data.content.result==false){
					if(data.content.isCarExist==false){
						alert("该车型不存在！");
					}
				}else{
					$('#sCar_Spec').empty();
					$('#sCar_Spec').append('<option value="0">选择车系</option>');
					for(var i=0;i<data.content.length;i++){
						$('#sCar_Spec').append('<option value="'+data.content[i].carDeptId+'">'+data.content[i].carDeptName+'</option>');
					}
				}
			}
			
		}, function(data) {
			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}
}

//修改车系
function getCarList(carDeptId, eleId){
	if(carDeptId!=null&&carDeptId!=''&&carDeptId!='0'){
		var url = yoyo.portalUrl+'/goodsManager/getCarList';
		var params = {};
		params.carDeptId = carDeptId;
		commonAjax(url, params, function(data) {
			if(data!=null&&data.content!=null){
				if(data.content.result==false){
					if(data.content.isCarDeptExist==false){
						alert("该车系不存在！");
					}
				}else{
					eleId = eleId.split("_")[1];
					$('#slSpec_'+eleId).empty();
					$('#slSpec_'+eleId).append('<option value="">选择车型</option>');
					for(var i=0;i<data.content.length;i++){
						$('#slSpec_'+eleId).append('<option value="'+data.content[i].carId+'">'+data.content[i].carName+'</option>');
					}
				}
			}
			
		}, function(data) {
			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}
}

//修改省份
function getCityInfo(provinceId,eleId){
	if(provinceId!=null&&provinceId!=''&&provinceId!='0'){
		var url = yoyo.portalUrl+'/goodsManager/getCityInfo';
		var params = {};
		params.provinceId = provinceId;
		commonAjax(url, params, function(data) {
			if(data!=null&&data.content!=null){
				if(data.content.result==false){
					if(data.content.isProvinceExist==false){
						alert("该省份不存在！");
					}
				}else{
					eleId = eleId.split("_")[1];
					$('#AreaCity_'+eleId).empty();
					$('#AreaCity_'+eleId).append('<option value="0">选择城市</option>');
					for(var i=0;i<data.content.length;i++){
						$('#AreaCity_'+eleId).append('<option value="'+data.content[i].areaId+'">'+data.content[i].areaName+'</option>');
					}
				}
			}
			
		}, function(data) {
			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}
}

//修改城市
function getCountyInfo(cityId,eleId){
	if(cityId!=null&&cityId!=''&&cityId!='0'){
		var url = yoyo.portalUrl+'/goodsManager/getCountyInfo';
		var params = {};
		params.cityId = cityId;
		commonAjax(url, params, function(data) {
			if(data!=null&&data.content!=null){
				if(data.content.result==false){
					if(data.content.isCityExist==false){
						alert("该城市不存在！");
					}
				}else{
					eleId = eleId.split("_")[1];
					$('#AreaCounty_'+eleId).empty();
					$('#AreaCounty_'+eleId).append('<option value="0">选择地区（可不选）</option>');
					for(var i=0;i<data.content.length;i++){
						$('#AreaCounty_'+eleId).append('<option value="'+data.content[i].areaId+'">'+data.content[i].areaName+'</option>');
					}
				}
			}
			
		}, function(data) {
			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}
}

//提交表单验证
function submitValidate(type) {
	var flag = true;
	if(type==1){
		if (jQuery("#low_cartype").val() == 0) {
			jQuery(".error_spec_"+type).show();
			flag = false;
		} else {
			jQuery(".error_spec_"+type).hide();
		}		
	}else{
		if (jQuery("#drive_carType").val() == 0) {
			jQuery(".error_spec_"+type).show();
			flag = false;
		} else {
			jQuery(".error_spec_"+type).hide();
		}
	}
	if (jQuery("#AreaCity_"+type).val() == "0") {
		jQuery(".error_city_"+type).html("请选择您的城市").show();
		flag = false;
	} else {
		jQuery(".error_city_"+type).hide();
	}
	if ($.trim(jQuery("#name_"+type).val()) == "") {
		jQuery(".error_name_"+type).show();
		flag = false;
	} else {
		jQuery(".error_name_"+type).hide();
	}
	if ($.trim(jQuery("#phone_"+type).val()) == "") {
		jQuery(".error_phone_"+type).html("请填写您的手机号码").show();
		flag = false;
	} else if (!checkMobilePhone($.trim(jQuery("#phone_"+type).val()))) {
		jQuery(".error_phone_"+type).html("请填写正确的手机号码").show();
		flag = false;
	} else {
		jQuery(".error_phone_"+type).hide();
	}
	
	return flag;
} 

//手机号码验证
function checkMobilePhone(val) {
    var $mobile = val; var myreg = /^(1[0-9]{10})$/; if ($mobile.length == 0) { return false; }
    else if ($mobile.length != 11 || !myreg.test($mobile)) { return false; }
    else return true;
}

//置换车型表单验证
function sCarValidate() {
	var flag = true;
	if (jQuery("#changecont_type").val() == 0) {
		jQuery(".error_spec_scar").show();
		flag = false;
	} else {
		jQuery(".error_spec_scar").hide();
	}
	if (jQuery("#sCar_DatePic_Year").val() == "0"||jQuery("#sCar_DatePic_Month").val() == "0") {
		jQuery(".error_pic").show();
		flag = false;
	} else {
		jQuery(".error_pic").hide();
	}
	var miles = $.trim(jQuery("#txt_Scar_Miles").val());
	if (miles == "") {
		jQuery(".error_miles").show();
		flag = false;
	} else if (isNaN(miles)||parseFloat(miles)<0.01||parseFloat(miles)>99.99) {
		jQuery(".error_miles").show();
		flag = false;
	} else {
		jQuery(".error_miles").hide();
	}
	return flag;
}

