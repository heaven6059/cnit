/**
 * 功能描述：咨询评论>咨询列表JS
 * 作者：王鹏
 * 创建时间：2015-04-22
 *
 */
$(function(){
	$('#drive_list_dataGrid').datagrid({
		url : _path+'/reservationDrive/loadReservationDrive',
		columns : [[
		{
			field : 'Id',
			checkbox:true,
			align : 'center',
			width:"30px"
		} , {
			field : 'carName' ,
			title : '申请试驾车型',
			align : 'center',
			width:"340px"
		} , {
			field : 'userName' ,
			title : '申请人姓名',
			align : 'center',
			width:"120px"
		} , {
			field : 'userSex' ,
			title : '性别',
			align : 'center',
			width:"80px",
			formatter: function(value,rows,index){
				if(value==1)return "男";
				if(value==2)return "女";
			}
		} , {
			field : 'phone' ,
			title : '手机号',
			align : 'center',
			width:"150px"		
		} , {
			field : 'province' ,
			title : '地域',
			align : 'center',
			width:"320px",
			formatter: function(value,rows,index){
				return rows.province+" - "+rows.city+" - "+rows.county;
			}
		} , {
			field : 'addTime' ,
			align : 'center',
			title : '申请时间',
			width:"160px",
			formatter: function(value,rows,index){
				var date=new Date();
				date.setTime(parseInt(value)*1000);
				return date.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
			}
		}
		]],
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		toolbar : '#toolbar',
		pageSize : 20,
		height:$(window).height()*0.95,
		pageList : [ 20, 50, 100, 200 ],
		pagination : true ,
		rownumbers : true ,
		//fitColumns : true ,
		nowrap : false,
		remoteSort : false ,
		multiSort : true ,
		ctrlSelect : true ,
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		}
	});
	
	
	$("#consultBrand").select2();
	$("#consultDept").select2();
	$("#consultCar").select2();
	
	$("#drive_search_form").find("select").each(function (x,item){
	});
	
	$("#consultBrand").change(function (){
		var option=[];
		initSeelct2(["consultDept","consultCar"]);
		option.push('<option value="-1">--请选择--</option>');
		car_info_utils.initDept(function (data){
		$.each(data.content,function (x,item){
			option.push('<optgroup label="'+item.factoryName+'">');
			$.each(item.carDepts,function (x,item){
				option.push('<option value='+item.carDeptId+'>'+item.carDeptName+'</option>');
			});
			$("#consultDept").html(option.join(""));
		});		
		},null,$(this).val());
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
		},null,$(this).val());
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
	$(':input',"#drive_search_form").not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	initSeelct2(["replaceDept","replaceCar","consultDept","consultCar"]);
	$("#replaceBrand").val(-1).trigger("change");
	$("#consultBrand").val(-1).trigger("change");
	$('#drive_list_dataGrid').datagrid('reload', {});
}

function initSeelct2(target){
    var data = [{ id: -1, text: '--请选择--' ,selected:true}];
    $.each(target,function (x,t){
    	$("#"+t).val(-1);
    	$("#"+t).empty();
		$("#"+t).select2({data:data});
	});
}

function deleteDrive(){
	var ids =new Array();
	var selectRows = $("#drive_list_dataGrid").datagrid('getChecked');
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
	console.log(params);
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/reservationDrive/deleteReservationDrive',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#drive_list_dataGrid').datagrid('reload', {});
			    		} else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}




