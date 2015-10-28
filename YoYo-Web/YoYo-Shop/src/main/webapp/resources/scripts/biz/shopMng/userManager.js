var errorMsg="系统繁忙，请稍后再试！";
var loginUrl=yoyo.memUrl+'/register/login';
//var indexUrl=yoyo.memUrl;//店铺不存在转跳
$(function(){
	//加载用户列表
	findRoleList(1,$('.pagination-page-list').val());
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
	//刷新用户列表
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
	$('.edit_roles').on("click",function(){
		editRoles($(this));
	});
	//绑定删除事件
	$('.dele_roles').on("click",function(){
		delRoles($(this));
	});
});

//更新用户列表
function findRoleList(pageIndex, pageSize){
	var url = _path+'/userManager/userPage';
	var params = {};
	params.pageSize=pageSize;
	params.pageIndex=pageIndex;
	commonAjax(url, params, function(data) {
		if(data.content.rows.length>=1){
			var trs=$(".datagrid-btable").find('tr');
			for(var i=2;i<trs.length;i++){
				$(trs[i]).remove();
			}
			for(var i=0;i<data.content.rows.length;i++){
				var tr=$(".datagrid-btable").find('tr').eq(-1);
	            var tr2=tr.clone(true);
	            $(tr2).attr("id","account_"+data.content.rows[i].accountId);
	            tr2.find('td[field="loginName"]').text(data.content.rows[i].loginName); 
	            tr2.find('td[field="roleName"]').text(data.content.rows[i].roleName); 
	            tr2.find('td[field="action"] > a').attr("index",data.content.rows[i].accountId);
	            tr.after( tr2 ); 
	            if(i==0){
	            	tr.remove();
	            }
			}
			$('.pagination-num').val(data.content.pageIndex);
//				$('.pagination-page-list').find('option');
			$('span[name="sumPages"]').text("共"+data.content.pages+"页");
			$('.pagination-info').text('显示'+((data.content.pageIndex-1)*data.content.pageSize+1)+'到'+((data.content.pageIndex-1)*data.content.pageSize+data.content.currPageSize)+',共'+data.content.total+'记录');
		}else{
			var trs=$(".datagrid-btable").find('tr');
			for(var i=1;i<trs.length;i++){
				$(trs[i]).remove();
			}
			$(".datagrid-btable").append('<tr class="emptyRoles"><td colspan="3">当前无记录</td></tr>');
			$('.datagrid-pager').hide();
		}
	}, function(data) {
		easyuiMsg('失败', '请选择要操作的数据项!');
	});
}

//删除指定用户
function delRoles(ele){
	if (!confirm("您确定要删除用户吗？")){
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


//获得用户数据
function getRoleData(url,obj){
	$.getJSON(url, function(json) {
		var data="";
		data+='[{ "text": "--请选择--", "id": ""   }';
		$.each(json.content, function(i, n) {
			data+=',{ "text": "'+n.rolesName+'", "id":'+n.rolesId+'}';
		});
		data+="]";
		
		$("#"+obj).combobox("loadData", $.parseJSON(data));
	});
}

//添加新用户
function addUser(){
	getRoleData(biz.rootPath() + '/userManager/findSelect?companyId=0','roleId');
	$('#addRoles').show();
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

//取消添加新用户
function hideAddRoles(){
	$('#addRoles').hide();
}

//保存用户
function saveUser(){
	var loginName = $('#loginName').val();
	var loginPassword = $('#loginPassword').val();
	var roleId = $("#roleId").combo('getValue');
	//var accountId = $('#accountId').val();
	alert("name="+loginName+",passwod="+loginPassword+",role="+roleId);
	if(loginName!=null&&loginName!=''&&loginPassword!=null&&loginPassword!=''&&roleId!=null&&roleId!=''){
		var url = _path+'/userManager/userAdd';
		var params = {};
		params.loginName = loginName;
		params.loginPassword = loginPassword;
		params.roleId = roleId;
		//params.accountId = 1;
		commonAjax(url, params, function(data) {
			if(data.content.result==true){
				alert("用户添加成功！");
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
		if(loginName==null||loginName==''){
			alert("请输入用户名称");
		}else if(loginPassword==null||loginPassword==''){
			alert("请输入密码");
		}else if(roleId==null||roleId==''){
			alert("请选择角色");
		}
	}
}

//编辑用户
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
			for(var i=0;i<data.content.resourceList.length;i++){
				if(i==6){
					$('#resource_td').append('<div style="width:100%;float: left;height:5px;"></div>');
				}
				var mydiv = $('#resource_td').find("div.res_"+data.content.resourceList[i].parentId);
				if(mydiv.length==1){
						$(mydiv).find('ul').append('<li><input class="resourceIds" type="checkbox" value="'+data.content.resourceList[i].id+'"/>'+data.content.resourceList[i].resourceName+'</li>');
				}else{
					$('#resource_td').append('<div style="width:144px;float: left;" class="res_'+data.content.resourceList[i].id+'"><ul><li style="font-weight:bold;">'+data.content.resourceList[i].resourceName+'</li></ul></div>');
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

//修改新用户
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
				alert("用户更新成功！");
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
			alert("请输入用户名称");
		}else if(checkResource.length<1){
			alert("请选择权限");
		}else if(!(rolesId!=null&&rolesId!='')){
			alert("该用户不存在");
		}
	}
}