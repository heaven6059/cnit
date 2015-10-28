/**
 * 功能描述：咨询评论>咨询列表JS 作者：王鹏 创建时间：2015-04-22
 * 
 */
$(function() {
	$('#refunds_list_dataGrid').datagrid(
			{
				toolbar : '#toolbar-goods',
				url : _path + '/aftersales/loadRefundsList',
				columns : [ [
						{
							field : 'ck',
							checkbox : true
						},
						{
							field : 'orderId',
							title : '订单编号',
							align : 'center',
							width : "10%"
						},
						{
							field : 'memberName',
							title : '申请人',
							align : 'center',
							width : "10%"
						},
						{
							field : 'tradeNo',
							title : '支付交易流水号',
							align : 'center',
							width : "13%"
						},
						{
							field : 'amount',
							title : '退款金额',
							align : 'center',
							width : "10%"
						},
						{
							field : 'refundText',
							title : '售后服务状态',
							align : 'center',
							width : "10%"
						},
						{
							field : 'safeguardType',
							title : '申请原因',
							align : 'center',
							width : "10%",
							formatter : function(value, rows, index) {
								if (value == 1)
									return "商品问题";
								if (value == 2)
									return "七天无理由退换货";
								return "";
							}
						},
						{
							field : 'addTime',
							title : '申请时间',
							align : 'center',
							width : "10%",
							formatter : function(value, rows, index) {
								if (value) {
									return new Date(value)
											.format("yyyy-MM-dd hh:mm:ss");
								}
								return "";
							}
						},
						{
							field : 'editor',
							title : '操作',
							align : 'center',
							width : '10%',
							formatter : function(value, rows, index) {
								var html = [];
								html.push("<a href='javascript:viewAfterSales("
										+ rows.returnId + ","
										+ rows.isSafeguard + ");'>查看</a>");
								return html.join("");
							}
						} ] ],
				singleSelect : false,
				selectOnCheck : true,
				checkOnSelect : true,
				toolbar : '#toolbar',
				pageSize : 25,
				height : $(window).height() - 150,
				pageList : [ 25, 50, 100, 200 ],
				pagination : true,
				rownumbers : true,
				fitColumns : true,
				remoteSort : false,
				multiSort : true,
				ctrlSelect : true,
				loadFilter : function(data) {
					if (data.rows) {
						return data;
					} else {
						return data.content;
					}
				}
			});
});

function viewAfterSales(returnId, isSafeguard) {
	var record = $('#refunds_list_dataGrid').datagrid('getSelected');
	if (record) {
		$("#window-view-after-sales").dialog(
				{
					href : "../aftersales/viewRefunds?returnId=" + returnId
							+ "&isSafeguard=" + isSafeguard,
					title : '查看售后服务纠纷',
					modal : true,
					cache : false,
					draggable : false,
					iconCls : 'icon-edit'
				});
		$('#window-view-after-sales').window('open');
	} else {
		$.messager.alert('警告', '请选择编辑的数据!', 'warning');
	}
}

function refundsAccount() {
	var rows = $('#refunds_list_dataGrid').datagrid('getChecked');
	if (rows.length < 1) {
		easyuiMsg('提示', '请选择要操作的数据项!');
	} else {
		var msg = new Array();
		var refuds = new Array();
		var count = 0;
		for (var i = 0; i < rows.length; i++) {
			var status = rows[i].status;
			if (status != '10') {
				easyuiMsg('提示', '退款状态必须是【审核通过】才能退款!');
				return;
			}
			var tradeNo = rows[i].tradeNo;
			if (!tradeNo) {
				easyuiMsg('提示', '支付交易流水号为空，无法退款！');
				return;
			}
			msg.push(tradeNo + "^0.01^协商好退款");
			refuds.push(tradeNo + "^" + rows[i].returnId);
			count += 1;
		}
//		$.messager.confirm("确认", "确认后将直接打款到买家支付账号，是否确认?", function(r) {
//			if (r) {
				var url = yoyo.portalUrl + "/updatePayLog/" + refuds.join("%23")+ "";
				yoyo.ajaxRequest(url, null, true, "json", function(data) {});
				window.location.href = yoyo.portalUrl+ "/resources/alipayapi_refund.jsp?batch_num=" + count+ "&WIDdetail_data=" + msg.join("%23") + "";
//			} else {
//				return;
//			}
//		});

	}
}
