/* jQuery form plug-in 1.0.1
 *
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * http://docs.jquery.com/Plugins/Validation
 *
 * Copyright (c) 2010 NickCheng
 * You can affiliation me My Email Address :NickCZPing@gmail.com
 * And My QQ Number is:	406762380
 *
 * $Id: jquery.form-1.0.1 6403 2010-04-09 09:07
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 * Power description :
 * 	 All of the form item's valid
 * 	 Involve hidden ,textBox,radio,checkBox,textArea,select and so on
 *   I will define their's style and validate
 *   And for example the textBox have many items userName,companyName,telephone,Address and so on
 *   目前该版本支持：ie6,7,8;firefox3.以上;Chrome
 */

(function($) {
	var str = "";
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	//判断浏览器类型
	var s;
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	//定义所有需验证的控件名称
	//定义所有的正则
     var WIDGETREG =
     {
		'username':/^[A-Za-z]\w{3,19}$/,
		'password':/^[0-9A-z-_~!@#$%^&*()_+`\[\]\;\:\"\<\>]{6,20}$/,
		'number':/^\d*$/,
		'chineseName':/(^[\u4e00-\u9fa5]*$)/,
		'address':/(^[\u4e00-\u9fa5]*[0-9a-zA-Z]*([\u4e00-\u9fa5]|[0-9a-zA-Z])*$)/,
		'qq':/^[1-9][0-9]{4,}$/,
		'email':/^[a-zA-Z0-9_\\.]+@[a-zA-Z0-9-]+[\\.a-zA-Z]+$/,
		'phone':/^(\+)?([0-9]{1,3}[-\s])?([0-9]{3,4}[-\s])?([0-9]{7,8})([-\s][0-9]{1,5})?$/,
		'mobile':/^([0-9]{11,12})?$/,
		'zipcode':/^[0-9]{6}$/,
		'money':/^-?\d+(\.\d{0,2})?$/,
		'authcode':/^[^\s?<>\'\"!@%#$~&*():;]*$/,
		'datetime':/^\d{4}-\d{2}-\d{2}$/,
		'bankaccount':/^(?:\d{4}){4,5}\d{3}$/,
		'textbox':/^[^;,'"<>#]*$/,
		'textarea':/^[^;,'"<>#]*$/,
      	'confirmpwd':'',
      	'commonSelect':'',
      	'commonRadio':'',
      	'idcard':'',
      	'invalidchar': /^[^<>$';]*$/,
      	'realName':/^([\u4e00-\u9fa5]{2,12})|([a-zA-Z-\s]{3,20})$/,
      	'urlname':/^([a-z]+:\/\/)?([\w-]+)\.([\w-]+)(\.[\w-]+)*(\/)?[^s]*$/,
      	'domain':/^(\*\.)?[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?$/,
      	'ip':/^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/
    };
	//初始化项当鼠标移向控件弹出提示信息，移开则隐藏提示信息（obj:当前对象，str_:初始化提示信息）
	$.init = function(obj,str_){
		  if(obj.attr("required")){
				var redstar = "<span class='redstar'>*</span>"
				var newtitle= obj.parent().prev().find('label').text();
				var trimtitle = $.trim(newtitle);
				if(_isHaveStar(trimtitle))
					obj.parent().prev().find('label').html(newtitle + redstar);
		  }
		  obj.hover(function(){
		  	 var splitclass = obj.attr('class').substring(16);
			 if(obj.val().length == 0 || splitclass == 'error-style'){
				  //如果存在则不加
				  if($('#tooltips-box').size()==0){
					if($('#TB_window').size() > 0){
					  $('#TB_window').append('<div id="tooltips-box"><div class="tooltips-howpanel-top" style="display:none;"></div><div class="tooltips-howpanel"></div><iframe id="tooltips_iframe"></iframe></div>');
					}else{
					  $('body').append('<div id="tooltips-box"><div class="tooltips-howpanel-top" style="display:none;"></div><div class="tooltips-howpanel"></div><iframe id="tooltips_iframe"></iframe></div>');
					}
				  }
				  tipShow();
			  }else{
			  	  if(obj.attr('type') == 'text') str_ = '输入正确' ;
				  tipHidden();
			  }
		  },function(){
			  tipHidden();
		  });

		  //获得当前高度（obj：当前对象）
		  _getTop = function(obj){
			  var position = obj.position();
			  var type = obj.attr("type");
			  var top = position.top ;
			  if(type == "textarea"){
				  var _top = 0;
				  if(Sys.ie) _top = obj.attr("rows") * 22;
				  if(Sys.firefox) _top = obj.attr("rows") * 19;
				  else _top = obj.attr("rows") * 17;
				  var tatop = top + _top;
				  return tatop;
			  }else{
				  var untatop = top+28;
				  return untatop;
			  }

		  };
		  _getWidth = function(object) {
		        return object.offsetWidth;
		  };

		  _getLeft = function(obj) {
			  var position = obj.position();
			  var left = position.left;
	      	  return left;
		  };

		 /* _getTop = function(object) {
	        var go = object;
	        var oParent,oTop = go.offsetTop;
	        while(go.offsetParent!=null) {
	            oParent = go.offsetParent;
	            oTop += oParent.offsetTop;
	            go = oParent;
	        }
	        return oTop + $(object).height()+ 5;
		  };*/
		  tipShow = function(){
			var left = _getLeft(obj);
			var width = $(".tooltips-howpanel").width();
			var swidth = document.body.scrollWidth;
			var xx = left+width-swidth;
			var lf = 0;
			var tp = _getTop(obj);
			if(xx>0){
				lf = _getLeft(obj)-xx;
				$('.tooltips-howpanel-top').css({left:xx+'px'});
				//$('.tooltips-howpanel-top').css({left:(lf+xx)+'px',top:tp+'px'});
			}else{
				lf = _getLeft(obj);
				$('.tooltips-howpanel-top').css({left:'10px'});
				//$('.tooltips-howpanel-top').css({left:lf+10+'px',top:tp+'px'});
			}
			$('#tooltips-box').css({left:lf+'px',top:tp+'px',position:'absolute'});
			$('#tooltips_iframe').css({width:'240px',border:0,height:'35px'});
			//$('.tooltips-howpanel').css({left:lf+'px',top:tp+'px'});
			$('.tooltips-howpanel').html(str_);
               $('.tooltips-howpanel').fadeIn("fast");
				$('.tooltips-howpanel-top').fadeIn("fast");
  		 };
		 tipHidden = function(){
			$('.tooltips-howpanel').hide();
			$('.tooltips-howpanel-top').hide();
			$('#tooltips_iframe').css({width:'0px',height:'0px'});
		 };
	//	 obj.info = obj.options.message;
		 obj.hover(window['tipShow'],window['tipHidden'])
					.focus(window['tipShow'])
					.blur(window['tipHidden']);

	};

	//判断是否需要加“*”号
	_isHaveStar = function(str){
		var flag = true;
		if(Sys.ie || Sys.chrome){
			str = str.substring(str.length-1);
			if(str == "*")
			return false;
		}else{
			for(var i in str){
				if(str[i] == "*"){
					flag = false;
					break;
				}
			}
		}
		return flag;
	};

	//表单所填信息验证（name:当前方法名，obj:当前控件对象）
	$.validator = function(name , obj,param){
		var title = _getLabelInfo(obj);
		var errorPrompt = title+'格式输入错误.';
		str = obj.val();
		for(j in WIDGETREG){
			var reg = WIDGETREG[j];
			if(name == j && reg != ""){
				if(str !="" && str != null && reg.test(str)){
					str = str;
				}else{
					str = this.error = {level:'error' ,text:errorPrompt};
				}
				return str;
			}
			else{
				if(name == 'idcard'){
					if(str.length!==16 && str.lenght!=17 && /^[1-9]\d{5}(?:\d{2}|\d{4})(?:(?:01|03|05|07|08|10|12)(?:0[1-9]|[1-2]\d|3[0-1])|(?:04|06|09|11)(?:0[1-9]|[1-2]\d|30)|02(?:0[1-9]|[1-2]\d))(?:\d{3}|\d{4}|\d{3}[xX])$/.test(str)){
						str = str;
					}else{
						str = this.error = {level:'error' ,text:errorPrompt+'（规范：15位或者18位）！'};
					}
					return str;
				}
				else if(name == 'confirmpwd'){
					var pwd = obj.parent().parent().parent().find("input[type='password']").val();
					if(str != pwd){
						str = this.error = {level:'error' ,text:'两次密码输入不一致.'};
					}
					if( /^[0-9A-z-_~!@#$%^&*()_+`\[\]\;\:\"\<\>]{6,20}$/.test(str))
					{
						str = str;
					}
					else{
						str = this.error = {level:'error' ,text:errorPrompt};
					}
					return str;
				}
				else if(name == 'commonSelect'){
					if(str != -1){
						str=str;
					}else{
						str = this.error = {level:'error' ,text:'请选择'+title};
					}
					return str;
				}else if ( name == 'callback' ){
					var res = param.callback( $(obj).val() );
					if( !res || res == true || res.result == true) {}
					else {
						str = { text : res.message , level : "error"} ;
					}
					return str;
				}
				/*else{
					if(str != "" && str != null){
						str=str;
					}else{
						str = this.error = {level:'error' ,text:'请选择'+title};
					}
					return str;
				}*/
			}
		}
	};

	_callbackValid = function( name , self , param ) {
		 var valid = $.validator( name ,self , param);
		 if(valid != self.val()){
			  if(valid['level'] == 'error'){
				  $.addStyle(self);
				  $.init(self,valid['text']);
			  }
		  }else{
			  $.removeStyle(self);
		  }
	};

	//必填项验证（itemName:当前方法名，self:当前控件对象）
	_requiredVaild = function(itemname,self){
		  if(self.attr("required")){
			  var valid = $.validator(itemname,self);
			  if(valid != self.val()){
				  if(valid['level'] == 'error'){
					  $.addStyle(self);
					  $.init(self,valid['text']);
				  }
			  }else{
				  $.removeStyle(self);
			  }
		  }
	};

	//可填项验证（itemName:当前方法名，self:当前控件对象）
	_commonValid = function(itemname,self){
		  if(self.attr("required")){
			  var valid = $.validator(itemname,self);
			  if(valid != self.val()){
				  if(valid['level'] == 'error'){
					  $.addStyle(self);
					  $.init(self,valid['text']);
				  }
			  }else{
				  $.removeStyle(self);
			  }
		  }else{
			  if(self.val() == ""){
				  $.removeStyle(self);
			  }
			  if(self.val() !="" ){
				  var valid = $.validator(itemname,self);
				  if(valid != self.val()){
					  if(valid['level'] == 'error'){
						  $.addStyle(self);
						  $.init(self,valid['text']);
					  }
				  }else{
					  $.removeStyle(self);
				  }
			  }
		  }
	};

	//提交时验证（form:当前form对象）
	submitValid = function(form){
		  var flag = true ;
		  $.each($(":input[type!='checkbox'][type!='radio'][type!='button'][type!='submit'][type!='reset']" , form ) , function(){
			  if(flag==false) return;
			  var self = $(this) ;
			  var selftype = self.attr("type");
			  var selfvalid = self.attr("valid");
			  var selfid = self.attr("id");
			  if(selfvalid == undefined ){
				  if(_checkSelfid(self)==true) selfvalid = selfid;
				  else selfvalid =  _checkSelfid(self);//
			  }
			  var valid = $.validator(selfvalid,self);
			  if(self.attr("required")){
				 if(valid != undefined ){
					  if(valid != self.val()){
						  if(valid['level'] == 'error'){
							  $.addStyle(self);
							  $.init(self,valid['text']);
							  flag =false;
							  return;
						  }
					  }else{
						  $.removeStyle(self);
						  flag =true;
					  }
				 }
			  }else{
				  if(self.val() == ""){
					  $.removeStyle(self);
					  flag =true;
				  }
				  if(self.val() !="" ){
					  if(valid != undefined){
						  if(valid != self.val()){
							  if(valid['level'] == 'error'){
								  $.addStyle(self);
								  $.init(self,valid['text']);
								  flag =false;
								  return;
							  }
						  }else{
							  $.removeStyle(self);
							  flag =true;
						  }
					 }
				  }
			  }
		  }) ;
		  return flag;
	};
	//查看是否定义了名为控件id的验证器（self:当前控件对象）返回true:表示存在false:不存在
	_checkSelfid = function(self){
		var flag = false;
		for(i in WIDGETREG){
			if(i == self.attr('id')){
				flag = true;
				break;
			}
		}
		//如果不存在，进行分析如果type为textarea则返回验证器‘textarea’
		if(!flag){
			if(self.attr('type') == 'textarea'){
				return 'textarea';
			}
			else{
				return 'textbox';
			}
		}
		return flag;
	};
	//获得表单值（form:当前form对象）
	_getData = function(form){
		  var data = "{";
		  $.each($(":input[type!='button'][type!='submit'][type!='reset']" , form ) , function(){
			  var self = $(this) ;
			  var selfid = self.attr("id");
			  var selfval = (self.val() != null && self.val() != '')? self.val() : null;
			  data += selfid+":"+selfval+",";
		  });
		  data = data.substring(0,data.length-1);
		  data = data + "}";
		  return data;
	};

	//获得当前控件前提示（self：当前控件对象）
	_getLabelInfo = function(self){
		var _title = self.parent().prev().find('label').html();
		if(_title == null)
			_title =  self.parent().prev().html();
		return _title.replace('\：','').replace('\*','');
	};

	//添加错误提示信息样式（隐藏正确样式）（obj：当前控件对象）
	$.addStyle = function(obj){
		obj.removeClass('right-style');
	//	$('#tooltips-box').hide();
		tipShow();
		obj.addClass("error-style");
	};

	//添加正确时样式（隐藏错误提示样式）（obj：当前控件对象）
	$.removeStyle = function(obj){
		obj.removeClass('error-style');
		obj.remove('error-style');
//		obj.removeClass('prompt-info');
		tipHidden();
		obj.addClass('right-style');
	};

	$.tipHidden = function(){
		$('.tooltips-howpanel').hide();
		$('.tooltips-howpanel-top').hide();
		$('#tooltips_iframe').css({width:'0px',height:'0px'});
	 };
})(jQuery);
$(function(){
	$(".formBody").find("input[type!='radio'][type!='checkbox'][type!='button'][type!='submit'][type!='reset']").each(function(){
		$(this).addClass("form_item_input");
	});
});