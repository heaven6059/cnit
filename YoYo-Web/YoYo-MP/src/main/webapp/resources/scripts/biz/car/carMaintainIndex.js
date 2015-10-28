/**
 * 功能描述： 商品保养周期的js
 * 
 */
//var select_data = "[]";
$(document).ready(function(){
	$('#table-car-maintain').datagrid({
		url : _path + '/carMaintain/carMaintainList',
		columns : [ [ {
			field:'maintainId',
			title:'',
			align : 'center',
			formatter: function(value, rowData, rowIndex){
				return '<input type="radio" name="selectRadio" value="' + rowData.maintainId + '" />';
			}
		},{
			field : 'carDeptName',
			title : '车系',
			align : 'center',
			width : '50%'
		}]],
		toolbar : '#toolbar-car_maintain',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		height:$(window).height()-150,
		pageList : [ 25, 50, 100, 150 ],
		checkOnSelect:true,
		selectOnCheck:true,
		remoteSort : false,
		multiSort : false,
		singleSelect:true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		},onSelect:function (rowIndex, rowData){
			$(".datagrid-btable").find("input[type=radio]").each(function (x,item){
				if($(item).val()==rowData.maintainId){
					$(item).prop("checked", true);
				}else{
					$(item).prop("checked", false);
				}
			});
		}
	});
	
	initSeelct2(["car_factory_qry","car_dept_qry"]);
	$("#car_brand_qry").change(function (){
		initSeelct2(["car_factory_qry","car_dept_qry"]);
		getCarFactory($(this).select2("val"));
	});

	$("#car_factory_qry").change(function (){
		initSeelct2(["car_dept_qry"]);
		getCarDept($(this).select2("val"));
	});
	getCarBrand();
});


function clearMaintain(){	
	$("#car_brand_qry").val(-1).trigger("change");	
	maintianAdvanceSearch();
}


//高级查询立即筛选
function maintianAdvanceSearch(){
	var param = biz.serializeObject($('#maintain_search_form'));
	param.carBrand = $("#car_brand_qry").val();
	param.carFactory = $("#car_factory_qry").val();
	param.carDept = $("#car_dept_qry").val();
	$('#table-car-maintain').datagrid('load', param);
}

function initSeelct2(target){
    var data = [{ id: -1, text: '--请选择--' ,selected:true}];
    $.each(target,function (x,t){
    	$("#"+t).val(null);
    	$("#"+t).empty();
		$("#"+t).select2({data:data});
	});
}

//获得车型
function getCarData(url,obj,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n){
			data+='{ text: "'+n.carName+'", id:'+n.carId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
	});
}

function getCar(deptId){
	$.post("../carMaintain/findCar",{deptId:deptId}, function(brand) {
		var data="[";
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		$.each(brand, function(i, n){
			
			data+='{ text: "'+n.carName+'", id:'+n.carId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		$("#car_type").select2({data:eval(data)});
	});
}


function getCarDept(factoryId){
	$.post("../carMaintain/findCarDept",{factoryId:factoryId}, function(brand) {
		var data="[";
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		$.each(brand, function(i, n){
			data+='{ text: "'+n.carDeptName+'", id:'+n.carDeptId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		$("#car_dept_qry").select2({data:eval(data)});
	});
}

function getCarFactory(brandId){	
	$.post("../carMaintain/findCarFactory",{brandId:brandId}, function(brand) {
		var data="[";
		data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		$.each(brand, function(i, n){
			data+='{ text: "'+n.factoryName+'", id:'+n.factoryId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		$("#car_factory_qry").select2({data:eval(data)});
	});	
}

function getCarBrand(){
	$("#car_factory").empty()
	$("#car_dept").empty();
	$("#car_type").empty();
	var identifier= yoyo.car;
	$.post("../carMaintain/findCarBrand",{identifier:identifier}, function(brand) {
		var data="[" 
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';		
		$.each(brand, function(i, n){
			data+='{ text: "'+n.brandName+'", id:'+n.brandId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		$("#car_brand_qry").select2({data:eval(data)});
	});
}

//打开添加对话框
function addMaintain() {
	$("#window-add-car-maintain").dialog({
	    width: 805,
	    height: 533,
	    href:"../carMaintain/toCarMaintainEdit",
	    modal: true,
	    closed: true,
	    title:'汽车厂商区域添加',
	    closed:true,
	    cache:false,
	    draggable:false
	});
	$('#window-add-car-maintain').window('open');
}



/**
 * 方法描述：新增周期
 */
function saveCarMaintain() {
	if ($('#form-car-maintain-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-car-maintain-add"));
		if($("#car_type").val()=='' || $("#car_type").val() == null ||$("#car_type").val()=='-1'){
			easyuiMsg('错误',"请选择车型！");
			return false;
		}
		param_data.carid = $("#car_type").val();
		$.post(_path + '/carMaintain/insertCarMaintain', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-car-maintain').datagrid('reload', {});
				$('#window-add-car-maintain').window('close');
			} else if (_data.head.retCode == '000004'){
				easyuiMsg('错误',"该车型周期名称已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}


//逻辑删除保养周期
function deleteCarMaintain(){
	var selectRows = $("#table-car-maintain").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var carMaintainIds =[];
	$.each(selectRows,function(i,v){
		carMaintainIds[i]=v.maintainId;
	});
	var params={};
	params.carMaintainIds=carMaintainIds;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carMaintain/deleteCarMaintain',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-car-maintain').datagrid('reload', {});
			    		} else {
			    			easyuiMsg('错误', "保存失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}



//打开编辑保养周期对话框
function editCarMaintain(row){
	$('#form-car-maintain-add').form('clear');
	$('#form-car-maintain-add').form('load', row);
	$('#car_type').val(row.carid).trigger("change");
	$('#add-car-maintain-btn').hide();
	$('#update-car-maintain-btn').show();
	$('#window-add-car-maintain').window('open');
}


/**
 * 更新保养周期
 */
function updateCarMaintain(){
	if ($('#form-car-maintain-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-car-maintain-add"));
		if($("#car_type").val()=='' || $("#car_type").val() == null ||$("#car_type").val()=='-1'){
			easyuiMsg('错误',"请选择车型！");
			return false;
		}
		param_data.carid = $("#car_type").val();
		$.post(_path + '/carMaintain/updateCarMaintain', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-car-maintain').datagrid('reload', {});
				$('#window-add-car-maintain').window('close');
			} else if (_data.head.retCode == '000004'){
				easyuiMsg('错误',"该保养周期名称已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}

//编辑关联的保养类别
function editMaintainCate(row){
	$("#selectId").val(row.maintainId);
	$("#maintain_select_parent").empty();
	$("#maintain_select_child").empty();
	initCategory('maintain_select_parent','maintain_select_child','maintain_category_ids',28);
	//获取分类信息
	$.ajax({
		url:_path + '/carMaintain/findMaintainShip?maintainId='+row.maintainId,
 	    type:"post",
 	    dataType:"json",
 	    success:function(data){
 	    	if (data.head.retCode == '000000') {
 	    		select_data = '[';
	    			$.each(data.content,function(i,v){
	    				select_data+='{ text: "'+v.categoryName+'", id:'+v.categoryId+',selected:true},';
	    			});
	    			select_data =select_data.length>1? select_data.substring(0, select_data.length-1) : select_data;
	    			select_data+="]";
	    			$("#maintain_category_ids").empty();
	    			$("#maintain_category_ids").select2({data:eval(select_data)});
	    		} else {
	    			easyuiMsg('错误', "获取分类信息失败！");
	    		}
 	    }
	});
	$("#window-maintain-category").window('open');
	
}

function closeCategory(){	$("#window-maintain-category").window('close');}

//更新保养周期与保养类别的关系
function updateMaintainCategory(){
	var param_data = {};
	param_data.categoryIds=$("#maintain_category_ids").val();
	param_data.maintainId = $("#selectId").val();
	$.post(_path + '/carMaintain/updateMaintainCate', param_data, function(_data) {
		if (_data.head.retCode == '000000') {
			$("#selectId").val('');
			$('#window-maintain-category').window('close');
		} else {
			easyuiMsg('错误', "保存失败！");
		}
	}, 'json');
}


function viewMaintain(){
	$("#advance_search_car_type").dialog({
		   width: 300,
		   height: 200,
		   modal: true,
		   closed: true,
		   title:'选择车型',
		   closed:true,
		   cache:false,
		   draggable:false
	});
	var record = $('#table-car-maintain').datagrid('getSelected');
	if(record){
		initSeelct2(["car_type"]);
		getCar(record.carDept);
		$('#advance_search_car_type').window('open');
	}else{
		easyuiMsg('提示', "请选择车系！");
	}
}

function getMaintainInfo(){
	var carId=$("#car_type").select2("val");
	if(carId<=0){
		easyuiMsg('提示', "请选择车型！");
		return;
	}
	initSeelct2(["car_type"]);
	var resultItem={
		CarMaintain:null,
		OfficialMaintain:null,
		OptionalMaintain:null
	};
	var opts={
		url:'../carMaintain/findMaintainByCar',
		data:{carId:carId},
		result:null
	}
	opts=sendAjaxReq(opts);
	resultItem.CarMaintain=opts.result;
	opts={
		url:'../carMaintain/findOfficialMaintainCategory',
		data:{identifier:yoyo.official_maintain},			
	}
	opts=sendAjaxReq(opts);
	resultItem.OfficialMaintain=opts.result;
	opts={
		url:'../carMaintain/findOptionalMaintainCategory',
		data:{identifier:yoyo.maintain},			
	}
	opts=sendAjaxReq(opts);
	resultItem.OptionalMaintain=opts.result;
	buildCarMaintainHTML(resultItem);
	
	$('#advance_search_car_type').window('close');
	$('#window-view-car-maintain').window('open');
}


function sendAjaxReq(opts){
	$.ajax({  
		async:false,   //同步请求  
		type:"POST",  
		data:opts.data,
		url:opts.url,  
		success:function(result){
			opts.result=result;
	    }
	});
	return opts;
}

function buildCarMaintainHTML(opts){
	var mHtml=
		'<table cellspacing="1" cellpadding="1" border="1" id="maintaincontainer" class="t_H3" width="100%">'+			
		'<tbody>'+
		'<tr>'+
		'<td width="180px"><strong>保养项目/里程</strong></td>';		
		$.each(opts.CarMaintain,function (x,maintain){
			mHtml+='<td id="c0rh" class="t_H3_seleted_th">'+maintain.maintainKm+'公里/'+maintain.maintainTime+'个月</td>';
		});
		mHtml+='</tr>';
		
		$.each(opts.OfficialMaintain,function (x,category){	
			mHtml+='<tr>';
			mHtml+='<td id="c0rh" class="t_H3_seleted_th">'+category.catName+'</td>';
			$.each(opts.CarMaintain,function (x,maintain){
				if(maintain.officialMaintain){
					var j=JSON.parse(maintain.officialMaintain)				
					if(j.maintainIndexOf(category.catId)>=0){
						mHtml+='<td style="background: none repeat scroll 0 0 #fff;" id="c2r1">●</td>';
					}else{
						mHtml+='<td style="background: none repeat scroll 0 0 #fff;" id="c2r1"></td>';
					}
				}else{
					mHtml+='<td style="background: none repeat scroll 0 0 #fff;" id="c2r1"></td>';
				}
			});
			mHtml+='</tr>';
		});
		if(opts.OptionalMaintain.length>0){
			mHtml+='<tr><td><strong>自选保养项</strong></td><td colspan='+(opts.CarMaintain.length)+'></td></tr>';
		}
		$.each(opts.OptionalMaintain,function (x,category){
			mHtml+='<tr>';
			mHtml+='<td id="c0rh" class="t_H3_seleted_th">'+category.catName+'</td>';
			$.each(opts.CarMaintain,function (x,maintain){
				if(maintain.optionalMaintain){
					var j=JSON.parse(maintain.optionalMaintain)
					if(j.maintainIndexOf(category.catId)>=0){
						mHtml+='<td style="background: none repeat scroll 0 0 #fff;" id="c2r1">●</td>';
					}else{
						mHtml+='<td style="background: none repeat scroll 0 0 #fff;" id="c2r1"></td>';
					}
				}else{
					mHtml+='<td style="background: none repeat scroll 0 0 #fff;" id="c2r1"></td>';
				}
			});
			mHtml+='</tr>';
		});
		
		mHtml+='</tbody>';
		mHtml+='</table>';
	$("#window-view-car-maintain").html(mHtml);
}

Array.prototype.maintainIndexOf = function(Object){
    for(var i = 0;i<this.length;i++){
       if(this[i].id == Object){
          return i;
       }
    }
   return -1;  
}

function replaceAll(str,replaceStr,tagetStr){
	return str.replace(new RegExp(replaceStr,'gm'),tagetStr)
}

