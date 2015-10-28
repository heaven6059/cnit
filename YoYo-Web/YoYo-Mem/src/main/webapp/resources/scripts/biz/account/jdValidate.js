function pwdLevel(value) {
    var pattern_1 = /^.*([\W_])+.*$/i;
    var pattern_2 = /^.*([a-zA-Z])+.*$/i;
    var pattern_3 = /^.*([0-9])+.*$/i;
    var level = 0;
    if (value.length > 10) {
        level++;
    }
    if (pattern_1.test(value)) {
        level++;
    }
    if (pattern_2.test(value)) {
        level++;
    }
    if (pattern_3.test(value)) {
        level++;
    }
    if (level > 3) {
        level = 3;
    }
    return level;
}
// 休眠
function sleepm(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime) return;
    }
}
var weakPwdArray = ["123456", "123456789", "111111", "5201314", "123123", "password", "1314520", "123321", "7758521", "1234567", "5211314", "666666", "520520", "woaini", "520131", "11111111", "888888", "hotmail.com", "112233", "123654", "654321", "1234567890", "a123456", "88888888", "163.com", "000000", "yahoo.com.cn", "sohu.com", "yahoo.cn", "111222tianya", "163.COM", "tom.com", "139.com", "wangyut2", "pp.com", "yahoo.com", "147258369", "123123123", "147258", "987654321", "100200", "zxcvbnm", "123456a", "521521", "7758258", "111222", "110110", "1314521", "11111111", "12345678", "a321654", "111111", "123123", "5201314", "00000000", "q123456", "123123123", "aaaaaa", "a123456789", "qq123456", "11112222", "woaini1314", "a123123", "a111111", "123321", "a5201314", "z123456", "liuchang", "a000000", "1314520", "asd123", "88888888", "1234567890", "7758521", "1234567", "woaini520", "147258369", "123456789a", "woaini123", "q1q1q1q1", "a12345678", "qwe123", "123456q", "121212", "asdasd", "999999", "1111111", "123698745", "137900", "159357", "iloveyou", "222222", "31415926", "123456", "111111", "123456789", "123123", "9958123", "woaini521", "5201314", "18n28n24a5", "abc123", "password", "123qwe", "123456789", "12345678", "11111111", "dearbook", "00000000", "123123123", "1234567890", "88888888", "111111111", "147258369", "987654321", "aaaaaaaa", "1111111111", "66666666", "a123456789", "11223344", "1qaz2wsx", "xiazhili", "789456123", "password", "87654321", "qqqqqqqq", "000000000", "qwertyuiop", "qq123456", "iloveyou", "31415926", "12344321", "0000000000", "asdfghjkl", "1q2w3e4r", "123456abc", "0123456789", "123654789", "12121212", "qazwsxedc", "abcd1234", "12341234", "110110110", "asdasdasd", "123456", "22222222", "123321123", "abc123456", "a12345678", "123456123", "a1234567", "1234qwer", "qwertyui", "123456789a", "qq.com", "369369", "163.com", "ohwe1zvq", "xiekai1121", "19860210", "1984130", "81251310", "502058", "162534", "690929", "601445", "1814325", "as1230", "zz123456", "280213676", "198773", "4861111", "328658", "19890608", "198428", "880126", "6516415", "111213", "195561", "780525", "6586123", "caonima99", "168816", "123654987", "qq776491", "hahabaobao", "198541", "540707", "leqing123", "5403693", "123456", "123456789", "111111", "5201314", "123123", "12345678", "1314520", "123321", "7758521", "1234567", "5211314", "520520", "woaini", "520131", "666666", "RAND#a#8", "hotmail.com", "112233", "123654", "888888", "654321", "1234567890", "a123456"];

function verc() {
    $("#JD_Verification1").click();
}
function verc2() {
    $("#JD_Verification2").click();
}
var validateRegExp = {
    decmal: "^([+-]?)\\d*\\.\\d+$",
    // 浮点数
    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",
    // 正浮点数
    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",
    // 负浮点数
    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",
    // 浮点数
    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",
    // 非负浮点数（正浮点数 + 0）
    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",
    // 非正浮点数（负浮点数 +
    // 0）
    intege: "^-?[1-9]\\d*$",
    // 整数
    intege1: "^[1-9]\\d*$",
    // 正整数
    intege2: "^-[1-9]\\d*$",
    // 负整数
    num: "^([+-]?)\\d*\\.?\\d+$",
    // 数字
    num1: "^[1-9]\\d*|0$",
    // 正数（正整数 + 0）
    num2: "^-[1-9]\\d*|0$",
    // 负数（负整数 + 0）
    ascii: "^[\\x00-\\xFF]+$",
    // 仅ACSII字符
    chinese: "^[\\u4e00-\\u9fa5]+$",
    // 仅中文
    color: "^[a-fA-F0-9]{6}$",
    // 颜色
    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",
    // 日期
    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",
    // 邮件
    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$",
    // 身份证
    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",
    // ip地址
    letter: "^[A-Za-z]+$",
    // 字母
    letter_l: "^[a-z]+$",
    // 小写字母
    letter_u: "^[A-Z]+$",
    // 大写字母
    mobile: "^0?(13|15|18|14|17)[0-9]{9}$",
    // 手机
    notempty: "^\\S+$",
    // 非空
    password: "^.*[A-Za-z0-9\\w_-]+.*$",
    // 密码
    fullNumber: "^[0-9]+$",
    // 数字
    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",
    // 图片
    qq: "^[1-9]*[1-9][0-9]*$",
    // QQ号码
    rar: "(.*)\\.(rar|zip|7zip|tgz)$",
    // 压缩文件
    tel: "^[0-9\-()（）]{7,18}$",
    // 电话号码的函数(包括验证国内区号,国际区号,分机号)
    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",
    // url
    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$",
    // 户名
    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
    // 单位名
    zipcode: "^\\d{6}$",
    // 邮编
    realname: "^[A-Za-z\\u4e00-\\u9fa5]+$",
    // 真实姓名
    companyname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
    companyaddr: "^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
    companysite: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$"
};
// 主函数
(function($) {
    $.fn.jdValidate = function(option, callback, def) {
        var ele = this;
        var id = ele.attr("id");
        var type = ele.attr("type");
        var rel = ele.attr("rel");
        var _onFocus = $("#" + id + validateSettings.onFocus.container);
        var _succeed = $("#" + id + validateSettings.succeed.container);
        var _isNull = $("#" + id + validateSettings.isNull.container);
        var _error = $("#" + id + validateSettings.error.container);        
        if (def == true) {
            var str = ele.val();
            var tag = ele.attr("sta");
            if (str == "" || str == "-1") {
                validateSettings.isNull.run({
                    prompts: option,
                    element: ele,
                    isNullEle: _isNull,
                    succeedEle: _succeed
                },
                option.isNull);
            } else if (tag == 1 || tag == 2) {
                return;
            } else {
                callback({
                    prompts: option,
                    element: ele,
                    value: str,
                    errorEle: _error,
                    succeedEle: _succeed
                });
            }
        } else {
            if (typeof def == "string") {
                ele.val(def);
            }
            if (type == "checkbox" || type == "radio") {
                if (ele.attr("checked") == true) {
                    ele.attr("sta", validateSettings.succeed.state);
                }
            }
            switch (type) {
            case "text":
            case "password":
                ele.bind("focus",function() {
                    var str = ele.val();
                    if (str == def) {
                        ele.val("");
                    }
                    validateSettings.onFocus.run({
                        prompts: option,
                        element: ele,
                        value: str,
                        onFocusEle: _onFocus,
                        succeedEle: _succeed
                    },
                    option.onFocus, option.onFocusExpand);
                }).bind("blur",
                function() {
                    var str = $.trim(ele.val());
                    if (str == "") {
                        ele.val(def);
                    }
                    if (validateRules.isNull(str)) {
                        validateSettings.isNull.run({
                            prompts: option,
                            element: ele,
                            value: str,
                            isNullEle: _isNull,
                            succeedEle: _succeed
                        },
                        "");
                    } else {
                        callback({
                            prompts: option,
                            element: ele,
                            value: str,
                            errorEle: _error,
                            isNullEle: _isNull,
                            succeedEle: _succeed
                        });
                    }
                });
                break;
            default:
                if (rel && rel == "select") {
                    ele.bind("change",
                    function() {
                        var str = ele.val();
                        callback({
                            prompts: option,
                            element: ele,
                            value: str,
                            errorEle: _error,
                            isNullEle: _isNull,
                            succeedEle: _succeed
                        });
                    })
                } else {
                    ele.bind("click",
                    function() {
                        callback({
                            prompts: option,
                            element: ele,
                            errorEle: _error,
                            isNullEle: _isNull,
                            succeedEle: _succeed
                        });
                    })
                }
                break;
            }
        }
    }
})(jQuery);

// 配置
var validateSettings = {
    onFocus: {
        state: null,
        container: "_error",
        style: "focus",
        run: function(option, str, expands) {
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style2).addClass(validateSettings.INPUT_style1);
            }
            option.succeedEle.removeClass(validateSettings.succeed.style);
            option.onFocusEle.removeClass().addClass(validateSettings.onFocus.style).html(str);
            if (expands) {
                expands();
            }
        }
    },
    isNull: {
        state: 0,
        container: "_error",
        style: "null",
        run: function(option, str) {
            option.element.attr("sta", 0);
            if (!validateRules.checkType(option.element)) {
                if (str == "") {
                    option.element.removeClass(validateSettings.INPUT_style2).removeClass(validateSettings.INPUT_style1);
                } else {
                    option.element.removeClass(validateSettings.INPUT_style1).addClass(validateSettings.INPUT_style2);
                }
            }

            option.succeedEle.removeClass(validateSettings.succeed.style);
            if (str == "") {
                option.isNullEle.removeClass().addClass(validateSettings.isNull.style).html(str);
            } else {
                option.isNullEle.removeClass().addClass(validateSettings.error.style).html(str);
            }
        }
    },
    error: {
        state: 1,
        container: "_error",
        style: "error",
        run: function(option, str) {
            option.element.attr("sta", 1);
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style1).addClass(validateSettings.INPUT_style2);
            }

            option.succeedEle.removeClass(validateSettings.succeed.style);
            option.errorEle.removeClass().addClass(validateSettings.error.style).html(str);
        }
    },
    succeed: {
        state: 2,
        container: "_succeed",
        style: "succeed",
        run: function(option) {
            option.element.attr("sta", 2);
            option.errorEle.empty();
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style1).removeClass(validateSettings.INPUT_style2);
            }

            option.succeedEle.addClass(validateSettings.succeed.style);
            option.errorEle.removeClass();
        }
    },
    INPUT_style1: "highlight1",
    INPUT_style2: "highlight2"
}

// 验证规则
var validateRules = {
    isNull: function(str) {
    	str=$.trim(str);    	
        return (str == "" || typeof str != "string");
    },
    betweenLength: function(str, _min, _max) {
        return (str.length >= _min && str.length <= _max);
    },
    isUid: function(str) {
        return new RegExp(validateRegExp.username).test(str);
    },
    fullNumberName: function(str) {
        return new RegExp(validateRegExp.fullNumber).test(str);
    },
    isPwd: function(str) {
        return /^.*([\W_a-zA-z0-9-])+.*$/i.test(str);
    },
    isPwdRepeat: function(str1, str2) {
        return (str1 == str2);
    },
    isEmail: function(str) {
        return new RegExp(validateRegExp.email).test(str);
    },
    isTel: function(str) {
        return new RegExp(validateRegExp.tel).test(str);
    },
    isMobile: function(str) {
        return new RegExp(validateRegExp.mobile).test(str);
    },
    checkType: function(element) {
        return (element.attr("type") == "checkbox" || element.attr("type") == "radio" || element.attr("rel") == "select");
    },
    isRealName: function(str) {
        return new RegExp(validateRegExp.realname).test(str);
    },
    isCompanyname: function(str) {
        return new RegExp(validateRegExp.companyname).test(str);
    },
    isCompanyaddr: function(str) {
        return new RegExp(validateRegExp.companyaddr).test(str);
    },
    isCompanysite: function(str) {
        return new RegExp(validateRegExp.companysite).test(str);
    },
    simplePwd: function(str) {
        // var pin = $("#regName").val();
        // if (pin.length > 0) {
        // pin = strTrim(pin);
        // if (pin == str) {
        // return true;
        // }
        // }
        return pwdLevel(str) == 1;
    },
    weakPwd: function(str) {
        for (var i = 0; i < weakPwdArray.length; i++) {
            if (weakPwdArray[i] == str) {
                return true;
            }
        }
        return false;
    }
};
// 验证文本
var validatePrompt = {
    regName: {
        onFocus:"4-20位字符,支持汉字、字母、数字及\"-\"、\"_\"组合",
        succeed: "",
        isNull: "请输入用户名",
        error: {
            beUsed: "该用户名已被使用，请重新输入。如果您是该用户，请立刻<a href='https://passport.jd.com/uc/login' class='flk13'>登录</a>",
            badLength: "用户名长度只能在4-20位字符之间",
            badFormat: "用户名只能由中文、英文、数字及\"-\"、\"_\"组成",
            fullNumberName: "用户名不能是纯数字，请重新输入"
        },
        onFocusExpand: function() {
            $("#morePinDiv").removeClass().addClass("intelligent-error hide");
        }
    },
    oldPwd: {
        onFocus: "请输入原始密码",
        succeed: "",
        isNull: "请输入原始密码",
        error: ""
    },
    pwd: {
        onFocus: "<span>8-16位字符，支持大小写字母、数字和符号</span>",
        succeed: "",
        isNull: "请输入密码",
        error: {
            badLength: "密码长度只能在8-16位字符之间",
            badFormat: "密码只能由英文、数字及标点符号组成",
            simplePwd: "<span>该密码比较简单，有被盗风险，建议您更改为复杂密码，如字母+数字的组合</span>",
            weakPwd: "<span>该密码比较简单，有被盗风险，建议您更改为复杂密码</span>"
        },
        onFocusExpand: function() {
            $("#pwdstrength").hide();
        }
    },
    pwdRepeat: {
        onFocus: "请再次输入密码",
        succeed: "",
        isNull: "请确认密码",
        error: {
            badLength: "密码长度只能在8-16位字符之间",
            badFormat2: "两次输入密码不一致",
            badFormat1: "密码只能由英文、数字及标点符号组成"
        }
    },
    phone: {
        onFocus: "请输入手机号码",
        succeed: "",
        isNull: "请输入手机号码",
        error: ""
    },
    protocol: {
        onFocus: "",
        succeed: "",
        isNull: "请先阅读并同意《京东用户注册协议》",
        error: ""
    },
    empty: {
        onFocus: "",
        succeed: "",
        isNull: "",
        error: ""
    }
};

var nameold, morePinOld, emailResult;
var namestate = false;
// 回调函数
var validateFunction = {
    regName: function(option) {
        $("#intelligent-regName").empty().hide();
        var regName = option.value;
        if (validateRules.isNull(regName) || regName == '') {
            option.element.removeClass(validateSettings.INPUT_style2).removeClass(validateSettings.INPUT_style1);
            $("#regName_error").removeClass().empty();
            return;
        }
        $("#authcodeDiv").show();
    },
    oldPwd:function (option){
    	option.errorEle.hide();
    	validateSettings.succeed.run(option);
    },
    pwd: function(option) {
        var str1 = option.value;
        var regName = $("#regName").val();
        if ((validateRules.isNull(regName) == false) && (regName != '') && regName == str1) {
            $("#pwdstrength").hide();
            validateSettings.error.run(option, "<span>您的密码与账户信息太重合，有被盗风险，请换一个密码</span>");
            return;
        }

        //var str2 = $("#pwdRepeat").val();
        $("#pwdRepeat").blur();
        var format = validateRules.isPwd(option.value);
        var length = validateRules.betweenLength(option.value, 8, 16);

        $("#pwdstrength").hide();
        if (!length && format) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        } else if (!length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        } else if (length && !format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        } else if (validateRules.weakPwd(str1)) {
            validateSettings.error.run(option, option.prompts.error.weakPwd);
        } else {

            validateSettings.succeed.run(option);
            validateFunction.pwdstrength();
            if (validateRules.simplePwd(str1)) {
                $("#pwd_error").removeClass().addClass("focus");
                $("#pwd_error").empty().html(option.prompts.error.simplePwd);
                return;
            }
        }
        //		if (str2 == str1) {
        //			$("#pwdRepeat").focus();
        //		}
    },
    pwdRepeat: function(option) {
        var str1 = option.value;
        var str2 = $("#pwd").val();
        var length = validateRules.betweenLength(option.value, 8, 16);
        var format2 = validateRules.isPwdRepeat(str1, str2);
        var format1 = validateRules.isPwd(str1);
        if (!length) {
            validateSettings.error.run(option, option.prompts.error.badLength);
        } else {
            if (!format1) {
                validateSettings.error.run(option, option.prompts.error.badFormat1);
            } else {
                if (!format2) {
                    validateSettings.error.run(option, option.prompts.error.badFormat2);
                } else {
                    validateSettings.succeed.run(option);
                }
            }
        }
    },
    // mobileCode: function(option) {
    // var bool = validateRules.isNull(option.value);
    // if (bool) {
    // validateSettings.error.run(option, option.prompts.error);
    // return;
    // } else {
    // validateSettings.succeed.run(option);
    // }
    // },
    protocol: function(option) {
        if (option.element.attr("checked") == true) {
            option.element.attr("sta", validateSettings.succeed.state);
            option.errorEle.html("");
        } else {
            option.element.attr("sta", validateSettings.isNull.state);
            option.succeedEle.removeClass(validateSettings.succeed.style);
        }
    },
    pwdstrength: function() {    	
        var element = $("#pwdstrength");
        var value = $("#pwd").val();
        if (value.length >= 6 && validateRules.isPwd(value)) {
            $("#pwd_error").removeClass('focus');
            $("#pwd_error").empty();
            element.show();
            var level = pwdLevel(value);
            switch (level) {
            case 1:
                element.removeClass().addClass("strengthA");
                break;
            case 2:
                element.removeClass().addClass("strengthB");
                break;
            case 3:
                element.removeClass().addClass("strengthC");
                break;
            default:
                break;
            }
        } else {
            element.hide();
        }
    },
    checkGroup: function(elements) {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].checked) {
                return true;
            }
        }
        return false;
    },
    checkSelectGroup: function(elements) {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].value == -1) {
                return false;
            }
        }
        return true;
    },

    FORM_submit: function(elements) {
        var bool = true;
        for (var i = 0; i < elements.length; i++) {
            if ($(elements[i]).attr("sta") == 2) {
                bool = true;
            } else {
                bool = false;
                break;
            }
        }

        return bool;
    }
};

// 检查用户名
var checkpin = -10;
function checkPin(option) {
    var pin = option.value;
    if (!validateRules.betweenLength(pin.replace(/[^\x00-\xff]/g, "**"), 4, 20)) {
        validateSettings.error.run(option, option.prompts.error.badLength);
        return false;
    }

    if (!validateRules.isUid(pin)) {
        validateSettings.error.run(option, option.prompts.error.badFormat);
        return;
    }
    if (validateRules.fullNumberName(pin)) {
        validateSettings.error.run(option, option.prompts.error.fullNumberName);
        return;
    }
    if (!namestate || nameold != pin) {
        if (nameold != pin) {
            nameold = pin;
            option.errorEle.html("<em style='color:#999'>检验中……</em>");
            $.getJSON("../validateuser/isPinEngaged?pin=" + escape(pin) + "&r=" + Math.random(),
            function(date) {
                checkpin = date.success;
                if (date.success == 0) {
                    validateSettings.succeed.run(option);
                    namestate = true;
                } else if (date.success == 2) {
                    validateSettings.error.run(option, "用户名包含了非法词");
                    namestate = false;
                } else {
                    validateSettings.error.run(option, "<span>" + option.prompts.error.beUsed.replace("{1}", option.value) + "</span>");
                    namestate = false;
                    morePinOld = date.morePin;
                    if (date.morePin != null && date.morePin.length > 0) {
                        var html = ""
                        for (var i = 0; i < date.morePin.length; i++) {
                            html += "<div class='item-fore'><input name='morePinRadio' onclick='selectMe(this);' type='radio' class='radio' value='" + date.morePin[i] + "'/><label>" + date.morePin[i] + "</label></div>"
                        }
                        $("#morePinGroom").empty();
                        $("#morePinGroom").html(html);
                        $("#morePinDiv").removeClass().addClass("intelligent-error");
                    }
                }
            });
        } else {

            if (checkpin == 2) {
                validateSettings.error.run(option, "用户名包含了非法词");
            } else {
                validateSettings.error.run(option, "<span>" + option.prompts.error.beUsed.replace("{1}", option.value) + "</span>");
                if (morePinOld != null && morePinOld.length > 0) {
                    $("#morePinDiv").removeClass().addClass("intelligent-error");
                }
            }
            namestate = false;
        }
    } else {
        validateSettings.succeed.run(option);
    }
}

function selectMe(option) {
    $("#morePinDiv").removeClass().addClass("intelligent-error hide");
    $("#regName").val(option.value);
    $("#regName").blur();
}
// 主流程发送手机验证码
function sendMobileCode() {
}
// 手机注册发送验证码target
function sendmCode() {
    if ($("#sendMobileCode").attr("disabled") || delayFlag == false) {
        return;
    }
    var state = $("#state").val();
    if (state != "unbind") {
        $("#rebind").remove();
        $("#mobileCodeDiv").show();
    }
    $("#sendMobileCode").attr("disabled", "disabled");
    jQuery.ajax({
        type: "get",
        url: "../notifyuser/mobileCode?state=" + state + "&mobile=" + $("#phone").val() + "&r=" + Math.random(),
        success: function(result) {
            if (result) {
                var obj = eval(result);
                if (obj.rs == 1 || obj.remain) {
                    $("#mobileCode_error").addClass("hide");
                    $("#dyMobileButton").html("120秒后重新获取");
                    if (obj.remain) {
                        $("#mobileCodeSucMessage").empty().html(obj.remain);
                    } else {
                        if (state == "unbind") {
                            $("#mobileCode_error").removeClass().addClass("cue").empty().html("校验码已发送,注册成功后手机号将与原帐号解绑");
                            $("#mobileCode_error").show();
                        } else {
                            $("#mobileCode_error").removeClass().empty().html("验证码已发送，请查收短信。");
                            $("#mobileCode_error").show();
                        }
                    }

                    setTimeout(countDown, 1000);
                    $("#sendMobileCode").removeClass().addClass("btn btn-15").attr("disabled", "disabled");
                    $("#mobileCode").removeAttr("disabled");
                }
                if (obj.rs == -1) {
                    mobileCodeError("网络繁忙，请稍后重新获取验证码");
                }
                if (obj.info) {
                    if (obj.info == "该手机号已被使用，请更换号码") {
                        mobileEngagedStyle();
                    } else {
                        mobileCodeError(obj.info);
                    }

                }

                if (obj.rs == -2) {
                    mobileCodeError("网络繁忙，请稍后重新获取验证码");
                }
            }
        }
    });
}
// 邮箱验证发送验证码target
function sendmCode1() {
    if ($("#sendMobileCode1").attr("disabled") || delayFlag1 == false) {
        return;
    }
    $("#rebind1").remove();
    $("#mobileCodeDiv1").show();
    $("#sendMobileCode1").attr("disabled", "disabled");
    var state = $("#state").val();
    jQuery.ajax({
        type: "get",
        url: "../notifyuser/mobileCode?state=" + state + "&mobile=" + $("#phone1").val() + "&r=" + Math.random(),
        success: function(result) {
            if (result) {
                var obj = eval(result);
                if (obj.rs == 1 || obj.remain) {
                    $("#mobileCode1_error").addClass("hide");
                    $("#dyMobileButton1").html("120秒后重新获取");
                    if (obj.remain) {
                        $("#mobileCodeSucMessage1").empty().html(obj.remain);
                    } else {
                        if (state == "unbind") {
                            $("#mobileCodeSucMessage1").removeClass().addClass("cue").empty().html("校验码已发送,注册成功后手机号将与原帐号解绑");
                        } else {
                            $("#mobileCodeSucMessage1").empty().html("验证码已发送，请查收短信。");
                        }
                    }

                    setTimeout(countDown1, 1000);
                    $("#sendMobileCode1").removeClass().addClass("btn btn-15").attr("disabled", "disabled");
                    $("#mobileCode1").removeAttr("disabled");
                }
                if (obj.rs == -1) {
                    $("#mobileCode1_error").html("网络繁忙，请稍后重新获取验证码");
                    $("#mobileCode1_error").removeClass().addClass("error");
                    $("#mobileCode1_error").show();
                    $("#sendMobileCode1").removeClass().addClass("btn").removeAttr("disabled");
                }
                if (obj.info) {
                    if (obj.info == "该手机号已被使用，请更换号码") {
                        mobileEngagedStyle1();
                    } else {
                        $("#mobileCode1_error").html(obj.info);
                        $("#mobileCode1_error").removeClass().addClass("error");
                        $("#mobileCode1_error").show();
                        $("#sendMobileCode1").removeClass().addClass("btn").removeAttr("disabled");
                    }
                }

                if (obj.rs == -2) {
                    $("#mobileCode1_error").html("网络繁忙，请稍后重新获取验证码");
                    $("#mobileCode1_error").removeClass().addClass("error");
                    $("#mobileCode1_error").show();
                    $("#sendMobileCode1").removeClass().addClass("btn").removeAttr("disabled");
                }
            }
        }
    });
}
// 次流程发送手机验证码
function sendMobileCode1() {
}

var oldEmail, emailCheckResult;
// 邮箱验证信息填写
function sendRegMail() {
    var mail = $("#mail").val();
    var authcode1 = $("#authcode1").val();
    if (mail == "") {
        $("#mail_error").removeClass().addClass("error").html("请输入邮箱");
        $("#mail_error").show();
        $('#mail_succeed').addClass('error-ico');
        $('#mail').addClass('highlight2');
        return;
    }
    var email = strTrim(mail);
    var format = validateRules.isEmail(email);
    var format2 = validateRules.betweenLength(email, 0, 50);
    if (!format) {
        $("#mail_error").html("邮箱地址不正确，请重新输入");
        $('#mail_succeed').addClass('error-ico');
        $('#mail').addClass('highlight2');
        return;
    } else {
        if (!format2) {
            $('#mail_error').removeClass().addClass("error");
            $("#mail_error").html("邮箱地址长度应在4-50个字符之间");
            $('#mail_succeed').addClass('error-ico');
            $('#mail').removeClass("highlight1").addClass('highlight2');
            return;
        } else {
            // if (oldEmail == email) {
            // if (emailCheckResult == 1) {
            // emailEngagedStyle();
            // return;
            // }
            // if (emailCheckResult == 2) {
            // emailFormatErrorStyle();
            // return;
            // }
            // return;
            // }
            // oldEmail = email;
            $.getJSON("../validateuser/isEmailEngaged?email=" + escape(email) + "&r=" + Math.random(),
            function(result) {
                emailResult = result.success;
                emailCheckResult = emailResult;
                // 邮箱未被验证 可注册
                if (emailResult == 0) {
                    $("#emailMg").val(email);
                    $("#authcodeMg").val(authcode1);
                    jdThickBoxclose();
                    $("#dyMobileButton1").html("获取短信验证码");
                    jQuery.jdThickBox({
                        type: "text",
                        width: 500,
                        height: 260,
                        source: $('#box01').html(),
                        title: "验证手机",
                        _close_val: "×",
                        _con: "opinioncon",
                        _titleOn: true
                    });
                }
                if (emailResult == 1) {
                    emailEngagedStyle();
                    return;
                }
                if (emailResult == 2) {
                    emailFormatErrorStyle();
                    return;
                }
            });

        }
    }
}

function emailEngagedStyle() {
    $('#mail_succeed').addClass('error-ico');
    $('#mail_error').removeClass().addClass("error");
    $("#mail_error").html("该邮箱已被使用，请更换其它邮箱");
}

function emailFormatErrorStyle() {
    $('#mail_succeed').addClass('error-ico');
    $('#mail_error').removeClass().addClass("error");
    $("#mail_error").html("邮箱地址不正确，请重新输入");
}

// 邮箱验证 验证手机 提交注册
function mobileReg() {
    var mail = $("#emailMg").val();
    var authcode = $("#authcodeMg").val();
    var email = strTrim(mail);
    var format = validateRules.isEmail(email);
    var format2 = validateRules.betweenLength(email, 0, 50);
    if (!format) {
        $("#mail_error").html("邮箱地址不正确，请重新输入");
        return;
    } else if (!format2) {
        $("#mail_error").html("邮箱地址长度应在4-50个字符之间");
        return;
    }

    var mobile = $("#phone1").val();
    var phonevalue = $("#phone").val();
    var mobileCode = $("#mobileCode1").val();
    if (mobile == "") {
        $('#phone1').addClass('highlight2');
        $("#phone1_error").removeClass().addClass("error").html("请输入手机号");
        $("#phone1_error").show();
        $("#phone1_succeed").removeClass().addClass("blank error-ico");
    }

    if (mobileCode == "") {
        $('#mobileCode1').addClass('highlight2');
        $("#mobileCodeSucMessage1").empty();
        $("#mobileCodeSucMessage1").removeClass();
        $("#mobileCode1_error").html("请输入短信验证码");
        $("#mobileCode1_error").removeClass().addClass("error");
        $("#mobileCode1_error").show();
        return;
    }
    if (mobile == "") {
        $('#phone1').addClass('highlight2');
        $("#phone1_error").removeClass().addClass("error").html("请输入手机号");
        $("#phone1_error").show();
        $("#phone1_succeed").removeClass().addClass("blank error-ico");
        return;
    } else if (validateRules.isNull(mobile) || !validateRules.isMobile(mobile)) {
        $("#phone1_error").html("手机号码格式有误，请输入正确的手机号");
        $("#phone1_error").removeClass().addClass("error");
        $("#phone1_succeed").removeClass().addClass("blank error-ico");
        $("#phone1_error").show();
        $('#phone1').removeClass().addClass('text highlight2');
        $("#mobileCodeDiv1").show();
        mobileFlag = false;
        return;
    }
    var state = $("#state").val();
    if (state == "unbind") {
        mobileFlag = true;
    }
    if (mobileFlag) {
        var paramList = $("#personRegForm").serialize() + "&email=" + email;
        var temp = paramList.replace("phone=" + phonevalue, "phone=" + mobile);
        var params = temp.replace("mobileCode=", "mobileCode=" + mobileCode);
        params = params.replace("authcode=", "authcode=" + authcode);
        $.ajax({
            type: "POST",
            url: "../register/sendRegEmail?r=" + Math.random() + "&" + location.search.substring(1),
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: params,
            success: function(result) {
                var obj = eval(result);
                var emailResult = obj.success;
                var key = obj.k;
                if (emailResult == 0) {
                    jdThickBoxclose();
                    jQuery.jdThickBox({
                        type: "text",
                        width: 510,
                        height: 280,
                        source: '<div class="thickbox-tip fz14">' + '<div class="icon-box">' + '<span class="succ-icon m-icon"></span>' + '<div class="item-fore">' + '<div class="ftx-02 info-succ">账户更安全购物更放心</div>' + '</div>' + '</div>' + '<div class="msg-txt">' + '系统已向您的邮箱&nbsp;<strong class="ftx-01">' + $("#emailMg").val() + '</strong>&nbsp;发送了一封验证邮件，请您登录邮箱，点击邮件中的链接完成邮箱验证。如果超过2分钟未收到邮件，您可以<a href="#none" onclick="reSendEmail(\'' + $("#emailMg").val() + '\',\'' + key + '\');" class="ftx-05">重新发送</a>' + '</div>' + '<div class="mt10 ftx-01"> <span id="reSendEmailSuccess"></span></div>' + '<div class="mt20">' + '<a href="#" id="emailLogin" class="btn-red">登录邮箱</a>'
                        // +'<a href="#none"
                        // onclick="changeEmail();"
                        // class="ftx-05 fr">返回邮箱修改</a>'
                        + '<span class="clr"></span>' + '</div>' + '</div>',
                        title: "温馨提示",
                        _close_val: "×",
                        _con: "opinioncon",
                        _titleOn: true
                    });

                    initEmailLoginUrl(email);
                } else {
                    $("#mobileCodeSucMessage1").removeClass().empty();
                    $("#mobileCode1_error").html(obj.info);
                    $("#mobileCode1_error").removeClass().addClass("error");
                    $("#mobileCode1_error").show();
                    $("#sendMobileCode1").removeClass().addClass("btn").removeAttr("disabled");
                }
            }
        });
    }
}
function mobileCodeError(content) {
    $("#mobileCode_error").html(content);
    $("#mobileCode_error").removeClass().addClass("error");
    $("#mobileCode_error").show();
    $("#sendMobileCode").removeClass().addClass("btn").removeAttr("disabled");
}
function mobileCodeHide() {
    $("#mobileCode_error").html("");
    $("#mobileCode_error").removeClass().addClass("error");
    $("#mobileCode_error").hide();
}
var delayTime = 120;
var delayFlag = true;
function countDown() {
    delayTime--;
    $("#sendMobileCode").attr("disabled", "disabled");
    $("#dyMobileButton").html(delayTime + '秒后重新获取');
    if (delayTime == 1) {
        delayTime = 120;
        $("#mobileCodeSucMessage").removeClass().empty();
        $("#dyMobileButton").html("获取短信验证码");
        $("#mobileCode_error").addClass("hide");
        $("#sendMobileCode").removeClass().addClass("btn").removeAttr("disabled");
        delayFlag = true;
    } else {
        delayFlag = false;
        setTimeout(countDown, 1000);
    }
}
var delayTime1 = 120;
var delayFlag1 = true;
function countDown1() {
    delayTime1--;
    $("#sendMobileCode1").attr("disabled", "disabled");
    $("#dyMobileButton1").html(delayTime1 + '秒后重新获取');
    if (delayTime1 == 1) {
        delayTime1 = 120;
        $("#mobileCodeSucMessage1").removeClass().empty();
        $("#dyMobileButton1").html("获取短信验证码");
        $("#mobileCode1_error").removeClass().empty();
        $("#mobileCode1_error").hide();
        $("#sendMobileCode1").removeClass().addClass("btn").removeAttr("disabled");
        delayFlag1 = true;
    } else {
        delayFlag1 = false;
        countDown1.timer = setTimeout(countDown1, 1000);
    }
}
countDown1.timer = '';
function strTrim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

$("#regName").blur(function() {
    setTimeout(function() {
        if ($("#schoolid").val() == "") {
            $("#schoolinput").val("");
            $("#hnschool").val("-1");
            $("#hnschool").attr("sta", 0);
            $("#schoolinput_succeed").removeClass("succeed");
        } else {
            $("#hnschool").val("1");
            $("#hnschool").attr("sta", 2);
            $("#schoolinput_error").html("");
            $("#schoolinput_succeed").addClass("succeed");
        }
        $('#intelligent-school').hide().empty();
        $("#hnseli").val("-1");
    },
    200)
})

function showHideProtocol() {
    var protocolNode = $('.protocol-box');
    if (!protocolNode.is(':hidden')) {
        protocolNode.hide();
    } else {
        protocolNode.show();
    }
    return false;
}

function validateRegName() {
    var loginName = $("#regName").val();
    if (validateRules.isNull(loginName) || loginName == '') {
        $("#regName").val("");
        $("#regName").attr({
            "class": "text highlight2"
        });
        $("#regName_error").html("请输入用户名").show().attr({
            "class": "error"
        });
        return false;
    }
    return true;
}
$("#regist .tab li").hover(function() {
    if ($(this).hasClass("curr")) {} else {
        $(this).addClass("new");
    }
},
function() {
    if ($(this).hasClass("curr")) {} else {
        $(this).removeClass("new");
    }
})

$("#registsubmit").hover(function() {
    $(this).addClass("hover-btn")
},
function() {

    $(this).removeClass("hover-btn")
})

// 主流程手机获得焦点事件
function phoneFocus() {
    var mobile = $("#phone").val();
    if (oldMobile == mobile && mobile != "") {
        return;
    }
    $("#phone_succeed").removeClass("blank succeed");
    $('#phone').removeClass().addClass('text highlight1');
    $("#phone_error").removeClass().addClass("focus").html("完成验证后，您可以用该手机号登录和找回密码");
    $("#phone_error").show();
    $('#phone_succeed').removeClass('error-ico');
}
//主流程手机获得焦点事件
function phoneOtherFocus() {
    var mobile = $("#phone").val();
    if (oldMobile == mobile && mobile != "") {
        return;
    }
    $("#phone_succeed").removeClass("blank succeed");
    $('#phone').removeClass().addClass('text highlight1');
    $("#phone_error").removeClass().addClass("focus").html("请输入手机号码");
    $("#phone_error").show();
    $('#phone_succeed').removeClass('error-ico');
}
// 次流程手机获得焦点事件
function phone1Focus() {
    var mobile1 = $("#phone1").val();
    if (oldMobile1 == mobile1 && mobile1 != "") {
        return;
    }
    $("#phone1_succeed").removeClass();
    $('#phone1').removeClass().addClass('text highlight1');
    $("#phone1_error").removeClass().addClass("focus").html("完成验证后，您可以用该手机号登录和找回密码");
    $("#phone1_error").show();
    $('#phone1_succeed').removeClass('error-ico');
}

var oldMobile, mobileResult;
// 主流程检查手机
function phoneBlur() {
}
//主流程检查手机
function phoneKeyup() {
}

//主流程检查手机
function phoneOtherBlur() {
}
//主流程检查手机
function phoneOtherKeyup() {
    var mobile = $("#phone").val();
    var mobileLength=mobile.length;
    if(mobileLength != 11)
    {
    	return;
    }
    if (mobile == "") {
        $('#phone').removeClass().addClass('text');
        $("#phone_error").removeClass().html("");
        $("#phone_error").hide();
        $("#phone_succeed").removeClass().addClass("");
        oldMobile = mobile;
        mobileFlags = false;
        return;
    }
    if (oldMobile == mobile && mobile != "") {
        // 未修改手机号
        // showMobileCheckResult(mobileResult);
        return;
    }
    oldMobile = mobile;
    if (validateRules.isNull(mobile) || !validateRules.isMobile(mobile)) {
        $('#phone').removeClass().addClass('text highlight2');
        $("#phone_error").html("手机号码格式有误，请输入正确的手机号");
        $("#phone_error").removeClass().addClass("error");
        $("#phone_error").show();
        $("#phone_succeed").removeClass().addClass("");
        mobileFlags = false;
        return;
    }
}
function showMobileCheckResult(result) {
    if (result == 0) {
        mobileOkStyle();
    }
    if (result == 1) {
        mobileEngagedStyle();
    }
    if (result == 2) {
        mobileBindedStyle();
    }
}

function mobileOkStyle() {
    $('#phone').removeClass().addClass("text");
    $("#phone_error").html("");
    $("#phone_error").hide();
    $("#phone_succeed").removeClass().addClass("blank succeed");
    $("#mobileCode_error").removeClass().empty();
    $("#mobileCodeDiv").show();
    $("#rebind").remove();
    $("#mobileCodeDiv").show();
    mobileFlags = true;
}

function mobileBindedStyle(ub) {
    $('#phone').removeClass().addClass('text highlight2');
    $("#phone_error").html("该手机号已被其它账户绑定，"+ub+"天内不可改绑");
    $("#phone_error").removeClass().addClass("error");
    $("#phone_error").show();
    $("#phone_succeed").removeClass().addClass("");
    $("#rebind").remove();
    $("#mobileCodeDiv").show();
    mobileFlags = false;
}

function mobileEngagedStyle() {
    $('#phone').removeClass().addClass('text highlight3');
    $("#phone_error").html("手机号已注册，继续注册将与原账号解绑");
    $("#phone_error").removeClass().addClass("cue");
    $("#phone_error").show();
    $("#phone_succeed").removeClass().addClass("blank cue-ico");
    $("#rebind").remove();
    $('#dphone').after('<div class="item" id="rebind"> <span class="label">&nbsp;</span><div class="fl item-ifo item-ifo-extra"> <a href="javascript:;" onclick="unbind();" class="btn-comm"><span>继续注册</span></a> </div> </div>');
    $("#mobileCodeDiv").hide();
    mobileFlags = false;
}
function showMobileCheckResult1(result) {
    if (result == 0) {
        mobileOkStyle1();
    }
    if (result == 1) {
        mobileEngagedStyle1();
    }
    if (result == 2) {
        mobileBindedStyle1();
    }
}
function mobileOkStyle1() {
    $('#phone1').removeClass().addClass("text");
    $("#phone1_error").removeClass().addClass("success");
    $("#phone1_error").html("此手机号可用");
    $("#phone1_succeed").removeClass().addClass("blank succeed");
    $("#mobileCodeDiv1").show();
    $("#dmcode1").show();
    $("#rebind1").remove();
    mobileFlag = true;
    return;
}

function mobileBindedStyle1(ub) {
    $('#phone1').removeClass().addClass('text highlight2');
    $("#phone1_error").html("该手机号已被其它账户绑定，"+ub+"天内不可改绑");
    $("#phone1_error").removeClass().addClass("error");
    $("#phone1_succeed").removeClass().addClass("blank error-ico");
    $("#phone1_error").show();
    $('#phone1').removeClass("highlight1").addClass('highlight2');
    $("#sendMobileCode1").attr("disabled", "disabled");
    $("#mobileCodeDiv1").show();
    $("#rebind1").remove();
    mobileFlag = false;
    return;
}

function mobileEngagedStyle1() {
    $('#phone1').removeClass().addClass('text highlight3');
    $("#phone1_error").html("手机号已注册，继续注册将与原账号解绑");
    $("#phone1_error").removeClass().addClass("cue");
    $("#phone1_succeed").removeClass().addClass("blank cue-ico");
    $("#phone1_error").show();
    $("#rebind1").remove();
    $('#dphone1').after('<div class="item"  id="rebind1"><span class="label">&nbsp;</span><div class="fl item-ifo"><a href="javascript:void(0);" onclick="unbind1();"  class="btn btn-comm"><span>继续注册</span></a></div></div>');
    $("#mobileCodeDiv1").hide();
    mobileFlag = false;
    return;
}
// 次流程手机失去焦点事件
var mobileFlag = false;
var oldMobile1, mobileResult1;
function phone1Blur() {
}
function phone1Keyup() {
}
// 次流程短信验证码获得焦点事件
function mobileCodeFocus() {
    $('#mobileCode').removeClass().addClass('text text-1 highlight1');
    $("#mobileCode_error").hide();
}
// 次流程短信验证码获得失去事件
function mobileCodeBlur() {
    $('#mobileCode').removeClass().addClass("text text-1");
    $("#mobileCode_error").hide();
}
// 次流程短信验证码获得焦点事件
function mobileCode1Focus() {
    $('#mobileCode1').removeClass().addClass('text text-1 highlight1');
    $("#mobileCode1_error").hide();
}
// 次流程短信验证码获得失去事件
function mobileCode1Blur() {
    $('#mobileCode1').removeClass().addClass("text text-1");
    $("#mobileCode1_error").hide();
    $('#mobileCode1_succeed').removeClass('error-ico');
}
// 解绑按钮事件
function unbind() {
    $("#state").val("unbind");
    $("#mobileCodeDiv").show();
    $("#rebind").remove();
    // sendmCode();
    sendMobileCode();
}
// 次流程解绑按钮事件
function unbind1() {
    $("#state").val("unbind");
    $("#mobileCodeDiv1").show();
    $("#rebind1").remove();
    sendMobileCode1();
}