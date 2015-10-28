/**
 * 功能描述： 商品分类   作者：李明 创建时间：2015-3-4
 * 
 */
$(function() {
	initCateGrid();
	initRealCateTree('combox-parent-cat', false);
	initSpecGridCurrent('select-ajax-spec');
});
function initSpecGridCurrent(obj) {
	var $obj = $('#' + obj);
	if ($obj) {
		$obj.combogrid({
			url : _path + '/spec/specList',
			multiple : true,
			idField : 'specId',
			textField : 'specName',
			panelHeight:300,
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
			rownumbers : true
		});
	}
}
function initCateGrid() {
	$('#table-cate').treegrid(
			{
				url : _path + '/cate/cateTree' ,
				rownumbers : true ,
				toolbar : '#toolbar-cate' ,
				idField : 'catId' ,
				treeField : 'catName' ,
//				fitColumns : true ,
				singleSelect : true ,
				queryParams : { 'parentCatId' : 0 } ,
				columns : [ [
						{ field : 'catId' , title : '节点id' ,halign:'center', hidden : true } ,
						{ field : 'parentCatId' , title : '父节点id' ,halign:'center', hidden : true } ,
						{ field : 'catName' , title : '分类名称' , width :350 ,halign:'center', fixed : true } ,
						{ field : 'hidden' , title : '在前台隐藏' , width :100 , halign:'center',formatter : function(value, row) {
							if (value == '1')
								return '是';
							return '否';
						} } ,
						{ field : 'orderNum' , width : 80 , title : '排序' , halign:'center',editor : 'numberbox' } ,
						{
							field : 'editor' ,
							title : '操作' ,
							align:'center',
							width : 300 ,
							formatter : function(value, row) {
								// 增加子类 编辑 删除 查看商品 预览
								var optResult='<a href="javascript:addSubCate(' + row.catId + ')">增加子类</a>';
								if(row.parentCatId!=0){
									optResult+= '<a href="javascript:editCate(' + row.catId + ')">编辑</a>';
//									optResult+= '<a href="javascript:deleteCate(' + row.catId + ')">删除</a>';
								}
//								optResult+= '<a href="javascript:showGoods(' + row.catId + ')">查看商品</a>';
								return optResult;
								/*+ '<a href="javascript:showGoods(' + row.catId + ')">查看商品</a>'*/
								/*+ '<a href="javascript:review(' + row.catId + ')">预览</a>';*/
							} } ] ] , loadFilter : function(data) {
					for (var i = 0; i < data.length; i++) {
						if (data[ i ].childCount > 0) {
							data[ i ].state = 'closed';
						} else {
							data[ i ].state = '';
						}
					}
					return data;
				} , onBeforeExpand : function(row) {
					var url = _path + '/cate/cateTree?parentCatId=' + row.catId+'&number='+Math.random();
					$("#table-cate").treegrid("options").url = url;
					return true;
				} , onDblClickCell : function(index, field, value) {

				} });
	$('#table-cate').datagrid({
		width : ($(window).width() * 0.98),
		height : ($(window).height() * 0.99)
	});
}
var _catId = null;
function editCate(catId) {
	_catId = catId;
	$('#window-cate-add').window('setTitle', '编辑分类');
	$('#window-cate-add').window('open');
}
function getDetail() {
	if (_catId) {
		$.post(_path + '/cate/getDetail', { 'catId' : _catId }, function(data) {
			if (data.head.retCode == '000000') {
				var _data = $.parseJSON(data.content);
				$('#form-cate-add').form('load', _data.cate);
				if(_data.cate.hidden=='1'){
					$("#hiddenId").prop("checked",true);
				}else{
					$("#hiddenId").prop("checked",false);
				}
				$('#table-attr-add').datagrid({ data : _data.attrs });
				var spec = [ ];
				$.each(_data.specs, function(n, value) {
					spec[ n ] = value.specId;
				});
				$('#select-ajax-spec').combogrid("setValues", spec);
			} else {
				easyuiMsg('错误', data.head.retMsg);
			}
		});
	}
}
function formatInputType(val,row,index){
	var j=row.attrInputType;
	var result="";
	if(j=='select'){
		result='下拉选择';
	}else{
		result='手工输入';
	}
	return result;
};
function formatAttrShow(val,row,index){
	var j=row.attrShow;
	var result="";
	if(j=='1'){
		result='是';
	}else{
		result='否';
	}
	return result;
};
function addSubCate(carId) {
	$('#tr_parent_cat_id').css('display','');
	$('#window-cate-add').window('setTitle', '添加分类');
	$('#window-cate-add').window('open');
	$('#combox-parent-cat').combotree('setValue', carId);
}
function clearDetail() {
	_catId = null;
	$('#form-cate-add').form('clear');
	$('#table-attr-add').datagrid({ data : [ ] });
	$('#select-ajax-spec').combogrid("setValues", [ ]);
}
function newCate() {
	$('#tr_parent_cat_id').css('display','none');
	$('#window-cate-add').window('setTitle', '添加分类');
	$('#window-cate-add').window('open');
}
function saveOrUpdateCate() {
	var fromObj = $('#form-cate-add');
	// 1.校验必填项
	var validateForm = fromObj.form('validate');
	if (validateForm == false) {
		return;
	}
	
	if ($('#form-cate-add').walidator('isValidated').attr('isValidated')=='true') {
		if(endEditing()){
			var params = {};
			// 基本信息
			var cate = yoyo.form2Json('form-cate-add');
			cate.hidden = $('#form-cate-add input[name="hidden"]').prop('checked')?1:0;
			cate.parentCatId = $('#combox-parent-cat').combo('getValue');
			params.cate = JSON.stringify(cate);
			// 扩展属性
			params.inserted = JSON.stringify($('#table-attr-add').datagrid('getChanges', 'inserted'));
			params.updated = JSON.stringify($('#table-attr-add').datagrid('getChanges', 'updated'));
			params.deleted = JSON.stringify($('#table-attr-add').datagrid('getChanges', 'deleted'));
			// 规格
			params.specs = JSON.stringify($('#select-ajax-spec').combogrid("getValues"));
			commonAjax(_path + '/cate/insertCate', params, function(data) {
				reloadTreeGrid('table-cate');
				$('#window-cate-add').window('close');
//				$('#table-cate').treegrid('reload');
				initRealCateTree('combox-parent-cat', false);
			}, function(data) {
				 if(data.head.retCode == '000004'){
					easyuiMsg('错误',"保存失败，分类名称已经存在！");
					return;
				 }else  if(data.head.retCode == '000009'){
					easyuiMsg('错误',"该分类规格已被引用不能删除！");
					return;
				 }
				easyuiMsg('错误', data.head.retMsg);
			});
		}else{
			easyuiMsg('提示','扩展属性信息未校验通过！');
		}
	}else{
		easyuiMsg('提示','基本信息未校验通过！');
	}
}
function deleteCate(catId) {
	if (catId) {
		if($('#table-cate').treegrid('getLevel',catId) != 1){
			$.messager.confirm('', '确定删除？', function(r) {
				if (r) {
					$.post(_path + '/cate/deleteCate', { 'catId' : catId }, function(data) {
						if (data.head.retCode == '000000') {
							reloadTreeGrid('table-cate');
						} else {
							easyuiMsg('错误', '删除失败');
						}
					}, 'json');
				}
			})
		}else{
			easyuiMsg('错误', '一级分类不允许被删除！');
		}
	}
}
