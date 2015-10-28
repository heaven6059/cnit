/**
 * 公共js
 * 
 * @author wanghb
 * @date 2015-4-20
 * @version 1.0.0
 */

var yoyo = {};

yoyo.location = (window.location + '').split('/');
yoyo.basePath = yoyo.location[0] + '//' + yoyo.location[2];// 获取当前URL路径

//yoyo.imagesUrl="http://10.255.8.17:8082/imageUrl";//图片服务器访问路径
//yoyo.imagesUrl="http://10.255.8.92:8082/imageUrl";//图片服务器访问路径
yoyo.imagesUrl="http://www.yoyocar.cn/imageUrl";
yoyo.memUrl = yoyo.basePath + "/yoyomem"; // 买家中心,仅仅域名或IP
yoyo.shopUrl = yoyo.basePath + "/yoyoshop";// 卖家中心,仅仅域名或IP
yoyo.portalUrl = yoyo.basePath + "/yoyoportal";//门户

yoyo.successCode = '000000'; // 成功返回code标志
yoyo.failCode = '000002';// 错误返回code标志
yoyo.validateElementError = '000100';// 错误返回code标志

/**
 * 平台常量定义 渲染页面和js所有平台数据来源，务在页面或js写死
 */
// 订单状态（例子）
yoyo.orderStatus = [ [ 0, '初审中' ], [ 1, '初审失败' ], [ 2, '核保中' ] ];

// 加载效果
yoyo.loading = function(type, msg) {
	if (msg == null || msg == "") {
		msg = "请稍后,正在加载......";
	}
	var body_width = document.body.clientWidth;
	var body_height = document.body.clientHeight;
	// 展示loading
	if (type == "show") {
		var myload = $(
				"<div id='myload' style='border:2px solid #95B8E7;display:inline-block;padding:10px 8px;;position:absolute;z-index:999999999;top:0px;left:0px;background:#ffffff'>"
						+ "<div style='float:left;'><img src='"
						+ yoyo.basePath
						+ "/common/jquery-easyui-1.4.1/themes/black/images/loading.gif'></div>"
						+ "<div style='font-size:12px;float:left;display:inline-block;margin-top:2px;margin-left:5px;'>"
						+ msg + "</div>" + "</div>").appendTo($("body"));
		var myloadwidth = myload.width();
		var myloadheight = myload.height();
		myload.css({
			"left" : (body_width - myloadwidth) / 2,
			"top" : (body_height - myloadheight) / 2
		});
		$(
				"<div id='remote_load' style='position:fixed;width:100%;height:"
						+ body_height
						+ "px;z-index:99999999;top:0px;left:0px;background-color: #ccc;opacity: 0.3;filter: alpha(opacity = 30);'></div>")
				.appendTo($("body"));
	} else {
		$("#myload").remove();
		$("#remote_load").remove();
	}
};

// 获取当前日期
yoyo.getCurDate = function() {
	var now = new Date();
	var SY = now.getFullYear();
	var SM = now.getMonth();
	var SD = now.getDate();
	return SY + "-" + (SM + 1) + "-" + SD;
};
// 获取当前日期前一个月
yoyo.getPreMonthDate = function() {
	var now = new Date();
	var SY = now.getFullYear();
	var SM = now.getMonth() - 1;
	var SD = now.getDate();
	return SY + "-" + (SM + 1) + "-" + SD;
};
//获取当前日期前半年
yoyo.getSexMonthDate = function() {
	var now = new Date();
	var SY = now.getFullYear();
	var SM = now.getMonth() - 6;
	var SD = now.getDate();
	return SY + "-" + (SM + 1) + "-" + SD;
};
/**
 * async:true异步 dataType:服务器返回的数据类型
 */
yoyo.ajaxRequest = function(sendurl, reqParam, async, dataType, callback) {
	var url = yoyo.portalUrl + "/errorWord";
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : reqParam,
		success : function(data) {
			if(data.retCode=="000012"){
				alert("您输入的内容包含敏感词！");
				return false;
			}else{
				$.ajax({
					async : async,
					type : 'POST',
					url : sendurl,
					data : reqParam,
					dataType : dataType,
					cache : false,
					success : callback
				});
			}
		}
	});
};
yoyo.word = function(reqParam) {//敏感词
	var url = yoyo.portalUrl + "/errorWord";
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : reqParam,
		success : function(data) {
			if(data.retCode=="000002"){
				return false;
			}else{
				return true;
			}
		}
	});
};
// 初始化付款方式(下拉框)
yoyo.initPayType = function(id) {
	var serviceTypeData;
	var json = new Array();
	json.push('{"label":"0","text":"0","value":"--请选择--"}');
	for ( var i = 0; i < yoyo.payType.length; i++) {
		json.push('{"label":"' + yoyo.payType[i][0] + '","text":"'
				+ yoyo.payType[i][0] + '","value":"' + yoyo.payType[i][1]
				+ '"}');
	}
	serviceTypeData = "[" + json.join(",") + "]";
	$("#" + id + "").combobox({
		data : eval("(" + serviceTypeData + ")"),
		valueField : 'text',
		textField : 'value'
	});
};
// 打开窗口
yoyo.openWindow = function(windowId, opt) {
	$('#' + windowId).window({
		title : opt,
		modal : true,
		resizable : false,
		draggable : false,
		collapsible : false,
		closed : true,
		maximized : true,
		minimizable : false,
		maximizable : false,
		onBeforeClose : function() {
			$(".tooltip").remove();
		}
	});
	$('#' + windowId).window('open');
};
// 将表单数据转为json
yoyo.form2Json = function(id) {
	var arr = $("#" + id).serializeArray()
	var jsonStr = "";
	jsonStr += '{';
	for ( var i = 0; i < arr.length; i++) {
		jsonStr += '"' + arr[i].name + '":"' + $.trim(arr[i].value) + '",'
	}
	jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
	jsonStr += '}'
	var json = JSON.parse(jsonStr)
	return json
};
// 关闭窗口
yoyo.closeWindow = function(windowId) {
	$('#' + windowId).window('close');
};

// 反推，对象填充到input
yoyo.setValue = function(obj) {
	// 开始遍历
	for ( var p in obj) {
		// 方法
		console.info(obj);
		if (typeof (obj[p]) == "function") {
			// obj[p]();
		} else {
			$("#" + p).val(obj[p]);
			// p 为属性名称，obj[p]为对应属性的值
		}
	}
}
// 正推， 表单到对象
yoyo.serializeObject = function(form) {
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
yoyo.keydown = function() {
	$(document).keydown(function(event) {
		if (event.keyCode == 13) {
			$("#search").click();
		}
	});
};

yoyo.urlEncode = function(url) {
	return encodeURIComponent(url).replace(/'/g, "%27").replace(/"/g, "%22");
}
yoyo.urlDecode = function(url) {
	return decodeURIComponent(url.replace(/\+/g, " "));
}
// 获取传递参数值
$.getUrlParam = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};


$.extend($.fn.validatebox.methods, {    
    remove: function(jq, newposition){    
        return jq.each(function(){    
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');  
        });    
    },  
    reduce: function(jq, newposition){    
        return jq.each(function(){    
           var opt = $(this).data().validatebox.options;  
           $(this).addClass("validatebox-text").validatebox(opt);  
        });    
    },
    reset: function(jq, newposition){ 
    	 return jq.each(function(){    
             $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');  
             var opt = $(this).data().validatebox.options;  
             $(this).addClass("validatebox-text").validatebox(opt);  
         });
    }
}); 

//清空datagrid数据
$.extend($.fn.datagrid.methods,{
	 clearData:function(jq){
	     return jq.each(function(){
	          $(this).datagrid('loadData',{total:0,rows:[]});
	     });
	 }
});


/**TODO 分类的类别
 *  整车 999
	配件 888
	保养 777
	精品 666
	其他 555
*/
yoyo.car=999;
yoyo.accessory=888;
yoyo.maintain=777;
yoyo.boutique=666;
yoyo.other=555;



Array.prototype.indexOf = function(val) {
       for (var i = 0; i < this.length; i++) {
           if (this[i] == val) return i;
       }
       return -1;
   };
 //数组删除指定元素
   Array.prototype.remove = function(val) {
       var index = this.indexOf(val);
       if (index > -1) {
           this.splice(index, 1);
       }
   };

 //输入框验证，add by ssd
   $.extend($.fn.validatebox.defaults.rules, {
       CHS: {
         validator: function (value, param) {
           return /^[\u0391-\uFFE5]+$/.test(value);
         },
         message: '请输入汉字'
       },
       english : {// 验证英语
             validator : function(value) {
                 return /^[A-Za-z]+$/i.test(value);
             },
             message : '请输入英文'
         },
         ip : {// 验证IP地址
             validator : function(value) {
                 return /\d+\.\d+\.\d+\.\d+/.test(value);
             },
             message : 'IP地址格式不正确'
         },
       ZIP: {
         validator: function (value, param) {
           return /^[0-9]\d{5}$/.test(value);
         },
         message: '邮政编码不存在'
       },
       QQ: {
         validator: function (value, param) {
           return /^[1-9]\d{4,10}$/.test(value);
         },
         message: 'QQ号码不正确'
       },
       mobile: {
         validator: function (value, param) {
           return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
         },
         message: '手机号码不正确'
       },
       tel:{
         validator:function(value,param){
           return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
         },
         message:'电话号码不正确'
       },
       mobileAndTel: {
         validator: function (value, param) {
           return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
         },
         message: '请正确输入电话号码'
       },
       number: {
         validator: function (value, param) {
           return /^[0-9]+.?[0-9]*$/.test(value);
         },
         message: '请输入数字'
       },
       money:{
         validator: function (value, param) {
          	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
          },
          message:'请输入正确的金额'

       },
       mone:{
         validator: function (value, param) {
          	return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
          },
          message:'请输入整数或小数'

       },
       integer:{
         validator:function(value,param){
           return /^[+]?[1-9]\d*$/.test(value);
         },
         message: '请输入最小为1的整数'
       },
       integ:{
         validator:function(value,param){
           return /^[+]?[0-9]\d*$/.test(value);
         },
         message: '请输入整数'
       },
       range:{
         validator:function(value,param){
           if(/^[1-9]\d*$/.test(value)){
             return value >= param[0] && value <= param[1]
           }else{
             return false;
           }
         },
         message:'输入的数字在{0}到{1}之间'
       },
       minLength:{
         validator:function(value,param){
           return value.length >=param[0]
         },
         message:'至少输入{0}个字'
       },
       maxLength:{
         validator:function(value,param){
           return value.length<=param[0]
         },
         message:'最多{0}个字'
       },
       //select即选择框的验证
       selectValid:{
         validator:function(value,param){
           if(value == param[0]){
             return false;
           }else{
             return true ;
           }
         },
         message:'请选择'
       },
       idCode:{
         validator:function(value,param){
           return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
         },
         message: '请输入正确的身份证号'
       },
       loginName: {
         validator: function (value, param) {
           return /^[\u0391-\uFFE5\w]+$/.test(value);
         },
         message: '只允许汉字、英文字母、数字及下划线。'
       },
       equalTo: {
         validator: function (value, param) {
           return value == $(param[0]).val();
         },
         message: '两次输入的字符不一至'
       },
       englishOrNum : {// 只能输入英文和数字
             validator : function(value) {
                 return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
             },
             message : '请输入英文、数字、下划线或者空格'
         },
        xiaoshu:{ 
           validator : function(value){ 
           return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
           }, 
           message : '最多保留两位小数！'    
       	},
       ddPrice:{
       validator:function(value,param){
         if(/^[1-9]\d*$/.test(value)){
           return value >= param[0] && value <= param[1];
         }else{
           return false;
         }
       },
       message:'请输入1到100之间正整数'
     },
     jretailUpperLimit:{
       validator:function(value,param){
         if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
           return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
         }else{
           return false;
         }
       },
       message:'请输入0到100之间的最多俩位小数的数字'
     },
     rateCheck:{
       validator:function(value,param){
         if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
           return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
         }else{
           return false;
         }
       },
       message:'请输入0到1000之间的最多俩位小数的数字'
     }
     });




