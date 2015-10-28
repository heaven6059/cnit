<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>

<!--面包屑导航-->
<div class="root_nav">
	<div class="root_navin">
    	<span><a href="javascript:;">首页</a></span>&nbsp;>&nbsp;
    	<span><a href="javascript:;">卖家中心</a></span>
    </div>
</div>
<!--侧导航-->
	<div class="sidebar fl">
        <p>卖家中心</p>
        <div class="buy_sid">
            <ul class="listUl"  id="menutree">
            </ul>
        </div>
    </div>
	<!--侧导航 end-->
    
<%-- 
<div class="shop-sidebar shop-sidebar2">
	<div class="shop-menu">
		<img src="${path}/resources/images/shop/shopcenter.png" />
		<ul class="body" id="menutree">
			<c:forEach items="${menuTree}" var="menu">
           		<li class="shop-menu-list">
	       			<div class="list-title-bg noborder">
						<h2 class="list-title-icon m-7">${menu.text}</h2>
					</div>
					<ul>
	                     <c:forEach items="${menu.children}" var="oneChildren">
							<li><a href="${oneChildren.attributes.url}">${oneChildren.text}</a></li>
	                     </c:forEach>
                    </ul>
                </li>
            </c:forEach>
		</ul>
	</div>
</div>
 --%>


<script type="text/javascript">
	
  
	$(document).ready(function() {
		
		buildHTML = function(tag, html, attrs) {
		    // you can skip html param
		    if (typeof (html) != 'string') {
		      attrs = html;
		      html = null;
		    }
		    var h = '<' + tag;
		    for (attr in attrs) {
		      if (attrs[attr] === false) continue;
		      h += ' ' + attr + '="' + attrs[attr] + '"';
		    }
		    return h += html ? ">" + html + "</" + tag + ">" : "/>";
		  } ;
		
		$.ajax({
			url : biz.rootPath() + "/shopManager/menuTree",
			type : "post",
			datatype : "json",
			success : function(data) {
				
				if (data.head.retCode == '000000') {
					var html = '';
	    			$.each(data.content.menuTree,function(i,v){
	    				html += '<li class="listLi"><a href="javascript:;" class="myTitle">';
	    				html += v.text+'</a> <ul>';
	    				var childHtml = '';
	    				$.each(v.children,function(j,k){
	    					childHtml += '<li>';
	    					var name = k.text;
	    					var herfStr = k.attributes.url;
	    					var a = buildHTML("a", name, {
	    				        id: "myLink",
	    				        href: '${path}'+herfStr
	    				      }); 
	    					childHtml += a;
	    					childHtml += '</li>';
	    				});
	    				html += childHtml;
	    				html += '</ul></li>';
	    				//alert("text="+v.text);
	    			});
	    			$("#menutree").append(html);
	    		} else {
	    			easyuiMsg('错误', "获取分类信息失败！");
	    		}
			}
		 });
	});
</script>


