var isEdit = 0;
$(function() {
	initAccCatalogDataGrid();
	$('#accessory-ads-button').advanceSeacher({aimDataGrid : 'table-accessory', renderTo : 'table-acc-advance-searcher', exceptField : ['editor', 'isRelatedCar']});
	initAccCatalogGrid('select-accessory-catalog');
	var catId = 0;
	$('#select-accessory-catalog').combogrid('options').onSelect = function(index, row) {
		catId = row.catId;
		if(isEdit == 0){
			initBrandByCatId('select-accessory-brand',catId);
		}
		
		getAccessoryParamValue();
		return true;
	};
	//initBrandByCatId('select-accessory-brand',0);
	initBrandComboxGrid('select-accessory-brand');

//	getCarFactoryData(biz.rootPath() + '/carFactory/findSelect', 'select-factory', true, null);
//	$("#select-dept").select2({placeholder : "--请选择--"});
//	$("#select-year").select2({placeholder : "--请选择--"});
//	$("#select-car").select2({placeholder : "--请选择--"});

	// 汽车厂商改变
	$("#select-factory").on("select2:select", function(e) {
		// 清空后面下拉框的数
//		$("#select-dept").empty();;
//		$("#select-dept").val('--请选择--').trigger("change");
//		getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#select-factory").val(), 'select-dept', true, null);// 获取车系下拉列表数据
		
		// 清空后面下拉框的数
		$("#select-dept").empty();
		$("#select-dept").select2({placeholder : "--请选择--"});
		
		$("#select-year").empty();
		$("#select-year").select2({placeholder : "--请选择--"});
		
		$("#select-car").empty();
		$("#select-car").select2({placeholder : "--请选择--"});
		if($("#select-factory").val()!='-1'){
			getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#select-factory").val(), 'select-dept', true, null);// 获取车系下拉列表数据
		}
		
	});

	// 汽车车系改变
	$("#select-dept").on("select2:select", function(e) {
		// 清空后面下拉框的数
//		$("#select-year").empty();;
//		$("#select-year").val('--请选择--').trigger("change");
//		getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + $("#select-dept").val(), 'select-year', true, null);// 获取年款下拉列表数据
		
		// 清空后面下拉框的数
		$("#select-year").empty();
		$("#select-year").select2({placeholder : "--请选择--"});
		
		$("#select-car").empty();
		$("#select-car").select2({placeholder : "--请选择--"});
		if($("#select-dept").val()!='-1'){
			getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + $("#select-dept").val(), 'select-year', true, null);// 获取年款下拉列表数据
		}
		
	});

	// 汽车年款改变
	$("#select-year").on("select2:select", function(e) {
		// 清空后面下拉框的数
//		$("#select-car").empty();;
//		$("#select-car").val('--请选择--').trigger("change");
//		getCarData(biz.rootPath() + '/car/carList?carYearId=' + $("#select-year").val(), 'select-car', true, null);// 获取车型下拉列表数据
		
		// 清空后面下拉框的数
		$("#select-car").empty();
		$("#select-car").select2({placeholder : "--请选择--"});
		
		if($("#select-year").val()!='-1'){
			getCarData(biz.rootPath() + '/car/carList?carYearId=' + $("#select-year").val(), 'select-car', true, null);// 获取车型下拉列表数据
		}
		
	});
});

/**
 * 方法描述：初始化品牌下拉列表
 * 
 * @param obj
 */
function initBrandByCatId(obj,catId) {
	var $obj = $('#' + obj);
	if ($obj) {
		$obj.combogrid({
			url : _path + '/brand/findBrandByCatId?catId='+catId,
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
		]],
			loadFilter : function(data) {
				return data.content;
			},
			rownumbers : true
		});
	}
}

function genGoodsDetailContent (row) {
	var html="适配车型：<br/>";
	$.ajax({  
        url : _path + '/accessory/getAccessoryDetail?',  
        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
        type : "POST",  
        dataType : "json",
        data: {'accId' : row.accId},
        success : function(data) {  
        	if (data.head.retCode == '000000') {
        		if(data.content.accessory.cars.length>0){
        			html+="<table  style='width:100%;height:100%;'>";
            		html+='<thead><tr><th width="80px">厂商</th><th width="80px">车系</th><th width="30px">年款</th><th width="100px">车型</th></tr></thead><tbody> ';
            		$.each(data.content.accessory.cars, function(index, item) {
        				html+='<tr><td>' + item.factoryName + '</td><td>' + item.deptName + '</td><td>' + item.yearValue + '</td><td>' + item.carName + '</td></tr>';
        			})
        			html+='</tbody></table>';
        		}else{
        			html="适用所有车型";
        		}
    			
    		}
        }  
    }); 
	return $('<div />').css({padding : '1px'}).append(html);
};
// 添加车型到表格
function addAccAndCarShip() {
	var car = $('#select-car').select2('data')[0];
	var html = [];
	var factory = $('#select-factory').select2('data')[0];
	var dept = $('#select-dept').select2('data')[0];
	if(car == null || car == '') {
		$('#table-car-index tbody').html("");
		$.post(_path + '/car/getCarByDeptId', {'deptId' : dept.id}, function(data) {
			if (data.head.retCode == '000000') {
				$.each(data.content, function(index, item) {
					html.push('<tr id="tr_car_' + item.carId + '"><td><span value=' + factory.id + ' style="display:block; width: 130px;padding: 3px 5px;">' + 
							factory.text + '</span></td><td><span value=' + dept.id + ' style="display:block; width: 130px;padding: 3px 5px;">' + dept.text + 
							'</span></td><td><span value=' + item.filterId + ' style="display:block; width: 130px;padding: 3px 5px;">' + item.carYearValue + 
							'</span></td><td><span value=' + item.carId + ' style="display:block; width: 130px;padding: 3px 5px;">' + item.carName + 
							'</span></td><td><a href="#" onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode)">删除</a></td></tr>');
				});
				$('#table-car-index tbody').append(html);
			}
		});
	}else {
		if (car.id != -1) {
			if ($('#tr_car_' + car.id).length > 0) {
				easyuiMsg('提示', '该车型已经选择，请选择其他车型！');
				return;
			}
			
			var year = $('#select-year').select2('data')[0];
			var deleteShip = function(carId) {
				$('#tr_car_' + car.id).remove();
			}
			
			html.push('<tr id="tr_car_' + car.id + '">');
			html.push('<td><span value=' + factory.id + ' style="display:block; width: 130px;padding: 3px 5px;">' + factory.text + '</span></td>');
			html.push('<td><span value=' + dept.id + ' style="display:block; width: 130px;padding: 3px 5px;">' + dept.text + '</span></td>');
			html.push('<td><span value=' + year.id + ' style="display:block; width: 130px;padding: 3px 5px;">' + year.text + '</span></td>');
			html.push('<td><span value=' + car.id + ' style="display:block; width: 130px;padding: 3px 5px;">' + car.text + '</span></td>');
			html.push('<td><a href="#" onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode)">删除</a></td>');
			html.push('</tr>');
			$('#table-car-index tbody').append($(html.join('')));
		}
	}
	
}
// 初始化配件列表
function initAccCatalogDataGrid() {
	$('#table-accessory').datagrid(
			{
				url : _path + '/accessory/accessoryList',
				pageSize: 25,
			    pageList: [25,50,100,150],
			    height:750,
				contentType : 'application/x-www-form-urlencoded',
				columns : [[
			            {field : 'accId', title : '配件编号', hidden : true,width : 100}, 
			            {field : 'accName', title : '配件名称', width : 200, halign:'center', sortable : true, options : {querytype : 'like'}},
			            {field : 'accCode', title : '配件编码', width : 100, halign:'center', sortable : true, options : {querytype : 'like'}}, 
			            {field : 'accSpec', title : '规格', width : 100, halign:'center', options : {querytype : 'like'}}, 
			            {field : 'accOem', title : 'OEM号', width : 100, halign:'center', sortable : true, options : {querytype : 'like'}},
						{field : 'accUnit', title : '计量单位', width : 100, halign:'center', halign:'center',options : {querytype : 'like'}}, 
						{field : 'accMainPlants', title : '主机厂', halign:'center', width : 100, options : {querytype : 'like'}},
						{field : 'brandName', title : '品牌', width : 100, halign:'center', sortable : true, options : {querytype : 'like'}}, 
						{field : 'accPack', title : '包装', width : 100, halign:'center', options : {querytype : 'like'}}, 
						{field : 'isRelatedCar', title : '是否已绑定车型', halign:'center', width : 100, formatter : function(value) {
							if (value == '1') {
								return '是';
							} else {
								return '否';
							}
						}}, {field : 'editor', title : '操作', align:'center',width : 200, formatter : function(value, row) {
							return '<a class="btn-grey" href="javascript:editAcc(' + row.accId + ')">编辑</a><a class="btn-grey" href="javascript:deleteAcc(' + row.accId + ')">删除</a>';
						}}]], 	onExpandRow : function(index, row) {
							var ddv = $(this).datagrid('getRowDetail', index).find('div.dropdown-good-detail');
							ddv.panel({
//								height : 500,
								border : true,
								cache : false,
								content : genGoodsDetailContent(row),
								onLoad : function() {
									$('#table-accessory').datagrid('fixDetailRowHeight', index);
								}
							});
							$('#table-accessory').datagrid('fixDetailRowHeight', index);
						},
						toolbar : '#toolbar-accessory',
						pagination : true,
						rownumbers : true, 
						singleSelect : true, 
						remoteSort : true, 
						multiSort : true,
						fitcolumns: true,
						view: detailview,
						detailFormatter : function(index, row) {
							return '<div class="dropdown-good-detail" style="padding:5px 0px;margin:5px auto;"></div>';
						},
						loadFilter : function(data) {
							if (data.rows) {
								return data;
							} else {
								return data.content;
							}
						}
				});
		$('#table-accessory').datagrid({
			width : ($(window).width() * 0.99),
			height : ($(window).height() * 0.95)
		});
}
// 打开配件新增窗口
function newAccessory() {
	$('#window-accessory').window('setTitle', '添加配件');
	$('#window-accessory').window('open');
	$("#select-dept").empty();
	$("#select-year").empty();
	$("#select-car").empty();
	
	getCarFactoryData(biz.rootPath() + '/carFactory/findSelect', 'select-factory', true, null);
	$("#select-dept").select2({placeholder : "--请选择--"});
	$("#select-year").select2({placeholder : "--请选择--"});
	$("#select-car").select2({placeholder : "--请选择--"});
	$("#select-factory").val('--请选择--').trigger("change");
}
var _accId = null;
// 打开配件编辑窗口
function editAcc(accId) {
	_accId = accId;
	$('#window-accessory').window('setTitle', '编辑配件');
	$('#window-accessory').window('open');
	$("#select-dept").empty();
	$("#select-year").empty();
	$("#select-car").empty();
	
	getCarFactoryData(biz.rootPath() + '/carFactory/findSelect', 'select-factory', true, null);
	$("#select-dept").select2({placeholder : "--请选择--"});
	$("#select-year").select2({placeholder : "--请选择--"});
	$("#select-car").select2({placeholder : "--请选择--"});
	$("#select-factory").val('--请选择--').trigger("change");
	
}
// 打开配件详细信息窗口
function getAccDetail() {
	if (_accId) {
		$.post(_path + '/accessory/getAccessoryDetail', {'accId' : _accId}, function(data) {
			if (data.head.retCode == '000000') {
				isEdit = 1;
				$('#form-accessory').form('load', data.content.accessory);
				genTableCarIndex(data.content.accessory.cars);
				genFormElement(data.content.params);
				isEdit = 0;
			}
		});
	}
}
// 将查找出的绑定车型显示在车型列表
function genTableCarIndex(data) {
	var html = [];
	$.each(data, function(index, item) {
		html.push('<tr id="tr_car_' + item.carId + '">');
		html.push('<td><span value=' + item.factoryId + ' style="display:block; width: 130px;padding: 3px 5px;">' + item.factoryName + '</span></td>')
		html.push('<td><span value=' + item.deptId + ' style="display:block; width: 130px;padding: 3px 5px;">' + item.deptName + '</span></td>')
		html.push('<td><span value=' + item.yearId + ' style="display:block; width: 130px;padding: 3px 5px;">' + item.yearValue + '</span></td>')
		html.push('<td><span value=' + item.carId + ' style="display:block; width: 130px;padding: 3px 5px;">' + item.carName + '</span></td>')
		html.push('<td><a href="#" onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode)">删除</a></td>')
		html.push('</tr>');
	})
	$('#table-car-index tbody').append($(html.join('')));
}
// 清空配件详情
function clearAccDetail() {
	$('#form-accessory').form('clear');
	$('#table-accessory-params').datagrid({data : []});
	$('#table-car-index tbody tr').remove();
	$('#tabs-accessory-edit').tabs('update', {tab : $('#tabs-accessory-edit').tabs('getTab', '配件参数信息'), options : {content : '未选择配件类型或该配件类型下未配置配件参数'}});
	_accId = null;
}
// 保存配件信息
function saveAccessory() {
	if ($('#form-accessory').walidator('isValidated').attr('isValidated') == 'true' && $('#form-acc-params').walidator('isValidated').attr('isValidated') == 'true') {
		var params = {};
		// 基本信息
		var accessory = biz.serializeObject($('#form-accessory'));
		accessory.brandName = $('#select-accessory-brand').combogrid('getText');
		params.accessory = JSON.stringify(accessory);
		// 扩展属性
		var values = [];
		$.each($("#form-acc-params").serializeArray(), function(index, obj) {
			var $formEle = $('#form-acc-params').find('[name="' + obj.name + '"]');
			var name = $formEle.attr('name');
			values.push({'paramId' : parseInt(name.substr(name.lastIndexOf('_') + 1)), 'dataType' : $formEle.attr('datatype'), 'value' : $formEle.val()});
		});
		params.params = JSON.stringify(values);

		var cars = [];
		$.each($('#table-car-index tbody tr'), function(index, element) {
			var $element = $(element);
			cars.push({factoryId : $element.find('span:eq(0)').attr('value'), factoryName : $element.find('span:eq(0)').text(), deptId : $element.find('span:eq(1)').attr('value'), deptName : $element.find('span:eq(1)').text(), yearId : $element.find('span:eq(2)').attr('value'), yearValue : $element.find('span:eq(2)').text(), carId : $element.find('span:eq(3)').attr('value'), carName : $element.find('span:eq(3)').text()});
		});
		params.cars = JSON.stringify(cars);
		commonAjax(_path + '/accessory/saveAccessory', params, function(data) {
			$('#table-accessory').datagrid('reload');
			$('#window-accessory').window('close');
		}, function(data) {
			if(data.head.retCode == '000004'){
				easyuiMsg('错误',"保存失败，配件名称已经存在！");
			}else
			easyuiMsg('错误', data.head.retMsg);
		});
	}
}
// 删除配件信息
function deleteAcc(accId) {
	if (accId) {
		$.messager.confirm('', '确定删除？', function(r) {
			if (r) {
				commonAjax(_path + '/accessory/deleteAccessory', {'accId' : accId}, function(data) {
					$('#table-accessory').datagrid('reload');
					$('#window-accessory').window('close');
				}, function(data) {
					easyuiMsg('错误', data.head.retMsg);
				});
			}
		})
	}
}
// 获得配件参数
function getAccessoryParamValue() {
	var accId = $('input[name="accId"]').val();
	var catId = $('input[name="catId"]').val();
	$('#form-acc-params').empty();
	if (catId) {
		var param = {};
		param.catalogId = catId;
		param.accId = accId;
		commonAjax(_path + '/accessory/getAccParams', param, function(data) {
			genFormElement(data.content);
		}, function(data) {
			easyuiMsg('错误', data.head.retMsg)
		});
	}
}
// 将获得的配件参数装饰成表单
function genFormElement(data) {
	var rootEle = $('<table />', {class : 'table-form'});
	var trEle = null;
	$.each(data, function(index, param) {
		var _label = $('<td />', {class : 'table-form-label'}).append($('<label />').text(param.paramName + '：'));
		var _input = undefined;
		if (param.dataType == 'STR' && !param.paramValues) {
			_input = $('<input />', {name : 'name_' + param.paramId, class : 'walidator', type : 'text', value : param.strValue, dataType : param.dataType}).attr('walitype', '{require:true}');
		} else if (param.dataType == 'INT' && !param.paramValues) {
			_input = $('<input />', {name : 'name_' + param.paramId, class : 'walidator', type : 'text', value : param.intValue, dataType : param.dataType}).attr('walitype', '{require:true,match:"number"}');
		} else if (param.dataType == 'DEC' && !param.paramValues) {
			_input = $('<input />', {name : 'name_' + param.paramId, class : 'walidator', type : 'text', value : param.decValue, dataType : param.dataType}).attr('walitype', '{require:true,match:"number"}');
		} else if (param.dataType == 'BOL' && !param.paramValues) {
			_input = $('<select />', {name : 'name_' + param.paramId, class : 'walidator', value : param.bolValue, dataType : param.dataType}).attr('walitype', '{require:true}');
			_input.append($('<option value=0 >否</option>')).append($('<option value=1 >是</option>'));
		} else if (param.paramValues) {
			var value = param.strValue || param.decValue || param.intValue || param.bolValue;
			_input = $('<select />', {name : 'name_' + param.paramId, class : 'walidator', value : value, dataType : param.dataType}).attr('walitype', '{require:true}');
			_input.append($('<option />').text('---请选择---'));
			$.each(param.paramValues.split('|'), function(index, item) {
				if (item == value) {
					_input.append($('<option selected = "selected"/>', {value : item}).text(item));
				} else {
					_input.append($('<option />', {value : item}).text(item));
				}

			});
		}

		if (trEle) {
			trEle.append(_label).append($('<td />').append(_input).append($('<span />').text(param.dataFormat)));
		} else {
			trEle = $('<tr />').append(_label).append($('<td />').append(_input).append($('<span />').text(param.dataFormat)));
		}
		rootEle.append(trEle);
		if ((index + 1) % 2 == 0) {
			trEle = null;
		}
	});

	if (data.length == 0) {
		rootEle.append($('<tr><td>未选择配件类型或该配件类型下未配置配件参数</td></tr>'))
	}

	$('#tabs-accessory-edit').tabs('update', {tab : $('#tabs-accessory-edit').tabs('getTab', '配件参数信息'), options : {content : $('<form />', {id : 'form-acc-params'}).attr('WalidateTime', 'realtime').append(rootEle)}});
	$('#form-acc-params').walidator();
}
// 获得汽车厂商数据
function getCarFactoryData(url, obj, isSelect, factoryId) {
	$("#select-factory").empty();
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if (n.factoryId == factoryId) {
				data += '{ text: "' + n.factoryName + '", id:' + n.factoryId + ' ,selected:true},';
			} else {
				data += '{ text: "' + n.factoryName + '", id:' + n.factoryId + '},';
			}
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({data : eval(data)});
	});
}
// 获得汽车车系数据
function getCarDeptData(url, obj, isSelect, deptId) {
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if (n.carDeptId == deptId) { // 选择指定值
				data += '{ text: "' + n.carDeptName + '", id:' + n.carDeptId + ',selected:true},';
			} else {
				data += '{ text: "' + n.carDeptName + '", id:' + n.carDeptId + '},';
			}
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({data : eval(data)});
	});
}
// 获得汽车年款数据-ssd
function getCarYearData(url, obj, isSelect, yearId) {
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if (n.id == yearId) { // 选择指定值
				data += '{ text: "' + n.carYearValue + '", id:' + n.id + ',selected:true},';
			} else {
				data += '{ text: "' + n.carYearValue + '", id:' + n.id + '},';
			}
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({data : eval(data)});
	});
}
// 获得车型数据-ssd
function getCarData(url, obj, isSelect, carId) {
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if (n.carId == carId) { // 选择指定值
				data += '{ text: "' + n.carName + '", id:' + n.carId + ',selected:true },';
			} else {
				data += '{ text: "' + n.carName + '", id:' + n.carId + '},';
			}

		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({data : eval(data)});
	});
}
