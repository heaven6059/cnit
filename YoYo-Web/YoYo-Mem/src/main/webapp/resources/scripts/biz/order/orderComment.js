$(document).ready(function(){
	$(".tb-void").on("click",".commstar a",function (){
		$(this).addClass("active").siblings().removeClass("active");
		$("#commentStar").val($(this).attr("_val"));
	});
	
	$(".tb-void").on("click",".submitform",function (){
		if(($("#title").val().length<4)||($("#title").val().length>20)){
			$("#title").parent().next(".msg-error-01").removeClass("hide");			
			return;
		}
		
		if($("#commentStar").val().length==0){
			$("#commentStar").closest(".item").find(".msg-error-01").removeClass("hide");
			return;
		}
		
		if(($.trim($("#comment").val()).length<10)||($.trim($("#comment").val()).length>500)){
			$("#comment").closest(".item").find(".msg-error-01").removeClass("hide");
			return;
		}else{
			$("#comment").closest(".item").find(".msg-error-01").addClass("hide");	
		}
		return;
		var form=$(this).closest("form");
		$(".tb-void").off("click",".submitform");
		$.post("../orderComment/addOrderComment",form.serialize(),function(result){
			if(result.retCode=="000000"){
				layer.alert("发表评论成功！",{title:false,closeBtn:false,icon:1,end:function(){
					window.location.href="";
				}});
			}else{
				layer.alert("发表评论失败！",{title:false,closeBtn:false,icon:0,end:function(){
					window.location.href="";
				}});
			}
		});
	});
	
	$(".lazy").each(function (x,img){
		$(img).attr("src",yoyo.imagesUrl+$(img).attr("src"));
	});
	
	$(".pj").click(function (){
		$(".tb-void").find(".comment-box").remove();
		if($(this).closest("td").find(".comment-box").length==0){
			$(this).closest("td").find("form").append($("#cmment_template").html());
			$(this).closest("td").find(".comment-box").show("200");
		}
		
	});
});
