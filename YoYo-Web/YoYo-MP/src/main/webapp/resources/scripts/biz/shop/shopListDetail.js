/**
 * 功能描述： 店铺审核详细页js
 * 
 */
$(function() {
	$('#experienceDataGrid'+$('#shopCompanyId').val()).datagrid({
		url :_path + '/shopCheck/findExperience?companyId='+$('#shopCompanyId').val(),
		columns : [ [ {
			field : 'modifyTime',
			sortable : true,
			halign: 'center',
			title : '日期',
			formatter : function(value, row) {
				if(value!=null && value!=''){
					return new Date(value.time).format("yyyy-MM-dd hh:mm:ss");
				}else{
					return "";
				}
			}
		}, {
			field : 'type',
			sortable : true,
			halign: 'center',
			title : '摘要',
			formatter : function(value, row) {
				var str = '';
				if(value=='0'){
					str='管理员改变经验值';
				}else if(value=='1'){
					str = '店铺评分获得经验值';
				}
				return str;
			}
		},{
			field : 'remark',
			halign : 'center',
			title : '备注'
			
		}, {
			field : 'experience',
			halign: 'center',
			title : '经验值'
		}, {
			field : 'orderId',
			halign: 'center',
			title : '订单'
		},  {
			field : 'loginName',
			halign: 'center',
			title : '操作员'
		}
		 ] ],
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		remoteSort : true,
		multiSort : true,
		pageSize: 5,
	    pageList: [5,20,50,100,200],
	   
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	
	});
	
	/*//图片空间
	$('#pictureDataGrid').datagrid({
		url :'',
		columns : [ [ {
			field : 'companyName',
			sortable : true,
			halign: 'center',
			title : '日期'			
		}, {
			field : 'storeName',
			sortable : true,
			halign: 'center',
			title : '摘要'
		},{
			field : 'shopName',
			halign : 'center',
			halign: 'center',
			title : '备注'
			
		}, {
			field : 'companyUrl',
			halign: 'center',
			title : '空间'
		},  {
			field : 'approvedremark',
			halign: 'center',
			title : '操作员'
		}
		 ] ],
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		remoteSort : true,
		multiSort : true,
		pageSize: 5,
	    pageList: [5,20,50,100,200],
	   
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	*/
	
	
	//保证金
	$('#earnestDataGrid'+$('#shopCompanyId').val()).datagrid({
		url :_path + '/earnestManager/findList?companyId='+$('#shopCompanyId').val(),
		columns : [ [ {
			field : 'addtime',
			sortable : true,
			halign: 'center',
			title : '日期',
			formatter : function(value, row) {
				if(value!=null && value!=''){
					return new Date(value.time).format("yyyy-MM-dd hh:mm:ss");
				}else{
					return "";
				}
			}
		}, {
			field : 'source',
			sortable : true,
			halign: 'center',
			title : '摘要',
			formatter : function(value, row) {
				var str='';
				if(value=='0'){
					str = '虚假发货';
				}else if(value=='1'){
					str='管理员';
				}else if(value=='2'){
					str='前台';
				}
				return str;
			}
		},{
			field : 'remark',
			halign : 'center',
			halign: 'center',
			title : '备注'
			
		}, {
			field : 'earnestValue',
			halign: 'center',
			title : '保证金'
		}, {
			field : 'orders',
			halign: 'center',
			title : '订单'
		},  {
			field : 'operator',
			halign: 'center',
			title : '操作员'
		}
		] ],
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		remoteSort : true,
		multiSort : true,
		pageSize: 5,
	    pageList: [5,20,50,100,200],
	   
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	//分店信息
	$('#storeDataGrid'+$('#shopCompanyId').val()).datagrid({
		url :_path + '/shop/findShopList?companyId='+$('#shopCompanyId').val(),
		columns : [[{
			field : 'storeName' ,
			align:'center',
			title : '分店名称'
		} , {
			field : 'addrs' ,
			title : '分店地址',
			align:'center',
			formatter: function(value,rows,index){
				if(rows.area!=null && rows.area!=''){
					return rows.area.replace(/-/g,' ')+" "+rows.addr;
				}else{
					return '';
				}
			}
		} , {
			field : 'lastModify' ,
			align:'center',
			title : '添加时间',
			formatter: function(value,row,index){
				if(value!=null && value!=''){
					return new Date(value.time).format("yyyy-MM-dd hh:mm:ss");
				}else{
					return "";
				}
			}
		}]] ,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		remoteSort : true,
		multiSort : true,
		pageSize: 10,
	    pageList: [10,20,50,100,200],
	   
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	loadPicture();
});

//保存经验值
function saveExperience(){
	if ($('#experienceForm'+$('#shopCompanyId').val()).form('validate')) {
		var param_data = biz.serializeObject($("#experienceForm"+$('#shopCompanyId').val()));
		param_data.companyId=$('#shopCompanyId').val();
		console.log(param_data);
		if(param_data.experience==''||param_data.experience==null){
			easyuiMsg('提示', "请填写经验值！");
			return false;
		}
		$.post(_path + '/shopCheck/saveExperience', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#experienceDataGrid').datagrid('reload', {});
				var ddv = $("#shopListDataGrid").datagrid('getRowDetail', $('#shop_datagrid_index').val()).find('div.shopCheckDetail');
				ddv.panel('refresh');
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}

//保存保证金
function saveEarnest(){
	if ($('#earnestForm'+$('#shopCompanyId').val()).form('validate')) {
		var param_data = biz.serializeObject($("#earnestForm"+$('#shopCompanyId').val()));
		param_data.companyId=$('#shopCompanyId').val();
		param_data.source='1'; //管理员
		console.log(param_data);
		if(param_data.earnestValue==''||param_data.earnestValue==null){
			easyuiMsg('提示', "请填写保证金值！");
			return false;
		}
		
		$.post(_path + '/earnestManager/saveEarnest', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#earnestDataGrid').datagrid('reload', {});
				var ddv = $("#shopListDataGrid").datagrid('getRowDetail', $('#shop_datagrid_index').val()).find('div.shopCheckDetail');
				ddv.panel('refresh');
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}

function loadPicture(){
	$.each($('.shoplist-confirm img'),function(i,that){
		$(that).attr('src',yoyo.imagesUrl+$(that).attr('data-img'));
	});
}


