var loginUrl = yoyo.portalUrl+"/register/login";
var goodsImgSrcPre = yoyo.imagesUrl;
$(function () {
	
	//加载商品咨询
	findGoodsDiscuss(1);
	//加载货品信息
	findProductInfo();
	
	//根据关键字搜索咨询列表
	$('#btnReferSearch').on('click', function(){
		findGoodsDiscuss(1);
	});
	
		/*consultationContent:
		{
			required: true,
			rangelength: [4, 100],
			specialWord: true
		}

jQuery.validator.addMethod("specialWord", function(value, element) { 
    return this.optional(element) || /^[^<>]+$/.test(value);
}, "Your words must not contain special word."); 





$("#form1").validate(
			{
				rules:
				{
					pointType:
						{
							required: true
						},
					consultationContent:
						{
							required: true,
							rangelength: [4, 100],
							specialWord: true
						}
				},
				messages:
				{
					consultationContent:
						{
							required: "请填写咨询内容，长度在4-100位字符之间",
							rangelength: "咨询内容长度只能在4-100位字符之间",
							specialWord: "咨询内容含有特殊字符"
						}
				}
			});



	consultationContent:
		{
			required: "请填写咨询内容，长度在4-100位字符之间",
			rangelength: "咨询内容长度只能在4-100位字符之间",
			specialWord: "咨询内容含有特殊字符"
		}*/
		$("#saveConsultation").click(function(){
			var consultationContent = $.trim($("#consultationContent").val());
			if(consultationContent.length>=4&&consultationContent.length<=100){
				if(/^[^<>]+$/.test(consultationContent)){
					var productId = $('#product_id').val();
					var goodsId = $('#goods_id').val();
					if(goodsId!=null&&goodsId!=''){
						var url = _path+'/goodsManager/saveConsultation';
						var params = {};
						params.goodsId = goodsId;
						params.productId = productId;
						params.consultationContent = consultationContent;
						commonAjax(url, params, function(data) {
							if(data.content==null&&data.head.retCode=="000000"){
								alert("咨询提交成功，请等待客服处理");
//								$('#saveConsultation').hide();
								window.location.reload(true);
							}else if(data.content.result == false){
								if(data.content.isBuyer==false){
									window.location.href=loginUrl;
								}else{
									alert(data.content.msg);
									if(data.content.msg=="请先登录"){
										window.location.href=loginUrl;
									}
								}
							}else{
								alert("系统繁忙，请稍后再试");
							}
						}, function(data) {
							alert("系统繁忙，请稍后再试");
						});
					}
				}else{
					alert("咨询内容含有特殊字符");
				}
			}else{
				alert("咨询内容长度只能在4-100位字符之间");
			}
		});
});

//加载商品咨询
function findGoodsDiscuss(pageIndex){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findGoodsDiscuss';
		var params = {};
		params.goodsId = goodsId;
		params.pageIndex = pageIndex;
		params.keywords = $('#txbReferSearch').val();
		commonAjax(url, params, function(data) {
			var div = $('div.Refer_List');
			if(data.content!=null){
				if(data.content!=null&&data.content.rows.length>=1){
					var commentPage = data.content;
//					$('#cst').text("商品咨询("+commentPage.total+")");
					var item = $(div).find("div.refer").eq(0);
					var item2 = item.clone(true);
					$(div).empty();
					for(var i=0;i<commentPage.rows.length;i++){
						if(i%2==0){
							$(item2).attr("class","refer");
						}else{
							$(item2).attr("class","refer refer_bg");
						}
						$(item2).find(".r_info span").eq(0).text(commentPage.rows[i].author);
						$(item2).find(".r_info span").eq(1).text(commentPage.rows[i].commentDate);
						$(item2).find(".ask span").eq(0).text(commentPage.rows[i].comment);
						
						if(commentPage.rows[i].replyCommentId==null||commentPage.rows[i].replyCommentId==0){
							$(item2).find("dl.answer").eq(0).hide();
						}else{
							$(item2).find(".answer dd").eq(0).text(commentPage.rows[i].replyComment);
							$(item2).find("dl.answer").eq(0).show();
						}
						$(item2).attr("style","");
						$(div).append(item2);
						item2 = item2.clone(true); 
					}
					$('.Pagination').empty();
					if(commentPage.pages >= 2){
						
						if(commentPage.pageIndex > 1){
//							alert("1");
							$('.Pagination').append('<a class="prev" href="javascript:findGoodsDiscuss('+(commentPage.pageIndex-1)+')">上一页<b></b></a>');
							$('.Pagination').append('<a href="javascript:findGoodsDiscuss(1)">1</a>');
						}
						
						if(commentPage.pageIndex-3 > 1){
//							alert("2");
							$('.Pagination').append('<span>...</span>');
						}
						
						if(commentPage.pageIndex-2 > 1){
//							alert("3");
							$('.Pagination').append('<a href="javascript:findGoodsDiscuss('+(commentPage.pageIndex-2)+')">'+(commentPage.pageIndex-2)+'</a>');
						}
						
						if(commentPage.pageIndex-1 > 1){
//							alert("4");
							$('.Pagination').append('<a href="javascript:findGoodsDiscuss('+(commentPage.pageIndex-1)+')">'+(commentPage.pageIndex-1)+'</a>');
						}
						
						$('.Pagination').append('<a class="current">'+commentPage.pageIndex+'</a>');
						
						if(commentPage.pageIndex+1 < commentPage.pages){
//							alert("5");
							$('.Pagination').append('<a href="javascript:findGoodsDiscuss('+(commentPage.pageIndex+1)+')">'+(commentPage.pageIndex+1)+'</a>');
						}
						
						if(commentPage.pageIndex+2 < commentPage.pages){
//							alert("6");
							$('.Pagination').append('<a href="javascript:findGoodsDiscuss('+(commentPage.pageIndex+2)+')">'+(commentPage.pageIndex+2)+'</a>');
						}
						
						if(commentPage.pageIndex+3 < commentPage.pages){
//							alert("7");
							$('.Pagination').append('<span>...</span>');
						}
						
						if(commentPage.pageIndex < commentPage.pages){
//							alert("8");
							$('.Pagination').append('<a href="javascript:findGoodsDiscuss('+commentPage.pages+')">'+commentPage.pages+'</a>');
							$('.Pagination').append('<a class="next" href="javascript:findGoodsDiscuss('+(commentPage.pageIndex+1)+')">下一页<b></b></a>');
						}
						/*<a class="prev" href="http://club.jd.com/allconsultations/1093258-5-1.html">上一页<b></b></a>
						<a href="http://club.jd.com/allconsultations/1093258-1-1.html">1</a>
						<span>...</span>
						<a href="http://club.jd.com/allconsultations/1093258-4-1.html">4</a>
						<a href="http://club.jd.com/allconsultations/1093258-5-1.html">5</a>
						<a class="current">6</a>
						<a href="http://club.jd.com/allconsultations/1093258-7-1.html">7</a>
						<a href="http://club.jd.com/allconsultations/1093258-8-1.html">8</a>
						<span>...</span>
						<a href="http://club.jd.com/allconsultations/1093258-117-1.html">117</a>
						<a class="next" href="http://club.jd.com/allconsultations/1093258-7-1.html">下一页<b></b></a>*/
					}
				}else{
					//无咨询
					$(div).empty();
				}
			}else{
				//无咨询
				$(div).empty();
			}
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
			$(div).empty();
		});
	}
}

//加载货品信息
function findProductInfo(){
	var productId = $('#product_id').val();
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findProductInfo';
		var params = {};
		params.goodsId = goodsId;
		params.productId = productId;
		commonAjax(url, params, function(data) {
			if(data.content.result == false){
//				alert(data.content.msg);
//				if(data.content.msg=="请先登录"){
//					window.location.href=loginUrl;
//				}
			}else{
				var product = data.content;
				$('.p-img a').attr("href",yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+product.goodsId+"&productId="+product.productId);
				$('.p-img a').attr("alt",product.name);
				$('.p-img a').attr("title",product.name);
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
			}
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
				alert(data.content.msg);
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
