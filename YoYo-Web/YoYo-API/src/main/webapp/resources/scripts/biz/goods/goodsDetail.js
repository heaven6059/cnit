//var loginUrl=yoyo.memUrl+'/register/login';
//var indexUrl=yoyo.memUrl;//店铺不存在转跳
var loginUrl = yoyo.portalUrl+'/register/login';
var indexUrl = yoyo.portalUrl;
//var confirmUrl = yoyo.memUrl+'/cart/confirmOrder';
//var cartUrl = yoyo.memUrl+'/cart/index';
var confirmUrl = yoyo.memUrl+'/cart/confirmOrder';
var cartUrl = yoyo.portalUrl+'/cart/index';
//var goodsImgSrcPre = yoyo.shopUrl;
var goodsImgSrcPre = yoyo.imagesUrl;
var defaultGoodsImage="/resources/images/cart/default_cart_img.jpg";
var productList ;
var isMarketable = 0 ;
var accessoryList;
var goodsList;
var errorMsg = "系统繁忙，请稍后再试";
//var bigPicSize = "500x500";
var midPicSize = "120x120";
var smallPicSize = "50x50";
$(function(){
	$(".extInfo").find("img").each(function (x,item){$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));});
	if($('.jqzoom').attr("src")!=null&&$('.jqzoom').attr("src")!=''){
		$('.jqzoom').attr("src",goodsImgSrcPre+$('.jqzoom').attr("src"));
		$('.jqzoom').attr("alt",goodsImgSrcPre+$('.jqzoom').attr("alt"));
		$('.jqzoom').parent().next().find('ul li img').attr("src",$('.jqzoom').attr("src"));
		$('.jqzoom').parent().next().find('ul li img').attr("alt",$('.jqzoom').attr("alt"));
	}
	if($('#spec-n1').find('img').attr("src")!=null&&$('#spec-n1').find('img').attr("src")!=''){
		$('#spec-n1').find('img').attr("src",goodsImgSrcPre+$('#spec-n1').find('img').attr("src"));
		$('#spec-list').find('ul li img').attr("src",$('#spec-n1').find('img').attr("src"));
	}
	var carId = $('#car_id').val();
	if(!(parseInt(carId)>0)){
		$('#priceName').text("优优价：");
	}
	//加载对比栏
	initCompareItems();
	//加载商品详情
	findGoodsDetail();
	//加载优惠券信息和商品属性信息
	//findCouponsAndAttribute();
//	//加载商品参数配置
//	findCarData();
//	//加载热销排行榜
//	findTopSales();
//	//加载参数配置
//	findCarParam();
//	//加载商品优惠套餐信息和相关商品信息
//	findAccessoryAndRelated();
//	//加载商品评论
//	findGoodsComment();
//	//加载商品咨询
//	findGoodsDiscuss();
//	//更新商品信息（浏览次数等）
//	updateGoodsInfo();
	//加入购物车
	$('#addCart').on('click', function(){
//		alert("addCart..");
		if($('#marketable').val()=='0'){
			alert("该商品已下架");
			return;
		}
		var store = $('.g_num_r span').text();
		if(parseInt(store)<=0){
			alert("该货品暂时缺货，无法加入购物车！");
			return;
		}
		var quantity = $('.g_num .ipt input').val();
		if(quantity==null||quantity==''||parseInt(quantity)!=quantity){
			alert("请输入购买数量");
			return;
		}
//		var chooseSpecDivs = $('.g_cs_b').find('div[name^="choose_spec_"]');
//		if($(chooseSpecDivs).length>=1){
//			if($(chooseSpecDivs).find("li.g_cur").length == $(chooseSpecDivs).length){
//				var selectSpecDesc='';
//				for(var i=0;i<chooseSpecDivs.length;i++){
//					if($(chooseSpecDivs[i]).find("li.g_cur").length>=1){
//						selectSpecDesc+=$(chooseSpecDivs[i]).attr("name").split("_")[2];
//						selectSpecDesc+="|";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_name').attr("title");
//						selectSpecDesc+=":";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[0];
//						selectSpecDesc+="|";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[1];
//						if(i!=chooseSpecDivs.length-1){
//							selectSpecDesc+=",";
//						}
//					}
//				}
//				var productId = '';
//				for(var i=0;i<productList.length;i++){
//					if(productList[i].specDesc == selectSpecDesc){
//						productId = productList[i].productId;
//						break;
//					}
//				}
//				if(productId!=''){
//					var url = yoyo.portalUrl+'/cart/addCart';
//					var params = {};
//					params.quantity=$('.g_num .ipt input').val();
//					params.productId = productId;
//					commonAjax(url, params, function(data) {
//						if(data.content.result==true){
////							alert("加入购物车成功");
////							var url = yoyo.memUrl+'/cart/findCartSize';
////							var params = {};
////							commonAjax(url, params, function(data) {
////								if(data!=null && data.content!=null && data.content.cartSize!=null){
////									$('#cartSize').text(data.content.cartSize);
////								}
////							}, function(data) {
////								easyuiMsg('失败', '请选择要操作的数据项!');
////							});
//							window.location.href = yoyo.portalUrl+"/cart/toAddSuccess?productId="+productId;
//						}else if(data.content.result==false){
//							alert(data.content.msg);
//						}else{
//							alert(errorMsg);
//						}
//					}, function(data) {
////						easyuiMsg('失败', '请选择要操作的数据项!');
////						alert(errorMsg);
//					});
//				}else{
//					alert("该货品不存在，请重新选择商品规格");
//				}
//			}else{
//				alert("请选择商品规格");
//			}
//		}else{
//			var url = yoyo.portalUrl+'/cart/addCart';
//			var params = {};
//			params.quantity=$('.g_num .ipt input').val();
//			params.productId = productList[0].productId;
//			commonAjax(url, params, function(data) {
//				if(data.content.result==true){
////					alert("加入购物车成功");
////					var url = yoyo.memUrl+'/cart/findCartSize';
////					var params = {};
////					commonAjax(url, params, function(data) {
////						if(data!=null && data.content!=null && data.content.cartSize!=null){
////							$('#cartSize').text(data.content.cartSize);
////						}
////					}, function(data) {
////						easyuiMsg('失败', '请选择要操作的数据项!');
////					});
//					window.location.href = yoyo.portalUrl+"/cart/toAddSuccess?productId="+productList[0].productId;
//				}else if(data.content.result==false){
//					alert(data.content.msg);
//				}else{
//					alert(errorMsg);
//				}
//			}, function(data) {
////				easyuiMsg('失败', '请选择要操作的数据项!');
////				alert(errorMsg);
//			});
//		}
		
		var productId = findProductId();
		if(productId!=null&&productId!=''){
			var url = yoyo.portalUrl+'/cart/addCart';
			var params = {};
			params.quantity=$('.g_num .ipt input').val();
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
		}else{
			alert("该货品不存在，请重新选择商品规格");
		}
	});
	
	//立即购买
	$('.g_buy').on('click', function(){
		if($('#marketable').val()=='0'){
			alert("该商品已下架");
			return;
		}
		var store = $('.g_num_r span').text();
		if(parseInt(store)<=0){
			alert("该货品暂时缺货，无法购买！");
			return;
		}
		var quantity = $('.g_num .ipt input').val();
		if(quantity==null||quantity==''||parseInt(quantity)!=quantity){
			alert("请输入购买数量");
		}
//		var chooseSpecDivs = $('.g_cs_b').find('div[name^="choose_spec_"]');
//		if($(chooseSpecDivs).length>=1){
//			if($(chooseSpecDivs).find("li.g_cur").length == $(chooseSpecDivs).length){
//				var selectSpecDesc='';
//				for(var i=0;i<chooseSpecDivs.length;i++){
//					if($(chooseSpecDivs[i]).find("li.g_cur").length>=1){
//						selectSpecDesc+=$(chooseSpecDivs[i]).attr("name").split("_")[2];
//						selectSpecDesc+="|";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_name').attr("title");
//						selectSpecDesc+=":";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[0];
//						selectSpecDesc+="|";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[1];
//						if(i!=chooseSpecDivs.length-1){
//							selectSpecDesc+=",";
//						}
//					}
//				}
//				var productId = '';
//				for(var i=0;i<productList.length;i++){
//					if(productList[i].specDesc == selectSpecDesc){
//						productId = productList[i].productId;
//						break;
//					}
//				}
//				if(productId!=''){
//					var url = yoyo.memUrl+'/cart/putSelectPro';
//					var params = {};
//					params.quantityList = "["+$('.g_num .ipt input').val()+"]";
//					params.proIdList = "["+productId+"]";
//					commonAjax(url, params, function(data) {
//						if(data.content.isLogin=="false"){
////							alert("请先登录！");
//							window.location.href = loginUrl;
//							return; 
//						}
//						window.location.href = confirmUrl;
//					}, function(data) {
////						easyuiMsg('失败', '请选择要操作的数据项!');
////						alert(errorMsg);
//						if(data.head==null){
//							window.location.href = loginUrl;
//						}
//						
//					});
//				}else{
//					alert("该货品不存在，请重新选择商品规格");
//				}
//			}else{
//				alert("请选择商品规格");
//			}
//		}else{
//			var url = yoyo.memUrl+'/cart/putSelectPro';
//			var params = {};
//			params.quantityList = "["+$('.g_num .ipt input').val()+"]";
//			params.proIdList = "["+productList[0].productId+"]";
//			commonAjax(url, params, function(data) {
//				if(data.content.isLogin=="false"){
////					alert("请先登录！");
//					window.location.href = loginUrl;
//					return; 
//				}
//				window.location.href = confirmUrl;
//			}, function(data) {
////				easyuiMsg('失败', '请选择要操作的数据项!');
////				alert(errorMsg);
//				if(data.head==null){
//					window.location.href = loginUrl;
//				}
//			});
//		}
		var productId = findProductId();
		if(productId!=null&&productId!=''){
			var url = yoyo.memUrl+'/cart/putSelectPro';
			var params = {};
			params.quantityList = "["+$('.g_num .ipt input').val()+"]";
			params.proIdList = "["+productId+"]";
			commonAjax(url, params, function(data) {
				if(data.content.isLogin=="false"){
					window.location.href = loginUrl;
					return; 
				}
				window.location.href = confirmUrl;
			}, function(data) {
				if(data.head==null){
					window.location.href = loginUrl;
				}
			});
		}
	});
	
	//立即购买优惠套装
	$('.g_buy_m .fl').on('click', function(){
		var store = $('.g_num_r span').text();
		if(parseInt(store)<=0){
			alert("该货品暂时缺货，无法购买！");
			return;
		}
		if($('#marketable').val()=='0'){
			alert("该商品已下架");
			return;
		}
		var productId = null;
//		var chooseSpecDivs = $('.g_cs_b').find('div[name^="choose_spec_"]');
//		if($(chooseSpecDivs).length>=1){
//			if($(chooseSpecDivs).find("li.g_cur").length == $(chooseSpecDivs).length){
//				var selectSpecDesc='';
//				for(var i=0;i<chooseSpecDivs.length;i++){
//					if($(chooseSpecDivs[i]).find("li.g_cur").length>=1){
//						selectSpecDesc+=$(chooseSpecDivs[i]).attr("name").split("_")[2];
//						selectSpecDesc+="|";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_name').attr("title");
//						selectSpecDesc+=":";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[0];
//						selectSpecDesc+="|";
//						selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[1];
//						if(i!=chooseSpecDivs.length-1){
//							selectSpecDesc+=",";
//						}
//					}
//				}
//				for(var i=0;i<productList.length;i++){
//					if(productList[i].specDesc == selectSpecDesc){
//						productId = productList[i].productId;
//						break;
//					}
//				}
//			}
//		}
//		if(productId==null){
//			productId = productList[0].productId;
//		}
		
		productId = findProductId();
		var accessoryId = $('li.g_nav_cur').attr("name").split("_")[1];
		var url = yoyo.memUrl+'/cart/putSelectPro';
		var params = {};
		params.accessoryId = accessoryId;
		params.productId = productId;
		commonAjax(url, params, function(data) {
			if(data.content.isLogin=="false"){
//				alert("请先登录！");
				window.location.href = loginUrl;
				return; 
			}else{
				if(data.content.result == "false"){
					if(data.content.msg != null && data.content.msg != ''){
						alert(data.content.msg);
						window.location.reload(true);
					}
				}
			}
			window.location.href = confirmUrl;
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
//			alert(errorMsg);
			if(data.head==null){
				window.location.href = loginUrl;
			}
		});
	});
	
	
	$('.b_left').on('click', function(){
		prePic();
	});
	
	$('.b_right').on('click', function(){
		nextPic();
	});
	
	/*//产品描述评论切换
	$(".stor h2").click(function(e){
        var currentId = $(this).attr("id");
		$(this).addClass("g_select").siblings("h2").removeClass("g_select");
		$(".g_cont").removeClass("g_open");
		$("#"+currentId +"_select").addClass("g_open");
    });*/
	
	
	//产品描述评论切换
	$(".stor h2").click(function(e){
        var currentId = $(this).attr("id");
		$(this).addClass("g_select").siblings("h2").removeClass("g_select");
		$(".g_cont").removeClass("g_open");
		$("#"+currentId +"_select").addClass("g_open");
        if("par"==$(this).attr("id"))
            isCangshu = true;
        else
            isCangshu = false;
    });
	
	//左侧参数菜单
	$("#navScrollLeft ul li a").click(function(t){
		clickFlag = true;
		var scrollTop = $(this).attr("scroll-top");//scrollTop值
		$(window).scrollTop(scrollTop);
		$("#navScrollLeft ul li a").removeClass("active");
		$(this).addClass("active");
		//console.log($(this).html()+"-"+scrollTop);
		
	});
	
	var leftMenuTop = [0,503,908,1582,1682,1902,2086,2491,2981,3200,3540,4060,4457,4748,5196,5380];//左侧菜单scrollTop值
	var currentSTop =0;
	var c_index = 0;//当前定位在左侧第几个菜单 0第一个
	var next_index = 1;
	var clickFlag = false; //是否是点击
	$(window).scroll(function(){
		//console.log(currentSTop+" - "+c_index+"- "+next_index );	
		currentSTop = $(window).scrollTop();
		if(currentSTop>leftMenuTop[next_index] ){
			if(next_index+1 <= leftMenuTop.length){
				c_index++;
				next_index++;
				if(!clickFlag){
					$("#navScrollLeft ul li a").removeClass("active");
					$("#navScrollLeft ul li a").eq(c_index).addClass("active");
				}
				
			}
			
		}else if(currentSTop < leftMenuTop[c_index] && currentSTop >=  leftMenuTop[0]) {
				c_index--;
				next_index--;
				if(!clickFlag){
					$("#navScrollLeft ul li a").removeClass("active");
					$("#navScrollLeft ul li a").eq(c_index).addClass("active");
				}
		}
		
		clickFlag = false;
		
		
	});

    var isCangshu = false; //是否是参数页
    var currentSTop = 0; //滚动条top值
    $(window).scroll(function(){
       if(isCangshu){
            currentSTop = $(window).scrollTop();
            if(currentSTop <480){
                $("#navScrollLeft").css({"position":"absolute","top":0});
            }else{
                $("#navScrollLeft").css({"position":"fixed","top":480});
            }
                  
            //console.log(currentSTop);
       }
    });
});

//获取当前选中的货品id
function findProductId(){
	var productId = '';
	var chooseSpecDivs = $('.g_cs_b').find('div[name^="choose_spec_"]');
	if($(chooseSpecDivs).length>=1){
		if($(chooseSpecDivs).find("li.g_cur").length == $(chooseSpecDivs).length){
			var selectSpecDesc='';
			for(var i=0;i<chooseSpecDivs.length;i++){
				if($(chooseSpecDivs[i]).find("li.g_cur").length>=1){
					selectSpecDesc+=$(chooseSpecDivs[i]).attr("name").split("_")[2];
					selectSpecDesc+="|";
					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_name').attr("title");
					selectSpecDesc+=":";
//					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[0];
					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("dataid");
					selectSpecDesc+="|";
//					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[1];
					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("title");
					if(i!=chooseSpecDivs.length-1){
						selectSpecDesc+=",";
					}
				}
			}
			for(var i=0;i<productList.length;i++){
//				if(productList[i].marketable != '0' && productList[i].limitGoodsdown != '1' && (parseInt(productList[i].store)>=1)){
				if(productList[i].marketable != '0' && productList[i].limitGoodsdown != '1'){
					if(productList[i].specDesc == selectSpecDesc){
						productId = productList[i].productId;
						break;
					}
				}
			}
		}
	}
	if(productId==''&&productList!=null&&productList.length>=1){
		for(var i=0;i<productList.length;i++){
//			if(productList[i].marketable != '0' && productList[i].limitGoodsdown != '1' && (parseInt(productList[i].store)>=1)){
			if(productList[i].marketable != '0' && productList[i].limitGoodsdown != '1'){
				productId = productList[i].productId;
				break;
			}
		}
	}
	return productId;
}
//该商品不存在
function emptyContainer(src){
	$('.goods').remove();
	$('.g_main').remove();
	$('.main').remove();
	$('.car_add').after('<div id="p-box" style="width:1200px; margin: 0 auto;">'
				+'<div class="w">'
					+'<div id="product-intro" class="m-item-grid clearfix">'
						+'<div id="preview" clstag="shangpin|keycount|product|1|mainpicarea">'
							+'<div id="spec-n1" class="jqzoom" onclick="" clstag="shangpin|keycount|product|1|mainpic">'
//								+'<img data-img="1" width="350" height="350" src="" jqimg="">'
								+'<img data-img="1" width="350" height="350" src="'+src+'" jqimg="">'
							+'</div>'
							+'<div id="spec-list" clstag="shangpin|keycount|product|1|lunbotu">'
								+'<div class="spec-items" style="position: absolute; width: 310px; height: 54px; overflow: hidden;">'
									+'<ul class="lh" style="position: absolute; width: 372px; height: 54px; top: 0px; left: 0px;">'
										+'<li><img alt="" src="'+src+'" data-url="" data-img="1" width="50" height="50" class="img-hover"></li>'
//										+'<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>'
//										+'<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>'
//										+'<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>'
//										+'<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>'
//										+'<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>'
									+'</ul>'
								+'</div>'
							+'</div>'
						+'</div>'
						+'<div class="m-item-inner" clstag="shangpin|keycount|product|1|zhushujuqu" style="width: 790px;">'
							+'<table style="width:100%;height:449px;">'
								+'<tbody><tr>'
									+'<td style="font-size: 15px;font-weight: bold;vertical-align: middle;padding-left: 50px;">该商品不存在，非常抱歉！</td>'
								+'</tr>'
							+'</tbody></table>'
						+'</div>'
					+'</div>'
				+'</div>'
			+'</div>');
}

//加载商品详情
function findGoodsDetail(){
	/*if($('#marketable').val()=='0'){
		return;
	}*/
	var goodsId = $('#goods_id').val();
	var marketable = $('#marketable').val();
	if(marketable=='0'){
		if($('.goods_m .g_cs').length>=1){
			$('.goods_m .g_cs').remove();
			$('.goods_m .g_num').remove();
			$('.goods_m .g_shop').remove();
			$('.goods_m').append('<div>'
        							+'<table style="width:100%;height:250px;">'
        								+'<tr>'
        									+'<td style="font-size: 25px;font-weight: bold;vertical-align: middle;padding-left: 50px;color: red;">该商品已下架</td>'
        								+'</tr>'
        							+'</table>'
        						+'</div>');	
		}	
		$('.g_show_r .g_buy_m').remove();
	}else{
		if($('#goods_id').length>=1&&goodsId!=null&&goodsId!=''){
			var url = _path+'/goodsManager/findGoodsDetail';
			var params = {};
			params.goodsId = goodsId;
			commonAjax(url, params, function(data) {
				if(data.content != null && data.content.result==false){
					if(data.content.isGoodsExist==false){
						//window.location.reload(true);
						var src = $('.goods_l .goods_show .goods_pic').find('img').attr('src');
						emptyContainer(src);
					}
				}else{
					productList = data.content;
	//				productList = data.content.pList;
					if(productList!=null&&productList.length>=1){
						//规格显示
						var specArray;
						var tempArray;
						var specId;
						var specName;
						var specValueId;
						var specValueName;
						var chooseSpec;
						var chooseSpecValue;
						var chooseSpecValue2; 
						for(var i=0;i<data.content.length;i++){
	//						alert("data.content[i].marketable != '0'...."+(data.content[i].marketable != '0') +'...'+data.content[i].marketable);
	//						alert("data.content[i].limitGoodsdown != '1'...."+(data.content[i].limitGoodsdown != '1')+'....'+data.content[i].limitGoodsdown);
//							if((data.content[i].marketable != '0') && (data.content[i].limitGoodsdown != '1') && (parseInt(data.content[i].store)>=1)){
							if((data.content[i].marketable != '0') && (data.content[i].limitGoodsdown != '1')){
	//							isMarketable = 1 ;
								if(data.content[i].specDesc!=null&&data.content[i].specDesc!=''){//"1|颜色:2|蓝色,2|款式:16|韩版"     "1|颜色:3|绿色,2|款式:16|韩版"
									specArray = data.content[i].specDesc.split(",");
									for(var j=0;j<specArray.length;j++){
										tempArray = specArray[j].split(":");
										specId = tempArray[0].split("|")[0];
										specName = tempArray[0].split("|")[1];
										specValueId = tempArray[1].split("|")[0];
										specValueName = tempArray[1].split("|")[1];
	//									alert(specId+"..."+specName+"..."+specValueId+"..."+specValueName);
										chooseSpec = $('div[name="choose_spec_'+specId+'"]');
										if(chooseSpec.length>=1){
//											if($(chooseSpec).find('li[data="'+specValueId+'_'+specValueName+'"]').length<1){
//											alert(specValueName+"..."+$(chooseSpec).find('li[dataid="'+specValueId+'"][title="'+specValueName+'"]').length);
											if($(chooseSpec).find('li[dataid="'+specValueId+'"][title="'+specValueName+'"]').length<1){
												chooseSpecValue = $(chooseSpec).find('li').eq(-1);
	//							            	alert(chooseSpecValue);
									            chooseSpecValue2 = chooseSpecValue.clone(true);
									            $(chooseSpecValue2).attr("data", specValueId+"_"+specValueName);
									            $(chooseSpecValue2).attr("dataid", specValueId);
									            $(chooseSpecValue2).attr("title", specValueName);
									            if(i==0){
									            	$(chooseSpecValue2).attr("class","g_cur");
									            }else{
									            	$(chooseSpecValue2).attr("class","disabledLi");
									            }
									            $(chooseSpecValue2).find("a").text(specValueName);
									            chooseSpecValue.after( chooseSpecValue2 ); 
	//								            alert(chooseSpecValue2);
											}
								            
										}else{
											var str = '';
											if(j==0){
												str += '<div class="g_s1 clearfix firstSpec" name="choose_spec_'+specId+'">';
											}else{
												str += '<div class="g_s1 clearfix" name="choose_spec_'+specId+'">';
											}
											str +=     		'<div class="g_s_name fl" title="'+specName+'">选择'+specName+'：</div>';
											str +=  		'<div class="g_s_data fl">';
											str +=	  			'<ul>';
	//										if(i==0&&j==0){
	//											str += 				'<li class="g_cur" data="'+specValueId+'_'+specValueName+'" name="specValueA"><a href="javascript:;" style="cursor:pointer">'+specValueName+'</a><i></i></li>';
	//										}else if(i==0){
	//											str += 				'<li class="g_cur" data="'+specValueId+'_'+specValueName+'" name="specValueA"><a href="javascript:;">'+specValueName+'</a><i></i></li>';
	//										}else{
	//											str += 				'<li class="disabledLi" data="'+specValueId+'_'+specValueName+'" name="specValueA"><a href="javascript:;">'+specValueName+'</a><i></i></li>';
	//										}
											if(isMarketable == 0 && j==0){
												str += 				'<li class="g_cur" data="'+specValueId+'_'+specValueName+'" dataid="'+specValueId+'" title="'+specValueName+'" name="specValueA"><a href="javascript:;" style="cursor:pointer">'+specValueName+'</a><i></i></li>';
											}else if(isMarketable == 0){
												str += 				'<li class="g_cur" data="'+specValueId+'_'+specValueName+'" dataid="'+specValueId+'" title="'+specValueName+'" name="specValueA"><a href="javascript:;">'+specValueName+'</a><i></i></li>';
											}else{
												str += 				'<li class="disabledLi" data="'+specValueId+'_'+specValueName+'" dataid="'+specValueId+'" title="'+specValueName+'" name="specValueA"><a href="javascript:;">'+specValueName+'</a><i></i></li>';
											}
											str +=				'</ul>';
											str +=			'</div>';
											str +=		'</div>';	
	//										alert(str);
											$('div.g_cs_b').append(str);
										}
									}
								}
								isMarketable = 1 ;
							}
						}
						if(isMarketable == 1){
							var temp = null;
							for(var i=0;i<data.content.length;i++){
//								if(data.content[i].marketable != '0' && data.content[i].limitGoodsdown != '1' && (parseInt(data.content[i].store)>=1)){
								if(data.content[i].marketable != '0' && data.content[i].limitGoodsdown != '1'){
									temp = i;
									break;
								}
							}
	//						alert("temp..."+temp);
							if(temp!=null){
								changeChooseSelected(data.content[temp].specDesc,null);
							}else{
								if($('.goods_m .g_cs').length>=1){
									$('.goods_m .g_cs').remove();
									$('.goods_m .g_num').remove();
									$('.goods_m .g_shop').remove();
									$('.goods_m').append('<div>'
					            							+'<table style="width:100%;height:250px;">'
					            								+'<tr>'
					            									+'<td style="font-size: 25px;font-weight: bold;vertical-align: middle;padding-left: 50px;color: red;">该商品已下架</td>'
					            								+'</tr>'
					            							+'</table>'
					            						+'</div>');	
								}	
								$('.g_show_r .g_buy_m').remove();
							}
						}else{
							if($('.goods_m .g_cs').length>=1){
								$('.goods_m .g_cs').remove();
								$('.goods_m .g_num').remove();
								$('.goods_m .g_shop').remove();
								$('.goods_m').append('<div>'
				            							+'<table style="width:100%;height:250px;">'
				            								+'<tr>'
				            									+'<td style="font-size: 25px;font-weight: bold;vertical-align: middle;padding-left: 50px;color: red;">该商品已下架</td>'
				            								+'</tr>'
				            							+'</table>'
				            						+'</div>');	
							}	
							$('.g_show_r .g_buy_m').remove();
						}
					}else{
	//					if($('.goods_m .g_cs').length>=1){
	//						$('.goods_m .g_cs').remove();
	//						$('.goods_m .g_num').remove();
	//						$('.goods_m .g_shop').remove();
	//						$('.goods_m').append('<div>'
	//		            							+'<table style="width:100%;height:250px;">'
	//		            								+'<tr>'
	//		            									+'<td style="font-size: 25px;font-weight: bold;vertical-align: middle;padding-left: 50px;color: red;">该商品已下架</td>'
	//		            								+'</tr>'
	//		            							+'</table>'
	//		            						+'</div>');	
	//					}	
	//					$('.g_show_r .g_buy_m').remove();
						var src = $('.goods_l .goods_show .goods_pic').find('img').attr('src');
						emptyContainer(src);
//						emptyContainer();
					}
				}
				//修改商品选中规格
				$('li[name="specValueA"]').find("a").on('click', function(){
					if($(this).parent().attr("class")==""||($(this).parent().parent().parent().parent().attr("class")=="g_s1 clearfix firstSpec"&&$(this).parent().attr("class")!="g_cur")){
						changeChooseSelected("",this);
					}
				});
				
				//修改购买数量
				$(".g_num_m .ipt input").on('change', function() {
					update_quantity(this);
				});
	
				//修改购买数量
				$(".g_num_m .ipt input").on('keyup', function() {
					update_quantity(this);
				});
	
				//增加购买数量
				$('.g_right').on('click', function() {
					var quantity = $(".g_num_m .ipt input").val();
					var store = $('.g_num_r span').text();
					if(parseInt(quantity)>=parseInt(store)){
						$(".g_num_m .ipt input").val(store);
					}else{
						$(".g_num_m .ipt input").val(parseInt(quantity)+1);
					}
					$(".g_num_m .ipt input").keyup();
				});
	
				//减少购买数量
				$('.g_left').on('click', function() {
					var quantity = $(".g_num_m .ipt input").val();
					if(parseInt(quantity)<=1){
						$(".g_num_m .ipt input").val(1);
					}else{
						$(".g_num_m .ipt input").val(parseInt(quantity)-1);
					}
					$(".g_num_m .ipt input").keyup();
				});
				
				var productId = $('#product_id').val();
				if(productId!=null&&productId!=''&&productList!=null){
					var specDesc = '';
					for(var j=0;j<productList.length;j++){
//						if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1' && (parseInt(productList[j].store)>=1)){
						if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1'){
							if(productList[j].productId == productId){
								specDesc = productList[j].specDesc;
								var specItems = specDesc.split(",");
								for(var i =0;i<specItems.length;i++){
//									$('li[data="'+specItems[i].split(":")[1].split('|')[0]+'_'+specItems[i].split(":")[1].split('|')[1]+'"]').find("a").click();
									$('li[dataid="'+specItems[i].split(":")[1].split('|')[0]+'"][title="'+specItems[i].split(":")[1].split('|')[1]+'"]').find("a").click();
								}
								break;
							}
						}
					}
				}
				
	//			if(productList!=null&&productList.length>=1){
	//				if(isMarketable == 1){
	//					//加载商品优惠套餐信息和相关商品信息
	//					findAccessoryAndRelated();
	//					//加载商品评论
	//					findGoodsComment();
	//					//加载商品咨询
	//					findGoodsDiscuss();
	//				}
	//				//更新商品信息（浏览次数等）
	//				updateGoodsInfo();
	//			}
			}, function(data) {
	//			easyuiMsg('失败', '请选择要操作的数据项!');
	//			alert(errorMsg);
			});
		}
	}
	//加载商品属性信息
	findCouponsAndAttribute();
	//加载商品参数配置
	findCarData();
	//加载热销排行榜
	findTopSales();
	//加载参数配置
	findCarParam();
	//加载商品优惠套餐信息和相关商品信息
	findAccessoryAndRelated();
	//加载商品评论
	findGoodsComment();
	//加载商品咨询
	findGoodsDiscuss();
	//更新商品信息（浏览次数等）
	updateGoodsInfo();
}

//修改商品选中规格
function changeChooseSelected(v,ele){
	var chooseSpecDivs = $('.g_cs_b').find('div[name^="choose_spec_"]');
	var selectSpecDesc = "";
	if(ele!=null){
		var choose ;
		//选中项的兄弟均变为可选状态，其他规格中与选中项匹配的规格为可选，其他为不可选
		//如果选中的是处于不可选状态的第一行的规格项，则让其他均为不可选状态，兄弟为可选，自己为已选
		if($(ele).parent().parent().parent().parent().attr("class")=="g_s1 clearfix firstSpec" && ($(ele).parent().attr("class")=="disabledLi"
			||($(chooseSpecDivs).find("li[class='g_cur']").length==1 && $(chooseSpecDivs[0]).find("li[class='g_cur']").length==1))){
			choose = "1";
			$('li[name="specValueA"]').attr("class","disabledLi");
			$(ele).parent().siblings().attr("class","");
			$(ele).parent().attr("class","g_cur");
		}else{
			choose = "2";
			//如果选中的为可选状态，则让自己被选中，兄弟为不可选
			$(ele).parent().siblings().attr("class","disabledLi");
			$(ele).parent().attr("class","g_cur");
		}
		if(chooseSpecDivs.length>=1){//"1|颜色:2|蓝色,2|款式:16|韩版,3|尺码:22|小码"    "1|颜色:3|绿色,2|款式:16|韩版,3|尺码:23|中码"     "1|颜色:2|蓝色,2|款式:6|男版,3|尺码,23:中码"
			for(var i=0;i<chooseSpecDivs.length;i++){
				if($(chooseSpecDivs[i]).find('li[class="g_cur"]').length>=1){
					selectSpecDesc+=$(chooseSpecDivs[i]).attr("name").split("_")[2];
					selectSpecDesc+="|";
					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_name').attr("title");
					selectSpecDesc+=":";
//					selectSpecDesc+=$(chooseSpecDivs[i]).find('li.g_cur').attr("data").split("_")[0];
					selectSpecDesc+=$(chooseSpecDivs[i]).find('li.g_cur').attr("dataid");
					selectSpecDesc+="|";
//					selectSpecDesc+=$(chooseSpecDivs[i]).find('li.g_cur').attr("data").split("_")[1];
					selectSpecDesc+=$(chooseSpecDivs[i]).find('li.g_cur').attr("title");
					selectSpecDesc+=",";
				}
			}
			if(selectSpecDesc.length>1){
				selectSpecDesc = selectSpecDesc.substring(0, selectSpecDesc.length-1);
			}
//			alert(selectSpecDesc+"......."+choose);
			if(choose=="1"){//如果选中的是处于不可选状态的第一行的规格项
				for(var i=1;i<chooseSpecDivs.length;i++){
					for(var j=0;j<productList.length;j++){
//						if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1' && (parseInt(productList[j].store)>=1)){
						if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1'){
							if(productList[j].specDesc.indexOf(selectSpecDesc)>=0){
								var tempSpecValueId = productList[j].specDesc.split(",");
//								if($(chooseSpecDivs[i]).find('li[data="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'_'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class")!="g_cur"){
//									$(chooseSpecDivs[i]).find('li[data="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'_'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class","");
//								}
								if($(chooseSpecDivs[i]).find('li[dataid="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'"][title="'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class")!="g_cur"){
									$(chooseSpecDivs[i]).find('li[dataid="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'"][title="'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class","");
								}
							}
						}
					}
				}
			}else{//如果选中的为可选状态
				for(var i=0;i<chooseSpecDivs.length;i++){
					if($(chooseSpecDivs[i]).find('li[class="g_cur"]').length<1){//该规格没有选中项
//						alert(i+"....该规格没有选中项");
						$(chooseSpecDivs[i]).find('li[name="specValueA"]').attr("class","disabledLi");
						for(var j=0;j<productList.length;j++){
//							if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1' && (parseInt(productList[j].store)>=1)){
							if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1'){
								var selectSpecItems = selectSpecDesc.split(",");
								var isExist = true;
								for(var k=0;k<selectSpecItems.length;k++){
									if(productList[j].specDesc.indexOf(selectSpecItems[k])==-1){
										isExist = false;
										break;
									}
								}
//								alert(productList[j].specDesc+"..."+isExist+"..."+selectSpecDesc);
								if(isExist == true){
									var tempSpecValueId = productList[j].specDesc.split(",");
//									if($(chooseSpecDivs[i]).find('li[data="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'_'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class")!="g_cur"){
//										$(chooseSpecDivs[i]).find('li[data="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'_'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class","");
//									}
									if($(chooseSpecDivs[i]).find('li[dataid="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'"][title="'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class")!="g_cur"){
										$(chooseSpecDivs[i]).find('li[dataid="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'"][title="'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class","");
									}
								}
							}
						}
					}else{//该规格有选中项
//						alert(i+"....该规格有选中项");
						var selectItem = $(chooseSpecDivs[i]).find('li.g_cur');
						$(selectItem).siblings().attr("class","disabledLi");
//						$(chooseSpecDivs[i]).find('div[name^="choose_specValue_"]').attr("class","item disabled");
						$(selectItem).attr("class","g_cur");
//						alert("将兄弟变为不可选");
						var tempSpecItem = $(chooseSpecDivs[i]).attr("name").split("_")[2]+"|";
						tempSpecItem += $(chooseSpecDivs[i]).find('div.g_s_name').attr("title")+":";
//						tempSpecItem += $(chooseSpecDivs[i]).find('li.g_cur').attr("data").split("_")[0]+"|";
//						tempSpecItem += $(chooseSpecDivs[i]).find('li.g_cur').attr("data").split("_")[1];
						tempSpecItem += $(chooseSpecDivs[i]).find('li.g_cur').attr("dataid")+"|";
						tempSpecItem += $(chooseSpecDivs[i]).find('li.g_cur').attr("title");
						tempSpecItem = selectSpecDesc.replace(tempSpecItem,"");
//						alert("其他被选中的规格信息。。。"+tempSpecItem);
						for(var j=0;j<productList.length;j++){
//							if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1' && (parseInt(productList[j].store)>=1)){
							if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1'){
								var selectSpecItems = tempSpecItem.split(",");
								var isExist = true;
								for(var k=0;k<selectSpecItems.length;k++){
									if(productList[j].specDesc.indexOf(selectSpecItems[k])==-1){
										isExist = false;
										break;
									}
								}
//								alert(productList[j].specDesc+"..."+isExist+"..."+tempSpecItem);
								if(isExist == true){
									var tempSpecValueId = productList[j].specDesc.split(",");
//									if($(chooseSpecDivs[i]).find('li[data="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'_'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class")!="g_cur"){
//										$(chooseSpecDivs[i]).find('li[data="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'_'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class","");
//									}
									if($(chooseSpecDivs[i]).find('li[dataid="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'"][title="'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class")!="g_cur"){
										$(chooseSpecDivs[i]).find('li[dataid="'+tempSpecValueId[i].split(":")[1].split("|")[0]+'"][title="'+tempSpecValueId[i].split(":")[1].split("|")[1]+'"]').attr("class","");
									}
								}
							}
						}
					}
				}
			}
		}
		
		if($(chooseSpecDivs).find("li[class='g_cur']").length == $(chooseSpecDivs).length){
			changeProInfo(selectSpecDesc);
		}
	}else{
		var selectSpecDesc = v;
		changeAbledSpec(chooseSpecDivs,selectSpecDesc);
		changeProInfo(selectSpecDesc);
	}
}


function changeAbledSpec(chooseSpecDivs,selectSpecDesc){
//	alert(chooseSpecDivs+'...'+selectSpecDesc);
	//判断可选项
	if(chooseSpecDivs.length>=1){//"1|颜色:2|蓝色,2|款式:16|韩版,3|尺码:22|小码"    "1|颜色:3|绿色,2|款式:16|韩版,3|尺码:23|中码"     "1|颜色:2|蓝色,2|款式:6|男版,3|尺码,23:中码"
//		var selectSpecDesc = productList[0].specDesc;//"1|颜色:2|蓝色,2|款式:16|韩版"     "1|颜色:3|绿色,2|款式:16|韩版"
//		var tempArray = selectSpecDesc.split(",");
		for(var i=0;i<chooseSpecDivs.length;i++){
			for(var j=0;j<productList.length;j++){
//				alert("productList[j].marketable != '0'...."+(productList[j].marketable != '0')+"..."+productList[j].marketable);
//				alert("productList[j].limitGoodsdown != '1'...."+(productList[j].limitGoodsdown != '1')+"..."+productList[j].limitGoodsdown);
//				alert(productList[j].marketable+"...."+productList[j].limitGoodsdown+"...."+productList[j].store);
//				if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1' && (parseInt(productList[j].store)>=1)){
				if(productList[j].marketable != '0' && productList[j].limitGoodsdown != '1' ){
//					alert("productList[j].specDesc!=selectSpecDesc...."+(productList[j].specDesc!=selectSpecDesc)+"..."+productList[j].specDesc);
//					alert("$(chooseSpecDivs[i]).find('.g_cur').length>=1...."+($(chooseSpecDivs[i]).find(".g_cur").length>=1)+"..."+$(chooseSpecDivs[i]).find(".g_cur").length);
					if(productList[j].specDesc!=selectSpecDesc && $(chooseSpecDivs[i]).find(".g_cur").length>=1){
						var selectItemDesc = $(chooseSpecDivs[i]).attr("name").split("_")[2] + "|" + $(chooseSpecDivs[i]).find("div[class='g_s_name fl']").eq(0).attr("title") + ":";
//						selectItemDesc += $(chooseSpecDivs[i]).find(".g_cur").attr("data").split("_")[0] + "|";
//						selectItemDesc += $(chooseSpecDivs[i]).find(".g_cur").attr("data").split("_")[1];
						selectItemDesc += $(chooseSpecDivs[i]).find(".g_cur").attr("dataid") + "|";
						selectItemDesc += $(chooseSpecDivs[i]).find(".g_cur").attr("title");
						var temp = selectSpecDesc.split(selectItemDesc);
//						alert(selectItemDesc+"......"+productList[j].specDesc+"..."+temp[0]+"..."+temp[temp.length-1]);
						if(productList[j].specDesc.indexOf(temp[0]) >= 0 && productList[j].specDesc.indexOf(temp[temp.length-1]) >= 0){
							var tempSpecValueId = productList[j].specDesc.replace(temp[0],"").replace(temp[temp.length-1],"");
//							alert(tempSpecValueId);
							if(tempSpecValueId.indexOf(",")==0){
								tempSpecValueId = substring(1,tempSpecValueId.length);
							}
							if(tempSpecValueId.lastIndexOf(",")==(tempSpecValueId.length-1)){
								tempSpecValueId = substring(0,tempSpecValueId.length-1);
							}
//							alert(tempSpecValueId);
//							if($(chooseSpecDivs[i]).find('li[data="'+tempSpecValueId.split(":")[1].split("|")[0]+'_'+tempSpecValueId.split(":")[1].split("|")[1]+'"]').attr("class")!="g_cur"){
//								$(chooseSpecDivs[i]).find('li[data="'+tempSpecValueId.split(":")[1].split("|")[0]+'_'+tempSpecValueId.split(":")[1].split("|")[1]+'"]').attr("class","");
//							}
							if($(chooseSpecDivs[i]).find('li[dataid="'+tempSpecValueId.split(":")[1].split("|")[0]+'"][title="'+tempSpecValueId.split(":")[1].split("|")[1]+'"]').attr("class")!="g_cur"){
								$(chooseSpecDivs[i]).find('li[dataid="'+tempSpecValueId.split(":")[1].split("|")[0]+'"][title="'+tempSpecValueId.split(":")[1].split("|")[1]+'"]').attr("class","");
							}
						}
					}
				}
			}
		}
	}
}

function changeProInfo(selectSpecDesc){
//	alert("changeProInfo..");
//	alert("changeInfo..."+selectSpecDesc);
	//修改商品信息
	$('.g_name').find("h2").text("");
	$('.g_j').find("strong").text("");
	/*$('.pro_unit').text("");
	$('.pro_store').text("");*/
	
	$('div[class="g_num_r fl"]').find("span").text("");
	for(var i=0;i<productList.length;i++){
//		if(productList[i].marketable != '0' && productList[i].limitGoodsdown != '1' && (parseInt(productList[i].store)>=1)){
		if(productList[i].marketable != '0' && productList[i].limitGoodsdown != '1'){
//			alert(productList[i].specDesc+"...."+selectSpecDesc);
//			alert(productList[i].specDesc==selectSpecDesc);
			if(productList[i].specDesc==selectSpecDesc){
//				alert(productList[i].name+"..."+productList[i].price);
				$('.g_name').find("h2").text(productList[i].name);
				$('.g_name').find("h2").attr("title",productList[i].name);
//				$('.g_j').find("strong").text("￥"+productList[i].price.toFixed(2));
				$('.g_j').find("strong").text("￥"+productList[i].priceDouble.toFixed(2));
				$('.g_j').find("#yoyoPrice").text("优优价：￥"+productList[i].mktprice.toFixed(2));
				/*if(productList[i].unit!=null){
					$('.pro_unit').text(productList[i].unit);
				}else{
					$('.pro_unit').text("");
				}
				$('.pro_store').text(productList[i].store);*/
//				$('div[class="g_num_r fl"]').find("span").text(productList[i].store);
				$('div[class="g_num_r fl"]').find("span").text(parseInt(productList[i].storeInt)>=0?productList[i].storeInt:0);
				
				$('.goods_pic').find('img').attr("src","");
				var ul = $('div[class="goods_min"]').find("ul");
				$(ul).empty();
				for(var j=0;j<productList[i].picList.length;j++){
					var src = goodsImgSrcPre + productList[i].picList[j].picturePath;
					if(j==0){
						/*$('#spec-n1').empty();
						$('#spec-n1').append('<img data-img="1" width="350" height="350" src="'+productList[i].picList[j].picturePath+'" alt="'+productList[i].name+'" jqimg="'+productList[i].picList[j].picturePath+'">');*/
//				        var index = src.lastIndexOf(".");
//						src = src.substring(0,index)+"."+bigPicSize+"."+src.substring(index+1,src.length);
						$('.goods_pic').find('img').attr("src",src);
						$('.goods_pic').find('img').attr("alt",src);
					}
//					smallSrc = changePicSize(src,smallPicSize);
					smallSrc = src;
					if(j==0){
						$(ul).append('<li style="margin-right: 3px;"><img src="'+smallSrc+'" rel="'+src+'" alt="'+productList[i].name+'" width="50" height="50"></li>');
					}else if(j>=5){
						/*$(ul).append('<li style="display:none;"><img class="" alt="'+productList[i].name+'" src="'+productList[i].picList[j].picturePath+'" data-url="'+productList[i].picList[j].picturePath+'" data-img="1" width="50" height="50" /></li>');*/
						$(ul).append('<li style="display:none;border:0px;margin-right: 3px;"><img src="'+smallSrc+'" rel="'+src+'" alt="'+productList[i].name+'" width="50" height="50"></li>');
					}else{
						/*$(ul).append('<li><img class="" alt="'+productList[i].name+'" src="" data-url="'+productList[i].picList[j].picturePath+'" data-img="1" width="50" height="50" /></li>');*/
						$(ul).append('<li style="border:0px;margin-right: 3px;"><img src="'+smallSrc+'" rel="'+src+'" alt="'+productList[i].name+'" width="50" height="50"></li>');
					}
				}
				/*$('#spec-forward').attr("class","spec-control disabled");
				if(productList[i].picList.length>5){
					$('#spec-backward').attr("class","spec-control");
				}else{
					$('#spec-backward').attr("class","spec-control disabled");
				}*/
				
				var sumPrice = $('#accessorySumPrice').val();
//				alert(sumPrice);
				sumPrice = parseFloat(sumPrice)+parseFloat(productList[i].price);
//				alert(sumPrice);
				$('.g_price').find("p").eq(1).find("del").text("￥"+sumPrice.toFixed(2));
				var discPrice = 0;
				var discType = $('#accessoryDiscType').val();
				var credit = $('#accessoryCredit').val();
				if(discType=="minus"&&credit!=null&&credit!=''){
					discPrice = parseFloat(sumPrice) - parseFloat(credit);
				}else if(discType=="discount"&&credit!=null&&credit!=''){
					discPrice = parseFloat(sumPrice) * parseFloat(credit);
				}else{
					discPrice = sumPrice;
				}
				$('.g_price').find("p").eq(0).find("i").text("￥"+discPrice.toFixed(2));
				$('.g_price').find("p").eq(2).find("i").text("￥"+(sumPrice.toFixed(2)-discPrice.toFixed(2)).toFixed(2));
				
				break;
			}
		}
	}
	
	$('.g_num .ipt input').keyup();
	
	$('.goods_show .goods_min ul img').mouseover(function () {
        $(this).parent().siblings().css("border","0px");
        $(this).parent().css("border","2px solid #e4393c");
//        $(imgobj).attr('src', $(this).attr(bsrc));
//        $(imgobj).attr('alt', $(this).attr(ssrc));
        var src = $(this).attr("rel");
//        var index = src.lastIndexOf(".");
//		src = src.substring(0,index)+"."+bigPicSize+"."+src.substring(index+1,src.length);
        $('.goods_show .goods_pic img').attr("src",src);
        $('.goods_show .goods_pic img').attr("alt",src);
    });
}

function changePicSize(src,size){
	var index = src.lastIndexOf(".");
	src = src.substring(0,index)+"."+size+"."+src.substring(index+1,src.length);
	return src;
}

function nextPic(){
	var liList = $('div[class="spec-items"]').find("ul li");
	//alert(liList.length);
	if(liList[liList.length-1].style.display!="none"){
		//alert("return...");
		return;
	}
	for(var i=0;i<liList.length;i++){
		//if(i+5>=aList.length-1){
		if(liList[i].style.display!="none"){
			liList[i].style.display="none";
			liList[i+5].style.display="";
			/*$('#spec-forward').attr("class","spec-control");*/
			break;
		}
	}
	/*if(liList[liList.length-1].style.display!="none"){
		$('#spec-backward').attr("class","spec-control disabled");
	}*/
}
function prePic(){
	var liList = $('div[class="spec-items"]').find("ul li");
	//alert(aList.length);
	if(liList[0].style.display!="none"){
		//alert("return...");
		return;
	}
	for(var i=0;i<liList.length;i++){
		if(liList[i].style.display!="none"){
			liList[i-1].style.display="";
			liList[i+4].style.display="none";
			/*$('#spec-backward').attr("class","spec-control");*/
			break;
		}
	}
	/*if(liList[0].style.display!="none"){
		$('#spec-forward').attr("class","spec-control disabled");
	}*/
}
/*//加入购物车
function addToCart(){
	var quantity = $('.g_num .ipt input').val();
	if(quantity==null||quantity==''||parseInt(quantity)!=quantity){
		alert("请输入购买数量");
		return;
	}
	var chooseSpecDivs = $('.g_cs_b').find('div[name^="choose_spec_"]');
	if($(chooseSpecDivs).find("li.g_cur").length == $(chooseSpecDivs).length){
		var selectSpecDesc='';
		for(var i=0;i<chooseSpecDivs.length;i++){
			if($(chooseSpecDivs[i]).find("li.g_cur").length>=1){
				selectSpecDesc+=$(chooseSpecDivs[i]).attr("name").split("_")[2];
				selectSpecDesc+="|";
				selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_name').attr("title");
				selectSpecDesc+=":";
				selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[0];
				selectSpecDesc+="|";
				selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[1];
				if(i!=chooseSpecDivs.length-1){
					selectSpecDesc+=",";
				}
			}
		}
		var productId = '';
		for(var i=0;i<productList.length;i++){
			if(productList[i].specDesc == selectSpecDesc){
				productId = productList[i].productId;
				break;
			}
		}
		if(productId!=''){
			var url = yoyo.memUrl+'/cart/addCart';
			var params = {};
			params.quantity=$('.g_num .ipt input').val();
			params.productId = productId;
			commonAjax(url, params, function(data) {
				if(data.content.result==true){
					alert("加入购物车成功");
				}else if(data.content.result==false){
					alert(data.content.msg);
				}else{
					alert(errorMsg);
				}
			}, function(data) {
				easyuiMsg('失败', '请选择要操作的数据项!');
			});
		}else{
			alert("该货品不存在，请重新选择商品规格");
		}
	}else{
		alert("请选择商品规格");
	}
}
*/
//修改购买数量
function update_quantity(input){
	var quantity = $(input).val();
	if(quantity!=null&&quantity!=''){
		if(parseInt(quantity)==quantity){
			if(parseInt($('.g_num_r span').text()) == 0){
				$(input).attr("back",0);
				$(input).val(0);
				$('.shop_cart').on('click', function(){
					alert("该商品暂时缺货，无法加入购物车!");
				});
			}else if(parseInt(quantity)<=1){
				$(input).attr("back",1);
				$(input).val(1);
			}else{
				var store = $('.g_num_r span').text();
				if(parseInt(quantity)>parseInt(store)){
					$(input).attr("back",store);
					$(input).val(store);
				}else{
					$(input).attr("back",quantity);
				}
			}
		}else{
			$(input).val($(input).attr("back"));
		}
	}
}


//加载优惠券信息和商品属性信息
function findCouponsAndAttribute(){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findCouponsAndAttribute';
		var params = {};
		params.goodsId = goodsId;
		commonAjax(url, params, function(data) {
			if(data.content.result==false){
				if(data.content.isGoodsExist==false){
//					alert("该商品不存在");
				}
			}else{
//				if(data.content.couponList.rows.length>=1){
//					$('.f_4').show();
//					for(var i=0 ; i<data.content.couponList.rows.length ; i++){
//						if((i+1)%3==0){
//							$('.f_4 .f_4_in ul').append('<li style=" margin-right: 0;"><a href="javascript:;"><img src="'+_path+'/resources/images/goods/yo_08.jpg" width="382" height="160" alt="'+data.content.couponList.rows[i].cpnsName+'" title="'+data.content.couponList.rows[i].cpnsName+'"></a></li>');
//						}else{
//							$('.f_4 .f_4_in ul').append('<li><a href="javascript:;"><img src="'+_path+'/resources/images/goods/yo_08.jpg" width="382" height="160" alt="'+data.content.couponList.rows[i].cpnsName+'" title="'+data.content.couponList.rows[i].cpnsName+'"></a></li>');
//						}
//						
//					}
//				}
				if(data.content.attributeList){
//					$('.g_cp .g_con div').show();
//					for(var i=0 ; i<data.content.attributeList.length ; i++){
//						$('.goods_attribute').find("li").eq(-2).after('<li>'+data.content.attributeList[i].attrName+'：'+data.content.attributeList[i].attrValue+'</li>');
//					}
					
//					var div = $('#dpt_select').find(".table").eq(0);
					var attributeList = data.content.attributeList;
					var accParams = data.content.accParams;
					$('#dpt_select').find(".table").eq(0).parent().show();
//					for(var i=0;i<attributeList.length;i++){
						if(attributeList.accName){
							$('.goods_attribute').append('<li>名称：'+attributeList.accName+'</li>');
						}
						if(attributeList.catalogName){
							$('.goods_attribute').append('<li>类型名称：'+attributeList.catalogName+'</li>');
						}
						if(attributeList.accSpec){
							$('.goods_attribute').append('<li>规格：'+attributeList.accSpec+'</li>');
						}
						if(attributeList.accUnit){
							$('.goods_attribute').append('<li>单位：'+attributeList.accUnit+'</li>');
						}
						if(attributeList.accForshort){
							$('.goods_attribute').append('<li>简称：'+attributeList.accForshort+'</li>');
						}
						if(attributeList.accCode){
							$('.goods_attribute').append('<li>编码：'+attributeList.accCode+'</li>');
						}
						if(attributeList.accOem){
							$('.goods_attribute').append('<li>OEM号：'+attributeList.accOem+'</li>');
						}
//						if(attributeList.brandName){
//							$('.goods_attribute').append('<li>品牌：'+attributeList.brandName+'</li>');
//						}
						if(attributeList.accMainPlants){
							$('.goods_attribute').append('<li>主机厂：'+attributeList.accMainPlants+'</li>');
						}
						if(attributeList.accScPrice){
							$('.goods_attribute').append('<li>SC价格：'+attributeList.accScPrice+'</li>');
						}
						if(attributeList.accPrice){
							$('.goods_attribute').append('<li>指导价：'+attributeList.accPrice+'</li>');
						}
						if(attributeList.accPack){
							$('.goods_attribute').append('<li>包装：'+attributeList.accPack+'</li>');
						}
						if(attributeList.accLogistics){
							$('.goods_attribute').append('<li>物流属性：'+attributeList.accLogistics+'</li>');
						}
						if(accParams!=null&&accParams.length>=1){
							for(var i =0; i<accParams.length; i++){
								if (accParams[i].dataType == 'STR' && accParams[i].paramValues!=null && accParams[i].paramValues!='' && accParams[i].strValue!=null && accParams[i].strValue!='' && accParams[i].strValue.indexOf("请选择")<0) {
//									_input = $('<input />', {name : 'name_' + param.paramId, class : 'walidator', type : 'text', value : param.strValue, dataType : param.dataType}).attr('walitype', '{require:true}');
									$('.goods_attribute').append('<li>'+accParams[i].paramName+'：'+accParams[i].strValue+'</li>');
								} else if (accParams[i].dataType == 'INT' && accParams[i].paramValues!=null && accParams[i].paramValues!='' && accParams[i].intValue !=null && accParams[i].intValue !='') {
//									_input = $('<input />', {name : 'name_' + param.paramId, class : 'walidator', type : 'text', value : param.intValue, dataType : param.dataType}).attr('walitype', '{require:true,match:"number"}');
									$('.goods_attribute').append('<li>'+accParams[i].paramName+'：'+accParams[i].intValue+'</li>');
								} else if (accParams[i].dataType == 'DEC' && accParams[i].paramValues!=null && accParams[i].paramValues!='' && accParams[i].decValue !=null && accParams[i].decValue !='' ) {
//									_input = $('<input />', {name : 'name_' + param.paramId, class : 'walidator', type : 'text', value : param.decValue, dataType : param.dataType}).attr('walitype', '{require:true,match:"number"}');
									$('.goods_attribute').append('<li>'+accParams[i].paramName+'：'+accParams[i].decValue+'</li>');
								} else if (accParams[i].dataType == 'BOL' && accParams[i].paramValues!=null && accParams[i].paramValues!='') {
//									_input = $('<select />', {name : 'name_' + param.paramId, class : 'walidator', value : param.bolValue, dataType : param.dataType}).attr('walitype', '{require:true}');
//									_input.append($('<option value=0 >否</option>')).append($('<option value=1 >是</option>'));
									if(accParams[i].bolValue == 0){
										$('.goods_attribute').append('<li>'+accParams[i].paramName+'：否</li>');
									}else if(accParams[i].bolValue == 1){
										$('.goods_attribute').append('<li>'+accParams[i].paramName+'：是</li>');
									}
								}
							}
						}
						if(attributeList.carName){
							$('.goods_attribute').append('<li style="width:804px;" title="'+attributeList.carName+'">适用车型：'+attributeList.carName+'</li>');
						}
//					}
					$('#dpt_select').find(".table").eq(0).parent().show();
				}
			}
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
//			alert(errorMsg);
		});
	}
}

//加载商品优惠套餐信息和相关商品信息
function findAccessoryAndRelated(){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findAccessoryAndRelated';
		var params = {};
		params.goodsId = goodsId;
		commonAjax(url, params, function(data) {
			if(data.content.result==false){
				if(data.content.isGoodsExist==false){
//					alert("该商品不存在");
				}
			}else{
				//相关商品
				if(data.content.relatedGoodsPage!=null&&data.content.relatedGoodsPage.rows!=null){
					if(data.content.relatedGoodsPage.rows.length>=1){
						for(var i = 0;i<data.content.relatedGoodsPage.rows.length;i++){
							$('div.goods_r').find("li").append('<div style="height:137px;overflow:hidden;"><a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content.relatedGoodsPage.rows[i].goodsId+'" target="_blank">'
									+'<img src="'+goodsImgSrcPre+data.content.relatedGoodsPage.rows[i].midPic+'" alt="'+data.content.relatedGoodsPage.rows[i].name+'" width="120px" height="120px">'
									+'</a><br/>'
									+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content.relatedGoodsPage.rows[i].goodsId+'" target="_blank" title="'+data.content.relatedGoodsPage.rows[i].name+'">'
									+ data.content.relatedGoodsPage.rows[i].name
									+'</a><br/></div>');
						}
						if(data.content.relatedGoodsPage.pages>1){
							$('div.goods_r div.g_ref').show();
						}else{
							$('div.goods_r div.g_ref').find("a").attr("onclick","");
						}
						$('div.goods_r').show();
					}
				}
				//优惠套餐
				if(data.content.accessoryList!=null&&data.content.productPageList!=null){
					if(data.content.accessoryList.rows.length>=1&&data.content.productPageList.length>=1&&data.content.accessoryList.rows.length==data.content.productPageList.length){
						accessoryList = data.content.accessoryList;
//						goodsPageList = data.content.goodsPageList;
//						showAccessory(accessoryList,goodsPageList);
						productPageList = data.content.productPageList;
						showAccessory(accessoryList,productPageList);
					}
				}
			}
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
//			alert(errorMsg);
		});
	}
}

//修改优惠套餐价格
function changeAccessoryPrice(ele, accessory, goodsList){
//	$(ele).text(accessory.accGroupName);
	$(ele).attr("name","accessory_"+accessory.id);
	$('.g_show_r').find("h3").text(accessory.accGroupName);
	var goodsPrice = $('.g_j').find('strong').text();
//	alert(goodsPrice);
	if(goodsPrice!=null && goodsPrice !=''){
		goodsPrice = goodsPrice.substring(1,goodsPrice.length);
//		alert(goodsPrice);
	}
	var sumPrice = accessory.sumPrice;
//	for(var j=0 ; j<goodsList.length ; j++){
//		sumPrice = parseFloat(sumPrice) + parseFloat(goodsList[j].price); 
//		alert(sumPrice);
//	}
	$('#accessorySumPrice').val(sumPrice.toFixed(2));
	$('#accessoryDiscType').val(accessory.discType);
	$('#accessoryCredit').val(accessory.credit);
	if(goodsPrice!=null&&goodsPrice!=''){
		sumPrice = parseFloat(sumPrice) + parseFloat(goodsPrice);
	}
	$('.g_price').find("p").eq(1).find("del").text("￥"+sumPrice.toFixed(2));
	var discPrice = 0;
	if(accessory.discType=="minus"&&accessory.credit!=null&&accessory.credit!=''){
		discPrice = parseFloat(sumPrice) - parseFloat(accessory.credit);
	}else if(accessory.discType=="discount"&&accessory.credit!=null&&accessory.credit!=''){
		discPrice = parseFloat(sumPrice) * parseFloat(accessory.credit);
	}else{
		discPrice = sumPrice;
	}
	$('.g_price').find("p").eq(0).find("i").text("￥"+discPrice.toFixed(2));
	$('.g_price').find("p").eq(2).find("i").text("￥"+(sumPrice.toFixed(2)-discPrice.toFixed(2)).toFixed(2));
}

//加载下一页相关商品
function findRelatedGoods(pageIndex){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findRelatedGoods';
		var params = {};
		params.goodsId = goodsId;
		params.pageIndex = pageIndex;
		commonAjax(url, params, function(data) {
			if(data.content.result==false){
				if(data.content.isGoodsExist==false){
//					alert("该商品不存在");
				}
			}else{
				//相关商品
				if(data.content.rows!=null){
					if(data.content.rows.length>=1){
						$('div.goods_r').find("li").empty();
						for(var i = 0;i<data.content.rows.length ;i++){
							$('div.goods_r').find("li").append('<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content.rows[i].goodsId+'" target="_blank">'
									+'<img src="'+goodsImgSrcPre+data.content.rows[i].midPic+'" alt="'+data.content.rows[i].name+'" width="120px" height="120px">'
									+'</a><br/>'
									+'<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+data.content.rows[i].goodsId+'" target="_blank">'
									+ data.content.rows[i].name
									+'</a><br/>');
						}
						if(data.content.pageIndex==data.content.pages){
							$('div.goods_r div.g_ref').find("a").attr("onclick","findRelatedGoods(1)");
						}else{
							$('div.goods_r div.g_ref').find("a").attr("onclick","findRelatedGoods("+(parseInt(data.content.pageIndex)+1)+")");
						}
					}
				}
			}
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
//			alert(errorMsg);
		});
	}
}

//加载上/下一页优惠套餐
function findAccessory(pageIndex){
	if(pageIndex==null){
		return;
	}
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findAccessory';
		var params = {};
		params.goodsId = goodsId;
		params.pageIndex = pageIndex;
		commonAjax(url, params, function(data) {
			if(data.content.result==false){
				if(data.content.isGoodsExist==false){
//					alert("该商品不存在");
				}
			}else{
				//优惠套餐
				if(data.content.accessoryList!=null&&data.content.goodsPageList!=null){
					if(data.content.accessoryList.rows.length>=1&&data.content.goodsPageList.length>=1&&data.content.accessoryList.rows.length==data.content.goodsPageList.length){
						//去掉原有的
						var lis = $("div.g_nav ul").find("li");
						if(lis!=null && lis.length>1){
							for(var i=lis.length-1;i>=1;i--){
								lis[i].remove();
							}
							$(lis[0]).attr("class","g_nav_cur");
						}
						var myDiv = $('.g_show_l');
						if(myDiv!=null&&myDiv.length>=2){
							for(var i=1;i<myDiv.length;i++){
								myDiv[i].remove();
							}
							$(myDiv[0]).show();
							var goodsDivs = $(myDiv).find('.g_show_tp');
							if(goodsDivs!=null&&goodsDivs.length>=2){
								for(var i=1;i<goodsDivs.length;i++){
									goodsDivs[i].remove();
								}
							}
						}
						accessoryList = data.content.accessoryList;
//						goodsPageList = data.content.goodsPageList;
//						showAccessory(accessoryList,goodsPageList);
						productPageList = data.content.goodsPageList;
						showAccessory(accessoryList,productPageList);
					}
				}
				
			}
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
//			alert(errorMsg);
		});
	}
}
//切换显示优惠套餐信息
function showAccessory(accessoryList,goodsPageList){
	myDiv = $('.g_show_l');
	$(myDiv).find('.g_show_tp').eq(0).find("img").attr("src",goodsImgSrcPre + $(myDiv).find('.g_show_tp').eq(0).find("img").attr("src"));
	for(var i=0;i<accessoryList.rows.length;i++){
		//显示优惠标题
		var li = $("div.g_nav ul").find("li").eq(0);
		if(i==0){
			$(li).text("优惠套装"+(((accessoryList.pageIndex-1)*accessoryList.pageSize)+i+1));
			changeAccessoryPrice(li,accessoryList.rows[i],goodsPageList[0].rows);
		}else{
			myLi = li.clone(true);
			$(myLi).attr("class","");
//			$(myLi).text(accessoryList.rows[i].accGroupName);
			$(myLi).text("优惠套装"+(((accessoryList.pageIndex-1)*accessoryList.pageSize)+i+1));
			$(myLi).attr("name","accessory_"+accessoryList.rows[i].id);
			$("div.g_nav ul").append(myLi);
		}
		
		//显示优惠商品
		$(myDiv).attr("name","accessory_"+accessoryList.rows[i].id);
		
		for(var j=0 ; j<goodsPageList[i].rows.length ; j++){
			var myDiv2 = $(myDiv).find('.g_show_tp').eq(0).clone(true);
			$(myDiv2).find("a").attr("href",yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+goodsPageList[i].rows[j].goodsId+"&productId="+goodsPageList[i].rows[j].productId);
			$(myDiv2).find("a").attr("target","_blank");
			$(myDiv2).find("img").attr("src",goodsImgSrcPre + goodsPageList[i].rows[j].picturePath);
			$(myDiv2).find("a").eq(1).text(goodsPageList[i].rows[j].name);
			$(myDiv2).find("a").eq(1).attr("title",goodsPageList[i].rows[j].name);
			if(j==goodsPageList[i].rows.length-1){//去掉最后一个“+”号
				$(myDiv2).find("span").remove();
			}
			$(myDiv).append(myDiv2);
		}
		if(i!=0){
			$(myDiv).css("display","none");
			$('.g_show').find("div.fr").prev().prev().after(myDiv);
		}else{
			//显示上一页和下一页
			if(parseInt(goodsPageList[0].pages) > parseInt(goodsPageList[0].pageIndex)){
				$('.accessoryGoodsNext').show();
				$('.accessoryGoodsNext').attr("onclick","findAccessoryGoods("+accessoryList.rows[i].id+",2)");
			}else{
				$('.accessoryGoodsNext').hide();
				$('.accessoryGoodsNext').attr("onclick","");
			}
//			if(1 < parseInt(goodsPageList[0].pageIndex)){
//				$('.accessoryGoodsPrev').show();
//				$('.accessoryGoodsPrev').attr("onclick","findAccessory("+(parseInt(goodsPageList[0].pageIndex)-1)+")");
//			}else{
//				$('.accessoryGoodsPrev').hide();
//				$('.accessoryGoodsPrev').attr("onclick","");
//			}
		}
		if(i!=accessoryList.rows.length-1){
			myDiv = myDiv.clone(true);//复制该元素并删除子元素（第一个子元素不删）
			var childDivs = $(myDiv).find('.g_show_tp');
			for(var j=1;j<childDivs.length;j++){
				$(childDivs[j]).remove();
			}
		}
		
	}
	//显示上一页和下一页
	if(parseInt(accessoryList.pages) > parseInt(accessoryList.pageIndex)){
		$('.accessoryNext').show();
		$('.accessoryNext').attr("onclick","findAccessory("+(parseInt(accessoryList.pageIndex)+1)+")");
	}else{
//		$('.accessoryNext').hide();
		$('.accessoryNext').attr("onclick","");
	}
	if(1 < parseInt(accessoryList.pageIndex)){
		$('.accessoryPrev').show();
		$('.accessoryPrev').attr("onclick","findAccessory("+(parseInt(accessoryList.pageIndex)-1)+")");
	}else{
//		$('.accessoryPrev').hide();
		$('.accessoryPrev').attr("onclick","");
	}
	$('div.g_f').eq(0).show();
	//查看其它优惠套装
	$('.g_nav ul li').on('click', function(){
		var name = $(this).attr("name");
		$(this).siblings().attr("class","");
		$(this).attr("class","g_nav_cur");
		$('div[name="'+name+'"]').siblings(".g_show_l").hide();
		$('div[name="'+name+'"]').show();
		for(var i = 0;i<accessoryList.rows.length;i++){
			if(accessoryList.rows[i].id==name.split('_')[1]){
				//显示上一页和下一页
				if(parseInt(goodsPageList[i].pages) > parseInt(goodsPageList[i].pageIndex)){
					$('.accessoryGoodsNext').show();
					$('.accessoryGoodsNext').attr("onclick","findAccessoryGoods("+accessoryList.rows[i].id+",2)");
				}else{
					$('.accessoryGoodsNext').hide();
					$('.accessoryGoodsNext').attr("onclick","");
				}
//				if(1 < parseInt(goodsPageList[i].pageIndex)){
//					$('.accessoryGoodsPrev').show();
//					$('.accessoryGoodsPrev').attr("onclick","findAccessory("+(parseInt(goodsPageList[i].pageIndex)-1)+")");
//				}else{
//					$('.accessoryGoodsPrev').hide();
//					$('.accessoryGoodsPrev').attr("onclick","");
//				}
				changeAccessoryPrice(this, accessoryList.rows[i], goodsPageList[i].rows);
			}
		}
	});
}
//加载上/下一页优惠套餐商品
function findAccessoryGoods(accessoryId,pageIndex){
	if(pageIndex==null||accessoryId==null){
		return;
	}
	var url = _path+'/goodsManager/findAccessoryGoods';
	var params = {};
	params.accessoryId = accessoryId;
	params.pageIndex = pageIndex;
	commonAjax(url, params, function(data) {
		if(data.content.result==false){
			if(data.content.isAccessoryExist==false){
//					alert("该商品不存在");
			}
		}else{
			//优惠套餐
			if(data.content!=null&&data.content.rows!=null){
				if(data.content.rows.length>=1){
					//去掉原有的
					var myDiv = $('.g_show_l[name="accessory_'+accessoryId+'"]');
					var goodsDivs = $(myDiv).find('.g_show_tp');
					if(goodsDivs!=null&&goodsDivs.length>=2){
						for(var i=1;i<goodsDivs.length;i++){
							goodsDivs[i].remove();
						}
					}
					for(var j=0 ; j<data.content.rows.length ; j++){
						var myDiv2 = $(myDiv).find('.g_show_tp').eq(0).clone(true);
						$(myDiv2).find("a").attr("href",yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+data.content.rows[j].goodsId+"&productId="+data.content.rows[j].productId);
						$(myDiv2).find("a").attr("target","_blank");
						$(myDiv2).find("img").attr("src",goodsImgSrcPre+data.content.rows[j].picturePath);
						$(myDiv2).find("a").eq(1).text(data.content.rows[j].name);
						if(j==data.content.rows.length-1){//去掉最后一个“+”号
							$(myDiv2).find("span").remove();
						}
						$(myDiv).append(myDiv2);
					}
//					if(data.content.pageIndex==1){
//						$(myDiv).find('.g_show_tp').eq(0).show();
//					}else{
//						$(myDiv).find('.g_show_tp').eq(0).hide();
//					}
					//显示上一页和下一页
					if(parseInt(data.content.pages) > parseInt(data.content.pageIndex)){
						$('.accessoryGoodsNext').show();
						$('.accessoryGoodsNext').attr("onclick","findAccessoryGoods("+accessoryId+","+(data.content.pageIndex+1)+")");
					}else{
						$('.accessoryGoodsNext').hide();
						$('.accessoryGoodsNext').attr("onclick","");
					}
					if(1 < parseInt(data.content.pageIndex)){
						$('.accessoryGoodsPrev').show();
						$('.accessoryGoodsPrev').attr("onclick","findAccessoryGoods("+accessoryId+","+(data.content.pageIndex-1)+")");
					}else{
						$('.accessoryGoodsPrev').hide();
						$('.accessoryGoodsPrev').attr("onclick","");
					}
				}
			}
			
		}
	}, function(data) {
//		easyuiMsg('失败', '请选择要操作的数据项!');
//		alert(errorMsg);
	});
}

//更新商品信息（浏览次数等）
function updateGoodsInfo(){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/updateGoodsInfo';
		var params = {};
		params.goodsId = goodsId;
		commonAjax(url, params, function(data) {
			
		}, function(data) {
			
		});
	}
}
//加载商品车型信息
function findCarData(){
	var goodsId = $('#goods_id').val();
	var carId = $('#car_id').val();
	if(carId!=null&&carId!=''&&carId!='0'){
		var url = _path+'/goodsManager/findCarData';
		var params = {};
		params.goodsId = goodsId;
		params.carId = carId;
		commonAjax(url, params, function(data) {
			if(data.content!=null){
				if(data.content.carType!=null){
					var factoryName = data.content.carType.factoryName;
					var gradename = data.content.carType.gradename;
					if(factoryName!=null&&factoryName!=''){
						$('li.g_cs01').find("i").eq(0).text(factoryName);
					}
					if(gradename!=null&&gradename!=''){
						$('li.g_cs01').find("i").eq(1).text(gradename);
					}
				}
				if(data.content.carList!=null){
					var div = $('#dpt_select').find(".table").eq(1);
					var carList = data.content.carList;
					for(var i=0;i<carList.length;i++){
						$(div).find('.dl01').append('<dd style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis;width:315px;padding-right:0px;">'+carList[i].carName+'</dd>');
						$(div).find('.dl02').append('<dd class="g_dd">'+(carList[i].price!=null?carList[i].price.toFixed(2):'')+'</dd>');//官方指导价
						$(div).find('.dl03').append('<dd></dd>');//优优价
						$(div).find('.dl04').append('<dd>'+(carList[i].storePrice!=null?carList[i].storePrice.toFixed(2):'')+'</dd>');//经销商报价
						$(div).find('.dl05').append('<dd class="br0"><a class="g_dd_a" href="'+yoyo.portalUrl+'/goodsManager/enquiry?type=1&carId='+carList[i].carId+'" style="margin: 8px;" target="_blank">询最低价</a><a class="g_dd_aa" href="'+yoyo.portalUrl+'/goodsManager/enquiry?type=2&carId='+carList[i].carId+'" style="margin: 8px;" target="_blank">预约试驾</a></dd>');//操作
					}
					$(div).find('.dl01').parent().parent().show();
				}
			}
			
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
//			alert(errorMsg);
		});
	}
}

//加载商品评论
function findGoodsComment(pageIndex){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findGoodsComment';
		var params = {};
		params.goodsId = goodsId;
		params.pageIndex = pageIndex;
		var ul = $('.g_b').find("ul").eq(0);
		commonAjax(url, params, function(data) {
			if(data.content!=null){
				var commentPage = data.content;
				if(commentPage!=null&&commentPage.rows.length>=1){
//					var commentPage = data.content.commentPage;
					$('#dis').text("评论("+commentPage.total+")");
					var li = $(ul).find("li").eq(0);
					var li2 = li.clone(true);
					$(ul).empty();
					var commentIds = new Array();
					for(var i=0;i<commentPage.rows.length;i++){
						commentIds.push(commentPage.rows[i].commentId);
						$(li2).attr("name","comment_"+commentPage.rows[i].commentId);
						$(li2).find(".g_b_l p").text(commentPage.rows[i].comment);
						$(li2).find(".tp_f_t").find("a").eq(0).text("回复("+commentPage.rows[i].replyCount+")");
						if(commentPage.rows[i].replyCount>0){//view-all-reply
							$(li2).find(".view-all-reply").show();
//							$(li2).find(".view-link").attr("onclick","reply("+commentPage.rows[i].commentId+")");
							$(li2).find(".view-link").attr("href",yoyo.portalUrl+"/goodsManager/reply?commentId="+commentPage.rows[i].commentId);
						}else{
							$(li2).find(".view-all-reply").hide();
//							$(li2).find(".view-link").attr("onclick","");
							$(li2).find(".view-link").attr("href","javascript:;");
						}
						$(li2).find(".tp_f_t").find("a").eq(1).text("赞("+commentPage.rows[i].praise+")");
//						$(li2).find(".tp_f_t").find("a").eq(1).text("赞(0)");
						$(li2).find(".tp_f_t").find("a").eq(1).attr("href","javascript:addPraise("+commentPage.rows[i].commentId+")");
						$(li2).find(".tp_f_t").find(".reply-textarea").css("display","none");
						$(li2).find('.tp_f_t').find("textarea").attr("placeholder","回复 "+commentPage.rows[i].loginName+"：");
						$(li2).find('.tp_f_t').find("button").attr("data-replyid",commentPage.rows[i].commentId);
//						$(li2).find(".g_b_r li").eq(0).text(commentPage.rows[i].loginName);
						$(li2).find(".g_b_r li").eq(0).text(commentPage.rows[i].loginName);
						$(li2).find(".g_b_r li").eq(1).text(commentPage.rows[i].memberLvName);
						if(commentPage.rows[i].orderCreateDate!=null&&commentPage.rows[i].orderCreateDate!=''){
							$(li2).find(".g_b_r li").eq(2).text(commentPage.rows[i].orderCreateDate+" 购买");
						}else{
							$(li2).find(".g_b_r li").eq(2).hide();
						}
						
						$(li2).find(".tp_div").find("em").css("width",parseInt(commentPage.rows[i].commentStar)*15+"px");
//						$(li2).find(".tp_div").find(".star").css("class","star sa"+commentPage.rows[i].goodsPoint);
						$(li2).find(".tp_div").find("tr").empty();
						$(li2).find(".tp_div").find("span").hide();
						
						if(commentPage.rows[i].picPageList!=null&&commentPage.rows[i].picPageList.length>=1){
							for(var j=0;j<commentPage.rows[i].picPageList.length;j++){
								if(j<5){
									$(li2).find(".tp_div").find("tr").append('<td data="'+j+'"><img src="'+goodsImgSrcPre + commentPage.rows[i].picPageList[j].picturePath+'" rel="'+goodsImgSrcPre + commentPage.rows[i].picPageList[j].picturePath+'" alt="" width="82px" height="82px" onload="autosize(this,82,82)" class="cursor-big"></td>');
								}else{
									$(li2).find(".tp_div").find("tr").append('<td data="'+j+'" style="display:none;"><img src="'+goodsImgSrcPre + commentPage.rows[i].picPageList[j].picturePath+'" rel="'+goodsImgSrcPre + commentPage.rows[i].picPageList[j].picturePath+'"  alt="" width="82px" height="82px" onload="autosize(this,82,82)" class="cursor-big"></td>');
								}
								
							}
//							$(li2).find(".tp_div").find("span").attr("pages",commentPage.rows[i].picPageList.pages);
							$(li2).find(".tp_div").find("span").eq(2).html('共'+commentPage.rows[i].picPageList.length+'张图 <a href="javascript:;" onclick="showHideBig(this)"> 查看晒单&gt;</a>');
							$(li2).find(".tp_div").find("span").show();
						}else{
							$(li2).find(".tp_div").find("tr").empty();
							$(li2).find(".tp_div").find("span").hide();
						}
//						$(li2).find(".tp_div").find(".star").show();
						$(li2).attr("style","");
						$(ul).append(li2);
						
						li2 = li2.clone(true); 
					}
					
					//好评度
					$('.pf_ul1').find("i").eq(0).text("("+commentPage.rows[0].positiveRate.toFixed(0)+"%)");
					$('.pf_ul1').find("i").eq(1).text("("+commentPage.rows[0].neutralRate.toFixed(0)+"%)");
					$('.pf_ul1').find("i").eq(2).text("("+commentPage.rows[0].negativeRate.toFixed(0)+"%)");
					$('.pf_1').find("strong").html(commentPage.rows[0].positiveRate.toFixed(0)+'<span>%</span>');
					$('.pf_ul2').find("i.i01").css("width",commentPage.rows[0].positiveRate.toFixed(0)+"px");
					$('.pf_ul2').find("i.i02").css("width",commentPage.rows[0].neutralRate.toFixed(0)+"px");
					$('.pf_ul2').find("i.i03").css("width",commentPage.rows[0].negativeRate.toFixed(0)+"px");
					
					//标签
					if(commentPage.rows[0].tagsList!=null&&commentPage.rows[0].tagsList.length>=1){
						var tagsList = commentPage.rows[0].tagsList;
						var tagsDiv = $('#dis_select .g_t .pf .pf_3');
						for(var i=0;i<tagsList.length;i++){
							$(tagsDiv).find("ul").append('<li>'+tagsList[i].tag+'<b>('+tagsList[i].count+')</b></li>');
							
						}
						$(tagsDiv).show();
					}
					
					
					//页码
					$('.ui-page').empty();
					if(commentPage.pages >= 2){
						if(commentPage.pageIndex > 1){
							$('.ui-page').append('<a class="ui-pager-prev" href="javascript:findGoodsComment('+(commentPage.pageIndex-1)+')">上一页<b></b></a>');
							$('.ui-page').append('<a href="javascript:findGoodsComment(1)">1</a>');
						}
						if(commentPage.pageIndex-3 > 1){
							$('.ui-page').append('<span>...</span>');
						}
						if(commentPage.pageIndex-2 > 1){
							$('.ui-page').append('<a href="javascript:findGoodsComment('+(commentPage.pageIndex-2)+')">'+(commentPage.pageIndex-2)+'</a>');
						}
						if(commentPage.pageIndex-1 > 1){
							$('.ui-page').append('<a href="javascript:findGoodsComment('+(commentPage.pageIndex-1)+')">'+(commentPage.pageIndex-1)+'</a>');
						}
						$('.ui-page').append('<a class="ui-page-curr">'+commentPage.pageIndex+'</a>');
						if(commentPage.pageIndex+1 < commentPage.pages){
							$('.ui-page').append('<a href="javascript:findGoodsComment('+(commentPage.pageIndex+1)+')">'+(commentPage.pageIndex+1)+'</a>');
						}
						if(commentPage.pageIndex+2 < commentPage.pages){
							$('.ui-page').append('<a href="javascript:findGoodsComment('+(commentPage.pageIndex+2)+')">'+(commentPage.pageIndex+2)+'</a>');
						}
						if(commentPage.pageIndex+3 < commentPage.pages){
							$('.ui-page').append('<span>...</span>');
						}
						if(commentPage.pageIndex < commentPage.pages){
							$('.ui-page').append('<a href="javascript:findGoodsComment('+commentPage.pages+')">'+commentPage.pages+'</a>');
							$('.ui-page').append('<a class="ui-pager-next" href="javascript:findGoodsComment('+(commentPage.pageIndex+1)+')">下一页<b></b></a>');
						}
					}
					
				}else{
					//无评论
					$(ul).empty();
				}
			}else{
				//无评论
				$(ul).empty();
			}
			
			$('.tp_div td').on('click', function(){
				var src = $(this).find("img").attr("rel");
				var data = $(this).attr("data");
//				var index = src.lastIndexOf(".");
//				src = src.substring(0,index)+"."+bigPicSize+"."+src.substring(index+1,src.length);
				$(this).parent().parent().parent().parent().parent().find(".p-photos-viewer").find("img").attr("data",data);
				$(this).parent().parent().parent().parent().parent().find(".p-photos-viewer").find("img").attr("src",src);
				$(this).parent().parent().parent().parent().parent().find(".p-photos-viewer").show();
				var tdList = $(this).siblings("td");
				if(tdList[tdList.length-1].style.display!="none"){
					return;
				}
				$(this).parent().parent().parent().next().removeClass("i-next-disable");
			});
			
			$('.J-hide-big-show').on('click',function(){
				$(this).parent().parent().hide();
			});
			
			$('.J-photo-prev').on('click',function(){
				var data = $(this).prev().attr("data");
				if(parseInt(data)-1>=0){
					var src = $(this).parent().parent().parent().find('.tp_div').find("td").eq(parseInt(data)-1).find("img").attr("rel");
//					var index = src.lastIndexOf(".");
//					src = src.substring(0,index)+"."+bigPicSize+"."+src.substring(index+1,src.length);
					$(this).prev().attr("data",parseInt(data)-1);
					$(this).prev().attr("src",src);
				}
			});
			
			$('.J-photo-next').on('click',function(){
				var data = $(this).prev().prev().prev().attr("data");
				if($(this).parent().parent().parent().find('.tp_div').find("td").eq(parseInt(data)+1).length>=1){
					var src = $(this).parent().parent().parent().find('.tp_div').find("td").eq(parseInt(data)+1).find("img").attr("rel");
//					var index = src.lastIndexOf(".");
//					src = src.substring(0,index)+"."+bigPicSize+"."+src.substring(index+1,src.length);
					$(this).prev().prev().prev().attr("data",parseInt(data)+1);
					$(this).prev().prev().prev().attr("src",src);
				}
			});
			
		}, function(data) {
			$(ul).empty();
//			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}
}

//查看晒单
function showHideBig(ele){
	var tdList = $(ele).parent().parent().find("table").find("td");
	$(tdList[0]).click();
}

//下一张评论图片
function nextCommentPic(ele){
	var tdList = $(ele).prev().find("td");
	//alert(liList.length);
	if(tdList[tdList.length-1].style.display!="none"){
		//alert("return...");
		return;
	}
	for(var i=0;i<tdList.length;i++){
		//if(i+5>=aList.length-1){
		if(tdList[i].style.display!="none"){
			tdList[i].style.display="none";
			tdList[i+5].style.display="";
			/*$('#spec-forward').attr("class","spec-control");*/
			$('.J-thumb-prev').removeClass("i-prev-disable");
			break;
		}
	}
}

//上一张评论图片
function prevCommentPic(ele){
	var tdList = $(ele).next().find("td");
	//alert(aList.length);
	if(tdList[0].style.display!="none"){
		//alert("return...");
		return;
	}
	for(var i=0;i<tdList.length;i++){
		if(tdList[i].style.display!="none"){
			tdList[i-1].style.display="";
			tdList[i+4].style.display="none";
			/*$('#spec-backward').attr("class","spec-control");*/
			$('.J-thumb-next').removeClass("i-next-disable");
			break;
		}
	}
	/*if(liList[0].style.display!="none"){
		$('#spec-forward').attr("class","spec-control disabled");
	}*/
}

//加载商品咨询
function findGoodsDiscuss(){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findGoodsDiscuss';
		var params = {};
		params.goodsId = goodsId;
		params.pageIndex = 1;
		commonAjax(url, params, function(data) {
			var div = $('div.zx');
			if(data.content!=null){
				if(data.content!=null&&data.content.rows.length>=1){
					var commentPage = data.content;
					$('#cst').text("商品咨询("+commentPage.total+")");
					var item = $(div).find("div.item").eq(0);
					var item2 = item.clone(true);
					$(div).empty();
					for(var i=0;i<commentPage.rows.length;i++){
						$(item2).find(".g_user span").eq(1).text(commentPage.rows[i].author);
						$(item2).find(".g_user span").eq(2).text(commentPage.rows[i].commentDate);
						$(item2).find("dd.ml56 a").eq(0).text(commentPage.rows[i].comment);
						if(commentPage.rows[i].replyCommentId==null||commentPage.rows[i].replyCommentId==0){
							$(item2).find("dl").eq(1).hide();
						}else{
							$(item2).find("dd.ml56 a").eq(1).text(commentPage.rows[i].replyComment);
							$(item2).find(".ml400").text(commentPage.rows[i].replyDate);
							$(item2).find("dl").eq(1).show();
						}
						$(item2).attr("style","");
						$(div).append(item2);
						item2 = item2.clone(true); 
					}
					$('.it_f').find("dt").eq(1).text("共"+commentPage.total+"条");
				}else{
					//无咨询
					$(div).empty();
				}
			}else{
				//无咨询
				$(div).empty();
			}
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
			$(div).empty();
		});
	}
}

//加载热销排行榜
function findTopSales(){
	var goodsId = $('#goods_id').val();
	if(goodsId!=null&&goodsId!=''){
		var url = _path+'/goodsManager/findTopSales';
		var params = {};
		params.goodsId = goodsId;
		params.pageIndex = 1;
		commonAjax(url, params, function(data) {
			var ul = $('.charts ul');
			if(data.content!=null){
				if(data.content!=null&&data.content.rows.length>=1){
					var goodsPage = data.content;
					var item = $(ul).find("li").eq(0);
					var item2 = item.clone(true);
					$(ul).empty();
					for(var i=0;i<goodsPage.rows.length;i++){
						$(item2).find("a").attr("href",yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+goodsPage.rows[i].goodsId);
						$(item2).find("img").attr("src",goodsImgSrcPre+goodsPage.rows[i].smallPic);
						$(item2).find("img").attr("alt",goodsImgSrcPre+goodsPage.rows[i].name);
						
						$(item2).find("h4").text(goodsPage.rows[i].name);
						$(item2).find("h3").text("￥"+goodsPage.rows[i].price);
						$(item2).find("h5").text("已出售"+goodsPage.rows[i].buyCount+"件");
						$(item2).attr("style","");
						$(ul).append(item2);
						item2 = item2.clone(true); 
					}
				}else{
					//无热销
					$(ul).empty();
				}
			}else{
				//无热销
				$(ul).empty();
			}
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
			$(ul).empty();
		});
	}
}

//将商品加入我的关注
function addWishList(){
	var productId = '';
	var chooseSpecDivs = $('.g_cs_b').find('div[name^="choose_spec_"]');
	if($(chooseSpecDivs).length>=1){
		if($(chooseSpecDivs).find("li.g_cur").length == $(chooseSpecDivs).length){
			var selectSpecDesc='';
			for(var i=0;i<chooseSpecDivs.length;i++){
				if($(chooseSpecDivs[i]).find("li.g_cur").length>=1){
					selectSpecDesc+=$(chooseSpecDivs[i]).attr("name").split("_")[2];
					selectSpecDesc+="|";
					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_name').attr("title");
					selectSpecDesc+=":";
//					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[0];
					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("dataid");
					selectSpecDesc+="|";
					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("title");
					if(i!=chooseSpecDivs.length-1){
						selectSpecDesc+=",";
					}
				}
			}
			for(var i=0;i<productList.length;i++){
				if(productList[i].specDesc == selectSpecDesc){
					productId = productList[i].productId;
					break;
				}
			}
		}
	}
	if(productId==''&&productList!=null&&productList.length>=1){
		for(var i=0;i<productList.length;i++){
			productId = productList[i].productId;
			break;
		}
	}
//	return productId;
//	var productId = findProductId();
	var goodsId = $('#goods_id').val();
	
//	alert(productId+"..."+goodsId);
	if((productId!=null&&productId!='')||(goodsId!=null&&goodsId!='')){
		var url = yoyo.memUrl+'/productWishList/addWishList';
		var params = {};
		params.productId = "["+productId+"]";
		params.goodsId = goodsId;
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
				alert("关注成功");
			}
		}, function(data) {
			if(data.head.retCode == '000004'){
				alert("您已关注该商品");
			}else if(data.head==null){
				window.location.href = loginUrl;
			}
		})
	}	
}

//转跳咨询列表页
function toConsult(goodsId){
//	var productId = '';
//	var chooseSpecDivs = $('.g_cs_b').find('div[name^="choose_spec_"]');
//	if($(chooseSpecDivs).length>=1){
//		if($(chooseSpecDivs).find("li.g_cur").length == $(chooseSpecDivs).length){
//			var selectSpecDesc='';
//			for(var i=0;i<chooseSpecDivs.length;i++){
//				if($(chooseSpecDivs[i]).find("li.g_cur").length>=1){
//					selectSpecDesc+=$(chooseSpecDivs[i]).attr("name").split("_")[2];
//					selectSpecDesc+="|";
//					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_name').attr("title");
//					selectSpecDesc+=":";
//					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[0];
//					selectSpecDesc+="|";
//					selectSpecDesc+=$(chooseSpecDivs[i]).find('div.g_s_data').find('li.g_cur').attr("data").split("_")[1];
//					if(i!=chooseSpecDivs.length-1){
//						selectSpecDesc+=",";
//					}
//				}
//			}
////			var productId = '';
//			for(var i=0;i<productList.length;i++){
//				if(productList[i].specDesc == selectSpecDesc){
//					productId = productList[i].productId;
//					break;
//				}
//			}
//		}else{
////			alert("请选择商品规格");
//		}
//	}
	var productId = findProductId();
//	if((productId!=null&&productId!='')&&(goodsId!=null&&goodsId!='')){
		window.location.href = yoyo.portalUrl+"/goodsManager/consult?productId="+productId+"&goodsId="+goodsId;
//	}
}

//点赞
function addPraise(commentId){
	if(commentId!=null&&commentId!=''){
		var url = _path+'/goodsManager/addPraise';
		var params = {};
		params.commentId = commentId;
		commonAjax(url, params, function(data) {
			if(data.content!=null&&data.content.result==null){
				$('li[name="comment_'+commentId+'"]').find(".tp_f").find("a").eq(1).text("赞("+data.content+")");
//				alert(data.content);
			}else if(data.content.result == false){
				if(data.content.isBuyer==false||data.content.msg=="请先登录"){
					window.location.href=loginUrl;
				}else{
					alert(data.content.msg);
				}
			}
		}, function(data) {
			if(data.head==null){
				window.location.href = loginUrl;
			}
		});
	}
}

//回复评论
function toReply(ele){
	if($(ele).parent().find('.reply-textarea').css("display")=="none"){
		$(ele).parent().find('.reply-textarea').find('.inner textarea').val('');
		$(ele).parent().find('.reply-textarea').show();
	}else{
		$(ele).parent().find('.reply-textarea').hide();
	}
}

//提交回复
function submitReply(ele){
	var commentText = $(ele).parent().parent().find("textarea").val();
	var commentId = $(ele).attr("data-replyid");
	var toName = $(ele).parent().prev().attr('placeholder').split(' ')[1];
	toName=toName.substring(0,toName.length-1);
	if(commentId!=null&&commentId!=''){
		if(commentText!=null&&commentText!=''){
			var url = _path+'/goodsManager/addReply';
			var params = {};
			params.commentId = commentId;
			params.commentText = commentText;
			commonAjax(url, params, function(data) {
				if(data.content!=null&&data.content.result==null){
					if(data.content.length>=1){
						var comment = data.content[0];
						var str = '';
						str+='<div class="comment-reply-item" data-name="" data-uid="">'
								+'<div class="reply-infor">'
									+'<span class="user-name">'
										+comment.loginName
									+'</span>';
						if(comment.toId!='0'){
							str+=	'<em> 回复  </em><span>'+toName+'</span>';
						}
						
						str+=		'：<span class="words">'+comment.comment+'</span>'
									+'<span class="time">　'+(comment.commentDate!=null?comment.commentDate:"")+'</span>'
								+'</div>'
								+'<div class="comment-operate">'
									+'<span class="reply J-reply-trigger" onclick="toReply(this)">回复</span>'
									+'<div class="reply-textarea hide J-reply-con">'
										+'<div class="reply-arrow"><b class="layer1"></b><b class="layer2"></b></div>'
										+'<div class="inner">'
											+'<textarea class="reply-input" name="" id="" placeholder="回复 '+comment.loginName+'："></textarea>'
											+'<div class="btnbox"><button type="button" data-guid="" data-replyid="'+comment.commentId+'" class="reply-submit J-submit-reply" onclick="submitReply(this)">提交回复</button></div>'
										+'</div>'
									+'</div>'
								+'</div>'
							+'</div>';
						
						$(ele).parent().parent().parent().hide();
						
						
//						$(ele).parent().parent().parent().parent().parent().find('.comment-replylist').prepend(str);
						$(ele).parents('.tp_f').find('.comment-replylist').prepend(str);
						
						var replyCount = $(ele).parents(".tp_f").find(".tp_f_t").find("a").eq(0).text();
						replyCount = replyCount.substring(replyCount.indexOf("(")+1,replyCount.length);
						replyCount = replyCount.substring(0,replyCount.lastIndexOf(")"));
						$(ele).parents(".tp_f").find(".tp_f_t").find("a").eq(0).text("回复("+(parseInt(replyCount)+1)+")");
					}else{
						alert(errorMsg);
					}
					
					
					
				//	<div class="comment-reply-item" data-name="317916383-470734" data-uid="60149556">                    <div class="reply-infor">                        <a class="user-name" target="_blank" href="http://club.jd.com/userreview/60149556-1-1.html">                            317916383-470734                        </a>：                        <span class="words">哈哈</span>                        <span class="time">　2015-05-20 10:03</span>                    </div>                    <div class="comment-operate">                        <span class="reply J-reply-trigger">回复</span>                        <div class="reply-textarea hide J-reply-con">                            <div class="reply-arrow"><b class="layer1"></b><b class="layer2"></b></div>                            <div class="inner">                                <textarea class="reply-input" name="" id="" placeholder="回复 317916383-470734："></textarea>                                <div class="btnbox"><button type="button" data-guid="fa47c772-8602-421a-822f-d1524de86a9a" data-replyid="44104537" class="reply-submit J-submit-reply">提交回复</button></div>                            </div>                        </div>                    </div>                </div>
//					alert(data.content);
				}else if(data.content.result == false){
					if(data.content.isBuyer==false){
						window.location.href=loginUrl;
					}else{
						if(data.content.msg=="请先登录"){
							window.location.href=loginUrl;
						}else{
							alert(data.content.msg);
						}
					}
					
				}
			}, function(data) {
				if(data.head==null){
					window.location.href = loginUrl;
				}
			});
		}else{
			alert("请输入回复内容");
		}
	}else{
		alert("该评论不存在");
	}
}

//加载参数配置
function findCarParam(){
	var goodsId = $('#goods_id').val();
	var carId = $('#car_id').val();
	if(carId!=null&&carId!=''&&carId!='0'){
		var url = _path+'/goodsManager/findCarParam';
		var params = {};
		params.goodsId = goodsId;
		params.carId = carId;
		commonAjax(url, params, function(data) {
			if(data.content!=null&&data.content.carDataCatalogDTOs!=null&&data.content.carDataCatalogDTOs.length>=1){
				var carDataCatalog = data.content.carDataCatalogDTOs;
				var contentDiv = $('#par_select .row .column .title .title-content');
				var str='';
				var carData = null;
				for(var i=0;i<carDataCatalog.length;i++){
					//左侧
//					if(carDataCatalog.length==1){
//						$('#navScrollLeft .folul').append('<li class="top"><a class="" href="javascript:;" scroll-top="">'+carDataCatalog[i].catalogName+'</a></li>');
//					}else if(i==0){
//						$('#navScrollLeft .folul').append('<li><a class="" href="javascript:;" scroll-top="">'+carDataCatalog[i].catalogName+'</a></li>');
//					}else if(i==carDataCatalog.length-1){
//						$('#navScrollLeft .folul').append('<li class="bottom"><a class="" href="javascript:;" scroll-top="">'+carDataCatalog[i].catalogName+'</a></li>');
//					}
					//右侧
					$(contentDiv).append('<div class="js-title genre-title" data-title="'+carDataCatalog[i].catalogName+'"><i class="icon10 icon10-pack"></i><h3 id="floor'+(i+1)+'">'+carDataCatalog[i].catalogName+'</h3></div>');
					str='<table class="js-titems tableinfo">';
					carData = carDataCatalog[i].carDatas;
					for(var j=0 ; j<carData.length ; j++){
						str+='<tr><th class="title" dataid="'+carData[j].dataId+'"><a href="javascript:;" target="_blank">'+carData[j].displayName+'</a></th>';
						str+='<td class="text" dataid="'+carData[j].dataId+'">-</td></tr>';
					}
					str+='</table>';
					$(contentDiv).append(str);
				}
				//参数值
				if(data.content.carConfig!=null&&data.content.carConfig.length>=1){
					var carConfig = data.content.carConfig;
					for(var i=0;i<carConfig.length;i++){
						$('td[dataid="'+carConfig[i].dataId+'"]').text(carConfig[i].configValue);
						$('td[dataid="'+carConfig[i].dataId+'"]').attr("escapeXml",true);
						if(carConfig[i].displayName=='厂商'){
							$('.g_cs01 p i').eq(0).text(carConfig[i].configValue);
						}else if(carConfig[i].displayName.indexOf('级别')>=0){
//							$('.g_cs01 p i').eq(0).text(carConfig[i].configValue);
						}else if(carConfig[i].displayName.indexOf('质保')>=0){
							$('.g_cs02 p i').eq(0).text(carConfig[i].configValue);
						}else if(carConfig[i].displayName.indexOf('保养')>=0){
							$('.g_cs02 p i').eq(1).text(carConfig[i].configValue);
						}else if(carConfig[i].displayName.indexOf('油耗')>=0){
							$('.g_cs03 p i').eq(0).text(carConfig[i].configValue);
						}else if(carConfig[i].displayName.indexOf('燃油')>=0&&carConfig[i].displayName.indexOf('标号')>=0){
							$('.g_cs03 p i').eq(1).text(carConfig[i].configValue);
						}
					}
				}
				$('#par').show();
			}
			
		}, function(data) {
//			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}
}

////查看全部回复内容
//function reply(commentId){
//	if(commentId!=null&&commentId!=''&&commentId!=0){
//		window.location.href = yoyo.portalUrl+"/goodsManager/reply?commentId="+commentId+"&productId="+productId;
//	}
//}

function autosize(ImgD,h,w){
	var width,height;
	var image=new Image(); 
	image.src=ImgD.src; 
	//alert(image.width+"..."+image.height);
	if (image.width<w && image.height<h){
	    ImgD.width=image.width;
	    ImgD.height=image.height;
	    width=image.width;
	    height=image.height;
	}else{
	    if (w / h <= image.width / image.height){
	        ImgD.width=w;
	        ImgD.height=w * (image.height / image.width);
	        width=w;
	    	height=w * (image.height / image.width);
	    }else{
	        ImgD.width=h * (image.width / image.height);
	        ImgD.height=h;
	        width = h * (image.width / image.height);
	    	height = h;
	    }
	}
	$(ImgD).css("width",width);
	$(ImgD).css("height",height);
	ImgD.style.width=width;
	ImgD.style.height=height;
}

function imgErr(img) { 
	img.src = url_upload+defaultGoodsImage;
	img.onerror=null; //控制不要一直跳动 
}

var utils={
		readCookie:function (cookieName){
			var cookieV=$.cookie(cookieName);// 存储 cookie
			return cookieV;
		},
		writeCookie:function(cookieName,cookieValue){
			$.cookie(cookieName,cookieValue,{expires: 7, path: "/"}); // 存储 cookie
		}
	};
function hideCompare(){
	utils.writeCookie("hide_compare", "1");
	$("#pop-compare").hide();
	
}

function addCompare(goodsId,name,goodsPic,price){
	var val=[],notJoin=true,compareObj={id:goodsId,name:name,pic:goodsPic,price:price};
	var accessory=utils.readCookie("accessory_compare");
	if(accessory){
		$.each(JSON.parse(accessory),function (x,item){
			if(item.id==goodsId){
				alert("已加入对比栏");
				notJoin=false;
				return notJoin;
			}else{
				val.push(item);
			}
		});
		if(!notJoin)return;
	}
	utils.writeCookie("hide_compare", "0");
	val.push(compareObj);	
	utils.writeCookie("accessory_compare", JSON.stringify(val));
	initCompareItems();
}

function delCompeareItem(id){
	var accessory=utils.readCookie("accessory_compare"),val=[];
	if(accessory){
		val=JSON.parse(accessory);
		$.each(val,function (x,item){
			if(item.id==id){
				val.splice(x, 1);
			}
		});		
	}	
	utils.writeCookie("accessory_compare", JSON.stringify(val));
	initCompareItems();
}

function clearAllCompare(){
	utils.writeCookie("accessory_compare", "");
	initCompareItems();
}

function toCompare(){
	var accessory=utils.readCookie("accessory_compare"),val=[];
	if(accessory){
		$.each(JSON.parse(accessory),function (x,item){
			val.push(item.id);
		});		
	}
	if(val.length>0){
		window.open(yoyo.portalUrl+"/carCompare/goodsCompare?ids="+val.join(","));
	}
}

function initCompareItems(){
	var accessory=utils.readCookie("accessory_compare"),html=[],spaceColNum=4,hideMe=utils.readCookie("hide_compare");
	if(accessory){
		spaceColNum=JSON.parse(accessory).length> 4 ? 0 : 4 - JSON.parse(accessory).length;
		$.each(JSON.parse(accessory),function (x,item){
			html.push('<dl fore="0" id="cmp_item_1258277" class="hasItem">');
			html.push('<dt>');
			html.push('<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+item.id+'" target="_blank">');
			html.push('<img width="50" height="50" src="'+yoyo.imagesUrl+item.pic+'">');
			html.push('</a>');
			html.push('</dt>');
			html.push('<dd>');
			html.push('<a href="'+yoyo.portalUrl+'"/goodsManager/goodsIndex?goodsId='+item.id+'" class="diff-item-name" target="_blank">'+item.name+'</a>');
			html.push('<span class="p-price">');
			html.push('<strong class="J-p-1258277">￥'+item.price+'</strong>');
			html.push('<a skuid="1258277" class="del-comp-item" onclick="delCompeareItem('+item.id+')" style="visibility: visible;">删除</a>');
			html.push('</span>');
			html.push('</dd>');
			html.push('</dl>');
		});
	}
	for (var j = 0; j < spaceColNum; j++) {
		html.push('<dl class="item-empty">');
		html.push('<dt>'+(j+1)+'</dt>');
		html.push('<dd>您还可以继续添加</dd>');
		html.push('</dl>');
	}
	$("#diff-items").html(html.join(""));
	if(hideMe==0){
		$("#pop-compare").show();
	}
}
function subReport(){
	$.post(yoyo.portalUrl+"/islogin",function (result){
		if(result.retCode==000000){
			var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
			if(!reg.test($("#mobile").val())) {
		      layer.alert("请输入正确的手机号！",{title:false,closeBtn:false,icon:0});
		      return;
			}
			$.ajax({
			    url:yoyo.memUrl+'/report/saveReport',
			    data:$("#reportForm").serialize(),
			    type:'post',    
			    cache:false,
			    dataType:'json',    
			    success:function(head){
			    	if(head.retCode==000000){
			    		layer.alert("商品举报成功！",{title:false,closeBtn:false,icon:1,end:function(){
			    			layer.closeAll();
			    		}});
			    		$("#reportBtn").parent().remove();
			    	}else{		    		
			    		layer.alert("商品举报失败！",{title:false,closeBtn:false,icon:0,end:function(){
			    			layer.closeAll();
			    		}});
			    	}
			    }
			});
		}else{
			layer.alert("请先登录在进行举报！",{title:false,closeBtn:false,icon:0});
		}
	});
}
function openReport(){
	layer.open({
		type: 1,
		title:false,
		move: false,
		area: ['700px', '435px'],
		closeBtn :2,
		zIndex:999,
		shadeClose: true, //点击遮罩关闭
		content:$("#report")		
	});
}



function focuseShop(companyId){
	$.post(yoyo.portalUrl+"/shop/focusStore",{companyId:companyId},function (result){
		if(result.retCode=="000000"){
			layer.msg("关注店铺成功!",{time:2000});
		}else if(result.retCode=="NL001"){
			layer.msg("请登录后再关注店铺!",{time:2000});
		}else if(result.retCode=="000002"){
			if(result.content){
				layer.msg(result.content.msg,{time:2000});	
			}else{
				layer.msg("关注店铺失败",{time:2000});
			}
		}
	});
}
/*//图片加载失败触发事件
function imgErr(img) { 
	img.src = yoyo.shopUrl + defaultGoodsImage;
	img.onerror=null; //控制不要一直跳动 
}*/