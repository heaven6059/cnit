$(function() {
	debugger
	initList();
	$('#advance_search_news').dialog({
		closed:true,
		modal:false,
		cache:false,
		buttons:'#search_btn',
		onBeforeClose:function(){
			searchClear();
		}
	});
});

function initList(){
	$('#table-news').datagrid({
		url : _path + '/news/newsList',
		columns : [ [ {
			field:'ck',
			checkbox:"true",
			width:'2%'
		},{
			field : 'newsId',
			align : 'center',
			hidden: 'true',
			title : '新闻id'			
		},{
			field : 'title',
			align : 'center',
			title : '新闻标题',
			sortable: true,
			width: "20%"
		}, {
			field : 'author',
			align : 'center',
			title : '作者',
			sortable: true,
			width: "10%"
		},{
			field : 'type',
			align : 'center',
			title : '分类',
			sortable: true,
			width: "5%",
			formatter: function(value,rows,index){
				if(value){
					return "公告";
				}else{
					return "特惠";
				}
			}
		}, {
			field : 'pubtime',
			align : 'center',
			title : '发布日期',
			width: "10%",
			formatter: function(value,rows,index){
				if(value){
					var time=new Date(value);
	    			return time.format("yyyy-MM-dd hh:mm:ss");
				}
			}
		},{
			field: 'editor',
			align: 'center',
			title: '操作',
			width: '5%',
			formatter: function(value,rows,index){
				return "<a href='javascript:void(0)' class='btn-grey' onclick='editNews("+rows.newsId+")'>编辑</a>&nbsp;";
			}
		}]],
		toolbar : '#toolbar-news',
		pagination : true,
		rownumbers : true,
		striped:true,
		fitColumns : true,
		pageSize : 25,
		pageList : [ 25, 50, 100, 150 ],
		selectOnCheck:true,
		remoteSort : false,
		multiSort : true,
		loadFilter : function(data) {
			debugger
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
}

//删除
function deleteNews(){
	debugger
	var ids = [];
	var rows = $('#table-news').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].newsId);
	}
	var param={};
	param.ids = ids
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/news/removeNews',
		    	    type:"post",
		    	    data:JSON.stringify(param),
		    	    dataType:"json",
		    	    contentType:"application/json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			$('#table-news').datagrid('reload');
			    		} else if (_data.head.retCode == '000001'){
							easyuiMsg('错误','请选择需要操作的数据项！');
						} else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    }
		}
	);
}

/**
 * 打开高级查询
 */
function openSeachWindow() {
	var width = $(window).width(); // 屏幕的宽度
	$('#advance_search_news').dialog('open').window('resize',{ width : '450px' , height : '500px' , top : 0 , left : (width - 450) });
}
//清空高级选项条件
function clearCondition(){
	$("#advance_search_article").form('clear');
	$("#search-combox-article-nodes").val(-1).trigger("change");
	$('#table-news').datagrid('reload');
}

function addNews(){
	var url = "/news/toEditNews?type=add";
	window.parent.addTabs(url,'新增新闻');
}

function editNews(newsId){
	var url = "/news/toEditNews?type=edit&newsId="+newsId;
	window.parent.addTabs(url,'编辑新闻');
}

function advanceSearch(){
	if($('input[name="startDate"]').val() > $('input[name="endDate"]').val()){
		$.messager.alert('提示', '起始时间不能大于结束时间！', 'error');
	}else{
		advanceQuery('table-news','news_search_form');
	}
}


function searchClear(){
	$(':input','#news_search_form')  
	.not(':button, :submit, :reset, :hidden')  
	.val('')  
	.removeAttr('checked')  
	.removeAttr('selected');
	
	$(':input[class="textbox-value"]','#news_search_form').val('') ;
}

function cleanAndSearch(){
	searchClear();
	debugger
	$('#table-news').datagrid('reload',{});
}
