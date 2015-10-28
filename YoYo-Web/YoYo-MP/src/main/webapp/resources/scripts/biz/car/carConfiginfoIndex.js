/**
 * 功能描述： 汽车数据项的js
 * 
 */
/*$(function() {
	 
	
	$('#car_configinfo_datagrid').datagrid({
		url : _path + '/carData/carDataList',
		columns : [ [ 
		              {
			field:'ck',
			checkbox:"true"
		},
		{
			field : 'dataId',
			hidden: 'true',
			align : 'center',
			halign: 'center',
			title : '数据项ID'			
		},{
			field : 'displayName',
			align : 'center',
			halign: 'center',
			title : '数据项名称'
		}, {
			field : 'dataType',
			align : 'center',
			halign: 'center',
			title : '数据类型',
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
			
		}, {
			field : 'catalogName',
			align : 'center',
			halign: 'center',
			title : '数据项类别'
		}, {
			field : 'listValue',
			align : 'center',
			halign: 'center',
			title : '列表数据项',
			formatter : function(value, row) {
				if(value=='1'){
					return '国产';
				}else if(value=='2'){
					return '进口';
				}
			}
			
		},{
			field : 'configValue',
			align : 'center',
			halign: 'center',
			title : '数据项值',
			formatter: function (value, row) {
                return "<input type=\"checkbox\"  name=\"PD\" value=\"" + 1 + "\" >";
            }
		},{
			field : 'editor',
			halign: 'center',
			title : '操作',
			formatter : function(value, row) {
				var html =  "<a href='javascript:editCarConfig("+JSON.stringify(row)+")'>编辑配置项值</a>";
				return html;
			}
		} ] ],
		toolbar : '#car_configinfo_toolba',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 20,
		height:750,
		pageList : [ 20, 50, 100, 200 ],
		singleSelect : true,
		checkOnSelect:false,
		remoteSort : false,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	
	

	//高级查询数据项列表
	//getCarData(biz.rootPath() + '/carDataCate/carDataCateList','query_catalogId',true);
});*/



/*function onDblClickCell(index, field, value) {
	if (editIndex != index) {
		if (endEditing()) {
			$('#car_configinfo_datagrid').datagrid('selectRow', index).datagrid('beginEdit', index);
			var ed = $('#car_configinfo_datagrid').datagrid('getEditor', { index : index , field : field });
			($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
			editIndex = index;
		} else {
			$('#car_configinfo_datagrid').datagrid('selectRow', editIndex);
		}
	}
}

function onSelect(index, row) {
	if (editIndex != index) {
		if (endEditing()) {
			return true;
		} else {
			$('#car_configinfo_datagrid').datagrid('selectRow', editIndex);
			return false;
		}
	}
}

var editIndex = undefined;
function endEditing() {
	if (editIndex == undefined) {
		return true;
	}
	if ($('#car_configinfo_datagrid').datagrid('validateRow', editIndex)) {
		$('#car_configinfo_datagrid').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}*/

//打开编辑数据项对话框
/*function editCarconfig(){
	$('#car_configinfo_form').form('clear');
	$('#add-car-config-btn').hide();
	$('#update-car-config-btn').show();
	
	var selectRows = $("#car_configinfo_datagrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var select_data = '[';
	$.each(selectRows,function(i,v){
		select_data+='{dataId:"'+v.dataId+'", displayName: "'+v.displayName+'", dataType:"'+v.dataType+'"},';
	});
	select_data =select_data.length>1? select_data.substring(0, select_data.length-1) : select_data;
	select_data+="]";
	if (endEditing()) {
		$('#table-configinfo-add').datagrid('appendRow', {});
		$('#table-configinfo-add').datagrid({ data : eval(select_data) });
		editIndex = $('#table-configinfo-add').datagrid('getRows').length - 1;
	}
	$('#table-configinfo-add').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	
	$('#car_configinfo_add').window('open');
}*/

//打开添加对话框
/*function openCarDataDialog() {
	$('#car_configinfo_form').form('clear');
	$('#add-car-config-btn').show();
	$('#update-car-config-btn').hide();
	
	var selectRows = $("#car_configinfo_datagrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var select_data = '[';
	$.each(selectRows,function(i,v){
		select_data+='{dataId:"'+v.dataId+'", displayName: "'+v.displayName+'", dataType:"'+v.dataType+'"},';
	});
	select_data =select_data.length>1? select_data.substring(0, select_data.length-1) : select_data;
	select_data+="]";
	if (endEditing()) {
		$('#table-configinfo-add').datagrid('appendRow', {});
		$('#table-configinfo-add').datagrid({ data : eval(select_data) });
		editIndex = $('#table-configinfo-add').datagrid('getRows').length - 1;
	}
	$('#table-configinfo-add').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	
	$('#car_configinfo_add').window('open');
}*/



/**
 * 方法描述：新增数据项
 */
/*function saveCarData() {
	if ($('#car_configinfo_form').form('validate')) {
		var params = {};
		params.inserted = JSON.stringify($('#table-configinfo-add').datagrid('getChanges', 'inserted'));
		params.updated = JSON.stringify($('#table-configinfo-add').datagrid('getChanges', 'updated'));
		params.deleted = JSON.stringify($('#table-configinfo-add').datagrid('getChanges', 'deleted'));
		//alert("carId="+$('#carId').val());
		params.carId = $('#carId').val();
		commonAjax(_path + '/carDataCate/saveConfiginfo', params, function(data) {
			$('#car_configinfo_datagrid').datagrid('reload');
			$('#car_configinfo_add').window('close');
		}, function(data) {
			if (data.head.retCode == '000004'){
				easyuiMsg('错误',"该配置项已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		});
	}
	
}*/

function saveCarData1() {
	if ($('#car_configinfo_form').form('validate')) {
		var params = {};
		var temp = '[';
		$("input[name='dataId']").each(
				function(i,n){
					temp += '{dataId:"'+$(this).val();
					//alert("carkId="+$(this).val());
					//alert("var["+i+"]");
					var displayName = $("input[name='displayName']").get(i).value;
					//alert("displayName["+i+"]="+displayName);
					var dataType = $("input[name='dataType']").get(i).value;
					//alert("dataType["+i+"]="+dataType);
					var inputType = $("input[name='configValue']").get(i).type;
					var configValue = '';
					if(inputType == 'text') {
						configValue = $("input[name='configValue']").get(i).value;
						//alert("text="+configValue);
					}else if(inputType == 'checkbox') {
						var bischecked=$('#configValue'+i).is(':checked');
						if(bischecked){
							configValue = 1;
						}else {
							configValue = 0;
						}
						//alert("checkbox="+configValue);
					}else if(inputType == 'hidden') {
						configValue = $("#listvalue"+i).val();
						if(configValue == -1) {
							configValue = '';
						}
					}
					//alert("configValue["+i+"]="+configValue);
					//alert("kk="+kk);
					
					//select_data+='{dataId:"'+v.dataId+'", displayName: "'+v.displayName+'", dataType:"'+v.dataType+'"},';
					temp += '",displayName:"' + displayName;
					temp += '",dataType:"' + dataType;
					temp += '",configValue:"' + configValue;
					temp += '"},';
				});
		temp =temp.length>1? temp.substring(0, temp.length-1) : temp;
		temp += "]";
		//alert("temp ="+temp);
		
		//alert("carId="+$('#carId').val());
		params.carId = $('#carId').val();
		params.inserted = temp;
		commonAjax(_path + '/carDataCate/saveConfiginfo', params, function(data) {
			//easyuiMsg('提示',"保存成功！");
			 var index = parent.$('#index_tabs').tabs('getTabIndex', parent.$('#index_tabs').tabs('getSelected'));
			 var panel = parent.$('#index_tabs').tabs('getTab', index-1).panel('panel');
             var frame = panel.find('iframe');
             try {
                 if (frame.length > 0) {
                     for ( var i = 0; i < frame.length; i++) {
                         frame[i].contentWindow.document.write('');
                         frame[i].contentWindow.close();
                         frame[i].src = frame[i].src;
                     }
                     if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
                         try {
                             CollectGarbage();
                         } catch (e) {
                         }
                     }
                 }
             } catch (e) {
             }
			 parent.$('#index_tabs').tabs('close',"配置参数设置");
			/*parent.$('#index_tabs').tabs('getTab', "车型列表").panel('refresh');*/
			
			 
		}, function(data) {
			if (data.head.retCode == '000000'){
				easyuiMsg('提示',"保存成功！");
			}else if (data.head.retCode == '000004'){
				easyuiMsg('错误',"该配置项已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		});
	}
	
}

function updateCarData() {
	if ($('#car_configinfo_form').form('validate')) {
		var params = {};
		var temp = '[';
		$("input[name='dataId']").each(
				function(i,n){
					temp += '{dataId:"'+$(this).val();
					var displayName = $("input[name='displayName']").get(i).value;
					var dataType = $("input[name='dataType']").get(i).value;
					var inputType = $("input[name='configValue']").get(i).type;
					var configValue = '';
					if(inputType == 'text') {
						configValue = $("input[name='configValue']").get(i).value;
					}else if(inputType == 'checkbox') {
						var bischecked=$('#configValue'+i).is(':checked');
						if(bischecked){
							configValue = 1;
						}else {
							configValue = 0;
						}
					}else if(inputType == 'hidden') {
						configValue = $("#listvalue"+i).val();
						if(configValue == -1) {
							configValue = '';
						}
					}
					temp += '",displayName:"' + displayName;
					temp += '",dataType:"' + dataType;
					temp += '",configValue:"' + configValue;
					temp += '"},';
				});
		temp =temp.length>1? temp.substring(0, temp.length-1) : temp;
		temp += "]";
		params.carId = $('#carId').val();
		params.inserted = temp;
		commonAjax(_path + '/carDataCate/updateConfiginfo', params, function(data) {
			easyuiMsg('提示',"更新成功！");
			parent.$('#index_tabs').tabs('close',"配件参数类型");
		}, function(data) {
			if (data.head.retCode == '000000'){
				easyuiMsg('提示',"更新成功！");
			}else {
				easyuiMsg('错误', "更新失败！");
			}
		});
	}
}

//更新数据项
function updateCarConfig() {
	if ($('#car_configinfo_form').form('validate')) {
		var params = {};
		params.inserted = JSON.stringify($('#table-configinfo-add').datagrid('getChanges', 'inserted'));
		params.updated = JSON.stringify($('#table-configinfo-add').datagrid('getChanges', 'updated'));
		params.deleted = JSON.stringify($('#table-configinfo-add').datagrid('getChanges', 'deleted'));
		//alert("carId="+$('#carId').val());
		params.carId = $('#carId').val();
		commonAjax(_path + '/carDataCate/updateConfiginfo', params, function(data) {
			$('#car_configinfo_datagrid').datagrid('reload');
			$('#car_configinfo_add').window('close');
			window.location.href=_path + '/car/index'; 
		}, function(data) {
			if (data.head.retCode == '000004'){
				easyuiMsg('错误',"该配置项已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		});
	}
	
}

function closeDailog(){
	$("#car_configinfo_add").window('close');
}



//获得车型数据项
/*function getCarData(url,obj,isSelect){
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
}*/


//清除高级查询
/*function carDataAdvanceClear(){
	search_clear('car_data_search_form');
	$("#query_catalogId").val('-1').trigger("change");
}


//高级查询立即筛选
function carDataAdvanceSearch(){
	var param = biz.serializeObject($('#car_data_search_form'));
	$('#car_configinfo_datagrid').datagrid('load', param);
}*/


//打开编辑数据项对话框
/*function editCarData(row){
	$('#car_data_add_form').form('clear');
	$('#car_data_add_form').form('load', row);
	$("#data_category_span").html(row.catalogName);
	$("#catalogId").val(row.catalogId);
	$('#car_data_add').window('open');
}*/



//逻辑删除数据项
/*function deleteCarData(){
	var selectRows = $("#car_data_datagrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id=v.dataId;
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
	
}*/



//////////////////////数据项类别操作///////////////////////////////////


//打开数据项类别对话框
/*function openCarDataCate(){
	
	$('#car_data_cate_table').datagrid({
		url : _path + '/carDataCate/carDataCateList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'catalogName',
			align : 'center',
			title : '数据项类别名称'			
		},{
			field : 'orderId',
			align : 'center',
			title : '排序'			
		},{
			field : 'editor',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a style='margin-right:15px;' href='javascript:editCatalog("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#car_data_cate_tools',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 30, 50, 200 ],
		//singleSelect : true,
		checkOnSelect:false,
		remoteSort : false,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	
	$('#car_data_cate_window').window('open');
}*/


//打开添加数据项类别的对话框
/*function openAndCateDialog(){
	$('#add_cate_form').form('clear');
	$("#data_category_span").html('');
	$('#catalogId').val(''); 
	
	if (endEditing()) {
		var baran = $('#brandId  option:selected').text();
		var factory = $('#factoryId option:selected').text();
		var carDeptName = $('#carDeptName').val();
		var carYearValue = $('#carYearValue').val();
		
		var cargearbox = $("input[name='cargearbox']:checked").val(); 
		var gradename = $("input[name='gradename']:checked").val();
		var carTypeinfo = baran+"  "+factory+"  "+carDeptName+"  "+carYearValue+"  "+cargearbox+"  "+gradename;
		$('#table-configinfo-add').datagrid('appendRow', {});
		$('#table-configinfo-add').datagrid({ data : [{cartype:carTypeinfo}] });
		editIndex = $('#table-configinfo-add').datagrid('getRows').length - 1;
	}
	$('#table-configinfo-add').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	
	$("#car_cate_add_window").window('open');
}*/




//保存数据项类别
/*function saveCate(){
	if ($('#add_cate_form').form('validate')) {
		var param_data = biz.serializeObject($("#add_cate_form"));
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
	}
}*/


//选择数据项类别
/*function getCatalogId(){
	var selectRows = $("#car_data_cate_table").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	console.log(selectRows[0]);
	$("#catalogId").val(selectRows[0].catalogId);
	$("#data_category_span").html(selectRows[0].catalogName);
	
	$('#car_data_cate_window').window('close');
}*/

//取消选择数据项类别
/*function closeCateDialog(){
	$('#car_cate_add_window').window('close');
	$('#car_data_cate_window').window('close');
}*/


//取消添加数据项类别
/*function closeAndCatalog(){
	$('#car_cate_add_window').window('close');
}*/

//删除类别
/*function deleteCatalog(){
	var selectRows = $("#car_data_cate_table").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id=v.catalogId;
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
						} else {
			    			easyuiMsg('错误', "保存失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}*/


//编辑车型数据项类别
/*function editCatalog(row){
	$('#add_cate_form').form('clear');
	$('#add_cate_form').form('load', row);
	$('#catalogId').val(row.catalogId);
	$('#car_cate_add_window').window('open');
}*/

