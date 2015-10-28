var datagrid = null;
var _var = {//全局变量
	retCode:{
		'success':'000000',
		'failure':'000002'
	},
	adType : {
		"0":"轮播",
		"1":"图片",
		"2":"文字"
	},
	enabled : {
		"false":"关闭",
		"true":"开启"
	}
};
$(function(){
	datagrid = $('#adListDataGrid');
	datagrid.datagrid({
		url:_path+'/siteAd/adList',
		method:'GET',
		//idField:'adId',
		pagination : true,
		pageSize: 25,
	    pageList: [25,50,100,200],
	    width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		remoteSort : true,
		multiSort : true,
		rownumbers : true,
		fitColumns : true,
		nowrap : false,
		toolbar : '#toolbar',
	    columns:[[{
	    	field:'adId',
	    	hidden:true
	    	
	    },{
	    	field:'ck',
			checkbox:"true",
			width:'2%'
	    },{
	    	field:'adType',
	        title:'广告类型',
	        align:'center',
	        width:100,
	        formatter: function(value,row,index){
	        	debugger
	    		return _var.adType[value];
	    	}
	    },{
	    	field:'name',
	        title:'广告名称',
	        width:100,
	        align:'center',
	        editor:{
	        	type:'text',
	        	options:{
	        	}
	        }
	    },{
	    	field:'beginTime',
	    	title:'开始时间',
	    	align:'center',
	    	width:100,
	    	formatter: function(value,row,index){
	    		if(value==null){
	    			return '';
	    		}
	    		return new Date(value).format('yyyy-MM-dd hh:mm:ss');
			}
	    },{
	    	field:'endTime',
	    	title:'结束时间',
	    	align:'center',
	    	width:100,
	    	formatter: function(value,row,index){
	    		if(value==null){
	    			return '';
	    		}
	    		return new Date(value).format('yyyy-MM-dd hh:mm:ss');
	    	}
	    },{
	    	field:'enabled',
	    	title:'启用状态',
	    	width:100,
	    	align:'center',
	    	formatter: function(value,row,index){
	    		if(value==null){
	    			return '';
	    		}
	    		return _var.enabled[value];
	    	}
	    },{
	    	field:'description',
	    	title:'描述',
	    	width:100,
	    	align:'center',
	    },{
	    	field:'editor',
	    	title:'操作',
	    	width:100,
	    	align:'center',
	    	formatter: function(value,row,index){
	    		return '<a href="javascript:void(0);" onclick="configAd('+ index +');" style="margin-right:10px">配置</a>' + '<a href="javascript:void(0);" onclick="editAd('+row.adId+',\''+row.name+'\');">编辑</a>';
	    	}
	    }]],
	    loadFilter: function(data){
	    	debugger
	    	if (data.rows){
				return data;
			} else{
				return data.content;
			}
	    }
	});
	
	
});

function linkage(obj, selector ){
	if(obj.checked == true){
		$('#addForm').find('.'+ selector ).each(function(){
			this.checked="checked";
		});
		
		$('#picWidth').removeAttr('disabled');
		$('#picHeight').removeAttr('disabled');
	}else{
		debugger
		$('#addForm').find('.'+ selector ).removeAttr("checked");
		$('#picWidth').val("");
		$('#picHeight').val("");
		$('#picWidth').attr('disabled','true');
		$('#picHeight').attr('disabled','true');
	}
}

/**
 * 编辑广告内容
 */
function editAd(id,name){
	var url = "/siteAd/edit?id="+id;
	debugger
	window.parent.addTabs(url,'编辑广告'+name);
}

/**
 * 配置广告配置 
 */
function configAd(index){
	var data = datagrid.datagrid('getData');
	var rowData = data.rows[index];
	$('#window').form('clear');
	$('#adType').combobox("setValue",rowData.adType);
	$('#name').val(rowData.name);
	$('#adType').combobox('disable');
	$('#name').attr('disabled','true');
	$('#adNum').val(rowData.adNum);
	$('#adId').val(rowData.adId);
	$('#addOrEdit').val(1);
	if(rowData.picSize){
		var sizeArr = rowData.picSize.split(',');
		$('#picWidth').val(sizeArr[0]);
		$('#picHeight').val(sizeArr[1]);
	}
	if(rowData.config){
		var selectedArr = rowData.config.split(',');
		for ( var i = 0; i < selectedArr.length; i++) {
			document.getElementById(selectedArr[i]).checked="checked";
			if('needPic' == selectedArr[i]){
				$('#picWidth').removeAttr('disabled');
				$('#picHeight').removeAttr('disabled');
			}
		}
	}
	$('#window').window('open');
}

function getParam(type){
	debugger
	var configObj = {};
	configObj.adType = $("#adType").combobox("getValue");
	configObj.name = $("#name").val().trim();
	if($('#adNum').val() && $('#adNum').val() > 0){
		configObj.adNum = $('#adNum').val();
	}
	var config = '';
	$('#addForm input:checked').each(function(){
		config += $(this).val()+',';
	});
	if(config.length>1){
		configObj.config = config.slice(0,config.length-1);
	}
	if(type==1){
		configObj.adId = $('#adId').val();
	}
	if($('#picWidth').val() && $('#picHeight').val()){
		configObj.picSize = $('#picWidth').val()+','+$('#picHeight').val()
	}
	return configObj;
}

function openDialog(){
	debugger
	$('#picWidth').attr('disabled','true');
	$('#picHeight').attr('disabled','true');
	$('#adType').combobox('enable');
	$('#name').removeAttr('disabled');
	$('#window').form('clear');
	$('#addOrEdit').val('0');
	$('#window').window('open');
}
/**
 * @param 0新增，1修改
 */
function save(){
	debugger
	close();
	var _url = "";
	var type = $('#addOrEdit').val();
	if(type == 0){
		_url = _path+'/siteAd/newAd';
	}else if(type == 1){
		_url= _path+'/siteAd/editAdConfig';
	}
	$.ajax({
		url: _url,
		data: getParam(type),
		type: "post"
	}).done(function(data){
		if(data.retCode == yoyo.successCode){
			$.messager.show({
				title:'提示',
				msg:'保存成功'
			});
			$('#adListDataGrid').datagrid('reload');
		}else{
			$.messager.show({
				title:'提示',
				msg:'保存失败'
			});
		};
	});
}

function deleteAd(){
	var ids =[];
	var selected = datagrid.datagrid("getSelections");
	if(!selected || selected.length < 0){
		$.messager.show({
			title:'错误',
			msg:'请选择要删除的广告',
			timeout:3000,
			showType:'slide'
		});
	}else{
		for(var i = 0; i<selected.length; i++){
			ids.push(selected[i].adId);
		}
		
		$.ajax({
			url: _path+'/siteAd/deleteAd',
			data: {adIds:ids.toString()},
			type: "post"
		}).done(function(data){
			if(data.retCode == yoyo.successCode){
				$.messager.show({
					title:'提示',
					msg:'删除成功'
				});
				$('#adListDataGrid').datagrid('reload');
			}else{
				$.messager.show({
					title:'提示',
					msg:'删除失败'
				});
			};
		});
	}
}
function close(){
	$('#window').window('close');
}