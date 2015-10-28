var total,rows,checkArry,companyArray;
$(function (){
	$("#Pagination").yoyoPagination(total,{
		items_per_page:20,
		callback:function(){
			loadWishList({refresh:false});			
		}
	});
});
function loadWishList(opts){
	$.ajax({
	    url:'../focusStore/loadFocusStoreList',
	    data:{page:$("#page-num").val(),rows:20},
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {	    	
	    	$(".gridlist tr:gt(0)").empty();
	    	if(data&&data.rows.length>0){
	    		$.each(data.rows,function (x,content){
	    			$(".gridlist").append(intiWishListHTML(content));
	    		});
	    	}
	    	if(opts&&opts.refresh){	    		
	    		$("#Pagination").yoyoPagination(content.total,{
	    			items_per_page:1,
	    			callback:function(){
	    				loadWishList({refresh:false});
	    			}
	    		});
	    	}
	     },    
	     error : function() {    
	     }
	});
}

function intiWishListHTML(content){
	var wishlist=[];
	wishlist.push('<tr>');
	wishlist.push('<td>'+content.loginName+'</td>');
	wishlist.push('<td>'+new Date(content.wishlistDate).format('yyyy-MM-dd hh:mm:ss')+'</td>');
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

