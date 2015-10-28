
$(document).ready(function(){
	loadComplainList();
});

function isEmpty(object){
	if(object == null || object)
	return 
}

function loadComplainList(){
	$("#tableId tr:not(:first)").remove();
	$.ajax({
		url:'../advance/getAdvanceList',
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
		  if(returnData.total > 0){
			var  advance = returnData.rows[0] ;
			var advanceHistorys = advance.advanceHistorys;
			var advance = advance.advance;
			if(advance == null || advance ==''){
				advance = 0 ;
			}
			$("#advance").text('￥'+fmoney(advance,2)+' 元');
			 var html = '<tbody>';
			 $.each(advanceHistorys,function(index,advanceHistory){
				 html += '<tr>';
				 html += ' <td align="center">'+(new Date(advanceHistory.mtime)).Format("yyyy-MM-dd HH:mm:ss") +'</td>';
				 if(advanceHistory.paymethod == null  ){
					 html += ' <td align="center">&nbsp;</td>';
				 }else{
					 html += ' <td align="center">'+advanceHistory.paymethod+'</td>';
				 }
				 if(advanceHistory.importMoney == null  ){
					 html += ' <td align="center">-</td>';
				 }else{
					 html += ' <td align="center" style="color:red" > ￥'+fmoney(advanceHistory.importMoney,2)+'元</td>';
				 }
				 if(advanceHistory.explodeMoney == null  ){
					 html += ' <td align="center">-</td>';
				 }else{
					 html += ' <td align="center"> ￥'+fmoney(advanceHistory.explodeMoney,2)+'元</td>';
				 }
				 if(advanceHistory.memberAdvance == null  ){
					 html += ' <td align="center">&nbsp;</td>';
				 }else{
					 html += ' <td align="center">￥'+fmoney(advanceHistory.memberAdvance,2)+'元</td>';
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