$(document).ready(function(){
	loadComplainList({refresh:true});
});

function isEmpty(object){
	if(object == null || object)
	return 
}

function loadComplainList(opts){
	var type=$("#type").val();
	$.ajax({
		url:'../memberCoupon/getMemberCouponList?type='+type+'',
		type:"post",
		dataType:"json",
		data:{page:$("#page-num").val(),rows:20},
		async: false,
		success:function(returnData){
			$(".gridlist tr:not(:first)").remove();
		  var html=[];
		  $.each(returnData.rows,function(x,coupon){
			  	html.push('<tr lass="itemsList">');
			  	html.push('<td>'+coupon.coupons.cpnsName+'</td>');
			  	html.push('<td>'+new Date(coupon.salesRuleGoods.fromTime).format("yyyy-MM-dd")+'至'+new Date(coupon.salesRuleGoods.toTime).format("yyyy-MM-dd")+'</td>');
			  	html.push('<td><a href="javascript:deleteCoupon('+coupon.memcCode+')">删除</a></td>');
				html.push('</tr>');
		  });
		  if(opts&&opts.refresh){
	    		$("#Pagination").yoyoPagination(returnData.total,{
	    			items_per_page:returnData.pageSize,
	    			callback:function(){
	    				loadComplainList({refresh:false});
	    			}
	    		});
	    	}
		  $(".gridlist").append(html.join(""));
		},
		error:function(){
		},
	});
}

function deleteCoupon(memcCode){
	layer_utils.confirm("是否删除该优惠卷?",function (){
		$.ajax({
		    url:'../memberCoupon/deleteMemberCoupon',
		    data:{memcCode:memcCode},
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(head){
		    	if(head.retCode==yoyo.successCode){		    		
		    		layer.alert("优惠卷删除成功！",{title:false,closeBtn:false,icon:1,end:loadComplainList({refresh:true})});
		    	}else{		    		
		    		layer.alert("优惠卷删除失败！",{title:false,closeBtn:false,icon:0});
		    	}
		    }
		});
	});
}