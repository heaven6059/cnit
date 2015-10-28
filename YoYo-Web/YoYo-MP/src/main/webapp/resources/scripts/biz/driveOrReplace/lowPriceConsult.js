/**
 * 功能描述：咨询评论>咨询列表JS
 * 作者：王鹏
 * 创建时间：2015-04-22
 *
 */
$(function(){
	$('#low_price_list_dataGrid').datagrid({
		url : _path+'/lowPriceConsult/loadLowPriceConsult',
		columns : [[
		{
			field : 'Id',
			checkbox:true,
			align : 'center',
			width:"10%"
		} , {
			field : 'carName' ,
			title : '询问车型',
			align : 'center',
			width:"10%"
		} , {
			field : 'userName' ,
			title : '询问人姓名',
			align : 'center',
			width:"10%"
		} , {
			field : 'userSex' ,
			title : '询问人性别',
			align : 'center',
			width:"5%",
			formatter: function(value,rows,index){
				console.log(value);
				if(value==1)return "男";
				if(value==2)return "女";
			}
		} , {
			field : 'phone' ,
			title : '询问人手机号',
			align : 'center',
			width:"10%"		
		} , {
			field : 'province' ,
			title : '询问人地域',
			align : 'center',
			width:"10%",
			formatter: function(value,rows,index){
				return rows.province+" - "+rows.city+" - "+rows.county;
			}
		} , {
			field : 'addTime' ,
			align : 'center',
			title : '询问时间',
			width:"10%",
			formatter: function(value,rows,index){
				var date=new Date();
				date.setTime(parseInt(value)*1000);
				return date.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
			}
		}, {
			field : 'isReplace' ,
			title : '是否置换',
			align : 'center',
			width:"5%",
			formatter: function(value,rows,index){
				if(value==1)return "是";
				if(value==0)return "否";
			}
		}, {
			field : 'replaceCar' ,
			title : '置换车型',
			align : 'center',
			width:"10%",
			formatter: function(value,rows,index){
				if(rows.isReplace==1)return value;
				if(rows.isReplace==0)return "-";
			}
				
		}, {
			field : 'replaceDept' ,
			title : '置换车型车系',
			align : 'center',
			width:"8%",
			formatter: function(value,rows,index){
				if(rows.isReplace==1)return value;
				if(rows.isReplace==0)return "-";
			}
		}, {
			field : 'replaceBrand' ,
			title : '置换车品牌',
			align : 'center',
			width:"8%",
			formatter: function(value,rows,index){
				if(rows.isReplace==1)return value;
				if(rows.isReplace==0)return "-";
			}
		}, {
			field : 'carMiles' ,
			title : '行驶里程/公里',
			align : 'center',
			width:"5%",
			formatter: function(value,rows,index){
				if(rows.isReplace==1)return value;
				if(rows.isReplace==0)return "-";
			}
		}, {
			field : 'cardYear' ,
			title : '上牌日期',
			align : 'center',
			width:"5%",
			formatter: function(value,rows,index){
				if(rows.isReplace==1)return rows.cardYear+"年"+rows.cardMonth+"月";
				if(rows.isReplace==0)return "-";
			}
		}
		]],
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		height:$(window).height()*0.95,
		pageList : [25,50,100,150],
		checkOnSelect:true,
		selectOnCheck:true,
		remoteSort : false,
		multiSort : false,
		singleSelect:true,
		nowrap:false,
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		}
	});
	
	$("#low_price_search_form").find("select").each(function (x,item){
		$(item).select2({minimumResultsForSearch: Infinity});
	});
	
	$("#consultBrand").change(function (){
		var option=[];
		option.push('<option value="-1">--请选择--</option>');
		initSeelct2(["consultDept","consultCar"]);
		car_info_utils.initDept(function (data){
		$.each(data.content,function (x,item){
			option.push('<optgroup label="'+item.factoryName+'">');
			$.each(item.carDepts,function (x,item){
				option.push('<option value='+item.carDeptId+'>'+item.carDeptName+'</option>');
			});
			$("#consultDept").html(option.join(""));
		})
		},null,$(this).val())
	});
	
	$("#consultDept").change(function (){
		initSeelct2(["consultCar"]);
		var option=[];
		option.push('<option value="-1">--请选择--</option>');
		car_info_utils.initCar(function (data){
			$.each(data.content,function (x,item){
				option.push('<option value='+item.carId+'>'+item.carName+'</option>');
			});
		$("#consultCar").html(option.join(""));
		},null,$(this).val())
	});
	
	$("#replaceBrand").change(function (){
		initSeelct2(["replaceDept","replaceCar"]);
		var option=[];
		option.push('<option value="-1">--请选择--</option>');
		car_info_utils.initDept(function (data){
		$.each(data.content,function (x,item){
			option.push('<optgroup label="'+item.factoryName+'">');
			$.each(item.carDepts,function (x,item){
				option.push('<option value='+item.carDeptId+'>'+item.carDeptName+'</option>');
			});
			$("#replaceDept").html(option.join(""));
		})
		},null,$(this).val())
	});
	
	$("#replaceDept").change(function (){
		initSeelct2(["replaceCar"]);
		var option=[];
		option.push('<option value="-1">--请选择--</option>');
		car_info_utils.initCar(function (data){
			$.each(data.content,function (x,item){
				option.push('<option value='+item.carId+'>'+item.carName+'</option>');
			});
		$("#replaceCar").html(option.join(""));
		},null,$(this).val())
	});
	
});
function openSeachWindow() {
	var width = $(window).width(); // 屏幕的宽度
	$('#advance_search_scope').dialog('open').window('resize',{ width : '380px' , height : '500px' , top : 0 , left : (width - 380) });
	car_info_utils.initBrand(function (data){
		var option=[];
		$.each(data.content,function (x,item){
			option.push('<option value='+item.brandId+'>'+item.brandName+'</option>');
		});
		$("#consultBrand").append(option.join(""));
		$("#replaceBrand").append(option.join(""));
	});
}

function clearCondition(){
	$(':input',"#low_price_search_form").not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	initSeelct2(["replaceDept","replaceCar","consultDept","consultCar"]);
	$("#replaceBrand").val(-1).trigger("change");
	$("#consultBrand").val(-1).trigger("change");
	$('#low_price_list_dataGrid').datagrid('reload', {});
}

function initSeelct2(target){
    var data = [{ id: -1, text: '--请选择--' ,selected:true}];
    $.each(target,function (x,t){
    	$("#"+t).val(null);
    	$("#"+t).empty();
		$("#"+t).select2({data:data});
	});
}

function deleteLowPriceConsult(){
	var ids =new Array();
	var selectRows = $("#low_price_list_dataGrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	$.each(selectRows,function(i,v){
		ids.push(v.id);
	});
	if(ids.length==0){
		$.messager.alert('提示', '非法操作请刷尝试刷新新界！', 'error');
		return false;
	}
	var params={
		ids:ids.join(",")
	};
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/lowPriceConsult/deleteLowPriceConsult',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#low_price_list_dataGrid').datagrid('reload', {});
			    		}else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}


