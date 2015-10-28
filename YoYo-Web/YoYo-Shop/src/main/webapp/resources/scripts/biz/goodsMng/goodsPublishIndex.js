/**
 * 发布商品js
 */

$(function(){
	$('input[name="goods_kind"]').click(function(){
		$(this).parent().removeClass('return_boxn');
		$(this).parent().addClass('return_boxon');
		$(this).parent().siblings().removeClass('return_boxon');
		$(this).parent().siblings().addClass('return_boxn');
	});
	$('input[name="goods_kind"]')[0].click();
});


/**点击下一步按钮*/
function submitBtn(){
	var goodsType = $('input[name="goods_kind"]:checked').val();
	window.location.href = _path + '/goodsPublish/goods_go?type='+goodsType;
}
