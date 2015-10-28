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
		url:'../reship/loadRefunds',
		data:{pageNum:$("#page-num").val(),pageSize:10},
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			 $.each(returnData.content.rows,function(index,reshipDTO){
				 html.push('<tr>');
				 html.push(' <td align="center">'+reshipDTO.orderId+'</td>');
				 html.push(' <td align="center">'+new Date(reshipDTO.addTime).format("yyyy-MM-dd hh:mm:ss")+'</td>');
				 html.push(' <td align="center">'+reshipDTO.amount+'</td>');
				 html.push(' <td align="center">'+reshipDTO.statusText+'</td>');
				 html.push('<td align="center">');
				 if(reshipDTO.status==11||reshipDTO.status==6){
					 html.push('<a href="../memberOrder/applyRefunds?orderId='+reshipDTO.orderId+'">重新申请</a><br>');
				 }
			     html.push('<a href="../reship/viewRefunds?reshipId='+reshipDTO.returnId+'">查看</a>');
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