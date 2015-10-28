var loginUrl = yoyo.portalUrl+'/register/login';
var indexUrl = yoyo.portalUrl;
var confirmUrl = yoyo.memUrl+'/cart/confirmOrder';
var cartUrl = yoyo.portalUrl+'/cart/index';
var goodsImgSrcPre = yoyo.imagesUrl;
var defaultGoodsImage="/resources/images/cart/default_cart_img.jpg";
var midPicSize = "120x120";
var smallPicSize = "50x50";
$(function(){
	initCalendar();
	$("#date-311").bind("click",function(){
		$("#date-delivery").show();
	});
	getOrderInfo();
	
	$('#order-submit').on('click',function(){
		var divs = $('div[name="company_store"]');
		var storeIds=new Array();
		for(var i=0;i<divs.length;i++){
			if($(divs[i]).hasClass("item-selected")){
				storeIds.push($(divs[i]).parent().attr("id").split("_")[2]);
			}
		}
		var payment = $('input[name="payment"]:checked').attr("payname");
		var date=$('#date-311').val();
		var inputs = $('input[name="goods_item_id"]');
		var proIds = new Array();
		for(var i=0;i<inputs.length;i++){
			proIds.push($(inputs[i]).val());
		}
		inputs = $('input[name="goods_quantity"]');
		var quantitys = new Array();
		for(var i=0;i<inputs.length;i++){
			quantitys.push($(inputs[i]).val());
		}
		if(date!=null&&date!=''&&date!=undefined){
			var url = yoyo.memUrl+'/cart/submitOrder';
			var params = {};
			params.storeId="["+storeIds.join(",")+"]";
			params.payment=payment;
			params.date=date;
			params.proIds="["+proIds.join(",")+"]";
			params.quantitys="["+quantitys.join(",")+"]";
			params.accessoryId = $('#accessoryId').val();
			commonAjax(url, params, function(data) {
				if(data.content.result==true){
//					alert("订单提交成功!");
					window.location.href = yoyo.memUrl+"/cart/toPay";
				}else{
					alert(data.content.msg);
					if(data.content.isLogin==false){
//						alert("请先登录");
						window.location.href = loginUrl;
					}
				}
			}, function(data) {
				if(data.head==null){
					window.location.href = loginUrl;
				}
			});
		}else{
			alert("请选择消费时间");
		}
	});
});

//初始化日历
function initCalendar(){
	var dData;
	var url = yoyo.memUrl+'/cart/getDays';
	var params = {};
	commonAjax(url, params, function(data) {
		dData=data.content;
		var str='<div id="date-delivery" style="position: absolute; top: 28px; left: 233px; width: 671px; height: 81px; display: block;" load="1">'
			+'<div class="inner" style="position: relative; width: 670px; height: 81px;">'
			+'<dl class="th">'
				+'<dt>时间段</dt>'
				+'<dd class="date">';
		for(var i=0;i<dData.length;i++){
			str+='<span row="-1" col="'+i+'" style="background: rgb(255, 255, 255);">'+dData[i].date+'<br>'+dData[i].week+'</span>';
		}
		str    +=  	 '</dd>'
					+'<dd class="time">'
						+'<span row="0" col="-1" style="background: rgb(255, 255, 255);">请选择</span>'
					+'</dd>'
				+'</dl>'
				+'<div class="data" style="width: 570px;">'
					+'<ul>'
						+'<li class="checkbox disabled" data-status="0" row="0" col="0" val="" style="color: rgb(122, 189, 84); background: rgb(255, 255, 255);"></li>'

					+'</ul>';
		for(var i=1;i<dData.length;i++){
			str+='<ul>'
					+'<li class="checkbox" data-status="1" row="0" col="'+i+'" val="  '+dData[i].date+'  ('+dData[i].week+')  " day="'+dData[i].date+'" range="" date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}" style="color: rgb(122, 189, 84); background: rgb(255, 255, 255);" onmouseover="changebg(this,1)" onmouseout="changebg(this,0)" onclick="checkTime(this)">可选</li>'
				+'</ul>';
		}
		str += '</div>'
			+'</div>'
		 +'</div>';
		
		$(".express-form").append(str);
		$("#date-delivery").hide();
	}, function(data) {
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
	
}
//日历中“可选”的获得焦点事件后改变背景和字体颜色
function changebg(ele,i){
	if(i=="1"){
		$(ele).css("background","rgb(122, 189, 84)");
		$(ele).css("color","rgb(255, 255, 255)");
	}else{
		$(ele).css("background","rgb(237, 249, 230)");
		$(ele).css("color","rgb(122, 189, 84)");
	}
}

//选中日期后隐藏日历
function checkTime(ele){
	var val=$(ele).attr("val");
	$('#date-311').val(val);
	$('#date-delivery').hide();
}

//获取订单数据
function getOrderInfo(){
	var url = yoyo.memUrl+'/cart/getOrderInfo';
	var params = {};
	commonAjax(url, params, function(data) {
		if(data.content.isLogin==false){
//			alert("请先登录！");
			window.location.href = loginUrl;
			return;
		}else if(data.content.empty==true){
			alert("没有选中任何商品！");
			window.location.href = cartUrl;
			return;
		}else if(data.content.msg!=null&&data.content.msg!=''){
			alert(data.content.msg);
			history.back();
		}else if(data.content.content.length<=0){
			alert("该货品库存不足！");
			window.location.href = cartUrl;
			return;
		}else if(data.content!=null&&data.content.content.length>=1){
//			var companyId=data.content.content[0].companyId;
//			var companyIds=new Array();
//			var companyNames=new Array();
//			for(var i=0;i<data.content.content.length;i++){
//				if(i==0||data.content.content[i].companyId!=companyId){
//					companyId=data.content.content[i].companyId;
//					companyIds.push(data.content.content[i].companyId);
//					companyNames.push(data.content.content[i].storeName);
//					$('#part-order').append(''
//						+'<div class="shopping-list ABTest">'
//							+'<div class="goods-list">'
//								+'<h4 class="vendor_name_h" id="company_'+data.content.content[i].companyId+'">商家：'+data.content.content[i].storeName+'</h4>'
//								+'<div class="goods-items">'
//								+'</div> '
//							+'</div>'
//							+'<div class="clr"></div>'
//						+'</div>');
//				}
//				$('#company_'+data.content.content[i].companyId).next().append(''
//					+'<div class="goods-item ">'
//						+'<input name="goods_item_id" type="hidden" value="'+data.content.content[i].productId+'"/>'
//						+'<input name="goods_quantity" type="hidden" value="'+data.content.content[i].quantity+'"/>'
//						+'<div class="p-img">'	
//							+'<a target="_blank" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content.content[i].goodsId+'">'
////								+'<img src="'+_path+'/resources/images/cart/default_cart_img.jpg" width="82px" height="82px" alt="">'
//								+'<img src="'+goodsImgSrcPre+data.content.content[i].goodsImage+'" width="82px" height="82px" alt="">'
//							+'</a>'
//						+'</div>'
//						+'<div class="goods-msg">'
//							+'<div class="p-name">'
//								+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content.content[i].goodsId+'" target="_blank"> '+data.content.content[i].goodsName+' </a>'
//							+'</div>'
//							+'<div class="p-price">'
//								+'<strong>￥'+formatNumber(data.content.content[i].goodsPrice)+'</strong>'
//								+'<span class="ml20"> x'+data.content.content[i].quantity+' </span> '
//							+'</div>'
//						+'</div>'
//						+'<div class="clr"></div>'
//					+'</div>');
//			}
//			if(data.content.ag!=null){
//				var discType = data.content.ag.discType;
//				var credit =data.content.ag.credit;
//				var discount = 0;
//				if(discType=="minus"){
//					discount = credit;
//				}else if(discType=="discount"){
//					discount = parseFloat(data.content.sumPrice) * (1 - parseFloat(credit));
//				}
//				discount = parseFloat(discount) * parseFloat(data.content.content[0].quantity);
//				if(parseFloat(discount.toFixed(2)) > parseFloat(data.content.sumPrice.toFixed(2))){
//					discount = data.content.sumPrice;
//				}
//				$('#accessoryId').val(data.content.ag.id);
//				$('#warePriceId').text("￥"+data.content.sumPrice.toFixed(2));
//				$('#couponPriceId').text("-￥"+discount.toFixed(2));
//				$('#sumPayPriceId').text("￥"+(parseFloat(data.content.sumPrice) - parseFloat(discount)).toFixed(2));
//				$('.order-summary').show();
//				$('#payPriceId').text("￥"+(parseFloat(data.content.sumPrice) - parseFloat(discount)).toFixed(2));
//			}else{
//				$('#payPriceId').text('￥'+formatNumber(data.content.sumPrice));
//			}
//			
//			//门店信息
//			if(data.content.content.length>=1&&data.content.storeList.length>=1){
//				var storeList = data.content.storeList;
//			
//				for(var i=0;i<data.content.storeList.length;i++){
//					$('#consignee1').append(''
//							+'<div class="shopping-list ABTest">'
//								+'<div class="goods-list">'
//									+'<h4 class="vendor_name_h" id="vendor_'+companyIds[i]+'">商家：'+companyNames[i]+'</h4>'
//									+'<div class="goods-items">'
//										+'<ul class="ui-switchable-panel-main" style="top: 0px; position: relative;" id="consignee-list-'+companyIds[i]+'">'
//										+'</ul>'
//									+'</div>'
//								+'</div>'
//								+'<div class="clr"></div>'
//							+'</div>'
//							+'');
////					for(var j=0;j<data.content.storeList[i].length;j++){
////						if(j==0){
////							$('#consignee-list-'+companyIds[i]).append(''
////									+'<li class="ui-switchable-panel ui-switchable-panel-selected" style="display: list-item;height:auto;width:auto;margin-right: 5px;" id="consignee_index_'+data.content.storeList[i][j].storeId+'">'
////										+'<div class="consignee-item item-selected" consigneeid="" name="company_store" style="width:160px;height:90px;">'
////											+'<span limit="" title="">'+data.content.storeList[i][j].storeName+'</span><br/>'
////											+'<span class="addr-name" limit="" title="">'+data.content.storeList[i][j].area+'  '+data.content.storeList[i][j].addr+'</span><br/>'
////											+'<b></b>'
////										+'</div>'
////									+'</li>'
////									+'');
////						}else{
////							$('#consignee-list-'+companyIds[i]).append(''
////									+'<li class="ui-switchable-panel" style="display: list-item;height:auto;width:auto;margin-right: 5px;" id="consignee_index_'+data.content.storeList[i][j].storeId+'" selected="selected">'
////										+'<div class="consignee-item" id="consignee_index_div_137840405" consigneeid="" name="company_store" style="width:160px;height:90px;">'
////											+'<span limit="" title="">'+data.content.storeList[i][j].storeName+'</span><br/>'
////											+'<span class="addr-name" limit="" title="">'+data.content.storeList[i][j].area+'  '+data.content.storeList[i][j].addr+'</span><br/>'
////											+'<b></b>'
////										+'</div>'
////									+'</li>'
////									+'');
////						}
//							
//						
//						for(var j=0;j<storeList.length;j++){
//							if(storeList[j].storeId == data.content.content[i].storeId){
//								$('#consignee-list-'+companyIds[i]).append(''
//										+'<li class="ui-switchable-panel ui-switchable-panel-selected" style="display: list-item;height:auto;width:auto;margin-right: 5px;" id="consignee_index_'+data.content.storeList[j].storeId+'">'
//											+'<div class="consignee-item item-selected" consigneeid="" name="company_store" style="width:160px;height:90px;">'
//												+'<span limit="" title="">'+data.content.storeList[j].storeName+'</span><br/>'
//												+'<span class="addr-name" limit="" title="">'+data.content.storeList[j].area+'  '+data.content.storeList[j].addr+'</span><br/>'
//												+'<b></b>'
//											+'</div>'
//										+'</li>'
//										+'');
//								break;
//							}
//						}
////					}
//				}
//			}
			
			
			
			var companyId=data.content.content[0].companyId;
			var companyIds=new Array();
			var companyNames=new Array();
			var storeId=data.content.content[0].storeId;
			var storeIds=new Array();
			var storeNames=new Array();
			var productList = data.content.content;
			for(var i=0;i<productList.length;i++){
				if(i==0||productList[i].storeId!=storeId){
					storeId=productList[i].storeId;
					storeIds.push(productList[i].storeId);
					storeNames.push(productList[i].storeName);
					companyIds.push(productList[i].companyId);
					companyNames.push(productList[i].companyName);
					$('#part-order').append(''
						+'<div class="shopping-list ABTest">'
							+'<div class="goods-list">'
								+'<h4 class="vendor_name_h" id="store_'+productList[i].storeId+'">商家：'+productList[i].companyName+"("+productList[i].storeName+")"+'</h4>'
								+'<div class="goods-items">'
								+'</div> '
							+'</div>'
							+'<div class="clr"></div>'
						+'</div>');
				}
				$('#store_'+productList[i].storeId).next().append(''
					+'<div class="goods-item ">'
						+'<input name="goods_item_id" type="hidden" value="'+productList[i].productId+'"/>'
						+'<input name="goods_quantity" type="hidden" value="'+productList[i].quantity+'"/>'
						+'<div class="p-img">'	
							+'<a target="_blank" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+productList[i].goodsId+'">'
//								+'<img src="'+_path+'/resources/images/cart/default_cart_img.jpg" width="82px" height="82px" alt="">'
								+'<img src="'+goodsImgSrcPre+productList[i].goodsImage+'" width="82px" height="82px" alt="">'
							+'</a>'
						+'</div>'
						+'<div class="goods-msg">'
							+'<div class="p-name">'
								+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+productList[i].goodsId+'" target="_blank"> '+productList[i].productName+' </a>'
								+'<p title="'+productList[i].specInfo+'">'+productList[i].specInfo+'</p>'
							+'</div>'
							+'<div class="p-price">'
								+'<strong>￥' + productList[i].goodsPrice.toFixed(2) + '</strong>'
								+'<span class="ml20"> x'+productList[i].quantity+' </span> '
							+'</div>'
						+'</div>'
						+'<div class="clr"></div>'
					+'</div>');
			}
			if(data.content.ag!=null){
				var discType = data.content.ag.discType;
				var credit =data.content.ag.creditDouble;
				var discount = 0;
				if(discType=="minus"){
					discount = credit;
				}else if(discType=="discount"){
					discount = parseFloat(data.content.sumPrice) * (1 - parseFloat(credit));
				}
				discount = parseFloat(discount) * parseFloat(data.content.content[0].quantity);
				if(parseFloat(discount.toFixed(2)) > parseFloat(data.content.sumPrice.toFixed(2))){
					discount = data.content.sumPrice;
				}
				$('#accessoryId').val(data.content.ag.id);
				$('#warePriceId').text("￥"+data.content.sumPrice.toFixed(2));
				$('#couponPriceId').text("-￥"+discount.toFixed(2));
				$('#sumPayPriceId').text("￥"+(parseFloat(data.content.sumPrice) - parseFloat(discount)).toFixed(2));
				$('.order-summary').show();
				$('#payPriceId').text("￥"+(parseFloat(data.content.sumPrice) - parseFloat(discount)).toFixed(2));
			}else{
				$('#payPriceId').text('￥'+formatNumber(data.content.sumPrice));
			}
//			alert(storeNames.join(","));
//			alert(companyNames.join(".."));
			//门店信息
			if(productList.length>=1&&data.content.storeList.length>=1){
				var storeList = data.content.storeList;
			
				for(var j=0;j<storeIds.length;j++){
					for(var i=0;i<storeList.length;i++){
						if(storeList[i].storeId == storeIds[j]){
							$('#consignee1').append(''
									+'<div class="shopping-list ABTest">'
										+'<div class="goods-list">'
											+'<h4 class="vendor_name_h" id="vendor_'+storeIds[j]+'">商家：'+companyNames[j]+'('+storeNames[j]+')'+'</h4>'
											+'<div class="goods-items">'
												+'<ul class="ui-switchable-panel-main" style="top: 0px; position: relative;" id="consignee-list-'+storeIds[j]+'">'
													+'<li class="ui-switchable-panel ui-switchable-panel-selected" style="display: list-item;height:auto;width:auto;margin-right: 5px;" id="consignee_index_'+storeList[i].storeId+'">'
														+'<div class="consignee-item item-selected" consigneeid="" name="company_store" style="width:160px;height:90px;">'
															+'<span limit="" title="">'+storeList[i].storeName+'</span><br/>'
															+'<span class="addr-name" limit="" title="">'+storeList[i].area+'  '+storeList[i].addr+'</span><br/>'
															+'<b></b>'
														+'</div>'
													+'</li>'
												+'</ul>'
											+'</div>'
										+'</div>'
										+'<div class="clr"></div>'
									+'</div>'
									+'');
							break;
						}
						
					}
				}
				
			}
			
			
			
			$('div.consignee-item ').on('click',function(){
				var lis = $(this).parent().parent().children();
				for(var i=0;i<lis.length;i++){
					$(lis[i]).children().attr("class","consignee-item");
				}
				$(this).attr("class","consignee-item item-selected");
			});
			
		}
	}, function(data) {
		if(data.head==null){
			window.location.href = loginUrl;
		}
	});
}

function formatNumber(value) {     
    var xsd = value.toString().split(".");
    if(xsd.length==1){
     	value = value.toString()+".00";
     	return value;
    }
    if(xsd.length>1){
     	if(xsd[1].length<2){
      		value = value.toString()+"0";  
     	}else if(xsd[1].length>2){
     		value = xsd[0]+"."+xsd[1].substring(0,2);
     	}
    	return value;
    }
}
