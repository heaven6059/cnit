var loginUrl = yoyo.portalUrl+"/register/login";
var goodsImgSrcPre = yoyo.imagesUrl;
$(function () {
	//加载评论信息
	findCommentInfo();
	//加载回复列表
	findReplyList(1);
//	//加载货品信息
//	findProductInfo();
	
	
	
	
	$('.J-hide-big-show').on('click',function(){
		$(this).parent().parent().hide();
	});
	
	$('.J-photo-prev').on('click',function(){
		var data = $(this).prev().attr("data");
		if(parseInt(data)-1>=0){
			var src = $(this).parent().parent().parent().find('.comment-show-pic').find("td").eq(parseInt(data)-1).find("img").attr("src");
			var index = src.lastIndexOf(".");
			src = src.substring(0,index)+".500x500."+src.substring(index+1,src.length);
			$(this).prev().attr("data",parseInt(data)-1);
			$(this).prev().attr("src",src);
		}
	});
	
	$('.J-photo-next').on('click',function(){
		var data = $(this).prev().prev().prev().attr("data");
		if($(this).parent().parent().parent().find('.comment-show-pic').find("td").eq(parseInt(data)+1).length>=1){
			var src = $(this).parent().parent().parent().find('.comment-show-pic').find("td").eq(parseInt(data)+1).find("img").attr("src");
			var index = src.lastIndexOf(".");
			src = src.substring(0,index)+".500x500."+src.substring(index+1,src.length);
			$(this).prev().prev().prev().attr("data",parseInt(data)+1);
			$(this).prev().prev().prev().attr("src",src);
		}
	});
	
});

//查看晒单
function showHideBig(ele){
	var tdList = $(ele).parent().parent().find("table").find("td");
	$(tdList[0]).click();
}

//下一张评论图片
function nextCommentPic(ele){
	var tdList = $(ele).prev().find("td");
	//alert(liList.length);
	if(tdList[tdList.length-1].style.display!="none"){
		//alert("return...");
		return;
	}
	for(var i=0;i<tdList.length;i++){
		//if(i+5>=aList.length-1){
		if(tdList[i].style.display!="none"){
			tdList[i].style.display="none";
			tdList[i+5].style.display="";
			/*$('#spec-forward').attr("class","spec-control");*/
			$('.J-thumb-prev').removeClass("i-prev-disable");
			break;
		}
	}
}

//上一张评论图片
function prevCommentPic(ele){
	var tdList = $(ele).next().find("td");
	//alert(aList.length);
	if(tdList[0].style.display!="none"){
		//alert("return...");
		return;
	}
	for(var i=0;i<tdList.length;i++){
		if(tdList[i].style.display!="none"){
			tdList[i-1].style.display="";
			tdList[i+4].style.display="none";
			/*$('#spec-backward').attr("class","spec-control");*/
			$('.J-thumb-next').removeClass("i-next-disable");
			break;
		}
	}
	/*if(liList[0].style.display!="none"){
		$('#spec-forward').attr("class","spec-control disabled");
	}*/
}

//加载评论信息
function findCommentInfo(){
	var commentId = $('#comment_id').val();
	if(commentId!=null&&commentId!=''){
		var url = _path+'/goodsManager/findCommentInfo';
		var params = {};
		params.commentId = commentId;
		commonAjax(url, params, function(data) {
			if(data!=null&&data.content!=null){
				var comment = data.content;
				if(comment.commentStar!=null&&parseInt(comment.commentStar)>=1&&parseInt(comment.commentStar)<=5){
					$('.i-item .o-topic .star').attr("class","star sa"+comment.commentStar);
				}
				if(comment.commentDate!=null){
					$('.i-item .o-topic .date-comment').text(comment.commentDate);
				}else{
					$('.i-item .o-topic .date-comment').text("");
				}
				$('.i-item .comment-content').find("dl").eq(0).find("dd").text(comment.comment);
				var picDl=$('.i-item .comment-content').find("dl").eq(1);
				if(comment.picPageList!=null&&comment.picPageList.length>=1){
					var tr = $(picDl).find('.comment-show-pic tr');
					$(tr).empty();
					for(var i = 0;i<comment.picPageList.length;i++){
						if(i>4){
							$(tr).append('<td style="display:none;" data="'+i+'">'             
                              		+'<a class="comment-show-pic-wrap" href="javascript:;" target="_blank" clstag="shangpin|keycount|product|shaipic">'                       
                                        +'<img alt="" src="'+goodsImgSrcPre+comment.picPageList[i].picturePath+'" class="cursor-big" onload="autosize(this,130,130)" width="130px" height="130px">'   
                                    +'</a>' 
                            +'</td>');
						}else{
							$(tr).append('<td data="'+i+'">'             
                              		+'<a class="comment-show-pic-wrap" href="javascript:;" target="_blank" clstag="shangpin|keycount|product|shaipic">'                       
                                        +'<img alt="" src="'+goodsImgSrcPre+comment.picPageList[i].picturePath+'" class="cursor-big" onload="autosize(this,130,130)" width="130px" height="130px">'   
                                    +'</a>' 
                            +'</td>');
						}
					}
					$(picDl).show();
					$(picDl).find('.comment-show-pic').find('em.fl').text("共"+comment.picPageList.length+"张图片");
				}else{
					$(picDl).hide();
				}
			
				$('.comment-content .dl-extra dd').text(comment.orderCreateDate);
				$('.i-item .btns').find("a").eq(0).html('回复(<span>'+comment.replyCount+'</span>)');
				$('.i-item .btns').find("a").eq(1).text('有用('+comment.praise+')');
				$('.i-item .btns').find("a").eq(1).attr("onclick","addPraise("+comment.commentId+")");
				$('.i-item .reply-list .reply-input').find('.fl').attr("rid",comment.commentId);
				$('#comments-list .item  .i-item .item-reply .reply-wrap .u-name').text(comment.loginName+"：");
				
				$('#comments-list .item .user .u-name').text(comment.loginName);
				$('#comments-list .item .user .u-level').text(comment.memberLvName);
				
				
				
				findProductInfo(comment.productId);
			}else {
//				alert("该评论不存在");
//				window.location.reload(true);
			}

			$('.comment-show-pic td').on('click', function(){
				var src = $(this).find("img").attr("src");
				var data = $(this).attr("data");
				var index = src.lastIndexOf(".");
				src = src.substring(0,index)+".500x500."+src.substring(index+1,src.length);
				$(".p-photos-viewer").find("img").attr("data",data);
				$(".p-photos-viewer").find("img").attr("src",src);
				$(".p-photos-viewer").show();
				var tdList = $(this).siblings("td");
				if(tdList[tdList.length-1].style.display!="none"){
					return;
				}
				$(this).parent().parent().parent().next().removeClass("i-next-disable");
			});
		}, function(data) {
			alert(errorMsg);
		});
	}
}

//加载回复列表
function findReplyList(pageIndex){
	var commentId = $('#comment_id').val();
	if(commentId!=null&&commentId!=''){
		var url = _path+'/goodsManager/findReplyList';
		var params = {};
		params.commentId = commentId;
		params.pageIndex = pageIndex;
//		var toName=$()
		
		commonAjax(url, params, function(data) {
			var div = $('div[name="replyList"]');
			var item = $(div).find(".item-reply").eq(0);
			var item2 = item.clone(true);
			$(div).empty();
			if(data.content!=null&&data.content.rows.length>=1){ 
				var commentPage = data.content;
				$('#comments-list .item .i-item .btns .btn-reply').find("span").text(commentPage.total);
				for(var i=0;i<commentPage.rows.length;i++){
					$(item2).find("strong").text(commentPage.total-((commentPage.pageIndex-1)*commentPage.pageSize+i));
					$(item2).find('.reply-con').find(".u-name").empty();
					$(item2).find('.reply-con').find(".u-name").append('<a rel="nofollow" href="javascript:;">'+commentPage.rows[i].loginName+'</a>');
					if(commentPage.rows[i].toId!=0){
						$(item2).find('.reply-con').find(".u-name").append('<em> 回复  </em><a rel="nofollow" href="javascript:;">'+commentPage.rows[i].toName+'</a>');
					}
					$(item2).find('.reply-con').find(".u-name").append('：');
					$(item2).find(".u-con").text(commentPage.rows[i].comment);
					$(item2).find('.reply-meta').find('.reply-left').text(commentPage.rows[i].commentDate!=null?commentPage.rows[i].commentDate:"");
					$(item2).find('.replay-form').find('.fl').attr("rid",commentPage.rows[i].commentId);
					$(item2).find('.replay-form').find(".reply-wrap").find('.u-name').text(commentPage.rows[i].loginName+"：");
					$(item2).find('.replay-form').css("display","none");
					
					$(item2).attr("style","");
					$(div).append(item2);
					item2 = item2.clone(true); 
				}
				$('.pagin').empty();
				if(commentPage.pages >= 2){
					if(commentPage.pageIndex > 1){
						$('.pagin').append('<a class="prev" href="javascript:findReplyList('+(commentPage.pageIndex-1)+')">上一页<b></b></a>');
						$('.pagin').append('<a href="javascript:findReplyList(1)">1</a>');
					}
					if(commentPage.pageIndex-3 > 1){
						$('.pagin').append('<span>...</span>');
					}
					if(commentPage.pageIndex-2 > 1){
						$('.pagin').append('<a href="javascript:findReplyList('+(commentPage.pageIndex-2)+')">'+(commentPage.pageIndex-2)+'</a>');
					}
					if(commentPage.pageIndex-1 > 1){
						$('.pagin').append('<a href="javascript:findReplyList('+(commentPage.pageIndex-1)+')">'+(commentPage.pageIndex-1)+'</a>');
					}
					$('.pagin').append('<a class="current">'+commentPage.pageIndex+'</a>');
					if(commentPage.pageIndex+1 < commentPage.pages){
						$('.pagin').append('<a href="javascript:findReplyList('+(commentPage.pageIndex+1)+')">'+(commentPage.pageIndex+1)+'</a>');
					}
					if(commentPage.pageIndex+2 < commentPage.pages){
						$('.pagin').append('<a href="javascript:findReplyList('+(commentPage.pageIndex+2)+')">'+(commentPage.pageIndex+2)+'</a>');
					}
					if(commentPage.pageIndex+3 < commentPage.pages){
						$('.pagin').append('<span>...</span>');
					}
					if(commentPage.pageIndex < commentPage.pages){
						$('.pagin').append('<a href="javascript:findReplyList('+commentPage.pages+')">'+commentPage.pages+'</a>');
						$('.pagin').append('<a class="next" href="javascript:findReplyList('+(commentPage.pageIndex+1)+')">下一页<b></b></a>');
					}
				}
			}
		}, function(data) {
			alert(errorMsg);
		});
	}
}

//回复
function toReply(ele){
	if($(ele).parent().next().css("display")=="none"){
		$(ele).parent().next().find('.reply-wrap .reply-input .fl input').val('');
		$(ele).parent().next().show();
	}else{
		$(ele).parent().next().hide();
	}
}

//提交回复
function submitReply(ele){
	var commentText = $(ele).prev().find("input").val();
	var commentId = $(ele).prev().attr("rid");
	if(commentId!=null&&commentId!=''){
		if(commentText!=null&&commentText!=''){
			var url = _path+'/goodsManager/addReply';
			var params = {};
			params.commentId = commentId;
			params.commentText = commentText;
			commonAjax(url, params, function(data) {
				if(data.content!=null&&data.content.result==null){
					if(data.content.length>=1){
//						var comment = data.content[0];
//						var order = $('div[name="replyList"]').find('div.item-reply').eq(0).find('strong').eq(0).text();
//						order = parseInt(order)+1;
//						var str = '';
//						str+='<div class="item-reply none" style="">'
//								+'<strong>'+order+'</strong>'
//								+'<div class="reply-list">'
//									+'<div class="reply-con">'
//										+'<span class="u-name"><a rel="nofollow" href="javascript:;">'+comment.author+'</a>';
//						if(comment.toId!='0'){
//							str+=	'<em> 回复  </em><a rel="nofollow" href="javascript:;">'+comment.toUname+'</a>';
//						}
//						str+=		'：</span><span class="u-con">'+comment.comment+'</span>'
//									+'</div>'
//									+'<div class="reply-meta">'
//										+'<span class="reply-left fl">　'+(comment.commentDate!=null?comment.commentDate:"")+'</span>'
//										+'<a class="p-bfc hl_blue" href="javascript:;" onclick="toReply(this)" style="visibility: visible;">回复</a>'
//									+'</div>'
//									+'<div class="replay-form" style="display: none;">'
//										+'<div class="arrow"> <em>◆</em><span>◆</span></div>'
//										+'<div class="reply-wrap">'
//											+'<p><em>回复</em> <span class="u-name"><a rel="nofollow" href="javascript:;">'+comment.author+'</a>：</span></p>'
//											+'<div class="reply-input">'
//												+'<div class="fl" uh="" ud="" un="" cid="" rid="'+comment.commentId+'"><input type="text"></div>'
//												+'<a href="javascript:;" class="reply-btn btn-gray" onclick="submitReply(this)">回复</a>'
//												+'<div class="clr"></div>'
//											+'</div>'
//										+'</div>'
//									+'</div>'
//								+'</div>'
//							+'</div>';
//						$(ele).parent().parent().parent().hide();
//						$('div[name="replyList"]').prepend(str);
						$(ele).parent().parent().parent().parent().parent().css("display","none");
						findReplyList(1);
					}else{
						alert(errorMsg);
					}
				}else if(data.content.result == false){
					if(data.content.isBuyer==false){
						window.location.href=loginUrl;
					}else{
						alert(data.content.msg);
						if(data.content.msg=="请先登录"){
							window.location.href=loginUrl;
						}
					}
					
				}
			}, function(data) {
				alert(errorMsg);
			});
		}else{
			alert("请输入回复内容");
		}
	}else{
		alert("该评论不存在");
	}
}

//加载货品信息
function findProductInfo(productId){
	if(productId!=null&&productId!=''){
		var url = _path+'/goodsManager/findProductInfo';
		var params = {};
		params.productId = productId;
		commonAjax(url, params, function(data) {
			var product = data.content;
			$('#product-info .p-img a').attr("href",yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+product.goodsId+"&productId="+product.productId);
			$('#product-info .p-img a').attr("alt",product.name);
			$('#product-info .p-img a').attr("title",product.name);
			if(product!=null&&product.picList!=null&&product.picList.length>=1){
				$('.p-img a img').attr("src",goodsImgSrcPre+product.picList[0].picturePath);
			}
			$('.p-name a').attr("href",yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+product.goodsId+"&productId="+product.productId);
			$('.p-name a').text(product.name);
			$('.p-price strong').text("￥"+product.price.toFixed(2));
			if(parseInt(product.commentCount)>0){
				$('#product_star').css("width",((15*(parseFloat(product.sumPoint)/parseFloat(product.commentCount)).toFixed(0))+"px"));
				$('#product_star_score').text((parseFloat(product.sumPoint)/parseFloat(product.commentCount)).toFixed(1));
			}else{
				$('#product_star').css("width","0px");
				$('#product_star_score').text(0);
			}
			$('#p-num-comment').text(product.commentCount);
			$('#product-info .p-btn .btn-append').attr("onclick","addCart()");
			$('#product_id').val(product.productId);
		}, function(data) {
			alert("系统繁忙，请稍后再试");
		});
	}
}
//加入购物车
function addCart(){
	var productId = $('#product_id').val();
	if(productId!=null&&productId!=''&&productId!='0'){
		var url = yoyo.portalUrl+'/cart/addCart';
		var params = {};
		params.quantity=1;
		params.productId = productId;
		commonAjax(url, params, function(data) {
			if(data.content.result==true){
				window.location.href = yoyo.portalUrl+"/cart/toAddSuccess?productId="+productId;
			}else if(data.content.result==false){
				if(data.content.isBuyer == false){
					window.location.href = loginUrl;
				}else{
					alert(data.content.msg);
				}
			}else{
				alert(errorMsg);
			}
		}, function(data) {
			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}else{
		alert("很抱歉，该货品已下架");
	}
}

//点赞
function addPraise(commentId){
	if(commentId!=null&&commentId!=''){
		var url = _path+'/goodsManager/addPraise';
		var params = {};
		params.commentId = commentId;
		commonAjax(url, params, function(data) {
			if(data.content!=null&&data.content.result==null){
				$('#agree').text("有用("+data.content+")");
//				$('li[name="comment_'+commentId+'"]').find(".tp_f").find("a").eq(1).text("赞("+data.content+")");
//				alert(data.content);
			}else if(data.content.result == false){
				if(data.content.isBuyer==false||data.content.msg=="请先登录"){
					window.location.href=loginUrl;
				}else{
					alert(data.content.msg);
				}
			}
		}, function(data) {
			if(data.head==null){
				window.location.href = loginUrl;
			}
		});
	}
}

function autosize(ImgD,h,w){
	var width,height;
	var image=new Image(); 
	image.src=ImgD.src; 
	//alert(image.width+"..."+image.height);
	if (image.width<w && image.height<h){
	    ImgD.width=image.width;
	    ImgD.height=image.height;
	    width=image.width;
	    height=image.height;
	}else{
	    if (w / h <= image.width / image.height){
	        ImgD.width=w;
	        ImgD.height=w * (image.height / image.width);
	        width=w;
	    	height=w * (image.height / image.width);
	    }else{
	        ImgD.width=h * (image.width / image.height);
	        ImgD.height=h;
	        width = h * (image.width / image.height);
	    	height = h;
	    }
	}
	$(ImgD).css("width",width);
	$(ImgD).css("height",height);
	ImgD.style.width=width;
	ImgD.style.height=height;
}

function imgErr(img) { 
	img.src = url_upload+defaultGoodsImage;
	img.onerror=null; //控制不要一直跳动 
}
