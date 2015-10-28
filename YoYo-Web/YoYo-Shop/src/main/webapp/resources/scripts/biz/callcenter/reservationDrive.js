$(document).ready(function(){
	loadDataList({refresh:true});
});


function loadDataList(opts){
	$.ajax({
		url:yoyo.shopUrl+'/reservationDrive/loadReservationDrive',
		data:{
			page:$("#page-num").val(),
			rows:20			
		},
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			 var html = [];
			 $(".gridlist tr:gt(0)").remove();
			 $.each(returnData.rows,function(index,lowPrice){
				 var userSex=lowPrice.userSex==1?"男":"女";
				 html.push('<tr>');
				 html.push('<td align="center">'+lowPrice.carName+'</td>');
				 html.push('<td align="center">'+lowPrice.userName+'</td>');
				 html.push('<td align="center">'+userSex+'</td>');
				 html.push('<td align="center">'+lowPrice.phone+'</td>');
				 html.push('<td align="center">'+lowPrice.province+" - "+lowPrice.city+" - "+lowPrice.county+'</td>');
				 html.push('<td align="center">'+new Date(lowPrice.addTime).format("yyyy-MM-dd hh:mm:ss")+'</td>');
				 html.push('</tr>');
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
			$(".gridlist").append(html.join(""));
		}
	});
}
