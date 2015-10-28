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
                    ele.attachEvent('on' + type, func); //ieϵ��ֱ�����ִ��
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
                    var parent = e.relatedTarget; //��һ��Ӧmouseover/mouseout�¼���Ԫ��
                    while (parent != this && parent) {//����������Ԫ�ز������Ԫ�ز�����Ŀ��Ԫ�أ�������mouseenter�¼���Ԫ�أ�
                        try {
                            parent = parent.parentNode;
                        } //��һ��Ӧ��Ԫ�ؿ�ʼ����Ѱ��Ŀ��Ԫ��
                        catch (e) {
                            break;
                        }
                    }
                    if (parent != this)//��mouseenterΪ���������Ҳ�����������ǰ�¼������㲻��Ŀ��Ԫ����
                        func(e); //����Ŀ�귽������������
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
    var elem = (typeof id == "string") ? this.dom.id(id) : id, //����
        _self = this,
        _this = {},
        f = 0, lazy = 10, lazyque = 10, // f���������� lazy�����ӳ� lazyque�����ӳ�
        tween = function (t, b, c, d) { return -c * (t /= d) * (t - 2) + b }, // ��������Ըı���������Ķ�����һ��
        adv = function (val, b) { // adv ����+= -= *= /=����
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
    // elem.animate ��ȡ���ڵ�ǰdomԪ���ϵĶ�������
    elem.animate = elem.animate || [];
    //stop ����Ҫʹ�õ�
    wd[id] = {};
    wd[id]['stop'] = true;
    // ͳһ������� ���ڷ��������ӳ٣���ֹͣ
    _this.entrance = function (fn, ags, lazytime) {
        //fn ���ú��� ags ���� lazytime �ӳ�ʱ��
        setTimeout(function () {
            fn(ags[0], ags[1], ags[2]);
        }, (lazytime || 0));
    };
    // ֹͣ���� �˷�����������
    _this.stop = function () {
        wd[id]['stop'] = false;
        elem.animate.length = 0;
        return _this;
    };
    // ���в���
    _this.queue = function () {
        if (elem.animate && ++f == elem.animate[0].length) {
            f = 0; // ��ռ�����
            elem.animate[0].callback ? elem.animate[0].callback.apply(elem) : false;
            // �ж��Ƿ��ж����ڵȴ�ִ��
            if (elem.animate.length > 1) {
                elem.animate[0].callback = elem.animate[1].callback;
                elem.animate = elem.animate || [];
                elem.animate.shift(); // �����ִ����Ķ�������
                var ea = elem.animate[0];
                // ѭ�����Ŷ��ж���
                for (var i = 0; i < ea.length; i++) {
                    _this.entrance(_this.execution, [ea[i][0], ea[i][1], ea[i][2]], lazyque);
                }
            }
            else
                elem.animate.length = 0; // �������
        }
    };
    //����lazy�������Ժ�Ķ��ж����ӳ�ʱ��
    _this.delay = function (val) {
        lazyque = val;
        return _this;
    };
    //�����仯
    _this.execution = function (key, val, t) {
        var s = (new Date()).getTime(), d = t || 500,
                    b = parseFloat(_self.css(elem, key)) || 0,
                    c = adv(val, b), // adv�������ø߼��������� += -= �ȵ�
                    un = val.match(/\d+(.+)/)[1]; // ��λ

        (function () {
            var t = (new Date()).getTime() - s;
            if (t > d) {
                t = d;
                elem.style[key] = parseInt(tween(t, b, c, d)) + un;
                _this.queue(); // ��������
                return _this;
            }
            elem.style[key] = parseInt(tween(t, b, c, d)) + un;
            //wd[id]['stop'] && setTimeout(arguments.callee, lazy);
            setTimeout(arguments.callee, lazy);
        })();
    };
    // ���
    _this.start = function (sty, t, fn) {
        // sty,t,fn �ֱ�Ϊ �仯�Ĳ���key,val��ʽ,������ʱ,�ص�����
        var len = elem.animate.length; // len�鿴�������г���
        elem.animate[len] = [];
        elem.animate[len].callback = fn;
        //��key ѭ�����ñ仯
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