<%@ page contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript">
	var _path = "${path}";
</script>
<script type="text/javascript" src="${path}/resources/scripts/decorator/footer.js?ver=8"></script>
</head>
<body>
	<div class="footer_t">
    	<div class="footer_t_l fl">
    		<dl>
	    		<dt><img src="${path}/resources/images/index/poh.jpg" alt="" width="32px" height="36px" /></dt>
	    		<dd>4001-022-772</dd>
    		</dl>
    		<dl>
	    		<dt><img src="${path}/resources/images/index/tim.jpg" alt="" width="32px" height="32px" /></dt>
	    		<dd>9:00-22:00<br />工作时间</dd>
    		</dl>
        </div>
        <div class="footer_t_m fl js_node">
        	<dl class="clearfix">
            	<dt style="display:none;"><span style="color: gray;">{nodeName}</span></dt>
                <dd style="display:none;"><a href="javascript:void(0);" onclick="toArticlePage(this,'{nodeId}')">{nodeName}</a></dd>
            	<dt><a href="javascript:;">购物指南</a></dt>
                <dd><a href="javascript:;">购物流程</a></dd>
                <dd><a href="javascript:;">会员介绍</a></dd>
                <dd><a href="javascript:;">团购活动</a></dd>
                <dd><a href="javascript:;">常见问题</a></dd>
                <dd><a href="javascript:;">购物指引</a></dd>
                <dd><a href="javascript:;">联系客服</a></dd>
            </dl>
            <br />
        	<dl class="clearfix">
            	<dt><a href="javascript:;">售后服务</a></dt>
                <dd><a href="javascript:;">售后政策</a></dd>
                <dd><a href="javascript:;">价格保护</a></dd>
                <dd><a href="javascript:;">退款说明</a></dd>
                <dd><a href="javascript:;">返修/退换货</a></dd>
                <dd><a href="javascript:;">如何办理退换货</a></dd>
                <dd><a href="javascript:;">取消订单</a></dd>
            </dl>
        </div>
        <div class="footer_t_m fl js_node">
        	<dl class="clearfix">
            	<dt><a href="javascript:;">支付方式</a></dt>
                <dd><a href="javascript:;">货到付款</a></dd>
                <dd><a href="javascript:;">在线支付</a></dd>
                <dd><a href="javascript:;">分期支付</a></dd>
                <dd><a href="javascript:;">到店支付</a></dd>
                <dd><a href="javascript:;">手机支付</a></dd>
                <dd><a href="javascript:;">银联转账</a></dd>
            </dl>
            <br />
        	<dl class="clearfix">
            	<dt><a href="javascript:;">特色服务</a></dt>
                <dd><a href="javascript:;">一元起拍</a></dd>
                <dd><a href="javascript:;">延保服务</a></dd>
                <dd><a href="javascript:;">YOYO豆</a></dd>
                <dd><a href="javascript:;">节能补贴</a></dd>
                <dd><a href="javascript:;">YOYO保障</a></dd>
            </dl>
        </div>
        <div class="footer_t_r fr js_end_node">
        	<dl class="clearfix">
            	<dt>YOYO自营覆盖范围</dt>
                <dd>YOYO已向全国推广，并提供自营服务，支持<br />POS机刷卡、到店支付和售后上门等服务。</dd>
            </dl>
            <ul>
	            <li>
	                <img src="${path}/resources/images/index/yoyou_z.jpg" width="65px" height="65px"/>
	            	<p>关注<span class="orange">YOYO</span><br />了解官网最新动态</p>
	            </li>
	            <li>
	                <img src="${path}/resources/images/index/yoyou_d.jpg" width="65px" height="65px"/>
	            	<p>下载<span class="orange">YOYO</span><br />了解官网最新动态</p>
	            </li>
            </ul>
        </div>
    </div>
	<div class="footer_b js_bottom">
    	<ul class="clearfix">
        	<li style="display:none;"><a href="javascript:void(0);" onclick="toArticlePage(this,'{nodeId}')">{nodeName}</a></li>
        	<li><a href="javascript:;">关于我们</a></li>
        	<li><a href="javascript:;">联系我们</a></li>
        	<li><a href="javascript:;">商家入驻</a></li>
        	<li><a href="javascript:;">营销中心</a></li>
        	<li><a href="javascript:;">手机YOYO</a></li>
        	<li><a href="javascript:;">友情链接</a></li>
        	<li><a href="javascript:;">销售联盟</a></li>
        	<li><a href="javascript:;">会员俱乐部</a></li>
        	<li class="br0"><a href="javascript:;">企业频道</a></li>
        	<li class="br0" style="display:none;"><a href="javascript:void(0);" onclick="toArticlePage(this,'{nodeId}')">{nodeName}</a></li>
        </ul>
   	  <p>CopyRight © 2015 ,All Rights Reserved. 版权所有 天津迪族信息技术有限公司   津B2-20070108-4号</p>
        <span><img src="${path}/resources/images/index/footer.png" width="297px" height="54px"/></span>
    </div>
    
 <script type="text/javascript">
 // 快捷导航返回顶部
	$('.mui ul .last').click(function(e) {
		// alert(1);
		$('html,body').animate({
			'scrollTop' : '0px'
		}, 0);
	}); 
</script>
 
</body>
</html>
