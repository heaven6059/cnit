/**
 * 功能描述：咨询评论>咨询列表JS
 * 作者：王鹏
 * 创建时间：2015-04-22
 *
 */
$(function(){
	$('#aftersales_list_dataGrid').datagrid({		
		url : _path+'/aftersales/loadaftersales',
		columns : [[
		{
			field : 'orderId' ,
			title : '订单编号',
			align : 'center',
			width:"160px"
		} , {
			field : 'memberName' ,
			title : '申请人',
			align : 'center',
			width:"160px"
		} , {
			field : 'refundText' ,
			title : '售后服务状态',
			align : 'center',
			width:"160px"
		} , {
			field : 'safeguardRequire' ,
			title : '售后要求',
			align : 'center',
			width:"160px"	,
			formatter: function(value,rows,index){
				if(value==3)return "要求换货";
				if(value==4)return "要求维修";		
				if(value==5)return "要求退款";
				return "";
			}
		}, {
			field : 'safeguardType' ,
			title : '申请原因',
			align : 'center',
			width:"160px",
			formatter: function(value,rows,index){
				if(value==1)return "商品问题";
				if(value==2)return "七天无理由退换货";
				return "";
			}
		}, {
			field : 'isSafeguard' ,
			title : '申请类型',
			align : 'center',
			width:"160px",
			formatter: function(value,rows,index){
				if(value==1)return "售前";
				if(value==2)return "售后";
				return "";
			}
		}, {
			field : 'editor',
			title : '操作',
			align : 'center',
			width : '160px',
			formatter: function(value,rows,index){
				return "<a href='javascript:viewAfterSales("+rows.returnId+","+rows.isSafeguard+");'>查看</a>";
			}
		}
		]],
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		toolbar : '#toolbar',
		pageSize : 25,
		height:$(window).height()*0.95,
		pageList : [ 25, 50, 100, 200 ],
		pagination : true ,
		rownumbers : true ,
		//fitColumns : true ,
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

function clearAfterSalesAdvanceSearch(){
	$("#adftersales_search_form").form('clear');
	afterSalesAdvanceSearch();
}

//高级查询立即筛选
function afterSalesAdvanceSearch(){
	var param={
		orderId:$("#order_id").val(),
		memberName:$("#member_name").val(),
		afterSalesRequire:$("#afterSalesRequire").val()>0?$("#afterSalesRequire").val():null,
		afterSalesType:$("#afterSalesType").val()>0?$("#afterSalesType").val():null
	};
	$('#aftersales_list_dataGrid').datagrid('load', param);
}

function viewAfterSales(returnId,isSafeguard){
	var record = $('#aftersales_list_dataGrid').datagrid('getSelected');
	if(record){
		$("#window-view-after-sales").dialog({
		    href:"../aftersales/viewAfterSales?returnId="+returnId+"&isSafeguard="+isSafeguard,
		    title:'查看售后服务纠纷',
		    modal: true,
		    cache:false,
		    draggable:false,
		    iconCls:'icon-edit'
		});
		$('#window-view-after-sales').window('open');
	}else{
		$.messager.alert('警告','请选择编辑的数据!','warning');
	}
}



