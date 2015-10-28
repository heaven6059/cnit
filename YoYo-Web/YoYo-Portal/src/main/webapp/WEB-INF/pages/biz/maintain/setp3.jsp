<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  --%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择保养商品 </title>
<script type="text/javascript">var _path="${path}";</script>
<script type="text/javascript" src="${path}/resources/scripts/biz/maintain/setp3.js?v=${versionVal}"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/maintain/maintain_common220150527.css?v=${versionVal}">
<link href="${path}/resources/styles/maintain/self20150228.css?v=${versionVal}" type="text/css" rel="stylesheet"/>
<link type="text/css" rel="stylesheet" href="${path}/resources/styles/maintain/cartitems.css?v=${versionVal}" source="widget"/>
</head>
<body>
<div class="conteBox">
<script type="text/javascript">
var loginstatus="${sessionScope.loginStatus}";
</script>
<div class="titleBox">
    <h2><b>自助保养服务</b>[ 选择保养项目和商品 ]</h2>
    <ol class="setp3">
      <li>查询项目</li>
      <li>确认项目</li>
      <li class="cur">选购商品</li>
      <li>加入购物车</li>
    </ol>
  </div>
  
   <div class="c"></div>
   <div class="models1">
     <h3><b>大众 CC 1.8TSI 2015年产</b></h3>
   </div>
   <div class="modelsSelect" id="div_AllAutoModelParam"> </div>
	<input type="hidden" value="54892|2015" id="txt_MyMSubIdYear">
   <div class="c"></div>
   <div class="listBox">
             
<div id="dd_NewMaintenItemList" class="partslist">

<div class="partsList">
  <input type="hidden" value="${qryDTO.companyId}" id="company_id">
  <input type="hidden" value="${catalogs[0].catalogId}" id="choose_category_id">
  <input type="hidden" value="" id="choose_brand_id">
  <input type="hidden" value="81,81,98" id="curr_catgids">
     <h4>爱车保养项目配件选购清单</h4>
    <div class="tableBox">
	<div class="cart-warp">
		<div class="w">
			<div id="jd-cart">
				<div class="cart-main">
					<div class="cart-thead">
						<div class="column t-goods">商品</div>
						<div class="column t-price">单价(元)</div>
						<div class="column t-quantity">数量</div>
						<div class="column t-action">操作</div>
					</div>
					<c:forEach items="${carMaintain}" var="carMaintain">
					<div id="cart-list">
						<div id="cart-item-list-01" class="cart-item-list">
							<div id="vender_8888" class="cart-tbody">
								<div class="shop">
									<span class="shop-txt">${carMaintain.catName}</span>
								</div>
								<div class="item-list">
									<div class="item-give item-full ">
										<div class="item-header">
											<span class="shop-txt">${carMaintain.catalogName}</span>
										</div>
										<!-- 单品-->
										<div class="item-last item-item item-selected" data="${carMaintain.categoryId}">
											<div class="item-form">												
												<div class="cell p-goods">
													<div class="goods-item">
														<div class="p-img">
															<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${carMaintain.goodsId}">
																<img src="${imagePath}${carMaintain.picturePath}" width="80px" height="80px" />
															</a>
														</div>
														<div class="item-msg">
															<div class="p-name">
																<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${carMaintain.goodsId}">${carMaintain.productName}</a>
															</div>
															<div class="p-extend">
															<c:choose>
															<c:when test="${carMaintain.disabled!=0}"><div class="red">该商品已失效</div></c:when>
															<c:when test="${carMaintain.marketable!=1}"><div class="red">该商品已下架</div></c:when>
															</c:choose>
															</div>
														</div>
													</div>
												</div>
												<div class="cell p-sum">
													<strong>${carMaintain.price}</strong>
												</div>
												<div class="cell p-quantity">
													<div class="quantity-form">
														<a id="" class="decrement" href="javascript:void(0);">-</a> 
														<input type="text" name="quantity" data-max="${carMaintain.store}" maxlength="2" value="1" class="itxt" autocomplete="off"> 
														<a id="" class="increment" href="javascript:void(0);">+</a>
													</div>
													<div  class="cell p-quantity">库存:${carMaintain.store}</div>
												</div>
												<div class="cell p-ops">
													<a href="javascript:void(0);" class="cart-remove" id="">删除</a>
												</div>												
												<input type="hidden" name="storeId" value="${carMaintain.storeId}" />
												<input type="hidden" name="companyId" value="${carMaintain.companyId}" />
												<input type="hidden" name="productId" value="${carMaintain.productId}" />
												<input type="hidden" name="goodsId" value="${carMaintain.goodsId}" />
												<input type="hidden" name="disable" value="${carMaintain.disabled}" />
												<input type="hidden" name="marketable" value="${carMaintain.marketable}" />
												<input type="hidden" name="store" value="${carMaintain.store}" />
												<input type="hidden" name="price" value="${carMaintain.price}" />
												<input type="hidden" name="appointment" value="${qryDTO.appDate}" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<div id="cart-floatbar">
				<div class="toolbar-wrap">
					<div class="selected-item-list hide"></div>
					<div class="options-box">													
						<div class="toolbar-right">
							<div class="normal">
								<div class="comm-right">
									<div class="btn-area">
										<a class="submit-btn" id="addToCart" href="javascript:addToCart();">加入购物车</a>
										<a class="submit-btn" id="process" href="javascript:;" style="display: none;">提交中...</a>
									</div>
									<div class="price-sum">
										<div>
											<span class="txt">总价：</span> <span class="price sumPrice"><em>￥0</em></span> <br>
										</div>
									</div>
									<div class="clr"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
    <div class="c"></div>            
</div>
</div>
       <!-- 
       <div id="div_moreList" class="moreList">
       <h5>更多可选配件</h5>
        <div class="selectBox">
        	<div id="div_partselect" class="select">
            	<span id="span_curpartname" data="${catalogs[0].catalogId}"><i></i>${catalogs[0].catalogName}</span>
				<ol class="ol_part" style="display: none">
					<c:forEach var="catalog" items="${catalogs}">
					<li><a data="${catalog.catalogId}" href="javascript:;">${catalog.catalogName}</a></li>
					</c:forEach>
				</ol>
            </div>
          <div id="div_brandselect" class="select right">
            	<span id="span_curbrandname"><i></i>配件品牌</span> 
                <ol class="ol_brand" style="display: none;">
                </ol>
            </div>
        </div>
         <div class="c"></div>
        <div id="moreitemlist">
	      <div class="c"></div>
	      <table>
	        	<thead>
		   	        <tr>
		       	        <td>配件图片</td>
	                   <td>配件名称</td>
	                   <td>单价</td>
	                   <td>选用</td>
	               </tr>
	           </thead>
	           <tbody>
		   	  
	          </tbody>
	       </table>
	       <hr>
         <div class="c"></div>
       </div>
        <div class="c"></div>
  </div>
   -->
   <div class="c"></div>
  </div><!--listbox-->
  <div class="c"></div>
  </div>
</body>
</html>