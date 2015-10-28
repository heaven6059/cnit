$(document).ready(function(){
	loadDataList({refresh:true});
});

function loadDataList(opts){
	$.ajax({
		url:yoyo.shopUrl+'/lowPriceConsult/loadLowPriceConsult',
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
				 var isReplace=lowPrice.isReplace==1?"是":"否";
				 var replaceCar=lowPrice.isReplace==1?lowPrice.replaceCar:"-";
				 var replaceDept=lowPrice.isReplace==1?lowPrice.replaceDept:"-";
				 var replaceBrand=lowPrice.isReplace==1?lowPrice.replaceBrand:"-";
				 var carMiles=lowPrice.isReplace==1?lowPrice.carMiles:"-";
				 var cardYear=lowPrice.isReplace==1?lowPrice.cardYear:"-";
				 html.push('<tr>');
				 html.push('<td align="center">'+lowPrice.carName+'</td>');
				 html.push('<td align="center">'+lowPrice.userName+'</td>');
				 html.push('<td align="center">'+userSex+'</td>');
				 html.push('<td align="center">'+lowPrice.phone+'</td>');
				 html.push('<td align="center">'+lowPrice.province+" - "+lowPrice.city+" - "+lowPrice.county+'</td>');
				 html.push('<td align="center">'+new Date(lowPrice.addTime).format("yyyy-MM-dd hh:mm:ss")+'</td>');
				 html.push('<td align="center">'+isReplace+'</td>');
				 html.push('<td align="center">'+replaceCar+'</td>');
				 html.push('<td align="center">'+replaceDept+'</td>');
				 html.push('<td align="center">'+replaceBrand+'</td>');
				 html.push('<td align="center">'+carMiles+'</td>');
				 html.push('<td align="center">'+cardYear+'</td>');
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
