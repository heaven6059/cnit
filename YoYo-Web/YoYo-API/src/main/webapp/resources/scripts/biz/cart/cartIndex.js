var errorMsg="系统繁忙，请稍后再试！";
var loginUrl = yoyo.portalUrl+'/register/login';
var indexUrl = yoyo.portalUrl;
var confirmUrl = yoyo.memUrl+'/cart/confirmOrder';
var goodsImgSrcPre = yoyo.imagesUrl;
var defaultGoodsImage="/resources/images/cart/default_cart_img.jpg";
var midPicSize = "120x120";
var smallPicSize = "50x50";
var page = 1;
$(function(){
	//加载购物车商品
	findCartList();
	//加载我的关注、最近浏览
	findWishAndHistory();
	
	//去结算
	$('.toolbar_right .btn_area a').on('click',function(){
		var proItems=$("input[name^='proItem_']");
		var ids=new Array();
		var quantitys = new Array();
		for(var i=0;i<proItems.length;i++){
			if(proItems[i].checked == true){
	   			ids.push(proItems[i].value);
	   			quantitys.push($(proItems[i]).parent().parent().parent().find('.p_quantity input').val());
	  		}
		}
		var url = yoyo.memUrl+'/cart/putSelectPro';
		var params = {};
		params.proIdList = "["+ids.join(",")+"]";
		params.quantitys = "["+quantitys.join(",")+"]";
		commonAjax(url, params, function(data) {
			if(data.content.isLogin=="false"){
//				alert("请先登录！");
				window.location.href = loginUrl;
				return; 
			}
			if(data.content.result=="empty"){
				alert("请选择商品！");
				return;
			}
			window.location.href = confirmUrl;
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
			if(data.head==null){
				window.location.href = loginUrl;
			}
		})
	})
	
//	var page = 1;
//	var i = 3; 
	//猜你喜欢
	$(".tab_nav ul li").click(function(e){
        var currentId = $(this).attr("id");
		$(this).addClass("tab_cur").siblings("li").removeClass("tab_cur");
		$(".p_list_cont").removeClass("sort_show");
		$("#" + currentId + "_select").addClass("sort_show");
		$("#" + currentId + "_select").find('.v_content_list').animate({ left : '0px'}, "slow"); //通过改变left值，跳转到第一个版面
		page = 1;
    });	
	
	
	//向右 按钮
	$(".arrow_r").click(function(){    //绑定click事件
		 if($(this).parents(".tab_con").find('.sort_show').find(".v_content_list").find("ul").length>=2){
			 var $parent = $(this).parents(".tab_con");//根据当前点击元素获取到父元素
			 var $v_show = $parent.find(".v_content_list"); //寻找到“视频内容展示区域”
			 var $v_content = $parent.find(".p_list_cont"); //寻找到“视频内容展示区域”外围的DIV元素
			 var v_width = $v_content.width() ;
//			 var len = $v_show.find("ul").length;
//			 var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
			 var page_count = $(this).parents(".tab_con").find('.sort_show').find(".v_content_list").find("ul").length ;
			 if( !$v_show.is(":animated") ){    //判断“视频内容展示区域”是否正在处于动画
				  if( page == page_count ){  //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
					$v_show.animate({ left : '0px'}, "slow"); //通过改变left值，跳转到第一个版面
					page = 1;
				  }else{
					$v_show.animate({ left : '-='+v_width }, "slow");  //通过改变left值，达到每次换一个版面
					page++;
				 }
			 }
		 }
   });
	//向左 按钮
	$(".arrow_l").click(function(){    //绑定click事件
		 if($(this).parents(".tab_con").find('.sort_show').find(".v_content_list").find("ul").length>=2){
			 var $parent = $(this).parents(".tab_con");//根据当前点击元素获取到父元素
			 var $v_show = $parent.find(".v_content_list"); //寻找到“视频内容展示区域”
			 var $v_content = $parent.find(".p_list_cont"); //寻找到“视频内容展示区域”外围的DIV元素
			 var v_width = $v_content.width() ;
//			 var len = $v_show.find("ul").length;
//			 var page_count = Math.ceil(len / i) ;   //只要不是整数，就往大的方向取最小的整数
			 var page_count = $(this).parents(".tab_con").find('.sort_show').find(".v_content_list").find("ul").length ;
			 if( !$v_show.is(":animated") ){    //判断“视频内容展示区域”是否正在处于动画
				 if( page == 1 ){  //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
					$v_show.animate({ left : '-='+v_width*(page_count-1) }, "slow");
					page = page_count;
				}else{
					$v_show.animate({ left : '+='+v_width }, "slow");
					page--;
				}
			 }
		 }
   });

});

//加载购物车商品
function findCartList(){
	var url = yoyo.portalUrl+'/cart/cartList';
	var params = {};
	commonAjax(url, params, function(data) {
		if(data==null||data.content==null){
			emptyContainer();
		}else if(data.content.isBuyer==false){
			window.location.href = loginUrl;
		}else if(data.content.cartList!=null&&data.content.cartList.length>=1){
			var divClass = "";
			var storeStatus = '';
			var checkHtml = '';
			var quantityHtml = '';
			var sumHtml = '';
			for(var i=0;i<data.content.cartList.length;i++){
				var cartList = data.content.cartList;
				if($('#store_'+cartList[i].storeId).length>=1){
					divClass = "item_list i_list2";
				}else{
					$('.car_list').append('<div id="store_'+cartList[i].storeId+'">'
							+'<div class="t_checkbox" style="overflow:hidden;">'
								+'<input type="checkbox" class="CheckedAll" checked="checked" name="checkShop_'+cartList[i].storeId+'">'
								+'<a href="'+yoyo.portalUrl+'/shop/index?companyId='+cartList[i].companyId+'" style="width:auto;">'+cartList[i].companyName+'('+cartList[i].storeName+')'+'</a>'
							+'</div>'
					 +'</div>');
					divClass = "item_list";
				}
				if(cartList[i].store!=null&&cartList[i].store>=1){
					storeStatus="有货";
				}else{
					storeStatus="无货";
				}
//				alert(cartList[i].limitGoodsdown);
				if(cartList[i].gMarketable==0||cartList[i].gDisabled==1||cartList[i].pMarketable==0||cartList[i].pDisabled==1
						||cartList[i].limitStore==1||cartList[i].limitStoredown==1||cartList[i].limitGoodsdown==1
						||cartList[i].shopStatus==1 ||cartList[i].status==1 ||cartList[i].disabled==1){
					checkHtml='<span style="background-color: gray;color: white;" name="disabled_'+cartList[i].productId+'">失效</span>';
					quantityHtml = '';
					sumHtml = '';
				}else{
					checkHtml='<input class="cr" type="checkbox" checked="checked" name="proItem_'+cartList[i].storeId+'" value="'+cartList[i].productId+'">';
					quantityHtml = '<div class="quantity_form">'
										+'<a class="rem_btn fl" href="javascript:;">-</a>'
										+'<input class="fl itxt" value="'+cartList[i].quantity+'" back="'+cartList[i].quantity+'" id="changeQuantity_'+cartList[i].productId+'">'
										+'<a class="add_btn fr" href="javascript:;">+</a>'
								  +'</div>'
								  +'<div>'+storeStatus+'</div>';
					sumHtml = '<strong>'+sumPrice(cartList[i].quantity,cartList[i].goodsPrice).toFixed(2)+'</strong>';
				}
				$('#store_'+cartList[i].storeId).append(''
						+'<div class="'+divClass+'" id="product_'+cartList[i].productId+'">'
							+'<div class="item_single">'
						            +'<div class="p_checkbox fl">'
						                +'<div class="t_checkbox" style="width:25px;">'
										    +checkHtml
										+'</div>'
									+'</div>'
									+'<div class="p_goods fl">'
										+'<div class="goods_item">'
											+'<div class="p_img fl" style="margin-right:auto;">'
												+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+cartList[i].goodsId+'" target="_blank"><img src="'+goodsImgSrcPre+cartList[i].goodsImage+'" alt="'+cartList[i].productName+'" width="80" height="80"></a>'
											+'</div>'
											+'<div class="item_msg fr">'
												+'<div class="p_name" style="height: 80px;">'
													+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+cartList[i].goodsId+'" title="'+cartList[i].productName+'" target="_blank" style="height: 40px;overflow: hidden;display: block;">'+cartList[i].productName+'</a>'
													+'<p title="'+cartList[i].specInfo+'" style="height: 40px;overflow: hidden;">'+cartList[i].specInfo+'</p>'
												+'</div>'
												+'<div class="p_extend">'
		//											+'<a href="javascript:;">购买礼品包装</a>'
												+'</div>'
											+'</div>'
										+'</div>'
									+'</div>'
									+'<div class="p_price fl">'
										+'<strong>'+cartList[i].goodsPrice.toFixed(2)+'</strong>'
									+'</div>'		
									+'<div class="p_quantity fl">'
										+ quantityHtml
									+'</div>'
									+'<div class="p_sum fl">'
										+ sumHtml
									+'</div>'
									+'<div class="p_ops fr">'
										+'<a href="javascript:;" id="remove_'+cartList[i].productId+'" class="cart-remove">删除</a>'
										+'<a href="javascript:;" id="wishList_'+cartList[i].productId+'">移到我的关注</a>'
									+'</div>'
							+'</div>'
						+'</div>');
					
			}
			
			updateSumPrice();
			
			//绑定指定店铺商品全选/全不选事件
			$('input[name^="checkShop_"]').on('change', function() {
//				var name = $(this).attr("name");
//				var companyId=name.split("_")[1];
//				var pros=$("input[name=proItem_"+companyId+"]");
				var name = $(this).attr("name");
				var storeId=name.split("_")[1];
				var pros=$("input[name=proItem_"+storeId+"]");
				if(this.checked==true){
					for(var i = 0;i < pros.length;i++){ 
						pros[i].checked = true;
					} 
				}else{
					for(var i = 0;i < pros.length;i++){ 
						pros[i].checked = false;
					} 
				}
				
				ipt = $('input[id^="CheckedAll"]');
				if($('input[name^="proItem_"]').length==$('input[name^="proItem_"]:checked').length){
					for(var i = 0;i < ipt.length;i++){ 
						ipt[i].checked = true;
					}
				}else{
					for(var i = 0;i < ipt.length;i++){ 
						ipt[i].checked = false;
					}
				}
				
				updateSumPrice();
			});
			
			//绑定全部商品全选/全不选事件
			$('#CheckedAll1').on('change',function(){
				selectAll(this.checked);
			});
			//绑定全部商品全选/全不选事件
			$('#CheckedAll2').on('change',function(){
				selectAll(this.checked);
			});
			
			//绑定选中商品事件
			$('input[name^="proItem_"]').click(function(){
				var storeId = $(this).attr("name").split('_')[1];
				var ipt = $('input[name="checkShop_'+storeId+'"]');
				if($('input[name="proItem_'+storeId+'"]').length==$('input[name="proItem_'+storeId+'"]:checked').length){
					for(var i = 0;i < ipt.length;i++){ 
						ipt[i].checked = true;
					}
				}else{
					for(var i = 0;i < ipt.length;i++){ 
						ipt[i].checked = false;
					}
				}
				ipt = $('input[id^="CheckedAll"]');
				if($('input[name^="proItem_"]').length==$('input[name^="proItem_"]:checked').length){
					for(var i = 0;i < ipt.length;i++){ 
						ipt[i].checked = true;
					}
				}else{
					for(var i = 0;i < ipt.length;i++){ 
						ipt[i].checked = false;
					}
				}
				updateSumPrice();
			});
			//绑定删除商品事件
			$('.cart-remove').on('click', function() {
				if (!confirm("您确定要删除商品吗？")){
			 		return;
			   	}
				//获取被删除的商品
				var id = $(this).attr("id");
				var productId = id.split("_")[1];
				var url = yoyo.portalUrl+'/cart/deleteCart';
				var params = {};
				params.proIdString = "["+productId+"]";
				commonAjax(url, params, function(data) {
					if(data.content.result==true){
						var parent = $('#product_'+productId).parent();
						$('#product_'+productId).remove();
						if($(parent).find("div[id^='product_']").length<1){
							$(parent).remove();
						}
						updateSumPrice();
					}else if(data.content.result==false){
						if(data.content.isBuyer==false){
							window.location.href=loginUrl;
						}else{
							alert(data.content.msg);
						}
					}
				}, function(data) {
//					easyuiMsg('失败', '请选择要操作的数据项!');
				})
			});
			
			//删除选中的商品
			$('#deleteAll').on('click', function() {
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
//					params.selectProIdString = "[]";
					commonAjax(url, params, function(data) {
						if(data.content.result==true){
							for(var i=0;i<ids.length;i++){
								var parent = $('#product_'+ids[i]).parent();
								$('#product_'+ids[i]).remove();
								if($(parent).find("div[id^='product_']").length<1){
									$(parent).remove();
								}
							}
							updateSumPrice();
						}else if(data.content.result==false){
							if(data.content.isBuyer==false){
								window.location.href=loginUrl;
							}else{
								alert(data.content.msg);
							}
						}
					}, function(data) {
//						easyuiMsg('失败', '请选择要操作的数据项!');
					})
				}else{
					//没有选中任何商品
				}
			});
			
			//修改购买数量
			$('input[id^=changeQuantity]').on('change', function() {
				var quantity = $(this).val();
				if(quantity!=null&&quantity!=''){
					if(quantity==parseInt(quantity)){
						update_quantity(this);
					}else{
						$(this).val($(this).attr("back"));
					}
				}
			});
			
			//修改购买数量
			$('input[id^=changeQuantity]').on('keyup', function() {
				var quantity = $(this).val();
				if(quantity!=null&&quantity!=''){
					if(quantity==parseInt(quantity)){
						update_quantity(this);
					}else{
						$(this).val($(this).attr("back"));
					}
				}
			});
			
			//增加购买数量
			$('.add_btn').on('click', function() {
				var quantity = $(this).prev().val();
				$(this).prev().val(parseInt(quantity)+1);
				$(this).prev().keyup();
			});
			
			//减少购买数量
			$('.rem_btn').on('click', function() {
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
			
			//绑定移入关注事件
			$('a[id^="wishList_"]').on('click', function() {
				var productId = $(this).attr("id").split("_")[1];
				var url = yoyo.memUrl+'/productWishList/addWishList';
				var params = {};
				params.productId = "["+productId+"]";
				commonAjax(url, params, function(data) {
					if(data.content!=null&&data.content.result!=null){
						if(data.content.result==false){
							if((data.content.isLogin!=null&&data.content.isLogin==false)||data.content.isBuyer==false){
								window.location.href = loginUrl;
							}else{
								alert(data.content.msg);
							}
						}
					}else if(data.head.retCode == '000000'){
						var url = yoyo.portalUrl+'/cart/deleteCart';
						var params = {};
						params.proIdString = "["+productId+"]";
						commonAjax(url, params, function(data) {
							if(data.content.result==true){
								var parent = $('#product_'+productId).parent();
								$('#product_'+productId).remove();
								if($(parent).find("div[id^='product_']").length<1){
									$(parent).remove();
								}
								updateSumPrice();
							}else if(data.content.result==false){
								if(data.content.isBuyer==false){
									window.location.href=loginUrl;
								}else{
									alert(data.content.msg);
								}
								return;
							}
						}, function(data) {
//							easyuiMsg('失败', '请选择要操作的数据项!');
						})
						alert("关注成功");
						findWishList();
						$("#gz_select").find('.v_content_list').animate({ left : '0px'}, "slow"); //通过改变left值，跳转到第一个版面
						page = 1;
					}
				}, function(data) {
					if(data.head==null){
						window.location.href = loginUrl;
					}
				})
			});
		}else{
			emptyContainer();
		}
	}, function(data) {
		emptyContainer();
//		easyuiMsg('失败', '请选择要操作的数据项!');
	});
}

//加载我的关注，最近浏览
function findWishAndHistory(){
	var url = yoyo.portalUrl+'/cart/findWishAndHistory';
	var params = {};
	commonAjax(url, params, function(data) {
		if(data!=null&&data.content!=null){
			if(data.content.proWishList!=null&&data.content.proWishList.length>=1){
				var proWishList = data.content.proWishList;
				for(var i = 0;i<proWishList.length;i++){
					if(i%6==0){
						$('#gz_select .v_content_list').append("<ul></ul>");
					}
					$('#gz_select .v_content_list ul').eq(-1).append(''
							+'<li>'
								+'<div class="p_list_img">'
									+'<a href="'+yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+proWishList[i].goodsId+'" target="_blank"><img src="'+goodsImgSrcPre+proWishList[i].picturePath+'" width="130" height="130" ></a>'
								+'</div>'
								+'<div>'
									+'<h5 style="height:36px;overflow:hidden;"><a href="'+yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+proWishList[i].goodsId+'" target="_blank">'+proWishList[i].productName+'</a></h5>'
									+'<h3 class="red">￥'+proWishList[i].price.toFixed(2)+'</h3>'
									+'<p><a class="dd_color" href="javascript:;">(已有'+proWishList[i].commentCount+'人评价)</a></p>'
									+'<span><a class="p_btn" href="javascript:;" onclick="addCart('+proWishList[i].productId+')">加入购物车</a></span>'
								+'</div>'
							+'</li>'
							+'');
				}
				$('#gz').show();
				$('#extendPart').show();
				$('#gz_select').siblings(".p_list_cont").removeClass("sort_show");
				$('#gz_select').addClass("sort_show");
			}
			
			if(data.content.proHistoryList!=null&&data.content.proHistoryList.length>=1){
				var proHistoryList = data.content.proHistoryList;
				for(var i = proHistoryList.length-1;i>=0;i--){
					if((proHistoryList.length-1-i)%6==0){
						$('#liu_select .v_content_list').append("<ul></ul>");
					}
					$('#liu_select .v_content_list ul').eq(-1).append(''
							+'<li>'
								+'<div class="p_list_img">'
									+'<a href="'+yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+proHistoryList[i].goodsId+'" target="_blank"><img src="'+goodsImgSrcPre+proHistoryList[i].picturePath+'" width="130" height="130" ></a>'
								+'</div>'
								+'<div>'
									+'<h5 style="height:36px;overflow:hidden;"><a href="'+yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+proHistoryList[i].goodsId+'" target="_blank">'+proHistoryList[i].productName+'</a></h5>'
									+'<h3 class="red">￥'+proHistoryList[i].price.toFixed(2)+'</h3>'
									+'<p><a class="dd_color" href="javascript:;">(已有'+proHistoryList[i].commentCount+'人评价)</a></p>'
									+'<span><a class="p_btn" href="javascript:;" onclick="addCart('+proHistoryList[i].productId+')">加入购物车</a></span>'
								+'</div>'
							+'</li>'
							+'');
				}
				$('#liu').show();
				$('#extendPart').show();
				$('#liu_select').siblings(".p_list_cont").removeClass("sort_show");
				$('#liu_select').addClass("sort_show");
			}
		}
	}, function(data) {
		
	})
	
}

//加载我的关注
function findWishList(){
	var url = yoyo.portalUrl+'/cart/findWishList';
	var params = {};
	commonAjax(url, params, function(data) {
		if(data.content.proWishList!=null&&data.content.proWishList.length>=1){
			$('#gz_select .v_content_list').empty();
			var proWishList = data.content.proWishList;
			for(var i = 0;i<proWishList.length;i++){
				if(i%6==0){
					$('#gz_select .v_content_list').append("<ul></ul>");
				}
				$('#gz_select .v_content_list ul').eq(-1).append(''
						+'<li>'
							+'<div class="p_list_img">'
								+'<a href="'+yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+proWishList[i].goodsId+'" target="_blank"><img src="'+goodsImgSrcPre+proWishList[i].picturePath+'" width="130" height="130" ></a>'
							+'</div>'
							+'<div>'
								+'<h5 style="height:36px;overflow:hidden;"><a href="'+yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+proWishList[i].goodsId+'" target="_blank">'+proWishList[i].productName+'</a></h5>'
								+'<h3 class="red">￥'+proWishList[i].priceDouble.toFixed(2)+'</h3>'
								+'<p><a class="dd_color" href="javascript:;">(已有'+proWishList[i].commentCount+'人评价)</a></p>'
								+'<span><a class="p_btn" href="javascript:;" onclick="addCart('+proWishList[i].productId+')">加入购物车</a></span>'
							+'</div>'
						+'</li>'
						+'');
			}
			$('#gz').show();
			$('#extendPart').show();
			$('#gz_select').siblings(".p_list_cont").removeClass("sort_show");
			$('#gz_select').addClass("sort_show");
		}
	}, function(data) {
		
	})
}

//将选中商品移入我的关注
function addWishList(){
	
	var proItems=$("input[name^='proItem_']");
	var ids=new Array();
	for(var i=0;i<proItems.length;i++){
		if(proItems[i].checked == true){
   			ids.push(proItems[i].value);
  		}
	}
	if(ids.length>=1){
		var url = yoyo.memUrl+'/productWishList/addWishList';
		var params = {};
		params.productId = "["+ids.join(",")+"]";
		commonAjax(url, params, function(data) {
			if(data.content!=null&&data.content.result!=null){
				if(data.content.result==false){
					if((data.content.isLogin!=null&&data.content.isLogin==false)||data.content.isBuyer==false){
						window.location.href = loginUrl;
					}else{
						alert(data.content.msg);
					}
				}
			}else if(data.head.retCode == '000000'){
				var url = yoyo.portalUrl+'/cart/deleteCart';
				var params = {};
				params.proIdString = "["+ids.join(",")+"]";
				commonAjax(url, params, function(data) {
					if(data.content.result==true){
						for(var i=0;i<ids.length;i++){
							var parent = $('#product_'+ids[i]).parent();
							$('#product_'+ids[i]).remove();
							if($(parent).find("div[id^='product_']").length<1){
								$(parent).remove();
							}
						}
						updateSumPrice();
					}else if(data.content.result==false){
						if(data.content.isBuyer==false){
							window.location.href=loginUrl;
						}else{
							alert(data.content.msg);
						}
						return;
					}
				}, function(data) {
//					easyuiMsg('失败', '请选择要操作的数据项!');
				})
				alert("关注成功");
				findWishList();
				$("#gz_select").find('.v_content_list').animate({ left : '0px'}, "slow"); //通过改变left值，跳转到第一个版面
				page = 1;
			}
		}, function(data) {
			if(data.head==null){
				window.location.href = loginUrl;
			}
		})
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
			if(data.content.isBuyer==false){
				window.location.href = loginUrl;
			}else{
				alert(data.content.msg);
			}
		}else{
			alert(errorMsg);
		}
	}, function(data) {
	});
}


function emptyContainer(){
	$('.cartList').empty();
	$('.cartList').append('<div class="w">'
									+'<div class="cart-empty">'
										+'<div class="message">'
											+'<ul>'
												+'<li class="txt" style="width:auto;">'
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
		var checkboxs = $('input[type=checkbox]');
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
		}
	}else{
		$('input[type=checkbox]').attr("checked",false);
	}
	updateSumPrice();
}

function updateSumPrice(){
	var itemList = $('div.item_list');
	if(itemList.length>=1){
		var proItems=$("input[name^='proItem_']");
		if(proItems.length>=1){
			var ids=new Array();
			var quantity = 0;
			var goodsPrice;
			var sumPrice=0;
			var totalQuantity = 0;
			for(var i=0;i<proItems.length;i++){
				if(proItems[i].checked == true){
		   			ids.push(proItems[i].value);
		   			goodsPrice=$(proItems[i]).parent().parent().next().next().children().text();
		   			quantity=$(proItems[i]).parent().parent().next().next().next().find("input").val();
		   			sumPrice = (parseFloat(sumPrice) + parseFloat(goodsPrice) * parseInt(quantity)).toFixed(2);
		  		}
				totalQuantity += parseInt(quantity);
			}
			$('.amount_sum').find(".red").text(ids.length);
			$('.price_sum .sumPrice .red').text('￥'+(parseFloat(sumPrice).toFixed(2)));
			$('#cartSize').text(totalQuantity);
		}else{
//			emptyContainer();
			$('#cartSize').text(0);
		}
	}else{
		emptyContainer();
		$('#cartSize').text(0);
	}
	
}

function update_quantity(input){
	var quantity = $(input).val();
	var productId = $(input).attr("id").split("_")[1];
	if(quantity!=null&&quantity!=''&&quantity!=undefined){
		var url = yoyo.portalUrl+'/cart/updateCart';
		var params = {};
		params.quantity = quantity;
		params.productId = productId;
		commonAjax(url, params, function(data) {
			if(data.content.result==false){
				if(data.content.isBuyer==false){
					window.location.href=loginUrl;
				}else{
					$(input).val($(input).attr("back"));
					if(data.content.msg!=null&&data.content.msg!=''){
						alert(data.content.msg);
					}else{
						alert(errorMsg);
					}
				}
			}else{
				$(input).val(data.content.quantity);
				$(input).attr("back",data.content.quantity);
				var price = $(input).parent().parent().prev().children().text();
				$(input).parent().parent().next().children().text((parseFloat(price)*parseInt(data.content.quantity)).toFixed(2));
				updateSumPrice();
			}
		}, function(data) {
			$(input).val($(input).attr("back"));
			if(data.content.msg!=null&&data.content.msg!=''){
				alert(data.content.msg);
			}else{
				alert(errorMsg);
			}
		})
	}
}

//商品价格小计
function sumPrice(quantity,price){
	return parseInt(quantity)*parseFloat(price); 
}

//删除失效商品
function removeDisabled(){
	var disabledSpan = $('span[name^="disabled_"]');
	var ids = new Array();
	for(var i=0;i<disabledSpan.length;i++){
		ids.push($(disabledSpan[i]).attr("name").split("_")[1]);
	}
	if(ids!=null&&ids.length>=1){
		if (!confirm("您确定要清空失效商品吗？")){
	 		return;
	   	}
		var url = yoyo.portalUrl+'/cart/deleteCart';
		var params = {};
		params.proIdString = "["+ids.join(",")+"]";
//		params.selectProIdString = "[]";
		commonAjax(url, params, function(data) {
			if(data.content.result==true){
				for(var i=0;i<ids.length;i++){
					var parent = $('#product_'+ids[i]).parent();
					$('#product_'+ids[i]).remove();
					if($(parent).find("div[id^='product_']").length<1){
						$(parent).remove();
					}
				}
				updateSumPrice();
			}else if(data.content.result==false){
				if(data.content.isBuyer==false){
					window.location.href=loginUrl;
				}else{
					alert(data.content.msg);
				}
			}
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
		})
	}
}

