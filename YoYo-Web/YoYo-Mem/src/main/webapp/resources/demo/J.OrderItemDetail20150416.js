
//filter 这个是过滤集合 从上到下 从左到右 
//elms 需要过滤的元素集合 ；
//filterAttr doms 上面的过滤属性 值是多少
//filterName  过滤名字 例如 是 ID  还是 Sid

// stationList  安装店集合
//filter: [doms, filterName,filterAttr]

// 根据 hash 匹配找到行，其实也就是一个索引的关系

/*
hashMaps //暂时只能支持2个哈希表， 因为如果hash表够多的话 那么他的性能可能会受到影响， 你遍历hash表 也是需要时间的 该表在服务器段生成

单个hash 表的表结构 
filterId  根据id过滤 id 的值多少 存放的是过滤的id值
filterIdName  根据哪个列过滤的 名字 如果是根据id 来的那么就是 "id"  如果根据 安装店id 来的那么就是 "CSid" 
hashMap  哈希映射出来的关系表  存放的是下表 例如 0,1,2,3  这种
*/
//var o = {
//    stationList: [],
//    Map: Map, 
//    stationAppendLeftDom:stationAppendLeftDom
//};
var _ie6;
if (($.browser.msie) && ($.browser.version == "6.0")){
	_ie6=true;
}
var currValue;
var p;
function stationMapInit(o) {
    //地图对象
    this.Map = o.Map;
    // 安装店列表
    this.stationList = o.stationList;
    
  this.OuterRight=o.OuterRight || "";
    
    this.OuterLeft=o.OuterLeft||"";
    
    this.OnThisClass=o.OnThisClass;

    //生成过滤之后合作服战html代码 ；需要append给他的dom元素; JQ
    this.stationAppendLeftDom = o.stationAppendLeftDom;
    this.Domlis=this.stationAppendLeftDom.children();
    // 当前安装店的下标值 ? 这里应该设置为当前被选中的这个dom对象
    this.currStationElm = this.stationAppendLeftDom.children().eq(0);
    

    this.prevStationElm = null;
    
    //信息框
    this.baiduImfoWindow = [];
    
     //地图标记，
    this.baiduObject = [];  

    // 百度坐标点 
    this.baiduPoints = [];
    this.baiduMarker=[];
       
    this.__p=this.stationList==null?[]:this.stationList.split("|");
    p=this.__p;
    
    this.length = this.__p.length || 0;
    var i = 0, _h = 0;
    var point;
    for (; i < this.length; i++) {
        var __arr=this.__p[i].split(",");
        
        point = new BMap.Point(__arr[0], __arr[1]);
        point.markerNum=this.__p[i].split(",")[3];
        this.baiduPoints.push(point);
        var marker = new CopStaOverlay(point,"查看 "+this.__p[i].split(",")[2] + " 人工费",this.__p[i].split(",")[3]);
        this.baiduMarker.push(marker);
        this.baiduObject.push(marker); 
    }
    //判断是否为搜索
    var keyword=$("#hidkeyword").val();
    if(keyword==null||keyword==""){
      $UICommon.Watermark("txt_last_receive_address", "例如:曹安公路");  
    }
    
    i = 0;
    
    this.initMapBaner();
    
    this.Map.addControl(new BMap.NavigationControl());
    this.Map.addControl(new BMap.ScaleControl({ anchor: BMAP_ANCHOR_BOTTOM_LEFT }));
    this.Map.enableScrollWheelZoom();
    var that=this;
    this.Map.addEventListener("dragend",function(){
      //var self=that;
          setTimeout(function(){
          that.AutoRight(that);},500)
    });
    this.Map.addEventListener("zoomend",function(){
        //var self=that;
        setTimeout(function(){
          that.AutoRight(that);}
        ,1000)
    });
    //如果直接传进来的是当前安装店的下表那么就覆盖上面的下标
    //    if (o.currViewIDs instanceof Array) {
    //        this.currViewMap = o.currViewIDs;
    //    }

    
    
}
stationMapInit.prototype = {
    initMapBaner: function() {
        var that = this;
        //设置地图中心
//        setTimeout(function() {
            //右侧列表为空定位上海
            if (p == null || p == "" || p == "&gt;") {
                var map = new BMap.Map("map_left");
                map.centerAndZoom("上海", 11);
                map.addControl(new BMap.NavigationControl());
                map.addControl(new BMap.ScaleControl({ anchor: BMAP_ANCHOR_BOTTOM_LEFT }));
                map.enableScrollWheelZoom();
            }
            that.SetMapViewCenter();
//        }, 0)

        //重新生成a标签
//        setTimeout(function() {
            that.RecreateMarker();
//        }, 0)

        ////初始化dom元素鼠标上移事件
        setTimeout(function() {
            that.initDomMouseOverEvent();
        }, 0)

    },
    //重新
    RecreateMarker: function() {
        //清楚覆盖物
        this.Map.clearOverlays();

        var len = this.baiduMarker.length;
        for (var n = 0; n < len; n++) {

            var marker = this.baiduMarker[n];
            this.Map.addOverlay(marker);
            marker.addEventListener("click", this.__p[n].split(",")[3]);
            marker.EventListener();
            // 如果 showAll ==1 那么这里必须将所有的marker 都设置成灰色
        }
    },
    // 创建一个信息框
    CreateInfoWindow: function(n, b) {
        var size = new BMap.Size(0, -31);
        //商户名称
        var displayname=this.Domlis.eq(n).find("#lista_"+n).html();
        //ID ,automodelID ,IsSearch,IsCollect,ServiceID
        var  sttrId=this.Domlis.eq(n).find("#lista_"+n).attr("data-sttr").split("_");
        //endtime ,realName,address
        var sttrValue=this.Domlis.eq(n).find("#lista_"+n).attr("data-value").split("_");
        //技术等级
        var sttrtech=this.Domlis.eq(n).find("#tech_"+n).find(".techdiv").html();
        //好评
        var sttrnStar=this.Domlis.eq(n).find("#nStar_"+n).html();
        
//        var html = this.Domlis.eq(n).find("#baidumapqipao_"+n).val();
//        var htmlLabel = this.Domlis.eq(n).find("#baidumapqiaodiv_"+n).val();
//        var sttr=html.split("_");
        var  htmlContent="<div style=\"width:310px\" class=\"gp\">"+
                       "<b>"+
                       "<a hidefocus=\"true\" class=\"hidefocus\"  href=\"javascript:;\" onclick=\"ShowDetatil("+sttrId[0]+","+sttrId[1]+","+sttrId[2].toLocaleLowerCase()+")\">"+displayname+"</a>"+baiduqipaohtml(sttrId[3].toLocaleLowerCase())+baiduyouhuiHtml(sttrId[4],sttrValue[0])+
                        "</b>"+baidurealNamehtml(sttrId[2].toLocaleLowerCase(),sttrValue[1])+
                       "<div class=\"info\">"+sttrValue[2]+"</div>"+
                       "<div class=\"tech\">"+
                       "<div class=\"view\">"+
                       "<a href=\"javascript:;\"  onclick=\"ShowDetatil("+sttrId[0]+","+sttrId[1]+","+sttrId[2].toLocaleLowerCase()+")\">查看人工费</a>"+
                       "</div>"+
                       "<span>技术等级：</span>"+
                       "<div class=\"techdiv\">"+sttrtech+"</div>"+
                       "<div class=\"c\"></div>"+
                       "</div>"+
                       "<div class=\"nStar\" id=\"nStar1\">"+
                       "<div class=\"sub\"><a href=\"javascript:;\" onclick=\"Btn_SpecialInstallationShop("+sttrId[0]+")\">确定选择</a></div>"+sttrnStar+"</div>"+
                       "</div>";
                       
        //var html = (this.OuterLeft ? this.OuterLeft : "") + (this.Domlis.eq(n).html()) + (this.OuterRight ? this.OuterRight : "");
        //        if (b) {
        //            html = html.replace("display:block", "display:none");
        //        }
        return new BMap.InfoWindow(htmlContent, { offset: size });
    }
    ,
    // 点击覆盖物打开信息框
    BindMarkerEvent: function(marker, point, index) {
        var iw = this.CreateInfoWindow(index);
        //this.baiduImfoWindow.push(iw);
        var that = this;
        this.BindInfoWindow(iw, marker);
        marker.addEventListener('click', function() {
            that.Map.openInfoWindow(iw, point);
        });
    },
    //对信息窗绑定事件 打开和关闭的事件
    BindInfoWindow: function(iw, marker) {
        var that = this;
        iw.addEventListener('open', function() {
            marker.setStyle("selected");
            marker._isOpen = true;
        });
        iw.addEventListener('close', function() {
            //alert("close")
            marker.setStyle();
            marker._isOpen = false;
        });
    }
    //设置地图中心
    , SetMapViewCenter: function() {
        var viewport = this.Map.getViewport(this.baiduPoints);

        this.Map.setViewport(viewport);
        // if($(".city > strong").html().indexOf("上海")>-1)
        this.Map.setZoom(11);
    }
,
    initDomMouseOverEvent: function() {
        var lis = this.Domlis;
        var that = this;
        var map = this.Map;
        //window.nnn=0;
        lis.mouseenter(function(e) {

            
            if (that.Timer) {
                clearTimeout(that.Timer);
            }
            if (e.stopPropagation)
                e.stopPropagation();
            else {
                //e.cancleBuble =true;
                e.returnValue = false;
            }
            var selfDom = this;
            that.Timer = setTimeout(function() {
                if (that.currStationElm != null) {
                    that.currStationElm.removeClass(that.OnThisClass);
                }
                that.prevStationElm = that.currStationElm;

                that.currStationElm = $(selfDom).addClass(that.OnThisClass);

                // that.currStationElm  =  $(this);
                //var currMapID = $(this).attr("data-map");

                var currMapID = lis.index($(selfDom));
                if (!that.baiduObject[currMapID]._isOpen) {

                    //var p = that.baiduPoints[currMapID];
                    //that.Map.openInfoWindow(that.baiduImfoWindow[currMapID], p); 

                    var __arr = that.__p[currMapID].split(",");
                    var point = new BMap.Point(__arr[0], __arr[1]);
                    var iw = that.CreateInfoWindow(currMapID, true);

                    map.openInfoWindow(iw, point);
                    $("#map_left .techLayer").hide();

                    //var ss= setTimeout(function(){ 
                    //that.Map.openInfoWindow(iw, point); 
                    //},10);        
                }
            }, 200);
        });
    },
    relase: function() {
        this.Map.clearOverlays();
        for (var n = 0; n < this.length; n++) {
            this.baiduObject[n].removeEventListener("click");
            this.baiduObject[n] = null;
        }
        for (var n = 0; n < this.length; n++) {
            this.baiduPoints[n] = null;
        }
        this.baiduPoints = null;
        this.baiduObject = null;
    }
    ,
    AutoRight: function(obj) {
        if (!obj.RightList) {
            if ($("#ulList > .copS").length > 0) {
                obj.RightList = $("#ulList > .copS");
            }
            else if ($("#gddivhelp > div").length > 0) {
                obj.RightList = $("#gddivhelp > div");
            }
        }
        if (!obj.RightList) {
            return false;
        }
        obj.RightList.show();
        //var cm=obj.Map.getOverlays(),len=cm.length;

        //        var o = obj.Map.getBounds();
        //        for (var i = 0; i < obj.length; i++) {
        //            if (!o.containsPoint(obj.baiduPoints[i])) {
        //                $("#li_help_" + parseInt(obj.baiduPoints[i].markerNum)).hide();
        //            }
        //        }
        var o = obj.Map.getBounds();
        var mathType = $("#MathTab .in").attr("data-value");
        var mathValue = $("#MathTab .in").attr("name");
        var PageType = $("#js_pageType").val();
        for (var i = 0; i < obj.length; i++) {
            var li_help_me = $("#li_help_" + parseInt(obj.baiduPoints[i].markerNum));
            if (!o.containsPoint(obj.baiduPoints[i]) || (li_help_me.attr("data-math") != mathType && mathType != -1)) {
                li_help_me.hide();
            }
        }

        // this.baiduPoints.push(point);

        //        for(var n=0;n<len;n++){
        //            $("#li"+cm[n].GetMarkedID()).show();
        //        }
        var lis = $("#ulList").children(".copS");
        if (lis.length <= 0) {
            lis = $("#gddivhelp").children("div");
            $("#helpnontposition").show();
        }
        if (mathType == 1) {
            if (PageType != "detail") {
                if (mathValue == -1) {
                    $("#helpnontposition").text("亲，当前地图区域内没有安装店~").show();
                }
                else {

                    $("#helpnontposition").text("亲，当前地图区域内没有能够做全部保养的安装店~").show();
                }
            }
            else {
                $("#helpnontposition").text("亲，当前地图区域内没有安装店~").show();
            }
        }
        else if (mathType == 0) {
            $("#helpnontposition").text("亲，当前地图区域内没有能够做部分保养的安装店~").show();
        }
        else {
            $("#helpnontposition").text("亲，当前地图区域内没有安装店~").show();
        }
        for (var nn = 0; nn < lis.length; nn++) {
            if ($(lis[nn]).css("display") === "list-item" || $(lis[nn]).css("display") === "block" || $(lis[nn]).css("display") === "inline-block") {
                $("#helpnontposition").hide();
                break;
            }
        }

    }

};

//合作安装店覆盖物
function CopStaOverlay(point,title,CSID) {
    this._point = point; //覆盖物经纬度坐标
    this._title=title;
    this._CSID=CSID;
    this._isOpen = false; //信息窗打开状态
}

CopStaOverlay.prototype = new BMap.Overlay();

//重写初始化函数
CopStaOverlay.prototype.initialize = function (map) { 
    this._map = map;
    
    //覆盖物主体div      
    var div = this._div = document.createElement("div");
    //设置div静态样式
    div.title=this._title;
    div.className = "copStaOverlay";
    div.setAttribute("id","s"+ parseInt( this._CSID));
    div.setAttribute("data-id", parseInt( this._CSID));
    
    if(_ie6)
        div.style.background="url(about:blank)";
    //$(div).css("_background","url(about:blank)");
    //div.getAttribute("data-id")
    //设置div动态样式
    div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
    
    this.originzIndex = BMap.Overlay.getZIndex(this._point.lat);
    var that = this;
     div.onmouseover = function () {
    }

    div.onmouseout = function () {
    }

    map.getPanes().labelPane.appendChild(div);

    return div;
}
//
CopStaOverlay.prototype.GetMarkedID = function () {
    return this._div.getAttribute("data-id");
}


//设置选入样式 蓝色
CopStaOverlay.prototype.setInStyle = function () {
    this._div.className = "copStaOverlayIn";
    this._div.style.zIndex = "10000000";
    var currentId=this._div.getAttribute("data-id");
    //商户名称
    var displayname=$("#li_help_"+currentId).find(".lista_"+currentId).html();
    //ID ,automodelID ,IsSearch,IsCollect,ServiceID
    var  sttrId=$("#li_help_"+currentId).find(".lista_"+currentId).attr("data-sttr").split("_");
    //endtime ,realName,address
    var sttrValue=$("#li_help_"+currentId).find(".lista_"+currentId).attr("data-value").split("_");
    //技术等级
    var sttrtech=$("#li_help_"+currentId).find(".tech_"+currentId).find(".techdiv").html();
    //好评
    var sttrnStar=$("#li_help_"+currentId).find(".nStar_"+currentId).html(); 
//    var html=$("#li_help_"+currentId).find(".baidutag_"+currentId).val();
//    var htmlLabel =$("#li_help_"+currentId).find(".baidudivtag_"+currentId).val();
//    var sttr=html.split("_");
             var  htmlContent="<div style=\"width:310px\" class=\"gp\">"+
                       "<b>"+
                       "<a hidefocus=\"true\" class=\"hidefocus\"  href=\"javascript:;\" onclick=\"ShowDetatil("+sttrId[0]+","+sttrId[1]+","+sttrId[2].toLocaleLowerCase()+")\">"+displayname+"</a>"+baiduqipaohtml(sttrId[3].toLocaleLowerCase())+baiduyouhuiHtml(sttrId[4],sttrValue[0])+
                        "</b>"+baidurealNamehtml(sttrId[2].toLocaleLowerCase(),sttrValue[1])+
                       "<div class=\"info\">"+sttrValue[2]+"</div>"+
                       "<div class=\"tech\">"+
                       "<div class=\"view\">"+
                       "<a href=\"javascript:;\"  onclick=\"ShowDetatil("+sttrId[0]+","+sttrId[1]+","+sttrId[2].toLocaleLowerCase()+")\">查看人工费</a>"+
                       "</div>"+
                       "<span>技术等级：</span>"+
                       "<div class=\"techdiv\">"+sttrtech+"</div>"+
                       "<div class=\"c\"></div>"+
                       "</div>"+
                       "<div class=\"nStar\" id=\"nStar1\">"+
                       "<div class=\"sub\"><a href=\"javascript:;\" onclick=\"Btn_SpecialInstallationShop("+sttrId[0]+")\">确定选择</a></div>"+sttrnStar+"</div>"+
                       "</div>";
                       
    
    
    var num=$("#li_help_"+currentId).attr("data-lat");
    var __arr=num.split("_");
    point = new BMap.Point(__arr[0], __arr[1]);
    var size = new BMap.Size(0, -31);   
    var iw = new BMap.InfoWindow(htmlContent, { offset: size, enableAutoPan: false });
    Map.openInfoWindow(iw, point);
    
}
//设置普通样式  橙色
CopStaOverlay.prototype.setNormalStyle = function () {
    this._div.className = "copStaOverlay";
    this._div.style.zIndex = this.originzIndex;
}
//设置普通样式  橙色
CopStaOverlay.prototype.setGreenStyle = function () {
    this._div.className = "copStaOverlayGreen";
    this._div.style.zIndex = "1000000000";
}
//重写绘制函数
CopStaOverlay.prototype.draw = function () {
    var map = this._map;
    var pixel = map.pointToOverlayPixel(this._point);
    this._div.style.left = pixel.x - 10 + "px";
    //这里的30为Magic Number注意！
    this._div.style.top = pixel.y - 31 + "px";
}
//增加事件函数
CopStaOverlay.prototype.addEventListener = function(event, csid) {
    var fn = (function() {
        return function() {
            csid = parseInt(csid);
            $("#li_help_" + csid).find(".checkPrice").click();
        }
    })(csid)
    this._div['on' + event] = fn;
}
//增加事件函数
CopStaOverlay.prototype.addEventListenerBy = function (event, fn) { 
    this._div['on' + event] = fn;
}
//增加事件函数
CopStaOverlay.prototype.EventListener = function () { 
     var Ti,that=this;var kk=0;
     function StartTimer(e){  
         e=e||window.event;
         if(e.stopPropagation){
             e.stopPropagation();
         }else{
            e.returnValue=false;
         }
         //alert(e.srcElement.tagName)
         Ti= setTimeout(function(){
            
            $(".copStaOverlayIn").removeClass("copStaOverlayIn").addClass("copStaOverlay");  
            that.setInStyle();
            $(".maplocal li").removeClass("localcur");
            
            $("#gddivhelp > div").removeClass("localcur");
            
            $("#li_help_"+ parseInt(that._CSID)).addClass("localcur");
         },500);
         
         //that._div.background="black";
         //that._div.innerHTML= (kk++);
     }
     function EndTimer(){
         clearTimeout(Ti);
         //that.setNormalStyle();
     } 
     this._div['onmouseover'] =StartTimer;
     this._div['onmouseout'] =EndTimer;
}


    var Map, J;
    // cityid 城市ID
    // isshowservicetab 是否需要展示全部和部分的切换功能 （详细页是不需要的）
    // Orderby - 0 默认排序 1 技术从高到低排序  2 好评从高到低排序
    // defaultShowType - 默认展示全部还是部分的服务站 （0全部  1部分）
    //areaId 区域ID
    //Keyword-关键字
    //value搜索类型 0，商户搜索 1.定位搜索 0.全部，无法确定 1.无法确定
    //Mycollection 我的收藏
     function ShowMap(cityid, isshowservicetab, orderby, defaultShowType,areaId,value,Mycollectionvalue) {
        var curr_itemids=$("#curr_itemids").val();
        var curr_catgids=$("#curr_catgids").val();
        var PageType=$("#js_pageType").val();
        if(PageType=="shopCar")
        {
            curr_itemids=$("#hiditemids").val();
        }
        var keyWord=$.trim($("#txt_last_receive_address").val());
        var automodelid=$("#hidautomidel").val();
        if(keyWord=="例如:养车无忧"){
            keyWord=="";
            return;
        }
        if(value==0&&keyWord=="")
        {
            alert(" 请输入搜索内容");
            return;
        }
        $.ajax({
            type: "GET",
            url: websitePath + "/Handlers/serviceproject/layerbycityid.aspx",
            data: { conmark: curr_catgids, itemids: curr_itemids, cityid: cityid, isshow: isshowservicetab, pageType: PageType, orderby: orderby, Type: defaultShowType, AreaId: areaId, type: value, Keyword: keyWord, Mycollection: Mycollectionvalue,amid:automodelid },
            cache: false,
            success: function(data) {
                if ($("#allstations").length === 0) {
                    $Layer.Reset();
                    $Layer.IsBack = true;
                    $Layer.IsFooter = false;
                    $Layer.Width = 898;
                    $Layer.Title = "";
                    $Layer.CloseText = "关 闭";
                    $Layer.Content = data;
                    $Layer.IsConfirm = true;
                    $Layer.Confirm.Text = "确 定";
                    $Layer.Open();
                    $UICommon.Watermark("txt_last_receive_address", "例如:曹安公路");
                }
                else {
                    
                    $("#item_content").replaceWith(data);
                }
                
                setTimeout(function() {
                    initMap();
                    //是商户搜索不执行定位方法
                    if (value == 0) {
                    }
                    else {
                        RegistEvent();
                    }

                    MathTabEvent();
                    TechHoverEvent();
                }, 0)
            },
            error: function(e) { }
        })
     }
      var Map,J;
     function initMap(){
        Map= new BMap.Map("map_left");
        var currSelectIDs=$("#points").html();
        var stationAppendLeftDom=$("#ulList");
        var o = {
                stationList: currSelectIDs,
                Map: Map,
                stationAppendLeftDom:stationAppendLeftDom,
                OnThisClass:"huangseback",
                OuterLeft:"<li style=\"width:310px\">",
                OuterRight:"</li>"
            };
        window.J=new stationMapInit(o);
        
        $("#ulList").children(".copS").mouseover(function(e){
            $("#ulhelp .copS").removeClass("localcur");
            var dataid = $(this).addClass("localcur").attr("data-id");
            var Ti;
            for(var i=0;i<J.length;i++){
                if( parseInt(J.baiduMarker[i]._CSID) == dataid){
                            function StartTimer(){
                                 e=e||window.event;
                                 if(e.stopPropagation){
                                     e.stopPropagation();
                                 }else{
                                    e.returnValue=false;
                                 }
                                 //alert(e.srcElement.tagName)
                                 Ti= (function(i){
                                      setTimeout(function(){ 
                                            $(".copStaOverlayIn").removeClass("copStaOverlayIn").addClass("copStaOverlay");  
                                            J.baiduMarker[i].setInStyle();
                                     },500);
                                 })(i);
                                 //that._div.background="black";
                                 //that._div.innerHTML= (kk++);
                             }
                             var EndTimer=(function(Ti,i){
                                return function(){
                                     clearTimeout(Ti);
                                     //J.baiduMarker[i].setNormalStyle(); 
                                }
                             })(Ti,i);
                          
                             StartTimer();

                              $(this).mouseout(function(){
                                      EndTimer();
                              })
                             
                }
            }
        })
    }
    
    var c_id;
    function ShowDetatil(id, AutoModelId,IsSearch,itemIds) {
        $("#hidCurrentOnclick").val($("#getInfo-"+id).attr("onclick"));
        $("#getInfo-"+id).attr("onclick","");
          c_id=id;
          var curr_catgids=$("#curr_catgids").val();
          var PageType=$("#js_pageType").val();
          $.ajax({
              type: "GET",
              url: "../handlers/ServiceProject/LayerDetatilByStationID.aspx",
              data: "id=" + id + "&conmark=" + curr_catgids + "&itemids=" + (itemIds || 0) + "&amid=" + AutoModelId + "&t=" + (+new Date()) + "&type=" + PageType + "&IsSearch=" + IsSearch,
              success: function(data) {
                  setTimeout(function() {
                      // $(".feelayer").eq(0).parent().html(data); stationdetails  allstations
                      $("#allstations").hide();
                      $("#allstations").parent().append(data);
                      $("#showBox").find("#div_commentlist #SubmainContent").hide().remove();
                      if (PageType == "pingjia" || PageType == "shopCar" || PageType == "orderdetail") {
                          $("#showBox").find("#div_Comment").hide().remove();
                      }
                      else if (PageType == "step3"||PageType=="detail") {
                         $("#showBox").find("#div_Comment .SubmainContent1").hide().remove();
                      }
                      techInit()
                  }, 0);
              },
              error: function(e) { }
          });
    }
    function backTomap(){
         $("#helpnontposition").hide();
         $("#allstations").show();
         setTimeout(function(){
             J.AutoRight(J);
         },200);
         
         $("#stationdetails").hide().remove();
         $("#s"+ parseInt(c_id)).removeClass("copStaOverlay").addClass("copStaOverlayIn"); 
         var sttr= $("#hidCurrentOnclick").val();
         $("#getInfo-"+c_id).attr("onclick",""+sttr+"");
    }
    var txt_last_receive_address;
    function RegistEvent(){
    //alert(txt_last_receive_address)
    
//    if(txt_last_receive_address)
//         $("#txt_last_receive_address").val(txt_last_receive_address)
//    var lastAddresss= unescape($State.GetValue("userPositionAddr")); 
//    if(lastAddresss && !$("#txt_last_receive_address").val()){ 
//        $("#txt_last_receive_address").val(lastAddresss);
//    }
         $("#btn_decide_loc").unbind("click").click(function() { 
             txt_last_receive_address = $.trim($("#txt_last_receive_address").val());
             if(txt_last_receive_address=="例如:曹安公路"||txt_last_receive_address=="例如:养车无忧"){
                return;
             }
             if (txt_last_receive_address) {   
                var geo = new BMap.Geocoder(); 
                //百度地图定位地址函数 
                
                geo.getPoint( cityname+" "+ txt_last_receive_address, function(point) { 
                    //定位地址后的回调函数
                    if (point) {
                        $(".copStaOverlayGreen").hide();
                        var marker = new CopStaOverlay(point,"定位地址 "+ txt_last_receive_address);
                        
                        Map.addOverlay(marker);
                       
                        //$State.Save("userPositionAddr",escape(txt_last_receive_address), 999999, false);
                         $.ajax({type: "POST",
                             url: "/handlers/serviceproject/cookiesave.ashx?value="+txt_last_receive_address.EncodeURI(),
                            cache: false
                         });
                        var size = new BMap.Size(0, -31);   
                        var iw = new BMap.InfoWindow("<div style='width:220px' class=\"copStaIW\">" + "<b>" + "定位地址" + "</b><p>" +cityname+"  "+ txt_last_receive_address + "</p>" + "</div>", { offset: size, enableAutoPan: false });
                  
                      
                        marker.setGreenStyle(); 
                        iw.addEventListener('open', function() {
                            //marker.setInStyle();
                            marker._isOpen = true;
                        });
                        iw.addEventListener('close', function() {
                           // marker.setNormalStyle();
                            marker._isOpen = false;
                        });
    
                        marker.addEventListenerBy('click', function() {
                            Map.openInfoWindow(iw, point);
                        });
                        
                        //marker.EventListener();


                        Map.openInfoWindow(iw, point);

                        Map.panTo(point);


                        setTimeout(function(){
                                J.AutoRight(J);},500) ;
                    } else {
                        alert("该地址无法定位！");
                    }
                  
                }, { enableHighAccuracy: true });
            }
        });
    
    
      //实现在定位输入框中按回车进行定位功能
        $("#txt_last_receive_address").unbind("keydown").keydown(function(event) {
            if (event.which == "13") {
                $("#btn_decide_loc").click();
            }
        });
    }

    // 切换支持全部项目和支持部分项目
    function MathTabEvent() {
        $("#MathTab  a").unbind("click").click(function() {
            $("#MathTab  a").removeClass("in");
            $(this).addClass("in");
            var mathType = $("#MathTab .in").attr("data-value");
            if (mathType == 1) {
                $("#TabTip_1").show();
                $("#TabTip_2").hide();
            } else {
                $("#TabTip_1").hide();
                $("#TabTip_2").show();
            }
            J.AutoRight(J);
        })
    }

    
   //按技术、好评排序
    function stationOrderBy(orderby) {
        var cityid = $("#hidlayercityid").val();
        var IsShowServiceTab = $("#hidIsShowServiceTab").val();
        var defaultShowType = $("#MathTab .in").attr("data-value");
        if(orderby==3)
        {
            $(".sequence a").first().addClass("cur");
        }
        else
        {
            $(".sequence a").last().removeClass();
        }
        if(defaultShowType == 1)
        {
            defaultShowType=0;
        }
        else
        {
            defaultShowType=1;
        }
        ShowMap(cityid, IsShowServiceTab, orderby, defaultShowType,0,1,false);
   }
   //鼠标悬停事件
   function TechHoverEvent(){
//        $(".tech").each(function(){
//            $(this).find(".techdiv").hover(
//                function(){
//                    $(this).parent().find(".techLayer").show();
//                },
//                function(){
//                    $(this).parent().find(".techLayer").hide();
//                }
//            )
//        })
   }

function techInit(){
//2015.01.21去除
//    $("#div_tech").hover(
//        function(){
//            $(".stLayer").show();
//        },
//        function(){
//            $(".stLayer").hide();
//    });
}

//切换搜索方式 
//function ToggleRecarch(obj){
//    if(obj==0)
//    {
//        $("#btn_decide_locOther").html("定位").attr({
//        "ID":"btn_decide_loc",
//        "onclick":""
//        })
//        RegistEvent();
//        $("#countyName").hide();
//        $("#SelectArea").html("地点名称");
//        $UICommon.Watermark("txt_last_receive_address", "例如:曹安公路");
//    }
//    else
//    {
//        var cityid=$("#hidlayercityid").val();
//        var areaid=$("#hidlayerareaid").val();
//        $("#btn_decide_loc").html("搜索").attr({
//        "ID":"btn_decide_locOther",
//        "onclick":"ShowMap("+cityid+",0,0,0,"+areaid+",0)"
//        }).unbind("click");
//        $("#countyName").hide();
//        $("#SelectArea").html("商户名称");
//        $UICommon.Watermark("txt_last_receive_address", "例如:养车无忧");
//    }
//}

function ToggleRecarch(obj){
    if(obj==0)
    {
        $("#btn_serch").hide();
        $("#btn_decide_loc").show();
        RegistEvent();
        $("#countyName").hide();
        $("#SelectArea").html("地点名称");
        $UICommon.Watermark("txt_last_receive_address", "例如:曹安公路");
    }
    else
    {
        $("#btn_serch").show();
        $("#btn_decide_loc").hide();
        $("#countyName").hide();
        $("#SelectArea").html("商户名称");
        $UICommon.Watermark("txt_last_receive_address", "例如:养车无忧");
    }
}

//确定选择
function Btn_SpecialInstallationShop(serviceId) {
    var PageType=$("#js_pageType").val();
    //服务类型
    var serviceStion = $("#js_serviceStion").val();
    var orderType = $("#hidorderType").val();
    var subid = $("#hidSubId").val();
    var year = $("#hidYear").val();
    $.ajax({
        type: "GET",
        cache: false,
        data: { ServiceId: serviceId, type: PageType, tagType: serviceStion, SplitOrderType: orderType, automodelsubid: subid, year: year },
        url: websitePath + "/Handlers/ServiceProject/ChoesSpecialShopHandler.ashx",
        success: function(data) {
            if (PageType == "pingjia") {
                $Layer.Close();
                $("#Div1 strong").html(data + "(查看人工费)");
            }
            else if (PageType == "shopCar") {
                if (orderType == 1 || orderType == 4) {
                    fun_cartitemprovider(1);
                    fun_cartitemprovider(4);
                } else {
                    fun_cartitemprovider(orderType);
                }
            }
            else {
                var sttr = data.split("_");
                if (sttr[0] == "success") {
                    $Layer.Close();
                    $("#ServiceStationText").html("<a class=\"underline\" href=\"javascript:;\" onclick=\"NewShowDetatil(" + serviceId + "," + sttr[2] + ")\">" + sttr[1] + "(查看人工费)</a><a href=\"javascript:;\" onclick=\"ShowMap()\" class=\"change\">更换</a>");
                }
            }
        },
        error: function(e) { }
    })
}

//详细页加入,取消收藏
function Cancelcollection(obj,serviceId){
    if(obj==0)
    {   $.ajax({
            type:"GET",
            cache:false,
            data:{Type:obj,ServiceId:serviceId},
            url:websitePath+"/Handlers/ServiceProject/AddCollectionHandler.ashx",
            success:function(data){
             $("#myCollection").attr({"class":"","onclick":"Cancelcollection(1,"+serviceId+")"}).html("取消收藏");
            },
            error:function(e){}
        })
    }
    else
    {
         $.ajax({
            type:"GET",
            cache:false,
            data:{Type:obj,ServiceId:serviceId},
            url:websitePath+"/Handlers/ServiceProject/AddCollectionHandler.ashx",
            success:function(data){
             $("#myCollection").attr({"class":"favorite","onclick":"Cancelcollection(0,"+serviceId+")"}).html("加入收藏");
            },
            error:function(e){}
          })
        
    }
}

//分页效果
function getcollaborationservicecommentscroll(id, pagesize, pageindex,projectid) {

    $.ajax({
        type: "GET",
        url: websitePath + "/handlers/collaborationservice/collaborationservicecomment.aspx",
        data: "id=" + id + "&pagesize=" + pagesize + "&page=" + pageindex + "&projectid=" + projectid,
        cache: false,
        success: function(data) {
            $("#div_commentlist").html(data);
            $("#div_AlertMsg").removeClass().html("20-2000字");
            $("#showBox").find("#div_commentlist #SubmainContent").hide().remove();
            $(document).scrollTop($("#div_Comment").offset().top);
        },
        error: function(e) { }
    });
}


//无法确定按钮
function Notsure(UserID){
 //页面类型
 var PageType=$("#js_pageType").val();
 var orderType = $("#hidorderType").val();
 var Servicestation=$("#js_serviceStion").val();
 var subid = $("#hidSubId").val();
 var year = $("#hidYear").val();
 $.ajax({
     type: "GET",
     cache: false,
     data: { uid: UserID, type: PageType, SplitOrderType: orderType, automodelsubid: subid, year: year, tagType: Servicestation },
     url: "/Handlers/ServiceProject/NotSureSpecialShopHandler.ashx?action=add",
     success: function(data) {
         $Layer.Close();
         if (PageType == "step3") {
             $(".other").html("安装服务：<a class=\"change hidefocus\" onclick=\"ShowMap(0,1)\" href=\"javascript:;\" hidefocus=\"true\">更换</a><span>无需特约安装店</span>");
         }
         else if (PageType == "detail") {
             $("#ServiceStationText").html("<span>无需特约安装店</span><a href=\"javascript:;\"  onclick=\"ShowMap()\" class=\"change\">更换</a>");
         }
         else if (PageType == "pingjia") {
             $("#Div1 strong").html("无法确定暂不选择");
         }
         else {
             if (orderType == 1 || orderType == 4) {
                 fun_cartitemprovider(1);
                 fun_cartitemprovider(4);
             } else {
                 fun_cartitemprovider(orderType);
             }
         }
     },
     error: function(e) { }
 })
}


function fun_cartitemprovider(orderType) {
    $Layer.Close();
    var ActivityID = $("#cartDiv" + orderType).attr("data-activityid");  //$("#hidActivityId").val(); //$("#hidSpikeActivityID").val();
    $.ajax({
        type: "GET",
        cache: false,
        url: '../shopping/handlers/cartitemprovider.aspx?splitordertype=' + orderType,
        data: { ActivityID: ActivityID },
        success: function(html) {
            $("#cartDiv" + orderType).replaceWith(html);
            $copStation = $("#cartDiv" + orderType).find(".copStation");
            goToIDRelative($copStation.eq(0).find(".cBut").eq(0));
            $UIShopCart.GoToNotCsNext($copStation.eq(0));
            $("#cartDiv" + orderType).find(".copStation").addClass("redBorder");
            $("#cartDiv" + orderType).find(".copStationNUll").addClass("redBorder");
            $UIShopCart.AjaxSuccess();
        }
    });
}

//查看人工费
function NewShowDetatil(id, AutoModelId)
{
          var   c_id=id;
          var curr_catgids=$("#curr_catgids").val();
          var PageType=$("#js_pageType").val();
          $.ajax({
                type: "GET",
                url: "../handlers/ServiceProject/LayerDetatilByStationID.aspx",
                data: "id=" + id + "&conmark=" + curr_catgids + "&itemids=" + ($("#curr_itemids").val() || 0) + "&amid=" + AutoModelId + "&t=" + (+new Date())+"&type="+PageType,
                success: function(data) {
                   $Layer.Reset();
                   $Layer.IsBack = true;
                   $Layer.IsFooter = false;
                   $Layer.Width = 898;
                   $Layer.Title = "";
                   $Layer.CloseText = "关 闭";
                   $Layer.Content = data;
                   $Layer.IsConfirm = true;
                   $Layer.Confirm.Text = "确 定";
                   setTimeout(function() {
                       $Layer.Open();
                        scrollshowBox(0);
                        $("#showBox").find(".js_footB,.feeTitle").hide().remove();
                        $("#showBox").find("#myCollection").hide().remove();
                        $("#showBox").find(".but").hide().remove();
                        $("#showBox").find("#div_commentlist #SubmainContent").hide().remove();
                         $(".baisebeijing ").find("#stationdetails").prepend("<div class=\"feeTitle\">特约安装店人工费详情<a class=\"backMap\" onclick=\"$Layer.Close()\" href=\"javascript:\">关闭</a></div>");
                         
                    },0);
                },
                error: function(e) { }
            });
}
function dataMaker(){
    
    //范围筛选重新布点
       var lis = $("#ulList").children(".copS");
       for(var i=0;i<lis.length;i++){
            var dataValue=$("#ulList").children(".copS").eq(i).attr("data-math");
            if(dataValue==1){
               var cuurentId=$("#ulList").children(".copS").eq(i).attr("data-id");
               var hidlat=$("#li_help_"+cuurentId).attr("data-lat");
               var __arr=hidlat.split("_");
               point = new BMap.Point(__arr[0], __arr[1]);
                
            }
            else if(dataValue==0){
               
            }
            
       }
}

function scrollshowBox(topval)
{
    
    if (($.browser.msie) && ($.browser.version == "6.0")){
        topval = $(document).scrollTop()+topval;
        $("#showBox").css({"top":topval});
        $(window).bind("scroll", function() {
            var topval = $(document).scrollTop()+topval;
	        $("#showBox").css({"top":topval});
        });
    }
}

function diweicommentop() {
    document.documentElement.scrollTop = $("#div_Comment").offset().top;
    document.body.scrollTop = $("#div_Comment").offset().top;
}

function baiduqipaohtml(reg)
{

   if(reg=="true")
   {
    return "<span></span>";
   }
   else
   {
    return "";
   }
}

function baiduyouhuiHtml(obj,time)
{
    var datenow = new Date();
    time = time.replace(/-/g,"/");
    var dateEndtime=new Date(time);


    var month=datenow.getMonth();
    if(month==0)
    {
         month=1;
    }
    var sttrdate=datenow.getFullYear()+"/"+month+"/"+datenow.getDate()+" "+datenow.getHours()+":"+datenow.getMinutes()+":"+datenow.getSeconds();
    var datetimeNow=new Date(sttrdate);
    if(obj>0&&dateEndtime>datetimeNow)
    {
        return "<i></i>";
    }
    else
    {
       return "";
    }
}

function baidurealNamehtml(obj,divhtml)
{
     if(obj=="true")
     {
        return "<div class=\"info\">"+divhtml+"</div>";
     }
     else
     {
        return "";
     }   
}