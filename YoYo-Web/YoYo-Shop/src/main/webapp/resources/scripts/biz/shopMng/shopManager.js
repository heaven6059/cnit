//店铺管理

//初始化店铺管理列表
function initShopListDg(){
	$('#shoplist_dg').datagrid({
		url : _path+'/shopManager/findShopList' ,
		columns : [[{
			field : 'storeName' ,
			align:'left',
			title : '分店名称'
		} , {
			field : 'addrs' ,
			title : '分店地址',
			align:'left',
			formatter: function(value,rows,index){
				return rows.area.replace(/-/g,' ')+" "+rows.addr;
			}
		} , {
			field : 'lastModify' ,
			align:'center',
			title : '添加时间',
			formatter: function(value,row,index){
				return new Date(value.time).format("yyyy-MM-dd hh:mm:ss");
			}
		} , {
			field : 'action' ,
			title : '操作',
			align:'center',
			
			formatter: function(value,row,index){
				// str = $.formatString('<img onclick="viewFun(\'{0}\',\'{1}\');" src="{2}" title="修改"/>', row.id,row.infoState, '${pageContext.request.contextPath}/resources/images/jqueryframe/note.png');
				
				return "<a href='#' onclick='editStore("+JSON.stringify(row)+")'><span style='margin-right:10px;color:blue;'>修改</span></a><a  onclick='deleteStore("+row.storeId+")'><span style='color:blue;'>删除</span></a>";
			}
		}]] ,
		pagination : true ,
		rownumbers : true ,
		fitColumns : true ,
		singleSelect : false ,
		remoteSort : false ,
		multiSort : true ,
		ctrlSelect : true ,
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		onLoadSuccess:function(data){
			if (data.rows){
				$("#limitStore").val(data.rows.length);
			} else{
				$("#limitStore").val('0');
			}
		}
	});
	
	
	//分店地址  获取一级地区
	findArea(0,1,"area_1");
	//获取二级地区
	$("#area_1").combobox({onSelect:function(record){
		//清空后面下拉框的数
		$("#area_3").combobox("loadData", {});
		$("#area_3").combobox("clear");
		findArea(record.id,2,"area_2");
		//$("#companyArea_1").val(record.id);
	}});
	//获取三级地区
	$("#area_2").combobox({onSelect:function(record){
		findArea(record.id,3,"area_3");
		$("#area_2").val(record.id);
	}});
}


//点击“添加分店”按钮
function addStoreclick(){
	$("#storeName").val('');
	$("#addr").val('');
	$('#addShop').show();
}



//保存新添加的分店
function saveStore(){
	var reg_data = biz.serializeObject($("#addShop_form"));
	
	 if(!$("#addShop_form").form('validate')){
		   return false;
	  } 
	 reg_data.area = $("#area_1").combo('getText')+"-"+$("#area_2").combo('getText')+"-"+$("#area_3").combo('getText');
	 reg_data.areaIds = $("#area_1").combo('getValue')+"-"+$("#area_2").combo('getValue')+"-"+$("#area_3").combo('getValue');
	 if($("#area_3").combo('getValue')=='' || $("#area_3").combo('getValue')==null){ //只有2级地区，如台湾，香港
		 reg_data.area=$("#area_1").combo('getText')+"-"+$("#area_2").combo('getText');
		 reg_data.areaIds = $("#area_1").combo('getValue')+"-"+$("#area_2").combo('getValue');
	 }
	 $.ajax({
		url : biz.rootPath() + "/shopManager/saveStore",
		data : reg_data,
		type : "post",
		datatype : "json",
		success : function(data) {
				if (data.head.retCode=='000000') {
					$.messager.alert('提示', '保存成功', 'info',function(){
						$("#addShop").hide();
						$("#shoplist_dg").datagrid('reload');
					});
				}else if(data.head.retCode=='000005'){
					$.messager.alert('错误', '保存失败,单店类型不能添加分店！', 'error');
				}else if(data.head.retCode=='000006'){
					$.messager.alert('错误', '保存失败,店铺个数已达到最大！', 'error');
				}else {
					$.messager.alert('错误', '保存失败', 'error');
				}
			}
	 });
}


//修改分店信息
function editStore(data){
	$("#addShop_form").form("load",data);
	var areaIds = data.areaIds;
	if(areaIds!=null && areaIds.trim()!=''){
		var ids = areaIds.split("-");
		setDefaultArea(0,1,"area_1",ids[0]);  //注册地址
		setDefaultArea(ids[0],2,"area_2",ids[1]);
		setDefaultArea(ids[1],3,"area_3",ids[2]);

	}
	setTimeout("$('#addShop').show()",500);
}

//删除分店
function deleteStore(data){
	if(data!=null){
		if($("#limitStore").val()==1){
			$.messager.alert('错误', '分店不能全部删除，必须保留一个！', 'error');
			return false;
		}
	
		$.messager.confirm('提示', '确定删除该分店吗？',function(r){
			if(r){
				 $.ajax({
						url : biz.rootPath() + "/shopManager/deleteStore",
						data : {"storeId":data},
						type : "post",
						datatype : "json",
						success : function(data) {
								if (data.head.retCode=='000000') {
									$.messager.alert('提示', '删除成功', 'info',function(){
										$("#shoplist_dg").datagrid('reload');
									});
								}else {
									$.messager.alert('错误', '删除失败', 'error');
								}
							}
					 });
			}
		});
	}
}


/**defaultVal:地区默认值id*/
function setDefaultArea(pid,deepId,obj,defaultVal){
	$.getJSON(biz.rootPath() + '/areaController/find?areaParentId='+pid+'&areaDeep='+deepId, function(json) {
		var data="";
		data+='[{ "text": "--请选择--", "id": "" ,"selected":true  }';
		$.each(json.resultObject.content, function(i, n) {
			if(defaultVal!=null && defaultVal.trim()!="" && defaultVal==n.areaId){
				data+=',{ "text": "'+n.areaName+'", "id":'+n.areaId+',"selected":true}';
			}else{
				data+=',{ "text": "'+n.areaName+'", "id":'+n.areaId+'}';
			}
		});
		data+="]";
		$("#"+obj).combobox("loadData", $.parseJSON(data));
	});
	
}
