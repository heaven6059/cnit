<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看退换货</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/popup/magnific-popup.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?r=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/popup/jquery.magnific-popup.min.js"></script>
<style type="text/css">

.msg-error {
    background: #ffebeb none repeat scroll 0 0;
    border: 1px solid #ffbdbe;
    color: #e4393c;
    float: left;
    line-height: 24px;
    padding: 2px 10px;
}

.text-count {
    color: #999999;
    z-index: 99;
}

</style>
</head>
<script type="text/javascript">
$(function (){
    $('.J_Pics').magnificPopup({
        delegate: 'a',
        type: 'image',
        closeOnContentClick: false,
        closeBtnInside: false,
        mainClass: 'mfp-with-zoom mfp-img-mobile',
        image: {
          verticalFit: true,            
        },
        gallery: {
          enabled: true
        },
        zoom: {
          enabled: true,
          duration: 300, // don't foget to change the duration also in CSS
          opener: function(element) {
            return element.find('img');
          }
        }
  });
    
});
</script>
<body>
	<div id="member-main" class="member-main tk member-main2">
		<div class="title">退款退货管理</div>		
		<div id="order-delivery">
			<div>
				<div class="note clearfix">
					<h3></h3>
					<div class="clearfix">
						<div class="span-auto colborder">
							订单编号：<span>${returnProduct.orderId}</span>
						</div>
						<div class="span-auto colborder">
							申请状态： <span>${returnProduct.refundText}</span>
						</div>
						<div class="span-auto last">
							申请时间：<span><fmt:formatDate value="${returnProduct.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						</div>
					</div>
				</div>
			</div>
			<c:if test="${returnProduct.isSafeguard==2&&returnProduct.applyNums>0}">
			<h4 class="lineheight-30">
				申请数量：<span class="color1 font14px">${returnProduct.applyNums}</span>
			</h4>
			</c:if>
			<c:if test="${returnProduct.safeguardRequire==5}">
			<h4 class="lineheight-30">
				退款金额：<span class="color1 font14px">￥${returnProduct.amount}</span>
			</h4>
			</c:if>
			<h4 class="lineheight-30">
				售后要求：<span class="color1 font14px">${returnProduct.safeguardRequireText}</span>
			</h4>
			<h4 class="lineheight-30">
				申请原因：<span class="color1 font14px">${returnProduct.afterSalesReasonText}</span>
			</h4>
			<h4 class="lineheight-30">
				申请人：<span class="color1 font14px">${returnProduct.memberName}</span>
			</h4>
			<h4 class="lineheight-30">
				联系电话：<span class="color1 font14px">${returnProduct.memberPhone}</span>
			</h4>
			<h4 class="lineheight-30">
				联系地址：<span class="color1 font14px">${returnProduct.refundAddress}</span>
			</h4>

			<table width="100%" cellspacing="0" cellpadding="3"
				class="gridlist border-all">
				<colgroup>
					<col class="span-4">
					<col class="span-auto">
					<col class="span-4">
				</colgroup>
				<thead>
					<tr>
						<th>商品图片</th>
						<th>商品名称</th>
						<th>购买数量</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reshipItem" items="${returnProduct.orderItems}">
					<tr>
						<td class="textcenter"><img width="50" height="50" src="${imgUrl}/${reshipItem.picturePath}" /></td>
						<td class="textcenter"><a href="${portalPath}/goodsManager/goodsIndex?goodsId=${reshipItem.goodsId}" target="_blank">${reshipItem.name}</a></td>
						<td class="textcenter">${reshipItem.nums}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<h4>详细说明：</h4>
			<div class="division division22">${returnProduct.content}</div>

			<c:if test="${!empty returnProduct.sellerComment}">
			<h4>退款留言：</h4>
			<div class="division division22">
				${returnProduct.sellerComment}
			</div>
			</c:if>
			
			<c:if test="${!empty returnProduct.sellerReason}">
			<h4>拒绝原因：</h4>
			<div class="division division22">
				${returnProduct.sellerReason}
			</div>
			</c:if>
			
			<c:if test="${!empty returnProduct.memberImagePath}">
			<h4>买家举证:</h4>
			<div class="division">
				<c:forEach var="image" items="${fn:split(returnProduct.memberImagePath,',')}">
				<div class="pics J_Pics" style="float: left;margin-left: 2px;">
					<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}">
						<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
					</a>
				</div>
				</c:forEach>
				<div class="clearfix"></div>
			</div>
			</c:if>
			
			<c:if test="${!empty returnProduct.interevenImage}">
			<h4>卖家举证:</h4>
			<div class="division">
				<div>
				${returnProduct.interevenComment}
				</div>
				<c:forEach var="image" items="${fn:split(returnProduct.interevenImage,',')}">
				<div class="pics J_Pics" style="float: left;margin-left: 2px;">
					<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}">
						<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
					</a>
				</div>
				</c:forEach>
				<div class="clearfix"></div>
			</div>
			</c:if>
			
			<c:if test="${returnProduct.status eq 4}">
			<link rel="stylesheet" href="${path}/resources/styles/zyUpload.css" type="text/css">
			<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
			<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
			<form id="form_1" action="../returnProductManager/shopAppealAfterSales" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border: nonel;text-align: center;">
				<tr>
					<td>举证留言：</td>
					<td>
						<div class="FormWrap">
							<textarea style="width: 98%" rows="3" cols="80" class="x-inputs x-input" name="interevenComment" id="interevenComment" type="textarea"></textarea>
						</div>
						<div class="text-count" style="display: none;">
							<span class="msg-error">麻烦填写10-300个字</span>
						</div>
					</td>
				</tr>
				<tr>
					<th>上传举证图片：</th>
					<td>
						<div class="goods-image-div" id="goods_picture"></div>
						<div style="clear: both;"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="button" onclick="doIntereven();" class="btn">
							<span><span>提交举证</span></span>
						</button>
					</td>
				</tr>
			</table>
				<input type="hidden" name="returnId" value="${returnProduct.returnId}"/>
				<input type="hidden" name="orderId" value="${returnProduct.orderId}"/>
				<input type="hidden" name="interevenImage" id="interevenImage" value=""/>
			</form>
			<script type="text/javascript">
				$(function (){
					var pics = '';
					// 初始化上传图片插件
					$("#goods_picture").zyUpload({ width : "800px" , // 宽度
					height : "200px" , // 高度
					itemWidth : "100px" , // 文件项的宽度
					itemHeight : "100px" , // 文件项的高度
					url : _path + "/image/uploadImg" , // 上传文件的路径
					multiple : false , // 是否可以多个文件上传
					dragDrop : false , // 是否可以拖动上传文件
					del : true , // 是否可以删除文件
					finishDel : false , // 是否在上传文件完成后删除预览
					/* 外部获得的回调接口 */
					onSuccess : function(file, data) { // 文件上传成功的回调方法
						var json = $.parseJSON(data);
						var pic = json.retMsg.split(";");
						pics =pics + pic[1] + ',' ;
						$('#interevenImage').attr('value',pics);
						if (json.retCode == "000000") {
						} else if (json.retCode == "000003") {
							parent.$.messager.alert('提示', '上传失败！' + json.retMsg, 'error');
						}
					}
					});
				});
				function doIntereven(){
					if($("#interevenComment").val().length>300||$("#interevenComment").val().length<10){
						$(".text-count").show();
						return
					}
					$("#form_1").submit();
				}
			</script>
			</c:if>
			<h3 class="lineheight-30">售后申请记录：</h3>
			<c:forEach var="log" items="${returnProduct.logs}">
			<div class="division">
			${log.opName}(${log.roleText})：
			${log.logText}<br> 日期：<fmt:formatDate value="${log.alttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
			</c:forEach>
		</div>

	</div>
</body>
</html>

