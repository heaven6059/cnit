;
var companyId ;
var mobile = /^1[3|4|5|7|8][0-9]{9}$/ ;

$(function(){
	debugger
	$('input.inp-text').focus(function(){
		this.value = '';
	});
	var payment_id =  $("input[name='payment'][checked]").val();

	companyId = getParameter('companyId');
	initCalendar();
	$("#date-311").bind("click",function(){
		$("#date-delivery").show();
	});
	
	initProvince();
    $("#province").change(function () {
    	initCity(this.value);
    });
    $("#city").change(function () {
    	initRegion(this.value);
    });
    
    $('.ui-switchable-panel-main').on('click','li',function(){
    	$(this).find('div:first-child').addClass('item-selected');
		$(this).addClass('store-selected');
		$(this).siblings().find('div:first-child').removeClass('item-selected');
		$(this).siblings().removeClass('store-selected');
    });
	
	//受损单ID
	var damageId = getParameter('damageId');
	var returnUrl = yoyo.memUrl+'/mypainting/offers?id='+damageId;

	//返回选择商家
	$('#retureUrl').on('click',function(){
		window.location.href = returnUrl;
	});
	
	$("input:radio").on("click", function() {
		payment_id = $(this).val();
	});

	$('#name').blur(function(){
		var name = $('#name').val();
		if(!(name && name.trim())){
			$('.error_name').show();
		}else{
			$('.error_name').hide();
		}
		return false;
	});
	$('#phone').blur(function(){
		var phone = $('#phone').val();
		if(!mobile.test(phone)){
			$('.error_phone').show();
		}else{
			$('.error_phone').hide();
		}
		return false;
	});
	//
	$('#order-submit').click(function(){
		var $selected = $('li.store-selected',$('#consignee-list-1'));
		var name = $('#name').val();
		var phone = $('#phone').val();
		var markText = $('#markText').val();
		//分店ID,名称，制定消费时间，受损单ID,报价表ID,支付方式，支付总额
		var storeId = $selected.find('input:hidden').eq(0).val();
		var storeName = $selected.find('span[title="storeName"]').text();
		var consumptionTime = $('#date-311').val();
		var car_damage_offer_id = $('input[name="offerId"]').val();
		var payed =  $("#payPriceId").text();
		var companyId = $selected.find('input.companyId').val();
		if(!(name && name.trim())){
			alert("请输入姓名方便卖家联系！");
			return false;
		}else if(!mobile.test(phone)){
			alert("请输入正确的手机号方便卖家联系！");
			return false;
		}else if(!storeId){
			alert("请选择一个分店");
			return false;
		}else if(!payment_id){
			alert("请选择支付方式");
			return false;
		}else if(!consumptionTime){
			alert("请选择预定消费时间");
			return false;
		}
		$(this).attr('disabled',true);

		var index = consumptionTime.indexOf('(');
		consumptionTime = consumptionTime.slice(0,index).trim();
		
		$.ajax({
			type : 'POST' ,
			url : yoyo.memUrl+'/mypainting/submitOrder' ,
			data : {
				'storeId':storeId,
				'storeName':storeName,
				'consumptionTime':consumptionTime,
				'carDamageOfferId':car_damage_offer_id,
				'paymentId':payment_id,
				'carDamageId':damageId,
				'payed':payed,
				'name':name,
				'phone':phone,
				'markText':markText,
				'companyId':companyId
			}
		}).done(function(data){
			$('#order-submit').removeProp( 'disabled');
			if(data.retCode == '000000'){
				if(payment_id == 0){
					window.location.href = yoyo.memUrl+ '/pay/payPaintingOrder?orderId='+data.content;
				}else if(payment_id == 1){
					window.location.href = yoyo.memUrl+ '/mypainting/orderList';
				}
			}else{
				alert(data.retMsg);
			}
		}).fail(function(data){
			alert("订单提交失败，请联系客服人员！");
			$('#order-submit').removeProp( 'disabled');
		});
	});
	
	$('#selectStorsLink').click(function(){
		$('#selectStores').show();
	});
});

function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) {
		return unescape(r[2]);
	} 
	return null;
}

//初始化日历
function initCalendar() {
	var dData;
	var url = yoyo.memUrl + '/cart/getDays';
	var params = {};
	commonAjax(
			url,
			params,
			function(data) {
				dData = data.content;
				var str = '<div id="date-delivery" style="position: absolute; top: 28px; left: 86px; width: 671px; height: 81px; display: block;" load="1">'
						+ '<div class="inner" style="position: relative; width: 670px; height: 81px;">'
						+ '<dl class="th">'
						+ '<dt>时间段</dt>'
						+ '<dd class="date">';
				for ( var i = 0; i < dData.length; i++) {
					str += '<span row="-1" col="' + i
							+ '" style="background: rgb(255, 255, 255);">'
							+ dData[i].date + '<br>' + dData[i].week
							+ '</span>';
				}
				str += '</dd>'
						+ '<dd class="time">'
						+ '<span row="0" col="-1" style="background: rgb(255, 255, 255);">请选择</span>'
						+ '</dd>'
						+ '</dl>'
						+ '<div class="data" style="width: 570px;">'
						+ '<ul>'
						+ '<li class="checkbox disabled" data-status="0" row="0" col="0" val="" style="color: rgb(122, 189, 84); background: rgb(255, 255, 255);"></li>'

						+ '</ul>';
				for ( var i = 1; i < dData.length; i++) {
					str += '<ul>'
							+ '<li class="checkbox" data-status="1" row="0" col="'
							+ i
							+ '" val="  '
							+ dData[i].date
							+ '  ('
							+ dData[i].week
							+ ')  " day="'
							+ dData[i].date
							+ '" range="" date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}" style="color: rgb(122, 189, 84); background: rgb(255, 255, 255);" onmouseover="changebg(this,1)" onmouseout="changebg(this,0)" onclick="checkTime(this)">可选</li>'
							+ '</ul>';
				}
				str += '</div>' + '</div>' + '</div>';

				$(".express-form").append(str);
				$("#date-delivery").hide();
			}, function(data) {
				easyuiMsg('失败', '请选择要操作的数据项!');
			});

}
// 日历中“可选”的获得焦点事件后改变背景和字体颜色
function changebg(ele, i) {
	if (i == "1") {
		$(ele).css("background", "rgb(122, 189, 84)");
		$(ele).css("color", "rgb(255, 255, 255)");
	} else {
		$(ele).css("background", "rgb(237, 249, 230)");
		$(ele).css("color", "rgb(122, 189, 84)");
	}
}

//选中日期后隐藏日历
function checkTime(ele) {
	var val = $(ele).attr("val");
	$('#date-311').val(val);
	$('#date-delivery').hide();
}


/**
 * 查询省份
 */
function initProvince(){
	$.ajax({
		url:yoyo.portalUrl+'/requirement/queryprovince',
		success:function(data){
			if(data != null){
				$.each(data,function(index,province){
					$('#province').append('<option value="'+province.areaId+'">'+province.areaName+'</option>');
				});
			}
		}
	});
}

/**
 * 查询城市
 * @param cityId
 */
function initCity(provinceId){
	if(provinceId && provinceId != '0'){
		$.ajax({
			url:yoyo.portalUrl+'/goodsManager/getCityInfo',
			data:{'provinceId':provinceId},
			async:false,
			success:function(data){
				if(data!=null&&data.content!=null){
					if(data.content.result==false){
						if(data.content.isProvinceExist==false){
							alert("该省份不存在！");
						}
					}else{
						$('#city').empty();
						$('#region').empty();
						$('#city').append('<option value="0">选择城市</option>');
						for(var i=0;i<data.content.length;i++){
							$('#city').append('<option value="'+data.content[i].areaId+'">'+data.content[i].areaName+'</option>');
						}
					}
				}
			}
		});
	}else if(provinceId == 0){
		$('#city').html('<option value="0">选择城市</option>');
		$('#region').html('<option value="0">选择地区</option>');
	}
}

/**
 * 查询地区
 * @param cityId
 */
function initRegion(cityId){
	if(cityId && cityId!='0'){
		$.ajax({
			url:yoyo.portalUrl+'/goodsManager/getCountyInfo',
			data:{'cityId':cityId},
			async:false,
			success:function(data){
				if(data!=null && data.content!=null){
					if(data.content.result==false){
						if(data.content.isCityExist==false){
							alert("该城市不存在！");
						}
					}else{
						$('#region').empty();
						$('#region').append('<option value="0">选择地区</option>');
						for(var i=0;i<data.content.length;i++){
							$('#region').append('<option value="'+data.content[i].areaId+'">'+data.content[i].areaName+'</option>');
						}
					}
				}
			}
		});
	}else if(cityId == 0){
		$('#region').html('<option value="0">选择地区</option>');
	}
}

/**
 * 手动选择区域分店
 */
function selectStores(){
	var province = $('#province').val() ==0 ? '': $('#province').val();
	var city = $('#city').val() ==0 ? '': $('#city').val();
	var	region = $('#region').val() ==0 ? '': $('#region').val();
	var area = '';
	if(province){
		area = $('#province').find('option:selected').text();
	}
	if(city){
		area += '-'+ $('#city').find('option:selected').text();
	}
	if(region){
		area += '-'+ $('#region').find('option:selected').text();
	}
	console.log(area);
	$.ajax({
		url: yoyo.memUrl+'/mypainting/queryStoreByArea' ,
		data:{'area':area,'companyId':companyId},
		success:function(data){
			if(data.retCode == '000000' ){
				$('#selectStores').hide();
				var stores = data.content.storeList;
				var area = data.content.area;
				$('#consignee-list-1').empty();
				if(stores.length < 1){
					var html = '<li class="ui-switchable-panel store-selected" style="display: list-item;height:auto;width:auto;margin-left:20px;margin-right: 10px;" id="consignee_index_1">';
					html+='	<p class="red" style="font-size: 14px;">该商家在<span style="font-weight:bolder">'+ area +'</span>没有分店，请选择其他地区的分店或选择其他商家</p></li>';
					$('#consignee-list-1').html(html);
				}else {
					var html = '';
					$.each(stores,function(index,store){
						if(index == 0){
							html += '<li class="ui-switchable-panel store-selected" style="display: list-item;height:auto;width:auto;margin-left:20px;margin-right: 10px;" id="consignee_index_1">';
							html += '	<div class="consignee-item item-selected" consigneeid="" name="company_store" style="width:160px;height:90px;">';
							html += '		<span limit="" title="storeName">' + store.storeName + '</span>';
							html += '		<br>';
							html += '		<span class="addr-name" limit="" title="">'+store.area+store.addr+'</span>';
							html += '		<br>';
							html += '		<b></b>';
							html += '	</div>';
							html += '	<input type="hidden" value="'+ store.storeId +'"/>';
							html += '	<input class="companyId" type="hidden" value="'+ store.companyId +'"/>';
							html += '</li>';
						}else{
							html += '<li class="ui-switchable-panel" style="display: list-item;height:auto;width:auto;margin-left:20px;margin-right: 10px;" id="consignee_index_1">';
							html += '	<div class="consignee-item" consigneeid="" name="company_store" style="width:160px;height:90px;">';
							html += '		<span limit="" title="storeName">' + store.storeName + '</span>';
							html += '		<br>';
							html += '		<span class="addr-name" limit="" title="">'+store.area+store.addr+'</span>';
							html += '		<br>';
							html += '		<b></b>';
							html += '	</div>';
							html += '	<input type="hidden" value="'+ store.storeId +'"/>';
							html += '	<input class="companyId" type="hidden" value="'+ store.companyId +'"/>';
							html += '</li>';
						}
					});
					$('#consignee-list-1').html(html);
				}
			}
		}
	});
}