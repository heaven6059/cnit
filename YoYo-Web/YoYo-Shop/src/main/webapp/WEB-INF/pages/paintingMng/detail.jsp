<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>受损单详情</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/sortedDynamic.css?v=${versionVal}">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/paintingMng/detail.css?v=${versionVal}">

<script src="${path}/resources/scripts/biz/paintingMng/paintingUtil.js?v=${versionVal}"></script>
<script src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
</head>
<body>
	<div class="right_side">
		<div class="list_head">
			<div class="damage_id">部位</div>
			<div class="u_remark">客户留言</div>
			<div class="offer_price">报价</div>
			<div class="s_remark">商户留言</div>
		</div>
		<div class="detail_list">
		<form id="offerForm">
			<c:choose>
				<c:when test="${null==detailList.carDamageOfferDetailList}">
					<c:forEach items="${detailList.carDamageDetailList}" var="detail">
						<div class="item_list">
							<div class="item_single">
								<div class="p_goods fl">
									<div class="goods_item">
										<div id="len">
											<input type="hidden" value="${detail.id}">
											<div class="p_title fl">${detail.carPart.partName}</div>
											<c:forTokens items="${detail.pic}" delims=";" var="picPath">
												<div class="p_img fl">
													<a href="javascript:;">
														<img src="${imgUrl}${picPath}" width="80" height="80" title="点击查看大图">
													</a>
												</div>
											 </c:forTokens>
										</div>
										<div class="p_remark fl">
												${detail.remark}
										</div>
									</div>
								</div>
								<div class="p_price fl">
									<input name="offerPrice" class="input_moneny" value="0" onkeydown="standard(this)"  onkeyup="sss(this)">&nbsp元
								</div>
								<div class="remark fr">
									<textarea name="remark" class="remark2"></textarea>
									<div class="red remark" style="padding-top:5px;display:none">如需免费，请留言说明</div>
									<div class="red remark2" style="padding-top:5px;display:none">请输入不大于100字的留言</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${detailList.carDamageOfferDetailList}" var="detail">
						<div class="item_list">
							<div class="item_single">
								<div class="p_goods fl">
									<div class="goods_item">
										<div id="len">
											<input type="hidden" value="${detail.carDamageDetail.id}">
											<div class="p_title fl">${detail.carDamageDetail.carPart.partName}</div>
											<c:forTokens items="${detail.carDamageDetail.pic}" delims=";" var="picPath">
												<div class="p_img fl">
														<img src="${imgUrl}${picPath}" width="80" height="80" title="点击查看大图">
												</div>
											 </c:forTokens>
										</div>
										<div class="p_remark fl">
											<textarea class="remark2" disabled="true">${detail.carDamageDetail.remark}</textarea>
										</div>
									</div>
								</div>
								<div class="p_price fl">
									<input name="offerPrice" class="input_moneny" value="${detail.offerPrice}" disabled="true">&nbsp元
								</div>
								<div class="remark fr">
									<textarea name="remark" class="remark2" disabled="true">${detail.remark}</textarea>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</form>	
		<div class="cart_floatbar">
            <div class="toolbar_right fr">
            	<c:if test="${null==detailList.carDamageOfferDetailList}">
	            	<div class="btn_area ">
	                	<a id="commit" href="javascript:void(0)">确定报价</a>
	                </div>
                </c:if>
                <div class="price_sum">
                	<span class="txt">总价:</span>
                    <span class="price sumPrice">
                    	<c:choose>
                    		<c:when test="${null != detailList.carDamageOffer}">
                    			<em class="red">￥<span id="totalprice">${detailList.carDamageOffer.totalprice}</span></em>
                    		</c:when>
                    		<c:otherwise>
                    			<em class="red">￥<span id="totalprice">0</span></em>
                    		</c:otherwise>
                    	</c:choose>
                    </span>
                    <br />
                </div>
            </div>
        </div>
	</div>
	</div>
	<script src="${path}/resources/scripts/biz/paintingMng/detail.js?v=${versionVal}"></script>
	<script type="text/javascript">
		var companyId = ${sessionScope.companyId};
	</script>
</body>
</html>