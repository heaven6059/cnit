/**
 * 功能描述： 汽车厂商的js
 * 
 */
$(function() {
	 
	
	$('#table-car-factory').datagrid({
		url : _path + '/carFactory/carFactoryList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'factoryName',
			halign : 'center',
			sortable : true,
			title : '品牌名称'			
		}, {
			field : 'linkUrl',
			halign : 'center',
			title : '品牌网址'
		}, {
			field : 'carType',
			halign : 'center',
			title : '品牌区域'
			
		}, {
			field : 'brandName',
			halign : 'center',
			sortable : true,
			title : '汽车品牌'
		},{
			field : 'iconFile',
			halign : 'center',
			title : '品牌LOGO',
			formatter : function(value, row) {
				if(value != ""){
					var str = yoyo.imageUrl + value;
					//return '<div><a href="' + str + '" target="_blank" ><img src="' + str + '" style="width : 100px; height : 80px;"></a></div>';
					return '<div><img src="' + str + '" style="width : 100px; height : 80px;"></div>';
				}else{
					return "";
				}
			}
		},{
			field : 'pinyin',
			halign : 'center',
			sortable : true,
			title : '品牌拼音'
		},{
			field : 'carBrandBrandtobrand',
			halign : 'center',
			title : '进口国产类型',
			formatter : function(value, row) {
				if(value=='1'){
					return '国产';
				}else if(value=='2'){
					return '进口';
				}
			}
		},{
			field : 'editor',
			title : '操作',
			halign : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:editCarfactory("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#toolbar-car_factory',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
	//	height:$(window).height()-2,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		checkOnSelect:false,
		remoteSort : true,
		selectOnCheck:false,
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
	//TODO 获得汽车品牌数据  整车的identifier=999 部署时要修改
	//高级查询初始化
	var selectObj=[$("#query_brand"),$("#brandId")]; 
	getBrandData(biz.rootPath() + '/brandTypeShip/findShip?identifier='+yoyo.car,selectObj,true);
	//获得厂商区域列表
	selectObj=[$("#query_carType"),$("#scopeId")];
	getCarFactoryScopeData(biz.rootPath() + '/carFactoryScope/carFactoryScopeList',selectObj,true);

});




//获得汽车品牌数据
function getBrandData(url,obj,isSelect){
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
		$.each(obj,function (x,curObj){
			curObj.select2({data:eval(data)});
		})
	});
}

//获得厂商区域数据
function getCarFactoryScopeData(url,obj,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content.rows, function(i, n) {
			data+='{ text: "'+n.carType+'", id:'+n.scopeId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";	
		$.each(obj,function (x,curObj){
			curObj.select2({data:eval(data)});
		})
	});
}

//打开添加对话框
function openSaveDialog() {
	$("#scope_span").html('');
	$("#scopeId").val('');
	$('#window-add-car-factory').panel({title: "添加品牌"});
	$("#brandId").val('-1').trigger("change"); //默认选中
	$("#scopeId").val('-1').trigger("change"); //默认选中
	$("#imgIconFile").attr('src',biz.defaultPic());
	$('#form-car-factory-add').form('clear');
	$('#window-add-car-factory').window('open');
}



/**
 * 方法描述：新增厂商
 */
function saveCarfactory() {
	if ($('#form-car-factory-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-car-factory-add"));
		if($("#brandId").val()=='' || $("#brandId").val() == null ||$("#brandId").val() == '-1'){
			easyuiMsg('错误',"请选择汽车品牌！");
			return false;
		}
		if($("#scopeId").val()==''||$("#scopeId").val()=='-1'||$("#scopeId").val()==null){
			easyuiMsg('错误',"请选择品牌区域！");
			return false;
		}
		
		if($("#linkUrl").val().trim()==''){
			easyuiMsg('错误',"请填写网址！");
			return false;
		}
		
		if($("#factoryName").val().trim()==''){
			easyuiMsg('错误',"请填写品牌名称！");
			return false;
		}
		var type = $("input[name='carBrandBrandtobrand']:checked").val(); //进口，国产类型
		if(type=='' || type == null){
			easyuiMsg('错误',"请选择进口国产类型！");
			return false;
		}
		
		param_data.scopeId = $("#scopeId").val();
		param_data.brandId = $("#brandId").val();
		param_data.carBrandBrandtobrand = type;
		if($("#factoryId").val()=='' || $("#factoryId").val()==null){ //添加
			$.post(_path + '/carFactory/insertCarFactory', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-car-factory').datagrid('reload', {});
					$('#window-add-car-factory').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该汽车品牌已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}else { //更新
			$.post(_path + '/carFactory/updateCarFactory', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-car-factory').datagrid('reload', {});
					$('#window-add-car-factory').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该汽车品牌已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
		
	}
}


//逻辑删除汽车厂商
function deleteCarfactory(){
	var selectRows = $("#table-car-factory").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id[i]=v.factoryId;
	});
	var params={};
	params.id=id;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carFactory/deleteCarFactory',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-car-factory').datagrid('reload', {});
			    		} else if (_data.head.retCode == '000007'){
							easyuiMsg('错误',"部分品牌存在级联数据，请先删除级联数据！");
							$('#table-car-factory').datagrid('reload', {});
						} else {
			    			easyuiMsg('错误', "保存失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}



//打开编辑厂商对话框
function editCarfactory(row){
	$('#form-car-factory-add').form('clear');
	$('#form-car-factory-add').form('load', row);
	$('#window-add-car-factory').panel({title: "编辑品牌"});
	if(row.iconFile!=''){
		$("#imgIconFile").attr('src',yoyo.imageUrl+row.iconFile);
	} else {
		$("#imgIconFile").attr('src',biz.defaultPic());
	}
	$("#brandId").val(row.brandId).trigger("change");
	$("#scopeId").val(row.scopeId).trigger("change");
	$('#window-add-car-factory').window('open');
}






//高级查询立即筛选
function CarFacAdvanceSearch(){
	var param = biz.serializeObject($('#car_factory_search_form'));
	param.carid = $("#search_car_type").val();
	$('#table-car-factory').datagrid('load', param);
}


//清除高级查询
function advance_clear(){
	search_clear('car_factory_search_form','table-car-factory');
	$("#query_brand").val('-1').trigger("change");
	$("#query_carType").val('-1').trigger("change");
	$('#carBrandBrandtobrand').combobox('setValue','-1');
}

//打开高级筛选
function openSearch(){
	openAdvaceQuery('advance_search_car_factory');
	$('#carBrandBrandtobrand').combobox('setValue','-1');
}

