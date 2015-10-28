var settle = {};

settle.listBaseUrl=yoyo.shopUrl+'/settle/getList';
settle.totalBaseUrl=yoyo.shopUrl+'/settle/getTotal';
settle.currentDatagrid="paylog";
/**
 * 页面初始化
 */
$(document).ready(function(){
	settle.loadDataGrid();
	settle.loadDataGridDetail({refresh:true});
});

settle.loadDataGrid = function(){
	$.ajax({
		url: settle.queryBy(settle.totalBaseUrl),
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			if(returnData.rows && returnData.rows.length>0){
				if(returnData.rows[0].totalAmount ){
					$("#totalAmount").html(returnData.rows[0].totalAmount);
				}
				if(returnData.rows[0].finalAmount){
					$("#finalAmount").html(returnData.rows[0].totalFee);
				}
				if(returnData.rows[0].refundsFeel){
					$("#refundsFeel").html(returnData.rows[0].refundsFeel);
				}
				if(returnData.rows[0].orderId){
					$("#orderTotal").html(returnData.rows[0].orderId);
				}
				if(returnData.rows[0].nums){
					$("#itemTotal").html(returnData.rows[0].nums);
				}
			}else{
				$("#totalAmount").html("0.00");
				$("#finalAmount").html("0.00");
				$("#refundsFeel").html("0.00");
				$("#orderTotal").html("0");
				$("#itemTotal").html("0");
			}
			
		},
		error:function(){
		},
	});
};
settle.loadDataGridDetail = function(opts){
	$.ajax({
		url: settle.queryBy(settle.listBaseUrl),
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			 var html = [];
			 $("#reportTableList tr:not(:first)").remove();
			 if(returnData && returnData.rows && returnData.rows!='undefined'){
				 $.each(returnData.rows,function(index,dtl){				 
					 html.push('<tr>');
					 html.push('<td align="center">'+dtl.orderId+'</td>');
					 html.push('<td align="center">'+dtl.totalAmount+'</td>');
					 html.push('<td align="center">'+dtl.totalFee+'</td>');
					 html.push('<td align="center">'+dtl.nums+'</td>');
					 html.push('<td align="center">'+dtl.name+'</td>');
					 if(dtl.refundsFeel){
						 html.push('<td align="center">'+dtl.refundsFeel+'</td>');
					 }else{
						 html.push('<td align="center">0.00</td>');
					 }
					 html.push('<td align="center"><a class="btn-bj-hover operate-btn" href="'+yoyo.shopUrl+'/shopOrder/viewOrder?orderid='+dtl.orderId+'" >查看详情</a></td>');
					 html.push('</tr>');
				 });
				 if(opts&&opts.refresh){
		   			var opt = {
		   				items_per_page:returnData.pageSize,
		   				callback:function (){
		   					settle.loadDataGridDetail({refresh:false});
		   				}
					};
		   			$("#Pagination").yoyoPagination(returnData.total,opt);
		   		}
				$("#reportTableList").append(html.join(""));
			 }
		},
		error:function(){
		},
	});
};
settle.resetForm = function(){
	$('#formId')[0].reset();  
};
settle.search = function(){
	settle.loadDataGrid();
	settle.loadDataGridDetail({refresh:true});
};
settle.selectDate = function(type){
	var oDate = new Date();
	var a = oDate.getDay();//今天周几
	if(type=='work'){
		oDate.setDate(oDate.getDate() - 7);
		$("#startDate").val(oDate.format("yyyy-MM-dd"));
	}else if(type=='month'){
		$("#startDate").val(yoyo.getPreMonthDate());
	}else if(type=='yeah'){
		$("#startDate").val(yoyo.getSexMonthDate());
	}
	$("#endDate").val(new Date().format("yyyy-MM-dd"));
};
settle.queryBy= function(url) {
	var sbUrl=[];
	sbUrl.push(url);
	var orderId=$("#orderId").val();
	var startCreateTimes = $('#startDate').val();
	var endCreateTimes = $('#endDate').val(); 
	if($.trim(orderId) && $.trim(orderId)!="null"){
		sbUrl.push("&orderId="+$.trim(orderId));
	}
	if($.trim(startCreateTimes) && $.trim(startCreateTimes)!="null"){
		sbUrl.push("&startDate="+$.trim(startCreateTimes));
	}
	if($.trim(endCreateTimes) && $.trim(endCreateTimes)!="null"){
		sbUrl.push("&endDate="+$.trim(endCreateTimes));
	}
	var returnUrl=sbUrl.join("");
	if(returnUrl.indexOf("?")==-1){
		returnUrl=returnUrl.replace("&","?");
	}
  	return returnUrl;
}
