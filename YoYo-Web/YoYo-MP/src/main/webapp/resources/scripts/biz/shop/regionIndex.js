/**
 * 功能描述： 商品经营范围   
 * 
 */
$(function() {
	initCateGrid();
	//initTree('combox-parent-region', false);
});
function initCateGrid() {
	$('#region-cate').treegrid(
			{
				url : _path + '/companyRegionCat/regionTree' ,
				rownumbers : true ,
				toolbar : '#toolbar-region' ,
				idField : 'regionId' ,
				treeField : 'regionName' ,
//				fitColumns : true ,
				singleSelect : true ,
				width : ($(window).width() * 0.98),
				height : ($(window).height() * 0.99),
				queryParams : { 'parentCatId' : 0 } ,
				columns : [ [
						{ field : 'regionId' , title : '节点id' ,halign:'center', hidden : true } ,
						{ field : 'parentRegionId' , title : '父节点id' ,halign:'center', hidden : true } ,
						{ field : 'regionName' , title : '经营范围名称' , width :350 ,halign:'center', fixed : true } ,
						{
							field : 'editor' ,
							title : '操作' ,
							align:'center',
							width : 300 ,
							formatter : function(value, row) {
								// 增加子类 编辑 删除 
								/*var optResult='<a href="javascript:addSubCate(' + row.regionId + ')">增加子类</a>';*/
								var optResult='';
								optResult+= '<a href="javascript:editCate(' +  row.regionId + ','+row.parentRegionId+',\''+row.regionName+'\')">编辑</a>';
								/*if(row.parentRegionId!=0){
									optResult+= '<a href="javascript:editCate(' +  row.regionId + ','+row.parentRegionId+',\''+row.regionName+'\')">编辑</a>';
									optResult+= '<a href="javascript:deleteCate(' + row.regionId + ','+row.parentRegionId+')">删除</a>'
								}*/
								return optResult;
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
					var url = _path + '/companyRegionCat/regionTree?parentCatId=' + row.regionId+'&number='+Math.random();
					$("#region-cate").treegrid("options").url = url;
					return true;
				} , onDblClickCell : function(index, field, value) {

				} });
	
	$('#combox-cat-virt-index').trapown(
			{ url : _path + '/comboBox/cateTreeCombox' , multiple : true , idField : 'catId' ,
				textField : 'catName' , parentField : 'parentCatId' , initParentId : 0,hiddenChild:true });

}
function editCate(regionId,pid,name) {
	$('#tr_parent_cat_id').css('display','');
	$('#window-region-add').window('setTitle', '编辑经营范围');
	/*$('#combox-parent-region').combotree('setValue', pid);*/
	$('#regionId').val(regionId);
	$('#regionName').val(name);
	//获取分类信息
	$.ajax({
		url:_path + '/companyRegionCat/findRegionShip?regionId='+regionId,
 	    type:"post",
 	    dataType:"json",
 	    success:function(data){
 	    	if (data.head.retCode == '000000') {
 	    	    var ct = [];
	    			$.each(data.content,function(i,v){
	    				ct.push(v.catId);
	    			});
	    			$('#combox-cat-virt-index').combotree('setValues', ct);
	    		} else {
	    			easyuiMsg('错误', "获取分类信息失败！");
	    		}
 	    }
	});
	
	$('#window-region-add').window('open');
	
}

function addSubCate(regionId) {
	$('#tr_parent_cat_id').css('display','');
	$('#window-region-add').window('setTitle', '添加经营范围');
	$('#window-region-add').window('open');
	$('#combox-parent-region').combotree('setValue', regionId);
}
function clearDetail() {
	$('#form-region-add').form('clear');
	
}
function newCate() {
	$('#tr_parent_cat_id').css('display','none');
	$('#window-region-add').window('setTitle', '添加经营范围');
	$('#window-region-add').window('open');
}
function saveOrUpdateCate() {
	var fromObj = $('#form-region-add');
	// 1.校验必填项
	var validateForm = fromObj.form('validate');
	if (validateForm == false) {
		return;
	}
	
	if ($('#form-region-add').walidator('isValidated').attr('isValidated')=='true') {
			var params = {};
			// 基本信息
			var cate = yoyo.form2Json('form-region-add');
			cate.parentCatId =0;/* $('#combox-parent-region').combo('getValue');*/
			var category = $('#combox-cat-virt-index').combotree('tree').tree('getChecked');
			var categoryIds = [];
			$.each(category,function(i,v){
				categoryIds.push(v.id);
			});
			if(categoryIds.length==0){
				easyuiMsg('错误',"请选择分类！");
				return false;
			}
			params.goodCategory = categoryIds;
			params.cate = JSON.stringify(cate);
			
			commonAjax(_path + '/companyRegionCat/insert', params, function(data) {
				//initTree('combox-parent-region', false);
				reloadTreeGrid('region-cate');
				$('#window-region-add').window('close');
				
			}, function(data) {
				 if(data.head.retCode == '000004'){
					easyuiMsg('错误',"保存失败，经营范围名称已经存在！");
					return;
				 }
				easyuiMsg('错误', "保存失败！");
			});
	}else{
		easyuiMsg('提示','基本信息未校验通过！');
	}
}
function deleteCate(catId,pid) {
	if (catId) {
		var params = {};
		params.regionId=catId;
		params.parentRegionId=pid;
		if($('#region-cate').treegrid('getLevel',catId) != 1){
			$.messager.confirm('', '确定删除？', function(r) {
				if (r) {
					$.post(_path + '/companyRegionCat/deleteRegion', params, function(data) {
						if (data.head.retCode == '000000') {
							reloadTreeGrid('region-cate');
						} else {
							easyuiMsg('错误', '删除失败');
						}
					}, 'json');
				}
			})
		}else{
			easyuiMsg('错误', '一级经营范围不允许被删除！');
		}
	}
}


/**
 * 方法描述：初始化经营范围下拉树
 * 
 * @param obj
 * @param multiple
 */
function initTree(obj, multiple, initParams) {
	var $obj = $('#' + obj);
	$obj.combotree({
		url : _path + '/companyRegionCat/regionTree',
		multiple : multiple,
		queryParams : initParams || {
			'parentCatId' : 0
		},
		columns : [[{
			field : 'regionId',
			title : '经营范围Id',
			fixed : true
		}, {
			field : 'regionName',
			title : '经营范围名称',
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
				data[i].id = data[i].regionId;
				data[i].text = data[i].regionName;
			}
			return data;
		},
		onBeforeExpand : function(row) {
			var url = _path + '/companyRegionCat/regionTree?parentCatId=' + row.regionId;
			$obj.combotree("tree").tree("options").url = url;
			return true;
		},
		onLoadSuccess : function(node, data) {
			$obj.combotree('tree').tree("expandAll");
		}
	});
}
