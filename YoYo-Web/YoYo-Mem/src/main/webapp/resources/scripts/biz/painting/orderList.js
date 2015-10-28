$(document).ready(function(){
	$("form").find("input[type!='radio'][type!='checkbox'][type!='button'][type!='submit'][type!='reset']").each(function(){
		$(this).addClass("form_item_input");
	});
	
	loadDataList({refresh:true});
	
	$("#search_createtime").change(function (){
		loadDataList({refresh:true});
	});
	
	$("#search_order_status").change(function (){
		loadDataList({refresh:true});
	});
	
	$("body").on("click",".commstar a", commentStart);
	$("body").on("click",".submitform",addOrderComment);
});

function commentStart(){
	$(this).addClass("active").siblings().removeClass("active");
	$("#commentStar").val($(this).attr("_val"));
}

function addOrderComment(){
	if(($("#title").val().length<4)||($("#title").val().length>20)){
		$("#title").parent().next(".msg-error-01").removeClass("hide");			
		return;
	}
	
	if($("#commentStar").val().length==0){
		$("#commentStar").closest(".item").find(".msg-error-01").removeClass("hide");
		return;
	}
	
	if(($("#comment").val().length<10)||($("#comment").val().length>500)){
		$("#comment").closest(".item").find(".msg-error-01").removeClass("hide");
		return;
	}
	
	var form=$(this).closest("form");
	$("body").off("click",".submitform");
	$.post("../mypainting/addOrderComment",form.serialize(),function(result){
		if(result.retCode=="000000"){
			layer.msg("发表评论成功",{time:2000,end:function(){
				layer.close(index);
			}});
		}else{
			layer.msg("发表评论失败",{time:2000,end:function(){
				layer.close(index);
			}});
		}
		loadDataList({refresh:true});
	});
}
var index;
function openComment(id){
	index = layer.open({
		type: 1,
	    title: false,
	    closeBtn: false,
	    shadeClose: true,
	    skin: 'yourclass',
	    area: ['970px', '390px'],
	    content: html
	});
	$('#orderId').val(id);
}

function viewComment(id){
	$.ajax({
		url : '../mypainting/getComment?orderId='+id,
		type:'GET',
		success : function(data) {
			if (data.retCode == yoyo.successCode) {
				index = layer.open({
					type: 1,
				    title: false,
				    closeBtn: false,
				    shadeClose: true,
				    skin: 'yourclass',
				    area: ['970px', '390px'],
				    content: html,
				    end:function(){
				    	$("body").on("click",".commstar a", commentStart);
				    }
				});
				$('#orderId').val(id);
				$('#title').val(data.content.title).attr('disabled','true');
				$('#comment').val(data.content.comment).attr('disabled','true');
				var star = data.content.commentStar;
				$('a[_val='+star+']').addClass("active");
				$("body").off("click",".commstar a", commentStart);
				$('#commitButton').remove();
			}
		}
	});
}

var html = '			<div id="cmment_template" >' +
'<div class="comment-box prompt01" style="display: block">' +
'<form>' +
'<input type="hidden" name="orderId" id="orderId">' +
'<div class="box-t"></div>' +
'<div class="form">' +
	'<div class="item item01 titleEl">' +
		'<span class="label"><em>*</em>标题：</span>' +
		'<div class="tit">' +
		'<input type="text" value="" class="text area01" style="width: 340px;" name="title" id="title" autocomplete="off">' +
			'<div class="clr"></div>' +
			'<div class="msg-text ftx-03">4-20字</div>' +
		'</div>' +
		'<span class="msg-error-01 ml10 hide">麻烦填写4-20个字呦</span>' +
		'<div class="clr"></div>' +
	'</div>' +
	'<div class="item scoreEl">' +
		'<span class="label"><em>*</em>评分：</span>' +
		'<div class="fl">' +
			'<span class="commstar"> ' +
				'<a _val="1" class="star1" href="javascript:;"></a> ' +
				'<a _val="2" class="star2" href="javascript:;"></a> ' +
				'<a _val="3" class="star3" href="javascript:;"></a> ' +
				'<a _val="4" class="star4" href="javascript:;"></a> ' +
				'<a _val="5" class="star5" href="javascript:;"></a>' +
			'</span>' +
			'<input type="hidden" id="commentStar" name="commentStar" />' +
			'<div class="clr"></div>' +
		'</div>' +
		'<span class="msg-error-01 ml10 hide">你的评分是偶们前进的动力</span>' +
		'<div class="clr"></div>' +
	'</div>' +
	'<div class="item item01 xindeEl">' +
		'<span class="label"><em>*</em>心得：</span>' +
		'<div class="cont">' +
			'<textarea class="area area01" name="comment" placeholder="商品是否给力？快分享你的购买心得吧~" id="comment"></textarea>' +
			'<div class="clr"></div>' +
			'<span class="msg-error-01 hide">麻烦填写10-500个字呦</span>' +
			'<div class="msg-text ftx-03">10-500字</div>' +
		'</div>' +
		'<div class="clr"></div>' +
	'</div>' +
	'<div class="item item02">' +
		'<span class="label">&nbsp;</span>' +
		'<div class="fl">' +
			'<a id="commitButton" class="btn-5 mr20 submitform" href="#none"><s></s><span class="pingjiaEl">评价</span></a>' +
		'</div>' +
		'<div class="clr"></div>' +
	'</div>' +
'</div>' +
'</form>' +
'</div>' +
'</div>';        

/**
 * 取消订单
 */
function cancelOrder(orderId){
	layer.confirm('您确定要取消该订单吗？', {icon: 3, title:'提示'}, function(index){
	    layer.close(index);
	    $.ajax({
    		url:'../mypainting/handleOrder?orderId='+orderId+'&type=1',
    		dataType:"json"
	    }).done(function(returnData){
	    	debugger
	    	if(returnData.retCode == yoyo.successCode){
    			loadDataList({refresh:true});
    		}else if(returnData.retCode == yoyo.failCode){
    			layer.alert("取消失败！",{title:false,closeBtn:false,icon:2});
    		}else if(returnData.retCode == yoyo.doing){
    			location.href =  yoyo.memUrl+"/mypainting/applyRefunds?orderId="+orderId; 
    		}
	    });
	});
}

/**
 * 删除订单
 * @param orderId
 */
function deleteOrder(orderId){
	layer.confirm('\t\t\t您确定要删除该订单吗？\n删除后，您可以在订单回收站还原该订单也可以做永久删除。', {icon: 3, title:'提示'}, function(index){
	    layer.close(index);
	    $.ajax({
	    		url:'../mypainting/handleOrder?orderId='+orderId+'&type=2',
	    		async: false,
	    		success:function(returnData){
	    			if(returnData.retCode == yoyo.successCode){
	    				loadDataList({refresh:true});
	    			}else if(returnData.retCode == yoyo.failCode){
	    				layer.alert("删除失败！",{title:false,closeBtn:false,icon:2});
	    			}
	    		}
		});
   	});
}

/**
 * 确定完成订单
 * @param orderId
 */
function finishOrder(orderId){
	layer.confirm('您确定完成订单吗？', {icon: 3, title:'提示'}, function(index){
	    layer.close(index);
		$.ajax({
	    	url:'../mypainting/handleOrder?orderId='+orderId+'&type=8',
	    	async: false,
	    	success:function(returnData){
	    	  if(returnData.retCode == yoyo.successCode){
	    		  loadDataList({refresh:true});
	    	  }else if(returnData.retCode == yoyo.failCode){
	    		  layer.alert("确认完成操作失败！",{title:false,closeBtn:false,icon:2});
	    	  }
	    	}
		});
	});
}

/**
 * 查询条件
 * @returns {___anonymous2294_2490}
 */
function buildCondition(){
	var beforeTime =$.trim($('#search_createtime').val()) ;
	var payStatus = '' ;
	var status ='';
	var optionId = $("#search_order_status option:selected").val();
	if(optionId == 'allStateId'){
		payStatus = '';
		status = '';
	}if(optionId == 'payStatusId'){
		payStatus = '0';
		status = '';
	}else if(optionId == 'unconfirmId'){
		payStatus = '';
		status = 'unconfirm';
	}else if(optionId == 'uninstallId'){
		payStatus = '';
		status = 'uninstall';
	}else if(optionId == 'installId'){
		payStatus = '';
		status = 'install';
	}else if(optionId == 'finishId'){
		payStatus = '';
		status = 'finish';
	}else if(optionId == 'deadId'){
		payStatus = '';
		status = 'dead';
	}
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
	        payStatus :payStatus,
	        status : status,
	        disabled : '0',
	        pageSize:10,
	        createtime: createtime,
	        beforeOneYear: beforeOneYear,
	        pageNum:$("#page-num").val()	        
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

function loadDataList(opts){
	var html=[];
	$.ajax({
		url:'../mypainting/getOrderList',
		data:buildCondition(),
		dataType:"json",
		async: false,
		success:function(returnData){
			$("#tableId tbody").remove();
		    $.each(returnData.rows,function(index,order){
		    	debugger
	              html.push('<tr class="tr-th">');
	              html.push('<td colspan="7"><span>订单编号：<a  href="../mypainting/viewOrder?orderId='+order.id+' "class="dd_color" >'+order.id+'</a>');
	              if(order.source != 'web'){
	            	  html.push(' ('+ order.source +'客户端)');
	              }
	              html.push('</span>');
	              html.push('</td>');
	              html.push('</tr>');
	              var itemsLength=order.damageOfferList.length;
	               $.each(order.damageOfferList,function(index,carDamageOfferDetail){
	            		html.push('<tr>');
	            		html.push('<td class="product">');
	            		html.push('<a href="javascript:void(0)" class="font-blue" target="_blank">');
	            		var picUrl;
	            		if(carDamageOfferDetail.carDamageDetail.pic){
	            			picUrl = carDamageOfferDetail.carDamageDetail.pic.split(';')[0];
	            		}
	            		html.push('<img src="'+yoyo.imagesUrl+picUrl+'" width="80px" height="80px" />');
	            		html.push('</a>');
	            		html.push('</td>');
	            		html.push('<td class="product_name">');
	            		html.push('<label class="col1" style="padding-left: 10px;"><a href="javascript:void(0)" class="font-blue" target="_blank">'+carDamageOfferDetail.carDamageDetail.carPart.partName+'</a></label>');
	            		html.push('<label class="col2">￥'+fmoney(carDamageOfferDetail.offerPrice,2)+'</label>');
	            		html.push('</td>');

	            		if(index==0){
	            			html.push('<td rowspan="'+itemsLength+'">'+defualVal(order.store.storeName)+'</td>');
	            			html.push('<td rowspan="'+itemsLength+'">'+fmoney(order.payed,2)+'</td>');
	            			html.push('<td rowspan="'+itemsLength+'"> '+(new Date(order.createtime)).Format("yyyy-MM-dd HH:mm:ss")+'</td>');
	            			html.push('<td rowspan="'+itemsLength+'" class="textcenter">');
	            			html.push('<div style="width: 100px; display: block; margin: 0 auto;">'+buildStatusText(order)+'</div>');
							html.push('</td>');
							html.push('<td rowspan="'+itemsLength+'">');
							html.push('<div class="buy_opera">');
							html.push('<a href="../mypainting/viewOrder?orderId='+order.id+' " >查看</a> ');
							//status=======active:活动订单;dead:已作废;finish:已完成;unconfirm:待确认;create:创建订单;uninstall:未安装;install:安装中,"refunds":"退款中"
							//pay_status===500:取消订单后支付状态;0：未支付；1：已支付；2：已付款至担保方；3：部分付款；4：部分退款；5：全额退款
							if(order.payStatus == '0' && order.paymentId==0){
								html.push('<a href="../pay/payPaintingOrder?orderId='+order.id+' " >付款</a> ');
							}
							if(order.payStatus == 1 && order.complainCount<=3){
								if(order.complainCount==0){
									html.push('<a href="../mypainting/complain?orderId='+order.id+' ">投诉卖家</a> ');
								}else{
									if(order.complain.status=='error'||order.complain.status=='cancel'){
										html.push('<a href="../mypainting/complain?orderId='+order.id+' ">重新投诉</a> ');
									}
									html.push('<a href="../memberOrder/complainDetail/?complainId='+order.complain.complainId+'" >'+buildComplainText(order.complain.status)+'</a> ');
								}
							}
							
							//订单未确认，可以取消订单
							if(order.status == "create" ||order.status == "unconfirm"){
								html.push('<a href="javascript:void(0);" onclick ="cancelOrder('+order.id+')">取消订单</a> ');								
							}else if(order.payStatus == '1' && (order.status =="create" || order.status =="unconfirm" ||order.status =="uninstall")){
								//未到店安装，也可取消订单并退款
								html.push('<a href="javascript:void(0);" onclick ="cancelOrder('+order.id+')">取消订单</a>');
							}
							
							if(order.status == 'finish' || order.status == 'dead'){
								html.push('<a href="javascript:void(0);" onclick ="deleteOrder('+order.id+')">删除</a> ');
							}
							if(order.status == 'finish' && order.relayCount <1){
								html.push('<a href="javascript:void(0)" onclick="openComment('+order.id+')">评论</a> ');
							}
							if(order.status == 'finish' && order.relayCount >=1){
								html.push('<a href="javascript:void(0)" onclick="viewComment('+order.id+')">查看评论</a> ');
							}
							if(order.status=='install'){
								html.push('<a href="javascript:void(0);" onclick ="finishOrder('+order.id+')">确认完成</a> ');
							}
					        html.push('</div>');
					}
	            	html.push("</tr>");	          
	           });
		   });
		   $('#tableId').append(html.join(""));
		   if(opts&&opts.refresh){
	   			var opt = {
	   				items_per_page:returnData.pageSize,
	   				callback:function (){
	   					loadDataList({refresh:false});
	   				}
				};
	   			$("#Pagination").yoyoPagination(returnData.total,opt);
	   		}
		  }
	});
}

function defualVal(val){
	if(val&&val.length>0)return val;
	return "";
}

function buildComplainText(status){
	if(status=='intervene'){
		return "投诉中";
	}
	if(status=='success'){
		return "投诉成功";
	}
	if(status=='error'){
		return "投诉不成立";
	}
	if(status=='cancel'){
		return "已撤销投诉";
	}
}

function buildStatusText(order){
	var pay_status = {'0' : '未支付','1' : '已支付','2' : '已付款至到担保方','3' : '部分付款','4' : '部分退款','5' : '全额退款'};
	var status = {"dead" : "已取消","finish" : "已完成","unconfirm" : "待确认","confirm":"已确认","uninstall" : "未安装","install" : "已安装","installend" : "安装完成","create" : "待支付","refunds":"退款中"};
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