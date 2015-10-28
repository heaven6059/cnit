<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOYO汽车商城-店铺管理</title>
<link type="text/css" href="${path}/resources/styles/shopMng/shopManager.css?r=${versionVal}" rel="stylesheet" />
</head>
<body>
	<div class="shop_manager_right">
		<div class="title">
			<span style="float:left">我的店铺<span class="disc"> 
		</div>
	</div>
	<div class="sell_ri fr">
    <div class="Plate">
       <h4>
        <a class="current" href="#">我的专营店</a>
        
        </h4>
    </div>
    <div class="sell_ri_top">
	    <div class="sell_ri_top_le fl">
			<a href="#"><img alt="" src="http://test.v3.bizsov.com/public/images/public/32/87/1c/959ced3d8b5767b02c2490080257ec79.jpg?1433408349#h"></a>
	    	<p>婧婧之家</p>
		</div>
	    <div class="sell_ri_top_ri fr">
           <div class="order_info">
		        <div class="order_info_le fl">
			        <ul>
				      	<li>
				      		<strong><b class="icon01"></b>订单</strong>
				      						      		<span class="color_1"><a href="/bbc3/business-seller_order-ship- - - -all.html">待发货订单<font>（40）</font></a></span>
				      						      		<span class="color_1"><a href="/bbc3/business-seller_order-refunded- - - -all.html">退款中的订单<font>（8）</font></a></span>
				      		
				      	</li>

				      	<li>
				      		<strong><b class="icon02"></b>提醒</strong>

				      						      		<span class="color_1"><a href="/bbc3/business-goods_instock.html">待上架的宝贝<font>（4）</font></a></span>
				      						      		<span class="color_1"><a href="/bbc3/business-goods_alert.html">需优化的宝贝<font>（3）</font></a></span>
                  						</li>

						<li>
							<strong><b class="icon03"></b>违规</strong>
					      	<span class="color_1">一般违规累计<font>（0）</font></span>
					      	<span class="color_1">严重违规累计<font>（0）</font></span>
						</li>

						<li class="none">
							<strong><b class="icon04"></b>宝贝</strong>

				      						      		<span class="color_1"><a href="/bbc3/business-goods_onsell.html">出售中的宝贝<font>（56）</font></a></span>
				      		
				      		<span class="color_1">待评论宝贝<font>（140）</font></span>
				    	</li>
			        </ul>
			  
			    </div>
		        <table cellspacing="0" cellpadding="0" class="order_info_ri fr">
					<tbody><tr>
						<th>店铺动态评分</th>
						<th>与同行业相比</th>
					</tr>
	                					<tr>
						<td class="color_1">描述相符：5.00</td>
						<td class="red">
	                    高于101.8%
	                    </td>
					</tr>
	                					<tr>
						<td class="color_1">服务态度：5.00</td>
						<td class="red">
	                    高于104.4%
	                    </td>
					</tr>
	                					<tr>
						<td class="color_1">发货速度：5.00</td>
						<td class="red">
	                    高于101.8%
	                    </td>
					</tr>
	                			    </tbody></table>
		    </div>
		</div>
		
	    <div class="cl_zhi"></div>
	</div>
	<div class="Plate width_472 fl">
		<h4><a class="current" href="#">营销推广活动</a></h4>
		<div class="Plate_info">
			<div class="Plate_info_table_div">
				<ul class="Plate_info_table">
										<li><span>[2014-03-11]</span><a href="/bbc3/promotions-attend.html">限时活动001</a></li>
										<li><span>[2013-11-05]</span><a href="/bbc3/promotions-attend.html">团购了了了了</a></li>
					
				</ul>
		  </div><!--
            <div style='float:right;'><a href="http://demo.bizsov.com/bbc3/article-promotions-1-12.html">更多活动》</a></div>-->
			<div class="cl_zhi"></div>
		</div>

	</div>
	<div class="Plate width_472 fr">
		<h4><a class="current" href="#">官方活动信息</a></h4>
		<div class="Plate_info">
			<ul class="Plate_info_ul">
								<li><a href="http://demo.bizsov.com/bbc3/article-news-65.html">
				<strong>▪ 8月26日平台大促</strong><span class="fr">[2013-07-02]</span>
				<span class="cl_zhi"></span>
				</a></li>
								<li><a href="http://demo.bizsov.com/bbc3/article-news-64.html">
				<strong>▪ 7月1日开放外部公测</strong><span class="fr">[2013-06-26]</span>
				<span class="cl_zhi"></span>
				</a></li>
								<li><a href="http://demo.bizsov.com/bbc3/article-news-55.html">
				<strong>▪ 系统升级通知（周二）！</strong><span class="fr">[2010-10-01]</span>
				<span class="cl_zhi"></span>
				</a></li>
								<li><a href="http://demo.bizsov.com/bbc3/article-news-54.html">
				<strong>▪ 8月8日暂停货品出库</strong><span class="fr">[2010-10-01]</span>
				<span class="cl_zhi"></span>
				</a></li>
								<li><a href="http://demo.bizsov.com/bbc3/article-news-53.html">
				<strong>▪ 商品评论改版升级!</strong><span class="fr">[2010-10-01]</span>
				<span class="cl_zhi"></span>
				</a></li>
								<li><a href="http://demo.bizsov.com/bbc3/article-news-52.html">
				<strong>▪ 银行系统升级通告！</strong><span class="fr">[2010-10-01]</span>
				<span class="cl_zhi"></span>
				</a></li>
				
			</ul>
		</div>

	</div>
	<div class="cl_zhi"></div>
	<div class="Plate width_960">
		<h4><a class="current" href="#">近期服务情况</a></h4>
		<div style="position:relative;" class="Plate_info">
        <div class="Yellow_bg">服务月</div>
        <div class="Plate_info_table_2">
			<table width="938" cellspacing="0" cellpadding="0" border="0">
				<tbody><tr>
					<th class="width_95">服务月</th>
					<th>退款</th> 
					<th>退款率</th>
					<th>纠纷退款</th>
					<th>纠纷退款率</th>
					<th colspan="2">平均退款速度</th>
					<th>投诉</th>
				</tr>
				<tr>
					<td>最近30天</td>
					<td>1&nbsp;笔</td>
					<td>0%</td>
					<td>1&nbsp;笔</td>
					<td>100% </td>
					<td style="text-align:right;"> 0&nbsp;天 </td>
                   
                                           <td style="text-align:left;width:200px;" class="td_color_2">
                       &nbsp;&nbsp;&nbsp;-                    </td>
					<td>0&nbsp;笔</td>
				</tr>
				<tr>
					<td>2015年06月 </td>
					<td>2&nbsp;笔</td>
					<td>0%</td>
					<td>0&nbsp;笔</td>
					<td>0%  </td>
					<td style="text-align:right;">0&nbsp;天 </td>
                   
                                           <td style="text-align:left;width:200px;" class="td_color_2">
                       &nbsp;&nbsp;&nbsp;-                    </td>
					<td>1&nbsp;笔</td>
				</tr>
			</tbody></table>
            </div>
		</div>

	</div>
	 
	 
	<div class="Plate width_472 f14 fl">
		<h4><a class="current" href="#">平台服务专区</a></h4>
		<div class="Plate_info tac">
			<p style="margin:25px auto;" class="Plate_info_p"> 
				<strong>需缴纳保证金额：</strong><span class="font-red">￥1000.00</span>元
			</p>
			<p style="margin:25px auto;" class="Plate_info_p"> 
				<strong>已缴纳保证金额：</strong><span class="font-blue">￥10000.00</span>元
			</p>
			
			<div style="margin-left:140px;">
            <p style="float:left;margin-right:10px;" class="button_1"> <a href="http://demo.bizsov.com/bbc3/store-storeapplystep4-676-isedit.html">资质认证</a></p>
                        <p style="float:left;" class="button_2"> <a href="">开店续费</a></p>
							<span class="cl_zhi"></span>
			</div>
		</div>

	</div>
	<div class="Plate width_472 fr">
		<h4><a class="current" href="#">评价信息</a></h4>
		<div class="Plate_info">
			<div class="Plate_info_table_2_div">
            <div class="Plate_info_table_2">
				<table width="100%">
					<tbody><tr>
						<th class="width_95">服务月</th>
						<th class="width_70">评价总数</th> 
						<th class="width_110">原始中差评数</th>
						<th class="width_110">删除中差评数</th>
					</tr>
					<tr>
						<td>最近30天 </td>
						<td>0&nbsp;笔</td>
						<td>0&nbsp;笔</td>
						<td>0&nbsp;笔</td>

					</tr>
					<tr>
						<td> 2015年06月 </td>
						<td>0&nbsp;笔</td>
						<td>0&nbsp;笔</td>
						<td>0&nbsp;笔</td>
					</tr>
				</tbody></table>
                </div>
			</div>
		</div>
	</div>
</div>
</body>
</html>