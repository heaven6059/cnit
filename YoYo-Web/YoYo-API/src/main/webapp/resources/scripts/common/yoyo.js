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
yoyo.imagesUrl="http://10.255.8.92:8082/imageUrl";//图片服务器访问路径
yoyo.memUrl = yoyo.basePath + "/yoyomem"; // 买家中心,仅仅域名或IP
yoyo.shopUrl = yoyo.basePath + "/yoyoshop";// 卖家中心,仅仅域名或IP
yoyo.portalUrl = yoyo.basePath + "/yoyoportal";//门户
yoyo.mpUrl = yoyo.basePath + "/yoyomp";//管理平台

yoyo.successCode = '000000'; // 成功返回code标志
yoyo.failCode = '000002';// 错误返回code标志

/**TODO 分类的类别
 *  整车 999
	配件 888
	保养 777
	精品 666
	其他 555
	官方保养 505(区别自定义保养分类)
*/
yoyo.car=999;
yoyo.accessory=888;
yoyo.maintain=777;
yoyo.boutique=666;
yoyo.official_maintain=505;
yoyo.other=555;

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

/**
 * async:true异步 dataType:服务器返回的数据类型
 */
yoyo.ajaxRequest = function(url, reqParam, async, dataType, callback) {
	$.ajax({
		// beforeSend: function(){
		// yoyo.isLogin();
		// },
		async : async,
		type : 'POST',
		url : url,
		data : reqParam,
		dataType : dataType,
		cache : false,
		success : callback
	});
};
yoyo.isLogin = function() {
	var url = yoyo.basePath + "/common/getCurrentUser?v=" + Math.random();
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (!data) {
				top.location = yoyo.basePath + "/pages/login.html";
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

