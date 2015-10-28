var total,rows;
$(function (){
	// 创建分页元素
	try{
		$("#Pagination").yoyoPagination($("#total").val(), {
			items_per_page:$("#rows").val(),
			page_num_id:"page",
			page_num_name:"page",
			current_page:$("#pageIndex").val()-1,
			callback:function (){
				$("#return_product_form").submit();
			}
		});
	}catch(e){
		
	}
});


function agreeReturnProduct(){
	if($.trim($("#agree-content").val()).length>300){
		alert("留言超出300字符限制请修改后再提交");
		return;
	}
	subForm($("#order-delivery-agree-process").serialize());
}

function disAgreeReturnProduct(){
	if($.trim($("#return-content").val()).length>300){
		alert("拒绝原因超出300字符限制请修改后再提交");
		return;
	}
	subForm($("#order-delivery-refuse-process").serialize());
}

function subForm(data){
	 $.ajax({
			url:yoyo.shopUrl+"/returnProductManager/processReturnProduct",
			data:data,
			type:"post",
			dataType:"json",
			async: false,
			success:function(returnData){
				var resultCode = returnData.retCode;
		   		if (resultCode == yoyo.successCode) {
		   			layer.alert("申请处理成功！",{title:false,closeBtn:false,icon:1,end:function (){
		   					if($("#isSafeguard").val()==1){
		   						window.location.href = yoyo.shopUrl+"/orderRefundsManager/toRefundsList";
		   					}else{
		   						window.location.href = yoyo.shopUrl+"/returnProductManager/toReturnProductList";
		   					}
		   				}
		   			});
		   		}else{
		   			layer.alert("申请处理失败！",{title:false,closeBtn:false,icon:0});	
		   		}
			},	
		   	error:function(){
		   		layer.alert("申请提交失败！",{title:false,closeBtn:false,icon:0});
		   	}
	 });
}

function do_agree(){
	$("#order-delivery").hide();
	$("#order-delivery-agree-process").show(300);
}

function do_send(){
	$("#order-delivery").hide();
	$("#order-delivery-refuse-process").show(300);
}

function to_back(obj){
	$("#"+obj).hide();
	$("#order-delivery").show(300);
}