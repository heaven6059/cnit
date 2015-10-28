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
	
	$("#tableId").on("click",".finish-order",function (){
		updateStatus("是否确认完成当前订单？",{orderid : $(this).attr("oid"),status : 'finish',addon:$(this).attr("data-addon")});
	});
});


function resetQuery(){
	window.location.href = '../memberOrder/orderList';
}

function cancelOrder(orderId,addon){
    layer_utils.confirm('您确定要取消该订单？(订单所使用的积分将不会返还)',function(){
	    $.ajax({
	    		url:'../memberOrder/canelOrder?orderId='+orderId+'&addon='+addon,
	    		type:"post",
	    		dataType:"json",
	    		async: false,
	    		success:function(returnData){
		    		if(returnData.retCode == yoyo.successCode){
		    		  window.location.href = '../memberOrder/orderList';
		    		  layer.alert("订单取消成功！",{title:false,closeBtn:false,icon:1,end:loadDataList({refresh:true})});
		    		}else if(returnData.retCode == yoyo.failCode){
		    			var msg="订单取消失败，请稍后尝试！";
						if(returnData.retMsg){
							msg=returnData.retMsg;
						}
		    			layer.alert(msg,{title:false,closeBtn:false,icon:2,end:loadDataList({refresh:true})});
		    		}
	    		}
		});
   	});
}

function deleteOrder(orderId){
    layer_utils.confirm('\t\t\t您确定要删除该订单吗？\n删除后，您可以在订单回收站还原该订单也可以做永久删除。',function(){
	    $.ajax({
	    	url:'../memberOrder/deleteOrder?orderId='+orderId,
	    	type:"post",
	    	dataType:"json",
	    	async: false,
	    	success:function(returnData){
	    	  if(returnData.retCode == yoyo.successCode){
	    		  layer.alert("订单删除成功！",{title:false,closeBtn:false,icon:1,end:loadDataList({refresh:true})});
	    	  }else if(returnData.retCode == yoyo.failCode){
	    		  alert("订单删除失败！");
	    	  }
	    	}
		});
   	});
}

function updateStatus(msg,param){
	layer_utils.confirm(msg, function() {
		$.ajax({
			url : '../memberOrder/updateStatus',
			data : param,
			type : "post",
			dataType : "json",
			async : false,
			success : function(returnData) {
				if (returnData.retCode == yoyo.successCode) {
					layer.alert("操作订单成功！",{title:false,closeBtn:false,icon:1,end:loadDataList({refresh:true})});
				} else if (returnData.retCode == yoyo.failCode) {
					var msg="操作订单失败！";
					if(returnData.retMsg){
						msg=returnData.retMsg;
					}
					layer.alert(msg,{title:false,closeBtn:false,icon:0,end:loadDataList({refresh:true})});
				}				
			}
		});
	});
}

function updateBuyCount(ids){
	$.ajax({
		url : '../memberOrder/updateGoodsBuyCount',
		data : {ids : ids},
		type : "post",
		dataType : "json",
		async : false,
		success : function(returnData) {}
	});
}



function submitForm(){
	loadDataList({refresh:true});
}
function buildCondition(){
	var orderId =$.trim($('#search_order_id').val());
	var name =$.trim($('#search_name').val()) ;
	var createtime =$.trim($('#search_createtime').val()) ;
	var payStatus = '' ;
	var status ='';
	var optionVal = $("#search_order_status").val();
	if(optionVal == 'all'){
		payStatus = '';
		status = '';
	}else if(optionVal == 'unpay'){
		payStatus = '0';
		status = 'create';
	}else{
		payStatus = '';
		status = optionVal;
	}
	var paraData = {
			orderId : orderId,
	        name : name ,
	        createtime : createtime ,
	        payStatus :payStatus,
	        status : status,
	        disabled : '0',
	        pageSize:10,
	        pageNum:$("#page-num").val()	        
	};
	return paraData;
}


function loadDataList(opts){
	var html=[];
	$("#tableId tbody").remove();
	$.ajax({
		url:'../memberOrder/getOrderList',
		type:"post",
		data:buildCondition(),
		dataType:"json",
		async: false,
		success:function(returnData){
			   $.each(returnData.rows,function(index,order){
 				    if(
 				    	((index==0||((returnData.rows[index-1])&&order.parentOrderId!=returnData.rows[index-1].parentOrderId)))&&
 				    	((returnData.rows[index+1])&&order.parentOrderId==returnData.rows[index+1].parentOrderId)
 				    ){
					      html.push('<tr>');
			              html.push('<td align="left" class="td-02" colspan="7">主订单号:'+order.parentOrderId+'</td>');
			              html.push('</tr>');
				   	 }
		              html.push('<tr class="tr-th">');
		              html.push('<td colspan="6">');
		              if(((returnData.rows[index+1])&&(order.parentOrderId==returnData.rows[index+1].parentOrderId))||
		            	 ((returnData.rows[index-1])&&(order.parentOrderId==returnData.rows[index-1].parentOrderId))){            	  
		            	  html.push('<span>订单号：<a href="../memberOrder/viewOrder?orderId='+order.orderId+' "class="dd_color" >'+order.orderId+'</a>(子订单)</span>');
		              }else{
		            	  html.push('<span>订单号：<a href="../memberOrder/viewOrder?orderId='+order.orderId+' "class="dd_color" >'+order.orderId+'</a></span>');
		              }
		              html.push('<span>'+(new Date(order.createtime)).Format("yyyy-MM-dd HH:mm:ss")+'</span>');
		              if(defualVal(order.store.storeName).length>0){
		              html.push('<span><a href="'+yoyo.portalUrl+'/shop/index?companyId='+order.store.companyId+'" target="_blank">'+defualVal(order.store.storeName)+'</span>');
		              }
		              html.push('</td>');
		              html.push('</tr>');
		             var itemsLength=order.orderItems.length;
		               $.each(order.orderItems,function(index,orderItem){
		            		html.push('<tr>');
		            		html.push('<td>');
		            		html.push('<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+orderItem.product.goodsId+'" class="font-blue" target="_blank">');
		            		html.push('<img src="'+yoyo.imagesUrl+orderItem.product.picturePath+'" width="50px" height="50px" />');
		            		html.push('</a>');
		            		html.push('</td>');
		            		html.push('<td class="product_name">');
		            		html.push('<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+orderItem.product.goodsId+'" class="font-blue" target="_blank">'+orderItem.product.name+'</a>');
		            		html.push('</td>');
		            		html.push('<td class="left">');
		            		html.push('单价：'+fmoney(orderItem.price,2)+'<br>');
		            		html.push('数量：'+orderItem.nums);
		            		html.push('</td>');
		            		if(index==0){
		            			html.push('<td rowspan="'+itemsLength+'" class="price">￥'+fmoney(order.finalAmount,2)+'</td>');		            			
		            			html.push('<td rowspan="'+itemsLength+'" class="textcenter">');
		            			html.push('<div style="width: 100px; display: block; margin: 0 auto;">'+buildStatusText(order)+'</div>');
								html.push('</td>');
								html.push('<td rowspan="'+itemsLength+'">');
								if(order.payStatus == '0'&&order.paymentId==0&&order.status=="create"){
									html.push('<a class="btn-4" href="../pay/payOrder?orderId='+order.orderId+'&addon='+order.addon+'" >付款</a><br>');
								}
								html.push('<a target="_blank" href="../memberOrder/viewOrder?orderId='+order.orderId+'" >查看</a><br>');
								if(order.payStatus == 1&&order.complainCount<3){
									if(order.complainCount==0){
										html.push('<a href="../memberOrder/complainPage?orderId='+order.orderId+' ">投诉卖家</a><br>');
									}else{
										if(order.complain.status=='error'||order.complain.status=='cancel'){
											html.push('<a href="../memberOrder/complainPage?orderId='+order.orderId+' ">重新投诉</a><br>');
										}
										html.push('<a href="../memberOrder/complainDetail/?complainId='+order.complain.complainId+'" >'+buildComplainText(order.complain.status)+'</a><br>');
									}
								}
								if(order.payStatus==1&&(order.status=='unconfirm'||order.status=='confirm')){
									html.push('<a href="../memberOrder/applyRefunds?orderId='+order.orderId+'">申请退款</a><br>');
								}
								if(order.status=='create'&&order.payStatus==0){
									html.push('<a href="javascript:void(0);" onclick ="cancelOrder('+order.orderId+','+order.addon+')">取消订单</a><br>');								
								}
								if(order.status=='confirm'&&order.refundStatus==0){									
									html.push('<a href="javascript:void(0);" class="finish-order" data-addon="'+order.addon+'" oid="'+order.orderId+'">确认完成</a><br>');
								}
								if(order.status == 'finish'){
									html.push('<a href="../memberOrder/applyReship?orderId='+order.orderId+' " >退货/退款</a><br>');
									html.push('<a href="../orderComment/orderComment?orderId='+order.orderId+' " >评论</a><br>');
								}
								if(order.status == 'finish'||order.status == 'dead'){
									html.push('<a href="javascript:void(0);" onclick ="deleteOrder('+order.orderId+')">删除</a><br>');
								}
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
		  },
		error:function(){
		},
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
	var pay_status={'0':'未支付','1':'已支付','2':'已付款至到担保方','3':'部分付款','4':'部分退款','5':'全额退款'};	
	var status={"create":"未支付","unconfirm":"待确认","confirm":"已确认","uninstall":"未安装","install":"已安装","refunds":"退款中","finish":"已完成","dead":"已撤单"};
	//var refund_status={"1":"等待卖家确认","2":"同意售后服务申请","3":"拒绝售后服务申请","4":"平台介入,等待卖家举证","5":"平台介入,卖家已举证","6":"平台介入,平台裁定拒绝申请","7":"平台介入,平台裁定同意申请","8":"卖家已退款，等待系统结算","9":"卖家未处理，系统自动同意申请","10":"完成"};
		
	var status_text="";
	//如果选项卡选中的是待付款则判断付款状态标识
	if(order.payStatus==0&&order.paymentId==1&&order.status!="dead"){
		return "未支付[到店支付]";
	}

	$.each(pay_status,function (x,y){
		if(x==order.payStatus){
			status_text=y;
		}			
	});
	
	$.each(status,function (x,y){
		if(x==order.status){
			status_text=y;
			return false;
		}
	});
	return status_text;
}