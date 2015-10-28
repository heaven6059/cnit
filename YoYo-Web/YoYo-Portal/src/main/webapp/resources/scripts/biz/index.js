$(function() {
	//初始化轮播广告设置
	$.ajax({
		url:_path+'/ad/getAdByName',
		type:'GET',
		data:{name:'homeCarousel'},
		dataType:'JSON',
		error:function(XMLHttpRequest, textStatus, errorThrown){
			
		},
	}).done(function(data, textStatus, jqXHR){
		var opts = null;
		if(data.head.retCode == '000000'){
			opts = initCarouselData(data.content);//初始化轮播区数据
		}
		initCarousel(opts);//初始化轮播区
	});
	
	//2楼层广告
	$.ajax({
		url:_path+'/ad/getAdByName',
		type:'GET',
		data:{name:'floor2_'},
		dataType:'JSON',
	}).done(function(data, textStatus, jqXHR){
		if(data.head.retCode == '000000'){
			initFloorAd(data.content,2);//初始化轮播区数据
		}
	});
	
	//3楼层广告
	$.ajax({
		url:_path+'/ad/getAdByName',
		type:'GET',
		data:{name:'floor3_'},
		dataType:'JSON',
	}).done(function(data, textStatus, jqXHR){
		if(data.head.retCode == '000000'){
			initFloorAd(data.content,3);//初始化轮播区数据
		}
	});
	
	// 快捷导航返回顶部
	$('.mui ul .last').click(function(e) {
		// alert(1);
		$('html,body').animate({
			'scrollTop' : '0px'
		}, 0);
	});

	index.initLeftHS();

	// 点击banner下广告切换
	$('.right_btn').click(function(e) {
		smallNum++;
		if (smallNum == 3) {
			smallNum = 0;
		}
		var numZhi = smallNum * -692;
		$('.b_ad_ul').stop().animate({
			'left' : '' + numZhi + 'px'
		}, 300);
	});

	$('.left_btn').click(function(e) {
		smallNum--;
		if (smallNum == -1) {
			smallNum = 2;
		}
		var numZhi = smallNum * -692;
		$('.b_ad_ul').stop().animate({
			'left' : '' + numZhi + 'px'
		}, 300);
	});
	
	//快速发布需求跳转
	$("body").on("click",".sidebar_li_div:last", function(){
		if(accountId == 0){
			window.location.href = loginUrl;
		}else{
			window.location.href = requireUrl;
		}
	});
});

/**
 * 初始化楼层广告
 * @param ads
 * @param floorNum
 */
function initFloorAd(ads,floorNum){
	if(ads.length != 0){
		if(2 == floorNum){
			$('.f_2_l').empty();
			for(var i=0; i<ads.length; i++){
				var content = JSON.parse(ads[i].adConfig).content;
				var size = ads[i].picSize;
				if(size){
					var arr = size.split(',');
					
					$('.f_2_l').prepend('<a href="'+content[0].destUrl+'" targer="_blank"><img src="'+yoyo.imagesUrl+content[0].picUrl+'" width="'+arr[0]+'" height="'+arr[1]+'"/></a>');
				}
			}
		}else if(3 == floorNum){
			$('.f_3_in .fl').eq(0).empty();
			var content = JSON.parse(ads[0].adConfig).content;
			var size = ads[0].picSize;
			
			if(size){
				var arr = size.split(',');
				$('.f_3_in .fl').eq(0).prepend('<a href="'+content[0].destUrl+'" targer="_blank"><img src="'+yoyo.imagesUrl+content[0].picUrl+'" width="'+arr[0]+'" height="'+arr[1]+'"/></a>');
			}
		}
	}
}

function initCarouselData(ad){
	var opts = {
		intervalTime:2000,
		carouselEnabled:1
	};
	try{
		if(ad!=null){
			var homeCarousel = ad;
			var carouselConfig = JSON.parse(homeCarousel[0].adConfig);
			opts.intervalTime = carouselConfig.intervalTime;
			var li = $('.banner_t ul li');
			var content = carouselConfig.content;
			for(var i=0;i<content.length;i++){
				li.eq(i).css("background-color",content[i].bgColor).css("text-align","center");
				li.eq(i).find("a").attr("href",content[i].destUrl);
				li.eq(i).find("img").attr("src",yoyo.imagesUrl+content[i].picUrl).css("width",760).css("height",470);
			}
			opts.carouselEnabled = homeCarousel.enabled;
		}
	}catch(e){
		console.error("广告数据设置代码报错");
	}
	return opts;
};

function initCarousel(opts){
	var _opts = {
		intervalTime:2000,
		carouselEnabled:1
	};
	$.extend(_opts,opts);
	var timer = null;
	var num = 0;
	var smallNum = 0; // banner小广告滚屏
	var fnTimer = function() {
		num++;
		if (num == 4) {
			num = 0;
		}
		// banner轮播图
		$('.banner_t ol li').eq(num).addClass('current').siblings()
				.removeClass('current');
		$('.banner_t ul li').eq(num).stop().fadeIn().siblings().hide();
	};
	// 第二种切换
	$('.banner_t ol li').mouseover(
			function(e) {
				$(this).addClass('current').siblings().removeClass('current');
				$('.banner_t ul li').eq($(this).index()).stop().fadeIn()
						.siblings().hide();
				num = $(this).index();
	});
	
	// 自动切换
    if(1==_opts.carouselEnabled){
    	timer = setInterval(fnTimer, _opts.intervalTime);
    	$('.banner_t').mouseover(function(e) {
    		clearInterval(timer);
    	}).mouseout(function(e) {
    		clearInterval(timer);
    		timer = setInterval(fnTimer, _opts.intervalTime);
    	});
    }
};

var accountId,accountType;
var loginUrl = yoyo.portalUrl+'/register/login';
var requireUrl = yoyo.portalUrl+'/requirement/index';

// YOYO快讯文字滚动
var rightNum = 0;
var yycDivHeight = 21*4;
var yycUlHeight;
var yycInterval;
var yycTop = 0;
function yycGundong() {
	rightNum++;
	yycTop = rightNum * 21;
	if (yycUlHeight - yycTop < yycDivHeight) {
		$('.yoyo_con ul').css("top", 0);
		clearInterval(yycInterval);
		rightNum = 0;
		yycTop = 0;
		yycInterval = setInterval("yycGundong()", 2000);
	} else {
		$('.yoyo_con ul').stop().animate({
			'top' : '' + -yycTop + 'px'
		});
	}
}

// 超值热销文字滚动
$(function() {
	var num = 0;
	var timer = null;
	var fnTimer = function() {
		num++;
		if (num == 4) {
			num = 0;
		}
		var numZhi = num * -43;
		if (0 == num)
			$('.con_addr_li ul').css("top", numZhi);
		else
			$('.con_addr_li ul').stop().animate({
				'top' : '' + numZhi + 'px'
			});
	};
	// 自动切换， 定时切换
	
	timer = setInterval(fnTimer, 3000);
	$('.con_addr_li').hover(function(e) {
		clearInterval(timer);
	}, function() {
		clearInterval(timer);
		timer = setInterval(fnTimer, 3000);
	});
	// YOYO快讯文字滚动
	yycUlHeight = $(".yoyo_con ul").height();
	if (yycUlHeight > yycDivHeight) {
		yycInterval = setInterval("yycGundong()", 2000);
	}
	
});

$(document).ready(function(){
	index.initVirtualCate();//初始化左侧虚拟分类
	index.initCarMaintain();
});

var index={
	queryByBrandId : function(data) {
		var sbUrl=[];
		sbUrl.push(sys.path+"/saleOrder/sendOutOrder_loadItemList.shtml");
		if(data.cateId && data.cateId!="null"){
			sbUrl.push("&param.cateId="+data.cateId);
		}
		if(data.page){
			sbUrl.push("&pageIndex="+data.page);
		}
		if(data.brandId && data.brandId>0 && data.brandId!="null"){
			sbUrl.push("&param.brandId="+data.brandId);
		}
		if(data.carBrandId && data.carBrandId!="null" ){
			sbUrl.push("&param.carBrandId="+data.carBrandId);
		}
		if(data.itemFlag && data.itemFlag!="null" ){
			sbUrl.push("&param.itemFlag="+data.itemFlag);
		}
		var url=sbUrl.join("");
		if(url.indexOf("?")==-1){
			url=url.replace("&","?");
		}
		$("#loadItemList").load(url);
	},
	initVirtualCate : function(){
		var url=yoyo.portalUrl+'/cate/getVirtualCateTree';   
		$.ajax({
			url : url,
			type: 'post',
			dataType : 'json',
			success : function(data) {
				var cateListHtml="";
				var listSize=data.length;
				if(listSize>7){
					listSize=7;
				}
				for(var i=0;i<listSize;i++){
					var li=parseInt(i+1);
					var liClass="sid_li",bHtml="<b></b>",ahref="javascript:;";
					if(li==6 || li==7){
						bHtml="";
						liClass="";
						if(li==6){
							ahref=yoyo.portalUrl+"/painting/index";
						}
					}else if(li==1){
						ahref=yoyo.portalUrl+"/search?cId=89072";
					}else{
						ahref=yoyo.portalUrl+"/search?cateId="+data[i].catId+"&cname="+data[i].catName+"";
					}
					cateListHtml+="<li class='li0"+li+" "+liClass+"'><a target='_blank' class='li_a' href='"+ahref+"'><span></span><div class='sidebar_li_div'><h3>"+data[i].catName+"</h3><i>"+data[i].title+"</i>"+bHtml+"</div> </a>";
				 if(li!=7){
					cateListHtml+="<div class='sid_newcar sid_con'>";
					cateListHtml+="<ul ";
					if(li==1){
						cateListHtml+=" id='brandList' ";
					}
					cateListHtml+=">";
					for(var j=0;j<data[i].childCateList.length;j++){
	    				var cateName=data[i].childCateList[j].catName;
	    				var catId=data[i].childCateList[j].catId;
			    		if(cateName.length>10){
			    			cateName=cateName.substring(0,10) + "...";
			    		}
						cateListHtml+="<li";
						if(j>0 && (parseInt(parseInt(j+1)%5)==0)){
							cateListHtml+= " class='bor0' ";
						}
						var onclickUrl="";
						if(li==6){
							onclickUrl=yoyo.portalUrl+"/painting/index";
						}else{
							onclickUrl=yoyo.portalUrl+"/search?cateId="+catId+"&cname="+cateName;
						}
						var image=data[i].childCateList[j].icon;
						if(!image){
							image=yoyo.portalUrl+"/resources/images/ff.gif";
						}else{
							image=yoyo.imagesUrl+data[i].childCateList[j].icon;
						}
						cateListHtml+=" ><a href='"+onclickUrl+"' target='_blank'><div><img src='"+image+"' width='65' height='65' ></div><p>"+cateName+"</p></a></li>";
		    		}
						cateListHtml+="</ul></div></li>";
					}
				}
				cateListHtml+="<li class='li06'><a class='li_a' href='javascript:;' target='_blank'><img src='"+yoyo.portalUrl+"/resources/images/index/yiy.jpg' width='198' height='66'></a></li>";
				$("#virtualCateList").html(cateListHtml);
				index.initBrand();//初始化品牌
				index.initLeftHS();
			}
		}); 
	},
	initBrand : function(){
		var newCateId=$("#newCateId").val();
		var url=yoyo.mpUrl+'/brandTypeShip/findShip?identifier='+yoyo.car;
		var searchUrl=yoyo.portalUrl+'/search?brandId=';
		$.ajax({
			url : url,
			type: 'post',
			dataType : 'json',
			success : function(data) {
				var html="";
				var brandSize=data.content.length;
				if(brandSize>15){
					brandSize=15;
				}
				for(var i=0;i<brandSize;i++){
				if(i>0 && (parseInt(parseInt(i+1)%5)==0)){
						html+='<li class="bor0"><a href="'+searchUrl+data.content[i].brandId+'&bname='+data.content[i].brandName+'" target="_blank"><div><img src="'+yoyo.imagesUrl+data.content[i].logo+'" width="56" height="56"></div><p>'+data.content[i].brandName+'</p></a></li>';
					}else{
						html+='<li><a href="'+searchUrl+data.content[i].brandId+'&bname='+data.content[i].brandName+'" target="_blank"><div><img src="'+yoyo.imagesUrl+data.content[i].logo+'" width="56" height="56"></div><p>'+data.content[i].brandName+'</p></a></li>';
					}
				}
				$("#brandList").html(html);
				if(data.content.length>15){
					$("#brandList").after('<a href="'+yoyo.portalUrl+'/search?q=" class="more_brand" target="_blank">更多品牌</a>');
				}
			}
		}); 
	},
	initLeftHS :function(){
		// 侧导航显示隐藏
		$(".sid_li").mouseover(function(e) {
			$(this).addClass("border");
			$(this).children(".sid_con").show();
			$(this).children(".li_a").addClass("sidebar_a");
		}).mouseout(function(e) {
			$(this).removeClass("border");
			$(this).children(".sid_con").hide();
			$(this).children(".li_a").removeClass("sidebar_a");
		});
	},
	initCarMaintain:function(){
		$("#curmileage").keyup(function (){			
			if($(this).val().length==1){
				$(this).val($(this).val().replace(/[^1-9]/g,''));
			}else{
				$(this).val($(this).val().replace(/\D/g,''));
			}
		});
		var mycarCookieVal=index.readCookie("my_car");
		if(!mycarCookieVal){
			return;
		}
		var mycarVal = mycarCookieVal.split("|");
		$("#year").change(function (){
			index.buildMonth($(this).val());
		});
		if(mycarVal){
			index.buildYear(mycarVal[4]);
		}
	},
	qryMaintian:function (){
		var mycarCookieVal=index.readCookie("my_car");
		if(!mycarCookieVal){
			$("#addMyCar").trigger("click");		
			return;
		}
		if(!mycarCookieVal){
			layer.alert("请选择车型！",{title:false,closeBtn:false,icon:0});
			return;
		}
		var mycarVal = mycarCookieVal.split("|");
		if(!$("#curmileage").val()){
			layer.alert("请输入当前行驶里程！",{title:false,closeBtn:false,icon:0});
			return;
		}
		if($("#year").val()==0){
			layer.alert("请选择新车上路年份！",{title:false,closeBtn:false,icon:0});
			return;
		}
		if($("#month").val()==0){
			layer.alert("请选择新车上路月份！",{title:false,closeBtn:false,icon:0});
			return;
		}
		window.location.href=yoyo.portalUrl+"/carMaintain/step2?carId="+mycarVal[2]+"&maintainKm="+$("#curmileage").val()+"&year="+$("#year").val()+"&month="+$("#month").val();
	},
	buildYear:function(year){
		if(year){
			var option=["<option value='0'>年份</option>"];
			for(var i=year-2;i<=new Date().getFullYear();i++){
				option.push("<option value="+i+">"+i+"年</option>");
			}
			$("#year").html(option.join(""));
			index.buildMonth(year);
		}
	},
	buildMonth:function(year){
		console.log(parseInt($("#year").val()));
		if(parseInt($("#year").val())<=0)return;
		var date=new Date(),option=["<option value='0'>月份</option>"],curMonth=12;
		if(date.getFullYear()==year){
			curMonth=date.getMonth()+1;
		}
		if(year&&year>0){
			for(var i=1;i<=curMonth;i++){
				option.push("<option value="+i+">"+i+"月</option>");
			}
		}
		$("#month").html(option);
	},
	readCookie:function (cookieName){
		var cookieV=$.cookie(cookieName);
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7, path: "/"});
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
}