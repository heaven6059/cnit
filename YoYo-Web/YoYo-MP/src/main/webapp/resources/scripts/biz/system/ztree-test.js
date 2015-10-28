var branchEdit = {};
branchEdit.companyId;
branchEdit.branchId;
branchEdit.zNodes;
/**
 * 页面初始化
 */
$(document).ready(function(){
	branchEdit.branchId = $.getUrlParam('branchId');
	$("#branchId").val(branchEdit.branchId);
	branchEdit.edit();
});
branchEdit.edit = function(){
	hz.openWindow('add', '编辑');
	//填充表单
	var url=hz.basePath+"/company/getCompanyBranch/"+branchEdit.branchId;
	hz.ajaxRequest(url,null,true,"json",function(data){
		$("#companyId").val(data.companyId);
		$("#companySimpleName").textbox('setValue',data.companySimpleName);
		$("#companyName").textbox('setValue',data.companyName);
		
		$("#branchName").textbox('setValue',data.branchName); 
		$("#offerAreaId").val(data.offerAreaId);
		var str="";
		if(data.offerAreaStr){
			var offerAreaStrs=data.offerAreaStr.split("-");
			if(offerAreaStrs.length>5){
				str=offerAreaStrs[0]+","+offerAreaStrs[1]+","+offerAreaStrs[2]+","+offerAreaStrs[3]+","+offerAreaStrs[4]+"等";
			}else{
				str=data.offerAreaStr;
			}
		}
		$("#selectedCate").html(str);
		$('#defaultOffer').combobox('setValue', data.defaultOffer);
		$('#autoOffer').combobox('setValue', data.autoOffer);
		
		//初始化树形选择器
		
	});
};
branchEdit.save = function(){
	var url=hz.basePath+"/company/updateCompanyBranch";
	var data = {
		branchId : branchEdit.branchId,
		companyId :$("#companyId").val(),
		defaultOffer : $('#defaultOffer').combobox('getValue'),
		autoOffer : $('#autoOffer').combobox('getValue'),
		offerAreaId : $('#offerAreaId').val()
	}
	hz.ajaxRequest(url,data,true,"json",function(data){
		var result = data.result;
		if (result == 'fail') {
			alert("更新失败,"+data.errorMsg);
		} else {
			alert("更新成功！");
			parent.closeTab('close');
			//window.opener.companyBranch.loadDataGrid();
		}
	});
};
branchEdit.selectArea = function(){
	var url=hz.basePath+"/company/getAllArea";
	hz.ajaxRequest(url,null,true,"json",function(data){
		var offerAreaId=$("#offerAreaId").val();
		branchEdit.zNodes=data;
		branchEdit.inintArearTree();
		var treeObj = $.fn.zTree.getZTreeObj("areaTree");
		if(offerAreaId){
			var offerAreaIds=offerAreaId.split("-");
			for(var i=0;i<offerAreaIds.length;i++){
				treeObj.checkNode(treeObj.getNodeByParam("id",offerAreaIds[i], null), true, true);
			}
		}
	});
	$("#categoryWindow").window({
		modal:false,
		resizable:false,
		draggable:false,
		collapsible:false,
		closed:true,
    	maximized:false,
    	minimizable:false,
    	maximizable:false
	});
	$('#categoryWindow').window('open');
	
};
branchEdit.setting = {
		check: {
			enable: true ,
			chkStyle:"checkbox" // 添加生效
//			chkboxType :{ "Y" : "p", "N" : "p" } //Y:勾选（参数：p:影响父节点），N：不勾（参数s：影响子节点）[p 和 s 为参数]
			},
		data: { simpleData: { enable: true } },
		callback: {
			onCheck: function(event, treeId, treeNode){
//				alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
			}
		}
	};
//branchEdit.zNodes =[
//	{ id:1, pId:0, name:"父 1", open:true},
//	{ id:11, pId:1, name:"子1-1"},
//	{ id:12, pId:1, name:"子  1-2", open:true},
//	{ id:121, pId:12, name:"子1-2-1", checked:true},
//	{ id:122, pId:12, name:"子 1-2-2"},
//	{ id:123, pId:12, name:"随意勾选 1-2-3"},
//	{ id:13, pId:1, name:"随意勾选 1-3"},
//	{ id:2, pId:0, name:"随意勾选 2", open:true},
//	{ id:21, pId:2, name:"随意勾选 2-1"},
//	{ id:22, pId:2, name:"随意勾选 2-2", open:true},
//	{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
//	{ id:222, pId:22, name:"随意勾选 2-2-2"},
//	{ id:223, pId:22, name:"随意勾选 2-2-3"},
//	{ id:23, pId:2, name:"随意勾选 2-3", checked:true}
//];

branchEdit.clearCheckedOldNodes=function() {
	var zTree = $.fn.zTree.getZTreeObj("areaTree"),
	nodes = zTree.getChangeCheckedNodes();
	for (var i=0, l=nodes.length; i<l; i++) {
		nodes[i].checkedOld = nodes[i].checked;
	}
};
branchEdit.check=function(){
	var ids=[],names=[];
	var treeObj = $.fn.zTree.getZTreeObj("areaTree");//树控件
	var nodes = treeObj.getCheckedNodes(true);//获取所有选择的节点
	for(var i=0;i<nodes.length;i++){
		//-1不存在子节点 或 子节点全部设置为 nocheck = true
		//0	无 子节点被勾选
		//1	部分 子节点被勾选
		//2	全部 子节点被勾选
		var state=nodes[i].check_Child_State;
		var nodesLevel=nodes[i].level;//级别
		if(nodesLevel==0){
			if((state==2||state==-1)){
				ids.push(nodes[i].id);
				names.push(nodes[i].name);
			}
		}else if(nodesLevel==1){
			var parentState=nodes[i].getParentNode().check_Child_State;
			if((state==2||state==-1) && parentState==1){
				ids.push(nodes[i].id);
				names.push(nodes[i].name);
			}
		}else if(nodesLevel==2){
			var parentState=nodes[i].getParentNode().check_Child_State;
			if(state==-1 && parentState==1){
				ids.push(nodes[i].id);
				names.push(nodes[i].name);
			}
		}
	}
	$("#offerAreaId").val(ids.join("-"));
	var allNames='';
	if(names.length>5){
		allNames=names[0]+","+names[1]+","+names[2]+","+names[3]+","+names[4]+"等";
	}else{
		allNames=names.join(",");
	}
	$("#selectedCate").html(allNames);
	hz.closeWindow('categoryWindow');
};
branchEdit.inintArearTree=function() {
	$.fn.zTree.init($("#areaTree"), branchEdit.setting, branchEdit.zNodes);
};
branchEdit.closeWindow=function() {
	parent.closeTab('close');
};


