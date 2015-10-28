$(function (){
	$('#score_list_dataGrid').datagrid({
		url : _path+'/cyberauction/loadCyberauction',
		columns : [[
		{	
			field : 'name' ,
			title : '活动名称'			
		} , 
		{
			field : 'startTime' ,
			title : '活动开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		} , {
			field : 'endTime' ,
			title : '活动结束时间',
			formatter: function(value,rows,index){
				if(value){
					var createDate=new Date();					
	    			createDate.setTime(value);	
	    			return createDate.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		} , {
			field : 'applyStartTime' ,
			title : '申请开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		} , {
			field : 'applyEndTime' ,
			title : '申请结束时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm");//引用YoYo-MP中common.js
				}
			}
		} , {
			field : 'actOpen' ,
			title : '是否开启' ,
			formatter: function(value,rows,index){
				if(value==1){
					return "开启";
				}
				return "关闭";
			}
		}]],
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		toolbar : '#toolbar',
		pagination : true ,
		rownumbers : true ,
		fitColumns : true ,
		remoteSort : false ,
		multiSort : true ,
		ctrlSelect : true ,
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		onDblClickCell : function(index , field , value){
			var record = $(this).datagrid('getSelected');
			$("#scoreBuyDialog").dialog({
			    width: 805,
			    height: 533,
			    href: _path+'/cyberauction/cyberauction?op=edit&id='+record.actId,
			    modal: true,
			    closed: true,
			    title:'积分换购',
			    closed:true,
			    cache:false,
			    draggable:false
			});
			$('#scoreBuyDialog').dialog('open');
		}
	});

	
	$("#addScoreBuy").click(function (){
		$("#scoreBuyDialog").dialog({
		    width: 805,
		    height: 533,
		    href: _path+'/cyberauction/cyberauctionDetail',
		    modal: true,
		    closed: true,
		    title:'积分换购',
		    closed:true,
		    cache:false,
		    draggable:false
		});
		$('#scoreBuyDialog').dialog('open');
	});
});

