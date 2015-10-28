var total,rows,checkArry,companyArray;
$(function (){
	$("#Pagination").yoyoPagination(total,{
		items_per_page:20,
		callback:function(){
			loadViewList({refresh:false});			
		}
	});
});
function loadViewList(opts){
	$.ajax({
	    url:'../viewHisotry/loadViewProductList',
	    data:{page:$("#page-num").val(),rows:20},
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	$(".gridlist tr:gt(0)").empty();
	    	if(data&&data.rows.length>0){
	    		$.each(data.rows,function (x,content){
	    			$(".gridlist").append(intiViewListHTML(content));
	    		});
	    	}
	    	if(opts&&opts.refresh){	    		
	    		$("#Pagination").yoyoPagination(data.total,{
	    			items_per_page:data.pageSize,
	    			callback:function(){
	    				loadViewList({refresh:false});
	    			}
	    		});
	    	}
	     },    
	     error : function() {    
	     }
	});
}

function intiViewListHTML(content){
	var wishlist=[];
	wishlist.push('<tr>');
	wishlist.push('<td>');
	wishlist.push('<img width="50" height="50" src="'+yoyo.imagesUrl+content.picturePath+'" />');
	wishlist.push('</td>');
	wishlist.push('<td style="text-align:left;">');
	wishlist.push('<a target="_blank" href="'+yoyo.portalUrl+'/goodsManager/goodsIndex?goodsId='+content.goodsId+'">'+content.productName+'</a>');
	wishlist.push('</td>');
	wishlist.push('<td>￥'+content.price+'</td>');
	wishlist.push('<td>'+new Date(content.viewDate).format("yyyy-MM-dd hh:mm:ss")+'</td>');								
	wishlist.push('<td>');
	wishlist.push('<div style="width: 200px;word-break:break-all; overflow: auto;">'+content.loginName+'</div>');
	wishlist.push('</td>');
	wishlist.push('</tr>');
	return wishlist.join("");
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

