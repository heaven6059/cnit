/**
 * 功能描述： 用户管理的js
 * 
 */
$(function() {
	$('#table-user-manager').datagrid({
		url : _path + '/shopUser/userManagerList',
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
		},{
			field : 'editor',
			halign: 'center',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:editUser("+JSON.stringify(row)+")'>绑定角色</a>";
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
	
	//getCompanyData(biz.rootPath() + '/shopRole/findCompanySelect','companyId');
	//getStoreData(biz.rootPath() + '/shopRole/findStoreSelect','storeId');
	getRoleData(biz.rootPath() + '/shopUser/findSelect','roleId');
	//主店改变
	/*$("#companyId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#storeId").empty();
		$("#storeId").val('--请选择--').trigger("change");
		getStoreData(biz.rootPath() + '/shopRole/findStoreSelect?companyId='+$("#companyId").val(),'storeId');
		
		$("#roleId").empty();
		$("#roleId").val('--请选择--').trigger("change");
		getRoleData(biz.rootPath() + '/shopUser/findSelect?companyId='+$("#companyId").val(),'roleId');//获取角色下拉列表数据
	});*/
	
});

//获得主店数据
function getCompanyData(url,obj){
	$.getJSON(url, function(json) {
		var data="";
		data+='[{ "text": "--请选择--", "id": ""   }';
		$.each(json.content, function(i, n) {
		
			data+=',{ "text": "'+n.companyName+'", "id":'+n.companyId+'}';
		});
		data+="]";
		
		$("#"+obj).select2({data:eval(data)});
	});
}

//获得分店数据
function getStoreData(url,obj){
	$.getJSON(url, function(json) {
		var data="";
		data+='[{ "text": "--请选择--", "id": ""   }';
		$.each(json.content, function(i, n) {
			data+=',{ "text": "'+n.storeName+'", "id":'+n.storeId+'}';
		});
		data+="]";
		$("#"+obj).select2({data:eval(data)});
	});
}

function getRoleData(url,obj){
	$.getJSON(url, function(json) {
		var data="";
		data+='[{ "text": "--请选择--", "id": ""   }';
		$.each(json.content, function(i, n) {
			data+=',{ "text": "'+n.roleName+'", "id":'+n.roleId+'}';
		});
		data+="]";
		
		$("#"+obj).select2({data:eval(data)});
	});
}


//打开添加对话框
/*function openSaveDialog() {
	$('#form-user-manager-add').form('clear');
	getRoleData(biz.rootPath() + '/shopUser/findSelect?companyId='+companyId,'roleId');
	$('#window-add-user-manager').window('open');
}*/

//获得角色数据
/*function getRoleData(url,obj,isSelect){
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
}*/


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
		/*if($("#companyId").val()=='' || $("#companyId").val() == null ||$("#companyId").val() == '-1'){
			easyuiMsg('错误',"请选择主店！");
			return false;
		}
		if($("#storeId").val()=='' || $("#storeId").val() == null ||$("#storeId").val() == '-1'){
			easyuiMsg('错误',"请选择分店！");
			return false;
		}*/
		
		param_data.userId = $("#accountId").val();
		
		/*if($("#accountId").val()=='' || $("#accountId").val()==null){ //添加
			$.post(_path + '/shopUser/insertShopUserRole', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-user-manager').datagrid('reload', {});
					$('#window-add-user-manager').window('close');
				} else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}else { //更新
*/			//param_data.id=$("#accountId").val();
			$.post(_path + '/shopUser/insertShopUserRole', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-user-manager').datagrid('reload', {});
					$('#window-add-user-manager').window('close');
				} else if (_data.head.retCode == 'MEM001') {
					easyuiMsg('错误', "没有会员信息！");
			}else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		/*}*/
		
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
		    	    url:_path + '/shopUser/deleteUser',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-user-manager').datagrid('reload', {});
			    		} else {
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
	//$("#carYearId").val(row.id);
	$('#window-add-user-manager').window('open');
}






