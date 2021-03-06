var errorMsg="系统繁忙，请稍后再试！";
var loginUrl=yoyo.portalUrl+'/register/login';
var indexUrl=yoyo.portalUrl;//店铺不存在转跳
$(function(){
	//加载角色列表
	findRoleList({refresh:true});
	//修改每页显示数
	$('.pagination-page-list').on('change', function() {
		var pageSize = $(this).val();
		findRoleList(1, pageSize);
	});
	//显示首页
	$('.pagination-first').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		findRoleList(1, pageSize);
	});
	//显示上一页
	$('.pagination-prev').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		var pageIndex = $('.pagination-num').val();
		findRoleList(parseInt(pageIndex)-1, pageSize);
	});
	//显示下一页
	$('.pagination-next').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		var pageIndex = $('.pagination-num').val();
		findRoleList(parseInt(pageIndex)+1, pageSize);
	});
	//显示末页
	$('.pagination-last').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		findRoleList(99999, pageSize);
	});
	//刷新角色列表
	$('.pagination-load').on('click', function(){
		var pageSize = $('.pagination-page-list').val();
		var pageIndex = $('.pagination-num').val();
		findRoleList(pageIndex, pageSize);
	});
	//修改当前页码
	$('input.pagination-num').on('change', function() {
		findRoleList($('.pagination-num').val(), $('.pagination-page-list').val());
	});
	//绑定编辑事件
	$('.datagrid-btable').on("click",'.edit_roles',function(){
		editRoles($(this));
	});
	//绑定删除事件
	$('.datagrid-btable').on("click",'.dele_roles',function(){
		delRoles($(this));
	});
});

//更新角色列表
function findRoleList(opts){
	var url = _path+'/roleManager/rolePage';
	var params = {};
	params.pageSize=10;
	params.pageIndex=$("#page-num").val();
	commonAjax(url, params, function(data) {
		if(data.content.result==false){
			if(data.content.isLogin==false){
				alert("请先登录！");
				window.location.href = loginUrl;
			}else if(data.content.isStoreExist==false){
				alert("该店铺不存在！");
				window.location.href = indexUrl;
			}else{
				alert(data.content.msg);
			}
		}else{
			if(data.content.rows.length>=1){
				var html=[];
				$(".datagrid-btable tr:gt(0)").remove();
				$.each(data.content.rows,function (x,item){
					html.push('<tr style="height: 25px;" class="datagrid-row" id="roles_'+item.rolesId+'">');
					html.push('<td field="storeName">'+item.rolesName+'</td>');
					html.push('<td field="lastModify">'+item.lastModifyDate+'</td>');
					html.push('<td field="action">');
					html.push('<a class="edit_roles" index="'+item.rolesId+'" href="javascript:;">修改</a>');							
					if(item.memberCount<=0){
						html.push(' <a class="dele_roles" index="'+item.rolesId+'" href="javascript:;">删除</a>');					
					}
					html.push('</td>');
					html.push('</tr>');
				});
				$(".datagrid-btable").append(html.join(""));
				if(opts&&opts.refresh){
		    		$("#Pagination").yoyoPagination(data.content.total,{
		    			items_per_page:data.content.pageSize,
		    			callback:function(){
		    				findRoleList({refresh:false});
		    			}
		    		});
		    	}
				/**
				var trs=$(".datagrid-btable").find('tr');
				for(var i=2;i<trs.length;i++){
					$(trs[i]).remove();
				}
				for(var i=0;i<data.content.rows.length;i++){
					var tr=$(".datagrid-btable").find('tr').eq(-1);
		            var tr2=tr.clone(true);
		            $(tr2).attr("id","roles_"+data.content.rows[i].rolesId);
		            tr2.find('td[field="storeName"]').text(data.content.rows[i].rolesName);		            
		            if(data.content.rows[i].memberCount>0){
		            	tr2.find('td[field="action"]').find("a[class='dele_roles']").remove();
		            }else{
		            	tr2.find('td[field="lastModify"]').text(data.content.rows[i].lastModifyDate);
		            }
		            
		            tr.after( tr2 ); 
		            if(i==0){
		            	tr.remove();
		            }
				}
				$('.pagination-num').val(data.content.pageIndex);
//				$('.pagination-page-list').find('option');
				$('span[name="sumPages"]').text("共"+data.content.pages+"页");
				$('.pagination-info').text('显示'+((data.content.pageIndex-1)*data.content.pageSize+1)+'到'+((data.content.pageIndex-1)*data.content.pageSize+data.content.currPageSize)+',共'+data.content.total+'记录');
				**/
			}else{
				var trs=$(".datagrid-btable").find('tr');
				for(var i=1;i<trs.length;i++){
					$(trs[i]).remove();
				}
				$(".datagrid-btable").append('<tr class="emptyRoles"><td colspan="3">当前无记录</td></tr>');
				$('.datagrid-pager').hide();
			}
		}
	}, function(data) {
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
}

//删除指定角色
function delRoles(ele){
	if (!confirm("您确定要删除角色吗？")){
 		return;
   	}
	var url = _path+'/roleManager/rolesDel';
	var params = {};
	params.rolesId = $(ele).attr("index");
	commonAjax(url, params, function(data) {
		if(data.content.result==false){
			if(data.content.isLogin==false){
				alert("请先登录！");
				window.location.href = loginUrl;
			}else if(data.content.isStoreExist==false){
				alert("该店铺不存在！");
				window.location.href = indexUrl;
			}else{
				alert(data.content.msg);
			}
		}else if(data.content.result == true){
			$('#addRoles').hide();
			$('.pagination-load').click();
		}
	}, function(data) {
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
}
//添加新角色
function addRole(){
	var url = _path+'/roleManager/findRolesResource';
	var params = {};
	commonAjax(url, params, function(data) {
		if(data.content.result==false){
			if(data.content.isLogin==false){
				alert("请先登录！");
				window.location.href = loginUrl;
			}else if(data.content.isStoreExist==false){
				alert("该店铺不存在！");
				window.location.href = indexUrl;
			}else{
				alert(data.content.msg);
			}
		}else{
			$('#rolesName').val("");
			$('#rolesId').val("");
			$('input[name="selectAll"]').attr("checked",false);
			if(data.content!=null&&data.content.length>=1){
				$('#resource_td').empty();
				var temp = -1;
				for(var i=0;i<data.content.length;i++){
					if(data.content[i].parentId == 0){
						temp++;
					}
					if(data.content[i].parentId==0 && temp%6==0){
						$('#resource_td').append('<div style="width:100%;float: left;height:5px;"></div>');
					}
					var mydiv = $('#resource_td').find("div.res_"+data.content[i].parentId);
					if(mydiv.length>=1){
						$(mydiv).find('ul').append('<li><input class="resourceIds" type="checkbox" value="'+data.content[i].id+'" pid="'+data.content[i].parentId+'" onclick="selectParent(this)"/>'+data.content[i].resourceName+'</li>');
					}else{
						$('#resource_td').append('<div style="width:144px;float: left;" class="res_'+data.content[i].id+'"><ul><li style="font-weight:bold;"><input class="resourceIds" type="checkbox" value="'+data.content[i].id+'" onclick="selectRes(this)"/>'+data.content[i].resourceName+'</li></ul></div>');
					}
				}
			}
		}
		$('input[value="保存"]').attr("onclick","saveRoles()");
		$('#addRoles').show();
	}, function(data) {
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
}

//全选权限菜单
function selectAllRes(ele){
	if(ele.checked==true){
		var checkboxs = $('.resourceIds');
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
		}
	}else{
		$('.resourceIds').attr("checked",false);
	}
}

//全选子菜单
function selectRes(ele){
	if(ele.checked==true){
		var checkboxs = $(ele).parent().parent().find("input[pid='"+$(ele).val()+"']");
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
		}
	}else{
		$(ele).parent().parent().find("input[pid='"+$(ele).val()+"']").attr("checked",false);
	}
}

//选择父菜单
function selectParent(ele){
	var parent = $(ele).parent().parent().find("input[value='"+$(ele).attr("pid")+"']");
	if(ele.checked==true){
		for(var i=0;i<parent.length;i++){
			parent[i].checked=true;
		}
	}else{
		var checkedBrother = $(ele).parent().parent().find("input[pid='"+$(ele).attr("pid")+"']:checked");
		if(checkedBrother.length>=1){
			for(var i=0;i<parent.length;i++){
				parent[i].checked=true;
			}
		}else{
			$(parent).attr("checked",false);
		}
	}
}

//取消添加新角色
function hideAddRoles(){
	$('#addRoles').hide();
}

//保存新角色
function saveRoles(){
	var rolesName = $('#rolesName').val();
	var checkResource = $('.resourceIds:checked');
	if(rolesName!=null&&rolesName!=''&&checkResource.length>=1){
		var resourceIds = new Array();
		for(var i = 0 ; i < checkResource.length ; i++){
			resourceIds.push($(checkResource[i]).val());
		}
		var url = _path+'/roleManager/rolesAdd';
		var params = {};
		params.rolesName = rolesName;
		params.resourceIds = "["+resourceIds.join(",")+"]";
		commonAjax(url, params, function(data) {
			if(data.content.result==true){
				alert("角色添加成功！");
				$('#addRoles').hide();
				var tr=$(".datagrid-btable").find('tr').eq(-1);
				if($(tr).attr("class")=="emptyRoles"){
					window.location.reload(true);
				}else{
					$('.pagination-load').click();
				}
			}else{
				if(data.content.isLogin==false){
					alert("请先登录！");
					window.location.href = loginUrl;
				}else if(data.content.isStoreExist==false){
					alert("该店铺不存在！");
					window.location.href = indexUrl;
				}else{
					alert(data.content.msg);
				}
			}
		}, function(data) {
			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}else{
		if(!(rolesName!=null&&rolesName!='')){
			alert("请输入角色名称");
		}else if(checkResource.length<1){
			alert("请选择权限");
		}
	}
}

//编辑角色
function editRoles(ele){
	var url = _path+'/roleManager/toRolesUpdate';
	var params = {};
	params.rolesId = $(ele).attr("index");
	commonAjax(url, params, function(data) {
		if(data.content.result==false){
			if(data.content.isLogin==false){
				alert("请先登录！");
				window.location.href = loginUrl;
			}else if(data.content.isStoreExist==false){
				alert("该店铺不存在！");
				window.location.href = indexUrl;
			}else{
				alert(data.content.msg);
			}
		}else if(data.content.result == true){
			$('#resource_td').empty();
			
			if(data.content!=null&&data.content.resourceList!=null&&data.content.resourceList.length>=1){
				var temp = -1;
				for(var i=0;i<data.content.resourceList.length;i++){
					if(data.content.resourceList[i].parentId == 0){
						temp++;
					}
					if(data.content.resourceList[i].parentId==0 && temp%6==0){
						$('#resource_td').append('<div style="width:100%;float: left;height:5px;"></div>');
					}
					var mydiv = $('#resource_td').find("div.res_"+data.content.resourceList[i].parentId);
					if(mydiv.length==1){
							$(mydiv).find('ul').append('<li><input class="resourceIds" type="checkbox" value="'+data.content.resourceList[i].id+'" pid="'+data.content.resourceList[i].parentId+'" onclick="selectParent(this)"/>'+data.content.resourceList[i].resourceName+'</li>');
					}else{
						$('#resource_td').append('<div style="width:144px;float: left;" class="res_'+data.content.resourceList[i].id+'"><ul><li style="font-weight:bold;"><input class="resourceIds" type="checkbox" value="'+data.content.resourceList[i].id+'" onclick="selectRes(this)"/>'+data.content.resourceList[i].resourceName+'</li></ul></div>');
					}
				}
			}
			var roles = data.content.roles;
			var rList = data.content.rList;
			$('#rolesName').val(roles.rolesName);
			$('#rolesId').val(roles.rolesId);
			$('input[name="selectAll"]').attr("checked",false);
			for(var j=0;j<rList.length;j++){
				var checkbox = $('input.resourceIds[value="'+rList[j].resourceId+'"]');
				$(checkbox).attr("checked",true);
			}
			$('input[value="保存"]').attr("onclick","updateRoles()");
			$('#addRoles').show();
		}
	}, function(data) {
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
}

//修改新角色
function updateRoles(){
	var rolesName = $('#rolesName').val();
	var checkResource = $('.resourceIds:checked');
	var rolesId = $('#rolesId').val();
	if(rolesName!=null&&rolesName!=''&&checkResource.length>=1&&rolesId!=null&&rolesId!=''){
		var resourceIds = new Array();
		for(var i = 0 ; i < checkResource.length ; i++){
			resourceIds.push($(checkResource[i]).val());
		}
		var url = _path+'/roleManager/rolesUpdate';
		var params = {};
		params.rolesName = rolesName;
		params.resourceIds = "["+resourceIds.join(",")+"]";
		params.rolesId = rolesId;
		commonAjax(url, params, function(data) {
			if(data.content.result==true){
				alert("角色更新成功！");
				$('#addRoles').hide();
				$('.pagination-load').click();
			}else{
				if(data.content.isLogin==false){
					alert("请先登录！");
					window.location.href = loginUrl;
				}else if(data.content.isStoreExist==false){
					alert("该店铺不存在！");
					window.location.href = indexUrl;
				}else{
					alert(data.content.msg);
				}
			}
		}, function(data) {
			easyuiMsg('失败', '请选择要操作的数据项!');
		});
	}else{
		if(!(rolesName!=null&&rolesName!='')){
			alert("请输入角色名称");
		}else if(checkResource.length<1){
			alert("请选择权限");
		}else if(!(rolesId!=null&&rolesId!='')){
			alert("该角色不存在");
		}
	}
}