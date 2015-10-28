var type=4;//类型：1、积分换购2、团购3、秒杀4、限时抢购5、捆绑活动
$(function() {
	loadDataList({refresh:true});
});

function loadDataList(opts){
	var html=[];
	$("#tableId tbody").remove();
	$.ajax({
		url:'../activityApply/loadScoreBuyApplyList?type='+type+'',
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			   $.each(returnData.content.rows,function(index,vo){
					   html.push('<tr>');
	            		var actOpen = '';
	            		if(vo.actOpen==1) {
	            			actOpen = "开启";
	    				}else{
	    					actOpen = "关闭";
	    				}
	            		var status='',optHtml='<a href="javascript:detail('+vo.id+')">查看详情</a>';
	            		if(vo.status==1){
	            			status='待审核';
	            			optHtml='<a href="javascript:closeApplyActivity('+vo.id+')">退出活动</a>|<a href="javascript:detail('+vo.id+')">查看详情</a>';
	            		}else if(vo.status==2){
	            			status="审核通过";
	            		}else if(vo.status==3){
	            			status="审核未通过";
	            		}
	            		html.push('<td>'+vo.actName+'</td><td>'+vo.goodsName+'</td><td>'+vo.price+'</td><td>'+status+'</td><td>'+actOpen+'</td><td>'+vo.remark+'</td>');
	            		
	            		html.push('<td>'+optHtml+'</td><br>');
		            	html.push("</tr>");	 
			   });
			   $('#tableId').append(html.join(""));
			   if(opts&&opts.refresh){
		   			var opt = {
		   				items_per_page:returnData.pageSize,
		   				callback:function (){
		   					loadDataList({refresh:false});
		   				}
					};
		   			$("#Pagination").yoyoPagination(returnData.total,opt);
		   		}
		  },
		error:function(){
			alert("error")
		},
	});
}
function closeApplyActivity(id){
	var url= _path + '/activityApply/closeApplyActivity?id='+id;
	$.ajax({
	    url:url,
	    type:"post",
	    dataType:"json",
	    success:function(_data){
	    	if (_data.head.retCode == '000000') {
    			easyuiMsg('提示', "您已成功退出该活动！");
    			loadDataList({refresh:true});
    		}else{
    			easyuiMsg('错误', "删除失败！");
    		}
	    }
	});
	
}
function detail(id){
	window.location.href = _path + '/activityApply/loadScoreBuyApplyDetail?id='+id;
}
//限时抢购
function openCheckingGoods(obj){
	$(".Plate").find('a').attr('class','active'); 
	$(obj).attr('class','active cur_active'); 
	type=4;
	loadDataList({refresh:true});
}
//团购
function openLocationGoods(obj){
	$(".Plate").find('a').attr('class','active');
	$(obj).attr('class','active cur_active'); 
	type=2;
	loadDataList({refresh:true});
}
//秒杀
function openPutawayGoods(obj){
	$(".Plate").find('a').attr('class','active');
	$(obj).attr('class','active cur_active'); 
	type=3;
	loadDataList({refresh:true});
}
//积分换购
function openDealingGoods(obj){
	$(".Plate").find('a').attr('class','active');
	$(obj).attr('class','active cur_active'); 
	type=1;
	loadDataList({refresh:true});
}
//捆绑活动
function openWarningGoods(obj){
	$(".Plate").find('a').attr('class','active');
	$(obj).attr('class','active cur_active'); 
	type=5;
	loadDataList({refresh:true});
}