/**
 * 功能描述： 店铺角色js
 * 
 */
$(function() {
	$('#shopRoleDataGrid').datagrid({
		url : _path + '/roleManager/shopRoleList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'rolesName',
			sortable : true,
			halign: 'center',
			title : '角色名称'			
		}, {
			field : 'shopName',
			sortable : true,
			halign: 'center',
			title : '店铺名称'
		},{
			field : 'storeName',
			sortable : true,
			halign: 'center',
			title : '分店名称'
		},{
			field : 'regtime',
			halign: 'center',
			title : '角色建立时间',
			sortable : true,
			formatter : function(value, row) {
				return new Date(value).format("yyyy-MM-dd hh:mm:ss");
			}
		} ] ],
		toolbar : '#toolbar',
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : false,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : true,
		multiSort : true,
		nowrap : false,
	//	height:$(window).height()-2,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	

	
});



/**删除*/
function deleteRoles(){
	var param_data = {};
	var selectRows = $("#shopRoleDataGrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.rolesId;
	});
	param_data.ids=ids;
	$.messager.confirm('确认','您确认想要删除店铺角色吗？',function(r){    
	    if (r){  
	    	$.ajax({
	    	    url:_path + '/roleManager/deleteShopRole',
	    	    type:"post",
	    	    data:param_data,
	    	    dataType:"json",
	    	    success:function(_data){
	    	    	if (_data.head.retCode == '000000') {
		    			easyuiMsg('提示', "删除成功！");
		    			$('#shopRoleDataGrid').datagrid('reload', {});
		    		} else if (_data.head.retCode == '000007') {
		    			easyuiMsg('提示', "部分角色未删除，因为部分角色存在店员在使用或存在资源菜单关联！");
		    			$('#shopRoleDataGrid').datagrid('reload', {});
		    		}else{
		    			easyuiMsg('错误', "删除失败！");
		    		}
	    	    }
	    	});
	    	 
	    }
	}
	);
}


