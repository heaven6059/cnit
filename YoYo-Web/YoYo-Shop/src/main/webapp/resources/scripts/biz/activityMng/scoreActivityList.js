var type=4;//类型：1、积分换购2、团购3、秒杀4、限时抢购5、捆绑活动
$(function() {
	var op={refresh:true};
	loadDataList(op);
});

function loadDataList(opts){
	var html=[];
	$("#tableId tbody").remove();
	$.ajax({
		url:'../activityApply/loadScoreBuyActivity?type='+type+'',
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			   $.each(returnData.content.rows,function(index,activity){
				   if(activity.actOpen == 1){
					   html.push('<tr>');
	            		var status = '';
	            		if(activity.actOpen==1) {
	            			status = "开启";
	    				}else{
	    					status = "关闭";
	    				}
	            		var startTime = null;
	            		var endTime = null;
	            		if(null != activity.startTime && activity.startTime != '') {
	            			startTime = new Date(activity.startTime).format("yyyy-MM-dd hh:mm:ss");
	            		}else {
	            			startTime = '';
	            		}
	            		if(null != activity.endTime && activity.endTime != '') {
	            			endTime = new Date(activity.endTime).format("yyyy-MM-dd hh:mm:ss");
	            		}else {
	            			endTime = '';
	            		}
	            		var actName = '';
	            		actName=activity.name;
	            		var limitNum=activity.limitNum;
	            		html.push('<td>'+actName+'</td><td>'+startTime+'</td><td>'+endTime+'</td><td>'+status+'</td>');
	            		html.push('<td><a href="../activityApply/addScoreActivity/'+type+'/'+limitNum+'/'+actName+'/'+startTime+'/'+endTime+'/'+activity.actId+'" ">申请参加</a></td><br>');
		            	html.push("</tr>");	 
				   }
            		          
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

function applyActivity(startTime,actName,endTime){
	window.location.href = _path + '/activityApply/addScoreActivity?name='+actName+'&startTime='+startTime+'&endTime='+endTime;
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
