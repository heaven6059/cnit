$(document).ready(function(){
	loadComplainList({refresh:true});
});

function isEmpty(object){
	if(object == null || object)
	return 
}

function loadComplainList(opts){
	var html=[];
	$("#tableId tr:not(:first)").remove();
	$.ajax({
		url:'../reship/getReshipList',
		type:"post",
		data:{pageNum:$("#page-num").val(),pageSize:20},
		dataType:"json",
		async: false,
		success:function(returnData){
			 $.each(returnData.content.rows,function(index,reshipDTO){
				 html.push('<tr>');
				 html.push('<td align="center">'+reshipDTO.returnId+'</td>');
				 html.push('<td align="center">'+reshipDTO.orderId+'</td>');
				 if(reshipDTO.productName == null  ){
				 html.push('<td align="center">&nbsp;</td>');
				 }else{
				 html.push('<td align="left"><a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+reshipDTO.goodsId+'" target="_blank">'+reshipDTO.productName+'</a></td>');
				 }
				 if(reshipDTO.number == null  ){
				 html.push('<td align="center">&nbsp;</td>');
				 }else{
				 html.push('<td align="center">'+reshipDTO.number+'</td>');
				 }
				 html.push('<td align="center">'+new Date(reshipDTO.addTime).format("yyyy-MM-dd hh:mm:ss")+'</td>');
				 html.push('<td align="center">'+reshipDTO.statusText+'</td>');
				 html.push('<td align="center">');
				 if((reshipDTO.status==6||reshipDTO.status==11)&&reshipDTO.appeal!=1){
					 html.push('<a href="'+yoyo.memUrl+'/memberOrder/appealReship?orderId='+reshipDTO.orderId+'&itemId='+reshipDTO.itemsId+'&returnId='+reshipDTO.returnId+'">再次申请</a><br>');	 
				 }
			     html.push('<a href="'+yoyo.memUrl+'/reship/viewAfterSales?reshipId='+reshipDTO.returnId+'">查看</a>');
				 html.push('</td>');
				 html.push('</tr>');
			 });
			$("#tableId").append(html.join(""));
			if(opts&&opts.refresh){
	   			var opt = {
	   				items_per_page:returnData.content.pageSize,
	   				callback:function (){
	   					loadComplainList({refresh:false});
	   				}
				};
	   			$("#Pagination").yoyoPagination(returnData.content.total,opt);
	   		}
		},
		error:function(){
		},
	});
}