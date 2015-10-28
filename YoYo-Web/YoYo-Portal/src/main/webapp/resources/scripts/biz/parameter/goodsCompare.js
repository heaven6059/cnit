$(function (){
	compare(info,goods);
});

var compare=function (config,goods){
    //缓存数据
    this.Data = {
        goodsList: [], //当前车型goods列表
        goods:[]
    };
    
    
    this.init = function () {
        //检测是否有数据    	
        if (!config||!goods) return;        
        this.Data.goodsList=config;
        this.Data.goods=goods;
        this.responseTitle();
        this.responseContent();
    };
    this.responseTitle = function () {
    	var html=[],priceContent=[],temp=[],spaceColNum = this.Data.goodsList.length > 4 ? 0 : 4 - this.Data.goodsList.length;
    	html.push('<table class="tablechoice tabcolor" style=" height: auto; margin-bottom: 0; ">');
    	html.push('<tr>');
    	html.push('<th class="title">');
    	html.push('<input type="checkbox" />&nbsp;<label>高亮显示差异参数</label><br><input type="checkbox" />&nbsp;<label>隐藏相同参数</label>');
    	html.push('</th>');
    	$.each(this.Data.goods,function(x,item){
    		html.push('<td class="text js-text-select js-items-select " data-index="0">');
        	html.push('<div class="btn_delbar">');
        	html.push('<a class="btn_del" href="javascript:delCompeareItem('+item.goodsId+');"></a>');
        	html.push('</div>');
        	html.push('<div class="carbox">');
        	html.push('<a rel="nofollow" href="../goodsManager/goodsIndex?goodsId='+item.goodsId+'" target="_blank"><img width="100" height="100" src="'+yoyo.imagesUrl+item.goodsImg+'"></a>');
        	html.push('<div class="carboxin"><a href="../goodsManager/goodsIndex?goodsId='+item.goodsId+'" target="_blank">'+item.goodsName+'</a></div>');
        	html.push('</div>');
        	html.push('</td>');
        	priceContent.push('<td class="text">￥'+item.price+'</td>');
    	});
    	for (var j = 0; j < spaceColNum; j++) {
    		html.push('<td data-index="3" class="text js-text-select js-items-select border-r-no">&nbsp;</td>');
    		temp.push('<td class="text"></td>');
    	}
    	html.push('</tr>');
    	html.push('</table>');
    	
    	
    	html.push('<table class="js-titems tableinfo">');
	    html.push('<tr>');
	    html.push('<th class="title">');
	    html.push('<a href="javascript:;" target="_blank">优优价</a>');
	    html.push('</th>');
	    html.push(priceContent.join("")+temp.join(""));
	    html.push('</tr>');
        html.push('</table>');
    	
    	$(".title-content").append(html.join(""));
    };
    this.responseContent = function () {
    	var html=[],spaceColNum = this.Data.goodsList.length > 4 ? 0 : 4 - this.Data.goodsList.length;
    	console.log(spaceColNum);
    	$.each(this.Data.goodsList,function(x,item){
    		html.push('<div class="js-title genre-title">');
    		html.push('<i class="icon10 icon10-pack"></i>');
    		html.push('<h3 id="floor1">'+item.name+'</h3>');
    		html.push('</div>');
    		
    		$.each(item.catalog,function(x,item){
    			html.push('<table class="js-titems tableinfo">');
    	    	html.push('<tr>');
    	    	html.push('<th class="title">');
    	    	html.push($.trim(item.name));
    	    	html.push('</th>');
    	    	$.each(item.catalogChildren,function (x,item){
    	    		html.push('<td class="text">'+$.trim(item.values)+'</td>');
    	    	});
    	    	var colNum=item.catalogChildren.length>spaceColNum?0:spaceColNum-item.catalogChildren.length;
    	    	/**
    	    	for (var j = 0; j < colNum; j++) {
   	        	 	html.push('<td class="text">-</td>');
    	    	}**/
    	    	for (var j = 0; j < spaceColNum; j++) {
    	    		html.push('<td class="text"></td>');
    	    	}
                html.push('</tr>');
                html.push('</table>');
    	    });
    	});
    	$(".title-content").append(html.join(""));
    };
    this.readCookie=function (cookieName){
		var cookieV=$.cookie(cookieName);// 存储 cookie
		return cookieV;
	};
	this.writeCookie=function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7, path: "/"}); // 存储 cookie
	};
    this.delCompeareItem=function(id){
    	var accessory=this.readCookie("accessory_compare"),val=[];
    	if(accessory){
    		val=JSON.parse(accessory);
    		$.each(val,function (x,item){
    			if(item.id==id){
    				val.splice(x, 1);
    				return false;
    			}
    		});		
    	}	
    	this.writeCookie("accessory_compare", JSON.stringify(val));
    	reloadCompare();
    };
    this.reloadCompare=function(){
    	var accessory=this.readCookie("accessory_compare"),val=[];
    	if(accessory){
    		$.each(JSON.parse(accessory),function (x,item){
    			val.push(item.id);
    		});		
    	}
    	window.location.href=yoyo.portalUrl+"/carCompare/goodsCompare?ids="+val.join(",");
    };
	this.init();
};