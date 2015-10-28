<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="notice_div" class="ui-corner-all ui-shadow" style="z-index: 2000;display:none;background-color:green;opacity:0.99;width:auto;margin: 0;padding: 5px 10px 5px 10px;text-align: center;position: fixed;bottom: 80px;margin-right: 10px;">
	<div style="color: #fff;text-shadow: none;" id="notice_div_content">设定成功</div>
</div>
<%-- <div id="loading" style="z-index: 200;width: 100%;height: 100%;position: fixed;display: none;opacity:0.4;vertical-align: middle;text-align: center;padding-top: 50%;top: 0;background-color: #fff;">
	<img src="${path}/base/images/individualCenter/loading2.gif"/>
</div> --%>

<script>
	function notice_show(content,methodstr,time){
		var c = content,m = methodstr, t = time;
		if(!c){
			c = "操作成功";
		}
		m = ";$(\"#notice_div\").fadeToggle(\"slow\");"+m;
		if(!t){
			t = 2000;
		}
		
		$("#notice_div_content").html(c);
		var width = Number($("#notice_div").css("width").replace("px",""));
		var left = $(window).width()/2 - width/2;
		if(width >= $(window).width()){
			left = 0;
		}
		$("#notice_div").css("left",left + "px");
		$("#notice_div").fadeToggle("slow");
		setTimeout(m,2000);
	}
	
	/* function notice_show_1(content){
		var c = content;
		if(!c){
			c = "操作成功";
		}
		var m = ";$(\"#notice_div\").fadeToggle(\"slow\");";
		$("#notice_div_content").html(c);
		var width = Number($("#notice_div").css("width").replace("px",""));
		var left = $(window).width()/2 - width/2;
		if(width >= $(window).width()){
			left = 0;
		}
		$("#notice_div").css("left",left + "px");
		$("#notice_div").fadeToggle("slow");
		setTimeout(m,2000);
	}
	
	function notice_loadding(){
		$("#loading").toggle();
	} */
</script>
