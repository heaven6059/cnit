var errorMsg="系统繁忙，请稍后再试！";
var loginUrl = yoyo.portalUrl+'/register/login';
var indexUrl = yoyo.portalUrl;
var confirmUrl = yoyo.memurl+'/cart/confirmOrder';
var goodsImgSrcPre = yoyo.imagesUrl;
var defaultGoodsImage="/resources/images/cart/default_cart_img.jpg";
var midPicSize = "120x120";
var smallPicSize = "50x50";
$(function(){
	var url = yoyo.portalUrl+'/cart/cartList';
	var params = {};
	commonAjax(url, params, function(data) {
		if(data==null||data.content==null||data.content.result=="empty"){
			emptyContainer();
		}else if(data.content.length>=1){
			var companyId=data.content[0].companyId;
			var companyCount=1;
			for(var i=0;i<data.content.length;i++){
				if(i==0||data.content[i].companyId!=companyId){
					companyId=data.content[i].companyId;
					companyCount=companyCount+1;
					$('#cart-list').append('<div id="cart-item-list-'+companyCount+'" class="cart-item-list">'
							+'<div class="cart-tbody" id="vender_'+data.content[i].companyId+'">'
							    +'<div class="shop">'
							        +'<div class="cart-checkbox">'
							            +'<input checked="checked" name="checkShop_'+data.content[i].companyId+'" class="checkShop" clstag="clickcart|keycount|xincart|cart_checkOn_shop" type="checkbox">'
							        +'</div>'
							        +'<a class="shop-name" href="http://yayajs.jd.com" id="venderId_'+data.content[i].companyId+'" clstag="clickcart|keycount|xincart|cart_shopName">'+data.content[i].storeName+'</a>'
							    +'</div>'
							    +'<div class="item-list">'
							    +'</div>'
							+'</div></div>');
				}
				$('#vender_'+data.content[i].companyId+' .item-list' ).append(''
						+'<div class="item-single  item-item item-selected  " id="product_'+data.content[i].productId+'">'
				            +'<div class="item-form">'
				                +'<div class="cell p-checkbox">'
								    +'<div class="cart-checkbox">'
								        +'<input p-type="'+data.content[i].productId+'_1" name="proItem_'+data.content[i].companyId+'" value="'+data.content[i].productId+'" checked="checked" data-bind="cbid" class="jdcheckbox" clstag="clickcart|keycount|xincart|cart_checkOn_sku" type="checkbox">'
										+'<span class="line-circle"></span>'
								    +'</div>'
								+'</div>'
								+'<div class="cell p-goods">'
								    +'<div class="goods-item">'
								        +'<div class="p-img">'
//								            +'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content[i].goodsId+'" target="_blank"><img alt="'+data.content[i].goodsName+'" clstag="clickcart|keycount|xincart|cart_sku_pic" src="'+_path+'/resources/images/cart/default_cart_img.jpg" width="82px" height="82px"></a>'
								        	+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content[i].goodsId+'" target="_blank"><img alt="'+data.content[i].productName+'" clstag="clickcart|keycount|xincart|cart_sku_pic" src="'+goodsImgSrcPre+data.content[i].goodsImage+'" width="82px" height="82px"></a>'
								        +'</div>'
								        +'<div class="item-msg">'
										    +'<div class="p-name" style="height:80px;">'
										    	+'<a clstag="clickcart|keycount|xincart|cart_sku_name" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content[i].goodsId+'" target="_blank" title="'+data.content[i].productName+'">'+data.content[i].productName+'</a>'
										    	+'<p title="'+data.content[i].specInfo+'">'+data.content[i].specInfo+'</p>'
										    +'</div>'
										    +'<div class="p-extend">'
//							                    +'<span class="promise" _giftcard="giftcard_1286519385"></span>'
//												+'<span class="promise" _yanbao="yanbao_1286519385_">'
							                    +'</span>'
										    +'</div>'
										+'</div>'
								    +'</div>'
								+'</div>'
								+'<div class="cell p-props">'
								+'</div>'
								+'<div class="cell p-price">'
								    +'<strong>'+formatNumber(data.content[i].goodsPrice)+'</strong>'
								+'</div>'
								+'<div class="cell p-quantity">'
								    +'<div class="quantity-form">'
								        +'<a href="javascript:void(0);" clstag="clickcart|keycount|xincart|cart_num_down" class="decrement" id="decrement_'+data.content[i].companyId+'_'+data.content[i].productId+'">-</a>'
								        +'<input autocomplete="off" class="itxt" value="'+data.content[i].quantity+'" back="'+data.content[i].quantity+'" id="changeQuantity_'+data.content[i].companyId+'_'+data.content[i].productId+'_1_1_0" type="text">'
								        +'<a href="javascript:void(0);" clstag="clickcart|keycount|xincart|cart_num_up" class="increment" id="increment_'+data.content[i].companyId+'_'+data.content[i].productId+'">+</a>'
							        +'</div>'
									/*+'<div class="ac ftx-03 quantity-txt" _stock="stock_1286519385">有货</div>'*/
								+'</div>'
								+'<div class="cell p-sum">'
									+'<strong>'+formatNumber(sumPrice(data.content[i].quantity,data.content[i].goodsPrice))+'</strong>'
								+'</div>'
				                +'<div class="cell p-ops">'
								    +'<a id="remove_'+data.content[i].productId+'" clstag="clickcart|keycount|xincart|cart_sku_del" data-name=""  class="cart-remove" href="javascript:void(0);">删除</a>'
					                +'<a href="javascript:void(0);" class="cart-follow" id="follow_87599_1286519385_1" clstag="clickcart|keycount|xincart|cart_sku_guanzhu">移到我的关注</a>'
								+'</div>'
				            +'</div>'
						    +'<div class="item-extra"></div><div class="item-line"></div>'
				        +'</div>'
						+'');
			}
			
			updateSumPrice();
			
			//绑定指定店铺商品全选/全不选事件
			$('.checkShop').on('change', function() {
				var name = $(this).attr("name");
				var companyId=name.split("_")[1];
				var pros=$("input[name=proItem_"+companyId+"]");
				if(this.checked==true){
					for(var i = 0;i < pros.length;i++){ 
						pros[i].checked = true;
					} 
				}else{
					for(var i = 0;i < pros.length;i++){ 
						pros[i].checked = false;
					} 
				}
				updateSumPrice();
			});
			
			//绑定全部商品全选/全不选事件
			$('#toggle-checkboxes_up').on('change',function(){
				selectAll(this.checked);
			});
			
			$('#toggle-checkboxes_down').on('change',function(){
				selectAll(this.checked);
			});
			
			//绑定删除商品事件
			$('.cart-remove').on('click', function() {
				if (!confirm("您确定要删除商品吗？")){
			 		return;
			   	}
				//获取选中的商品
				var proItems=$("input[name^='proItem_']");
				var ids=new Array();
				for(var i=0;i<proItems.length;i++){
					if(proItems[i].checked == true){
			   			ids.push(proItems[i].value);
			  		}
				}
				//获取被删除的商品
				var id = $(this).attr("id");
				var productId = id.split("_")[1];
				var url = yoyo.portalUrl+'/cart/deleteCart';
				var params = {};
				params.proIdString = "["+productId+"]";
				params.selectProIdString = "["+ids.join(",")+"]";
				commonAjax(url, params, function(data) {
					if(data.content.result==true){
						var parent = $('#product_'+productId).parent();
						$('#product_'+productId).remove();
						if($(parent).children().length<1){
							$(parent).parent().parent().remove();
						}
						updateSumPrice();
					}else if(data.content.result==false){
						alert(data.content.msg);
					}
				}, function(data) {
					easyuiMsg('失败', '请选择要操作的数据项!');
				})
			});
			
			//删除选中的商品
			$('.remove-batch').on('click', function() {
				var proItems=$("input[name^='proItem_']");
				var ids=new Array();
				for(var i=0;i<proItems.length;i++){
					if(proItems[i].checked == true){
			   			ids.push(proItems[i].value);
			  		}
				}
				if(ids.length>=1){
					if (!confirm("您确定要删除商品吗？")){
				 		return;
				   	}
					var url = yoyo.portalUrl+'/cart/deleteCart';
					var params = {};
					params.proIdString = "["+ids.join(",")+"]";
					params.selectProIdString = "[]";
					commonAjax(url, params, function(data) {
						if(data.content.result==true){
							for(var i=0;i<ids.length;i++){
								var parent = $('#product_'+ids[i]).parent();
								$('#product_'+ids[i]).remove();
								if($(parent).children().length<1){
									$(parent).parent().parent().remove();
								}
							}
							updateSumPrice();
						}else if(data.content.result==false){
							alert(data.content.msg);
						}
					}, function(data) {
						easyuiMsg('失败', '请选择要操作的数据项!');
					})
				}else{
					//没有选中任何商品
				}
			});
			
			//修改购买数量
			$('input[id^=changeQuantity]').on('change', function() {
				update_quantity(this);
			});
			
			//修改购买数量
			$('input[id^=changeQuantity]').on('keyup', function() {
				update_quantity(this);
			});
			
			//增加购买数量
			$('a.increment').on('click', function() {
				var quantity = $(this).prev().val();
				$(this).prev().val(parseInt(quantity)+1);
				$(this).prev().keyup();
			});
			
			//减少购买数量
			$('a.decrement').on('click', function() {
				var quantity = $(this).next().val();
				if(parseInt(quantity)<=1){
					$(this).next().val(1);
				}else{
					$(this).next().val(parseInt(quantity)-1);
				}
				$(this).next().keyup();
			});
			
			$('input[name^="proItem_"]').on('click', function() {
				updateSumPrice();
			});
		}else{
			emptyContainer();
		}
	}, function(data) {
		emptyContainer();
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
	
	//去结算
	$('.submit-btn').on('click',function(){
		var proItems=$("input[name^='proItem_']");
		var ids=new Array();
		for(var i=0;i<proItems.length;i++){
			if(proItems[i].checked == true){
	   			ids.push(proItems[i].value);
	  		}
		}
		var url = yoyo.memUrl+'/cart/putSelectPro';
		var params = {};
		params.proIdList = "["+ids.join(",")+"]";
		commonAjax(url, params, function(data) {
			if(data.content.isLogin=="false"){
				alert("请先登录！");
				window.location.href = loginUrl;
				return; 
			}
			if(data.content.result=="empty"){
				alert("请选择商品！");
				return;
			}
			window.location.href = confirmUrl;
		}, function(data) {
			if(data.head==null){
				window.location.href = loginUrl;
			}
		})
	})
});

function emptyContainer(){
	$('#main_container').empty();
	$('#main_container').append('<div class="w">'
									+'<div class="cart-empty">'
										+'<div class="message">'
											+'<ul>'
												+'<li class="txt">'
													+'购物车内暂时没有商品'
												+'</li>'
												+'<li>'
													+'<a href="'+indexUrl+'" class="ftx-05">去购物&gt;</a>'
												+'</li>'
											+'</ul>'
										+'</div>'
									+'</div>'
								+'</div>');
}

function selectAll(v){
	if(v==true){
		var checkboxs = $('.checkShop');
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
		}
		checkboxs = $('.jdcheckbox');
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
		}
	}else{
		$('input[type=checkbox]').attr("checked",false);
	}
	updateSumPrice();
}

function updateSumPrice(){
	var proItems=$("input[name^='proItem_']");
	if(proItems.length>=1){
		var ids=new Array();
		var quantity;
		var goodsPrice;
		var sumPrice=0;
		for(var i=0;i<proItems.length;i++){
			if(proItems[i].checked == true){
	   			ids.push(proItems[i].value);
	   			goodsPrice=$(proItems[i]).parent().parent().next().next().next().children().text();
	   			quantity=$(proItems[i]).parent().parent().next().next().next().next().children().find("input").val();
	   			sumPrice = formatNumber(parseFloat(sumPrice) + parseFloat(goodsPrice) * parseInt(quantity));
	  		}
		}
		$('#goodsSum').text(ids.length);
		$('#totalPrice').text('￥'+formatNumber(sumPrice));
	}else{
		emptyContainer();
	}
}

function update_quantity(input){
	var quantity = $(input).val();
	var productId = $(input).attr("id").split("_")[2];
	if(quantity!=null&&quantity!=''&&quantity!=undefined){
		var url = yoyo.portalUrl+'/cart/updateCart';
		var params = {};
		params.quantity = quantity;
		params.productId = productId;
		commonAjax(url, params, function(data) {
			if(data.content.result==false){
				$(input).val($(input).attr("back"));
				if(data.content.msg!=null&&data.content.msg!=''){
					alert(data.content.msg);
				}else{
					alert(errorMsg);
				}
			}else{
				$(input).val(data.content.quantity);
				$(input).attr("back",data.content.quantity);
				var price = $(input).parent().parent().prev().children().text();
				$(input).parent().parent().next().children().text(formatNumber(parseFloat(price)*parseInt(data.content.quantity)));
				updateSumPrice();
			}
		}, function(data) {
			alert(errorMsg);
		})
	}
}

//商品价格小计
function sumPrice(quantity,price){
	return parseInt(quantity)*parseFloat(price); 
}

//保留两位小数
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


