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
					<td><span class="font-orange">${afterSales.orderId}</span></td>
				</tr>
				<tr>
					<th>状态：</th>
					<td><span class="font-orange"
						id="x-return_item_status-20150706119712">${afterSales.refundText}</span></td>
				</tr>
				<tr>
					<th>提出申请的时间：</th>
					<td><span class="font-orange"><fmt:formatDate value="${afterSales.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
				</tr>
				
				<tr>
					<th>售后要求：</th>
					<td><span class="font-orange">${afterSales.safeguardRequireText}</span></td>
				</tr>
				
				<tr>
					<th>申请原因：</th>
					<td><span class="font-orange">${afterSales.afterSalesReasonText}</span></td>
				</tr>
				<c:if test="${afterSales.safeguardRequire==5}">
				<tr>
					<th>退款金额：</th>
					<td><span class="font-orange">￥${afterSales.amount}</span></td>
				</tr>
				</c:if>
				<tr>
					<th>买家电话：</th>
					<td><span class="font-orange">${afterSales.memberPhone}</span></td>
				</tr>
			</tbody>
		</table>
	</div>
	<c:if test="fn:startWith(afterSales.orderId,'333')">
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
						<th style="width: 20%">货号</th>
						<th>商品名称</th>
						<th>数量</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="reshipItem" items="${afterSales.orderItems}">
						<tr>
							<td>
								<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${reshipItem.goodsId}" target="_blank">
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
	</c:if>
	<h5>详细说明</h5>
	<div class="division">${afterSales.content}</div>
	
	<c:if test="${!empty afterSales.memberImagePath}">
		<h4>买家举证:</h4>
		<div class="division">
			<c:forEach var="image" items="${fn:split(afterSales.memberImagePath,',')}">
			<div class="pics J_Pics" style="float: left;margin-left: 2px;">
				<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}">
					<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
				</a>
			</div>
			</c:forEach>
			<div class="clearfix"></div>
		</div>
	</c:if>
	
	<h5>卖家拒绝原因</h5>
	<div class="division">${afterSales.sellerReason}</div>
	<h4>卖家举证:</h4>
	<div class="division">
		<div style="padding-bottom: 10px;">
			${afterSales.interevenComment}
		</div>
		<c:if test="${!empty afterSales.interevenImage}">
			<c:forEach var="image" items="${fn:split(afterSales.interevenImage,',')}">
			<div class="pics J_Pics" style="float: left;margin-left: 2px;">
				<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}" target="_blank">
					<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
				</a>
			</div>
			</c:forEach>
		</c:if>
		<div class="clearfix"></div>
	</div>
	
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
					<c:forEach var="log" items="${afterSales.logs}">
					<tr>
						<td><fmt:formatDate value="${log.alttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="Coldetails">${log.logText}</td>
						<td class="Colamount">${log.opName}(${log.roleText})</td>
					</tr>
					</c:forEach>					
				</tbody>
			</table>
		
			<c:if test="${afterSales.status==4||afterSales.status==5}">
			<div class="table-action">
				<form method="post" id="return-refuse" action="../aftersales/processAfterSales">
					<c:if test="${afterSales.safeguardRequire==5}">
					<input type="text" size="10" name="amount" id="amount" value="${afterSales.amount}" maxlength="9" style="text-align: right;" autocomplete="off" onkeyup="clearNoNum(this)">（请手动输入退款金额）
					</c:if>
					<input type="hidden" value="" name="status" id="status">
					<input type="hidden" value="${afterSales.returnId}" name="returnId"> 
					<input type="hidden" value="${afterSales.orderId}" name="orderId">
					<input type="hidden" value="${afterSales.amount}" id="refundsAmount">
					<button class="btn" type="button" onclick="process(6)">
						<span><span>强制拒绝申请</span></span>
					</button>
					<button class="btn" type="button" onclick="process(7)">
						<span><span>强制同意申请</span></span>
					</button>
				</form>
				<script type="text/javascript">
					function process(status){
						$("#status").val(status);
						<c:if test="${afterSales.safeguardRequire==5}">
						if($("#amount").val().length==0){
							$.messager.alert('警告','请输入退款金额!','warning');
							return;
						}
						if($("#refundsAmount").val()<$("#amount").val()){
							$.messager.alert('警告','输入的金额不能大于退款金额!','warning');
							return;
						}
						</c:if>
						$("#return-refuse").submit();
					}
					
					 function clearNoNum(obj){
						 obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
						 obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是
						 obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
						 obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
						 obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
					 }
				</script>
			</div>
			</c:if>
		</div>
	</div>
</div>