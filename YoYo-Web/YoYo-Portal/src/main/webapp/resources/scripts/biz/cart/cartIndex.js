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
	
	$(".jdcheckbox").click(function (){
		if($(this).is(":checked")){
			$(this).closest(".shop-goods").find(":checkbox").each(function (){
				$(this).prop("checked",true);
				updateSumPrice();
			});
		}else{
			$(this).closest(".shop-goods").find(":checkbox").each(function (){
				$(this).prop("checked",false);
				updateSumPrice();
			});
		}
	});
	
	//去结算
	$('.toolbar_right .btn_area a').on('click',function(){
		var proItems=$("input[name^='proItem_']");
		var ids=new Array();
		var quantitys = new Array();
		if($('input[name^="proItem_"]:checked').length<=0){
			layer.alert("请选择需要结算的商品",{title:false,closeBtn:false,icon:0});
			return;			
		}		
		$.ajax({
		    url:yoyo.portalUrl+'/cart/unPayProduct',
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(head){
		    	if(head.retCode==yoyo.successCode){
		    		for(var i=0;i<proItems.length;i++){
		    			if(proItems[i].checked == true){
		    	   			ids.push(proItems[i].value);
		    	   			quantitys.push($(proItems[i]).parent().parent().parent().find('.p_quantity input').val());		    	   			
		    	   			if(head.content.toString().indexOf(proItems[i].value)>=0){
		    	   				layer.alert("“"+$(proItems[i]).attr("data-name")+"” 所在的订单未完成，请完成后再加入该商品",{title:false,closeBtn:false,icon:0});
		    	   				return;
		    	   			}
		    	  		}
		    		}
		    	
		    		var goodsId=[],appointment=[],index=[];
		    		$('input[name^="proItem_"]:checked').each(function(){
		    			var Id=$(this).attr("data-app").split(",")[0];
		    			var appointment=$(this).attr("data-app").split(",")[1];
		    			var i=$(this).attr("data-app").split(",")[2];
		    			if(Id&&Id.length>0){
		    				goodsId.push(Id);
		    			}
		    			if(appointment&&appointment.length>0){
		    				appointment.push(appointment);	
		    			}
		    			if(i&&i.length>0){
		    				index.push(i);
		    			}
	    			}); 
		    		var req={
		    				goodsId:goodsId.join(","),
		    				appointment:appointment.join(","),
		    				index:index.join(",")
			    		};
		    		$.ajax({type : 'POST', url : yoyo.portalUrl+'/cart/checkGoodsAppointment',data:req}).done(function(data) {
						if(data==yoyo.successCode){
							var params = {};
				    		params.proIdList = "["+ids.join(",")+"]";
				    		params.quantitys = "["+quantitys.join(",")+"]";
				    		window.location.href = yoyo.memUrl+'/cart/confirmOrder?productIds='+params.proIdList;
						}else{
							layer.alert("该时间段存在已预约满的商品,请选择其他时间段！",{title:false,closeBtn:false,icon:0});
						}
					});
		    		
		    	}else if(head.retCode=="NL001"){		    		
		    		window.location.href = loginUrl;	
		    	}else{
		    		layer.alert("提交结算失败！",{title:false,closeBtn:false,icon:0});
		    	}
		    }
		});		
	});
	
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
			updateSumPrice();
			//绑定指定店铺商品全选/全不选事件
			$('input[name^="checkShop_"]').on('change', function() {
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
			$("#CheckedAll1").on('click',function(){				
				selectAll(this.checked);
			});
			//绑定全部商品全选/全不选事件
			$('#CheckedAll2').on('click',function(){
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
						});
						layer.alert("关注成功！",{title:false,closeBtn:false,icon:1,end:loadWishList({refresh:true})});
						findWishList();
						$("#gz_select").find('.v_content_list').animate({ left : '0px'}, "slow"); //通过改变left值，跳转到第一个版面
						page = 1;
					}		
				}, function(data) {
					if(data.head==null){
						window.location.href = loginUrl;
					}else if(data.retCode=='000004'){
						layer.alert("该商品已关注过！",{title:false,closeBtn:false,icon:0});
					}
				});
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

