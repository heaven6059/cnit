$(function(){
	$("#report_list_dataGrid").datagrid({		
		url : yoyo.mpUrl+'/report/reportListData',
		columns : [[
		{
			field : 'reportId' ,
			title : '举报编号',
			align : 'center',
			width:"10%"
		} , {
			field : 'name' ,
			title : '商品名称',
			halign : 'center',
			width:"30%"
		} , {
			field : 'storeName' ,
			title : '被举报方',
			halign : 'center',
			width:"15%"
		} , {
			field : 'reason' ,
			title : '举报原因',
			align : 'center',
			width:"10%"	,
			formatter: function(value,rows,index){
				if(value == 'false' ){
					return '虚假信息';
				}else if(value == 'unconformity'){
					return '图片不符';
				}
			}
		}, {
			field : 'status' ,
			title : '举报状态',
			align : 'center',
			width:"10%",
			formatter: function(value,rows,index){
				if(value == 'intervene' ){
					 return '平台介入';
				 }else if(value == 'voucher'){
					 return '取证中';
				 }else if(value == 'success'){
					 return '举报成立';
				 }else if(value == 'error' ){
					 return '举报不成立';
				 }else if(value == 'cancel'){
					 return '举报撤销';
				 }else if(value == 'finish'){
					 return '完成';
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
				return "<a href='javascript:viewReport("+rows.reportId+");'>查看</a>";
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


function viewReport(reportId){
	$("#window-view-after-sales").dialog({
	    href:"../report/reportDetail?reportId="+reportId,
	    title:'查看举报详情',
	    modal: true,
	    cache:false,
	    draggable:false,
	    iconCls:'icon-edit'
	});
	$('#window-view-after-sales').window('open');
}



function clearReportSearch(){
	$("#report_search_form").form('clear');
	$("#reason").val("");
    $("#status").val("");
	reportAdvanceSearch();
}

//高级查询立即筛选
function reportAdvanceSearch(){
	var param={
		productName:$("#productName").val(),
		reportId:$("#reportId").val(),
		reason:$("#reason").val(),
		status:$("#status").val(),
		storeName:$("#storeName").val()
	};
	$('#report_list_dataGrid').datagrid('load', param);
}