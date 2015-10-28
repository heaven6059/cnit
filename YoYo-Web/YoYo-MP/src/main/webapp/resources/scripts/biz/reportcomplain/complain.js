$(function(){
	$("#complain_list_dataGrid").datagrid({		
		url : yoyo.mpUrl+'/complain/loadComplainData',
		columns : [[
		{
			field : 'complainId' ,
			title : '投诉编号',
			align : 'center',
			width:"10%"
		} , {
			field : 'orderId' ,
			title : '投诉订单',
			align : 'center',
			width:"15%"
		} , {
			field : 'storeName' ,
			title : '被投诉方',
			halign : 'center',
			width:"15%"
		} , {
			field : 'reason' ,
			title : '投诉原因',
			align : 'center',
			width:"10%"	,
			formatter: function(value,rows,index){
				if(value == 'after' ){
					 return '售后问题';
				 }else if(value == 'action'){
					 return '行为违规';
				 }else if(value == 'quality'){
					 return '质量问题';
				 }else{
					 return "";
				 }
			}
		}, {
			field : 'status' ,
			title : '投诉状态',
			align : 'center',
			width:"10%",
			formatter: function(value,rows,index){
				if(value == 'intervene' ){
					 return '平台介入';
				 }else if(value == 'success'){
					 return '投诉成立';
				 }else if(value == 'error' ){
					 return '投诉不成立';
				 }else if(value == 'cancel'){
					 return '投诉撤销';
				 }else{
					 return "";
				 }
			}
		},  {
			field : 'createtime' ,
			title : '提交时间',
			align : 'center',
			width:"10%",
			formatter: function(value,rows,index){
				 return new Date(value).format("yyyy-MM-dd hh:mm:ss");
			}
		},{
			field : 'editor',
			title : '操作',
			align : 'center',
			width : '10%',
			formatter: function(value,rows,index){
				return "<a href='javascript:viewComplain("+rows.complainId+");'>查看</a>";
			}
		}
		]],
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		toolbar : '#toolbar',
		pageSize : 25,
		height : ($(window).height() * 0.95),
		pageList : [ 25, 50, 100, 200 ],
		pagination : true ,
		rownumbers : true ,
		nowrap : false,
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
});


function viewComplain(complainId){
	$("#window-view-after-sales").dialog({
	    href:"../complain/complainDetail?complainId="+complainId,
	    title:'查看售后服务纠纷',
	    modal: true,
	    cache:false,
	    draggable:false,
	    iconCls:'icon-edit'
	});
	$('#window-view-after-sales').window('open');
}



function clearComplainSearch(){
	$("#complain_search_form").form('clear');
    $("#reason").val("");
    $("#status").val("");
	complainAdvanceSearch();
}

//高级查询立即筛选
function complainAdvanceSearch(){
	var param={
		complainId:$("#complainId").val(),
		reason:$("#reason").val(),
		status:$("#status").val(),
		storeName:$("#storeName").val()
	};
	$('#complain_list_dataGrid').datagrid('load', param);
}