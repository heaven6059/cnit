var total,rows,checkArry;
$(function (){
	loadWishList({refresh:true});
	$(".gridlist").on("click",".no_bk input[type='checkbox']",function (){
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
			layer.alert("请选择需要删除的关注商品！",{title:false,closeBtn:false,icon:0});
			return;
		}
		deleteWishList(checkArry.join(","));
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

Array.prototype.elremove = function(m){  
    if(isNaN(m)||m>this.length){return false;}  
    this.splice(m,1);  
};

function deleteWishList(id){
	layer_utils.confirm("是否删除选择关注商品?",function (){
		$.ajax({
		    url:'../productWishList/deleteWishList',
		    data:{id:id},
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(head){
		    	if(head.headObject.retCode==0000){
		    		$("#page-num").val("1");
		    		$("#del-all-box").prop("checked",false);
		    		layer.alert("关注商品删除成功！",{title:false,closeBtn:false,icon:1,end:loadWishList({refresh:true})});
		    	}else{		    		
		    		layer.alert("关注商品删除失败！",{title:false,closeBtn:false,icon:0});
		    	}
		    }
		});
		checkArry=[];
	});
}

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

function loadWishList(opts){
	$.ajax({
	    url:'../productWishList/loadProductWishList',
	    data:{page:$("#page-num").val(),rows:10},
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	var content=data.resultObject.content;
	    	var head=data.resultObject.head;
	    	if(head.retCode==0000){
	    		$(".gridlist tr:gt(0)").remove();
	    		$.each(content.rows,function (x,content){
	    			$(".gridlist").append(intiWishListHTML(content));
	    		});
	    	}else{
	    		layer_utils.alert("加载商品关注信息失败");
	    	}
	    	if(opts&&opts.refresh){
	    		$("#Pagination").yoyoPagination(content.total,{
	    			items_per_page:content.pageSize,
	    			callback:function(){
	    				loadWishList({refresh:false});
	    			}
	    		});
	    	}
	     },    
	     error : function() {    
	     }
	});
	$("#del-all-box").prop("checked",false);
	checkArry=[];
}

function intiWishListHTML(content){
	var createDate=new Date(content.wishlistDate),html= [];
	html.push('<tr product="'+content.id+'" class="itemsList">');
	html.push('<td class="no_bk"><input type="checkbox" value="'+content.id+'"></td>');
	html.push('<td align="center">');
	html.push('<div class="goodpic">');
	html.push('<a href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+content.goodsId+'" target="_blank">');
	html.push('<img alt="'+content.productName+'" src="'+yoyo.imagesUrl+content.productImg+'" style="width:50px; height:50px;">');
	html.push('</a>');
   	html.push('</div>');
   	html.push('</td>');
	html.push('<td class="horizontal-m" style="white-space:normal">');
   	html.push('<div class="goods-main">');
	html.push('<div class="goodinfo">');
	html.push('<a title="'+content.productName+'" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+content.goodsId+'" target="_blank" class="font-blue">'+content.productName+'</a>');	
	html.push('</div>');
	html.push('</div>');
  	html.push('</td>');
   	html.push('<td align="center" class="price-button">');
    html.push('<ul>');
	html.push('<li><span class="point">￥'+formatCurrency(content.productPrice)+'</span></li></ul>');
	html.push('</td>');
    html.push('<td>'+createDate.format("yyyy-MM-dd hh:mm:ss")+'</td>');
  	html.push('<td align="center" class="member-fav" style="vertical-align:middle">');
  	if(content.disabled==0&&content.marketable==1){
  		html.push('<a rel="nofollow" title="加入购物车" class="btn-bj-hover operate-btn" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+content.goodsId+'" target="_blank">加入购物车</a>');
  	}else if(content.disabled!=0){
  		html.push('<h5 style="color:red;">该商品已失效</h5>');
  	}else if(content.marketable!=1){
  		html.push('<h5 style="color:red;">该商品已下架</h5>');
  	}
  	
	html.push('<a class="operate-btn-del operate-btn" onclick="deleteWishList('+content.id+');">删除</a>');
	html.push('</td>');
	html.push('</tr>');
	return html.join("");
}


/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
    /*
     * eg:format="YYYY-MM-dd hh:mm:ss";
     */
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
    // millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }

    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
	if(!num)return "";
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}
