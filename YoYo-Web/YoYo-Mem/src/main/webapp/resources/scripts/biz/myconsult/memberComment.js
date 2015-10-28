var total,rows,checkArry;
$(function (){
	loadMemberComment({refresh:true});
	
	$(".com-table-main").on("click",".view_reply",function (){
		var target=$(this).closest(".comments-item").find(".comment-replylist");
		var clickTarget=$(this);
		$.ajax({
		    url:'../memberComment/loadMemberCommentReply',
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
		     },    
		     error : function() {    
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


function loadMemberComment(opts){	
	$.ajax({
	    url:'../memberComment/loadMemberComment',
	    data:{page:$("#page-num").val(),rows:10},
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	var content=data.content;
	    	if(data.retCode==0000){
	    		$(".com-table-main div").remove();
	    		$.each(content.rows,function (x,content){
	    			$(".com-table-main").append(buildMemberCommentHTML(content));
	    		});
	    	}
	    	if(opts&&opts.refresh){	    		
	    		$("#Pagination").yoyoPagination(content.total,{
	    			items_per_page:content.pageSize,
	    			callback:function(){
	    				loadMemberComment({refresh:false});
	    			}
	    		});
	    	}
	     },    
	     error : function() {    
	     }
	});
}

function buildMemberCommentHTML(content){
	var time=new Date(content.createTime),productName="",productUrl="";
	if(content.productName){
		productName=content.productName;
	}
	if(content.goodsId){
		productUrl=yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+content.goodsId;
	}
	var memberCommentHTML=
		'<div class="comments-item">'+
			'<table class="com-item-main clearfix">'+
				'<tr>'+
					'<td class="com-i-column column1"><div class="p-comment"><span class="desc">'+content.comment+'</span></div></td>';
	//如果是钣金订单则评论商品改为订单号
	if(((content.orderId && content.orderId.toString()).indexOf('333'))>-1){
		memberCommentHTML += '<td class="com-i-column column2"><a target="_blank" href="../mypainting/viewOrder?orderId='+ content.orderId +' ">钣金订单：'+ content.orderId +'</a></td>';
	}else{
		memberCommentHTML += '<td class="com-i-column column2"><a target="_blank" href="'+productUrl+'">'+productName+'</a></td>';
	}
	memberCommentHTML += '<td class="com-i-column column3">'+time.format("yyyy-MM-dd hh:mm:ss")+'</td>'+
				'</tr>'+
			'</table>'+
		'<div class="comment-replylist">';
		memberCommentHTML+=buildReplyHTML(content.replyComment);
		memberCommentHTML+='</div>'+
		'</div>';
	return memberCommentHTML;
}


function buildReplyHTML(content){
	var replyHTML=[];
	$.each(content,function (x,content){
		var time=new Date(content.createTime);
		replyHTML.push('<div class="comment-reply-item">');
		replyHTML.push('<div class="reply-infor">');
		if(content.commentsType=='0'){
			replyHTML.push('<span class="user-name">卖家解释：</span>');
		}else if(content.commentsType=='2'){
			replyHTML.push('<span class="user-name">买家回复：</span>');	
		}
		replyHTML.push('<span class="words">'+content.comment+'</span>');
		replyHTML.push('<span class="time">&#12288;'+time.format("yyyy-MM-dd hh:mm:ss")+'</span>');
		replyHTML.push('</div>');
		replyHTML.push('</div>');
	});
	return replyHTML.join("");
}