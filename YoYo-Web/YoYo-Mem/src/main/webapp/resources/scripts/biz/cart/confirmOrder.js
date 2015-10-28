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
	
	$(".jdcheckbox").click(function (){
		var cur=$(this).attr("data-coupon").split(","),clickTag=$(this);
		$("input[name='couponckbox']:checked").each(function (){
			var coupon = $(this).attr("data-coupon").split(",");
			if(coupon[1]==cur[1]&&clickTag.val()!=$(this).val()){
				layer.alert("同一店铺优惠卷一次只能使用一张！",{title:false,closeBtn:false,icon:0});
				clickTag.prop("checked",false);
			}
		});

		var storePrice=0;
		var otherPrice=0;
		var totalQuanity=0;
		$(".goods-item").each(function(){
			if(cur[1]==$(this).find("input[name='goods_storeId']").val()){
				storePrice+=parseFloat($(this).find("input[name='goods_price']").val())*parseInt($(this).find("input[name='goods_quantity']").val());
				totalQuanity+=parseInt($(this).find("input[name='goods_quantity']").val());
			}else{
				otherPrice+=parseFloat($(this).find("input[name='goods_price']").val())*parseInt($(this).find("input[name='goods_quantity']").val());
			}
		});
		var totalPrice=(parseFloat(otherPrice)+parseFloat(storePrice)).toFixed(2);
		if('promotion_conditions_order_subtotalallgoods'==cur[2]&&storePrice<parseFloat(cur[3])){
			//总价
			layer.alert("商品总价未达到该优惠卷的使用条件！",{title:false,closeBtn:false,icon:0});
			clickTag.prop("checked",false);
			return;
		}else if('promotion_conditions_order_itemsquanityallgoods'==[2]){
			//总数量
			layer.alert("商品总数未达到该优惠卷的使用条件！",{title:false,closeBtn:false,icon:0});
			clickTag.prop("checked",false);
			return;
		}
		var couponPrice=0;
		$("input[name='couponckbox']:checked").each(function (y,box){
			var checked=$(this).attr("data-coupon").split(",");
			var storePrice=0;
			$(".goods-item").each(function(x,item){
				if(checked[1]==$(this).find("input[name='goods_storeId']").val()){
					storePrice+=parseFloat($(this).find("input[name='goods_price']").val())*parseInt($(this).find("input[name='goods_quantity']").val());
				}
			});
			if(checked[4]=='promotion_solutions_topercent'){
				//价格乘以X%折扣出售
				couponPrice=(parseFloat(storePrice-(storePrice*(checked[5]/100)))+parseFloat(couponPrice)).toFixed(2);
			}else if(checked[4]=='promotion_solutions_bypercent'){
				//价格减去X%折扣出售
				couponPrice=(parseFloat(storePrice-(storePrice-(storePrice*(checked[5]/100))))+parseFloat(couponPrice)).toFixed(2);
			}else if(checked[4]=='promotion_solutions_byfixed'){
				couponPrice=(parseFloat(storePrice-(storePrice-checked[5]))+parseFloat(couponPrice)).toFixed(2);
			}
		});
		$("#couponNum").html($("input[name='couponckbox']:checked").length);
		$("#couponPrice").html(parseFloat(couponPrice).toFixed(2));
		$("#couponPriceId").html(parseFloat(couponPrice).toFixed(2));
		$("#payPriceId").html("￥"+(parseFloat(totalPrice-couponPrice)).toFixed(2));
		$("#sumPayPriceId").html("￥"+(parseFloat(totalPrice-couponPrice)).toFixed(2));
	});
	
	$("#member-point-div .toggle-title").on("click",function(event){
		if($(this).parent().hasClass("toggle-active")){
			$(this).parent().removeClass("toggle-active");
			$(this).next().hide();
		}else{
			$(this).parent().addClass("toggle-active");
			$(this).next().show();
		}
		 event.preventDefault();		 
	});
	
	$("#orderCouponItem .toggle-title").on("click",function(event){
		if($(this).parent().hasClass("toggle-active")){
			$(this).parent().removeClass("toggle-active");
			$(this).next().hide();
		}else{
			$(this).parent().addClass("toggle-active");
			$(this).next().show();
		}
		 event.preventDefault();		 
	});
	
	$("#J_PointInput").keyup(function (){		
		var reg = new RegExp("^\[0-9][0-9]*$");
		if(!reg.test($(this).val())&&$(this).val().length>0){			
			$(".discharge-error").text("使用积分点数必须为大于或等于0的整数").show();
			return;
		}
		$(".discharge-error").hide();
		if(parseInt($(".usablePoints").text())<$(this).val()){
			$(this).val($(".usablePoints").text());
		}
		$("#J_Discharge").html(parseInt($(this).val())/10);
	});
	
	$('#order-submit').on('click',function(){		
		var divs = $('div[name="company_store"]');
		
		var payment = $('input[name="payment"]:checked').attr("payname");
		var date=$('#date-311').val();
		
		
		var storeIds=[],proIds=[],quantitys=[],appointment=[],goodsIds=[];
		var goods_storeId = $('input[name="goods_storeId"]');
		$.each(goods_storeId,function (x,input){
			storeIds.push($(input).val());
		});
		
		var goods_item_id = $('input[name="goods_item_id"]');
		$.each(goods_item_id,function (x,input){
			proIds.push($(input).val());
		});
		
		var goods_quantity = $('input[name="goods_quantity"]');
		$.each(goods_quantity,function (x,input){
			quantitys.push($(input).val());
		});
		
		var goods_appointment = $('input[name="goods_appointment"]');
		$.each(goods_appointment,function (x,input){
			if($(this).val()){
				appointment.push($(input).val());
			}
		});
 		
		var goods_id=$("input[name='goods_id']");
		$.each(goods_id,function (x,input){
			goodsIds.push($(input).val());
		});
		var coupons=[];
		$("input[name='couponckbox']:checked").each(function (){
			coupons.push($(this).val());
		});
		
		if($.trim($('textarea[name="remark"]').val()).length>100){
			layer.alert("订单备注最多输入100个字符！",{title:false,closeBtn:false,icon:0});
			return;
		}
			var params = {};
			params.storeId="["+storeIds.join(",")+"]";
			params.payment=payment;
			params.date=date;
			params.proIds="["+proIds.join(",")+"]";
			params.quantitys="["+quantitys.join(",")+"]";
			params.accessoryId = $('#accessoryId').val();
			params.remark = $('textarea[name="remark"]').val();
			params.appointment=appointment.join(",");
			params.goodsIds=goodsIds.join(",");
			params.orderType=$("#orderType").val();
			console.log($("#orderType").val()=='SCORE_BUY');
			if($("#orderType").val()=='SCORE_BUY'){
				if(parseInt($(".usablePoints").text())<parseInt($("#payPoint").text())){
					layer.alert("积分换购可用积分不足!当前可用积分："+$(".usablePoints").text()+"兑换所需积分："+$("#payPoint").text(),{title:false,closeBtn:false,icon:0});
					return;
				}
				params.quantity=quantitys[0];
			}
			if($("#member-point-div").hasClass("toggle-active")){
				params.point=$("#J_PointInput").val();
			}
			if($("#orderCouponItem").hasClass("toggle-active")){
				params.coupons=coupons.join(",");
			}			
			commonAjax(yoyo.memUrl+"/cart/submitOrder", params, function(data) {
				if(data.content.result==true){
					//选择到店支付，跳转订单列表页面
					if($('input:radio[name="payment"]:checked').attr('payid') == '1'){
						window.location.href = yoyo.memUrl+"/memberOrder/orderList";
					}else{
						window.location.href = yoyo.memUrl+"/pay/payOrder?orderId="+data.content.orderId;
					}
				}else{
					if(data.content.result==false){
						if(data.content.isBuyer==false){
							window.location.href = loginUrl;
							return;
						}
					}
					layer.alert(data.content.msg,{title:false,closeBtn:false,icon:0});
					if(data.content.isLogin==false){
						window.location.href = loginUrl;
					}
				}
			}, function(data) {
				if(data.head==null){
					//window.location.href = loginUrl;
				}
			});
	});
	
	$('#cartRetureUrl').on('click',function(){
		window.location.href=cartUrl;
	});
});


//初始化日历
function initCalendar(){
	var dData;
	var url = yoyo.memUrl+'/cart/getDays';
	var params = {};
	commonAjax(url, params, function(data) {
		dData=data.content;
		var str='<div id="date-delivery" style="position: absolute; top: 28px; left: 207px; width: 751px; height: 81px; display: block;" load="1">'
			+'<div class="inner" style="position: relative; width: 750px; height: 81px;">'
			+'<dl class="th">'
				+'<dt>时间段</dt>'
				+'<dd class="date">';
		for(var i=0;i<dData.length;i++){
			str+='<span row="-1" col="'+i+'" style="background: rgb(255, 255, 255);width:80px;">'+dData[i].date+'<br>'+dData[i].week+'</span>';
		}
		str    +=  	 '</dd>'
					+'<dd class="time">'
						+'<span row="0" col="-1" style="background: rgb(255, 255, 255);">请选择</span>'
					+'</dd>'
				+'</dl>'
				+'<div class="data" style="width: 650px;">'
					+'<ul>'
						+'<li class="checkbox disabled" data-status="0" row="0" col="0" val="" style="color: rgb(122, 189, 84); background: rgb(255, 255, 255);width:80px;"></li>'

					+'</ul>';
		for(var i=1;i<dData.length;i++){
			str+='<ul>'
					+'<li class="checkbox" data-status="1" row="0" col="'+i+'" val="  '+dData[i].date+'  ('+dData[i].week+')  " day="'+dData[i].date+'" range="" date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}" style="color: rgb(122, 189, 84); background: rgb(255, 255, 255);width:80px;" onmouseover="changebg(this,1)" onmouseout="changebg(this,0)" onclick="checkTime(this)">可选</li>'
				+'</ul>';
		}
		str += '</div>'
			+'</div>'
		 +'</div>';
		
		$(".express-form").append(str);
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
	return;
	var url = yoyo.memUrl+'/cart/getOrderInfo';
	var params = {};
	commonAjax(url, params, function(data) {
		if(data.content.result==false){
			if(data.content.isBuyer==false){
				window.location.href = loginUrl;
				return;
			}
		}else if(data.content.isLogin==false){
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
			alert("该货品无法购买！");
			window.location.href = cartUrl;
			return;
		}else if(data.content!=null&&data.content.content.length>=1){
			var companyId=data.content.content[0].companyId;
			var companyIds=new Array();
			var companyNames=new Array();
			var storeId=data.content.content[0].storeId;
			var storeIds=new Array();
			var storeNames=new Array();
			var productList = data.content.content;
			for(var i=0;i<productList.length;i++){
			
				if($('#store_'+productList[i].storeId).length<=0){
					storeIds.push(productList[i].storeId);
					storeNames.push(productList[i].storeName);
					companyNames.push(productList[i].companyName);
					$('#part-order').append(''
							+'<div class="shopping-list ABTest">'
								+'<div class="goods-list">'
									+'<h4 class="vendor_name_h" id="store_'+productList[i].storeId+'">商家：<a href="'+yoyo.portalUrl+'/shop/index?companyId='+productList[i].companyId+'" style="width:auto;">'+productList[i].companyName+"("+productList[i].storeName+")"+'</a></h4>'
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
								+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+productList[i].goodsId+'" target="_blank" style="width: 980px;display: block;overflow: hidden;height: 18px;"> '+productList[i].productName+' </a>'
								+'<p title="'+productList[i].specInfo+'" style="width: 980px;overflow: hidden;height: 18px;">'+productList[i].specInfo+'</p>'
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
				var credit =data.content.ag.credit;
				var discount = 0;
				if(discType=="minus"){
					discount = credit;
				}else if(discType=="discount"){
					discount = parseFloat(data.content.sumPrice) * (1 - parseFloat(credit));
				}
				console.log(data);				
				discount = parseFloat(discount) * parseFloat(data.content.content[0].quantity);
				if(parseFloat(discount.toFixed(2)) > parseFloat(data.content.sumPrice).toFixed(2)){
					discount = data.content.sumPrice;
				}
				$('#accessoryId').val(data.content.ag.id);
				$('#warePriceId').text("￥"+parseFloat(data.content.sumPrice).toFixed(2));
				$('#couponPriceId').text("-￥"+parseFloat(discount).toFixed(2));
				$('#sumPayPriceId').text("￥"+(parseFloat(data.content.sumPrice) - parseFloat(discount)).toFixed(2));
				$('.order-summary').show();
				$('#payPriceId').text("￥"+(parseFloat(data.content.sumPrice) - parseFloat(discount)).toFixed(2));
			}else{
				$('#payPriceId').text('￥'+formatNumber(data.content.sumPrice));
			}
			//门店信息
			if(productList.length>=1&&data.content.storeList.length>=1){
				var storeList = data.content.storeList;
			
				for(var j=0;j<storeIds.length;j++){
					for(var i=0;i<storeList.length;i++){
						if(storeList[i].storeId == storeIds[j]){
							$('#consignee1').append(''
									+'<div class="shopping-list ABTest">'
										+'<div class="goods-list">'
											+'<h4 class="vendor_name_h" id="vendor_'+storeIds[j]+'">商家：<a href="'+yoyo.portalUrl+'/shop/index?companyId='+productList[i].companyId+'" style="width:auto;">'+companyNames[j]+'('+storeNames[j]+')'+'</a></h4>'
											+'<div class="goods-items">'
												+'<ul class="ui-switchable-panel-main" style="top: 0px; position: relative;" id="consignee-list-'+storeIds[j]+'">'
													+'<li class="ui-switchable-panel ui-switchable-panel-selected" style="display: list-item;height:auto;width:auto;margin-right: 5px;" id="consignee_index_'+storeList[i].storeId+'">'
														+'<div class="consignee-item item-selected" consigneeid="" name="company_store" style="width:160px;height:90px;">'
															+'<span limit="" title="">'+storeList[i].storeName+'</span><br/>'
															+'<span class="addr-name" limit="" title="">'+(storeList[i].area!=null?storeList[i].area:'')+'  '+(storeList[i].addr!=null?storeList[i].addr:'')+'</span><br/>'
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
