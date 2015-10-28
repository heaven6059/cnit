/**
 * 初始化表格
 */
function DataGrid(){
	$('#clerk_datagrid').datagrid({
		url :  _path+'/clerkManager/clerkInfoSelect' ,
		columns : [[{
			field:  'attachId',hidden:true,
			align:'center',
			title:  '店员ID'
		},{
			field:  'loginName',
			align:'center',
			title:  '用户名'
		},{
			field:  'rolesName',
			align:'center',
			title:  '角色'
		},{
			field:  'tel',
			align:'center',
			title:  '联系电话'
		},{
			field:  'name',
			align:'center',
			title:  '姓名'
		},{
			field:  'storeName',
			align:'center',
			title:  '店铺名称'
		},{
			field:  'email',
			align:'center',
			title:  '邮箱'
		},{
			field:  'regTime',
			align:'center',
			title:  '注册时间',
			formatter: function(value,row,index){
				return strToDate(value);
			}
		},{
			field:  'operate',
			title:  '操作',
			align:'center',
			formatter:function(value,row,index){  
	                    var s = '<a href="#" onclick="editrow(\''+row.loginName+'\',\''+row.roleId+'\',\''+row.attachId+'\',\''+row.storeId+'\')">修改</a> ';  
	                    var c = '<a href="#" onclick="deleterow('+index+')">删除</a>';  
	                    return s+c;  
	             
			},
		}]],
		pagination : true ,
		rownumbers : true ,
		fitColumns : true ,
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
}

//检查邮箱是否已使用
function checkEmailIfUsed(obj){
	$obj = $('#' + obj);
	if ($obj.val()){
		var param = {};
		param.loginName = $obj.val();
		param.loginNameType = '1';
		checkAccountExist(obj , param , function(data){
			if (data.content.rows != 0){
				renderMsg($obj , '该邮箱已被使用！');
			} else{
				getEmailCode(obj);
			}
		});
	} else{
		renderMsg($obj , '请输入邮箱地址！');
	}
}
//添加店员
function saveClerk(){
	 if($("#rolesName").combobox('getValue')==''){
		 $("#hint").empty();
		 $("#hint").append("<span id='hint_span' style='color:red'>角色不能为空!</span>");
		 return false;
	 }else {
		 $("#hint_span").remove();
	 }
	if(!$("#addClerk_form").form('validate')){
		  return false;
	} 
	if(companyType=='1'){ //集团
		 if($("#storeId").combobox('getValue')==''){
			 $("#hint").empty();
			 $("#hint").append("<span id='hint_span' style='color:red'>分店名称不能为空!</span>");
			 return false;
		 }else {
			 $("#hint_span").remove();
		 }
	}
	commonAjax(_path + "/clerkManager/clerkInfoInsert" , $('#addClerk_form').serializeArray() , saveClerkSuc , function(data){
		layer.alert(data.retMsg, {icon: 2}); 
	});
}

function saveClerkSuc(){
	$('#addClerk').dialog('close');
	$("#memberId").val('');
	$('#clerk_datagrid').datagrid('reload');
}


function save(){
	 $("#edit_hint").empty();
	 if($("#rolesName1").combobox('getValue')==''){
		 $("#edit_hint").append("<span id='hint_span' style='color:red'>角色不能为空!</span>");
		 return false;
	 }else {
		 $("#hint_span").remove();
	 }
	 if(companyType=='1'){ //集团
		 if($("#edit_storeId").combobox('getValue')==''){
			 $("#edit_hint").append("<span id='hint_span' style='color:red'>分店名称不能为空!</span>");
			 return false;
		 }else {
			 $("#hint_span").remove();
		 }
	}

	 
	$('#editClerk').dialog('close');
	formSubmit('editClerk_form' , _path+'/clerkManager/clerkInfoUpdate' , function(){	
		$('#clerk_datagrid').datagrid('reload');
	} , null);
}
function cancel(){
	$('#addClerk').dialog('close');
	$('#editClerk').dialog('close');
}
function add(){
	$('#loginName').val('');
	$("#edit_hint").empty();
	$("#hint").empty();
	$("#password1").val('');
	$("#password2").val('');
	$("#email").val('');
	$('#addClerk').dialog('open');
	//findrolesName("rolesName");
	var companyId = $('#companyId').val();
	getRoleData(biz.rootPath() + '/roleManager/findSelect?companyId='+companyId,'rolesName');
	if(companyType=='1'){ //集团
		$('#store_tr').show();
		findStore("storeId");
	}else{
		$.getJSON(biz.rootPath() + '/shopManager/findShopList', function(json) {console.log(json.content);	
			$('#storeId').val(json.content.rows[0].storeId);
		
	});
	}
	
}
/**
 * 编辑
 * @param loginName
 * @param roleId 
 * @param attachId  店员id
 * @param storeId  分店id
 */
function editrow(loginName,roleId,attachId,storeId){
	
	var data= $('#clerk_datagrid').datagrid('getSelected');
	if(data){
		$("#editClerk").dialog("open").dialog('setTitle','编辑成员');
		$("#cur_loginname").html(loginName);
		$("#attachId").val(attachId);
		if(companyType=='1'){
			$("#edit_store_tr").show();
			$.getJSON(biz.rootPath() + '/shopManager/findShopList', function(json) { //查找分店	
				var data="";
				data+='[{ "text": "--请选择--","id": ""   }';
				$.each(json.content.rows, function(i, n) {
					if(n.storeId==storeId){
						data+=',{ "text": "'+n.storeName+'", "id":'+n.storeId+',"selected":true}';
					}else{
						data+=',{ "text": "'+n.storeName+'", "id":'+n.storeId+'}';
					}
				});
				data+="]";
				$("#edit_storeId").combobox("loadData", $.parseJSON(data));
			});
		}else{
			$.getJSON(biz.rootPath() + '/shopManager/findShopList', function(json) { //查找分店	
				$('#storeId').val(json.content.rows[0].storeId);
			});
		}
		/*$.getJSON(biz.rootPath() + '/roleController/find', function(json) {	//查找角色
			var data="";
			data+='[{ "text": "--请选择--","id": ""   }';
			$.each(json.content, function(i, n) {
				if(n.rolesId==roleId){
					data+=',{ "text": "'+n.rolesName+'", "id":'+n.rolesId+',"selected":true}';
				}else{
					data+=',{ "text": "'+n.rolesName+'", "id":'+n.rolesId+'}';
				}
			});
			data+="]";
			$("#rolesName1").combobox("loadData", $.parseJSON(data));
			
		});*/
		var companyId = $('#companyId').val();
		getRoleData(biz.rootPath() + '/roleManager/findSelect?companyId='+companyId,'rolesName1');
	}
	
	
}


//查找角色名
function findrolesName(obj){
	$.getJSON(biz.rootPath() + '/roleController/find', function(json) {	
		var data="";
		data+='[{ "text": "--请选择--","id": "" ,"selected":true  }';
		$.each(json.content, function(i, n) {
			data+=',{ "text": "'+n.rolesName+'", "id":'+n.rolesId+'}';
		});
		data+="]";
		$("#"+obj).combobox("loadData", $.parseJSON(data));
	});
	
}

//获得用户数据
function getRoleData(url,obj){
	$.getJSON(url, function(json) {
		var data="";
		data+='[{ "text": "--请选择--", "id": ""   }';
		$.each(json.content, function(i, n) {
			data+=',{ "text": "'+n.rolesName+'", "id":'+n.rolesId+'}';
		});
		data+="]";
		
		$("#"+obj).combobox("loadData", $.parseJSON(data));
	});
}


//查找分店
function findStore(obj){
	$.getJSON(biz.rootPath() + '/shopManager/findShopList', function(json) {console.log(json.content);	
		var data="";
		data+='[{ "text": "--请选择--","id": "" ,"selected":true  }';
		$.each(json.content.rows, function(i, n) {
			data+=',{ "text": "'+n.storeName+'", "id":'+n.storeId+'}';
		});
		data+="]";
		$("#"+obj).combobox("loadData", $.parseJSON(data));
	});
	
}

//删除店员
function deleterow(target){
		$.messager.confirm('Confirm','確定要刪除?',function(r){
			if (r){
				var row = $('#clerk_datagrid').datagrid('getSelections');
				$.ajax({
					url : _path+'/clerkManager/clerkInfoDelete',
					data : {'attachId':row[0].attachId},	
					type : 'POST',
					dataType:'json',
					success : function(row) {						
							$('#clerk_datagrid').datagrid('reload');
					}

				});
				}
		});
	}

function checkloginName(){
	  $.ajax({
			url : _path+'/clerkManager/clerkLoginName?loginName='+$("#loginName").val(),
			type : 'POST',
			dataType:'json',
			success : function(data) {						
				if(data.retCode=='000000'){ 
					if(data.content=='0'){//不存在该用户名
						$("#password_tr1").show();
						$("#password_tr2").show();
						$("#email_tr3").show();		
					}else{ //是会员，不是店员
						$("#memberId").val(data.content);
					}
				}else{
					$.messager.alert('错误', '该用户已经是店员！请修改用户名', 'error',function(){
						$('#loginName').focus();
					});
					
				}
			}

		});
}



function checkPass(password1,password2){
	p1Value = document.getElementById(password1).value;
	p2Value = document.getElementById(password2).value;
	
	if(p1Value!=p2Value){
		
		$("#hint").html("<span id='hint_span' style='color:red'>二次密码不一样，请重新输入!</span>");
		/*document.getElementById(password1).value ='';
		document.getElementById(password2).value ='';	*/	
	}else{
		$("#hint_span").remove();
	}
}

