var total, rows;
$(function() {
	$("#btnsearch").click(function() {
		var op = {
			refresh : true
		};
		$("#page-num").val("0");
		initOrderList(op);
	});

	$("#order_time").change(function() {
		$("#page-num").val("0");
		var op = {
			refresh : true
		};
		initOrderList(op);
	});
	$(".Plate a").click(function() {
		if ($(this).hasClass("current"))
			return;
		$(this).addClass("current").siblings().removeClass("current");
		$("#page-num").val("0");
		$("#pay_status").val("");
		$("#status").val("");
		$("#search_order_id").val("");
		$("#search_goods_name").val("");
		$("#search_goods_bn").val("");
		$("#order_time").val("");
		switch ($(this).attr("args")) {
		case "nopayed":
			$("#pay_status").val(0);
			$("#status").val("create");
			break;
		case "uninstall":
			$("#pay_status").val(1);
			$("#status").val("uninstall");
			break;
		case "install":
			$("#pay_status").val(1);
			$("#status").val("install");
			break;
		case "unconfirm":
			$("#pay_status").val(1);
			$("#status").val('unconfirm');
			break;
		case "confirm":
			$("#pay_status").val(1);
			$("#status").val('confirm');
			break;
		case "finish":
			$("#pay_status").val(1);
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
	initOrderList({
		refresh : true
	});
	$(".setCommentDisplay").click(function() {
		var btnObj = $(this);
		$.ajax({
			url : '../commentsManager/setCommentDisplay',// 跳转到 action
			data : {
				commentId : $(this).attr("data-commentId"),
				display : $(this).attr("data-display")
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.resultObject.retCode == 0000) {
					if (btnObj.attr("data-display") == 0) {
						btnObj.html("开启");
						btnObj.attr("data-display", "1");
						alert("设置成功，前台页面已经关闭显示");
					} else {
						btnObj.html("关闭");
						btnObj.attr("data-display", "0");
						alert("设置成功，前台页面已经显示");
					}
				} else {
					alert("解释信息提交失败");
				}
			},
			error : function() {
				alert("异常！");
			}
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
		var param={orderid : $(this).attr("oid"),status : 'confirm',payStatus:null,addon:$(this).attr("data-addon")};
		layer.open({
			type: 1,
			title:false,
			move: false,
			area: ['370px', '210px'],
			closeBtn :2,
			zIndex:999,
			shadeClose: true, //点击遮罩关闭
			content:$("#salesConsultant"),
			success: function(layero, index){
				$("#salesConsultant .confirm-order").on("click",function(){
					if($("#sale-name").val().length>0||$("#sale-phone").val().length>0){
						if($("#sale-name").val().length<=0){
							layer.alert("请填写销售顾问姓名！",{title:false,closeBtn:false,icon:0});
							return;
						}
						var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
						if(!reg.test($("#sale-phone").val())) {
					      layer.alert("请输入正确的手机号！",{title:false,closeBtn:false,icon:0});
					      return;
						}
					}
					param.saleName=$("#sale-name").val();
					param.salePhone=$("#sale-phone").val();
					updateStatus("是否确认当前订单？",param);
				});
				$("#salesConsultant .cancel-order").on("click",function(){					
					layer.close(index);
				});
		    },
		    end:function (){
		    	$("#salesConsultant .confirm-order").unbind("click");
		    	$("#salesConsultant .cancel-order").unbind("click");
		    }
		});
	});
	
	$("#shop_order_list").on("click",".install-order",function (){
		updateStatus("是否确认完成安装？",{orderid : $(this).attr("oid"),status : 'install',payStatus:null,addon:$(this).attr("data-addon")});
	});
	
	$("#shop_order_list").on("click",".confirm-pay",function (){
		updateStatus("是否确认该订单已经到店支付完成？",{orderid : $(this).attr("oid"),status : 'unconfirm',payStatus:1,addon:$(this).attr("data-addon")});
	});
});

function loadImg() {
	$("body").find("img").each(function(x, img) {
		if ($(img).attr("data-original")) {
			$(img).attr("src", yoyo.imagesUrl + $(this).attr("data-original"));
		}
	});
}

function initOrderList(option) {
	$.ajax({url : '../shopOrder/loadOrderList',// 跳转到 action
			data : {
				page : $("#page-num").val(),
				rows : 10,
				status : $.trim($("#status").val()),
				payStatus : $.trim($("#pay_status").val()),
				orderTime : $.trim($("#order_time").val()),
				goodsName : $.trim($("#search_goods_name").val()),
				goodsBn : $.trim($("#search_goods_bn").val()),
				orderId : $.trim($("#search_order_id").val())
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
			if (data.resultObject.retCode == 0000) {
					$("#shop_order_list tbody:gt(0)").remove();
					$.each(data.resultObject.content.rows,function(x, order) {var createDate = new Date();
						createDate.setTime(order.createtime);
						var html=[];
						  html.push('<tbody>');
						  html.push('<tr class="tr-th">');
						  html.push('<td colspan="7">');
						  html.push('<span class="tcol1">订单号:<a target="_blank" href="../shopOrder/viewOrder?orderid='+ order.orderId + '">'+ order.orderId+ '</a></span>');
						  html.push('<span>'+createDate.format("yyyy-MM-dd hh:mm:ss")+'</span>');
						  html.push('</td>');
						  html.push('</tr>');
						$.each(order.orderItems,function(index,item) {
							var img = "", name = "", memberName = "", itemsLength = 0;
							if (item.product) {
								if (item.product
										&& item.product.picturePath) {
									img = item.product.picturePath;
								}
								if (item.product.name) {
									name = item.product.name;
								}
							}
							if (order.orderItems&& order.orderItems.length > 0) {
								itemsLength = order.orderItems.length;
							}
							html.push('<tr>');
							html.push('<td class="product">'+ '<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+ item.product.goodsId+ '" class="font-blue" target="_blank">');
							html.push('<img src="'+yoyo.imagesUrl+img+'" width="50px" height="50px" />'+ '</a>');
							html.push('</td>');
							html.push('<td class="product_name">');
							html.push('<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+ item.product.goodsId+ '" class="font-blue" target="_blank">'+ name+ '</a>');							
							html.push('</td>');
							html.push('<td>￥'+ fmoney(item.price)+ '<br>数量:'+ item.nums+ '</td>');
							if (index == 0) {
								html.push('<td rowspan="'+ itemsLength+ '">￥'+ fmoney(order.finalAmount)+'</td>');
								html.push('<td rowspan="'+ itemsLength+ '"> '+ order.orderMemberName+ '</td>');
								html.push('<td rowspan="'+ itemsLength+ '" class="textcenter">');
								html.push('<div style="width: 100px; display: block; margin: 0 auto;">');
								html.push(buildStatusText(order));
								html.push('</div>');
								html.push('</td>');
								html.push('<td rowspan="'+ itemsLength+ '">');
								if(order.payStatus==0&&order.paymentId==1&&order.status=="create"){
									html.push('<a href="javascript:void(0);" class="confirm-pay" data-addon="'+order.addon+'" oid="'+order.orderId+'">确认收款</a>');
								}else if(order.status=='unconfirm'){
									html.push('<a href="javascript:void(0);" class="confirm-order" data-addon="'+order.addon+'" oid="'+order.orderId+'">确认订单</a>');
								}
								html.push('<a target="_blank" class="font-blue operate-btn" href="../shopOrder/viewOrder?orderid='+ order.orderId+ '">查看订单</a>');
								if (order.payStatus == 0&&order.status!='dead'){
									html.push('<a href="javascript:void(0);" onclick ="cancelOrder('+ order.orderId+ ',\''+order.addon+'\')">取消订单</a>');
								}
								html.push('<a class="font-blue operate-btn" target="_blank" href="../shopOrder/printOrder?orderid='+ order.orderId+ '" target="_blank">打印订单</a>');
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
							items_per_page : data.resultObject.content.pageSize,
							callback : function() {
								var op = {
									refresh : false
								};
								initOrderList(op);
							}
						};
						$("#Pagination").yoyoPagination(
								data.resultObject.content.total, opt);
					}
				}
			}
		});
}

function buildStatusText(order) {
	var pay_status = {'0' : '未支付','1' : '已支付','2' : '已付款至到担保方','3' : '部分付款','4' : '部分退款','5' : '全额退款'};
	var status = {"dead" : "已撤单","finish" : "已完成","unconfirm" : "待确认","confirm" : "已确认","uninstall" : "待安装","install" : "已安装","installend" : "安装完成","create" : "待付款","refunds":"退款中"};
	var status_text = "";
	// 如果选项卡选中的是待付款则判断付款状态标识
	if(order.payStatus==0&&order.paymentId==1&&order.status!="dead"){
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

function updateStatus(msg,param){
	layer_utils.confirm(msg, function() {
		$.ajax({
			url : '../shopOrder/updateStatus',
			data : param,
			type : "post",
			dataType : "json",
			async : false,
			success : function(returnData) {
				if (returnData.retCode == yoyo.successCode) {
					layer.alert("操作订单成功！",{title:false,closeBtn:false,icon:1,end:function (){
						layer.closeAll();
						initOrderList({refresh:true});
						}
					});
				} else if (returnData.retCode == yoyo.failCode) {
					var msg="操作订单失败！";
					if(returnData.retMsg){
						msg=returnData.retMsg;
					}
					layer.alert(msg,{title:false,closeBtn:false,icon:0,end:function(){
						initOrderList({refresh:true});
						layer.closeAll();
					}});
				}				
			}
		});
	});
}

function cancelOrder(orderId,addon) {
	layer_utils.confirm('您确定要取消该订单吗？', function() {
		$.ajax({
			url : '../shopOrder/doCanelOrder',
			data : {orderid : orderId,addon:addon},
			type : "post",
			dataType : "json",
			async : false,
			success : function(returnData) {
				if (returnData.retCode == yoyo.successCode) {
					layer.alert("取消订单成功！",{title:false,closeBtn:false,icon:1,end:initOrderList({refresh:true})});
				} else if (returnData.retCode == yoyo.failCode) {
					var msg="订单取消失败，请稍后尝试！";
					if(returnData.retMsg){
						msg=returnData.retMsg;
					}
					layer.alert(msg,{title:false,closeBtn:false,icon:0,end:initOrderList({refresh:true})});
				}
			}
		});
	});
}

function reply(commentId) {
	$("#comment_tr_" + commentId).show(200);
}

function doReplyComment(commentId) {
	if ($.trim($("#reply-input-" + commentId).val()) == "") {
		alert("请输入解释内容");
		return;
	}
	if ($.trim($("#reply-input-" + commentId).val()).length > 300) {
		alert("内容超出300字符限制。");
		return;
	}
	$.ajax({
		url : '../commentsManager/doReplyComment',// 跳转到 action
		data : $("#reply_form_" + commentId).serialize(),
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data.resultObject.retCode == 0000) {
				var reply = '<tr class="noneborder">' + '<td colspan="4">'
						+ '<div class="refer refer_bg">'
						+ '<dl class="answer">' + '<dt>掌柜回复：</dt>' + '<dd>'
						+ $("#reply-input-" + commentId).val() + '</dd>'
						+ '</dl>' + '</div>' + '</td>' + '</tr>';
				$("#comment_tr_" + commentId).parent().append(reply);
				$("#comment_tr_" + commentId).val("");
			} else {
				alert("解释信息提交失败");
			}
		},
		error : function() {
			alert("异常！");
		}
	});
	$("#comment_tr_" + commentId).hide(200);
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
	}

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
}

function fmoney(s, n)   
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(var i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
}