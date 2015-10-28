/**
 * 仓库中,待上架，违规，审核中的商品页的打开js
 * 公共js
 */

/**修改图片地址，加上前缀*/
function loadPicture(){
	$.each($('.product img'),function(i,that){
		$(that).attr('src',yoyo.imagesUrl+$(that).attr('data-img'));
	});
}
/**清空查询条件*/
function cleanFun() {
	$('#searchForm ._clear').val('');
	$('#goodsCategory').combotree('clear');
	$("#btnsearch").click();
}

/**打开商品详情页*/
function openGoodsLink(goodsId){
	window.open(yoyo.portalUrl + '/goodsManager/goodsIndex?goodsId=' + goodsId);
}

/**获得选择的商品**/
function vailidateOperate(){
	var rows = [];
	 $("input[name=goodsItem]:checked").each(function(){ 
		rows.push('{"goodsId":'+$(this).val()+'}'); 
	}); 
	 
	return rows;
}

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 * 
 * @param num
 *            数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * showCents:是否显示小数
 * @type String
 */
function formatCurrency(num,showCents) {
	if (!num)
		return "0";
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ','
				+ num.substring(num.length - (4 * i + 3));
	if(showCents){
		return (((sign) ? '' : '-') + num + '.' + cents);
	}else{
		return (((sign) ? '' : '-') + num );
	}
}


/**打开等待上架中的商品***/
function openPutawayGoods(){
	window.location.href = yoyo.shopUrl+'/goods/loadPutawayList?marketableQ=0';
}

/**打开预警中的商品***/
function openWarningGoods(){
	window.location.href = yoyo.shopUrl+'/goods/loadWarningList';
}


/**打开违规下架中的商品***/
function openDealingGoods(){
	window.location.href = yoyo.shopUrl+'/goods/loadViolationList';  
}

/**打开审核中的商品***/
function openCheckingGoods(){
	window.location.href = yoyo.shopUrl+'/goods/loadCheckingList?marketableQ=2&isChecking=1';  
}

/**打开本地暂存的商品*/
function openLocationGoods(){
	window.location.href = yoyo.shopUrl+'/goods/loadLocationList?marketableQ=-1';
}



