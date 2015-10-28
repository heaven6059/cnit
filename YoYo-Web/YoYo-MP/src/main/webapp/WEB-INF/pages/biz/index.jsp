<%@ page language="java"   contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.cnit.yoyo.util.Configuration" %> 
<!DOCTYPE html>
<%
   String path = request.getContextPath();
   request.setAttribute("path", path);
   application.setAttribute("imgUrl", Configuration.getInstance().getConfigValue("images.url"));
   application.setAttribute("portalPath", Configuration.getInstance().getConfigValue("portal.url"));
   application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
   response.addHeader("Pragma", "no-cache");
   response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
   response.addHeader("Cache-Control", "pre-check=0, post-check=0");
   response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>YOYO汽车电商</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?t=${versionVal }" />

<script type="text/javascript">
    var index_tabs;
    var index_tabsMenu;
    var index_layout;
    $(function() {
        index_layout = $('#index_layout').layout({
            fit : true
        });
        /*index_layout.layout('collapse', 'east');*/

        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            onContextMenu : function(e, title) {
                e.preventDefault();
                index_tabsMenu.menu('show', {
                    left : e.pageX,
                    top : e.pageY
                }).data('tabTitle', title);
            },
            tools : [ {
                iconCls : 'icon-reload',
                handler : function() {
                    var href = index_tabs.tabs('getSelected').panel('options').href;
                    if (href) {/*说明tab是以href方式引入的目标页面*/
                        var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                        index_tabs.tabs('getTab', index).panel('refresh');
                    } else {/*说明tab是以content方式引入的目标页面*/
                        var panel = index_tabs.tabs('getSelected').panel('panel');
                        var frame = panel.find('iframe');
                        try {
                            if (frame.length > 0) {
                                for ( var i = 0; i < frame.length; i++) {
                                    frame[i].contentWindow.document.write('');
                                    frame[i].contentWindow.close();
                                    frame[i].src = frame[i].src;
                                }
                                if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
                                    try {
                                        CollectGarbage();
                                    } catch (e) {
                                    }
                                }
                            }
                        } catch (e) {
                        }
                    }
                }
            }, {
                iconCls : 'icon-cancel',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    } else {
                        $.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
                    }
                }
            } ]
        });

        index_tabsMenu = $('#index_tabsMenu').menu({
            onClick : function(item) {
                var curTabTitle = $(this).data('tabTitle');
                var type = $(item.target).attr('title');

                if (type === 'refresh') {
                    index_tabs.tabs('getTab', curTabTitle).panel('refresh');
                    return;
                }

                if (type === 'close') {
                    var t = index_tabs.tabs('getTab', curTabTitle);
                    if (t.panel('options').closable) {
                        index_tabs.tabs('close', curTabTitle);
                    }
                    return;
                }

                var allTabs = index_tabs.tabs('tabs');
                var closeTabsTitle = [];

                $.each(allTabs, function() {
                    var opt = $(this).panel('options');
                    if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
                        closeTabsTitle.push(opt.title);
                    } else if (opt.closable && type === 'closeAll') {
                        closeTabsTitle.push(opt.title);
                    }
                });

                for ( var i = 0; i < closeTabsTitle.length; i++) {
                    index_tabs.tabs('close', closeTabsTitle[i]);
                }
            }
        });
    });
  
    
    /*改变西部区域的菜单栏
    * pid： 父级菜单id
    */
    function changeWest(pid){
        $('#index_layout').layout('panel','west').panel({href:'${path}/resource/resourceTree?pid='+pid+'&layout=west'});
        $('#index_layout').layout('panel','west').panel('refresh');  
    }
</script>


</head>
<body>

    <div id="index_layout">
        <div data-options="region:'north',href:'${path}/resource/resourceTree?pid=0&layout=north'" style="height: 120px; overflow: hidden;" >
        </div>
        <!-- TODO 修改这里的pid,默认是第一个菜单的id -->
        <div data-options="region:'west',href:'${path}/resource/resourceTree?pid=2&layout=west',split:true" title="导航列表" style="width: 200px;overflow: hidden;">
        </div>
        <div id="index_tabs" style="overflow: hidden;" data-options="region:'center'">
                <div title="首页" data-options="border:false,iconCls:'home'" style="overflow: hidden;" >
                    <iframe src="${path}/mpIndex" scrolling="no" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
                </div>
        </div>
   </div>
        <%-- <div data-options="region:'south',href:'${pageContext.request.contextPath}/biz/layout/south.jsp',border:false" style="height: 60px; overflow: hidden;"></div> --%>

    <div id="index_tabsMenu" style="width: 320px; display: none;">
        <div title="refresh" data-options="iconCls:'transmit'">刷新</div>
        <div class="menu-sep"></div>
        <div title="close" data-options="iconCls:'delete'">关闭</div>
        <div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
        <div title="closeAll" data-options="iconCls:'delete'">关闭所有</div>
    </div>
    
</body>
</html>