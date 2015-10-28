var patterns = {
	mobile : /^1[3|4|5|8][0-9]{9}$/ ,
	userName : /^[a-zA-Z0-9_-]{1,16}$/ ,
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

var layer_utils={
		layerindex:"",
		alert:function(message,callback){
			layerindex=layer.alert(message,{
					closeBtn:false,
					title:false,
					icon:2,
					end:function (){
						if(callback)
							callback.call();
					}
			});
		},
		confirm:function(message,confirm,cancel){
			layerindex=layer.confirm(message, {
			    btn: ['确定','取消'], //按钮
			    shade: false, //不显示遮罩
			    title:false,
			    move:false,
			    icon:3
			}, function(){
				if(confirm){
					confirm.call();
				}
				layer.close(layer_utils.layerindex);
			}, function(){
				if(cancel){
					cancel.call();
				}
			});
		}
};

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
function compareDate(begin, end) {
	// 将字符串转换为日期
	//var begin = new Date(begin.replace(/-/g, "/"));
	//var end = new Date(end.replace(/-/g, "/"));
	// js判断日期
	if ((begin - end) >= 0) {
		return false;
	}
	return true;
}

// ======================================== easyui update ========================================
$.parser.auto = false;
$(function(){
	$.messager.progress({
		text : '数据加载中...' ,
		interval : 100
	});
	$.parser.parse(window.document);
	window.setTimeout(function(){
		$.messager.progress('close');
		if (self != parent){
			window.setTimeout(function(){
				try{
					parent.$.messager.progress('close');
				} catch (e){
				}
			} , 500);
		}
	} , 1);
	$.parser.auto = true;
});
/**
 * 扩展tree getCheckedExt 获得选中节点+实心节点 getSolidExt 获取实心节点
 */
$.extend($.fn.tree.methods , {
	getCheckedExt : function(jq){
		var checked = [ ];
		var checkbox2 = $(jq).find("span.tree-checkbox1,span.tree-checkbox2").parent();
		$.each(checkbox2 , function(){
			var thisData = {
				target : this ,
				"checked" : true
			};
			var node = $.extend({} , $.data(this , "tree-node") , thisData);
			checked.push(node);
		});
		return checked;
	} ,
	getSolidExt : function(jq){
		var checked = [ ];
		var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
		$.each(checkbox2 , function(){
			var node = $.extend({} , $.data(this , "tree-node") , {
				target : this
			});
			checked.push(node);
		});
		return checked;
	} ,
	setValues : function(jq , v){
		var options = $.data(jq[0] , "tree").options;
		if ( ! options.checkbox){
			return;
		}
		var ck = biz.getArray(v);
		for ( var i = 0 ; i < ck.length ; i ++ ){
			var _curNode = $(jq).tree('find' , ck[i]);
			if (_curNode){
				$(jq).tree('check' , _curNode.target);
			}
		}
	} ,
	getChildrenIds : function(jq , node){
		// 获取当前节点下所有子节点的id值(包括当前节点的值),以逗号隔开返回
		var _nodes = $(jq).tree('getChildren' , node.target);
		var _sNode = [ ];
		// biz.companyRootId
		_sNode.push(node.id);
		if (_nodes && _nodes.length > 0){
			$.each(_nodes , function(i , n){
				_sNode.push(n.id);
			});
		}
		return _sNode.join(',');
	} ,
	getTreeIds : function(jq){
		var roots = $(jq).tree('getRoots');
		if (roots.length == 0)
			return '';
		var _sNode = [ ];
		$.each(roots , function(i , n){
			_sNode.push(n.id);
			var _nodes = $(jq).tree('getChildren' , n.target);
			if (_nodes && _nodes.length > 0){
				$.each(_nodes , function(i , n){
					_sNode.push(n.id);
				});
			}
		});
		return _sNode.join(',');
	} ,
	getParentIds : function(jq , node){
		// 获取当前节点的父节点id值
		var pNode = [ ];
		if ( ! node)
			return '';
		var $curNode = node;
		pNode.push($curNode.id);
		while ($(jq).tree('getParent' , $curNode.target)){
			$curNode = $(jq).tree('getParent' , $curNode.target);
			pNode.push($curNode.id);
		}
		return pNode.join(",");
	}
});
/**
 * 扩展datagrid，添加动态增加或删除Editor的方法
 */
$.extend($.fn.datagrid.methods , {
	addEditor : function(jq , param){
		if (param instanceof Array){
			$.each(param , function(index , item){
				var e = $(jq).datagrid('getColumnOption' , item.field);
				e.editor = item.editor;
			});
		} else{
			var e = $(jq).datagrid('getColumnOption' , param.field);
			e.editor = param.editor;
		}
	} ,
	removeEditor : function(jq , param){
		if (param instanceof Array){
			$.each(param , function(index , item){
				var e = $(jq).datagrid('getColumnOption' , item);
				e.editor = {};
			});
		} else{
			var e = $(jq).datagrid('getColumnOption' , param);
			e.editor = {};
		}
	} ,
	hideDisplayMsg : function(jq){
		var pager = $(jq).datagrid('getPager');
		$(pager).pagination({
			displayMsg : ''
		});
	}
});
/**
 * 扩展datagrid editor 增加带复选框的下拉树 combocheckboxtree 增加日期时间组件 datetimebox 增加多选combobox组件 multiplecombobox
 */
$.extend($.fn.datagrid.defaults.editors , {
	combocheckboxtree : {
		init : function(container , options){
			var editor = $('<input />').appendTo(container);
			options.multiple = true;
			editor.combotree(options);
			return editor;
		} ,
		destroy : function(target){
			$(target).combotree('destroy');
		} ,
		getValue : function(target){
			return $(target).combotree('getValues').join(',');
		} ,
		setValue : function(target , value){
			$(target).combotree('setValues' , sy.getList(value));
		} ,
		resize : function(target , width){
			$(target).combotree('resize' , width);
		}
	} ,
	datetimebox : {
		init : function(container , options){
			var editor = $('<input />').appendTo(container);
			editor.datetimebox(options);
			return editor;
		} ,
		destroy : function(target){
			$(target).datetimebox('destroy');
		} ,
		getValue : function(target){
			return $(target).datetimebox('getValue');
		} ,
		setValue : function(target , value){
			$(target).datetimebox('setValue' , value);
		} ,
		resize : function(target , width){
			$(target).datetimebox('resize' , width);
		}
	} ,
	multiplecombobox : {
		init : function(container , options){
			var editor = $('<input />').appendTo(container);
			options.multiple = true;
			editor.combobox(options);
			return editor;
		} ,
		destroy : function(target){
			$(target).combobox('destroy');
		} ,
		getValue : function(target){
			return $(target).combobox('getValues').join(',');
		} ,
		setValue : function(target , value){
			$(target).combobox('setValues' , sy.getList(value));
		} ,
		resize : function(target , width){
			$(target).combobox('resize' , width);
		}
	}
});
/**
 * 扩展 datagrid/treegrid 增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e , field){
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if ( ! headerContextMenu){
		var tmenu = $('<div style="width:150px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for ( var i = 0 ; i < fields.length ; i ++ ){
			var fildOption = grid.datagrid('getColumnOption' , fields[i]);
			if ( ! fildOption.hidden){
				$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			} else{
				$('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item){
				var field = $(item.target).attr('field');
				if (item.iconCls == 'icon-ok'){
					grid.datagrid('hideColumn' , field);
					$(this).menu('setIcon' , {
						target : item.target ,
						iconCls : 'icon-empty'
					});
				} else{
					grid.datagrid('showColumn' , field);
					$(this).menu('setIcon' , {
						target : item.target ,
						iconCls : 'icon-ok'
					});
				}
			}
		});
	}
	headerContextMenu.menu('show' , {
		left : e.pageX ,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
/**
 * 扩展 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function(XMLHttpRequest){
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
 * 扩展easyui的validator插件rules，支持更多类型验证
 */
$
		.extend(
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
							return /^(13|14|15|17|18)\d{9}$/i.test(value);
						} ,
						message : '手机号码格式不正确'
					} ,
					phoneAndMobile : {// 电话号码或手机号码
						validator : function(value){
							return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value)
									|| /^(13|14|15|17|18)\d{9}$/i.test(value);
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
							return /^[a-zA-Z0-9_-]{8,16}$/i.test(value);
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
							return /^[a-zA-Z0-9_-]{1,16}$/.test(value);
						} ,
						message : '1-16位字符，支持字母、数字及“_”“-”组合！'
					} , 
					password : {// 用户名
						validator : function(value){
							return /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{8,16}$/.test(value);
						} ,
						message : '输入的密码为大小字母,数字及@!#$^&.~*字符组合,长度8~16位'
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
$.extend($.fn.validatebox.methods , {
	remove : function(jq , newposition){
		return jq.each(function(){
			$(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');
			// $(this).validatebox('hideTip', this);
		});
	} ,
	reduce : function(jq , newposition){
		return jq.each(function(){
			var opt = $(this).data().validatebox.options;
			$(this).addClass("validatebox-text").validatebox(opt);
			// $(this).validatebox('validateTip', this);
		});
	} ,
	validateTip : function(jq){
		jq = jq[0];
		var opts = $.data(jq , "validatebox").options;
		var tip = $.data(jq , "validatebox").tip;
		var box = $(jq);
		var value = box.val();
		function setTipMessage(msg){
			$.data(jq , "validatebox").message = msg;
		}
		;
		var disabled = box.attr("disabled");
		if (disabled == true || disabled == "true"){
			return true;
		}
		if (opts.required){
			if (value == ""){
				box.addClass("validatebox-invalid");
				setTipMessage(opts.missingMessage);
				$(jq).validatebox('showTip' , jq);
				return false;
			}
		}
		if (opts.validType){
			var result = /([a-zA-Z_]+)(.*)/.exec(opts.validType);
			var rule = opts.rules[result[1]];
			if (value && rule){
				var param = eval(result[2]);
				if ( ! rule["validator"](value , param)){
					box.addClass("validatebox-invalid");
					var message = rule["message"];
					if (param){
						for ( var i = 0 ; i < param.length ; i ++ ){
							message = message.replace(new RegExp("\\{" + i + "\\}" , "g") , param[i]);
						}
					}
					setTipMessage(opts.invalidMessage || message);
					$(jq).validatebox('showTip' , jq);
					return false;
				}
			}
		}
		box.removeClass("validatebox-invalid");
		$(jq).validatebox('hideTip' , jq);
		return true;
	} ,
	showTip : function(jq){
		jq = jq[0];
		var box = $(jq);
		var msg = $.data(jq , "validatebox").message
		var tip = $.data(jq , "validatebox").tip;
		if ( ! tip){
			tip = $(
					"<div class=\"validatebox-tip\">" + "<span class=\"validatebox-tip-content\">" + "</span>"
							+ "<span class=\"validatebox-tip-pointer\">" + "</span>" + "</div>").appendTo("body");
			$.data(jq , "validatebox").tip = tip;
		}
		tip.find(".validatebox-tip-content").html(msg);
		tip.css({
			display : "block" ,
			left : box.offset().left + box.outerWidth() ,
			top : box.offset().top
		});
	} ,
	hideTip : function(jq){
		jq = jq[0];
		var tip = $.data(jq , "validatebox").tip;
		if (tip){
			tip.remove;
			$.data(jq , "validatebox").tip = null;
		}
	}
});
/**
 * 扩展easyui datagrid 连续列合并扩展
 */
$.extend($.fn.datagrid.methods , {
	autoMergeCells : function(jq , fields){
		return jq.each(function(){
			var target = $(this);
			if ( ! fields){
				fields = target.datagrid("getColumnFields");
			}
			var rows = target.datagrid("getRows");
			var i = 0 , j = 0 , temp = {};
			for (i ; i < rows.length ; i ++ ){
				var row = rows[i];
				j = 0;
				for (j ; j < fields.length ; j ++ ){
					var field = fields[j];
					var tf = temp[field];
					if ( ! tf){
						tf = temp[field] = {};
						tf[row[field]] = [i];
					} else{
						var tfv = tf[row[field]];
						if (tfv){
							tfv.push(i);
						} else{
							tfv = tf[row[field]] = [i];
						}
					}
				}
			}
			$.each(temp , function(field , colunm){
				$.each(colunm , function(){
					var group = this;
					if (group.length > 1){
						var before , after , megerIndex = group[0];
						for ( var i = 0 ; i < group.length ; i ++ ){
							before = group[i];
							after = group[i + 1];
							if (after && (after - before) == 1){
								continue;
							}
							var rowspan = before - megerIndex + 1;
							if (rowspan > 1){
								target.datagrid('mergeCells' , {
									index : megerIndex ,
									field : field ,
									rowspan : rowspan
								});
							}
							if (after && (after - before) != 1){
								megerIndex = after;
							}
						}
					}
				});
			});
		});
	} ,
	clearData : function(jq , fields){
		$(jq).datagrid('loadData' , {
			total : 0 ,
			rows : [ ]
		});
	}
});

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
};

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