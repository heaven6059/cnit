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
<%@include file="/resources/include/head.jsp" %>
</head>
<body>
<div class="webwidth">
       <div class="orderpay">
<form id="f_order_pay" target="_blank" action="/paycenter-dopayment-order.html" method="post">
<input type="hidden" name="payment[order_id]" value="2015051217166637">
<input type="hidden" name="payment[money]" value="2200.00" id="hidden_money">
<input type="hidden" name="payment[currency]" value="CNY">
<input type="hidden" name="payment[cur_money]" value="2200.00" id="hidden_cur_money">
<input type="hidden" name="payment[cur_rate]" value="1.0000">
<input type="hidden" name="payment[cur_def]" value="￥">
<input type="hidden" name="payment[pay_app_id]" value="alipay">
<input type="hidden" name="payment[cost_payment]" value="0.000">
<input type="hidden" name="payment[cur_amount]" value="2200.00">
<input type="hidden" name="payment[memo]" value="">
<!--<input type="hidden" name="payment[return_url]" value="/paycenter-result.html" />-->

<!--<div class="success clearfix pushdown-2">
   <h3>恭喜！您的订单已经提交！</h3>

</div>-->

<h3 class="payfor_order_title">订单信息</h3>

  <div class="p15 border-all over">
      <p class="lh25">订单编号：<strong class="font14px">2015051217166637</strong>&nbsp;&nbsp;[ <a href="/member-orderdetail-2015051217166637.html">查看订单详细信息»</a> ]</p>
     <p class="lh25">订单金额：<strong class="hueorange font-red font16px" id="span_amount">￥2200.00</strong></p>
  </div>

<h3 class="payfor_order_title">订单支付</h3>


<div class="p15 border-all over">
<div class=" p5 fontbold">
您选择了：<strong class="hueorange font-red font12px">支付宝</strong>
	 <a href="/member-orderPayments-2015051217166637-true.html">[ 选择其他支付方式 ]</a><!--zongfang 付款银行卡方式暂时勿动-->

<div class="cart_paymentzf" id="cart_paymentzf" style="display:none">
           <div class="tip_tishi"><i class="deng"></i>为了顺利支付，建议选择<span>快捷支付</span>和<span>IE系列</span>的浏览器，如支付遇到问题，请致电<span>xxxx-xxxxxxxx</span></div>
           <!-- <div class="tip_short"><b>【推荐】</b>无需开通网银</div> -->
           <ul class="cart_paymentzf_tab">
                  <!--<li class="current" id="business"><a href="javascript:void(0);">快捷支付</a></li>	-->
                  <li id="preson" class="current"><a href="javascript:void(0);">储蓄卡</a></li>
                  <li id="credit"><a href="javascript:void(0);">信用卡</a></li>
           </ul>
            <div class="crl"></div>
<input autocomplete="off" class="x-input " name="payment[bankaccounttype]" id="bank_type" value="92" type="hidden">
            <div class="crl"></div>
            <div class="cart_payarea crl_xu" id="preson_bank" style="">
              <p class="font999">请选择在线支付的方式：</p>
              <h5 class="fasth5">快捷支付<span class="font999">一步验证，无需网银！</span><i class="recm"></i></h5>
              <ul class="cart_payarealist">
                <li id="92">
				 <input id="r9001000" value="9001000" onclick="confirmBank(this)" name="payment[banktype]" type="radio" checked="checked">
                <label class="limited-coupon" for="r9001000">
                   <span title="中国农业银行" class="icon_bank bank_online"></span> <span class="bank-name">银联在线</span> </label>
                </li>
                <div class="crl"></div>
              </ul>
              <div class="crl"></div>
              
              
           <!-- <div class="cart_payarea crl_xu" id="preson_bank" style="display:none;"> -->
                   <h5>网银支付<span class="font999">需开通网银</span></h5>
                   <ul class="cart_payarealist">
					                          <li id="11">
						  <input id="1021000" type="radio" value="1021000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="1021000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_gs"></span>
								<span class="bank-name">中国工商银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="1031000" type="radio" value="1031000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="1031000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_abc"></span>
								<span class="bank-name">中国农业银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="1041000" type="radio" value="1041000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="1041000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_china"></span>
								<span class="bank-name">中国银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="1051000" type="radio" value="1051000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="1051000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_js"></span>
								<span class="bank-name">中国建设银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3012900" type="radio" value="3012900" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3012900" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_jt"></span>
								<span class="bank-name">中国交通银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3021000" type="radio" value="3021000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3021000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_zx"></span>
								<span class="bank-name">中信银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3031000" type="radio" value="3031000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3031000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_gd"></span>
								<span class="bank-name">中国光大银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3051000" type="radio" value="3051000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3051000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_ms"></span>
								<span class="bank-name">中国民生银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3065810" type="radio" value="3065810" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3065810" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_gf"></span>
								<span class="bank-name">广东发展银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3071000" type="radio" value="3071000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3071000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_pa"></span>
								<span class="bank-name">平安银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3085840" type="radio" value="3085840" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3085840" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_zs"></span>
								<span class="bank-name">招商银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3093910" type="radio" value="3093910" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3093910" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_xy"></span>
								<span class="bank-name">兴业银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3102900" type="radio" value="3102900" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3102900" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_pf"></span>
								<span class="bank-name">上海浦东发展银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3131000" type="radio" value="3131000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3131000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_bj"></span>
								<span class="bank-name">北京银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3133010" type="radio" value="3133010" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3133010" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_nj"></span>
								<span class="bank-name">南京银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3133320" type="radio" value="3133320" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3133320" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_nb"></span>
								<span class="bank-name">宁波银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="3222900" type="radio" value="3222900" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="3222900" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_shnc"></span>
								<span class="bank-name">上海农村商业银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="4031000" type="radio" value="4031000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="4031000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_ems"></span>
								<span class="bank-name">中国邮政储蓄银行</span>
								</label>
                          </li>						  
					                          <li id="11">
						  <input id="5021000" type="radio" value="5021000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="5021000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_dy"></span>
								<span class="bank-name">东亚银行</span>
								</label>
                          </li>						  
										
                    </ul>
                    <div class="crl"></div>
					
					<h5 class=" crl_xu">企业银行</h5>
					<ul class="cart_payarealist">
						                          <li id="21">
						  <input id="q1021000" type="radio" value="1021000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="q1021000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_comgs"></span>
								<span class="bank-name">中国工商银行</span>
								</label>
                          </li>
					                          <li id="21">
						  <input id="q1031000" type="radio" value="1031000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="q1031000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_comny"></span>
								<span class="bank-name">中国农业银行</span>
								</label>
                          </li>
					                          <li id="21">
						  <input id="q1041000" type="radio" value="1041000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="q1041000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_comchina"></span>
								<span class="bank-name">中国银行</span>
								</label>
                          </li>
					                          <li id="21">
						  <input id="q1051000" type="radio" value="1051000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="q1051000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_comjs"></span>
								<span class="bank-name">中国建设银行</span>
								</label>
                          </li>
					                          <li id="21">
						  <input id="q3031000" type="radio" value="3031000" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="q3031000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_comgd"></span>
								<span class="bank-name">中国光大银行</span>
								</label>
                          </li>
					                          <li id="21">
						  <input id="q3102900" type="radio" value="3102900" onclick="confirmBank(this)" name="payment[banktype]">
                          <label for="q3102900" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_compf"></span>
								<span class="bank-name">上海浦东发展银行</span>
								</label>
                          </li>
											  <li id="91">
						  		<input id="r9001001" type="radio" value="9001000" onclick="confirmBank(this)" name="payment[banktype]">
								<label for="r9001001" class="limited-coupon  current">
								<span title="中国农业银行" class="icon_bank bank_online"></span>
								<span class="bank-name">银联在线</span>
								</label>
                          </li>						  
					</ul>
                    <div class="crl"></div>
            </div>
			<div class="cart_payarea crl_xu" id="credit_bank" style="display:none;">
              <p class="font999">请选择在线支付的方式：</p>
              <h5 class="fasth5">快捷支付<span class="font999">一步验证，无需网银！</span><i class="recm"></i></h5>
              <ul class="cart_payarealist">
                <li id="92">
				<input id="r9001002" value="9001000" onclick="confirmBank(this)" name="payment[banktype]" type="radio">
                <label for="r9001002" class="limited-coupon"> 
                  <span title="中国农业银行" class="icon_bank bank_online"></span> <span class="bank-name">银联在线</span> </label>
                </li>
                <div class="crl"></div>
              </ul>
              <div class="crl"></div>
              <h5>网银支付<span class="font999">需开通网银</span></h5>
                   <ul class="cart_payarealist">
					                          <li id="12">
						  <input id="e1021000" type="radio" value="1021000" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e1021000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_gs"></span>
								<span class="bank-name">中国工商银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e1031000" type="radio" value="1031000" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e1031000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_abc"></span>
								<span class="bank-name">中国农业银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e1041000" type="radio" value="1041000" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e1041000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_china"></span>
								<span class="bank-name">中国银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e1051000" type="radio" value="1051000" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e1051000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_js"></span>
								<span class="bank-name">中国建设银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e3012900" type="radio" value="3012900" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e3012900" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_jt"></span>
								<span class="bank-name">中国交通银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e3031000" type="radio" value="3031000" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e3031000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_gd"></span>
								<span class="bank-name">中国光大银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e3051000" type="radio" value="3051000" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e3051000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_ms"></span>
								<span class="bank-name">中国民生银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e3065810" type="radio" value="3065810" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e3065810" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_gf"></span>
								<span class="bank-name">广东发展银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e3085840" type="radio" value="3085840" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e3085840" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_zs"></span>
								<span class="bank-name">招商银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e3093910" type="radio" value="3093910" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e3093910" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_xy"></span>
								<span class="bank-name">兴业银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e3102900" type="radio" value="3102900" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e3102900" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_pf"></span>
								<span class="bank-name">上海浦东发展银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e3133320" type="radio" value="3133320" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e3133320" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_nb"></span>
								<span class="bank-name">宁波银行</span>
								</label>
                          </li>						  
					                          <li id="12">
						  <input id="e5021000" type="radio" value="5021000" onclick="confirmBank(this)" name="payment[banktype]">
						 <label for="e5021000" class="limited-coupon">
								<span title="中国农业银行" class="icon_bank bank_dy"></span>
								<span class="bank-name">东亚银行</span>
								</label>
                          </li>						  
										
                    </ul>
                    <div class="crl"></div>
            </div>

</div><!--cart_paymentzf end-->

<script>
	window.addEvent('domready',function(){
		var tabLi = $$('.cart_paymentzf_tab').getChildren('li');
		$$('.cart_paymentzf_tab').getChildren('li').each(function(item){
			item.addEvent('click',function(el){
				item.removeClass('current');
				$(this).addClass('current');	
				$$('.cart_payarea').each(function(elk){
					elk.style.display = 'none';
				});
				id = $(this).id;
				$(id+"_bank").setStyle('display','');
			});
			
		});
	});

	function confirmBank(obj){
		var objLi = obj.parentNode;
		$('bank_type').value = objLi.id;
	}
</script></div>
			<input type="hidden" name="payment[pay_app_id]" value="alipay">
		
</div>

<div class="textcenter p10">
  <button type="submit" class="btn order-btn lijizhifu btn-has-icon btn-has-icon"><span><span><i class="btn-icon"><img src="/app/b2c/statics/bundle/set-arrow.gif"></i>立刻支付</span></span></button>
</div>


</form>

</div>
<div class="clr"></div>
<script>
        void function(){
        var form= $('f_order_pay');
            Order ={

                paymentChange:function(target){
                         if(!target)return;
                         target = $(target);
                     var money  = target.get('moneyamount');
                     var fmoney = target.get('formatmoney');
                     var paytype= target.get('paytype');
					 var cur_money = target.get('curmoney');

                     $('hidden_money').set('value',money);
                     $('hidden_cur_money').set('value',cur_money);
                     $('span_amount').set('text',fmoney);

					 var btn_odr = form.getElement('button[type=submit]');

                     btn_odr.getElement('span span').innerHTML = paytype!='offline'?'支&nbsp;付<i class="btn-icon"> <img src="/app/b2c/statics/bundle/set-arrow.gif" /></i>':'确定';
					  					
					 if (paytype!='offline'){
						btn_odr.set('class', 'btn order-btn btn-has-icon lijizhifu');
					 }else{
						btn_odr.set('class', 'btn order-btn lijizhifu');

					 }
                     if(paytype=='ysepay'){
                        $('cart_paymentzf').setStyle('display','');
                    }else{
                        $('cart_paymentzf').setStyle('display','none');
                    }
                     //form.getElement('button[type=submit]')[(paytype=='offline'?'addClass':'removeClass')]('btn-pay-ok');
                     /* $$('#_normal_payment th .ExtendCon input[type=radio]').fireEvent('checkedchange');*/
                     
                }

            };

            var selecttype = "0";
			if(selecttype==0){
				if(form&&form.getElement('button[type=submit]')){
					form.getElement('button[type=submit]').addEvent('click',function(e){
						var bank_type = $('bank_type').value;
						var paytypeId = $ES('#_normal_payment input[type=radio]:checked');
						var pay_app_id_js = "alipay";
						if(bank_type==''&&pay_app_id_js=='ysepay'){
							 Message.error('请选择一个银行');
							 return e.stop();
						}
						testMessageBox();
					});
				} 
			}

            if($('f_order_pay').getElement('.select-paymethod')){
                Order.paymentChange($('f_order_pay').getElement('.select-paymethod input[checked]'));

                if(form&&form.getElement('button[type=submit]')){
                    form.getElement('button[type=submit]').addEvent('click',function(e){

                        if(!$('f_order_pay').getElement('.select-paymethod input[checked]')){
                        Message.error('请选择支付方式');
                        return e.stop();
                        }else{
							var bank_type = $('bank_type').value;
							var paytypeId = $ES('#_normal_payment input[type=radio]:checked').get('paytype');
							if(paytypeId=='ysepay'&&bank_type==''){
								 Message.error('请选择一个银行');
								 return e.stop();
							}
						}

                    });
                }
            }
          
        }();
<!-- 弹出层效果开始 -->
window.onload = function(){
	document.getElementById('preson').onclick = function(){
		document.getElementById('r9001000').checked = 'true';	
		document.getElementById('bank_type').value="92"
	}
	document.getElementById('credit').onclick = function(){
		document.getElementById('r9001002').checked = 'true';	
		document.getElementById('bank_type').value="92"
	}
}
var isIe=(document.all)?true:false;
function setSelectState(state)
{
var objl=document.getElementsByTagName('select');
for(var i=0;i<objl.length;i++)
{
objl[i].style.visibility=state;
}
}

 function btnHover(){
	var bg1 = document.getElementById('paybtn');
	var bg2 = document.getElementById('payspan');
	var msOver = function(){
		bg1.style.backgroundPosition = '0px -50px';
		bg2.style.backgroundPosition = 'right -134px';
		
	}
	var msOut = function(){
		bg1.style.backgroundPosition = '0px -81px';
		bg2.style.backgroundPosition = 'right -165px';
	}
	bg1.onmouseover = msOver;
	bg2.onmouseover = msOver;
	bg1.onmouseout = msOut;
	bg2.onmouseout = msOut;
}

function showMessageBox(wTitle,content,wHeight,wWidth)
{

var bWidth=parseInt(document.documentElement.scrollWidth);
var bHeight=parseInt(document.documentElement.scrollHeight);
if(isIe){
setSelectState('hidden');}
var back=document.createElement("div");
back.id="back";
var styleStr="top:0px;left:0px;position:absolute;background:#666;width:"+bWidth+"px;height:"+bHeight+"px;";

back.style.cssText=styleStr;
document.body.appendChild(back);

var mesW=document.createElement("div");
mesW.id="mesWindow";
mesW.className="mesWindow";
mesW.innerHTML="<div class='mesWindowTop'><table width='100%' height='100%'><tr style='height:40px;'><td>"+wTitle+"</td><td style='width:1px;'><input type='button' onclick='closeGo();' title='关闭窗口' class='close' value='' /></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'></div>";

styleStr="left:50%;top:200px;margin-left:"+(-(wWidth/2))+"px;position:fixed;width:"+wWidth+"px;";
mesW.style.cssText=styleStr;
document.body.appendChild(mesW);


}
function closeGo(){
		window.location.href="/member-orderdetail-2015051217166637.html";
}

//关闭窗口
function closeWindow()
{
if(document.getElementById('back')!=null)
{
document.getElementById('back').parentNode.removeChild(document.getElementById('back'));
}
if(document.getElementById('mesWindow')!=null)
{
document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
}
if(isIe){setSelectState('');}
refres_h();
}
function refres_h(){
  window.location.href=window.location.href;  
  window.location.reload;   	
}
function testMessageBox(ev)
{
//窗口内容
messHead = '支付';
messContent='<div><p class="lightgray">请您在新打开的网上银行页面进行支付，支付完成后选择:</p><ul><li><span class="success">&nbsp;</span><span class="text">付款成功</span><span class="lightgray"> ｜您可以选择：</span><a class="ftbl ml10" href="javascript:;" onclick="closeGo();" >查看订单</a></li><li><span class="error_m">&nbsp;</span><span class="text">付款失败</span><span class="lightgray"> ｜建议您选择：</span><a class="ftbl ml10" href="javascript:;" onclick="closeWindow();" >选择其他支付方式</a></li><p class="p2">如遇到支付问题，请致电<span class="ftred">xxxx-xxxxxxxx</span>或联系平台<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=123456&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:123456:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></p></ul></div>';
showMessageBox(messHead,messContent,250,616);
//btnHover();
}

<!-- 弹出层效果结束-->
</script>
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
</body>
</html>