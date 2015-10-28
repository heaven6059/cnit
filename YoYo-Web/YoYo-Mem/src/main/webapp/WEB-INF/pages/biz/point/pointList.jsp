<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的积分</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
</head>
<body>
	<div class="member-main">
		<div>
			<div class="title title2">我的积分</div>

			<div>
				<p class="admin-title textright">
				  <span class="flt">
					可用积分：<em class="font-orange fontbold font14px" id = "totalPoint">${member.pointUseable}</em>
				  </span>
				</p>
				<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id = "tableId">
					<tbody>
						<tr>
							<th>日期</th>
							<th>收入积分</th>
							<th>支出积分</th>
							<th>剩余积分</th>							
							<th>来源/去向</th>
						</tr>
					</tbody>
				</table>
				
				<div class="page clearfix">
					<div id="Pagination" class="yoyoPagination"></div>
				</div>
			</div>
			<p class="admin-title textleft">
				温馨提示：
			</p>			
			<p class="admin-title textleft">
				 1:订单状态变为“交易成功”后系统自动将积分发放到账户。
			</p>
			<p class="admin-title textleft">
			 2:查看《<a
					href="javascript:void(0);"
					onclick="showOrHideRules() "><font
					color="blue">积分规则及使用方法</font></a>》
			</p>
			<span id="point_use_rules" style="display:none"> 
				<h5>平台积分规则：</h5>
				<p>
					<strong>1.</strong> 积分专属平台商城，仅限平台商城内使用。
				</p>
				<p>
					<strong>2. </strong>100积分=1元现金。
				</p>
				<p>
					<strong>3. </strong>买家在付款时，在完成该笔交易后（支付系统显示该交易状态为“交易成功”），才能得到此次交易的相应积分。
				</p>
				<p>
					<strong>4. </strong>商家派送的积分由商家自行决定，但不能超过其拥有的全部积分。
				</p>
				<p>
					<strong>5.</strong>
					B2B2C平台卖家出售商品自愿选择是否可用积分抵部分现金，消费者最高消耗积分不能高于商品的10%售价；卖家得到消费者所用的积分（对于技术开发需求，平台管理可以根据发展需求，可调整这个最高值）。
				</p>
				<p>
					<strong>6. </strong>消费者的积分不能兑现，不可转让。
				</p>
				<p>
					<strong>7. </strong>积分有效期即从获得开始至次年年底（12月31日），逾期自动作废。以公历的年、北京时间为准，如若交易在使用的积分有效期之外发生退款，该部分积分不予退还
				</p>
			</span>
		</div>
	</div>
<script type="text/javascript" >
    function  showOrHideRules(){
    	var displayStyle = $('#point_use_rules').attr('style');
		if(displayStyle == 'display:none') {
			$('#point_use_rules').attr('style','display:block');
		 }else if(displayStyle == 'display:block') {
			 $('#point_use_rules').attr('style','display:none');
		}
	}
</script>		
<script type="text/javascript" src="${path}/resources/scripts/biz/point/pointList.js"></script>	
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
</body>
</html>