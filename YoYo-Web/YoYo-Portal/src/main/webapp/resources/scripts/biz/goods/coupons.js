var loginUrl = yoyo.portalUrl+'/register/login';
var indexUrl = yoyo.portalUrl;
var confirmUrl = yoyo.memUrl+'/cart/confirmOrder';
var cartUrl = yoyo.portalUrl+'/cart/index';
var goodsImgSrcPre = yoyo.imagesUrl;
var errorMsg = "系统繁忙，请稍后再试";
$(function(){
	//活动立即购买
	$("#activityBuyNow").click(function(){
		var cpnsId = $("#cpnsId").val();
		window.location.href = yoyo.memUrl+'/pay/payOrderCoupon?cpnsId='+cpnsId+'';
	});
	
})