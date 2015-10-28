var totalRecords,memberId;
$(function() {
	$(".Plate a").click(function() {
		if ($(this).hasClass("current")){
			return;
		}
		$(this).addClass("current").siblings().removeClass("current");
	});
	var passStatus = getParameter('passStatus');
	if(passStatus){
		var numSelect = parseInt(passStatus)+1;
		$(".Plate a").eq(numSelect).trigger('click');
	}
	
	function ajaxGetData(){
		$.ajax({
			url:yoyo.memUrl+'/mypainting/ajaxData',
			data:{'memberId':memberId,'page':$('span[class="current"]').text(),'passStatus':passStatus},
			success:function(data){
				debugger
				var html = '';
				$.each(data.rows,function(index,ele){
					var rowNum = ele.detailList.length;
					$.each(ele.detailList,function(_index, _ele){
						if(index%2 == 0){
							html += '<tr>';
						}else{
							html += '<tr style="background-color:#F5F5F5">';
						}
						var createTime = new Date(ele.createtime).pattern("yyyy-MM-dd hh:mm:ss");
						var date = createTime.split(" ")[0];
						var time = createTime.split(" ")[1];
						html += '	<td>';
						var picArr = _ele.pic.split(';');
						for ( var int = 0; int < picArr.length; int++) {
							html += '		<div class="sell_img fl">';
							html += '			<a href="javascript:void(0)"><img src="'+ yoyo.imagesUrl + picArr[int] +'" style="width:68px; height:68px"></a>';
							html += '		</div>';
						}
						html += '		<div class="sell_name fl">';
						html += '			<p>';
						html += 				_ele.carPart.partName;
						html += '			<p>';
						html += '		</div>';
						html += '	</td>';
						if(_index == 0){
							html += '	<td class="pading_left" style="text-align:left" rowspan="'+rowNum+'">';
							html += '		受损单编号：<a class="dd_color" href="javascript:void(0)" onclick="detail(this)">'+ ele.id+'</a>';
							html += '		<br>';
							if(ele.source != 'web'){
								html += '	<span class="source">来自'+ ele.source +'客户端</span>';
							}
							html += '	</td>';
							html += '	<td class="sell_time" rowspan="'+rowNum+'">';
							html += '		<p>'+ date +'</p>';
							html += '		<p>'+ time +'</p>';
							html += '	</td>';
							if(ele.passStatus == 0){
								html +=	'	<td rowspan="'+rowNum+'">审核中</td>';
							}else if(ele.passStatus == 1){
								html +=	'	<td rowspan="'+rowNum+'">报价中</td>';
							}else if(ele.passStatus == 2){
								html +=	'	<td class="red" rowspan="'+rowNum+'">审核未通过</td>';
							}else{
								html +=	'	<td rowspan="'+rowNum+'">已成交</td>';
							}
							html += '	<td rowspan="'+rowNum+'">';
							html += '		<div class="buy_opera">';
							if(ele.passStatus <2 && ele.offerCount <= 0){
								html +=	'    	<span class="offerPrice">暂未有商家报价</span>';
							}else if(ele.passStatus ==2){
								html +=	'    	<span class="offerPrice">'+ (ele.reason==null ? '':ele.reason) +'</span>';
							}else if(ele.passStatus <2 && ele.offerCount > 0){
								html +=	'    	<span class="offerPrice">已有'+ ele.offerCount +'家商家报价<br><a href="../mypainting/offers?id='+ ele.id +'">点击查看报价</a></span>';
							}else if(ele.passStatus ==3 ){
								html +=	'    	<span class="offerPrice"><a href="../mypainting/orderDetail?damageId='+ ele.id +'">订单详情</a></span>';
							}
							html += '		</div>';
							html += '	</td>';
						}
						html += '</tr>';
					});
				});
				$('tbody').html(html);
			}
		});
	}
	var opt = {
		callback:function (){
			ajaxGetData();
		}
	};
	$("#pagination").yoyoPagination(totalRecords,opt);
});
