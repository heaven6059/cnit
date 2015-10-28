var patterns = {
	mobile : /^1[3|4|5|8][0-9]{9}$/,
	userName : /[a-zA-Z0-9_-]{1,16}$/,
	password : /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{8,16}$/,
	email : /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i};
/**
 * 方法描述：获取短信验证码 作者：李明 创建时间：2015-2-12
 * 
 * @param $obj
 */
function getSmsCode(obj, button) {
	var $obj = $("#" + obj);
	if ($obj && patterns.mobile.test($obj.val())) {
		$.ajax({type : 'POST', url : _path + '/oauth/sendSmsCode', data : 'mobile=' + $obj.val() + '&_rand=' + Math.random()}).done(function(data) {
			codeInterval(button, 120);
		});
	} else {
		renderMsg($obj, '请输入手机号码！');
	}
}
/**
 * 两次获得验证码的定时器
 * 
 * @param button
 * @param times
 */
function codeInterval(button, times) {
	var $button = $('#' + button);
	$button.attr('disabled', 'disabled');
	$button.attr('stoper', setInterval(function() {
		clearInterval($button.attr('stoper'));
		clearInterval($button.attr('counter'));
		$button.removeAttr('stoper');
		$button.removeAttr('counter');
		$button.removeAttr('disabled');
		$button.removeAttr('sec');
		$button.text($button.attr('oranText'));
	}, times * 1000));
	$button.attr('sec', times);
	$button.attr('counter', setInterval(function() {
		var sec = $button.attr('sec');
		sec -= 1;
		$button.attr('sec', sec);
		$button.text(sec + '秒后重新获取验证码');
	}, 1000));
}

/**
 * 方法描述：获取有效验证码 作者：李明 创建时间：2015-3-17
 * 
 * @param obj
 */
function getEmailCode(obj, button) {
	var $obj = $("#" + obj);
	if ($obj && $obj.val()) {
		if (patterns.email.test($obj.val())) {
			$.ajax({type : 'POST', url : _path + '/oauth/sendEmailCode', data : 'email=' + $obj.val() + "&loginName=" + $('#id_loginName').val() + '&_rand=' + Math.random()}).done(function(data) {
				if (data.retCode != '000000') {
					renderMsg($obj, data.retMsg);
				} else {
					codeInterval(button, 120);
					var content = '验证码已发送至： ' + $obj.val() + ' 邮箱，请及时查收！';
					renderDialog(content);
				}
			});
		} else {
			renderMsg($obj, '请输入邮箱格式不正确！');
		}
	} else {
		renderMsg($obj, '请输入邮箱！');
	}
}
/**
 * 方法描述：刷新图形验证码 作者：李明 创建时间：2015-2-12
 * 
 * @param obj
 */
function refresh(obj) {
	obj.src = _path + '/verifyImage?width=60&height=30&_rand=' + Math.random();
}
/**
 * 方法描述：获取第三方登录窗口 作者：李明 创建时间：2015-2-12
 * 
 * @param thirdType
 */
function getAuthorizeURL(thirdType) {
	var callback = "http://www.unvs.cn/oauth/qq_js/Fqqlogin.html";
	$.ajax({type : "POST", url : _path + "/oauth/getAuthorizeURL", data : "thirdType=" + thirdType + "&accountType=" + $('#id_accountType').val()+ "&callback=" + callback + "&_rand=" + Math.random(), success : function(data) {
		if (data.head.retCode == '000000') {
			layer.open({
			    type: 2,
			    title: false,
			    shadeClose: true,
			    closeBtn : false,
			    shade: 0.8,
			    area: ['860px', '400px'],
			    content: data.content //iframe的url
			}); 
		}
	}});
}


function getOs()  
{  
    var OsObject = "";  
   if(navigator.userAgent.indexOf("MSIE")>0) {  
        return "MSIE";  
   }  
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){  
        return "Firefox";  
   }  
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {  
        return "Safari";  
   }   
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){  
        return "Camino";  
   }  
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){  
        return "Gecko";  
   }  
    
}  

var my_car_utils={
	my_car_cook:"my_car",
	save_my_car:"save_my_car",
	is_login:"IS_LOGIN",
	showSelectCarLayer:function(opts){
		layer.open({
			type: 1,
			title:false,
			move: false,
			area: ['700px', '435px'],
			closeBtn :2,
			zIndex:999,
			shadeClose: true, //点击遮罩关闭
			content:opts.layerObj,
			end:opts.end,
			yes:opts.yes
		});
	},
	notLogin:function (){
		layer.msg('抱歉您没登录,请先未登录', function(){
		});
	},
	addCarFailure:function (){
		if($(".cart_txt").length>0){
			var area=["280px","20px"];
			if(getOs()=="Safari"){
				area=["280px","25px"];
			}
			layer.tips("车型信息未保存,<a style='color:#ddd;font-size:14px;font-weight:bloder' href='"+yoyo.portalUrl+"/register/login'>登录</a>或<a style='color:#ddd;font-size:14px;font-weight:bloder' href='"+yoyo.portalUrl+"/register/signup'>注册</a>后将会自动保存", '.cart_txt', {
				tips:3,
				time:false,
				closeBtn :1,
				zIndex:98,
				area:area,				
				success: function(layero, index){						
					var left=$("#addMyCar").offset().left-($(layero).width()/2-$("#addMyCar").width()/2);
			        $(layero).css({left:left,"font-size":"12px"});
			        $(layero).find("i").css({left:$(layero).width()/2-4});
			    }
			});
		}
	},
	addCarSuccess:function (){
		this.writeCookie(this.save_my_car, "1");
	},
	readCookie:function (cookieName){
		var cookieV=$.cookie(cookieName);// 存储 cookie
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7, path: "/"}); // 存储 cookie
	},
	readMyCar:function(){
		if(my_car_utils.readCookie(my_car_utils.is_login)){
			commonAjax(yoyo.memUrl+"/membercar/findMemberDefaultCar",null,function (data){		
				var carinfo=[],car=data.content;
				if(car){
					carinfo.push(car.carId);
					carinfo.push(car.carDeptId);
					carinfo.push(car.carBrandId);
					carinfo.push(car.carName);
					carinfo.push(car.carYear);				
					my_car_utils.writeCookie(my_car_utils.my_car_cook, carinfo.join("|"));
					my_car_utils.addCarSuccess();
				}
				my_car_utils.readMyTitleCar();
			},function (){
				my_car_utils.readMyTitleCar();
			});
		}else{
			my_car_utils.readMyTitleCar();
		}
	},
	readMyTitleCar:function (){
		var myCarCookie=my_car_utils.readCookie(my_car_utils.my_car_cook);
		if(myCarCookie){
			if(!my_car_utils.readCookie(my_car_utils.is_login)){
				my_car_utils.addCarFailure();
			}
			
			var myCarValue=myCarCookie.split("|");
			$(".cart_txt").find(".openmt").html(myCarValue[3]+"&nbsp;&nbsp;"+myCarValue[4]).attr("title",myCarValue[3]+" "+myCarValue[4]);			
			$(".cart_txt").find(".my_car_list").text("我的车型").attr("href",yoyo.memUrl+"/membercar/memberCarList");
		}else{
			var area =['360px', '30px'];
			if(getOs()=="Safari"){
				area =['360px', '40px'];
			}
			if($('#addMyCar').length>0){
				layer.tips("请选择您的爱车,我们将为您提供个性化的汽车终生服务，让您省时、省力、省钱、省心、为您提升品质汽车生活", '#addMyCar', {
					tips:3,
					time:false,
					zIndex:99,
					closeBtn :2,
					area: area,
					success: function(layero, index){						
						var left=$("#addMyCar").offset().left-($(layero).width()/2-$("#addMyCar").width()/2);
				        $(layero).css({left:left});
				        $(layero).find("i").css({left:$(layero).width()/2-4});
				    }
				});
			}
		}
	},
	saveMyCar:function (){
		if(!my_car_utils.readCookie(my_car_utils.is_login)){
			return;
		}
		var myCarCookie=this.readCookie(this.my_car_cook);
		if(myCarCookie){
			var myCarValue=myCarCookie.split("|");
			var rqeustdata={
				carBrandId:myCarValue[0],
				carDeptId:myCarValue[1],
				carId:myCarValue[2],
				carYear:myCarValue[4],
				isDefault:1
			};
			commonAjax(yoyo.memUrl+"/membercar/hasAddCurrentCar",rqeustdata,function (data){
				if(data.content==0){
					commonAjax(yoyo.memUrl+"/membercar/saveMemberCar",rqeustdata,function (data){
						my_car_utils.addCarSuccess();
						my_car_utils.readMyCar();
					},function (data){
						try{
							if(data.retCode=="000009"){
								layer.msg('最多只能添加6辆车型，请前往我的车型列表修改后再添加',{time: 3000});
							}
						}catch (e) {}	
					});
				}else{
					my_car_utils.addCarSuccess();
					layer.msg('该车型已添加',{time:2000});
				}
			},function(data){});
		}
	},initSel:function(items){
		$.each(items,function (x,item){
			$(item).select2({placeholder: "--请选择--",allowClear: true});
//			$(item).select2("val",'');
	    	$(item).find("option").remove();
		});
	}
};

$(function (){
	if("1"!=my_car_utils.readCookie(my_car_utils.save_my_car)){
		my_car_utils.saveMyCar();
	}
	my_car_utils.readMyCar();
	$("#addMyCar").click(function (){
		var opts={
			layerObj:$("#myCar"),
			end:function (){
				try{
					var mycarVal=my_car_utils.readCookie("my_car").split("|");				
					if(mycarVal&&index){
						index.buildYear(mycarVal[4]);
					}
				}catch(e){}
				choose_car.destory();
			}
		};	
		my_car_utils.showSelectCarLayer(opts);
	});
	choose_car.init(false);
});

var choose_car={
	crumbArray:[],
	init:function(refresh){
		if(refresh){
			crumbArray=[];
			$(".layerStep").removeClass().addClass("layerStep");
			$(".layerStep").find("li:eq(0)").addClass("curStep").siblings().removeClass("curStep");
			$("#choosecar1_div_choosecar").children().remove();
		}
		choose_car.removeCurmb();
		choose_car.chooseBrand();		
		$("#choosecar1_div_choosecar").on("click","li",function (){
			var param=JSON.parse($(this).attr("param"));
			choose_car.crumbArray.push(param);
			choose_car.brandCrumb();
			switch(param.chooseType){
			case 1:
				choose_car.layerStep(1);
				choose_car.chooseCarDept(param);
			  break;
			case 2:
				choose_car.layerStep(2);
				$(".layerStep").addClass("layerStep2").find("li:eq(2)").addClass("curStep").siblings().removeClass("curStep");
				choose_car.chooseCar(param.id);
			  break;
			case 3:
				choose_car.layerStep(2);
				$(".layerStep").addClass("layerStep3").find("li:eq(3)").addClass("curStep").siblings().removeClass("curStep");
				choose_car.chooseYear(param.id,param.value);
			  break;
			case 4:				
				choose_car.chooseFinish(param.id);
			  break;
			}
		});
	},
	destory:function (){
		choose_car.layerStep(0);
		choose_car.crumbArray=[];
		$("#choosecar1_div_choosecar").children().remove();
		choose_car.chooseBrand();
	},
	removeCurmb:function (){
		$("#choosecar1_div_choosecar").on("click",".brandCrumb>p>a",function (){
			switch($(this).parent().index()){
			case 1:
				choose_car.layerStep(0);
				choose_car.crumbArray=[];
				$("#choosecar1_div_choosecar").children().remove();
				choose_car.chooseBrand();
			  break;
			case 2:
				choose_car.layerStep(1);
				$("#choosecar1_div_choosecar").children().remove();
				var brand=choose_car.crumbArray[0];
				choose_car.crumbArray.splice(1,3);
				choose_car.brandCrumb();
				choose_car.chooseCarDept(brand);
			  break;
			case 3:
				choose_car.layerStep(2);
				$("#choosecar1_div_choosecar").children().remove();
				var carDeptId=choose_car.crumbArray[1].id;
				choose_car.crumbArray.splice(2,2);
				choose_car.brandCrumb();
				choose_car.chooseCar(carDeptId);
			  break;
			}
			$(this).unbind("click");
		});
	},
	layerStep:function (index){
		$(".layerStep").removeClass().addClass("layerStep layerStep"+index).find("li:eq("+index+")").addClass("curStep").siblings().removeClass("curStep");
	},
	brandCrumb:function (){
		var html=[];
		html.push('<div class="brandCrumb">');
		html.push('<b>已选车型：</b>');
		$.each(choose_car.crumbArray,function(x,item){
			html.push('<p>'+item.value+'<a href="javascript:;">X</a></p>');
		});
		html.push('</div>');
		$("#choosecar1_div_choosecar").html(html.join(""));
	},chooseBrand:function(){
		$.post(yoyo.portalUrl+"/comboBox/findCarBrand",{identifier:yoyo.car},function(data){
			var html=[];
			var let = ["A","B","C","D","F","H","J","K","L","M","Q","R","S","T","W","X","Y","Z"];
			html.push('<div class="brandChoose">');
			html.push('<b>品牌首字母选择：</b>');
			html.push('<ul>');
			html.push('<li class="hotb chooseCur "><a href="javascript:void()">热门</a></li>');
			$.each(let,function (x,item){
			html.push('<li class=""><a  href="javascript:void()">'+item+'</a><span></span></li>');
			});
			html.push('</ul>');
			html.push('</div>');
			
			
			html.push('<div class="brandCompany">');
			html.push('<ul>');
			$.each(data.content,function (x,brand){
				html.push('<li param=\'{\"id\":'+brand.brandId+',\"value\":\"'+brand.brandName+'\",\"logo\":\"'+brand.brandLogo+'\",\"chooseType\":1}\'>');
				html.push('<a href="javascript:;" title="'+brand.brandName+'">');
				html.push('<dl>');
				html.push('<dt><img width="36" height="36" src="'+yoyo.imagesUrl+brand.brandLogo+'"></dt>');
				html.push('<dd>'+brand.brandName+'</dd>');
				html.push('</dl>');
				html.push('</a>');
				html.push('</li>');
			});
			html.push('</ul>');
			html.push('</div>');
			$("#choosecar1_div_choosecar").append(html.join(""));
		});
	},chooseCarDept:function(param){
		var html=[];
		$("#brand_choose_input").val(param.id);
		$("#brand_log_choose_input").val(param.logo);
		$.post(yoyo.portalUrl+"/comboBox/findCarDept",{brandId:param.id},function(data){
			html.push('<div class="carNature">');
			$.each(data.content,function (x,item){
				html.push('<b>'+item.factoryName+'</b>');				
				html.push('<ul>');
				$.each(item.carDepts,function (x,item){
					html.push('<li param=\'{\"id\":'+item.carDeptId+',\"value\":\"'+item.carDeptName+'\",\"chooseType\":2}\'>');
				    html.push('<a title="'+item.carDeptName+'" href="javascript:;">'+item.carDeptName+'</a>');
				    html.push('</li>');
				});
				html.push('</ul>');
			});
			html.push('</div>');
			$("#choosecar1_div_choosecar").append(html.join(""));
		});
	},chooseCar:function(deptId){		
		$("#dept_choose_input").val(deptId);
		$.post(yoyo.portalUrl+"/comboBox/findCar",{deptId:deptId},function(data){
			var html=[];
			html.push('<div class="carNature">');
			html.push('<ul>');
			$.each(data.content,function (x,item){
				html.push('<li param=\'{\"id\":'+item.carId+',\"value\":\"'+item.carName+'\",\"chooseType\":3}\'>');
				html.push('<a title="'+item.carName+'" href="javascript:;">'+item.carName+'</a>');
				html.push('</li>');
			});
			html.push('</ul>');
			html.push('</div>');
			$("#choosecar1_div_choosecar").append(html.join(""));
		});
	},chooseYear:function(carId,carName){
		$("#car_choose_input").val(carId);
		$("#car_name_choose_input").val(carName);
		$.post(yoyo.portalUrl+"/comboBox/findCarYear",{deptId:$("#dept_choose_input").val()},function (data){
			var html=[];
			html.push('<div class="carNature">');
			html.push('<ul>');
			$.each(data.content,function (x,item){
				html.push('<li  param=\'{\"id\":'+item.carYearValue+',\"value\":\"'+item.carYearValue+'\",\"chooseType\":4}\'>');
				html.push('<a title="'+item.carYearValue+'" href="javascript:;">'+item.carYearValue+'</a>');
				html.push('</li>');
			});
			html.push('</ul>');
			html.push('</div>');
			$("#choosecar1_div_choosecar").append(html.join(""));	
		});
	},chooseFinish:function(carYear){		
		$("#year_choose_input").val(carYear);
		var carinfo=[];
		carinfo.push($("#brand_choose_input").val());
		carinfo.push($("#dept_choose_input").val());
		carinfo.push($("#car_choose_input").val());
		carinfo.push($("#car_name_choose_input").val());
		carinfo.push($("#year_choose_input").val());				
		my_car_utils.writeCookie(my_car_utils.my_car_cook, carinfo.join("|"));
		my_car_utils.readMyCar();				
		my_car_utils.saveMyCar();
		my_car_utils.writeCookie(my_car_utils.save_my_car, "0");		
		layer.closeAll();
	}
};

/**
 * 方法描述：验证图片验证码 作者：李明 创建时间：2015-2-12
 * 
 * @param $obj
 */
function validateImgCode(obj, sucCallback, failCallback) {

	var $obj = $('#' + obj);
	if ($obj && $obj.val()) {
		commonAjax(_path + "/oauth/validateImgCode", "imgCode=" + $obj.val(), sucCallback, function(data) {
			renderMsg($obj, data.retMsg);
			failCallback(data);
		});
	} else {
		renderMsg($obj, '请输入验证码！');
		$obj.focus();
	}
}
/**
 * 方法描述：验证短信验证码 作者：李明 创建时间：2015-2-12
 * 
 * @param $obj
 */
function validateSmsCode(obj, sucCallback, failCallback) {
	var $obj = $('#' + obj);
	if ($obj && $obj.val()) {
		commonAjax(_path + "/oauth/validateSmsCode", "smsCode=" + $obj.val(), sucCallback, function(data) {
			renderMsg($obj, data.retMsg);
			failCallback(data);
		});
	} else {
		renderMsg($obj, '请输入短信验证码！');
		$obj.focus();
	}
}
/**
 * 方法描述：验证邮箱验证码 作者：李明 创建时间：2015-3-17
 * 
 * @param obj
 * @param sucCallback
 * @param failCallback
 */
function validateEmailCode(obj, sucCallback, failCallback) {
	var $obj = $('#' + obj);
	if ($obj && $obj.val()) {
		commonAjax(_path + "/oauth/validateEmailCode", "emailCode=" + $obj.val(), sucCallback, function(data) {
			renderMsg($obj, data.retMsg);
			failCallback(data);
		});
	} else {
		renderMsg($obj, '请输入邮箱验证码！');
		$obj.focus();
	}
}
/**
 * 方法描述：消息提示 作者：李明 创建时间：2015-2-12
 * 
 * @param $obj
 * @param msg
 */
function renderMsg($obj, msg) {
	layer.tips(msg, $obj, {tips : [1, '#3595CC'], time : 4000});
}
/**
 * 方法描述：消息提示弹出层 作者：李明 创建时间：2015-2-12
 * 
 * @param $obj
 * @param msg
 */
function renderDialog(msg) {
	layer.open({type : 1, skin : 'layui-layer-demo', // 样式类名
	closeBtn : true, shift : 2, shadeClose : true, content : msg});
}
/**
 * 方法描述：通用表单提交 作者：李明 创建时间：2015-3-2
 * 
 * @param $obj
 * @param url
 * @param sucCallback
 * @param failCallback
 */
function formSubmit(obj, url, sucCallback, failCallback) {
	var $obj = $('#' + obj);
	if ($obj) {
		commonAjax(url, $obj.serializeArray(), sucCallback, failCallback);
	} else {
		renderMsg($obj, '未知表单数据！');
	}
}
/**
 * 方法描述：通用信息校验方法 作者：李明 创建时间：2015-3-13
 * 
 * @param url
 * @param data
 * @param sucCallback
 * @param failCallback
 */
function commonAjax(url, data, sucCallback, failCallback) {
	$.ajax({type : 'POST', url : url, data : data}).done(function(data) {		
		if (data.retCode != '000000') {
			if ($.isFunction(failCallback)) {
				failCallback(data);
			}
		} else {
			if ($.isFunction(sucCallback)) {
				sucCallback(data);
			}
		}
	});
}
/**
 * 方法描述：检查账户是否存在 作者：李明 创建时间：2015-3-16
 * 
 * @param obj
 */
function checkAccountExist(obj, data, sucCallback, failCallback) {
	var $obj = $('#' + obj);
	if ($obj) {
		commonAjax(_path + '/sign/checkAccountExist', data, sucCallback, failCallback);
	} else {
		renderMsg($obj, '未知表单数据！');
	}
}
function testLoginNameType(val) {
	if (patterns.email.test(val)) {
		return '1';
	} else if (patterns.mobile.test(val)) {
		return '2';
	} else if (patterns.userName.test(val)) {
		return '3';
	} else {
		return '0';
	}
}

/**
 * init
 * 
 * $(function() { // i18n 国际化加载 cookie var language =
 * $.cookie('clientLanguage'); if (language == null) { language = 'zh_CN'; }
 * jQuery.i18n.properties({// 加载资浏览器语言对应的资源文件 name : 'Messages', // 资源文件名称
 * language : language,// 国际化语言 path : biz.rootPath() + '/base/i18n/', // 资源文件路径
 * mode : 'map', // 用Map的方式使用资源文件中的值 callback : function() {// 加载成功后设置显示内容 } }); //
 * console.info($.i18n.prop('test00')); });
 */
/**
 * 命名空间
 */
var biz = $.extend({}, biz);
/**
 * 默认图片
 */
biz.defaultPic = function() {
	return biz.rootPath() + "/resources/images/pre_default.png";
};

/**
 * 获得全路径
 */
biz.rootBasePath = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};
/**
 * 获取根路径
 */
biz.rootPath = function() {
	var pathName = window.document.location.pathname;
	return pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
};
/**
 * 字符串格式化
 */
biz.formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};
/**
 * 字符串全格式化
 */
biz.formatStringAll = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace(new RegExp('\\{' + i + '\\}', "gm"), arguments[i + 1]);
	}
	return str;
};

/**
 * 将秒转换为时间格式例如00:32:23 秒数不能超过一天，否则返回“” hhq
 */
biz.formatSeconds = function(seconds) {
	function formatPart(num) {
		if (num < 10) {
			return "0" + num;
		} else {
			return num + "";
		}
	}

	if (seconds < 60) {
		return "00:00:" + formatPart(seconds);
	} else if (seconds < 60 * 60) {
		var minutes = parseInt(seconds / 60);
		var second = seconds % 60;
		return "00:" + formatPart(minutes) + ":" + formatPart(second);
	} else if (seconds < 24 * 60 * 60) {
		var hours = parseInt(seconds / (60 * 60));
		var minutes = parseInt((seconds - hours * 60 * 60) / 60);
		var second = (seconds - hours * 60 * 60) % 60;
		return formatPart(hours) + ":" + formatPart(minutes) + ":" + formatPart(second);
	} else {
		return "";
	}
}
// 引入css文件
biz.addCssByLink = function(url) {
	var doc = document;
	var link = doc.createElement("link");
	link.setAttribute("rel", "stylesheet");
	link.setAttribute("type", "text/css");
	link.setAttribute("href", url);

	var heads = doc.getElementsByTagName("head");
	if (heads.length)
		heads[0].appendChild(link);
	else
		doc.documentElement.appendChild(link);
};

/**
 * 数字精度 用法 (159/123.0).toFixed(2); 保留两位有效数字 hhq
 */
Number.prototype.toFixed = function(d) {
	var s = this + "";
	if (!d)
		d = 0;
	if (s.indexOf(".") == -1)
		s += ".";
	s += new Array(d + 1).join("0");
	if (new RegExp("^(-|\\+)?(\\d+(\\.\\d{0," + (d + 1) + "})?)\\d*$").test(s)) {
		var s = "0" + RegExp.$2, pm = RegExp.$1, a = RegExp.$3.length, b = true;
		if (a == d + 2) {
			a = s.match(/\d/g);
			if (parseInt(a[a.length - 1]) > 4) {
				for ( var i = a.length - 2; i >= 0; i--) {
					a[i] = parseInt(a[i]) + 1;
					if (a[i] == 10) {
						a[i] = 0;
						b = i != 1;
					} else
						break;
				}
			}
			s = a.join("").replace(new RegExp("(\\d+)(\\d{" + d + "})\\d$"), "$1.$2");
		}
		if (b)
			s = s.substr(1);
		return (pm + s).replace(/\.$/, "");
	}
	return this + "";
};
/**
 * 格式化文件大小的格式（将B 转换为KB,MB,GB） hhq
 */
biz.formatFileSize = function(fileSize) {

	var unit = 1024.0;

	if (fileSize < unit) {
		return fileSize + "B";
	} else if (fileSize < unit * unit) {
		return (fileSize / unit).toFixed(2) + "KB";
	} else if (fileSize < unit * unit * unit) {
		return (fileSize / (unit * unit)).toFixed(2) + "MB";
	} else if (fileSize < unit * unit * unit * unit) {
		return (fileSize / (unit * unit * unit)).toFixed(2) + "GB";
	}
}

/**
 * 更换主题
 */
biz.changeTheme = function(themeName) {
	var $theme = $('#bizTheme');
	var url = $theme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$theme.attr('href', href);
	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#bizTheme').attr('href', href);
		}
	}
	$.cookie('bizTheme', themeName, {expires : 7});
};
/**
 * form表单元素的值序列化对象
 */
biz.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};
/**
 * object对象转成json形式字符串
 */
biz.object2Json = function(obj) {
	return $.toJSON(obj);
}
/**
 * 更新url+arg
 */
// refreshUrlLink
biz.refreshUrlLink = function(url, arg) {
	var index = url.indexOf('?');
	var length = url.length;
	if (index < 0) {
		url = url + '?' + arg;
	} else if (index == length - 1) {
		url = url + arg;
	} else {
		url = url.substring(0, index + 1) + arg + '&' + url.substring(index + 1, length);
	}
	return url;
};
/**
 * dotoHtmlById
 */
biz.dotoHtmlById = function(id, tos) {
	var returnHtml = $('#' + id).html();
	if (null != returnHtml) {
		var maxArgsNumb = tos.length;
		for ( var i = 0; i < maxArgsNumb; i++) {
			returnHtml = returnHtml.replace(new RegExp("'#arg" + i + "'", "gm"), typeof (tos[i]) == 'undefined' ? 'this' : tos[i]);
		}
		return returnHtml;
	} else {
		return "";
	}
}

// 将字符串转换为数组
biz.getArray = function(str) {
	var list = str.split(",");
	var l = [];
	if (list.length == 0)
		return l;
	$.each(list, function(i, n) {
		l.push(n);
	});
	return l;
}
/**
 * 对easyui dialog 封装
 */
biz.dialog = function(options) {
	var opts = $.extend({modal : true, onClose : function() {
		$(this).dialog('destroy');
	}}, options);
	return $('<div/>').dialog(opts);
};
/**
 * 左到右
 */
biz.left2right = function(but) {
	var $layout = $($(but).parents('.easyui-layout')[0]);
	var left = $layout.find('select')[0];
	var rigth = $layout.find('select')[1];
	if ($.browser.msie) {// IE 单独处理
		for ( var i = left.options.length - 1; i >= 0; i--) {
			var option = left.options[i];
			if (option.selected) {
				var opt = new Option(option.text, option.value);
				rigth.options.add(opt);
				left.remove(i);
			}
		}
	} else {
		$(left.options).each(function(i, n) {
			if (n.selected) {
				n.selected = false;
				rigth.options.add(n);
			}
		});
	}
};
/**
 * 右到左
 */
biz.right2left = function(but) {
	var $layout = $($(but).parents('.easyui-layout')[0]);
	var left = $layout.find('select')[0];
	var rigth = $layout.find('select')[1];
	if ($.browser.msie) {// IE 单独处理
		for ( var i = rigth.options.length - 1; i >= 0; i--) {
			var option = rigth.options[i];
			if (option.selected) {
				var opt = new Option(option.text, option.value);
				left.options.add(opt);
				rigth.remove(i);
			}
		}
	} else {
		$(rigth.options).each(function(i, n) {
			if (n.selected) {
				n.selected = false;
				left.options.add(n);
			}
		});
	}
}
/**
 * 左全到右
 */
biz.leftall2right = function(but) {
	var $layout = $($(but).parents('.easyui-layout')[0]);
	var left = $layout.find('select')[0];
	var rigth = $layout.find('select')[1];
	if ($.browser.msie) {// IE 单独处理
		for ( var i = 0; i < left.options.length; i++) {
			var option = left.options[i];
			var opt = new Option(option.text, option.value);
			rigth.options.add(opt);
		}
		$(left).empty();
	} else {
		$(left.options).each(function(i, n) {
			rigth.options.add(n);
		});
	}
};
/**
 * 右全到左
 */
biz.rightall2left = function(but) {
	var $layout = $($(but).parents('.easyui-layout')[0]);
	var left = $layout.find('select')[0];
	var rigth = $layout.find('select')[1];
	if ($.browser.msie) {// IE 单独处理
		for ( var i = 0; i < rigth.options.length; i++) {
			var option = rigth.options[i];
			var opt = new Option(option.text, option.value);
			left.options.add(opt);
		}
		$(rigth).empty();
	} else {
		$(rigth.options).each(function(i, n) {
			left.options.add(n);
		});
	}
};
/**
 * 选择框数据采集
 */
biz.findSelectMultipleValue = function(options) {
	var returnArr = [], ids = [], texts = [];
	if ($.browser.msie) {// IE 单独处理
		for ( var i = 0; i < options.length; i++) {
			ids.push(options[i].value);
			texts.push(options[i].text);
		}
	} else {
		$(options).each(function(i, n) {
			ids.push($(n).val());
			texts.push($(n).html());
		});
	}
	returnArr.push(ids);
	returnArr.push(texts);
	return returnArr;
}

/**
 * 系统提示
 */
biz.sysTip = function() {
	return $.i18n.prop('biz.common.message.systemtip');
}
/**
 * 公司组织根节点值
 */
biz.companyRootId = -1;
/**
 * 时间对象的格式化
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {"M+" : this.getMonth() + 1, // month
	"d+" : this.getDate(), // day
	"h+" : this.getHours(), // hour
	"m+" : this.getMinutes(), // minute
	"s+" : this.getSeconds(), // second
	"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
	"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};
// ======================================== easyui update
// ========================================
//$.parser.auto = false;
/**
 * 扩展tree getCheckedExt 获得选中节点+实心节点 getSolidExt 获取实心节点
 */
$.extend($.fn.tree.methods, {getCheckedExt : function(jq) {
	var checked = [];
	var checkbox2 = $(jq).find("span.tree-checkbox1,span.tree-checkbox2").parent();
	$.each(checkbox2, function() {
		var thisData = {target : this, "checked" : true};
		var node = $.extend({}, $.data(this, "tree-node"), thisData);
		checked.push(node);
	});
	return checked;
}, getSolidExt : function(jq) {
	var checked = [];
	var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
	$.each(checkbox2, function() {
		var node = $.extend({}, $.data(this, "tree-node"), {target : this});
		checked.push(node);
	});
	return checked;
}, setValues : function(jq, v) {
	var options = $.data(jq[0], "tree").options;
	if (!options.checkbox) {
		return;
	}
	var ck = biz.getArray(v);
	for ( var i = 0; i < ck.length; i++) {
		var _curNode = $(jq).tree('find', ck[i]);
		if (_curNode) {
			$(jq).tree('check', _curNode.target);
		}
	}
}, getChildrenIds : function(jq, node) {
	// 获取当前节点下所有子节点的id值(包括当前节点的值),以逗号隔开返回
	var _nodes = $(jq).tree('getChildren', node.target);
	var _sNode = [];
	// biz.companyRootId
	_sNode.push(node.id);
	if (_nodes && _nodes.length > 0) {
		$.each(_nodes, function(i, n) {
			_sNode.push(n.id);
		});
	}
	return _sNode.join(',');
}, getTreeIds : function(jq) {
	var roots = $(jq).tree('getRoots');
	if (roots.length == 0)
		return '';
	var _sNode = [];
	$.each(roots, function(i, n) {
		_sNode.push(n.id);
		var _nodes = $(jq).tree('getChildren', n.target);
		if (_nodes && _nodes.length > 0) {
			$.each(_nodes, function(i, n) {
				_sNode.push(n.id);
			});
		}
	});
	return _sNode.join(',');
}, getParentIds : function(jq, node) {
	// 获取当前节点的父节点id值
	var pNode = [];
	if (!node)
		return '';
	var $curNode = node;
	pNode.push($curNode.id);
	while ($(jq).tree('getParent', $curNode.target)) {
		$curNode = $(jq).tree('getParent', $curNode.target);
		pNode.push($curNode.id);
	}
	return pNode.join(",");
}});
/**
 * 扩展datagrid，添加动态增加或删除Editor的方法
 */
$.extend($.fn.datagrid.methods, {addEditor : function(jq, param) {
	if (param instanceof Array) {
		$.each(param, function(index, item) {
			var e = $(jq).datagrid('getColumnOption', item.field);
			e.editor = item.editor;
		});
	} else {
		var e = $(jq).datagrid('getColumnOption', param.field);
		e.editor = param.editor;
	}
}, removeEditor : function(jq, param) {
	if (param instanceof Array) {
		$.each(param, function(index, item) {
			var e = $(jq).datagrid('getColumnOption', item);
			e.editor = {};
		});
	} else {
		var e = $(jq).datagrid('getColumnOption', param);
		e.editor = {};
	}
}, hideDisplayMsg : function(jq) {
	var pager = $(jq).datagrid('getPager');
	$(pager).pagination({displayMsg : ''});
}});
/**
 * 扩展datagrid editor 增加带复选框的下拉树 combocheckboxtree 增加日期时间组件 datetimebox
 * 增加多选combobox组件 multiplecombobox
 */
$.extend($.fn.datagrid.defaults.editors, {combocheckboxtree : {init : function(container, options) {
	var editor = $('<input />').appendTo(container);
	options.multiple = true;
	editor.combotree(options);
	return editor;
}, destroy : function(target) {
	$(target).combotree('destroy');
}, getValue : function(target) {
	return $(target).combotree('getValues').join(',');
}, setValue : function(target, value) {
	$(target).combotree('setValues', sy.getList(value));
}, resize : function(target, width) {
	$(target).combotree('resize', width);
}}, datetimebox : {init : function(container, options) {
	var editor = $('<input />').appendTo(container);
	editor.datetimebox(options);
	return editor;
}, destroy : function(target) {
	$(target).datetimebox('destroy');
}, getValue : function(target) {
	return $(target).datetimebox('getValue');
}, setValue : function(target, value) {
	$(target).datetimebox('setValue', value);
}, resize : function(target, width) {
	$(target).datetimebox('resize', width);
}}, multiplecombobox : {init : function(container, options) {
	var editor = $('<input />').appendTo(container);
	options.multiple = true;
	editor.combobox(options);
	return editor;
}, destroy : function(target) {
	$(target).combobox('destroy');
}, getValue : function(target) {
	return $(target).combobox('getValues').join(',');
}, setValue : function(target, value) {
	$(target).combobox('setValues', sy.getList(value));
}, resize : function(target, width) {
	$(target).combobox('resize', width);
}}});
/**
 * 扩展 datagrid/treegrid 增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $('<div style="width:150px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for ( var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid('getColumnOption', fields[i]);
			if (!fildOption.hidden) {
				$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			} else {
				$('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({onClick : function(item) {
			var field = $(item.target).attr('field');
			if (item.iconCls == 'icon-ok') {
				grid.datagrid('hideColumn', field);
				$(this).menu('setIcon', {target : item.target, iconCls : 'icon-empty'});
			} else {
				grid.datagrid('showColumn', field);
				$(this).menu('setIcon', {target : item.target, iconCls : 'icon-ok'});
			}
		}});
	}
	headerContextMenu.menu('show', {left : e.pageX, top : e.pageY});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
/**
 * 扩展 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function(XMLHttpRequest) {
	$.messager.progress('close');
	// $.messager.alert('错误', XMLHttpRequest.responseText);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;
/**
 * 防止panel/window/dialog组件超出浏览器边界
 */
/*
 * var easyuiPanelOnMove = function(left , top){ var l = left; var t = top; if
 * (l < 1){ l = 1; } if (t < 1){ t = 1; } var width =
 * parseInt($(this).parent().css('width')) + 14; var height =
 * parseInt($(this).parent().css('height')) + 14; var right = l + width; var
 * buttom = t + height; var browserWidth = $(window).width(); var browserHeight =
 * $(window).height(); if (right > browserWidth){ l = browserWidth - width; } if
 * (buttom > browserHeight){ t = browserHeight - height; }
 * $(this).parent().css({ 修正面板位置 left : l , top : t }); };
 * $.fn.dialog.defaults.onMove = easyuiPanelOnMove; $.fn.window.defaults.onMove =
 * easyuiPanelOnMove; $.fn.panel.defaults.onMove = easyuiPanelOnMove;
 */
/**
 * 扩展easyui的validator插件rules，支持更多类型验证
 */
$(function (){
	$.extend(
			$.fn.validatebox.defaults.rules ,
			{
				minLength : { // 判断最小长度
					validator : function(value , param){
						return value.length >= param[0];
					} ,
					message : '最少输入{0}个字符'
				} ,
				length : { // 长度
					validator : function(value , param){
						var len = $.trim(value).length;
						return len >= param[0] && len <= param[1];
					} ,
					message : "输入内容长度必须介于{0}和{1}之间"
				} ,
				not_email : {
					validator : function(value){
						return ! /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
								.test(value);
					} ,
					message : "不能设置为邮箱地址."
				} ,
				phone : {// 验证电话号码
					validator : function(value){
						return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
					} ,
					message : '格式不正确,请使用下面格式:020-88888888'
				} ,
				mobile : {// 验证手机号码
					validator : function(value){
						return /^(13|15|18)\d{9}$/i.test(value);
					} ,
					message : '手机号码格式不正确'
				} ,
				phoneAndMobile : {// 电话号码或手机号码
					validator : function(value){
						return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value)
								|| /^(13|15|18)\d{9}$/i.test(value);
					} ,
					message : '电话号码或手机号码格式不正确'
				} ,
				idcard : {// 验证身份证
					validator : function(value){
						return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value) || /^\d{18}(\d{2}[A-Za-z0-9])?$/i.test(value);
					} ,
					message : '身份证号码格式不正确'
				} ,
				bankId : {// 验证银行卡号
					validator : function(value){
						return /^[0-9]{16,19}$/i.test(value);
					} ,
					message : '银行卡号格式不正确'
				} ,
				license : {// 验证营业执照
					validator : function(value){
						return /^[0-9]{15}$/i.test(value);
					} ,
					message : '格式不正确,请填15位数字'
				} , 
				organizationCode : {// 验证组织机构代码
					validator : function(value){
						return /^[a-zA-Z\d]{8}\-[a-zA-Z\d]$/i.test(value);
					} ,
					message : '组织机构代码格式不正确，如：B1111111-2'
				} ,
				intOrFloat : {// 验证整数或小数
					validator : function(value){
						return /^\d+(\.\d+)?$/i.test(value);
					} ,
					message : '请输入数字，并确保格式正确'
				} ,
				currency : {// 验证货币
					validator : function(value){
						return /^\d+(\.\d+)?$/i.test(value);
					} ,
					message : '货币格式不正确'
				} ,
				qq : {// 验证QQ,从10000开始
					validator : function(value){
						return /^[1-9]\d{4,9}$/i.test(value);
					} ,
					message : 'QQ号码格式不正确'
				} ,
				integer : {// 验证整数
					validator : function(value){
						return /^[+]?[0-9]+\d*$/i.test(value);
					} ,
					message : '请输入整数'
				} ,
				notAllInteger : {// 验证整数
					validator : function(value){
						return ! /^[+]?[1-9]+\d*$/i.test(value);
					} ,
					message : '不能全为数字.'
				} ,
				chinese : {// 验证中文
					validator : function(value){
						return /^[\u0391-\uFFE5]+$/i.test(value);
					} ,
					message : '请输入中文'
				} ,
				chineseAndLength : {// 验证中文及长度
					validator : function(value){
						var len = $.trim(value).length;
						if (len >= param[0] && len <= param[1]){
							return /^[\u0391-\uFFE5]+$/i.test(value);
						}
					} ,
					message : '请输入中文'
				} ,
				english : {// 验证英语
					validator : function(value){
						return /^[A-Za-z]+$/i.test(value);
					} ,
					message : '请输入英文'
				} ,
				englishAndLength : {// 验证英语及长度
					validator : function(value , param){
						var len = $.trim(value).length;
						if (len >= param[0] && len <= param[1]){
							return /^[A-Za-z]+$/i.test(value);
						}
					} ,
					message : '请输入英文'
				} ,
				englishUpperCase : {// 验证英语大写
					validator : function(value){
						return /^[A-Z]+$/.test(value);
					} ,
					message : '请输入大写英文'
				} ,
				unnormal : {// 验证是否包含空格和非法字符
					validator : function(value){
						return /.+/i.test(value);
					} ,
					message : '输入值不能为空和包含其他非法字符'
				} ,
				username : {// 验证用户名
					validator : function(value){
						return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
					} ,
					message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
				} ,
				faxno : {// 验证传真
					validator : function(value){
						return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
					} ,
					message : '传真号码不正确'
				} ,
				zip : {// 验证邮政编码
					validator : function(value){
						return /^[1-9]\d{5}$/i.test(value);
					} ,
					message : '邮政编码格式不正确'
				} ,
				ip : {// 验证IP地址
					validator : function(value){
						return /d+.d+.d+.d+/i.test(value);
					} ,
					message : 'IP地址格式不正确'
				} ,
				name : {// 验证姓名，可以是中文或英文
					validator : function(value){
						return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
					} ,
					message : '请输入姓名'
				} ,
				engOrChinese : {// 可以是中文或英文
					validator : function(value){
						return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
					} ,
					message : '请输入中文'
				} ,
				engOrChineseAndLength : {// 可以是中文或英文
					validator : function(value){
						var len = $.trim(value).length;
						if (len >= param[0] && len <= param[1]){
							return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
						}
					} ,
					message : '请输入中文或英文'
				} ,
				carNo : {// 车牌号码
					validator : function(value){
						return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);
					} ,
					message : '车牌号码无效（例：粤B12350）'
				} ,
				userName : {// 用户名
					validator : function(value){
						return /^[a-zA-Z0-9_-]{8,16}$/.test(value);
					} ,
					message : '8-16位字符，支持字母、数字及“_”“-”组合！'
				} , 
				password : {// 用户名
					validator : function(value){
						return /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{8,16}$/.test(value);
					} ,
					message : '输入的密码为大小字母及@!#$^&.~*字符组合,长度8~16位'
				} ,
				carenergin : {// 发动机号码
					validator : function(value){
						return /^[a-zA-Z0-9]{16}$/.test(value);
					} ,
					message : '发动机型号无效(例：FG6H012345654584)'
				} ,
				same : {// 一致信息
					validator : function(value , param){
						if ($("#" + param[0]).val() != "" && value != ""){
							return $("#" + param[0]).val() == value;
						} else{
							return true;
						}
					} ,
					message : '两次输入的密码不一致!'
				} ,
				special:{  //特殊字符
					validator : function(value){
						var pattern = new RegExp("[`~!@#$^&*()={}':;',\\[\\].<>/?~！@#￥……&*（）—{}【】‘；：”“'。，、？]");
						return ! pattern.test(value);
					} ,
					message : "不能设置为特殊字符."
				 
				},
				beforeFill : {
					validator : function(value , param){
						var name = $("#" + param[0]).val();
						if ( (name != null && name != "") && (value == null || value == "")){
							return false;
						}
						return true;
					} ,
					message : '不能为空'
				},
				isUrl:{
					validator : function(value){
						return /(http[s]?|ftp):\/\/[^\/\.]+?\..+\w$/i.test(value);
					} ,
					message : '请输入正确的网址,以“http(s)://”开头'
					
				}
			});
	
	/**
	 * 扩展easyui validatebox的两个方法.移除验证和还原验证
	 */
	$.extend($.fn.validatebox.methods, {remove : function(jq, newposition) {
		return jq.each(function() {
			$(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');
			// $(this).validatebox('hideTip', this);
		});
	}, reduce : function(jq, newposition) {
		return jq.each(function() {
			var opt = $(this).data().validatebox.options;
			$(this).addClass("validatebox-text").validatebox(opt);
			// $(this).validatebox('validateTip', this);
		});
	}, validateTip : function(jq) {
		jq = jq[0];
		var opts = $.data(jq, "validatebox").options;
		var tip = $.data(jq, "validatebox").tip;
		var box = $(jq);
		var value = box.val();
		function setTipMessage(msg) {
			$.data(jq, "validatebox").message = msg;
		};
		var disabled = box.attr("disabled");
		if (disabled == true || disabled == "true") {
			return true;
		}
		if (opts.required) {
			if (value == "") {
				box.addClass("validatebox-invalid");
				setTipMessage(opts.missingMessage);
				$(jq).validatebox('showTip', jq);
				return false;
			}
		}
		if (opts.validType) {
			var result = /([a-zA-Z_]+)(.*)/.exec(opts.validType);
			var rule = opts.rules[result[1]];
			if (value && rule) {
				var param = eval(result[2]);
				if (!rule["validator"](value, param)) {
					box.addClass("validatebox-invalid");
					var message = rule["message"];
					if (param) {
						for ( var i = 0; i < param.length; i++) {
							message = message.replace(new RegExp("\\{" + i + "\\}", "g"), param[i]);
						}
					}
					setTipMessage(opts.invalidMessage || message);
					$(jq).validatebox('showTip', jq);
					return false;
				}
			}
		}
		box.removeClass("validatebox-invalid");
		$(jq).validatebox('hideTip', jq);
		return true;
	}, showTip : function(jq) {
		jq = jq[0];
		var box = $(jq);
		var msg = $.data(jq, "validatebox").message
		var tip = $.data(jq, "validatebox").tip;
		if (!tip) {
			tip = $("<div class=\"validatebox-tip\">" + "<span class=\"validatebox-tip-content\">" + "</span>" + "<span class=\"validatebox-tip-pointer\">" + "</span>" + "</div>").appendTo("body");
			$.data(jq, "validatebox").tip = tip;
		}
		tip.find(".validatebox-tip-content").html(msg);
		tip.css({display : "block", left : box.offset().left + box.outerWidth(), top : box.offset().top});
	}, hideTip : function(jq) {
		jq = jq[0];
		var tip = $.data(jq, "validatebox").tip;
		if (tip) {
			tip.remove;
			$.data(jq, "validatebox").tip = null;
		}
	}});
});


/**
 * 扩展easyui datagrid 连续列合并扩展
 */
$.extend($.fn.datagrid.methods, {autoMergeCells : function(jq, fields) {
	return jq.each(function() {
		var target = $(this);
		if (!fields) {
			fields = target.datagrid("getColumnFields");
		}
		var rows = target.datagrid("getRows");
		var i = 0, j = 0, temp = {};
		for (i; i < rows.length; i++) {
			var row = rows[i];
			j = 0;
			for (j; j < fields.length; j++) {
				var field = fields[j];
				var tf = temp[field];
				if (!tf) {
					tf = temp[field] = {};
					tf[row[field]] = [i];
				} else {
					var tfv = tf[row[field]];
					if (tfv) {
						tfv.push(i);
					} else {
						tfv = tf[row[field]] = [i];
					}
				}
			}
		}
		$.each(temp, function(field, colunm) {
			$.each(colunm, function() {
				var group = this;
				if (group.length > 1) {
					var before, after, megerIndex = group[0];
					for ( var i = 0; i < group.length; i++) {
						before = group[i];
						after = group[i + 1];
						if (after && (after - before) == 1) {
							continue;
						}
						var rowspan = before - megerIndex + 1;
						if (rowspan > 1) {
							target.datagrid('mergeCells', {index : megerIndex, field : field, rowspan : rowspan});
						}
						if (after && (after - before) != 1) {
							megerIndex = after;
						}
					}
				}
			});
		});
	});
}, clearData : function(jq, fields) {
	$(jq).datagrid('loadData', {total : 0, rows : []});
}});

/*
 * MAP对象，实现MAP功能 接口： size() 获取MAP元素个数 isEmpty() 判断MAP是否为空 clear() 删除MAP所有元素
 * put(key, value) 向MAP中增加元素（key, value) remove(key)
 * 删除指定KEY的元素，成功返回True，失败返回False get(key) 获取指定KEY的元素值VALUE，失败返回NULL
 * element(index) 获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
 * containsKey(key) 判断MAP中是否含有指定KEY的元素 containsValue(value) 判断MAP中是否含有指定VALUE的元素
 * values() 获取MAP中所有VALUE的数组（ARRAY） keys() 获取MAP中所有KEY的数组（ARRAY） 例子： var map =
 * new Map(); map.put("key", "value"); var val = map.get("key") ……
 */
function Map() {
	this.elements = new Array();

	// 获取MAP元素个数
	this.size = function() {
		return this.elements.length;
	};

	// 判断MAP是否为空
	this.isEmpty = function() {
		return (this.elements.length < 1);
	};

	// 删除MAP所有元素
	this.clear = function() {
		this.elements = new Array();
	};

	// 向MAP中增加元素（key, value)
	this.put = function(_key, _value) {
		this.elements.push({key : _key, value : _value});
	};

	// 删除指定KEY的元素，成功返回True，失败返回False
	this.removeByKey = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	// 删除指定VALUE的元素，成功返回True，失败返回False
	this.removeByValue = function(_value) {// removeByValueAndKey
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	// 删除指定VALUE的元素，成功返回True，失败返回False
	this.removeByValueAndKey = function(_key, _value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value && this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	// 获取指定KEY的元素值VALUE，失败返回NULL
	this.get = function(_key) {
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					return this.elements[i].value;
				}
			}
		} catch (e) {
			return false;
		}
		return false;
	};

	// 获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	this.element = function(_index) {
		if (_index < 0 || _index >= this.elements.length) {
			return null;
		}
		return this.elements[_index];
	};

	// 判断MAP中是否含有指定KEY的元素
	this.containsKey = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	// 判断MAP中是否含有指定VALUE的元素
	this.containsValue = function(_value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	// 判断MAP中是否含有指定VALUE的元素
	this.containsObj = function(_key, _value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value && this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	// 获取MAP中所有VALUE的数组（ARRAY）
	this.values = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].value);
		}
		return arr;
	};

	// 获取MAP中所有VALUE的数组（ARRAY）
	this.valuesByKey = function(_key) {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			if (this.elements[i].key == _key) {
				arr.push(this.elements[i].value);
			}
		}
		return arr;
	};

	// 获取MAP中所有KEY的数组（ARRAY）
	this.keys = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].key);
		}
		return arr;
	};

	// 获取key通过value
	this.keysByValue = function(_value) {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			if (_value == this.elements[i].value) {
				arr.push(this.elements[i].key);
			}
		}
		return arr;
	};

	// 获取MAP中所有KEY的数组（ARRAY）
	this.keysRemoveDuplicate = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			var flag = true;
			for ( var j = 0; j < arr.length; j++) {
				if (arr[j] == this.elements[i].key) {
					flag = false;
					break;
				}
			}
			if (flag) {
				arr.push(this.elements[i].key);
			}
		}
		return arr;
	};
}



//查找地址，pid:父级地址， deepId:地区层级，obj:下拉框的id
function findArea(pid,deepId,obj){
	$.getJSON(biz.rootPath() + '/areaController/find?areaParentId='+pid+'&areaDeep='+deepId, function(json) {
		
		var data="";
		data+='[{ "text": "--请选择--", "id": "" ,"selected":true  }';
		$.each(json.resultObject.content, function(i, n) {
			data+=',{ "text": "'+n.areaName+'", "id":'+n.areaId+'}';
		});
		data+="]";
		$("#"+obj).combobox("loadData", $.parseJSON(data));
	});
	
}


/**
 * 方法描述：easyui消息提示 作者：李明 创建时间：2015-2-12
 * 
 * @param $obj
 * @param msg
 */
function easyuiMsg(_title, _msg) {
	$.messager.show({ title : _title , msg : _msg ? _msg : '' });
}
/**
 * 上传图片
 * file:type=file的input的id
 * imgPath: 上传成功后返回的图片路径，保存到imgPath的input中
 * preImg:预览框的id
 * 2015.03.30
 */
function submitForm(file,imgPath,preImg) {
	var filev = document.getElementById(file);
	var patn = /\.jpg$|\.png$|\.jpeg$|\.gif$|\.JPG$|\.PNG$|\.JPEG$|\.GIF$/;
	if (filev.value != null && patn.test(filev.value)) {
		parent.$.messager.progress({
			title : '提示',
			text : '正在上传....'
		});
		$.ajaxFileUpload({
					url : biz.rootPath()+'/image/uploadImg',
					secureuri : false,
					fileElementId : file,
					dataType : 'json',
					success : function(data,status) {
						parent.$.messager.progress('close');
						if(data.retCode=="000000"){
							easyuiMsg('提示', '上传成功！');
							
							var img=$('#'+imgPath);
							var result = data.retMsg.split(";"); //包含id与路径
							img.val(result[1]);
						}else if (data.retCode=="000003"){
							parent.$.messager.alert('提示', '上传失败！'+data.retMsg, 'error');
							var pre_img=document.getElementById(preImg);
							pre_img.src=biz.rootPath()+"/resources/images/pre_default.png";
						}else{
							parent.$.messager.alert('提示', '上传失败！', 'error');
						}
						
					},
					error : function(data, status, e) {
						
						parent.$.messager.progress('close');
					}
				});
	} else {
		parent.$.messager.alert('提示', '选择图片上传!', 'error');
	}
}

/**
 * 获得select2需要的数据
 * 
 * @param obj
 *            显示分类的下拉框的id
 * @param url
 *            链接
 * @param isSelect
 *            是否需要“--请选择--”这个提示，其值有：true,false
 */
function getSelect2Data(url, obj, isSelect) {
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json, function(i, n) {
			data += '{ text: "' + n.catName + '", id:' + n.catId + '},';
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({
			data : eval(data)
		});
	});
}
