/*
 *
 */

(function () {
    // Private array of chars to use
    var CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    Math.uuidFast = function () {
        var chars = CHARS, uuid = new Array(36), rnd = 0, r;
        for (var i = 0; i < 36; i++) {
            if (i == 8 || i == 13 || i == 18 || i == 23) {
                uuid[i] = '-';
            } else if (i == 14) {
                uuid[i] = '4';
            } else {
                if (rnd <= 0x02) rnd = 0x2000000 + (Math.random() * 0x1000000) | 0;
                r = rnd & 0xf;
                rnd = rnd >> 4;
                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
            }
        }
        return uuid.join('');
    };

    _jaq.track=function (page){}

    function setCookie(c_name, value, expireMinutes) {
        var exdate = new Date();
        exdate.setTime(exdate.getTime() + 60*1000*expireMinutes);
        document.cookie = c_name + "=" + escape(value) + ((expireMinutes == null) ? "" : ";expires=" + exdate.toGMTString());
    }

    function getCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    }



    function track(page) {
        var img = new Image();//
        img.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'rdp.jd.com/rest/ja?page=' + page + '&uid=boxer&acc=' + _UA + '&rnd=' + Math.random()+'&refer='+document.referrer;
    }

    var _UA = '';
    var cnf = _jaq.shift();
    while (cnf != null) {
        if (cnf instanceof Array && cnf.length > 1) {
            if (cnf[0] == '_setAccount') {
                _UA = cnf[1];
            }
        } else {
            track(cnf);
            var user='uv_'+_UA+'_'+ cnf;
            var num='nv_'+_UA+'_'+ cnf;
            var nuser='nu_'+_UA+'_'+ cnf;
            var uv = getCookie(user) || '';
            var nv = getCookie(num) || '';
            var nu = getCookie(nuser) || '';
            if (uv == null || uv == 'undefined' || uv == '') {
                uv = Math.uuidFast();
                setCookie(user, uv, 1440);
                track(cnf + "_UV");
            }
            if (nv == null || nv == 'undefined' || nv == '') {
                nv = Math.uuidFast();
                track(cnf + "_NV");
            }
            if (nu == null || nu == 'undefined' || nu == '') {
                nu = Math.uuidFast();
                setCookie(nuser, nu, 365*1440);
                track(cnf + "_NU");
            }
            setCookie(num, nv, 30);
        }
        cnf = _jaq.shift();
    }

})()