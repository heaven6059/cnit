var total,rows,checkArry;
$(document).ready(function(){
	loadDataList({refresh:true});
	
	$(".Plate>H4>a").click(function (){
		$(this).addClass("current").siblings().removeClass("current");
		loadDataList({refresh:true});
	});
	
	$("#del-all-box").click(function (){	
		if($(this).is(":checked")){
			checkArry = new Array();
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).prop("checked",true);
				checkArry.push($(box).val());
			});
		}else{
			checkArry = [];
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).prop("checked",false);
			});
		}
	});
	
	$(".gridlist").on("click",":checkbox",function (){
		if(typeof(checkArry) == "undefined"){
			checkArry = new Array();
		}
		if($(this).is(":checked")){
			checkArry.push($(this).val());
		}else{
			for(var i=0;i<checkArry.length;i++){
				if($(this).val()==checkArry[i]){
					checkArry.elremove(i);  
				}
			}
		}
	});
	
	$(".trigger-btn").click(function (){
		if(typeof(checkArry) == "undefined"||checkArry.length==0){
			layer_utils.alert('请选择需要设置的消息');
			return;
		}
		updateReadStatus(checkArry.join(","));
	});
});

function updateReadStatus(ids){
	layer_utils.confirm("是否确定设置为已读?",function (){
		$.ajax({
			url:'../memberMsg/updateMsgReadStatus',
			type:"post",
			data:{ids:ids},
			dataType:"json",
			async: false,
			success:function(returnData){
				console.log(returnData.retCode);
				if(returnData.retCode=="000000"){
					layer.msg("设置为已读成功!",{time:2000,success:function(){
						loadDataList({refresh:true});
					}});
				}
			}
		});
	});
}

function buildCondition(){	
	var condition={
			pageNum:$("#page-num").val(),
			pageSize:10,
			hasRead:$(".Plate>H4").find(".current").attr("data-args"),			
		};	
	return condition;
}

function loadDataList(opts){
	$("#tableId tr:not(:first)").remove();
	$.ajax({
		url:'../memberMsg/getMemberMsgList',
		type:"post",
		data:buildCondition(),
		dataType:"json",
		async: false,
		success:function(returnData){
			 var html =[];
			 $.each(returnData.rows,function(index,memberMsg){
				 html.push('<tr>');
				 html.push('<td class="no_bk"><input type="checkbox" value="'+memberMsg.msgId+'"></td>');
				 html.push('<td valign="middle"><a class="btn-bj-hover operate-btn" href = "../memberMsg/getMemberMsgDetailById?msgId='+memberMsg.msgId+'">');
				 if(memberMsg.hasRead == 'true'){
					 html.push(memberMsg.subject);
				 }else{
					 html.push('<font color="red">'+ memberMsg.subject+'</font>');
				 }
				 html.push('</a></td>');			 
				 html.push('<td valign="middle">'+(new Date(memberMsg.createTime)).format("yyyy-MM-dd hh:mm:ss")+'</td>');
				 html.push('<td valign="middle">');
				 html.push('<a class="btn-bj-hover operate-btn" href="../memberMsg/getMemberMsgDetailById?msgId='+memberMsg.msgId+'">查看</a>');
				 if(memberMsg.hasRead != 'true'){
					 html.push('<a class="btn-bj-hover operate-btn" href="javascript:updateReadStatus('+memberMsg.msgId+');">标记为已读</a>');
				 }
				 html.push('</td>');
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
			$("#tableId").append(html.join(""));
		},
		error:function(){
		},
	});
	//$("#formId").submit();
}