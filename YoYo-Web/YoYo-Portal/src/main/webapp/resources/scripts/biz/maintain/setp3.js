$(function (){
	maintian.init();	
	$(".itxt").focusout(function (){
		if($(this).val()>parseInt($(this).attr("data-max"))){
			$(this).val($(this).attr("data-max"));
		}else{
			$(this).css({"border-color":"#cacbcb"});
		}
	});
});

var maintian={
	maintian_items:[],
	maintian_category_id:[],
	my_car_cook:"my_car",
	is_login:"IS_LOGIN",
	init:function (){
		maintian.totalPrice();
		maintian.loadOptionalAccessories();
		var mycar=this.readCookie(this.my_car_cook);
		if(mycar){
			mycar=mycar.split("|");
			$(".models1 h3 b").html(mycar[3]+" "+mycar[4]);
		}
		
		$(".addmainten").click(function (){
			var len =$(this).closest(".delist").find("input:checkbox:checked").length;
			if(len==0){
				alert("请选择保养项目");
				return;
			}
			var maintainItem={
				index:$(this).closest(".delist").index(),
				maintainText:$(this).attr("data-orgs"),
				maintainItems:[]
			};
			$(this).closest(".delist").find("input[type='checkbox']").each(function (x,item){
				if($(item).is(":checked")){
					maintainItem.maintainItems.push($(item));
					maintian.maintian_category_id.push($(item).val());
				}
			});
			maintian.addMaintainItem($(this).closest(".delist").index(),maintainItem);
			maintian.loadMaintainCompany();
		});
		
		$("#div_brandselect").find("span").click(function(event){
			$(this).siblings(".ol_brand").show();
			//隐藏下拉框
			$("body").bind("click", function (){
				$(".ol_brand").hide();
				$("body").unbind("click");
			});
			event.stopPropagation();
		});

		$("#div_partselect").find("span").click(function(event){
			$(this).siblings(".ol_part").show();+
			//隐藏下拉框
			$("body").bind("click", function (){
				$(".ol_part").hide();
				$("body").unbind("click");
			});
			event.stopPropagation();
		});
		
		$(".ol_part li").click(function(){
			$("#span_curpartname").attr("data",$(this).children().attr("data")).html("<i></i>"+$(this).children().text());
			$("#choose_category_id").val($(this).children().attr("data"));
			maintian.loadOptionalAccessories();
		});
		
		$(".ol_brand").on("click","li",function(event){
			$("#span_curbrandname").attr("data",$(this).children().attr("data")).html("<i></i>"+$(this).children().text());
			$("#choose_brand_id").val($(this).children().attr("data"));
			maintian.loadOptionalAccessories();
			$(this).parent().hide();
		});
		
		$(".item-last").on("click",".increment",function (){
			if($(this).prev().val()<99&&$(this).prev().val()<parseInt($(this).prev().attr("data-max"))){
				$(this).prev().val(parseInt($(this).prev().val())+1);
				maintian.totalPrice();
			}
		});
		
		$(".item-last").on("click",".decrement",function (){			
			if($(this).next().val()<=parseInt($(this).next().attr("data-max"))){
				$(this).next().css({"border-color":"#cacbcb"});
			}
			if($(this).next().val()>1){
				$(this).next().val(parseInt($(this).next().val())-1);
				maintian.totalPrice();
			}
		});
		
		$(".item-last").on("click",".cart-remove",function (){
			$(this).closest(".item-form").remove();
			maintian.totalPrice();
			var categoryId=$(this).attr("data-key");
			$(".pName").each(function(x,p_name){
				if($(p_name).attr("name")==categoryId){
					$(p_name).attr("rowspan",parseInt($(p_name).attr("rowspan"))-1);
				}
			});
		});
	},
	addMaintainItem:function (index,newItem){
	},
	totalPrice:function(){
		var total=0;
		$(".item-form").each(function (x,item){
			if($(item).find(".p-sum").length>0&&$(item).find(".itxt").length>0){
				total+=parseFloat($(item).find("input[name='price']").val())*parseInt($(item).find(".itxt").val());
			}
		});
		$(".sumPrice em").html("￥"+parseFloat(total).toFixed(2));
	},
	loadOptionalAccessories:function(){
		var html=[];
		$.post("../carMaintain/findOptionalAccessories",{categoryId:$("#choose_category_id").val(),companyId:$("#company_id").val(),brandId:$("#choose_brand_id").val()},function(result){
			$.each(result.content.product,function (x,item){
				html.push('<tr>');
				html.push('<td class="pPic"><i></i>');
				html.push('<a target="_blank" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+item.goodsId+'" class="hidefocus" hidefocus="true">');
				html.push('<img src="'+yoyo.imagesUrl+item.picturePath+'"></a></td>');
				html.push('<td class="pLink"><a target="_blank" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+item.goodsId+'" class="hidefocus" hidefocus="true">'+item.productName+'</a>');
				html.push('<span style="color:#090;display:block;"></span></td>');
				html.push('<td class="pPrice">￥'+parseFloat(item.price).toFixed(2)+'&nbsp;</td>');
				html.push('<td class="pDo">&nbsp;<a name="" style="width:34px;height:33px;" onclick="choose(\''+item.categoryId+'_'+item.productId+'\',\''+item.productName+'\','+item.price+','+item.categoryId+','+item.productId+','+item.companyId+','+item.storeId+','+item.goodsId+','+item.store+')" href="javascript:;" class="hidefocus" hidefocus="true">选用</a></td>');
				html.push('</tr>');
			});
			$("#moreitemlist").find("table tr:gt(0)").remove();
			$("#moreitemlist").find("table").append(html.join(""));
			html=[];
			$.each(result.content.brand,function (x,item){
				html.push('<li><a hidefocus="true" class="hidefocus" href="javascript:;" data="'+item.brandId+'" status="1">'+item.brandName+'</a></li>');
			});
			$(".ol_brand").html(html.join(""));
		});
	},
	readCookie:function (cookieName){
		var cookieV=$.cookie(cookieName);// 存储 cookie
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7, path: "/"}); // 存储 cookie
	}
};

function choose(cur,name,price,categoryId,productId,companyId,storeId,goodsId,store){
	$(".item-last").each(function(x,item_last){
		if($(item_last).attr("data")==categoryId){
			var result=true;
			$(".item-form").each(function (x,item){
				if($(item).find("input[name='productId']").val()==productId){
					$(item).find(".itxt").val(parseInt($(item).find(".itxt").val())+1);
					result=false;
					return result;
				}
			});
			if(result){
				var html=[];
				html.push('<div class="item-form">');
				html.push('<div class="cell p-goods">');
				html.push('<a href="" target="_blank">'+name+'</a>');
				html.push('</div>');
				html.push('<div class="cell p-sum">');
				html.push('<strong>'+parseFloat(price).toFixed(2)+'</strong>');
				html.push('</div>');
				html.push('<div class="cell p-quantity">');
				html.push('<div class="quantity-form">');
				html.push('<a href="javascript:void(0);" class="decrement" id="">-</a>'); 
				html.push('<input type="text" autocomplete="off" name="quantity" class="itxt" value="1" maxlength="2" id="">'); 
				html.push('<a href="javascript:void(0);" class="increment" id="">+</a>');
				html.push('</div>');
				html.push('<div class="cell p-quantity">库存:'+store+'</div>');
				html.push('</div>');
				html.push('<div class="cell p-ops">');
				html.push('<a id="" class="cart-remove" href="javascript:void(0);">删除</a>');
				html.push('</div>');
				html.push('<input type="hidden" name="productId" value="'+productId+'">');
				html.push('<input type="hidden" name="goodsId" value="'+goodsId+'" />');
				html.push('<input type="hidden" name="storeId" value="'+storeId+'" />');
				html.push('<input type="hidden" name="companyId" value="'+companyId+'" />');				
				html.push('<input type="hidden" name="disable" value="0" />');
				html.push('<input type="hidden" name="marketable" value="1" />');
				html.push('<input type="hidden" name="store" value="'+store+'" />');
				html.push('<input type="hidden" name="price" value="'+price+'" />');
				html.push('</div>');
				$(item_last).append(html.join(""));
			}
		}
	});
	maintian.totalPrice();
}

function addToCart(){
	var carts=[],pass=true;	
	$(".item-form").each(function (x,item){
		var cart={
				quantity:$(item).find("input[name='quantity']").val(),
				goodsId:$(item).find("input[name='goodsId']").val(),
				productId:$(item).find("input[name='productId']").val(),
				companyId:$(item).find("input[name='companyId']").val(),
				storeId:$(item).find("input[name='storeId']").val(),
				appointment:$(item).find("input[name='appointment']").val()
			};
		if(parseInt(cart.quantity)>parseInt($(item).find("input[name='quantity']").attr("data-max"))){
			pass=false;
			$(item).find("input[name='quantity']").css({"border-color":"red"});
			layer.alert("数量不能大于库存数！",{title:false,closeBtn:false,icon:0});
			return pass;
		}
		if($(item).find("input[name='disable']").val()!=0){
			layer.alert("存在失效的商品！",{title:false,closeBtn:false,icon:0});
			$(item).parent().css({"border": "1px solid red"});
			pass=false;
			return pass;
		}
		if($(item).find("input[name='marketable']").val()!=1){
			layer.alert("存在已下架的商品！",{title:false,closeBtn:false,icon:0});
			$(item).parent().css({"border":"1px solid red"});
			pass=false;
			return pass;
		}
		carts.push(cart);	
	});
	if(!pass)return;
	if(carts.length==0){
		layer.alert("保养项目中无商品，无法加入到购物车！",{title:false,closeBtn:false,icon:0});
		return;
	}
	if(loginstatus&&loginstatus==1){
		$("#addToCart").hide();
		$("#process").show();
		$.ajax({
		    url:yoyo.portalUrl+'/cart/addToCart',
		    data:{carts:JSON.stringify(carts)},
		    type:'post',    
		    cache:false,    
		    dataType:'json',
		    success:function(head){	    	
		    	if(head&&head.retCode==yoyo.successCode){
	    			window.location.href=yoyo.portalUrl+'/cart/index';
		    	}
		    }
		});
	}else{
		window.location.href=yoyo.portalUrl+'/register/login?returnUrl='+encodeURIComponent(document.location.href);
	}
}