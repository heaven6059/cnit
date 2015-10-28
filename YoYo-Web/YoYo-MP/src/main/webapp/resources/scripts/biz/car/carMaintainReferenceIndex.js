/**
 * 功能描述： 商品保养周期的js
 * 
 */
$(document).ready(function(){	
	$("#table-car-maintain").datagrid({
		url : _path + '/carMaintainReference/carMaintainReferenceList',
		rowStyler: function(index,row){
			if(row.maintStatus==0){
				return 'background-color:#799fbe;color:#fff;font-weight:bold;';
			}
		},
		columns : [
		 [
		  	{title:"<h4>优优保养</h4>",colspan:4},
	        {title:"<h4>官方保养</h4>",colspan:4},
		  	{
		  		title:'是否变动',
		  		rowspan:2,
		  		field:'crawlerId',
		    	formatter : function(value, row) {
		    		if(row.maintStatus==0 || !(row.officialMaintain && row.crawlerOfficialMaintain)){
		  				return "<lable style='color:red'>数据差异</lable>";
		  			}else{
			    		if(row.officialMaintain && row.crawlerOfficialMaintain && row.officialMaintain.length!=row.crawlerOfficialMaintain.length){
			    			return "<lable style='color:red'>已变动</lable>";
			    		}else{
			    			for(var i=0;i<row.officialMaintain.length;i++){
			    				if(JSON.stringify(row.crawlerOfficialMaintain).indexOf(row.officialMaintain[i].text)<0){
			    					return "<lable style='color:red'>已变动</lable>";
			    				}
			    			}
			    		}
		  			}
		    		if(row.carId!=row.crawlerCarId){
		    			return "<lable style='color:red'>已变动</lable>";
		    		}
		    		if(row.crawlerMaintainKm!=row.maintainKm){
		    			return "<lable style='color:red'>已变动</lable>";	
		    		}
		    		if(row.maintainTime!=row.crawlerMaintainTime){
		    			return "<lable style='color:red'>已变动</lable>";
		    		}
		    		return "<lable style='color:green'>未变动</lable>";
		    	}
		  	}
		 ],		 
		 [	
		  	{
				field : 'cycleCarName',
				title : '车型',
				halign:'center',
				width : '220px'
			}, {
				field : 'maintainKm',
				title : '保养里程',
				halign : 'center',
				width : '80px',
				formatter : function(value, row) {
					if(value){
						return value+"公里";
					}else{
						return "";
					}
				}
			}, {
				field : 'maintainTime',
				title : '保养时间',
				halign : 'center',
				width : '80px',
				formatter : function(value, row) {
					if(value){
						return value+"个月";
					}else{
						return "";
					}
				}
			}, {
				field : 'officialMaintain',
				title : '保养项目',
				halign:'center',
				width : '430px',
				formatter : function(value, row) {
					var itemTitle="";
					if(row.officialMaintain&&row.officialMaintain.length>0){
						$.each(row.officialMaintain,function (x,item){
							itemTitle+=item.text+"、";
						});
					}
					if(itemTitle.length>0){
						return itemTitle;
					}else{
						return "";
					}
				}
			}, {
				field : 'crawlerCarName',
				title : '车型',
				halign:'center',
				sortable : true,
				width : '220px',
			}, {
				field : 'crawlerMaintainKm',
				title : '保养里程',
				halign : 'center',
				width : '80px',
				formatter : function(value, row) {
					if(value){
						return value+"公里";
					}else{
						return "";
					}
				}
			}, {
				field : 'crawlerMaintainTime',
				title : '保养时间',
				halign : 'center',
				width : '80px',
				formatter : function(value, row) {
					if(value){
						return value+"个月";
					}else{
						return "";
					}
				}
			}, {
				field : 'crawlerOfficialMaintain',
				title : '保养项目',
				halign : 'center',
				width : '430px',
				formatter : function(value, row) {
					var itemTitle="";
					if(row.crawlerOfficialMaintain&&row.crawlerOfficialMaintain.length>0){
						$.each(row.crawlerOfficialMaintain,function (x,item){
							itemTitle+=item.text+"、";
						});
					}
					if(itemTitle.length>0){
						return itemTitle;
					}else{
						return "";
					}
					
				}
			}
		]],
		toolbar : '#toolbar_car_maintain',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		//fitColumns : true,
		nowrap : false,
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
		},onDblClickCell : function(index , field , value){
		},onSelect:function (rowIndex, rowData){
			return "bacground-clr";
		}
	});
	initSeelct2(["car_factory_qry","car_dept_qry","car_type_qry"]);
	$("#car_brand_qry").change(function (){
		initSeelct2(["car_factory_qry","car_dept_qry","car_type_qry"]);
		getCarFactory($(this).select2("val"));
	});

	$("#car_factory_qry").change(function (){
		initSeelct2(["car_dept_qry","car_type_qry"]);
		getCarDept($(this).select2("val"));
	});

	$("#car_dept_qry").change(function (){
		initSeelct2(["car_type_qry"]);
		getCar($(this).select2("val"));
	});	
	
	getCarBrand();
});

function clearMaintain(){	
	$("#car_brand_qry").val(-1).trigger("change");	
	searchMaintain();
}

function searchMaintain(){
	var param=biz.serializeObject($("#maintain_search_form"));
	$('#table-car-maintain').datagrid('load', param);
}


function editMaintain(){
	var record = $('#table-car-maintain').datagrid('getSelected');
	if(record){		
		var carId=record.crawlerCarId;
		if(record.maintainId>0){
			carId=record.carId;
		}
		var officialMaintain="";
		if(record.crawlerOfficialMaintain){
			try{
				officialMaintain=JSON.stringify(record.crawlerOfficialMaintain)
			}catch (e) {
			}
		}
		$("#window-edit-car-maintain").dialog({
		    href:"../carMaintain/toCarMaintainEdit?maintainId="+record.maintainId+"&carId="+carId+"&maintainKm="+record.crawlerMaintainKm+"&maintainTime="+record.crawlerMaintainTime+"&crawlerId="+record.crawlerId+"&officialMaintain="+officialMaintain,
		    title:'车型保养编辑',
		    modal: true,
		    cache:false,
		    draggable:false,
		    iconCls:'icon-edit'
		});
		$('#window-edit-car-maintain').window('open');
	}else{
		$.messager.alert('警告','请选择编辑的数据!','warning');
	}
}



function initSeelct2(target){
    $.each(target,function (x,t){    	
    	$("#"+t).val(null);
    	$("#"+t).empty();
    	var data=[{"text":"--请选择--","id": -1,selected:true}];
    	$("#"+t).select2({data:eval(data)});
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
		$("#car_type_qry").select2({data:eval(data)});
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