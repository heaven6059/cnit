/**
 * 功能描述：咨询评论>咨询列表JS
 * 作者：王鹏
 * 创建时间：2015-04-22
 * 需引用 comment.js 
 */
$(function(){	
	$(".comment-display").on("click",function (){
		var display=$(this).attr("data-display");
		var commentId=$(this).attr("data-commentId");
		var reloadMain=$(this).attr("reload-main");
		var curObj=$(this);
		var op={
				commentId:commentId,
				display:display,
				callback:function (){
					if(display==1){
						curObj.html("[关闭显示]");
						curObj.attr("data-display","0");
					}else{
						curObj.html("[显示到商品]");
						curObj.attr("data-display","1");
					}
					if(reloadMain){
						refreshComment();
					}
				}
			}
		openOrCloseAsk(op);
	});
	
	$(".comment-del").click(function (){
		var commentId=$(this).attr("data-commentId"),close=$(this).attr("close"),curObj=$(this),op={
			commentId:commentId,
			callback:function (){
				curObj.parent().parent().parent().remove();
				if(close){
					$("#commentDetail").dialog('close');
				}
			}
		};
		delAsk(op);
	});
});