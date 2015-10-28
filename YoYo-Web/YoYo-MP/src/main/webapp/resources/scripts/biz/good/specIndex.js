/**
 * 功能描述： 作者：李明 创建时间：2015-3-4
 * 
 */
var _specId = null;
$(function() {
	initSpecDataGrid();
	$('#acc-spec-ads-button').advanceSeacher({aimDataGrid : 'table-spec', renderTo : 'table-spec-advance-searcher', exceptField : ['editor']});
});

function initSpecDataGrid() {
	$('#table-spec').datagrid({
		url : _path + '/spec/specList',
		pageSize: 25,
	    pageList: [25,50,100,150],
//	    height:780,
		columns : [[
        {field : 'specId', hidden : true}, 
        {field : 'specName', title : '规格名称', halign:'center',width:150,sortable : true, options : {querytype : 'like'}}, 
        {field : 'specSelectMode', title : '显示方式', halign:'center',width:80,sortable : true, options : {querytype : 'equal', items : [{value : 'SELECT', text : '下拉'}, {value : 'FLAT', text : '平铺'}]}, formatter : function(value, row) {
			if (value == 'FLAT') {
				return '平铺';
			} else if (value == 'SELECT') {
				return '下拉';
			} else {
				return '';
			}
        }}, 
		{field : 'specAlias', title : '规格别名',halign:'center',width:150, options : {querytype : 'like'}},
		{field : 'specMemo', title : '规格备注', halign:'center', width:350,options : {querytype : 'like'}}, 
		{field : 'editor', title : '操作',align:'center', width:150,formatter : function(value, row) {
			return '<a href="javascript:editSpecAndValue(' + row.specId + ')" >编辑</a>' + '&nbsp;&nbsp;' + '<a href="javascript:deleteSpecAndValue(' + row.specId + ')" >删除</a>';
		}}]], toolbar : '#toolbar-spec', pagination : true, rownumbers : true, fitColumns : true, singleSelect : true, selectOnCheck : false, remoteSort : false, multiSort : true, loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}});
		$('#table-spec').datagrid({
			width : ($(window).width() * 0.99),
			height : ($(window).height() * 0.95)
		});
}
function getDetail() {
	if (_specId) {
		$.post(_path + '/spec/getDetail', {'specId' : _specId}, function(data) {
			if (data.head.retCode == '000000') {
				$('#form-spec-add').form('load', data.content.spec)
				$('#table-spec-values-add').datagrid({data : data.content.values});
			}
		});
	}
}

function clearDetail() {
	$('#form-spec-add').form('clear');
	$('#table-spec-values-add').datagrid({data : []});
	_specId = null;
}
function newSpecAndValue() {
	$('#window-add-spec').window('setTitle', '添加规格');
	$('#window-add-spec').window('open');
}
function editSpecAndValue(specId) {
	_specId = specId;
	$('#window-add-spec').window('setTitle', '编辑规格');
	$('#window-add-spec').window('open');
}

function deleteSpecAndValue(specId) {
	$.messager.confirm('', '确定删除？', function(r) {
		if (r) {
			$.post(_path + '/spec/deleteSpec', {specId : specId}, function(result) {
				if (result.retCode != '000000') {
					easyuiMsg('错误', result.retMsg);
				} else {
					$('#table-spec').datagrid('reload', {});
				}
			}, 'json');
		}
	});
}

function saveSpecAndValue() {
//	editIndex = undefined;
	if ($('#form-spec-add').walidator('isValidated').attr('isValidated') == 'true') {
		if (endEditing()) {
			var params = {};
			params.spec = JSON.stringify(biz.serializeObject($('#form-spec-add')));
			params.inserted = JSON.stringify($('#table-spec-values-add').datagrid('getChanges', 'inserted'));
			params.updated = JSON.stringify($('#table-spec-values-add').datagrid('getChanges', 'updated'));
			params.deleted = JSON.stringify($('#table-spec-values-add').datagrid('getChanges', 'deleted'));
			$.post(_path + '/spec/insertSpec', params, function(data) {
				if (data.head.retCode == '000000') {
					$('#table-spec').datagrid('reload', {});
					$('#window-add-spec').window('close');
				} else if(data.head.retCode == '000004'){
					easyuiMsg('错误',"保存失败，规格名称已经存在！");
				}else if(data.head.retCode == '000009'){
					easyuiMsg('错误',"规格值已关联商品不能更新或删除！");
				}else {
					easyuiMsg('错误', data.head.retMsg);
				}
			}, 'json');
		} else {
			easyuiMsg('提示', '规格值列表数据未校验通过！');
		}
	} else {
		easyuiMsg('提示', '规格基本信息未校验通过！');
	}
}

function modifySpecAndValues() {
	$('#table-spec-values-update').datagrid('acceptChanges');
	if ($('#form-spec-update').form('validate')) {
		var params = {};
		params.spec = JSON.stringify(biz.serializeObject($('#form-spec-update')));
		params.values = JSON.stringify($('#table-spec-values-update').datagrid('getRows'));
		$.post(_path + '/spec/updateSpec', params, function(data) {
			if (data.head.retCode == '000000') {
				$('#table-spec').datagrid('reload', {});
				$('#window-update-spec').window('close');
			} else {
				easyuiMsg('错误', data.head.retMsg);
			}
		}, 'json');
	}
}

function isDisable(value, row, index){
	if(value=='1'){
		return '是';
	}else{
		return '否';
	}
}
