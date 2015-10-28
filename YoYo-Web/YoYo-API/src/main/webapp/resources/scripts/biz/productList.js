$(document).ready(function(){
	if($("#store").val() && $("#store").val()=='1'){
		$("#showStore").attr({style:"background-position: -70px -14px; "});
	}
	 
	productList.initCategory();
	
	
	//商品对比
	$(".btn-compare span").click(function(e) {
        $(this).toggleClass("comCur");
		$(".pop_compare").stop().animate({"bottom":"0"},300);
    });
	
	$(".hide_me").click(function(e) {
		$(".pop_compare").stop().animate({"bottom":"-139px"},300);
    });
	
	
	//鼠标经过出现红色边框
	$(".btns>a").mouseover(function(e) {
        $(this).addClass("ahover").siblings("a").removeClass("ahover");
    }).mouseout(function(e) {
        $(this).removeClass("ahover");
    });
	
	//对比栏
	$(".hasItem").mouseover(function(e) {
		$(this).find(".del_comp_item").css("visibility","visible");
	}).mouseout(function(e) {
		$(this).find(".del_comp_item").css("visibility","hidden");
    });
	
	$("#searchKey").val($("#queryKey").val());
	$("#itemKey").html($("#queryKey").val());
	$("#searchResult").html($("#queryKey").val());
	
	// 创建分页元素
	$("#Pagination").yoyoPagination($("#total").val(), {
		items_per_page:$("#rows").val(),
		page_num_id:"page",
		page_num_name:"page",
		current_page:$("#pageIndex").val()-1,
		callback:function (){
			var data={
				searchKey:$("#searchKey").val(),
				orderFile:$("#orderFile").val(),
				sq:$("#sq").val(),
				cateId:$("#cateId").val(),
				cId:$("#cId").val(),
				store:$("#store").val(),
			    currentPage:$("#page").val()
			};
			productList.queryByBrandId(data);
		}
	});
	$('#sq').bind('keypress',function(event){
	     if(event.keyCode == "13")    
	     {
	    	 productList.sort('');
	     }
	});
	$("#confirm").click(function(){
		productList.sort('');
	});
	$("#showStore").click(function(){
		if($(this).attr("style")){
			$("#store").val('0');
			productList.sort('store');
			$(this).attr({style:""});
		}else{
		  $("#store").val('1');
		   productList.sort('store');
		   $(this).attr({style:"background-position: -70px -14px; "});
		}
	});
});
var productList={
	initCategory:function(){
		var html="";
		$.ajax({
		    url:yoyo.portalUrl+'/cate/getVirtualCateTree',   
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	var cateId=$("#cateId").val();
		    	for(var i=0,l=data.length;i<l;i++){
		    		var classLi='class="sea_current" ';
		    		if(i>2){
		    			classLi=' class="all_s hide" ';
		    		}
		    		var catName=data[i].catName;
		    		if(catName.length>10){
		    			catName=catName.substring(0,10) + "...";
		    		}
		    		html+=' <li '+classLi+'><a href="javascript:;" class="myTitle" title="'+data[i].catName+'">'+catName+'</a>';
		    		if(data[i].childCateList && data[i].childCateList.length>0){
		    			html+='<ul>';
		    		}
	    			for(var j=0;j<data[i].childCateList.length;j++){
	    				var cateName="";
	    				var subCatName=data[i].childCateList[j].catName;
			    		if(subCatName.length>10){
			    			subCatName=subCatName.substring(0,10) + "...";
			    		}
	    				if(cateId==data[i].childCateList[j].catId){
	    					cateName="<span style='color: #f60;' >"+subCatName+"</span>";
	    				}else{
	    					cateName=subCatName;
	    				}
	    				if(j==3){
			    			 html+=' <li class="sea_more">查看更多<i></i></li> ';
			    		}
		    			html+='<li ';
		    			if(j>2){
		    				html+=' class="s_more hide" ';
		    			}
		    			html+='><a href="javascript:productList.searchCate('+data[i].childCateList[j].catId+');" title="'+data[i].childCateList[j].catName+'">'+cateName+'</a></li>';
		    		}
	    			
	    			if(data[i].childCateList && data[i].childCateList.length>0){
		    			html+=' </ul>';
		    		}
		    		html+="</li>";
		    	}
				$("#searchCategory").html(html);
				//侧边栏切换
				$(".myTitle").click(function(e) {
			        $(this).parent().toggleClass("sea_current");
			    });	
				$(".sea_more").click(function(e) {
			        $(this).siblings(".s_more").toggleClass("hide");
			    });
				$(".sear_all").click(function(e) {
			        $(".all_s").toggleClass("hide");
			    });
				
		     }
		});
	},
	queryByBrandId : function(data) {
		var sbUrl=[];
		sbUrl.push(yoyo.portalUrl+"/search");
		if($.trim(data.searchKey) && $.trim(data.searchKey)!="null"){
			sbUrl.push("&q="+$.trim(data.searchKey));
		}
		if(data.currentPage){
			sbUrl.push("&page="+data.currentPage);
		}
		if(data.orderFile && data.orderFile!="null" ){
			sbUrl.push("&orderFile="+data.orderFile);
		}
		if($.trim(data.sq) && $.trim(data.sq)!="null" ){
			sbUrl.push("&sq="+$.trim(data.sq));
		}
		if(data.cateId && data.cateId!="null"){
			sbUrl.push("&cateId="+data.cateId);
		}
		if(data.cId && data.cId!="null"){
			sbUrl.push("&cId="+data.cId);
		}
		if(data.attrId && data.attrId!="null"){//扩展属性
			sbUrl.push("&aId="+data.attrId);
		}
		if(data.attrValue && data.attrValue!="null"){
			sbUrl.push("&atv="+data.attrValue);
		}
		if(data.store && data.store!="null"){
			sbUrl.push("&store="+data.store);
		}
		if(data.brandId && data.brandId!="null"){
			sbUrl.push("&brandId="+data.brandId);
		}
		var url=sbUrl.join("");
		if(url.indexOf("?")==-1){
			url=url.replace("&","?");
		}
	    window.location.href=url;
	},
	prePage :function(){
		var data={
			searchKey:$("#searchKey").val(),
			orderFile:$("#orderFile").val(),
			sq:$("#sq").val(),
			cateId:$("#cateId").val(),
			cId:$("#cId").val(),
			store:$("#store").val(),
		    currentPage:parseInt($("#pageIndex").val())-1
		};
		productList.queryByBrandId(data);
	},
	nextPage:function(){
		var data={
			searchKey:$("#searchKey").val(),
			orderFile:$("#orderFile").val(),
			sq:$("#sq").val(),
			cateId:$("#cateId").val(),
			cId:$("#cId").val(),
			store:$("#store").val(),
		    currentPage:parseInt($("#pageIndex").val())+1
		};
		productList.queryByBrandId(data);
	},
	sort:function(orderFile){
		var data={
			searchKey:$("#searchKey").val(),
		    orderFile:orderFile,
		    sq:$("#sq").val(),
		    cateId:$("#cateId").val(),
		    cId:$("#cId").val(),
		    store:$("#store").val(),
		    currentPage:1
		};
		productList.queryByBrandId(data);
	},
	searchCate:function(cateId){
		var data={
				searchKey:$("#searchKey").val(),
			    cateId:cateId,
			    cId:$("#cId").val(),
			    currentPage:1
			};
			productList.queryByBrandId(data);
	},
	searchFilter:function(attrId,attrValue){
		var data={
				searchKey:$("#searchKey").val(),
			    cateId:$("#cateId").val(),
			    cId:$("#cId").val(),
			    attrId:attrId,
			    attrValue:attrValue,
			    currentPage:1
			};
			productList.queryByBrandId(data);
	},
	searchByBId:function(brandId){
		var data={
				searchKey:$("#searchKey").val(),
			    cateId:$("#cateId").val(),
			    brandId:brandId,
			    cId:$("#cId").val(),
			    currentPage:1
			};
			productList.queryByBrandId(data);
	},
	addWishList:function(goodsId){
		var loginUrl=yoyo.portalUrl+"/register/login";
		var url = yoyo.memUrl+'/productWishList/addWishList';
		var params = {};
		params.goodsId = goodsId;
		commonAjax(url, params, function(data) {
			if(data.content!=null&&data.content.result!=null){
				if(data.content.result==false){
					if(data.content.isLogin!=null&&data.content.isLogin==false){
						window.location.href = loginUrl;
					}else{
						alert(data.content.msg);
					}
				}
			}else if(data.head.retCode == '000000'){
				alert("关注成功");
			}
		}, function(data) {
			if(data.head==null){
				window.location.href = loginUrl;
			}
		})
	},
	addCart:function(goodsId){
		var  findProductUrl=yoyo.portalUrl+'/goodsManager/getProductByGoodsId';
		var goodIds = {};
		goodIds.goodsId=goodsId;
		var productId="";
		$.ajax({
			type : 'POST',
			url : findProductUrl,
			data : goodIds,
			cache : false,
			success : function(data1){
				productId=data1;
				if(productId!=null&&productId!=''){
					var url = yoyo.portalUrl+'/cart/addCart';
					var params = {};
					params.quantity=1;
					params.productId = productId;
					$.ajax({
						type : 'POST',
						url : url,
						data : params,
						cache : false,
						success : function(data){
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
						}
					});
				}else{
					alert("该货品不存在，请重新选择商品规格");
				}
			}
		});
	}
	
};