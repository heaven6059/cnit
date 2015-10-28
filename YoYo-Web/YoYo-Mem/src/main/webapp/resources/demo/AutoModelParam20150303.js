usingNamespace("Web.UI")["AutoModelParam"] = {
    // mProjectId参数格式：“projectId|pjId,PjId”
    VinCodeErrorCount: 0,
    MProjectMatch: function(mProjectId, action, NotVinCode) {
   
        $.ajax({
            type: "GET",
            url: "/shopping/handlers/matchmproject.ashx",
            cache: false,
            data: { mprojectid: mProjectId, action: action, code: NotVinCode },
            success: function(data) {
                var message = data.split('^');
                switch (message[0]) {
                    case "match":
                        $AutoModelParam.MatchCallBack(ItemId);
                        break;
                    case "notmatch":
                        $UIMessage.Show("抱歉，本商品不适用于您所选的车型！", 306, 1);
                        break;
                    case "opendialog":
                        $Layer.Reset();
                        $Layer.IsBack = true;
                        $Layer.IsFooter = false;
                        $Layer.Width = 492;
                        $Layer.Title = "";
                        $Layer.CloseText = "关 闭";
                        $Layer.Content = message[1];
                        $Layer.IsConfirm = true;
                        $Layer.Confirm.Text = "确 定";
                        $Layer.Open();
                        $AutoModelParam.VinProccess(mProjectId, 1);
                        break;
                }
            },
            error: function(e) { }
        });
    },
    checkLogin: function(ItemId, action) {
        var url = window.location.href;
        $.ajax({
            type: "POST",
            url: "/shopping/handlers/matchlogin.ashx",
            data: { itemid: ItemId, action: action, backurl: url },
            cache: false,
            success: function(data) {
                if (data != "ok") {
                    $Layer.Reset();
                    $Layer.IsBack = true;
                    $Layer.IsFooter = false;
                    $Layer.Width = 430;
                    $Layer.Title = "";
                    $Layer.CloseText = "关 闭";
                    $Layer.Content = data;
                    $Layer.IsConfirm = true;
                    $Layer.Confirm.Text = "确 定";
                    $Layer.Open();

                }
                else {
                    $AutoModelParam.MatchSku(itemid, "");

                }

            },
            error: function(e) { }
        });
    },



    // 点击加入购物车触发
    MatchSku: function(ItemId, action, NotVinCode) {
        $.ajax({
            type: "GET",
            url: "/shopping/handlers/matchitem.ashx",
            cache: false,
            data: { itemid: ItemId, action: action, code: NotVinCode },
            success: function(data) {
                var message = data.split('^');
                switch (message[0]) {
                    case "match":
                        $AutoModelParam.MatchCallBack(ItemId);
                        break;
                    case "notmatch":
                    case "opendialog":
                        $Layer.Reset();
                        $Layer.IsBack = true;
                        $Layer.IsFooter = false;
                        $Layer.Width = 492;
                        $Layer.Title = "";
                        $Layer.CloseText = "关 闭";
                        $Layer.Content = message[1];
                        $Layer.IsConfirm = true;
                        $Layer.Confirm.Text = "确 定";
                        $Layer.Open();
                        $AutoModelParam.VinProccess(ItemId, 0);
                        break;
                }
            },
            error: function(e) { }
        });
    },
    VinProccess: function(ItemId, type) {
        if (document.getElementById("txt_vincode")) 
        {
            var vincode = document.getElementById("txt_vincode").value;
            if (vincode == "") {
                $UICommon.Watermark("txt_vincode", "输入行驶证或车架上车辆识别代码（VIN码）...");
            }

            $("#btn_subvincode").click(function() {
                var vincode = document.getElementById("txt_vincode").value;

                if (vincode == "" || vincode == "输入行驶证或车架上车辆识别代码（VIN码）...") {
                    alert("请输入VIN码");
                    return;
                }
                $AutoModelParam.PostVinCode(vincode, ItemId, type);
            });
        }
    },
    PostVinCode: function(VinCode, ItemId, type) {
        var mycarid = 0;
        if(type==2)
        {
            mycarid = document.getElementById("hidmycarid").value;
        }
        $.ajax({
            type: "POST",
            url: "/handlers/vin/postvincode.ashx",
            cache: false,
            data: { code: VinCode, garageid: mycarid },
            success: function(data) {
                var message = data.split('_');
                switch (message[0]) {
                    case "error":
                        var em_errormsg = $("#em_errormsg");
                        em_errormsg.html(message[1]).show();
                        $AutoModelParam.VinCodeErrorCount++;
                        if ($AutoModelParam.VinCodeErrorCount >= 2) {
                            if (document.getElementById("vinFeedback")) {
                                $("#vinFeedback").remove();
                            }
                            var amsid = document.getElementById("hidcurrentautomodelsubid").value;
                            var year = document.getElementById("hidcurrentyear").value;
                            $(".formLine").after("<div id=\"vinFeedback\" class=\"tipText\">如果您反复输入正确的信息，仍无法提交，你可以向我们<a href=\"javascript:;\" onclick=\"VinInformation(" + amsid + "," + year + ",'" + VinCode + "')\">反馈相关问题</a></div>");
                        }
                        break;
                    case "ok":
                        $("#em_errormsg").html("").hide();
                        if (type == 0) 
                        {
                            $AutoModelParam.MatchSku(ItemId, "param", 1)
                        } else if (type == 1) 
                        {
                            $AutoModelParam.MProjectMatch(ItemId, "param", 1)
                        } else if (type == 2) 
                        {
                            window.location.reload();
                        }
                        break;
                }
            },
            error: function(e) { }
        });
    },
    // 匹配调方法
    MatchCallBack: function(ItemId) {

    },
    // 修改当前车型
    UpdateCurrentAutoModel: function(param) {
    
        $.ajax({
            type: "GET",
            url: "/handlers/userautomodel/updateautomodelparam.ashx",
            cache: false,
            data: { param: param },
            success: function(data) {
                // 修改当前车型完成之后调用该方法
                $AutoModelParam.UpdateCurrentAutoModelComplete();
                
            },
            error: function(e) { }
        });
    },
    // 修改车型的参数
    UpdateCurrentAutoModelParam: function(param) {
    
        $AutoModelParam.UpdateCurrentAutoModel(param);
    },
    // 修改当前车型完成之后调用该方法
    UpdateCurrentAutoModelComplete: function() {
        var itemid = $("#hiditemid").val();

        $AutoModelParam.MatchSku(itemid, "param");
    },
    // 点击确认后触发的事件
    ConfirmClick: function(itemid) {
   
        $(".rorwinfo").show();
        if ($("#HidIsMatch").val() == "1") {
            $AutoModelParam.MatchCallBack(itemid);
        }
    },
    LayerClose: function() {
    
        $Layer.Close();
    }  
    
        
    
}

var $AutoModelParam = Web.UI.AutoModelParam;
