/*******************************************************************************
* Copyright (C) Gasgoo Corporation. All rights reserved.
* 
* Author: Zhu.Xing.Chen
* Create Date: 2011-08-05
* Description: Image loading delay
*          
* Revision History:
* Date         Author               Description
* 
*********************************************************************************/
(function($) {
    $.Lazyload = {
        pageTop: function(settings) {  //可见区域高度 + 网页被卷去的高
            var oDoc = document; //性能优化
            return oDoc.documentElement.clientHeight + Math.max(oDoc.documentElement.scrollTop, oDoc.body.scrollTop) + settings.threshold;
        },
        imgLoad: function(jqImg, settings) {  //加载图片
            var preCallback=settings.preCallback;
            var srcVal=settings.dataSrcvalue;
            var l = jqImg.length;
            for (var _len = l - settings.loadedNumber; _len--; ) { //性能优化
                var jqThisImg = $(jqImg[l - 1 - _len]);
                if (jqThisImg.offset().top <= $.Lazyload.pageTop(settings)) {
                    var src2 = jqThisImg.attr(srcVal);
                    if (src2) {
                        if(preCallback){
                             preCallback(jqThisImg);
                         }
                        jqThisImg.attr("src", src2).removeAttr(srcVal)[settings.effect]("slow");
                    }
                } else {
                    settings.loadedNumber = l - 1 - _len;
                    jqThisImg = null; //释放内存
                    break;
                }
            }
        }
    };
    $.fn.Lazyload = function(options) {
        var settings = {
            time: 100,            //延迟时间
            threshold: 0,       //边境值触发加载
            effect: "fadeIn",     //效果
            loadedNumber: 0,    //记录加载到第几个
            event: "scroll",    //事件
            container: window,   //容器对象
            preCallback:null,  //前回调函数
            dataSrcvalue:"src2",
            ClearLazy:null
        };
        $.extend(true, settings, options || {});
        var jqImg = $(this).find('img');
        $.Lazyload.imgLoad(jqImg, settings);
        $(settings.container).bind(settings.event, function() {
            clearTimeout(settings.ClearLazy);
            settings.ClearLazy = setTimeout(function() {
                $.Lazyload.imgLoad(jqImg, settings);
            }, settings.time);
        });
    };
})(jQuery);

usingNamespace("Web.UI")["ItemGift"] = {
    MoveEvent: function(){
            $(".gifts a").mouseover(function(){
                $(this).next().show();
             });
              $(".gifts a").mouseout(function(){
                $(this).next().hide();
              });
    }
}

var $ItemGift = Web.UI.ItemGift; 