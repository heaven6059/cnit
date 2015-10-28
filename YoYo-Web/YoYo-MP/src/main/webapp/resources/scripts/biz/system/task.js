/**
 * 页面初始化
 */
$(document).ready(function(){
	task.loadDataGrid();//初始化列表
});

var task = {
		loadDataGrid:function(){
			$('#taskList').datagrid({
				toolbar : '#toolbar-task',
		        methord: 'post',
		        url : yoyo.mpUrl+'/task/taskList',
		        pageSize: 20,
			    pageList: [20,50,100,150],
			    pagination : true,
				rownumbers : true,
				autoRowHeight:true,
				pageSize: 25,
			    pageList: [25,50,100,150],
				singleSelect : true,
				selectOnCheck : false,
				checkOnSelect : false,
				remoteSort : true,
				multiSort : true,
				nowrap : false,
		        columns: [[
					{ field: 'jobName', title: '任务名称', width: 300, align: 'center'},
					{ field: 'cronExpression', title: '时间表达式', width: 150, align: 'center' },
					{ field: 'beanClass', title: '执行类', width: 200, align: 'center' },
					{ field: 'methodName', title: '执行方法', width: 200, align: 'center' },
					{ field: 'jobStatus', title: '状态', width: 100, align: 'center' ,formatter : function(value) {
						if(value=='0'){
							return "已停止";
						}else{
							return "正在运行";
						}
					}},
					{ field: 'createTime', title: '创建时间', width: 200, align: 'center',formatter : function(value) {
						 return  new Date(value).format("yyyy-MM-dd hh:mm:ss");
					} },
					{ field: 'updateTime', title: '更新时间', width: 200, align: 'center' ,formatter : function(value) {
						 return  new Date(value).format("yyyy-MM-dd hh:mm:ss");
					} },
					{ field: 'opt', title: '操作', width: 200, align: 'center' ,formatter : function(value, row) {
						var html="";
						if(row.jobStatus=='1'){
							html="<a href='javascript:task.optJob("+row.jobId+",1);'>执行一次</a>|<a href='javascript:task.optJob("+row.jobId+",2);'>停止</a>";
						}else{
							html="<a href='javascript:task.optJob("+row.jobId+",3);'>运行</a>";
						}
						return ""+html+"|<a href='javascript:task.editJob("+row.jobId+");'>编辑</a>";
					} }
				]],
		        onLoadSuccess: function (data) {
		           
		        }
		    });
			$('#taskList').datagrid({
				height : ($(window).height() * 0.90)
			});
		},
		optJob:function(jobId,type){
			var url=yoyo.mpUrl+"/task/runAJobNow";
			if(type=='2'){
				url=yoyo.mpUrl+"/task/pauseJob";//暂停
			}else if(type=='3'){
				url=yoyo.mpUrl+"/task/resumeJob";//恢复任务
			}
			$.ajax({
				type : 'POST',
				url : url,
				data : { "jobId":jobId},
				dataType : "JSON",
				cache : false,
				success : function(data){
					if(data.retCode=='000000'){
						alert('操作任务成功！');
						$('#taskList').datagrid('reload');
					}else{
						alert(data.retMsg);
					}
				}
			});
		},
		editJob:function(jobId){
			$(".easyui-validatebox").attr("readonly",true);
			$("#jobName").attr('disabled','disabled');
			$("#cronExpression").attr("readonly",false);
			$('#jobStatus').combobox({disabled:true}); 
			$('#dlg').dialog('open').dialog('setTitle', '编辑任务');
			$('#fm').form('load', yoyo.mpUrl+'/task/getTaskById?jobId='+jobId);  
		},
		newJob:function(){
			$('#fm').form('clear');
			$('#jobStatus').combobox({disabled:false});
			$(".easyui-validatebox").attr("readonly",false);
			$('#jobStatus').combobox('select','0');
			$('#dlg').dialog('open').dialog('setTitle', '新增任务');
		},
		updateJob:function(){
			if($("#fm").form("validate")){
				var jobStatus=$("#jobStatus").combobox('getValue');
				if(jobStatus && jobStatus=='1'){//正在运行
					var r=confirm("任务正在运行状态，确认强制修改时间吗？");
					if(r){
						task.uJob();
					}
				}else{
					task.uJob();
				}
		    }
		},
		uJob:function(){
			$('#fm').form('submit',{  
                url: yoyo.mpUrl+'/task/updateCron',  
                onSubmit: function(){  
                    return $(this).form('validate');  
                },  
                success: function(data){
                	data=jQuery.parseJSON(data);
                	if(data.retCode=='000000'){
						alert('操作任务成功！');
						$('#dlg').dialog('close');
						$('#taskList').datagrid('reload');
					}else{
						alert(data.retMsg);
					}
                }  
            });
//			$.ajax({
//				type : 'POST',
//				url :  yoyo.mpUrl+'/task/updateCron',
//				data : { "jobId":$("#jobId").val(),"cronExpression":$("#cronExpression").val()},
//				dataType : "JSON",
//				cache : false,
//				success : function(data){
//					if(data.retCode=='000000'){
//						alert('操作任务成功！');
//						$('#dlg').dialog('close');
//						$('#taskList').datagrid('reload');
//					}else{
//						alert(data.retMsg);
//					}
//				}
//			});
		}
		
};

