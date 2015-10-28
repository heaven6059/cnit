/**
 * 功能描述： 店铺等级的js
 * 
 */
//var select_data = "[]";
$(function() {
	 
	
	$('#table-shopGrade').datagrid({
		url:_path+'/shopGrade/shopGradeList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'gradeName',
			sortable : true,
			align: 'center',
			title : '等级名称'			
		}, {
			field : 'shopNums',
			sortable : true,
			align: 'center',
			title : '开店个数'
		}, {
			field : 'experience',
			sortable : true,
			align: 'center',
			title : '所需经验值'
		},{
			field : 'defaultLv',
			align : 'center',
			align: 'center',
			title : '是否为默认等级',
			formatter : function(value, row) {
				if(value == "1"){
					return "是";
				}else{
					return "否";
				}
			}
		}, {
			field : 'issueMoney',
			align: 'center',
			title : '保证金'
		}, {
			field : 'goodsNum',
			align: 'center',
			title : '允许发布商品数'
		}, {
			field : 'couponsNum',
			align: 'center',
			title : '允许发行优惠券数'
		}, {
			field : 'themeNum',
			align: 'center',
			title : '可选模板套数'
		},  {
			field : 'issueType',
			align: 'center',
			title : '店铺类型',
			formatter : function(value, row) {
				if(value == "1"){
					return "集团";
				}else{
					return "单店";
				}
			}
		},{
			field : 'gradeMoney',
			align: 'center',
			title : '收费标准'
		},{
			field : 'remark',
			align: 'center',
			title : '店铺备注'
		}, {
			field : 'editor',
			title : '操作',
			align: 'center',
			formatter : function(value, row) {
				return "<a href='javascript:editshopGrade("+JSON.stringify(row)+")'>编辑</a>";
			}
		} ] ],
		toolbar : '#toolbar-shopGrade',
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
		//singleSelect : true,
		checkOnSelect:false,
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

	$('#issueType').change(function(){
		if($('#issueType').val()=='0'){ //单店
			$('#shopNums').numberbox('setValue',1);
			$('#shopNums').numberbox({disabled:true});
		}else{
			$('#shopNums').numberbox({disabled:false});
		}
	});
});

//打开添加对话框
function newshopGradeAndValue() {
	chis.clearForm('#form-shopGrade-add')  ;
	$('#shopNums').numberbox('setValue',1);
	$('#add-shopGrade-btn').show();
	$('#update-shopGrade-btn').hide();
	$('#defaultLv').prop('checked', true);
	$('#shopNums').numberbox({disabled:false});
	$('#window-add-shopGrade').window('open');
}




/**
 * 方法描述：新增等级
 */
function saveShopGrade() {
	
	
	if ($('#form-shopGrade-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-shopGrade-add"));
	    if($('#issueType').val()==''||$('#issueType').val()==null){
	    	easyuiMsg('提示',"请选择店铺类型！");
	    	return false;
	    }
		$.post(_path + '/shopGrade/insertShopGrade', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-shopGrade').datagrid('reload', {});
				$('#window-add-shopGrade').window('close');
			} else if (_data.head.retCode == '000004'){
				easyuiMsg('错误',"该等级名称已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}


//逻辑删除等级
function deleteShopGrade(){
	var selectRows = $("#table-shopGrade").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var shopGradeIds =[];
	$.each(selectRows,function(i,v){
		shopGradeIds[i]=v.gradeId;
	});
	var params={};
	params.shopGradeIds=shopGradeIds;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/shopGrade/deleteShopGrade',
		    	    type:"post",
		    	    dataType:"json",
		    	    data:params,
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-shopGrade').datagrid('reload', {});
			    		}else if (_data.head.retCode == '000007'){
							easyuiMsg('错误',"部分等级存在级联数据，该等级下有店铺，请先删除店铺！");
							$('#table-shopGrade').datagrid('reload', {});
						} else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}

//打开编辑等级对话框
function editshopGrade(row){
	chis.clearForm('#form-shopGrade-add')  ;
	$('#defaultLv').prop('checked', true);
	$('#form-shopGrade-add').form('load', row);
	$('#issueType').val(row.issueType);
	$('#add-shopGrade-btn').hide();
	$('#update-shopGrade-btn').show();
	$('#window-add-shopGrade').window('open');
	
	if($('#issueType').val()=='0'){ //单店
		$('#shopNums').numberbox('setValue',1);
		$('#shopNums').numberbox({disabled:true});
	}else{
		$('#shopNums').numberbox({disabled:false});
	}
}


/**
 * 更新等级
 */
function updateShopGrade(){
	if ($('#form-shopGrade-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-shopGrade-add"));
		
		if($('#issueType').val()==''||$('#issueType').val()==null){
		    	easyuiMsg('提示',"请选择店铺类型！");
		    	return false;
		   }
		$.post(_path + '/shopGrade/updateShopGrade', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-shopGrade').datagrid('reload', {});
				$('#window-add-shopGrade').window('close');
			} else if (_data.head.retCode == '000004'){
				easyuiMsg('错误',"该等级名称已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}


