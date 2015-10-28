var damageId = getParameter('id');
$(function(){
	var area = $.cookie('myProvince');
	$('#submit').click(function(){
		var companyId = $( "input:checked" ).val();
		var name = $.trim($( "input:checked" ).parent().next().text());
		location.href = yoyo.memUrl+'/mypainting/chooseStore?companyId='+ companyId +'&name='+name+'&damageId='+damageId;
	})
	
})

function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) {
		return unescape(r[2]);
	} 
	return null;
}

var toggle = function(obj){
	var $this = $(obj);
	debugger
	if($this.find('span').hasClass('openClose')){
		$this.find('span').attr('class','openClose2');
		$(obj).parent().parent().next().find('td').hide();
	}else{
		$this.find('span').attr('class','openClose');
		$(obj).parent().parent().next().find('td').show();
	}
}

