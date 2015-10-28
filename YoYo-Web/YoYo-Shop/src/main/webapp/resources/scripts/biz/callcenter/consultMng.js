var total,rows,checkArry;
$(function (){
	$(".switchable-triggerBox li").click(function (){
		$(this).addClass("active").siblings().removeClass("active");
		$("#filterReply").val($(this).attr("data"));
		initConsult({refresh:true});
	});
	
	$("#del-all-box").click(function (){
		if($(this).is(":checked")){
			checkArry = new Array();
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).attr("checked",true);
				checkArry.push($(box).val());
			});
		}else{
			checkArry = [];
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).attr("checked",false);
			});
		}
		console.log(checkArry);
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
		console.log(checkArry);
	});
	
	initConsult({refresh:true});
});

Array.prototype.elremove = function(m){  
    if(isNaN(m)||m>this.length){return false;}  
    this.splice(m,1);  
};

function initConsult(opts){
	$.ajax( {    
	    url:'../consultManager/loadConsultList',// 跳转到 action    
	    data:{
	    	page:$("#page-num").val(),
	    	filterReply:$("#filterReply").val()
	    },    
	    type:'post',
	    cache:false,
	    dataType:'json',
	    success:function(data) {
	    	if(data.resultObject.retCode==0000){	
	    		$(".gridlist tr:gt(0)").remove();
	    		var content=data.resultObject.content;
	    		$.each(content.rows,function (x,content){
	    			$(".gridlist").append(initConsultHTML(content));
	    		});
	    		if(opts&&opts.refresh){
		    		$("#Pagination").yoyoPagination(content.total,{
		    			items_per_page:content.pageSize,
		    			callback:function(){
		    				initConsult({refresh:false});
		    			}
		    		});
		    	}
	    	}
	     }
	});
}
function reply(commentId){
	console.log($("#comment_tr_"+commentId));
	$("#comment_tr_"+commentId).show(200);
}

function cancelReplyConsult(commentId){
	$("#comment_tr_"+commentId).hide(200);
	$("#reply-input-"+commentId).val("");
}

function doReplyConsult(commentId){
	if($.trim($("#reply-input-"+commentId).val())==""){
		alert("请输入回复内容");
		return;
	}
	if($.trim($("#reply-input-"+commentId).val()).length>300){
		alert("内容超出300字符限制。");
		return;
	}
	
	$.ajax( {    
	    url:'../consultManager/doReplyComment',// 跳转到 action    
	    data:$("#reply_form_"+commentId).serialize(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.resultObject.retCode==000000){
	    		initConsult({refresh:true});
	    	}else{
	    		alert("回复信息提交失败");
	    	}
	     }
	});
	$("#reply-input-"+commentId).val("");
	$("#comment_tr_"+commentId).hide(200);
}


function initConsultHTML(content){
	/**'<td class="no_bk"><input type="checkbox" value="'+content.commentId+'"></td>'+**/
	var consultHTML='<tr>'+
			'<td class="product">'+	
			'<div">'+content.author+"("+new Date(content.time).format("yyyy-MM-dd hh:mm:ss")+'):</div>'+
				'<div style="text-indent: 2em;">'+content.comment+'</div>'+	
			'</td>'+
			'<td style="text-align:left;"><a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+content.product.goodsId+'" style="" target="_blank">'+content.product.name+'</a></td>'+
			'<td><a href="javascript:void(0);" onclick="reply('+content.commentId+')">回复</a></td>'+
		'</tr>'+
		'<tr class="noneborder" id="comment_tr_'+content.commentId+'" style="display: none;">'+
			'<td colspan="5">'+
				'<div class="reply-textarea J-reply-con">'+
					'<div class="reply-arrow"><b class="layer1"></b><b class="layer2"></b></div>'+
						'<div class="inner">'+
							'<form id="reply_form_'+content.commentId+'">'+							
							'<input type="hidden" name="forCommentId" value="'+content.commentId+'" />'+
							'<input type="hidden" name="typeId" value="0" />'+
							'<input type="hidden" name="productId" value="'+content.product.productId+'" />'+
							'<input type="hidden" name="objectType" value="discuss" />'+
							'<input type="hidden" name="toId" value="'+content.authorId+'" />'+
							'<input type="hidden" name="pIndex" value="1" />'+
							'<textarea placeholder="回复:'+content.author+'" id="reply-input-'+content.commentId+'" name="comment" class="reply-input"></textarea>'+
							'<div class="btnbox">'+
								'<button onclick="cancelReplyConsult('+content.commentId+')" class="reply-submit J-submit-reply J-submit-reply-lz" type="button">取消</button>&nbsp;'+
								'<button onclick="doReplyConsult('+content.commentId+')" class="reply-submit J-submit-reply J-submit-reply-lz" type="button">提交</button>'+
							'</div>'+
							'</form>'+
					'</div>'+
				'</div>'+
			'</td>'+
		'</tr>';
		$.each(content.replyComment,function (x,replay){
			var time=new Date();
			time.setTime(replay.time);
			consultHTML+=
			'<tr class="noneborder">'+
				'<td colspan="5">'+
					'<div class="refer refer_bg">'+
						'<dl class="answer">'+
							'<dt>掌柜回复：('+time.format("yyyy-MM-dd hh:mm:ss")+')</dt><br><dd style="text-indent: 2em;">'+replay.comment+'</dd>'+
						'</dl>'+
					'</div>'+
				'</td>'+
			'</tr>';
		});		
	return consultHTML;
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

