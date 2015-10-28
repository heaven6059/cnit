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
					<th>举报商品：</th>
					<td><span class="font-orange">${reportDTO.name}</span></td>
				</tr>
				<tr>
					<th>举报原因：</th>
					<td><span class="font-orange">
						<c:if test="${reportDTO.reason == 'false'}"> 
							<div class="info">虚假信息</div>
						</c:if>
						<c:if test="${reportDTO.reason == 'unconformity'}">
							<div class="info">图片不符</div>
						</c:if>
						</span>
					</td>
				</tr>
				<tr>
					<th>举报状态：</th>
					<td>
						<span class="font-orange" id="x-return_item_status-20150706119712">
							<c:if test="${reportDTO.status == 'intervene'}">平台介入</c:if>
							<c:if test="${reportDTO.status == 'success'}">举报成立</c:if>							
							<c:if test="${reportDTO.status == 'error'}">举报不成立</c:if>
							<c:if test="${reportDTO.status == 'cancel'}">举报撤销</c:if>
							<c:if test="${reportDTO.status == 'finish'}">完成</c:if>
						</span>
					</td>
				</tr>
				<tr>
					<th>提交时间：</th>
					<td><span class="font-orange"><fmt:formatDate value="${reportDTO.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
				</tr>
				
				<tr>
					<th>举报店铺：</th>
					<td><span class="font-orange">${reportDTO.storeName }</span></td>
				</tr>
				
			</tbody>
		</table>
	</div>
	<h5>举报备注</h5>
	<div class="division">
	${reportDTO.memo}
	</div>
	
	<h5>举报商品</h5>
	<div class="division">
		<div class="box-bd">
			<div class="row-item first-row-item">
				<div class="ctitle">
					<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${reportDTO.goodsId}"> 
						<img src="${imgUrl}/${reportDTO.picturePath}" class="goods-img" alt="${reportDTO.name}" width="255" height="255"> 
					</a>
					<label class=""></label>
				</div>
				<div class="info">
					<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${reportDTO.goodsId}" class="text-link">${reportDTO.name}</a> <br>
				</div>
			</div>
			<div class="row-item">
				<div class="ctitle">
					<label>单价：</label>
					<span><span class="price-thin">￥<fmt:formatNumber value="${reportDTO.price}" pattern="#,#00.00#"/></span>元</span>
				</div>
			</div>	
		</div>
	</div>
	
	<c:if test="${reportDTO.status eq 'intervene'}">
	<div container="true">
		<div class="tableform">
			<div class="table-action">
				<input value="${reportDTO.reportId}" id="reportId" type="hidden"/>
				<button class="btn" type="button" onclick="process('success')">
					<span><span>举报成立</span></span>
				</button>
				<button class="btn" type="button" onclick="process('error')">
					<span><span>举报不成立</span></span>
				</button>
				<script type="text/javascript">
				function process(status){
					$.ajax({
					    url:'../report/updateReport',
					    data:{status:status,reportId:$("#reportId").val()},
					    type:'post',    
					    cache:false,    
					    dataType:'json',    
					    success:function(head){
					    	if(head&&head.retCode==yoyo.successCode){
					    		$('#report_list_dataGrid').datagrid('load');
					    			$('#window-view-after-sales').window('close');    
					    		$.messager.alert('提示信息','举报处理成功！');					    		
					    	}else{
					    		$('#window-view-after-sales').window('close');					    			
					    		$.messager.alert('警告','处理举报失败！');
					    	}
					    }
					});
				}
				</script>
			</div>
		</div>
	</div>
	</c:if>
	
	<h5>举报留言</h5>
	<div class="division">
	<c:if test="${fn:length(reportDTO.reportComments)>0}">
	  	<c:forEach items="${reportDTO.reportComments}" var="reportComment">
		<h4>
			<c:if test="${reportComment.source=='buyer'}">买家：</c:if>
			<c:if test="${reportComment.source=='seller'}">卖家：</c:if>
		</h4>
		<div class="division">
			<span> ${reportComment.author}</span> &nbsp;&nbsp;(<span class="time"><fmt:formatDate value="${reportComment.lastModified}" pattern="yyyy-MM-dd HH:mm:ss" /></span>)
			<div class="action-desc" >
				<label></label>
				<div class="desc" style="text-indent: 2em;">${reportComment.comment}</div>
			</div>
			<c:if test="${!empty reportComment.imagePath}">
			<c:forEach var="image" items="${fn:split(reportComment.imagePath,',')}">
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