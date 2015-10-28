$(document).ready(function(){
	loadDataList({refresh:true});
});

function search(){
	if($('#search_startDate').val()&&$('#search_endDate').val()){
		var begin = new Date($('#search_startDate').val().replace(/-/g, "/"));
		var end = new Date($('#search_endDate').val().replace(/-/g, "/"));
		if(!compareDate(begin,end)){
			layer.alert("开始时间不能大于结束时间！",{title:false,closeBtn:false,icon:0});
			return;
		}	
	}
	loadDataList();
}

function resetForm(){
	$('#formId')[0].reset();
	loadDataList();
}

function loadDataList(opts){
	$.ajax({
		url:yoyo.shopUrl+'/report/reportListData',
		data:{
			page:$("#page-num").val(),
			rows:20,
			productName:$.trim($('#search_productName').val()), 
			reportId:$.trim($('#search_reportId').val()),    
			reason:$.trim($('#search_reason').val()),    
			status:$.trim($('#search_status').val()),
			startDate:$.trim($('#search_startDate').val()),
			endDate:$.trim($('#search_endDate').val())
		},
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			 var html = [];
			 $("#reportTableList tr:not(:first)").remove();
			 $.each(returnData.rows,function(index,reportDTO){				 
				 html.push('<tr>');
				 html.push('<td align="center">'+reportDTO.reportId+'</td>');
				 html.push('<td align="center"><a class="btn-bj-hover operate-btn" href = "'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+reportDTO.goodsId+'" target="_blank">'+reportDTO.name+'</a></td>');
				 html.push('<td align="center">'+reportDTO.storeName+'</td>');
				 html.push('<td align="center">'+buildReason(reportDTO)+'</td>');
				 html.push('<td align="center">'+buildStatus(reportDTO)+'</td>');
				 html.push('<td align="center">'+new Date(reportDTO.createtime).format("yyyy-MM-dd hh:mm:ss")+'</td>');
				 html.push('<td align="center"><a class="btn-bj-hover operate-btn" href = "'+yoyo.shopUrl+'/report/reportDetail?reportId='+reportDTO.reportId+'" >查看详情</a></td>');
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
			$("#reportTableList").append(html.join(""));
		},
		error:function(){
		},
	});
}

function buildStatus(reportDTO){
	if(reportDTO.status == 'intervene' ){
		 return '平台介入';
	 }else if(reportDTO.status == 'voucher'){
		 return '取证中';
	 }else if(reportDTO.status == 'success'){
		 return '举报成立';
	 }else if(reportDTO.status == 'error' ){
		 return '举报不成立';
	 }else if(reportDTO.status == 'cancel'){
		 return '举报撤销';
	 }else if(reportDTO.status == 'finish'){
		 return '完成';
	 }			
}

function buildReason(reportDTO){
	if(reportDTO.reason == 'false' ){
		return '虚假信息';
	}else if(reportDTO.reason == 'unconformity'){
		return '图片不符';
	}
}