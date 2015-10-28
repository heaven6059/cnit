$(function() {
	var op={
			refresh:true
		};
	loadDataList(op);

});


function loadDataList(opts){
	var html=[];
	
	$("#tableId tbody").remove();
	$.ajax({
		url:'../fullReduce/getFullReduceList',
		type:"post",
		dataType:"json",
		async: false,
		success:function(returnData){
			   $.each(returnData.rows,function(index,activity){
            		html.push('<tr>');
            		var status = '';
            		if(activity.actStatus==1) {
            			status = "待审核";
    				}else if(activity.actStatus==2) {
    					status = "审核通过";
    				}else {
    					status = "审核不通过";
    				}
            		var member = '';
            		var memberArray=[];
            		memberArray = activity.memberLvIds.split(",");
            		for(var i=0;i<memberArray.length;i++) {
            			if(memberArray[i]==1) {
                			member += "初级会员,";
        				}else if(memberArray[i]==2) {
        					member += "中级会员,";
        				}else if(memberArray[i]==3) {
        					member += "高级会员,";
        				}else if(memberArray[i]==4) {
        					member += "顶级会员,";
        				}else if(memberArray[i]==5) {
        					member += "骨灰初级,";
        				}else if(memberArray[i]==6) {
        					member += "骨灰高级,";
        				}else if(memberArray[i]==7) {
        					member += "骨灰顶级,";
        				}else {
        					member += "菜鸟等级,";
        				}
            		}
            		
            		html.push('<td>'+activity.name+'</td><td>'+status+'</td><td>'+activity.conditions+
            				'</td><td>'+member+'</td><td>'+activity.startTime.substring(0,10)+'~'+activity.endTime.substring(0,10)+'</td>');
            		html.push('<td><a href="../fullReduce/editActivity/'+activity.actId+'" >编辑</a>|<a href="javascript:void(0);" onclick ="deleteActivity('+activity.actId+","+activity.ruleId+')">删除</a></td><br>');
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
		},
	});
}

function addBtn(){
	window.location.href = _path + '/fullReduce/addActivityRule';
}

function deleteActivity(actId,ruleId) {
	$.messager.confirm('提示', '确定删除？', function(r) {
		if (r) {
			$.ajax({ url : _path + '/fullReduce/deleteFullReduce?actId='+actId+'&ruleId='+ruleId ,type : "post" ,  datatype : "json" , success : function(data) {
				window.location.reload();
			} });
		}
	});
}
