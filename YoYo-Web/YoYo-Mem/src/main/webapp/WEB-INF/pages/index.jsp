<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOYO汽车商城</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/index.css">
</head>
<body>
<!--content-->
<div class="con">
    <div class="con_in clearfix">
        <div class="con_left fl">
        	<div class="con_left_t">
                <!--侧边栏-->
            	<div class="sidebar fl">
                	<ul>
                    	<li class="li01">
                        	<a class="li_a" href="javascript:;">
                                <span></span>
                                <div class="sidebar_li_div">
                                    <h3>新车优惠</h3>
                                    <i>优惠信息快、准、狠</i>
                                </div>
                            </a>
                            <div class="sid_newcar sid_con">
                            	<ul>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li01.png" width="56" height="56"></div>
                                            <p>大众</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li02.png" width="56" height="56"></div>
                                            <p>现代</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li03.png" width="56" height="56"></div>
                                            <p>福特</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li04.png" width="56" height="56"></div>
                                            <p>起亚</p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li05.png" width="56" height="56"></div>
                                            <p>别克</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li06.png" width="56" height="56"></div>
                                            <p>长城</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li07.png" width="56" height="56"></div>
                                            <p>奥迪</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li08.png" width="56" height="56"></div>
                                            <p>长安</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li09.png" width="56" height="56"></div>
                                            <p>比亚迪</p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/li10.png" width="56" height="56"></div>
                                            <p>雪佛兰</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    	<li class="li02">
                        	<a class="li_a" href="javascript:;">
                                <span></span>
                                <div class="sidebar_li_div">
                                    <h3>保养服务</h3>
                                    <i>大保养</i>
                                    <i>小保养</i>
                                </div>
                            </a>
                            <div class="sid_con sid_fw">
                            	<ul>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/by_01.png" width="92" height="65"></div>
                                            <p>看看我需要做什么项目</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/by_02.png" width="92" height="65"></div>
                                            <p>小保养(机油+机滤)</p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/by_03.png" width="92" height="65"></div>
                                            <p>大保养(机油+3滤)</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    	<li class="li03">
                        	<a class="li_a" href="javascript:;">
                                <span></span>
                                <div class="sidebar_li_div">
                                    <h3>汽车美容</h3>
                                    <i>内饰洗护</i>
                                    <i>车身打蜡</i>
                                 </div>
                            </a>
                            <div class="sid_con sid_mr">
                            	<ul>
                                	<li>
                                        <a class="clearfix" href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/mr01.png" width="65" height="65"></div>
                                            <p>小保养</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/mr02.png" width="65" height="65"></div>
                                            <p>打蜡</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/mr03.png" width="65" height="65"></div>
                                            <p>空调清洗</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/mr04.png" width="65" height="65"></div>
                                            <p>消毒去味</p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/mr05.png" width="65" height="65"></div>
                                            <p>车身镀膜</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/mr06.png" width="65" height="65"></div>
                                            <p>钣金油漆</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/mr07.png" width="65" height="65"></div>
                                            <p>内饰洗护</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div><img src="${path}/resources/images/index/mr08.png" width="65" height="65"></div>
                                            <p>划痕修复</p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    	<li class="li04">
                        	<a class="li_a" href="javascript:;">
                                <span></span>
                                <div class="sidebar_li_div">
                                    <h3>更换与清洁</h3>
                                    <i>发动机外部清洗</i>
                                </div>
                            </a>
                            <div class="sid_con sid_qx">
                            	<ul>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    	<li class="li05">
                        	<a class="li_a" href="javascript:;">
                                <span></span>
                                <div class="sidebar_li_div">
                                    <h3>钣金油漆</h3>
                                    <i>快速补漆</i>
                                </div>
                            </a>
                            <div class="sid_con sid_yq">
                            	<ul>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li>
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                	<li class="bor0">
                                        <a href="javascript:;">
                                        	<div></div>
                                            <p></p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    	<li class="last li06">
                        	<a class="li_a" href="javascript:;">
                                <span></span>
                                <div class="sidebar_li_div">
                                    <h3>快速发布需求</h3>
                                    <i class="red">第一时间发布需求</i>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
                <!--侧边栏 end-->
                
               <!--banner-->
              <div class="banner fr">
                	<div class="banner_t">
                    	<ul>
                        	<li class="current"><img src="${path}/resources/images/index/banner1.jpg" alt=""></li>
                            <li><img src="${path}/resources/images/index/banner2.jpg" alt=""></li>
                            <li><img src="${path}/resources/images/index/banner1.jpg" alt=""></li>
                            <li><img src="${path}/resources/images/index/banner2.jpg" alt=""></li>
                        </ul>
                    	<ol>
                            <li class="current"></li>
                            <li></li>
                            <li></li>
                            <li></li>
                      </ol>
                </div>
                	<div class="banner_b">
                    	<a class="left_btn" href="javascript:;"><img src="${path}/resources/images/index/left_btn.png" width="10" height="19"></a>
                        <ul class="clearfix">
                        	<li>
                                <a href="javascript:;">
                                	<div class="sib fl">
                                    	<h5>喷油嘴清洗服务</h5>
                                        <p>优惠价：￥128</p>
                                    </div>
                                    <span class="sib_span fr"></span>
                                </a>
                            </li>
                        	<li>
                                <a href="javascript:;">
                                	<div class="sib fl">
                                    	<h5>喷油嘴清洗服务</h5>
                                        <p>优惠价：￥128</p>
                                    </div>
                                    <span class="sib_span fr"></span>
                                </a>
                            </li>
                        	<li>
                                <a href="javascript:;">
                                	<div class="sib fl">
                                    	<h5>喷油嘴清洗服务</h5>
                                        <p>优惠价：￥128</p>
                                    </div>
                                    <span class="sib_span fr"></span>
                                </a>
                            </li>
                        	<li>
                                <a href="javascript:;">
                                	<div class="sib fl">
                                    	<h5>喷油嘴清洗服务</h5>
                                        <p>优惠价：￥128</p>
                                    </div>
                                    <span class="sib_span fr"></span>
                                </a>
                            </li>
                        </ul>
                    	<a class="right_btn" href="javascript:;"><img src="${path}/resources/images/index/right_btn.png" width="10" height="19"></a>
                    </div>
              </div>
               <!--banner end-->
              
            </div>
            <div class="con_left_b">
            	<div class="con_addl fl"></div>
                <div class="con_addr fr">
                	<ul class="clearfix">
                    	<li>
                            <a href="javascript:;">大保养</a>
                            <a href="javascript:;">zxx</a>
                            <a href="javascript:;">gdesg</a>
                            <a href="javascript:;">khjf</a>
                        </li>
                    	<li><a href="javascript:;">小保养</a></li>
                    	<li><a href="javascript:;">更换空调清滤器</a></li>
                    	<li><a href="javascript:;">更换空调制冷剂</a></li>
                    	<li><a href="javascript:;">更换雨刮片</a></li>
                    	<li><a href="javascript:;">更换雨花塞</a></li>
                    	<li><a href="javascript:;">更换刹车油</a></li>
                    	<li><a href="javascript:;">更换刹车片</a></li>
                    	<li><a href="javascript:;">更换刹车盘</a></li>
                    	<li><a href="javascript:;">更换手动变速油箱</a></li>
                    	<li><a href="javascript:;">更换自动变速油箱</a></li>
                    	<li><a href="javascript:;">更换防冷冻却液</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="con_right fr">
        	<div class="con_right_t">
            	<div class="yoyo">
                	<h3 class="fl">YOYO快讯</h3>
                    <a class="fr">更多>></a>
                </div>
                <div class="yoyo_con">
                	<ul>
                    	<li>
                            <a href="javascript:;">
                            	<h5 class="fl">[特惠]</h5>
                                <span class="fr">闺蜜节大牌1折起 PO照赢大</span>
                            </a>
                        </li>
                    	<li>
                            <a href="javascript:;">
                            	<h5 class="fl">[特惠]</h5>
                                <span class="fr">闺蜜节大牌1折起 PO照赢大</span>
                            </a>
                        </li>
                    	<li>
                            <a href="javascript:;">
                            	<h5 class="fl">[特惠]</h5>
                                <span class="fr">闺蜜节大牌1折起 PO照赢大</span>
                            </a>
                        </li>
                    	<li>
                            <a href="javascript:;">
                            	<h5 class="fl">[特惠]</h5>
                                <span class="fr">闺蜜节大牌1折起 PO照赢大</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="con_right_b">
            	<div class="yoyo">
                	<h3 class="fl">自助保养服务</h3>
                </div>
                <div class="serve">
                	<div class="serve_t clearfix">
                    	<p>当前行驶里程</p>
                        <div class="serve_ipt">
                            <input class="fl" type="text" placeholder="" autocomplete="off" />
                            <span class="fr">公里</span>
                        </div>
                    </div>
                    <div class="serve_m clearfix">
                    	<p>新车上路时间</p>
                        <select class="fl" id="year">
                            <option>年份</option>
                        </select>
                        <select class="fr" id="month">
                            <option>月份</option>
                        </select>
                    </div>
                    <div class="serve_b">
                    	<button type="submit">查看我需要做哪些保养项目</button>
                    </div>
                    <div class="serve_f">
                    	<ul>
                        	<li>
                            	<span class="fl"></span>
                                <p class="fr">100%<br />正 品</p>
                            </li>
                        	<li>
                            	<span class="fl"></span>
                                <p class="fr">先 行<br />赔 付</p>
                            </li>
                        	<li>
                            	<span class="fl"></span>
                                <p class="fr">技 术<br />保 障</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        

        <!--右侧便捷功能-->
        <div class="mui">
            <ul>
                <li>
                    <a href="javascript:;">
                        <span></span>
                        <p>在线咨询</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="s02"></span>
                        <p>在线咨询</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <span class="s03"></span>
                        <p>在线咨询</p>
                    </a>
                </li>
                <li class="last">
                    <a class="clearfix" href="javascript:;">
                        <span class="last fl"></span>
                        <p class="fr">返回<br />顶部</p>
                    </a>
                </li>
            </ul>
        </div>
        <!--右侧便捷功能end-->
        
    </div>
</div>
<!--content end-->


<!--main-->
<div class="main">

    <!--1F-->
  <div class="f_1">
    	<div class="stor">
        	<h2 class="fl">新车优惠</h2>
            <a class="fr" href="javascript:;">更多优惠>></a>
        </div>
        <div class="car_show">
        	<ul class="clearfix">
            	<li>
                    <a class="clearfix" href="${path}/productDetail">
                        <img src="${path}/resources/demo/500300+300.jpg" width="96" height="100"> 
                    	<h4>长安 PSA DS 5</h4>
                        <h3 class="red">148.00元</h3>
                        <h5>2800最高回馈28000</h5>
                    </a>
                </li>
            	<li>
                    <a class="clearfix" href="javascript:;">
                        <img src="${path}/resources/images/index/car.jpg" width="196" height="100"> 
                    	<h4>长安 PSA DS 5</h4>
                        <h3 class="red">21.99<span>万元起</span></h3>
                        <h5>2800最高回馈28000</h5>
                    </a>
                </li>
            	<li>
                    <a class="clearfix" href="javascript:;">
                        <img src="${path}/resources/images/index/car.jpg" width="196" height="100"> 
                    	<h4>长安 PSA DS 5</h4>
                        <h3 class="red">21.99<span>万元起</span></h3>
                        <h5>2800最高回馈28000</h5>
                    </a>
                </li>
            	<li>
                    <a class="clearfix" href="javascript:;">
                        <img src="${path}/resources/images/index/car.jpg" width="196" height="100"> 
                    	<h4>长安 PSA DS 5</h4>
                        <h3 class="red">21.99<span>万元起</span></h3>
                        <h5>2800最高回馈28000</h5>
                    </a>
                </li>
            </ul>
      </div>
    </div>
    
    <!--2F-->
  <div class="f_2">
    	<div class="stor">
        	<h2 class="fl">保养服务</h2>
            <a class="fr" href="javascript:;">更多优惠>></a>
        </div>
        <div class="f_2_in">
       	  <div class="f_2_l fl">
                <ul class="f_2_ult clearfix">
                    <li>
                        <a class="clearfix" href="javascript:;">
                            <h4>夏日保养</h4>
                            <h3>5S级保养体验<br /><span>4折</span>套餐价格</h3>
                            <h5>Summer Maintenance</h5>
                            <img src="${path}/resources/images/index/yo_03.jpg" width="130" height="174"> 
                        </a>
                    </li>
             	</ul>
                <ul class="f_2_ulb clearfix">
                    <li>
                        <a class="clearfix" href="javascript:;">
                            <h4>夏日保养</h4>
                            <h3>YOYO<span>全方位</span>照顾</h3>
                            <h5>Summer Maintenance</h5>
                            <img src="${path}/resources/images/index/yo_05.jpg" alt=""> 
                        </a>
                    </li>
                 </ul>
            </div>
          <div class="f_2_m fl">
            	<ul>
                    <li>
                        <a class="clearfix" href="javascript:;">
                            <img src="${path}/resources/images/index/yo_02.jpg" alt=""> 
                            <h4>汽车小保养顶级方案</h4>
                            <h5>4L金美孚1号+机滤+免工时费</h5>
                            <h3>￥428.00</h3> <s>￥750.00</s>
                            <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                        </a>
                    </li>
                    <li>
                        <a class="clearfix" href="javascript:;">
                            <img src="${path}/resources/images/index/yo_02.jpg" alt=""> 
                            <h4>汽车小保养顶级方案</h4>
                            <h5>4L金美孚1号+机滤+免工时费</h5>
                            <h3>￥428.00</h3> <s>￥750.00</s>
                            <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="f_2_r fr">
            	<ul class="clearfix">
                    <li>
                        <a class="clearfix" href="javascript:;">
                            <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                            <div class="fr">
                                <h4>汽车小保养顶级方案</h4>
                                <h5>4L金美孚1号+机滤+免工时费</h5>
                                <h3>￥428.00</h3> <s>￥750.00</s>
                                <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                            </div>
                        </a>
                  </li>
                    <li>
                        <a class="clearfix" href="javascript:;">
                            <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                            <div class="fr">
                                <h4>汽车小保养顶级方案</h4>
                                <h5>4L金美孚1号+机滤+免工时费</h5>
                                <h3>￥428.00</h3> <s>￥750.00</s>
                                <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                            </div>
                        </a>
                  </li>
                    <li>
                        <a class="clearfix" href="javascript:;">
                            <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                            <div class="fr">
                                <h4>汽车小保养顶级方案</h4>
                                <h5>4L金美孚1号+机滤+免工时费</h5>
                                <h3>￥428.00</h3> <s>￥750.00</s>
                                <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                            </div>
                        </a>
                  </li>
              </ul>
          </div>
	</div>
    </div>
    
    <!--3F-->
  <div class="f_3">
    	<div class="stor">
        	<h2 class="fl">配件更换</h2>
            <a class="fr" href="javascript:;">更多优惠>></a>
        </div>
        <div class="f_3_in">
        	<div class="fl"><img src="${path}/resources/images/index/yo_06.jpg" width="180" height="380"></div>
            <div class="fr" >
            	<ul class="clearfix">
                  <li>
                    <a class="clearfix" href="javascript:;">
                        <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                        <div class="fr">
                            <h4>汽车小保养顶级方案</h4>
                            <h5>4L金美孚1号+机滤+免工时费</h5>
                            <h3>￥428.00</h3> <s>￥750.00</s>
                            <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                        </div>
                    </a>
                  </li>
                  <li>
                    <a class="clearfix" href="javascript:;">
                        <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                        <div class="fr">
                            <h4>汽车小保养顶级方案</h4>
                            <h5>4L金美孚1号+机滤+免工时费</h5>
                            <h3>￥428.00</h3> <s>￥750.00</s>
                            <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                        </div>
                    </a>
                  </li>
                  <li>
                    <a class="clearfix" href="javascript:;">
                        <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                        <div class="fr">
                            <h4>汽车小保养顶级方案</h4>
                            <h5>4L金美孚1号+机滤+免工时费</h5>
                            <h3>￥428.00</h3> <s>￥750.00</s>
                            <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                        </div>
                    </a>
                  </li>
              </ul>
            	<ul class="clearfix">
                  <li>
                    <a class="clearfix" href="javascript:;">
                        <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                        <div class="fr">
                            <h4>汽车小保养顶级方案</h4>
                            <h5>4L金美孚1号+机滤+免工时费</h5>
                            <h3>￥428.00</h3> <s>￥750.00</s>
                            <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                        </div>
                    </a>
                  </li>
                  <li>
                    <a class="clearfix" href="javascript:;">
                        <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                        <div class="fr">
                            <h4>汽车小保养顶级方案</h4>
                            <h5>4L金美孚1号+机滤+免工时费</h5>
                            <h3>￥428.00</h3> <s>￥750.00</s>
                            <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                        </div>
                    </a>
                  </li>
                  <li>
                    <a class="clearfix" href="javascript:;">
                        <span class="fl"><img src="${path}/resources/images/index/yo_04.jpg" width="110" height="148"></span>
                        <div class="fr">
                            <h4>汽车小保养顶级方案</h4>
                            <h5>4L金美孚1号+机滤+免工时费</h5>
                            <h3>￥428.00</h3> <s>￥750.00</s>
                            <p class="shop"><span>预定</span>  已有<i>1200</i>人参加</p>
                        </div>
                    </a>
                  </li>
              </ul>
          </div>
		</div>
   	</div>  
    <!--4楼-->
    <div class="f_4">
        <div class="stor">
            <h2 class="fl">优惠券</h2>
            <a class="fr" href="javascript:;">更多优惠>></a>
        </div>
        <div class="f_4_in">
            <ul class=" clearfix">
                <li><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li style=" margin-right: 0;"><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li style=" margin-right: 0;"><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
            </ul>
        </div>
    </div>
</div>
<!--main end-->

</body>
</html>