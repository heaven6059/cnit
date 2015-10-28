
$(function() {
	initViolationCateGrid();
	initViolationCateTree('combox-parent-violation-cat', false);
	$('.panel-tool-close').on('click', function(){
		$('#MODALPANEL').hide();
	});
});
function initViolationCateGrid() {
	$('#table-violation-cate').treegrid(
			{
				url : _path + '/storeViolationCat/findViolationCatList' ,
				rownumbers : false ,
				toolbar : '#toolbar-violation-cate' ,
				idField : 'catId' ,
				treeField : 'catName' ,
				fitColumns : true ,
//				striped : true ,
//				nowrap : false ,
				openAnimation : true ,
				singleSelect : true ,
				queryParams : { 'parentCatId' : 0 } ,
				columns : [ [
						{ field : 'catId' , title : '节点id' ,align:'center', hidden : true } ,
						{ field : 'parentId' , title : '父节点id' , align:'center',hidden : true } ,
						{ field : 'catName' , title : '分类名称' , width : 400 ,halign:'center', fixed : true } ,
						{ field : 'pOrder' , width : 100 , title : '排序' ,align:'center', editor : 'numberbox' } ,
						{
							field : 'editor' ,
							title : '操作' ,
							align:'center',
							width : 300 ,
							formatter : function(value, row) {
								// 增加子类 编辑 删除 查看商品 预览
								return '<a href="javascript:addCate(' + row.catId + ')" style="margin-right:15px;" >增加子类</a>'
										+ '<a href="javascript:editCate(' + row.catId + ')" style="margin-right:15px;" >编辑</a>'
										+ '<a href="javascript:deleteCate(' + row.catId + ')" style="margin-right:15px;" >删除</a>';
							} } ] ] , 
				loadFilter : function(data) {
					for (var i = 0; i < data.length; i++) {
						if (data[ i ].childCount > 0) {
							data[ i ].state = 'closed';
						} else {
							data[ i ].state = '';
						}
//						data[ i ]._parentId = data[ i ].parentId;
					}
					return data;
				} , 
				onBeforeExpand : function(row) {
					var url = _path + '/storeViolationCat/findViolationCatList?parentCatId=' + row.catId;
					$("#table-violation-cate").treegrid("options").url = url;
					return true;
				} , 
				onLoadSuccess : function(row, data) {
					expandAll();
				},
				onDblClickCell : function(index, field, value) {

				} });
	$('#table-violation-cate').datagrid({
		width : ($(window).width() * 0.98),
		height : ($(window).height() * 0.99)
	});
}

function initViolationCateTree(obj, multiple) {
	var $obj = $('#' + obj);
	$obj.combotree({
		url : _path + '/storeViolationCat/findViolationCatList',
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
			var url = _path + '/storeViolationCat/findViolationCatList?parentCatId=' + row.catId;
			$obj.combotree("tree").tree("options").url = url;
			return true;
		},
		onLoadSuccess : function(node, data) {
			$obj.combotree('tree').tree("expandAll");
		}
	});
}

var _virtCatId = null;
//编辑
function editCate(catId) {
	_virtCatId = catId;
	$('#window-violation-cate-add').window('setTitle', '编辑分类');
	$('#MODALPANEL').show();
	$('#window-violation-cate-add').window('open');
}
//获取指定的违规类型对象
function getVirtCateDetail() {
	if (_virtCatId) {
		$.post(_path + '/storeViolationCat/getViolationCateDetail', { 'catId' : _virtCatId }, function(data) {
			if (data.head.retCode == '000000') {
				var _data = data.content;
				$('#form-violation-cate-add').form('load', _data.cate);
				if(_data.cate.parentId=='0'){
					$('#tr_parent_violation_cat_id').css('display','none');
					$('#combox-parent-violation-cat').combotree('clear');
				}else{
					$('#tr_parent_violation_cat_id').css('display','');
					$('#combox-parent-violation-cat').combotree('setValue', _data.cate.parentId);
				}
			} else {
				easyuiMsg('错误', data.head.retMsg);
			}
		});
	}
}
//新增类型
function addCate(catId) {
	$('#form-violation-cate-add').form('clear');
	if(catId=='0'){
		$('#tr_parent_violation_cat_id').css('display','none');
		$('#combox-parent-violation-cat').combotree('clear');
	}else{
		$('#tr_parent_violation_cat_id').css('display','');
		$('#combox-parent-violation-cat').combotree('setValue', catId);
	}
	$('#window-violation-cate-add').window('setTitle', '添加子类');
	$('#MODALPANEL').show();
	$('#window-violation-cate-add').window('open');
}
function clearVirtCateDetail() {
	_virtCatId = null;
	$('#form-virt-cate-add').form('clear');
	$('#select-ajax-spec').combogrid("setValues", [ ]);
}
//新增或更新违规类型
function saveOrUpdateCate() {
	var fromObj = $('#form-violation-cate-add');
	// 1.校验必填项
	var validateForm = fromObj.form('validate');
	if (validateForm == false) {
		return;
	}
	var cate = {};
	cate.catId = $('input[name="catId"]').val();
	cate.catName = $('input[name="catName"]').val();
	cate.parentId = $('#combox-parent-violation-cat').combotree('getValue');
	if(cate.parentId && cate.catId == cate.parentId ){
		easyuiMsg('错误', "请重新选择上级分类");
		return;
	}
	cate.pOrder = $('input[name="pOrder"]').val();
	/*cate.score = $('input[name="score"]').val();*/
	cate.score = 0;
	commonAjax(_path + '/storeViolationCat/saveOrUpdateCate', { 'cate' : JSON.stringify(cate) }, function(data) {
		$('#window-violation-cate-add').window('close');
		$('#MODALPANEL').hide();
		initViolationCateGrid();
		initViolationCateTree('combox-parent-violation-cat', false);
	}, function(data) {
		 if(data.head.retCode == '000004'){
			easyuiMsg('错误',"保存失败，分类名称已经存在！");
			return;
		 }else if(data.head.retCode == '000007'){
			 easyuiMsg('错误',"保存失败，该上级分类不合法！");
			return;
		 }else if(data.head.retCode == '000001'){
			 easyuiMsg('错误','保存失败，该违规类型不存在！');
			 return;
		 }
		easyuiMsg('错误', data.head.retMsg);
	});
}
//删除违规类型
function deleteCate(catId) {
	if (catId) {
		$.messager.confirm('', '确定删除？', function(r) {
			if (r) {
				$.post(_path + '/storeViolationCat/deleteCate', { 'catId' : catId }, function(data) {
					if (data.head.retCode == '000000') {
						initViolationCateGrid();
						initViolationCateTree('combox-parent-violation-cat', false);
					}else if(data.head.retCode == '000007'){
						easyuiMsg('错误',"删除失败，本分类下面还有子分类");
					}else{
						easyuiMsg('错误', data.head.retMsg);
					}
				}, 'json');
			}
		})
	}
}
