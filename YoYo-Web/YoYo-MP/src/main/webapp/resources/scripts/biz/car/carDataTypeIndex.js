/**
 * 功能描述： 汽车数据项类别的js
 * 
 */
$(function() {
	 
	$('#car_data_cate_table').datagrid({
		url : _path + '/carDataCate/carDataCateList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'catalogName',
			align : 'center',
			sortable : true,
			title : '数据项类别名称'			
		},{
			field : 'orderId',
			align : 'center',
			sortable : true,
			title : '排序'			
		},{
			field : 'editor',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:editCatalog("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#car_data_cate_tools',
		pagination : true,
		pagePosition : 'bottom',
		//height:$(window).height()-30,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		checkOnSelect:false,
		remoteSort : true,
		selectOnCheck:false,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
});










//清除高级查询
function carDataTypeAdvanceClear(){
	search_clear('car_data_type_search_form','car_data_cate_table');
	$("#query_catalogId").val('-1').trigger("change");
}


//高级查询立即筛选
function carDataTypeAdvanceSearch(){
	var param = biz.serializeObject($('#car_data_type_search_form'));
	$('#car_data_cate_table').datagrid('load', param);
}



//打开添加数据项类别的对话框
function openAndCateDialog(){
	$('#add_cate_form').form('clear');
	$("#data_category_span").html('');
	$('#catalogId').val('');
	$('#car_cate_add_window').panel({title: "添加数据项类别"});
	$("#car_cate_add_window").window('open');
}
//取消添加数据项类别
function closeAndCatalog(){
	$('#car_cate_add_window').window('close');
}

//保存数据项类别
function saveCate(){
	if ($('#add_cate_form').form('validate')) {
		var param_data = biz.serializeObject($("#add_cate_form"));
		if($('#addCatalogName').val().trim()==''){
			easyuiMsg('提示',"类别名称不能为空！");
			return false;
		}
		if($("#catalogId").val()!=''){ //更新
			$.post(_path + '/carDataCate/updateCarDataCate', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#car_data_cate_table').datagrid('reload', {});
					$('#car_cate_add_window').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该类别名称已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		} else { //添加
			$.post(_path + '/carDataCate/insertCarDataCate', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#car_data_cate_table').datagrid('reload', {});
					$('#car_cate_add_window').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该类别名称已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
	}else{
		easyuiMsg('错误', "请填写类别名称！");
	}
}


//选择数据项类别
function getCatalogId(){
	var selectRows = $("#car_data_cate_table").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	console.log(selectRows[0]);
	$("#catalogId").val(selectRows[0].catalogId);
	$("#data_category_span").html(selectRows[0].catalogName);
	
	$('#car_data_cate_window').window('close');
}



//删除类别
function deleteCatalog(){
	var selectRows = $("#car_data_cate_table").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id[i]=v.catalogId;
	});
	var params={};
	params.id=id;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carDataCate/deleteCarDataCate',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#car_data_cate_table').datagrid('reload', {});
			    		}else if (_data.head.retCode == '000007'){
							easyuiMsg('错误',"该数据项类别存在级联数据，请先删除级联数据！");
							$('#car_data_cate_table').datagrid('reload', {});
						} else {
			    			easyuiMsg('错误', "保存失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}


//编辑车型数据项类别
function editCatalog(row){
	$('#add_cate_form').form('clear');
	$('#add_cate_form').form('load', row);
	$('#catalogId').val(row.catalogId);
	$('#car_cate_add_window').panel({title: "编辑数据项类别"});
	$('#car_cate_add_window').window('open');
}

