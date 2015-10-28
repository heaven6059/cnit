var inventorySize;
//jqueryzoom
!function(a){a.fn.jqueryzoom=function(b){var c={xzoom:200,yzoom:200,offset:10,position:"right"},d=a(this);a.extend(c,b);var e="";a(this).parent().hover(function(){var b=d.offset().left,f=d.offset().top,g=a(this).find("img").get(0).offsetWidth,h=a(this).find("img").get(0).offsetHeight,i=e=a(this).find("img").attr("alt");a(this).find("img").attr("alt",""),0==a("div.zoomdiv").size()&&a(this).find("img").after("<div class='zoomdiv'><img class='bigimg' src='"+i+"'/></div>"),0==a("div.zoomspan").size()&&a(this).find("img").eq(0).after("<div class='zoomspan'></div>"),a("div.zoomdiv").css({top:c.top,left:c.left}).width(c.xzoom).height(c.yzoom).show(),a("div.zoomspan").show(),a(document.body).mousemove(function(c){var d=a(".bigimg").get(0).offsetWidth,e=a(".bigimg").get(0).offsetHeight,i="x",j="y";if(isNaN(j)|isNaN(i))var j=Math.round(d/g),i=Math.round(e/h);var k={};k.x=c.pageX,k.y=c.pageY;var l=k.x-b-100,m=k.y-f-100;0>l&&(l=0),l>g-200&&(l=g-200),0>m&&(m=0),m>h-200&&(m=h-200),a("div.zoomspan").css({left:l+"px",top:m+"px"}),scrolly=k.y-f-1*a("div.zoomdiv").height()/i/2,a("div.zoomdiv").scrollTop(scrolly*i),scrollx=k.x-b-1*a("div.zoomdiv").width()/j/2,a("div.zoomdiv").scrollLeft(scrollx*j)})},function(){a(this).find("img").attr("alt",e),a("div.zoomdiv").hide().remove(),a(document.body).unbind("mousemove"),a(".lenszoom").remove(),a("div.zoomspan").hide()})}}(jQuery);
$(function(){
	//详情图片滚动，放大
    $("img.jqzoom").jqueryzoom({
            xzoom: 400,
            yzoom: 400,
            offset: 30,
            left: 380,
            top: -1,
            position: "right"
        });
    var i = 0;
    $(".goods_show .goods_pic").delegate('div.prev','click',function(){
        var box = $(this).next().find('div');
        i--;
        if(i<0){i=0;}
        box.stop(true,true).animate({top:'-'+(i*90)+'px'});
    }).delegate('div.next','click',function(){
        var box = $(this).prev().find('div');
        var max = box.find('a').size()-4;
        i++;
        if(i>max){i=max;}
        box.stop(true,true).animate({top:'-'+i*90+'px'});
    }).delegate('div.move_box a','mouseenter',function(){
        $(this).siblings().removeClass('on').find('i').remove();
        $(this).append('<i></i>').addClass('on');

    })
	;
//    tabimg('#imgshow dd a', '#imgshow dt img', 'imgselect', 'bigsrc', 'supsrc');
    $("div.zoomspan").hide();
});

/*小图切大图*/
//    function tabimg(btn, imgobj, cls, bsrc, ssrc) {
//        $(btn).mouseover(function () {
//            $(this).siblings().removeClass('imgselect');
//            $(this).addClass(cls);
//            $(imgobj).attr('src', $(this).attr(bsrc));
//            $(imgobj).attr('alt', $(this).attr(ssrc));
//        });
//    };