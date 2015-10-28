<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品发布</title>
<link type="text/css"
	href="${path}/resources/styles/shopMng/shopManager.css?v=${versionVal}"	rel="stylesheet" />
</head>
<body>

	<script type="text/javascript"	src="${path}/resources/scripts/biz/goodsMng/goodsPublishIndex.js?v=${versionVal}"></script>


	<div class="shop_manager_right shop_manager2">
		<h1 class="title">商品种类选择</h1>
		<form 	enctype="multipart/form-data" method="post" name="goods_add_go" id='x-return-form'>
			<div class="return_box">
				<c:forEach items="${region }" var="item"> 
				   <c:if test="${item.catId==1 }">
						<div class="return_boxon" >
							<input type="radio" name="goods_kind" value="999"  id="car_id"/><label for="car_id">发布整车</label>
						</div>
					</c:if>
					 <c:if test="${item.catId==2 }">
						<div class="return_boxon" >
							<input type="radio" name="goods_kind" value="888"  id="accessory_id"/><label for="accessory_id">发布配件</label>
						</div>
						<div class="return_boxon" >
							<input type="radio" name="goods_kind" value="777"  id="maintain_id"/><label for="maintain_id">发布保养</label>
						</div>
					</c:if>
					<c:if test="${item.catId==4 }">
						<div class="return_boxon" >
							<input type="radio" name="goods_kind" value="666"  id="jp_id"/><label for="jp_id">发布用品</label>
						</div>
					</c:if>
				</c:forEach>
				<div class="return_boxon" >
					<input type="radio" name="goods_kind" value="5"  id="virtual_id"/><label for="virtual_id">虚拟商品</label>
				</div>
			</div>
			<div class="cl_zhi"></div>
			<div class="table-action" style="margin-top: 10px;">
				<a  class="btn" onclick="submitBtn()">
					<span><span>下一步</span></span>
				</a>
			</div>
		</form>

	</div>

</body>
</html>

