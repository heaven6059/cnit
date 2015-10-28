$(function() {
	var intervalTime = 2000;
	var carouselEnabled = 1;//自动轮播
	//初始化轮播广告设置
	try{
		if(window.adMap!=null){
			var homeCarousel = adMap['homeCarousel'];
			var carouselConfig = JSON.parse(homeCarousel.adConfig);
			intervalTime = carouselConfig.intervalTime;
			var li = $('.banner_t ul li');
			var content = carouselConfig.content;
			for(var i=0;i<content.length;i++){
				li.eq(i).find("a").attr("href",content[i].destUrl);
				li.eq(i).find("img").attr("src",basePath+content[i].picUrl);
			}
			carouselEnabled = homeCarousel.enabled;
		}
	}catch(e){
		console.error("广告数据设置代码报错");
	}
	//
	var timer = null;
	var num = 0;
	var smallNum = 0; // banner小广告滚屏
	var fnTimer = function() {
		num++;
		if (num == 10) {
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
    if(1==carouselEnabled){
    	timer = setInterval(fnTimer, intervalTime);
    	$('.banner_t').mouseover(function(e) {
    		clearInterval(timer);
    	}).mouseout(function(e) {
    		clearInterval(timer);
    		timer = setInterval(fnTimer, intervalTime);
    	});
    }


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
		debugger
		if(accountId == 0){
			window.location.href = loginUrl;
		}else{
			window.location.href = requireUrl;
		}
	});
	console.log('accountId:' + accountId + ',accountType:'+accountType);
});
var accountId,accountType;
var loginUrl = yoyo.portalUrl+'/register/login';
var requireUrl = yoyo.portalUrl+'/requirement/index';

// YOYO快讯文字滚动
var rightNum = 0;
var yycDivHeight = 98;
var yycUlHeight;
var yycInterval;
var yycTop = 0;
function yycGundong() {
	rightNum++
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
					}
					cateListHtml+="<li class='li0"+li+" "+liClass+"'><a class='li_a' href='"+ahref+"'><span></span><div class='sidebar_li_div'><h3>"+data[i].catName+"</h3><i>"+data[i].title+"</i>"+bHtml+"</div> </a>";
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
							onclickUrl=yoyo.portalUrl+"/search?cateId="+catId;
						}
						var image=data[i].childCateList[j].icon;
						if(!image){
							image=yoyo.portalUrl+"/resources/images/ff.gif";
						}else{
							image=yoyo.imagesUrl+data[i].childCateList[j].icon;
						}
						cateListHtml+=" ><a href='"+onclickUrl+"'><div><img src='"+image+"' width='65' height='65'></div><p>"+cateName+"</p></a></li>";
		    		}
						cateListHtml+="</ul></div></li>";
					}
				}
				cateListHtml+="<li class='li06'><a class='li_a' href='javascript:;'><img src='"+yoyo.portalUrl+"/resources/images/index/yiy.jpg' width='198' height='66'></a></li>";
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
						html+='<li class="bor0"><a href="'+searchUrl+data.content[i].brandId+'"><div><img src="'+yoyo.imagesUrl+data.content[i].logo+'" width="56" height="56"></div><p>'+data.content[i].brandName+'</p></a></li>';
					}else{
						html+='<li><a href="'+searchUrl+data.content[i].brandId+'"><div><img src="'+yoyo.imagesUrl+data.content[i].logo+'" width="56" height="56"></div><p>'+data.content[i].brandName+'</p></a></li>';
					}
				}
				$("#brandList").html(html);
				if(data.content.length>15){
					$("#brandList").after('<a href="'+yoyo.portalUrl+'/search?q=" class="more_brand">更多品牌</a>');
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
		window.location.href=yoyo.portalUrl+"/carMaintain/step2?carId="+mycarVal[0]+"&maintainKm="+$("#curmileage").val()+"&year="+$("#year").val()+"&month="+$("#month").val();
	},
	buildYear:function(year){
		if(year){
			var option=["<option value='0'>年份</option>"];
			for(var i=year;i<=new Date().getFullYear();i++){
				option.push("<option value="+i+">"+i+"年</option>");
			}
			$("#year").html(option.join(""));
			index.buildMonth(year);
		}
	},
	buildMonth:function(year){
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
	},readCookie:function (cookieName){
		var cookieV=$.cookie(cookieName);
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7, path: "/"});
	},
};