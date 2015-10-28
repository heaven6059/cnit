/**
 * 功能描述： 汽车车系年款的js
 * 
 */
var now = new Date();  
var year = now.getFullYear(); 

$(function() {
	
	$('#table-car-year').datagrid({
		url : _path + '/carYear/carYearList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'factoryName',
			align : 'center',
			halign: 'center',
			title : '汽车品牌',
			sortable: true
		},{
			field : 'carDeptName',
			align : 'center',
			halign: 'center',
			title : '车系名称',
			sortable: true
		}, {
			field : 'carYearValue',
			align : 'center',
			halign: 'center',
			title : '年份值',
			sortable: true
		},{
			field : 'editor',
			halign: 'center',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:editCarYear("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#toolbar-car_year',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageList : [ 25, 50, 100, 150 ],
		//singleSelect : true,
		selectOnCheck:false,
		checkOnSelect:false,
		remoteSort : true,
		multiSort : true,
		singleSelect : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	//TODO 获得汽车品牌数据  整车的id=25 部署时要修改
	//getCarData(biz.rootPath() + '/brandTypeShip/findBrandCateShip?categoryId=25','brandId',true);
	// 汽车厂商改变
	$("#factoryId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#carDeptId").empty();
		$("#carDeptId").select2({placeholder : "--请选择--"});

		if($("#factoryId").val()!='-1'){
			getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#factoryId").val(), 'carDeptId', true, null);// 获取车系下拉列表数据
		}
		
	});
	getCarFactoryData(biz.rootPath() + '/carFactory/findSelect', 'searchFactoryId','factoryId', true, null);

	//getCarFactoryData(biz.rootPath() + '/carFactory/findSelect', 'factoryId', true, null);
	//高级查询初始化
	$("#searchFactoryId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#searchCarDeptId").empty();
		$("#searchCarDeptId").select2({placeholder : "--请选择--"});

		if($("#searchFactoryId").val()!='-1'){
			getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#searchFactoryId").val(), 'searchCarDeptId', true, null);// 获取车系下拉列表数据
		}
		
	});
	
	//高级查询初始化
	//getCarDeptData(biz.rootPath() + '/carDept/findSelect','searchCarDeptId','carDeptId',true);//获取车系信息下拉列表数据
	
	//getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','gradeSelectId',true);//获取汽车级别信息下拉列表数据

	/*$('#dd').datebox({
	    required:true,
	    onSelect: function(date){
			alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
			$('#dd').datebox('setValue', '6/1/2012');	// set datebox value
		}
	});*/
	
	/*var obj=document.getElementById("s1");
	var searchObj=document.getElementById("searchYear");
	for(var i=1953;i<2054;i++){
	var op=new Option(i,i);
	obj.add(op);
	
	}*/
	
	_getSelect(year);
   
	
	/*for(var i=1953;i<2054;i++){
		var op=new Option(i,i);
		searchObj.add(op);
		
		}*/
});



var $select = null; 
var $selectSerach = null;
function _getSelect(year){  
    if(!year){  
        year = new Date().getFullYear();  
    }  
      
    $select = $("#s2");
    $selectSerach = $("#searchYear");
    for(var i = 45; i >=0; i--){  
        $select.append($("<option/>").text(year - i )); 
        $selectSerach.append($("<option/>").text(year - i ));
    }  
    for(var i = 1; i <= 50; i++){  
        $select.append($("<option/>").text(year + i )); 
        $selectSerach.append($("<option/>").text(year + i ));
    }  
    $select.find("option").each(function(){  
        if($(this).text() == year){  
            $(this).prop("selected", "selected"); 
            //$(this).selected = true;
        }  
    });
    $selectSerach.find("option").each(function(){  
        if($(this).text() == year){  
            $(this).attr("selected", "selected"); 
        }  
    });
    //return $select;  
}


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
	$('#form-car-year-add').form('clear');
	$("#s2").attr('multiple','multiple');
	$('#window-add-car-year').panel({title: "添加年款"});
	//$("#carDeptId").val('-1').trigger("change"); //默认选中
	//getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','gradeId',true);//获取汽车级别信息下拉列表数据
	//getCarDeptData(biz.rootPath() + '/carDept/findSelect','carDeptId',true);//获取车系信息下拉列表数据
	//getCarFactoryData(biz.rootPath() + '/carFactory/findSelect', 'factoryId', true, null);
	$("#carDeptId").empty();

	$("#carDeptId").select2({placeholder : "--请选择--"});
	$("#factoryId").val(-1).trigger("change");
	$('#window-add-car-year').window('open');
	  _getSelect(year);
}

//获得汽车车系数据
function getCarDeptData(url,obj1,obj2,isSelect,deptId){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		/*$.each(json.content, function(i, n) {
			data+='{ text: "'+n.carDeptName+'", id:'+n.carDeptId+'},';
		});*/
		$.each(json.content, function(i, n) {
			if(n.carDeptId==deptId){ //选择指定值
				data += '{ text: "' + n.carDeptName + '", id:' + n.carDeptId + ',selected:true},';
			}else{
				data += '{ text: "' + n.carDeptName + '", id:' + n.carDeptId + '},';
			}
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj1).select2({data:eval(data)});
		$("#"+obj2).select2({data:eval(data)});
	});
}

//获得汽车厂商数据
function getCarFactoryData(url, obj1,obj2, isSelect, factoryId) {
	$("#select-factory").empty();
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if (n.factoryId == factoryId) {
				data += '{ text: "' + n.factoryName + '", id:' + n.factoryId + ' ,selected:true},';
			} else {
				data += '{ text: "' + n.factoryName + '", id:' + n.factoryId + '},';
			}
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		//$("#" + obj).select2({data : eval(data)});
		$("#"+obj1).select2({data:eval(data)});
		$("#"+obj2).select2({data:eval(data)});
	});
}

//获得汽车级别数据
function getCarBrandGradeData(url,obj,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {console.log(n);
			data+='{ text: "'+n.gradeName+'", id:'+n.gradeId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj).select2({data:eval(data)});
	});
}




/**
 * 方法描述：新增车系年款
 */
function saveCarYear() {
	if ($('#form-car-year-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-car-year-add"));
		if($("#carDeptId").val()=='' || $("#carDeptId").val() == null ||$("#carDeptId").val() == '-1'){
			easyuiMsg('错误',"请选择车系名称！");
			return false;
		}
		
		
		param_data.carDeptId = $("#carDeptId").val();
		if($("#carYearId").val()=='' || $("#carYearId").val()==null){ //添加
			$.post(_path + '/carYear/insertCarYear', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					easyuiMsg('提示', "保存成功！");
					$('#table-car-year').datagrid('reload', {});
					$('#window-add-car-year').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('提示', "数据已存在！");
				} else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}else { //更新
			param_data.id=$("#carYearId").val();
			$.post(_path + '/carYear/updateCarYear', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-car-year').datagrid('reload', {});
					$('#window-add-car-year').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('提示', "数据已存在！");
				} else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
		
	}
}

//高级查询立即筛选
function advanceSearch(){
	var param = biz.serializeObject($('#search_form'));
	param.carDeptId = $("#searchCarDeptId").val();
	param.carYearValue = $("#searchYear").val();
	$('#table-car-year').datagrid('load', param);
}

//清除高级查询
function advance_clear(){
	search_clear('search_form','table-car-year');
	$('#table-car-year').datagrid('reload', {});
	//$("#searchFactoryId").val('-1').trigger("change");
	//$("#searchCarDeptId").val('-1').trigger("change");
	$("#searchFactoryId").select2({placeholder : "--请选择--"});
	$("#searchCarDeptId").select2({placeholder : "--请选择--"});
	
	$("#searchYear").val('-1').trigger("change");
}


//逻辑删除汽车车系年款
function deleteCarYear(){
	var selectRows = $("#table-car-year").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.id;
	});
	var params={};
	params.ids=ids;
	
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carYear/deleteCarYear',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-car-year').datagrid('reload', {});
			    		} else if (_data.head.retCode == 'PDE001'){
			    			easyuiMsg('错误', "页面输入数据错误！");
			    		}else if (_data.head.retCode == '000007'){
			    			easyuiMsg('错误', "存在级联数据！");
			    			$('#table-car-year').datagrid('reload', {});
			    		}else{
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}



//打开编辑车系年款对话框
function editCarYear(row){
	$('#form-car-year-add').form('clear');
	$('#form-car-year-add').form('load', row);
	$("#s2").removeAttr('multiple');
	$('#window-add-car-year').panel({title: "编辑年款"});
	//getCarDeptData(biz.rootPath() + '/carDept/findSelect','carDeptId',true);//获取车系信息下拉列表数据
	//getCarFactoryData(biz.rootPath() + '/carFactory/findSelect', 'factoryId', true, null);
	$("#factoryId").val(row.factoryId).trigger("change");
	getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + row.factoryId, 'carDeptId','carDeptId', true, row.carDeptId);// 获取车系下拉列表数据
	//$("#carDeptId").val(row.carDeptId).trigger("change");
	$("#carYearId").val(row.id);
	 setTimeout("$('#window-add-car-year').window('open')",1000);
	//$('#window-add-car-year').window('open');
}






