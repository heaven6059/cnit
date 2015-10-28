<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
<!--
.test{line-height:38px;}
-->
</style>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<script type="text/javascript">
	
	/**
	* 对于accordion控件使用动态加载时，
	*accordion的onSelect方法中又动态生成tree，为什么要第二次选择accordion，tree才生成
	*解决方案：不使用accordion的add和onSelect的方法，一次性拿到整个tree的数据，
	*然后在jsp页面中进行foreach循环即可
	* @author xiaox
	* @date 2014-7-25  上午10:54:04 
	*/
	function addTabs(url,title){
		if (url ) {
			addTab({
				url :'${path}'+ url,
				title : title,
				iconCls : 'flag'
			});
		}
	}

	function addTab(params) {
		var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			refreshs();
			parent.$.messager.progress('close');
		} else {
			 //关闭高级查询 所有页面的高级查询的div都有advance_search这个class样式
	        $(".advance_search").window('close');
			t.tabs('add', opts);
		}
	}

	//刷新 
	function refreshs() {
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

	$(function(){
		$('.easyui-tree').tree({
		onClick:function(node){
			eval(($(node.text).attr('onclick')));   //点击空白处，执行a标签的点击事件，因为在easyui-tree中的li上面的点击事件被屏蔽了，因而点击事件只能在这里执行
		}});
	});
	
</script>
<div id="accordionWest" class="easyui-accordion" data-options="fit:true,height:540,border:false,">
   <c:forEach items="${menuTree}" var="menu">
   <div  title="${menu.text}" data-options='iconCls:"icon-folder-open"'>
        <ul id="tree${menu.id}" class="easyui-tree">
            <c:forEach items="${menu.children}" var="oneChildren">
                <li >
                   <a style="border:none;color:#000;line-height:24px;text-decoration: none; " href="javascript:void(0)" onclick='addTabs("${oneChildren.attributes.url}","${oneChildren.text}")'>${oneChildren.text}</a>
                	<c:if test="${oneChildren.children!=null}">
                	 	<ul id="tree2${oneChildren.id}" class="" >
                 		<c:forEach items="${oneChildren.children}" var="twoChildren">
	                  		<li> <span ><a style="border:none;color:#000" href="javascript:void(0)" onclick='addTabs("${twoChildren.attributes.url}","${twoChildren.text}")'>${twoChildren.text}</a></span></li>
                     	</c:forEach>
                    	</ul>
                	</c:if>
                 </li>
            </c:forEach>
         </ul>
     </div> 
    </c:forEach>
 </div>

