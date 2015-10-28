var total,rows;
$(function (){
	initComments({refresh:true});
	$(".gridlist").on("click",".setCommentDisplay",function (){
		var btnObj=$(this);
		$.ajax({
		    url:'../commentsManager/setCommentDisplay',// 跳转到 action    
		    data:{
		    	commentId:$(this).attr("data-commentId"),
		    	display:$(this).attr("data-display")
		    },
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data.retCode==000000){	    		
		    		if(btnObj.attr("data-display")==0){
		    			btnObj.html("显示");
		    			btnObj.attr("data-display","1");
		    			layer.alert("设置成功，前台页面已经关闭显示！",{title:false,closeBtn:false,icon:1});
		    		}else{
		    			btnObj.html("关闭");
		    			btnObj.attr("data-display","0");
		    			layer.alert("设置成功，前台页面已经显示！",{title:false,closeBtn:false,icon:1});
		    		}		    		
		    	}else{
		    		layer.alert("设置评论显示失败！",{title:false,closeBtn:false,icon:0});
		    	}
		     }
		});
	});
});

function initComments(opts){
	$.ajax({    
	    url:'../commentsManager/getCommentsListData',// 跳转到 action    
	    type:'post',
	    data:{rows:10,page:$("#page-num").val()},
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.retCode==000000){
	    		buildCommentsHTML(data.content);
	    	}
	    	if(opts&&opts.refresh){
	    		$("#Pagination").yoyoPagination(data.content.total,{
	    			items_per_page:data.content.pageSize,
	    			callback:function(){
	    				initComments({refresh:false});
	    			}
	    		});
	    	}
	     }
	});
}

function buildCommentsHTML(result){
	var html = [];
	$.each(result.rows,function (x,comment){
		html.push('<tr class="tr-th">');
		html.push('	<td colspan="4">');
		html.push('<span class="tcol1">');
		if(comment.orderId && comment.orderId.toString().startsWith('333')){
			html.push('订单号:<a href="../paintingManager/viewOrder?orderId='+comment.orderId+'">'+comment.orderId+'</a>');
		}else{
			html.push('订单号:<a href="../shopOrder/viewOrder?orderid='+comment.orderId+'">'+comment.orderId+'</a>');
		}
		html.push('</span>');
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr>');						
		html.push('<td class="product" width="60%">');
		html.push('<h3>标题：'+comment.title+'</h3>');
		html.push('<div style="text-indent: 2em;">评论内容：'+comment.comment+'</div>');	
		html.push('</td>');
		html.push('<td width="10%">');
		if(comment.orderId && comment.orderId.toString().startsWith('333')){
			html.push('<label class="col1">\\</label>');
		}else{
			html.push('<label class="col1"><a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+comment.goodsId+'" target="_blank">'+comment.productName+'</a></label>');
		}
		html.push('</td>');
		html.push('<td width="10%">');
		html.push('<label class="col1">');
		html.push(''+comment.loginName+'<br>('+new Date(comment.createTime).format("yyyy-MM-dd hh:mm:ss")+')');
		html.push('</label>');
		html.push('</td>');
		html.push('<td width="10%">');
		html.push('<a href="javascript:void(0);" onclick="openReply('+comment.commentId+')">回复</a>');
		if(!(comment.orderId && comment.orderId.toString().startsWith('333'))){
			html.push('<br>');
			html.push('<a href="javascript:void(0);" class="setCommentDisplay" data-commentId="'+comment.commentId+'" data-display="'+(comment.display==1?0:1)+'">');
			html.push(comment.display==1?"关闭":"显示");
			html.push('</a>');
		}
		html.push('</td>');
		html.push('</tr>');
		html.push('<tr class="noneborder" id="comment_tr_'+comment.commentId +'" style="display: none;">');
		html.push('<td colspan="4">');
		html.push('<div class="reply-textarea J-reply-con">');
		html.push('<div class="reply-arrow">');
		html.push('<b class="layer1"></b><b class="layer2"></b>');
		html.push('</div>');
		html.push('<div class="inner">');
		html.push('<form id="reply_form_'+comment.commentId+'">');
		html.push('<input type="hidden" name="orderId" value="'+comment.orderId+'" />');
		html.push('<input type="hidden" name="forCommentId" value="'+comment.commentId+'" />');
		if(comment.orderItemId){
			html.push('<input type="hidden" name="orderItemId" value="'+comment.orderItemId+'" />');
		}
		if(comment.productId){
			html.push('<input type="hidden" name="productId" value="'+comment.productId+'" />');
		}
		html.push('<input type="hidden" name="toName" value="'+comment.loginName+'" />');
		html.push('<input type="hidden" name="toId" value="'+comment.memberId+'" />');
		html.push('<textarea placeholder="回复:" id="reply-input-'+comment.commentId+'" name="comment" class="reply-input"></textarea>');
		html.push('<div class="btnbox">');
		html.push('<button onclick="doCancelComment('+comment.commentId+')" class="reply-submit" type="button">取消</button>&nbsp;');		
		html.push('<button onclick="doReplyComment('+comment.commentId+')" class="reply-submit" type="button">提交</button>');
		html.push('</div>');
		html.push('</form>');
		html.push('</div>');
		html.push('</div>');
		html.push('</td>');
		html.push('</tr>');
		$.each(comment.replyComment,function (x,replyComment){
			html.push('<tr class="noneborder">');
			html.push('<td colspan="4">');
			html.push('<div class="refer refer_bg">');
			html.push('<dl class="answer">');
			if(replyComment.commentsType=='0'){
			html.push('<dt>卖家回复：('+new Date(replyComment.createTime).format("yyyy-MM-dd hh:mm:ss")+')：</dt>');
			}else if(replyComment.commentsType=='2'){
			html.push('<dt>买家回复：('+new Date(replyComment.createTime).format("yyyy-MM-dd hh:mm:ss")+')：</dt>');
			}
			html.push('<br><dd style="text-indent: 2em;">'+replyComment.comment+'</dd>');
			html.push('</dl>');
			html.push('</div>');
			html.push('</td>');
			html.push('</tr>');
		});
	});
	$(".gridlist tr:gt(0)").remove();
	$(".gridlist").append(html.join(""));
}

function loadImg(){
	$(".gridlist").find("img").each(function (x,img){
		if($(img).attr("data-original")){				
			$(img).attr("src",yoyo.imagesUrl+$(this).attr("data-original"));
		}
	});
}


function openReply(commentId){
	$("#reply-input-"+commentId).val("");
	$("#comment_tr_"+commentId).show(200);
}
function doCancelComment(commentId){
	$("#reply-input-"+commentId).val("");
	$("#comment_tr_"+commentId).hide(200);
}
function doReplyComment(commentId){
	if($.trim($("#reply-input-"+commentId).val())==""){
		layer.alert("请输入解释内容！",{title:false,closeBtn:false,icon:0});
		return;
	}
	if($.trim($("#reply-input-"+commentId).val()).length>300){
		layer.alert("内容超出300字符限制！",{title:false,closeBtn:false,icon:0});
		return;
	}
	$.ajax( {    
	    url:'../commentsManager/doCommentReply',// 跳转到 action    
	    data:$("#reply_form_"+commentId).serialize(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.retCode==000000){
	    		$("#reply-input-"+commentId).val("");
	    		initComments({refresh:false});
	    	}else{
	    		layer.alert("解释信息提交失败！",{title:false,closeBtn:false,icon:0});
	    	}
	     }
	});
	$("#comment_tr_"+commentId).hide(200);
}


/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
    // millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }

    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}