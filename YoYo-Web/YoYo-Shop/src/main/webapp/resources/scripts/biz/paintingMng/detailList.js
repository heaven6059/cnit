var totalRecords,companyId,accountId;

$(function(){
	var opt = {
			callback:function (){
				ajaxGetData();
			}
		};
	$("#pagination").yoyoPagination(totalRecords,opt);
});

function detail(obj){
	 $(obj).parents('tr').next().find('span[name="aText"]').trigger('click');
}

function ajaxGetData(){
	$.ajax({
		url:yoyo.shopUrl+'/paintingManager/ajaxList',
		data:{'canSee':true,'companyId':companyId,'page':$('span[class="current"]').text()},
		success:function(damageList){
			var html = '<tr>';
			$.each(damageList.rows,function(index,damage){
				var createTime = new Date(damage.createtime).pattern("yyyy-MM-dd hh:mm:ss");
				var date = createTime.split(" ")[0];
				var time = createTime.split(" ")[1];
				
				html += '<th colspan="6" class="padding_left">受损单编号：<a class="dd_color" href="javascript:void(0)" onClick="detail(this)">'+damage.id+'</a>';
				if(damage.source != 'web'){
					html += '<span> ('+ damage.source +'客户端)</span>';
				}
				html += '</th></tr>';
				html += '<tr>';
				html += '<td>';
				$.each(damage.detailList,function(index,detail){
					var index = detail.pic.indexOf(";");
					html += '<div class="sell_img fl">';
					if( index != -1){
						html += '<a href="javascript:void(0)"><img src="'+ yoyo.imagesUrl + detail.pic.slice(0,index) +'" style="width:68px; height:68px"></a>';
					}else{
						html += '<a href="javascript:void(0)"><img src="'+ yoyo.imagesUrl + detail.pic +'" style="width:68px; height:68px"></a>';
					}
					html += '</div>';
				});
				html += '<div class="sell_name fl">';
				html += '	<p>';
				$.each(damage.detailList,function(index,detail){
					if( index < damage.detailList.length-1){
						html += detail.carPart.partName +'，';
					}else{
						html += detail.carPart.partName;
					}
				});
				html += '	</p>';
				html += '</div>';
				html += '</td>';
				html += '<td>';
				html += '<p>'+damage.pamAccount.loginName+'</p>';
				html += '</td>';
				html += '<td class="sell_time">';
				html +=	'	 	<p>'+ date +'</p>';
				html +=	'	 	<p>'+ time +'</p>';
				html +=	'	</td>';
				if(damage.offered){
					html +=	'<td class="red">已报价</td>';
					html +=	'<td>';
					html +=	'    <div class="buy_opera">';
					html +=	'   		<a href="../paintingManager/detail?id='+damage.id+'&edit=0"><span name="aText">查看详情</span></a>';
					html +=	'    </div>';
					html +=	'</td>';
				}else{
					html +=	'<td >未报价</td>';
					html +=	'<td>';
					html +=	'    <div class="buy_opera">';
					html +=	'   		<a href="../paintingManager/detail?id='+damage.id+'&edit=1"><span name="aText">我要报价</span></a>';
					html +=	'    </div>';
					html +=	'</td>';
				}
				html +=	'</tr>';
			});
			$('tbody').html(html);
		}
	});
}