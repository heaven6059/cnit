var total, rows;
$(function() {
	debugger
	initOrderList({
		refresh : true
	});
	
	$("#btnsearch").click(function() {
		debugger
		var op = {
			refresh : true
		};
		$("#page-num").val("0");
		initOrderList(op);
	});

	$(".Plate a").click(function() {
		if ($(this).hasClass("current")) return;
		$(this).addClass("current").siblings().removeClass("current");
		$("#page-num").val("0");
		$("#pay_status").val("");
		$("#status").val("");
		$("#search_order_id").val("");
		$("#search_goods_name").val("");
		$("#search_goods_bn").val("");
		$("#order_time").val("0");
		switch ($(this).attr("args")) {
			case "nopayed":
				$("#pay_status").val(0);
				break;
			case "uninstall":
				$("#status").val("uninstall");
				break;
			case "install":
				$("#status").val("install");
				break;
			case "unconfirm":
				$("#status").val('unconfirm');
				break;
			case "finish":
				$("#status").val('finish');
				break;
			case "dead":
				$("#status").val('dead');
				break;
		}
		initOrderList({
			refresh : true
		});
	});
	
	// 创建分页元素
	$("#Pagination").yoyoPagination(total, {
		items_per_page : rows,
		callback : function() {
			var op = {
				refresh : false
			};
			initOrderList(op);
		}
	});
	
	$("#shop_order_list").on("click",".confirm-order",function (){
		updateStatus("是否确认当前订单？",$(this).attr("oid"), $(this).attr('ostatus'), '6');
	});
	
	$("#shop_order_list").on("click",".install-order",function (){
		updateStatus("是否确认完成安装？",$(this).attr("oid"), $(this).attr('ostatus'), '7');
	});
	
});

/**
 * 查询条件
 */
function buildCondition(){
	var beforeTime =$.trim($('#order_time').val()) ;
	var beforeOneYear,createtime;
	if( beforeTime && parseInt(beforeTime) == 0){
		beforeTime = null;
		beforeOneYear = false;
	}else if( beforeTime && parseInt(beforeTime) < 12 ){
		createtime = countDate(beforeTime);
		beforeOneYear = false;
	}else if( beforeTime && parseInt(beforeTime) > 12 ){
		createtime = countDate(12);
		beforeOneYear = true;
	}else if( beforeTime && parseInt(beforeTime) == 12 ){
		var thisYear = new Date().getFullYear();
		createtime = new Date(thisYear, 0, 1).pattern("yyyy-MM-dd");
	}
	var paraData = {
			pageNum : $("#page-num").val(),
			pageSize : 10,
			status : $.trim($("#status").val()),
			payStatus : $.trim($("#pay_status").val()),
			id : $.trim($("#search_order_id").val()),
			createtime: createtime,
			beforeOneYear: beforeOneYear
	};
	return paraData;
}

/**
 * 日期计算
 */
function countDate(i){
	var now = new Date();
	now.setMonth(now.getMonth()-parseInt(i));
	return now.pattern("yyyy-MM-dd");
}

function loadImg() {
	$("body").find("img").each(function(x, img) {
		if ($(img).attr("data-original")) {
			$(img).attr("src", yoyo.imagesUrl + $(this).attr("data-original"));
		}
	});
}

function initOrderList(option) {
	$.ajax({url : '../paintingManager/getOrderList',// 跳转到 action
			data : buildCondition(),
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				debugger
				if (data.retCode == yoyo.successCode) {
					$("#shop_order_list tbody:gt(0)").remove();
					$.each(data.content.rows,function(x, order) {
						var createDate = new Date();
						createDate.setTime(order.createtime);
						var html=[];
						  html.push('<tbody>');
						  html.push('<tr class="tr-th">');
						  html.push('<td colspan="7"><span class="tcol1">订单号:<a href="../paintingManager/viewOrder?orderId='+ order.id + '">'+ order.id+ '</a></span></td>');
						  html.push('</tr>');
						$.each(order.damageOfferList, function(index,item) {
							var img = "", name = "", itemsLength = 0;
							if (item.carDamageDetail) {
								if (item.carDamageDetail && item.carDamageDetail.pic) {
									img = item.carDamageDetail.pic.split(';')[0];
								}
								if (item.carDamageDetail.carPart.partName) {
									name = item.carDamageDetail.carPart.partName;
								}
							}
							if (order.damageOfferList && order.damageOfferList.length > 0) {
								itemsLength = order.damageOfferList.length;
							}
							html.push('<tr>');
							html.push('<td class="product">'+ '<a href="javascript:void(0)" class="font-blue" target="_blank">');
							html.push('<img src="'+yoyo.imagesUrl+img+'" width="80px" height="80px" />'+ '</a>');
							html.push('</td>');
							html.push('<td class="product_name">');
							html.push('<label class="col1"><a href="javascript:void(0)" class="font-blue" target="_blank">'+ name+ '</a></label>');
							html.push('<label class="col2">￥'+ formatCurrency(item.offerPrice)+ '</label>');
							html.push('</td>');
							if (index == 0) {
								html.push('<td rowspan="'+ itemsLength+ '">￥'+ formatCurrency(order.payed)+'</td>');
								html.push('<td rowspan="'+ itemsLength+ '">'+ createDate.format("yyyy-MM-dd hh:mm:ss")+ '</td>');
								html.push('<td rowspan="'+ itemsLength+ '"> '+ order.name+ '</td>');
								html.push('<td rowspan="'+ itemsLength+ '" class="textcenter">');
								html.push('<div style="width: 100px; display: block; margin: 0 auto;">');
								html.push(buildStatusText(order));
								html.push('</div>');
								html.push('</td>');
								html.push('<td rowspan="'+ itemsLength+ '">');
								html.push('<a class="font-blue operate-btn" href="../paintingManager/viewOrder?orderId='+ order.id+ '">查看订单</a>');
								if(order.status=='uninstall'){
									html.push('<a href="javascript:void(0);" class="install-order" oid="'+order.id+'" ostatus="'+ order.status +'">完成安装</a>');
								}
//								if( order.paymentId == 1 && (order.status=='unconfirm' || order.status=='create')){
//									html.push('<a href="javascript:void(0);" class="confirm-order" oid="'+order.id+'">确认订单</a>');
//								}
//								if( order.paymentId == 0 && order.status=='unconfirm'){
//									html.push('<a href="javascript:void(0);" class="confirm-order" oid="'+order.id+'">确认订单</a>');
//								}
								//在线支付，已支付,待确认
								if(order.paymentId == 0 && order.status=='unconfirm' && order.payStatus =="1"){
									html.push('<a href="javascript:void(0);" class="confirm-order" oid="'+order.id+'" ostatus="'+ order.status +'">确认订单</a>');
								}
								//到店支付，未支付，新建订单
								if(order.paymentId == 1 && order.status=='create' && order.payStatus =="0"){
									html.push('<a href="javascript:void(0);" class="confirm-order" oid="'+order.id+'" ostatus="'+ order.status +'">确认订单</a>');
								}
								html.push('<a class="font-blue operate-btn" target="_blank" href="../paintingManager/printOrder?orderId='+ order.id+ '" target="_blank">打印订单</a>');
								html.push('</td>');
							}
							html.push('</tr>');
						});
						html.push('</tr>');
						html.push('</tbody>');
						$("#shop_order_list").append(html.join(""));
					});
					loadImg();
					if (option && option.refresh) {
						var opt = {
							items_per_page : data.content.pageSize,
							callback : function() {
								var op = {
									refresh : false
								};
								initOrderList(op);
							}
						};
						$("#Pagination").yoyoPagination(data.content.total, opt);
					}
				}else{
					$('#content').empty();
					$('.title').after(data.retMsg);
				}
			}
		});
}

function buildStatusText(order) {
	var pay_status = {'0' : '未支付','1' : '已支付','2' : '已付款至到担保方','3' : '部分付款','4' : '部分退款','5' : '全额退款'};
	var status = {"dead" : "已取消","finish" : "已完成","unconfirm" : "待确认","confirm":"已确认","uninstall" : "待安装","install" : "已安装","installend" : "安装完成","create" : "待付款"};
	var status_text = "";
	// 如果选项卡选中的是待付款则判断付款状态标识
	if (order.payStatus == 0 && order.paymentId == 1 && order.status == 'create') {
		return "未支付[到店支付]";
	}
	$.each(pay_status, function(x, y) {
		if (x == order.payStatus) {
			status_text = y;
		}
	});
	$.each(status, function(x, y) {
		if (x == order.status) {
			status_text = y;
			return false;
		}
	});
	return status_text;
}

function updateStatus(msg,orderId,oldStatus,type){
	layer_utils.confirm(msg, function() {
		$.ajax({
			url : '../paintingManager/handleOrder',
			data : {
				orderId : orderId,
				type : type,
				oldStatus:oldStatus
			},
			type : "post",
			dataType : "json",
			async : false,
			success : function(returnData) {
				if (returnData.retCode == yoyo.successCode) {
					layer.msg("操作订单成功！",{end:initOrderList({refresh:true})});
				} else if (returnData.retCode == yoyo.failCode) {
					layer.msg("操作订单失败！");
				}else if(returnData.retCode == yoyo.validateElementError){
					layer.msg("订单状态有更新，请刷新页面重新操作");
				}
			}
		});
	});
}

/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
	/*
	 * eg:format="YYYY-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 * 
 * @param num
 *            数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
	if (!num)
		return "";
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ','
				+ num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + num + '.' + cents);
}