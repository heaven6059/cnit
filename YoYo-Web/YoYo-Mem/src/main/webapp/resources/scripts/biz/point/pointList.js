$(document).ready(function(){
	loadComplainList({refresh:true});
});

function isEmpty(object){
	if(object == null || object)
	return 
}

function loadComplainList(option){
	$("#tableId tr:not(:first)").remove();
	$.ajax({
		url:'../point/getPointList',
		type:"post",
		data:{pageNum:$("#page-num").val(),pageSize:10},
		dataType:"json",
		async: false,
		success:function(returnData){
		  if(returnData.content.total > 0){		
				var html = ['<tbody>'];
				 $.each(returnData.content.rows,function(index,pointHistory){
					 html.push('<tr>');
					 html.push('<td align="center">'+(new Date(pointHistory.createtime)).format("yyyy-MM-dd hh:mm:ss")+'</td>');					 
					 html.push('<td align="center">'+(pointHistory.inPoint>0?pointHistory.inPoint:0)+'</td>');
					 html.push('<td align="center" style="color: #F60" >'+(pointHistory.outPoint>0?pointHistory.outPoint:0)+'</td>');
					 html.push('<td align="center">'+pointHistory.remainPoint+'</td>');
					 html.push('<td align="center">'+(pointHistory.remark!=null?pointHistory.remark:"")+'</td>');
					 html.push('</tr>');
				 });
				 if(option&&option.refresh){
		    			var opt = {
		    				items_per_page:returnData.content.pageSize,
		    				callback:function (){
		    					loadComplainList({refresh:false});
		    				}
	 				};
		    			$("#Pagination").yoyoPagination(returnData.content.total,opt);
		    	}
				html.push("</tbody>");
				$("#tableId").append(html.join(""));
		  	}
		}
	});
}