

usingNamespace("Web.UI")["ItemSearch"] = {
    CurrentIndex: 0,
    EventObj: {
        EventObjTxtId: ["txt-brand", "txt-cars", "txt-vehicle-emissions", "txt-startyear"],
        EventObjId: ["brand-arrow", "cars-arrow", "vehicle-emissions-arrow", "startyear-arrow"],
        EventTargetObjId: ["brand", "cars", "vehicle-emissions", "startyear"]
    },
    ClearData: function(txtId, autoMessage) {
        var index = $UIItemSearch.Index(txtId);
        for (; index < 3; index++) {
            $("#" + $UIItemSearch.EventObj.EventObjTxtId[index + 1]).html(autoMessage[index]).attr("name", "0");
            $("#" + $UIItemSearch.EventObj.EventTargetObjId[index + 1]).find("ul").empty();
        }
    },
    DataBind: function(targetId, txtId, data, autoMessage, callback) {
        var jqDataSources = $("#" + targetId).find("ul");
        if (data) {
            for (var i = 0, len = data.length; i < len; i++) {
                if (targetId == "cars") {
                    var icostr = "";
                    if (data[i].DefaultPicture != "" && data[i].DefaultPicture != null && data[i].DefaultPicture != undefined) {
                        icostr = "<a title='点击查看车型图' class='icon' target='_blank' href='" + websitePath + "/automodel/automodelpic.aspx?amid=" + data[i].Id + "' name='" + data[i].Id + "'></a>";
                    }
                    jqDataSources.append("<li><div class='caricon'>" + icostr + "<a  class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a></div></li>");
                } else if (targetId == "vehicle-emissions") {
                    var classname = "";
                    if (i == 1) {
                        classname = "def";
                    }
                    jqDataSources.append("<li><div class='caricon'><a class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a></div></li>");
                } else if (targetId == "startyear") {
                    var classname = "";
                    if (i == 1) {
                        classname = "def";
                    }
                    jqDataSources.append("<li><div class='caricon'><a class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a><a title='如何确定生产年份' class='" + classname + " eicon' href='" + websitePath + "/guide/production-year.html#autoyear' target='_blank'></a></div></li>");
                } else {
                    jqDataSources.append("<li><a class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a></li>");
                }

                //jqDataSources.append("<li><a class='autoitem' href='javascript:;' name='" + data[i].Id + "'>" + data[i].Name + "</a></li>");
            }
            jqDataSources.find("li a.autoitem").click(function(event) {
                var seletValue = $(this).html();
                var itemID = $(this).attr("name");
                var index = $UIItemSearch.Index(txtId);
                $("#" + txtId).html(seletValue).attr("name", itemID);
                if (callback) {
                    $UIItemSearch.ClearData(txtId, autoMessage);
                    callback(itemID, seletValue);
                    //                    if ($(this).parents(".sel_layer").siblings(".input").find("div").attr("id") == "txt-vehicle-emissions") {
                    //                        if (ResetYearValue)
                    //                            ResetYearValue("txt-startyear");
                    //                    }
                    $("#" + $UIItemSearch.EventObj.EventTargetObjId[index]).hide();
                    if (itemID != "0") {
                        $("#" + $UIItemSearch.EventObj.EventTargetObjId[index + 1]).show();
                        $UIItemSearch.CurrentIndex = index + 1;
                        event.stopPropagation();
                    }
                } else {
                    $("#" + $UIItemSearch.EventObj.EventTargetObjId[index]).hide();


                };
                if (txtId == "txt-startyear") {
                    if (isPackageList) {

                        //zhengf开始
                        var autobrandid = $("#txt-brand").attr("name");
                        var automodelid = $("#txt-cars").attr("name");
                        var automodelsubid = $("#txt-vehicle-emissions").attr("name");
                        var year = $("#txt-startyear").html();
                        $UIChildUserAutoModel.SetMyCurrAutoModel(autobrandid, automodelid, automodelsubid, year);
                        var test = "?" + Math.random();
                        var url = document.location.href;
                        document.location = url.substring(0, url.indexOf('.html') + 5) + test + "#check";
                        return false;
                        //zhengf结束

                        $UIItemSearch.AutoChoosed();
                        $UIItemSearch.RequestPackageList(subNavId, $("#txt-startyear").attr("name"), $("#txt-startyear").html());

                    }
                }
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
    //重新选择车型
    ChooseClick: function(url, isPackageList) {
        window.location.href = url;
        if (isPackageList) {
            $("#div_Choosed").empty();
            $("#txt-brands").attr("name", 0);
            $("#txt-brand").html("汽车品牌");
            $("#txt-cars").attr("name", 0);
            $("#txt-cars").html("车系");
            $("#cars").find("ul").empty();
            $("#txt-vehicle-emissions").attr("name", 0);
            $("#txt-vehicle-emissions").html("排量");
            $("#vehicle-emissions").find("ul").empty();
            $("#txt-startyear").attr("name", 0);
            $("#txt-startyear").html("生产年份");
            $("#startyear").find("ul").empty();
            $UIItemSearch.RequestPackageList(subNavId, 0, 0);
        }
    },
    Brand: function() {
        $.ajax({
            type: "Get",
            url: websitePath + "/handlers/auto/autobrand.ashx",
            data: "",
            success: function(data) {
                var autoMessage = ["车系", "排量", "生产年份"];
                $UIItemSearch.DataBind("brand", "txt-brand", data, autoMessage, $UIItemSearch.Cars);
            },
            error: function(e) { }
        });
    },
    Cars: function(id) {
        $.ajax({
            type: "GET",
            url: websitePath + "/handlers/auto/automodel.ashx?parentId=" + id,
            data: "",
            success: function(data) {
                var autoMessage = ["", "排量", "生产年份"];
                $UIItemSearch.DataBind("cars", "txt-cars", data, autoMessage, $UIItemSearch.VehicleEmissions);
            },
            error: function(e) { }
        });
    },
    VehicleEmissions: function(id) {
        $.ajax({
            type: "GET",
            url: websitePath + "/handlers/auto/automodelsub.ashx?parentId=" + id,
            data: "",
            success: function(data) {
                var autoMessage = ["", "", "生产年份"];
                $UIItemSearch.DataBind("vehicle-emissions", "txt-vehicle-emissions", data, autoMessage, $UIItemSearch.Startyear);
            },
            error: function(e) { }
        });
    },
    Startyear: function(id) {
        $.ajax({
            type: "GET",
            url: websitePath + "/handlers/auto/autoyears.ashx?parentid=" + id,
            data: "",
            success: function(data) {
                $UIItemSearch.DataBind("startyear", "txt-startyear", data);
                if (ResetYearValue)
                    ResetYearValue();
            },
            error: function(e) { }
        });
    },
    Status: function(jqListItem) {
        if (jqListItem.find("li").size()) {
            jqListItem.show();
        }
    },
    Index: function(id) {
        var $Search = $UIItemSearch;
        for (var len = $Search.EventObj.EventObjId.length; len--; ) {
            if ($Search.EventObj.EventObjId[len] == id || $Search.EventObj.EventObjTxtId[len] == id) {
                return len;
            }
        }
    },
    AddEvent: function() {
        var $Search = $UIItemSearch;
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
            var currentIndex = $Search.CurrentIndex;
            var jqListItem = $("#" + $Search.EventObj.EventTargetObjId[currentIndex]);
            var me = $(o);
            var layerBackground = document.getElementById("layerBackground");
            if (layerBackground == null) {
                if (!$(o).hasClass("eicon") && !$(o).hasClass("icon") && o["id"] != $Search.EventObj.EventTargetObjId[currentIndex] && o["id"] != $Search.EventObj.EventObjId[currentIndex] && o["id"] != $Search.EventObj.EventObjTxtId[currentIndex]) {
                    jqListItem.hide();
                }
            }
        });
    },
    Onclick1: function(itemid, id, valueId, message) {
        $("#" + id).unbind("click").click(function() {

            if (!$("#" + valueId).attr("name") || $("#" + valueId).attr("name") == "0") {
                $UIMessage.Show(message, 306, 1);
            } else {
                //zhengf 开始
                $UIChildUserAutoModel.SetMyCurrAutoModel($("#txt-brand").attr("name"), $("#txt-cars").attr("name"), $("#txt-vehicle-emissions").attr("name"), $("#txt-startyear").attr("name"));
                document.location.href = document.location.href;
                return false;
                //zhengf结束
            }
        });
    },
    //当前所选车型
    AutoChoosed: function() {
        $("#div_Choosed").show();
        $("#div_Choosed").html("<strong>您已选择：</strong><span>"
        + $("#txt-brand").html().substring(1, $("#txt-brand").html().length) + " "
        + $("#txt-cars").html() + " "
        + $("#txt-vehicle-emissions").html() + " "
        + $("#txt-startyear").html() + " "
        + "</span><a class='closepro' href='###' onclick='$UIItemSearch.ChooseClick(firstUrl,isPackageList)'></a><a href='###' onclick='$UIItemSearch.ChooseClick(firstUrl,isPackageList)'>重新选择车型</a>");
    },
    //请求套装详细信息
    CheckPrice: function(subNavId, packageId, id, valueId, message) {
        $("#" + id).click(function() {
            if ($("#" + valueId).html().Trim() == "生产年份") {
                $UIMessage.Show(message, 306, 1);
            } else {

                //zhengf 开始
                $UIChildUserAutoModel.SetMyCurrAutoModel($("#txt-brand").attr("name"), $("#txt-cars").attr("name"), $("#txt-vehicle-emissions").attr("name"), $("#txt-startyear").attr("name"));
                document.location = document.location.href;
                return false;
                //zhengf结束

                $UIItemSearch.AutoChoosed();
                $UIItemSearch.RequestPackageDetail(subNavId, packageId, $("#txt-startyear").attr("name"), $("#txt-startyear").html());
            }
        });
    },
    //请求套装详细信息
    RequestPackageDetail: function(subNavId, packageId, autoModelSubId, startYear) {
        $.ajax({
            type: "GET",
            url: websitePath + "/handlers/auto/myautocheckpackagedetail.aspx",
            data: "subnavid=" + subNavId
                  + "&packageId=" + packageId
                  + "&amsid=" + autoModelSubId
                  + "&syear=" + startYear,
            cache: false,
            success: function(data) {
                $("#div_AutoCheck").html(data);
                //绑定最大购买量
                new $UIShoppingCart().BindTxtEvent($("#inputValue"), packageId, 999);
                //绑定总价格
                $UIItemSearch.BindTotalPrice("#js-checklist", "#b_Price,", "#b_allPrice");
                AddPackageToCart(autoModelSubId, startYear);
                if ($("#div_PackageDetail").attr("name") != undefined) {
                    $.ajax({
                        type: "GET",
                        url: websitePath + "/item/itemspeparameters.aspx",
                        data: "itemids=" + $("#div_PackageDetail").attr("name"),
                        cache: false,
                        success: function(data) {
                            if (data != "error") {
                                $("#div_CheckParameters").html(data);
                            }
                        },
                        error: function(e) { }
                    });
                }
            },
            error: function(e) { }
        });
    },
    //请求套装列表
    RequestPackageList: function(subNavId, autoModelSubId, startYear) {
        $.ajax({
            type: "GET",
            url: websitePath + "/handlers/auto/myautocheckpackagelist.aspx",
            data: "subnavid=" + subNavId
                  + "&amsid=" + autoModelSubId
                  + "&syear=" + startYear,
            cache: false,
            success: function(data) {
                $("#div_AutoCheckList").html(data);
            },
            error: function(e) { }
        });
    },
    //绑定当前总价格
    BindTotalPrice: function(checkList, thisPrice, allPrice) {
        if ($(thisPrice).attr("name") != undefined) {
            var price = 0;
            $(checkList).find("input").each(function() {
                if ($(this).is(":checked")) {
                    price += Number($(this).parents("li").find("p").attr("name"));
                }
            })
            var thisPrice = Number($(thisPrice).attr("name"));
            $(allPrice).attr("name", (thisPrice + price).toFixed(2));
            $(allPrice).html("￥" + (thisPrice + price).toFixed(2) + "元");
        }
        else {
            $(allPrice).html("请先选择车型");
        }
    },
    //保养套装推荐商品
    PackageRecommendCheck: function(checkList, choosedCount, allPrice) {
        var domObj = document.getElementById(checkList);
        if (domObj) {
            $(domObj).find("input").each(function() {
                $(this).click(function() {
                    if ($(allPrice).html() != "请先选择车型") {
                        var price = 0;
                        if ($(this).is(":checked")) {
                            price = Number($(allPrice).attr("name")) + Number($(this).parents("li").find("p").attr("name"));
                        } else {
                            price = Number($(allPrice).attr("name")) - Number($(this).parents("li").find("p").attr("name"));
                        }
                        $(allPrice).html("￥" + price.toFixed(2) + "元");
                        $(allPrice).attr("name", price.toFixed(2));
                    }
                    $UIItemSearch.UpdateChoosedCount(checkList, choosedCount);
                });
            });
        }
    },
    //更新保养套装所选商品数量
    UpdateChoosedCount: function(checkList, choosedCount) {
        var number = $("#" + checkList).find("input:checked").length;
        $(choosedCount).html("已搭配：" + number + " 件商品");
    },
    //获取选中的推荐商品
    GetRecommendIds: function(checkList) {
        var price = "";
        $(checkList).find("input").each(function() {
            if ($(this).is(":checked")) {
                price += $(this).attr("name") + ",";
            }
        })
        if (price.length > 1) {
            price = price.substring(0, price.length - 1);
        }
        return price;
    }
}
var $UIItemSearch = Web.UI.ItemSearch;

usingNamespace("Web.UI")["Tab"] = {
    Hover: function(tab, tabParent, con, conParent) {
        var jqTab = $("." + tab).find("ul[id=jq-tab-nav]").find(tabParent);
        var jqTabcon = $("#" + con + ">" + conParent);
        jqTab.click(function() {
            var index = jqTab.index(this);
            jqTab.find("a").removeClass("cur");
            jqTab.find("a").eq(index).addClass("cur");
            jqTabcon.hide();
            jqTabcon.eq(index).show();
            return false;
        });
    },
    Onclick: function(itemid, id, message, message2) {
        $("#Consultation").click(function() {
            var jqMessage = $("#" + id);
            var value = $UITab.ReplaceHtml(jqMessage.val());
            if (value.length == 0) {
                $UIMessage.Show(message, 306, 1);
                return;
            }
            $.ajax({
                type: "POST",
                url: websitePath + "/handlers/Consultation.ashx",
                cache: false,
                data: "message=" + jqMessage.val() + "&itemid=" + itemid,
                success: function(data) {
                    if (data == "true") {
                        $UIMessage.Show(message2, 306, 1);
                        jqMessage.val("");
                    }
                    else if (data == "false") {
                        $UIMessage.Show(message, 306, 1);
                        jqMessage.val("");
                    }
                    else {
                        $UIMessage.Show(data, 306, 1);
                        jqMessage.val("");
                    }
                },
                error: function(e) { }
            });
        });
    },
    Onkeyup: function(id) {
        $("#" + id).keyup(function() {
            $UITab.CheckLength(this);
        });
    },
    Onkeydown: function(id) {
        $("#" + id).keydown(function() {
            $UITab.CheckLength(this);
        });
    },
    ReplaceHtml: function(value) {
        var r = /<.*?>/g;
        return value.replace(r, "");
    },
    CheckLength: function(which) {
        var maxChars = 400;
        if (which.value.length > maxChars) {
            which.value = which.value.substring(0, maxChars);
        }
    },

    combineFirstc: function(obj) {
        $(obj).addClass("noclick");
        if ($("#div_ItemCobineList li").length > 5) {
            $("#tjCombine_right").removeClass("noclick");
        }
    },
    combineLastc: function(obj) {
        $(obj).addClass("noclick");
        if ($("#div_ItemCobineList li").length > 5) {
            $("#tjCombine_left").removeClass("noclick");
        }
    },
    combineScollc: function() {
        $("#tjCombine_right,#tjCombine_left").removeClass("noclick");
    },

    // 组合商品
    MoveEvent: function() {
        // 赠品
        $("#div_ItemCobineList .gifts a").mouseover(function() {
            var obj = $(this).parent();
            var tempStr = $(this).next().html();
            $("#div_GiftContent").empty().html(tempStr);
            $("#div_GiftWarp").show();
            var left = 175 + (170 * obj.parents("li").attr("ref"));
            $("#div_GiftWarp").css("left", left + "px");

        });
        $(".gifts a").mouseout(function() {
            $("#div_GiftWarp").hide();
        });

        // 备注
        $(".a_ItemCombineMemo").mousemove(function() {
            $(this).parent().parent().find(".suitpro").show();
        }).mouseleave(function() {
            $(this).parent().parent().find(".suitpro").hide();
        });

        $("#a_UserReviewSorce").click(function() { $("#a_tabUserReview").click(); });
    }
}
var $UITab = Web.UI.Tab;
var $UIItem = Web.UI.Item;

function paging(id) {
    $.ajax({
        type: "GET",
        url: websitePath + "/handlers/customer/myconsultation.aspx",
        cache: false,
        data: "itemid=" + itemid + "&page=" + id,
        success: function(data) {
            $("#ulConsultation").html(data);
            document.documentElement.scrollTop = $("#questions").offset().top;
            document.body.scrollTop = $("#questions").offset().top;
            return false;
        }
    });
}

var reviewpaging = function(id, NotScrollTop,jquery1) {
    $.ajax({
        type: "GET",
        url: websitePath + "/handlers/customer/myitemreview.aspx",
        cache: false,
        data: "itemid=" + itemid + "&page=" + id,
        success: function(data) {
      
            $("#itemReview").html(data);
//            if (NotScrollTop == true) {
//                var userscore = $(".userscore")
//                userscore.html($("#yibuuserscore").html());
//            }
            if (NotScrollTop != true) {
                document.documentElement.scrollTop = $(".ccontent").offset().top;
                document.body.scrollTop = $(".ccontent").offset().top;
            }
            return false;
        }
    });
}

function userreview(id) {
    $.ajax({
        type: "GET",
        url: websitePath + "/handlers/review/userreview.ashx",
        cache: false,
        data: "itemid=" + id,
        success: function(data) {
            if (data == "jump") {
                window.location = websitePath + "/customer/review.aspx?itemid=" + itemid;
            }
            else {
                $UIMessage.Show(data, 306, 1);
            }
        }
    });
}

//添加保养套装到购物车
function AddPackageToCart(subId, startYear) {
    $("#btn_Add").click(function() {
        if ($("#div_PackageDetail").attr("name") == undefined) {
            $UIMessage.Show("请先选择车型查看价格", 306, 1);
            return;
        }
        if (new $UIShoppingCart().AddPackageToCart(packageId, subId, startYear, $("#inputValue").val(), function() { }, function(data) {
            $UIMessage.Show("您选择的数量已超出最大购买量", 306, 1);
        }
            ))
            window.location = shoppingCartUrl;
        return false;
    });
}

//添加推荐套装到购物车
function AddRecommendToCart() {
    $("#btn_AddRecommend").click(function() {
        if ($("#div_PackageDetail").attr("name") == undefined) {
            $UIMessage.Show("请先选择车型查看价格", 306, 1);
            return;
        }
        if ($("#js-checklist").find("input:checked").length < 1) {
            $UIMessage.Show("请选择推荐保养商品", 306, 1);
            return;
        }
        if (new $UIShoppingCart().AddPackageToCart(packageId, subId, startYear, $("#inputValue").val(), function() { }, function(data) {
            $UIMessage.Show("您选择的数量已超出最大购买量", 306, 1);
        })) {
            if (new $UIShoppingCart().AddPackageRecommendToCart($UIItemSearch.GetRecommendIds("#js-checklist"), function() { }, function(data) { $UIMessage.Show("您选择的数量已超出最大购买量", 306, 1); }))
            { window.location = shoppingCartUrl; }
        }
        return false;
    });
}


//到货通知
function NoticeMsg(itemID)
{
    $.ajax({
        type: "POST",
        url: "../handlers/noticearrival/getnoticearrivaldiv.ashx",
        cache: true,
        success: function(data) {
            $("#div_NoticeContent").empty();
            $Layer.Reset();
            $Layer.IsBack = true;
            $Layer.IsFooter = false;
            $Layer.Width = 450;
            $Layer.Title = "";
            $Layer.CloseText = "关 闭";
            $Layer.Content = data;
            $Layer.IsConfirm = true;
            $Layer.Confirm.Text = "确 定";
            $Layer.Open();
            $("#input_ItemID").val(itemID);
        },
        error: function(e) {
            $UIMessage.Show("系统错误！", 306, 1);
        }
    });
}
function CloseNoticeMsg()
{
    $Layer.Close();
}
function CheckInput()
{
    var flagcheck=true;
    if(!CheckEmail("#input_Email"))
    {
        flagcheck=false;
    }
    if(!CheckTelNumber("#input_Tel"))
    {
        flagcheck=false;
    }
    if(flagcheck)
    {
        var itemID = $("#input_ItemID").val(); 
        var txtEmail=$("#input_Email").val();
        var txtTel=$("#input_Tel").val();
         $.ajax({
            type: "POST",
            url: "../handlers/noticearrival/addnoticearrival.ashx",
            cache: false,
            data: "itemid=" + itemID+"&email="+txtEmail+"&mphone="+txtTel,
            success: function(data) {
                if (data == "success") {
                    $Layer.Close();
                    $UIMessage.Show("提交成功", 306, 1);
                    var AutoExit=setTimeout("$Layer.Close();",1000);
//                    var html="<div class='succeedlayer'><p>提交成功</p></div>";
//                    $Layer.Reset();
//                    $Layer.IsBack = true;
//                    $Layer.IsFooter = false;
//                    $Layer.Width = 300;
//                    $Layer.Title = "";
//                    $Layer.CloseText = "关 闭";
//                    $Layer.Content = html;
//                    $Layer.IsConfirm = true;
//                    $Layer.Confirm.Text = "确 定";
//                    $Layer.Open();
//                    var AutoExit=setTimeout("$Layer.Close();",1000);
                }
                else if (data == "repeat") {
                    $Layer.Close();
                    $UIMessage.Show("本商品您已订阅了到货通知！", 306, 1);
                }
                else if(data == "error")
                {
                    $Layer.Close();
                    $UIMessage.Show("系统错误！", 306, 1);
                }
                else if(data == "fail_email")
                {
                    $("#input_Email").next("i").html("E-mail 格式错误");
                }
                else if(data == "fail_mobile")
                {
                    $("#input_Tel").next("i").html("手机号码格式错误");
                }
            },
            error: function(e) { 
                    $UIMessage.Show("系统错误！", 306, 1);}
        });
    }
}

function GetItemCombine(ItemId,jquery1) {
    jquery1.ajax({
        type: "GET",
        url: websitePath + "/handlers/NewItem/ItemCombineProvider.aspx",
        cache: false,
        data: { itemid: ItemId },
        success: function(data) {
            if (data != "nodata") {
                $("#div_otherpro").html(data);
                $("#div_ItemCobineList").scroll({ number: 5, width: 181, pageUp: "tjCombine_left", pageNext: "tjCombine_right", claCon: "ul", noScroll: 5, fc: $UITab.combineFirstc, lc: $UITab.combineLastc, sc: $UITab.combineScollc });
                $("#tjCombine_left").addClass("noclick");
                if ($("#div_ItemCobineList li").length < 6) { $("#tjCombine_right").addClass("noclick"); }
            }
        },
        error: function(e) {
            //$UIMessage.Show("系统错误！", 306, 1);
        }
    });
}