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
<link type="text/css" href="${path}/resources/styles/base.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/reply.css" rel="stylesheet" />
<script src="${path}/resources/scripts/biz/goods/reply.js"></script>

<!--css文件-->
</head>
<body>
<div id="main_container" class="root61" style="width: 1200px !important; margin:auto; height: auto;display: table;margin-top:10px;">

<div class="w" style="width: 1200px;">
    <div class="right">
        <div id="comment-detail" class="m m1">
            <div class="mt">
                <h2 class="fl">评价详情</h2>
                
            </div>
            <div id="comments-list" class="mc">
                <div class="item">
                    <div class="user">
                        <div class="u-name"> 
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
                                <div class="replay-form" >
                                    <div class="arrow">
                                        <em>◆</em><span>◆</span>
                                    </div>
                                    <div class="reply-wrap">
                                        <p><em>回复</em> <span class="u-name"></span></p>
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
                        <div name="replyList" id="replyList">
                        </div>
                        <!-- replies end-->
                    </div>
                    <div class="corner tl"></div>
                </div>
                                
		<div class="clearfix m">
		<div class="pagin fr">
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
		</li>
		<li class="p-count">
			<div class="dt">评&nbsp;&nbsp;价&nbsp;&nbsp;数：</div>
		    <div id="p-num-comment" class="dd">0条</div>
		</li>
	</ul>
</div>

</div>


<script type="text/javascript">
$().ready(function (){
getProductPrice();
});
</script>
   </div>

    <div class="clr"></div>
</div>
  
</div>
</body>
</html>