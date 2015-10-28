var loginUrl=yoyo.memUrl+'/register/login';
//var indexUrl=yoyo.memUrl;//店铺不存在转跳
$(function(){
	//加载保证金对象
	findEarnest(1,$('.pagination-page-list').val());
	//修改每页显示数
	$('.pagination-page-list').on('change', function() {
		var pageSize = $(this).val();
		findEarnest(1, pageSize);
	});
	//显示首页
	$('.pagination-first').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		findEarnest(1, pageSize);
	});
	//显示上一页
	$('.pagination-prev').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		var pageIndex = $('.pagination-num').val();
		findEarnest(parseInt(pageIndex)-1, pageSize);
	});
	//显示下一页
	$('.pagination-next').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		var pageIndex = $('.pagination-num').val();
		findEarnest(parseInt(pageIndex)+1, pageSize);
	});
	//显示末页
	$('.pagination-last').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		findEarnest(99999, pageSize);
	});
	//刷新角色列表
	$('.pagination-load').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		var pageIndex = $('.pagination-num').val();
		findEarnest(pageIndex, pageSize);
	});
	//修改当前页码
	$('input.pagination-num').on('change', function() {
		findEarnest($('.pagination-num').val(), $('.pagination-page-list').val());
	});
});

//加载保证金对象
function findEarnest(pageIndex, pageSize){
	var url = _path+'/earnestManager/findEarnest';
	var params = {};
	params.pageSize=pageSize;
	params.pageIndex=pageIndex;
	commonAjax(url, params, function(data) {
		if(data.content.result==false){
			if(data.content.isLogin==false){
				alert("请先登录！");
				window.location.href = loginUrl;
			}else if(data.content.isStoreExist==false){
				alert("该店铺不存在！");
				window.location.href = indexUrl;
			}else if(data.content.isCompanyExist==false){
				alert("该集团不存在！");
				window.location.href = indexUrl;
			}else{
				alert(data.content.msg);
			}
		}else{
			if(data.content.page!=null&&data.content.page.rows.length>=1){
				var trs=$(".datagrid-btable").find('tr');
				for(var i=2;i<trs.length;i++){
					$(trs[i]).remove();
				}
				for(var i=0;i<data.content.page.rows.length;i++){
					var tr=$(".datagrid-btable").find('tr').eq(-1);
		            var tr2=tr.clone(true);
		            $(tr2).attr("id","earnest_"+data.content.page.rows[i].id);
		            tr2.find('td[field="earnest"]').text("￥"+(data.content.page.rows[i].value).toFixed(2)); 
		            tr2.find('td[field="operator"]').text(data.content.page.rows[i].operator); 
		            tr2.find('td[field="remark"]').text(data.content.page.rows[i].remark); 
		            tr2.find('td[field="date"]').text(data.content.page.rows[i].addDate); 
		            tr.after( tr2 ); 
		            if(i==0){
		            	tr.remove();
		            }
				}
//				$('span[name="earnestValue"]').text("￥"+(parseFloat(data.content.sumEarnest)-parseFloat(data.content.sumViolationEarnest)).toFixed(2));
//				$('span[name="earnestValue2"]').text("￥"+(parseFloat(data.content.issueMoney)-(parseFloat(data.content.sumEarnest)-parseFloat(data.content.sumViolationEarnest))).toFixed(2));
//				$('span[name="earnestValue"]').text("￥"+(data.content.earnest.toFixed(2)));
				if(parseFloat(data.content.earnest)<0){
					$('span[name="earnestValue"]').text("￥0.00");
					$('span[name="earnestValue2"]').text("￥"+(parseFloat(0)-parseFloat(data.content.earnest)).toFixed(2));
					$('span[name="earnestValue2"]').parent().show();
				}else{
					$('span[name="earnestValue"]').text("￥"+parseFloat(data.content.earnest).toFixed(2));
				}
				$('.pagination-num').val(data.content.page.pageIndex);
				$('span[name="sumPages"]').text("共"+data.content.page.pages+"页");
				$('.pagination-info').text('显示'+((data.content.page.pageIndex-1)*data.content.page.pageSize+1)+'到'+((data.content.page.pageIndex-1)*data.content.page.pageSize+data.content.page.currPageSize)+',共'+data.content.page.total+'记录');
			}else{
				var trs=$(".datagrid-btable").find('tr');
				for(var i=1;i<trs.length;i++){
					$(trs[i]).remove();
				}
				$(".datagrid-btable").append('<tr class="emptyRoles"><td colspan="4">当前无记录</td></tr>');
//				$('span[name="earnestValue2"]').text("￥"+(data.content.issueMoney.toFixed(2)));
				$('span[name="earnestValue"]').text("￥"+(data.content.earnest.toFixed(2)));
				$('.datagrid-pager').hide();
			}
		}
	}, function(data) {
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
}

//保留两位小数
function formatNumber(value) {     
    var xsd = value.toString().split(".");
    if(xsd.length==1){
     	value = value.toString()+".00";
     	return value;
    }
    if(xsd.length>1){
     	if(xsd[1].length<2){
      		value = value.toString()+"0";  
     	}
    	return value;
    }
}
