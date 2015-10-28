$(document).ready(function(){
	$('#QQLink').click(function(){
		getQQAuthorizeURL('QQ');
	});
	$('#WXLink').click(function(){
//		authorizeURL('WX');
	});
	$('#SNLink').click(function(){
//		authorizeURL('SN');
	});
	loadModelList();
});

function loadModelList(){
	$("#tableId tr:not(:first)").remove();
	$.ajax({
		url:'../pamAuth/getPamAuthList',
		type:"get",
		dataType:"json",
		success:function(returnData){
			if(returnData.retCode =='000000'){
				var html = '<tbody>';
				$.each(returnData.content, function(index,thirdAccount){
					 html += '<tr>';
					 html += '  <td valign="middle" name="accountName">'+thirdAccount.accountName+'</td>';
					 html += '  <td valign="middle" name="accountType">'+thirdAccount.accountType+'</td>';
					 html += '  <td valign="middle" name="createTime">'+thirdAccount.createTime+'</td>';
					 html += '  <td valign="middle"><a href="javascript:void(0)" onclick="removeBinding('+ thirdAccount.accountId +',&quot;'+thirdAccount.accountType+'&quot;,this)">解除绑定</a>';
					 html += '</tr>';
				 });
				html += "</tbody>";
				$("#tableId").append(html);
			}
			format();
			forbidClick();
		}
	});
}

//替换第三方类型显示
//如果已经绑定某个第三方，就不允许点击相应的图标
function format(){
	$('td[name="accountType"]').each(function(index,ele){
		var $ele = $(ele);
		if($ele.text() == 'SN'){
			$ele.text("新浪微博");
			$('#SNLink').attr('disabled','disabled');
		}else if($ele.text() == 'WX'){
			$ele.text("微信");
			$('#WXLink').attr('disabled','disabled');
		}else if($ele.text() == 'QQ'){
			$('#QQLink').attr('disabled','disabled');
		}
	});
}

function forbidClick(){
	$('a[disabled="disabled"]').each(function(index, ele){
		$(ele).off('click');
	})
}

//解除绑定
function removeBinding(id,type,obj){
	$.ajax({
		url:'../pamAuth/removeBinding',
		type:'post',
		data:{'id':id},
		success:function(data){
			if(data.retCode =='000000'){
				$(obj).closest('tr').remove();
				$('#'+type+'Link').removeAttr('disabled');
				$('#'+type+'Link').click(function(){
					 authorizeURL(type);
				});
			}else{
				alert('取消绑定失败！');
			}
		}
	});
}

//获得授权地址
function authorizeURL(thirdType){
	var callback = _path+'/pamAuth/binding'+thirdType;
	$.ajax({
		type : "POST" ,
		url : _path + "/oauth/getAuthorizeURL" ,
		data : "thirdType=" + thirdType + "&callback=" + callback,
	}).done(function(data){
		if (data.head.retCode == '000000'){
			location.href = data.content;
		}
	});
}