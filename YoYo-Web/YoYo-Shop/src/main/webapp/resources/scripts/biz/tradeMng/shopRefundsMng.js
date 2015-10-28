var total,rows;
$(function (){
	// 创建分页元素
	$("#Pagination").yoyoPagination($("#total").val(), {
		items_per_page:$("#rows").val(),
		page_num_id:"page",
		page_num_name:"page",
		current_page:$("#pageIndex").val()-1,
		callback:function (){
			$("#refunds_form").submit();
		}
	});
});

function loadImg(){
	$(".gridlist").find("img").each(function (x,img){
		if($(img).attr("data-original")){				
			$(img).attr("src",yoyo.imagesUrl+$(this).attr("data-original"));
		}
	});
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