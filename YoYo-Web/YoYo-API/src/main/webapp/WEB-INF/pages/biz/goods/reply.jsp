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
<title>回复</title>
<script type="text/javascript">var _path="${path}";</script>
<script src="${path}/resources/scripts/common/yoyo.js"></script>
<%-- <link type="text/css" href="${path}/resources/styles/goods/jd/base.css" rel="stylesheet" /> --%>
<link type="text/css" href="${path}/resources/styles/base.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/reply.css" rel="stylesheet" />
<%-- <link type="text/css" href="${path}/resources/styles/goods/jd/base.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/details.css" rel="stylesheet" /> --%>
<script src="${path}/resources/scripts/biz/goods/reply.js"></script>
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
<div id="main_container" class="root61" style="width: 1200px !important; margin:auto; height: auto;display: table;margin-top:10px;">

<div class="w" style="width: 1200px;">
    <div class="right">
        <div id="comment-detail" class="m m1">
            <div class="mt">
                <h2 class="fl">评价详情</h2>
                <!-- <div class="partake-new">
                    Baidu Button BEGIN
                            <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
                            <span style="padding-top:6px;color:#999999">分享到：</span>
                            <a class="bds_qzone" title="分享到QQ空间" href="#"></a>
                            <a class="bds_tsina" title="分享到新浪微博" href="#"></a>
                            <a class="bds_tqq" title="分享到腾讯微博" href="#"></a>
                            <a class="bds_renren" title="分享到人人网" href="#"></a>
                            <a class="bds_t163" title="分享到网易微博" href="#"></a>
                            <span class="bds_more">更多</span>
                            </div>
                            <script type="text/javascript" id="bdshare_js" data="type=tools&amp;mini=1&amp;uid=247057" src="http://bdimg.share.baidu.com/static/js/bds_s_v2.js?cdnversion=397788"></script>
                            
                            <script type="text/javascript">
                            var
                            bds_config={ "snsKey":{ 'tsina':'2445336821','tqq':'','t163':'','tsohu':''}}
                            document.getElementById("bdshell_js").src =
                            "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" +
                            Math.ceil(new Date()/3600000)
                            </script>
                            Baidu Button END                     
                </div> -->
            </div>
            <div id="comments-list" class="mc">
                <div class="item">
                    <div class="user">
                        <!-- <div class="u-icon">  
                        <a rel="nofollow" title="" href="" target="_blank">
							 <img height="50" width="50" upin="" src="http://storage.360buyimg.com/i.imageUpload/667465726566666563745f6d31343139393331313639383033_sma.jpg" onerror="this.src='http://misc.360buyimg.com/lib/img/u/b105.gif'">
						</a> 
                        </div> -->
                        <div class="u-name"> <!-- <a href="http://me.jd.com/126709730.html" rel="nofollow" target="_blank">
                          ftereffect
                        </a> -->
                        </div> <span class="u-level"><span style="color:#ff0000"> </span><span class="u-address">
                        	
                        </span></span>
                    </div>
                    <div class="i-item" data-nickname="ftereffect">
                        <div class="o-topic"> 
                            <strong class="topic">
                            	<a href="" target="_blank"></a>
                            </strong>
	                        <span class="star sa4"></span> 
	                        <span class="date-comment">2014-11-13 16:54</span>
                        </div>
                        <div class="comment-content">
                            <!-- <dl>
                            	                            	
                                <dt>标　　签：</dt>
                                <dd>
                                    	<span class="comm-tags" href="#none"><span>速度快</span></span> 
                                    	<span class="comm-tags" href="#none"><span>4K跑分高</span></span> 
                                    	<span class="comm-tags" href="#none"><span>极致速度</span></span> 
                                </dd>
                                                            </dl> -->
                            <dl>
                                <dt>心　　得：</dt>
                                <dd></dd>
                            </dl> 
                            <dl style="display:none;"> 
                                <dt>晒　　单：</dt>             
                                        <dd> 
                                        
                                    <div class="p-photos-viewer" style="display:none;">            
	                            		<div class="p-photos-wrap">                
	                            			<i></i>                
	                            			<img class="J-big-img" src="" alt="" style="transform: rotate(0deg); width: 500px; height: 370px;" width="500" height="370" onload="autosize(this,500,500)" data="0">                
	                            			<div class="cursor-left J-photo-prev"></div>                
	                            			<div class="cursor-small J-hide-big-show"></div>                
	                            			<div class="cursor-right J-photo-next"></div>            
	                            		</div>        
	                            	</div>
                                        
                                        
                                    <div class="comment-show-pic"> 
                                   	 	<span class="J-thumb-prev i-prev-btn i-prev-disable" style="float:left;position:relative;top:-30px;" onclick="prevCommentPic(this)"></span>
                                        <table cellspacing="10" style="margin-left: 0px;">
	                                        <tbody>
		                                        <tr>                                
			                                        <td>             
			                                            <a class="comment-show-pic-wrap" href="" target="_blank" clstag="shangpin|keycount|product|shaipic">                       
			                                                <img alt="" src="">   
			                                            </a> 
			                                        </td>                     
			                                        <td>             
			                                            <a class="comment-show-pic-wrap" href="" target="_blank" clstag="shangpin|keycount|product|shaipic">                       
			                                                <img alt="" src="">   
			                                            </a> 
			                                        </td>                     
			                                        <td>             
			                                            <a class="comment-show-pic-wrap" href="" target="_blank" clstag="shangpin|keycount|product|shaipic">                       
			                                                <img alt="" src="">   
			                                            </a> 
			                                        </td>                     
		                                        </tr>
	                                        </tbody>
                                        </table>   
                                        <span class="J-thumb-next i-next-btn i-next-disable" style="float:left;position:relative;top:-30px;" onclick="nextCommentPic(this)">  </span>          
                                        <span clstag="shangpin|keycount|product|shaitext" style="clear:both;margin-top:auto;">
	                                        <em class="fl" style="color:#9C9A9C;margin-right:5px;display:block;">共0张图片</em>
	                                        <a class="p-simsun" href="javascript:;" target="_blank" onclick="showHideBig(this)"> 查看晒单&gt;</a>
                                        </span>                
                                    </div>     
                                </dd>                                   
                            </dl>
                            <div class="dl-extra">
                                <dl>
                                    <dt>购买日期：</dt>
                                    <dd>
                                    	
                                    </dd>
                                </dl>
                            </div>                             
                        </div>
                        <div class="btns">
                            <a class="btn-reply" href="javascript:;"  onclick="toReply(this)">回复(<span></span>)</a>                                                                                                
                            <div class="useful" id="fa47c772-8602-421a-822f-d1524de86a9a">    
                            	<a name="agree" id="agree" class="btn-agree"  href="javascript:;" >有用()</a>
                            </div>
                            
                            </div>
                                                <div class="item-reply reply-lz" style="display:none;">
                            <div class="reply-list">
                                <!-- 弹出回复层 -->
                                <div class="replay-form" >
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">
                                          ftereffect
 ：
                                          </span></p>
                                        <div class="reply-input">
                                            <div class="fl" cid="" rid=""><input type="text"></div>
                                            <a href="javascript:;" class="reply-btn btn-gray" onclick="submitReply(this)">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div> 
                                <!-- 弹出回复层 -->
                            </div>
                        </div>
                                                <!-- replies begin-->
                        <div name="replyList">
                        <div class="item-reply none" style="display:none;">
                            <strong>
                            	8
                            </strong>
                            <div class="reply-list">
                                <div class="reply-con">
                                    <span class="u-name">
                                        <a rel="nofollow" href="javascript:;" >
                                        	飞翔955
										</a>
                                        <em>回复</em>
                                        <a rel="nofollow" href="javascript:;">
                                    	    Noodles___
										</a>：
                                    </span>
                                    <span class="u-con">我没刷 但是现在我格式化后 又查不出问题了 这样还能换吗</span>
                                </div>
                                <div class="reply-meta">
                                    <span class="reply-left fl">
                                    2014-12-02 15:00:34
                                    </span>
                                    <a class="p-bfc hl_blue" href="javascript:;" onclick="toReply(this)" style="visibility: visible;">回复</a><!--  style="visibility: hidden;" -->
                                </div>
                                <div class="replay-form" style="display: none;">
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">：</span></p>
                                        <div class="reply-input">                                            
                                            <div class="fl" uh="" ud="" un="" cid="" rid=""><input type="text"></div>
                                            <a href="javascript:;" class="reply-btn btn-gray" onclick="submitReply(this)">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- <div class="item-reply none">
                            <strong>
                            	7
                            </strong>
                            <div class="reply-list">
                                <div class="reply-con">
                                    <span class="u-name">
                                        <a rel="nofollow" href="http://me.jd.com/22887743.html" target="_blank">
                                        sunisme
</a>
                                        <em>回复</em>
                                        <a rel="nofollow" target="_blank" href="http://me.jd.com/85173732.html">
                                        Noodles___
</a>：
                                    </span>
                                    <span class="u-con">我也是这样诶。。是固件问题么
</span>
                                </div>
                                <div class="reply-meta">
                                    <span class="reply-left fl">
                                    2014-11-30 12:16:07
                                    </span>
                                    <a class="p-bfc hl_blue" href="#none" style="visibility: hidden;">回复</a>
                                </div>
                                <div class="replay-form" style="display: none;">
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">
                                        sunisme
：</span></p>
                                        <div class="reply-input">                                            
                                            <div class="fl" uh="4" ud="22887743" un="
                                            sunisme
" cid="fa47c772-8602-421a-822f-d1524de86a9a" rid="35056984"><input type="text"></div>
                                            <a href="#none" class="reply-btn btn-gray">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="item-reply none">
                            <strong>
                            	6
                            </strong>
                            <div class="reply-list">
                                <div class="reply-con">
                                    <span class="u-name">
                                        <a rel="nofollow" href="http://me.jd.com/85173732.html" target="_blank">
                                        Noodles___
</a>
                                        <em>回复</em>
                                        <a rel="nofollow" target="_blank" href="http://me.jd.com/122217051.html">
                                        飞翔955
</a>：
                                    </span>
                                    <span class="u-con">我也是.已经让京东更换了.别返厂,直接找京东.返厂来回太久.京东直接换.ps:你是不是刷新固件1.05了?</span>
                                </div>
                                <div class="reply-meta">
                                    <span class="reply-left fl">
                                    2014-11-29 14:21:59
                                    </span>
                                    <a class="p-bfc hl_blue" href="#none" style="visibility: hidden;">回复</a>
                                </div>
                                <div class="replay-form" style="display: none;">
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">
                                        Noodles___
：</span></p>
                                        <div class="reply-input">                                            
                                            <div class="fl" uh="0" ud="85173732" un="
                                            Noodles___
" cid="fa47c772-8602-421a-822f-d1524de86a9a" rid="35042800"><input type="text"></div>
                                            <a href="#none" class="reply-btn btn-gray">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="item-reply none">
                            <strong>
                            	5
                            </strong>
                            <div class="reply-list">
                                <div class="reply-con">
                                    <span class="u-name">
                                        <a rel="nofollow" href="http://me.jd.com/41294201.html" target="_blank">
                                        伟之帅星欣彩
</a>
                                        <em>回复</em>
                                        <a rel="nofollow" target="_blank" href="http://me.jd.com/122217051.html">
                                        飞翔955
</a>：
                                    </span>
                                    <span class="u-con">真的有这么回事？</span>
                                </div>
                                <div class="reply-meta">
                                    <span class="reply-left fl">
                                    2014-11-22 14:30:35
                                    </span>
                                    <a class="p-bfc hl_blue" href="#none" style="visibility: hidden;">回复</a>
                                </div>
                                <div class="replay-form" style="display: none;">
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">
                                        伟之帅星欣彩
：</span></p>
                                        <div class="reply-input">                                            
                                            <div class="fl" uh="0" ud="41294201" un="
                                            伟之帅星欣彩
" cid="fa47c772-8602-421a-822f-d1524de86a9a" rid="34688704"><input type="text"></div>
                                            <a href="#none" class="reply-btn btn-gray">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="item-reply none">
                            <strong>
                            	4
                            </strong>
                            <div class="reply-list">
                                <div class="reply-con">
                                    <span class="u-name">
                                        <a rel="nofollow" href="http://me.jd.com/70350796.html" target="_blank">
                                        jd_西風肥
</a>
：
                                    </span>
                                    <span class="u-con">价格涨这快干吗</span>
                                </div>
                                <div class="reply-meta">
                                    <span class="reply-left fl">
                                    2014-11-21 11:10:02
                                    </span>
                                    <a class="p-bfc hl_blue" href="#none" style="visibility: hidden;">回复</a>
                                </div>
                                <div class="replay-form" style="display: none;">
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">
                                        jd_西風肥
：</span></p>
                                        <div class="reply-input">                                            
                                            <div class="fl" uh="0" ud="70350796" un="
                                            jd_西風肥
" cid="fa47c772-8602-421a-822f-d1524de86a9a" rid="34634931"><input type="text"></div>
                                            <a href="#none" class="reply-btn btn-gray">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="item-reply none">
                            <strong>
                            	3
                            </strong>
                            <div class="reply-list">
                                <div class="reply-con">
                                    <span class="u-name">
                                        <a rel="nofollow" href="http://me.jd.com/122217051.html" target="_blank">
                                        飞翔955
</a>
：
                                    </span>
                                    <span class="u-con">我靠 今天突然不能识别 数据全部丢失 辛苦几天的工程全部泡汤 要求赔偿！！！！！！！！！！！</span>
                                </div>
                                <div class="reply-meta">
                                    <span class="reply-left fl">
                                    2014-11-21 00:23:06
                                    </span>
                                    <a class="p-bfc hl_blue" href="#none" style="visibility: hidden;">回复</a>
                                </div>
                                <div class="replay-form" style="display: none;">
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">
                                        飞翔955
：</span></p>
                                        <div class="reply-input">                                            
                                            <div class="fl" uh="0" ud="122217051" un="
                                            飞翔955
" cid="fa47c772-8602-421a-822f-d1524de86a9a" rid="34622343"><input type="text"></div>
                                            <a href="#none" class="reply-btn btn-gray">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="item-reply none">
                            <strong>
                            	2
                            </strong>
                            <div class="reply-list">
                                <div class="reply-con">
                                    <span class="u-name">
                                        <a rel="nofollow" href="http://me.jd.com/537146092.html" target="_blank">
                                        小黑系统nhy
</a>
：
                                    </span>
                                    <span class="u-con">价格小高</span>
                                </div>
                                <div class="reply-meta">
                                    <span class="reply-left fl">
                                    2014-11-20 10:38:45
                                    </span>
                                    <a class="p-bfc hl_blue" href="#none" style="visibility: hidden;">回复</a>
                                </div>
                                <div class="replay-form" style="display: none;">
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">
                                        小黑系统nhy
：</span></p>
                                        <div class="reply-input">                                            
                                            <div class="fl" uh="0" ud="537146092" un="
                                            小黑系统nhy
" cid="fa47c772-8602-421a-822f-d1524de86a9a" rid="34568521"><input type="text"></div>
                                            <a href="#none" class="reply-btn btn-gray">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="item-reply none">
                            <strong>
                            	1
                            </strong>
                            <div class="reply-list">
                                <div class="reply-con">
                                    <span class="u-name">
                                        <a rel="nofollow" href="http://me.jd.com/235714162.html" target="_blank">
                                        jd765897hbz
</a>
：
                                    </span>
                                    <span class="u-con">拍照技术不错呀</span>
                                </div>
                                <div class="reply-meta">
                                    <span class="reply-left fl">
                                    2014-11-17 18:06:26
                                    </span>
                                    <a class="p-bfc hl_blue" href="#none" style="visibility: hidden;">回复</a>
                                </div>
                                <div class="replay-form" style="display: none;">
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name">
                                        jd765897hbz
：</span></p>
                                        <div class="reply-input">                                            
                                            <div class="fl" uh="0" ud="235714162" un="
                                            jd765897hbz
" cid="fa47c772-8602-421a-822f-d1524de86a9a" rid="34426864"><input type="text"></div>
                                            <a href="#none" class="reply-btn btn-gray">回复</a>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> -->
                        </div>
                        <!-- replies end-->
                    </div>
                    <div class="corner tl"></div>
                </div>
                                
		<div class="clearfix m">
		<div class="pagin fr">
			<!-- <a class="prev" href="http://club.jd.com/repay/1093258_fa47c772-8602-421a-822f-d1524de86a9a_4.html">上一页<b></b></a>
			<a href="http://club.jd.com/repay/1093258_fa47c772-8602-421a-822f-d1524de86a9a_1.html">1</a>
			<span>...</span>
			<a href="http://club.jd.com/repay/1093258_fa47c772-8602-421a-822f-d1524de86a9a_3.html">3</a>
			<a href="http://club.jd.com/repay/1093258_fa47c772-8602-421a-822f-d1524de86a9a_4.html">4</a>
			<a class="current">5</a> -->
		</div>
		</div>
		            </div>
        </div>
        	<div id="relate-r"></div>
		<div class="clearfix m"></div>
    </div>
    <div class="left" style="width:200px;">
        <!--pinfo start-->
<div class="m m2" id="product-info">
<div class="mt">
<h2>商品信息</h2>
<%-- <input type="hidden" id="goods_id" value="${goodsId}" />
<input type="hidden" id="product_id" value="${productId}" /> --%>
<input type="hidden" id="product_id" value="${productId}" />
<input type="hidden" id="comment_id" value="${commentId}" />
</div>
<div class="mc">
<ul>
	<li class="p-img ac"><a href="" target="_blank"> <img alt="" src="" width="130px" height="130px"> </a></li>

<li class="p-name">商品名称：<a href="" target="_blank"></a></li>
<li class="p-price"><div class="dt">优&nbsp;&nbsp;优&nbsp;&nbsp;价：</div>
<strong class="">￥0.00</strong></li>
<li class="p-grade">
<div class="dt">商品评分：</div>
<div class="fl dd">
<span id="product_star" class="star sa5"></span>
(<span id="product_star_score">5</span>分)</div>
</li>
<li class="p-count">
<div class="dt">
                            评&nbsp;&nbsp;价&nbsp;&nbsp;数：
                        </div>
                        <div id="p-num-comment" class="dd">0条</div>
</li>

<li class="hide"><a href="" target="_blank">商品咨询</a></li>

<li class="p-btn"><a class="btn-append" href="javascript:;">添加到购物车</a>
</li>
	
</ul>
</div>

</div>

<div style="display:none;">
<ul>
        <li><a href="http://jae.jd.com">京东云擎</a> </li>    
        <li><a href="http://baitiao.jd.com">京东白条</a> </li>    
        <li><a href="http://item.jd.com/11417193.html">贝克汉姆</a> </li>    
        <li><a href="http://sale.jd.com/act/RT0hWJ6or5.html">京东蝴蝶节</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/45241.html">比特币是什么</a> </li>    
        <li><a href="http://re.jd.com/">京东热卖</a> </li>    
        <li><a href="http://club.jr.jd.com/licai/jingxuan">京东理财论坛</a> </li>    
        <li><a href="http://licai.bx.jd.com/">京东保险理财</a> </li>    
        <li><a href="http://loan.jd.com/home.html">京小贷</a> </li>    
        <li><a href="http://go.jd.com/activity/introduce">京东旅游白条</a> </li>    
        <li><a href="http://loan.jd.com/scf/">京保贝</a> </li>    
        <li><a href="http://jr.jd.com/stubaitiao/">京东校园白条</a> </li>    
        <li><a href="http://baitiao.jd.com/">京东白条</a> </li>    
        <li><a href="http://vip.jd.com">京东会员</a> </li>    
        <li><a href="http://diaoyan.jd.com/">京东调研</a> </li>    
        <li><a href="http://item.jd.com/1045274.html">LG G flex</a> </li>    
        <li><a href="http://www.wangyin.com">网银钱包</a> </li>    
        <li><a href="http://sale.jd.com/act/mQCgq28vjG.html">京东618</a> </li>    
        <li><a href="http://luopan.jd.com">京东数据罗盘</a> </li>    
        <li><a href="http://smarthome.jd.com/">京东智能云</a> </li>    
        <li><a href="http://item.jd.com/1013281.html">努比亚小牛2 Z5S mini</a> </li>    
        <li><a href="http://jahvery.jd.com/">JAHVERY</a> </li>    
        <li><a href="http://wiki.jd.com/">京东</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/1910.html">按摩器哪个品牌好</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/6359.html">小牛手机</a> </li>    
        <li><a href="http://red.jd.com/">闪购</a> </li>    
        <li><a href="http://xuan.jd.com/">京选</a> </li>    
        <li><a href="http://wiki.jd.com/knowledge/2453.html">机械键盘</a> </li>    
        <li><a href="http://www.jd.com/compare/931454-760765-760785-769688.html">移动电源哪个牌子好</a> </li>    
        <li><a href="http://club.jd.com/review/11198146-3-1.html">劳动合同法2013</a> </li>    
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
</script>        <!--pinfo end-->

        <!-- <div clstag="shangpin|keycount|product|recent" id="combination" class="m m2" style="display: none;">  
        </div> -->
    </div>

    <div class="clr"></div>
</div>
  
</div>
</body>
</html>