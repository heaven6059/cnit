$(function(){
	$('.subside-list .list-item.current').closest('.subside-mod').addClass('on');

	$('.subside-in').on('click','.title',function(){
		$(this).parent().toggleClass('on');
	});
	
	$('ul.tab').find('.list-item').click(function(){
		if(this.id != 'articleAll'){
			$(this).siblings().removeClass('current');
			$(this).addClass('current');

			var nodeId = $(this).find('input:hidden').val();
			getAticleIndex(nodeId);
			var nodeName = $(this).find('.dk-line').text();
			var selectId = $('#selectedId').val();
			if(!$('#sLevel3').text()){
				$('#sLevel2').wrap('<a id="reflash" href="'+ _path +'/article/index?nodeId='+ selectId +'"></a>');
				$('#reflash').after('&nbsp;&gt;&nbsp;<span id="sLevel3">'+nodeName+'</span>');
			}else{
				$('#sLevel3').text(nodeName);
			}
		}
	});
});


/**
 * 获得节点下的文章索引列表
 * @param nodeId
 */
function getAticleIndex(nodeId){
	$.ajax({
		url:_path+'/article/articleIndexList',
		data:{'nodeId':nodeId},
		type:"GET",
		success:function(data){
			if(data.retCode == yoyo.successCode){
				$('ul.help_list').empty();
				if(data.content && data.content.rows){
					var indexList = data.content.rows;
					for(var i=0; i<indexList.length; i++){
						var html = '<li>';
						html += 		'<a href="javascript:viewContent('+ indexList[i].articleId +')">';
						html += 			'<b>·</b>';
						html += 			indexList[i].title;
						html += 		'</a>';
						html += 	'</li>';
						$('ul.help_list').append(html);
					}
				}
			}else{
				layer.msg('对不起，服务器请求失败');
			}
		}
	});
}


/**
 * 查看文章内容
 * @param articleId
 */
function viewContent(articleId){
	$.ajax({
		url:_path+'/article/articleContent',
		data:{'articleId':articleId},
		type:"GET",
		success:function(data){
			debugger
			if(data.retCode == yoyo.successCode){
				$('#all-ques-tab').empty();
				if(data.content){
					var article = data.content;
					var html = '<div class="articlewrap">';
					html += '<h2 class="textcenter">'+ article.title +'</h2>';
					html += '<div class=" textcenter fontnormal font-gray pubdate">发布日期：'+new Date(article.uptime).format("yyyy-MM-dd hh:mm:ss")+'</div>';
					html += article.content;
					html += '</div>';
					$('#all-ques-tab').append(html);
				}
			}else{
				layer.msg('对不起，服务器请求失败');
			}
		}
	});
}
