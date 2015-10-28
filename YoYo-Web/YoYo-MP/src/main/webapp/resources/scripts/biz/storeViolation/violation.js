/**
 * 功能描述： 汽车车型的js
 * 
 */
$(function() {
	//查询违规处理列表
	initList();
	//查询违规类型列表
	initViolationCate('search-combox-violation-cat',true);
	
	$('select[name="earnestMoneySearchType"]').on('change', function() {
		changeSearchType($(this).val());
	});
});
//查询违规处理列表
function initList(){
	$('#table-violation').datagrid({
		url : _path + '/violation/findViolationList',
		columns : [ [ {
			field:'ck',
			checkbox:"true",
			width:'2%'
		},{
			field : 'violationId',
			align : 'center',
			hidden: 'true',
			title : '违规ID'			
		},{
			field : 'catName',
			align : 'center',
			title : '违规类型',
			sortable: true
		}, {
			field : 'goodsdownDays',
			align : 'center',
			title : '下架店铺内所有商品天数',
			sortable: true
		}, {
			field : 'goodsDays',
			align : 'center',
			title : '限制发布商品天数',
			sortable: true
		}, /*{
			field : 'newsDays',
			align : 'center',
			title : '商品降权天数',
			sortable: true
		}, {
			field : 'newsDaysValue',
			align : 'center',
			title : '商品降权值',
			sortable: true
		}, */{
			field : 'storeDays',
			align : 'center',
			title : '店铺屏蔽天数',
			sortable: true
		}, {
			field : 'storedownDays',
			align : 'center',
			title : '关闭店铺天数',
			sortable: true
		}, /*{
			field : 'salesDays',
			align : 'center',
			title : '限制参加营销活动天数',
			sortable: true
		}, *//*{
			field : 'scoreValue',
			align : 'center',
			title : '扣分节点',
			sortable: true
		}, */{
			field : 'earnestMoney',
			align : 'center',
			title : '交付违约金',
			sortable: true
		}, {
			field : 'lastModify',
			align : 'center',
			title : '最后修改时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value.time);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		},{
			field: 'editor',
			align: 'center',
			title: '操作',
			width: '8%',
			formatter: function(value,rows,index){
				return "<a href='javascript:void(0);' onclick='openSaveDialog(\"edit\","+JSON.stringify(rows)+");' class='btn-grey'>编辑</a>&nbsp;"+
//				 	   "<a href='javascript:void(0);' onclick='deleteViolation("+rows.violationId+");' class='btn-grey'>删除</a>";
						"";
			}
		}]],
		toolbar : '#toolbar-violation',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		striped:true,
		fitColumns : true,
		pageSize : 25,
		pageList : [ 25, 50, 100, 150 ],
		singleSelect : true,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : false,
		selectOnCheck:false,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		},onDblClickCell : function(index , field , value){
			openSaveDialog("edit",JSON.stringify(rows));
		}
	});
	
}


function initViolationCate(id, exist) {
	var url = _path + '/storeViolationCat/findViolationCatList?parentCatId=0&exist='+exist;
	$.getJSON(url, function(json) {
		var data="[";
		if(true){
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json, function(i, n) {
			data+='{ text: "'+n.catName+'", id:'+n.catId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		var obj = [$('#'+id)];
		$.each(obj,function (x,curObj){
			curObj.select2({data:eval(data)});
		})
	});
}

//打开添加对话框
function openSaveDialog(op,_data) {
	if(op=='edit'){
		$('#form-violation-add').form('clear');
		initViolationCate('combox-violation-cat',false);
		$('#form-violation-add').form('load', _data);
		$('#window-add-violation').panel({title: "编辑"});
		var url = _path + '/storeViolationCat/findViolationCatList?parentCatId=0&exist=false';
		$.getJSON(url, function(json) {
			$("#combox-violation-cat").empty();
			var data="[";
			if(true){
				data+='{ text: "--请选择--", id: "-1"  },';
				data+='{ text: "'+_data.catName+'", id: "'+_data.catId+'" ,selected:true  },';
			}
			$.each(json, function(i, n) {
				data+='{ text: "'+n.catName+'", id:'+n.catId+'},';
			});
			data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
			data+="]";
			var obj = [$('#combox-violation-cat')];
			$.each(obj,function (x,curObj){
				curObj.select2({data:eval(data)});
			})
		});
	}else{
		$("#combox-violation-cat").empty();
		initViolationCate('combox-violation-cat',false);
		$('#form-violation-add').form('clear');
		$("#combox-violation-cat").val(-1).trigger("change");
	}		
	$('#MODALPANEL').show();
	$('#window-add-violation').dialog('open');
	$('.panel-tool-close').on('click', function(){
		$('#MODALPANEL').hide();
	});
}

//保存
function saveOrUpdateViolation(scopeId){
	if ($('#form-violation-add').form('validate')) {
		var catId = $('#combox-violation-cat').val();
		if(catId==null||catId==''||catId=='-1'){
			easyuiMsg('错误', "请选择违规类型");
			return;
		}
//		if(!$('input[name="scoreValue"]').val()){
//			$('input[name="scoreValue"]').val('0');
//		}
		if(!$('input[name="goodsDays"]').val()){
			$('input[name="goodsDays"]').val('0');
		}
		if(!$('input[name="goodsdownDays"]').val()){
			$('input[name="goodsdownDays"]').val('0');
		}
//		if(!$('input[name="newsDays"]').val()){
//			$('input[name="newsDays"]').val('0');
//		}
//		if(!$('input[name="newsDaysValue"]').val()){
//			$('input[name="newsDaysValue"]').val('0');
//		}
		if(!$('input[name="storeDays"]').val()){
			$('input[name="storeDays"]').val('0');
		}
		if(!$('input[name="storedownDays"]').val()){
			$('input[name="storedownDays"]').val('0');
		}
//		if(!$('input[name="salesDays"]').val()){
//			$('input[name="salesDays"]').val('0');
//		}
		
		var param_data = biz.serializeObject($("#form-violation-add"));
		param_data.scoreValue = 0;
		param_data.newsDays= 0 ;
		param_data.salesDays = 0;
		$.post(_path + '/violation/saveOrUpdateViolation', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-violation').datagrid('reload', {});
				initViolationCate('combox-violation-cat',false);
				$('#MODALPANEL').hide();
				$('#window-add-violation').window('close');
			}else {
				if(_data.head.retCode == '000004'){
					easyuiMsg('错误', "该违规类型的违规处理已存在");
				}else if(_data.head.retCode == '000001'){
					easyuiMsg('错误', "该违规类型不存在");
				}
			}
		}, 'json');
	}
}

//关闭弹出框
function closeCarFactoryScope(){
	$('#MODALPANEL').hide();
	$('#window-add-scope').window('close');
}

//删除
function deleteViolation(id){
	var ids =new Array();
	if(id){
		ids.push(id);
	}else{
		var selectRows = $("#table-violation").datagrid('getChecked');
		if(selectRows.length==0){
			$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
			return false;
		}
		$.each(selectRows,function(i,v){
			ids.push(v.violationId);
		});
	}
	if(ids.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var params={};
	params.id=ids.join(",");
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/violation/deleteViolation',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
//		    	    	alert(_data.head.retCode);
		    	    	if (_data.head.retCode == '000000') {
//			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-violation').datagrid('reload', {});
			    		} else if (_data.head.retCode == '000001'){
							easyuiMsg('错误','请选择需要操作的数据项！');
						} else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    }
		}
	);
}

/**
 * 打开高级查询
 */
function openSeachWindow() {
	var width = $(window).width(); // 屏幕的宽度
	$('#advance_search_violation').dialog('open').window('resize',{ width : '380px' , height : '500px' , top : 0 , left : (width - 380) });
}
//清空高级选项条件
function clearCondition(){
	$("#advance_search_violation").form('clear');
	$("#search-combox-violation-cat").val(-1).trigger("change");
	$('select[name="earnestMoneySearchType"]').val("gt");
	$('div.bt').hide();
	$('div.nbt').show();
	$('#table-violation').datagrid('reload', {});
}
//更改高级搜索违约金查询条件
function changeSearchType(value){
//	alert(value);
	if(value=='bt'){
		$('div.nbt').hide();
		$('div.bt').show();
	}else{
		$('div.bt').hide();
		$('div.nbt').show();
	}
}

