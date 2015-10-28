/*******************************************************************************
* Copyright (C) Gasgoo Corporation. All rights reserved. 
* 
* Version: 1.0 
* Author: Zhu.Xing.Chen 
* Create Date: 2011-10-25 
* Description: Slide 
*          
* Revision History:
* Date         Author               Description
* 
*********************************************************************************/
(function($) {
    $.slide = {
        inIt: function(settings, jqSlide) {
            var jqClaNav = $("#" + settings.claNav).find(settings.claNavParent); //目标对象
            var jqClaCon = $("#" + settings.claCon); //目标对象内容
            var conNum = jqClaCon.find(settings.claConParent).size(); //滚动对象的子元素个数
            var slideH = jqClaCon.find(settings.claConParent).height()//滚动对象的子元素高度，相当于滚动的高度

            // 20130409 zhengf
            if (settings.claConHeight > 0) {
                slideH = settings.claConHeight;
            }
            // 20130409 zhengf

            var slideW = jqClaCon.find(settings.claConParent).width(); //滚动对象的子元素宽度，相当于滚动的宽度
            var autoPlay;
            var index = 0;
            var over = function() {
                if (autoPlay) {
                    clearInterval(autoPlay);
                }
            }
            var out = function() {
                if (autoPlay) {
                    clearInterval(autoPlay);
                }
                autoPlay = setInterval(doPlay, settings.timer);
            }
            var doPlay = function() {
                index++;
                if (index * settings.steps >= conNum) {
                    index = 0;
                }
                $.slide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
            }
            if (settings.effect == "scroolX") {
                jqClaCon.css("width", conNum * slideW);
            }
            if (settings.page.isPage) {
                $("#" + settings.claNav).css("width", conNum * 77+77);
                if (conNum <= 4) {
                    //$("#" + settings.page.pageUp).hide();
                    //$("#" + settings.page.pageNext).hide();
                } else {
                    $("#" + settings.page.pageUp).click(function() {
                        if (index > 0) {
                            index--;
                            $.slide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
                            $.slide.effect.test(jqClaNav, jqClaCon, index, slideH, 77, settings);
                        }
                        return
                    });
                    $("#" + settings.page.pageNext).click(function() {
                        if (index < conNum - 1) {
                            index++;
                            $.slide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
                            $.slide.effect.test(jqClaNav, jqClaCon, index, slideH, 77, settings);
                        }
                        return
                    });
                }
                jqClaNav.hover(function() {
                    index = jqClaNav.index(this);
                    $.slide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
                });
            } else {
                jqClaNav.hover(function() {
                    if (settings.autoPlay) {
                        over();
                    }
                    index = jqClaNav.index(this);
                    $.slide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
                }, function() {
                    if (settings.autoPlay) {
                        out();
                    }
                });
            }
            $.slide.effect[settings.effect](jqClaNav, jqClaCon, settings.defIndex, slideH, slideW, settings); //默认显示第一张图片
            if (settings.autoPlay) {
                autoPlay = setInterval(doPlay, settings.timer);
                jqClaCon.hover(function() {
                    over();
                }, function() {
                    out();
                });
            }
            if (settings.isClose) {
                $("#jsClose").show().click(function() {
                    $.slide.close(jqSlide);
                })
            }
        },
        close: function(jqSlide) {
            jqSlide.hide();
        },
        loadPic: function(jqClaCon, index) { //延迟加载图片
            var jqShowPic = jqClaCon.find("img").eq(index);
            if (jqShowPic.attr("src2")) {
                var src2 = jqShowPic.attr("src2");
                jqShowPic.attr("src", src2).removeAttr("src2").show();
            } else {
                jqShowPic.show();
            };
        },
        set: function(jqClaNav, jqClaCon, index, settings) { //设置信息
            if (settings.isTitle) {
                jqClaNav.parents().siblings(".title").html(jqClaNav.eq(index).find("a").attr("title")).show();
            }
            if (jqClaNav) {
                jqClaNav.eq(index).addClass("on").siblings().removeClass("on");
            }
        },
        effect: { //幻灯效果	横向和纵向	
            scroolDefalut: function(jqClaNav, jqClaCon, index, slideH, slideW, settings) {
                $.slide.loadPic(jqClaCon, index);
                jqClaCon.find(settings.claConParent).hide();
                jqClaCon.find(settings.claConParent).eq(index).show();
                $.slide.set(jqClaNav, jqClaCon, index, settings);
            },
            scroolX: function(jqClaNav, jqClaCon, index, slideH, slideW, settings) {
                $.slide.loadPic(jqClaCon, index);
                jqClaCon.stop().animate({ "left": -index * settings.steps * slideW }, settings.speed);
                $.slide.set(jqClaNav, jqClaCon, index, settings);
            },
            scroolY: function(jqClaNav, jqClaCon, index, slideH, slideW, settings) {
                $.slide.loadPic(jqClaCon, index);
                jqClaCon.stop().animate({ "top": -index * settings.steps * slideH }, settings.speed);
                $.slide.set(jqClaNav, jqClaCon, index, settings);
            },
            test: function(jqClaNav, jqClaCon, index, slideH, slideW, settings) {
                $("#" + settings.claNav).stop().animate({ "left": -index * settings.steps * slideW }, settings.speed);
            }
        }
    }
    $.fn.slide = function(options) {  //构造函数初始化变量和方法
        var settings = {
            autoPlay: true, //是否自动播放
            effect: "scroolY", //效果方向
            speed: "normal", //速度
            timer: 2000, // 计时器
            defIndex: 0, //默认显示的对象
            steps: 1, //步长
            isTitle: false, //是否有标题
            isClose: false, //是否关闭显示
            page: {
                isPage: false,
                pageUp: "",
                pageNext: ""
            },
            claNav: "JQ-slide-nav",
            claCon: "JQ-slide-content",
            claNavParent: "li",
            claConParent: "li",
            claConHeight: 0,     // 指定滚动内元素的高度
            isLoop: false,         // 是否要循环滚动
            LoopNum: 3           // 每个循环需要展示的个数
        };
        var jqSlide = $(this);
        $.extend(true, settings, options || {});
        $.slide.inIt(settings, jqSlide);
    }
})(jQuery);


(function($) {
    $.scroll = {
        inIt: function(jqCon, settings) {
            var index = 0;
            var conNum = jqCon.find("li").length;
            jqCon.css("width", conNum * settings.width);
            $("#" + settings.pageUp).click(function() {
                if (index > 0) {
                    index = index - settings.number;
                    $.scroll.effect(jqCon, index, settings);
                    if (settings.sc) settings.sc(index);
                }
                if (index == 0) {
                    if (settings.fc) settings.fc(this);
                }
                return
            });
            $("#" + settings.pageNext).click(function() {
                if (index < conNum && conNum - index > settings.noScroll) {
                    index = index + settings.number;
                    $.scroll.effect(jqCon, index, settings);
                    if (settings.sc) settings.sc(index);
                }
                if (index >= conNum - settings.number) {
                    if (settings.lc) settings.lc(this);
                }
                $.scroll.loadPic(jqCon, index, settings);
                return
            });
        },
        loadPic: function(jqCon, index, settings) { //延迟加载图片
            var jqShowPic = jqCon.find("img").each(function(i) {
                if (i >= index && i < index + settings.number) {
                    var jqsp = $(this);

                    if (jqsp.attr("src2")) {
                        var src2 = jqsp.attr("src2");
                        jqsp.attr("src", src2).removeAttr("src2").show();
                    } else {
                        jqsp.show();
                    };
                }
            });

        },
        effect: function(jqCon, index, settings) {
            jqCon.stop().animate({ "left": -index * settings.steps * settings.width }, settings.speed);
        }
    };
    $.fn.scroll = function(options) {
        var settings = {
            speed: "normal",
            steps: 1,
            number: 1,
            noScroll: 6,
            width: 50,
            pageUp: "",
            pageNext: "",
            claCon: "ul",
            fc: null,   //当滚动到第一行时执行
            lc: null,   //当滚动到最后一行时执行
            sc: null     //每次滚动时执行
        };
        $.extend(true, settings, options || {});
        var jqCon = $(this).find(settings.claCon).first();
        $.scroll.inIt(jqCon, settings);
    }
})(jQuery);


/*
con   容器载体
len   一次滚动几个
*/
var VerticalScroll = function(con, len) {
    //document.childNodes.length
    var elms = GetTagList(con.childNodes);
    window.con = con;
    window.elms = elms;
    window.len = len;
    var top = parseInt($(elms[0]).height() + 9) * len + "px";
    //top = 91 * len;
    var timer = setInterval(function() {
        $(con).animate({ "top": (-parseInt(top)) }, "normal", resetDom);
    }, 4000);
}
function resetDom() {
    for (var i = 0; i < len; i++) {
        con.appendChild(elms[i]);
    }
    for (var i = 0; i < len; i++) {
        elms.push(elms.shift());
    }
    con.style.top = "0px";
    $("#JQ-darenslide-content .photo50").Lazyload();
}
function GetTagList(nods) {
    var o = [];
    for (var i = 0; i < nods.length; i++) {
        if (nods[i].nodeType === 1) {
            o.push(nods[i]);
        }
    }
    return o;
}


  function adscroll() {
                var len = $("#JQ-slide-content > div").length;
                var jqNav = $("#JQ-slide-nav");
                for (var i = 0; i < len; i++) {
                    jqNav.append("<li><a href=\"javascript:;\"  >" + (i+1) + "</a></li>");
                }
                $("#slide").slide({ claConParent: "div", timer: 6000 });
            }