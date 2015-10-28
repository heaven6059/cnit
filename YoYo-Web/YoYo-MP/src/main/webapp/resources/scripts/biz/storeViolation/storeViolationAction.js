/**
 * 功能描述： 汽车车型的js
 * 
 */
$(function() {
	//查询违规处理列表
	initList();
	//查询违规类型列表
	initViolationCate('search-combox-violation-cat',false);
	
	$('select[name="earnestMoneySearchType"]').on('change', function() {
		changeSearchType($(this).val());
	});
});
//查询违规处理列表
function initList(){
	$('#table-store-violation').datagrid({
		url : _path + '/storeViolation/findStoreViolationActionList',
		columns : [ [ {
			field:'ck',
			checkbox:"true",
			width:'2%'
		},{
			field : 'id',
			align : 'center',
			hidden: 'true',
			title : 'ID'			
		},{
			field : 'storeName',
			align : 'center',
			title : '分店名称',
			sortable: true
		}, {
			field : 'companyName',
			align : 'center',
			title : '集团名称',
			sortable: true
		}, {
			field : 'catName',
			align : 'center',
			title : '违规类型',
			sortable: true
		}, {
			field : 'parentCatName',
			align : 'center',
			title : '违规分类',
			sortable: true
		}, {
			field : 'goodsdownStarttime',
			align : 'center',
			title : '下架店铺内所有商品开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		}, {
			field : 'goodsdownEndtime',
			align : 'center',
			title : '下架店铺内所有商品结束时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		}, {
			field : 'goodsStarttime',
			align : 'center',
			title : '限制发布商品开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		}, {
			field : 'goodsEndtime',
			align : 'center',
			title : '限制发布商品结束时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		}, {
			field : 'storeStarttime',
			align : 'center',
			title : '店铺屏蔽开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		}, {
			field : 'storeEndtime',
			align : 'center',
			title : '店铺屏蔽结束时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		}, {
			field : 'storedownStarttime',
			align : 'center',
			title : '关闭店铺开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		}, {
			field : 'storedownEndtime',
			align : 'center',
			title : '关闭店铺结束时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		},{
			field : 'earnest',
			align : 'center',
			title : '交付违约金',
			sortable: true
		}, {
			field : 'processed',
			align : 'center',
			title : '违规处理状态',
			formatter : function(value, row) {
				if (value == '1' && row.status == '0' )
					return '处理中';
				return '已处理';
			}
		}, {
			field : 'lastModify',
			align : 'center',
			title : '最后修改时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		}]],
		toolbar : '#toolbar-store-violation',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		striped:true,
		/*fitColumns : true,*/
		pageSize : 25,
		pageList : [ 25, 50, 100, 150 ],
		singleSelect : true,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : false,
		selectOnCheck:false,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		},onDblClickCell : function(index , field , value){
//			openSaveDialog('edit');
		}
	});
	
}

function initViolationCate(obj, multiple) {
	var $obj = $('#' + obj);
	$obj.combotree({
		url : _path + '/storeViolationCat/findViolationCatList',
		multiple : multiple,
		queryParams : {
			'parentCatId' : 0
		},
		columns : [[{
			field : 'catId',
			title : '分类Id',
			fixed : true
		}, {
			field : 'catName',
			title : '分类名称',
			fixed : true
		}]],
		fitColumns : true,
		loadFilter : function(data) {
			for ( var i = 0; i < data.length; i++) {
				if (data[i].childCount > 0) {
					data[i].state = 'closed';
				} else {
					data[i].state = '';
				}
				data[i].id = data[i].catId;
				data[i].text = data[i].catName;
			}
			return data;
		},
		onBeforeExpand : function(row) {
			var url = _path + '/storeViolationCat/findViolationCatList?parentCatId=' + row.catId;
			$obj.combotree("tree").tree("options").url = url;
			return true;
		},
		onLoadSuccess : function(node, data) {
			$obj.combotree('tree').tree("expandAll");
		}
	});
}

//删除
function deleteStoreViolation(id){
	var ids =new Array();
	if(id){
		ids.push(id);
	}else{
		var selectRows = $("#table-store-violation").datagrid('getChecked');
		if(selectRows.length==0){
			$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
			return false;
		}
		$.each(selectRows,function(i,v){
			ids.push(v.id);
		});
	}
	if(ids.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var params={};
	params.id=ids.join(",");
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/storeViolation/deleteStoreViolation',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
//		    	    	alert(_data.head.retCode);
		    	    	if (_data.head.retCode == '000000') {
//			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-store-violation').datagrid('reload', {});
			    		} else if (_data.head.retCode == '000001'){
							easyuiMsg('错误','请选择需要操作的数据项！');
						} else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    }
		}
	);
}


/**
 * 打开高级查询
 */
function openSeachWindow() {
	var width = $(window).width(); // 屏幕的宽度
	$('#advance_search_store_violation').dialog('open').window('resize',{ width : '400px' , height : '500px' , top : 0 , left : (width - 400) });
}
//清空高级选项条件
function clearCondition(){
	$("#advance_search_store_violation").form('clear');
//	$("#search-combox-violation-cat").val(-1).trigger("change");
	$("#search-combox-violation-cat").combotree("clear");
	$('select[name="earnestMoneySearchType"]').val("gt");
	$('div.bt').hide();
	$('div.nbt').show();
	$('#table-store-violation').datagrid('reload', {});
}
//更改高级搜索违约金查询条件
function changeSearchType(value){
//	alert(value);
	if(value=='bt'){
		$('div.nbt').hide();
		$('div.bt').show();
	}else{
		$('div.bt').hide();
		$('div.nbt').show();
	}
}

