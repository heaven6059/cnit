<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品咨询</title>
<script type="text/javascript">var _path="${path}";</script>
<script src="${path}/resources/scripts/common/yoyo.js"></script>
<%-- <link type="text/css" href="${path}/resources/styles/goods/jd/base.css" rel="stylesheet" /> --%>
<link type="text/css" href="${path}/resources/styles/base.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/consult.css" rel="stylesheet" />
<%-- <link type="text/css" href="${path}/resources/styles/goods/jd/base.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/details.css" rel="stylesheet" /> --%>
<script src="${path}/resources/scripts/biz/goods/consult.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>

<!--css文件-->
<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/base.css"> --%>
<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/common.css"> --%>
<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/driveAndConsult.css"> --%>


<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/goods.css"> --%>
</head>
<body>
<div id="main_container" class="root61" style="width: 1200px !important; margin:auto; height: auto;display: table;">
  <div class="left">
		<!--pinfo start-->
<div class="m" id="pinfo">
<div class="mt">
<h2>商品信息</h2>
<input type="hidden" id="goods_id" value="${goodsId}" />
<input type="hidden" id="product_id" value="${productId}" />
</div>
<div class="mc">
	<div class="p-img">
		<a href="" target="_blank"> 
			<img alt="" src="" width="130px" height="130px"> 
		</a>
	</div>
	<div class="p-name">
		商品名称：<a href="" target="_blank">
		
		</a>
	</div>
	<div class="p-price">优优价：
		<strong class="">￥0.00</strong>
	</div>
	<div class="p-grade clearfix">
		<span class="fl">评价得分：</span>
		<div class="fl">
			<div id="product_star" class="star sa5"></div>
			(<span id="product_star_score">0</span>分)
		</div>
	</div>
	<div class="num-comment">
                            评论数： <span id="p-num-comment">0条</span>
	</div>

	<div class="hide">
		<a href="http://club.jd.com/allconsultations/1093258-1-1.html" target="_blank">商品咨询</a>
	</div>

	<div class="btn">
		<a class="btn-append" href="javascript:addCart()">添加到购物车</a>
	</div>
	
</div>

</div>

<div style="display:none;">
<ul>
        <li><a href="http://jae.jd.com">京东云擎</a> </li>    
        <li><a href="http://loan.jd.com/">京东京小贷</a> </li>    
        <li><a href="http://licai.bx.jd.com/">京东保险理财</a> </li>    
        <li><a href="http://item.jd.com/11417193.html">贝克汉姆</a> </li>    
        <li><a href="http://jr.jd.com/stubaitiao/">京东校园白条</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/45241.html">比特币是什么</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/30930.html">孕妇能吃巧克力吗</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/45046.html">iphone5s和iphone5的区别</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/46940.html">鱼腥草的功效与作用及食用方法</a> </li>    
        <li><a href="http://club.jr.jd.com/licai/jingxuan">京东理财论坛</a> </li>    
        <li><a href="http://re.jd.com/">京东热卖</a> </li>    
        <li><a href="http://loan.jd.com/home.html">京小贷</a> </li>    
        <li><a href="http://go.jd.com/activity/introduce">京东旅游白条</a> </li>    
        <li><a href="http://baitiao.jd.com/">京东白条</a> </li>    
        <li><a href="http://vip.jd.com">京东会员</a> </li>    
        <li><a href="http://group.jd.com/index/20000001.htm">京东游戏社区</a> </li>    
        <li><a href="http://diaoyan.jd.com/">京东调研</a> </li>    
        <li><a href="http://item.jd.com/1045274.html">LG G flex</a> </li>    
        <li><a href="http://www.wangyin.com">网银钱包</a> </li>    
        <li><a href="http://www.letv.com/tv/10002953.html">学姐知道</a> </li>    
        <li><a href="http://luopan.jd.com">京东数据罗盘</a> </li>    
        <li><a href="http://smarthome.jd.com/">京东智能云</a> </li>    
        <li><a href="http://item.jd.com/1013281.html">努比亚小牛2 Z5S mini</a> </li>    
        <li><a href="http://jipiao.jd.com/city/">打折机票查询</a> </li>    
        <li><a href="http://wiki.jd.com/">京东</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/365.html">移动硬盘分区</a> </li>    
        <li><a href="http://red.jd.com/">闪购</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/2518.html">蜂蜜水什么时候喝好</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/10856.html">长痘痘的位置图解</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/46358.html">皮肤干燥起皮怎么办</a> </li>    
</ul>
</div>

<script type="text/javascript">
$.ajax({
	type:"GET",
	url:"http://club.jd.com/ProductPageService.aspx?method=GetCommentSummaryBySkuId&referenceId=1093258",
	data:{},
	dataType:'jsonp',
	success:function(data){
		$('#product_star').removeClass().addClass('star sa'+data.AverageScore);
		$('.fl').children('#product_star_score').html(data.AverageScore);
		if(!isNaN(data.CommentCount) && data.CommentCount>0){
			$('#p-num-comment').html(data.CommentCount+"条");
		}
		
	}
});
function getProductPrice(){
    if(parseInt('1093258')> 0) { 
    $.ajax({
    	type:"GET",
    	url:"http://p.3.cn/prices/mgets",
    	data:{ skuids:'J_1093258' , 
            type:1        
        },
    	dataType:'jsonp',
    	success:function(data){  
    	for( var key in data){   
         	   if(data[key]['p'] && parseFloat(data[key]['p'])>=0) {  
        	        $('.c-'+data[key]['id']).html('￥'+data[key]['p']);             
        	   } 		
    		}
    	}
    });
    }
}
$().ready(function (){
getProductPrice();
});
</script>			
		<!--pinfo end-->	
			<div id="miaozhen8151" class="AD_L"></div>
		</div>
		
		
		
		
		
		
		
		
		
		
		
		
		<div class="right" style="width:979px;">
			<div class="View_Mode">
	<span class="float_Right">
		<a href="${path}/goodsManager/goodsIndex?goodsId=${goodsId}" class="link_1">返回商品页&gt;&gt;</a>
	</span>
	<div class="viewindex   viewindex_curr  ">
		<a href="javascript:;">全部购买咨询</a>
	</div>
	<!-- <div class="viewindex  ">
		<a href="http://club.jd.com/allconsultations/1093258-1-2.html">商品咨询</a>
	</div>
	<div class="viewindex  ">
		<a href="http://club.jd.com/allconsultations/1093258-1-3.html">库存配送</a>
	</div>
	<div class="viewindex  ">
		<a href="http://club.jd.com/allconsultations/1093258-1-4.html">支付</a>
	</div>
	<div class="viewindex  ">
		<a href="http://club.jd.com/allconsultations/1093258-1-5.html">发票保修</a>
	</div> -->
</div>
<ul class="column_wxts" style="width:937px;">
    <li class="column_wxts_ss">
    	<a name="rs"></a>
    	<strong>咨询前请选搜索，方便又快捷：</strong>
    	<input type="text" class="input1" id="txbReferSearch" value="" style="color: rgb(68, 68, 68);">
    	<input type="button" class="btn1" id="btnReferSearch" value="">
    </li>
    <li class="column_wxts_ts" style="width:686px;"><div><strong>温馨提示:</strong>因厂家更改产品包装、产地或者更换随机附件等没有任何提前通知，且每位咨询者购买情况、提问时间等不同，为此以下回复仅对提问者3天内有效，其他网友仅供参考！若由此给您带来不便请多多谅解，谢谢！</div></li>
</ul>
<div class="refer_info" id="column_wxts_ssjg" style="display:none"></div>
<div class="Refer_List">
			<div class="refer" style="display:none;">
				<div class="r_info">网友：<!-- <a rel="nofollow" href="http://club.jd.com/userreview/40092433-1-1.html" target="_blank">
					jd_mice01
</a>&nbsp;&nbsp;&nbsp;&nbsp;<em id="level" name=""></em>&nbsp;&nbsp;&nbsp;&nbsp;2015-04-16 14:39:56 -->
					<span>jd_mice01</span>
					&nbsp;&nbsp;&nbsp;&nbsp;<em id="level" name=""></em>&nbsp;&nbsp;&nbsp;&nbsp;
					<span>2015-04-16 14:39:56</span>
				</div>
		<dl class="ask">
			<dt>咨询内容：</dt>
			<dd><!-- <a target="_blank" href="http://club.jd.com/consultation/1093258-49375432.html">
                你好，戴尔N4110  能使用吗？
				</a> -->
				<span>你好，戴尔N4110  能使用吗？</span>
			</dd>
		</dl>
		<dl class="answer">
			 
			<dt>京东回复：</dt>
						<dd>
				您好！可以。感谢您对京东的支持！祝您购物愉快！
			</dd> 
					</dl>
			 
	</div>
		
			
		
			
	</div>
<div class="yoyoPagination">
	<!-- <a class="prev" href="http://club.jd.com/allconsultations/1093258-5-1.html">上一页<b></b></a>
	<a href="http://club.jd.com/allconsultations/1093258-1-1.html">1</a>
	<span>...</span>
	<a href="http://club.jd.com/allconsultations/1093258-4-1.html">4</a>
	<a href="http://club.jd.com/allconsultations/1093258-5-1.html">5</a>
	<a class="current">6</a>
	<a href="http://club.jd.com/allconsultations/1093258-7-1.html">7</a>
	<a href="http://club.jd.com/allconsultations/1093258-8-1.html">8</a>
	<span>...</span>
	<a href="http://club.jd.com/allconsultations/1093258-117-1.html">117</a>
	<a class="next" href="http://club.jd.com/allconsultations/1093258-7-1.html">下一页<b></b></a> -->
</div>

<form id="form1">
<div class="Review_Form">
	<h5>发表咨询：</h5>
	<div class="Re_Explain">声明：您可在购买前对产品包装、颜色、运输、库存等方面进行咨询，我们有专人进行回复！因厂家随时会更改一些产品的包装、颜色、产地等参数，所以该回复仅在当时对提问者有效，其他网友仅供参考！咨询回复的工作时间为：周一至周五，9:00至18:00，请耐心等待工作人员回复。</div>
	<ul>
		<li id="pointType">
			<span style="display: inline;">咨询类型：</span>
			<input type="radio" value="2" name="pointType" checked="checked">
			商品咨询  
			<!-- <input type="radio" value="3" name="pointType">
			库存及配送  
			<input type="radio" value="4" name="pointType">
			支付问题  
			<input type="radio" value="5" name="pointType">
			发票及保修  
			<input type="radio" value="6" name="pointType">
			促销及赠品
			<label class="error" style="display:none;" for="pointType">  请选择咨询类型</label> -->
		</li>
		<li id="tipAnswer" style="display: none;">
			<p id="answer2" style="display: none;">
				<strong>京东承诺</strong>：商品均为原装正品行货，自带机打发票，严格执行国家三包政策，享受全国联保服务。<br>
				<strong>功能咨询</strong>：咨询商品功能建议您拨打各品牌的官方客服电话，以便获得更准确的信息。 <br> 
			</p>
			<p id="answer3" style="display: none;">
				<strong>发货时间</strong>：现货：下单后一日内即可发货；在途：一般1-2天发货； 预订：一般1-6天可发货；无货：已售完，相应物流中心覆盖地区内的用户不能购买<br>
				<strong>运&nbsp;&nbsp;&nbsp;&nbsp;费</strong>：如需查看快递运输收费标准及<strong style="color:red;font-weight:normal">免运费规则</strong>，<a class="link_1" href="http://www.jd.com/help/kdexpress.aspx" target="blank">请点此查看</a><br>
				<strong>货到付款</strong>：如需查看开通货到付款地区及运费，<a class="link_1" href="http://www.jd.com/help/cod.aspx" target="blank">请点此查看</a><br>
				<strong>上门自提</strong>：上门自提不收取运费，如需查看全部自提点位置、地图、注意事项，<a class="link_1" href="http://www.jd.com/help/ziti.aspx" target="blank">请点此查看</a><br>
				<strong>物流中心</strong>：京东商城拥有北京、上海、广州三个物流中心，各物流中心覆盖不同的城市，<a class="link_1" href="http://www.jd.com/help/kdexpress.aspx" target="blank">请点此查看</a><br>
			</p>
			<p id="answer4" style="display: none;">
				<strong>限&nbsp;&nbsp;&nbsp;&nbsp;额</strong>：如需查看各银行在线支付限额，<a class="link_1" href="http://www.jd.com/help/onlinepay.aspx" target="blank">请点此查看</a><br>
				<strong>大额支付</strong>：快钱支付中的招行、工行、建行、农行、广发支持大额支付，最高单笔一次支付10000元<br>
				<strong>分期付款</strong>：单个商品价格在500元以上，可使用<strong style="color:red;font-weight:normal">中国银行</strong>、<strong style="color:red;font-weight:normal">招商银行</strong>发行的信用卡申请分期付款，<a class="link_1" href="http://www.jd.com/help/dividedpay.aspx" target="blank">查看</a><br>
				<strong>货到付款</strong>：如需查看开通货到付款地区及运费，<a class="link_1" href="http://www.jd.com/help/cod.aspx" target="blank">请点此查看</a><br>
			</p>
			<p id="answer5" style="display: none;">
				<strong>京东承诺</strong>：商品均为原装正品行货，自带机打发票，严格执行国家三包政策，享受全国联保服务。<br>
				<strong>发票类型</strong>：京东商城所售商品均自带机打发票，在提供相关企业资料证明后，可申请开取增值税发票。<a class="link_1" href="http://www.jd.com/help/invoice.aspx" target="_blank">查看</a><br>
				<strong>退 换 货</strong>：京东商城为您提供完善的退换货服务，<a class="link_1" href="http://www.jd.com/help/return_policy.aspx" target="_blank">请点此查看</a><br>
			</p>
		</li>
		<li>
			<span>咨询内容：</span>
			<textarea id="consultationContent" name="consultationContent" class="area1" style="font-size: 14px;"></textarea>
		</li>
		<li id="column_refer_result" style="display:none;">
			<div class="column_refer_result">	
			</div>
		</li>
		<li class="buttons">
			<a href="javascript:;" id="getRefer">
				<img id="saveConsultation" src="${path}/resources/images/goods/button_08.jpg">
			</a>
			
			<span id="realConsultation" style="display:none">
			    <strong class="text1">没有想要的答案？继续提交咨询</strong>
			    <a href="#none">
				    <img id="submitConsultation" src="http://club.jd.com/Static/img/submit_1.gif">
			    </a>
			</span>
		</li>
	</ul>
</div>
</form>
 
		</div>
  
</div>
</body>
</html>