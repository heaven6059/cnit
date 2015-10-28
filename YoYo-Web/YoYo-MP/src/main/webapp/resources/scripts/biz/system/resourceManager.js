/**
 * 功能描述： 资源管理的js
 * 
 */
$(function() {
	$('#table-resource-manager').datagrid({
		url : _path + '/resourceManager/resourceManagerList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'id',
			align : 'center',
			halign: 'center',
			title : '资源ID'			
		},{
			field : 'resourceName',
			align : 'center',
			halign: 'center',
			title : '资源名称'			
		},{
			field : 'resourceType',
			halign: 'center',
			align : 'center',
			title : '资源类型'	,
			formatter : function(value, row) {
				if(value=='menu'){
					return '菜单';
				}else if(value=='button'){
					return '按钮';
				}
			}
		},{
			field : 'url',
			halign: 'center',
			align : 'center',
			title : '资源路径'			
		},/*{
			field : 'disabled',
			halign: 'center',
			align : 'center',
			title : '资源可用性',
			formatter : function(value, row) {
				if(value=='1'){
					return '不可用';
				}else if(value=='0'){
					return '可用';
				}
			}
		},{
			field : 'display',
			halign: 'center',
			align : 'center',
			title : '资源显示性',
			formatter : function(value, row) {
				if(value=='1'){
					return '不可见';
				}else if(value=='0'){
					return '可见';
				}
			}
		},*/{
			field : 'permission',
			halign: 'center',
			align : 'center',
			title : '权限'
		},{
			field : 'target',
			halign: 'center',
			align : 'center',
			title : '是否叶节点',
			formatter : function(value, row) {
				if(value=='0'){
					return '否';
				}else if(value=='1'){
					return '是';
				}
			}
		},{
			field : 'menuOrder',
			halign: 'center',
			align : 'center',
			title : '级别'
		},{
			field : 'parentId',
			halign: 'center',
			align : 'center',
			title : '父节点'
		},/*{
			field : 'parentIds',
			halign: 'center',
			align : 'center',
			title : '父节点列表'
		},*/{
			field : 'editor',
			title : '操作',
			halign: 'center',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a style='align:center;' href='javascript:editResource("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#toolbar-resource-manager',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageList : [ 25, 50, 100, 150 ],
		singleSelect : true,
		checkOnSelect:false,
		remoteSort : false,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
});


//打开添加对话框
function openSaveDialog() {
	$('#form-resource-manager-add').form('clear');
	$('#window-add-resource-manager').panel({title: "添加资源"});
	//getRoleData(biz.rootPath() + '/userManager/findSelect','roleId',true);//获取角色下拉列表数据
	$('#window-add-resource-manager').window('open');
}


/**
 * 方法描述：新增资源
 */
function saveResource() {
	if ($('#form-resource-manager-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-resource-manager-add"));
		if($("#resourceType").val()=='' || $("#resourceType").val() == null ||$("#resourceType").val() == '-1'){
			easyuiMsg('错误',"请选择资源类型！");
			return false;
		}
		if($("#target").val()=='' || $("#target").val() == null ||$("#target").val() == '-1'){
			easyuiMsg('错误',"请选择是否叶节点！");
			return false;
		}
		
		param_data.id = $("#id").val();
		
		if($("#id").val()=='' || $("#id").val()==null){ //添加
			$.post(_path + '/resourceManager/insertResource', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-resource-manager').datagrid('reload', {});
					$('#window-add-resource-manager').window('close');
				} else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}else { //更新
			param_data.resourceId=$("#id").val();
			$.post(_path + '/resourceManager/updateResource', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-resource-manager').datagrid('reload', {});
					$('#window-add-resource-manager').window('close');
				} else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
		
	}
}


//逻辑删除资源
function deleteResource(){
	var selectRows = $("#table-resource-manager").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id=v.id;
	});
	var params={};
	params.id=id;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/resourceManager/deleteResource',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-resource-manager').datagrid('reload', {});
			    		} else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}



//打开编辑资源对话框
function editResource(row){
	$('#form-resource-manager-add').form('clear');
	$('#window-add-resource-manager').panel({title: "编辑资源"});
	$("#id").val(row.id);
	$('#resourceName').val(row.resourceName);
	$("#resourceType").val(row.resourceType).trigger("change");
	$("#target").val(row.target).trigger("change");
	$('#url').val(row.url);
	$('#permission').val(row.permission);
	$('#menuOrder').val(row.menuOrder);
	$('#parentId').val(row.parentId);
	
	$('#window-add-resource-manager').window('open');
}






