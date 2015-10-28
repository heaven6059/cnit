/**
 * 优惠券列表js
 */
$(document).ready(function(){
	loadInitDataGrid({refresh:true});
});
function loadInitDataGrid(opts){
	var type=$("#type").val();
	$.ajax({
		url: _path + '/coupons/couponsListJson?type='+type+'',
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			 var html = [];
			 if(returnData){
				 $.each(returnData,function(index,dtl){
					 html.push('<tr>');
					 html.push('<td align="center">'+dtl.cpnsPrefix+'</td>');
					 html.push('<td align="center">'+dtl.cpnsName+'</td>');
					 if(dtl.cpnsType=='0'){
						 html.push('<td align="center">一张无限使用</td>');
					 }else{
						 html.push('<td align="center">多张使用一次</td>');
					 }
					 html.push('<td align="center">'+dtl.cpnsGenQuantity+'</td>');
					 if(dtl.issueType=='0'){
						 html.push('<td align="center">平台</td>');
					 }else{
						 html.push('<td align="center">店铺</td>');
					 }
					 if(dtl.cpnsStatus=='0'){
						 html.push('<td align="center">禁用</td>');
					 }else{
						 html.push('<td align="center">已启用</td>');
					 }
					 html.push('<td align="center">'+dtl.onlineQuantity+'</td>');
					 html.push('<td align="center">'+dtl.onlineGenQuantity+'</td>');
					 html.push('<td align="center">'+dtl.onlineLimit+'</td>');
					 if(dtl.issueType=='1'){
						 html.push('<td align="center">');
						 if(dtl.cpnsStatus=='0'){
							 html.push('<a class="delete_addr" href="'+yoyo.shopUrl+'/coupons/editCoupons?couponId='+dtl.cpnsId+'" class="btn-bj-hover operate-btn">编辑|</a>');
							 html.push('<a class="delete_addr" href="#" onclick="enableCoupons('+dtl.cpnsId+','+dtl.ruleId+')" class="btn-bj-hover operate-btn"> 启用|</a>');
							 html.push('<a class="delete_addr" href="#" onclick="deleteCoupons('+dtl.cpnsId+','+dtl.ruleId+')" class="btn-bj-hover operate-btn"> 删除</a>');
						 }else{
							 html.push('<a class="delete_addr" href="'+yoyo.shopUrl+'/coupons/editCoupons?couponId='+dtl.cpnsId+'" class="btn-bj-hover operate-btn">查看详情|</a>');
							 html.push('<a class="delete_addr" href="#" onclick="disableCoupons('+dtl.cpnsId+','+dtl.ruleId+')" class="btn-bj-hover operate-btn"> 禁用</a>');
						 }
						 html.push('</td>');
					 }else{
						 html.push('<td align="center"></td>');
					 }
					 html.push('</tr>');
				 });
				 if(opts&&opts.refresh){
		   			var opt = {
		   				items_per_page:returnData.pageSize,
		   				callback:function (){
		   					loadInitDataGrid({refresh:false});
		   				}
					};
		   			$("#Pagination").yoyoPagination(returnData.total,opt);
		   		}
				$("#tableList").append(html.join(""));
			 }
		},
		error:function(){
		},
	});
}
/**删除优惠券*/
function deleteCoupons(couponId,ruleId){
	$.messager.confirm('提示', '确定删除？', function(r) {
		if (r) {
			$.ajax({ url : _path + '/coupons/deleteCoupons?couponId='+couponId+'&ruleId='+ruleId ,type : "post" ,  datatype : "json" , success : function(data) {
				window.location.reload();
			} });
		}
	});
}
function enableCoupons(couponId,ruleId){
	$.messager.confirm('提示', '确定启用？', function(r) {
		if (r) {
			$.ajax({ url : _path + '/coupons/optCoupons?opt=enable&couponId='+couponId+'&ruleId='+ruleId ,type : "post" ,  datatype : "json" , success : function(data) {
				window.location.reload();
			} });
		}
	});
}
function disableCoupons(couponId,ruleId){
	$.messager.confirm('提示', '确定禁用？', function(r) {
		if (r) {
			$.ajax({ url : _path + '/coupons/optCoupons?opt=disable&couponId='+couponId+'&ruleId='+ruleId ,type : "post" ,  datatype : "json" , success : function(data) {
				window.location.reload();
			} });
		}
	});
}