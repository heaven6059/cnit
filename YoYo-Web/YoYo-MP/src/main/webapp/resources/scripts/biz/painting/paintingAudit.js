;
$(function(){
	var prefix = _path + '/painting/damageList';
	$('#tabs').tabs({'onSelect': function(title,index){
		if(index == '0'){
			$('#paintingTable').datagrid('options').url = prefix;
		}else if(index == '1'){
			$('#paintingTable').datagrid('options').url = prefix + '?passStatus=0';
		}else  if(index == '2'){
			$('#paintingTable').datagrid('options').url = prefix + '?canSee=true';
		}else{
			$('#paintingTable').datagrid('options').url = prefix + '?passStatus=2';
		}
		$('#paintingTable').datagrid('reload');
	}});
	$('#paintingTable').datagrid({
		url : _path + '/painting/damageList',
		columns:[[
	                {field:'ck',checkbox:"true",sortable:true,halign:'center'},
	                {field:'id',title:'受损单号',halign:'center',width:'80px'},
	                {
	                	field:'partName',
	                	title:'受损部位',
	                	halign:'center',
	                	width:'300px',
	                	formatter:function(value,row,index){
	                		if(row.detailList && row.detailList.length > 0){
	                			var partnameArr = [];
	                			for(var i=0; i<row.detailList.length; i++){
	                				partnameArr.push(row.detailList[i].carPart.partName);
	                			} 
	                			return partnameArr.join(', ');
	                		}
	                	}
	                },
	                {
	                	field:'createtime',
	                	halign:'center',
	                	width:'200px',
	                	title:'下单时间',
                		formatter:function(value,row,index){  
                            var unixTimestamp = new Date(value).format("yyyy-MM-dd hh:mm:ss");  
                            return unixTimestamp;  
                        }  
	                },
	                {	
	                	field:'name',
	                	title:'买家账号',
	                	width:'100px',
	                	halign:'center',
	                	formatter: function(value, row, index){
	                		if(row.pamAccount){
	                			return row.pamAccount.loginName
	                		}
	                	}
	                },
	                {
	                	field:'passStatus',
	                	title:'审核状态',
	                	halign:'center',
	                	width:'100px',
	                	formatter: function(value, row, index){
	                		if(value == "0"){ 
	                			return "待审核"
	                		}else if (value == "1" || value == "3"){ 
	                			return "已通过"
	                		}else if (value == "2"){
	                			return "未通过"
	                		}
	                	}
	                },
	                {
	                	field:'action',
	                	title:'操作',
	                	halign:'center',
	                	width:'200px',
	                	formatter: function(value, row, index){
		    				if(row.passStatus == "0"){
		    					return "<a href='javascript:void(0)' onclick='changeStatus(" + row.id + ","+ 1 +")'><span>审核通过</span></a>" +
		    							"<a href='javascript:void(0)' onclick='changeStatus(" + row.id + ","+ 2 +")'><span>审核不通过</span></a>";
		    				}else{
		    					return "--";
		    				}
		    			}
	                }]],
        pageSize: 25,
	    pageList: [25,50,100,150],
		pagination : true, //分页
		rownumbers : true, //显示行号
		fitColumns : true, //自适应宽度
		checkOnSelect : true,
		multiSort : true,
		nowrap : false, //字段长度超过时不截取
		loadFilter : function(data){
			debugger
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		view: detailview, //点击查看详细视图    
		detailFormatter : function(index, row) {
			return '<div id="detailView_'+index+'"></div>';
		},
		onExpandRow : function(index, row) {
			$('#detailView_'+ index).panel({
				border : false,
				cache : false,
				content : detailHtml(row),
				onLoad : function() {
					$('#paintingTable').datagrid('fixDetailRowHeight', index);
				}
			})
			$('#paintingTable').datagrid('fixDetailRowHeight', index);
		},
		onLoadSuccess:function(data){
			$('.datagrid-row td').css('text-align','center');
		}
	});
	
});
			
//展开详细信息html
function detailHtml(row){
	var $div = $(document.createElement('div'));
	$div.html('<div class="detailPic"></div>');
	var detailList = row.detailList;
	if(detailList.length > 0){
		for(var i=0; i<detailList.length; i++){
			$div.find('.detailPic').eq(0).append('<div class="pic"></div><div class="remark"></div>');
			var detail = detailList[i];
			var picStr = detail.pic;
			if(picStr.indexOf(';') > 0){
				var _picArr = picStr.split(';');
			}
			var _remark = detail.remark;
			if(!(_picArr === undefined) && _picArr.length > 0){
				$(_picArr).each(function(index){
					$div.find('.pic').eq(i).append('<a target="_blank" href="'+yoyo.imagesUrl+_picArr[index]+'"><img class="_pic" width="100px" height="100px" src="'+yoyo.imagesUrl+_picArr[index]+'"/></a>');
				});
			}else{
				$div.find('.pic').eq(i).append('<a target="_blank" href="'+yoyo.imagesUrl+picStr+'"><img class="_pic" width="100px" height="100px" src="'+yoyo.imagesUrl+picStr+'"/></a>');
			}
			$div.find('.remark').eq(i).text(_remark);
		}
		debugger
//		enlargeImage();
	}
	return $div.html();
}

//审核买家受损单
function changeStatus(_id,_operation){
	var title = '';
	if(_operation == '1'){
		$.messager.confirm('确认', '您确认审核通过吗？', function(r) {
			if (r) {
				$.ajax({
					url : _path + '/painting/updatePassStatus',
					data :  { id: _id, operation: _operation,reason: '' },
					type : "post",
					datatype : "json",
					success : function(data) {
							if (data.head.retCode == '000000') {console.log("ddd");
								reloadGrid();
							}else {
								$.messager.alert('错误', '操作失败！', 'error');
							}
						}
				 });
			}
		});
	}else if(_operation == '2' ){
		$.messager.prompt('审核不通过', '不通过原因', function(_reason){
			if (_reason){
				$.ajax({
					url : _path + '/painting/updatePassStatus',
					data :  { id: _id, operation: _operation,reason: _reason},
					type : "post",
					datatype : "json",
					success : function(data) {
						if (data.head.retCode == '000000') {console.log("ddd");
							reloadGrid();
						}else {
							$.messager.alert('错误', '操作失败！', 'error');
						}
					}
				 });
			}else{
				$.messager.show({
					title:'提示', 
					msg:"请输入不通过的原因！"
				});
			}
		});
	}
}

//刷新数据
function reloadGrid(){
	$('#paintingTable').datagrid('reload');
}

//function  enlargeImage(){
//	layer.config({
//		extend: [
//		'extend/layer.ext.js' 
//		],
//		shift:5, //设置动画风格
//		});
//	
//	layer.ready(function(){ //为了layer.ext.js加载完毕再执行
//	    layer.photos({
//	        photos: '._pic'
//	    });
//	}); 
//}
