/*******************************************************************************
* Copyright (C) Gasgoo Corporation. All rights reserved.
* 
* Author: Samuel.M.Xu
* Create Date: 2010-09-15
* Description: Gasgoo js framework
*          
* Revision History:
* Date         Author               Description
* 
*********************************************************************************/
/*手机号码验证*/
var g_phoneNumVerify = "^(1)[0-9]{10}$";

String.IsNullOrEmpty = function(v) {
    return !(typeof (v) === "string" && v.length != 0);
};

var __sp = String.prototype;

__sp.EncodeURI = function() {
    if (this == null || this == "") { return "" }
    return escape(this).replace(/\*/g, "%2A").replace(/\+/g, "%2B").replace(/-/g, "%2D").replace(/\./g, "%2E").replace(/\//g, "%2F").replace(/@/g, "%40").replace(/_/g, "%5F");
};

__sp.EncodeHtml = function() {
    if (this == null || this == "") { return "" }
    return this.replace(/\&/g, "&amp;").replace(/\>/g, "&gt;").replace(/\</g, "&lt;").replace(/\'/g, "&#039;").replace(/\"/g, "&quot;");
};

__sp.Trim = function() {
    return this.replace(/^\s+|\s+$/g, "")
};

__sp.TrimStart = function(v) {
    if ($String.IsNullOrEmpty(v)) {
        v = "\\s";
    };
    var re = new RegExp("^" + v + "*", "ig");
    return this.replace(re, "");
};

__sp.TrimEnd = function(v) {
    if ($String.IsNullOrEmpty(c)) {
        c = "\\s";
    };
    var re = new RegExp(c + "*$", "ig");
    return v.replace(re, "");
};

window.usingNamespace = function(a) {
    var ro = window;
    if (!(typeof (a) === "string" && a.length != 0)) {
        return ro;
    }
    var co = ro;
    var nsp = a.split(".");
    for (var i = 0; i < nsp.length; i++) {
        var cp = nsp[i];
        if (!ro[cp]) {
            ro[cp] = {};
        };
        co = ro = ro[cp];
    };

    return co;
};

usingNamespace("Web.Utils")["String"] = {
    IsNullOrEmpty: function(v) {
        return !(typeof (v) === "string" && v.length != 0);
    },
    IsEqual: function(str1, str2) {
        if (str1 == str2)
            return true;
        else
            return false;
    },
    IsInteger: function(str) {
        var re = new RegExp(/^(-|\+)?\d+$/);
        return re.test(str);
    }
};
var $String = Web.Utils.String;

usingNamespace("Web")["QueryString"] = {
    Get: function(key) {
        key = key.toLowerCase();
        var qs = Web.QueryString.Parse();
        var value = qs[key];
        return (value != null) ? value : "";
    },
    Set: function(key, value) {
        key = key.toLowerCase();
        var qs = Web.QueryString.Parse();
        qs[key] = value.EncodeURI();
        return Web.QueryString.ToString(qs);
    },
    Parse: function(qs) {
        var params = {};

        if (qs == null) qs = location.search.substring(1, location.search.length);
        if (qs.length == 0) return params;

        qs = qs.replace(/\+/g, ' ');
        var args = qs.split('&');
        for (var i = 0; i < args.length; i++) {
            var pair = args[i].split('=');
            var name = pair[0].toLowerCase();

            var value = (pair.length == 2)
				? pair[1]
				: name;
            params[name] = value;
        }

        return params;
    },
    ToString: function(qs) {
        if (qs == null) qs = Web.QueryString.Parse();

        var val = "";
        for (var k in qs) {
            if (val == "") val = "?";
            val = val + k + "=" + qs[k] + "&";
        }
        val = val.substring(0, val.length - 1);
        return val;
    }
};
var $QueryString = Web.QueryString;

usingNamespace("Web")["State"] = {
    Cookies: {
        Key: {
            OrderLogic: "OrderLogic"
        },
        Save: function(name, value, expires, secure) {

            var cv = "";
            if (typeof (value) == "string") {
                cv = value;
            } else if (typeof (value) == "object") {
                var jsonv = Web.State.Cookies.ToJson(Web.State.Cookies.GetValue(name));
                if (jsonv == false) jsonv = {};
                for (var k in value) {
                    eval("jsonv." + k + "=\"" + value[k] + "\"");
                }
                for (var k in jsonv) {
                    cv += k + '=' + escape(jsonv[k]) + '&';
                }
                cv = cv.substring(0, cv.length - 1);
            }

            var path, domain;
            try {
                if (null != websitePath) {
                    domain = websitePath.replace("http://www", "").replace("http://item", "").replace("http://p", "");
                    if (domain.indexOf(":") > 0) {
                        domain = domain.substring(0, domain.indexOf(":"));
                    }
                    var ad = $Converter.ToFloat(expires);
                    if (ad > 0) {
                        var d = new Date();
                        d.setTime(d.getTime() + ad * 1000);
                        expires = d;
                    };
                    path = '/';
                    secure = secure;
                }
            } catch (ex) { };

            var cok = name + "=" + cv +
			   ((expires) ? "; expires=" + expires.toGMTString() : "") +
			   ((path) ? "; path=" + path : "") +
				((domain) ? "; domain=" + domain : "") +
				((secure) ? "; secure" : "");


            document.cookie = cok;
        },
        Clear: function(n) {
            var domain, path, secure;
            try {
                var c;
                if (null != (c = Web.Config.CookieConfig[n])) {
                    domain = $WebsiteConfig.Domain;
                    path = c.Path;
                    secure = c.SecureOnly;
                };
            } catch (ex) { };

            document.cookie = n + "=" +
            ((path) ? ";path=" + path : "") +
            ((domain) ? ";domain=" + domain : "") +
            ";expires=Thu, 01-Jan-1900 00:00:01 GMT";
        },
        GetValue: function(n, k) {
            var reg = new RegExp("(^| )" + n + "=([^;]*)(;|$)");
            var arr = document.cookie.match(reg);
            if (arguments.length == 2) {
                if (arr != null) {
                    var kArr, kReg = new RegExp("(^| |&)" + k + "=([^&]*)(&|$)");
                    var c = arr[2];
                    var c = c ? c : document.cookie;
                    if (kArr = c.match(kReg)) {
                        return kArr[2];
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            } else if (arguments.length == 1) {
                if (arr != null) {
                    return unescape(arr[2]);
                } else {
                    return "";
                }
            }
        },
        ToJson: function(cv) {
            var cv = cv.replace(new RegExp("=", "gi"), ":'").replace(new RegExp("&", "gi"), "',").replace(new RegExp(";\\s", "gi"), "',");
            return eval("({" + cv + (cv.length > 0 ? "'" : "") + "})");
        },
        GetImgShowCokiee: function(i) {
            var cookie = $State.GetValue("autotip");
            if (cookie.length != 9) {
                cookie = "1_1_1_1_1";
            }
            var cookieArr = cookie.split("_");
            if (i < cookieArr.length) {
                if (cookieArr[i] == 1) {
                    return true;
                }
            }
            return false;
        },
        RegCancleCookie: function(k) {
            if (k == 5) {
                $(".brandTips").show();
            }
            else {
                if (k == 1) {
                    $(".layer" + k).addClass("layer1ImgLazyy").show();
                } else {
                    $(".layer" + k).show();
                }
            }
        },
        DefaultPageShowZhiNan: function(i) {
            if (i < 2) {
                $State.RegCancleCookie(i + 1);
            }
        },
        hideImgNewS: function(i) {
            $(".layer" + (i + 1)).hide();
            var cookie = $State.GetValue("autotip");
            if (cookie.length != 9) {
                cookie = "1_1_1_1_1";
            }

            var cookieArr = cookie.split("_");
            if (i < cookieArr.length) {
                if (cookieArr[i] == 1) {
                    cookieArr[i] = 0;
                    $State.Save("autotip", cookieArr.join("_"), 999999, false);
                }
            }
            if (i == 4) {
                $(".brandTips").hide();
            }
            if (i + 2 < 4)
                $State.RegCancleCookie(i + 2);
        }


    }
};
var $State = Web.State.Cookies;

usingNamespace("Web.Utils")["Converter"] = {
    ToFloat: function(v) {
        if (!v || (v.length == 0)) {
            return 0;
        };
        return parseFloat(v);
    }
};
var $Converter = Web.Utils.Converter;

usingNamespace("Web.UI")["Layer"] = {
    ID: 'showBox',
    IsMove: false,
    IsBack: false,
    IsFooter: true,
    Width: 'auto',
    Title: '',
    Content: '',
    CloseText: 'Close',
    IsConfirm: false,
    Confirm: {
        Text: 'Ok',
        Event: ''
    },
    IsAutoClose: false,
    CloseCallback: function() {

    },
    AutoTime: 3000,
    Iframe: {
        Height: ''
    },
    IsMyAutoClose: false,
    Open: function() {

        if ($Layer.IsBack && $("body>#layerBackground").length == 0) {
            $("body").prepend('<div id="layerBackground" class="setOpacity" style="height:' + $("body").height() + '"></div>');
        }

        var top = 0;
        var left = 0;
        if ($("#" + $Layer.ID).length == 0) {
            var confirmContent = ""; if ($Layer.IsConfirm) { confirmContent = "<input type='button' onclick='" + $Layer.Confirm.Event + "' value='" + $Layer.Confirm.Text + "' id='btnLayerEnter' class='saveA'/>"; }
            // window width
            var width = $Layer.Width; if (width != "auto") { width = width + "px"; }
            //Iframe add in 2011-1-13 code by Longcj <iframe scrolling="no" frameborder="0" style="z-index:-1;position:absolute;width:399px;height:253px;" allowtransparency="true"></iframe><div id="myAlpha"></div>
            $("body").prepend('<div id="' + $Layer.ID + '" style="display:none;width:' + width + ';" class="showBox"><!–[if lte IE 6.5]><iframe scrolling="no" name="ajaxiframeshu" frameborder="0" style="filter:alpha(opacity=0);z-index:-1;position:absolute;width:100%;height:600px;"></iframe><![endif]–><div id="myAlpha"></div></div>')

            //$("#" + $Layer.ID).css({ 'top': top + document.documentElement.scrollTop, 'left': );margin-left:438px;margin-top: 45px;

            var header = "";
            if ($Layer.Title != "")
                "<h1 id='layerTitle' style='-moz-user-select: none;'>" + $Layer.Title + "</h1><span class='close' onclick='$Layer.Close();' style='-moz-user-select: none;'>X</span>";
            var content = "<div class='baisebeijing'>" + $Layer.Content + "</div>";
            var footer = ""; if ($Layer.IsFooter) { footer = "<div style='background:#FFF;text-align:center;padding:10px 10px;'>" + confirmContent + "<input type='button' onclick='$Layer.Close();' value='" + $Layer.CloseText + "' id='btnLayerClose' class='saveA'/></div>"; }
            var element = document.documentElement;

            var _div = document.createElement("div");
            _div.innerHTML = header + content + footer;

            $("#" + $Layer.ID).append(_div.innerHTML);


            top = (element.clientHeight - $("#" + $Layer.ID).height()) / 2;
            if (top < 0) {
                top = 0;
            }
            left = (element.clientWidth - $("#" + $Layer.ID).width()) / 2;

            if ($.browser.msie && $.browser.version == '6.0') {
                top = top * 1.5
            }

            $("#" + $Layer.ID).css({ 'margin-top': top, 'left': left });
            $("#" + $Layer.ID).show();
            if ($Layer.IsMove) { $Layer.ObjectDragDrop(); }
        }
        else {
            $("#" + $Layer.ID).remove();
            $Layer.Open();
        }

        if ($Layer.IsAutoClose) {
            var autoClose = 0;
            $("#showBox").mouseover(function() {
                clearInterval(autoClose);
            });
            $("#showBox").mouseout(function() {
                autoClose = setTimeout('$Layer.Close()', $Layer.AutoTime);
            });
            autoClose = setTimeout('$Layer.Close()', $Layer.AutoTime);
        }
    },
    Iframe: function(src) {
        var height = $Layer.Iframe.Height;
        if (height != null && height != '') {
            height = "height:" + $Layer.Iframe.Height + "px;";
        }
        if (src.indexOf("?") > 0) {
            src = src + "&r=" + Math.random();
        }
        else {
            src = src + "?r=" + Math.random();
        }

        return "<iframe id='frame" + $Layer.ID + "' style='width:100%;" + height + "' frameborder='0' src='" + src + "'></iframe>";
    },
    Close: function() {
        $("#layerBackground").remove();
        $("#" + $Layer.ID).remove();
        if ($Layer.CloseCallback) {
            $Layer.CloseCallback();
        }
    },
    Reset: function() {
        $Layer.IsBack = false;
        $Layer.IsFooter = true;
        $Layer.Width = 'auto';
        $Layer.Title = '';
        $Layer.Content = '';
        $Layer.IsConfirm = false;
        $Layer.Event = '';
        $Layer.Iframe.Height = '';
    },
    ObjectDragDrop: function() {
        var top, left, move = false;
        $("#" + $Layer.ID + " h1").mousedown(function(e) {
            top = e.pageY - parseInt($("#" + $Layer.ID).css("top"));
            left = e.pageX - parseInt($("#" + $Layer.ID).css("left"));
            move = true;
        });
        $(document).mousemove(function(e) {
            if (move) {
                var x = e.pageX - left;
                var y = e.pageY - top;
                $("#" + $Layer.ID).css({ "left": x, "top": y });
            }
        }).mouseup(function() {
            move = false;
        })
    },
    ResetWidth: function(width) {
        $("#" + $Layer.ID).css("width", width);
        $("#" + $Layer.ID).css({ 'left': (document.documentElement.clientWidth - width) / 2 });
    },
    ResetHeight: function(height) {
        $("#" + $Layer.ID).css("height", height);
        $("#frame" + $Layer.ID).css("height", height - 52);
        var top = (document.documentElement.clientHeight - $("#" + $Layer.ID).height()) / 2;
        if (top < 0) {
            top = 0;
        }
        $("#" + $Layer.ID).css({ 'margin-top': top });
    }
};
var $Layer = Web.UI.Layer;

function lazyload(option) {
    var settings = {
        defObj: null,
        defHeight: 0
    };
    settings = $.extend(settings, option || {});
    var defHeight = settings.defHeight, defObj = (typeof settings.defObj == "object") ? settings.defObj.find("img") : $(settings.defObj).find("img");
    var pageTop = function() {
        return document.documentElement.clientHeight + Math.max(document.documentElement.scrollTop, document.body.scrollTop) - settings.defHeight;
    };
    var imgLoad = function() {
        defObj.each(function() {
            if ($(this).offset().top <= pageTop()) {
                var src2 = $(this).attr("src2");
                if (src2) {
                    $(this).attr("src", src2).removeAttr("src2");
                }
            }
        });
    };
    imgLoad();
    $(window).bind("scroll", function() {
        imgLoad();
    });
}

usingNamespace("Web.UI")["Common"] = {
    Watermark: function(id, value) {
        $("#" + id).val(value);
        $("#" + id).css("color", "#777");
        $("#" + id).blur(function() {
            if (this.value == '') { this.value = value; this.style.color = '#777'; $("#header .search").removeClass("in"); }
        }).focus(function() {
            if (this.value == value) { this.value = ''; this.style.color = '#000'; $("#header .search").addClass("in"); }
        });
    },
    showMessage: function(messageObj) {
        var html = messageObj.content || "",
            title = messageObj.title || "提示",
            width = parseInt(messageObj.width, 10) || 555;
        $Layer.Reset();
        $Layer.IsBack = true;
        $Layer.IsFooter = false;
        $Layer.Width = width;
        //$Layer.Height = parseInt(height, 10) || 555;
        $Layer.Title = title;
        $Layer.CloseText = "关 闭";
        $Layer.Content = html;
        $Layer.IsConfirm = true;
        $Layer.Confirm.Text = "确 定";
        $Layer.Open();
    }
};
$UICommon = Web.UI.Common;

usingNamespace("Web.UI")["Button"] = {
    Onclick: function(id, valueId, message, CompareValue) {
        $("#" + id).click(function() {
            var value = $.trim($("#" + valueId).val());
            if (value == "" || value == CompareValue) {
                alert(message);
            } else {
                location.href = websitePath + "/item/search.aspx?keyword=" + $("#" + valueId).val().replace(/\=/, "").replace(/\?/, "").replace(/\&/, "").EncodeURI();
            }
            return false;
        });
    },
    Keypress: function(id, message, CompareValue) {
        $("#" + id).keypress(function(event) {
            if (event.keyCode == 13) {
                var value = $.trim($(this).val());
                if (value == "" || value == CompareValue) {
                    alert(message);
                } else {
                    location.href = websitePath + "/item/search.aspx?keyword=" + $(this).val().replace(/\=/, "").replace(/\?/, "").replace(/\&/, "").EncodeURI();
                }
                return false;
            }
        });
    }
}
$UIButton = Web.UI.Button;
var UIMenutime;
usingNamespace("Web.UI")["Menu"] = {
    On: function(target) {
        var jqSubmenu = $("#" + target);
        jqSubmenu.find("li").hover(function() {
            var index = jqSubmenu.find("li").index(this);
            var jqTarget = jqSubmenu.find("li").eq(index);
            var jqContent = jqSubmenu.find("li").eq(index).find("div");
            jqTarget.addClass("mcur");
            jqContent.show();
        }, function() {
            var index = jqSubmenu.find("li").index(this);
            var jqTarget = jqSubmenu.find("li").eq(index);
            var jqContent = jqSubmenu.find("li").eq(index).find("div");
            jqTarget.removeClass("mcur");
            jqContent.hide();
        });
    },
    Hover: function(id, target) {
        var jqListmenu = $("#" + target);
        /*ie6下的iframe 处理*/
        var $part = $("#js-parts");
        $("#" + id).hover(function() {

            if ($.trim($("#listMenu").html()) == "") {
                //Ajax请求商品分类
                $.ajax({
                    type: "GET",
                    url: websitePath + "/handlers/special/categorynav.aspx",
                    cache: false,
                    success: function(data) {
                        $("#listMenu").html(data);
                        //loadAdvertbyID("100527,100528");
                        $UIMenu.showlistmenu(jqListmenu, $part);

                    }
                })
            } else {
                $UIMenu.showlistmenu(jqListmenu, $part);
            }
        }, function() {
            if (UIMenutime) { clearTimeout(UIMenutime); }
            jqListmenu.hide();
            jqListmenu.prev().hide();
            if ($part.length > 0) {
                $part.hide();
            };
            if ($(".div_nav").size() > 0) {
                $(".div_nav").removeClass("z10004");
            }
        });
    },
    showlistmenu: function(jqListmenu, $part) {
        UIMenutime = setTimeout(function() {
            jqListmenu.show();

            /*
            此行代码导致谷歌下导航被配件商城的下拉层撑下去了
            jqListmenu.prev().height(jqListmenu.height()).show();
            */
            if ($part.length > 0) {
                $part.show();
            };
            if ($(".div_nav").size() > 0) {
                $(".div_nav").addClass("z10004");
            }
        }, 300);
    },
    HoverEvent: function() {
        $(".vip").mousemove(function() {
            $(this).find("p").show();
        }).mouseleave(function() {
            $(this).find("p").hide();
        });
    },
    nav_CurMileagefocus: function() {
        var initialValue;
        var objInput;
        $("#nav_CurMileage").focus(function() {
            objInput = $(this);
            initialValue = objInput.val();
            $(".mainBox .tools .toolsBox .input").addClass("cur");
        }).blur(function() {
            var number = objInput.val();
            if (number != "") {
                var re = new RegExp('^[0-9]\\d*$');
                if (re.test(number) && number > 0) {
                    objInput.val(number);
                } else {
                    objInput.val(initialValue);
                }
                $(".mainBox .tools .toolsBox .input").removeClass("cur");
            }
        });
    },
    InintRoadYearsMonth: function() {
        var autoYear = $("#hidcurrentyear").val();
        if (autoYear > 0) {
            $UIMenu.RoadYears(autoYear);
            //$("#nav-txt-roadyears").html(autoYear + "年");
        } else {
            // 如果未选车型，开始时间为当前时间减30年
            var myDate = new Date();
            autoYear = myDate.getFullYear() - 30;
            $UIMenu.RoadYears(autoYear);
        }
        if ($("#hid_IsBack").val() == "True") {
            $UIMenu.RoadMonth($("#nav-txt-roadyears").attr("name"));
            //$("#nav-txt-roadmonth").html($("#nav-txt-roadmonth").attr("name") + "月");
        }
    },
    PackageHover: function(id, target) {
        // 如果是首页则不需要弹出
        var hidIsHome = document.getElementById("hidIsHome")
        if (hidIsHome && hidIsHome.value == "1") {
            return;
        }
        var jqListmenu = $("#" + target);
        var time;
        $("#" + id).hover(function() {
            var navcurrentautomodelsubid = $("#hid_nav_hidcurrentautomodelsubid").val();
            var currentautomodelsubid = $("#hidcurrentautomodelsubid").val();
            if ($.trim(jqListmenu.html()) == "" || navcurrentautomodelsubid != currentautomodelsubid) {
                $.ajax({
                    type: "GET",
                    url: websitePath + "/handlers/special/baoyangnav.aspx",
                    cache: false,
                    success: function(data) {
                        jqListmenu.html(data);
                        $UIMenu.nav_CurMileagefocus();
                        $UIMenu.InintRoadYearsMonth();
                        $MyAutoModel.AddEvent();
                        $UIMenu.InintpsearchBut();
                    }
                })
            }
            time = setTimeout(function() { jqListmenu.show(); }, 100);
        }, function() {
            clearTimeout(time);
            jqListmenu.hide();
        });
    },
    //新车上路时间(月份)
    RoadMonth: function(id) {

        if (id > 0) {
            //            $.ajax({
            //                type: "GET",
            //                url: websitePath + "/handlers/maintenance/roadtime.ashx?action=roadmonth&parentId=" + id,
            //                data: "",
            //                cache: false,
            //                success: function(data) {
            //                    $MyAutoModel.DataBind("nav-roadmonth", "nav-txt-roadmonth", data);
            //                },
            //                error: function(e) { }
            //            });
            $("#divIndexDiyMaintenanceMonth").slideSelect({ url: websitePath + "/handlers/maintenance/roadtime.ashx?action=roadmonth&parentId=" + id, callback: function() {
                $("#nav-txt-roadmonth").attr("name", arguments[0]);
                $("#divIndexDiyMaintenanceMonth").find("b").html(arguments[1]);
            }
            });
        }
    },
    //新车上路时间(年份)
    RoadYears: function(id) {
        if (id > 0) {
            //            $.ajax({
            //                type: "GET",
            //                url: websitePath + "/handlers/maintenance/roadtime.ashx?action=roadyears&parentId=" + id,
            //                data: "",
            //                cache: false,
            //                success: function(data) {
            //                    var autoMessage = ["", "", "", "", "月份"];
            //                    $MyAutoModel.DataBind("nav-roadyears", "nav-txt-roadyears", data, autoMessage, $UIMenu.RoadMonth);
            //                },
            //                error: function(e) { }
            //            });

            $("#divIndexDiyMaintenanceYear").slideSelect({ url: "/handlers/maintenance/roadtime.ashx?action=roadyears&parentId=" + id, callback: function() {
                var autoMessage = ["", "", "", "", "月份"];
                $("#nav-txt-roadyears").attr("name", arguments[0]);
                $("#nav-txt-roadmonth").attr("name", 0).html("月份");
                //alert(arguments[0] + '::' + arguments[1]);
                ReloadMonth(arguments[0]);
            }
            });
            var selectYear = $("#nav-txt-roadyears").attr("name");
            if (selectYear > 0) {
                $("#nav-txt-roadyears").html($("#nav-txt-roadyears").attr("name"));
            }

        }
    },
    // 导航自助保养按钮事件
    InintpsearchBut: function() {
        $("#nav_btnstartbaoyang").unbind("click").click(function() {
            var curMileage = $("#nav_CurMileage");

            if ($.trim(curMileage.val()).length == 0) {
                $UIMenu.UnBindBaoyangnavHover();
                alert("请输入当前行驶里程！");
                $UIMenu.BindBaoyangnavHover();
                return;
            }
            else {
                var reg = new RegExp("^[0-9]+$");
                if (!reg.test($.trim(curMileage.val()))) {
                    $UIMenu.UnBindBaoyangnavHover();
                    alert("您输入的里程格式不正确！");
                    $UIMenu.BindBaoyangnavHover();
                    return;
                }
                if (parseInt(curMileage.val()) < 1) {
                    $UIMenu.UnBindBaoyangnavHover();
                    alert("您输入的里程范围太小！");
                    $UIMenu.BindBaoyangnavHover();
                    return;
                }
                if (parseInt(curMileage.val()) > 9999999) {
                    $UIMenu.UnBindBaoyangnavHover();
                    alert("您输入的里程超出了范围！");
                    $UIMenu.BindBaoyangnavHover();
                    return;
                }
            }

            var year = $("#nav-txt-roadyears");
            var month = $("#nav-txt-roadmonth");
            var firstTime;
            if ($.trim(year.html()) == "年份" || $.trim(month.html()) == "月份") {
                $UIMenu.UnBindBaoyangnavHover();
                alert("请选择新车上路时间！");
                $UIMenu.BindBaoyangnavHover();
                return;
            }
            if (parseInt(month.attr("name")) < 10) {
                firstTime = year.attr("name") + "0" + month.attr("name");
            }
            else {
                firstTime = year.attr("name") + month.attr("name");


            }

            if ($State.GetValue("MyCurrentAutoModel") == "nothing" || $State.GetValue("MyCurrentAutoModel") == "") {

                $MyAutoModel.showCarChooseLayer();
                $MyAutoModel.CurrentAutoModelChange = function() {

                    var runy = year.attr("name"); //新车上路
                    var proy = $("#hidcurrentyear").val(); //生产年份
                    //生产年份不能大于新车上路年份
                    if (runy < proy) {
                        alert("生产年份与新车上路年份不符！");
                        ReloadYear(proy, proy);
                        return false;
                    }

                    if (saveCurMileageCookie) {
                        saveCurMileageCookie("savecurmileage");
                    }
                    setTimeout(function() {
                        $UIMenu.UpdateCurrentAutoModelParam("101_" + firstTime, curMileage.val(), firstTime);
                    }, 50);
                }
                //                $UIMenu.Choose(
                //                        function() {
                //                    $("#btn_Determine,#btn_chooseotherauto").unbind("click").click(function() {
                //                        if ($.trim($("#txt-startyear").html()) == "生产年份(与上路年份未必相同)") {
                //                            alert("请您先选择车型！");
                //                            return;
                //                        }
                //                        var amsid = $("#txt-automodelsub").attr("name");
                //                        var syear = $("#txt-startyear").attr("name");
                //                        $MyAutoModel.CurrentAutoModelChange = function() {

                //                            $UIMenu.SetAutoModel(amsid, syear, function() {
                //                                $UIMenu.UpdateCurrentAutoModelParam("101_" + firstTime, curMileage.val(), firstTime);
                //                            });
                //                        }
                //                        $MyAutoModel.SetCurrentAutoModel(0, 0, amsid, syear);
                //                    })

                //                    $("#ul_MyCarList li").unbind("click").click(function() {
                //                        $UIMenu.ChooseMyGarage(this);
                //                    })

                //                    $("#btn_ChooseMyGarageOk").click(function() {
                //                        $UIMenu.ChooseMyGarageOk();
                //                        $MyAutoModel.CurrentAutoModelChange = function() {

                //                            var obj = $($("#ul_MyCarList").find(".listcur"));
                //                            var objId = obj.attr("id");
                //                            var objName = obj.attr("name");
                //                            $UIMenu.SetAutoModel(objId, objName, function() {
                //                                $UIMenu.UpdateCurrentAutoModelParam("101_" + firstTime, curMileage.val(), firstTime);
                //                            });
                //                        }
                //                    })
                //                }
                //                    );

            } else {
                if (saveCurMileageCookie) {
                    saveCurMileageCookie("savecurmileage");
                }
                setTimeout(function() {

                    $UIMenu.UpdateCurrentAutoModelParam("101_" + firstTime, curMileage.val(), firstTime);
                }, 50);
            }
        })
    },






    UpdateCurrentAutoModelParam: function(param, curMileage, firstTime) {
        $.ajax({
            type: "GET",
            url: "/handlers/userautomodel/updateautomodelparam.ashx",
            cache: false,
            data: { param: param },
            success: function(data) {
                var maintenanceStep2 = $("#hid_nav_maintenanceStep2").val();
                window.location.href = maintenanceStep2 + "?curmileage=" + curMileage
                                             + "&firsttime=" + firstTime + "&src=nav";
                //                window.open(maintenanceStep2 + "?curmileage=" + curMileage
                //                                             + "&firsttime=" + firstTime, '_blank', '');
            },
            error: function(e) { }
        });
    },
    //自助保养第一步：选择、更换车型
    Choose: function(callback) {
        $.ajax({
            type: "Get",
            url: websitePath + "/handlers/maintenance/mymaintenmodels.aspx",
            data: "",
            cache: false,
            success: function(data) {
                var html = data;
                $Layer.Reset();
                $Layer.IsBack = true;
                $Layer.IsFooter = false;
                $Layer.Width = 680;
                $Layer.Title = "";
                $Layer.CloseText = "关 闭";
                $Layer.Content = html;
                $Layer.IsConfirm = true;
                $Layer.Confirm.Text = "确 定";
                $Layer.Open();
                $UIMenu.Brand();
                $UIMenu.Cars($("#txt-autobrand").attr("name"));
                $UIMenu.VehicleEmissions($("#txt-automodel").attr("name"));
                $UIMenu.Startyear($("#txt-automodelsub").attr("name"));
                $MyAutoModel.AddEvent();
                if (typeof keyDocumentPosition == "function") {
                    var o = [{ "show": $("#myam_autobrand"), "scorll": $("#myam_autobrand").find("ul")[0], "height": $("#myam_autobrand li").first().height() },
                    { "show": $("#autobrand"), "scorll": $("#autobrand").find("ul")[0], "height": $("#autobrand li").first().height()}];
                    keyDocumentPosition(o);
                }
                if (callback) {
                    callback();
                }
            },
            error: function(e) { }
        });
    },
    Brand: function() {
        $.ajax({
            type: "Get",
            url: websitePath + "/handlers/auto/autobrand.ashx",
            data: "",
            cache: false,
            success: function(data) {
                var autoMessage = ["车系", "排量", "生产年份(与上路年份未必相同)", "开始年份", "开始月份"];
                $MyAutoModel.DataBind("autobrand", "txt-autobrand", data, autoMessage, $UIMenu.Cars);
            },
            error: function(e) { }
        });
    },
    Cars: function(id) {
        if (id > 0) {
            $.ajax({
                type: "GET",
                url: websitePath + "/handlers/auto/automodel.ashx?parentId=" + id,
                data: "",
                cache: false,
                success: function(data) {
                    var autoMessage = ["", "排量", "生产年份(与上路年份未必相同)", "开始年份", "开始月份"];
                    $MyAutoModel.DataBind("automodel", "txt-automodel", data, autoMessage, $UIMenu.VehicleEmissions);
                },
                error: function(e) { }
            });
        }
    },
    VehicleEmissions: function(id) {
        if (id > 0) {
            $.ajax({
                type: "GET",
                url: websitePath + "/handlers/auto/automodelsub.ashx?parentId=" + id,
                data: "",
                cache: false,
                success: function(data) {
                    var autoMessage = ["", "", "生产年份(与上路年份未必相同)", "开始年份", "开始月份"];
                    $MyAutoModel.DataBind("automodelsub", "txt-automodelsub", data, autoMessage, $UIMenu.Startyear);
                },
                error: function(e) { }
            });
        }
    },
    Startyear: function(id) {
        if (id > 0) {
            $.ajax({
                type: "GET",
                url: websitePath + "/handlers/auto/autoyears.ashx?parentid=" + id,
                data: "",
                cache: false,
                success: function(data) {

                    $MyAutoModel.DataBind("startyear", "txt-startyear", data);
                },
                error: function(e) { }
            });
        }
    },
    // 设置自助保养车型
    SetAutoModel: function(amsid, syear, callback) {
        $.ajax({
            type: "GET",
            cache: false,
            url: '/handlers/maintenance/maintenanceprovider.ashx?action=setautomodel&amsid=' + amsid + '&syear=' + syear,
            success: function(data) {
                var errmsg = data.split('_');
                switch (errmsg[0]) {
                    case "success":
                        if (callback) callback();
                        break;
                    case "fail":
                        alert(errmsg[1]);
                        break;
                }
            }
        });
    },
    BindBaoyangnavHover: function() {
        //$UIMenu.PackageHover("baoyangnav", "packlist_nav");
    }
    ,
    UnBindBaoyangnavHover: function() {
        $("#baoyangnav").unbind("mouseenter").unbind("mouseleave");
    },
    //自助保养第一步：选择我的车库
    ChooseMyGarage: function(obj) {
        $("#ul_MyCarList").find("li").removeClass("listcur");
        $(obj).addClass("listcur");
    },
    //自助保养第一步：确定选择我的车库
    ChooseMyGarageOk: function() {
        var obj = $($("#ul_MyCarList").find(".listcur"));
        var objId = obj.attr("id");
        var objName = obj.attr("name");
        if (objId == undefined || objName == undefined) {
            alert("请您先选择车型！");
            return;
        }
        $MyAutoModel.SetCurrentAutoModel(0, 0, objId, objName);
    }
}
var $UIMenu = Web.UI.Menu;



usingNamespace("Web.UI")["Message"] = {
    Show: function(message, width, type, callback, yesTxt, noTxt) {
        var html = "";
        yesTxt = yesTxt || "确定";
        noTxt = noTxt || "取消";
        html += '<div id="layer"><div class="smalltips"><div class="close"><a href="javascript:;" >x</a></div><div class="c"></div>';
        html += '<p>' + message + '</p>';
        if (type == 1) {
            //html += '<span class="tipscancel"><a href="javascript:;" class="close" >关闭</a></span>';

        } else {
            html += '<span class="tipsok"><a href="javascript:;" onclick=\'' + callback + '\'>' + yesTxt + '</a></span><span class="tipscancel"><a href="javascript:;" onclick="$Layer.Close();" >' + noTxt + '</a></span>';
        }
        html += '<div class="c"></div></div></div>';

        $Layer.Reset();
        $Layer.IsBack = true;
        $Layer.IsFooter = false;
        $Layer.Width = width;
        $Layer.Title = "";
        $Layer.CloseText = "关 闭";
        $Layer.Content = html;
        $Layer.IsConfirm = true;
        $Layer.Confirm.Text = "确 定";
        $Layer.Open();
        if (type == 1) {
            $(".close").click(function() {
                $Layer.Close();
                if (callback) callback();
                return false;
            })
        } else {
            $(".close").click(function() {
                $Layer.Close();
                return false;
            })
        }
    }
}
var $UIMessage = Web.UI.Message;


usingNamespace("Web.UI")["ShoppingCart"] = function() {
    this.url = websitePath + "/shopping/handlers/shoppingcartprovider.ashx";
}
Web.UI.ShoppingCart.prototype =
{
    AddOneToCartNoMsg: function(itemid, callback) {
        this.AddToCart(itemid, 1, callback, callback);
    },
    AddToCart: function(itemid, count, success, fail) {
        var flag = false;
        if (itemid == "" || parseInt(count) < 1)
        { return; }
        var me = this;
        $.ajax({
            type: "GET",
            url: me.url,
            async: false,
            cache: false,
            data: { action: 'addcount', itemid: itemid, count: count },
            success: function(data) {
                if (data == 'success') {
                    flag = true;
                    if (success) success();

                }
                else if (fail) {
                    fail(data);
                }

            },
            error: function(e) { }
        });
        return flag;
    },
    NewsAddToCart: function(itemids, callback) {
        $.ajax({
            type: "GET",
            cache: false,
            url: '/shopping/handlers/shoppingcartprovider.ashx?action=additemstocarts&itemids=' + itemids,
            success: function(html) {
                window.location.href = resourcepath;
            }
        });
    },

    //添加保养套装到购物车
    AddPackageToCart: function(packageId, autoModelSubId, startYear, count, success, fail) {
        var flag = false;
        if (parseInt(packageId) < 1 || parseInt(count) < 1)
        { return; }
        var me = this;
        $.ajax({
            type: "GET",
            url: me.url,
            async: false,
            cache: false,
            data: { action: 'addpackagecount', itemid: packageId, amsid: autoModelSubId, syear: startYear, count: count },
            success: function(data) {
                if (data == 'success') {
                    flag = true;
                    if (success) success();
                }
                else if (fail) {
                    fail(data);
                }
            },
            error: function(e) { }
        });
        return flag;
    },
    //添加套装推荐商品到购物车
    AddPackageRecommendToCart: function(itemids, success, fail) {
        var flag = false;
        var me = this;
        $.ajax({
            type: "GET",
            url: me.url,
            async: false,
            cache: false,
            data: { action: 'addcounts', itemids: itemids },
            success: function(data) {
                if (data == 'success') {
                    flag = true;
                    if (success) success();

                }
                else if (fail) {
                    fail(data);
                }

            },
            error: function(e) { }
        });
        return flag;
    },
    BindTxtEvent: function(obj, itemid, maxVal) {
        var me = this;
        var initialValue;
        $(obj).focus(function() {
            initialValue = $(this).val();
        }).blur(function() {
            var re = new RegExp('^[1-9]\\d*$');
            var value = $(this).val();
            if (value > maxVal) {
                $UIMessage.Show("您选择的数量已超出最大购买量", 306, 1);
                $(this).val(initialValue);
            }
            if (!re.test(value)) {
                $(this).val(initialValue);
            }
        });
    },
    SetTotalCount: function() {
        var me = this;
        $.ajax({
            type: "GET",
            url: me.url,
            cache: false,
            data: { action: 'totalcount' },
            success: function(data) {
                if (/\d+/.test(data)) {
                    $("#shoppingCartCount").html(data);
                    if (data == 0) {
                        $("#js_navRights").unbind("mouseenter").unbind("mouseleave");
                    }
                }
            },
            error: function(e) { }
        });
    }

}
Web.UI.ShoppingCart.GetSingleCart = function() {
    var singleton;
    return function() {
        if (!singleton)
            singleton = new $UIShoppingCart();
        return singleton;
    }
} ();
var $UIShoppingCart = Web.UI.ShoppingCart;



//
//ANIMATE4COLOR juqery动画颜色动画支持
//
(function(d) {
    d.each(["backgroundColor", "borderColor", "borderBottomColor", "borderLeftColor", "borderRightColor", "borderTopColor", "color", "outlineColor"], function(f, e) {
        d.fx.step[e] = function(g) {
            if (!g.colorInit) {
                g.start = c(g.elem, e);
                g.end = b(g.end);
                g.colorInit = true
            }
            g.elem.style[e] = "rgb(" + [Math.max(Math.min(parseInt((g.pos * (g.end[0] - g.start[0])) + g.start[0]), 255), 0), Math.max(Math.min(parseInt((g.pos * (g.end[1] - g.start[1])) + g.start[1]), 255), 0), Math.max(Math.min(parseInt((g.pos * (g.end[2] - g.start[2])) + g.start[2]), 255), 0)].join(",") + ")"
        }
    });

    function b(f) {
        var e;
        if (f && f.constructor == Array && f.length == 3) {
            return f
        }
        if (e = /rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(f)) {
            return [parseInt(e[1]), parseInt(e[2]), parseInt(e[3])]
        }
        if (e = /rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)/.exec(f)) {
            return [parseFloat(e[1]) * 2.55, parseFloat(e[2]) * 2.55, parseFloat(e[3]) * 2.55]
        }
        if (e = /#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(f)) {
            return [parseInt(e[1], 16), parseInt(e[2], 16), parseInt(e[3], 16)]
        }
        if (e = /#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])/.exec(f)) {
            return [parseInt(e[1] + e[1], 16), parseInt(e[2] + e[2], 16), parseInt(e[3] + e[3], 16)]
        }
        if (e = /rgba\(0, 0, 0, 0\)/.exec(f)) {
            return a.transparent
        }
        return a[d.trim(f).toLowerCase()]
    }
    function c(g, e) {
        var f;
        do {
            f = d.css(g, e);
            if (f != "" && f != "transparent" || d.nodeName(g, "body")) {
                break
            }
            e = "backgroundColor"
        } while (g = g.parentNode);
        return b(f)
    }
    var a = {
        aqua: [0, 255, 255],
        azure: [240, 255, 255],
        beige: [245, 245, 220],
        black: [0, 0, 0],
        blue: [0, 0, 255],
        brown: [165, 42, 42],
        cyan: [0, 255, 255],
        darkblue: [0, 0, 139],
        darkcyan: [0, 139, 139],
        darkgrey: [169, 169, 169],
        darkgreen: [0, 100, 0],
        darkkhaki: [189, 183, 107],
        darkmagenta: [139, 0, 139],
        darkolivegreen: [85, 107, 47],
        darkorange: [255, 140, 0],
        darkorchid: [153, 50, 204],
        darkred: [139, 0, 0],
        darksalmon: [233, 150, 122],
        darkviolet: [148, 0, 211],
        fuchsia: [255, 0, 255],
        gold: [255, 215, 0],
        green: [0, 128, 0],
        indigo: [75, 0, 130],
        khaki: [240, 230, 140],
        lightblue: [173, 216, 230],
        lightcyan: [224, 255, 255],
        lightgreen: [144, 238, 144],
        lightgrey: [211, 211, 211],
        lightpink: [255, 182, 193],
        lightyellow: [255, 255, 224],
        lime: [0, 255, 0],
        magenta: [255, 0, 255],
        maroon: [128, 0, 0],
        navy: [0, 0, 128],
        olive: [128, 128, 0],
        orange: [255, 165, 0],
        pink: [255, 192, 203],
        purple: [128, 0, 128],
        violet: [128, 0, 128],
        red: [255, 0, 0],
        silver: [192, 192, 192],
        white: [255, 255, 255],
        yellow: [255, 255, 0],
        transparent: [255, 255, 255]
    }
})(jQuery);
//闪烁动画函数
var animChange = function(cnum, mxnum) {
    ++cnum;
    var color = cnum % 2 == 1 ? "#e60012" : "#cfcfcf";
    var fontcolor = cnum % 2 == 1 ? "#FFED00" : "#fff";
    var bgcolor = cnum % 2 == 1 ? "#e60012" : "##c10208";
    $(".toolsBox .buttonA").animate({ "backgroundColor": bgcolor }, 150);
    $(".toolsBox .buttonA").animate({ "color": fontcolor }, 150);
    $(".toolsBox .year").animate({ "borderColor": color }, 300);
    $(".toolsBox .month").animate({ "borderColor": color }, 300);
    $(".toolsBox .input").animate({ "borderColor": color }, 300, function() {
        if (cnum >= mxnum) {
            $(".toolsBox .year").removeAttr("style");
            $(".toolsBox .month").removeAttr("style");
            $(".toolsBox .input").removeAttr("style");
            $(".toolsBox .buttonA").removeAttr("style");
            return false;
        }
        else
            animChange(cnum, mxnum);
    });
}

//我的车库开始

usingNamespace("Web.UI")["MyAutoModel"] = {
    IsFirstRequest: 0,
    CurrentIndex: 0,
    HaveChild: 0,   //页面是否有子控件
    MyamLocation: "myam_mainpanel", //弹出窗口所在容器
    CurrentChangeIsUpdate: true,     //当前车型改变时，是否需要重新加载控件？
    CurrentAutoModelChange: function() { },
    CurrentAutoModelIds: [0, 0, 0, 0],
    IsBaoChun: false,
    IsIframe: false, //是否是Iframe控件
    Inint: function() {
        if ($MyAutoModel.IsFirstRequest == 1) {
            $MyAutoModel.CurrentAutoModelIds[0] = document.getElementById("hidcurrentautobrandid").value;
            $MyAutoModel.CurrentAutoModelIds[1] = document.getElementById("hidcurrentautomodelid").value;
            $MyAutoModel.CurrentAutoModelIds[2] = document.getElementById("hidcurrentautomodelsubid").value;
            $MyAutoModel.CurrentAutoModelIds[3] = document.getElementById("hidcurrentyear").value;
            $MyAutoModel.BindAutoBrand();
        }

        $MyAutoModel.AddEvent();
    },
    EventObj: {
        EventObjTxtId: ["txt_myam_autobrand", "txt_myam_automodel", "txt_myam_parentnav", "txt_myam_childnav", "nav-txt-roadyears", "nav-txt-roadmonth", "txt-autobrand", "txt-automodel", "txt-automodelsub", "txt-startyear"],
        EventObjId: ["arrow_myam_autobrand", "arrow_myam_automodel", "arrow_myam_parentnav", "arrow_myam_childnav", "nav-arrow-roadyears", "nav-arrow-roadmonth", "arrow-autobrand", "arrow-automodel", "arrow-automodelsub", "arrow-startyear"],
        EventTargetObjId: ["myam_autobrand", "myam_automodel", "myam_parentnav", "myam_childnav", "nav-roadyears", "nav-roadmonth", "autobrand", "automodel", "automodelsub", "startyear"],
        EventBoxId: ["myam_mainpanel", "myam_mycartype", "myam_panelschoosecar", "myam_addcar"]
    },
    ClearData: function(txtId, autoMessage) {
        var index = $MyAutoModel.Index(txtId);
        for (; index < 5; index++) {
            if (document.getElementById($MyAutoModel.EventObj.EventObjTxtId[index + 1])
            && document.getElementById($MyAutoModel.EventObj.EventTargetObjId[index + 1])) {
                $("#" + $MyAutoModel.EventObj.EventObjTxtId[index + 1]).html(autoMessage[index]).attr("name", "0");
                $("#" + $MyAutoModel.EventObj.EventTargetObjId[index + 1]).find("ul").empty();
            }
        }
    },
    DataBind: function(targetId, txtId, data, autoMessage, callback) {
        var me = this;
        var html = "";

        var jqDataSources = $("#" + targetId).find("ul");
        if (data) {
            if (targetId == "myam_childnav") {
                if (typeof isNSShowImg == "function" && $("#myam_childnav").css("display") == "block")
                    isNSShowImg();
                for (var i = 0, len = data.length; i < len; i++) {
                    var classname = "";
                    if (i == 1) {
                        classname = "def";
                    }
                    html += "<li><div class='caricon'><a class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a><a title='如何确定生产年份' class='" + classname + " eicon' href='" + websitePath + "/guide/production-year.html#autoyear' target='_blank'></a></div></li>";
                    if ($MyAutoModel.CurrentAutoModelIds[3] == data[i].Id && data[i].Id > 0) {
                        $("#" + txtId).html(data[i].Id + "年生产").attr("name", data[i].Id);
                    }
                }
            }
            else {
                for (var i = 0, len = data.length; i < len; i++) {
                    if (targetId == "myam_automodel") {
                        var icostr = "";
                        if (data[i].DefaultPicture != "" && data[i].DefaultPicture != null && data[i].DefaultPicture != undefined) {
                            icostr = "<a title='点击查看车型图' class='icon' target='_blank' href='" + websitePath + "/automodel/automodelpic.aspx?amid=" + data[i].Id + "' name='" + data[i].Id + "'></a>";
                        }
                        html += "<li><div class='caricon'>" + icostr + "<a class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a></div></li>";
                    } else if (targetId == "myam_parentnav") {
                        var classname = "";
                        if (i == 1) {
                            classname = "def";
                        }
                        html += "<li><div class='caricon'><a class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a></div></li>";
                    }
                    else {
                        html += "<li><a class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a></li>";
                    }

                    if (data[i].Id > 0 && ($MyAutoModel.CurrentAutoModelIds[0] == data[i].Id || $MyAutoModel.CurrentAutoModelIds[1] == data[i].Id || $MyAutoModel.CurrentAutoModelIds[2] == data[i].Id)) {
                        $("#" + txtId).html(data[i].Name).attr("name", data[i].Id);
                        callback(data[i].Id);
                    }
                }
            }
            jqDataSources.html(html);
            if (targetId == "myam_childnav" && (typeof imgAinimate == "function")) {
                imgAinimate();
            }
            jqDataSources.find("li a.autoitem").unbind("click").click(function(event) {
                var seletValue = $(this).html();
                var itemID = $(this).attr("name");
                var index = $MyAutoModel.Index(txtId);
                $("#" + txtId).html(seletValue).attr("name", itemID);
                if (callback) {
                    if (targetId == "myam_parentnav") {
                        if (typeof isNSShowImg == "function")
                            isNSShowImg();
                    }
                    $MyAutoModel.ClearData(txtId, autoMessage);
                    callback(itemID, seletValue);
                    $("#" + $MyAutoModel.EventObj.EventTargetObjId[index]).hide();
                    if (itemID != "0") {
                        $("#" + $MyAutoModel.EventObj.EventTargetObjId[index + 1]).show();
                        $MyAutoModel.CurrentIndex = index + 1;
                        event.stopPropagation();
                    }

                } else {
                    $("#" + $MyAutoModel.EventObj.EventTargetObjId[index]).hide();

                }
                //$MyAutoModel.ControlSecondLayer();
            });
            jqDataSources.find("li .caricon").hover(
                function() {
                    if ($(this).find(".autoitem").attr("name") > 0) {
                        jqDataSources.find("li .caricon .def").removeClass("def");
                        $(this).find(".eicon").addClass("def");
                    }
                },
                function() {

                }
            );
        }
    },
    OpenAutoModelPic: function(AutoModelId) {
        $.ajax({
            type: "Get",
            url: "/handlers/auto/showautomodelpic.aspx?amid=" + AutoModelId,
            data: "",
            success: function(data) {
                //$Layer.Reset();
                $Layer.IsBack = true;
                $Layer.IsFooter = false;
                $Layer.Width = 680;
                $Layer.Title = "";
                $Layer.CloseText = "关 闭";
                $Layer.Content = data;
                $Layer.IsConfirm = true;
                $Layer.Confirm.Text = "确 定";
                $Layer.Open();
            },
            error: function(e) { }
        });
    },
    BindAutoBrand: function() {
        $.ajax({
            type: "Get",
            url: "/handlers/auto/autobrand.ashx",
            data: "",
            success: function(data) {
                var autoMessage = ["车系", "排量", "生产年份"];
                $MyAutoModel.DataBind("myam_autobrand", "txt_myam_autobrand", data, autoMessage, $MyAutoModel.BindAutoModel);
            },
            error: function(e) { }
        });
    },
    BindAutoModel: function(id) {
        if (id > 0) {
            $.ajax({
                type: "GET",
                url: "/handlers/auto/automodel.ashx?parentId=" + id,
                data: "",
                success: function(data) {
                    var autoMessage = ["", "排量", "生产年份"];
                    $MyAutoModel.DataBind("myam_automodel", "txt_myam_automodel", data, autoMessage, $MyAutoModel.BindAutoModelSub);
                },
                error: function(e) { }
            });
        }
    },
    BindAutoModelSub: function(id) {
        if (id > 0) {
            $.ajax({
                type: "GET",
                url: "/handlers/auto/automodelsub.ashx?parentId=" + id,
                data: "",
                success: function(data) {
                    var autoMessage = ["", "", "生产年份(与上路年份未必相同)"];
                    $MyAutoModel.DataBind("myam_parentnav", "txt_myam_parentnav", data, autoMessage, $MyAutoModel.BindYear);
                },
                error: function(e) { }
            });
        }
    },
    BindYear: function(id) {
        if (id > 0) {
            $.ajax({
                type: "GET",
                url: "/handlers/auto/autoyears.ashx?parentid=" + id,
                data: "",
                success: function(data) {

                    $MyAutoModel.DataBind("myam_childnav", "txt_myam_childnav", data);
                },
                error: function(e) { }
            });
        }
    },
    Status: function(jqListItem) {
        if (jqListItem.find("li").size()) {
            jqListItem.show();
        }
    },
    Index: function(id) {
        var $Search = $MyAutoModel;
        for (var len = $Search.EventObj.EventObjId.length; len--; ) {
            if ($Search.EventObj.EventObjId[len] == id || $Search.EventObj.EventObjTxtId[len] == id) {
                return len;
            }
        }
    },
    AddEvent: function() {
        var $Search = $MyAutoModel;
        var currentIndex = $Search.CurrentIndex;
        var execution = function(event) {

            var o = event.srcElement || event.target;
            var len = $Search.Index(o["id"]);

            if (currentIndex != len) {
                $("#" + $Search.EventObj.EventTargetObjId[currentIndex]).hide();
            }
            var jqListItem = $("#" + $Search.EventObj.EventTargetObjId[len]);
            $Search.Status(jqListItem);
            $Search.CurrentIndex = len;
            for (; len < 3; len++) {
                $("#" + $Search.EventObj.EventTargetObjId[len + 1]).hide();
            }
        }
        for (var len = $Search.EventObj.EventObjId.length; len--; ) {
            $("#" + $Search.EventObj.EventObjTxtId[len]).click(function(event) {
                execution(event);
            });
            $("#" + $Search.EventObj.EventObjId[len]).click(function(event) {
                execution(event);
            });
        }
        $(document).click(function(event) {
            var o = event.srcElement || event.target;
            var me = $(o);
            var currentIndex = $Search.CurrentIndex;
            var jqListItem = $("#" + $Search.EventObj.EventTargetObjId[currentIndex]);
            var layerBackground = document.getElementById("layerBackground");
            $("#header .layer4").hide();
            $("#myam_addcar .layer4").hide();
            if (me.attr("id") == "txt_myam_childnav" || me.attr("id") == "arrow_myam_childnav") {
                if ($("#txt_myam_parentnav").attr("name") != 0 && $("#txt_myam_parentnav").attr("name") != undefined) {
                    if ($State.GetImgShowCokiee && $State.GetImgShowCokiee(3)) {
                        $("#myam_addcar .layer4").show();
                    }
                }
            }
            //            if (me.hasClass("yearinfo")) {
            //                $("#myam_addcar .layer4").show();
            //            }
            if (me.hasClass("newguide")) {
                $(".layer1").hide();
            }

            if (layerBackground == null) {
                if (!me.hasClass("eicon") && !me.hasClass("icon") && o["id"] != $Search.EventObj.EventTargetObjId[currentIndex] && o["id"] != $Search.EventObj.EventObjId[currentIndex] && o["id"] != $Search.EventObj.EventObjTxtId[currentIndex]) {
                    jqListItem.hide();
                }
                //$MyAutoModel.ControlSecondLayer();
                if (me.parents("#" + $MyAutoModel.EventObj.EventBoxId[0] + ":eq(0)").length == 0 && me.parents("#myam_childpanel:eq(0)").length == 0) {
                    $MyAutoModel.CloseMyam_mycartype(); //$("#" + $MyAutoModel.EventObj.EventBoxId[1]).hide();
                    if ($Search.IsIframe) {
                        $MyAutoModel.IframeCallback();
                    }
                }
            }
        });

        $MyAutoModel.Onclick("myam_setcurrentautomodel", "txt_myam_autobrand", "txt_myam_automodel", "txt_myam_parentnav", "txt_myam_childnav", "请您先选择车型！");
        //$MyAutoModel.Onclick("myam_addautomodel", "txt_myam_autobrand", "txt_myam_automodel", "txt_myam_parentnav", "txt_myam_childnav", "请您先选择车型！");

        //更换车型
        $(".myam_openchangeautomodel").unbind().click(function() {
            $MyAutoModel.commonObj.isHomeAnC = false;
            $MyAutoModel.selectCarControl(function() {
                $("#" + $MyAutoModel.EventObj.EventBoxId[0]).append($("#" + $MyAutoModel.EventObj.EventBoxId[1]));
                $MyAutoModel.MyamLocation = $MyAutoModel.EventObj.EventBoxId[0];
                $MyAutoModel.addHtmlToDom();
                $MyAutoModel.Showpanelschoosecar();
                // 构造车型下拉框
                $MyAutoModel.IsFirstRequest = $MyAutoModel.IsFirstRequest == 0 ? 1 : 2;
                $MyAutoModel.Inint();

                if ($Search.IsIframe) {
                    $MyAutoModel.IframeCallback();
                }
            });
        })

        //显示车型选择
        $(".myam_showchoosecar,.js_myam_showchoosecar").unbind().click(function() {
            $MyAutoModel.commonObj.isHomeAnC = false;
            $MyAutoModel.selectCarControl(function() {
                $("#" + $MyAutoModel.EventObj.EventBoxId[0]).append($("#" + $MyAutoModel.EventObj.EventBoxId[1]));
                $MyAutoModel.addHtmlToDom();

                $MyAutoModel.MyamLocation = $MyAutoModel.EventObj.EventBoxId[0];

                // 构造车型下拉框
                $MyAutoModel.IsFirstRequest = $MyAutoModel.IsFirstRequest == 0 ? 1 : 2;
                $MyAutoModel.Showmyamaddcar();
                $MyAutoModel.Inint();
            });
        })

        //删除车型
        $("#" + $MyAutoModel.EventObj.EventBoxId[2] + " .myam_btndelautomodel").click(function() {

            if (!confirm("确认要删除吗？"))
                return;
            var pam = this.id.split("_");
            $.ajax({
                type: "GET",
                cache: false,
                url: "/handlers/UserAutoModel/UserAutoModelProvider.ashx?action=deleteautomodel&abid=" + pam[0] + "&amid=" + pam[1] + "&amsid=" + pam[2] + "&syear=" + pam[3],
                data: "",
                success: function(data) {
                    $MyAutoModel.ResetMyAutoModel(function() {
                        if ($MyAutoModel.MyamLocation == "myam_childbox") {
                            $("#myam_childbox").append($("#" + $MyAutoModel.EventObj.EventBoxId[1]));
                        }
                    });
                },
                error: function(e) { }
            });
        })

        //设置当前车型
        $("#myam_btnsetcurrentautomodel").click(function() {

            var radcount = $("input[name=radcurrentautomodel]").length;
            if (radcount > 0) {
                var radcurrentautomodel = $("input[name=radcurrentautomodel]:checked");
                if (radcurrentautomodel.length == 1) {
                    var pam = radcurrentautomodel[0].id.replace('rad', '').split("_");
                    $MyAutoModel.SetCurrentAutoModel(pam[0], pam[1], pam[2], pam[3]);

                } else {
                    alert("请选择车型！");
                }
            } else {
                $MyAutoModel.CloseMyam_mycartype(); //$("#" + $MyAutoModel.EventObj.EventBoxId[1]).hide();
            }
        })

        $("#myam_finditem").click(function() {
            if (this.name != "") {
                var pam = this.name.split('_');
                //var url = websitePath + "/list/a0-0-0-" + pam[0] + "-" + pam[1] + "-" + pam[2] + "-" + pam[3] + "-0-0-1.html";
                var url = websitePath + "/car/";
                window.location.href = url;
                return false;
            } else {
                alert("请先选择车型！");
            }
        })

        // 鼠标经过展示车型参数
        $("#linkdowlist").mouseover(function() {
            $("#autoparamdownlist").show();
        })
        $("#autoparamdownlist").hover(
        function() { },
        function() { $("#autoparamdownlist").hide(); });

    },
    addHtmlToDom: function() {
        $("#" + $MyAutoModel.EventObj.EventBoxId[3] + " #layer4").remove();
        $("#" + $MyAutoModel.EventObj.EventBoxId[3]).append($('<div class="layer4" style="display:none" id="layer4">' +
'<a href="javascript:" class="layer4close" onclick="$State.hideImgNewS(3)"></a>' +
'<a href="' + websitePath + '/guide/production-year.html#autoyear" target="_blank"  class="yearinfo"></a></div>'));
    },
    Onclick: function(id, txtBrandId, txtAutoModelId, txtParentNav, txtChildNav, message, host) {
        $("#" + id).unbind("click").click(function() {
            if ($.trim($("#" + txtBrandId).html()) == "汽车品牌" || $.trim($("#" + txtAutoModelId).html()) == "车系"
            || $.trim($("#" + txtParentNav).html()) == "排量" || $.trim($("#" + txtChildNav).html()) == "生产年份(与上路年份未必相同)") {
                alert(message);
            } else {
                var parentNavId = $("#" + txtParentNav).attr("name");
                parentNavId = parseInt(parentNavId, 10);

                var childNavId = $("#" + txtChildNav).attr("name");
                childNavId = parseInt(childNavId, 10);

                var autoModelId = $("#" + txtAutoModelId).attr("name");
                autoModelId = parseInt(autoModelId, 10);

                var brandName = $("#" + txtBrandId).attr("name");
                var brandId = brandName.substr(brandName.indexOf('_') + 1);
                var action = "";
                var callback = function() { };

                $MyAutoModel.AddAutoModel(brandId, autoModelId, parentNavId, childNavId);
            }
            return false;
        });
    },
    ResetMyAutoModel: function(callback) {
        if (document.getElementById($MyAutoModel.EventObj.EventBoxId[2])) {
            var mycartypeIsshow = $("#" + $MyAutoModel.EventObj.EventBoxId[1]).is(":hidden");
            var myam_panelschoosecarIsshow = $("#" + $MyAutoModel.EventObj.EventBoxId[2]).is(":hidden");
        } else {
            var mycartypeIsshow = true;
            var myam_panelschoosecarIsshow = true;
        }

        $.ajax({
            type: "GET",
            cache: false,
            url: '/handlers/userautomodel/userautomodelprovider.aspx?',
            data: { backurl: window.location.href },
            success: function(html) {
                $("#" + $MyAutoModel.EventObj.EventBoxId[1]).remove();
                $("#" + $MyAutoModel.EventObj.EventBoxId[0]).replaceWith(html)

                if (!mycartypeIsshow) {
                    $("#" + $MyAutoModel.EventObj.EventBoxId[1]).show();
                } else {
                    $MyAutoModel.CloseMyam_mycartype();
                }
                if (!myam_panelschoosecarIsshow) {
                    $("#" + $MyAutoModel.EventObj.EventBoxId[2]).show();
                } else {
                    $("#" + $MyAutoModel.EventObj.EventBoxId[2]).hide();
                }
                //投票专用
                if (typeof (voteAuto) != "undefined") { voteAuto(html); }
                if (callback) callback();

                welcomeTipsControl();
            }
        });
    },
    SetCurrentAutoModel: function(autobrandid, automodelid, automodelsubid, year, param, ismygarage, async) {
        if (autobrandid === 0 && year === 0) {
            $("#divIndexDiyMaintenanceYear").find('li:first').trigger('click');
            $("#divIndexDiyMaintenanceMonth").find('b').html('月份');
        }

        if ($String.IsInteger(autobrandid)) $MyAutoModel.CurrentAutoModelIds[0] = autobrandid;
        if ($String.IsInteger(automodelid)) $MyAutoModel.CurrentAutoModelIds[1] = automodelid;
        if ($String.IsInteger(automodelsubid)) $MyAutoModel.CurrentAutoModelIds[2] = automodelsubid;
        if ($String.IsInteger(year)) $MyAutoModel.CurrentAutoModelIds[3] = year;
        if (async == undefined) { async = true; }
        $.ajax({
            type: "GET",
            cache: false,
            async: async || false,
            url: websitePath + "/handlers/UserAutoModel/UserAutoModelProvider.ashx?action=setcurrentautomodel&abid=" + autobrandid + "&amid=" + automodelid + "&amsid=" + automodelsubid + "&syear=" + year + "&param=" + param + "&ismygarage=" + ismygarage,
            success: function(data) {
                var callback = function() {
                    setautoModelSubID();
                    $MyAutoModel.CloseMyam_mycartype();
                    $Layer.Close();
                    $MyAutoModel.IframeCallback();
                    $MyAutoModel.CurrentAutoModelChange();
                };
                $MyAutoModel.commonSetCurCarEvent(callback);
                if (typeof showStep == "function" && automodelsubid == "0") {
                    showStep();
                }
                $MyAutoModel.SetCarsteWardNavigationUrl(autobrandid, automodelid, automodelsubid, year);
                //ReloadShaiBaoYang();
                //$("#nav-txt-roadyears").html($("#nav-txt-roadyears").attr("name"));
            },
            error: function(e) { }
        });
        //$("#nav-txt-roadyears").html($("#nav-txt-roadyears").attr("name"));
    },
    saveparamtodb: function() {
        $.ajax({
            type: "GET",
            cache: false,
            async: true,
            url: websitePath + "/handlers/UserAutoModel/UserAutoModelProvider.ashx?action=saveparamtodb",
            success: function(data) {

            },
            error: function(e) { }
        });
    },
    AddAutoModel: function(autobrandid, automodelid, automodelsubid, year) {
        if ($String.IsInteger(autobrandid)) $MyAutoModel.CurrentAutoModelIds[0] = autobrandid;
        if ($String.IsInteger(automodelid)) $MyAutoModel.CurrentAutoModelIds[1] = automodelid;
        if ($String.IsInteger(automodelsubid)) $MyAutoModel.CurrentAutoModelIds[2] = automodelsubid;
        if ($String.IsInteger(year)) $MyAutoModel.CurrentAutoModelIds[3] = year;
        $.ajax({
            type: "GET",
            cache: false,
            url: "/handlers/UserAutoModel/UserAutoModelProvider.ashx?action=saveautomodel&abid=" + autobrandid + "&amid=" + automodelid + "&amsid=" + automodelsubid + "&syear=" + year,
            data: "",
            success: function(data) {
                var errmsg = data.split('_');
                switch (errmsg[0]) {
                    case "success":
                        $MyAutoModel.SetCarsteWardNavigationUrl(autobrandid, automodelid, automodelsubid, year);
                        $Layer.IsMyAutoClose = true;
                        var callback = function() {
                            $MyAutoModel.CloseMyam_mycartype();
                            $MyAutoModel.CurrentAutoModelChange();
                            if ($MyAutoModel.MyamLocation == "myam_childbox") {
                                $("#myam_childbox").append($("#" + $MyAutoModel.EventObj.EventBoxId[1]));
                            }
                        };

                        $MyAutoModel.commonSetCurCarEvent(callback);
                        break;
                    case "fail":
                        alert(errmsg[1]);
                        break;
                }
            },
            error: function(e) { }
        });
    },
    Showpanelschoosecar: function() {
        $("#myam_mycartype").css({ "top": "", "left": "" });
        $("#" + $MyAutoModel.EventObj.EventBoxId[1]).toggle();
        if ($("#" + $MyAutoModel.EventObj.EventBoxId[1]).is(":hidden")) {
            $MyAutoModel.CloseMyam_mycartype();
        }
        $("#" + $MyAutoModel.EventObj.EventBoxId[2]).show();
    },
    Showmyamaddcar: function() {
        $("#" + $MyAutoModel.EventObj.EventBoxId[1]).toggle();
        $("#" + $MyAutoModel.EventObj.EventBoxId[2]).show();
    },
    SetMyCurrentAutoModel: function(value) {
        $State.Save("MyCurrentAutoModel", value, 0, false)
    },
    SetCarsteWardNavigationUrl: function(AutoBrandId, AutoModelId, AutoModelSubId, Year) {
        if (AutoBrandId == 0
        || AutoModelId == 0
        || AutoModelSubId == 0
        || Year == 0) {
            $("#main_bar_carsteward").attr("href", "/car/");
        } else {
            $("#main_bar_carsteward").attr("href", "/car/c" + AutoBrandId + "-" + AutoModelId + "-" + AutoModelSubId + "-" + Year + ".html");
        }
    },
    CloseMyam_mycartype: function() {
        $("#" + $MyAutoModel.EventObj.EventBoxId[1]).hide();
    },
    IframeCallback: function() {
        if (window.parent.closemycart) {
            window.parent.closemycart();
        }
    },
    IframeLayerCallBack: function() {
        if (window.parent.openlayer) {
            window.parent.openlayer();
        }
    },
    //调整浮动层高度
    IframeLayerHeight: function(height) {
        if (window.parent.$Layer.ResetHeight) {
            window.parent.$Layer.ResetHeight(height);
        }
    },
    // 执行 Layer 层关闭的回调函数。
    execLayerCallBack: function() {

    },
    commonSetCurCarEvent: function(callback) {

        if ($MyAutoModel.CurrentChangeIsUpdate) {
            $MyAutoModel.ResetMyAutoModel(callback);
            if ($MyAutoModel.HaveChild == 1) {
                $UIChildUserAutoModel.ResetChildAutoModel();
            }

        } else {
            $MyAutoModel.CloseMyam_mycartype();
            $MyAutoModel.CurrentAutoModelChange();
        }
        //位置 1
        if ($MyAutoModel.commonObj.isHomeAnC) {
            $MyAutoModel.showCarAnimation($("#js_HomeCar")[0], $(".carName")[0], 660);
        }
        //位置2 1 2 位置不可颠倒。
        if (typeof hideStep == "function") {
            hideStep();
        }

        if (parseInt($("#nav-txt-roadyears").attr("name")) > 0) {
            $("#nav-txt-roadyears").html($("#nav-txt-roadyears").attr("name") + "年");
        }
        $MyAutoModel.commonObj.isHomeAnC = false;
    },
    //选择车型 处理函数。
    selectCarControl: function(oldfn) {
        var thisObj = this;
        // cookie是否已经有了车型值。 
        if (thisObj.isSelectedCar()) {
            if (oldfn) {
                oldfn(); //todo 原来的事情。
            }
        } else {
            thisObj.showCarChooseLayer();
        }
    },
    commonObj: {
        "carKey": $("#js-MyAutoModelListKey").val() || "MyAutoModelList|MyCurrentAutoModel", // 我的车型的 cookie key值。 
        "isFirst": true, //Layer 中弹出的。选择车型。---第四步 true  第一次点击  false 非第一次点击。 用于改变 showBox marginTop 
        "isHomeAnC": false,  //是否是首页的 图片点击过来的。
        "showBoxMarginTop": 0   // 弹出框的 MarginTop 原始。
    },
    //cookie 是否有车型信息。
    isSelectedCar: function() {
        var thisObj = this,
             result = 0,
             key = thisObj.commonObj["carKey"],
             keys = key.split('|');
        if (keys.length > 1) {
            //不等于  nothing '' 的时候。 表示选过车型。&&$State.GetValue(keys[1])!="nothing"
            result = $State.GetValue(keys[0]).length > 0 || ($State.GetValue(keys[1]).length > 0 && $State.GetValue(keys[1]) != "nothing") ? 1 : 0
        }
        return result;
    },
    // 保存完 我的车型后的 函数。
    closeCarLayerEvent: function() {

        var thisObj = this,
            callback = function() {
                $MyAutoModel.CloseMyam_mycartype();
                $MyAutoModel.CurrentAutoModelChange();
                if ($MyAutoModel.MyamLocation == "myam_childbox") {
                    $("#myam_childbox").append($("#" + $MyAutoModel.EventObj.EventBoxId[1]));
                }
                $("#divIndexDiyMaintenanceYear").find('li:last').trigger('click');
            };
        $Layer.Close();
        thisObj.commonSetCurCarEvent(callback);
        //如果是第一次选车型（第一次进入网站的用户，调用闪烁函数引导用户，完成后cookie标记
        if ($State.GetValue("gas_isguide") != "1") {
            //调用闪烁函数
            animChange(0, 6);
            $State.Save("gas_isguide", "1", 432000, false);
        }

    },
    //选择车型 优化newLayer。 type 2 首页的图片链接 过来的。
    showCarChooseLayer: function(elem, typeStep) {
        var type = typeStep || 0,
             objValue = '{"Alphabet":null,"AutoBrandId":0,"AutoModelId":0,"AutoModelSubId":0,"ChooseType":1,"MainAutoModelID":0,"SubIds":null,"Year":0}',
             thisObj = this,
             $elem = $(elem),
             paramscontent = "",
             param = null;
        function getContent(param) {
            var handleObj = {
                "url": "/handlers/choosecar/choosecarprovider.aspx",
                "param": param,
                "successFn": function(data) {
                    if (data.toLowerCase() === "success") {
                        thisObj.closeCarLayerEvent();
                        $MyAutoModel.IframeCallback();
                        //ReloadShaiBaoYang();
                        return;
                    }

                    var content = data,
                         messageObj = { "content": content, "title": "", "width": "696" };
                    thisObj.showMessage(messageObj);
                    $("#showBox .stepClose").click(function() {
                        $Layer.Close();
                        $MyAutoModel.execLayerCallBack();
                        if (window.parent.$Layer.Close) {
                            window.parent.$Layer.Close();
                        }
                        return false;
                    });
                    $MyAutoModel.IframeLayerHeight($("#" + $Layer.ID).height());
                    //初始化Layer 中弹出的第四部。选择车型。
                    thisObj.commonObj.isFirst = true;
                    thisObj.commonObj.showBoxMarginTop = parseFloat($("#showBox").css("marginTop"));
                    //png fix
                    thisObj.imgPngFix([".picrightArrow", ".picLeftArrow", ".picrightArrow a", ".picLeftArrow a"]);
                    var paramval = eval("(" + param.json + ")");
                    if (paramval.ChooseType == 5) {
                        // 如果是选择车型，默认选中第一个车型
                        var natureCur = $(".carNature ul li.natureCur a");
                        if (natureCur) $MyAutoModel.showLayerModelPicutre(natureCur.attr("name"), natureCur[0]);
                    }
                }
            }
            thisObj.handleControl(handleObj);
        }
        if (parseInt(type, 10) > 0 && parseInt(type, 10) == 2) {
            thisObj.commonObj.isHomeAnC = true;
        }
        if (type != 2 && $elem.length > 0 && $elem.attr("choosecarparam").length > 0) {
            objValue = $elem.attr("choosecarparam");
        }
        paramscontent = objValue;
        param = { "json": paramscontent };
        getContent(param);

        //        var jsonTemp = eval('(' + param.json + ')');
        //        if (typeof ReloadYear === 'function' && jsonTemp.Year!=undefined && jsonTemp.Year > 0) {
        //            ReloadYear(jsonTemp.Year);
        //        }

    },
    imgPngFix: function(array) {
        var arr = array || [],
                isIE = !!window.ActiveXObject,
                isIE6 = isIE && !window.XMLHttpRequest;
        if (isIE) {
            if (isIE6) {
                if (typeof pngFix == "function")
                    pngFix(array);
            }
        }
    },
    showMessage: function(messageObj) {
        Web.UI.Common.showMessage(messageObj);
    },
    // 子选择车型的回调函数。
    exexChildSelectCarFn: function() {
        //默认为空在需要的页面内重写改方法。
    },
    handleControl: function(handleObj) {
        $.ajax({
            type: handleObj.type || "get",
            url: handleObj.url,
            data: $.param(handleObj.param),
            cache: false,
            dataType: handleObj.dataType || "text",
            success: function(data) {
                handleObj.successFn(data);
            },
            error: function(e) {
            }
        });
    },
    //保养心得吧！
    bindShowSelectCarLayer: function(MyamLocation) {
        //切换车型
        //车型优化
        var MyamLocationValue = MyamLocation || "js-myam_childbox";
        var MyamLocationValueObj = $("#" + MyamLocationValue);

        $MyAutoModel.selectCarControl(function() {
            MyamLocationValueObj.append($("#myam_mycartype"));
            $MyAutoModel.addHtmlToDom();
            $MyAutoModel.MyamLocation = MyamLocationValue;
            setTimeout(function() {
                $("#myam_mycartype").show();
                $("#myam_panelschoosecar").show();
            }, 1);
            $MyAutoModel.IsFirstRequest = $MyAutoModel.IsFirstRequest == 0 ? 1 : 2;
            $MyAutoModel.Inint();
        });
    },
    //保存车型。
    setCurModel: function(elem) {
        var thisObj = this,
             params = null;
        //得到车型参数。
        function getParams() {
            var choosecarparam = $(".carNature li.natureCur").find("a").attr("choosecarparam");
            if (choosecarparam.length > 0) {
                var objValue = eval("( " + choosecarparam + " )");
                objValue = ((typeof objValue).toLowerCase() == "object") ? objValue : null;
                return objValue;
            }
        }
        // 没有natureCur 这个样式 的时候。
        if ($(".carNature li.natureCur").length <= 0) {
            alert("请选择车型！");
            return;
        }
        params = getParams();
        if (params.hasOwnProperty("AutoBrandId")) {
            thisObj.SetCurrentAutoModel(params.AutoBrandId, params.AutoModelId, params.AutoModelSubId, params.Year);
            //$MyAutoModel.IframeCallback();
            //$Layer.Close();
        }
    },
    // 小车的 人生。  起点元素sElem，  终点元素dElem 
    showCarAnimation: function(sElem, dElem, Sleft) {
        var thisObj = this,
             stDom = $(sElem).eq(0),
             toDom = $(dElem).eq(0),
             left = parseInt(toDom.offset().left) + 70,
             top = toDom.offset().top,
             crrLeft = Sleft > 0 ? (stDom.offset().left + Sleft) : stDom.offset().left,
             crrTop = stDom.offset().top,
             imgObj = thisObj.getImg(crrLeft, crrTop);
        $(document.body).append(imgObj);
        thisObj.imgPngFix(["#imgGpOD"])
        $(imgObj).animate({ "top": top + "px", "left": left + "px" }, 1200, function() {
            $("#imgGpOD").hide("slow", function() {
                $(this).remove();
            });
        });
    },
    isIE6: function() {
        var isIE = !!window.ActiveXObject,
            isIE6 = isIE && !window.XMLHttpRequest;
        return isIE6;
    },
    //图片的限制最大最小宽高。
    maxHAndWEvent: function() {
        var thisObj = this;
        if (thisObj.isIE6()) {
            $('#js-carDiffentUl .hasMaxEffect').each(function() {
                thisObj.resizeImage(this, 335, 335);
            });
        }
    },
    //图片的限制最大最小宽高。
    resizeImage: function(elem, maxW, maxH) {
        var $this = $(elem),
               width = $this.width(),    // 图片实际宽度
               height = $this.height(),  // 图片实际高度
               maxWidth = maxW, // 图片最大宽度
               maxHeight = maxH,    // 图片最大高度
               ratio = 0;  // 缩放比例
        if (width > height) {   // 看哪个最大。 宽大 按照宽 来压缩， 高大 按照高来压缩。     
            // 检查图片是否超宽
            if (width > maxWidth) {
                ratio = maxWidth * (1.0) / width;   // 计算缩放比例
                $this.css("width", maxWidth); // 设定实际显示宽度
                height = height * ratio;    // 计算等比例缩放后的高度
                $this.css("height", height);  // 设定等比例缩放后的高度
            }
        } else {
            // 检查图片是否超高
            if (height > maxHeight) {
                ratio = maxHeight * (1.0) / height; // 计算缩放比例
                $this.css("height", maxHeight);   // 设定实际显示高度
                width = width * ratio;    // 计算等比例缩放后的高度
                $this.css("width", width);    // 设定等比例缩放后的高度
            }
        }
    },
    // 创建小车
    getImg: function(crrLeft, crrTop) {
        var img = document.createElement("img");
        img.setAttribute("src", resourcePath + "/images/home/car.png");
        img.className = "animationCar";
        img.style.left = crrLeft + "px";
        img.style.top = crrTop + "px";
        img.setAttribute("id", "imgGpOD");
        return img;
    },
    // Layer 中选中车型。展示图片
    showLayerModelPicutre: function(modelId, elem) {
        var hasPicture = false,
            thisObj = this,
            $data = $("#js-AutoModelPictureListJson"),
            objValue = null,
            data = null;
        // 样式初始化。
        $(".carNature li").removeClass("natureCur");
        $(elem).parent("li").addClass("natureCur");
        $("#js-carDiffentUl").css("left", "0px");
        if ($data.length > 0 && $data.val().length > 0) {
            objValue = eval("( " + $data.val() + " )");
        }
        //生成 li html 及其绑定 左右的 按钮事件。
        for (var i = 0; i < objValue.length; i++) {
            if (objValue[i].AutoModelId.toString() == modelId.toString() && objValue[i].pictureList.length > 0) {
                hasPicture = true;
                data = objValue[i];
                $("#js-carDiffentUl").html(thisObj.formatHtml(data));
                //bind 事件。
                thisObj.bindAutoModelScroll(modelId);
                thisObj.maxHAndWEvent();
            }
        }

        // 有图片的 调整 Layer MarginTop
        if (!hasPicture) {
            $("#js-carDiffentUl").html("");
            $("#js-carDiffent").hide();

            $("#showBox").css("margin-top", (thisObj.commonObj.showBoxMarginTop).toString() + "px");
            $MyAutoModel.IframeLayerHeight($("#" + $Layer.ID).height());
        } else {
            if (window.parent.closemycart) {
                $MyAutoModel.IframeLayerHeight($("#" + $Layer.ID).height());
            } else {
                var jianshu = 75;
                // 如果是IE6
                if (! -[1, ] && !window.XMLHttpRequest) {
                    jianshu = 23;
                }
                var trueTop = thisObj.commonObj.showBoxMarginTop - jianshu;
                if (trueTop < 0) {
                    trueTop = 0;
                }
                $("#showBox").css({ "margin-top": trueTop.toString() + "px" });
            }
            $("#js-carDiffent").show();
        }
    },
    //li -----格式化Layer 车 图片。
    formatHtml: function(data) {
        String.format = function(str) {
            var args = arguments, re = new RegExp("%([1-" + args.length + "])", "g");
            return String(str).replace(re,
                    function($1, $2) {
                        return args[$2];
                    }
                    );
        };
        //format html
        var result = "",
              strTemp = '<li name="%1"><span><img class="hasMaxEffect" src="%2"></span></li>',
              i = 0;
        for (; i < data.pictureList.length; i++) {
            result += String.format(strTemp, data.AutoModelId, data.pictureList[i]);
        }
        return result;
    },
    //Layer 车 轮播的效果.
    bindAutoModelScroll: function(Id) {
        var thisObj = this,
                num = Id,
                $carDiffent = $("#js-carDiffent");
        $carDiffent.find(".picLeftArrow").addClass("pnullLeft");
        $carDiffent.find(".picrightArrow").addClass("pnullRight");
        if ($("#js-carDiffentUl").find("li").size() > 2) {
            $carDiffent.find(".picrightArrow").removeClass("pnullRight");
        }
        $carDiffent.find(".picLeftArrow").attr("id", "js_picLeftArrow" + num);
        $carDiffent.find(".picrightArrow").attr("id", "js_picrightArrow" + num);
        $("#js_picrightArrow" + num).unbind();
        $("#js_picLeftArrow" + num).unbind();
        //轮播效果。 
        var slide = {
            $parentObj: $("#js-carDiffent"),
            hideNextpage: function() {
                slide.$parentObj.find(".picrightArrow").addClass("pnullRight");
                return false;
            },
            showpage: function() {
                slide.$parentObj.find(".picLeftArrow").removeClass("pnullLeft");
                slide.$parentObj.find(".picrightArrow").removeClass("pnullRight");
                return false;
            },
            hidePrePage: function() {
                slide.$parentObj.find(".picLeftArrow").addClass("pnullLeft");
                return false;
            }
        };
        $("#js-carDiffent").show().scrollauto({ number: 2, width: 335, pageUp: "js_picLeftArrow" + num,
            pageNext: "js_picrightArrow" + num, claCon: "ul", noScroll: 2,
            lc: slide.hideNextpage, sc: slide.showpage, fc: slide.hidePrePage
        });
    },
    //jsonToString()
    jsonToString: function(obj) {
        var THIS = this;
        switch (typeof (obj)) {
            case 'string':
                return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
            case 'array':
                return '[' + obj.map(THIS.jsonToString).join(',') + ']';
            case 'object':
                if (obj instanceof Array) {
                    var strArr = [];
                    var len = obj.length;
                    for (var i = 0; i < len; i++) {
                        strArr.push(THIS.jsonToString(obj[i]));
                    }
                    return '[' + strArr.join(',') + ']';
                } else if (obj == null) {
                    return 'null';

                } else {
                    var string = [];
                    for (var property in obj) string.push(THIS.jsonToString(property) + ':' + THIS.jsonToString(obj[property]));
                    return '{' + string.join(',') + '}';
                }
            case 'number':
                return obj;
            case 'boolean':
                return obj;
            case false:
                return obj;
        }
    },
    closeFirstCarTip: function() {
        //如果是第一次选车型（第一次进入网站的用户，调用闪烁函数引导用户，完成后cookie标记
        if ($State.GetValue("gas_isguide") != "1") {
            //调用闪烁函数
            animChange(0, 6);
            $State.Save("gas_isguide", "1", 432000, false);
        }
        var num = 1, add = 0;
        $("#js_welcomeTips").hide();
        // autoCarSelect 关闭 五次不出现了 没选车型的时候自动弹层。
        if ($State.GetValue("autoCarSelect").length > 0) {
            add = parseInt($State.GetValue("autoCarSelect"), 10);
            if (add > 0) {
                num = num + add;
            }
        }
        $State.Save("autoCarSelect", num.toString(), 999999, false);
    },
    showFirstCarTip: function() {
        $MyAutoModel.showCarChooseLayer();
        this.closeFirstCarTip();
    }
}
var $MyAutoModel = Web.UI.MyAutoModel;


//我的车库结束


usingNamespace("Web.UI")["ItemGift"] = {
    MoveEvent: function() {
        $(".gifts a").mouseover(function() {
            $(this).next().show();
        });
        $(".gifts a").mouseout(function() {
            $(this).next().hide();
        });
    }
}

var $ItemGift = Web.UI.ItemGift;



//隐藏 IE FF下 点击 A 的焦点虚线框
$(function() {
    var links = document.getElementsByTagName('a');
    for (var i = 0, l = links.length; i < l; i++) {
        $(links[i]).addClass("hidefocus").attr("hidefocus", true);
    }
});

window.advertSuccess = function() {
    try { $(document).Lazyload({ jqImg: "div[id^='advertDiv']" }); } catch (e) { }
};


usingNamespace("Web.UI")["CommonMap"] = {
    ShowMap: function() {
        var html = '<div class="maptips">';
        html += '<p><img style="margin-top:16px;" src="' + resourcePath + '/images/order/shmap.jpg" width="600" height="473"/></p>';
        html += '<div class="close"><a href="javascript:void(0)" onclick="$Layer.Close();"></a></div></div>';
        $Layer.Reset();
        $Layer.IsBack = true;
        $Layer.IsFooter = false;
        $Layer.Width = 604;
        $Layer.Title = "";
        $Layer.CloseText = "关 闭";
        $Layer.Content = html;
        $Layer.IsConfirm = true;
        $Layer.Confirm.Text = "确 定";
        $Layer.Open();
    }
}

var $CommonMap = Web.UI.CommonMap;






(function($) {
    $.autoslide = {
        inIt: function(settings, jqSlide) {
            var jqClaNav = $("#" + settings.claNav).find(settings.claNavParent); //目标对象
            var jqClaCon = $("#" + settings.claCon); //目标对象内容
            var conNum = jqClaCon.find(settings.claConParent).size(); //滚动对象的子元素个数
            var slideH = jqClaCon.find(settings.claConParent).height()//滚动对象的子元素高度，相当于滚动的高度
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
                $.autoslide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
            }
            if (settings.effect == "scroolX") {

                jqClaCon.css("width", conNum * slideW);
            }
            if (settings.page.isPage) {
                $("#" + settings.claNav).css("width", conNum * settings.claNavWidth);
                if (conNum <= 4) {
                    $("#" + settings.page.pageUp).addClass("noclick");
                    $("#" + settings.page.pageNext).addClass("noclick");
                } else {
                    $("#" + settings.page.pageUp).click(function() {
                        if (index > 0) {
                            index--;
                            $.autoslide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
                            $.autoslide.effect.test(jqClaNav, jqClaCon, index, slideH, settings.claNavWidth, settings);
                        }
                        return
                    });
                    $("#" + settings.page.pageNext).click(function() {
                        if (index < conNum - 1) {
                            index++;
                            $.autoslide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
                            $.autoslide.effect.test(jqClaNav, jqClaCon, index, slideH, settings.claNavWidth, settings);
                        }
                        return
                    });
                }
                jqClaNav.hover(function() {
                    index = jqClaNav.index(this);
                    $.autoslide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
                });
            } else {
                jqClaNav.hover(function() {
                    if (settings.autoPlay) {
                        over();
                    }
                    index = jqClaNav.index(this);
                    $.autoslide.effect[settings.effect](jqClaNav, jqClaCon, index, slideH, slideW, settings);
                }, function() {
                    if (settings.autoPlay) {
                        out();
                    }
                });
            }
            $.autoslide.effect[settings.effect](jqClaNav, jqClaCon, settings.defIndex, slideH, slideW, settings); //默认显示第一张图片
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
                    $.autoslide.close(jqSlide);
                })
            }
        },
        close: function(jqSlide) {
            jqSlide.hide();
        },
        loadPic: function(jqClaCon, index) { //延迟加载图片
            var jqShowPic = jqClaCon.find("img").eq(index);
            var imgobj = jqShowPic.attr("src2");
            if (imgobj) {
                var src2 = imgobj;
                jqShowPic.attr("src", src2).removeAttr("src2").show();
            } else {
                jqShowPic.show();
            };
            jqShowPic.width("auto");
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
                $.autoslide.loadPic(jqClaCon, index);
                jqClaCon.find(settings.claConParent).hide();
                jqClaCon.find(settings.claConParent).eq(index).show();
                $.autoslide.set(jqClaNav, jqClaCon, index, settings);

            },
            scroolX: function(jqClaNav, jqClaCon, index, slideH, slideW, settings) {

                $.autoslide.loadPic(jqClaCon, index);
                jqClaCon.stop().animate({ "left": -index * settings.steps * slideW }, settings.speed);
                $.autoslide.set(jqClaNav, jqClaCon, index, settings);
            },
            scroolY: function(jqClaNav, jqClaCon, index, slideH, slideW, settings) {
                $.autoslide.loadPic(jqClaCon, index);
                jqClaCon.stop().animate({ "top": -index * settings.steps * slideH }, settings.speed);
                $.autoslide.set(jqClaNav, jqClaCon, index, settings);
            },
            test: function(jqClaNav, jqClaCon, index, slideH, slideW, settings) {
                $("#" + settings.claNav).stop().animate({ "left": -index * settings.steps * slideW }, settings.speed);
            }
        }
    }
    $.fn.autoslide = function(options) {  //构造函数初始化变量和方法
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
            claNavWidth: 134,
            claNav: "AM-slide-nav",
            claCon: "AM-slide-content",
            claNavParent: "li",
            claConParent: "li"
        };
        var jqSlide = $(this);
        $.extend(true, settings, options || {});
        $.autoslide.inIt(settings, jqSlide);
    }
    $(".showparamimfo").mouseover(function() {
        $(this).next("em").show();
    }).mouseout(function() {
        $(this).next("em").hide();
    })
})(jQuery);
function keyDocumentPosition(o) {
    var html = [];
    var secect = false;
    document.onkeyup = function(event) {
        if (!secect) {
            for (var j = 0; j < o.length; j++) {
                if ($(o[j]["show"]).css("display") == "block") {
                    secect = true;
                    break;
                }
            }
        }
        if (secect) {
            if (html.length == 0) {
                var lis = $(o[0]["show"]).find("a");
                if (lis.length == 0) {
                    var lis_1 = $(o[1]["show"]).find("a");
                    if (lis_1.length == 0) {
                        lis_1 = $(o[2]["show"]).find("a");
                    }
                    lis_1.each(function(m, v) {
                        var obj = {};
                        var str = $(v).html().substr(0, 1).toLowerCase();
                        obj[str] = m;
                        html[html.length] = obj;
                    })
                }
                else {
                    lis.each(function(m, v) {
                        var obj = {};
                        var str = $(v).html().substr(0, 1).toLowerCase();
                        obj[str] = m;
                        html[html.length] = obj;
                    })
                }
            }
            var e = event || window.event;
            var key = String.fromCharCode(e.keyCode).toLowerCase();
            for (var i = 0; i < html.length; i++) {
                if (key in html[i]) {
                    for (var k = 0; k < o.length; k++) {
                        var objShow = o[k]["show"];
                        var objScorll = o[k]["scorll"];
                        if (objShow != null && objShow != undefined) {

                            if ($(objShow).css("display") == "block") {
                                scorllH = $(objScorll).find("li").first().height() || 18;
                                $(objScorll).scrollTop(html[i][key] * scorllH);
                                $(objScorll).find("a").css("backgroundColor", "");
                                $($(objScorll).find("a")[html[i][key]]).css("backgroundColor", "#fdfead");
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
};

$(function() {


    $("#txt_myam_childnav,#arrow_myam_childnav").click(function() {
        if ($("#txt_myam_parentnav").attr("name") != 0 && $("#txt_myam_parentnav").attr("name") != undefined) {
            if ($State.GetImgShowCokiee && $State.GetImgShowCokiee(3)) {
                $("#myam_addcar .layer4").show();
            }
        }
    })
    //购物车弹出框处理
    //shoppingCartLayer.cartShow();
    //shoppingCartLayer.deteleHandle();

    //    var isIE = !!window.ActiveXObject;
    //    var isIE6 = isIE && !window.XMLHttpRequest;
    //    if (isIE) {
    //        if (isIE6) {
    //            if (typeof pngFix == "function")
    //                pngFix([".layer4"]);
    //        }
    //    }

});
function isNSShowImg() {
    if ($State.GetImgShowCokiee && $State.GetImgShowCokiee(3)) {
        $("#header").children(".wrap").children(".layer4").hide();
        $("#myam_addcar .layer4").show();
    }
};


$(function() {
    if (typeof keyDocumentPosition == "function") {
        var o = [{ "show": $("#autobrand"), "scorll": $("#autobrand"), "height": 0 },
        { "show": $("#brand"), "scorll": $("#brand").find("ul")[0], "height": 0 }, { "show": $("#myam_autobrand"), "scorll": $("#myam_autobrand").find("ul")[0], "height": 0 }, { "show": $("#autobrand"), "scorll": $("#autobrand ul")[0], "height": 0 }
        ];
        keyDocumentPosition(o);
    }
});
var BindEventClick = (function() {
    if (document.addEventListener) {
        return function(target, type, fuc) {
            target.addEventListener(type, fuc, false);
        }
    }
    else if (document.attachEvent) {
        return function(target, type, fuc) {
            function _hander(e) {
                fuc.call(target);
            };
            target.attachEvent("on" + type, fuc);
        }
    }
    else {
        return function(target, type, fuc) {
            target["on" + type] = fuc;
        }
    }
})();
//验证邮箱格式
function CheckEmail(txtId) {
    var flag = true;
    var txtEmail = $(txtId).val();
    var regEmail = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
    if (txtEmail == null || txtEmail == "") {
        $(txtId).next("i").html("输入不能为空");
        flag = false;
    }
    else if (!regEmail.test(txtEmail)) {
        $(txtId).next("i").html("E-mail 格式错误");
        flag = false;
    }
    else {
        $(txtId).next("i").html("");
    }

    return flag;
}
//验证手机格式
function CheckTelNumber(txtId) {
    var flag = true;
    var txtTel = $(txtId).val();
    var regTel = /^(1)[0-9]{10}$/;
    if (txtTel == null || txtTel == "") {
        $(txtId).next("i").html("输入不能为空");
        flag = false;
    }
    else if (!regTel.test(txtTel) || (txtTel.length != 11)) {
        $(txtId).next("i").html("手机号码格式错误");
        flag = false;
    }
    else {
        $(txtId).next("i").html("");
    }
    return flag;
}

$(".peace ol li").hover(
            function() {
                var me = $(this);
                var left = me.position().left;
                var panel = $(".peacecontent");
                panel.append(me.attr("name"));
                if (me.hasClass("clsLiLast")) {
                    panel.css({ left: left - 120 });
                    panel.find("i").css({ left: 140 });
                } else {
                    panel.css({ left: left - 50 });
                }
                panel.show();
            }
            ,
            function() {
                var me = $(this);
                var panel = $(".peacecontent");
                panel.html("<i></i>");
                panel.css({ left: 0 });
                panel.hide();
            }
        );
var _s = '<div class="rclistbg">' +
        '<div class="photo50 clubcenter"><a href="UserHomePath" >' +
         '   <img src="ImageWebPath"></a></div>' +
        '<div class="rcmessage">' +
         '   <ul>' +
          '      <li class="rcname"><span><a href="UserHomePath" class="hidefocus" title="TitleUserName" hidefocus="true">UserName</a></span>' +
          '          <div class="star">' +
           '             <a href="DarenLevelNUM" target="_blank" title="DarenLevelName" class="hidefocus" hidefocus="true"><i class="grade0LeverPath"></i></a>' +
            '        </div>' +
             '   </li>' +
              '  <li><strong class="bus"></strong><span><a href="ModelNameUrl" class="hidefocus" hidefocus="true">BrandName</a></span><span>SubName</span></li>' +
           ' </ul>' +
       ' </div>' +
       ' <div class="flower">' +
        '    <ul>' +
         '       <li>鲜花数<span> （FlowersNum）</span></li>' +
          '  </ul>' +
           ' <a href="UserHomePath" target="_blank" class="hidefocus" hidefocus="true">去TA的空间 &gt;&gt;</a></div>' +
    '</div>';

var DaRenNextPage = function(obj) {
    var json = window.json || eval($("#hidedaren").html());
    if (!window.json)
        window.json = json;
    var pageSize = parseInt($(obj).attr("data-pagesize")) || 5;
    var curr = currLast = parseInt($(obj).attr("data-currlast")) || 4;
    var html = "";
    var length = json.length < pageSize ? pageSize : json.length;
    var n = 0;
    for (var i = currLast + 1; i < currLast + 1 + pageSize; i++) {

        i = i % length;
        html += _s.replace("ImageWebPath", json[i].ImageWebPath).replace("TitleUserName", json[i].TitleUserName)
                                .replace("UserHomePath", json[i].UserHomePath)
                                .replace("UserHomePath", json[i].UserHomePath)
                                .replace("ModelNameUrl", json[i].ModelNameUrl)
                                .replace("BrandName", json[i].BrandName + " " + json[i].ModelName)
                                .replace("SubName", json[i].SubName)
                                .replace("FlowersNum", json[i].FlowersNum)
                                .replace("UserHomePath", json[i].UserHomePath)
                                .replace("rclistbg", n % 2 == 0 ? "rclist" : "rclistbg")
                                .replace("UserName", json[i].UserName)
                                .replace("LeverPath", json[i].LeverPath)
                                .replace("DarenLevelNUM", json[i].DarenLevelNUM)
                                .replace("DarenLevelName", json[i].DarenLevelName);


        n++;
        curr = i;
        if (n == pageSize) {
            n = 0;
            break;
        }
    }
    $(obj).attr("data-currlast", curr);
    setTimeout(function() {
        $(obj).parent().nextAll().remove();
    }, 0);
    setTimeout(function() {
        $(obj).parent().parent().append(html);
    }, 0);
};

function ClubHeadTip() {
    // 个人中心 鼠标移入移出效果
    //    $("#js_liMessage").hover(
    //        function() { $("#js_liTopPCenterP").addClass("newsCur"); $(".js_onChoose").show(); },
    //        function() { $("#js_liTopPCenterP").removeClass("newsCur"); $(".js_onChoose").hide(); }
    //    );
    $("#js_user").hover(
        function() { $("#js_user").find(".js_userInfo").show(); $("#js_user >span ").addClass("userCur"); },
        function() { $("#js_user").find(".js_userInfo").hide(); $("#js_user >span ").removeClass("userCur"); }
    );
    $.ajax({
        type: "POST",
        url: websitePath + "/handlers/maintenancepeople/clubheadtipprovider.ashx",
        cache: false,
        success: function(data) {
            // 整站顶部
            var result = data.split(':');
            if (parseInt(result[0]) + parseInt(result[1]) > 0) {
                $("#liTopPCenter").html("新消息<i></i>");
                //$("#js_liTopPCenterP").addClass("newsCur"); 
            }
            if (result[0] == "1") {
                $(".clsNavMessage").html("系统消息<i></i>");
            }
            if (result[1] == "1") {
                $(".clsNavMyHome").html("我的空间<i></i>");
            }
        },
        error: function(e) { }
    });
}

// 设置社区提示信息
function SetClubTip(data) {
    if (data != "nosource") {
        $("#topnav_club").append(data);
    }
}

// 设置问答提示信息
function SetAskTip(data) {
    if (data != "nosource") {
        $("#topnav_ask").append(data);
    }
}


// 解决跨域问题
var createAjaxIframe = {
    appIframe: function(iframeId, iframeSrc, fn) {
        var iframe = document.createElement("iframe");
        iframe.src = iframeSrc;
        iframe.id = iframeId;
        iframe.style.display = "none";
        if (iframe.attachEvent) {
            iframe.attachEvent("onload", function() {
                createAjaxIframe.domainAjax(iframeId);
                if (fn)
                    fn();
            });
        } else {
            iframe.onload = function() {
                //iJquery = createAjaxIframe.domainAjax(iframeId);
                if (fn)
                    fn();
            };
        }
        document.body.appendChild(iframe);
    },
    domainAjax: function(iframeId) {
        $.ajax = document.getElementById(iframeId).contentWindow.jQuery.ajax;
    }
};

// 53客服辅助代码
//function controlkf() {
//    var kf53_status_key = "kf53_status";    // 53客服展示状态值Key
//    var kf53statusValue = $State.GetValue(kf53_status_key);

//    $("#kf_close").click(function() {
//        setTimeout(function() { hide_kf(); }, 0);
//        $State.Save(kf53_status_key, "1", 999999, false);
//    });
//    if (kf53statusValue == "1") {
//        setTimeout(function() { hide_kf(); }, 0);
//    }

//    $("#kf_xiaokuai").mouseover(function() {
//        //车模的活动
//        if($("#js-ActivityStatus").length>0){
//            $("#kf53panel").css("z-index",99999999);
//        }
//        setTimeout(function() { show_kf(); }, 0);
//    });
//    
//    $("#kf_hidden").hover(
//        function() { },
//        function() { 
//        //车模的活动的。
//        if($("#js-ActivityStatus").length>0){
//            $("#kf53panel").css("z-index",9999);
//        }
//        setTimeout(function() { hide_kf(); }, 0); }
//    );
//}

function hide_kf() {
    $("#kf_xiaokuai").show();
    $("#kf_hidden").hide();
    $("#kf53panel").width(25);
}
function show_kf() {
    $("#kf_xiaokuai").hide();
    $("#kf_hidden").show();
    $("#kf53panel").width(150);
}

function Check_Layer(o, t) {
    if (!o.value) {
        var v;
        if (t == 1) {
            v = "请输入您的登录账号";
        } else {
            v = "请输入您的登录密码";
        }
        $(o).next().show().html(v);
    } else {
        $(o).next().hide();
    }
}
function Check_Layer_PassPost(callbackEvent) {
    var name = $.trim($("#txt_Uname").val());
    var pwd = $.trim($("#txt_Pwd").val());
    var autologin = $("#autologin").is(":checked") ? 1 : 0;

    if (name && pwd) {
        $.ajax({
            type: "POST",
            url: "/handlers/customer/logincarmodel.ashx",
            data: "uname=" + name + "&pwd=" + pwd + "&autologin=" + autologin + "&t=" + (+new Date()),
            cache: false,
            success: function(data) {
                if (data.toString() == "1") {
                    if (callbackEvent) {
                        callbackEvent();
                    }
                    else {
                        window.location.reload();
                    }
                } else {
                    $("#promptlogin").html("用户名或者密码错误").show();
                }
            },
            error: function(e) { }
        });
    }
    if (!name) {
        $("#txt_Uname").next().show().html("请输入您的登录账号");
    }
    if (!pwd) {
        $("#promptlogin").show().html("请输入您的登录密码");
    }
}


function callbackLoginLayer() {
    if ($("#js-helpGod").length > 0) {
        $("#js-helpGod").show();
        $("#js-helpGod").siblings(".ask").hide();
        //todo  why
        $("#js-helpGod i").click();
        $("#js-helpGod").hide();
    }
}
function ShowLonginLayer(str) {
    $Layer.Close();
    $Layer.Reset();
    $Layer.IsBack = true;
    $Layer.IsFooter = false;
    $Layer.Width = 430;
    $Layer.Title = "";
    $Layer.CloseText = "关 闭";
    $Layer.Content = str;
    $Layer.IsConfirm = true;
    $Layer.Confirm.Text = "确 定";
    $Layer.Open();
    $(".inputValue").keypress(function(e) {
        if (e.keyCode == "13") {
            Check_Layer_PassPost(callbackLoginLayer); return false;
        }
    });
}

function clearNoNum(obj) {
    obj.value = obj.value.replace(/[^\d.]/g, ""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字而不是
    obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
}

// 用于选车型的五步
(function($) {
    $.scrollauto = {
        inIt: function(jqCon, settings) {
            var index = 0;
            var conNum = jqCon.find("li").length;
            jqCon.css("width", conNum * settings.width);
            $("#" + settings.pageUp).click(function() {
                if (index > 0) {
                    index = index - settings.number;
                    $.scrollauto.effect(jqCon, index, settings);
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
                    $.scrollauto.effect(jqCon, index, settings);
                    if (settings.sc) settings.sc(index);
                }
                if (index >= conNum - settings.number) {
                    if (settings.lc) settings.lc(this);
                }
                $.scrollauto.loadPic(jqCon, index, settings);
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
    $.fn.scrollauto = function(options) {
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
        $.scrollauto.inIt(jqCon, settings);
    }
})(jQuery);

//车友社区的 车型 小图标链接。
function setCurrentModel(me) {
    var $controlCarTips = $(me).parents(".carTips");
    if ($controlCarTips.find(".currentautobrandid").val().length > 1) {
        $MyAutoModel.SetCurrentAutoModel(0, 0, $controlCarTips.find(".currentautomodelsubid").val(), $controlCarTips.find(".currentyear").val(), "", 0, false);
    }
}
//车友社区 展示  carTips
function BaoyangEvent() {
    var setTimeoutId1 = 0;
    var time = 300;
    $(".js_CarTips").hover(function() {
        clearTimeout(setTimeoutId1);
        $(".js_controlCarTips").find(".carTips").hide().removeClass(".z11");
        $(".js_controlCarTips").removeClass("z11");
        $(this).next(".carTips").show();
        $(this).parents(".js_controlCarTips").addClass("z11");
    }, function() {
        var $me = $(this);
        $me.next(".carTips").hover(function() {
            clearTimeout(setTimeoutId1);
        }, function() {
            $me.next(".carTips").hide();
            $(this).parents(".js_controlCarTips").removeClass("z11");
        });
        setTimeoutId1 = setTimeout(function() {
            $me.next(".carTips").hide();
            $(this).parents(".js_controlCarTips").removeClass("z11");
        },
       time);
    });
    $(".js_controlCarTips .carTips .js_gotoBaoyang,.js_controlCarTips .carTips .js_exclusive").bind("click", function() {
        setCurrentModel(this);
    });
}
setTimeout(function() {
    BaoyangEvent();

}, 0);

// 没选车型的处理。
function welcomeTipsControl() {
    //首页的特殊处理 有缓存的 直接用js 判断的。
    var hidIsHome = document.getElementById("hidIsHome"),
     isIE = !!window.ActiveXObject,
     isIE6 = isIE && !window.XMLHttpRequest,
     png = new Array('.welcomeTips');
    if (hidIsHome && hidIsHome.value == "1") {
        if ($State.GetValue("MyCurrentAutoModel") == "nothing" || $State.GetValue("MyCurrentAutoModel") == "") {
            if ($State.GetValue("MyAutoModelList").length > 0 || $State.GetValue("autoCarSelect").length < 0 || parseInt($State.GetValue("autoCarSelect"), 10) > 4) {
                $("#js_welcomeTips").hide();
            }
            else {
                $("#js_welcomeTips").show();
                if (isIE6) {
                    if (pngFix) {
                        pngFix(png);
                    }
                }
            }
            return;
        }
        else {
            $("#js_welcomeTips").hide();
            return;
        }
    }
}

function checkCookies() {

    //首页的特殊处理 有缓存的 直接用js 判断的。
    // var downloadid = document.getElementById("dd_mobiledownlayer"),
    //     isIE = !!window.ActiveXObject,
    //     isIE6 = isIE && !window.XMLHttpRequest,
    //     png = new Array('.mobileLayer'); 
    var cookies1 = $State.GetValue("GeneralCookies").split(",");
    var tcookies = cookies1[3];
    var tcookies2 = cookies1[2];
    if ($State.GetValue("GeneralCookies") != "" && tcookies == 1) {
        onclose3();
    }
    else {

        if ($("#hidmobiledownlayerhide").val() == "1") {
            return;
        }
        $("#dd_mobiledownlayer").show();
    }
}

function onclose3() {
    $("#dd_mobiledownlayer").hide();

    getUrl = "/handlers/defauldownloadtqipao.ashx";
    getData = "";
    $.ajax({
        type: "GET",
        url: getUrl,
        data: getData,
        cache: false,
        success: function(data) {
        },
        error: function(e) { }
    });
}
function checkNewsAddToCart(itemids) {
    newitemids = itemids;
    var url = window.location.href;
    $.ajax({
        type: "POST",
        url: "/handlers/news/matchlogin.ashx",
        data: { backurl: url },
        cache: false,
        success: function(data) {
            if (data != "ok") {

                $Layer.Reset();
                $Layer.IsBack = true;
                $Layer.IsFooter = false;
                $Layer.Width = 555;
                $Layer.Title = "";
                $Layer.CloseText = "关 闭";
                $Layer.Content = data;
                $Layer.IsConfirm = true;
                $Layer.Confirm.Text = "确 定";
                $Layer.Open();

            }
            else {
                var sc = new $UIShoppingCart();
                sc.NewsAddToCart(itemids);
            }
        },
        error: function(e) { }
    });
}









function BuilderParamsHover(clsName) {
    $("." + clsName).mouseover(function() { $(this).find(".boxTips").show(); $(this).addClass("z10"); })
                            .mouseout(function() { $(this).find(".boxTips").hide(); $(this).removeClass("z10") });
}
// 购物车弹出层
var shoppingCartLayer = {
    cartShow: function() {
        $("#js_navRights").hover(function() {
            $(this).find(".cartLayer").eq(0).show();
        }, function() {
            $(this).find(".cartLayer").eq(0).hide();
        });
    },
    deteleHandle: function() {
        $(".js_deleteitem").bind("click", function() {
            var message = "您确定要删除该产品吗？",
                 action = '"deletecart"',
                 selfDataList = $(this).attr("dataid").split(","),
                 itemid = selfDataList[0],
                 amsid = selfDataList[1],
                 year = selfDataList[2];
            MaintenanceId = selfDataList[3];
            ServiceType = selfDataList[4];
            autoparam = '"' + selfDataList[5] + '"';
            $UIMessage.Show(message, 306, 2, "shoppingCartLayer.CartDelete(" + action + "," + itemid + "," + amsid + "," + year + "," + MaintenanceId + "," + ServiceType + "," + autoparam + ")");
        });
    },
    CartDelete: function(action, itemid, amsid, year, MaintenanceId, ServiceType, autoparam) {
        $Layer.Close();
        $.ajax({
            type: "GET",
            url: "../shopping/handlers/shoppingcartprovider.ashx?action=" + action + "&itemid=" + itemid + "&amsid=" + amsid + "&syear=" + year + "&param=" + autoparam + "&maintenid=" + MaintenanceId + "&splitordertype=" + ServiceType,
            data: "",
            cache: false,
            success: function(data) {
                data == 'success' && $.ajax({
                    type: "GET",
                    cache: false,
                    url: '../handlers/itemlistprovider.aspx',
                    success: function(html) {
                        // 用于 多个 结算按钮的时候 点击其中一个单子 中清空 该车型所有商品 时候 ，判断 如果 这个时候 布置一个订单 去掉 改订单。
                        var sc = $UIShoppingCart.GetSingleCart();
                        sc.SetTotalCount();
                        $("#js_itemHandle").html(html);
                        shoppingCartLayer.deteleHandle();
                    }
                });
            }
        });
    }
};


function setautoModelSubID() {
    var ss = $(".clearfix1");
    ss.hover(function(event) {
        var trg = $(this).find(".nobackgroup").attr("name");
        $(this).append("<a id='update' class='change1' href='" + trg + "'>修改信息</a>").css("background", "#026ebb").find("a").css("color", "#fff");
    }, function() {
        $(this).css("background", "#ffffff ").find("a").css("color", "#246BA7"); ;
        $(".clearfix1 #update").remove();

    }
    )
}
$(function() {
    setautoModelSubID();
});



function VinInformation(AutoModelSubID, year, VinCode) {
    $.ajax({
        type: "GET",
        url: "/Handlers/VIN/vininformationhandler.ashx",
        cache: false,
        data: { VinCode: VinCode, autoModelSubID: AutoModelSubID, year: year },
        success: function(data) {
            $Layer.Reset();
            $Layer.IsBack = true;
            $Layer.IsFooter = false;
            $Layer.Width = 604;
            $Layer.Title = "";
            $Layer.CloseText = "关 闭";
            $Layer.Content = data;
            $Layer.IsConfirm = true;
            $Layer.Confirm.Text = "确 定";
            $Layer.Open();

            $("#btnload").click(function() {
                $("#image_file").click();
                $(".tipT").css("display", "block");
                $(".tipE").html("");
            })
        },
        error: function(e) { }
    })
}

//当上传图片改变时

function fileSelected(fileid, formid) {
    var iMaxFilesize = 5048576;
    var sResultFileSize = '';
    if (!fileid) {
        fileid = "image_file";
    }
    if (!formid) {
        formid = "upload_form";
    }
    curupdateid = fileid;

    //显示图片
    var s = $("#image_file").val();
    $(".button").hide();
    $("#txt").html("上传图片:" + s + "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='del();' id='del'>删除</a>");
    $("#btnphoto").css("display", "block");
    //分隔长度大于用省略号代替
    var sttr = $("#txt").html();
    if (sttr.length > 85) {
        var stg = sttr.substring(0, 15) + "..." + "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='del();' id='del'>删除</a>";
        $("#txt").html(stg);
    }
    else {
        $("#txt").html("上传图片:" + s + "&nbsp;&nbsp;<a href='javascript:void(0)' onclick='del();' id='del'>删除</a>");
    }

    // get selected file element
    var oFile = document.getElementById(fileid).files[0];
    // filter for image files
    var rFilter = /^(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)$/i;
    if (!rFilter.test(oFile.type)) {
        $(".tipE").css("display", "block").html("只允许上传图片");
        $("#btnload").show();
        $("#btnphoto").hide();
        return;
    }

    // little test for filesize
    if (oFile.size > iMaxFilesize) {
        $(".tipE").css("display", "block").html("上传图片超出5M大小");
        return;
    }

    var oReader = new FileReader();
    oReader.onload = function(e) {
        sResultFileSize = bytesToSize(oFile.size);
    };
    // read selected file as DataURL
    oReader.readAsDataURL(oFile);
    //    startUploading(formid);

}
function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB'];
    if (bytes == 0) return 'n/a';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
};


//删除图片
function del() {
    $("#txt").html("");
    $(".button").show();
    $("#btnphoto").css("display", "none");
    $(".tipE").css("display", "none");
    $(".tipT").css("display", "block");
    $(".loading").css("display", "none");
}

function submit() {
    var str = "<iframe id='uploadframe' name='uploadframe'></iframe>";
    $("body").append(str);
    document.getElementById("upload_form").submit();
    $("#btnphoto").hide();
    $(".loading").css("display", "block").show();
}
//手机二维码扫描下载
function mouseon() {
    $("#erweima").css("display", "block");
}
function mouseout() {
    $("#erweima").css("display", "none");
}


var User = "";
var Inte1;
//页面加载时判断抢码
$(function() {
    var jiandomain = websitePath.replace(/http:\/\/\w+\./, "").replace(/:\d+/, "");
    if (document.domain == jiandomain) {
        createAjaxIframe.appIframe("iframe", websitePath + "/IFrame/iframe.aspx",
    function() {
        openqiangma();
    });
    } else {
        openqiangma();
    }
})

function openqiangma() {
    $.ajax({
        type: "GET",
        cache: false,
        url: websitePath + "/handlers/promotion/robcodeHandler.ashx",
        success: function(data) {
            if (data != "" && data != null) {
                var User = data.split("/");
                if (User[1] == 0) {
                    $("#div_one").show();
                    if (User[0] != "error" && User[0] != null && User[0] != "") {
                        var time = User[0];
                        if (User[0] > 0 && User[0] <= 900) {
                            window.setInterval(function() {
                                time--;
                                $("#time1 span").html(time);
                                if (time < 0) {
                                    $("#div_one").css("display", "none");
                                }
                            }, 1000)
                        }
                        else {
                            $("#div_one").css("display", "none");
                        }
                    }
                }
                else if (User[1] == 2) {
                    $("#div_two").css("display", "block");
                    $(".input_white").html(User[2]);
                    var time = User[0];
                    if (User[0] > 0 && User[0] <= 300) {
                        if (Inte1) {
                            clearInterval(Inte1);
                        }

                        Inte1 = window.setInterval(function() {
                            time--;
                            $("#time2 span").html(time);
                            if (time < 0) {
                                $("#div_two").css("display", "none");
                            }
                        }, 1000)
                    }
                    else {
                        $("#div_two").css("display", "none");
                    }

                }
                else {
                    checkCookies();
                }

            }
        },
        error: function(e) {
        }
    })
}

//点击抢码
function RobcodeClickQiangMa() {
    $.ajax({
        type: "GET",
        cache: false,
        url: "/handlers/promotion/robcodeclickqiangmahandler.ashx",
        success: function(data) {
            var sttr = data.split("/");
            $("#div_one").css("display", "none");
            $("#div_two").css("display", "block");
            $(".input_white").html(sttr[1]);

            if (sttr[0] > 0 && sttr[0] <= 300) {
                var time = sttr[0];
                window.setInterval(function() {
                    time--;
                    $("#time2 span").html(time);
                    if (time < 0) {
                        $("#div_two").css("display", "none");
                    }
                }, 1000)
            }
            else {
                $("#div_two").css("display", "none");
            }

        },
        error: function(e) {
        }
    })
}

//点击关闭按钮
function RobcodeClickClose() {
    $UIMessage.Show("您只有一次抢码机会，关闭后就没了哦", 306, 2, 'Robcode()');

}
function Robcode() {
    $("#div_one").css("display", "none");
    $("#div_two").css("display", "none");
    $.ajax({
        type: "GET",
        cache: false,
        url: "/handlers/promotion/robcodeclickclosehandler.ashx",
        success: function(data) {
            $Layer.Close();
            return;
        },
        error: function(e) {
        }
    })
}



//UI改版js
$(function() {
    $("#js_uname #user1").mouseover(function() {
        $(".userLayer").show();
    });
    $(".userLayer").hover(function() {
        $(".userLayer").show();
    }, function() {
        $(".userLayer").hide();
    });

    $("#js_uname .message").mouseover(function() {
        $(".messageLayer").show();
    });
    $(".messageLayer").hover(function() {
        $(".messageLayer").show();
    }, function() {
        $(".messageLayer").hide();
    });


    //微信二维码展示
    $("#weixinQRCode").hover(
                function() {
                    $("#weixin2").show();
                    $("#weixinQRCode").addClass("cur");
                },
                function() {
                    $("#weixin2").hide();
                    $("#weixinQRCode").removeClass("cur");
                });


    //手机养车二维码展示
    $("#mobileQRCode").hover(
                function() {
                    $("#tmobile2").show();
                    $("#mobileQRCode").addClass("cur");
                },
                function() {
                    $("#tmobile2").hide();
                    $("#mobileQRCode").removeClass("cur");
                });

    //导航
    $(".navBox .nav a").each(function() {
        if ($($(this))[0].href == window.location.href) {
            $(this).addClass("cur");
            $(this).siblings().removeClass("cur");
            return;
        } else if (window.location.href.indexOf("/category/") > 0 || window.location.href.indexOf("//item.") > 0) {
            $(".navBox #hover .menu").addClass("cur");
        } else if (window.location.href.indexOf("/baoyang/") > 0) {
            $(".navBox .nav a:eq(1)").addClass('cur');
        }
    });
})



//UI改版js侧边栏跳动效果20150323
$(function() {
    //qq群入口 20150331
    $('div.qq').mouseover(function() {
        $('div.qq-detl-fix').css({ display: 'block' });
    });

    $('div.qq').mouseout(function() {
        $('div.qq-detl-fix').css({ display: 'none' });

    });
    //微信入口20150413
    $('div.wx').mouseover(function() {

        $('div.wxLayerC').css('display', 'block');
        $('div.wxLayer').css('display', 'block');
        $('div.brother').css('display', 'block')
    })
    $('div.wx').mouseout(function() {

        $('div.wxLayerC').css('display', 'none');
        $('div.wxLayer').css('display', 'none');
        $('div.brother').css('display', 'none');
    })


    //递归调用函数，实现侧边栏的跳动效果。     
    var animChange = function(cnum, mxnum) {
        isBlink = true;
        ++cnum;
        var topString = cnum % 2 == 1 ? "223px" : "237px";
        $(".layerSide").animate({ "top": topString }, 150, function() {
            if (cnum >= mxnum) {
                $(".layerSide").animate({ "top": "230px" }, 150);
                isBlink = false;
                return false;
            }
            else {
                animChange(cnum, mxnum);
            }

        });
    }

    //控制发生滚动事件时递归函数执行次数
    var isBlink = false;
    //滚动条滚动前的滚动条位置
    var s1 = $(window).scrollTop();
    $(window).bind('scroll', function() {
        //滚动条滚动后滚动条位置
        var s2 = $(window).scrollTop();
        //设置一个定时器判断一定时间后滚动条的前后状态（滚动or静止);根据条件判断是否执行递归函数。
        setTimeout(function() {
            if (s1 !== s2 && isBlink == false) {
                animChange(0, 4);
            }

        }, 600)
    });


})

function ReloadShaiBaoYang() {
    if (window.location.href.indexOf("www.") > 0) {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "../handlers/home/TogetherShaiBaoYang.aspx/GetRecomArticlehtml",
            data: "{}",
            dataType: "json",
            success: function(data) {
                $("#divShowShaiBaoYang").html(data.d);
                var showCount = 4;
                if (!ismaxwidthbool) {
                    showCount = 3;
                    $("#divShowShaiBaoYang dl:nth-child(4n)").remove();
                }
            }
        });
    }
};



$(function() {
    /*ie620150401start*/
    if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
        $('.feature').find('a:eq(2)').addClass('ie2');
        $('.feature').find('a:eq(2)').removeClass('cur');
    }
    $('.feature').find('a').each(function(index) {
        $(this).hover(function() {
            if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
                if (index == 0 || index == 1) {
                    $('.feature').find('a:eq(2)').removeClass('ie2');
                    $(this).addClass('ie' + index);
                } else {
                    $('.feature').find('a:eq(2)').addClass('ie2');
                    $('.feature').find('a:eq(0)').removeClass('ie0')
                    $('.feature').find('a:eq(1)').removeClass('ie1')
                }
            } else {
                $(this).addClass('cur');
                $(this).siblings().removeClass('cur');
            }


        }, function() {
            if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
                if (index == 0) {
                    $('.feature').find('a:eq(1)').removeClass('ie1');
                    $('.feature').find('a:eq(2)').removeClass('ie2');

                }
                if (index == 1) {
                    $('.feature').find('a:eq(0)').removeClass('ie0');
                    $('.feature').find('a:eq(2)').removeClass('ie2');

                }
                if (index == 2) {
                    $('.feature').find('a:eq(0)').removeClass('ie0');
                    $('.feature').find('a:eq(1)').removeClass('ie1');

                }

            }

        })

    })

    /*ie620150401end*/

    $('.switchQH a').each(function(index) {

        $(this).click(function() {
            switch (index) {
                case 0:
                    {
                        $('.switchAD').show();
                        $('.whatBox').hide();
                        $(this).addClass('selected');
                        $(this).siblings().removeClass('selected');

                    }
                    break;
                case 1:
                    {
                        $('.switchAD').hide();
                        $('.whatBox').show();
                        $(this).addClass('selected');
                        $(this).siblings().removeClass('selected');
                    }
                    break;
            }
        });

    })

})         
         
         
         