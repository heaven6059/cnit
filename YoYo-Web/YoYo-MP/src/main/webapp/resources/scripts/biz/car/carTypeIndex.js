/**
 * 功能描述： 汽车车型的js
 * 
 */
$(function() {
	$('#table-car-type').datagrid({
		url : _path + '/car/carTypeList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},
		{
			field : 'carId',
			align : 'center',
			hidden: 'true',
			title : '车型ID'			
		},{
			field : 'carName',
			align : 'center',
			halign: 'center',
			title : '车型名称',
			sortable: true
		}, {
			field : 'brandname',
			align : 'center',
			halign: 'center',
			title : '品牌',
			sortable: true
		}, {
			field : 'factoryName',
			align : 'center',
			halign: 'center',
			title : '汽车品牌',
			sortable: true
		}, {
			field : 'carDeptName',
			align : 'center',
			halign: 'center',
			title : '汽车车系',
			sortable: true
		},{
			field : 'iconFile',
			align : 'center',
			halign: 'center',
			title : '车型图片',
			/*formatter : function(value, row) {
				if(value != ""){
					var str = yoyo.imagesUrl + value;
					return '<div><a href="' + str + '" target="_blank" ><img src="' + str + '" style="width : 64px; height : 64px;"></a></div>';
				}else{
					return "";
				}
			}*/
		formatter : function(value, row) {
			if (value != "") {
				var str = yoyo.imagesUrl + value;
				return '<a style="width : 60px; height : 60px;" href="' + str + '" target="_blank" ><img  src="' + str + '" width="64px" height="64px;"></a>';
			} else {
				return '<img  src="' + _path + '/resources/images/pre_default.png" width="64px" height="64px;">';
			}
		}
		},{
			field : 'carYearValue',
			align : 'center',
			halign: 'center',
			title : '汽车年款',
			sortable: true
		},{
			field : 'gradeName',
			align : 'center',
			halign: 'center',
			title : '汽车级别',
			sortable: true
		},{
			field : 'editor',
			halign: 'center',
			title : '操作',
			formatter : function(value, row) {
				var html =  "<a style='margin-right:15px;' href='javascript:openView("+row.carId+")'>查看</a>";
				html+= "<span><a style='margin-right:15px;' href='javascript:editCarType("+JSON.stringify(row)+")'>编辑</a></span>";
				if(row.isconfig == 1) {
					html+= "<span><a style='color:red;' href='javascript:setInfo("+JSON.stringify(row)+")'>配置完成</a></span>";
				}else {
					html+= "<span><a  href='javascript:setInfo("+JSON.stringify(row)+")'>配置信息</a></span>";
				}
				
				return html;
			}
		} ] ],
		toolbar : '#toolbar-car_type',
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
		multiSort : true,
		singleSelect : true,
		nowrap : false,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	//汽车品牌改变
	/*$("#brandId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#factoryId").empty();
		$("#factoryId").val('--请选择--').trigger("change");
		getCarFactoryData(biz.rootPath() + '/carFactory/findSelect?brandId=' + $("#brandId").val(), 'factoryId', 'factoryId',true);// 获取厂商信息下拉列表数据
	});
	
	// 汽车厂商改变
	$("#factoryId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#carDeptId").empty();
		$("#carDeptId").val('--请选择--').trigger("change");
		getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#factoryId").val()+'&gradeId='+$("#carGradeId").val(), 'carDeptId','carDeptId', true,null);// 获取车系下拉列表数据
	});
	
	// 汽车级别改变
	$("#carGradeId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#carDeptId").empty();
		$("#carDeptId").val('--请选择--').trigger("change");
		getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#factoryId").val()+'&gradeId='+$("#carGradeId").val(), 'carDeptId','carDeptId', true,null);// 获取车系下拉列表数据
	});
	
	// 汽车车系改变
	$("#carDeptId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#carYearValue").empty();
		$("#carYearValue").val('--请选择--').trigger("change");
		getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + $("#carDeptId").val(), 'carYearValue','carYearValue', true,null);// 获取年款下拉列表数据
	});
	
	
	//搜索汽车品牌改变
	$("#searchBrandId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#searchFactoryId").empty();
		$("#searchFactoryId").val('--请选择--').trigger("change");
		getCarFactoryData(biz.rootPath() + '/carFactory/findSelect?brandId=' + $("#searchBrandId").val(), 'searchFactoryId', 'searchFactoryId',true);// 获取厂商信息下拉列表数据
	});
	
	// 搜索汽车厂商改变
	$("#searchFactoryId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#searchCarDeptId").empty();
		$("#searchCarDeptId").val('--请选择--').trigger("change");
		getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#searchFactoryId").val()+'&gradeId='+$("#searchCarGradeId").val(), 'searchCarDeptId','searchCarDeptId', true,null);// 获取车系下拉列表数据
	});
	
	// 搜索汽车级别改变
	$("#searchCarGradeId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#searchCarDeptId").empty();
		$("#searchCarDeptId").val('--请选择--').trigger("change");
		getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#searchFactoryId").val()+'&gradeId='+$("#searchCarGradeId").val(), 'searchCarDeptId','searchCarDeptId', true,null);// 获取车系下拉列表数据
	});
	
	// 搜索汽车车系改变
	$("#searchCarDeptId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#searchCarYearValue").empty();
		$("#searchCarYearValue").val('--请选择--').trigger("change");
		getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + $("#searchCarDeptId").val(), 'searchCarYearValue','searchCarYearValue', true,null);// 获取年款下拉列表数据
	});*/
	
	//汽车品牌改变
	$("#brandId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#factoryId").empty();
		$("#factoryId").select2({placeholder : "--请选择--"});
		
		$("#carDeptId").empty();
		$("#carDeptId").select2({placeholder : "--请选择--"});
		
		$("#carYearValue").empty();
		$("#carYearValue").select2({placeholder : "--请选择--"});

		if($("#brandId").val()!='-1'){
			getCarFactoryData(biz.rootPath() + '/carFactory/findSelect?brandId=' + $("#brandId").val(), 'factoryId', 'factoryId',true,null);// 获取厂商信息下拉列表数据
		}
	});
	
	// 汽车厂商改变
	$("#factoryId").on("select2:select", function(e) {
		
		$("#carDeptId").empty();
		$("#carDeptId").select2({placeholder : "--请选择--"});
		
		$("#carYearValue").empty();
		$("#carYearValue").select2({placeholder : "--请选择--"});
		if($("#factoryId").val()!='-1'){
			getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#factoryId").val()+'&gradeId='+$("#carGradeId").val(), 'carDeptId','carDeptId', true,null);// 获取车系下拉列表数据
		}
		
	});
	
	// 汽车级别改变
	$("#carGradeId").on("select2:select", function(e) {
		
		$("#carDeptId").empty();
		$("#carDeptId").select2({placeholder : "--请选择--"});
		
		$("#carYearValue").empty();
		$("#carYearValue").select2({placeholder : "--请选择--"});
		if($("#carGradeId").val()!='-1'){
			getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#factoryId").val()+'&gradeId='+$("#carGradeId").val(), 'carDeptId','carDeptId', true,null);// 获取车系下拉列表数据
		}
		
	});

	// 汽车车系改变
	$("#carDeptId").on("select2:select", function(e) {

		// 清空后面下拉框的数
		$("#carYearValue").empty();
		$("#carYearValue").select2({placeholder : "--请选择--"});

		if($("#carDeptId").val()!='-1'){
			getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + $("#carDeptId").val(), 'carYearValue','carYearValue', true, null);// 获取年款下拉列表数据
		}
		
	});
	
	//汽车品牌改变
	$("#searchBrandId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#searchFactoryId").empty();
		$("#searchFactoryId").select2({placeholder : "--请选择--"});
		
		$("#searchCarDeptId").empty();
		$("#searchCarDeptId").select2({placeholder : "--请选择--"});
		
		$("#searchCarYearValue").empty();
		$("#searchCarYearValue").select2({placeholder : "--请选择--"});

		if($("#searchBrandId").val()!='-1'){
			getCarFactoryData(biz.rootPath() + '/carFactory/findSelect?brandId=' + $("#searchBrandId").val(), 'searchFactoryId', 'searchFactoryId',true,null);// 获取厂商信息下拉列表数据
		}
	});
	
	// 汽车厂商改变
	$("#searchFactoryId").on("select2:select", function(e) {
		
		$("#searchCarDeptId").empty();
		$("#searchCarDeptId").select2({placeholder : "--请选择--"});
		
		$("#searchCarYearValue").empty();
		$("#searchCarYearValue").select2({placeholder : "--请选择--"});
		if($("#searchFactoryId").val()!='-1'){
			getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#searchFactoryId").val()+'&gradeId='+$("#searchCarGradeId").val(), 'searchCarDeptId','searchCarDeptId', true,null);// 获取车系下拉列表数据
		}
		
	});
	
	// 汽车级别改变
	$("#searchCarGradeId").on("select2:select", function(e) {
		
		$("#searchCarDeptId").empty();
		$("#searchCarDeptId").select2({placeholder : "--请选择--"});
		
		$("#searchCarYearValue").empty();
		$("#searchCarYearValue").select2({placeholder : "--请选择--"});
		if($("#searchCarGradeId").val()!='-1'){
			getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#searchFactoryId").val()+'&gradeId='+$("#searchCarGradeId").val(), 'searchCarDeptId','searchCarDeptId', true,null);// 获取车系下拉列表数据
		}
		
	});

	// 汽车车系改变
	$("#searchCarDeptId").on("select2:select", function(e) {

		// 清空后面下拉框的数
		$("#searchCarYearValue").empty();
		$("#searchCarYearValue").select2({placeholder : "--请选择--"});

		if($("#searchCarDeptId").val()!='-1'){
			getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + $("#searchCarDeptId").val(), 'searchCarYearValue','searchCarYearValue', true, null);// 获取年款下拉列表数据
		}
		
	});


	
	getCarData(biz.rootPath() + '/brandTypeShip/findShip?identifier='+yoyo.car,'searchBrandId','brandId',true);//获取品牌信息下拉列表数据
	//getCarFactoryData(biz.rootPath() + '/carFactory/findSelect','searchFactoryId','factoryId',true);//获取厂商信息下拉列表数据
	//getCarDeptData(biz.rootPath() + '/carDept/findSelect','searchCarDeptId','carDeptId',true);//获取车系下拉列表数据
	//getCarYearData(biz.rootPath() + '/carYear/findSelect','searchCarYearValue','carYearValue',true);//获取年款下拉列表数据
	getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','searchCarGradeId','carGradeId',true);//获取汽车级别信息下拉列表数据
	
	//getCarData(biz.rootPath() + '/brandTypeShip/findShip?identifier='+yoyo.car,'brandId',true);//获取品牌信息下拉列表数据
	//getCarFactoryData(biz.rootPath() + '/carFactory/findSelect','factoryId',true);//获取厂商信息下拉列表数据
	//getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','carGradeId',true);//获取汽车级别信息下拉列表数据
	//getCarDeptData(biz.rootPath() + '/carDept/findSelect','carDeptId',true);//获取车系下拉列表数据
	//getCarYearData(biz.rootPath() + '/carYear/findSelect','carYearValue',true);//获取年款下拉列表数据
	String.prototype.trim=function() {  
	    return this.replace(/(^\s*)|(\s*$)/g,'');  
	};
});

function setInfo(row) {
	window.parent.addTabs('/carData/carDataEditList?carId='+row.carId+"&isconfig="+row.isconfig,'配置参数设置');
}



//打开编辑车型对话框
function editCarType(row){
	$('#form-car-type-add').form('clear');
	$('#form-car-type-add').form('load', row);
	//alert("carid="+row.carId);
	$('#window-add-car-type').panel({title: "编辑车型"});
	
	//alert("carid1="+$('#carId').val());
	
	//getCarData(biz.rootPath() + '/brandTypeShip/findShip?identifier='+yoyo.car,'searchBrandId','brandId',true);//获取品牌信息下拉列表数据
	//getCarFactoryData(biz.rootPath() + '/carFactory/findSelect','searchFactoryId','factoryId',true);//获取厂商信息下拉列表数据
	//getCarDeptData(biz.rootPath() + '/carDept/findSelect','searchCarDeptId','carDeptId',true);//获取车系下拉列表数据
	//getCarYearData(biz.rootPath() + '/carYear/findSelect','searchCarYearValue','carYearValue',true);//获取年款下拉列表数据
	//getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','searchCarGradeId','carGradeId',true);//获取汽车级别信息下拉列表数据
	
	$("#brandId").val(row.brandId).trigger("change");
	$('#carGradeId').val(row.gradeId).trigger("change");
	getCarFactoryData(biz.rootPath() + '/carFactory/findSelect?brandId=' + row.brandId, 'factoryId', 'factoryId',true,row.factoryId);
	//$('#factoryId').val(row.factoryId).trigger("change");
	getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + row.factoryId+'&gradeId='+row.gradeId, 'carDeptId','carDeptId', true,row.carDeptId);// 获取车系下拉列表数据
	//$('#carDeptId').val(row.carDeptId).trigger("change");
	getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + row.carDeptId, 'carYearValue','carYearValue', true, row.carYearId);// 获取年款下拉列表数据
	//$('#carYearValue').val(row.carYearId).trigger("change");
	
	//alert("status="+row.status);
	
	$('#keyword').val(row.keyword);
	$('#price').val(row.price);
	var carName = row.carName;
	var arr = carName.split(" ");
	var name = arr[0];
	var cargearbox = arr[3];
	$('#carName').val(name);
	
	if(cargearbox == '手动') {
		//$("#cargearbox2").removeProp("checked");
		$("#cargearbox1").prop("checked","checked");
	}else if(cargearbox == '自动'){
		//$("#cargearbox1").removeProp("checked");
		$("#cargearbox2").prop("checked","checked");
	}
	//alert("ss="+row.status);
	if(row.status == '1') {
		//$("#status2").removeProp("checked");
		$("#status1").prop("checked","checked");
	}else if(row.status == '2'){
		//$("#status1").removeProp("checked");
		$("#status2").prop("checked","checked");
	}
	
	if(row.iconFile!=''){
		$("#imgIconFile").attr('src',yoyo.imageUrl+row.iconFile);
	} else {
		$("#imgIconFile").attr('src',biz.defaultPic());
	}
	
	$('#add-car-type-btn').hide();
	$('#update-car-type-btn').show();
	
	//$('#window-add-car-type').window('open');
	setTimeout("$('#window-add-car-type').window('open')",1000);
}

/**
 * 更新车型
 */
function updateCarType(){
	if ($('#form-car-type-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-car-type-add"));
		if($("#brandId").val()=='' || $("#brandId").val() == null ||$("#brandId").val()=='-1'){
			easyuiMsg('错误',"请选择汽车品牌！");
			return false;
		}
		if($("#factoryId").val()=='' || $("#factoryId").val() == null ||$("#factoryId").val()=='-1'){
			easyuiMsg('错误',"请选择汽车厂商！");
			return false;
		}
		if($("#carDeptId").val()=='' || $("#carDeptId").val() == null ||$("#carDeptId").val()=='-1'){
			easyuiMsg('错误',"请选择汽车厂商！");
			return false;
		}
		if($("#carYearValue").val()=='' || $("#carYearValue").val() == null ||$("#carYearValue").val()=='-1'){
			easyuiMsg('错误',"请选择汽车年款！");
			return false;
		}
		param_data.carYearValue=$("#carYearValue").find("option:selected").text();
		param_data.carYearId=$("#carYearValue").val();
		param_data.carDeptName=$("#carDeptId").find("option:selected").text();
		param_data.gradeName=$("#carGradeId").find("option:selected").text();
		//alert("carId="+$("#carId").val());
		param_data.carId = $("#carId").val();
		$.post(_path + '/car/updateCarType', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-car-type').datagrid('reload', {});
				$('#window-add-car-type').window('close');
			} else if (_data.head.retCode == '000001'){
				easyuiMsg('错误',"该车型名称不存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}

/**
 * 方法描述：新增车型
 */
function saveCarType() {
	if ($('#form-car-type-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-car-type-add"));
		if($("#brandId").val()=='' || $("#brandId").val() == null ||$("#brandId").val()=='-1'){
			easyuiMsg('错误',"请选择汽车品牌！");
			return false;
		}
		if($("#factoryId").val()=='' || $("#factoryId").val() == null ||$("#factoryId").val()=='-1'){
			easyuiMsg('错误',"请选择汽车厂商！");
			return false;
		}
		if($("#carDeptId").val()=='' || $("#carDeptId").val() == null ||$("#carDeptId").val()=='-1'){
			easyuiMsg('错误',"请选择汽车厂商！");
			return false;
		}
		
		if($("#carYearValue").val()=='' || $("#carYearValue").val() == null ||$("#carYearValue").val()=='-1'){
			easyuiMsg('错误',"请选择汽车年款！");
			return false;
		}
		
		if($("#iconFile1").val()=='' || $("#iconFile1").val() == null ||$("#iconFile1").val()=='-1'){
			easyuiMsg('错误',"请选择图片！");
			return false;
		}
		
		var cargearbox = $('input:radio[name="cargearbox"]:checked').val();
		if(cargearbox =='' || cargearbox == null){
			easyuiMsg('错误',"请选择变速箱！");
			return false;
		}
		
		param_data.carYearValue=$("#carYearValue").find("option:selected").text();
		param_data.carYearId=$("#carYearValue").val();
		//alert("cartDeptID="+$("#carDeptId").find("option:selected").text());
		//alert("param_data="+param_data);
		//param_data.carid = $("#car_type").val();
		param_data.carDeptName=$("#carDeptId").find("option:selected").text();
		param_data.gradeName=$("#carGradeId").find("option:selected").text();
		$.post(_path + '/car/insertCarType', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-car-type').datagrid('reload', {});
				$('#window-add-car-type').window('close');
			} else if (_data.head.retCode == '000004'){
				easyuiMsg('错误',"该车型名称已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}

//打开添加对话框
function openSaveDialog() {
	$('#form-car-type-add').form('clear');
	$('#add-car-type-btn').show();
	$('#update-car-type-btn').hide();
	$('#window-add-car-type').panel({title: "添加车型"});
	
	/*$("#brandId").val('-1').trigger("change"); //默认选中
	$("#factoryId").val('-1').trigger("change"); //默认选中
	$("#carGradeId").val('-1').trigger("change"); //默认选中
	$("#carDeptId").val('-1').trigger("change"); //默认选中
	$("#carYearValue").val('-1').trigger("change"); //默认选中
*/	
	
	//getCarData(biz.rootPath() + '/brandTypeShip/findShip?identifier='+yoyo.car,'searchBrandId','brandId',true);//获取品牌信息下拉列表数据
	$("#factoryId").select2({placeholder : "--请选择--"});
	//$("#carGradeId").select2({placeholder : "--请选择--"});
	$("#carDeptId").select2({placeholder : "--请选择--"});
	$("#carYearValue").select2({placeholder : "--请选择--"});
	$("#brandId").val('--请选择--').trigger("change");
	$("#carGradeId").val('--请选择--').trigger("change");
	
	$("#imgIconFile").attr('src','');
	
	
	
	//TODO 整车的id=25 部署时要修改
	//getCarData(biz.rootPath() + '/brandTypeShip/findShip?identifier='+yoyo.car,'brandId',true);//获取品牌信息下拉列表数据
	//getCarFactoryData(biz.rootPath() + '/carFactory/findSelect','factoryId',true);//获取厂商信息下拉列表数据
	//getCarBrandGradeData(biz.rootPath() + '/carBrandGrade/findSelect','carGradeId',true);//获取汽车级别信息下拉列表数据
	//getCarDeptData(biz.rootPath() + '/carDept/findSelect','carDeptId',true);//获取车系下拉列表数据
	//getCarYearData(biz.rootPath() + '/carYear/findSelect','carYearValue',true);//获取年款下拉列表数据
	
	$('#window-add-car-type').window('open');
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



function clearDetail() {
	//$('#form-spec-add').form('clear');
	$('#add_brand_table').datagrid({ data : [ ] });
	//_specId = null;
}

function onDblClickCell(index, field, value) {
	if (editIndex != index) {
		if (endEditing()) {
			$('#car-type-view-table').datagrid('selectRow', index).datagrid('beginEdit', index);
			var ed = $('#car-type-view-table').datagrid('getEditor', { index : index , field : field });
			($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
			editIndex = index;
		} else {
			$('#car-type-view-table').datagrid('selectRow', editIndex);
		}
	}
}

/*function newSpecValue() {
	if (endEditing()) {
		var baran = $('#brandId  option:selected').text();
		var factory = $('#factoryId option:selected').text();
		var carDeptName = $('#carDeptName').val();
		var carYearValue = $('#carYearValue').val();
		
		var cargearbox = $("input[name='cargearbox']:checked").val(); 
		var gradename = $("input[name='gradename']:checked").val();
		var carTypeinfo = baran+"  "+factory+"  "+carDeptName+"  "+carYearValue+"  "+cargearbox+"  "+gradename;
		$('#table-spec-values-add').datagrid('appendRow', {});
		$('#table-spec-values-add').datagrid({ data : [{cartype:carTypeinfo}] });
		editIndex = $('#table-spec-values-add').datagrid('getRows').length - 1;
	}
	$('#table-spec-values-add').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
}*/

/*function specValuesEditor(value, row, index) {
	return '<span><a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:setInfo()">汽车配置信息</a></span>'+
	'<a onclick="javascript:deleteTableSpecValuesRow(' + index + ')">删除</a>';
}*/

/*function deleteTableSpecValuesRow(index) {
	$('#table-spec-values-add').datagrid('deleteRow', index);
}

var editIndex = undefined;
function endEditing() {
	if (editIndex == undefined) {
		return true;
	}
	if ($('#table-spec-values-add').datagrid('validateRow', editIndex)) {
		$('#table-spec-values-add').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}*/

//逻辑删除车型
function deleteCarType(){
	var selectRows = $("#table-car-type").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.carId;
	});
	var params={};
	params.ids=ids;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/car/deleteCarType',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-car-type').datagrid('reload', {});
			    		} else if (_data.head.retCode == 'PDE001'){
			    			easyuiMsg('错误', "页面输入数据错误！");
			    		}else if (_data.head.retCode == '000007'){
			    			easyuiMsg('错误', "存在级联数据！");
			    			$('#table-car-type').datagrid('reload', {});
			    		}else{
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}

//打开厂商区域对话框
function openView(carId){
	$('#car-type-view-table').datagrid('loadData', { total: 0,rows: [] });
	var params={};
	params.id=carId;
	//var temp;
	var tempstr = "[";
	//var tempArray = new Array();
	$.ajax({
	    url:_path + '/car/getDetail',
	    type:"post",
	    data:params,
	    dataType:"json",
	    success:function(_data){
	    	if (_data.head.retCode == '000000') {
	    		$('#car-type-view-table').datagrid('appendRow', {});
	    		$.each(_data.content, function(i, n) {console.log(n);
	    		tempstr += '{"carName":"'+n.carName+'","keyword":"'+n.keyword+'","viewCount":"'+(n.viewCount=='null'?0:n.viewCount)+'","status":"'+(n.status==1?"在产":"停产")+'","price":"'+n.price+'","displayName":"'+n.displayName+'","displayValue":"'+n.displayValue+'"},';
	    		});
	    		tempstr=tempstr.length>1?tempstr.substring(0, tempstr.length-1):tempstr; //除开没有数据的情况
	    		tempstr += "]";
	    		//alert("temp="+tempstr);
	    		//alert("data="+eval("(" + tempstr + ")"));
	    		$('#car-type-view-table').datagrid({
	    			rownumbers : true,
	    			fitColumns : true,
	    			width : ($(window).width() * 0.99),
	    			height : ($(window).height() * 0.95),
	    			checkOnSelect:false,
	    			selectOnCheck:false,
	    			remoteSort : false,
	    			multiSort : true,
	    			nowrap : false,
	    			data:eval("(" + tempstr + ")")
	    			});
    		} else if(_data.head.retCode == '000001'){
    			easyuiMsg('提示', "没有配置项数据！");
    		} else {
    			//$('#car-type-view-table').datagrid('loadData', { total: 0, rows: [] });
    			easyuiMsg('错误', "数据加载失败！");
    		}
	    }
	});
	
	  $('#window-view-car-type').window('open');
}

//上传图片
/*function submitForm(file) {
	var filev = document.getElementById(file);
	var patn = /\.jpg$|\.png$|\.jpeg$|\.gif$|\.JPG$|\.PNG$|\.JPEG$|\.GIF$/;
	if (filev.value != null && patn.test(filev.value)) {
		$.messager.progress({
			title : '提示',
			text : '正在上传....'
		});
		$.ajaxFileUpload({
			url : biz.rootPath() + '/image/uploadImg',
			secureuri : false,
			fileElementId : file,
			dataType : 'json',
			success : function(data, status) {
				$.messager.progress('close');
				if(data.retCode == "000000"){
					$.messager.alert('提示', '上传成功！', 'info');
					var imgID = file + "1";
					var img = document.getElementById(imgID);
					img.value = data.retMsg;
				}else if (data.retCode == "000003"){
					$.messager.alert('提示', '上传失败！' + data.retMsg, 'error');
					var pre_img = document.getElementById("img" + file);
					pre_img.src = biz.rootPath() + "/resources/images/pre_default.png";
				}else{
					$.messager.alert('提示', '上传失败！', 'error');
				}
			},
			error : function(data, status, e) {
				$.messager.progress('close');
			}
		});
	} else {
		$.messager.alert('提示', '选择图片上传!', 'error');
	}
}*/

//获得汽车品牌数据
function getCarData(url,obj1,obj2,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {console.log(n);
			data+='{ text: "'+n.brandName+'", id:'+n.brandId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj1).select2({data:eval(data)});
		$("#"+obj2).select2({data:eval(data)});
	});
}

//获得汽车厂商数据
function getCarFactoryData(url,obj1,obj2,isSelect,fId){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {console.log(n);
			//data+='{ text: "'+n.factoryName+'", id:'+n.factoryId+'},';
			if(n.factoryId==fId){ //选择指定值
				data += '{ text: "' + n.factoryName + '", id:' + n.factoryId + ',selected:true},';
			}else{
				data += '{ text: "' + n.factoryName + '", id:' + n.factoryId + '},';
			}
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj1).select2({data:eval(data)});
		$("#"+obj2).select2({data:eval(data)});
	});
}

//获得汽车车系数据
function getCarDeptData(url,obj1,obj2,isSelect,deptId){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {console.log(n);
			//data+='{ text: "'+n.carDeptName+'", id:'+n.carDeptId+'},';
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

//获得汽车年款数据-ssd
function getCarYearData(url,obj1,obj2,isSelect,yearId){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {console.log(n);
			//data+='{ text: "'+n.carYearValue+'", id:'+n.id+'},';
			if(n.id==yearId){ //选择指定值
				data += '{ text: "' + n.carYearValue + '", id:' + n.id + ',selected:true},';
			}else{
				data += '{ text: "' + n.carYearValue + '", id:' + n.id + '},';
			}
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj1).select2({data:eval(data)});
		$("#"+obj2).select2({data:eval(data)});
	});
}



function closeDailog(){
	$("#window-add-car-type").window('close');
}

function closeDetailDialog(){
	$("#window-view-car-type").window('close');
}


//高级查询立即筛选
function advanceSearch(){
	

/*	$('#search_form').form('clear');
	
	$("#brandId").val('-1').trigger("change"); //默认选中
	$("#factoryId").val('-1').trigger("change"); //默认选中
	$("#carGradeId").val('-1').trigger("change"); //默认选中
	$("#carDeptId").val('-1').trigger("change"); //默认选中
	$("#carYearValue").val('-1').trigger("change"); //默认选中
*/	
	var param = biz.serializeObject($('#search_form'));
	
	$('#table-car-type').datagrid('load', param);
}

//清除高级查询
function advance_clear(){
	search_clear('search_form','table-car-type');
	$('#table-car-type').datagrid('reload', {});
	$("#searchBrandId").val('-1').trigger("change");
	$("#searchFactoryId").val('-1').trigger("change");
	$("#searchCarDeptId").val('-1').trigger("change");
	$("#searchCarYearValue").val('-1').trigger("change");
	$("#searchCarGradeId").val('-1').trigger("change");
}


//编辑关联的保养类别
/*function editfactoryCate(row){
	$("#selectId").val(row.factoryId);
	$("#factory_select_parent").empty();
	$("#factory_select_child").empty();
	initCategory('factory_select_parent','factory_select_child','factory_category_ids',28);
	//获取分类信息
	$.ajax({
		url:_path + '/carfactory/findfactoryShip?factoryId='+row.factoryId,
 	    type:"post",
 	    dataType:"json",
 	    success:function(data){
 	    	if (data.head.retCode == '000000') {
 	    		select_data = '[';
	    			$.each(data.content,function(i,v){console.log(v);
	    				select_data+='{ text: "'+v.categoryName+'", id:'+v.categoryId+',selected:true},';
	    			});
	    			select_data =select_data.length>1? select_data.substring(0, select_data.length-1) : select_data;
	    			select_data+="]";
	    			$("#factory_category_ids").empty();
	    			$("#factory_category_ids").select2({data:eval(select_data)});
	    		} else {
	    			easyuiMsg('错误', "获取分类信息失败！");
	    		}
 	    }
	});
	$("#window-factory-category").window('open');
	
}*/
/*
function closeCategory(){	$("#window-factory-category").window('close');}

//更新保养周期与保养类别的关系
function updatefactoryCategory(){
	var param_data = {};
	param_data.categoryIds=$("#factory_category_ids").val();
	param_data.factoryId = $("#selectId").val();
	$.post(_path + '/carfactory/updatefactoryCate', param_data, function(_data) {
		if (_data.head.retCode == '000000') {
			$("#selectId").val('');
			$('#window-factory-category').window('close');
		} else {
			easyuiMsg('错误', "保存失败！");
		}
	}, 'json');
}
*/

