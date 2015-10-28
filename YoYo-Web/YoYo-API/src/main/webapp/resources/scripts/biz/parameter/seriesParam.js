var brandDefault='<dd><a data-key="0" href="javascript:void(0)">请选择品牌</a></dd>';
var seriesDefault='<dd><a data-key="0" href="javascript:void(0)">请选择车系</a></dd>';
var specDefault='<dd><a data-key="0" href="javascript:void(0)">请选择车型</a></dd>';
$(function (){
	$(".comparepop-btn").click(function (){
		if($(".comparepop-content").hasClass("fn-hide")){
			$(".comparepop-content ").removeClass("fn-hide");
			$(".comparepop").css({width:"280px"});
		}else{
			$(".comparepop-content ").addClass("fn-hide");
			$(".comparepop").css({width:"50px"});
		}
	});
	
	
	$('.select-selected').bind("click", function(event) {
		$(".select-option").hide();		
		$(this).siblings(".select-option").show();
		$("body").bind("click", hideinfo);
		event.stopPropagation();
	});
	
	//点击品牌
	$('#divBrand dl').on("click","dd", function(event) {
		//点击品牌
		$("#divSeries").removeClass("select-disabled");
		var opts={
			brandId:$(this).children().attr("data-key"),
		};
		loadSeries(opts);
		$(this).addClass("current").siblings().removeClass("current");
		$(this).parent().parent(".select-option").hide();	
		$(this).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));
	});
	
	//点击车系
	$('#divSeries').on("click","dd", function(event) {
		$("#divSpec").removeClass("select-disabled");
		var opts={
				deptId:$(this).children().attr("data-key"),
			};
		loadCar(opts);
		$(this).addClass("current").siblings().removeClass("current");
		$(this).parent().parent(".select-option").hide();	
		$(this).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));		
	});
	
	//点击车型
	$("#divSpec").on("click","dd", function(event) {
		$(this).addClass("current").siblings().removeClass("current");
		$(this).parent().parent(".select-option").hide();	
		
		$(this).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));
		if($(this).hasClass("current")){
			buildCarList($(this).children().attr("data-key"),$(this).text(),$("#divSeries").find("span").attr("data-key"),$("#divBrand").find("span").attr("data-key"));
		}
		$(".comparepop-select-btn").show();
	});
	
	$(".comparepop-list").on("click","i",function (){
		var curKey=$(this).parent().attr("data-key");
		var cookieV=utils.readCookie("car_infos");
		if(cookieV){
			compareList=JSON.parse(cookieV);
			$.each(compareList,function (x,item){
				if(item.key==curKey){
					compareList.splice([x],1);	
				}
			});
			utils.writeCookie("car_infos",JSON.stringify(compareList));
		}
		var cookieV=utils.readCookie("car_ids");
		var splitV=cookieV.split("|");
		for(var i=0;i<splitV.length;i++){
			var o=splitV[i].split(",");			
			if(o[0]==curKey){
				splitV.splice([i],1);				
			}			
		}
		utils.writeCookie("car_ids",splitV.join("|")); // 存储 cookie
		$(this).parent().remove();
	});
	
	$(".comparepop-select-btn").on("click",".btn",function (){
		var cookieV=utils.readCookie("car_ids");
		var carids=new Array();
		$.each(cookieV.split("|"),function (x,item){
			var itemObj=item.split(",");
			if(itemObj.length>0){
				carids.push(itemObj[0]);
			}
		});
		$("#car_id").val(carids.join(","));
		$("#sub").submit();
	});
	
	$(".comparepop-select-btn").on("click",".fn-right",function (){
		clearAll();
	});
	
	var cookieV=utils.readCookie("car_infos");
	if(cookieV){
		compareList=JSON.parse(cookieV);
		if(compareList.length>0){
			$(".comparepop-select-btn").show();
			$(".comparepop-content ").removeClass("fn-hide");
			$(".comparepop").css({width:"280px"});
		}
		$.each(compareList,function (x,item){			
			initCarList(item.key,item.title);
		});
	}
	loadBrand();
});
function clearAll(){
	$(".comparepop-list").html("");
	utils.writeCookie("car_infos", "");
	utils.writeCookie("car_ids", "");
}
function buildCarList(dataKey,dataTitle,series,brand){
	var opts=
		[
		 dataKey,
		 series,
		 brand
		];
	if(buildCarIds(opts)){
		initCarList(dataKey,dataTitle);
		if($(".comparepop-list").children().length==4){
			$(".comparepop-alert").removeClass("fn-hide");			
			$("#divBrand").addClass("select-disabled");
			$("#divSeries").addClass("select-disabled");
			$("#divSpec").addClass("select-disabled");
		}else{
			$("#divBrand").removeClass("select-disabled");
			$(".comparepop-alert").addClass("fn-hide");
		}
		var compareList=[]; 
		var carInfoObj={
			key:dataKey,
			title:dataTitle
		};
		var cookieV=utils.readCookie("car_infos");
		if(cookieV){
			compareList=JSON.parse(cookieV);
		}
		compareList.push(carInfoObj);
		utils.writeCookie("car_infos",JSON.stringify(compareList));
		
		$(".comparepop-select-btn").show();
		$(".comparepop-content ").removeClass("fn-hide");
		$(".comparepop").css({width:"280px"});
	}
}

function initCarList(dataKey,dataTitle){
	var carList=new Array();
	carList.push('<li data-key="'+dataKey+'">');
	carList.push('<a href="" target="_blank">'+dataTitle+'</a>');
	carList.push('<i class="icon16 icon16-close"></i>');
	carList.push('</li>');
	$(".comparepop-list").append(carList.join(""));
}

function buildCarIds(opts){
	var parameters=new Array(4);
	var isJoin=true;

	var cookieV=utils.readCookie("car_ids");
	if(cookieV){
		var splitV=cookieV.split("|");
		for(var i=0;i<splitV.length;i++){
			var o=splitV[i].split(",");			
			if(o[0]==opts[0]){
				alert("已选择该车型");
				isJoin=false;					
			}
			
			if(isJoin&&splitV[i].length==0&&opts.length>0){
				parameters[i]=opts.join(",");
				opts={};
			}else{
				parameters[i]=splitV[i];
			}
		}
		if(isJoin){
			utils.writeCookie("car_ids",parameters.join("|")); // 存储 cookie 
		}
	}else{
		parameters[0]=opts.join(",");
		utils.writeCookie("car_ids",parameters.join("|")); // 存储 cookie
	}
	return isJoin;
}

var utils={
	readCookie:function (cookieName){
		var cookieV="";
		if(cookieName){
			cookieV=$.cookie(cookieName);// 读取 cookie
		}else{
			cookieV=$.cookie('the_cookie');// 读取 cookie	
		}
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		if(cookieName){
			$.cookie(cookieName,cookieValue,{expires: 7}); // 存储 cookie
		}else{
			$.cookie('the_cookie',cookieValue,{expires: 7}); // 存储 cookie
		}
	}
};

//隐藏下拉框
function hideinfo() {
	$(".select-option").hide();
	$("body").unbind("click", hideinfo);
}
function loadBrand(callback){

	$.ajax({
	    url:'../carCompare/findCarBrand',
	    data:{identifier:yoyo.car},
	    type:'post',    
	    cache:false,    
	    dataType:'json',
	    async:false,
	    success:function(data){
	    	if(data.head.retCode==0000){
	    		$.each(data.content,function (x,brand){
	    			$("#divBrand").find("dl").append('<dd><a data-key="'+brand.brandId+'">'+brand.brandName+'</a></dd>');		
	    		});
	    	}
	    	if(callback){
	    		callback.call();
	    	}
	     }
	});
}



function loadSeries(opts){
	$.ajax({
	    url:'../carCompare/findCarDept',
	    data:{brandId:opts.brandId},
	    type:'post',   
	    cache:false,    
	    async:false,    
	    dataType:'json',    
	    success:function(data){
	    	if(data.head.retCode==0000){
	    		var seriesHTML="";
	    		$.each(data.content,function (x,factory){
	    			seriesHTML+='<dt>'+factory.factoryName+'</dt>';
	    			$.each(factory.carDepts,function (x,dept){
	    				seriesHTML+='<dd><a data-key="'+dept.carDeptId+'">'+dept.carDeptName+'</a></dd>';
	    			});	    			
	    		});
	    		$("#divSeries").find("dl").html(seriesHTML);	    		
	    	}	    	
	    	if(opts.callback){
	    		opts.callback();
	    	}
	    }
	});
}

function loadCar(opts){
	$.ajax({
	    url:'../carCompare/findCar',
	    data:{deptId:opts.deptId},
	    type:'post',   
	    cache:false,    
	    async:false,    
	    dataType:'json',    
	    success:function(data){
	    	if(data.head.retCode==0000){
	    		var seriesHTML="";
	    		$.each(data.content,function (x,car){
	    			seriesHTML+='<dd><a data-key="'+car.carId+'">'+car.carName+'</a></dd>';
	    		});	    		
	    		$("#divSpec dl").html(seriesHTML);		
	    	}
	    	if(opts.callback){
	    		opts.callback();
	    	}
	    }
	});
}
