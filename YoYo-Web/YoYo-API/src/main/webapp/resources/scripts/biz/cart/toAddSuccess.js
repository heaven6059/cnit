var errorMsg="系统繁忙，请稍后再试！";
var loginUrl = yoyo.portalUrl+'/register/login';
var indexUrl = yoyo.portalUrl;
var confirmUrl = yoyo.memUrl+'/cart/confirmOrder';
var cartUrl = yoyo.portalUrl+'/cart/index';
var goodsImgSrcPre = yoyo.imagesUrl;
var defaultGoodsImage="/resources/images/cart/default_cart_img.jpg";
var midPicSize = "120x120";
var smallPicSize = "50x50";
$(function(){
	//加载购物车商品
	findCartList();
	//加载同订单货品和推荐货品
	findProductFromSameOrder();
	
//	//去结算
//	$('.toolbar_right .btn_area a').on('click',function(){
//		
//		var url = yoyo.memUrl+'/cart/putSelectPro';
//		var params = {};
//		params.proIdList = "["+ids.join(",")+"]";
//		commonAjax(url, params, function(data) {
//			
//		}, function(data) {
//			
//		})
//	})
	
	
});

//加载购物车商品
function findCartList(){
	var productId = $('#product_id').val();
	var url = yoyo.portalUrl+'/cart/cartList';
	var params = {};
	params.productId = productId;
	commonAjax(url, params, function(data) {
		if(data.content!=null){
			if(data.content.cartList!=null&&data.content.cartList.length>=1){
				var cartList = data.content.cartList;
				var amount = 0;
				for(var i = 0 ; i<cartList.length ; i++){
					if(productId==cartList[i].productId){
						$('.add_right dl.clearfix').eq(0).append(''
								+'<dd class="clearfix">'
									+'<div class="r_pic fl">'
										+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+cartList[i].goodsId+'" target="_blank"><img src="'+goodsImgSrcPre+cartList[i].goodsImage+'" alt="cartList[i].productName" width="50px" height="50px"></a>'
									+'</div>'
									+'<div class="p_info fr" style="width:130px;">'
										+'<div class="p_name" style="height:36px;overflow:hidden;"><a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+cartList[i].goodsId+'">'+cartList[i].productName+'</a></div>'
										+'<div class="p_price">'
											+'<span class="red">'+"￥"+cartList[i].goodsPrice.toFixed(2)+'</span>'
//											+'<em>&nbsp;(满赠总价)</em>'
										+'</div>'
									+'</div>'
								+'</dd>'
								+'');
					}else{
						$('.add_right dl.clearfix').eq(1).append(''
								+'<dd class="clearfix">'
									+'<div class="r_pic fl">'
										+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+cartList[i].goodsId+'" target="_blank"><img src="'+goodsImgSrcPre+cartList[i].goodsImage+'" alt="cartList[i].productName" width="50px" height="50px"></a>'
									+'</div>'
									+'<div class="p_info fr" style="width:130px;">'
										+'<div class="p_name" style="height:36px;overflow:hidden;"><a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+cartList[i].goodsId+'" target="_blank">'+cartList[i].productName+'</a></div>'
										+'<div class="p_price">'
											+'<span class="red">'+"￥"+cartList[i].goodsPrice.toFixed(2)+'</span>'
//											+'<em>&nbsp;(满赠总价)</em>'
										+'</div>'
									+'</div>'
								+'</dd>'
								+'');
					}
					amount = (parseFloat(amount)+parseFloat(cartList[i].goodsPrice)*parseInt(cartList[i].quantity)).toFixed(2);
				}
				if($('.add_right dl.clearfix').eq(0).find('.clearfix').length>=1){
					$('.add_right dl.clearfix').eq(0).find("dt").show();
					$('.add_right').show();
				}
				if($('.add_right dl.clearfix').eq(1).find('.clearfix').length>=1){
					$('.add_right dl.clearfix').eq(1).find("dt").show();
					$('.add_right').show();
				}
				$('.add_right .total .red').eq(0).text(cartList.length);
				$('.add_right .total .red').eq(1).text("￥"+amount);
			}
		}
	}, function(data) {
		
	});
}

//加载同订单货品和推荐货品
function findProductFromSameOrder(){
	var productId = $('#product_id').val();
	if(productId!=null&&productId!=''){
		var url = yoyo.portalUrl+'/cart/findProductFromSameOrder';
		var params = {};
		params.productId = productId;
		commonAjax(url, params, function(data) {
			if(data.content!=null){
				if(data.content.sameOrderProduct!=null&&data.content.sameOrderProduct.length>=1){
					$('.add_left .add_in').eq(0).show();
					var sameOrderList = data.content.sameOrderProduct;
					for(var i =0; i<sameOrderList.length;i++){
						$('.add_left .add_in').eq(0).find('.shop_con ul').append(''
								+'<li>'
									+'<div class="p_img fl">'
										+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?productId='+sameOrderList[i].productId+'" target="_blank"><img src="'+goodsImgSrcPre+sameOrderList[i].picturePath+'" alt="'+sameOrderList[i].productName+'" width="100px" height="100px"></a>'
									+'</div>'
									+'<div class="fr" style="width:192px;height:100px;">'
										+'<h5 style="height:44px;overflow:hidden;"><a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?productId='+sameOrderList[i].productId+'" target="_blank">'+sameOrderList[i].productName+'</a></h5>'
										+'<h3 class="red">￥'+sameOrderList[i].price.toFixed(2)+'</h3>'
										+'<p><a class="dd_color" href="javascript:;">(已有'+sameOrderList[i].commentCount+'人评价)</a></p>'
										+'<span><a class="p_btn" href="javascript:;" onclick="addCart('+sameOrderList[i].productId+')">加入购物车</a></span>'
//										+'<span><a class="p_btn" href="javascript:;">加入购物车</a></span>'
									+'</div>'
								+'</li>'
								+'');
//						alert(i);
					}
					$('.add_left .add_in').eq(0).show();
				}
			}
		}, function(data) {
			
		});
	}
}

//加入购物车
function addCart(productId){
	var url = yoyo.portalUrl+'/cart/addCart';
	var params = {};
	params.quantity = 1;
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
	});
}
