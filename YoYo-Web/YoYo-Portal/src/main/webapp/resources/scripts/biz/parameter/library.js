(function (namespace) {
    var wd = function () { };

    wd.fn = wd.prototype = {
        dom: {
            id: function (id, doc) { return (doc || document).getElementById(id); },
            tag: function (tag, doc) { return (doc || document).getElementsByTagName(tag); },
            create: function (tag) { return document.createElement(tag); },
            byClass: function (a, b, c) {
                var d = []; (b == null) && (b = document); (c == null) && (c = '*');
                var e = b.getElementsByTagName(c), len = e.length, pattern = new RegExp('(^|\\\\s)' + a + '(\\\\s|$)'), i;
                for (i = 0; i < len; ++i) pattern.test(e[i].className) && d.push(e[i]);
                return d;
            }
        },
        handler: {
            addEvent: function (ele, type, func) {
                if (window.document.all)
                    ele.attachEvent('on' + type, func); //ie系列直接添加执行
                else {//ff
                    if (type === 'mouseenter')
                        ele.addEventListener('mouseover', this.withoutChildFunction(func), false);
                    else if (type === 'mouseleave')
                        ele.addEventListener('mouseout', this.withoutChildFunction(func), false);
                    else
                        ele.addEventListener(type, func, false);
                }
            },
            removeEvent: function (ele, type, func) {
                return ele.removeEventListener ? ele.removeEventListener(type, func, false) : ele.detachEvent("on" + type, func);
            },
            withoutChildFunction: function (func) {
                return function (e) {
                    var parent = e.relatedTarget; //上一响应mouseover/mouseout事件的元素
                    while (parent != this && parent) {//假如存在这个元素并且这个元素不等于目标元素（被赋予mouseenter事件的元素）
                        try {
                            parent = parent.parentNode;
                        } //上一响应的元素开始往上寻找目标元素
                        catch (e) {
                            break;
                        }
                    }
                    if (parent != this)//以mouseenter为例，假如找不到，表明当前事件触发点不在目标元素内
                        func(e); //运行目标方法，否则不运行
                };
            }
        },
        loadJS: function (url, fn) {

            var ss = this.dom.tag('script'),
            loaded = false;
            for (var i = 0, len = ss.length; i < len; i++) {
                if (ss[i].src && ss[i].getAttribute('src') == url) {
                    loaded = true;
                    break;
                }
            }

            if (loaded) {
                if (fn) fn();
                return false;
            }

            var s = this.dom.create('script'),
            b = false;
            s.setAttribute('type', 'text/javascript');
            s.setAttribute('src', url);
            s.onload = s.onreadystatechange = function () {
                if (!b && (!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete')) {
                    b = true;
                    if (fn) fn();
                }
            };
            this.dom.tag('head')[0].appendChild(s);
        },
        browsers: {
            isIE: !!(window.attachEvent && !window.opera),
            isIE6: !!(window.attachEvent && !window.opera && navigator.userAgent.indexOf("MSIE 6") > -1),
            fireFox: !!(navigator.userAgent.indexOf("Firefox") > 0),
            chrome: !!(navigator.userAgent.indexOf("Chrome") > 0),
            opera: !!(window.opera)
        },
        css: function (elem, css, val) {
            if (val != undefined)
                elem.style[css] = val;
            else {
                if (typeof css == 'object') {
                    for (var i in css) elem.style[i] = css[i];
                }
                else {
                    if (document.all) {
                        var v = '';
                        if (css == 'height') {
                            if (this.css(elem, 'display') !== 'none')
                                v = elem.offsetHeight || elem.clientHeight;
                            else {
                                this.css(elem, 'display', 'block');
                                v = elem.offsetHeight || elem.clientHeight;
                                this.css(elem, 'display', 'none');
                            }
                        }
                        else if (css == 'width') {
                            if (this.css(elem, 'display') !== 'none')
                                v = elem.clientWidth || elem.offsetWidth;
                            else {
                                this.css(elem, 'display', 'block');
                                v = elem.clientWidth || elem.offsetWidth;
                                this.css(elem, 'display', 'none');
                            }
                        }
                        else
                            v = elem.currentStyle ? elem.currentStyle[css] : "";
                        return v + 'px';
                    }
                    else
                        return document.defaultView.getComputedStyle(elem, false)[css] || "";
                }
            }
        },
        abs: {
            point: function (e) {
                var x = e.offsetLeft, y = e.offsetTop;
                while (e = e.offsetParent) {
                    x += e.offsetLeft;
                    y += e.offsetTop;
                }
                return { 'x': x, 'y': y };
            },
            scroll: function () {
                return {
                    'left': document["documentElement"].scrollLeft + document["body"].scrollLeft,
                    'top': document["documentElement"].scrollTop + document["body"].scrollTop
                };
            }
        }
    };

    window[namespace] = wd.fn;
})('wd');

wd.animate = function (id) {
    var elem = (typeof id == "string") ? this.dom.id(id) : id, //对象
        _self = this,
        _this = {},
        f = 0, lazy = 10, lazyque = 10, // f动画计数器 lazy动画延迟 lazyque队列延迟
        tween = function (t, b, c, d) { return -c * (t /= d) * (t - 2) + b }, // 算子你可以改变他来让你的动画不一样
        adv = function (val, b) { // adv 用于+= -= *= /=操作
            var va, re = /^([+-\\*\/]=)([-]?[\d.]+)/;
            if (re.test(val)) {
                var reg = val.match(re);
                reg[2] = parseFloat(reg[2]);
                switch (reg[1]) {
                    case '+=':
                        va = reg[2];
                        break;
                    case '-=':
                        va = -reg[2];
                        break;
                    case '*=':
                        va = b * reg[2] - b;
                        break;
                    case '/=':
                        va = b / reg[2] - b;
                        break;
                }
                return va;
            }
            return parseFloat(val) - b;
        };
    // elem.animate 读取用于当前dom元素上的动画队列
    elem.animate = elem.animate || [];
    //stop 功能要使用的
    wd[id] = {};
    wd[id]['stop'] = true;
    // 统一队列入口 用于方便设置延迟，与停止
    _this.entrance = function (fn, ags, lazytime) {
        //fn 调用函数 ags 参数 lazytime 延迟时间
        setTimeout(function () {
            fn(ags[0], ags[1], ags[2]);
        }, (lazytime || 0));
    };
    // 停止动画 此方法还不能用
    _this.stop = function () {
        wd[id]['stop'] = false;
        elem.animate.length = 0;
        return _this;
    };
    // 队列操作
    _this.queue = function () {
        if (elem.animate && ++f == elem.animate[0].length) {
            f = 0; // 清空计数器
            elem.animate[0].callback ? elem.animate[0].callback.apply(elem) : false;
            // 判断是否有动画在等待执行
            if (elem.animate.length > 1) {
                elem.animate[0].callback = elem.animate[1].callback;
                elem.animate = elem.animate || [];
                elem.animate.shift(); // 清除刚执行完的动画队列
                var ea = elem.animate[0];
                // 循环播放队列动画
                for (var i = 0; i < ea.length; i++) {
                    _this.entrance(_this.execution, [ea[i][0], ea[i][1], ea[i][2]], lazyque);
                }
            }
            else
                elem.animate.length = 0; // 队列清楚
        }
    };
    //设置lazy方法，以后的队列动画延迟时间
    _this.delay = function (val) {
        lazyque = val;
        return _this;
    };
    //动画变化
    _this.execution = function (key, val, t) {
        var s = (new Date()).getTime(), d = t || 500,
                    b = parseFloat(_self.css(elem, key)) || 0,
                    c = adv(val, b), // adv用于设置高级操作比如 += -= 等等
                    un = val.match(/\d+(.+)/)[1]; // 单位

        (function () {
            var t = (new Date()).getTime() - s;
            if (t > d) {
                t = d;
                elem.style[key] = parseInt(tween(t, b, c, d)) + un;
                _this.queue(); // 操作队列
                return _this;
            }
            elem.style[key] = parseInt(tween(t, b, c, d)) + un;
            //wd[id]['stop'] && setTimeout(arguments.callee, lazy);
            setTimeout(arguments.callee, lazy);
        })();
    };
    // 入口
    _this.start = function (sty, t, fn) {
        // sty,t,fn 分别为 变化的参数key,val形式,动画用时,回调函数
        var len = elem.animate.length; // len查看动画队列长度
        elem.animate[len] = [];
        elem.animate[len].callback = fn;
        //多key 循环设置变化
        for (var i in sty) {
            elem.animate[len].push([i, sty[i], t]);
            if (len == 0) {
                _this.entrance(_this.execution, [i, sty[i], t], lazyque);
            }
        }
        return _this;
    };

    return _this;
};