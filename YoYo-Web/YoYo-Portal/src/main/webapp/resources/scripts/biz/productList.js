$(document).ready(function(){
	var st=$("#st").val();
	if(st && st=="2"){
		$(".select_showbox").html('搜店铺');
		$("#searchSelect").val(2);
	}else if(st && st=="3"){
		$(".select_showbox").html('搜优惠券');
		$("#searchSelect").val(3);
	}else{
		$(".select_showbox").html('搜商品');
		$("#searchSelect").val(1);
	}
	
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
	if($("#queryKey").val()!='搜索关键字...'){
		$("#searchKey").attr("style","color:#000");
		$("#itemKey").html($("#queryKey").val());
		$("#searchResult").html($("#queryKey").val());
	}
	
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
	productList.initAd();
});
var productList={
		initAd:function(){
			$.ajax({
				url:yoyo.portalUrl+'/ad/getAdByName',
				data:{name:'productListAd'}
			}).done(function(data){
				if(data.retCode == yoyo.successCode){
					debugger
					$('#ad').empty();
					var adArr = data.content;
					for ( var i = 0; i < adArr.length; i++) {
						var ad = adArr[i];
						var config = JSON.parse(ad.adConfig);
						var html ='';
						for(var j=0; j< config.content.length;j++){
							html += '<a href="'+ config.content[j].destUrl+'"><img src="'+yoyo.imagesUrl+ config.content[j].picUrl+'" alt=""></a>';
						}
						$('#ad').append(html);
					}
				}
			});
		},
	initCategory:function(){
		var html="";
		$.ajax({
		    url:yoyo.portalUrl+'/cate/getVirtualCateTree',   
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	var cateId=$("#cateId").val();
		    	for(var i=1,l=data.length;i<l;i++){
		    		var classLi='class="sea_current" ';
		    		if(i>2){
		    			classLi=' class="all_s hide" ';
		    		}
		    		var catName=data[i].catName;
		    		if(catName.length>10){
		    			catName=catName.substring(0,10) + "...";
		    		}
		    		if(cateId==data[i].catId){
		    			catName="<span style='color: #f60;' >"+catName+"</span>";
    				}
		    		if(i==data.length-1 ){//快速发布需求
	    				if(accountId == 0){
		    				html+=" <li "+classLi+"><a href='javascript:;' class='myTitle'></a><span class='myTitle_name'><a href="+yoyo.portalUrl+"/register/login >"+catName+"</a></span>";
//	    					html+=' <li '+classLi+'><a href="javascript:;" class="myTitle"></a><span class="myTitle_name"><a href="" class="myTitle" title="'+data[i].catName+'">'+catName+'</a></span>';
	    				}else{
		    				html+=" <li "+classLi+"><a href='javascript:;' class='myTitle'></a><span class='myTitle_name'><a href="+yoyo.portalUrl+"/requirement/index >"+catName+"</a></span>";
//	    					html+=' <li '+classLi+'><a href="javascript:;" class="myTitle"></a><span class="myTitle_name"><a href="'+yoyo.portalUrl+'/requirement/index">'+catName+'</a></span>';
	    				}
	    			}else if(i==data.length-2){
	    				html+=' <li '+classLi+'><a href="javascript:;" class="myTitle"></a><span class="myTitle_name"><a href="'+yoyo.portalUrl+'/painting/index">'+catName+'</a></span>';
	    			}else{
	    				html+=" <li "+classLi+"><a href='javascript:;' class='myTitle'></a><span class='myTitle_name'><a href=javascript:productList.searchCate("+data[i].catId+",'"+data[i].catName+"');>"+catName+"</a></span>";
	    			}
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
			    			 html+=' <li class="sea_more" licount='+j+'>查看更多<i></i></li> ';
			    		}
		    			html+='<li ';
		    			if(j>2){
		    				html+=' class="s_more hide" ';
		    			}
		    		  html+="><a href=javascript:productList.searchCate("+data[i].childCateList[j].catId+",'"+data[i].childCateList[j].catName+"'); title="+data[i].childCateList[j].catName+">"+cateName+"</a></li>";
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
			        $(this).hide();
			    });
				$(".sear_all").click(function(e) {
			        $(".all_s").toggleClass("hide");
			    });
				
		     }
		});
	},
	queryByBrandId : function(data) {
		var sbUrl=[];
		var st=$("#st").val();
		if(st && st=="2"){
			sbUrl.push(yoyo.portalUrl+"/searchShop");
		}else if(st && st=="3"){
			sbUrl.push(yoyo.portalUrl+"/searchCoupons");
		}else{
			sbUrl.push(yoyo.portalUrl+"/search");
		}
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
		if(data.cname && data.cname!="null"){
			sbUrl.push("&cname="+data.cname);
		}
		if(data.attrId && data.attrId!="null"){//扩展属性
			sbUrl.push("&aId="+data.attrId);
		}
		if(data.attrValue && data.attrValue!="null"){
			sbUrl.push("&atv="+data.attrValue);
		}
		if(data.atV && data.atV!="null"){
			sbUrl.push("&atV="+data.atV);
		}
		if(data.atN && data.atN!="null"){
			sbUrl.push("&atN="+data.atN);
		}
		if(data.store && data.store!="null"){
			sbUrl.push("&store="+data.store);
		}
		if(data.goodsKind && data.goodsKind!="null"){
			sbUrl.push("&goodsKind="+data.goodsKind);
		}
		if(data.brandId && data.brandId!="null"){
			sbUrl.push("&brandId="+data.brandId);
		}
		if(data.bname && data.bname!="null"){
			sbUrl.push("&bname="+data.bname);
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
			brandId:$("#brandId").val(),
			bname:$("#bname").val(),
			cateId:$("#cateId").val(),
			cname:$("#cname").val(),
			cId:$("#cId").val(),
			store:$("#store").val(),
			goodsKind:$("#goodsKind").val(),
			attrId:$("#aId").val(),
		    attrValue:$("#atv").val(),
		    atV:$("#atV").val(),
			atN:$("#atN").val(),
		    currentPage:parseInt($("#pageIndex").val())-1
		};
		productList.queryByBrandId(data);
	},
	nextPage:function(){
		var data={
			searchKey:$("#searchKey").val(),
			orderFile:$("#orderFile").val(),
			sq:$("#sq").val(),
			brandId:$("#brandId").val(),
			bname:$("#bname").val(),
			cateId:$("#cateId").val(),
			cname:$("#cname").val(),
			cId:$("#cId").val(),
			store:$("#store").val(),
			goodsKind:$("#goodsKind").val(),
			attrId:$("#aId").val(),
		    attrValue:$("#atv").val(),
		    atV:$("#atV").val(),
			atN:$("#atN").val(),
		    currentPage:parseInt($("#pageIndex").val())+1
		};
		productList.queryByBrandId(data);
	},
	sort:function(orderFile){
		var data={
			searchKey:$("#searchKey").val(),
		    orderFile:orderFile,
		    sq:$("#sq").val(),
		    brandId:$("#brandId").val(),
		    bname:$("#bname").val(),
		    cateId:$("#cateId").val(),
		    cname:$("#cname").val(),
		    cId:$("#cId").val(),
		    store:$("#store").val(),
		    goodsKind:$("#goodsKind").val(),
		    attrId:$("#aId").val(),
		    attrValue:$("#atv").val(),
		    atV:$("#atV").val(),
			atN:$("#atN").val(),
		    currentPage:1
		};
		productList.queryByBrandId(data);
	},
	searchCate:function(cateId,cname){
		var data={
				searchKey:$("#searchKey").val(),
			    cateId:cateId,
			    cname:cname,
			    cId:$("#cId").val(),
			    currentPage:1
			};
			productList.queryByBrandId(data);
	},
	searchFilter:function(attrName,attrValue,attrId,attrValueId){
		var aId=$("#aId").val();
		var atv=$("#atv").val();
		var atN=$("#atN").val();
		var atV=$("#atV").val();
		var aIds = new Array();
		var atvs = new Array();
		var attrValues = new Array();
		var attrNames = new Array();
		if(aId){
			aIds=aId.split(",");
			atvs=atv.split(",");
			attrValues=atV.split(",");
			attrNames=atN.split(",");
			var bolleanTrue=false;
			for(var i=0;i<aIds.length;i++){
				if(aIds[i]==attrId){//已经存在属性
					bolleanTrue=true;
					atvs[i]=attrValueId;
					attrValues[i]=attrValue;
				}
			}
			if(!bolleanTrue){
				aIds.push(attrId);
				atvs.push(attrValueId);
				attrValues.push(attrValue);
				attrNames.push(attrName);
			}
		}else{
			aIds.push(attrId);
			atvs.push(attrValueId);
			attrValues.push(attrValue);
			attrNames.push(attrName);
		}
		var data={
			searchKey:$("#searchKey").val(),
		    cateId:$("#cateId").val(),
		    cname:$("#cname").val(),
		    cId:$("#cId").val(),
		    brandId:$("#brandId").val(),
		    bname:$("#bname").val(),
		    attrId:aIds.join(","),
		    attrValue:atvs.join(","),
		    atV:attrValues.join(","),
			atN:attrNames.join(","),
		    goodsKind:$("#goodsKind").val(),
		    currentPage:1
		};
		productList.queryByBrandId(data);
	},
	searchByBId:function(brandId,bname){
		var data={
			searchKey:$("#searchKey").val(),
		    cateId:$("#cateId").val(),
		    cname:$("#cname").val(),
		    brandId:brandId,
		    bname:bname,
		    attrId:$("#aId").val(),
		    attrValue:$("#atv").val(),
		    atV:$("#atV").val(),
			atN:$("#atN").val(),
		    cId:$("#cId").val(),
		    currentPage:1
		};
		productList.queryByBrandId(data);
	},
	deleteBrand:function(){
		var data={
			searchKey:$("#searchKey").val(),
		    sq:$("#sq").val(),
		    cateId:$("#cateId").val(),
		    cId:$("#cId").val(),
		    store:$("#store").val(),
		    attrId:$("#aId").val(),
		    attrValue:$("#atv").val(),
		    atV:$("#atV").val(),
			atN:$("#atN").val(),
		    goodsKind:$("#goodsKind").val(),
		    currentPage:1
		};
		productList.queryByBrandId(data);
	},
	deleteAttr:function(attrValueId){
		var aId=$("#aId").val();
		var atv=$("#atv").val();
		var atN=$("#atN").val();
		var atV=$("#atV").val();
		var aIds = new Array();
		var atvs = new Array();
		var attrValues = new Array();
		var attrNames = new Array();
		if(aId){
			aIds=aId.split(",");
			atvs=atv.split(",");
			attrValues=atV.split(",");
			attrNames=atN.split(",");
			for(var i=0;i<atvs.length;i++){
				if(atvs[i]==attrValueId){
					aIds.splice(i,1);
					atvs.splice(i,1);
					attrValues.splice(i,1);
					attrNames.splice(i,1);
				}
			}
			var data={
				searchKey:$("#searchKey").val(),
			    sq:$("#sq").val(),
			    cateId:$("#cateId").val(),
			    cId:$("#cId").val(),
			    brandId:$("#brandId").val(),
			    bname:$("#bname").val(),
			    store:$("#store").val(),
			    attrId:aIds.join(","),
			    attrValue:atvs.join(","),
			    atV:attrValues.join(","),
				atN:attrNames.join(","),
			    goodsKind:$("#goodsKind").val(),
			    currentPage:1
			};
			productList.queryByBrandId(data);
		}
	},
	deleteCateId:function(){
		var data={
			searchKey:$("#searchKey").val(),
			brandId:$("#brandId").val(),
		    bname:$("#bname").val(),
		    attrId:$("#aId").val(),
		    attrValue:$("#atv").val(),
		    atV:$("#atV").val(),
			atN:$("#atN").val(),
		    cId:$("#cId").val(),
		    currentPage:1
		 };
		productList.queryByBrandId(data);
	},
	deleAll:function(){
		var data={
			searchKey:$("#searchKey").val(),
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
	},
	focuseShop :function(companyId){
		$.post(yoyo.portalUrl+"/shop/focusStore",{companyId:companyId},function (result){
			if(result.retCode=="000000"){
				layer.alert("关注店铺成功!",{title:false,closeBtn:false,icon:1});
			}else if(result.retCode=="NL001"){
				layer.alert("请登录后再关注店铺!",{title:false,closeBtn:false,icon:0,end:function(){
					window.location.href=yoyo.portalUrl+'/register/login';
				}});
			}else if(result.retCode=="000002"){
				if(result.content){
					layer.msg(result.content.msg,{time:2000});	
				}else{
					layer.msg("关注店铺失败",{time:2000});
				}
			}
		});
	},
	focuseCoupon :function(cpnsId){
		$.post(yoyo.portalUrl+"/saveMemberCoupon",{cpnsId:cpnsId},function (result){
			if(result.retCode=="000000"){
				layer.alert("领取优惠券成功!",{title:false,closeBtn:false,icon:1});
			}else if(result.retCode=="NL001"){
				layer.alert("请登录后再领取优惠券!",{title:false,closeBtn:false,icon:0,end:function(){
					window.location.href=yoyo.portalUrl+'/register/login';
				}});
			}else if(result.retCode=="000002"){
				if(result.content){
					layer.msg(result.content.msg,{time:2000});	
				}else{
					layer.msg("领取优惠券失败",{time:2000});
				}
			}else if(result.retCode=="000009"){
				layer.msg("领用数量已到最大限制数量，不能领用。",{time:2000});	
			}
		});
	}
	
};