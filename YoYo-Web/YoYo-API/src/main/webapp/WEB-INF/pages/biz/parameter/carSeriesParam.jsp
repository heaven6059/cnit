<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    request.setAttribute("t", System.currentTimeMillis());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=emulateie7" />
    <title></title>
    <base target="_blank" />
    <script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
    <link rel="stylesheet" href="${path}/resources/styles/bo.ashx" />
    <link rel="stylesheet" href="${path}/resources/styles/carParams.css?t=${t}" />
</head>
<body>
    <div class="mainWrap sub_nav">
    </div>
    
    <div class="pzbox" id="content">
        <!--顶部操作区开始-->
        <div class="operation" id="config_nav"></div>
        <div class="operation" id="config_nav_temp" style="display: none; z-index: 0; filter: alpha(opacity=0); opacity: 0;"></div>
        <!--顶部操作区结束-->
        <div class="conbox" id="config_data">
            <!--左侧浮层区开始-->
            <div id="config_side" class="fdbox" style="display: none;"></div>
            <!--左侧浮层区结束-->
        </div>
        <div class="pztip">注：以上参数配置信息仅供参考，实际参数配置信息以店内销售车辆为准，解释权归生产厂家所有。</div>
    </div>

	<div class="comparepop"
		style="right: 0px; position: fixed; top: 180px; width: 50px;">
		<div class="comparepop-content fn-hide">
			<ul class="comparepop-list"></ul>
			<div class="comparepop-alert fn-hide" style="color:#666666;">最多4个车型，如添加请先删除一个。</div>
			<div class="comparepop-select" id="divCompare">
				<div class="area">
					<div class="select" style="z-index: 40;" id="divBrand">
						<div class="select-selected" data-key="0" data-val="选择品牌" data-type="brand">
							<span>选择品牌</span><i class="icon10 icon10-down1"></i>
						</div>
						<div style="display: none;" class="select-option">
							<dl>							
							</dl>
						</div>
					</div>
				</div>
				<div class="area">
					<div class="select select-disabled" style="z-index: 20;"
						id="divSeries">
						<div class="select-selected" data-key="0" data-val="选择车系" data-type="series">
							<span>选择车系</span><i class="icon10 icon10-down1"></i>
						</div>
						<div style="display: none;" class="select-option">
							<dl></dl>
						</div>
					</div>
				</div>
				<div class="area">
					<div class="select select-disabled" style="z-index: 10;"
						id="divSpec">
						<div class="select-selected" data-key="0" data-val="选择车型" data-type="spec">
							<span>选择车型</span><i class="icon10 icon10-down1"></i>
						</div>
						<div style="display: none;" class="select-option select-option-cx">
							<dl></dl>
						</div>
					</div>
				</div>
				<div style="display: none;" class="area comparepop-select-btn">
					<a href="javascript:void(0);" class="btn btn-small btn-orange fn-left" target="_self">开始对比</a>
					<a href="javascript:void(0);" class="fn-right" target="_self">清空</a>
				</div>
			</div>
		</div>
		<a class="comparepop-btn" href="javascript:void(0);" target="_self">车型对比</a>
	</div>
	<div style="clear:both;"></div>    
    <script type="text/javascript" src="${path}/resources/scripts/biz/parameter/library.js"></script>
    <script type="text/javascript">
    var config = ${result};                
    </script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/parameter/config-min.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/parameter/seriesParam.js"></script>
    <script type="text/javascript">
     
    </script>
    <script type="text/javascript">
        var _paramConfig = paramConfig(null, null,null);
    </script>
    
<form action="${path}/carCompare/carCompare" id="sub">
	<input type="hidden" id="car_id" name="ids"/>
</form>
</body>
</html>
