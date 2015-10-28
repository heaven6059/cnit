<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<script type="text/javascript">
    var _path = "${path}";
</script>
</head>
<body>
    <jsp:include page="${_path}/WEB-INF/pages/decorator/statusBar.jsp" />
    <div class="main easyui-layout">
        <div region="north" style="padding-left: 5px; overflow:hidden;background-color:#E0ECFF;">
            <c:forEach items="${menuTree}" var="menu">
                 <a style="width: 80px; border: 3px solid #E0ECFF;" onclick="javascript:changeWest(${menu.id})" class="easyui-splitbutton"  menu="#${menu.id}">
                    <span style="font-size:15px;font-weight:bold;">${menu.text}</span></a>
                    <div id="${menu.id}" class="easyui-menu" style="width: 200px;">
                     <c:forEach items="${menu.children}" var="oneChildren">
                        
				            <div>
				                <span style="font-size:14px;font-weight:bold;">${oneChildren.text}</span>
				                <div>
				                   <c:if test="${oneChildren.children!=null}">
				                      <c:forEach items="${oneChildren.children}" var="twoChildren">
					                    <div>
					                        <a onclick="addTabs('${twoChildren.attributes.url}','${twoChildren.text}')">${twoChildren.text}</a>
					                    </div>
					                   
					                  </c:forEach>
				                    </c:if>
				                </div>
				            </div>
			            
                   </c:forEach>    
                 </div>   
          </c:forEach>
       </div>
   </div>
       
</body>
<script type="text/javascript">
   
    
    //选择与关闭tab时需要关闭高级查询
    $("#tabs").tabs({
        onSelect:function(title,index){
            $(".advance_search").window('close');
        },
        onClose:function(title,index){
            $(".advance_search").window('close');
        }
    });
    
    
</script>
</html>