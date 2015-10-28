var total,rows,checkArry=[];
$(function (){
	saveViewStore();
	loadProductViewHistory({refresh:true});
	$(".switchable-triggerBox li").click(function (){
		$(this).addClass("active").siblings().removeClass("active");		
		$(".history_content").eq($(this).index()).show().siblings().hide();
		$("#page-num").val("1");
		$("#del-all-box").prop("checked",false);
		checkArry=[];
		$.each($(".gridlist").find(":checkbox"),function (x,box){
			$(box).prop("checked",false);
		});
		if($(this).attr("data-args")=='product'){
			loadProductViewHistory({refresh:true});
		}else{
			loadStoreViewHistory({refresh:true});
		}
	});
	
	$("#del-all-box").click(function (){
		if($(this).is(":checked")){
			checkArry = new Array();
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).prop("checked",true);
				checkArry.push($(box).val());
			});
		}else{
			checkArry = [];
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).prop("checked",false);
			});
		}
	});
	
	$("#del-all-btn").click(function (){
		if(typeof(checkArry) == "undefined"||checkArry.length==0){
			layer.alert("请选择需要删除的数据！",{title:false,closeBtn:false,icon:0});
			return;
		}
		if($(".switchable-triggerBox li").eq(0).hasClass("active")){
			console.log(checkArry);
			deleteProductViewHistory(checkArry.join(","));
		}else{
			deleteStoreViewHistory(checkArry.join(","));
		}
	});

	$(".gridlist").on("click",":checkbox",function (){
		if(typeof(checkArry) == "undefined"){
			checkArry = new Array();
		}
		if($(this).is(":checked")){
			checkArry.push($(this).val());
		}else{
			for(var i=0;i<checkArry.length;i++){
				if($(this).val()==checkArry[i]){
					checkArry.elremove(i);  
				}
			}
		}
	});
});

function addToCart(productId){
	$.ajax({
	    url:yoyo.portalUrl+'/cart/addCart',
	    data:{productId:productId,quantity:1},
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data){
	    	if(data.retCode==0000){
	    		if(data.content.result){
	    			layer_utils.confirm("加入购物车成功 是否前往购物车结算?",function (){
	    				window.location.href=yoyo.portalUrl+"/cart/index";
	    			});
	    		}else{
	    			layer.msg(data.content.msg,{icon:0,shade: [0.3, '#393D49'],time:1500});
	    		}
	    	}else{
	    		layer_utils.alert("加入购物车失败");
	    	}
	    }
	});
}

function saveViewStore(){
	var historyCookieV=$.cookie("v_store"),view_history=[];// 存储 cookie
	if(historyCookieV){
		$.ajax({
		    url:'../memberViewHistory/saveViewStore',
		    data:{viewStore:historyCookieV},
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(head){
		    	//$.cookie("v_store","",{path: "/"}); // 存储 cookie
		    }
		});
	}
}
function deleteStoreViewHistory(id){
	layer_utils.confirm("是否删除选择选中店铺",function (){
		$.ajax({
		    url:'../memberViewHistory/deleteStoreViewHistory',
		    data:{id:id},
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(head){
		    	if(head.headObject.retCode==0000){
		    		$("#page-num").val("1");
		    		$("#del-all-box").attr("checked",false);
		    		layer.alert("浏览店铺删除成功！",{title:false,closeBtn:false,icon:1,end:loadStoreViewHistory({refresh:true})});
		    		checkArry=[];
		    	}else{
		    		layer.alert("浏览店铺删除失败！",{title:false,closeBtn:false,icon:0});
		    	}
		     }
		});
	});
}

function deleteProductViewHistory(id){
	layer_utils.confirm("是否删除选择选中商品?",function (){
		$.ajax({
		    url:'../memberViewHistory/deleteProductViewHistory',
		    data:{id:id},
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(head){
		    	if(head.headObject.retCode==0000){
		    		$("#page-num").val("1");
		    		loadProductViewHistory({refresh:true});
		    		$("#del-all-box").attr("checked",false);
		    		layer.alert("浏览商品删除成功！",{title:false,closeBtn:false,icon:1,end:loadProductViewHistory({refresh:true})});
		    		checkArry=[];
		    	}else{
		    		layer.alert("浏览商品删除失败！",{title:false,closeBtn:false,icon:0});
		    	}
		     }
		});
	});
}

function loadStoreViewHistory(opts){
	$.ajax({
	    url:'../memberViewHistory/loadStoreViewHistory',
	    data:{page:$("#page-num").val(),rows:10},
	    type:'post',
	    cache:false,
	    dataType:'json',
	    success:function(data) {
	    	var content=data.content;
	    	if(data.retCode==0000){
	    		$("#sotre_history_list").find("tr:gt(0)").remove();
	    		buildStoreViewHistoryHTML(content);
	    	}
	    	if(opts&&opts.refresh){
				var opt = {
	    				items_per_page:content.pageSize,
	    				callback:function (){
	    					loadStoreViewHistory({refresh:false});
	    				}
    				};
	    		$("#Pagination").yoyoPagination(content.total,opt);
	    	}
	     },    
	     error : function() {    
	     }
	});
	$("#del-all-box").prop("checked",false);
	checkArry=[];
}

function loadProductViewHistory(opts){
	$.ajax({
	    url:'../memberViewHistory/loadProductViewHistory',
	    data:{page:$("#page-num").val(),rows:10},
	    type:'post',
	    cache:false,
	    dataType:'json',
	    success:function(data) {
	    	var content=data.content;
	    	if(data.retCode==0000){
	    		$("#product_history_list").find("tr:gt(0)").remove();
	    		buildProductViewHistoryHTML(content);
	    	}
	    	if(opts&&opts.refresh){
				var opt = {
	    				items_per_page:content.pageSize,
	    				callback:function (){
	    					var op={
	    						refresh:false
	    					};
	    					loadProductViewHistory(op);
	    				}
    				};
	    		$("#Pagination").yoyoPagination(content.total,opt);
	    	}
	     },    
	     error : function() {    
	     }
	});
	$("#del-all-box").prop("checked",false);
	checkArry=[];
}

function buildProductViewHistoryHTML(contents){
	var html=[];
	$.each(contents.rows,function (x,content){
		var time=new Date(),productUrl="";
		time.setTime(content.viewDate);
		html.push('<tr>');
		html.push('<td class=""><input type="checkbox" value='+content.goodsId+' class="check"></td>');
		html.push('<td ><img width="50" height="50" src='+yoyo.imagesUrl+content.picturePath+' /></td>');
		html.push('<td style="text-align:left;"><a target="_blank" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+content.goodsId+'">'+isNull(content.productName)+'</a></td>');
		html.push('<td class="">￥'+formatCurrency(isNull(content.price))+'</td>');
		html.push('<td class="">'+new Date(content.viewDate).format("yyyy-MM-dd hh:mm:ss")+":"+content.disabled+":"+content.marketable+'</td>');
		html.push('<td align="left" class="member-fav" style="vertical-align:middle">');
		if(content.disabled==0&&content.marketable==1){
	  		html.push('<a rel="nofollow" title="加入购物车" class="btn-bj-hover operate-btn" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+content.goodsId+'" target="_blank">加入购物车</a>');
	  	}else if(content.disabled!=0){
	  		html.push('<h5 style="color:red;">该商品已失效</h5>');
	  	}else if(content.marketable!=1){
	  		html.push('<h5 style="color:red;">该商品已下架</h5>');
	  	}
		html.push('<a class="operate-btn-del operate-btn" onclick="deleteProductViewHistory('+content.goodsId+');" href="javascript:void(0);">删除</a>');
		html.push('</td>');
		html.push('</tr>');
	});
	$("#product_history_list").append(html.join(""));	
}


function buildStoreViewHistoryHTML(contents){
	var productViewHistoryHTML='';
	$.each(contents.rows,function (x,content){
		var time=new Date();
		time.setTime(content.viewDate);
		if(content.goodsId){
			productUrl=yoyo.portalUrl+"/goodsManager/goodsIndex?goodsId="+content.goodsId;
		}
		productViewHistoryHTML+=		
				'<tr>'+
					'<td class=""><input type="checkbox" value='+content.id+' class="check"></td>'+
					'<td class=""><a target="_balnk" href="'+yoyo.portalUrl+'/shop/index?companyId='+content.companyId+'"><img width="145" height="70" src="'+yoyo.imagesUrl+content.logo+'"/></a></td>'+
					'<td class=""><a target="_balnk" href="'+yoyo.portalUrl+'/shop/index?companyId='+content.companyId+'">'+content.stroeName+'</a></td>'+
					'<td class="">'+new Date(content.viewDate).format("yyyy-MM-dd hh:mm:ss")+'</td>'+
					'<td align="left" class="member-fav" style="vertical-align:middle">'+
						'<a class="operate-btn-del operate-btn" onclick="deleteStoreViewHistory('+content.id+');" href="javascript:void(0);">删除</a>'+
					'</td>'+
				'</tr>';
	});
	$("#sotre_history_list").append(productViewHistoryHTML);	
}

function isNull(val){
	if(val){
		return val;
	}
	return "";
}
