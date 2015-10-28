/**
 * 
 */
/*$(function() {
	// 获取商品详细信息
	initRealCateTree('select-good-edit-cat', false);
	initBrandComboxGrid('select-good-edit-brand', false);
	commonAjax(_path + '/goods/goodsDetail', { goodsId : $('#GOOD_EDIT_GOOD_ID').val() }, function(data) {
		$('#form-good-edit-base-info').form('load', data.content);
		$('#form-good-edit-ext-info').extribute(
				{ catId : data.content.catId , goodsId : data.content.goodsId , cssClass : 'form-table' });
	}, function(data) {

	});
	$('#button-good-edit-acc-add').on(
			'click',
			function() {
				$('#toolbar-good-edit-acc-info-group')
						.addaccessory(
								'kreatePanel',
								{ renderAim : '#good-edit-acc-info-from-group' ,
									toolbar : '#toolbar-good-edit-acc-info-group' });
			});
	$('#select-good-edit-ass').comdropdown(
			{ url : _path + '/goods/goodsList' , cache : true , idField : 'goodsId' , textField : 'name' ,
				multiple : 'multiple' });
	// 选项卡切换
	var tab = { '基本信息' : 'order-detail-base-info' , '商品' : 'order-detail-good-info' , '收退款记录' : 'order-detail-refund-info' ,
		'优惠方案' : 'order-detail-coupon-info' , '订单备注' : 'order-detail-remark-info' , '订单日记' : 'order-detail-log-info' , 
		'顾客留言' : 'order-detail-message-info'};
	$('#tabs-order-detail').tabs(
			{ onSelect : function(title, index) {
				$('#tabs-order-detail').tabs(
						'update',
						{ tab : $('#tabs-order-detail').tabs('getTab', title) ,
							options : { content : document.getElementById(tab[ title ]) } });
			} });
	// 在线html编辑器
	$('#cle-good-detail-info').cleditor();
	// 关闭窗口
	$('#button-good-edit-close').on('click', function() {
		$('#window-goods-edit').window('close');
	});
	// 保存信息
	$('#btn_do_submit').on('click', function() {
		var param = {};
	});
	
	
	
});*/

//新增订单备注
function saveOrderRemark() {
	//alert("kkkk");
	if ($('#form-order-add').form('validate')) {
		var param_data = biz.serializeObject($("#form-order-add"));
		param_data.orderId = $("#order_id").val();
		$.post(_path + '/order/insertOrderRemark', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#table-order-list').datagrid('reload', {});
				//$('#window-add-car-type').window('close');
				easyuiMsg('提示', "保存成功！");
			} else if (_data.head.retCode == '000004'){
				easyuiMsg('错误',"该车型周期名称已经存在！");
			}else {
				easyuiMsg('错误', "保存失败！");
			}
		}, 'json');
	}
}