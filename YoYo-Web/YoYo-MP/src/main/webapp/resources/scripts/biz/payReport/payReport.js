var check = {};

check.insureBaseUrl=yoyo.mpUrl+'/pay/getList';
check.currentDatagrid="paylog";
/**
 * 页面初始化
 */
$(document).ready(function(){
	check.loadDataGrid();//初始化列表
	check.onClickTabs();
});

check.loadDataGrid = function(){
	$('#paylog').datagrid({
        methord: 'post',
        url : check.queryBy(check.insureBaseUrl),
        pageSize: 20,
	    pageList: [20,50,100,150],
        border: true,
        pagination : true,
		rownumbers : true,
        showFooter:true,
        columns: [[
				{ field: 'orderId', title: '订单号', width: 200, align: 'center'},
				{ field: 'totalFee', title: '交易金额', width: 80, align: 'center' },
				{ field: 'name', title: '买家', width: 100, align: 'center' },
				{ field: 'buyerEmail', title: '买家支付账号', width: 200, align: 'center' },
				{ field: 'shopName', title: '卖家', width: 100, align: 'center' },
				{ field: 'sellerEmail', title: '卖家支付账号', width: 200, align: 'center' },
				{ field: 'tradeNo', title: '支付交易号', width: 250, align: 'center' },
				{ field: 'paymentType', title: '支付方式', width: 80, align: 'center',
					formatter : function(value, row) {
						if (row.buyerEmail )
							if(row.buyerEmail.indexOf('@alipay.com')>-1) {
								return '网银支付';
							} else {
								return '支付宝支付';
							}
				 }},
				{ field: 'notifyTime', title: '支付系统通知时间', width: 200, align: 'center' },
				{ field: 'createTime', title: '记录时间', width: 200, align: 'center' }
			]],
        onLoadSuccess: function (data) {
           
        }
    });
	$('#paylog').datagrid({
		height : ($(window).height() * 0.80)
	});
};
check.refund = function(){
	$('#refund').datagrid({
        methord: 'post',
        url : check.queryBy(check.insureBaseUrl+"?type=3"),
        pageSize: 20,
	    pageList: [20,50,100,150],
        border: true,
        pagination : true,
		rownumbers : true,
        showFooter:true,
        columns: [[
				{ field: 'orderId', title: '订单号', width: 200, align: 'center'},
				{ field: 'totalFee', title: '退款金额', width: 80, align: 'center' },
				{ field: 'name', title: '买家', width: 100, align: 'center' },
				{ field: 'buyerEmail', title: '买家支付账号', width: 200, align: 'center' },
				{ field: 'shopName', title: '卖家', width: 100, align: 'center' },
				{ field: 'sellerEmail', title: '卖家支付账号', width: 200, align: 'center' },
				{ field: 'tradeNo', title: '交易号', width: 250, align: 'center' },
				{ field: 'createTime', title: '退款时间', width: 200, align: 'center' }
			]],
        onLoadSuccess: function (data) {}
    });
	$('#refund').datagrid({
		height : ($(window).height() * 0.80)
	});
};
check.buyer = function(){
	$('#buyer').datagrid({
        methord: 'post',
        url : check.queryBy(check.insureBaseUrl+"?type=1"),
        pageSize: 20,
	    pageList: [20,50,100,150],
        border: true,
        pagination : true,
		rownumbers : true,
        showFooter:true,
        columns: [[
				{ field: 'name', title: '买家', width: 100, align: 'center' },
				{ field: 'orderId', title: '交易笔数', width: 100, align: 'center'},
				{ field: 'totalFee', title: '交易总金额', width: 150, align: 'center' },
				{ field: 'buyerEmail', title: '买家支付账号', width: 250, align: 'center' },
				{ field: 'createTime', title: '最近交易时间', width: 250, align: 'center' }
			]],
        onLoadSuccess: function (data) {
           
        }
    });
	$('#buyer').datagrid({
		height : ($(window).height() * 0.80)
	});
};
check.sell = function(){
	$('#sell').datagrid({
        methord: 'post',
        url : check.queryBy(check.insureBaseUrl+"?type=2"),
        pageSize: 20,
	    pageList: [20,50,100,150],
        border: true,
        pagination : true,
		rownumbers : true,
        showFooter:true,
        columns: [[
				{ field: 'shopName', title: '卖家', width: 200, align: 'center' },
				{ field: 'orderId', title: '交易笔数', width: 100, align: 'center'},
				{ field: 'totalFee', title: '交易总金额', width: 150, align: 'center' },
				{ field: 'sellerEmail', title: '卖家支付账号', width: 250, align: 'center' },
				{ field: 'createTime', title: '最近交易时间', width: 250, align: 'center' }
			]],
        onLoadSuccess: function (data) {}
    });
	$('#sell').datagrid({
		height : ($(window).height() * 0.80)
	});
};
check.onClickTabs = function(){
	$('#InsureListTabs').tabs({
	    border: true,
	    onSelect: function (title) {
	        switch (title) {
	            case '支付报表':check.currentDatagrid = "paylog";  break;
	            case '退款报表':check.currentDatagrid = "refund";  break;
	            case '买家报表': check.currentDatagrid = "buyer";  break;
	            case '卖家报表': check.currentDatagrid = "sell";  break;
	        };
	
	        if (title == "支付报表") {
	        	check.loadDataGrid();
	        }
	        else if (title == '退款报表') {
	        	check.refund();
	        }
	        else if (title == '买家报表') {
	        	check.buyer();
	        }
	        else if (title == '卖家报表') {
	        	check.sell();
	        }
	    }
	});
};
check.search = function(){
	if (check.currentDatagrid == "paylog") {
		check.loadDataGrid();
	} else if (check.currentDatagrid == "refund") {
		check.loadDataGrid();
	} else if (check.currentDatagrid == "buyer") {
		check.buyer();
	} else if (check.currentDatagrid == "sell") {
		check.sell();
	}
};
check.selectDate = function(type){
	var oDate = new Date();
	var a = oDate.getDay();//今天周几
	if(type=='work'){
		oDate.setDate(oDate.getDate() - 7);
		$("#startCreateTimes").datebox("setValue", oDate.format("yyyy-MM-dd"));
	}else if(type=='month'){
		$("#startCreateTimes").datebox("setValue",yoyo.getPreMonthDate());
	}else if(type=='yeah'){
		$("#startCreateTimes").datebox("setValue",yoyo.getSexMonthDate());
	}
	$("#endCreateTimes").datebox("setValue", new Date().format("yyyy-MM-dd"));
};
check.queryBy= function(url) {
	var sbUrl=[];
	sbUrl.push(url);
	var orderId=$("#orderId").val();
	var name=$("#name").val();
	var buyerEmail=$("#buyerEmail").val();
	var shopName=$("#shopName").val();
	var sellerEmail=$("#sellerEmail").val();
	var tradeNo=$("#tradeNo").val();
	var startCreateTimes = $('#startCreateTimes').datebox('getValue'); 
	var endCreateTimes = $('#endCreateTimes').datebox('getValue'); 
	if($.trim(orderId) && $.trim(orderId)!="null"){
		sbUrl.push("&orderId="+$.trim(orderId));
	}
	if($.trim(name) && $.trim(name)!="null"){
		sbUrl.push("&name="+$.trim(name));
	}
	if($.trim(buyerEmail) && $.trim(buyerEmail)!="null"){
		sbUrl.push("&buyerEmail="+$.trim(buyerEmail));
	}
	if($.trim(shopName) && $.trim(shopName)!="null"){
		sbUrl.push("&shopName="+$.trim(shopName));
	}
	if($.trim(sellerEmail) && $.trim(sellerEmail)!="null"){
		sbUrl.push("&sellerEmail="+$.trim(sellerEmail));
	}
	if($.trim(tradeNo) && $.trim(tradeNo)!="null"){
		sbUrl.push("&tradeNo="+$.trim(tradeNo));
	}
	if($.trim(startCreateTimes) && $.trim(startCreateTimes)!="null"){
		sbUrl.push("&startDate="+$.trim(startCreateTimes));
	}
	if($.trim(endCreateTimes) && $.trim(endCreateTimes)!="null"){
		sbUrl.push("&endDate="+$.trim(endCreateTimes));
	}
	var returnUrl=sbUrl.join("");
	if(returnUrl.indexOf("?")==-1){
		returnUrl=returnUrl.replace("&","?");
	}
  	return returnUrl;
}
