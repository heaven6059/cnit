var total,rows,checkArry;
$(function (){
	loadContent({refresh:true});
	
	$('.com-table-main').on('click','.reply-submit.addConsult',function(){
		if($(this).parent().prev().val().trim()){
			var description = $(this).parent().prev('textarea').val();
			var parentId = $(this).parent().prevAll('input.parentId').val();
			var requirementId = $(this).parent().prevAll('input.requirementId').val();
			response(parentId,requirementId,description);
		}else{
			layer.msg('请输入追评！');
		}
	});
	$('.com-table-main').on('click','.reply-submit.cancle',function(){
		$(this).parent().prev().val('');
		$(this).closest('.reply-textarea.J-reply-con').hide();
	});
	$('.com-table-main').on('click','.showContent',function(){
		$(this).next().find('.reply-textarea.J-reply-con').show();
	});
	
});

/**
 * 回复需求
 */
function response(parentId,postRequirementId,description){
	$.ajax({
		url : yoyo.shopUrl + '/requirement/response',
		data :  { 
			parentId: parentId, 
			description: description, 
			postRequirementId: postRequirementId,
		},
		type : "post",
		success : function(data) {
			if (data.head.retCode == '000000') {
				loadContent({refresh:true});
			}else {
				$.messager.alert('错误', '操作失败！', 'error');
			}
		}
	 });
}

function loadContent(opts){
	$.ajax({
	    url:yoyo.shopUrl+'/requirement/getList',
	    data:{page:$("#page-num").val()},
	    type:'post',
	    cache:false,
	    success:function(data) {
	    	var content=JSON.parse(data.content);
	    	if(data.head.retCode == yoyo.successCode){
	    		$(".com-table-main div").remove();
	    		$.each(content.rows,function (index,content){
	    			$(".com-table-main").append(buildHtml(content));
	    		});
	    	}
	    	if(opts && opts.refresh){
				var opt = {
						items_per_page:content.pageSize,
						callback:function (){
	    					loadMemberConsult({refresh:false});
	    				}
    				};
	    		$("#Pagination").yoyoPagination(content.total,opt);
	    	}
	     }
	});
}

function buildHtml(content){
	var html = '<div class="comment-item">';
	$.each(content.contentList, function(index, r_content) {
		//判断是咨询还是回复
		if(/^1\d{2}$/.test(r_content.accountType)){
			html += '<div class="reply-item">'+
						'<div class="reply-infor-one">';
							if(r_content.disabled){
								html += '<span class="words">**************</span>';
							}else{
								html += '<span class="words">'+ r_content.description+'</span><br/>';
							}
							html += '<span>'+ new Date(r_content.createTime).format("yyyy-MM-dd hh:mm:ss")+'</span>'+
						'</div>'+
					'</div>';
		}else if(/^2\d{2}$/.test(r_content.accountType)){
			html += '<div class="reply-item">'+
						'<div class="reply-infor-two">'+
							'<span class="user-name">客服回复：</span>'+
							'<span class="words">'+ r_content.description+'</span><br/>'+
							'<span>'+ new Date(r_content.createTime).format("yyyy-MM-dd hh:mm:ss")+'</span>'+
						'</div>'+
					'</div>';
		}
	});
	html += '<button class="showContent" type="button" >追加评论</button>';
	var i = content.contentList.length-1;
	html +=		'<div class="reply-item">'+
					'<div class="reply-textarea J-reply-con" >'+
						'<div class="reply-arrow">'+
							'<b class="layer1"></b>'+
							'<b class="layer2"></b>'+
						'</div>'+
					'<div class="inner">'+
						'<input class="parentId" type="hidden" value="'+ content.contentList[i].id +'"/>'+
						'<input class="requirementId" type="hidden" value="'+ content.contentList[i].postRequirementId +'"/>'+
						'<textarea placeholder="请输入您的追评：" id="reply-input-42" name="comment" class="reply-input"></textarea>'+
						'<div class="btnbox">'+
							'<button class="reply-submit cancle" type="button">取消</button>&nbsp;'+
							'<button class="reply-submit addConsult" type="button">提交</button>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>';
	return html;
}
