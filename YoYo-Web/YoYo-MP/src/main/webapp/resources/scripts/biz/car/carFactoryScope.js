/**
 * 功能描述： 汽车厂商区域的js
 * 
 */
$(function() {
	$('#table-car-factory-scope').datagrid({
		url : _path + '/carFactoryScope/carFactoryScopeList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'carType',
			align : 'center',
			title : '品牌区域名称',
			width:100,
			sortable : true
		},{
			field : 'createtime',
			align : 'center',
			title : '创建时间',
			sortable : true,
			width:100,
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		},{
			field : 'lastMidifity',
			align : 'center',
			title : '最后修改时间',
			sortable : true,
			width:100,
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		},{
			field: 'scopeId',
			align: 'center',
			title: '操作',
			width: '8%',
			formatter: function(value,rows,index){
				return "<a href='javascript:void(0);' onclick='openSaveDialog(\"edit\","+value+");' class='btn-grey'>编辑</a>&nbsp;"+
				 	   "<a href='javascript:void(0);' onclick='deleteScope("+value+");' class='btn-grey'>删除</a>";
			}
		}]],
		toolbar : '#toolbar-car-factory-scope',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		//height:$(window).height()-30,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		singleSelect : true,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : false,
		nowrap:false,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		},onDblClickCell : function(index , field , value){
			openSaveDialog('edit');
		}
	});
});


//保存厂商区域
function saveScope(scopeId){
	if ($('#add_scopes_form').form('validate')) {
		var param_data = biz.serializeObject($("#add_scopes_form"));
		if(scopeId){ //更新
			$.post(_path + '/carFactoryScope/updateCarFactoryScope', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-car-factory-scope').datagrid('reload', {});
					$('#window-add-scope').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该品牌区域名称已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		} else { //添加
			$.post(_path + '/carFactoryScope/insertCarFactoryScope', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-car-factory-scope').datagrid('reload', {});
					$('#window-add-scope').window('close');
				} else if (_data.head.retCode == '000004'){
					easyuiMsg('错误',"该品牌区域名称已经存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
	}
}


function openSaveDialog(op,id){
	var scopeId="";
	if(op=='edit'){
		if(id){
			scopeId=id;
		}else{
			var record=$("#table-car-factory-scope").datagrid("getSelected");
			if(record){
				scopeId = record.scopeId;
			}else{
				$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
				return;
			}
		}
	}		
	$("#window-add-scope").dialog({
	    href: _path+'/carFactoryScope/toCarFactoryScopeEdit?op='+op+'&id='+scopeId,
	    modal: true,
	    //title:'汽车厂商区域',
	    closed:true,
	    draggable:false
	});
	if(op=='edit'){
		$('#window-add-scope').panel({title: "编辑汽车品牌区域"});
	}else {
		$('#window-add-scope').panel({title: "添加汽车品牌区域"});
	}
	$('#window-add-scope').dialog('open');
}

function closeCarFactoryScope(){
	$('#window-add-scope').window('close');
}

//删除厂商区域
function deleteScope(id){
	var ids =new Array();
	if(id){
		ids.push(id);
	}else{
		var selectRows = $("#table-car-factory-scope").datagrid('getChecked');
		if(selectRows.length==0){
			$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
			return false;
		}
		$.each(selectRows,function(i,v){
			ids.push(v.scopeId);
		});
	}
	if(ids.length==0){
		$.messager.alert('提示', '非法操作请刷尝试刷新新界！', 'error');
		return false;
	}
	var params={};
	params.id=ids.join(",");
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/carFactoryScope/deleteCarFactoryScope',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-car-factory-scope').datagrid('reload', {});
			    		}else if (_data.head.retCode == '000007'){
							easyuiMsg('错误',_data.retMsg+"品牌区域存在级联数据，请先删除级联数据！");
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
	$('#advance_search_scope').dialog('open').window('resize',{ width : '380px' , height : '500px' , top : 0 , left : (width - 380) });
}

function clearCondition(){
	$("#advance_search_scope").form('clear');
	chis.clearForm($("#advance_search_scope"));
	$('#table-car-factory-scope').datagrid('reload', {});
}
