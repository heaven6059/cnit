/**
 * 发布虚拟商品
 */
$(function() {
	findMemberLevel();
	createTimeCombox($('#start_time_hh'),$('#start_time_hh_hide').val(),24);
	createTimeCombox($('#start_time_mm'),$('#start_time_mm_hide').val(),60);
	createTimeCombox($('#end_time_hh'),$('#end_time_hh_hide').val(),24);
	createTimeCombox($('#end_time_mm'),$('#end_time_mm_hide').val(),60);
	
	/**优惠基本信息下一步*/
	$('#btn_coupon_next').click(function(){
		if (!$("#form_saveVirtualGoods").form('validate')) {
			easyuiMsg('提示', "请填写完整信息！");
			return false;
		}
		
		if ($(".mlev input[name='memberLvIds']:checked").length == 0) {
			easyuiMsg('提示', "请选择会员级别！");
			return false;
		}
		if (parseInt($("#onlineQuantity").val())< parseInt($("#onlineLimit").val())) {
			easyuiMsg('提示', "发放数量不能小于领取数量");
			return false;
		}
		
		$('#coupon_conditions').show();
		$('#coupon_basic').hide();
		$('#coupon_solution').hide();
	});
	
	/**优惠条件下一步*/
	$('#c_coupon_next').click(function(){
		if ($(".can input[name='cTemplate']:checked").length == 0) {
			easyuiMsg('提示', "请选择优惠条件！");
			return false;
		}
		if($('input[name="cTemplate"]:checked').parent().parent().find('.tableform input').length>0){ //是否存在input
			if ($($(".can .tableform").find('input:visible')).val() == '') {
				easyuiMsg('提示', "请填写完整信息！");
				return false;
			}
		}
		$('#coupon_conditions').hide();
		$('#coupon_basic').hide();
		$('#coupon_solution').show();
	});
	
	/**优惠条件上一步*/
	$('#c_coupon_prev').click(function(){
		$('#coupon_conditions').hide();
		$('#coupon_basic').show();
		$('#coupon_solution').hide();
	});
	
	/**优惠方案上一步*/
	$('#s_coupon_prev').click(function(){
		$('#coupon_conditions').show();
		$('#coupon_basic').hide();
		$('#coupon_solution').hide();
	});
	
	/**优惠条件选择*/
	$(".can  input[name='cTemplate']").click( function() { // 绑定点击事件
		$(this).parent().parent().parent().find('.tableform').hide();
		$(this).parent().parent().find('.tableform').show();
	});
	
	
	/**优惠方案选择*/
	$(".can_solution  input[name='sTemplate']").click( function() { // 绑定点击事件
		$('.solution_li').find('li').hide();
		$('.solution_li').find('li').eq($(this).attr('sort')).show();
	});
	
	
	/**保存*/
	$('#s_coupon_next').click(function(){
		if ($(".can_solution input[name='sTemplate']:checked").length == 0) {
			easyuiMsg('提示', "请选择优惠方案！");
			return false;
		}
		if($('.solution_li').find('input:visible').length>0){ //是否存在input
			if ($($('.solution_li').find('input:visible')).val() == '') {
				easyuiMsg('提示', "请填写完整信息！");
				return false;
			}
		}
		var params = biz.serializeObject($("#form_saveVirtualGoods"));
		params.fromTime = new Date(params.fromTime+" "+$('#start_time_hh').val()+":"+$('#start_time_mm').val()+":00");
		params.toTime =  new Date(params.toTime+" "+$('#end_time_hh').val()+":"+$('#end_time_mm').val()+":00");
		params.cpnsPrefix = "B"+params.cpnsPrefix; //B券类型
		var url  = _path + '/coupons/updateCoupons';  //编辑
		if(params.cpnsId=='' || params.cpnsId==null){ //新增
			url  = _path + '/coupons/saveCoupons';
		}
		console.log(params);
		$.ajax({ url : url , data : params , type : "post" , datatype : "json" , success : function(data) {
			if (data.head.retCode == '000000') {
				window.location.href=_path+"/coupons/couponsList";
			} else {
				$.messager.alert('错误', '保存失败', 'error');
			}
		} });
	});
	
});

/**获得会员等级*/
function findMemberLevel() {
	var ids = null;
	if($("#memberIds").val()!='' && $("#memberIds").val()!=null){  //编辑时，进行默认选择
		ids = $("#memberIds").val().split(',');
	}
	$.getJSON(biz.rootPath() + '/memberLevel/findAll', function(json) {console.log(json);
		var html = "";
		$.each(json.content, function(i, n) {
			html += '<input type="checkbox" name="memberLvIds" value="'+n.memberLvId+'" id="level_'+i+'"';
			if(ids!=null && ids.indexOf(n.memberLvId+'')!=-1){
				html += 'checked';
			}
			html += '><label for="level_'+i+'">'+n.name+'</label>';
		});
		$('#mLev').append(html);
	});

}

/** 创建时间下拉框 
 * $this:下拉框对象
 * val:默认选择的值 
 * limit:最大值
 */
function createTimeCombox($this,val,limit) {
	var html = '';
	for ( var i = 0; i < limit; i++) {
		if (i < 10) {
			html += '<option value="0' + i + '">0' + i + '</option>';
		} else {
			html += '<option value="' + i + '">' + i + '</option>';
		}
	}
	$this.append(html);
	if(val != '' && val!=null){
		$this.val(val);
	}else{
		$this.val('00');
	}
}


