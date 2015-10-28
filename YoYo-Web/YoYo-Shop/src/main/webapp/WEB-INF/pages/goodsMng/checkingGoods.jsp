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
<title>审核中的商品</title>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?v=${versionVal}" rel="stylesheet" />
</head>
<body>
<script type="text/javascript"  src="${path}/resources/scripts/biz/goodsMng/checkingGoods.js?v=${versionVal}"></script>
<script type="text/javascript"  src="${path}/resources/scripts/biz/goodsMng/goodsIndex.js?v=${versionVal}"></script>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">仓库中的商品</div>
			<div class="Plate">
				<h4>
				    <a class="active cur_active"  href="javascript:openCheckingGoods();">审核中的商品</a>
				    <a class="active"  href="javascript:openLocationGoods();"><font color="#333">暂存本地的商品</font></a>
					<a class="active"  href="javascript:openPutawayGoods();"><font color="#333">等待上架的商品</font></a> 
					<!-- <a class="active"  href="javascript:openWarningGoods();"><font color="#333">预警中的商品</font></a> --> 
					<a class="active"  href="javascript:openDealingGoods();"><font color="#333">违规下架的商品</font></a>
				</h4>
			</div>
			<div class="clr"></div>
			<div class="lineh b4">
				<form id="searchForm">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" style="height: 100px;">
						<tr>
							<td width="60">商品名称：</td><td width="100"><input type="text" vtype="text" name="name" size="20" class="x-input _clear"> </td>
							<td width="60">商品编号:</td><td width="100"><input type="text" vtype="text" name="fbn" size="20"  class="x-input _clear"> </td>
							<td width="60">商品类别:</td>
								<td width="100">
									<select class="easyui-combogrid _clear"  name="catId" id="goodsCategory" style="width: 160px;">
									</select>
								</td>
						</tr>
						<tr style="margin-top:10px;">
							<td>价格:</td><td><input  type="text" name="minPrice" class="x-input x-input-width _clear" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">到<input  type="text" name="maxPrice" class="x-input x-input-width _clear" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
							<td>总销量:</td><td><input type="text" name="minBuyCount"  class="x-input x-input-width _clear" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">到<input  type="text" name="maxBuyCount" class="x-input x-input-width _clear" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
								
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
						<th width="80">修改时间</th>
						<th width="80">状态</th>
						<th width="80">原因</th>
						<th width="80">审核时间</th>
						<th width="80">操作</th>
					</tr>
					<tr>
			          <td colspan="9" class="p0 list-top bln bnn">
			            <div class="operations mr_5">
			              <span class="lineall"><input class="all-selector" id="checkAll" type="checkbox"><label for="checkAll">全选</label></span>
			              <button class="trigger-btn" onclick="javascript:operationItem('delete');" type="button">删 除</button>
			             <!--  <button class="trigger-btn" onclick="javascript:operationItem('up')" type="button"> 上 架 </button> -->
			            </div>
			          </td>
			        </tr>
				</tbody>
				<c:forEach var="good" items="${goodslist.rows}">
				<tbody>
					<tr class="tr-th">
						<td colspan="9">
						<span class="tcol1">
							<input type="checkbox" name="goodsItem" class="selector" value="${good.goodsId }">商品编码:<a href="">${good.fbn}</a>
						</span>
						</td>
					</tr>
					<tr>						
						<td class="product"><img src="${path }/resources/images/default_white.png" data-img="${good.smallPic}" width="80px" height="80px"/></td>
						<td class="product_name">
							<label class="col1">
								${good.name}
							</label>
						</td>	
						<td><fmt:formatNumber value="${good.price}" maxFractionDigits="2" pattern="#,#00.00" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${good.store}" maxFractionDigits="0"/></td>
						<td>
						 <c:if test="${!empty good.lastModify }">${fn:substring(good.lastModify,0,4)}-${fn:substring(good.lastModify,4,6)}-${fn:substring(good.lastModify,6,8)}
						  ${fn:substring(good.lastModify,8,10)}:${fn:substring(good.lastModify,10,12)}:${fn:substring(good.lastModify,12,14)}
						 </c:if> 
						</td>
						<td><c:if test="${good.marketable=='2' }">审核中</c:if><c:if test="${good.marketable=='3' }">审核不通过</c:if></td>
						<td>${good.p22 }</td>
						<td> <c:if test="${!empty good.p23 }">${fn:substring(good.p23,0,4)}-${fn:substring(good.p23,4,6)}-${fn:substring(good.p23,6,8)}
						  ${fn:substring(good.p23,8,10)}:${fn:substring(good.p23,10,12)}:${fn:substring(good.p23,12,14)}
						 </c:if> </td>
						<td><c:if test="${good.marketable=='3' }"><a class="font-blue operate-btn" href="../goods/editIndex/${good.identifier }/${good.goodsId }">编辑商品</a></c:if></td>
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

