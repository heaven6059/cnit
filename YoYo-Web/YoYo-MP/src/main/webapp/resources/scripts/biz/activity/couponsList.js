/**
 * 功能描述： 优惠券的js
 * 
 */
$(function() {

	$('#coupons_datagrid')
			.datagrid(
					{
						url : _path + '/coupons/couponsList',
						columns : [ [
								{
									field : 'ck',
									checkbox : "true"
								},
								{
									field : 'cpnsName',
									align : 'center',
									title : '优惠券名称'
								},
								{
									field : 'cpnsStatus',
									align : 'center',
									title : '是否启用',
									formatter : function(value, row) {
										if (value == '0') {
											return '禁用';
										} else if (value == '1') {
											return '启用';
										}
									}
								},
								{
									field : 'onlineGenQuantity',
									align : 'center',
									title : '线上领取的数量'

								},
								{
									field : 'onlineQuantity',
									align : 'center',
									sortable : true,
									title : '线上发放的数量'
								},
								{
									field : 'onlineLimit',
									align : 'center',
									title : '线上用户领取数量限制'
								},
								{
									field : 'cpnsGenQuantity',
									align : 'center',
									title : '获取的总数量'
								},
								{
									field : 'cpnsPrefix',
									align : 'center',
									title : '优惠券号码'
								},
								{
									field : 'cpnsType',
									title : '优惠券类型',
									align : 'center',
									formatter : function(value, row) {
										if (value == '0') {
											return '一张无限使用';
										} else if (value == '1') {
											return '多张使用一次';
										}
									}
								},
								{
									field : 'issueType',
									title : '发行类型',
									align : 'center',
									formatter : function(value, row) {
										if (value == '0') {
											return '平台发行';
										} else if (value == '1') {
											return '店铺发行';
										}
									}
								},
								{
									field : 'storeName',
									title : '店铺名称',
									align : 'center'									
								},
								{
									field : 'action',
									title : '操作',
									align : 'center',
									formatter : function(value, row) {
										var html = '<a style="margin-right:15px;" href="javascript:void(0)" onclick="editCoupons('+row.cpnsId+')">编辑</a>';
										html += '<a  href="javascript:void(0)" onclick="deleteCoupons('+row.cpnsId+ ','+row.ruleId+')" >删除</a>';
										return html;
									}
								} ] ],
						toolbar : '#toolbar_coupons',
						pagination : true,
						pagePosition : 'bottom',
						rownumbers : true,
						fitColumns : true,
						pageSize : 25,
						width : ($(window).width() * 0.99),
						height : ($(window).height() * 0.95),
						pageList : [ 25,50, 100, 200 ],
						singleSelect : true,
						checkOnSelect : false,
						remoteSort : false,
						multiSort : true,
						width : ($(window).width() * 0.99),
						height : ($(window).height() * 0.95),
						loadFilter : function(data) {
							if (data.rows) {
								return data;
							} else {
								return data.content;
							}
						}
					});

	findMemberLevel();
	createTimeCombox($('#start_time_hh'), $('#start_time_hh_hide').val(), 24);
	createTimeCombox($('#start_time_mm'), $('#start_time_mm_hide').val(), 60);
	createTimeCombox($('#end_time_hh'), $('#end_time_hh_hide').val(), 24);
	createTimeCombox($('#end_time_mm'), $('#end_time_mm_hide').val(), 60);

	/** 优惠基本信息下一步 */
	$('#btn_coupon_next').click(
			function() {
				if (!$("#form_saveVirtualGoods").form('validate')) {
					easyuiMsg('提示', "请填写完整信息！");
					return false;
				}

				if ($(".mlev input[name='memberLvIds']:checked").length == 0) {
					easyuiMsg('提示', "请选择会员级别！");
					return false;
				}
				if (parseInt($("#onlineQuantity").val()) < parseInt($(
						"#onlineLimit").val())) {
					easyuiMsg('提示', "发放数量不能小于领取数量");
					return false;
				}

				$('#coupon_conditions').show();
				$('#coupon_basic').hide();
				$('#coupon_solution').hide();
			});

	/** 优惠条件下一步 */
	$('#c_coupon_next')
			.click(
					function() {
						if ($(".can input[name='cTemplate']:checked").length == 0) {
							easyuiMsg('提示', "请选择优惠条件！");
							return false;
						}
						if ($('input[name="cTemplate"]:checked').parent()
								.parent().find('.tableform input').length > 0) { // 是否存在input
							if ($($(".can .tableform").find('input:visible'))
									.val() == '') {
								easyuiMsg('提示', "请填写完整信息！");
								return false;
							}
						}
						$('#coupon_conditions').hide();
						$('#coupon_basic').hide();
						$('#coupon_solution').show();
					});

	/** 优惠条件上一步 */
	$('#c_coupon_prev').click(function() {
		$('#coupon_conditions').hide();
		$('#coupon_basic').show();
		$('#coupon_solution').hide();
	});

	/** 优惠方案上一步 */
	$('#s_coupon_prev').click(function() {
		$('#coupon_conditions').show();
		$('#coupon_basic').hide();
		$('#coupon_solution').hide();
	});

	/** 优惠条件选择 */
	$(".can  input[name='cTemplate']").click(function() { // 绑定点击事件
		$(this).parent().parent().parent().find('.tableform').hide();
		$(this).parent().parent().find('.tableform').show();
	});

	/** 优惠方案选择 */
	$(".can_solution  input[name='sTemplate']").click(function() { // 绑定点击事件
		$('.solution_li').find('li').hide();
		$('.solution_li').find('li').eq($(this).attr('sort')).show();
	});

	/** 保存 */
	$('#s_coupon_next')
			.click(
					function() {
						if ($(".can_solution input[name='sTemplate']:checked").length == 0) {
							easyuiMsg('提示', "请选择优惠方案！");
							return false;
						}
						if ($('.solution_li').find('input:visible').length > 0) { // 是否存在input
							if ($($('.solution_li').find('input:visible'))
									.val() == '') {
								easyuiMsg('提示', "请填写完整信息！");
								return false;
							}
						}
						var params = biz
								.serializeObject($("#form_saveVirtualGoods"));
						params.fromTime = new Date(params.fromTime + " "
								+ $('#start_time_hh').val() + ":"
								+ $('#start_time_mm').val() + ":00");
						params.toTime = new Date(params.toTime + " "
								+ $('#end_time_hh').val() + ":"
								+ $('#end_time_mm').val() + ":00");
						params.cpnsPrefix = "B" + params.cpnsPrefix; // B券类型
						var url = _path + '/coupons/updateCoupons'; // 编辑
						var flag = false;
						if (params.cpnsId == '' || params.cpnsId == null) { // 新增
							url = _path + '/coupons/saveCoupons';
							flag = true;
						}
						console.log(params);
						$.ajax({
							url : url,
							data : params,
							type : "post",
							datatype : "json",
							success : function(data) {
								if (data.head.retCode == '000000') {
									$('#coupons_datagrid').datagrid('reload', {});
									if(!flag) { // 编辑
										window.opener=null;
					    				window.open('','_self');
					    				window.close();
									}else{
										window.location.href = yoyo.mpUrl
										+ '/coupons/index';
									}
								} else {
									$.messager.alert('错误', '保存失败', 'error');
								}
							}
						});
					});

	// 添加或编辑优惠券返回按钮
	$('#back').click(function() {
		window.location.href = yoyo.mpUrl + '/coupons/index';
	});

});

function editCoupons(cpsId){
	window.open(yoyo.mpUrl+ '/coupons/editCoupons?couponId=' + cpsId);
}

// 打开添加对话框
function openSaveDialog() {
	window.location.href = yoyo.mpUrl + '/coupons/add';
}

/** 获得会员等级 */
function findMemberLevel() {
	var ids = null;
	if ($("#memberIds").val() != '' && $("#memberIds").val() != null) { // 编辑时，进行默认选择
		ids = $("#memberIds").val().split(',');
	}
	$.getJSON(biz.rootPath() + '/memberLevel/findAll', function(json) {
		var html = "";
		$.each(json.content, function(i, n) {
			html += '<input type="checkbox" name="memberLvIds" value="'
					+ n.memberLvId + '" id="level_' + i + '"';
			if (ids != null && ids.indexOf(n.memberLvId + '') != -1) {
				html += 'checked';
			}
			html += '><label for="level_' + i + '">' + n.name + '</label>';
		});
		$('#mLev').append(html);
	});

}

/**
 * 创建时间下拉框 $this:下拉框对象 val:默认选择的值 limit:最大值
 */
function createTimeCombox($this, val, limit) {
	var html = '';
	for ( var i = 0; i < limit; i++) {
		if (i < 10) {
			html += '<option value="0' + i + '">0' + i + '</option>';
		} else {
			html += '<option value="' + i + '">' + i + '</option>';
		}
	}
	$this.append(html);
	if (val != '' && val != null) {
		$this.val(val);
	} else {
		$this.val('00');
	}
}

/**
 * 删除优惠券
 */
function deleteCoupons(cpnsId,ruleId) {
	$.messager.confirm('确认', '您确认想要删除优惠券吗？', function(r) {
		if (r) {
			$.ajax({
				url : yoyo.mpUrl + "/coupons/deleteCoupons?couponId=" + cpnsId + "&ruleId=" + ruleId,
				type : "post",
				dataType : "json",
				success : function(_data) {
					if (_data.head.retCode == '000000') {
						easyuiMsg('提示', "删除成功！");
						$('#coupons_datagrid').datagrid('reload', {});
					} else {
						easyuiMsg('错误', "删除失败！");
					}
				}
			});

		}
	});
	
}
