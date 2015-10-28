var _path = "${path}";
var patterns = {
	mobile : /^1[3|4|5|8][0-9]{9}$/,
	userName : /^[a-zA-Z]{1}[a-zA-Z0-9_]?$/,
	email : /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
};

function paramObj(param){
	var obj = {};
	var arr = param.split('&');
	for ( var i = 0; i < arr.length; i++) {
		var item = arr[i];
		//"2015-07-01+19%3A54%3A56"
		var _arr = item.split('=');
		if(_arr[1]){
			if(_arr[0].indexOf('Date')){
				_arr[1] = decodeURIComponent(_arr[1].replace('+',' '));
			}
			obj[_arr[0]] = _arr[1];
		}
	}
	return obj;
}

/**
 * 方法描述：layer消息提示 作者：李明 创建时间：2015-2-12
 * 
 * @param $obj
 * @param msg
 */
function renderMsg(obj, msg) {
	var $obj = $('#' + obj);
	if ($obj) {
		layer.tips(msg, $obj, {
			guide : 2,
			time : 3000,
			style : ['background-color:#c00; color:#fff', '#c00'],
			maxWidth : 240
		});
	}
}

/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
	/*
	 * eg:format="YYYY-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1, // month
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
/**
 * 字符串格式化;
 * @author jpzhou
 */
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
Array.prototype.arryIndexOf = function(Object) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i] == Object) {
			return i;
		}
	}
	return -1;
};

function compareDate(begin, end) {
	// 将字符串转换为日期
	var begin = new Date(begin.replace(/-/g, "/"));
	var end = new Date(end.replace(/-/g, "/"));
	// js判断日期
	if ((begin - end) > 0) {
		return false;
	}
	return true;
}

/**
 * 方法描述：easyui消息提示 作者：李明 创建时间：2015-2-12
 * 
 * @param $obj
 * @param msg
 */
function easyuiMsg(_title, _msg, callback) {
	$.messager.show({
		title : _title,
		msg : _msg ? _msg : ''
	});
	if (callback) {
		callback();
	}
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
		$.ajax({
			type : 'POST',
			url : url,
			data : $obj.serializeArray()
		}).progress(function() {}).done(function(data) {
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
}
/**
 * 方法描述：数据提交 作者：李明 创建时间：2015-3-2
 */
function commonAjax(url, data, sucCallback, failCallback) {
	$.ajax({
		type : 'POST',
		url : url,
		data : data
	}).done(function(data) {
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
 * 方法描述：初始化虚拟分类下拉树
 * 
 * @param obj
 * @param multiple
 */
function initVirtCateTree(obj, multiple) {
	var $obj = $('#' + obj);
	$obj.combotree({
		url : _path + '/comboBox/virtCateTreeCombox',
		multiple : multiple,
		queryParams : {
			'parentCatId' : 0
		},
		columns : [[{
			field : 'catId',
			title : '分类Id',
			fixed : true
		}, {
			field : 'catName',
			title : '分类名称',
			fixed : true
		}]],
		fitColumns : true,
		loadFilter : function(data) {
			for ( var i = 0; i < data.length; i++) {
				if (data[i].childCount > 0) {
					data[i].state = 'closed';
				} else {
					data[i].state = '';
				}
				data[i].id = data[i].catId;
				data[i].text = data[i].catName;
			}
			return data;
		},
		onBeforeExpand : function(row) {
			var url = _path + '/comboBox/virtCateTreeCombox?parentCatId=' + row.catId;
			$obj.combotree("tree").tree("options").url = url;
			return true;
		},
		onLoadSuccess : function(node, data) {
			$obj.combotree('tree').tree("expandAll");
		}
	});
}
/**
 * 方法描述：初始化分类下拉树
 * 
 * @param obj
 * @param multiple
 */
function initRealCateTree(obj, multiple, initParams) {
	var $obj = $('#' + obj);
	$obj.combotree({
		url : _path + '/comboBox/cateTreeCombox',
		multiple : multiple,
		queryParams : initParams || {
			'parentCatId' : 0
		},
		columns : [[{
			field : 'catId',
			title : '分类Id',
			fixed : true
		}, {
			field : 'catName',
			title : '分类名称',
			fixed : true
		}]],
		fitColumns : true,
		loadFilter : function(data) {
			for ( var i = 0; i < data.length; i++) {
				if (data[i].childCount > 0) {
					data[i].state = 'closed';
				} else {
					data[i].state = '';
				}
				data[i].id = data[i].catId;
				data[i].text = data[i].catName;
			}
			return data;
		},
		onBeforeExpand : function(row) {
			var url = _path + '/comboBox/cateTreeCombox?parentCatId=' + row.catId;
			$obj.combotree("tree").tree("options").url = url;
			return true;
		},
		onLoadSuccess : function(node, data) {
			$obj.combotree('tree').tree("expandAll");
		}
	});
}

/**
 * 方法描述：初始化权限下拉树
 * 
 * @param obj
 * @param multiple
 */
/*
 * function initResourceCateTree(obj, multiple) { var $obj = $('#' + obj);
 * $obj.combotree({ url : _path + '/resourceManager/resourceTreeCombox' ,
 * multiple : multiple , queryParams : { 'parentId' : 0 } , columns : [ [ {
 * field : 'id' , title : '权限Id' , fixed : true } , { field : 'resourceName' ,
 * title : '权限名称' , fixed : true } ] ] , fitColumns : true , loadFilter :
 * function(data) { for (var i = 0; i < data.length; i++) { if (data[ i
 * ].childCount > 0) { data[ i ].state = 'closed'; } else { data[ i ].state =
 * ''; } data[ i ].id = data[ i ].id; data[ i ].text = data[ i ].resourceName; }
 * alert("data="+data); return data; } , onBeforeExpand : function(row) { var
 * url = _path + '/resourceManager/resourceTreeCombox?parentId=' + row.id;
 * $obj.combotree("tree").tree("options").url = url; return true; } ,
 * onLoadSuccess : function(node, data) {
 * $obj.combotree('tree').tree("expandAll"); } }); }
 */

/**
 * 方法描述：初始化规格下拉列表
 * 
 * @param obj
 */
function initSpecGrid(obj) {
	var $obj = $('#' + obj);
	if ($obj) {
		$obj.combogrid({
			url : _path + '/spec/specList',
			multiple : true,
			idField : 'specId',
			textField : 'specName',
			columns : [[{
				field : 'specName',
				title : '规格',
				width : '40%'
			}, {
				field : 'specAlias',
				title : '别名',
				width : '40%'
			}, ]],
			loadFilter : function(data) {
				return data.content;
			},
			pagination : true,
			rownumbers : true
		});
	}
}
/**
 * 方法描述：初始化汽车配件类型下拉列表
 * 
 * @param obj
 */
function initAccCatalogGrid(obj) {
	var $obj = $('#' + obj);
	if ($obj) {
		$obj.combogrid({
			url : _path + '/accessory/catalogList',
			fitColumns : true,
			panelMinWidth : 200,
			panelMinHeight : 350,
			idField : 'catalogId',
			textField : 'catalogName',
			columns : [[{
				field : 'catalogId',
				title : '类型编号',
				width : '30%'
			},{
				field : 'catId',
				title : '分类ID',
				width : '30%'
			},{
				field : 'catalogName',
				title : '类型名称',
				width : '80%'
			}]],
			loadFilter : function(data) {
				return data.content;
			},
//			pagination : true,
			rownumbers : false
		});
	}
}
/**
 * 方法描述：初始化商品下拉列表
 * 
 * @param obj
 */
function initGoodsGrid(obj, multiple) {

}
/**
 * 方法描述：初始化品牌下拉列表
 * 
 * @param obj
 */
function initBrandComboxGrid(obj) {
	var $obj = $('#' + obj);
	if ($obj) {
		$obj.combogrid({
			url : _path + '/brand/brandList',
			fitColumns : true,
			panelMinWidth : 300,
			panelMinHeight : 400,
			idField : 'brandId',
			textField : 'brandName',
			columns : [[{
				field : 'brandId',
				title : '品牌编号',
				width : 80
			}, {
				field : 'brandName',
				title : '品牌名称',
				width : 100
			}
			/*, {
				field : 'brandLogo',
				title : '品牌Logo',
				width : 100,
				formatter : function(value, row) {
					if (value) {
						return '<img width=20 height=20 src="' + _path + '/resources/images/ff.gif"/>';
					}
				}}*/
		]],
			loadFilter : function(data) {
				return data.content;
			},
//			pagination : true,
			rownumbers : true
		});
	}
}
/**
 * 方法描述：刷新树形列表
 * 
 * @param obj
 */
function reloadTreeGrid(obj) {
	var $obj = $('#' + obj);
	if ($obj) {
		var url = $obj.treegrid("options").url;
		if (url) {
			url = url.substr(0, url.indexOf('?') + 1);
			var queryParams = $obj.treegrid("options").queryParams;
			$.each(queryParams, function(i) {
				url = url + i + "=" + queryParams[i] + "&";
			});
			$obj.treegrid("options").url = url;
			$obj.treegrid('reload');
		}
	}
}
/*
 * 命名空间
 */
var chis = $.extend({}, chis);

/*
 * 清空表单
 */
chis.clearForm = function(objE) {// objE为form表单
	$(objE).find(':input').each(function() {
		switch (this.type) {
			case 'passsword' :
			case 'select-multiple' :
			case 'select-one' :
			case 'text' :
			case 'textarea' :
				$(this).val('');
				break;
			case 'checkbox' :
			case 'radio' :
				this.checked = false;
		}
	});
};

// /////////////////////////高级查询生成html代码的方法/////////////////////////////////////////////////
/**
 * 打开高级查询
 */
function openAdvaceQuery(obj) {
	var width = $(window).width(); // 屏幕的宽度
	$('#' + obj).dialog('open').window('resize', {
		width : '380px',
		height : '500px',
		top : 0,
		left : (width - 380)
	});
	$('#' + obj).form('clear');
	chis.clearForm($('#' + obj));
}
/**
 * 高级查询(可用来生成html代码，打印在浏览器控制台，然后对下拉框，时间范围等进行修改) 该方法只用于生成高级查询html代码
 * datagrid:datagrid的id
 */

function advancedQuery(datagrid) {
	// $('#'+obj).empty(); //先清空

	var html = ' <form id="search_form" class="easyui-form" method="post" data-options="novalidate:true">';
	html += '<table style="border-collapse:separate;border-spacing:5px;"> ';
	var opts = $('#' + datagrid).datagrid('getColumnFields'); // 获取列名field
	$.each(opts, function(i, v) { // 获取列的标题title
		var title = $('#' + datagrid).datagrid('getColumnOption', v).title;
		html += '<tr>';
		html += '<td align="right"><span>' + title + ': </span></td>';
		// html += '<td><input id="'+v+'" name="'+v+'" class="easyui-textbox"
		// style="border:1px solid #EAEAEA;height: 25px;" /></tr>';
		html += '<td><input id="' + v + '" name="' + v + '" class="easyui-textbox search_class"  /></td></tr>';
	});

	html += '</table></form>';
	console.log(html);
	// $("#"+obj).append(html);
}

/**
 * 清除筛选条件
 */
function search_clear(obj,table) {
	$('#' + obj).form('clear');
	chis.clearForm($('#' + obj));
	$('#' + table).datagrid('reload', {});
}

/**
 * 立即筛选 obj:datagrid的id search_form:高级筛选的表单id
 */
function advanceQuery(obj, search_form) {
	$('#' + obj).datagrid('load', biz.serializeObject($('#' + search_form)));
}

/**
 * 关闭指定的对话框
 */
function closeDailog(obj) {
	var $this = $('#' + obj);
	$($this).window('close');
}

/**
 * 命名空间
 */
var biz = $.extend({}, biz);

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
 * 上传的图片路径
 */
biz.imagePath = function() {
	return biz.rootPath() + "/resources/upload/";
};

/**
 * 默认图片
 */
biz.defaultPic = function() {
	return biz.rootPath() + "/resources/images/pre_default.png";
};

/**
 * 上传图片 file:type=file的input的id imgPath: 上传成功后返回的图片路径，保存到imgPath的input中
 * preImg:预览框的id 2015.03.30
 * cb:包含回调函数的对象。如{success:function(data){..},error:function(data){..}}
 */
function submitForm(file, imgPath, preImg, cb) {
	var filev = document.getElementById(file);
	var patn = /\.jpg$|\.png$|\.jpeg$|\.gif$|\.JPG$|\.PNG$|\.JPEG$|\.GIF$/;
	if (filev.value != null && patn.test(filev.value)) {
		parent.$.messager.progress({
			title : '提示',
			text : '正在上传....'
		});
		$.ajaxFileUpload({
			url : biz.rootPath() + '/image/uploadImg',
			secureuri : false,
			fileElementId : file,
			dataType : 'json',
			success : function(data, status) {
				parent.$.messager.progress('close');
				if (data.retCode == "000000") {
					easyuiMsg('提示', '上传成功！');

					var img = document.getElementById(imgPath);
					var result = data.retMsg.split(";"); //包含id与路径
					img.value=result[1];
				} else if (data.retCode == "000003") {
					parent.$.messager.alert('提示', '上传失败！' + data.retMsg, 'error');
					var pre_img = document.getElementById(preImg);
					pre_img.src = biz.rootPath() + "/resources/images/pre_default.png";
				} else {
					parent.$.messager.alert('提示', '上传失败！'+(data.retMsg||''), 'error');
				}
				var callback = cb||{};
				typeof callback.success == "function"?callback.success(data,status):null;
			},
			error : function(data, status, e) {
				parent.$.messager.progress('close');
				var callback = cb||{};
				typeof callback.error == "function"?callback.error(data,status,e):null;
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

// 分类对话框的实现
var select_data = "[]";
/**
 * 分类对话框的实现 parentSelect : 上级分类的select元素的id childSelect ：二级分类的select元素的id
 * showSelect ：显示选择的分类的select元素的id
 * 
 * @author xiaox
 */
function categorySelect(parentSelect, childSelect, showSelect) {
	var $parent = $("#" + parentSelect); // 上级分类
	var $child = $("#" + childSelect); // 二级分类
	var $show = $("#" + showSelect); // 显示选择的分类
	$parent.on("select2:select", function(e) {
		// TODO 25：是整车的id，整车类型没有下级分类，直接关联品牌
		// if (e.params.data.id == 25) {// 整车
		// select_data = '[{ text: "' + e.params.data.text + '", id:' +
		// e.params.data.id + ',selected:true}]';
		// $show.empty();
		// $show.select2({ data : eval(select_data) });
		// } else {
		$child.empty();
		getSelect2Data(biz.rootPath() + '/cate/cateTree?parentCatId=' + e.params.data.id, childSelect, true);
		// }
	});

	// 选择子级分类取得该分类的id

	$child.on("select2:select", function(e) {
		// 先判断select_data中是否已经存在该值
		if (e.params.data.id != '-1' && select_data.indexOf('{ text: "' + e.params.data.text + '", id:' + e.params.data.id + ',selected:true}', 0) < 0) {
			select_data = select_data.substring(0, select_data.length - 1);
			select_data = select_data.length > 1 ? select_data + "," : select_data; // 除开没有数据的情况
			select_data += '{ text: "' + e.params.data.text + '", id:' + e.params.data.id + ',selected:true}';
			select_data += "]";
		}
		$show.empty();
		$show.select2({
			data : eval(select_data)
		});
	});

	// 取消子级分类
	$show.on("select2:unselect", function(e) {
		var data = eval(select_data);
		$.each(data, function(i, n) {
			if (n.id == e.params.data.id) {
				data.splice(i, 1);
			}
		});
		select_data = JSON.stringify(data);
		$show.empty();
		$show.select2({
			data : eval(select_data)
		});
	});

}

// 初始化分类信息 pid:父级id
function initCategory(parentSelect, childSelect, showSelect, pid) {
	select_data = "[]";
	$("#" + showSelect).select2({
		data : eval(select_data)
	});
	$("#" + childSelect).select2();
	getSelect2Data(biz.rootPath() + '/cate/cateTree?parentCatId=' + pid, parentSelect, true);
}

var car_info_utils={
	initBrand:function(sucCallback,failCallback){
		$.ajax({
			type : 'POST',
			url :_path + '/comboBox/findCarBrand',
			data : {identifier:yoyo.car}
		}).done(function(data) {
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
	},
	initDept:function (sucCallback,failCallback,brandId){
		$.ajax({
			type : 'POST',
			url : _path + '/comboBox/findCarDept',
			data : {brandId:brandId}
		}).done(function(data) {
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
	},
	initCar:function (sucCallback,failCallback,deptId){
		$.ajax({
			type : 'POST',
			url : _path + '/comboBox/findCar',
			data : {deptId:deptId}
		}).done(function(data) {
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
};
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
				special:{  //特殊字符
					validator : function(value){
						var pattern = new RegExp("[`~@#$^&*={}':;',\\[\\].<>?~！@#￥……&*（）—{}【】‘；：”“'。，、？]");
						return ! pattern.test(value);
					} ,
					message : "不能设置为特殊字符."
				 
				},
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
		var msg = $.data(jq, "validatebox").message;
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
