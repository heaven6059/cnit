var size ;
$(function(){
	$('#requirementTable').datagrid({
		url : _path + '/requirement/list',
		columns:[[
	                {field:'ck',checkbox:"true",sortable:true,align:'center'},
	                {field:'id',title:'需求编号',align:'center',width:'70px'},
	                {	
	                	field:'loginName',
	                	title:'发布者',
	                	width:'100px',
	                	align:'center',
	                	formatter: function(value, row, index){
	                		if(row.pamAccount){
	                			return row.pamAccount.loginName;
	                		}
	                	}
	                },
	                {field:'name',title:'联系人',align:'center',width:'80px'},
	                {field:'phone',title:'联系手机',align:'center',width:'90px'},
	                {field:'email',title:'联系邮箱',align:'center',width:'130px'},
	                {field:'address',title:'联系地址',align:'center',width:'280px'},
	                {
	                	field:'createTime',
	                	align:'center',
	                	width:'180px',
	                	title:'发布时间',
                		formatter:function(value,row,index){  
                            var unixTimestamp = new Date(value).format("yyyy-MM-dd hh:mm:ss");  
                            return unixTimestamp;  
                        }  
	                },
	                {
	                	field:'action',
	                	title:'操作',
	                	align:'center',
	                	width:'80px',
	                	formatter: function(value, row, index){
		    				if(row.passStatus == "0"){
		    					return "<a href='javascript:void(0)' onclick='invalid(" + row.id +")'><span>删除</span></a>";
		    				}else{
		    					return "--";
		    				}
		    			}
	                }
	          ]],
        pageSize: 25,
	    pageList: [25,50,100,150],
		pagination : true, //分页
		rownumbers : true, //显示行号
		fitColumns : true, //自适应宽度
		checkOnSelect : true,
		multiSort : true,
		nowrap : false, //字段长度超过时不截取
		loadFilter : function(data){
			return JSON.parse(data.content);
		},
		view: detailview, //点击查看详细视图    
		detailFormatter : function(index, row) {
			return '<div style="padding:2px"><table id="detailView_' + index + '"></table></div>';  
		},
		onExpandRow : function(index, row) {
			debugger
			var _index = index;
			 $('#detailView_'+index).datagrid({
				url : _path + '/requirement/getContentList?requirementId='+row.id,
				columns:[[
			                {field:'id',hideColumn:"true"},
			                {field:'postRequirementId',hideColumn:"true"},
			                {	
			                	field:'reply',
			                	title:'询问/回复',
			                	width:'60px',
			                	align:'center',
			                	formatter: function(value, row, index){
			                		if(/^1\d{2}$/.test(row.accountType)){
			                			return "咨询：";
			                		}else if(/^2\d{2}$/.test(row.accountType)){
			                			return "回复：";
			                		}
			                	}
			                },
			                {field:'description',title:'内容',width:'600px',halign:'center'},
			                {
			                	field:'createTime',
			                	align:'center',
			                	width:'180px',
			                	title:'发布时间',
		                		formatter:function(value,row,index){  
		                            var unixTimestamp = new Date(value).format("yyyy-MM-dd hh:mm:ss");  
		                            return unixTimestamp;  
		                        }  
			                },
			                {
			                	field:'action',
			                	title:'操作',
			                	align:'center',
			                	width:'120px',
			                	formatter: function(value, row, index){
				    				if(row.disabled == "0" && /^1\d{2}$/.test(row.accountType) && index == size-1){
				    					return "<a href='javascript:void(0)' onclick='response(" + row.id +","+row.postRequirementId+",true,"+_index+")'><span>回复</span></a>" +
				    							"<a href='javascript:void(0)' onclick='response(" + row.id +","+row.postRequirementId+",false,"+_index+")'><span>屏蔽</span></a>";
				    				}else{
				    					return "--";
				    				}
				    			}
			                }
			          ]],
				rownumbers : true, //显示行号
				fitColumns : true, //自适应宽度
				checkOnSelect : true,
				nowrap : false, //字段长度超过时不截取
				loadFilter : function(data){
					size = 0;
					var arr = JSON.parse(data.content);
					var obj = {};
					size = arr.length;
					obj.total = size;
					obj.rows = arr; 
					return obj;
				},
				onResize:function(){  
					$('#data_result').datagrid('fixDetailRowHeight',index);  
				},  
				onLoadSuccess: function(){
					$('#detailView_'+index).datagrid('hideColumn','id');
					$('#detailView_'+index).datagrid('hideColumn','postRequirementId');
					$('#requirementTable').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#requirementTable').datagrid('fixDetailRowHeight', index);
		}
	});
});

//刷新数据
function reloadGrid(){
	$('#requirementTable').datagrid('reload');
};

//高级查询立即筛选
function advanceSearch() {
	var param = $('#search_form').serialize() ;
	console.log(paramObj(param));
	$('#requirementTable').datagrid('load', paramObj(param));
};

/**
 * 清楚查询
 * @returns
 */
function searchClear(){
	$(':input','#search_form')  
	.not(':button, :submit, :reset, :hidden')  
	.val('')  
	.removeAttr('checked')  
	.removeAttr('selected');
	
	$(':input[class="textbox-value"]','#search_form').val('') ;
}

function invalid(id){
	$.ajax({
		url:_path+ '/requirement/invalid',
		data:{
			id: id,
			disabled: true
		},
		success:function(data){
			if (data.head.retCode == '000000') {
				reloadGrid();
			}else {
				$.messager.alert('错误', '操作失败！', 'error');
			}
		}
	});
}


/**
 * 回复需求
 */
function response(id,requirementId,yesOrNot,_index){
	$.messager.prompt('回复', '请输入您的回复', function(reason){
		if (reason){
			$.ajax({
				url : _path + '/requirement/response',
				data :  { 
					parentId: id, 
					description: reason, 
					postRequirementId: requirementId,
					yesOrNot:yesOrNot
				},
				type : "post",
				datatype : "json",
				success : function(data) {
					if (data.head.retCode == '000000') {
						 $('#detailView_'+_index).datagrid('reload');
					}else {
						$.messager.alert('错误', '操作失败！', 'error');
					}
				}
			 });
		}
	});
}


