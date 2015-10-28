var patterns = {
	mobile : /^1[3|4|5|7|8][0-9]{9}$/ ,
	userName : /[a-zA-Z][a-zA-Z0-9_-]{8,16}$/ ,
	password : /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{8,16}$/ ,
	email : /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
};
/**
 * 方法描述：获取短信验证码
 * 作者：李明
 * 创建时间：2015-2-12
 * @param $obj
 */
function getSmsCode(obj){
	var $obj = $("#" + obj);
	if ($obj && patterns.mobile.test($obj.val())){
		$.ajax({
			type : 'POST' ,
			url : _path + '/oauth/sendSmsCode' ,
			data : 'mobile=' + $obj.val() + '&_rand=' + Math.random()
		}).done(function(data){
			if (data != '000000'){
				renderMsg($obj , data.head.retMsg);
			}
		});
	} else{
		renderMsg($obj , '请输入手机号码！');
	}
}
/**
 * 方法描述：获取有效验证码
 * 作者：李明
 * 创建时间：2015-3-17
 * @param obj
 */
function getEmailCode(obj){
	var $obj = $("#" + obj);
	if ($obj && $obj.val()){
		if (patterns.email.test($obj.val())){
			$.ajax({
				type : 'POST' ,
				url : _path + '/oauth/sendEmailCode' ,
				data : 'email=' + $obj.val() + '&_rand=' + Math.random()
			}).done(function(data){
				if (data.retCode != '000000'){
					renderMsg($obj , data.retMsg);
				}
			});
		} else{
			renderMsg($obj , '请输入邮箱格式不正确！');
		}
	} else{
		renderMsg($obj , '请输入邮箱！');
	}
}
/**
 * 方法描述：刷新图形验证码
 * 作者：李明
 * 创建时间：2015-2-12
 * @param obj
 */
function refresh(obj){
	obj.src = _path + '/verifyImage?width=60&height=30&_rand=' + Math.random();
}
var layer_utils={
	layerindex:"",
	alert:function(message){
		layerindex=layer.alert(message,{closeBtn:false,title:false,icon:2});
	},
	confirm:function(message,confirm,cancel){
		layerindex=layer.confirm(message, {
		    btn: ['确定','取消'], //按钮
		    shade:[0.3, '#393D49'], //不显示遮罩
		    title:false,
		    closeBtn:false,
		    title:false,
		    move:false,
		    icon:3,
		    yes:function (){
		    	if(confirm)confirm.call();
		    },
		    cancel:function (){
		    	if(cancel)cancel.call();
		    }
		});
	}
};

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
			layer.tips("车型信息未保存,<a style='color:#ddd;font-size:14px;font-weight:bloder' href='"+yoyo.portalUrl+"/register/login'>登录</a>或<a style='color:#ddd;font-size:14px;font-weight:bloder' href='"+yoyo.portalUrl+"/register/signup'>注册</a>后将会自动保存", '.cart_txt', {
				tips:3,
				time:false,
				closeBtn :1,
				zIndex:998,				
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
		/**
		if(my_car_utils.readCookie(my_car_utils.is_login)){
			commonAjax(yoyo.memUrl+"/membercar/findMemberDefaultCar",null,function (data){		
				var carinfo=[],car=data.content;
				if(car){
					carinfo.push(car.carBrandId);
					carinfo.push(car.carDeptId);
					carinfo.push(car.carId);
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
		**/
		my_car_utils.readMyTitleCar();
		/**
		}
		**/
	},
	readMyTitleCar:function (){
		var myCarCookie=my_car_utils.readCookie(my_car_utils.my_car_cook);
		if(myCarCookie){
			if(!my_car_utils.readCookie(my_car_utils.is_login)){
				my_car_utils.addCarFailure();
			}
			
			var myCarValue=myCarCookie.split("|");
			$(".cart_txt").find(".openmt").html(myCarValue[3]+"&nbsp;&nbsp;"+myCarValue[4]).attr("title",myCarValue[3]+"  "+myCarValue[4]);
			$(".cart_txt").find(".my_car_list").text("我的车型").attr("href",yoyo.memUrl+"/membercar/memberCarList");
		}else{
			$(".header_other_box").show();
			/**
			if($('#addMyCar').length>0){
				layer.tips("请选择您的爱车,我们将为您提供个性化的汽车终生服务，让您省时、省力、省钱、省心、为您提升品质汽车生活", '#addMyCar', {
					tips:3,
					time:false,
					zIndex:99,
					closeBtn :1,
					area: ['360px', '30px'],
					success: function(layero, index){						
						var left=$("#addMyCar").offset().left-($(layero).width()/2-$("#addMyCar").width()/2);
				        $(layero).css({left:left});
				        $(layero).find("i").css({left:$(layero).width()/2-4});
				    }
				});
			}
			**/
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
	my_car_utils.readMyCar();
	$(".openmt").click(function (){
		var opts={
			layerObj:$("#myCar"),
			success:choose_car.chooseFinish=function(){
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
			},
			end:choose_car.destory()
		};	
		my_car_utils.showSelectCarLayer(opts);
	});
	choose_car.init();
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
			$("#choosecar1_div_choosecar").on("click",".choose li",function (){
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
					choose_car.chooseYear(param.id,param.value);
				  break;
				case 3:
					choose_car.layerStep(2);
					$(".layerStep").addClass("layerStep3").find("li:eq(3)").addClass("curStep").siblings().removeClass("curStep");
					choose_car.chooseCar(param);				
				  break;
				case 4:
					$("#car_name_choose_input").val(param.value);
					$("#car_choose_input").val(param.id);
					choose_car.chooseFinish();
				  break;
				}
			});
			$("#choosecar1_div_choosecar").on("click",".brandChoose li",function (){
				choose_car.chooseBrand($(this).attr("data-pinyin"));
				$(this).addClass("chooseCur").siblings().removeClass("chooseCur");
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
					choose_car.chooseYear(carDeptId);
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
		},chooseBrand:function(pinyin){
			$.post(yoyo.portalUrl+"/comboBox/findCarBrand",{identifier:yoyo.car,pinyin:pinyin},function(data){
					var html=[];
					var let = ["A","B","C","D","F","H","J","K","L","M","Q","R","S","T","W","X","Y","Z"];
					html.push('<div class="brandChoose">');
					html.push('<b>品牌首字母选择：</b>');
					html.push('<ul>');
					if(typeof(pinyin)=="undefined"||pinyin==""){
						html.push('<li class="hotb chooseCur" data-pinyin=""><a href="javascript:;">全部</a></li>');	
					}else{
						html.push('<li class="hotb" data-pinyin=""><a href="javascript:;">全部</a></li>');
					}
					$.each(let,function (x,item){
						if(pinyin==item){
							html.push('<li class="chooseCur" data-pinyin="'+item+'"><a href="javascript:;">'+item+'</a><span></span></li>');
						}else{
							html.push('<li data-pinyin="'+item+'"><a href="javascript:;">'+item+'</a><span></span></li>');
						}
					});
					html.push('</ul>');
					html.push('</div>');
					
					html.push('<div class="brandCompany choose">');
					html.push(choose_car.buildBrand(data));
					html.push('</div>');
					$("#choosecar1_div_choosecar").html(html.join(""));
			});
		},chooseCarDept:function(param){
			var html=[];
			$("#brand_choose_input").val(param.id);
			$("#brand_log_choose_input").val(param.logo);
			$.post(yoyo.portalUrl+"/comboBox/findCarDept",{brandId:param.id},function(data){
				html.push('<div class="carNature choose">');
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
		},chooseYear:function(deptId,carName){		
			$("#dept_choose_input").val(deptId);		
			$.post(yoyo.portalUrl+"/comboBox/findCarYear",{deptId:deptId},function (data){
				var html=[];
				html.push('<div class="carNature choose">');
				html.push('<ul>');
				$.each(data.content,function (x,item){
					html.push('<li  param=\'{\"id\":'+item.id+',\"value\":\"'+item.carYearValue+'\",\"chooseType\":3}\'>');
					html.push('<a title="'+item.id+'" href="javascript:;">'+item.carYearValue+'</a>');
					html.push('</li>');
				});
				html.push('</ul>');
				html.push('</div>');
				$("#choosecar1_div_choosecar").append(html.join(""));	
			});
		},chooseCar:function(param){
			$("#year_choose_input").val(param.value);
			$.post(yoyo.portalUrl+"/comboBox/findCar",{carYearId:param.id,deptId:$("#dept_choose_input").val()},function(data){
				var html=[];
				html.push('<div class="carNature choose">');
				html.push('<ul>');
				$.each(data.content,function (x,item){
					var carName=item.carName.substring(item.carName.lastIndexOf("款")+1,item.carName.length);
					html.push('<li param=\'{\"id\":'+item.carId+',\"value\":\"'+item.carName+'\",\"chooseType\":4}\'>');
					html.push('<a title="'+carName+'" href="javascript:;">'+carName+'</a>');
					html.push('</li>');
				});
				html.push('</ul>');
				html.push('</div>');
				$("#choosecar1_div_choosecar").append(html.join(""));
			});
		},chooseFinish:function(){
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
		},buildBrand:function(data){
			var html=[];
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
			return html.join("");
		}
	};

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
 * 方法描述：获取第三方登录窗口
 * 作者：李明
 * 创建时间：2015-2-12
 * @param thirdType
 */
function getAuthorizeURL(thirdType){
	var callback = "http://www.unvs.cn/oauth/qq_js/Fqqlogin.html";
	$.ajax({
		type : "POST" ,
		url : _path + "/oauth/getAuthorizeURL" ,
		data : "thirdType=" + thirdType + "&callback=" + callback + "&_rand=" + Math.random() ,
		success : function(data){
			if (data.head.retCode == '000000'){
				
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
		}
	});
}
/**
 * 方法描述：验证图片验证码
 * 作者：李明
 * 创建时间：2015-2-12
 * @param $obj
 */
function validateImgCode(obj , sucCallback , failCallback){

	var $obj = $('#' + obj);
	if ($obj && $obj.val()){
		commonAjax(_path + "/oauth/validateImgCode" , "imgCode=" + $obj.val() , sucCallback , function(data){
			renderMsg($obj , data.retMsg);
			failCallback(data);
		});
	} else{
		renderMsg($obj , '请输入验证码！');
		$obj.focus();
	}
}
/**
 * 方法描述：验证短信验证码
 * 作者：李明
 * 创建时间：2015-2-12
 * @param $obj
 */
function validateSmsCode(obj , sucCallback , failCallback){
	var $obj = $('#' + obj);
	if ($obj && $obj.val()){
		commonAjax(_path + "/oauth/validateSmsCode" , "smsCode=" + $obj.val() , sucCallback , function(data){
			renderMsg($obj , data.retMsg);
			failCallback(data);
		});
	} else{
		renderMsg($obj , '请输入短信验证码！');
		$obj.focus();
	}
}
/**
 * 方法描述：验证邮箱验证码
 * 作者：李明
 * 创建时间：2015-3-17
 * @param obj
 * @param sucCallback
 * @param failCallback
 */
function validateEmailCode(obj , sucCallback , failCallback){
	var $obj = $('#' + obj);
	if ($obj && $obj.val()){
		commonAjax(_path + "/oauth/validateEmailCode" , "emailCode=" + $obj.val() , sucCallback , function(data){
			renderMsg($obj , data.retMsg);
			failCallback(data);
		});
	} else{
		renderMsg($obj , '请输入邮箱验证码！');
		$obj.focus();
	}
}
/**
 * 方法描述：消息提示
 * 作者：李明
 * 创建时间：2015-2-12
 * @param $obj
 * @param msg
 */
function renderMsg($obj , msg){
	layer.tips(msg , $obj , {
		guide : 2 ,
		time : 3 ,
		style : ['background-color:#c00; color:#fff' , '#c00'] ,
		maxWidth : 240
	});
}
/**
 * 方法描述：通用表单提交
 * 作者：李明
 * 创建时间：2015-3-2
 * @param $obj
 * @param url
 * @param sucCallback
 * @param failCallback
 */
function formSubmit(obj , url , sucCallback , failCallback){
	var $obj = $('#' + obj);
	if ($obj){
		commonAjax(url , $obj.serializeArray() , sucCallback , failCallback);
	} else{
		renderMsg($obj , '未知表单数据！');
	}
}
/**
 * 方法描述：通用信息校验方法
 * 作者：李明
 * 创建时间：2015-3-13
 * @param url
 * @param data
 * @param sucCallback
 * @param failCallback
 */
function commonAjax(url , data , sucCallback , failCallback){
	$.ajax({
		type : 'POST' ,
		url : url ,
		data : data
	}).done(function(data){
		if (data.retCode != '000000'){
			if ($.isFunction(failCallback)){
				failCallback(data);
			}
		} else{
			if ($.isFunction(sucCallback)){
				sucCallback(data);
			}
		}
	});
}
/**
 * 方法描述：检查账户是否存在
 * 作者：李明
 * 创建时间：2015-3-16
 * @param obj
 */
function checkAccountExist(obj , data , sucCallback , failCallback){
	var $obj = $('#' + obj);
	if ($obj){
		commonAjax(_path + '/sign/checkAccountExist' , data , sucCallback , failCallback);
	} else{
		renderMsg($obj , '未知表单数据！');
	}
}
function testLoginNameType(val){
	if (patterns.email.test(val)){
		return '1';
	} else if (patterns.mobile.test(val)){
		return '2';
	} else if (patterns.userName.test(val)){
		return '3';
	} else{
		return '0';
	}
}

/**
 * init

$(function() {
	// i18n 国际化加载 cookie
	var language = $.cookie('clientLanguage');
	if (language == null) {
		language = 'zh_CN';
	}
	jQuery.i18n.properties({// 加载资浏览器语言对应的资源文件
		name : 'Messages', // 资源文件名称
		language : language,// 国际化语言
		path : biz.rootPath() + '/base/i18n/', // 资源文件路径
		mode : 'map', // 用Map的方式使用资源文件中的值
		callback : function() {// 加载成功后设置显示内容
		}
	});

	// console.info($.i18n.prop('test00'));
}); */
/**
 * 命名空间
 */
var biz = $.extend({} , biz);
/**
 * 获得全路径
 */
biz.rootBasePath = function(){
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0 , pos);
	var projectName = pathName.substring(0 , pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};
/**
 * 获取根路径
 */
biz.rootPath = function(){
	var pathName = window.document.location.pathname;
	return pathName.substring(0 , pathName.substr(1).indexOf('/') + 1);
};
/**
 * 字符串格式化
 */
biz.formatString = function(str){
	for ( var i = 0 ; i < arguments.length - 1 ; i ++ ){
		str = str.replace("{" + i + "}" , arguments[i + 1]);
	}
	return str;
};
/**
 * 字符串全格式化
 */
biz.formatStringAll = function(str){
	for ( var i = 0 ; i < arguments.length - 1 ; i ++ ){
		str = str.replace(new RegExp('\\{' + i + '\\}' , "gm") , arguments[i + 1]);
	}
	return str;
};

/**
 * 将秒转换为时间格式例如00:32:23 秒数不能超过一天，否则返回“” hhq
 */
biz.formatSeconds = function(seconds){
	function formatPart(num){
		if (num < 10){
			return "0" + num;
		} else{
			return num + "";
		}
	}

	if (seconds < 60){
		return "00:00:" + formatPart(seconds);
	} else if (seconds < 60 * 60){
		var minutes = parseInt(seconds / 60);
		var second = seconds % 60;
		return "00:" + formatPart(minutes) + ":" + formatPart(second);
	} else if (seconds < 24 * 60 * 60){
		var hours = parseInt(seconds / (60 * 60));
		var minutes = parseInt( (seconds - hours * 60 * 60) / 60);
		var second = (seconds - hours * 60 * 60) % 60;
		return formatPart(hours) + ":" + formatPart(minutes) + ":" + formatPart(second);
	} else{
		return "";
	}
}

// 引入css文件
biz.addCssByLink = function(url){
	var doc = document;
	var link = doc.createElement("link");
	link.setAttribute("rel" , "stylesheet");
	link.setAttribute("type" , "text/css");
	link.setAttribute("href" , url);

	var heads = doc.getElementsByTagName("head");
	if (heads.length)
		heads[0].appendChild(link);
	else
		doc.documentElement.appendChild(link);
};

/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
    // millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }

    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}
/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
	if(!num)return "0.00";
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}

Array.prototype.elremove = function(m){  
    if(isNaN(m)||m>this.length){return false;}  
    this.splice(m,1);  
};

function compareDate(begin, end) {
	// 将字符串转换为日期
	//var begin = new Date(begin.replace(/-/g, "/"));
	//var end = new Date(end.replace(/-/g, "/"));
	// js判断日期
	if ((begin - end) > 0) {
		return false;
	}
	return true;
}

/**
 * 数字精度 用法 (159/123.0).toFixed(2); 保留两位有效数字 hhq
 */
Number.prototype.toFixed = function(d){
	var s = this + "";
	if ( ! d)
		d = 0;
	if (s.indexOf(".") == - 1)
		s += ".";
	s += new Array(d + 1).join("0");
	if (new RegExp("^(-|\\+)?(\\d+(\\.\\d{0," + (d + 1) + "})?)\\d*$").test(s)){
		var s = "0" + RegExp.$2 , pm = RegExp.$1 , a = RegExp.$3.length , b = true;
		if (a == d + 2){
			a = s.match(/\d/g);
			if (parseInt(a[a.length - 1]) > 4){
				for ( var i = a.length - 2 ; i >= 0 ; i -- ){
					a[i] = parseInt(a[i]) + 1;
					if (a[i] == 10){
						a[i] = 0;
						b = i != 1;
					} else
						break;
				}
			}
			s = a.join("").replace(new RegExp("(\\d+)(\\d{" + d + "})\\d$") , "$1.$2");
		}
		if (b)
			s = s.substr(1);
		return (pm + s).replace(/\.$/ , "");
	}
	return this + "";
};
/**
 * 格式化文件大小的格式（将B 转换为KB,MB,GB） hhq
 */
biz.formatFileSize = function(fileSize){

	var unit = 1024.0;

	if (fileSize < unit){
		return fileSize + "B";
	} else if (fileSize < unit * unit){
		return (fileSize / unit).toFixed(2) + "KB";
	} else if (fileSize < unit * unit * unit){
		return (fileSize / (unit * unit)).toFixed(2) + "MB";
	} else if (fileSize < unit * unit * unit * unit){
		return (fileSize / (unit * unit * unit)).toFixed(2) + "GB";
	}
}

/**
 * 更换主题
 */
biz.changeTheme = function(themeName){
	var $theme = $('#bizTheme');
	var url = $theme.attr('href');
	var href = url.substring(0 , url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$theme.attr('href' , href);
	var $iframe = $('iframe');
	if ($iframe.length > 0){
		for ( var i = 0 ; i < $iframe.length ; i ++ ){
			var ifr = $iframe[i];
			$(ifr).contents().find('#bizTheme').attr('href' , href);
		}
	}
	$.cookie('bizTheme' , themeName , {
		expires : 7
	});
};
/**
 * form表单元素的值序列化对象
 */
biz.serializeObject = function(form){
	var o = {};
	$.each(form.serializeArray() , function(index){
		if (o[this['name']]){
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else{
			o[this['name']] = this['value'];
		}
	});
	return o;
};
/**
 * object对象转成json形式字符串
 */
biz.object2Json = function(obj){
	return $.toJSON(obj);
}
/**
 * 更新url+arg
 */
// refreshUrlLink
biz.refreshUrlLink = function(url , arg){
	var index = url.indexOf('?');
	var length = url.length;
	if (index < 0){
		url = url + '?' + arg;
	} else if (index == length - 1){
		url = url + arg;
	} else{
		url = url.substring(0 , index + 1) + arg + '&' + url.substring(index + 1 , length);
	}
	return url;
};
/**
 * dotoHtmlById
 */
biz.dotoHtmlById = function(id , tos){
	var returnHtml = $('#' + id).html();
	if (null != returnHtml){
		var maxArgsNumb = tos.length;
		for ( var i = 0 ; i < maxArgsNumb ; i ++ ){
			returnHtml = returnHtml.replace(new RegExp("'#arg" + i + "'" , "gm") , typeof (tos[i]) == 'undefined' ? 'this' : tos[i]);
		}
		return returnHtml;
	} else{
		return "";
	}
}

// 将字符串转换为数组
biz.getArray = function(str){
	var list = str.split(",");
	var l = [ ];
	if (list.length == 0)
		return l;
	$.each(list , function(i , n){
		l.push(n);
	});
	return l;
}
/**
 * 对easyui dialog 封装
 */
biz.dialog = function(options){
	var opts = $.extend({
		modal : true ,
		onClose : function(){
			$(this).dialog('destroy');
		}
	} , options);
	return $('<div/>').dialog(opts);
};
/**
 * 左到右
 */
biz.left2right = function(but){
	var $layout = $($(but).parents('.easyui-layout')[0]);
	var left = $layout.find('select')[0];
	var rigth = $layout.find('select')[1];
	if ($.browser.msie){// IE 单独处理
		for ( var i = left.options.length - 1 ; i >= 0 ; i -- ){
			var option = left.options[i];
			if (option.selected){
				var opt = new Option(option.text , option.value);
				rigth.options.add(opt);
				left.remove(i);
			}
		}
	} else{
		$(left.options).each(function(i , n){
			if (n.selected){
				n.selected = false;
				rigth.options.add(n);
			}
		});
	}
};
/**
 * 右到左
 */
biz.right2left = function(but){
	var $layout = $($(but).parents('.easyui-layout')[0]);
	var left = $layout.find('select')[0];
	var rigth = $layout.find('select')[1];
	if ($.browser.msie){// IE 单独处理
		for ( var i = rigth.options.length - 1 ; i >= 0 ; i -- ){
			var option = rigth.options[i];
			if (option.selected){
				var opt = new Option(option.text , option.value);
				left.options.add(opt);
				rigth.remove(i);
			}
		}
	} else{
		$(rigth.options).each(function(i , n){
			if (n.selected){
				n.selected = false;
				left.options.add(n);
			}
		});
	}
}
/**
 * 左全到右
 */
biz.leftall2right = function(but){
	var $layout = $($(but).parents('.easyui-layout')[0]);
	var left = $layout.find('select')[0];
	var rigth = $layout.find('select')[1];
	if ($.browser.msie){// IE 单独处理
		for ( var i = 0 ; i < left.options.length ; i ++ ){
			var option = left.options[i];
			var opt = new Option(option.text , option.value);
			rigth.options.add(opt);
		}
		$(left).empty();
	} else{
		$(left.options).each(function(i , n){
			rigth.options.add(n);
		});
	}
};
/**
 * 右全到左
 */
biz.rightall2left = function(but){
	var $layout = $($(but).parents('.easyui-layout')[0]);
	var left = $layout.find('select')[0];
	var rigth = $layout.find('select')[1];
	if ($.browser.msie){// IE 单独处理
		for ( var i = 0 ; i < rigth.options.length ; i ++ ){
			var option = rigth.options[i];
			var opt = new Option(option.text , option.value);
			left.options.add(opt);
		}
		$(rigth).empty();
	} else{
		$(rigth.options).each(function(i , n){
			left.options.add(n);
		});
	}
};
/**
 * 选择框数据采集
 */
biz.findSelectMultipleValue = function(options){
	var returnArr = [ ] , ids = [ ] , texts = [ ];
	if ($.browser.msie){// IE 单独处理
		for ( var i = 0 ; i < options.length ; i ++ ){
			ids.push(options[i].value);
			texts.push(options[i].text);
		}
	} else{
		$(options).each(function(i , n){
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
biz.sysTip = function(){
	return $.i18n.prop('biz.common.message.systemtip');
}
/**
 * 公司组织根节点值
 */
biz.companyRootId = - 1;
/**
 * 时间对象的格式化
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format){
	var o = {
		"M+" : this.getMonth() + 1 , // month
		"d+" : this.getDate() , // day
		"h+" : this.getHours() , // hour
		"m+" : this.getMinutes() , // minute
		"s+" : this.getSeconds() , // second
		"q+" : Math.floor( (this.getMonth() + 3) / 3) , // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format)){
		format = format.replace(RegExp.$1 , (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o){
		if (new RegExp("(" + k + ")").test(format)){
			format = format.replace(RegExp.$1 , RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr( ("" + o[k]).length));
		}
	}
	return format;
};

/*
 * MAP对象，实现MAP功能 接口： size() 获取MAP元素个数 isEmpty() 判断MAP是否为空 clear() 删除MAP所有元素 put(key, value) 向MAP中增加元素（key, value) remove(key)
 * 删除指定KEY的元素，成功返回True，失败返回False get(key) 获取指定KEY的元素值VALUE，失败返回NULL element(index) 获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
 * containsKey(key) 判断MAP中是否含有指定KEY的元素 containsValue(value) 判断MAP中是否含有指定VALUE的元素 values() 获取MAP中所有VALUE的数组（ARRAY） keys() 获取MAP中所有KEY的数组（ARRAY） 例子： var
 * map = new Map(); map.put("key", "value"); var val = map.get("key") ……
 */
function Map(){
	this.elements = new Array();

	// 获取MAP元素个数
	this.size = function(){
		return this.elements.length;
	};

	// 判断MAP是否为空
	this.isEmpty = function(){
		return (this.elements.length < 1);
	};

	// 删除MAP所有元素
	this.clear = function(){
		this.elements = new Array();
	};

	// 向MAP中增加元素（key, value)
	this.put = function(_key , _value){
		this.elements.push({
			key : _key ,
			value : _value
		});
	};

	// 删除指定KEY的元素，成功返回True，失败返回False
	this.removeByKey = function(_key){
		var bln = false;
		try{
			for (i = 0 ; i < this.elements.length ; i ++ ){
				if (this.elements[i].key == _key){
					this.elements.splice(i , 1);
					return true;
				}
			}
		} catch (e){
			bln = false;
		}
		return bln;
	};

	// 删除指定VALUE的元素，成功返回True，失败返回False
	this.removeByValue = function(_value){// removeByValueAndKey
		var bln = false;
		try{
			for (i = 0 ; i < this.elements.length ; i ++ ){
				if (this.elements[i].value == _value){
					this.elements.splice(i , 1);
					return true;
				}
			}
		} catch (e){
			bln = false;
		}
		return bln;
	};

	// 删除指定VALUE的元素，成功返回True，失败返回False
	this.removeByValueAndKey = function(_key , _value){
		var bln = false;
		try{
			for (i = 0 ; i < this.elements.length ; i ++ ){
				if (this.elements[i].value == _value && this.elements[i].key == _key){
					this.elements.splice(i , 1);
					return true;
				}
			}
		} catch (e){
			bln = false;
		}
		return bln;
	};

	// 获取指定KEY的元素值VALUE，失败返回NULL
	this.get = function(_key){
		try{
			for (i = 0 ; i < this.elements.length ; i ++ ){
				if (this.elements[i].key == _key){
					return this.elements[i].value;
				}
			}
		} catch (e){
			return false;
		}
		return false;
	};

	// 获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	this.element = function(_index){
		if (_index < 0 || _index >= this.elements.length){
			return null;
		}
		return this.elements[_index];
	};

	// 判断MAP中是否含有指定KEY的元素
	this.containsKey = function(_key){
		var bln = false;
		try{
			for (i = 0 ; i < this.elements.length ; i ++ ){
				if (this.elements[i].key == _key){
					bln = true;
				}
			}
		} catch (e){
			bln = false;
		}
		return bln;
	};

	// 判断MAP中是否含有指定VALUE的元素
	this.containsValue = function(_value){
		var bln = false;
		try{
			for (i = 0 ; i < this.elements.length ; i ++ ){
				if (this.elements[i].value == _value){
					bln = true;
				}
			}
		} catch (e){
			bln = false;
		}
		return bln;
	};

	// 判断MAP中是否含有指定VALUE的元素
	this.containsObj = function(_key , _value){
		var bln = false;
		try{
			for (i = 0 ; i < this.elements.length ; i ++ ){
				if (this.elements[i].value == _value && this.elements[i].key == _key){
					bln = true;
				}
			}
		} catch (e){
			bln = false;
		}
		return bln;
	};

	// 获取MAP中所有VALUE的数组（ARRAY）
	this.values = function(){
		var arr = new Array();
		for (i = 0 ; i < this.elements.length ; i ++ ){
			arr.push(this.elements[i].value);
		}
		return arr;
	};

	// 获取MAP中所有VALUE的数组（ARRAY）
	this.valuesByKey = function(_key){
		var arr = new Array();
		for (i = 0 ; i < this.elements.length ; i ++ ){
			if (this.elements[i].key == _key){
				arr.push(this.elements[i].value);
			}
		}
		return arr;
	};

	// 获取MAP中所有KEY的数组（ARRAY）
	this.keys = function(){
		var arr = new Array();
		for (i = 0 ; i < this.elements.length ; i ++ ){
			arr.push(this.elements[i].key);
		}
		return arr;
	};

	// 获取key通过value
	this.keysByValue = function(_value){
		var arr = new Array();
		for (i = 0 ; i < this.elements.length ; i ++ ){
			if (_value == this.elements[i].value){
				arr.push(this.elements[i].key);
			}
		}
		return arr;
	};

	// 获取MAP中所有KEY的数组（ARRAY）
	this.keysRemoveDuplicate = function(){
		var arr = new Array();
		for (i = 0 ; i < this.elements.length ; i ++ ){
			var flag = true;
			for ( var j = 0 ; j < arr.length ; j ++ ){
				if (arr[j] == this.elements[i].key){
					flag = false;
					break;
				}
			}
			if (flag){
				arr.push(this.elements[i].key);
			}
		}
		return arr;
	};
}


//把20150311105443这种形式的时间，转化为：2015-03-11 10:54:43
function strToDate(str){
	if(str=='' || str==null){
		return '';
	}else{
		return str.substr(0,4)+"-"+str.substr(4,2)+"-"+str.substr(6,2)+" "+str.substr(8,2)+":"+str.substr(10,2)+":"+str.substr(12,2);
	}
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
 * 上传的图片路径
 */
biz.imagePath = function() {
	return biz.rootPath() + "/resources/upload";
};


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
							
							var img=document.getElementById(imgPath);
							var result = data.retMsg.split(";"); //包含id与路径
							img.value=result[1];
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




//分类对话框的实现
var select_data = "[]";


/**
 * 获得select2需要的数据
 * @param obj  显示分类的下拉框的id
 * @param url  链接
 * @param isSelect  是否需要“--请选择--”这个提示，其值有：true,false
 */
function getSelect2Data(url,obj,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json, function(i, n) {
			data+='{ text: "'+n.catName+'", id:'+n.catId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj).select2({data:eval(data)});
	});
}
/**
 * 分类对话框的实现
 * parentSelect : 上级分类的select元素的id
 * childSelect  ：二级分类的select元素的id
 * showSelect   ：显示选择的分类的select元素的id
 * @author xiaox 
 */
function categorySelect(parentSelect,childSelect,showSelect){
	var $parent = $("#"+parentSelect); //上级分类
	var $child = $("#"+childSelect);   //二级分类
	var $show = $("#"+showSelect);     //显示选择的分类
	$parent.on("select2:select", function (e) { 
		$child.empty();
		getSelect2Data('http://localhost:8080/YoYoMP/cate/cateTree?parentCatId='+e.params.data.id,childSelect,true);
	});
	
	
	//选择子级分类取得该分类的id
	
	$child.on("select2:select", function (e) { 
		//先判断select_data中是否已经存在该值
		if(e.params.data.id!='-1' && select_data.indexOf('{ text: "'+e.params.data.text+'", id:'+e.params.data.id+',selected:true}', 0)<0){
			select_data=select_data.substring(0, select_data.length-1);
			select_data=select_data.length>1?select_data+",":select_data; //除开没有数据的情况
			select_data+='{ text: "'+e.params.data.text+'", id:'+e.params.data.id+',selected:true}';
			select_data+="]";
		}
		$show.empty();
		$show.select2({data:eval(select_data)});
	});
	
	//取消子级分类
	$show.on("select2:unselect", function (e) { 
		var data=eval(select_data);
		$.each(data, function(i, n) {
			if(n.id==e.params.data.id){
				data.splice(i,1);
			}
		});
		select_data=JSON.stringify(data);
		$show.empty();
		$show.select2({data: eval(select_data)});
	});

}

//初始化分类信息
function initCategory(parentSelect,childSelect,showSelect,pid){
	select_data = "[]";
	$("#"+showSelect).select2({data:eval(select_data)});
	$("#"+childSelect).select2();
	getSelect2Data('http://localhost:8080/YoYoMP/cate/cateTree?parentCatId='+pid,parentSelect,true);
}




/**
 * 方法描述：初始化分类下拉树
 * 
 * @param obj
 * @param multiple
 */
function initRealCateTree(obj, multiple) {
	var $obj = $('#' + obj);
	$obj.combotree({
		url : _path + '/comboBox/cateTreeCombox' ,
		multiple : multiple ,
		queryParams : { 'parentCatId' : 0 } ,
		columns : [
			[
					{ field : 'catId' , title : '分类Id' , fixed : true } ,
					{ field : 'catName' , title : '分类名称' , fixed : true }
			]
		] , fitColumns : true , loadFilter : function(data) {
			for (var i = 0; i < data.length; i++) {
				if (data[ i ].childCount > 0) {
					data[ i ].state = 'closed';
				} else {
					data[ i ].state = '';
				}
				data[ i ].id = data[ i ].catId;
				data[ i ].text = data[ i ].catName;
			}
			return data;
		} , onBeforeExpand : function(row) {
			var url = _path + '/comboBox/cateTreeCombox?parentCatId=' + row.catId;
			$obj.combotree("tree").tree("options").url = url;
			return true;
		} , onLoadSuccess : function(node, data) {
			$obj.combotree('tree').tree("expandAll");
		} });
}

//日期转换
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}

//获取url某个参数
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) {
		return unescape(r[2]);
	} 
	return null;
}

String.prototype.format = function(objOrArr){
	/*
	 *eg:var s = "hello {1},good{2}!";
	 *s.format(["lili","night"]);
	 *或者 var s = "hello {name},good{time}";
	 *s.format({name:'lili',time:'night'}); 
	 */
	var that = this;
	var reg = null;
	if($.isArray(objOrArr)){
		for(var i=0;i<objOrArr.length;i++){
			reg = new RegExp("\\{"+i+"\\}","gim");
			that = that.replace(reg, objOrArr[i]);
		}
	}else if(typeof objOrArr == 'object'){
		for(var attr in objOrArr){
			reg = new RegExp("\\{"+attr+"\\}","gim");
			that = that.replace(reg, objOrArr[attr]);
		}
	}
	return that;
};