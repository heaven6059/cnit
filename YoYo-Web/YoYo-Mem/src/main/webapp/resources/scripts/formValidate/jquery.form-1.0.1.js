/*! jQuery form plug-in 1.0.1
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
 * 	 All of the form item's define
 * 	 Involve hidden 、textBox、radio、checkBox、textArea、select and so on
 *   I will define their's style and validate
 *   And for example the textBox have many items user Name,company Name,telephone,Address and so on
 */

(function($) {
	jQuery.fn.extend({
		  textbox : function(validname){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				if(validname == undefined) validname = 'invalidchar';
				  _commonValid(validname,self);
			  });
		  },
		  invalidchar : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('invalidchar',self);
			  });
		  },
		  username : function(){
			  var message = "请正确输入用户名.";
			  var self = this;
			  $.init(self,message);
			  this.blur(function(){
				  _requiredVaild('username',self);
			  });
		  },
		  password : function(){
			  var message = "请输入密码 注意：格式必须为6-20个非特殊字符。 ";
			  var self = this;
			  $.init(self,message);
			  this.blur(function(){
				  _requiredVaild('password',self);
			  });
		  },
		  confirmpwd : function(firstpwd){
			  var message = "请输入确认密码  注意：格式必须为6-20个非特殊字符。";
			  var self = this;
			  $.init(self,message);
			  this.blur(function(){
				  _requiredVaild('confirmpwd',self,firstpwd);
			  });
		  },
		  number : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('number',self);
			  }).keyup(function(){
				  var val = self.val();
				  this.value = val.replace(/\s/g,'').replace(/\D/g,'');
			  });
		  },
		  textarea : function() {
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('textarea',self);
			  });
		  },
		  chineseName : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title+"注意：只能为汉字";
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('chineseName',self);
			  });
		  },
		  realName : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title+"注意：只能为汉字";
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('realName',self);
			  });
		  },
		  address : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title+",注意：只能以汉字开头,中间可以包含字母和数字。）";
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('address',self);
			  });
		  },
		  qq : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('qq',self);
			  }).keyup(function(){
				  var val = self.val();
				  this.value = val.replace(/\s/g,'').replace(/\D/g,'');
			  });
		  },
		  email : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('email',self);
			  });
		  },
		  phone : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('phone',self);
			  });
		  },
		  mobile : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('mobile',self);
			  });
		  },
		  domain : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title+",例如：www.3jia5.cn）";
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('domain',self);
			  });
		  },
		  ip : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title+",例如：192.168.1.1）";
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('ip',self);
			  });
		  },
		  zipcode : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('zipcode',self);
			  }).keyup(function(){
				  var val = self.val();
				  this.value = val.replace(/\s/g,'').replace(/\D/g,'');
			  });
		  },
		  idcard : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('idcard',self);
			  });
		  },
		  money : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('money',self);
			  });
		  },
		  authcode : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.blur(function(){
				  _commonValid('authcode',self);
			  });
		  },
		  datetime : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请输入"+title;
			  $.init(self,message);
			  this.click(function(){
				  MyCalendar.SetDate(this);
			  }).blur(function(){
				  _commonValid('datetime',self);
			  });
		  },
		  provinceAndCity : function(){
			  var self = this;
			  var province = self.attr("id");
			  var city = self.next().attr("id");
			  hw_init(province,city,"${province}$city");
			  this.click(function(){
				  hw_select(province,city);
			  });
		  },
		  commonSelect : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请选择"+title;
			  $.init(self,message);
			  var selfid = self.attr("id");
			  this.blur(function(){
				  _commonValid('commonSelect',self);
			  });
		  },
		  commonRadio : function(){
			  var self = this;
			  var title = _getLabelInfo(self);
			  var message = "请选择"+title;
			  $.init(self,message);
			  var selfid = self.attr("id");
			  this.click(function(){
			  });
		  },
		  callbackValid : function(options){
		  	var self = this ;
		  	this.blur(function(){
			  	_callbackValid("callback" , self , options ) ;

		  	});
		  },
		  //URL
		  urlname : function(){
			  var message = "请正确输入url地址。";
			  var self = this;
			  $.init(self,message);
			  this.blur(function(){
				  _requiredVaild('urlname',self);
			  });
		  },
		  numberRange : function( min , max ) {
			this.callbackValid({callback:function(val){
				if( val < min || val > max ) {
					return { message : "数字必须大于" + min + "小于" + max } ;
				}
				return true ;
			}});
		  },
		  //Button form 提交按钮
		  htmlform : function(formid){
				var obj = this;
				var form = obj.parents("form") ;
				this.click(function(){
					 var flag = submitValid(form);
					 if(flag) form.submit();
				});
		  },
		  //Button form Ajax提交按钮
		  ajaxform : function(){
			  var obj = this;
			  var form = obj.parents("form") ;
			  this.click(function(){
    			var flag = submitValid(form);
				var data =_getData(form);// $.extend({},this.options.data) ;
				if(flag){
					var url = form.attr( "action" ) ;
					var method =  form.attr( "method" ) ;
					$.ajax({
							url : url ,
							async : true ,
							type : method,
							data : data ,
							success :  function(){alert("success");} ,
							error :  function(){alert("error");}
					});
				}
			  });
		  }
	});
})(jQuery);