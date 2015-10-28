<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
<!--
.tableform{background:#f8f8f8 none repeat scroll 0 0;border:1px solid #d9d9d9;border-radius:5px;margin:10px 15px;padding:5px}
.tableform .division{background:transparent none repeat scroll 0 0;margin:5px;padding:5px 10px;line-height:normal;white-space:normal}
.table-action{margin:5px 5px 3px;padding:3px 0 7px;clear:both;height:30px;line-height:35px;text-align:center}
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
					<td><span class="font-orange">${complain.orderId}</span></td>
				</tr>
				<tr>
					<th>订单金额：</th>
					<td><span class="font-orange">${complain.finalAmount}</span></td>
				</tr>
				<tr>
					<th>申请原因：</th>
					<td><span class="font-orange">
						<c:if test="${complain.reason == 'action'}">
							<div class="info">行为违规</div>
						</c:if>
						<c:if test="${complain.reason == 'after'}">
							<div class="info">售后问题</div>
						</c:if>
						<c:if test="${complain.reason == 'quality'}">
							<div class="info">质量问题</div>
						</c:if>
						</span>
					</td>
				</tr>
				<tr>
					<th>投诉状态：</th>
					<td>
						<span class="font-orange" id="x-return_item_status-20150706119712">
							<c:if test="${complain.status eq 'cancel'}">已撤销投诉</c:if>
							<c:if test="${complain.status eq 'intervene'}">审核中，请耐心等待</c:if>
							<c:if test="${complain.status eq 'success'}">投诉成功</c:if>
							<c:if test="${complain.status eq 'error'}">投诉不成立</c:if>
						</span>
					</td>
				</tr>
				<tr>
					<th>提交时间：</th>
					<td><span class="font-orange"><fmt:formatDate value="${complain.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
				</tr>
				
				<tr>
					<th>投诉店铺：</th>
					<td><span class="font-orange">${complain.companyName}</span></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<h5>投诉商品</h5>
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
					<c:forEach var="reshipItem" items="${complain.items}">
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
	<c:if test="${complain.status eq 'intervene'}">
	<div container="true">
		<div class="tableform">
			<div class="table-action">
				<form method="post" id="return-refuse" action="../aftersales/processAfterSales">
					<input value="${complain.complainId}" id="complainId" type="hidden"/>
					<button class="btn" type="button" onclick="process('success')">
						<span><span>投诉成立</span></span>
					</button>
					<button class="btn" type="button" onclick="process('error')">
						<span><span>投诉不成立</span></span>
					</button>
				</form>
				<script type="text/javascript">
				function process(status){
					$.ajax({
					    url:'../complain/updateComplain',
					    data:{status:status,complainId:$("#complainId").val()},
					    type:'post',    
					    cache:false,    
					    dataType:'json',    
					    success:function(head){
					    	if(head&&head.retCode==yoyo.successCode){
					    		$('#complain_list_dataGrid').datagrid('load');
					    			$('#window-view-after-sales').window('close');
					    		$.messager.alert('提示信息','投诉处理成功！');					    		
					    	}else{
					    		$('#window-view-after-sales').window('close');					    			
					    		$.messager.alert('警告','投诉处理失败！');
					    	}
					    }
					});
				}
				</script>
			</div>
		</div>
	</div>
	</c:if>
	
	<h5>投诉留言</h5>
	<div class="division">
	<c:if test="${fn:length(complain.complainComments)>0}">
	  	<c:forEach items="${complain.complainComments}" var="complainComment">
		<h4>
			<c:if test="${complainComment.source=='buyer'}">买家：</c:if>
			<c:if test="${complainComment.source=='seller'}">卖家：</c:if>
		</h4>
		<div class="division">
			<span> ${complainComment.author}</span> &nbsp;&nbsp;(<span class="time"><fmt:formatDate value="${complainComment.lastModified}" pattern="yyyy-MM-dd HH:mm:ss" /></span>)
			<div class="action-desc" >
				<label></label>
				<div class="desc" style="text-indent: 2em;">${complainComment.comment}</div>
			</div>
			<c:if test="${!empty complainComment.imagePath}">
			<c:forEach var="image" items="${fn:split(complainComment.imagePath,',')}">
			<div class="pics J_Pics" style="float: left;margin-left: 2px;">
				<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}">
					<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
				</a>
			</div>
			</c:forEach>
			</c:if>
			<div class="clearfix"></div>
		</div>
		</c:forEach>
	</c:if>
	</div>
	
	
</div>