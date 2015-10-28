/**
 * 功能描述： 店员js
 * 
 */
$(function() {
	$('#shopClerksDataGrid').datagrid({
		url : _path + '/clerkManager/shopClerksList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'shopkeeper',
			sortable : true,
			halign: 'center',
			title : '店主名'			
		}, {
			field : 'loginName',
			sortable : true,
			halign: 'center',
			title : '店员名'
		},{
			field : 'storeName',
			sortable : true,
			halign: 'center',
			title : '店铺名称'
		},{
			field : 'shopName',
			sortable : true,
			halign: 'center',
			title : '分店名称'
		},{
			field : 'rolesName',
			halign: 'center',
			sortable : true,
			title : '角色名称'
			
		} ,{
			field : 'lastModify',
			halign: 'center',
			sortable : true,
			title : '更新时间'
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
function deleteClerks(){
	var param_data = {};
	var selectRows = $("#shopClerksDataGrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.attachId;
	});
	param_data.ids=ids;
	$.messager.confirm('确认','您确认想要删除店员吗？',function(r){    
	    if (r){  
	    	$.ajax({
	    	    url:_path + '/clerkManager/deleteShopClerk',
	    	    type:"post",
	    	    data:param_data,
	    	    dataType:"json",
	    	    success:function(_data){
	    	    	if (_data.head.retCode == '000000') {
		    			easyuiMsg('提示', "删除成功！");
		    			$('#shopClerksDataGrid').datagrid('reload', {});
		    		} else{
		    			easyuiMsg('错误', "删除失败！");
		    		}
	    	    }
	    	});
	    	 
	    }
	}
	);
}


