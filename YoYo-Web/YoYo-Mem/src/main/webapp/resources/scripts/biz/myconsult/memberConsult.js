var total,rows,checkArry;
$(function (){
	loadMemberConsult({refresh:true});
	
	$(".com-table-main").on("click",".view_reply",function (){
		var target=$(this).closest(".comments-item").find(".comment-replylist");
		var clickTarget=$(this);
		$.ajax({
		    url:'../memberConsult/loadMemberConsult',
		    data:{commentId:$(this).attr("data-args")},
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	var content=data.content;
		    	if(data.retCode==0000){
		    		target.append(buildReplyHTML(content));
		    		target.fadeIn(200);
		    		clickTarget.html("关&nbsp;闭").removeClass("view_reply").addClass("close_reply");
		    	}		    	
		     }
		});
	});
	
	$(".com-table-main").on("click",".close_reply",function (){
		var target=$(this).closest(".comments-item").find(".comment-replylist");
		$(this).html("查看回复"+"("+$(this).attr("data-count")+")").removeClass("close_reply").addClass("show_reply");
		target.addClass("hide").fadeOut(200);	
	});
	$(".com-table-main").on("click",".show_reply",function (){
		var target=$(this).closest(".comments-item").find(".comment-replylist");
		target.addClass("hide").fadeIn(200);
		$(this).html("关&nbsp;闭").removeClass("show_reply").addClass("close_reply");
	});
});


function loadMemberConsult(opts){
	$.ajax({
	    url:'../memberConsult/loadMemberConsult',
	    data:{page:$("#page-num").val(),rows:10},
	    type:'post',
	    cache:false,
	    dataType:'json',
	    success:function(data) {
	    	var content=data.content;
	    	if(data.retCode==0000){
	    		$(".com-table-main div").remove();
	    		$.each(content.rows,function (x,content){
	    			$(".com-table-main").append(buildMemberConsultHTML(content));
	    		});
	    	}
	    	if(opts&&opts.refresh){
				var opt = {items_per_page:content.pageSize,callback:function (){
	    					loadMemberConsult({refresh:false});
	    				}
    				};
	    		$("#Pagination").yoyoPagination(content.total,opt);
	    	}
	     }
	});
}

function buildMemberConsultHTML(content){
	var time=new Date(),productName="",productUrl="";
	time.setTime(content.time);
	if(content.product){
		if(content.product.name){
			productName=content.product.name;
		}
		if(content.product.goodsId){
			productUrl=yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+content.product.goodsId;
		}
	}
	var memberCommentHTML=
		'<div class="comments-item">'+
			'<table class="com-item-main clearfix">'+
				'<tbody>'+
					'<tr>'+
						'<td class="com-i-column column1" style="width:600px;">'+
							'<div class="p-comment">'+
								'<span class="desc">'+content.comment+'</span>'+
								'&#12288;'+
								'<b class="time">'+time.format("yyyy-MM-dd hh:mm:ss")+'</b>'+
							'</div>'+
						'</td>'+
						'<td class="com-i-column column2" style="padding-left:30px;"><a target="_blank" href="'+productUrl+'">'+productName+'</a></td>'+						
					'</tr>'+
				'</tbody>'+
			'</table>'+
			'<div class="comment-replylist ">';
			$.each(content.replyComment,function (x,reply){
				var time=new Date();
				time.setTime(reply.time);
				memberCommentHTML+=
				'<div class="comment-reply-item">'+
					'<div class="reply-infor">'+
						'<span class="user-name">卖家回复：</span> ：'+ 
						'<span class="words">'+reply.comment+'</span>'+
						'<span class="time">&#12288;'+time.format("yyyy-MM-dd hh:mm:ss")+'</span>'+
					'</div>'+
				'</div>';
			});
			memberCommentHTML+='</div>';
		'</div>';
	return memberCommentHTML;
}
