<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cnit.yoyo.util.Configuration" %> 
<%@page import="com.cnit.yoyo.util.CommonUtil" %>
<meta property="qc:admins" content="2422667512636143475463757" />
<%
	application.setAttribute("portalPath", Configuration.getInstance().getConfigValue("portal.url"));
	application.setAttribute("memPath", Configuration.getInstance().getConfigValue("mem.url"));
	application.setAttribute("shopPath", Configuration.getInstance().getConfigValue("shop.url"));
	request.getSession().setAttribute("accountType",CommonUtil.getSession(request).getAttribute("accountType"));
	session.setAttribute("imagePath", Configuration.getInstance().getConfigValue("images.url"));
%>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<div class="header_bg">
   <div class="header_in_t clearfix">
   	  <div class="header_in_t-l fl">
         <dl><dt><i></i></dt>
                	<dt>城市：</dt>
                    <dd><ul class="header_ul">
                            <li class="header_li">
                                <a  class="listA br0" href="javascript:;"><span id="selectedProvince">广东 </span><span class="drop"></span></a>

                                <div class="dorpdown_layer">
                                    <div class="dd_spacer"></div>
                                    <dl class="fore1 clearfix">
                                        <dd id="selectProvince">
                                            <div class="d_item">
                                                <a href="javascript:;">湖南</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;" class="selected">广东</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">北京</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">天津</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">上海</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">云南</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">四川</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">湖北</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">内蒙古</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">浙江</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">黑龙江</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">广西</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">山西</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">河南</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">辽宁</a>
                                            </div>
                                            <div class="d_item">
                                                <a href="javascript:;">陕西</a>
                                            </div>
                                        </dd>
                                    </dl>
                                </div>
                            </li>
                        </ul>
                    </dd>
                </dl>
   	  </div>
      <div class="header_in_t-r fr">
            	<ul class="header_ul">
            	<c:choose>
				<c:when test="${sessionScope.loginStatus==1}">
					<li><a class="listA last" href="#"><c:out value="${sessionScope.loginName}" />&nbsp;&nbsp;欢迎来到YOYO商城</a> </li>
        			<li><a class="listA orange" href="${portalPath}/sign/loginOut">注销登录</a></li>
				   	<c:if test="${sessionScope.accountType==100}">
						<li ><a class="listA" href="${memPath }/sign2/doLogin2">买家中心</a></li>
					</c:if>
					<c:if test="${sessionScope.accountType==110 || sessionScope.accountType==120}">
						<li class="header_li"><a class="listA" href="${shopPath}/sign1/doLogin1">卖家中心</a></li>
						<c:if test="${sessionScope.companyId==null || sessionScope.companyId==0  }"> <!-- 登录了，但是没有入驻店铺 -->
							<li class="header_li"><a class="listA" href="${portalPath}/shopEnter/shopRegister">商家入驻</a></li>
						</c:if>
					</c:if>
					<li class="header_li">
                    	<a class="listA" href="javascript:;">我的优优<span class="drop"></span></a>
                        <div class="dorpdown_layer" style=" width: 270px; ">
                        	<div class="dd_spacer"></div>
                        	<div class="userinfo clearfix">
                            	<div class="u_pic fl"></div>
                                <div class="fl">
                                    <div class="u_name">
                                        <a href="#">你好，<c:out value="${sessionScope.loginName}" /></a>
                                    </div>
                                    <div class="u_extra">
                                        <a href="javascript:;">优惠券</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                                        <a href="javascript:;">消息</a>
                                    </div>
                                </div>
                            </div>
                        	<div class="otherlist clearfix">
                            	<div class="fore1 fl">
                                	<div class="d_item">
                                	<c:if test="${sessionScope.accountType==100}">
										<a  href="${memPath }/sign2/doLogin2">我的订单</a>
									</c:if>
									<c:if test="${sessionScope.accountType==110 || sessionScope.accountType==120}">
										<a href="${shopPath}/sign1/doLogin1">我的订单</a>
									</c:if>
                                	</div>
                                	<div class="d_item"><a href="javascript:;">咨询回复</a></div>
                                	<div class="d_item"><a href="javascript:;">降价商品</a></div>
                                	<div class="d_item"><a href="javascript:;">返修退换货</a></div>
                                </div>
                            	<div class="fore1 fr">
                                	<div class="d_item"><a href="javascript:;">我的关注</a></div>
                                	<div class="d_item"><a href="javascript:;">我的YOYO</a></div>
                                	<div class="d_item"><a href="javascript:;">我的理财</a></div>
                                	<div class="d_item"><a href="javascript:;">我的白条</a></div>
                                </div>
                            </div>
                            <div class="viewlist clearfix">
                            	<div class="smt clearfix">
                                	<h4 class="fl">最近浏览</h4>
                                    <span class="fr"><a href="javascript:;">更多></a></span>
                                </div>
                                <div class="smc clearfix">
                                	<div class="d_item"><a href="javascript:;"></a></div>
                                	<div class="d_item"><a href="javascript:;"></a></div>
                                	<div class="d_item"><a href="javascript:;"></a></div>
                                	<div class="d_item"><a href="javascript:;"></a></div>
                                </div>
                            </div>
                        </div>
                    </li>
				</c:when>
				 <c:otherwise>
				  	 <li><a class="listA last" href="${portalPath}/register/login">欢迎来到YOYO商城，请登录</a> </li>
				  	 <li><a class="listA orange" href="${portalPath}/register/signup">免费注册</a></li>
				  	 <li><a  class="listA" href="${portalPath}/shopEnter/shopRegister">商家入驻</a></li>
				  	 <li class="header_li">
                    	<a class="listA" href="javascript:;">我的优优<span class="drop"></span></a>
                        <div class="dorpdown_layer" style=" width: 270px; ">
                        	<div class="dd_spacer"></div>
                        	<div class="userinfo clearfix">
                            	<div class="u_pic fl"></div>
                                <div class="fl">
                                    <div class="u_name">
                                        <a href="../login/login.html">你好，请登录</a>
                                    </div>
                                    <div class="u_extra">
                                        <a href="javascript:;">优惠券</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                                        <a href="javascript:;">消息</a>
                                    </div>
                                </div>
                            </div>
                        	<div class="otherlist clearfix">
                            	<div class="fore1 fl">
                                	<div class="d_item"><a href="${portalPath}/register/login">我的订单</a></div>
                                	<div class="d_item"><a href="javascript:;">咨询回复</a></div>
                                	<div class="d_item"><a href="javascript:;">降价商品</a></div>
                                	<div class="d_item"><a href="javascript:;">返修退换货</a></div>
                                </div>
                            	<div class="fore1 fr">
                                	<div class="d_item"><a href="javascript:;">我的关注</a></div>
                                	<div class="d_item"><a href="javascript:;">我的YOYO</a></div>
                                	<div class="d_item"><a href="javascript:;">我的理财</a></div>
                                	<div class="d_item"><a href="javascript:;">我的白条</a></div>
                                </div>
                            </div>
                            <div class="viewlist clearfix">
                            	<div class="smt clearfix">
                                	<h4 class="fl">最近浏览</h4>
                                    <span class="fr"><a href="javascript:;">更多></a></span>
                                </div>
                                <div class="smc clearfix">
                                	<div class="d_item"><a href="javascript:;"></a></div>
                                	<div class="d_item"><a href="javascript:;"></a></div>
                                	<div class="d_item"><a href="javascript:;"></a></div>
                                	<div class="d_item"><a href="javascript:;"></a></div>
                                </div>
                            </div>
                        </div>
                    </li>
				 </c:otherwise>
				</c:choose>
                    <li><a class="listA" href="javascript:;">会员俱乐部</a></li>
                    <li><a class="listA" href="javascript:;">企业频道</a></li>
                   <!--  <li class="header_li">
                    	<a class="listA" href="javascript:;">
                        	<span class="phon"></span>手机优优 <span class="drop"></span>
                        </a>
                        <div class="dorpdown_layer" style=" width: 200px; ">
                        	<div class="dd_spacer" style=" width: 90px;"></div>
                        	<div class="userinfo clearfix">
                            	<div class="u_pic fl"></div>
                                <div class="fr">
                                    <div class="u_name" style=" width: 100px;">
                                        <a href="javascript:;">下载</a>
                                    </div>
                                    <div class="u_extra" style=" width: 100px;">
                                        <a href="javascript:;">下载</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li> -->
                    <li class="header_li">
                    	<a class="listA" href="javascript:;">关注优优<span class="drop"></span></a>
                        <div class="dorpdown_layer" style=" width: 100px;">
                        	<div class="dd_spacer"></div>
                        	<div class="userinfo clearfix">
                            	<div class="u_pic fl">二维码</div>
                            </div>
                        </div>
                    </li>
                    <li class="header_li">
                    	<a class="listA" href="javascript:;"> 客服服务<span class="drop"></span></a>
                        <div class="dorpdown_layer" style=" width: 100px;">
                        	<div class="dd_spacer"></div>
                            <div class="fore1 wauto pd20">
                                <!-- <div class="d_item wauto"><a href="javascript:;">我的订单</a></div> -->
                                <div class="d_item wauto"><a href="javascript:;">咨询回复</a></div>
                                <div class="d_item wauto"><a href="javascript:;">降价商品</a></div>
                                <div class="d_item wauto"><a href="javascript:;">返修退换货</a></div>
                            </div>
                        </div>
                    </li>
                    <li class="header_li lasr_Li" id="ttbar">
                    	<a class="listA last" href="javascript:;">网站导航 <span class="drop"></span></a>
                        <div class="dorpdown_layer">
                        	<div class="dd_spacer"></div>
                        	<dl class="fore1">
                            	<dt>特色主题</dt>
                                <dd><div class="d_item"><a href="javascript:;">品牌街</a></div>
                                </dd>
                            </dl>
                        	<dl class="fore2">
                            	<dt>行业频道</dt>
                                <dd><div class="d_item"><a href="javascript:;">服装城</a></div></dd>
                            </dl>
                        	<dl class="fore3">
                            	<dt>生活服务</dt>
                                <dd><div class="d_item"><a href="javascript:;">优优众筹</a></div></dd>
                            </dl>
                        	<dl class="fore4">
                            	<dt>更多精选</dt>
                                <dd><div class="d_item"><a href="javascript:;">优优社区</a></div></dd>
                            </dl>
                        </div>
                   </li>
                </ul>
            </div>
 		  
   </div>
</div>

<div class="header_in clearfix">
  <c:if test="${homeHead.enabled}">
  	<div class="header_in_m"><img src="${path}/${homeHeadAdConfig.content[0].picUrl}" width="1200" height="70"></div>
  </c:if>
  <div class="header_in_b clearfix">
  	<div class="logo fl"><a href="${portalPath}/index">优优商城</a></div>
      <div class="search fl">
      	<div class="search_bg">
      		<label for="tex"></label><input class="ipt" type="text" placeholder="宝马X6" value="" id="searchKey"/><span id="search">搜索</span>
        </div>
        <div class="search_list">
        	<ul class="clearfix">
        		<li><a class="orange" href="javascript:redSearchKey('奥迪');">奥迪</a></li>
	           	<li><a href="javascript:redSearchKey('轮胎');">轮胎</a></li>
	           	<li><a href="javascript:redSearchKey('起亚K5');">起亚K5</a></li>
	           	<li class="noline"><a href="javascript:redSearchKey('小保养');">小保养</a></li>
            </ul>
        </div>
      </div>
     <div class="cart fr clearfix">
     <c:choose>
		<c:when test="${sessionScope.loginStatus==1}">
			<c:if test="${sessionScope.accountType==100}">
	     	<div class="cart_l fl">
	         	<i class="fl">我的车型</i>
	             <a class="add fl openmt" id="addMyCar" href="javascript:;">添加</a>
	             <div class="cart_txt fl">
	                 <a class="f12 openmt" href="javascript:;">新增我的车辆</a>
	                 <h5 class="f12">
                 	 <a class="my_car_list">按车型选择产品，省心放心</a>
                 	 </h5>
	             </div>
	         </div>
	         </c:if>
      	</c:when>
      	<c:otherwise>
      	<div class="cart_l fl">
         	<i class="fl">我的车型</i>
             <a class="add fl openmt" id="addMyCar" href="javascript:;">添加</a>
             <div class="cart_txt fl">
                 <a class="f12 openmt" href="javascript:;">新增我的车辆</a>
                 <h5 class="f12">
                 	<a class="my_car_list">按车型选择产品，省心放心</a>
                 </h5>
             </div>
         </div>
      	</c:otherwise>
     	</c:choose>
     	<c:if test="${sessionScope.accountType==0||sessionScope.accountType==100}">
        <div class="cart_r fr">
        	<a class="cart_shop" href="${portalPath}/cart/index">
            	<span class="cartSpan"><em></em></span>
                <p>购物车</p>
                <i></i>
                <b class="shop_txt" id="cartSize">0</b>
            </a>
            <div class="dorpdown_layer">
            	<div class="dd_spacer"></div>
            	<div id="settleup_con">
                	<div class="smt">
                    	<h4>最新加入的商品</h4>
                    </div>
                    <div class="smc">
                    	<ul>
                        	<li>
                            	<div class="p_img fl"><a href="javascript:;"><img></a></div>
                                <div class="p_name fl"><a href="javascript:;">【西昊】电脑椅子 办公椅子家用转椅座椅人体工学椅 黑色</a></div>
                                <div class="p_detail fr"><span><strong class="red">￥198.00</strong>x1</span><a class="delete" href="javascript:;">删除</a></div>
                            </li>
                        </ul>
                    </div>
                    <div class="smb">
                    	<div class="p_total">共<b>6</b>件商品 共计<strong>￥900.00</strong></div>
                        <a href="${portalPath}/cart/index">去购物车</a>
                    </div>
                </div>
            </div>
        </div>
       </c:if>
     </div>
 </div>
</div>

<div id="myCar" style="display: none;" class="baisebeijing">
	<input type="hidden" id="brand_choose_input"/>
	<input type="hidden" id="brand_log_choose_input"/>
	<input type="hidden" id="dept_choose_input"/>
	<input type="hidden" id="car_choose_input"/>
	<input type="hidden" id="car_name_choose_input"/>
	<input type="hidden" id="year_choose_input"/>
	<div class="carStepLayer">	
		 <div class="stepLayerTitle">
		 <p>
		 	<i>选择车型</i>
		 </p>
		 </div>
	 
		 <div class="layerStep">
		  <ul>
		   <li class="curStep"><a class="nolink" href="javascript:void(0);">汽车品牌</a></li>
		   <li class=""><a class="nolink" href="javascript:void(0);">车系</a></li>
		   <li class=""><a class="nolink" href="javascript:void(0);">车型</a></li>
		   <li class=""><a class="nolink" href="javascript:void(0);">生产年份</a></li>
		  </ul>
		 </div>
	 
		<div id="choosecar1_div_choosecar">
		</div>
	</div>
</div>



<script type="text/javascript">
//header隐藏层显示
$(".header_ul li").bind("mouseover mouseout", function(){
	$(this).toggleClass("hover");	
	
});
$(document).ready(function(){
	//省份设置
	var myProvince = $.cookie('myProvince');
	if(myProvince){
		$('#selectedProvince').text(myProvince+' ');
		$('#selectProvince').find('a').removeClass('selected');
		$('#selectProvince').find('a:contains('+myProvince+')').addClass('selected');
	}
	var url=yoyo.portalUrl+"/cart/findCartSize";
	$.ajax({
		url : url,
		type: 'post',
		dataType : 'json',
		success : function(data) {
			$("#cartSize").html(data.content.cartSize);
		}
	});
	$('#searchKey').bind('keypress',function(event){
       if(event.keyCode == "13")    
       {
    	   $("#search").click();
       }
	 });
	$("#search").click(function(){
		var searchKey=$("#searchKey").val();
		var url=yoyo.portalUrl+"/search?q="+searchKey;
		window.location.href=url;
	});
	$("#indexCreate").click(function(){
		var searchKey=$("#searchKey").val();
		var url=yoyo.portalUrl+"/search/create";
		window.location.href=url;
	});
	$("#deleteIndex").click(function(){
		var url=yoyo.portalUrl+"/search/deleteIndex";
		window.location.href=url;
	});
	//省份选择
	$('#selectProvince').find('a').click(function(){
		$.cookie('myProvince',this.innerText,{
			expires:7,
			path:'/'
		});
		location.reload();
	});
});
function redSearchKey(searchKey){
	var url=yoyo.portalUrl+"/search?q="+searchKey;
	window.location.href=url;
}
</script>