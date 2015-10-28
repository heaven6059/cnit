$(function(){
	loadPreference({refresh : true});
	loadNotice({refresh : true});
});

/**
 * 特惠分页
 * @param opts
 */
function loadPreference(opts){
	var html=[];
	$.ajax({
		url:'../news/getMore',
		type:'post',
		data:{
			'title':$('#textPreference').val(),
			'type':0,
			'disabled':0,
			'pageSize':10,
		    'pageNum':$("#page-num").val()	
		},
		async:false,
		dataType:'json'
	}).done(function(returnData){
		$("#preferenceUl").empty();
	    $.each(returnData.rows,function(index,news){
	    	html.push('<li>');
	    	html.push('	<div>');
	    	if(news.hasContent){
		    	html.push('		<a href="../news/detail?newsId='+ news.newsId +'">');
	    	}else{
	    		html.push('		<a href="'+ news.url +'">');
	    	}
	    	html.push(news.title);
	    	html.push('		</a>');
	    	html.push('	</div>');
	    	html.push('	<span>'+new Date(news.pubtime).format("yyyy-MM-dd hh:mm:ss")+'</span>');
	    	html.push('	<div class="line"></div>');
	    	html.push('</li>');	
	    });
	   $('#preferenceUl').append(html.join(""));
	   if(opts && opts.refresh){
		   var opt = {
				   items_per_page:returnData.pageSize,
				   callback:function (){
					   loadPreference({refresh:false});
				   }
		   };
		   $("#preference").yoyoPagination(returnData.total,opt);
	   };
	});
}

/**
 * 公告分页
 * @param opts
 */
function loadNotice(opts){
	var html=[];
	$.ajax({
		url:'../news/getMore',
		data:{
			'title':$('#textNotice').val(),
			'type':1,
			'disabled':0
		},
		async:false,
		dataType:'json'
	}).done(function(returnData){
		$("#noticeUl").empty();
	    $.each(returnData.rows,function(index,news){
	    	html.push('<li>');
	    	html.push('	<div>');
	    	if(news.hasContent){
		    	html.push('		<a href="../news/detail?newsId='+ news.newsId +'">');
	    	}else{
	    		html.push('		<a href="'+ news.url +'">');
	    	}
	    	html.push(news.title);
	    	html.push('		</a>');
	    	html.push('	</div>');
	    	html.push('	<span>'+new Date(news.pubtime).format("yyyy-MM-dd hh:mm:ss")+'</span>');
	    	html.push('	<div class="line"></div>');
	    	html.push('</li>');	
	    });
	   $('#noticeUl').append(html.join(""));
	   if(opts && opts.refresh){
		   var opt = {
				   items_per_page:returnData.pageSize,
				   callback:function (){
					   loadPreference({refresh:false});
				   }
		   };
		   $("#notice").yoyoPagination(returnData.total,opt);
	   };
	});
}

function queryNews(type){
	if(type == 0){
		loadPreference({refresh : true});
	}else if(type == 1){
		loadNotice({refresh : true});
	}
}