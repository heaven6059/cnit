$(function(){
	$("#add_point_type").click(function (){
		if($("#comment_setting").find("ul li").length>=10){
			$.messager.alert('信息',"最多只可添加10项");
			return;
		}else{
			$("#comment_setting").find("ul").append($("#point_template").html());
		}
	});
	
	$("#add_gask_type").click(function (){
		if($("#consult_items").find("tr:gt(0)").length>=10){
			$.messager.alert('信息',"最多只可添加10项");
			return;
		}else{
			$("#consult_items").append($("#ask_item_template").html());
		}
	});
	
	$("#comment_setting").on("click",":checkbox",function(){
		if($(this).is(":checked")){
			$(this).val('1');
		}else{
			$(this).val('0');
		}
	});
	
	$("#comment_setting").on("focusout","textarea",function(){
		if ($(this).val().length > 100){
			$.messager.alert('信息',"最多只可输入100个字符,请修改输入字符数");
	   	}
	});
	
	$("#sub_form_2").click(function (){
		var next = true;
		$.each($("#consult_items").find("input"),function (x,input){
			if($.trim($(input).val()).length<=0){
				$(input).focus();
				$.messager.alert('信息',"请输入咨询标题");
				next = false;
				return next;
			}
		});
		if(next){
			$(this).closest("form[name=consult_form]").submit();
		}
	});
	
	$("#sub_form_3").click(function (){
		var param_obj="[";
		var next=true;
		$.each($("#comment_setting").find("li"),function (x,o){
			param_obj+="{"
			$(o).find("input").each(function (x,input){
				var curInput=$(input);
				if($.trim(curInput.val()).length<=0&&curInput.attr("requird")){						
					$(input).focus();
					$.messager.alert('信息',curInput.attr("requird"));
					next=false;
					return next;
				}					
				param_obj+='"'+curInput.attr("name")+'":"'+curInput.val()+'",';
			});
			$(o).find("textarea").each(function (x,input){
				var curInput=$(input);
				if($.trim(curInput.val()).length<=0&&curInput.attr("requird")){
					$(input).focus();
					$.messager.alert('信息',curInput.attr("requird"));
					next=false;
					return next;
				}
				if ($(this).val().length > 100){
					$.messager.alert('信息',"最多只可输入100个字符请修改后数再提交");
					next=false;
					return next;
			   	}
				param_obj+='"'+$(input).attr("name")+'":"'+$(input).val()+'",';
			});			
			param_obj=param_obj.substring(0, param_obj.length-1);
			param_obj+="},"
			return next;
		});
		param_obj=param_obj.substring(0, param_obj.length-1);
		$("#goods_point").val(param_obj+"]");
		if(next){
			$(this).closest("form[name=comment_form]").submit();
		}
	});
	
	$("#consult_setting .division").on("click","span",function (){
		var curObj=$(this);
		if(!$(this).attr("data-id")){
			curObj.parent().parent().remove();
			return;
		}
		$.messager.confirm('确认对话框', '确定要删除该项?', function(r){
			if(r){
				$.ajax({
		    	    url:"../consultCommentArgs/deleteConsultItems",// 跳转到 action    
		    	    data:{id:curObj.attr("data-id")},
		    	    type:'post',    
		    	    cache:false,    
		    	    dataType:'json',    
		    	    success:function(data) {
		    	    	if(data.headObject.retCode==0000){
		    	    		$.messager.alert('信息','删除成功');
		    	    		curObj.parent().parent().remove();
		    	    	}else{
		    	    		$.messager.alert('信息','删除失败');
		    	    	}
		    	     },    
		    	     error : function() {    
		    	          alert("异常！");
		    	     }
		    	});
			}
		});
	});
	
	$("#comment_setting .division").on("click","span", function(){
		var curObj=$(this);
		if(!$(this).attr("data-id")){
			curObj.parent().remove();
			return;
		}
		$.messager.confirm('确认对话框', '确定要删除该项?', function(r){
			if (r){
				$.ajax({
		    	    url:"../consultCommentArgs/deleteGoodsPoint",// 跳转到 action    
		    	    data:{id:curObj.attr("data-id")},
		    	    type:'post',    
		    	    cache:false,    
		    	    dataType:'json',    
		    	    success:function(data) {
		    	    	if(data.headObject.retCode==0000){
		    	    		$.messager.alert('信息','删除成功');
		    	    		curObj.parent().remove();
		    	    	}else{
		    	    		$.messager.alert('信息','删除失败');
		    	    	}
		    	     },    
		    	     error : function() {    
		    	          alert("异常！");
		    	     }
		    	});
			}
		});
	});
});