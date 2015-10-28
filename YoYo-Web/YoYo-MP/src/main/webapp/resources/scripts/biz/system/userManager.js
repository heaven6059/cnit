/**
 * 功能描述： 用户管理的js
 * 
 */
$(function() {
	$('#table-user-manager').datagrid({
		url : _path + '/userManager/userManagerList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'accountId',
			halign: 'center',
			align : 'center',
			hidden: 'true',
			title : '用户ID'			
		},{
			field : 'loginName',
			halign: 'center',
			align : 'center',
			title : '用户名'			
		}, {
			field : 'roleName',
			halign: 'center',
			align : 'center',
			title : '角色列表'
		},{
			field : 'editor',
			halign: 'center',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:editUser("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#toolbar-user-manager',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageList : [ 25, 50, 100, 150 ],
		//singleSelect : true,
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
	
	getRoleData(biz.rootPath() + '/userManager/findSelect','roleId',true);//获取角色下拉列表数据
});


//打开添加对话框
function openSaveDialog() {
	$('#form-user-manager-add').form('clear');
	$('#window-add-user-manager').panel({title: "添加用户"});
	//getRoleData(biz.rootPath() + '/userManager/findSelect','roleId',true);//获取角色下拉列表数据
	$('#window-add-user-manager').window('open');
}

//获得角色数据
function getRoleData(url,obj,isSelect){
	$.getJSON(url, function(json) {
		var data="[";
		if(isSelect){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {console.log(n);
			data+='{ text: "'+n.roleName+'", id:'+n.roleId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		
		$("#"+obj).select2({data:eval(data)});
	});
}




/**
 * 方法描述：新增用户
 */
function saveUser() {
	if ($('#form-user-manager-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-user-manager-add"));
		if($("#roleId").val()=='' || $("#roleId").val() == null ||$("#roleId").val() == '-1'){
			easyuiMsg('错误',"请选择角色！");
			return false;
		}
		
		if($("#loginName").val()=='' || $("#loginName").val() == null){
			easyuiMsg('错误',"用户名不能为空！");
			return false;
		}
		
		if($("#loginPassword").val()=='' || $("#loginPassword").val() == null){
			easyuiMsg('错误',"用户密码不能为空！");
			return false;
		}
		
		param_data.accountId = $("#accountId").val();
		
		if($("#accountId").val()=='' || $("#accountId").val()==null){ //添加
			$.post(_path + '/userManager/insertUser', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					easyuiMsg('提示', "保存成功！");
					$('#table-user-manager').datagrid('reload', {});
					$('#window-add-user-manager').window('close');
				} else if (_data.head.retCode == '000004') {
					easyuiMsg('提示', "该用户已存在！");
				}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}else { //更新
			param_data.id=$("#accountId").val();
			$.post(_path + '/userManager/updateUser', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-user-manager').datagrid('reload', {});
					$('#window-add-user-manager').window('close');
				} else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
		
	}
}


//逻辑删除用户
function deleteUser(){
	var selectRows = $("#table-user-manager").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id=v.accountId;
	});
	var params={};
	params.id=id;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/userManager/deleteUser',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-user-manager').datagrid('reload', {});
			    		} else if (_data.head.retCode == '000007') {
			    			easyuiMsg('提示', "存在级联数据！");
		    			$('#table-user-manager').datagrid('reload', {});
		    		} else{
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}



//打开编辑用户对话框
function editUser(row){
	$('#form-user-manager-add').form('clear');
	$('#form-user-manager-add').form('load', row);
	$('#window-add-user-manager').panel({title: "编辑用户"});
	
	$("#accountId").val(row.accountId);
	//alert("kkk="+row.roleId);
	$("#roleId").val(row.roleId).trigger("change");
	$("#loginName").val(row.loginName);
	//$("#carYearId").val(row.id);
	$('#window-add-user-manager').window('open');
}






