/**
 * 功能描述： 汽车车系的js
 * 
 */
$(function() {
	$('#table-car-dept').datagrid({
		url : _path + '/carDept/carDeptList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'carDeptName',
			align : 'center',
			halign: 'center',
			title : '车系名称',
			sortable: true,
			options : {querytype : 'like'}
		},/* {
			field : 'keyword',
			align : 'center',
			halign: 'center',
			title : '关键字',
			sortable: true
		},*/ {
			field : 'factoryName',
			align : 'center',
			halign: 'center',
			title : '汽车品牌',
			sortable: true,
			options : {querytype : 'like'}
		}, {
			field : 'gradeName',
			align : 'center',
			halign: 'center',
			sortable : true,
			title : '汽车级别',
			options : {querytype : 'like'}
		},{
			field : 'editor',
			halign: 'center',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:editCarDept("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#toolbar-car_dept',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageList : [ 25, 50, 100, 150 ],
		//singleSelect : true,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : true,
		singleSelect : true,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	//高级查询初始化
	getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','gradeSelectId','carGradeId',true);//获取汽车级别信息下拉列表数据
	getCarFactoryData(biz.rootPath() + '/carFactory/findSelect','searchFactoryId','factoryId',true);//获取厂商信息下拉列表数据

	//getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','carGradeId',true);//获取汽车级别信息下拉列表数据
	//getCarFactoryData(biz.rootPath() + '/carFactory/findSelect','factoryId',true);//获取厂商信息下拉列表数据
});




//获得汽车品牌数据
/*function getCarData(url,obj,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			data+='{ text: "'+n.brandName+'", id:'+n.brandId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj).select2({data:eval(data)});
	});
}
*/
//获得厂商区域数据
/*function getCarFactoryScopeData(url,obj,isSelect){
	$.getJSON(url, function(json) {console.log(json);
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content.rows, function(i, n) {
			data+='{ text: "'+n.carType+'", id:'+n.scopeId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj).select2({data:eval(data)});
	});
}*/

//打开添加对话框
function openSaveDialog() {
	//$("#scope_span").html('');
	//$("#scopeId").val('');
	//$("#brandId").val('-1').trigger("change"); //默认选中
	//$("#imgIconFile").attr('src',biz.defaultPic());
	$('#form-car-dept-add').form('clear');
	$('#window-add-car-dept').panel({title: "添加车系"});
	$("#factoryId").val('-1').trigger("change"); //默认选中
	$("#carGradeId").val('-1').trigger("change"); //默认选中
	/*getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','carGradeId',true);//获取汽车级别信息下拉列表数据
	getCarFactoryData(biz.rootPath() + '/carFactory/findSelect','factoryId',true);//获取厂商信息下拉列表数据
*/	$('#window-add-car-dept').window('open');
}

//获得汽车厂商数据
function getCarFactoryData(url,obj1,obj2,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {console.log(n);
			data+='{ text: "'+n.factoryName+'", id:'+n.factoryId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj1).select2({data:eval(data)});
		$("#"+obj2).select2({data:eval(data)});
	});
}

//获得汽车级别数据
function getCarBrandGradeData(url,obj1,obj2,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {console.log(n);
			data+='{ text: "'+n.gradeName+'", id:'+n.gradeid+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj1).select2({data:eval(data)});
		$("#"+obj2).select2({data:eval(data)});
	});
}



/**
 * 方法描述：新增车系
 */
function saveCarDept() {
	if ($('#form-car-dept-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-car-dept-add"));
		if($("#factoryId").val()=='' || $("#factoryId").val() == null ||$("#factoryId").val() == '-1'){
			easyuiMsg('错误',"请选择厂商名称！");
			return false;
		}
		if($("#carGradeId").val()=='' || $("#carGradeId").val() == null ||$("#carGradeId").val() == '-1'){
			easyuiMsg('错误',"请选择汽车级别！");
			return false;
		}
		
		param_data.factoryid = $("#factoryId").val();
		param_data.gradeId = $("#carGradeId").val();
		if($("#carDeptId").val()=='' || $("#carDeptId").val()==null){ //添加
			$.post(_path + '/carDept/insertCarDept', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-car-dept').datagrid('reload', {});
					$('#window-add-car-dept').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该汽车车系已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}else { //更新
			$.post(_path + '/carDept/updateCarDept', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-car-dept').datagrid('reload', {});
					$('#window-add-car-dept').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该汽车车系已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
		
	}
}


//逻辑删除汽车车系
function deleteCarDept(){
	var selectRows = $("#table-car-dept").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.carDeptId;
	});
	var params={};
	params.ids=ids;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carDept/deleteCarDept',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-car-dept').datagrid('reload', {});
			    		} else if (_data.head.retCode == 'PDE001'){
			    			easyuiMsg('错误', "页面输入数据错误！");
			    		}else if (_data.head.retCode == '000007'){
			    			easyuiMsg('错误', "存在级联数据！");
			    			$('#table-car-dept').datagrid('reload', {});
			    		}else{
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}



//打开编辑车系对话框
function editCarDept(row){
	$('#form-car-dept-add').form('clear');
	$('#form-car-dept-add').form('load', row);
	$('#window-add-car-dept').panel({title: "编辑车系"});
	if(row.iconFile!=''){
		$("#imgIconFile").attr('src',biz.imagePath()+row.iconFile);
	} else {
		$("#imgIconFile").attr('src',biz.defaultPic());
	}
	/*getCarFactoryData(biz.rootPath() + '/carFactory/findSelect','factoryId',true);//获取厂商信息下拉列表数据
	getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','carGradeId',true);//获取汽车级别信息下拉列表数据
*/	//alert("fid="+row.factoryid+",gid="+row.gradeid);
	//$("#factoryId").val(row.factoryid);
    //$("#factoryId").trigger("change",row.factoryid); 
	$("#factoryId").val(row.factoryid).trigger("change");
	$("#carGradeId").val(row.gradeId).trigger("change");
	$("#gradeName_span").html(row.gradeName);
	//$("#carGradeId").val(row.gradeid);
	$('#window-add-car-dept').window('open');
}






//高级查询立即筛选
function CarDeptSearch(){
	var param = biz.serializeObject($('#car_dept_search_form'));
	param.carid = $("#search_car_type").val();
	$('#table-car-dept').datagrid('load', param);
}


//清除高级查询
function advance_clear(){
	search_clear('car_dept_search_form','table-car-dept');
	$('#table-car-dept').datagrid('reload', {});
	$("#searchFactoryId").val('-1').trigger("change");
	$("#gradeSelectId").val('-1').trigger("change");
}




///////////////////车系区域操作///////////////////////
//打开添加级别的对话框
function openAddGradeDialog(){
	$('#add_grade_form').form('clear');
	$("#window-add-grade").window('open');
}


//打开汽车级别对话框
function openGrade(){
	
	$('#factory-grade-table').datagrid({
		url : _path + '/carBrandGrade/carBrandGradeList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'gradeName',
			align : 'center',
			title : '级别名称'			
		},{
			field : 'editor',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a style='margin-right:15px;' href='javascript:editGrade("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#toolbar-car-brand-grade',
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
	
	
	$('#window-car-grade').window('open');
}


//保存级别
function saveGrade(){
	if ($('#add_grade_form').form('validate')) {
		var param_data = biz.serializeObject($("#add_grade_form"));
		if($("#gradeId").val()!=''){ //更新
			$.post(_path + '/carBrandGrade/updateCarBrandGrade', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#factory-grade-table').datagrid('reload', {});
					$('#window-add-grade').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该车系名称已经存在！");
				}else {
					easyuiMsg('错误', "更新失败！");
				}
			}, 'json');
		} else { //添加
			$.post(_path + '/carBrandGrade/insertCarBrandGrade', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#factory-grade-table').datagrid('reload', {});
					$('#window-add-grade').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该车系名称已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
	}
}

//关闭级别添加对话框
function closeAddGrade(){
	$('#window-add-grade').window('close');
}

//删除级别
function deleteGrade(){
	var selectRows = $("#factory-grade-table").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id=v.gradeId;
	});
	var params={};
	params.id=id;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carBrandGrade/deleteCarBrandGrade',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#factory-grade-table').datagrid('reload', {});
			    		}else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}


//编辑级别
function editGrade(row){
	$('#add_grade_form').form('clear');
	$('#add_grade_form').form('load', row);
	$('#gradeId').val(row.gradeId);
	$('#window-add-grade').window('open');
}


//关闭选择级别对话框
function closeGradeDialog(){
	$('#window-add-grade').window('close');
	$('#window-car-grade').window('close');
}


//选择级别
function getGrade(){
	var selectRows = $("#factory-grade-table").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	$("#carGradeId").val(selectRows[0].gradeId);
	$("#gradeName_span").html(selectRows[0].gradeName);
	
	$('#window-car-grade').window('close');
}