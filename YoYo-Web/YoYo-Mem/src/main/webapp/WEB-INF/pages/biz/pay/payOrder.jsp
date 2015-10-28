<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单支付</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/b2c.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/framework.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/pay/basic.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/paydemo/main.css">

<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/dataUtil.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/placeholder/jquery.enplaceholder.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/validity/jquery.validity.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
.lijizhifu2 {
	background: rgba(0, 0, 0, 0) url("../resources/images/lijizhifu2.gif")
		no-repeat scroll 0 0;
	border: 0 none;
	cursor: pointer;
	display: block;
	height: 37px;
	margin: 0 auto;
	width: 140px;
}

.payment-list .pl-item:hover {
	border: 1px solid #ff5d5b;
}
</style>
<style type="text/css">
.paybox.paybox-selected {
	background-color: #fff;
	border: 2px solid #b0c2e1;
	border-radius: 2px;
	margin-top: -2px;
	padding: 0;
}

.paybox .p-wrap {
	padding-bottom: 10px;
	padding-top: 10px;
	position: relative;
}

.paybox .p-key {
	float: left;
	padding-left: 18px;
	padding-top: 5px;
}
</style>
</head>

<body>
	<div class="webwidth">
		<div class="orderpay">
			<form id="f_order_pay" action="${path}/memberPayOrder/payOrder" method="post">
				<h3 class="payfor_order_title">订单信息</h3>
				<div class="p15 border-all over">
					<p class="lh25">
						订单编号：<strong id="orderId" class="font14px"><c:out value="${order.orderId}"></c:out></strong>&nbsp;&nbsp;[ <a id="viewOrder" href="${path}/memberOrder/viewOrder?orderId=<c:out value="${order.orderId}" />">查看订单详细信息»</a>]
					</p>
					<p class="lh25">
						订单金额：<strong class="hueorange font-red font16px" id="span_amount">￥<span id="amount"><fmt:formatNumber value="${order.finalAmount}" pattern="#,#00.00#" /></span></strong>
					</p>
				</div>
				<h3 class="payfor_order_title">订单支付</h3>
				<!-- 银行卡支付 -->
				<div class="ui-tab">
					<div class="ui-tab-items">
						<ul class="clearfix">
							<li class="ui-tab-item bw-tab-quick curr" data-widget="tab-item" id="quickPayCardMenuLi" onclick="quickCardShow();"><a href="javascript:;"><i></i>支付宝支付</a></li>
							<li class="ui-tab-item bw-tab-wangyin " data-widget="tab-item" id="normalPayCardMenuLi" onclick="normalPayCardShow();"><a href="javascript:;">网银支付</a></li>
						</ul>
					</div>
				</div>
				<br />

				<!-- 网银支付  start-->
				<div class="bw-tab-content" data-widget="tab-content" id="normalPayCardDiv" style="display: none;">
					<!-- 支付银行列表 -->
					<div class="payment-list j_eBankList" id="ebankPaymentListDiv" style="display: block;">
						<ul class="pl-wrap">
							<li class="pl-item" clstag="ICBCB2C"><span class="bank-logo" id="bank-icbc">工商银行</span></li>
							<li class="pl-item" clstag="CCB"><span class="bank-logo" id="bank-ccb">建设银行</span></li>
							<li class="pl-item" clstag="CMB"><span class="bank-logo" id="bank-cmb">招商银行</span></li>
							<li class="pl-item" clstag="ABC"><span class="bank-logo" id="bank-abc">农业银行</span></li>
							<li class="pl-item" clstag="COMM"><span class="bank-logo" id="bank-bcom">中国交通银行</span></li>
							<li class="pl-item" clstag="GDB"><span class="bank-logo" id="bank-gdb">广东发展银行</span></li>
							<li class="pl-item" clstag="BOCB2C"><span class="bank-logo" id="bank-boc">中国银行</span></li>
							<li class="pl-item" clstag="CMBC"><span class="bank-logo" id="bank-cmbc">中国民生银行</span></li>
							<li class="pl-item" clstag="CIB"><span class="bank-logo" id="bank-cib">兴业银行</span></li>
							<li class="pl-item" clstag="CEB-DEBIT"><span class="bank-logo" id="bank-ceb">中国光大银行</span></li>
							<li class="pl-item" clstag="POSTGC"><span class="bank-logo" id="bank-post">邮政储蓄</span></li>
							<li class="pl-item" clstag="CITIC"><span class="bank-logo" id="bank-citic">中信银行</span></li>
							<li class="pl-item" clstag="SPDB"><span class="bank-logo" id="bank-spdb">浦东发展银行</span></li>
							<li class="pl-item" clstag="SPABANK"><span class="bank-logo" id="bank-sdb">深圳发展银行</span></li>
							<li class="pl-item" clstag="BJBANK"><span class="bank-logo" id="bank-bob">北京银行</span></li>
							<li class="pl-item" clstag="SPABANK"><span class="bank-logo" id="bank-pab">平安银行</span></li>
							<li class="pl-item" clstag="HZCBB2C"><span class="bank-logo" id="bank-hzb">杭州银行</span></li>
							<li class="pl-item" clstag="SHBANK"><span class="bank-logo" id="bank-shb">上海银行</span></li>
							<!-- <li class="pl-more j_showBankMore" id="j_showBankLi" onclick="moreBank()"><span>更多银行</span></li> -->
						</ul>
						<!-- <div class="bw-more-unionpay">
							如果以上没有您需要的银行，请使用&nbsp;<a href="javascript:void(0)" onclick="unionPaySubmit();">银联在线支付</a>
						</div> -->
					</div>

				</div>
				<!-- 网银支付end -->
				<!-- 第三方支付start -->
				<div class="bw-tab-content" data-widget="tab-content" id="quickPayCardDiv">
					<!-- 支付银行列表 -->
					<div class="payment-list j_quickBankList" id="quickPayListDiv">
						<ul class="pl-wrap">
							<li class="pl-item pay" id="alipay" style="height: 38px;" onclick="selectedpay('alipay')"><img src="${portalPath }/resources/images/bank/zfb2.gif" alt="支付宝"></label></li>
							<!-- <li class="pl-item pay" id="bank" style="height: 38px;" onclick="selectedpay('bank')"><img src="${portalPath }/resources/images/bank/bank_0.gif" alt="在线支付"></li>
							<li class="pl-item pay" id="cft" style="height: 38px;" onclick="selectedpay('cft')"><img src="${portalPath }/resources/images/bank/cft2.gif" alt="财富通"></li>
							<li class="pl-item pay" id="kq" style="height: 38px;" onclick="selectedpay('kq')"><img src="${portalPath }/resources/images/bank/kq2.gif" alt="快钱"></li> -->
						</ul>
					</div>
				</div>
			</form>
		</div>
		<div class="textcenter p10">
			<button type="submit" onclick="submitBtn();" disabled="disabled" id="payBtn" class="btn order-btn lijizhifu2 btn-has-icon btn-has-icon"></button>
		</div>

	</div>
	<div class="clr"></div>
<script type="text/javascript">
		var orderId = $('#orderId').text();
		var bankId="";
		$(function(){
			//showBank();
			if(/^333/.test(orderId)){
				$('#viewOrder').attr('href','../mypainting/viewOrder?orderId='+orderId);
			}
			$("#ebankPaymentListDiv .pl-item").on("click",function (){
				$("#ebankPaymentListDiv .pl-item").attr("style","");
				$('#payBtn').removeAttr("disabled").removeClass("lijizhifu2").addClass("lijizhifu");
				bankId=$(this).attr('clstag');
				$(this).attr("style","border-color: #ff5d5b");
			});
			
		});
	function showBank(){
		$("#j_showBankLi").show();
       var n_ul = $("#ebankPaymentListDiv").find("ul");
       var n_lis=n_ul.children();
       if(n_lis.length>11){
       for(var i=0;i<n_lis.length;i++){
           if(i>=11){
               if(n_lis[i].id !="j_showBankLi"){
                   n_lis[i].className =n_lis[i].className + " hide";
               }
           } else{
               if(n_lis[i].id !="j_showBankLi"){
                   n_lis[i].className =n_lis[i].className;
               }
           }
       }
       } else{
           $("#ebankPaymentListDiv").find(".j_showBankMore").hide();
       }
	}
	function submitBtn(){
		$('#tips_warp').attr("style","display: block");
		$('#tips_overlay').attr("style","display: block");
		
		
		var viewUrl='';
		if(/^333/.test(orderId)){
			viewUrl=yoyo.memUrl+"/mypainting/viewOrder?orderId="+orderId;
		}else{
			viewUrl=yoyo.memUrl+"/memberOrder/viewOrder?orderId="+orderId;
		}
		
		$('#orderDetail').attr("href",viewUrl);
		var WIDsubject= $('#orderId').text();
		var WIDbody="YOYO商城订单："+$('#orderId').text();
		//var WIDtotal_fee=$("#amount").html();
		var WIDtotal_fee=0.01;
		var url=yoyo.portalUrl+'/resources/alipayapi.jsp?WIDdefaultbank='+bankId+'&WIDout_trade_no='+ orderId +'&WIDsubject='+ WIDsubject +'&WIDtotal_fee='+ WIDtotal_fee+'&WIDbody='+WIDbody+'&WIDshow_url='+viewUrl;
		window.open(url,"_blank");
	}
	
	function closePay(){
		$('#tips_warp').attr("style","display: none");
		$('#tips_overlay').attr("style","display: none");
	}
	//第三方支付
     function quickCardShow(){
    	 $(".pay").attr("style","height:38px;");
    	 $("#ebankPaymentListDiv .pl-item").attr("style","");
    	 $('#payBtn').attr("disabled","disabled").removeClass("lijizhifu").addClass("lijizhifu2");
    	 $("#quickPayCardMenuLi").addClass("curr");
    	 $("#normalPayCardMenuLi").removeClass("curr");
	   	 $("#quickPayCardDiv").show();
	   	 $("#normalPayCardDiv").hide();
    }
	//网银支付
    function normalPayCardShow(){
	   	 $(".pay").attr("style","height:38px;");
	   	 $("#ebankPaymentListDiv .pl-item").attr("style","");
	   	 $('#payBtn').attr("disabled","disabled").removeClass("lijizhifu").addClass("lijizhifu2");
	   	 $("#quickPayCardMenuLi").removeClass("curr");
	   	 $("#normalPayCardMenuLi").addClass("curr");
	   	 $("#quickPayCardDiv").hide();
	   	 $("#normalPayCardDiv").show();
    }
	function selectedpay(type){
		$(".pay").attr("style","height:38px;");
		bankId="";
		if(type=='alipay'){
			$("#payBank").val('alipay');
			$("#alipay").attr("style","border-color: #ff5d5b;height:38px;");
			$('#payBtn').removeAttr("disabled").removeClass("lijizhifu2").addClass("lijizhifu");
		}else if(type=='bank'){
			$("#bank").attr("style","border-color: #ff5d5b;height:38px;");
		}else if(type=='cft'){
			$("#cft").attr("style","border-color: #ff5d5b;height:38px;");
		}else if(type=='kq'){
			$("#kq").attr("style","border-color: #ff5d5b;height:38px;");
		}
	}
	function moreBank(){
		$(".pl-item").show();
		$("#j_showBankLi").hide();
	}
</script>
	<div id="tips_overlay" class="tips_overlay" style="display: none; height: 955px;"></div>
	<div class="gmTips_warp" id="tips_warp" style="display: none;">
		<div class="gmTips_hd gmTips_hd_w" id="tips_hd">
			<b class="gmTips_close gClose" onclick="closePay();"></b><span class="gmTips_title">支付反馈</span>
		</div>
		<div class="gmTips_bd" id="tips_con">
			<div class="popBox">
				<div class="popContent">
					<p>请您在新打开的网上银行页面进行支付，支付完成后选择：</p>
					<p>
						<i class="sbIcoR"></i><b>支付成功：</b><a pname="支付反馈" positionid="1" href="#" id="orderDetail" class="nBlue nStat_pop pay-ckdd">查看订单</a> <a pname="支付反馈" positionid="2" href="${portalPath }/search" class="nBlue nStat_pop">继续购物</a>
					</p>
					<p>
						<i class="sbIcoW"></i><b>支付失败：</b> <a pname="支付反馈" positionid="3" href="javascript:closePay();" class="nBlue nStat_pop pay-xzqt">选择其他银行支付</a> <a pname="支付反馈" positionid="4" href="javascript:void(0);" class="nSever live online-cs-pop nBlue nStat_pop">联系在线客服</a> <a pname="支付反馈" positionid="5" target="_blank" href="#" class="nBlue nStat_pop">查看支付帮助</a>
					</p>
					<p style="padding-top: 10px;">
						<b>支付遇到问题？</b>
					</p>
					<dl class="pay-questions">
						<dt>
							<b></b>网上银行页面打不开怎么办？
						</dt>
						<dd>建议使用ie浏览器，点击菜单：工具→Internet选项→安全，将安全中的各项设置恢复到默认。</dd>
						<dt>
							<b></b>支付额度受限怎么处理？
						</dt>
						<dd>如果您遇到订单金额超过支付限额，建议使用银联在线，最高支付额度为3万元。</dd>
						<dt>
							<b></b>银行卡已扣款，但仍显示待支付怎么办？
						</dt>
						<dd>由于网络原因，部分用户支付后支付金额未能及时到账，请稍后刷新查看。</dd>
					</dl>

				</div>
			</div>
		</div>
	</div>
</body>
</html>