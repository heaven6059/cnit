var brandDefault='<dd><a data-key="0" href="javascript:void(0)">请选择品牌</a></dd>';
var seriesDefault='<dd><a data-key="0" href="javascript:void(0)">请选择车系</a></dd>';
var specDefault='<dd><a data-key="0" href="javascript:void(0)">请选择车型</a></dd>';
$(function() {
	$("#navScrollLeft").css({left:$(".column").offset().left-$("#navScrollLeft").width()});
	loadBrand(function (){
		init();
	});
	
	$("#highlight").click(function (){
		if($(this).is(":checked")){
			compareData("highlight");
		}else{
			$(".js-titems tr").each(function(x,item){
				$(this).removeClass("highlight");
			});
		}
	});
	
	$("#need_hide").click(function (){
		if($(this).is(":checked")){
			compareData("hide");
		}else{
			$(".js-titems tr").each(function(x,item){
				$(this).show();
			});
		}
	});
	
	//点击品牌
	$('.js-brand dl').on("click","dd", function(event) {
		var opts={
				brandId:$(this).children().attr("data-key"),
				target:$(this).closest(".text-ul").find(".js-series dl")
		};
		loadSeries(opts);
		
		$(this).addClass("current").siblings().removeClass("current");
		$(this).parent().parent(".select-option").hide();	
		$(this).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));
		var target={
			nextTarget:$(this).closest("td").next(),
			curTarget:$(this).closest("td"),
			cloneBrand:true,
			clearSeries:true,
			clearSpec:true,
			text:$(this).text(),
			key:$(this).children().attr("data-key"),
			cloneSeries:true
		};
		refreshNextTarget(target);
		$(this).closest(".text-ul").find(".js-series span").html(seriesDefault);
		$(this).closest(".text-ul").find(".js-spec span").html(specDefault);
	
		
	});
	//点击车系
	$('.js-series dl').on("click","dd", function(event) {
		var opts={
				deptId:$(this).children().attr("data-key"),
				target:$(this).closest(".text-ul").find(".js-spec dl")
			};
		loadCar(opts);
		$(this).addClass("current").siblings().removeClass("current");
		$(this).parent().parent(".select-option").hide();	
		$(this).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));
		var target={
			nextTarget:$(this).closest("td").next(),
			curTarget:$(this).closest("td"),
			cloneBrand:false,
			cloneSeries:true,
			clearSpec:false,
			text:$(this).text(),
			key:$(this).children().attr("data-key")
		};
		refreshNextTarget(target);
	});
	
	//点击车型
	$('.js-spec dl').on("click","dd", function(event) {
		if(buildCarIds($(this))){
			$(this).addClass("current").siblings().removeClass("current");
			$(this).parent().parent(".select-option").hide();	
			
			$(this).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));
			realodParam();
		}
	});
	
	
	$('.select-selected').bind("click", function(event) {
		$(".select-option").hide();		
		$(this).siblings(".select-option").show();
		$("body").bind("click", hideinfo);
		event.stopPropagation();
	});
	
	$('.js-btn-delete').click(function (){
		
		var target=$(this).closest("td");
		var cookieV = utils.readCookie("car_ids");		
		if(cookieV){
			var splitV=cookieV.split("|");
			splitV.splice([target.index()-1],1);			
		utils.writeCookie("car_ids",splitV.join("|"));
		}
		
		cookieV=utils.readCookie("car_infos");
		var curKey=$(target).find(".js-spec span").attr("data-key");
		var compareList=[];
		if(cookieV){
			compareList=JSON.parse(cookieV);
			$.each(compareList,function (x,item){
				console.log(compareList);
				if(item.key==curKey){
					compareList.splice([x],1);
					return false;
				}
			});
		utils.writeCookie("car_infos",JSON.stringify(compareList));
		}
		
		realodParam();
	});
	
	$(".tablechoice").on("click",".js-btn-right",function (){
		
		var targetIndex=$(this).closest("td").index();		
		if(targetIndex==3){
			$(this).hide().siblings(".js-btn-left").show();
		}
		if(1<targetIndex&&targetIndex<3){
			$(this).show().siblings(".js-btn-left").show();
		}
		var temp = $(this).closest("td").next().html();
		$(this).closest("td").next().html($(this).closest("td").html());		
		$(this).closest("td").html(temp);
		
		$(".tableinfo tr").each(function(x,item){			
			var target=$(item).find("td");
			var temp=target.eq(targetIndex).html();
			target.eq(targetIndex).html(target.eq(targetIndex-1).html());
			target.eq(targetIndex-1).html(temp);			
		});
		utils.showMoveBtn();
	});
	
	$(".tablechoice").on("click",".js-btn-left",function (){
		var targetIndex=$(this).closest("td").index()-1;	
		console.log(targetIndex);
		if(targetIndex==0){
			$(this).hide().siblings(".js-btn-left").show();
		}
		var temp = $(this).closest("td").prev().html();
		$(this).closest("td").prev().html($(this).closest("td").html());		
		$(this).closest("td").html(temp);
		
		$(".tableinfo tr").each(function(x,item){			
			var target=$(item).find("td");
			var temp=target.eq(targetIndex-1).html();
			target.eq(targetIndex-1).html(target.eq(targetIndex).html());
			target.eq(targetIndex).html(temp);	
		});
		utils.showMoveBtn();
	});
	$(".folul li").click(function (){
		$(this).siblings().children().removeClass("active");
		$(this).children().addClass("active");
	});
});

function realodParam(){
	
	
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
}

function compareData(type){	
	$(".js-titems tr").each(function(x,item){
		var compareObj=[]
		$(item).find("td").each(function (x,item){
			if(x<cars_count){
				compareObj.push($.trim($(item).text()));
			}
		});
		if(isEqaul(compareObj)&&type=="hide"){
			$(this).hide();
		}
		if(!isEqaul(compareObj)&&type=="highlight"){
			$(this).addClass("highlight");
		}
	});
}

function isEqaul(compareObj){
	var eqaul=true;
	for(var i=0;i<compareObj.length;i++){
		if(compareObj[i]!=compareObj[i+1]&&(i+1)<compareObj.length){
			eqaul=false;
		}
	}
	console.log(compareObj);
	console.log(eqaul);
	return eqaul;
}

function refreshNextTarget(target){
	//复制品牌至下一个下拉
	if(target.cloneBrand){
		target.nextTarget.find(".js-brand span").text(target.text).attr("data-key",target.key);
		target.nextTarget.find(".js-brand dl dd").each(function (x,dd){
			if($(dd).children().attr("data-key")==target.key){
				$(dd).addClass("current").siblings().removeClass("current");
			}
		});
		target.nextTarget.find(".js-series dl").html(target.curTarget.find(".js-series dl").html());
	}
	//复制车系至下一个下拉
	if(target.cloneSeries){
		target.nextTarget.find(".js-series span").text(target.text).attr("data-key",target.key);
		target.nextTarget.find(".js-series dl dd").each(function (x,dd){
			if($(dd).children().attr("data-key")==target.key){
				$(dd).addClass("current").siblings().removeClass("current");
			}
		});	
		target.nextTarget.find(".js-spec dl").html(target.curTarget.find(".js-spec dl").html());
	}
	//清除当前车系	
	if(target.clearSeries){
		target.nextTarget.find(".js-series span").text("请选择车系").attr("data-key","");
		target.curTarget.find(".js-series span").text("请选择车系").attr("data-key","");			
	}
	//清除当前Option
	if(target.clearSpec){
		target.nextTarget.find(".js-spec span").text("请选择车系").attr("data-key","");
		target.curTarget.find(".js-spec span").text("请选择车系").attr("data-key","");
		target.nextTarget.find(".js-spec dd").remove();
		target.curTarget.find(".js-spec dd").remove();
	}	
}

var initDrop={
		initBrand:function (brand,targetIndex){
			if(!brand)return;
			$.each($(".text-ul").eq(targetIndex).find('.js-brand dl dd'),function (x,target){
				if(brand==$(this).children().attr("data-key")){
					$(target).addClass("current");
					$(target).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));
					return false;
				}
			});
		},
		initSeries:function(brandId,series,targetIndex) {
			if(!brandId)return;
			var opts={
					brandId:brandId,
					target:$(".text-ul").eq(targetIndex).find('.js-series dl'),
					callback:function (){
						$.each($(".text-ul").eq(targetIndex).find('.js-series dl dd'),function (x,target){
							if(series==$(this).children().attr("data-key")){
								$(target).addClass("current");
								$(target).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));
								return false;
							}
						});
					}
				};
			loadSeries(opts);
		},
		//初始化车型
		initSpec:function (deptId,spec,targetIndex){
			if(!deptId)return;			
			var opts={
					deptId:deptId,
					target:$(".text-ul").eq(targetIndex).find('.js-spec dl'),
					callback:function (){
						$.each($(".text-ul").eq(targetIndex).find('.js-spec dl dd'),function (x,target){							
							if(spec==$(this).children().attr("data-key")){
								$(target).addClass("current");
								$(target).closest(".select").find("span").text($(this).text()).attr("data-key",$(this).children().attr("data-key"));
								return false;
							}
						});
					}
				};
			loadCar(opts);
		}		
    };

var utils={
	readCookie:function (cookieName){
		var cookieV=$.cookie(cookieName);// 存储 cookie
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7}); // 存储 cookie
	},
	showMoveBtn:function(){
		$.each($(".tablechoice").find("td"),function (x,item){
			if($(item).index()==1){
				$(item).find(".js-btn-left").hide();
			}
			else if($(item).index()==4){
				$(item).find(".js-btn-right").hide();
			}
			else{
				$(item).find(".js-btn-left").show();
				$(item).find(".js-btn-right").show();
			}
		});
	}
};

function init(){
	var cookieV=utils.readCookie("car_ids");
	if(cookieV){
		var splitV=cookieV.split("|");
		for(var i=0;i<splitV.length;i++){		
			var splitO=splitV[i].split(",");
			if(splitO.length>0){
				var opts={
					brand:splitO[2],
					series:splitO[1],
					spec:splitO[0],
					targetIndex:i
				};
				initDrop.initBrand(opts.brand, opts.targetIndex);
				initDrop.initSeries(opts.brand,opts.series, opts.targetIndex);
				initDrop.initSpec(opts.series,opts.spec, opts.targetIndex);
			}
		}
	}
}


function buildCarIds(target){
	if($(target).hasClass("current")){
		return false;
	};
	var parameters=new Array(4);
	var targetIndex=$(target).closest("td").index()-1,isJoin=true;
	var opts=
			[
				$(target).children().attr("data-key"),//获取车型
				$(target).closest("ul").find(".js-series span").attr("data-key"),//获取车系
				$(target).closest("ul").find(".js-brand span").attr("data-key"),//获取品牌
			];
	var cookieV=utils.readCookie("car_ids");
	if(cookieV){
		var splitV=cookieV.split("|");
		for(var i=0;i<splitV.length;i++){
			var o=splitV[i].split(",");
			if(o[0]==opts[0]){
				alert("已选择该车型");
				isJoin=false;					
			}			
			parameters[i]=splitV[i];			
		}
		if(isJoin){
			parameters[targetIndex]=opts.join(",");
			utils.writeCookie("car_ids",parameters.join("|")); // 存储 cookie 
		}
	}else{
		parameters[targetIndex]=opts.join(",");
		utils.writeCookie("car_ids",parameters.join("|")); // 存储 cookie
	}
	return isJoin;
}


function loadBrand(callback){	
	$(".js-brand").find("dl").html(brandDefault);
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
	    			$(".js-brand").find("dl").append('<dd><a data-key="'+brand.brandId+'" href="javascript:void(0)">'+brand.brandName+'</a></dd>');		
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
	    				seriesHTML+='<dd><a data-key="'+dept.carDeptId+'" href="javascript:void(0)">'+dept.carDeptName+'</a></dd>';
	    			});	    			
	    		});
	    		opts.target.html(seriesHTML);	    		
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
	    			seriesHTML+='<dd><a data-key="'+car.carId+'" href="javascript:void(0)">'+car.carName+'</a></dd>';
	    		});	    		
	    		opts.target.html(seriesHTML);		
	    	}
	    	if(opts.callback){
	    		opts.callback();
	    	}
	    }
	});
}

//隐藏下拉框
function hideinfo() {
	$(".select-option").hide();
	$("body").unbind("click", hideinfo);
}