$(function (){
	$(".bannerWrap").find("img").each(function (x,img){
		$(img).attr("src",yoyo.imagesUrl+$(img).attr("src"));
	});
	
	$(".f_2").find("img").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
	
	$(".f_3").find("img").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
	
	$(".f_4").find("img").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
	
	$(".car_show ul li").mouseover(function(e) {
        $(this).children("b").css("left","30px");
    }).mouseout(function(e) {
        $(this).children("b").css("left","10px");
    });
	
    $.fn.lazyhover = function(fuc_on, de_on, de_out) {
        var self = $(this);
        var flag = 1;
        var h;
        var handle = function(elm) {
            clearTimeout(h);
            if (!flag) {
                self.removeData("timer");
            }
            return flag ? fuc_on.apply(elm) : null;
        };
        var time_on = de_on || 0;
        var time_out = de_out || 500;
        var timer = function(elm) {h && clearTimeout(h);
            h = setTimeout(function() {
                handle(elm);
            },
            flag ? time_on: time_out);
            self.data("timer", h);
        };
        self.hover(function() {
            flag = 1;
            timer(this);
        },
        function() {
            flag = 0;
            timer(this);
        });
    };
    function HtmlSlidePlayer(config) {
        this.n = 0;
        this.j = 0;
        this.first_show = 1;
        var _this = this;
        var t;
        var defaults = {
            fontsize: 12,
            right: 10,
            bottom: 15,
            time: 5000,
            autosize: 0,
            slidearrow: false
        };
        this.config = $.extend(defaults, config);
        this.count = $("#mtsBanner" + " li").length;
        this.factory = function() {
            $("#mtsBanner").css({
                position: "relative",
                zIndex: "0",
                overflow: "hidden",
                height: $("#mtsBanner" + " ul").height() + "px"
            });
            $("#mtsBanner").prepend("<a href='javascript:;' class='move_left'></a><a href='javascript:;' class='move_right'></a>");
            $("#mtsBanner").prepend("<div class='slide_control'></div>");
            $("#mtsBanner" + " ul").css({
                position: "relative",
                zIndex: "0",
                margin: "0",
                padding: "0",
                overflow: "hidden",
                width: "100%"
            });
            $("#mtsBanner" + " li").css({
                position: "absolute",
                top: "0",
                left: "0",
                width: "100%",
                height: "100%",
                overflow: "hidden"
            }).hide().each(function(i) {
                $("#mtsBanner" + " .slide_control").append("<a>" + (i + 1) + "</a>");
            });
            this.resetclass("#mtsBanner" + " .slide_control a", 0);
            this.slide();
            t = setInterval(this.autoplay, this.config.time);
            $("#mtsBanner" + " .slide_control a").eq(0).triggerHandler("mouseover");
            var slideContorlWidth = $(".slide_control").width();
            $(".slide_control").css({
                marginLeft: -slideContorlWidth / 2
            });
        };
        this.slide = function() {
            $("#mtsBanner" + " .slide_control a").lazyhover(function() {
                _this.j = $(this).text() - 1;
                _this.n = _this.j;
                if (_this.j >= _this.count) {
                    return
                }
                moveFun(_this.first_show, _this.j);
            },
            200, 500);
            $("#mtsBanner").delegate("a.move_left", "click",
            function() {
                _this.j = $("#mtsBanner .slide_control a.mall_dot_hover").text() - 1;
                _this.j -= 1;
                _this.n = _this.j;
                if (_this.j >= _this.count) {
                    this.j = 0;
                }
                if (_this.j < 0) {
                    _this.j = _this.count - 1;
                }
                moveFun(_this.first_show, _this.j);
            }).delegate("a.move_right", "click",
            function() {
                _this.j = $("#mtsBanner .slide_control a.mall_dot_hover").text() - 1;
                _this.j += 1;
                _this.n = _this.j;
                if (_this.j >= _this.count) {
                    _this.j = 0
                }
                moveFun(_this.first_show, _this.j);
            });
        };
        function moveFun(first_show, j) {
            if (first_show) {
                _this.first_show = 0;
                $("#mtsBanner" + " li:eq(" + j + ")").show().addClass("on").siblings("li").removeClass("on").hide();
            } else {
                var li = $("#mtsBanner" + " li:eq(" + j + ")");
                var next_li;
                if (_this.count >= _this.j + 1) {
                    next_li = $("#mtsBanner" + " li:eq(" + (j + 1) + ")");
                }
                li.fadeIn("200",
                function() {
                	$(this).addClass("on");
                }).siblings("li").fadeOut("200",function() {
                    $(this).removeClass("on");
                });
            }	            
            _this.resetclass("#mtsBanner" + " .slide_control a", j)
        }
        $("#mtsBanner").delegate("", "mouseover",
        function() {
            $(this).find("a.move_left,a.move_right").stop("true", "true").fadeIn("fast");
        });
        $("#mtsBanner").delegate("", "mouseleave",
        function() {
            $(this).find("a.move_left,a.move_right").stop("true", "true").fadeOut("fast");
        });
        $("#mtsBanner").mouseover(function() {
            clearInterval(t);
        });
        $("#mtsBanner").mouseout(function() {
            t = setInterval(_this.autoplay, _this.config.time);
        });
        this.autoplay = function() {
            _this.n = _this.n >= (_this.count - 1) ? 0 : ++_this.n;
            $("#mtsBanner" + " .slide_control a").eq(_this.n).triggerHandler("mouseover")
        };
        this.resetclass = function(obj, i) {
            $(obj).removeClass("mall_dot_hover").addClass("mall_dot");
            $(obj).eq(i).addClass("mall_dot_hover");
            if (!window.XMLHttpRequest) {
                $(".img_slider_trigger").css("zoom", "1");
            }
        };
        this.factory();
    }
    HtmlSlidePlayer({
        autosize: 0,
        fontsize: 12,
        time: 4000
    });
    
	$(".floorBanner").find("img").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
	
	$(".bill_logo").find("img").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
	shop_index.init();
});
var shop_index={
	view_cookie:"v_store",
	view_history:[],
	readCookie:function (cookieName){
		var cookieV=$.cookie(cookieName);// 存储 cookie
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7, path: "/"}); // 存储 cookie
	},
	init:function (){
		if(company){
			var historyCookieV=shop_index.readCookie(shop_index.view_cookie);
			if(historyCookieV){
				$.each(historyCookieV.split(","),function (x,item){
					if(company!=item.split("|")[0]){
						shop_index.view_history.push(item);
					}
				});
				shop_index.view_history.push(company+"|"+new Date().getTime());
			}else{
				shop_index.view_history.push(company+"|"+new Date().getTime());
			}
			shop_index.writeCookie(shop_index.view_cookie, shop_index.view_history.join(","));
		}
	}
};

function focuseShop(companyId){
	$.post(yoyo.portalUrl+"/shop/focusStore",{companyId:companyId},function (result){
		if(result.retCode=="000000"){
			layer.msg("关注店铺成功!",{time:2000});
		}else if(result.retCode=="NL001"){
			layer.msg("请登录后再关注店铺!",{time:2000});
		}else if(result.retCode=="000002"){
			if(result.content){
				layer.msg(result.content.msg,{time:2000});	
			}else{
				layer.msg("关注店铺失败",{time:2000});
			}
		}
	});
}
