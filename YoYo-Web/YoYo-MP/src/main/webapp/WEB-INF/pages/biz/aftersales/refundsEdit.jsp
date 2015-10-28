<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
<!--
.tableform{background:#f8f8f8 none repeat scroll 0 0;border:1px solid #d9d9d9;border-radius:5px;margin:10px 15px;padding:5px}
.tableform .division{background:transparent none repeat scroll 0 0;margin:5px;padding:5px 10px;line-height:normal;white-space:normal}
.table-action{border-top:2px solid #e0e0e0;margin:5px 5px 3px;padding:3px 0 7px;border-top:1px solid #e0e0e0;clear:both;height:70px;line-height:35px;text-align:center}
.c-orange,.font-orange{color:#c40001}
.tableform h5{border-bottom:1px solid #c8c8c8;color:#305c89;margin:0 0 5px;padding:7px 0 5px 5px;font-size:12px}
.tableform .gridlist th{border-bottom:1px solid #d0d2d5;padding:0 3px;width:auto;word-break:keep-all}
.finder-detail .tableform th{color:#555;vertical-align:top;width:125px}
.finder-detail .tableform td,.finder-detail .tableform th{padding:3px}
.tableform th{color:#4f4f4f;font-weight:400;padding-right:5px;text-align:right;white-space:nowrap;width:120px}
.gridlist{background:#fff none repeat scroll 0 0;border:1px solid #d5dfe3;border-collapse:collapse;color:#5b5b5b;height:auto;margin:0}
.gridlist tbody td,.gridlist tbody th{border-bottom:1px solid #e8e8e8;border-right:1px solid #e8e8e8;height:25px;line-height:25px;padding:0 3px;text-align:center;vertical-align:middle;white-space:normal}
.gridlist thead th{background:transparent url(../resources/images/tile_bg.png) repeat scroll 0 -300px;border-bottom:1px solid #d0d2d5;border-right:1px solid #c8d6dc;color:#4e4e4e;font-size:12px;font-weight:400;height:22px;line-height:22px;padding:0 3px;text-align:center;vertical-align:middle;white-space:nowrap}
.btn{background:transparent none repeat scroll 0 0;border:0 none;cursor:pointer;display:inline-block;font-size:12px;font-weight:400;height:27px;line-height:27px;margin:0 1px;overflow:visible;padding:0;text-decoration:none;vertical-align:middle}
.btn span,.col-select-opt:hover,.desktop-sliding,.finder-action-items a,.finder-action-items span,.finder-packet li a,.finder-packet li span,.finder-tabs-wrap .current,.finder-tabs-wrap .current span,.finder-tabs-wrap .tab,.finder-tabs-wrap .tab span,.gridlist-action .tabs-wrap li.current,.head-nav dt a,.head-nav dt a.current,.head-nav dt a.current span,.head-nav dt a.current:hover,.head-nav dt a.current:hover span,.head-nav dt span,.object-select .handle,.object-select .label,.switch-head li,.switch-head li em,.tabs-wrap .current,.tabs-wrap .current span,.widget-edit-head .exit button span,.widget-edit-head .tab-head,.widget-edit-head li a.act,.widget-edit-head li a.act em,.widget-edit-head li a.droping,.widget-edit-head li a.droping em,.widget-edit-head li a:hover,.widget-edit-head li a:hover em{background:transparent url(../resources/images/sliding.png) no-repeat scroll 0 0}
.btn-primary span,.table-action .btn span{height:27px;line-height:27px}
.btn-primary span,.btn-secondary span,.table-action .btn span{background-position:0 -556px;color:#fff;font-weight:700}
.btn span {background-position: 0 -1091px;display: block;overflow: visible;padding: 0 0 0 10px;white-space: nowrap;}
.btn-primary span span, .btn-secondary span span, .table-action .btn span span {background-position: 100% -556px;height: 27px;line-height: 27px;min-width: 40px;}
.table-action .btn span, .btn-primary span {height: 27px;line-height: 27px;}
.btn-primary span, .btn-secondary span, .table-action .btn span {background-position: 0 -556px;color: white;font-weight: bold;}
.btn span span {background-position: 100% -1091px;display: block;height: 23px;line-height: 23px;padding: 0 10px 0 0;}
.btn span {background-position: 0 -1091px;display: block;overflow: visible;padding: 0 0 0 10px;white-space: nowrap;}
.tableform th {color: #555555;vertical-align: top;width: 125px;padding: 3px;}
.tableform th {color: #4f4f4f;font-weight: normal;padding-right: 5px;text-align: right;white-space: nowrap;width: 120px;}

-->
</style>
<div class="tableform">
	<div class="division">
		<table cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<th>订单编号：</th>
					<td><span class="font-orange">${refunds.orderId}</span></td>
				</tr>
				<tr>
					<th>状态：</th>
					<td><span class="font-orange"
						id="x-return_item_status-20150706119712">${refunds.refundText}</span></td>
				</tr>
				<tr>
					<th>提出申请的时间：</th>
					<td><span class="font-orange"><fmt:formatDate value="${refunds.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
				</tr>
				
				<tr>
					<th>售后要求：</th>
					<td><span class="font-orange">${refunds.safeguardRequireText}</span></td>
				</tr>

				<tr>
					<th>申请原因：</th>
					<td><span class="font-orange">${refunds.afterSalesReasonText}</span></td>
				</tr>
				<c:if test="${refunds.safeguardRequire==5}">
				<tr>
					<th>退款金额：</th>
					<td><span class="font-orange">￥${refunds.amount}</span></td>
				</tr>
				</c:if>
				<tr>
					<th>买家电话：</th>
					<td><span class="font-orange">${refunds.memberPhone}</span></td>
				</tr>
			</tbody>
		</table>
	</div>

	<c:if test="${!fn:startsWith(refunds.orderId,'333')}">
		<h5>申请售后服务的商品</h5>
			<div class="division">
				<table width="100%" cellspacing="0" cellpadding="3" class="gridlist">
					<colgroup>
						<col class="span-3">
						<col class="span-auto">
						<col class="span-2">
					</colgroup>
					<thead>
						<tr>
							<th style="width: 20%">图片</th>
							<th>商品名称</th>
							<th>数量</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach var="reshipItem" items="${refunds.orderItems}">
							<tr>
								<td>
									<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${reshipItem.product.goodsId}" target="_blank">
										<img width="50" height="50" src="${imgUrl}/${reshipItem.picturePath}" />
									</a>
								</td>
								<td ><a href="${portalPath}/goodsManager/goodsIndex?goodsId=${reshipItem.goodsId}" target="_blank">${reshipItem.name}</a></td>
								<td >${reshipItem.nums}</td>
							</tr>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</div>
		<h5>详细说明</h5>
	</c:if>
	
	<div class="division">${refunds.content}</div>
	
	<c:if test="${!empty refunds.memberImagePath}">
		<h4>买家举证:</h4>
		<div class="division">
			<c:forEach var="image" items="${fn:split(refunds.memberImagePath,',')}">
			<div class="pics J_Pics" style="float: left;margin-left: 2px;">
				<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}">
					<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
				</a>
			</div>
			</c:forEach>
			<div class="clearfix"></div>
		</div>
	</c:if>
	<c:if test="${!empty sellerReason}">
	<h5>卖家拒绝原因</h5>
	<div class="division">${refunds.sellerReason}</div>
	</c:if>
	
	<c:if test="${!empty refunds.interevenImage}">
		<h4>卖家举证:</h4>
		<div class="division">
			<div style="padding-bottom: 10px;">
				${refunds.interevenComment}
			</div>
			<c:forEach var="image" items="${fn:split(refunds.interevenImage,',')}">
			<div class="pics J_Pics" style="float: left;margin-left: 2px;">
				<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}" target="_blank">
					<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
				</a>
			</div>
			</c:forEach>
			<div class="clearfix"></div>
		</div>
	</c:if>
	
	
	<c:if test="${!empty refunds.logs}">
	<h5>退货日志</h5>
	<div container="true">
		<div class="tableform">
			<table width="100%" cellspacing="0" cellpadding="0" border="0"
				class="gridlist">
				<colgroup>
					<col class="Coldate">
					<col class="Coldetails">
					<col class="Colamount">
				</colgroup>
				<thead>
					<tr>
						<th>日期</th>
						<th>操作</th>
						<th>操作员</th>
						<!--<th>过期</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="log" items="${refunds.logs}">
					<tr>
						<td><fmt:formatDate value="${log.alttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="Coldetails">${log.logText}</td>
						<td class="Colamount">${log.opName}(${log.roleText})</td>
					</tr>
					</c:forEach>					
				</tbody>
			</table>
		</div>
	</div>
	</c:if>
	<c:if test="${refunds.status==2||refunds.status==7||refunds.status==9}">
		<div class="table-action">
			<form method="post" id="return-refuse" action="../aftersales/processRefunds">
			<script type="text/javascript">
				function process(status){
					$("#status").val(status);
					$("#return-refuse").submit();
				}
			</script>
				<input type="hidden" value="" name="status" id="status">
				<input type="hidden" value="${refunds.returnId}" name="returnId">
				<input type="hidden" value="${refunds.orderId}" name="orderId"> 
				<button class="btn" type="button" onclick="process(10)">
					<span><span>审核通过</span></span>
				</button>
				<button class="btn" type="button" onclick="process(11)">
					<span><span>审核不通过</span></span>
				</button>
			</form>
		</div>
	</c:if>
</div>