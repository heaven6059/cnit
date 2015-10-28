$(document).ready(function(){	
	loadComplainList({refresh:true});
});

function search(){
	var begin = new Date($('#p_startDate').val().replace(/-/g, "/"));
	var end = new Date($('#p_endDate').val().replace(/-/g, "/"));
	if(!compareDate(begin,end)){
		layer.alert("开始时间不能大于结束时间！",{title:false,closeBtn:false,icon:0});
		return;
	}
	loadComplainList();
}

function resetForm(){
	$('#formId')[0].reset();
	loadComplainList();
}

function loadComplainList(opts){	
	$.ajax({
		url:'../complain/complainData',
		data:{
			page:$("#page-num").val(),
			rows:20,
	    	orderId:$.trim($('#p_orderId').val()), 
	    	complainId:$.trim($('#p_complainId').val()),    
	    	reason:$.trim($('#p_reason').val()),    
	    	status:$.trim($('#p_status').val()),
	    	startDate:$.trim($('#p_startDate').val()),
	    	endDate:$.trim($('#p_endDate').val())
		},
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			$("#tableId tr:not(:first)").remove();
			 var html = [];
			 $.each(returnData.rows,function(index,complain){
				 html.push('<tr>');
				 html.push('<td valign="middle">'+complain.complainId+'</td>');
				 if(/^333/.test(complain.orderId)){
					 html.push('<td valign="middle"><span><a target="_blank" class="btn-bj-hover operate-btn" href = "../paintingManager/viewOrder?orderId='+complain.orderId+'" >'+complain.orderId+'</a></span></td>');
				 }else{
					 html.push('<td valign="middle"><span><a target="_blank" class="btn-bj-hover operate-btn" href = "../shopOrder/viewOrder?orderid='+complain.orderId+'" >'+complain.orderId+'</a></span></td>');
				 }
				 html.push('<td valign="middle">'+complain.storeName+'</td>');
				 html.push('<td valign="middle">'+buildreason(complain)+'</td>');
				 html.push('<td valign="middle">'+buildstatus(complain)+'</td>');
				 html.push('<td valign="middle">'+new Date(complain.createtime).format("yyyy-MM-dd hh:mm:ss")+'</td>');				 
				 html.push('<td valign="middle"><span><a target="_blank" class="btn-bj-hover operate-btn" href = "../complain/complainDetail/?complainId='+complain.complainId+'" >查看详情</a></span></td>');
				 html.push('</tr>');
			 });
			 if(opts&&opts.refresh){
	   			var opt = {
	   				items_per_page:returnData.pageSize,
	   				callback:function (){
	   					loadComplainList({refresh:false});
	   				}
				};
	   			$("#Pagination").yoyoPagination(returnData.total,opt);
	   		}
			$("#tableId").append(html.join(""));
		}
	});
}

function buildreason(complain){
	if(complain.reason == 'after' ){
		 return '售后问题';
	 }else if(complain.reason == 'action'){
		 return '行为违规';
	 }else if(complain.reason == 'quality'){
		 return '质量问题';
	 }else{
		 return "";
	 }
}

function buildstatus(complain){
	if(complain.status == 'intervene' ){
		 return '平台介入';
	 }else if(complain.status == 'success'){
		 return '投诉成立';
	 }else if(complain.status == 'error' ){
		 return '投诉不成立';
	 }else if(complain.status == 'cancel'){
		 return '投诉撤销';
	 }else{
		 return "";
	 }
}