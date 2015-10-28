<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出售中的商品</title>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?v=${versionVal}" rel="stylesheet" />
</head>
<body>
<script type="text/javascript"  src="${path}/resources/scripts/biz/goodsMng/sellingGoods.js?v=${versionVal}"></script>
<script type="text/javascript"  src="${path}/resources/scripts/biz/goodsMng/goodsIndex.js?v=${versionVal}"></script>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">出售中的商品</div>
			<div class="clr"></div>
			<div class="lineh b4">
				<form id="searchForm">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" style="height: 100px;">
						<tr>
							<td width="60">商品名称：</td><td width="100"><input type="text" vtype="text" name="name" size="20" class="x-input _clear" style="border:1px solid #d4d4d4"> </td>
							<td width="60">商品编号:</td><td width="100"><input type="text" vtype="text" name="fbn" size="20"  class="x-input _clear" style="border:1px solid #d4d4d4"> </td>
							<td width="60">商品类别:</td>
								<td width="100">
									<select class="easyui-combogrid _clear"  name="catId" id="goodsCategory" style="width: 160px;">
									</select>
								</td>
						</tr>
						<tr style="margin-top:10px;">
							<td>价格:</td><td><input  type="text" style="border:1px solid #d4d4d4" name="minPrice" class="x-input x-input-width _clear"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"><span style="padding:0px 10px;">到</span><input  type="text" style="border:1px solid #d4d4d4" name="maxPrice" class="x-input x-input-width _clear"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
							<td>总销量:</td><td><input type="text" style="border:1px solid #d4d4d4" name="minBuyCount"  class="x-input x-input-width _clear" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"><span style="padding:0px 10px;">到</span><input style="border:1px solid #d4d4d4"  type="text" name="maxBuyCount" class="x-input x-input-width _clear" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
								
							<td><button id="btnsearch" class="btn search1" type="button">
								<span><span>搜索</span></span>
							</button></td> 
							<td><button  class="btn search1" type="button" onclick="cleanFun()">
								<span><span>清空</span></span>
							</button></td> 
						</tr>
					</table>
				</form>
			</div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist">
				<tbody>
					<tr>
						<th width="290" colspan="2" style="border-left: 1px solid #f2f2f2;">商品名称</th>
						<th width="98">价格</th>
						<th width="80">库存</th>
						<!-- <th width="80">冻结</th> -->
						<th width="100">总销量</th>
						<th width="80">发布时间</th>
						<!-- <th width="80">操作</th> -->
					</tr>
					<tr>
			          <td colspan="6" class="p0 list-top bln bnn">
			            <div class="operations mr_5">
			              <span class="lineall"><input class="all-selector" id="checkAll" type="checkbox"><label for="checkAll">全选</label></span>
			              <button class="trigger-btn" onclick="javascript:operationItem('down')" type="button"> 下 架 </button>
			              	<span class="lineall">预警值：<input name="alertValue" id="alertValue" type="text"/></span>
			              <button class="trigger-btn" onclick="javascript:operationItem('alert')" type="button"> 设置 </button>
			            <!--   <button class="trigger-btn" onclick="javascript:recommendGoods('recommend',1)" type="button"> 热门推荐 </button>
			              <button class="trigger-btn" onclick="javascript:recommendGoods('recommend',0)" type="button"> 取消推荐 </button>
			              <button class="trigger-btn" onclick="javascript:recommendGoods('new',1)" type="button"> 新品上架 </button>
			              <button class="trigger-btn" onclick="javascript:recommendGoods('new',0)" type="button"> 取消新品</button> -->
			                          </div>
			          </td>
			        </tr>
				</tbody>
				<c:forEach var="good" items="${goodslist.rows}">
				<tbody>
					<tr class="tr-th">
						<td colspan="6">
						<span class="tcol1">
							<input type="checkbox" name="goodsItem" class="selector" value="${good.goodsId }">商品编码:<a href="">${good.fbn}</a>
						</span>
						</td>
					</tr>
					<tr>						
						<td class="product"><a href="#" onclick="openGoodsLink( ${good.goodsId});" ><img src="${path }/resources/images/default_white.png" data-img="${good.smallPic}" width="80px" height="80px"/></a></td>
						<td class="product_name">
							<a href="#" onclick="openGoodsLink(${good.goodsId});"  ><label class="col1" style="cursor:pointer;">
								${good.name}
							</label></a>
						</td>	
						<td><fmt:formatNumber value="${good.price}" maxFractionDigits="2" pattern="#,#00.00" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${good.store}" maxFractionDigits="0"/></td>
						<%-- <td><fmt:formatNumber value="${good.storeFreeze}" maxFractionDigits="0"/></td> --%>
						<td>${good.buyCount}</td>
						<td>
						 <c:if test="${!empty good.upTime }">${fn:substring(good.upTime,0,4)}-${fn:substring(good.upTime,4,6)}-${fn:substring(good.upTime,6,8)}
						  ${fn:substring(good.upTime,8,10)}:${fn:substring(good.upTime,10,12)}:${fn:substring(good.upTime,12,14)}
						 </c:if> 
						</td>
						<!-- <td></td> -->
					</tr>
				</tbody>
				</c:forEach>				
			</table>
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?t=${time}"></script>
	<script type="text/javascript">
		total="${goodslist.total}";rows="${goodslist.pageSize}";
	</script>
</body>
</html>

