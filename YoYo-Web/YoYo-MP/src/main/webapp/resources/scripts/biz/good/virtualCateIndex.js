/**
 * 功能描述： 作者：李明 创建时间：2015-3-4
 * 
 */
$(function() {
	initVirtCateGrid();
	initVirtCateTree('combox-parent-vir-cat', false);
	$('#combox-brand-virtcat-index').comdropdown(
			{ url : _path + '/brand/brandList' , cache : true , idField : 'brandId' , textField : 'brandName' ,
				multiple : 'multiple' , queryParams : { disabledQ : 0 , rows : 20 } });
	$('#combox-cat-virt-index').trapown(
			{ url : _path + '/comboBox/cateTreeCombox' , multiple : true , idField : 'catId' ,
				textField : 'catName' , parentField : 'parentCatId' , initParentId : 0 });
	
	$('#combox-tag-virtcat-index').comdropdown(
			{ url : _path + '/label/brandApplyLabels' , cache : true , idField : 'id' , textField : 'name' ,
				multiple : 'multiple' , queryParams : { rows : 20 , campanyId : 1} });
});
function initVirtCateGrid() {
	$('#table-virt-cate').treegrid(
			{
				url : _path + '/cate/getVirtualCateTree' ,
				rownumbers : true ,
				toolbar : '#toolbar-virt-cate' ,
				idField : 'catId' ,
				treeField : 'catName' ,
//				fitColumns : true ,
//				striped : true ,
//				nowrap : false ,
				openAnimation : true ,
				singleSelect : true ,
				queryParams : { 'parentCatId' : 0 } ,
				columns : [ [
						{ field : 'catId' , title : '节点id' ,halign:'center', hidden : true } ,
						{ field : 'parentCatId' , title : '父节点id' , halign:'center',hidden : true } ,
						{ field : 'catName' , title : '分类名称' , width : 400 ,halign:'center', fixed : true } ,
						{ field : 'hidden' , title : '在前台隐藏' , width : 100 , halign:'center',formatter : function(value, row) {
							if (value == '1')
								return '是';
							return '否';
						} } ,
						{ field : 'orderNum' , width : 100 , title : '排序' ,halign:'center', editor : 'numberbox' } ,
						{
							field : 'editor' ,
							title : '操作' ,
							align:'center',
							width : 300 ,
							formatter : function(value, row) {
								// 增加子类 编辑 删除 查看商品 预览
								return '<a href="javascript:addVirtSubCate(' + row.catId + ')">增加子类</a>'
										+ '<a href="javascript:editVirtCate(' + row.catId + ')">编辑</a>'
										+ '<a href="javascript:deleteVirtCate(' + row.catId + ')">删除</a>'
//										+ '<a href="javascript:showGoods(' + row.catId + ')">查看商品</a>'
//										+ '<a href="javascript:review(' + row.catId + ')">预览</a>'
										;
							} } ] ] , loadFilter : function(data) {
					for (var i = 0; i < data.length; i++) {
						if (data[ i ].childCount > 0) {
							data[ i ].state = 'closed';
						} else {
							data[ i ].state = '';
						}
						data[ i ]._parentId = data[ i ].parentCatId;
					}
					return data;
				} , onBeforeExpand : function(row) {
					var url = _path + '/cate/getVirtualCateTree?parentCatId=' + row.catId;
					$("#table-virt-cate").treegrid("options").url = url;
					return true;
				} , onDblClickCell : function(index, field, value) {

				} });
	$('#table-virt-cate').datagrid({
		width : ($(window).width() * 0.98),
		height : ($(window).height() * 0.99)
	});
}
var _virtCatId = null;
function editVirtCate(catId) {
	_virtCatId = catId;
	$('#window-virt-cate-add').window('setTitle', '编辑分类');
	$('#window-virt-cate-add').window('open');
	$("#imgCateImage").attr('src',biz.rootPath()+"/resources/images/pre_default.png");
	$("#cateIcon").val("");
}
function getVirtCateDetail() {
	if (_virtCatId) {
		$.post(_path + '/cate/getVirtualCateDetail', { 'catId' : _virtCatId }, function(data) {
			if (data.head.retCode == '000000') {
				var _data = $.parseJSON(data.content);
				$('#form-virt-cate-add').form('load', _data.cate);
				if(_data.cate.icon!=''){
					$("#imgCateImage").attr('src',yoyo.imageUrl+_data.cate.icon);
				}else{
					$("#imgCateImage").attr('src',biz.defaultPic());
				}
				if(_data.cate.hidden=='1'){
					$("#hiddenId").prop("checked",true);
				}else{
					$("#hiddenId").prop("checked",false);
				}
				if (_data.cate.filter) {
					var filter = $.parseJSON(_data.cate.filter);
					$('input[name="pmi"]').val(filter.pr.pmi);
					$('input[name="pma"]').val(filter.pr.pma);
					$('input[name="kw"]').val(filter.kw);
					$('input[name="tag"]').val(filter.tg);
					var tg_val = [];
					$.each(filter.tg,function(index,obj){
						tg_val.push(obj.id);
					});
					$('#combox-tag-virtcat-index').val(tg_val).trigger("change");
					var br_val = [];
					$.each(filter.br,function(index,obj){
						br_val.push(obj.id);
					});
					$('#combox-brand-virtcat-index').val(br_val).trigger("change");
					var ct = [ ];
					var ctext = [];
					$.each(filter.ct, function(index, obj) {
						ct[ index ] = obj.id;
						ctext.push(obj.text);
					});
					$('#combox-cat-virt-index').combotree('setValues', ct);
					$('#combox-cat-virt-index').combotree('setText', ctext.join(','));
				}
			} else {
				easyuiMsg('错误', data.head.retMsg);
			}
		});
	}
}
function addVirtSubCate(carId) {
	$("#imgCateImage").attr('src',biz.rootPath()+"/resources/images/pre_default.png");
	$("#cateIcon").val("");
	$('#tr_parent_virtual_cat_id').css('display','');
	$('#combox-parent-vir-cat').combotree('setValue', carId);
	$('#window-virt-cate-add').window('setTitle', '添加子类');
	$('#window-virt-cate-add').window('open');
}
function clearVirtCateDetail() {
	_virtCatId = null;
	$('#form-virt-cate-add').form('clear');
	$('#select-ajax-spec').combogrid("setValues", [ ]);
}
function newVirtCate() {
	$("#imgCateImage").attr('src',biz.rootPath()+"/resources/images/pre_default.png");
	$("#cateIcon").val("");
	$('#tr_parent_virtual_cat_id').css('display','none');
	$('#combox-parent-vir-cat').combotree('clear');
	$('#window-virt-cate-add').window('setTitle', '添加分类');
	$('#window-virt-cate-add').window('open');
}
function saveOrUpdateVirtCate() {
	var fromObj = $('#form-virt-cate-add');
	// 1.校验必填项
	var validateForm = fromObj.form('validate');
	if (validateForm == false) {
		return;
	}
	var virtCate = {};
	virtCate.catId = $('input[name="catId"]').val();
	virtCate.catName = $('input[name="catName"]').val();
	virtCate.parentCatId = $('#combox-parent-vir-cat').combotree('getValue');
	virtCate.icon = $('input[name="icon"]').val();
	virtCate.orderNum = $('input[name="orderNum"]').val();
	virtCate.hidden = $('input[name="hidden"]').prop("checked") ? '1' : '0';
	virtCate.title = $('input[name="title"]').val();
	virtCate.metaKeywords = $('input[name="metaKeywords"]').val();
	virtCate.metaDescription = $('input[name="metaDescription"]').val();
	if(virtCate.parentCatId && virtCate.catId == virtCate.parentCatId ){
		easyuiMsg('错误', "不能选择自身节点作为父级");
		return;
	}
	if(virtCate.title && virtCate.title.length>20){
		easyuiMsg('错误', "描述不能超过20个字符");
		return;
	}
	
	var filter = {};
	filter.pr = { 'pmi' : $('input[name="pmi"]').val() , 'pma' : $('input[name="pma"]').val() };
	filter.kw =  $('input[name="kw"]').val()||'';
	var tg = [];
	$.each($('#combox-tag-virtcat-index option'),function(index,option){
		var $option = $(option);
		tg[index]={'id':$option.val(),'text':$option.text()};
	});
	filter.tg = tg;
	var br = [];
	$.each($('#combox-brand-virtcat-index option'),function(index,option){
		var $option = $(option);
		br[index]={'id':$option.val(),'text':$option.text()};
	});
	filter.br = br;
	var ct = [];
	var ct_id =$('#combox-cat-virt-index').combotree('getValues');
	if(ct_id.length > 0){
		var ct_text =$('#combox-cat-virt-index').combotree('getText').split(',');
		$.each(ct_id,function(index, obj) {
			ct[ index ] = { 'id' : ct_id[index] , 'text' : ct_text[index] };
		});
	}
	filter.ct = ct;
	virtCate.filter = filter;
	commonAjax(_path + '/cate/saveVirtualCate', { 'virtCate' : JSON.stringify(virtCate) }, function(data) {
		$('#window-virt-cate-add').window('close');
		initVirtCateGrid();
		initVirtCateTree('combox-parent-vir-cat', false);
	}, function(data) {
		 if(data.head.retCode == '000004'){
			easyuiMsg('错误',"保存失败，虚拟分类名称已经存在！");
			return;
		 }
		easyuiMsg('错误', data.head.retMsg);
	});
}
function deleteVirtCate(catId) {
	if (catId) {
		$.messager.confirm('', '确定删除？', function(r) {
			if (r) {
				$.post(_path + '/cate/deleteVirtualCate', { 'catId' : catId }, function(data) {
					if (data.head.retCode != '000000') {
						easyuiMsg('错误', data.head.retMsg);
					}
					initVirtCateGrid();
				}, 'json');
			}
		})
	}
}
