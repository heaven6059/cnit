/**
 * 功能描述： 角色管理的js
 * 
 */
$(function() {
	$('#table-role-manager').datagrid({
		url : _path + '/shopRole/shopRoleList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'roleId',
			halign: 'center',
			align : 'center',
			hidden: 'true',
			title : '角色ID'			
		},{
			field : 'roleName',
			halign: 'center',
			align : 'center',
			title : '角色名称'			
		},{
			field : 'description',
			halign: 'center',
			align : 'center',
			title : '角色描述'
		},/*{
			field : 'companyName',
			halign: 'center',
			align : 'center',
			title : '主店名称'
		},{
			field : 'storeName',
			halign: 'center',
			align : 'center',
			title : '分店名称'
		},*/{
			field : 'resourceName',
			width : 400,
			halign: 'center',
			align : 'center',
			title : '拥有的权限',
		},{
			field : 'editor',
			title : '操作',
			halign: 'center',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a href='javascript:editRole("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		} ] ],
		toolbar : '#toolbar-role-manager',
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
		nowrap : false,
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
	//主店改变
	/*$("#companyId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#storeId").empty();
		$("#storeId").val('--请选择--').trigger("change");
		getStoreData(biz.rootPath() + '/shopRole/findStoreSelect?companyId='+$("#companyId").val(),'storeId');
	});*/
	
});

//生成添加页面的菜单树
var setting = {
		check: {
			enable: true ,
			chkStyle:"checkbox" // 添加生效
			},
		data: { simpleData: { enable: true } },
		callback: {
			onCheck: function(event, treeId, treeNode){
			}
		}
	};

/*var zNodes =[
 			{ id:1, pId:0, name:"随意勾选 1", open:true},
 			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
 			{ id:111, pId:11, name:"随意勾选 1-1-1"},
 			{ id:112, pId:11, name:"随意勾选 1-1-2"},
 			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
 			{ id:121, pId:12, name:"随意勾选 1-2-1"},
 			{ id:122, pId:12, name:"随意勾选 1-2-2"},
 			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
 			{ id:21, pId:2, name:"随意勾选 2-1"},
 			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
 			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
 			{ id:222, pId:22, name:"随意勾选 2-2-2"},
 			{ id:23, pId:2, name:"随意勾选 2-3"}
 		];*/
//生成添加页面的菜单树
function inintArearTree() {
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
};

var zNodes;

//打开添加对话框
function openSaveDialog() {
	$('#form-role-manager-add').form('clear');
	$('#window-add-role-manager').panel({title: "添加角色"});
	var param_data = {};
	param_data.roleId = '';
	//生成添加页面的菜单树
	$.post(_path + '/shopResource/resourceTree', param_data, function(data) {
		zNodes=data;
		inintArearTree();
	}, 'json');
	
	$('#window-add-role-manager').window('open');
}


/**
 * 方法描述：新增角色
 */
function saveRole() {
	if ($('#form-role-manager-add').form('validate')) {
		
		var param_data = biz.serializeObject($("#form-role-manager-add"));
		//获取树的所勾选的值
		var ids=[],names=[];
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");//树控件
		var nodes = treeObj.getCheckedNodes(true);//获取所有选择的节点
		//alert("nodes="+nodes);
		//alert("len="+nodes.length);
		for(var i=0;i<nodes.length;i++){
			//-1不存在子节点 或 子节点全部设置为 nocheck = true
			//0	无 子节点被勾选
			//1	部分 子节点被勾选
			//2	全部 子节点被勾选
			var state=nodes[i].check_Child_State;
			//alert("state="+state);
			var nodesLevel=nodes[i].level;//级别
			//alert("level="+nodesLevel);
			if(nodesLevel==0){
				//if((state==2||state==-1)){
					ids.push(nodes[i].id);
					names.push(nodes[i].name);
				//}
			}else if(nodesLevel==1){
				var parentState=nodes[i].getParentNode().check_Child_State;
				//if((state==2||state==-1) && parentState==1){
					ids.push(nodes[i].id);
					names.push(nodes[i].name);
				//}
			}else if(nodesLevel==2){
				var parentState=nodes[i].getParentNode().check_Child_State;
				//if(state==-1 && parentState==1){
					ids.push(nodes[i].id);
					names.push(nodes[i].name);
				//}
			}
		}
		var ct="";
		$.each(ids, function(index, obj) {
			ct += obj+",";
		});
		ct=ct.length>1?ct.substring(0, ct.length-1):ct;
		
		
/*		param_data.roleName = $("#roleName").val();
		param_data.description = $("#description").val();
		param_data.companyId = $("#companyId").val();
		param_data.storeId = $("#storeId").val();*/
		param_data.resourceIds=ct;
		if($("#roleId").val()=='' || $("#roleId").val()==null){ //添加
			param_data.roleId = 0;
			$.post(_path + '/shopRole/insertShopRole', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-role-manager').datagrid('reload', {});
					$('#window-add-role-manager').window('close');
				} else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}else { //更新
			param_data.roleId=$("#roleId").val();
			$.post(_path + '/shopRole/updateRole', param_data, function(_data) {
				if (_data.head.retCode == '000000') {
					$('#table-role-manager').datagrid('reload', {});
					$('#window-add-role-manager').window('close');
				} else {
					easyuiMsg('错误', "保存失败！");
				}
			}, 'json');
		}
		
	}
}

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


//逻辑删除角色
function deleteRole(){
	var selectRows = $("#table-role-manager").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var id =[];
	$.each(selectRows,function(i,v){
		id=v.roleId;
	});
	var params={};
	alert("id="+id);
	params.id=id;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/shopRole/deleteRole',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-role-manager').datagrid('reload', {});
			    		} else if (_data.head.retCode == '000007'){
			    			easyuiMsg('错误', "存在级联数据！");
			    			$('#table-role-manager').datagrid('reload', {});
			    		}else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
	
}



//打开编辑角色对话框
function editRole(row){
	$('#form-role-manager-add').form('clear');
	$('#form-role-manager-add').form('load', row);
	$('#window-add-role-manager').panel({title: "编辑角色"});
	/*$.post(_path + '/shopRole/resourceTree', null, function(data) {
		zNodes=data;
		inintArearTree();
	}, 'json');*/
	var param_data = {};
	param_data.roleId = row.roleId;
	$.post(_path + '/shopResource/resourceTree', param_data, function(data) {
		zNodes=data;
		inintArearTree();
	}, 'json');
	$("#roleId").val(row.roleId);
	$("#roleName").val(row.roleName);
	$("#description").val(row.description);
	$('#window-add-role-manager').window('open');
}






