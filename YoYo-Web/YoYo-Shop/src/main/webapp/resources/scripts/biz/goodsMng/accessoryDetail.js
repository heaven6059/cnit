/**
 * 配件适用车型
 */
$(function() {
	// 创建分页元素
	$("#Pagination").yoyoPagination(total, {
		items_per_page:rows,
		callback:function (){
			var op={
				refresh:false
			};
			initPageList(op);
		}
	});
	
});



/**
 * 分页操作
 * @param option
 */
function initPageList(option){
	var regData = {};
	regData.page = $("#page-num").val();
	regData.rows = 10;
	regData.accId = $('#accId').val();
	$.ajax( {    
	    url:'../accessory/getCarsList',// 跳转到 action    
	    data:regData,    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.retCode=='000000'){
	    		$(".gridlist tr:gt(1)").empty();
	    		$.each(data.content.rows,function (x,car){
	    			var html=
					'<tbody>'+
					'<tr>'+						
					'<td class="product_name">'+
						car.factoryName 
					+'</td>'+	
					'<td class="product_name">'+
						car.deptName
					+'</td>'+
					'<td class="product_name">'+
						car.yearValue
					+'</td>'+
					'<td class="product_name">'+
						car.carName
					+'</td>';
					html+='</tr></tbody>';
					$(".gridlist").append(html);
	    		});
	    		if(option&&option.refresh){
	    			var opt = {
	    				items_per_page:data.content.pageSize,
	    				callback:function (){
	    					var op={
	    						refresh:false
	    					};
	    					initPageList(op);
	    				}
    				};
	    			$("#Pagination").yoyoPagination(data.content.total,opt);
	    			
	    		}
	    		
	    	}
	     },  
	     error : function() {
	          alert("异常！");
	     }
	});
}



