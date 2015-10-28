/**
 * 功能描述： 订单列表的js
 * 
 */
$(function() {
	
	var goodsListSrc = _path + '/order/orderList?';
	// 切换选项卡
	$('#tabs-order').tabs({ 'onSelect' : function(title, index) {
		if (title == '未处理') {
			$('#table-order-list').datagrid('options').url = goodsListSrc + 'status=active&payStatus=0';
		} else if (title == '已付款未安装') {
			$('#table-order-list').datagrid('options').url = goodsListSrc + 'status=uninstall&payStatus=1&shipStatusQ=0';
		} else if (title == '已完成') {
			$('#table-order-list').datagrid('options').url = goodsListSrc + 'status=finish&payStatus=1&shipStatusQ=1';
		}else if (title == '已退款') {
			$('#table-order-list').datagrid('options').url = goodsListSrc + 'status=dead&payStatus=5';
		}else if (title == '已取消') {
			$('#table-order-list').datagrid('options').url = goodsListSrc + 'status=dead';
		}else {
			$('#table-order-list').datagrid('options').url = goodsListSrc;
		}
		$('#table-order-list').datagrid('reload');
	} });
	
	// 标签
	/*$('#toolbar-roder-tag div').on('click', function() {
		if ($(this).text() == '为选中项打标签') {
			var rows = $('#table-order-list').datagrid('getChecked');
			if (rows.length < 1) {
				easyuiMsg('提示', '请选择要操作的数据项!');
			} else {
				easyuiMsg('提示', '为选中项打标签!');
			}
		} else {
			easyuiMsg('提示', '标签设置!');
		}
	});*/
	
	var loadFilter = function(data) {
		if (data.content) {
			return data.content;
		} else {
			return { total : 0 , rows : [ ] };
		}
	};
	

	var columns = [
	       		[
	       				{ field : 'chkbox' , checkbox : true } ,
	       				/*{ field : 'edit' , title : '操作' , formatter : function(value, row) {
	       					return '<a href="javascript:$(this).orderIndex(\'doView\',' + row.orderId + ')">查看</a>';
	       				} } , 
	       				{ field : 'dbn' , title : '标签' } ,*/
	       				{ field : 'lastModified' ,halign: 'center', sortable: true,title : '最后更新时间'} ,
	       				//{ field : 'thumbnailPic' , title : '订单本次来源ID' } , 
	       				{ field : 'status' , halign: 'center',sortable: true,title : '订单状态', formatter : function(value, row) {
	       					if(value=='active'){
	       						return '活动订单';
	       					}else if(value=='dead'){
	       						return '已作废';
	       					}else if(value=='finish'){
	       						return '已完成';
	       					}else if(value=='unconfirm'){
	       						return '待确认';
	       					}else if(value=='create'){
	       						return '创建订单';
	       					}else if(value=='uninstall'){
	       						return '未安装';
	       					}else if(value=='install'){
	       						return '安装中';
	       					}else if(value=='refunds'){
	       						return '退款中';
	       					}
	       				}} ,
	       				//{ field : 'storeName' , title : '订单本次来源URL' } , 
	       				{ field : 'markText' ,halign: 'center', title : '订单备注' } ,
	       				{ field : 'orderId' , halign: 'center',title : '订单号' } , 
	       				//{ field : 'tags' , title : '会员用户名' } ,
	       				//{ field : 'lastModify' , title : '店铺名称' } , 
	       				//{ field : 'upTime' , title : '会员手机' } ,
	       				{ field : 'createtime' ,halign: 'center',sortable: true, title : '下单时间' } , 
	       				//{ field : 'payment' , title : '支付类型' } ,
	       				{ field : 'payStatus' , halign: 'center',sortable: true,title : '付款状态',formatter : function(value, row) {
	       					if(value=='0'){
	       						return '未支付';
	       					}else if(value=='1'){
	       						return '已支付';
	       					}else if(value=='2'){
	       						return '已付款至担保方';
	       					}else if(value=='3'){
	       						return '部分付款';
	       					}else if(value=='4'){
	       						return '部分退款';
	       					}else if(value=='5'){
	       						return '全额退款';
	       					}else if(value=='6'){
	       						return '到店支付';
	       					}
	       				} } , 
	       				{ field : 'payment' ,halign: 'center',sortable: true, title : '支付方式' } ,
	       				{ field : 'orderType' ,halign: 'center',sortable: true, title : '订单类型',formatter : function(value, row) {
	       					if(value=='entity'){
	       						return '实体物品订单';
	       					}else if(value=='virtual'){
	       						return '虚拟物品订单';
	       					}
	       				} } ,
	       				{ field : 'source' , halign: 'center',sortable: true,title : '平台来源',formatter : function(value, row) {
	       					if(value=='pc'){
	       						return '标准平台';
	       					}else if(value=='wap'){
	       						return '手机触屏';
	       					}else if(value=='weixin'){
	       						return '微信等商城';
	       					}
	       				}  } /*,
	       				{ field : 'refundStatus' ,halign: 'center', sortable: true,title : '退款状态',formatter : function(value, row) {
	       					if(value=='0'){
	       						return '未申请退款';
	       					}else if(value=='1'){
	       						return '退款申请中,等待卖家审核';
	       					}else if(value=='2'){
	       						return '卖家拒绝退款';
	       					}else if(value=='3'){
	       						return '卖家同意退款,等待买家退货';
	       					}else if(value=='4'){
	       						return '卖家已退款';
	       					}else if(value=='5'){
	       						return '买家已退货,等待卖家确认收货';
	       					}else if(value=='6'){
	       						return '卖家不同意协议,等待买家修改';
	       					}else if(value=='7'){
	       						return '买家已退货,卖家不同意协议,等待买家修改';
	       					}else if(value=='8'){
	       						return '平台介入,等待卖家举证';
	       					}else if(value=='9'){
	       						return '平台介入,等待平台处理';
	       					}else if(value=='10'){
	       						return '平台介入已处理';
	       					}else if(value=='11'){
	       						return '卖家同意退款，等待卖家打款至平台';
	       					}else if(value=='12'){
	       						return '卖家已退款，等待系统结算';
	       					}
	       				}  }*/ , 
	       				{ field : 'finalAmount' ,halign: 'center',sortable: true, title : '订单总额' } 
	       				//{ field : 'downTime' , title : '订单本次来源时间' } , 
	       				//{ field : 'store' , title : '订单首次来源时间' } ,
	       				//{ field : 'brief' , title : '订单首次来源ID' } , 
	       				//{ field : 'cost' , title : '订单首次来源URL' }
	       		]
	       	];
	
	$('#table-order-list').datagrid(
			{
				toolbar : '#toolbar-order-list' ,
				pagination : true ,
				rownumbers : true ,
				fitColumns : true ,
				singleSelect : true ,
				selectOnCheck : false ,
				checkOnSelect : false ,
				remoteSort : true ,
				pageSize : 25,
				pageList : [ 25, 50, 100, 150 ],
				multiSort : true ,
				url : _path + '/order/orderList' ,
				columns : columns ,
				loadFilter : loadFilter ,
				view : detailview ,
				detailFormatter : function(index, row) {
					return '<div class="dropdown-order-detail" style="padding:10px 0px;margin:10px auto;"></div>';
				} ,
				onExpandRow : function(index, row) {
					var ddv = $(this).datagrid('getRowDetail', index).find('div.dropdown-order-detail');
					ddv.panel({border : false ,height:400, cache : false , href:_path + '/order/orderDetailTab?orderId=' + row.orderId,
						onLoad : function() {
							$('#table-order-list').datagrid('fixDetailRowHeight', index);
						} });
					$('#table-order-list').datagrid('fixDetailRowHeight', index);
				} });
	
	
	$.fn.orderIndex = function(method) {
		if (methods[ method ]) {
			methods[ method ](arguments[ 1 ]);
		}
	}
	
	var methods = {
			doView : function(_id) {
				window.open(_path + '/order/orderDetail?orderId=' + _id, '3km');
			} ,
			doSaleUp : function(_id) {

			} ,
			doSaleDown : function(_id) {

			} ,
			doEditProduct : function(_id) {

			} ,
			doBatchDelete : function() {
				if (vailidateOperate) {
					var params = {};
					params.op = 'delete';
					params.rows = JSON.stringify($('#table-goods').datagrid('getChecked'));
					commonAjax(_path + '/goods/batchEdit', params, function(data) {
						$('#table-goods').datagrid('reload');
					}, function(data) {
						easyuiMsg('失败', data.head.retMsg);
					});
				}
			} ,
			doBatchTagging : function() {
				if (vailidateOperate) {
					// 获取标签
					commonAjax(_path + '/label/brandApplyLabels', { "campanyId" : 1 }, function(data) {
						var content = $('<div />').append($('<div />').text('对选择的' + 0 + '个条目应用标签'));
						var labels = data.content.rows;
						if (labels.length > 0) {
							var ulDom = $('<ul />');
							for (var i = 0, label; label = labels[ i++ ];) {
								var liDom = $('<li />').css('display', 'inline');
								liDom.append($('<input />', { name : 'labelChk' , type : checkbox }).val(label.id));
								liDom.append($('<span />').css(
										{ 'color' : label.fontColor , 'background-color' : label.bgColor })
										.text(label.name));
								ulDom.append(liDom);
							}
							content.append($('<div />').append(ulDom));
						}
						content.append($('<div />').css('text-align', 'center').append(
								$('<a />', { onclick : 'javascritp:$(this).goodsIndex("doBatchTagging")' }).text('保存')));
					}, null);
					var advsWindow = $('<div />', { id : _aimDataGrid + '_asw' });
					$('#' + _parent).parent().append(advsWindow);
					$('#' + _aimDataGrid + '_asw').window(
							{ title : '设置标签' , width : 400 , height : 500 , closed : true , inline : true , cache : false ,
								resizable : false , content : _rootEle });
				}
			} };
	
	
});

//高级查询立即筛选
function advanceSearch(){
	var param = biz.serializeObject($('#search_form'));
	//param.carid = $("#search_car_type").val();
	param.payment = $("#payment").val();
	param.status = $("#status").val();
	param.payStatus = $("#payStatus").val();
	param.orderType = $("#orderType").val();
	param.source = $("#source").val();
	param.refundStatus = $("#refundStatus").val();
	$('#table-order-list').datagrid('load', param);
}

//清除高级查询
function advance_clear(){
	search_clear('search_form','table-order-list');
	$('#table-order-list').datagrid('reload', {});
	$("#status").val('-1').trigger("change");
	$("#payment").val('-1').trigger("change");
	$("#payStatus").val('-1').trigger("change");
	$("#orderType").val('-1').trigger("change");
	$("#source").val('-1').trigger("change");
	$("#refundStatus").val('-1').trigger("change");
}

