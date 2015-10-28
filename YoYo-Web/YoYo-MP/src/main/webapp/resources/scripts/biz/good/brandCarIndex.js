/**
 * 功能描述： 整车品牌的js
 * 
 */
//var select_data = "[]";
$(function() {
	 
	
	$('#table-brand').datagrid({
		url : _path + '/brand/brandList?brandType=1',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'brandName',
			sortable : true,
			halign: 'center',
			title : '品牌名称'			
		}, {
			field : 'brandKeywords',
			sortable : true,
			halign: 'center',
			title : '品牌别名'
		},{
			field : 'brandLogo',
			halign : 'center',
			title : '品牌LOGO',
			formatter : function(value, row) {
				if(value != ""){
					var str = yoyo.imageUrl+value;
					//return '<div><a href="' + str + '" target="_blank" ><img src="' + str + '" style="width : 100px; height : 80px;"></a></div>';
					return '<div><img src="' + str + '" style="width : 100px; height : 80px;"></div>';
				}else{
					return "";
				}
			}
		}, {
			field : 'brandUrl',
			halign: 'center',
			title : '品牌网址'
		}, {
			field : 'ordernum',
			sortable : true,
			halign: 'center',
			title : '排序'
		}, {
			field : 'editor',
			halign: 'center',
			title : '编辑',
			formatter : function(value, row) {
				return "<a href='javascript:editBrand("+JSON.stringify(row)+")'>编辑</a>";
			}
		} ] ],
		toolbar : '#toolbar-brand',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		selectOnCheck:false,
		//height:$(window).height()-2,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		singleSelect : true,
		checkOnSelect:false,
		remoteSort : true,
		multiSort : true,
		nowrap : false,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	$('#combox-cat-virt-index').trapown(
			{ url : _path + '/comboBox/cateTreeCombox' , multiple : true , idField : 'catId' ,
				textField : 'catName' , parentField : 'parentCatId' , initParentId : 0,brandType: '1' });
	
});

//打开添加对话框
function newbrandAndValue() {
	clearImg();
	$('#form-brand-add').form('clear');
	$('#add-brand-btn').show();
	$('#update-brand-btn').hide();
	$('#window-add-brand').window('setTitle','添加品牌');
	$('#window-add-brand').window('open');
}


/**
 * 清空图片预览
 */
function clearImg(){
	$("#imgBrandLogo").attr('src',biz.rootPath()+"/resources/images/pre_default.png");
	$("#imgBrandAptitude").attr('src',biz.rootPath()+"/resources/images/pre_default.png");
}


/**
 * 方法描述：新增品牌
 */
function savebrandAndValue() {
	
	if ($('#form-brand-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-brand-add"));
		param_data.brandType=1;  //整车品牌
		if($('#desc_txt').val().length>1000){
			param_data.brandDesc=$('#desc_txt').val().substring(0,1000);
		}
		if($('#brandNames').val().trim()==''){
			easyuiMsg('错误',"品牌名称不能为空！");
			return false;
		}
		var category = $('#combox-cat-virt-index').combotree('tree').tree('getChecked');
		var categoryIds = [];
		var identifiers = [];
		$.each(category,function(i,v){
			if(v.children.length==0){ //保存的是叶子节点 2015.06.13 xiaox修改
				categoryIds.push(v.id);
				identifiers.push(v.identifier);
			}
		});
		if(categoryIds.length==0){
			easyuiMsg('错误',"请选择分类！");
			return false;
		}
		param_data.goodCategory = categoryIds;
		param_data.identifiers = identifiers;
		console.log(param_data);
		$.post(_path + '/brand/insertBrand', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-brand').datagrid('reload', {});
				$('#window-add-brand').window('close');
			} else if (_data.head.retCode == '000004'){
				easyuiMsg('错误',"该品牌名称已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}


//逻辑删除品牌
function deleteBrand(){
	var selectRows = $("#table-brand").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var brandIds =[];
	$.each(selectRows,function(i,v){
		brandIds[i]=v.brandId;
	});
	var params={};
	params.brandIds=brandIds;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/brand/deleteBrand',
		    	    type:"post",
		    	    dataType:"json",
		    	    data:params,
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-brand').datagrid('reload', {});
			    		}else if (_data.head.retCode == '000007'){
							easyuiMsg('错误',"部分品牌存在级联数据，汽车厂商或在售商品在使用该品牌，请先删除级联数据！");
							$('#table-brand').datagrid('reload', {});
						} else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}


//打开编辑品牌对话框
function editBrand(row){
	clearImg();
	$('#form-brand-add').form('clear');
	$('#form-brand-add').form('load', row);
	if(row.brandLogo!=''){
		$("#imgBrandLogo").attr('src',yoyo.imageUrl+row.brandLogo);
	}else{
		$("#imgBrandLogo").attr('src',biz.defaultPic());
	}
	
	if(row.brandAptitude!=''){
		$("#imgBrandAptitude").attr('src',yoyo.imageUrl+row.brandAptitude);
	}else{
		$("#imgBrandAptitude").attr('src',biz.defaultPic());
	}
	//获取分类信息
	$.ajax({
		url:_path + '/brand/findBrandCateShip?brandId='+row.brandId,
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
	
	
	$('#add-brand-btn').hide();
	$('#update-brand-btn').show();
	$('#window-add-brand').window('setTitle','编辑品牌');
	$('#window-add-brand').window('open');
}


/**
 * 更新品牌
 */
function updateBrand(){
	if ($('#form-brand-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-brand-add"));
		param_data.brandType=1;  //整车品牌
		if($('#desc_txt').val().length>1000){
			param_data.brandDesc=$('#desc_txt').val().substring(0,1000);
		}
		var category = $('#combox-cat-virt-index').combotree('tree').tree('getChecked');
		var categoryIds = [];
		var identifiers = [];
		$.each(category,function(i,v){
			if(v.children.length==0){ //保存的是叶子节点 2015.06.13 xiaox修改
				categoryIds.push(v.id);
				identifiers.push(v.identifier);
			}
		});
		if(categoryIds.length==0){
			easyuiMsg('错误',"请选择分类！");
			return false;
		}
		param_data.goodCategory = categoryIds;
		param_data.identifiers = identifiers;
		$.post(_path + '/brand/updateBrand', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-brand').datagrid('reload', {});
				$('#window-add-brand').window('close');
			} else if (_data.head.retCode == '000004'){
				easyuiMsg('错误',"该品牌名称已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}






