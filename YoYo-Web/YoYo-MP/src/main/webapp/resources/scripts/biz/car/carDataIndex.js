/**
 * 功能描述： 汽车数据项的js
 * 
 */
$(function() {
	 
	
	$('#car_data_datagrid').datagrid({
		url : _path + '/carData/carDataList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'displayName',
			halign : 'center',
			sortable : true,
			title : '数据项名称'			
		}, {
			field : 'catalogName',
			halign : 'center',
			title : '数据项类别',
			sortable : true
		}, {
			field : 'dataType',
			halign : 'center',
			title : '数据类型',
			sortable : true,
			formatter : function(value, row) {
				var result = '文本';
				if(value=='INT'){
					result = '整型';
				}else if(value=='BOL'){
					result = '布尔型';
				}else if(value=='DEC'){
					result = '数值型';
				}else if(value=='LIST'){
					result='列表';
				}
				return result ;
			}
			
		},{
			field : 'orderId',
			halign : 'center',
			title : '排序',
			sortable : true
		},{
			field : 'listValue',
			halign : 'center',
			title : '列表数据项'
			
		},{
			field : 'editor',
			title : '操作',
			halign : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:editCarData("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#car_data_toolba',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		//height:$(window).height()-30,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25, 50, 100, 150],
		singleSelect : true,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : true,
		nowrap:false,
		multiSort : true,
		loadFilter : function(data) {
			console.log(data);
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	//高级查询数据项列表
	getCarData(biz.rootPath() + '/carDataCate/carDataCateList','query_catalogId',true);
	getCarData(biz.rootPath() + '/carDataCate/carDataCateList','add_catalogId',true);
	//数据类型改变
	$("#dataType").combobox({onSelect:function(record){
		if(record.value=='LIST'){
			$('.listTr').show();
		}else{
			$('.listTr').hide();
		}
	}});
});



//打开添加对话框
function openCarDataDialog() {
	$("#data_category_span").html('');
	$('#car_data_add_form').form('clear');
	$("#add_catalogId").val('-1').trigger("change");
	$('#car_data_add').panel({title: "添加数据项"});
	$('#car_data_add').window('open');
}

function test() {
	var param_data = {};
	$.post(_path + '/carData/test', param_data, function(_data) {
		if (_data.head.retCode == '000000') {
			
		} else {
			easyuiMsg('错误', "保存失败！");
		}
	}, 'json');
}


/**
 * 方法描述：新增数据项
 */
function saveCarData() {
	if ($('#car_data_add_form').form('validate')) {
		var param_data = biz.serializeObject($("#car_data_add_form"));
		if($("#dataType").combobox('getValue')=='' || $("#dataType").combobox('getValue') == null){
			easyuiMsg('错误',"请选择数据类型！");
			return false;
		}
		if($("#dataType").combobox('getValue')=='LIST' && $("#listValue").val()==''){
			easyuiMsg('错误',"请填写列表数据项！");
			return false;
		}
		if($("#add_catalogId").val()==''||$("#add_catalogId").val()==-1){
			easyuiMsg('错误',"请选择数据项类别！");
			$("#data_category_span").html('');
			return false;
		}
		param_data.catalogId = $("#add_catalogId").val();
		param_data.dataType = $("#dataType").combobox('getValue');
		if($("#dataId").val()=='' || $("#dataId").val()==null){ //添加
			$.post(_path + '/carData/insertCarData', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#car_data_datagrid').datagrid('reload', {});
					$('#car_data_add').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该数据项名称已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}else { //更新
			$.post(_path + '/carData/updateCarData', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#car_data_datagrid').datagrid('reload', {});
					$('#car_data_add').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该数据项名称已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
		
	}
	
}

//获得车型数据项
function getCarData(url,obj,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "请选择", id: "-1" ,selected:true  },';
		}
		$.each(json.content.rows, function(i, n) {
			data+='{ text: "'+n.catalogName+'", id:'+n.catalogId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj).select2({data:eval(data)});
	});
}


//清除高级查询
function carDataAdvanceClear(){
	search_clear('car_data_search_form','car_data_datagrid');
	$("#query_catalogId").val('-1').trigger("change");
}


//高级查询立即筛选
function carDataAdvanceSearch(){
	var param = biz.serializeObject($('#car_data_search_form'));
	$('#car_data_datagrid').datagrid('load', param);
}


//打开编辑数据项对话框
function editCarData(row){
	$('#car_data_add_form').form('clear');
	$('#car_data_add_form').form('load', row);
	$("#data_category_span").html(row.catalogName);
	$("#add_catalogId").val(row.catalogId).trigger("change");
	$('#car_data_add').panel({title: "编辑数据项"});
	$('#car_data_add').window('open');
}



//逻辑删除数据项
function deleteCarData(){
	var selectRows = $("#car_data_datagrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id[i]=v.dataId;
	});
	var params={};
	params.id=id;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carData/deleteCarData',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#car_data_datagrid').datagrid('reload', {});
			    		} else if (_data.head.retCode == '000007'){
							easyuiMsg('错误',"该数据项存在级联数据，请先删除级联数据！");
						} else {
			    			easyuiMsg('错误', "保存失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}




//////////////////////数据项类别操作///////////////////////////////////











