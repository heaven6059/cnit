<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>虚拟商品发布</title>
<link type="text/css" href="${path}/resources/styles/shopMng/shopManager.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="${path }/resources/styles/shopEnter/shopRegister.css?v=${versionVal}">

<link type="text/css" rel="stylesheet" href="${path }/resources/styles/goodsMng/goodsPublishVirtual.css?v=${versionVal}">
</head>
<body>

	<script type="text/javascript" src="${path}/resources/scripts/biz/goodsMng/goodsPublishVirtual.js?v=${versionVal}"></script>

	<script type="text/javascript" src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>


	<div class="shop_manager_right ">
		<div class="title ">发布虚拟商品</div>
		<form method="post" id='form_saveVirtualGoods' class="section">
			<input type="hidden" value="${coupons.ruleId }" name="ruleId" /> <input type="hidden" value="${coupons.cpnsId }" name="cpnsId" /> <input type="hidden" value="1" name="issueType" />
			<!-- 优惠券基本信息填写 -->
			<div id="coupon_basic" style="display: block;">

				<div class="division liststyle data">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<th width="10%"><em class="red">*</em>类型：</th>
								<td><select class="easyui-combobox" style="width: 100px;"><option>优惠券</option></select></td>
							</tr>

							<tr>
								<th width="10%"><em class="red">*</em>名称：</th>
								<td><input type="text" class="easyui-validatebox x-input" data-options="required:true" name="cpnsName" required="true" value="${coupons.cpnsName }"></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="division liststyle data">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="liststyle2">
						<tbody>
							<tr>
								<th><em class="red">*</em>号码：</th>
								<td><span id="c_p">B</span><input class="easyui-validatebox x-input" data-options="required:true" type="text" name="cpnsPrefix" <c:if test="${!empty coupons }"> value="${fn:substring(coupons.cpnsPrefix,1,-1)}"</c:if>></td>
							</tr>
							<tr>
								<th>状态：</th>
								<td><label><input type="radio" name="cpnsStatus" value="1" <c:if test="${coupons.cpnsStatus=='1' }">checked="checked"</c:if> />启用</label> <label><input type="radio" name="cpnsStatus" value="0" <c:if test="${empty coupons || coupons.cpnsStatus=='0' }">checked="checked"</c:if> />禁用</label></td>
							</tr>
							<tr>
								<th><em class="red">*</em>优先级：</th>
								<td><input type="text" class="easyui-validatebox x-input" data-options="required:true" value="${coupons.sortOrder}" name="sortOrder" maxlength="5" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
							</tr>
							<tr>
								<th><em class="red">*</em>排他：</th>
								<td><input type="radio" name="stopRulesProcessing" value="1" <c:if test="${coupons.stopRulesProcessing=='1' }">checked="checked"</c:if> />是 <input type="radio" name="stopRulesProcessing" value="0" <c:if test="${empty coupons || coupons.stopRulesProcessing=='0' }">checked="checked"</c:if> />否</td>

							</tr>
							<tr>
								<th><em class="red">*</em>线上发放数量：</th>
								<td><input type="text" class="easyui-validatebox x-input" data-options="required:true" id="onlineQuantity" value="${coupons.onlineQuantity}" name="onlineQuantity" maxlength="5" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" required="true"></td>
							</tr>
							<tr>
								<th><em class="red">*</em>线上用户领取数量限制：</th>
								<td><input type="text" class="easyui-validatebox x-input" data-options="required:true" id="onlineLimit" value="${coupons.onlineLimit }" name="onlineLimit" maxlength="5" size="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" required="true"></td>
							</tr>
							<tr>
								<th>优惠券类型：</th>
								<td id="c_t">
									<!--
            <label><input id="c_t" type='radio' name='coupon[cpns_type]' value=0  checked="checked"/>A类优惠券&nbsp;&nbsp;<span class="font-gray">说明：此类优惠券，顾客只需获得一张，即可在规定的时间内重复使用。</span></label><br>
            --> <label><input type="radio" name="cpnsType" value="1" checked="checked">B类优惠券&nbsp;&nbsp;<span class="font-gray">说明：此类优惠券，顾客可一次获得多张，但在规定时间内每张只能使用一次，无法重复使用。 只限会员使用</span></label>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="division liststyle data">
					<table border="0" cellpadding="0" cellspacing="0" class="liststyle2">
						<tbody>
							<tr>
								<th><em class="red">*</em>规则描述：</th>
								<td><textarea type="textarea" class="easyui-validatebox inputstyle" data-options="required:true" name="description" cols="80" rows="10">${coupons.description }</textarea></td>
							</tr>
							<tr>
								<!-- 编辑时的隐藏域 -->
								<input type="hidden" id="start_time_hh_hide" <c:if test="${!empty coupons }"> value="${fn:substring(coupons.fromTime,11,13)}"</c:if> />
								<input type="hidden" id="start_time_mm_hide" <c:if test="${!empty coupons }"> value="${fn:substring(coupons.fromTime,14,16)}"</c:if> />
								<input type="hidden" id="end_time_hh_hide" <c:if test="${!empty coupons }"> value="${fn:substring(coupons.toTime,11,13)}"</c:if> />
								<input type="hidden" id="end_time_mm_hide" <c:if test="${!empty coupons }"> value="${fn:substring(coupons.toTime,14,16)}"</c:if> />
								<th>开始时间：</th>
								<td><input size="10" class="easyui-datebox cal" required="required" maxlength="10" type="text" name="fromTime" value="${coupons.fromTime }">&nbsp;&nbsp; <select id="start_time_hh"></select> : <select id="start_time_mm"></select></td>
							</tr>
							<tr>
								<th>结束时间：</th>
								<td><input size="10" value="${coupons.toTime }" class="easyui-datebox cal" maxlength="10" type="text" required="true" name="toTime">&nbsp;&nbsp; <select id="end_time_hh"></select> : <select id="end_time_mm"></select></td>
							</tr>
							<tr>
								<input type="hidden" id="memberIds" <c:if test="${!empty coupons }"> value="${coupons.memberLvIds }"</c:if> />
								<th><em class="red">*</em>会员级别：</th>
								<td id="mLev" class="mlev"></td>
							</tr>

						</tbody>
					</table>
				</div>
				<div style="text-align: center; margin-top: 20px;">
					<a class="btn btn-primary" type="button" id="btn_coupon_next"><span><span>下一步</span></span></a>
				</div>
			</div>

			<!-- 优惠券条件选择 -->
			<div id="coupon_conditions" style="display: none;">
				<h3 id="coupon_title">优惠条件</h3>
				<div class="division">

					<!-- 模板选择区域 -->
					<div class="can" id="ctpl_list" style="display: block;">
						<ul>

							<li><label><input name="cTemplate" type="radio" <c:if test="${coupons.cTemplate=='promotion_conditions_order_subtotalallgoods' }">checked="checked"</c:if> value="promotion_conditions_order_subtotalallgoods">当订单商品总价满X时,对所有商品优惠</label>
								<div class="tableform" <c:if test="${coupons.cTemplate=='promotion_conditions_order_subtotalallgoods' }">style="display:block;"</c:if>>
									订单金额满 :<input type="text" name="conditionsVal">
								</div></li>
							<li><label><input name="cTemplate" type="radio" <c:if test="${coupons.cTemplate=='promotion_conditions_order_itemsquanityallgoods' }">checked="checked"</c:if> value="promotion_conditions_order_itemsquanityallgoods">当订单商品数量满X,给予优惠</label>
								<div class="tableform" <c:if test="${coupons.cTemplate=='promotion_conditions_order_itemsquanityallgoods' }">style="display:block;"</c:if>>
									当订单商品数量满 <input type="text" name="conditionsVal"> 给予优惠
								</div></li>
							<li><label><input name="cTemplate" type="radio" <c:if test="${coupons.cTemplate=='promotion_conditions_order_allorderallgoods' }">checked="checked"</c:if> value="promotion_conditions_order_allorderallgoods">对所有订单给予优惠</label>
								<div class="tableform" <c:if test="${coupons.cTemplate=='promotion_conditions_order_allorderallgoods' }">style="display:block;"</c:if>>对所有订单给予优惠</div></li>
						</ul>
					</div>
				</div>
				<div style="text-align: center; margin-top: 20px;">
					<a class="btn btn-primary" type="button" id="c_coupon_prev"><span><span>上一步</span></span></a> <a class="btn btn-primary" type="button" id="c_coupon_next"><span><span>下一步</span></span></a>
				</div>
			</div>


			<div id="coupon_solution" style="display: none;">
				<h3 id="coupon_title">优惠方案</h3>
				<div class="division">
					<!-- 模板选择区域 -->

					<!-- 预过滤全部应用于商品 -->
					<div class="can_solution" id="stpl_list" style="display: block;">

						<ul>

							<li><label><input name="sTemplate" <c:if test="${coupons.sTemplate=='promotion_solutions_addscore' }">checked="checked"</c:if> type="radio" sort="0" value="promotion_solutions_addscore">订单赠送积分</label></li>
							<li><label><input name="sTemplate" <c:if test="${coupons.sTemplate=='promotion_solutions_topercent' }">checked="checked"</c:if> type="radio" sort="1" value="promotion_solutions_topercent">订单以固定折扣出售</label></li>
							<li><label><input name="sTemplate" <c:if test="${coupons.sTemplate=='promotion_solutions_bypercent' }">checked="checked"</c:if> type="radio" sort="2" value="promotion_solutions_bypercent">订单减去固定折扣出售</label></li>
							<li><label><input name="sTemplate" <c:if test="${coupons.sTemplate=='promotion_solutions_byfixed' }">checked="checked"</c:if> type="radio" sort="3" value="promotion_solutions_byfixed">订单减固定价格购买</label></li>
							<li><label><input name="sTemplate" <c:if test="${coupons.sTemplate=='promotion_solutions_getcoupon' }">checked="checked"</c:if> type="radio" sort="4" value="promotion_solutions_getcoupon">订单送优惠券</label></li>
						</ul>
					</div>
				</div>


				<div class="division solution_li">
					<ul>
						<li <c:if test="${coupons.sTemplate=='promotion_solutions_addscore' }">style="display:block;"</c:if>>订单赠送积分<input type="text"></li>
						<li <c:if test="${coupons.sTemplate=='promotion_solutions_topercent' }">style="display:block;"</c:if>>订单价格乘以<input type="text">%折扣出售
						</li>
						<li <c:if test="${coupons.sTemplate=='promotion_solutions_bypercent' }">style="display:block;"</c:if>>订单价格减去<input type="text">%折扣出售
						</li>
						<li <c:if test="${coupons.sTemplate=='promotion_solutions_byfixed' }">style="display:block;"</c:if>>订单价格优惠<input type="text">出售
						</li>
						<li <c:if test="${coupons.sTemplate=='promotion_solutions_getcoupon' }">style="display:block;"</c:if>>订单送优惠券<select class="easyui-combobox"></select></li>
					</ul>

				</div>
				<div style="text-align: center; margin-top: 20px;">
					<a class="btn btn-primary" type="button" id="s_coupon_prev"><span><span>上一步</span></span></a> <a class="btn btn-primary" type="button" id="s_coupon_next"><span><span>保存并关闭</span></span></a>
				</div>
			</div>


		</form>

	</div>
</body>
</html>

