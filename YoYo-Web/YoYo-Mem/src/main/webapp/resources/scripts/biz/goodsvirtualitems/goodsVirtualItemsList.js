$(document).ready(function(){
	loadDataList();
});

function loadDataList(){
	$("#tableId tr:not(:first)").remove();
	$.ajax({
		url:'../goodsVirtualItems/getGoodsVirtualItemsList',
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
		  if(returnData.total > 0){
			 var html = '<tbody>';
			 $.each(returnData.rows,function(index,goodsVirtualItems){
				 html += '<tr>';
				 html += ' <td align="center">'+goodsVirtualItems.orderId+'</td>';
				 if(goodsVirtualItems.goodsName == null  ){
					 html += ' <td align="center">&nbsp;</td>';
				 }else{
					 html += ' <td align="center">'+goodsVirtualItems.goodsName+'</td>';
				 }
				 if(goodsVirtualItems.cardId == null  ){
					 html += ' <td align="center">&nbsp;</td>';
				 }else{
					 html += ' <td align="center" style="color: #F60" >'+goodsVirtualItems.cardId+'</td>';
				 }
				 if(goodsVirtualItems.cardPsw == null  ){
					 html += ' <td align="center">&nbsp;</td>';
				 }else{
					 html += ' <td align="center">'+goodsVirtualItems.cardPsw+'</td>';
				 }
				 if(goodsVirtualItems.isOverdue == 'true'  ){
					 html += ' <td align="center">无过期</td>';
				 }else{
					 html += ' <td align="center">'+(new Date(goodsVirtualItems.sendTime)).Format("yyyy-MM-dd HH:mm:ss")+'</td>';
				 }				 
				 html += '</tr>';
			 });
			html += "</tbody>";
			$("#tableId").append(html);
		  }
		},
		error:function(){
		},
	});
}