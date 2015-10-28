/**
 * 配件参数类型
 */
var editIndex = undefined;
$(function() {
	initAccCatalogDataGrid();
	$('#acc-catalog-ads-button').advanceSeacher({
		aimDataGrid : 'table-acc-catalog',
		renderTo : 'table-acc-catalog-advance-searcher',
		exceptField : ['orderNum', 'editor', 'catId']
	});
	initRealCateTree('id-good-cat', false, {
		'parentCatId' : 81
	});
	$('#form-acc-catalog').walidator();
});

function initAccCatalogDataGrid() {
	$('#table-acc-catalog').datagrid({
		url : _path + '/accessory/catalogList',
		pageSize: 25,
	    pageList: [25,50,100,150],
//	    height:750,
		columns : [[{
			field : 'catalogId',
			title : '类型编号',
			hidden : true,
			width : 100
		}, {
			field : 'catalogName',
			title : '类型名称',halign:'center',
			width : 200,
			sortable : true,
			options : {
				querytype : 'like'
			}
		}, {
			field : 'catId',
			title : '是否已绑定分类',halign:'center',
			width : 100,
			formatter : function(value, row) {
				if (value != null && value != '') {
					return '是';
				} else {
					return '否';
				}
			}
		}, {
			field : 'orderNum',halign:'center',
			title : '排序',
			width :100,
			sortable : true,
		}, {
			field : 'editor',align:'center',
			title : '操作',
			width : 200,
			formatter : function(value, row) {
				return '<a href="javascript:editAccCatalog(' + row.catalogId + ')">编辑</a><a href="javascript:deleteAccCatalog(' + row.catalogId + ')">删除</a>';
			}
		}]],
		toolbar : '#toolbar-acc-catalog',
		pagination : true,
		rownumbers : true,
		singleSelect : true,
		remoteSort : true,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	$('#table-acc-catalog').datagrid({
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95)
	});
}

function newAccCatalog() {
	$('#window-acc-catalog').window('setTitle', '添加配件类型');
	$('#window-acc-catalog').window('open');
}

var _catalogId = null;
function editAccCatalog(catalogId) {
	_catalogId = catalogId;
	$('#window-acc-catalog').window('setTitle', '编辑配件类型');
	$('#window-acc-catalog').window('open');
}

function getAccCatalogDetail() {
	if (_catalogId) {
		$.post(_path + '/accessory/accCatelogDetail', {'catalogId' : _catalogId}, function(data) {
			if (data.head.retCode == '000000') {
				$('#form-acc-catalog').form('load', data.content.catalog);
				$('#id-good-cat').combotree('setValue', data.content.catalog.catId);
				$('#table-acc-catalog-params').datagrid({
					data : data.content.params
				});
			}
		});
	}
}

function clearDetail() {
	$('#form-acc-catalog').form('clear');
	$('#table-acc-catalog-params').datagrid({
		data : []
	});
	_catalogId = null;
}

function saveAccCatalog() {
	if ($('#form-acc-catalog').walidator('isValidated').attr('isValidated') == 'true') {
		if (endEditing()) {
			var params = {};
			// 基本信息
			var catalog = yoyo.serializeObject($('#form-acc-catalog'));
			catalog.catId = $('#id-good-cat').combotree('getValue');
			params.catalog = JSON.stringify(catalog);
			// 扩展属性
			var inserted = $('#table-acc-catalog-params').datagrid('getChanges', 'inserted');
			inserted.length > 0 ? params.inserted = JSON.stringify(inserted) : null;
			var updated = $('#table-acc-catalog-params').datagrid('getChanges', 'updated');
			updated.length > 0 ? params.updated = JSON.stringify(updated) : null;
			var deleted = $('#table-acc-catalog-params').datagrid('getChanges', 'deleted');
			deleted.length > 0 ? params.deleted = JSON.stringify(deleted) : null;
			commonAjax(_path + '/accessory/saveAccCatalog', params, function(data) {
				$('#table-acc-catalog').datagrid('reload');
				$('#window-acc-catalog').window('close');
				editIndex = undefined;
			}, function(data) {
				if(data.head.retCode == '000004'){
					easyuiMsg('错误',"保存失败，配件数据项名称已经存在！");
				}else {
					easyuiMsg('错误', data.head.retMsg);
				}
			});
		} else {
			easyuiMsg('提示', '配件参数列表数据未校验通过！');
		}
	} else {
		easyuiMsg('提示', '配件类型基本信息未校验通过！');
	}
}

function deleteAccCatalog(catalogId) {
	if (catalogId) {
		$.messager.confirm('', '确定删除？', function(r) {
			if (r) {
				commonAjax(_path + '/accessory/deleteAccCatalog', {
					'catalogId' : catalogId
				}, function(data) {
					$('#table-acc-catalog').datagrid('reload');
					$('#window-acc-catalog').window('close');
				}, function(data) {
					easyuiMsg('错误', "该配置数据项已被配件使用，不可删除！");
				});
			}
		})
	}
}

function accCatalogParamsEditor(value, row, index) {
	return '<a href="javascript:editrow(' + index + ')">编辑</a><a href="javascript:deleteAccCatalogParam(' + index + ')">删除</a>'
}
function editrow(index){
	$('#table-acc-catalog-params').datagrid('selectRow', index).datagrid('beginEdit', index); 
	editIndex = index;
};
function deleteAccCatalogParam(index) {
//	$('#table-acc-catalog-params').datagrid('deleteRow', index);
//	var rows = $('#table-acc-catalog-params').datagrid("getRows");
//	$('#table-acc-catalog-params').datagrid("loadData", rows);
	var row = $('#table-acc-catalog-params').datagrid('getSelected');
	if (row) {
		var rowIndex = $('#table-acc-catalog-params').datagrid('getRowIndex', row);
		$('#table-acc-catalog-params').datagrid('deleteRow', rowIndex);
	}
	editIndex = undefined;
}
function onDblClickCell(index, field, value) {
	if (editIndex != index) {
		if (endEditing()) {
			$('#table-acc-catalog-params').datagrid('selectRow', index).datagrid('beginEdit', index);
			var ed = $('#table-acc-catalog-params').datagrid('getEditor', {
				index : index,
				field : field
			});
			($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
			editIndex = index;
		} else {
			$('#table-acc-catalog-params').datagrid('selectRow', editIndex);
		}
	}
}
function onSelect(index, row) {
	if (editIndex != index) {
		if (endEditing()) {
			return true;
		} else {
			$('#table-acc-catalog-params').datagrid('selectRow', editIndex);
			return false;
		}
	}
}
function addAccCatalogParam() {
	if (endEditing()) {
		$('#table-acc-catalog-params').datagrid('appendRow',{dataType:'字符'});
		editIndex = $('#table-acc-catalog-params').datagrid('getRows').length-1;
		$('#table-acc-catalog-params').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
}
function endEditing() {
	if (editIndex == undefined) {
		return true;
	}
	var $table = $('#table-acc-catalog-params');
	var paramName = $table.datagrid('getEditor', {
		index : editIndex,
		field : 'paramName'
	}).target;
	if (paramName.val()) {
		var rows=$('#table-acc-catalog-params').datagrid('getRows');
//		for(var i=0;i<rows.length-1;i++){
//			if(paramName.val()==rows[i].paramName){
//				return false;
//			}
//		}
		$('#table-acc-catalog-params').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
