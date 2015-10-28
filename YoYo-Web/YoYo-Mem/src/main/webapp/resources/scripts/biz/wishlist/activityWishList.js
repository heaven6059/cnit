var total,rows,checkArry;
$(function (){
	loadWishList({refresh:true});
	$("#del-all-box").click(function (){
		if($(this).is(":checked")){
			checkArry = new Array();
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).prop("checked", true);
				checkArry.push($(box).val());
			});
		}else{
			checkArry = [];
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				 $(box).prop("checked", false);
			});
		}
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
	
	$("#del-all-btn").click(function (){
		if(typeof(checkArry) == "undefined"||checkArry.length<=0){
			alert("请选择需要删除的关注活动");
			return;
		}
		deleteWishList(checkArry.join(","));
	});
	
	$(".gridlist").on("mouseover",".share",function (){
		$(this).addClass("hover");
	});
	
	$(".gridlist").on("mouseout",".share",function (){
		$(this).removeClass("hover");
	});
});

Array.prototype.elremove = function(m){  
    if(isNaN(m)||m>this.length){return false;}  
    this.splice(m,1);  
};


function deleteWishList(id){
	if(!confirm("是否删除选择关注活动")){
		return;
	}
	$.ajax({
	    url:'../activityWishList/deleteActivityWishList',
	    data:{id:id},
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(head){
	    	if(head.headObject.retCode==0000){
	    		$("#page-num").val("1");
	    		loadWishList({refresh:true});
	    		$("#del-all-box").attr("checked",false);
	    		alert("删除成功");
	    	}else{
	    		alert("删除失败");
	    	}
	     },    
	     error : function() {    
	          alert("异常！");    
	     }
	});
	checkArry=[];
}

function loadWishList(opts){	
	$.ajax({
	    url:'../activityWishList/loadActivityWishList',
	    data:{page:$("#page-num").val()},
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	var content=data.resultObject.content;
	    	var head=data.resultObject.head;
	    	if(head.retCode==0000){
	    		$(".gridlist tr:gt(0)").empty();
	    		$.each(content.rows,function (x,content){
	    			$(".gridlist").append(intiWishListHTML(content));
	    		});
	    	}else{
	    		alert("加载关注的活动信息失败");
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
	          alert("异常！");    
	     }
	});
}

function intiWishListHTML(content){
	var createDate=new Date();
	createDate.setTime(content.wishlistDate);
	var wishlist=
	'<tr product="'+content.id+'" class="itemsList">'+
	'<td class="no_bk"><input name="checked-item" type="checkbox" value="'+content.id+'"></td>'+
	'<td class="horizontal-m"><a href="">买300送300</a></td>'+
  	'<td align="center">2015-04-27至 2015-05-31 </td>'+
  	'<td align="center">活动进行中</td>'+
  	'<td align="center" class="member-fav" style="vertical-align:middle">'+
		  	'<ul class="operating">'+
			'<li class="f1">'+
				'<div class="share">'+
					'<div class="fore">分享给好友<b></b></div>'+
					'<ol class="hide">'+
					'<li clstag="click|keycount|follow|siteqzone" class="fore1"><s></s><a href="javascript:void(0)" onclick="share(\'http://v.t.qq.com/share/share.php?source=1000002&amp;site=http://www.360buy.com\',\'qzone\',\'\')" id="site-qzone" title="推荐到腾讯微博">qzone</a></li>'+
						'<li clstag="click|keycount|follow|sitedouban" class="fore2"><s></s><a href="javascript:void(0)" onclick="share(\'http://www.douban.com/recommend/?\',\'douban\',\'\');" id="site-douban" title="推荐到豆瓣">豆瓣</a></li>'+
						'<li clstag="click|keycount|follow|sitekaixin" class="fore4"><s></s><a href="javascript:void(0)" onclick="share(\'http://www.kaixin001.com/repaste/share.php?\',\'kaixing\',\'\')" id="site-kaixing" title="推荐到开心网">开心网</a></li>'+
						'<li clstag="click|keycount|follow|sitexinlang" class="fore5"><s></s><a href="javascript:void(0)" onclick="share(\'http://v.t.sina.com.cn/share/share.php?appkey=2445336821\',\'sina\',\'\')" id="site-sina" title="推荐到新浪微博">新浪微博</a></li>'+
					'</ol>'+
				'</div>'+
			'</li>'+
			'<li>'+
				'<a class="operate-btn-del operate-btn" onclick="deleteWishList('+content.id+');">删除</a>'+ 
			'</li>'+
		'</ul>'+		
	'</td>'+
	'</tr>';
	return wishlist;
}


function share(url, stype, activityUrl) {
	var title = activityUrl;
	var content = "我在@YOYO商城 发现了一个非常不错的活动：";
	if (title)
	content+=title;
	content+="。感觉不错，分享一下";
	if (stype == "qzone") {
		url = url + "&title=" + content;
	}
	if (stype == "sina") {
		url = url + "&title=" + content;
	}
	if (stype == "renren") {
		url = url + "title=" + "&content=" + content;
	}
	if (stype == "kaixing") {
		url = url + "rtitle=" + "&rcontent=" + content;
	}
	if (stype == "douban") {
		url = url + "title=" + title + "&comment=" + content;
	}
	if (stype == "MSN") {
		url = url + "url=" + activityUrl + "&title="
				+ "&description=" + content;
	}
	window.open(encodeURI(url), "", "height=500, width=600");
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

