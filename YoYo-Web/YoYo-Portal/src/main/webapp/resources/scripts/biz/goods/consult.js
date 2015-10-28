var loginUrl = yoyo.portalUrl+"/register/login";
var goodsImgSrcPre = yoyo.imagesUrl;
$(function () {
	
	//加载商品咨询
	findGoodsDiscuss({refresh:true});
	//加载货品信息
	findProductInfo();
	
	//根据关键字搜索咨询列表
	$('#btnReferSearch').on('click', function(){
		findGoodsDiscuss(1);
	});
	$("#saveConsultation").click(function(){
		var consultationContent = $.trim($("#consultationContent").val());
		if(consultationContent.length<10||consultationContent.length>100){
			alert("咨询内容长度只能在10-100位字符之间");
			return;
		}
		if(!(/^[^<>]+$/.test(consultationContent))){
			alert("咨询内容含有特殊字符");
			return;
		}
		
		validateImgCode('id_imgValidation', function(data) {
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
		},function (data){
			
		});
	});
});

//加载商品咨询
function findGoodsDiscuss(opts){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findGoodsDiscuss';
		var params = {};
		params.goodsId = goodsId;
		params.pageIndex = 1;
		params.keywords = $('#txbReferSearch').val();
		params.page=$("#page-num").val();
		params.rows=10;
		commonAjax(url, params, function(data) {
			var div = $('div.Refer_List');
			if(data.content!=null){
				if(data.content!=null&&data.content.rows.length>=1){
					var commentPage = data.content;
					var html=[];
					$.each(commentPage.rows,function (x,item){
						html.push('<div class="refer">');
						html.push('<div class="r_info">网友：'+item.author+'<span></span>&nbsp;&nbsp;&nbsp;&nbsp;<em id="level" name=""></em>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+new Date(item.time).format("yyyy-MM-dd hh:mm:ss")+'</span>');
						html.push('</div>');
						html.push('<dl class="ask">');
						html.push('<dt>咨询内容：</dt>');
						html.push('<dd>');
						html.push('<span>'+item.comment+'</span>');
						html.push('</dd>');
						html.push('</dl>');
						$.each(item.replyComment,function (x,replyitem){
							html.push('<dl class="answer">');
							html.push('<dt>掌柜回复：</dt>');
							html.push('<dd>'+replyitem.comment+'</dd>');
							html.push('</dl>');
						});
						html.push('</div>');
					});
					$(".Refer_List").html(html.join(""));
					if(opts&&opts.refresh){
						$("#Pagination").yoyoPagination(commentPage.total,{
			    			items_per_page:commentPage.pageSize,
			    			callback:function(){
			    				findGoodsDiscuss({refresh:false});
			    			}
			    		});
					}
				}
			}
		}, function(data) {
			
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
				if(product.cost>0){
				$('.p-cost-price strong').text("￥"+product.cost.toFixed(2));
				$('.p-cost-price').show();
				}
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
