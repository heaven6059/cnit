var total,rows,checkArry;
$(document).ready(function(){
	
	$(".Plate a").click(function() {
		if ($(this).hasClass("current")){
			return;
		}
		$(this).addClass("current").siblings().removeClass("current");
	});
	
	$("#del-all-box").click(function (){	
		if($(this).is(":checked")){
			checkArry = new Array();
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).prop("checked",true);
				checkArry.push($(box).val());
			});
		}else{
			checkArry = [];
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).prop("checked",false);
			});
		}
	});
	
	$(".gridlist").on("click",":checkbox",function (){
		if(typeof(checkArry) == "undefined"){
			checkArry = new Array();
		}
		if($(this).is(":checked")){
			checkArry.push($(this).val());
		}else{
			for(var i=0;i<checkArry.length;i++){
				if($(this).val()==checkArry[i]){
					checkArry.elremove(i);  
				}
			}
		}
	});
	
	$(".trigger-btn").click(function (){
		var currentText = $(".Plate a.current").text();
		if(typeof(checkArry) == "undefined"||checkArry.length==0){
			layer_utils.alert('请选择需要操作的订单');
			return;
		}
		if($(this).attr("data-action")=="batchPDelOrder"){
			if('钣金订单' == currentText){
				handleOrder(checkArry.join(","),4);
			}else{
				deleteOrderComplete(checkArry.join(","));
			}
		}else{
			if('钣金订单' == currentText){
				handleOrder(checkArry.join(","),3);
			}else{
				deleteOrderRestore(checkArry.join(","));
			}
		}
	});
	loadDataList({refresh:true});
});

Array.prototype.elremove = function(m){  
    if(isNaN(m)||m>this.length){return false;}  
    this.splice(m,1);  
};

function resetQuery(){
	var inputObjs=jQuery("#query_id input[type='text']");
	  for(var i=0;i<inputObjs.length;i++){
	   var inputObj = inputObjs[i];
	   inputObj.value="";
	  }
	  var selectObjs = jQuery("#query_id select");
	  for(var i=0;i<selectObjs.length;i++){
	   var selectObj = selectObjs[i];
	   selectObj.options[0].selected=true;
	  }
	  search();
}



function deleteOrderRestore(orderId){
	layer_utils.confirm('您确定要还原订单吗？', function() {
    	$.ajax({
    		url:'../memberOrder/orderRestore',
    		type:"post",
    		data:{orderId:orderId},
    		dataType:"json",
    		async: false,
    		success:function(returnData){
    		  if(returnData.retCode == yoyo.successCode){    			  
    			  layer.msg('订单还原成功!',{time:2000,end:function (){
    				  window.location.href = '../memberOrder/orderRecycleBin';
    			  }});
    		  }else if(returnData.retCode == yoyo.failCode){
    			  layer.msg('订单还原失败!',{time:2000});
    		  }
    		}
		});
	});
}

function deleteOrderComplete(orderId){
	layer_utils.confirm('您确定要永久删除该订单吗？永久删除后，订单将无法恢复，您将无法对该订单的商品申请售后服务，请谨慎操作。',function(){
    	$.ajax({
    		url:'../memberOrder/deleteOrderComplete',
    		type:"post",
    		data:{orderId:orderId},
    		dataType:"json",
    		async: false,
    		success:function(returnData){
    		  if(returnData.retCode == yoyo.successCode){
    			  layer.msg('订单删除成功!',{time:1500,end:function(){
    				  window.location.href = '../memberOrder/orderRecycleBin';
    			  }});
    		  }else if(returnData.retCode == yoyo.failCode){
    			  layer.msg('订单删除失败!',{time:1500});
    		  }
    		}
		});
	});
}

function search(){
	var currentText = $(".Plate a.current").text();
	if('钣金订单' == currentText){
		ajaxGetData({refresh:true});
	}else{
		loadDataList({refresh:true});
	}
}

function submitForm(){
	var orderId =$.trim($('#search_order_id').val());
	var name =$.trim($('#search_name').val()) ;
	var createtime =$.trim($('#search_createtime').val()) ;
	var payStatus = '' ;
	var status ='';
	var optionId = $("#search_order_status option:selected").attr("id");
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
	}else if(optionId == 'finishId'){
		payStatus = '';
		status = 'finish';
	}else if(optionId == 'deadId'){
		payStatus = '';
		status = 'dead';
	}
	var paraData = {
			pageSize:10,
			pageNum:$("#page-num").val(),
			orderId : orderId,
	        name : name ,
	        createtime : createtime ,
	        payStatus :payStatus,
	        status : status,
	        disabled : '1',
	        qryType:"recycleBin"
	};
	return paraData;
}

function loadDataList(opts){
	$("#tableId tr:not(:first)").remove();
	$.ajax({
		url:'../memberOrder/getOrderList',
		type:"post",
		data:submitForm(),
		dataType:"json",
		async: false,
		success:function(returnData){
			  var html =[];
			  $.each(returnData.rows,function(index,order){
	              html.push('<tr class="tr-th">');
	              html.push('<td class="no_bk"><input type="checkbox" value="'+order.orderId+'"></td>');
	              html.push('<td class="order-hd" colspan="2"><span>订单编号：<a  href="../memberOrder/viewOrder?orderId='+order.orderId+' "class="dd_color" >'+order.orderId+'</a></span>');
				  if(order.parentOrderId != null && order.parentOrderId != '' ){
					html.push('子订单');
				  }
				  html.push('<span>订单日期：'+(new Date(order.createtime)).format("yyyy-MM-dd hh:mm:ss")+'</span>');
	              html.push('</td>');
	              html.push('<td class="order-hd" colspan="3">商家：'+order.store.storeName+'</td>');
	              html.push('</tr>');
	              var itemsLength=order.orderItems.length;
	               $.each(order.orderItems,function(index,orderItem){
	            		html.push('<tr>');
	            		html.push('<td class="product">');
	            		html.push('<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+orderItem.product.goodsId+'" class="font-blue" target="_blank">');
	            		html.push('<img src="'+yoyo.imagesUrl+orderItem.product.picturePath+'" width="50px" height="50px" />');
	            		html.push('</a>');
	            		html.push('</td>');
	            		html.push('<td class="product_name">');
	            		html.push('<label class="col1"><a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+orderItem.product.goodsId+'" class="font-blue" target="_blank">'+orderItem.product.name+'</a></label>');
	            		html.push('<label class="col2">￥'+formatCurrency(orderItem.price,2)+'</label>');
	            		html.push('<label class="col3">数量:'+orderItem.nums+'</label>');
	            		html.push('</td>');

	            		if(index==0){
	            			html.push('<td rowspan="'+itemsLength+'">'+formatCurrency(order.finalAmount,2)+'</td>');
	            			html.push('<td rowspan="'+itemsLength+'" class="textcenter">');
	            			html.push('<div style="width: 100px; display: block; margin: 0 auto;">'+buildStatusText(order)+'</div>');
							html.push('</td>');
							html.push('<td rowspan="'+itemsLength+'">');
							html.push('<div class="buy_opera">');
							html.push('<a href="../memberOrder/viewOrder?orderId='+order.orderId+'">查看</a> ');
							html.push('<a href="javascript:deleteOrderRestore('+order.orderId+')">还原订单</a> ');
							html.push('<a href="javascript:deleteOrderComplete('+order.orderId+')">永久删除</a> ');
					        html.push('</div>');
					        html.push('</td>');
					}
	            	html.push("</tr>");	          
	           });
		   });
			if(opts&&opts.refresh){
	   			var opt = {
	   				items_per_page:returnData.pageSize,
	   				callback:function (){
	   					loadDataList({refresh:false});
	   				}
				};
	   			$("#Pagination").yoyoPagination(returnData.total,opt);
	   		}
			$('#tableId').append(html.join(""));
		},
		error:function(){
		},
	});
}


function buildStatusText(order){
	var pay_status={'0':'未支付','1':'已支付','2':'已付款至到担保方','3':'部分付款','4':'部分退款','5':'全额退款'};	
	var status={"dead":"已取消","finish":"已完成","unconfirm":"待确认","uninstall":"未安装","install":"安装中","create":"未支付"};
	var status_text="";
	//如果选项卡选中的是待付款则判断付款状态标识
	if(order.payStatus==0&&order.paymentId==1){
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


//钣金相关--------------------------------------------start

function queryPaintingOrder(){
	$('tbody').empty();
	$('#name').remove();
	$('#del-all-box').removeAttr('checked');
	checkArry = [];
	ajaxGetData({refresh:true});
}

/**
 *获得查询参数
 */
function getConditions(){
	var params = {
		beforeOneYear: false,
		disabled : '1',
		pageSize:10,
        pageNum:$("#page-num").val()	  
	};
	var orderId = $('#search_order_id').val().trim();
	var createtime = parseInt($('#search_createtime').val());
	if(orderId){
		params.id = orderId;
	}
	if(createtime != 0 ){
		if( createtime > 12){
			params.createtime =  countDate(12);
			params.beforeOneYear = true;
		}else{
			params.createtime =  countDate(createtime);
		}
	}
	return params;
}

/**
 * 查询日期处理
 * @param i
 * @returns
 */
function countDate(i){
	var value = parseInt(i);
	if(value == 12){
		var thisYear = new Date().getFullYear();
		return new Date(thisYear, 0, 1).pattern("yyyy-MM-dd HH:mm:ss");
	}
	var now = new Date();
	now.setMonth(now.getMonth()-value);
	return now.pattern("yyyy-MM-dd HH:mm:ss");
}

/**
 * 还原或者永久删除订单
 * @param id
 * @param type
 */
function handleOrder(id,type){
	var comfirmText = '';
	if(type && parseInt(type) == 3){
		comfirmText = '您确定还原订单吗';
	}else if(type && parseInt(type) == 4){
		comfirmText = '您确定要永久删除该订单吗？永久删除后，订单将无法恢复，您将无法对该订单的商品申请售后服务，请谨慎操作。';
	}
	var r = confirm(comfirmText);
	if( r == true){
		$.ajax({
	    	url:'../mypainting/handleOrder?orderId='+id+'&type='+type,
	    	async: false,
	    	success:function(returnData){
	    	  if(returnData.retCode == yoyo.successCode){
	    		  ajaxGetData({refresh:true});
	    		  layer.msg('订单处理成功!',{time:2000});
	    	  }else if(returnData.retCode == yoyo.failCode){
	    		  layer.msg('订单处理失败!',{time:2000});
	    	  }
	    	}
		});
	}
}

function ajaxGetData(opts){
	var html =[];
	$.ajax({
		url:'../mypainting/getOrderList',
		type:"post",
		data:getConditions(),
		async: false,
		success:function(returnData){
			  $("#tableId tbody").remove();
			  $.each(returnData.rows,function(index,order){
	              html.push('<tr class="tr-th">');
	              html.push('<td class="no_bk"><input type="checkbox" value="'+order.id+'"></td>');
	              html.push('<td class="order-hd" colspan="2"><span>订单编号：<a  href="../mypainting/viewOrder?orderId='+order.id+' "class="dd_color" >'+order.id+'</a></span>');
				  html.push('<span>订单日期：'+(new Date(order.createtime)).format("yyyy-MM-dd hh:mm:ss")+'</span>');
	              html.push('</td>');
	              html.push('<td class="order-hd" colspan="3">商家：'+order.store.storeName+'</td>');
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
	            		html.push('<label class="col1"><a href="javascript:void(0)" class="font-blue" target="_blank">'+carDamageOfferDetail.carDamageDetail.carPart.partName+'</a></label>');
	            		html.push('<label class="col2">￥'+formatCurrency(carDamageOfferDetail.offerPrice,2)+'</label>');
	            		html.push('</td>');

	            		if(index==0){
	            			html.push('<td rowspan="'+itemsLength+'">'+formatCurrency(order.payed,2)+'</td>');
	            			html.push('<td rowspan="'+itemsLength+'" class="textcenter">');
	            			html.push('<div style="width: 100px; display: block; margin: 0 auto;">'+buildStatusText(order)+'</div>');
							html.push('</td>');
							html.push('<td rowspan="'+itemsLength+'">');
							html.push('<div class="buy_opera">');
							html.push('<a href="../mypainting/viewOrder?orderId='+order.id+'">查看</a> ');
							html.push('<a href="javascript:handleOrder('+order.id+','+3+')">还原订单</a> ');
							html.push('<a href="javascript:handleOrder('+order.id+','+4+')">永久删除</a> ');
					        html.push('</div>');
					        html.push('</td>');
					}
	            	html.push("</tr>");	          
	           });
		   });
			if(opts && opts.refresh){
	   			var opt = {
	   				items_per_page:returnData.pageSize,
	   				callback:function (){
	   					ajaxGetData({refresh:false});
	   				}
				};
	   			$("#Pagination").yoyoPagination(returnData.total,opt);
	   		}
			$('#tableId').append(html.join(""));
		}
	});
}

//钣金相关---------------------------------------------end