usingNamespace("Web.UI")["ChildUserAutoModel"] = {
    Inint: function() {
        $MyAutoModel.HaveChild = 1;
        $UIChildUserAutoModel.AddEvent();
    },
    AddEvent: function() {
        //把临时车型保存到我的车型库
        $("#myam_child_savetomyautomodel").click(function() {
            if (this.name != "") {
                var pam = this.name.split('_');
                $.ajax({
                    type: "GET",
                    cache: false,
                    url: "/handlers/UserAutoModel/UserAutoModelProvider.ashx?action=saveautomodel&abid=" + pam[0] + "&amid=" + pam[1] + "&amsid=" + pam[2] + "&syear=" + pam[3],
                    data: "",
                    success: function(data) {
                        var errmsg = data.split('_');
                        switch (errmsg[0]) {
                            case "success":
                                $MyAutoModel.SetCarsteWardNavigationUrl(pam[0], pam[1], pam[2], pam[3]);
                                $MyAutoModel.ResetMyAutoModel();
                                $UIChildUserAutoModel.ResetChildAutoModel();
                                alert("车型保存成功！");
                                break;
                            case "fail":
                                alert(errmsg[1]);
                                break;
                        }
                    },
                    error: function(e) { }
                });
            }
        })
        //切换车型
        $("#myam_child_btnchangeautomodel").unbind().click(function() {
            //车型优化
            function Refresh() {
                window.location.reload();
            }
            $MyAutoModel.CurrentChangeIsUpdate = false; //当前车型改变时不重新加载控件
            $MyAutoModel.CurrentAutoModelChange = function() {
                Refresh();
            }
            $MyAutoModel.selectCarControl(function() {
                $("#myam_childbox").append($("#myam_mycartype"));
                $MyAutoModel.addHtmlToDom();
                $MyAutoModel.MyamLocation = "myam_childbox";
                $("#myam_mycartype").toggle();
                $("#myam_panelschoosecar").show();
                $MyAutoModel.IsFirstRequest = $MyAutoModel.IsFirstRequest == 0 ? 1 : 2;
                $MyAutoModel.Inint();
            });
        })
        $("#startyear ul li a").click(function() {
            var autobrandid = $("#brand-arrow").attr("name");
            var automodelid = $("#cars-arrow").attr("name");
            var automodelsubid = $("#vehicle-emissions-arrow").attr("name");
            var year = $(this).attr("name");
            $UIChildUserAutoModel.SetMyCurrAutoModel(autobrandid, automodelid, automodelsubid, year);
        })
    },
    ResetChildAutoModel: function(callback) {
        $.ajax({
            type: "GET",
            cache: false,
            url: '/handlers/userautomodel/childuserautomodelprovider.aspx',
            success: function(html) {
                //$("#myam_mycartype").remove();
                $("#myam_childpanel").replaceWith(html)
                if ($MyAutoModel.MyamLocation == "myam_childbox") {
                    $("#myam_childbox").append($("#myam_mycartype"));
                }
                if (callback) callback();
            }
        });
    },
    SetMyCurrAutoModel: function(autobrandid, automodelid, automodelsubid, year) {
        if (autobrandid != 0 && automodelid != 0 && automodelsubid != 0 && year != 0) {
            $MyAutoModel.SetMyCurrentAutoModel("[" + autobrandid + "|" + automodelid + "|" + automodelsubid + "|" + year + "|||]");
        }
    }
}
var $UIChildUserAutoModel = Web.UI.ChildUserAutoModel;