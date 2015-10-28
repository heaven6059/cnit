$(document).ready(function(){
	orderId  =  $('#initOrderId').val();
	loadDataList({refresh:true});
});


function resetQuery(){
	var inputObjs=jQuery("#query_id input[type='text']");
	for(var i=0;i<inputObjs.length;i++){
	  var inputObj = inputObjs[i];
	  inputObj.value="";
	}
	loadDataList({refresh:true});
}


function searchReship(){
	loadDataList({refresh:true});
}

function addReship(orderId,itemId){
	window.location.href="../memberOrder/addReship?orderId="+orderId+"&itemId="+itemId;
}

function submitForm(){
	var orderId =$.trim($('#search_order_id').val());
	var name =$.trim($('#search_name').val()) ;
	var page=0;
	if($("#page-num").val()){
		page=$("#page-num").val();
	}
	var paraData = {
			orderId : orderId,
			goodsName : name ,
	        disabled : '0',
	        orderKind : 'reshipList',
	        payStatus:1,	        
	        page:page,
	        rows:10,
	};
	return paraData;
}


function loadDataList(opts){
	var html=[];
	$("#tableId tr:not(:first)").remove();
	$.ajax({
		url:'../memberOrder/applyAfterSales',
		type:"post",
		data:submitForm(),
		dataType:"json",
		async: false,
		success:function(result){
		  if(result.head.retCode==0000){
			 $.each(result.content.rows,function(index,order){
				 html.push('<tr>');
				 html.push('<td class="vt no_bk">');
				 html.push('<span><a  href="../memberOrder/viewOrder?orderId='+order.orderId+' " class="operate-btn"> '+order.orderId +' </a></span>');
				 html.push('</td>');
				 html.push('<td class="horizontal-m">');
			   $.each(order.afterSales,function(index,orderItem){
				   	html.push('<div class="item-form">');
					html.push('<div class="cell p-goods">');
					html.push('<div class="goods-item">');
					html.push('<div class="p-img">');
					html.push('<a target="_blank" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+orderItem.goodsId+'" target="_blank"><img src="'+yoyo.imagesUrl+orderItem.picturePath+'" width="50" height="50"></a>');
					html.push('</div>');
					html.push('<div class="item-msg">');
					html.push('	<div class="p-name">');
					html.push('		<a target="_blank" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+orderItem.goodsId+'">'+orderItem.productName+'</a>');
					html.push('	</div>');							
					html.push('</div>');
					html.push('</div>');
					html.push('</div>');
					html.push('<div class="cell p-ops">');
					if (orderItem.returnId){
						html.push('<a class="btn-5 flex01" href="../reship/viewAfterSales?reshipId='+orderItem.returnId+'"><s></s>查看进度</a>');
					}else{
						html.push('<a class="btn-5 flex01" onclick="addReship(\''+order.orderId+'\',\''+orderItem.itemId+'\')" href="javascript:void(0);">申请<s></s></a>');
					}
					html.push('</div>');
					html.push('</div>');
			   });
			   
			   	html.push('</td>');
				html.push('<td>'+(new Date(order.createTime)).format("yyyy-MM-dd hh:mm:ss")+'</td>');
				html.push('</tr>');
			 });
		  	}
		  	$("#tableId").append(html.join(""));
		  	if(opts&&opts.refresh){
	   			var opt = {
	   				items_per_page:result.content.pageSize,
	   				callback:function (){
	   					loadDataList({refresh:false});
	   				}
				};
	   			$("#Pagination").yoyoPagination(result.content.total,opt);
	   		}
		},
		error:function(){
		},
	});
}