var total,rows,checkArry,companyArray;
$(function (){
	loadWishList({refresh:true});
	$("#del-all-box").click(function (){
		if($(this).is(":checked")){
			checkArry = new Array();
			companyArray=new Array();
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				$(box).prop("checked", true);
				checkArry.push($(box).val());
				companyArray.push($(box).attr("data-company"));
			});
		}else{
			checkArry = [];
			companyArray = [];
			$.each($(".gridlist").find(":checkbox"),function (x,box){
				 $(box).prop("checked", false);
			});
		}		
	});
	
	$("#del-all-btn").click(function (){
		if(typeof(checkArry) == "undefined"||checkArry.length==0){
			layer.alert("请选择需要取消关注的店铺！",{title:false,closeBtn:false,icon:0});
			return;
		}
		deleteWishList(checkArry.join(","),companyArray.join(","));
	});
	
	$(".gridlist").on("click",":checkbox",function (){
		if(typeof(checkArry) == "undefined"){
			checkArry = new Array();
		}
		if($(this).is(":checked")){
			checkArry.push($(this).val());
			companyArray.push($(box).attr("data-company"));
		}else{
			for(var i=0;i<checkArry.length;i++){
				if($(this).val()==checkArry[i]){
					checkArry.elremove(i);  
				}
			}
			for(var i=0;i<companyArray.length;i++){
				if($(this).attr("data-company")==companyArray[i]){
					companyArray.elremove(i);  
				}
			}			
		}
	});
});

Array.prototype.elremove = function(m){  
    if(isNaN(m)||m>this.length){return false;}  
    this.splice(m,1);  
};


function deleteWishList(id,companyId){
	layer_utils.confirm("是否确认取消关注选择的店铺",function (){
		$.ajax({
		    url:'../storeWishList/deleteStoreWishList',
		    data:{id:id,companyId:companyId},
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(head){
		    	if(head.headObject.retCode==0000){
		    		$("#page-num").val("1");
		    		loadWishList({refresh:true});
		    		layer.alert("已取消关注店铺！",{title:false,closeBtn:false,icon:1});
		    	}else{		    		
		    		layer.alert("取消关注店铺失败！",{title:false,closeBtn:false,icon:0});
		    	}
		     }
		});
	});
	checkArry=[];
	companyArray=[];
	$("#del-all-box").attr("checked",false);
}

function loadWishList(opts){
	$.ajax({
	    url:'../storeWishList/loadStoreWishList',
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
	var createDate=new Date(),favCount=0;
	createDate.setTime(content.wishlistDate);	
	if(content.favCount!=null){
		favCount=content.favCount;
	}
	var wishlist=
	'<tr product="'+content.id+'" class="itemsList">'+
	'<td class="no_bk"><input name="checked-item" type="checkbox" data-company="'+content.companyId+'" value="'+content.id+'"></td>'+
	'<td class="horizontal-m" style="white-space:normal">'+
        '<div class="goodpic">'+
            '<a href="'+yoyo.portalUrl+'/shop/index?companyId='+content.companyId+'" style="" target="_blank">'+
    			'<img alt="'+content.storeName+'" src="'+yoyo.imagesUrl+content.storeLogo+'" style="width:80px; height:80px;">'+
  			'</a>'+
       	'</div>'+
  		'<div class="goods-main">'+
  			'<div class="goodinfo"><a href="'+yoyo.portalUrl+'/shop/index?companyId='+content.companyId+'">'+content.storeName+'</a>'+
   			'</div>'+
  		'</div>'+
  	'</td>'+
  	'<td align="center">'+content.shopName+'</td>'+
  	'<td align="center">'+favCount+'</td>'+
    '<td>'+createDate.format("yyyy-MM-dd hh:mm:ss")+'</td>'+
  	'<td align="left" class="member-fav" style="vertical-align:middle">'+
		'<a class="operate-btn-del operate-btn" onclick="deleteWishList('+content.id+','+content.companyId+');">取消关注</a>'+
	'</td>'+
	'</tr>';
	return wishlist;
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

